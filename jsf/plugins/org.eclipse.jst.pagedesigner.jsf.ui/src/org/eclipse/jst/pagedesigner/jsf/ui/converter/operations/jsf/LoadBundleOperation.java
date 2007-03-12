/*******************************************************************************
 * Copyright (c) 2005 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ian Trimble - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.pagedesigner.jsf.ui.converter.operations.jsf;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.PropertyResourceBundle;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.jsf.common.ui.IFileFolderConstants;
import org.eclipse.jst.jsf.common.ui.internal.utils.ResourceUtils;
import org.eclipse.jst.pagedesigner.IJSFConstants;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.AbstractTransformOperation;
import org.eclipse.jst.pagedesigner.utils.PreviewUtil;
import org.eclipse.jst.pagedesigner.utils.StructuredModelUtil;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.w3c.dom.Element;

/**
 * ITransformOperation implementation specifically for the "loadBundle" JSF
 * (core) Element. 
 * 
 * @author Ian Trimble - Oracle
 */
public class LoadBundleOperation extends AbstractTransformOperation {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.AbstractTransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	public Element transform(Element srcElement, Element curElement) {
		if (srcElement != null) {
			//get "var" and "basename" attributes of srcElement
			String var = srcElement.getAttribute(IJSFConstants.ATTR_VAR);
			String basename = srcElement.getAttribute(IJSFConstants.ATTR_BASENAME);
			if (var != null && basename != null && var.length() > 0 && basename.length() > 0) {
				String basePath =
					basename.replace('.', IFileFolderConstants.PATH_SEPARATOR.charAt(0)) +
							IFileFolderConstants.DOT +
							IFileFolderConstants.EXT_PROPERTIES;
				if (srcElement instanceof IDOMNode) {
					//get model
					IDOMModel model = ((IDOMNode)srcElement).getModel();
					if (model != null) {
						//get project
						IProject project = StructuredModelUtil.getProjectFor(model);
						if (project != null) {
							//attempt to locate properties file in "/{WebRoot}/WEB-INF/classes"
							IVirtualComponent component = ComponentCore.createComponent(project);
							IPath webRootPath = component.getRootFolder().getProjectRelativePath();
							StringBuffer sb = new StringBuffer(webRootPath.toString());
							sb.append(IFileFolderConstants.PATH_SEPARATOR);
							sb.append(IFileFolderConstants.FOLDER_WEBINF);
							sb.append(IFileFolderConstants.PATH_SEPARATOR);
							sb.append(IFileFolderConstants.FOLDER_CLASS);
							sb.append(IFileFolderConstants.PATH_SEPARATOR);
							sb.append(basePath);
							IResource resource = project.findMember(sb.toString());
							if (resource == null) {
								resource = findFileInSrcFolder(project, basePath);
								if (resource != null) {
									//load properties file and configure PreviewUtil
									InputStream inputStream = null;
									try {
										File file = new File(resource.getLocation().toString());
										inputStream = new FileInputStream(file);
										if (inputStream != null) {
											inputStream = new BufferedInputStream(inputStream);
											PropertyResourceBundle bundle = new PropertyResourceBundle(inputStream);
											if (bundle != null) {
												if (PreviewUtil.BUNDLE_MAP == null) {
													PreviewUtil.BUNDLE_MAP = new HashMap();
												} else {
													PreviewUtil.BUNDLE_MAP.clear();
												}
												PreviewUtil.BUNDLE_MAP.put(var, bundle);
												PreviewUtil.BUNDLE = bundle;
												PreviewUtil.VAR = var;
											}
										}
									} catch(IOException ioe) {
										PDPlugin.getLogger(LoadBundleOperation.class).error("LoadBundleTagConverter.convertRefresh.IOException", ioe);
									} finally {
										ResourceUtils.ensureClosed(inputStream);
									}
								}
							}
						}
					}
				}
			}
		}
		//return null to indicate there is no HTML element associated with this tag
		return null;
	}

	/**
	 * Find specified file in any source folder of the specified project.
	 * 
	 * @param project IProject instance.
	 * @param filePath Source folder-relative path of the file to be located.
	 * @return
	 */
	protected IResource findFileInSrcFolder(IProject project, String filePath) {
		IResource resource = null;
		IJavaProject javaProject = JavaCore.create(project);
		if (javaProject != null) {
			try {
				IClasspathEntry[] classpathEntries = javaProject.getResolvedClasspath(true);
				for (int i = 0; i < classpathEntries.length; i++) {
					IClasspathEntry classpathEntry = classpathEntries[i];
					if (classpathEntry.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
						IPath srcPath = classpathEntry.getPath();
						//srcPath = srcPath.removeFirstSegments(srcPath.matchingFirstSegments(project.getFullPath()));
						IPath srcFilePath = srcPath.append(filePath);
						IResource tmpResource = project.getParent().findMember(srcFilePath);
						if (tmpResource != null) {
							resource = tmpResource;
							break;
						}
					}
				}
			} catch(JavaModelException jme) {
				//ignore - returning null from method will indicate failure
			}
		}
		return resource;
	}

}
