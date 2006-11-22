/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.editors.palette.impl;

import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDDocument;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDElementDeclaration;
import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.IJMTConstants;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.common.utils.ResourceUtils;
import org.eclipse.jst.pagedesigner.common.utils.StringUtil;
import org.eclipse.jst.pagedesigner.editors.palette.IPaletteConstants;
import org.eclipse.jst.pagedesigner.editors.palette.IPaletteItemCategory;
import org.eclipse.jst.pagedesigner.editors.palette.IPaletteItemDescriptor;
import org.eclipse.jst.pagedesigner.editors.palette.IPaletteItemManager;
import org.eclipse.wst.html.core.internal.contentmodel.HTMLElementDeclaration;
import org.eclipse.wst.xml.core.internal.contentmodel.CMDocument;
import org.eclipse.wst.xml.core.internal.contentmodel.CMElementDeclaration;
import org.eclipse.wst.xml.core.internal.contentmodel.CMNamedNodeMap;
import org.osgi.framework.Bundle;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Helper class.
 * 
 * @author mengbo
 */
public class PaletteHelper {
	public static void configPaletteItemsByTLD(IPaletteItemManager manager,
			TLDDocument tldDoc) {
		String tldURI = tldDoc.getUri();
		if (tldURI == null) {
			return;
		}
		IPaletteItemCategory category = manager.getCategoryByURI(tldURI);
		if (category == null) {
			category = manager.createCategory(tldURI);
		}
		// category is in what user selected in Component Support Property Page
		// ,so set state to be Visible
		category.setVisible(true);

		String prefix = category.getDefaultPrefix();
		if (prefix == null || prefix.length() == 0) {
			category.setDefaultPrefix(tldDoc.getShortname());
		}
		String label = category.getLabel();
		if (label == null || label.length() == 0) {
			category.setLabel(tldDoc.getDisplayName());
		}
		loadFromCMDocument(category, tldDoc);
	}

	/**
	 * basically, this method will read information from the CMDocument. It will
	 * check the existing items in the registry. If the corresponding tag is not
	 * in palette manager, then it will create one, and mark the newly created
	 * item as "expert". Otherwise, it will check whether the tld contains more
	 * information than the palette manager, and adding those information to it
	 * (such as description, icons for tags)
	 * 
	 * @param manager
	 * @param tldDoc
	 */
	public static void loadFromCMDocument(IPaletteItemCategory category,
			CMDocument cmdoc) {
		CMNamedNodeMap nodeMap = cmdoc.getElements();
		for (int i = 0, size = nodeMap.getLength(); i < size; i++) {
			CMElementDeclaration eledecl = (CMElementDeclaration) nodeMap
					.item(i);
			String tagName = eledecl.getElementName();
			IPaletteItemDescriptor item;
			if (tagName.equalsIgnoreCase(IHTMLConstants.TAG_INPUT)) {
				StringBuffer name = new StringBuffer(category.getURI());
				name.append(":").append(tagName).append(":").append(tagName);
				item = category.getItemByID(name.toString());
			} else {
				item = category.getItemByTagName(tagName);
			}
			if (item == null) {
				item = category.createItem(tagName);
				StringBuffer name = new StringBuffer(category.getURI());
				name.append(":").append(tagName).append(":").append(tagName);
				item.setId(name.toString());
				// for newly created item, set expert to true.
				item.setVisible(true);
				item.setURI(category.getURI());
				if (category.getURI().equals(IJMTConstants.URI_HTML))//$NON-NLS-1$
				{
					item.setVisible(false);
				}
			}
			configItem(item, eledecl);
			item.setDefaultPrefix(category.getDefaultPrefix());
		}
	}

	/**
	 * @param item
	 * @param eledecl
	 */
	private static void configItem(IPaletteItemDescriptor item,
			CMElementDeclaration eledecl) {
		if (eledecl instanceof HTMLElementDeclaration) {
			// TODO: does nothing: configHTMLItem(item, (HTMLElementDeclaration) eledecl);
		} else if (eledecl instanceof TLDElementDeclaration) {
			configTLDItem(item, (TLDElementDeclaration) eledecl);
		}
	}

	/**
	 * @param item
	 * @param declaration
	 */
	private static void configTLDItem(IPaletteItemDescriptor item,
			TLDElementDeclaration declaration) {
		String olddesc = item.getDescription();
		if (olddesc == null || olddesc.length() == 0) {
			String des = declaration.getDescription();
			des = StringUtil.filterConvertString(des);
			item.setDescription(des);
		}
	}

	/**
	 * @param item
	 * @param declaration
	 */
//	private static void configHTMLItem(IPaletteItemDescriptor item,
//			HTMLElementDeclaration declaration) {
//        // TODO: does nothing
//	}

	public static void readConfigFromPlugin(IPaletteItemManager manager) {
		IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
		IExtensionPoint point = extensionRegistry
				.getExtensionPoint(PDPlugin.getPluginId(),
						IJMTConstants.EXTENSION_POINT_PALETTEITEMCONFIG);
		if (point != null) {
			IConfigurationElement[] elements = point.getConfigurationElements();
			Arrays.sort(elements, new Comparator() {
				/*
				 * (non-Javadoc)
				 * 
				 * @see java.util.Comparator#compare(java.lang.Object,
				 *      java.lang.Object)
				 */
				public int compare(Object o1, Object o2) {
					IConfigurationElement d1 = (IConfigurationElement) o1;
					IConfigurationElement d2 = (IConfigurationElement) o2;
					return d1
							.getAttribute(
									IJMTConstants.ATTRIBUTE_INDEX_PALETTEITEMCONFIG)
							.compareToIgnoreCase(
									d2
											.getAttribute(IJMTConstants.ATTRIBUTE_INDEX_PALETTEITEMCONFIG));
				}
			});
			for (int i = 0, size = elements.length; i < size; i++) {
				IConfigurationElement ce = elements[i];
				String id = ce.getDeclaringExtension().getNamespaceIdentifier();
				Bundle bundle = Platform.getBundle(id);
				String path = ce
						.getAttribute(IJMTConstants.ATTRIBUTE_PATH_PALETTEITEMCONFIG);
				readConfig(manager, bundle, path);
			}
		}

	}

	static void readConfig(IPaletteItemManager manager, Bundle bundle,
			String path) {
		try {
			if (bundle.getEntry(path) == null) {
				return;
			}
			InputStream stream = bundle.getEntry(path).openStream();
			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document doc = builder.parse(stream);
			ResourceUtils.ensureClosed(stream);

			NodeList categories = doc
					.getElementsByTagName(IPaletteConstants.CATEGORY_TAG);
			for (int i = 0, ilength = categories.getLength(); i < ilength; i++) {
				Element cat = (Element) categories.item(i);
				String prefix = cat.getAttribute(IPaletteConstants.ICONPREFIX);
				String uri = cat.getAttribute(IPaletteConstants.URI);
				String catlabel = cat.getAttribute(IPaletteConstants.LABEL);
				IPaletteItemCategory category = manager.findOrCreateCategory(
						uri, catlabel);
				String jsfComponentCategory = cat
						.getAttribute(IPaletteConstants.JSFCOMPONENTCATEGORY);
				boolean bJSF = Boolean.TRUE.toString().equalsIgnoreCase(
						jsfComponentCategory);
				if (bJSF) {
					// avoid override. maybe someother place has already set it
					// to be true.
					category.setJSFComponentCategory(bJSF);
				}

				NodeList items = cat
						.getElementsByTagName(IPaletteConstants.ITEM_TAG);

				for (int j = 0, jlength = items.getLength(); j < jlength; j++) {
					Element item = (Element) items.item(j);
					PaletteItemDescriptor descriptor = new PaletteItemDescriptor();
					descriptor.setJSFComponent(category
							.isJSFComponentCategory());
					descriptor.setURI(uri);

					String tagName = item
							.getAttribute(IPaletteConstants.TAGNAME);
					descriptor.setTagName(tagName);

					String label = item.getAttribute(IPaletteConstants.LABEL);
					descriptor.setLabel(label);

					StringBuffer id = new StringBuffer(uri);
					id.append(":").append(tagName).append(":").append(label);
					descriptor.setId(id.toString());

					String shortDesc = item
							.getAttribute(IPaletteConstants.SHORTDESC);
					descriptor.setDescription(shortDesc);

					String smallIcon = item
							.getAttribute(IPaletteConstants.SMALLICON);
					descriptor.setSmallIcon(getImageDescriptor(bundle, prefix,
							smallIcon));

					String largeIcon = item
							.getAttribute(IPaletteConstants.LARGEICON);
					descriptor.setLargeIcon(getImageDescriptor(bundle, prefix,
							largeIcon));

					String expert = item.getAttribute(IPaletteConstants.EXPERT);
					boolean isVisible = !Boolean.TRUE.toString()
							.equalsIgnoreCase(expert); //$NON-NLS-1$
					descriptor.setVisible(isVisible);

					String requireHForm = item
							.getAttribute(IPaletteConstants.REQUIREHFORM);
					boolean r = Boolean.TRUE.toString().equalsIgnoreCase(
							requireHForm);
					descriptor.setRequireHForm(r);

					NodeList attributes = item
							.getElementsByTagName(IPaletteConstants.ATTRIBUTE_TAG);
					if (attributes.getLength() > 0) {
						Map attrMap = new HashMap();
						for (int k = 0, klength = attributes.getLength(); k < klength; k++) {
							Element attr = (Element) attributes.item(k);
							String name = attr
									.getAttribute(IPaletteConstants.NAME);
							String value = attr
									.getAttribute(IPaletteConstants.VALUE);
							attrMap.put(name, value);
						}
						descriptor.setInitialAttributes(attrMap);
					}

					descriptor.setTemplateSubNodes(PaletteElementTemplateHelper
							.readTemplate(item));
					category.addPaletteItem(descriptor);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * @param plugin
	 * @param prefix
	 * @param smallIcon2
	 * @return
	 */
	private static ImageDescriptor getImageDescriptor(Bundle bundle,
			String prefix, String u) {
		if (u == null || u.length() == 0) {
			return null;
		}
		String path = (prefix == null ? "" : prefix) + u;
		URL url = bundle.getEntry(path);
		return ImageDescriptor.createFromURL(url);
	}
}
