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
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.jsf.common.metadata.internal.IImageDescriptorProvider;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataSourceModelProvider;
import org.eclipse.jst.jsf.common.metadata.internal.TraitValueHelper;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.MetadataFactory;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.Model;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.query.IMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.query.MetaDataQueryHelper;
import org.eclipse.jst.jsf.common.ui.internal.utils.ResourceUtils;
import org.eclipse.jst.jsf.common.ui.internal.utils.StringUtil;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.TLDCMDocumentManager;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDDocument;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDElementDeclaration;
import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.IJMTConstants;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.editors.palette.IPaletteConstants;
import org.eclipse.jst.pagedesigner.editors.palette.IPaletteItemManager;
import org.eclipse.jst.pagedesigner.editors.palette.TagToolPaletteEntry;
import org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.provisional.PaletteInfo;
import org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.provisional.PaletteInfos;
import org.eclipse.wst.html.core.internal.contentmodel.HTMLCMDocument;
import org.eclipse.wst.html.core.internal.contentmodel.JSPCMDocument;
import org.eclipse.wst.xml.core.internal.contentmodel.CMDocument;
import org.eclipse.wst.xml.core.internal.contentmodel.CMElementDeclaration;
import org.eclipse.wst.xml.core.internal.contentmodel.CMNamedNodeMap;
import org.eclipse.wst.xml.core.internal.provisional.contentmodel.CMDocType;
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
	private final static ImageDescriptor DEFAULT_SMALL_ICON = PDPlugin
	.getDefault().getImageDescriptor(
			"palette/GENERIC/small/PD_Palette_Default.gif");

private final static ImageDescriptor DEFAULT_LARGE_ICON = PDPlugin
	.getDefault().getImageDescriptor(
			"palette/GENERIC/large/PD_Palette_Default.gif");

//	public static void configPaletteItemsByTLDOLD(IPaletteItemManager manager,
//			TLDDocument tldDoc) {
//		
//		String tldURI = tldDoc.getUri();
//		if (tldURI == null) {
//			return;
//		}
//		TaglibPaletteDrawer category = findCategory(manager, tldURI);
//		if (category != null) 
//			return;
//		
//		
//		// category is in what user selected in Component Support Property Page
//		// ,so set state to be Visible
//		category.setVisible(true);
//		category.setInitialState(PaletteDrawer.INITIAL_STATE_CLOSED);
//		
//		String prefix = category.getDefaultPrefix();
//		if (prefix == null || prefix.length() == 0) {
//			category.setDefaultPrefix(tldDoc.getShortname());
//		}
//		String label = category.getLabel();
//		if (label == null || label.length() == 0) {
//			label = tldDoc.getDisplayName();
//			if (label == null || label.length() == 0)
//				label = tldURI;
//			category.setLabel(label);
//		}
//		String desc = category.getDescription();
//		if (desc == null || desc.length() == 0) {
//			desc = tldDoc.getDescription();
//			if (desc == null || desc.length() == 0) 
//				desc = tldURI;
//			category.setDescription(desc);
//		}
//		loadFromCMDocument(category, tldDoc);
//		
//	}
	

	public static void configPaletteItemsByTLD(IPaletteItemManager manager, IProject project,
			CMDocument doc) {
//bit of a hack... could be greatly improved		
		String tldURI = null;
		if (doc instanceof TLDDocument){
			tldURI = ((TLDDocument)doc).getUri();
		}
		else if (doc instanceof HTMLCMDocument){
			tldURI = CMDocType.HTML_DOC_TYPE;
		}
		else if (doc instanceof JSPCMDocument){
			tldURI = CMDocType.JSP11_DOC_TYPE;
		}
		
		if (tldURI == null) 
			return;
			
		TaglibPaletteDrawer category = findCategory(manager, tldURI);
		if (category != null) 
			return;
		
		IMetaDataModelContext modelContext = MetaDataQueryHelper.createMetaDataModelContext(project, MetaDataQueryHelper.TAGLIB_DOMAIN, tldURI);
		Model model = MetaDataQueryHelper.getModel(modelContext);
		category = createTaglibPaletteDrawer(manager, doc, model);
		
		if (category != null){		
			loadTags(category, doc, model);			
		}
	}

	private static void loadTags(TaglibPaletteDrawer category,
			CMDocument doc,Model model) {
		
		if (model != null) {
			Trait trait = MetaDataQueryHelper.getTrait(model, "paletteInfos");
			if (trait != null){
				PaletteInfos tags = (PaletteInfos)trait.getValue();
				for (Iterator it=tags.getInfos().iterator();it.hasNext();){
					PaletteInfo tag = (PaletteInfo)it.next();
					createTagEntry(category, tag);
				}
			} else {
				for (Iterator it=model.getChildEntities().iterator();it.hasNext();){
					Entity tagAsEntity = (Entity)it.next();
					createTagEntry(category, tagAsEntity);
				}
			}
		}
		else {
			loadFromCMDocument(category, doc);
		}
			
	}

	private static TaglibPaletteDrawer createTaglibPaletteDrawer(IPaletteItemManager manager,
			CMDocument doc, Model model) {
		
		TaglibPaletteDrawer	 category = null;
		if (model != null){
			//do we create it?
			boolean isHidden = getBooleanTagTraitValue(model, "hidden", false);			
			if (isHidden){
				return null;
			}
						
			String label = getStringTagTraitValue(model, "display-label", model.getId());
			label = label.equals("") ? model.getId() : label;
			category = manager.createTaglibPaletteDrawer(model.getId(), label);
			
			String desc = getStringTagTraitValue(model, "description", model.getId());
			category.setDescription(StringUtil.filterConvertString(desc));
			
			ImageDescriptor largeIconImage = getImageDescriptorFromTagTraitValueAsString(model, "small-icon", null);
			if (largeIconImage != null)
				category.setLargeIcon(largeIconImage);
			
			
			String prefix = getStringTagTraitValue(model, "default-prefix", null);
			category.setDefaultPrefix(prefix);
			
			boolean isVisible = !(getBooleanTagTraitValue(model, "expert", false));
			category.setVisible(isVisible);
			
			category.setInitialState(PaletteDrawer.INITIAL_STATE_CLOSED);
		
		}
//		else { //shouldn't get here!
//			category = manager.createTaglibPaletteDrawer(tldDoc.getUri(), tldDoc.getDescription());
//
//			category.setVisible(true);
//			category.setInitialState(PaletteDrawer.INITIAL_STATE_CLOSED);
//			
//			String prefix = category.getDefaultPrefix();
//			if (prefix == null || prefix.length() == 0) {
//				category.setDefaultPrefix(tldDoc.getShortname());
//			}
//			String label = category.getLabel();
//			if (label == null || label.length() == 0) {
//				label = tldDoc.getDisplayName();
//				if (label == null || label.length() == 0)
//					label = tldDoc.getUri();
//				category.setLabel(label);
//			}
//			String desc = category.getDescription();
//			if (desc == null || desc.length() == 0) {
//				desc = tldDoc.getDescription();
//				if (desc == null || desc.length() == 0) 
//					desc = tldDoc.getUri();
//				category.setDescription(desc);
//			}
//		}
		return category;
	}

	private static TaglibPaletteDrawer findCategory(IPaletteItemManager manager,
			String tldURI) {
		TaglibPaletteDrawer lib = null;
		for (Iterator it = manager.getAllCategories().iterator();it.hasNext();){
			lib = (TaglibPaletteDrawer)it.next();
			if (tldURI.equals(lib.getURI()))
				return lib;					
		}
		return null;
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
	public static void loadFromCMDocument(TaglibPaletteDrawer category,
			CMDocument cmdoc) {
		CMNamedNodeMap nodeMap = cmdoc.getElements();
		for (int i = 0, size = nodeMap.getLength(); i < size; i++) {
			CMElementDeclaration eledecl = (CMElementDeclaration) nodeMap
					.item(i);
			String tagName = eledecl.getElementName();
			TagToolPaletteEntry item;
			if (tagName.equalsIgnoreCase(IHTMLConstants.TAG_INPUT)) {//TODO:  fix this nonsense!
				StringBuffer name = new StringBuffer(category.getURI());
				name.append(":").append(tagName).append(":").append(tagName);
				item = category.getTagPaletteEntryById(name.toString());
			} else {
				item = category.getTagPaletteEntryByTagName(tagName);
			}
			if (item == null) {
				createTagEntry(category, eledecl);

			}
//			configItem(item, eledecl);
//			item.setDefaultPrefix(category.getDefaultPrefix());
		}
	}
	
	private static void createTagEntry(TaglibPaletteDrawer category,
			PaletteInfo info) {
		
		Boolean hidden = info.getHidden();
		if ((hidden != null) && (hidden.booleanValue()))//do not create a palette entry
			return; 
		
		IMetaDataSourceModelProvider sourceProvider = ((Trait)info.eContainer().eContainer()).getSourceModelProvider();
		String tagName = info.getTag();
		String id = info.getId();		
		String label = info.getDisplayLabel();
		String desc = StringUtil.filterConvertString(info.getDescription());		
		ImageDescriptor smallIcon = getImageDescriptorFromString(sourceProvider, info.getSmallIcon(), DEFAULT_SMALL_ICON);
		ImageDescriptor largeIcon = getImageDescriptorFromString(sourceProvider, info.getLargeIcon(), DEFAULT_LARGE_ICON);
		Boolean expert = info.getExpert();
				
		internalCreateTagEntry(category, id, tagName, label, desc, smallIcon, largeIcon, (expert !=null && expert.booleanValue()));		
		
	}

	private static void createTagEntry(TaglibPaletteDrawer category,
			Entity entity) {
		
		boolean hidden = getBooleanTagTraitValue(entity, "hidden", false);
		if (hidden)//do not create a palette entry
			return; 
		
		String tagName = entity.getId();
		String label = getStringTagTraitValue(entity, "display-label", tagName);
		String desc = StringUtil.filterConvertString(getStringTagTraitValue(entity, "description", tagName));		
		ImageDescriptor smallIcon = getImageDescriptorFromTagTraitValueAsString(entity, "small-icon", DEFAULT_SMALL_ICON);
		ImageDescriptor largeIcon = getImageDescriptorFromTagTraitValueAsString(entity, "large-icon", DEFAULT_LARGE_ICON);
		boolean expert = getBooleanTagTraitValue(entity, "expert", false);
				
		internalCreateTagEntry(category, tagName, tagName, label, desc, smallIcon, largeIcon, expert);
		
	}

	private static TagToolPaletteEntry internalCreateTagEntry(TaglibPaletteDrawer category, String id, String tagName, String label, String desc, ImageDescriptor smallIcon, ImageDescriptor largeIcon, boolean expert){
		TagToolPaletteEntry item = new TagToolPaletteEntry(tagName, label, desc, smallIcon, largeIcon);
//		StringBuffer name = new StringBuffer(category.getURI());
//		name.append(":").append(tagName).append(":").append(tagName);
		item.setId(id);
		
		item.setVisible(!expert);
		category.getChildren().add(item);
		item.setParent(category);
		
		return item;
	}

	private static boolean getBooleanTagTraitValue(Entity entity,
			String key, boolean defaultValue) {
		Trait trait = MetaDataQueryHelper.getTrait(entity, key);
		if (trait != null){
			return TraitValueHelper.getValueAsBoolean(trait);
		}
		return defaultValue;	
	}

	private static String getStringTagTraitValue(Entity entity, String key, String defaultValue){
		Trait trait = MetaDataQueryHelper.getTrait(entity, key);
		if (trait != null){
			return TraitValueHelper.getValueAsString(trait);
		}
		return defaultValue;		
	}

	private static ImageDescriptor getImageDescriptorFromTagTraitValueAsString(Entity entity, String key, ImageDescriptor defaultValue){
		Trait t = MetaDataQueryHelper.getTrait(entity, key);
		if (t != null){
			String imgDesc = TraitValueHelper.getValueAsString(t);
			return getImageDescriptorFromString(t.getSourceModelProvider(), imgDesc, defaultValue);
		}
		return defaultValue;
	}
	
	private static ImageDescriptor getImageDescriptorFromString(IMetaDataSourceModelProvider sourceModelProvider,  String imgDesc, ImageDescriptor defaultValue){
		ImageDescriptor image = defaultValue;
		IImageDescriptorProvider imageProvider = (IImageDescriptorProvider)sourceModelProvider.getAdapter(IImageDescriptorProvider.class);			
		if (imageProvider != null){
			image = imageProvider.getImageDescriptor(imgDesc);
		}
		return image;
	}
	
	private static void createTagEntry(TaglibPaletteDrawer category,
			CMElementDeclaration eledecl) {
		
		String tagName = eledecl.getElementName();
		String label = null;
		String desc = null;
		ImageDescriptor smallIcon = null;

		if (eledecl instanceof TLDElementDeclaration){
			TLDElementDeclaration tag = (TLDElementDeclaration)eledecl;			
			label = tag.getDisplayName();			
			desc = tag.getDescription();						
		}
		else {
			
		}
		if (label == null || label.equals(""))
			label = tagName;
		
		if (desc == null )
			desc = "";
		else
			desc = StringUtil.filterConvertString(desc);
		
		TagToolPaletteEntry item = internalCreateTagEntry(category, tagName, tagName, label, desc, getDefaultSmallIcon(), getDefaultLargeIcon(), false);
		item.setToolProperty("CMElementDeclaration", eledecl);
//		TagToolPaletteEntry item = new TagToolPaletteEntry(tagName, label, desc, getDefaultSmallIcon(), getDefaultLargeIcon());
//		StringBuffer name = new StringBuffer(category.getURI());
//		name.append(":").append(tagName).append(":").append(tagName);//what is this?
//		item.setId(name.toString());
//		category.getChildren().add(item);
//		item.setParent(category);
//		// for newly created item, set expert to true.
//		item.setVisible(true);
//		if (category.getURI().equals(IJMTConstants.URI_HTML))//$NON-NLS-1$
//		{
////			item.setVisible(false);
//		}
		
	}
//
//	/**
//	 * @param item
//	 * @param eledecl
//	 */
//	private static void configItem(TagToolPaletteEntry item,
//			CMElementDeclaration eledecl) {
//		if (eledecl instanceof HTMLElementDeclaration) {
//			// TODO: does nothing: configHTMLItem(item, (HTMLElementDeclaration) eledecl);
//		} else if (eledecl instanceof TLDElementDeclaration) {
//			configTLDItem(item, (TLDElementDeclaration) eledecl);
//		}
//	}

//	/**
//	 * @param item
//	 * @param declaration
//	 */
//	private static void configTLDItem(TagToolPaletteEntry item,
//			TLDElementDeclaration declaration) {
//		String olddesc = item.getDescription();
//		if (olddesc == null || olddesc.length() == 0) {
//			String des = declaration.getDescription();
//			des = StringUtil.filterConvertString(des);
//			item.setDescription(des);
//		}
//	}

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

	static void readConfig(IPaletteItemManager manager, Bundle bundle, String path) {
		
		
		
	}
	
	static void readConfigX(IPaletteItemManager manager, Bundle bundle,
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
				TaglibPaletteDrawer category = manager.findOrCreateCategory(
						uri, catlabel);
				
	//FIX ME			
//				String jsfComponentCategory = cat
//						.getAttribute(IPaletteConstants.JSFCOMPONENTCATEGORY);
//				boolean bJSF = Boolean.TRUE.toString().equalsIgnoreCase(
//						jsfComponentCategory);
//				if (bJSF) {
//					// avoid override. maybe someother place has already set it
//					// to be true.
//					category.setJSFComponentCategory(bJSF);
//				}

				NodeList items = cat
						.getElementsByTagName(IPaletteConstants.ITEM_TAG);
//FIX ME
//				for (int j = 0, jlength = items.getLength(); j < jlength; j++) {
//					Element item = (Element) items.item(j);
//					PaletteItemDescriptor descriptor = new PaletteItemDescriptor();
//					descriptor.setJSFComponent(category
//							.isJSFComponentCategory());
//					descriptor.setURI(uri);
//
//					String tagName = item
//							.getAttribute(IPaletteConstants.TAGNAME);
//					descriptor.setTagName(tagName);
//
//					String label = item.getAttribute(IPaletteConstants.LABEL);
//					descriptor.setLabel(label);
//
//					StringBuffer id = new StringBuffer(uri);
//					id.append(":").append(tagName).append(":").append(label);
//					descriptor.setId(id.toString());
//
//					String shortDesc = item
//							.getAttribute(IPaletteConstants.SHORTDESC);
//					descriptor.setDescription(shortDesc);
//
//					String smallIcon = item
//							.getAttribute(IPaletteConstants.SMALLICON);
//					descriptor.setSmallIcon(getImageDescriptor(bundle, prefix,
//							smallIcon));
//
//					String largeIcon = item
//							.getAttribute(IPaletteConstants.LARGEICON);
//					descriptor.setLargeIcon(getImageDescriptor(bundle, prefix,
//							largeIcon));
//
//					String expert = item.getAttribute(IPaletteConstants.EXPERT);
//					boolean isVisible = !Boolean.TRUE.toString()
//							.equalsIgnoreCase(expert); //$NON-NLS-1$
//					descriptor.setVisible(isVisible);
//
//					String requireHForm = item
//							.getAttribute(IPaletteConstants.REQUIREHFORM);
//					boolean r = Boolean.TRUE.toString().equalsIgnoreCase(
//							requireHForm);
//					descriptor.setRequireHForm(r);
//
//					NodeList attributes = item
//							.getElementsByTagName(IPaletteConstants.ATTRIBUTE_TAG);
//					if (attributes.getLength() > 0) {
//						Map attrMap = new HashMap();
//						for (int k = 0, klength = attributes.getLength(); k < klength; k++) {
//							Element attr = (Element) attributes.item(k);
//							String name = attr
//									.getAttribute(IPaletteConstants.NAME);
//							String value = attr
//									.getAttribute(IPaletteConstants.VALUE);
//							attrMap.put(name, value);
//						}
//						descriptor.setInitialAttributes(attrMap);
//					}
//
//					descriptor.setTemplateSubNodes(PaletteElementTemplateHelper
//							.readTemplate(item));
////					category.addPaletteItem(descriptor);
//				}
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
	
	/**
	 * @return
	 */
	private static ImageDescriptor getDefaultLargeIcon() {
		return DEFAULT_LARGE_ICON;
	}

	/**
	 * @return
	 */
	private static ImageDescriptor getDefaultSmallIcon() {
		return DEFAULT_SMALL_ICON;
	}
}
