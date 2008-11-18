/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
/**
 * 
 */
package org.eclipse.jst.jsf.designtime.internal.view.model.jsp.registry;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.core.internal.JSFCoreTraceOptions;
import org.eclipse.jst.jsp.core.taglib.ITaglibIndexDelta;
import org.eclipse.jst.jsp.core.taglib.ITaglibIndexListener;

class TagIndexListener implements ITaglibIndexListener
{
    private final TLDTagRegistry _registry;
    private final LibraryOperationFactory  _factory;
    
    TagIndexListener(final TLDTagRegistry registry)
    {
        _registry = registry;
        _factory = new LibraryOperationFactory(_registry);
    }

    public void indexChanged(final ITaglibIndexDelta delta)
    {
        if (JSFCoreTraceOptions.TRACE_JSPTAGREGISTRY_CHANGES)
        {
            JSFCoreTraceOptions.log("TagIndexListener.indexChanged: start"); //$NON-NLS-1$
        }

        visitDelta(delta);

        if (JSFCoreTraceOptions.TRACE_JSPTAGREGISTRY_CHANGES)
        {
            JSFCoreTraceOptions.log("TagIndexListener.indexChanged: finish"); //$NON-NLS-1$
        }
    }

    private void visitDelta(final ITaglibIndexDelta delta)
    {
        final IProject project = delta.getProject();

        if (_registry != null && !_registry.isDisposed()
                && delta.getTaglibRecord() != null)
        {
            switch (delta.getKind())
            {
                case ITaglibIndexDelta.ADDED:
                {
                    if (JSFCoreTraceOptions.TRACE_JSPTAGREGISTRY_CHANGES)
                    {
                        JSFCoreTraceOptions.log(String.format(
                                "Processing add change: project=%s", project //$NON-NLS-1$
                                        .getName()));
                    }

                    _registry.addLibraryOperation(_factory
                            .createAddOperation(delta.getTaglibRecord()));
                }
                break;
                case ITaglibIndexDelta.REMOVED:
                {
                    if (JSFCoreTraceOptions.TRACE_JSPTAGREGISTRY_CHANGES)
                    {
                        JSFCoreTraceOptions
                                .log(String
                                        .format(
                                                "Processing remove change: project=%s, tagrecord=%s", //$NON-NLS-1$
                                                project.getName()));
                    }
                    _registry.addLibraryOperation(_factory
                            .createRemoveOperation(delta.getTaglibRecord()));
                }
                break;
                case ITaglibIndexDelta.CHANGED:
                {
                    if (JSFCoreTraceOptions.TRACE_JSPTAGREGISTRY_CHANGES)
                    {
                        JSFCoreTraceOptions.log(String.format(
                                "Processing change: project=%s, tagrecord=%s", //$NON-NLS-1$
                                project.getName()));
                    }
                    _registry.addLibraryOperation(_factory
                            .createChangeOperation(delta.getTaglibRecord()));
                }
                break;
            }
        }

        // visit children
        for (final ITaglibIndexDelta child : delta.getAffectedChildren())
        {
            visitDelta(child);
        }
    }
}