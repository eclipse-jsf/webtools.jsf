/*******************************************************************************
 * Copyright (c) 2008 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facelet.core.internal.cm.attributevalues;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.jsf.common.internal.componentcore.AbstractCompCoreQueryFactory;
import org.eclipse.jst.jsf.common.internal.componentcore.AbstractVirtualComponentQuery;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IDOMContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.facelet.core.internal.FaceletCorePlugin;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.features.PossibleValue;
import org.eclipse.jst.jsf.taglibprocessing.attributevalues.WebPathType;
import org.eclipse.wst.common.componentcore.resources.IVirtualContainer;
import org.w3c.dom.Attr;
import org.w3c.dom.Node;

/**
 * Web-path attribute value type that adds possible values support
 * 
 * @author cbateman
 * 
 */
public class TemplateWebPathType extends WebPathType implements IPossibleValues {

    public List getPossibleValues() {
        final IStructuredDocumentContext context = getStructuredDocumentContext();
        final IDOMContextResolver resolver = IStructuredDocumentContextResolverFactory.INSTANCE
                .getDOMContextResolver(context);
        if (resolver != null) {
            final Node node = resolver.getNode();
            if (node instanceof Attr) {
                return createPossibleValues((Attr) node);
            }
        }
        return Collections.EMPTY_LIST;
    }

    private List createPossibleValues(final Attr node) {
        String currentPathString = node.getNodeValue();

        final List possibleValues = new ArrayList();

        if (currentPathString == null || "".equals(currentPathString.trim())) //$NON-NLS-1$
        {
            currentPathString = "/"; //$NON-NLS-1$
        }

        final IPath currentPath = new Path(currentPathString);

        final IVirtualContainer webRoot = getWebRoot();

        if (webRoot == null)
        {
            return possibleValues;
        }

        final IResource deepestElement = findDeepestCommonElement(currentPath,
                (IContainer) webRoot.getUnderlyingResource());

        if (deepestElement == null) {
            // empty
            return possibleValues;
        }

        if (deepestElement instanceof IContainer) {
            try {
                for (final IResource child : ((IContainer) deepestElement).members()) {
                    if (child.exists()) {
                        IPath childPath = child.getProjectRelativePath();
                        int numLeadingSegments = webRoot.getProjectRelativePath().matchingFirstSegments(childPath);
                        childPath = childPath.removeFirstSegments(numLeadingSegments);
                        String pathName = null;
                        if (currentPath.isAbsolute()) {
                            pathName = childPath.makeAbsolute().toString();
                        } else {
                            pathName = childPath.makeRelative().toString();
                        }

                        final PossibleValue pv = new PossibleValue(pathName, pathName);
                        possibleValues.add(pv);
                    }
                }
            } catch (final CoreException ce) {
                FaceletCorePlugin.log("While trying possible values", ce); //$NON-NLS-1$
            }
        }
        return possibleValues;
    }

    private IResource findDeepestCommonElement(final IPath currentPath, final IContainer webRoot) {
        final String[] segments = currentPath.segments();
        IResource deepestElement = null;
        if (segments != null) {
            IPath longestSubPath = new Path(""); //$NON-NLS-1$
            for (final String segment : segments) {
                longestSubPath = longestSubPath.append(segment);
                deepestElement = webRoot.findMember(longestSubPath);
                if (deepestElement == null) {
                    longestSubPath = longestSubPath.removeLastSegments(1);
                    break;
                }
            }

            deepestElement = webRoot.findMember(longestSubPath);
            if (deepestElement == null) {
                deepestElement = webRoot;
            } else {
                int avoidInfiniteLoopCount = 0;
                while (avoidInfiniteLoopCount < 1000 && // we timeout in cause
                                                        // of circular chains.
                        deepestElement != null && !(deepestElement instanceof IContainer)) {
                    deepestElement = deepestElement.getParent();
                }

                if (avoidInfiniteLoopCount == 1000) {
                    throw new IllegalStateException();
                }
            }
        }
        return deepestElement;
    }

    private IVirtualContainer getWebRoot()
    {
        AbstractCompCoreQueryFactory compCoreQueryFactory = FaceletCorePlugin.getDefault().getCompCoreQueryFactory();
        AbstractVirtualComponentQuery virtualComponentQuery = compCoreQueryFactory.createVirtualComponentQuery(getProject());
        if (virtualComponentQuery != null)
        {
            return virtualComponentQuery.getWebContentFolder(getProject());
        }
        return null;
    }
}
