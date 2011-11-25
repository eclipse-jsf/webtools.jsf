/*******************************************************************************
 * Copyright (c) 2011 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Andrew McCulloch - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.jsf.core.jsfappconfig;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJarEntryResource;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.jsf.common.internal.componentcore.AbstractVirtualComponentQuery.DefaultVirtualComponentQuery;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;

/**
 * Wrapper around IPackageFragmentRoot that determines if the
 * IPackageFragmentRoot meets the conditions to be searched for annotated Faces
 * components.
 * 
 * This class does not check the faces version or metadata-complete flag.
 * This instance is invalid if the project is renamed and must be recreated.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * @author Andrew McCulloch - Oracle
 */
public class AnnotationPackageFragmentRoot {

    private final IPackageFragmentRoot root;
    private final IJavaProject jProject;
    private final IPath webInfLibPath;
    private final IPath webInfClassesPath;

    /**
     * Construct the wrapper around a package fragment root.
     * 
     * @param root
     */
    public AnnotationPackageFragmentRoot(IPackageFragmentRoot root) {
        if (root != null && root.exists() && !root.isExternal()) {
            this.root = root;
            IPath tempWebInfLibPath = null;
            IPath tempWebInfClassesPath = null;
            jProject = root.getJavaProject();
            if (jProject != null) {
                IProject project = jProject.getProject();
                if (project != null) {
                    IVirtualFolder webContent = new DefaultVirtualComponentQuery().getWebContentFolder(project);
                    if (webContent != null) {
                        IContainer webContentFolder = webContent.getUnderlyingFolder();
                        if (webContentFolder != null && webContentFolder.exists()) {
                            IPath webContentPath = webContentFolder.getFullPath();
                            if (webContentPath != null) {
                                tempWebInfLibPath = webContentPath.append("WEB-INF/lib"); //$NON-NLS-1$
                                tempWebInfClassesPath = webContentPath.append("WEB-INF/classes"); //$NON-NLS-1$
                            }
                        }
                    }
                }
            }
            webInfClassesPath = tempWebInfClassesPath;
            webInfLibPath = tempWebInfLibPath;
        } else {
            this.root = null;
            this.webInfClassesPath = null;
            this.webInfLibPath = null;
            this.jProject = null;
        }
    }
    
    /**
     * @return true if this package fragment root wrapper should be scanned for annotated faces components.
     */
    public final boolean canContainAnnotatedComponents() {
        if (root == null || !root.exists() || webInfClassesPath == null || webInfLibPath == null) {
            return false;
        }
        IPath rootPath = root.getPath();
        if (rootPath != null) {
            return isWebInfClasses(root) || isFacesArchive(rootPath);
        }
        return false;
    }

    private final boolean isWebInfClasses(IPackageFragmentRoot root_) {
        IClasspathEntry cpe;
        try {
            cpe = root_.getResolvedClasspathEntry();
//            IPath rootPath = cpe.getOutputLocation(); 
            return cpe.getEntryKind() == IClasspathEntry.CPE_SOURCE;
//            if (rootPath == null) {
//                rootPath = jProject.getOutputLocation();
//            }
//            return webInfClassesPath.equals(rootPath);
        } catch (JavaModelException e) {
            return false;
        }
    }

    private final boolean isFacesArchive(IPath rootPath) {
        if (webInfLibPath.isPrefixOf(rootPath)) {

            Object[] nonJavaResources;
            try {
                nonJavaResources = root.getNonJavaResources();
                if (nonJavaResources != null) {
                    for (Object nonJavaResource : nonJavaResources) {
                        if (nonJavaResource instanceof IJarEntryResource) {
                            IJarEntryResource jarEntry = (IJarEntryResource) nonJavaResource;
                            if (!jarEntry.isFile()) {
                                String entryName = jarEntry.getName();
                                if ("META-INF".equals(entryName)) { //$NON-NLS-1$
                                    IJarEntryResource[] metaInfContents = jarEntry.getChildren();
                                    for (IJarEntryResource resource : metaInfContents) {
                                        if (resource.isFile() && "faces-config.xml".equals(resource.getName())) { //$NON-NLS-1$
                                            return true;
                                        }
                                    }
                                } else if (entryName != null && jarEntry.getName().charAt(0) == '.') {
                                    return hasDotFacesConfigFile(jarEntry);
                                }
                            }
                        }
                    }
                }
            } catch (JavaModelException e) {
                return false;
            }
        }
        return false;
    }

    private final boolean hasDotFacesConfigFile(IJarEntryResource jarEntry) {
        IJarEntryResource[] contents = jarEntry.getChildren();
        for (IJarEntryResource resource : contents) {
            if (resource.isFile()) {
                if (".faces-config.xml".equals(resource.getName())) { //$NON-NLS-1$
                    return true;
                }
            } else {
                if (hasDotFacesConfigFile(resource)) {
                    return true;
                }
            }
        }
        return false;
    }
}