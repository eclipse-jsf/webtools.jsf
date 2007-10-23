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
package org.eclipse.jst.pagedesigner.elementedit.jsp;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.Action;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.wst.sse.core.internal.util.URIResolver;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.w3c.dom.Element;

/**
 * @author mengbo
 * @version 1.5
 */
public class TaglibURIAction extends Action {
	private String URI;

	private Element element;

	/**
	 * Default constructor
	 */
	public TaglibURIAction() {
		setText(PDPlugin.getResourceString("ElementEdit.Submenu.Taglib"));//$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.IAction#isEnabled()
	 */
	public boolean isEnabled() {
		if (element == null || URI == null || "".equals(URI))//$NON-NLS-1$
		{
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
		String fileName = getResolvedURL(getElement(), URI);

		if (fileName != null && fileName.length() > 0) {
			IPath includedPath = new Path(fileName);
			includedPath.makeAbsolute();

			IFile file = getFile(includedPath);
			if (file != null && file.exists()) {
				try {
					IDE.openEditor(getPage(), file);
					return;
				} catch (PartInitException e) {
					PDPlugin.getAlerts().warning(
							"Message.Warning.Title", e.getLocalizedMessage());//$NON-NLS-1$
				}
			}
		}
		PDPlugin.getAlerts().warning(
				"Message.Warning.Title", "Taglib.OpenFile.ERROR");//$NON-NLS-1$ //$NON-NLS-2$
	}

	private IWorkbenchPage getPage() {
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		return window.getActivePage();
	}

	/**
	 * @param uri
	 */
	public void setURI(String uri) {
		this.URI = uri;
	}

	private IFile getFile(IPath includedPath) {
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

	private String getResolvedURL(Element element_, String attrName) {
		URIResolver resolver = null;
		if (element_ instanceof IDOMNode) {
			resolver = ((IDOMNode) element_).getModel().getResolver();
		}
		if (null == resolver) {
			return null;
		}
		String src = URI;
		if (src != null && src.length() > 0) {
			return resolver.getLocationByURI(src);
		}
		return null;
	}

	/**
	 * @return Returns the element.
	 */
	public Element getElement() {
		return element;
	}

	/**
	 * @param element
	 *            The element to set.
	 */
	public void setElement(Element element) {
		this.element = element;
	}
}
