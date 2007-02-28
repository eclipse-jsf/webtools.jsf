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
package org.eclipse.jst.pagedesigner.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.jst.jsf.core.internal.tld.CMUtil;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.IJSFConstants;
import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyID;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author mengbo
 */
public class SelectManyHelper {
//	private static final String OPTION_VISUAL_PREFIX = "option: "; //$NON-NLS-1$
//
//	private static final String OPTION_VISUAL_PREFIX_BINDING = "option(binding): "; //$NON-NLS-1$
//
//	private static final String OPTION_VISUAL_PREFIX_VALUE = "option(value): "; //$NON-NLS-1$

	private static final String NO_VALUE = "<no value>"; //$NON-NLS-1$

	public static HashSet JSF_SELECT_TAGS, HTML_SELECT_TAGS;

	public static HashSet HTML_SELECT_TAG_OPTIONS, JSF_SELECT_TAG_OPTIONS;

	static {
		HTML_SELECT_TAGS = new HashSet(10);
		HTML_SELECT_TAGS.add(IHTMLConstants.TAG_SELECT);
		HTML_SELECT_TAGS.add(IHTMLConstants.TAG_OPTGROUP);
		HTML_SELECT_TAG_OPTIONS = new HashSet(10);
		HTML_SELECT_TAG_OPTIONS.add(IHTMLConstants.TAG_OPTGROUP);
		HTML_SELECT_TAG_OPTIONS.add(IHTMLConstants.TAG_OPTION);

		JSF_SELECT_TAG_OPTIONS = new HashSet(10);
		JSF_SELECT_TAG_OPTIONS.add(IJSFConstants.TAG_SELECTITEM);
		JSF_SELECT_TAG_OPTIONS.add(IJSFConstants.TAG_SELECTITEMS);
		JSF_SELECT_TAGS = new HashSet(10);
		JSF_SELECT_TAGS.add(IJSFConstants.TAG_SELECTONELISTBOX);
		JSF_SELECT_TAGS.add(IJSFConstants.TAG_SELECTONEMENU);
		JSF_SELECT_TAGS.add(IJSFConstants.TAG_SELECTMANYLISTBOX);//
		JSF_SELECT_TAGS.add(IJSFConstants.TAG_SELECTMANYMENU);//
		JSF_SELECT_TAGS.add(IJSFConstants.TAG_SELECTMANYCHECKBOX);
	}

	public static Object[] getSelectOptions(Element node) {
		if (node == null) {
			return null;
		}
		IDOMModel xmlModel = null;
		if (node instanceof IDOMNode) {
			xmlModel = ((IDOMNode) node).getModel();
		}
		if (xmlModel == null) {
			return null;
		}
		// TODO: this query is not cached.
		String prefixNode = JSPUtil.getPrefix(xmlModel,
				ITLDConstants.URI_JSF_HTML);
		if (prefixNode != null && node.getPrefix() != null
				&& node.getPrefix().equals(prefixNode)) {
			if (JSF_SELECT_TAGS.contains(node.getLocalName())) {
				String prefixItem = JSPUtil.getPrefix(xmlModel,
						ITLDConstants.URI_JSF_CORE);
				List result = new ArrayList();
				NodeList items = node.getElementsByTagName(prefixItem
						+ ":" + IJSFConstants.TAG_SELECTITEM); //$NON-NLS-1$
				for (int i = 0, n = items.getLength(); i < n; i++) {
					result.add(items.item(i));
				}
				items = node.getElementsByTagName(prefixItem
						+ ":" + IJSFConstants.TAG_SELECTITEMS); //$NON-NLS-1$
				for (int i = 0, n = items.getLength(); i < n; i++) {
					result.add(items.item(i));
				}
				return result.size() > 0 ? result.toArray(new Node[result
						.size()]) : null;
			}
		}
		if (node.getPrefix() == null) {
			if (HTML_SELECT_TAGS.contains(node.getNodeName().toLowerCase())) {
				List result = new ArrayList();
				NodeList options = node
						.getElementsByTagName(IHTMLConstants.TAG_OPTION);
				NodeList optionGroups = node
						.getElementsByTagName(IHTMLConstants.TAG_OPTGROUP);
				for (int i = 0, n = options.getLength(); i < n; i++) {
					result.add(options.item(i));
				}
				for (int i = 0, n = optionGroups.getLength(); i < n; i++) {
					result.add(optionGroups.item(i));
				}
				return result.toArray(new Node[result.size()]);
			}
		}
		return null;
	}

	public static String[] getSelectOptionsString(Element node) {
		if (node == null) {
			return null;
		}
		Object[] options = getSelectOptions(node);
		if (null == options || options[0] == null) {
			return null;
		}
		List result = new ArrayList();
		for (int i = 0, n = options.length; i < n; i++) {
			if (options[i] instanceof Element) {
				Element element = (Element) options[i];
				Node value = null;
				if (element.getNodeName()
						.indexOf(IJSFConstants.TAG_SELECTITEMS) >= 0) {
					value = element.getAttributeNode(IJSFConstants.ATTR_VALUE);
				} else if (element.getNodeName().indexOf(
						IJSFConstants.TAG_SELECTITEM) >= 0) {
					value = element
							.getAttributeNode(ICSSPropertyID.ATTR_ITEMLABEL);
				}
				if (value != null) {
					result.add(value.getNodeValue());
				}
			}
		}
		return (String[]) result.toArray(new String[] {});
	}

	public static boolean hasSelectOptions(Element node) {
		if (node == null) {
			return false;
		}
		String uri = CMUtil.getElementNamespaceURI(node);
		if (ITLDConstants.URI_JSF_HTML.equals(uri)) {

			if (JSF_SELECT_TAGS.contains(node.getLocalName())) {
				IDOMModel model = ((IDOMElement) node).getModel();
				String jsfcorePrefix = JSPUtil.getPrefix(model,
						ITLDConstants.URI_JSF_CORE);
				if (jsfcorePrefix != null) {
					NodeList nl = node.getElementsByTagName(jsfcorePrefix + ":"
							+ IJSFConstants.TAG_SELECTITEM);
					NodeList nl1 = node.getElementsByTagName(jsfcorePrefix
							+ ":" + IJSFConstants.TAG_SELECTITEMS);
					return nl.getLength() > 0 || nl1.getLength() > 0;
				}
                return false;
			}
		}
		if (ITLDConstants.URI_HTML.equals(uri)) {
			if (HTML_SELECT_TAGS.contains(node.getNodeName().toLowerCase())) {
				NodeList nl = node
						.getElementsByTagName(IHTMLConstants.TAG_OPTION);
				NodeList nl1 = node
						.getElementsByTagName(IHTMLConstants.TAG_OPTGROUP);
				return nl.getLength() > 0 || nl1.getLength() > 0;
			}
		}
		return false;
	}

	public static boolean supportSections(Element node) {
		String uri = CMUtil.getElementNamespaceURI(node);
		if (ITLDConstants.URI_JSF_HTML.equals(uri)) {
			return JSF_SELECT_TAGS.contains(node.getLocalName());
		}
		if (ITLDConstants.URI_HTML.equals(uri)) {
			return HTML_SELECT_TAGS.contains(node.getNodeName().toLowerCase());
		}
		return false;
	}

	public static String getJsfSelectionVisualLabel(Element element) {
		/*
		 * if (ele.getAttribute(ICSSPropertyID.ATTR_ITEMLABEL) != null) { return
		 * OPTION_VISUAL_PREFIX +
		 * ele.getAttribute(ICSSPropertyID.ATTR_ITEMLABEL); //$NON-NLS-1$ } else
		 * if (ele.getAttribute(ICSSPropertyID.ATTR_BINDING) != null) { return
		 * OPTION_VISUAL_PREFIX_BINDING +
		 * ele.getAttribute(ICSSPropertyID.ATTR_BINDING); //$NON-NLS-1$ } else
		 * if (ele.getAttribute(ICSSPropertyID.ATTR_ITEMVALUE) != null) { return
		 * OPTION_VISUAL_PREFIX_VALUE +
		 * ele.getAttribute(ICSSPropertyID.ATTR_ITEMVALUE); //$NON-NLS-1$ }
		 * 
		 * return NO_VALUE; //$NON-NLS-1$
		 */
		if (element != null) {
			return element.getNodeName();
		}
        return NO_VALUE;
	}
}
