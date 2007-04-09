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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.editors.palette.TagToolPaletteEntry;
import org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.provisional.TagCreationInfo;
import org.eclipse.jst.pagedesigner.utils.JSPUtil;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMText;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 * @author mengbo
 */
public class PaletteElementTemplateHelper {
	//private static final String TEMPLATE_ITEM_NAME = "template";

	private static final String PREFIX_ATTRIBUTE = "_uri_";

//	private static Logger _log = PDPlugin
//			.getLogger(PaletteElementTemplateHelper.class);

	/**
	 * This method is used to process template element which is read from .xmi
	 * file and generate default element.
	 * 
	 * @param model
	 * @param element
	 * @param tagItem
	 * @param tagCreationInfo 
	 */
	public static void applyTemplate(IDOMModel model, Element element,
			TagToolPaletteEntry tagItem, TagCreationInfo tagCreationInfo) {
		if (element == null || element.getLocalName() == null) {
			return;
		}
		
		Node[] templateNodes = getTemplateNodes(model,  tagCreationInfo);
		if (templateNodes != null) {
			for (int i=0;i<templateNodes.length;i++){
				Node anode = templateNodes[i];
				element.appendChild(anode);				
			}
		}
	}

	private static Node[] getTemplateNodes(IDOMModel model,
			TagCreationInfo tagCreationInfo) {

		if (tagCreationInfo == null)
			return null;
		
		String template = (String)tagCreationInfo.getTemplate();
		if (template != null){
			
			final String nodeStr = prepareNode(template);//(String)template.getTemplate();
			final Logger logger = PDPlugin.getLogger(PaletteElementTemplateHelper.class); 
			try {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				factory.setNamespaceAware(true);
				factory.setValidating(false);
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document doc = builder.parse(new ByteArrayInputStream( nodeStr.getBytes()));
				Node beginNode = doc.getFirstChild();
				Node templateNode = beginNode.cloneNode(true);//model.getDocument().importNode(beginNode, true);
				Node[] templateNodes = applyPrefixes(model, tagCreationInfo, templateNode.getChildNodes(), model.getDocument());
				return templateNodes;
			} catch (ParserConfigurationException e) {
			    logger.error(e);
			} catch (SAXException e) {
                logger.error(e);
			} catch (IOException e) {
                logger.error(e);
			} catch (Exception e){
                logger.error(e);
			}
		}
		return null;
	}

	/**
	 * @param template
	 * @return xml as String wrapped by a <begin> node as template may not have a single root element
	 */
	private static String prepareNode(String template) {
		StringBuffer buf = new StringBuffer("<begin>");
		buf.append(template);
		buf.append("</begin>");
		return buf.toString();
	}

	/**
	 * Use the actrual prefixs of jsf html and jsf core to set the prefix of
	 * each node declared in template.
	 * 
	 * @param prefixH
	 * @param prefixC
	 * @param nl
	 * @param document
	 * @return
	 */
	public static Node[] applyPrefixes(IDOMModel model, TagCreationInfo info,
			NodeList templateNodes, Document document) {
		List result = new ArrayList();
		for (int i = 0, n = templateNodes.getLength(); i < n; i++) {
			Node node = cloneNodeDeep(model, document, templateNodes.item(i));
			if (node instanceof Element) {
				result.add(node);
			}
		}
		return (Node[]) result.toArray(new Node[result.size()]);
	}

	/**
	 * TODO: Later we may add some logic to reference the tld file through tag
	 * name to resolve the prefixs directly.
	 * 
	 * @param model
	 * @param prefixH
	 * @param prefixC
	 * @param node
	 * @return
	 */
	private static void internalApplyPrefixes(IDOMModel model, Element refNode, Element node) {
		if (node != null && refNode != null) {
			String uri = refNode.getAttribute(PREFIX_ATTRIBUTE); //$NON-NLS-1$
			if (uri != null) {
				String prefix = JSPUtil.getPrefix(model, uri);
				node.setPrefix(prefix);
			}
		}
	}


	public static Node cloneNodeDeep(IDOMModel model, Document destDoc, Node sourceNode) {
		switch (sourceNode.getNodeType()) {
		case Node.ELEMENT_NODE:
			Element sourceEle = (Element) sourceNode;
			Element resultEle = destDoc.createElement(sourceEle.getTagName());
			internalApplyPrefixes(model, sourceEle, resultEle);
			NamedNodeMap attrs = sourceEle.getAttributes();
			for (int i = 0, size = attrs.getLength(); i < size; i++) {
				Attr a = (Attr) attrs.item(i);
				if (!PREFIX_ATTRIBUTE.equalsIgnoreCase(a.getNodeName())) {
					resultEle.setAttribute(a.getName(), a.getValue());
				}
			}
			NodeList children = sourceEle.getChildNodes();
			for (int i = 0, size = children.getLength(); i < size; i++) {
				Node n = children.item(i);
				Node d = cloneNodeDeep(model, destDoc, n);
				if (d != null) {
					resultEle.appendChild(d);
				}
			}
			return resultEle;
		case Node.TEXT_NODE:
			Text txt = destDoc.createTextNode(sourceNode.getNodeValue());
			if (txt instanceof IDOMText && sourceNode instanceof IDOMText) {
				try {
					((IDOMText) txt).setSource(((IDOMText) sourceNode)
							.getSource());
				} catch (Exception ex) {
					// ignore
				}
			}
			return txt;
		case Node.CDATA_SECTION_NODE:
			return destDoc.createCDATASection(sourceNode.getNodeValue());
		default:
			return null; // not support.
		}
	}
}
