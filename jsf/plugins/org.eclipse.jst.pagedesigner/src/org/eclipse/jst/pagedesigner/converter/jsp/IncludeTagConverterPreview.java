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
package org.eclipse.jst.pagedesigner.converter.jsp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.converter.AbstractTagConverter;
import org.eclipse.jst.pagedesigner.jsp.core.internal.pagevar.DocumentPageVariableAdapter;
import org.eclipse.jst.pagedesigner.jsp.core.pagevar.adapter.PageVariableAdapterFactory;
import org.eclipse.jst.pagedesigner.preview.PageExpressionContext;
import org.eclipse.jst.pagedesigner.preview.PreviewConvertContext;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.util.URIResolver;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * This is the tag converter for preview.
 * 
 * @author mengbo
 * @version 1.5
 */
public class IncludeTagConverterPreview extends AbstractTagConverter {
	private static Logger _log = PDPlugin
			.getLogger(IncludeTagConverterPreview.class);

	private String _fileAttrName;

	/**
	 * @param host
	 * @param fileAttrname 
	 */
	public IncludeTagConverterPreview(Element host, String fileAttrname) {
		super(host);
		this._fileAttrName = fileAttrname;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.converter.AbstractTagConverter#doConvertRefresh()
	 */
	protected Element doConvertRefresh() {
		String fileName = getResolvedURL(getHostElement(), this._fileAttrName);
		if (fileName == null || fileName.length() == 0) {
			return null;
		}
        IPath includedPath = new Path(fileName);
        includedPath.makeAbsolute();

        IFile file = getFile(includedPath);
        if (file == null) {
        	return null;
        }
        return previewFile(file);
	}

	/**
	 * @param includedPath
	 * @return the IFile corresponding to the IPath
	 */
	public IFile getFile(IPath includedPath) {
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IProject[] projects = workspaceRoot.getProjects();

		for (int i = 0, length = projects.length; i < length; i++) {
			IPath path = projects[i].getLocation();
			path = path.makeAbsolute();
			if (path != null && path.isPrefixOf(includedPath)) {
				// -1 so we still have the project path
				includedPath = includedPath.removeFirstSegments(path
						.segmentCount() - 1);
				return ResourcesPlugin.getWorkspace().getRoot().getFile(
						includedPath);
			}
		}
		return null;
	}

	/**
	 * @param file
	 * @return the Element
	 */
	public Element previewFile(IFile file) {
		IDOMModel xmlModel = null;
		DocumentPageVariableAdapter provider = null;
		boolean pushedPageVarProvider = false;
		try {

			xmlModel = (IDOMModel) StructuredModelManager.getModelManager().getModelForRead(
					file);
			if (xmlModel != null) {
				IDOMDocument doc = xmlModel.getDocument();

				// XXX: need to also register page variable adapters. In the
				// future, this should go to some
				// SSE system registry mechanism.
				xmlModel.getFactoryRegistry().addFactory(
						new PageVariableAdapterFactory());
				provider = new DocumentPageVariableAdapter(doc);
				doc.addAdapter(provider);

				provider.refresh();
				PageExpressionContext.getCurrent()
						.pushPageVarProvider(provider);

				Node child = xmlModel.getDocument().getFirstChild();
				PreviewConvertContext context = new PreviewConvertContext(this
						.getDestDocument());
				List results = new ArrayList();
				while (child != null) {
					Node node = context.previewConvert(child);
					if (node != null) {
						results.add(node);
					}
					child = child.getNextSibling();
				}

				if (results.size() == 0) {
					return null;
				} else if (results.size() == 1
						&& results.get(0) instanceof Element) {
					return (Element) results.get(0);
				} else {
					Element ret = createElement(IHTMLConstants.TAG_SPAN);
					for (int i = 0, n = results.size(); i < n; i++) {
						ret.appendChild((Node) results.get(i));
					}
					return ret;
				}
			}
		} catch (CoreException e) {
			_log.error("PreviewUtil.previewFile.CoreException", e); //$NON-NLS-1$
		} catch (IOException e) {
			_log.error("PreviewUtil.previewFile.IOException", e); //$NON-NLS-1$
		} catch (Exception ex) {
			_log.error("PreviewUtil.previewFile.CoreException", ex); //$NON-NLS-1$
		} finally {
			if (pushedPageVarProvider) {
				PageExpressionContext.getCurrent().popPageVarProvider(provider);
			}
			if (xmlModel != null) {
				xmlModel.releaseFromRead();
			}
		}
		return null;
	}

	static String getResolvedURL(Element element, String attrName) {
		URIResolver resolver = null;
		if (element instanceof IDOMNode) {
            // TODO: the new URI resolver is not available on the IStructuredModel
			resolver = ((IDOMNode) element).getModel().getResolver();
		}
		if (null == resolver) {
			return null;
		}
		String src = element.getAttribute(attrName);
		if (src != null && src.length() > 0) {
			return resolver.getLocationByURI(src);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.converter.ITagConverter#isMultiLevel()
	 */
	public boolean isMultiLevel() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.style.ITagEditInfo#isWidget()
	 */
	public boolean isWidget() {
		return false;
	}
}
