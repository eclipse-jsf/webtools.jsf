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
package org.eclipse.jst.pagedesigner.preview;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.dtresourceprovider.DTSkinManager;
import org.eclipse.jst.pagedesigner.dtresourceprovider.IDTSkin;
import org.eclipse.jst.pagedesigner.jsp.core.pagevar.IPageVariablesProvider;
import org.eclipse.jst.pagedesigner.jsp.core.pagevar.adapter.IDocumentPageVariableAdapter;
import org.eclipse.jst.pagedesigner.parts.DocumentEditPart;
import org.eclipse.jst.pagedesigner.parts.NodeEditPart;
import org.eclipse.jst.pagedesigner.utils.PreviewUtil;
import org.eclipse.jst.pagedesigner.utils.StructuredModelUtil;
import org.eclipse.wst.html.core.internal.provisional.contenttype.ContentTypeIdForHTML;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.xml.core.internal.document.XMLGeneratorImpl;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.eclipse.wst.xml.core.internal.provisional.document.ISourceGenerator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author mengbo
 * @version 1.5
 */
public class PreviewHandlerNew {
	//private static Logger _log = PDPlugin.getLogger(PreviewHandlerNew.class);

	/**
	 * @param part
	 * @param result
	 */
	public static void generatePreview(DocumentEditPart part,
			StringBuffer result) {
		try {
			IProject prj = StructuredModelUtil.getProjectFor(part.getIDOMNode()
					.getModel());
			PageExpressionContext.initialize(prj);

			IDOMDocument doc = (IDOMDocument) part.getIDOMNode();
			Object obj = doc.getAdapterFor(IDocumentPageVariableAdapter.class);
			if (obj instanceof IPageVariablesProvider) {
				((IPageVariablesProvider) obj).refresh();
				PageExpressionContext.getCurrent().pushPageVarProvider(
						(IPageVariablesProvider) obj);
			} else {
				PageExpressionContext.getCurrent().pushPageVarProvider(null);
			}

			// IDOMModel previewModel =
			// (IDOMModel)StructuredModelManager.getModelManager().createNewInstance(doc.getModel());
			// IDOMDocument previewDoc = previewModel.getDocument();

			// CR400625: creating XML model here instead of HTML model. Since
			// for HTML model, there are checking enforced
			// by WTP to make sure the HTML content model is not invalidated.
			// And sometimes, the converted HTML may not fully
			// comply with HTML content model.
			// Use XML instead to workaround the content model validation.

			// CR403449: But if we use XML, then system can't recogonize special
			// tag such as "script", "style", they
			// support <!-- --> in them.
			// So we are still using HTML model, but in TagConverter, we are
			// always caling DOMUtil.cloneNodeDeepIgnoreError
			// to try to skip the errors.
			// Hopefully in later version of SSE, the famous "br" problem is
			// fixed, and we won't met error when doing
			// deep clone.
			// IStructuredModel sModel =
			// StructuredModelManager.getModelManager().createUnManagedStructuredModelFor(IContentTypeIdentifier.ContentTypeID_XML);
			// FIXME: if is not jsp, should use original contentType, if is jsp,
			// should use the corresponding
			// content type
			IStructuredModel sModel = StructuredModelManager.getModelManager()
					.createUnManagedStructuredModelFor(
							ContentTypeIdForHTML.ContentTypeID_HTML);

			IDOMDocument previewDoc = ((IDOMModel) sModel).getDocument();
			PreviewConvertContext context = new PreviewConvertContext(
					previewDoc);

			ISourceGenerator generator = XMLGeneratorImpl.getInstance();
			List subeditparts = part.getChildren();
			for (int i = 0, size = subeditparts.size(); i < size; i++) {
				NodeEditPart subpart = (NodeEditPart) subeditparts.get(i);
				Node node = context.previewConvert(subpart.getIDOMNode());

				PreviewUtil.previewNode(node);

				appendSkinStyleSheetLinks(node, subpart.getIDOMNode());

				if (node != null) {
					result.append(generator.generateSource(node));
				}

			}
			sModel.releaseFromEdit();
		} finally {
			PageExpressionContext.reset();
		}
	}

	private static void appendSkinStyleSheetLinks(Node previewNode, IDOMNode domNode) {
		if (previewNode != null && domNode != null) {
			Element head = locateHeadElement(previewNode);
			if (head != null) {
				Document document = head.getOwnerDocument();
				if (document != null) {
					DTSkinManager skinManager = DTSkinManager.getInstance(domNode);
					if (skinManager != null) {
						List<IDTSkin> currentSkins = skinManager.getCurrentSkins();
						for (IDTSkin currentSkin: currentSkins) {
							List<String> styleSheetLocations = currentSkin.getStyleSheetLocations();
							for (String styleSheetLocation: styleSheetLocations) {
								Element link = document.createElement(IHTMLConstants.TAG_LINK);
								link.setAttribute(IHTMLConstants.ATTR_REL, "stylesheet"); //$NON-NLS-1$
								link.setAttribute(IHTMLConstants.ATTR_TYPE, "text/css"); //$NON-NLS-1$
								link.setAttribute(IHTMLConstants.ATTR_HREF, styleSheetLocation);
								head.appendChild(link);
							}
						}
					}
				}
			}
		}
	}

	private static Element locateHeadElement(Node node) {
		Element head = null;
		if (node != null && node instanceof Element) {
			if (node.getLocalName().equalsIgnoreCase(IHTMLConstants.TAG_HEAD)) {
				head = (Element)node;
			} else {
				NodeList childNodes = node.getChildNodes();
				for (int i = 0, len = childNodes.getLength(); i < len; i++) {
					head = locateHeadElement(childNodes.item(i));
					if (head != null) {
						break;
					}
				}
			}
		}
		return head;
	}

}
