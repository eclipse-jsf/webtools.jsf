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
import org.eclipse.wst.xml.core.internal.provisional.document.ISourceGenerator;
import org.w3c.dom.Node;

/**
 * @author mengbo
 * @version 1.5
 */
public class PreviewHandlerNew {
	//private static Logger _log = PDPlugin.getLogger(PreviewHandlerNew.class);

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

				if (node != null) {
					result.append(generator.generateSource(node));
				}

			}
			// XXX: seemed in WTP0.7, releaseFromEdit for a unmanaged model may
			// fail
			try {
				sModel.releaseFromEdit();
			} catch (Throwable th) {
				// "Error in model release:"
				// _log.info("PreviewHandlerNew.Error.0", th); //$NON-NLS-1$
			}
			return;
		} finally {
			PageExpressionContext.reset();
		}
	}

}
