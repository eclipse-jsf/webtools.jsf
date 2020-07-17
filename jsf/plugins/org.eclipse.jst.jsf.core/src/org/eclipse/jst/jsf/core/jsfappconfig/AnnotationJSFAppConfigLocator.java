/*******************************************************************************
 * Copyright (c) 2011 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Andrew McCulloch - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.jsf.core.jsfappconfig;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.ElementChangedEvent;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IElementChangedListener;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaElementDelta;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.jsf.common.internal.componentcore.AbstractVirtualComponentQuery.DefaultVirtualComponentQuery;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;

/**
 * AnnotationJSFAppConfigLocator locates JSF configuration specified as JSF 2.x annotations.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * @author Andrew McCulloch - Oracle
 */
public class AnnotationJSFAppConfigLocator extends AbstractJSFAppConfigLocater {

    private final IElementChangedListener listener = new ElementChangeListener();
    
    private IPath webInfLibPath = null;
    private IPath webInfClassesPath = null;

    public void startLocating() {
        IProject project = getJSFAppConfigManager().getProject();
        if (JSFAppConfigUtils.isValidJSFProject(project, "2.0")) { //$NON-NLS-1$
            IVirtualFolder webContent = new DefaultVirtualComponentQuery().getWebContentFolder(project);
            if (webContent != null) {
                IContainer webContentFolder = webContent.getUnderlyingFolder();
                if (webContentFolder != null && webContentFolder.exists()) {
                    IPath webContentPath = webContentFolder.getProjectRelativePath();
                    if (webContentPath != null) {
                        webInfLibPath = webContentPath.append("WEB-INF/lib"); //$NON-NLS-1$
                        webInfClassesPath = webContentPath.append("WEB-INF/classes"); //$NON-NLS-1$
                        
                        addProvider();
                        JavaCore.addElementChangedListener(listener);
                    }
                }
            }
        }
    }

    private void addProvider() {
        Set newConfigProviders = new LinkedHashSet();
        newConfigProviders.add(new AnnotationJSFAppConfigProvider());
        updateConfigProviders(newConfigProviders);
    }

    @Override
    public void stopLocating() {
        JavaCore.removeElementChangedListener(listener);
    }

    private class ElementChangeListener implements IElementChangedListener {

        public void elementChanged(ElementChangedEvent event) {
            if (isRelevantChange(event.getDelta(), getJSFAppConfigManager().getProject())) {
                addProvider();
            }
        }

        /*
         * 11.5.1 Requirements for scanning of classes for annotations =
         * [P1_start-annotation-discovery]If the <faces-config> element in the
         * WEB-INF/faces-config.xml file contains metadata-complete attribute
         * whose value is "true", the implementation must not perform annotation
         * scanning on any classes except for those classes provided by the
         * implementation itself. Otherwise, continue as follows. = If the
         * runtime discovers a conflict between an entry in the Application
         * Configuration Resources and an annotation, the entry in the
         * Application Configuration Resources takes precedence. = All classes
         * in WEB-INF/classes must be scanned. = For every jar in the
         * application's WEB-INF/lib directory, if the jar contains a
         * "META-INF/faces-config.xml" file or a file that matches the regular
         * expression ".*\.faces-config.xml" (even an empty one), all classes in
         * that jar must be scanned.[P1_end-annotation-discovery]
         */

        private final boolean isRelevantChange(IJavaElementDelta delta, IProject project) {
            int deltaFlags = delta.getFlags();
            /*
             * F_CONTENT means the content of an element changed.  If the element is a class in web-inf/classes this is relevant
             * F_ADDED_TO_CLASSPATH, F_ARCHIVE_CONTENT_CHANGED, F_REMOVED_FROM_CLASSPATH all indicate archive changes which are relevant 
             *    if the archive has the metadata and is in web-inf/lib.
             * F_CLASSPATH_REORDER could indicate a class with a bean annotation has been discovered or hidden by classpath changes.
             */
            if ((deltaFlags & (IJavaElementDelta.F_CONTENT | IJavaElementDelta.F_ADDED_TO_CLASSPATH | IJavaElementDelta.F_ARCHIVE_CONTENT_CHANGED | 
                                IJavaElementDelta.F_REMOVED_FROM_CLASSPATH | IJavaElementDelta.F_REORDER | IJavaElementDelta.F_PRIMARY_RESOURCE)) != 0) {
                IJavaElement changedElement = delta.getElement();
                switch (changedElement.getElementType()) {
                    case IJavaElement.COMPILATION_UNIT:
                        if (changedElement instanceof ICompilationUnit) {
                            return true;
                        }
                        IType type = (IType)changedElement;
                        IPath classFilePath = type.getPath();
                        if (classFilePath != null) {
                            if (project.getFullPath().append(webInfClassesPath).isPrefixOf(classFilePath)) {
                                return true;
                            }
                        }
                    case IJavaElement.PACKAGE_FRAGMENT_ROOT:
                        IPackageFragmentRoot root = (IPackageFragmentRoot)changedElement;
                        if (root.isArchive() && !root.isExternal()) {
                            IPath archivePath = root.getPath();
                            if (archivePath != null) {
                                if (project.getFullPath().append(webInfLibPath).isPrefixOf(archivePath)) {
                                    if ((deltaFlags & IJavaElementDelta.F_REMOVED_FROM_CLASSPATH) != 0) {
                                        return true;
                                    }
                                    //don't bother processing this delta or children if the root is missing
                                    AnnotationPackageFragmentRoot wrapper = new AnnotationPackageFragmentRoot(root);
                                    return wrapper.canContainAnnotatedComponents();
                                }
                            }
                        }
                    default://do nothing
                }
            }
            IJavaElementDelta[] childDeltas = delta.getAffectedChildren();
            if (childDeltas != null) {
                for (IJavaElementDelta childDelta : childDeltas) {
                    if (isRelevantChange(childDelta, project)) {
                        return true;
                    }
                }
            }
            return false;
        }
   }
}
