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
package org.eclipse.jst.pagedesigner.utils;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataDomainContext;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.query.ITaglibDomainMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.query.TaglibDomainMetaDataQueryHelper;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryContextFactory;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.editors.palette.IDropSourceData;
import org.eclipse.jst.pagedesigner.editors.palette.ITagDropSourceData;
import org.eclipse.jst.pagedesigner.itemcreation.CreationData;
import org.eclipse.jst.pagedesigner.itemcreation.ITagCreator;
import org.eclipse.jst.pagedesigner.itemcreation.internal.TagCreationFactory;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.w3c.dom.Element;

/**
 * @author mengbo
 * @version 1.5
 */
public class CommandUtil
{
    private static final Logger _log = PDPlugin.getLogger(CommandUtil.class);

    /**
     * @param dropSourceData
     * @param model
     * @param domPosition
     * @param customizationData
     * @return the element inserted or null if failed
     */
    public static Element executeInsertion(
            final IDropSourceData dropSourceData, final IDOMModel model,
            final IDOMPosition domPosition, final IAdaptable customizationData)
    {
        try
        {
            final IMetaDataModelContext modelContext = CommandUtil.getMetadataModelContext(
                    dropSourceData.getNamespace(), model);

            ITagDropSourceData tagDropSourceData = null;

            if (dropSourceData instanceof ITagDropSourceData)
            {
                tagDropSourceData = (ITagDropSourceData) dropSourceData;
            } else if (customizationData != null)
            {
                tagDropSourceData = (ITagDropSourceData) customizationData
                        .getAdapter(ITagDropSourceData.class);
            }

            if (tagDropSourceData == null)
            {
                PDPlugin
                        .log(
                                "Could not down cast dropSourceData to tagDropSourceData", new Exception("for stack trace only")); //$NON-NLS-1$//$NON-NLS-2$
            }

            // TODO: note that the constructor currently causes the prefix defn
            // to be added to the target doc. This should be moved into
            // an ensure method in the AbstractTagCreator
            final CreationData creationData = new CreationData(
                    tagDropSourceData, model, domPosition, modelContext,
                    customizationData);

            final ITagCreator tagCreator = TagCreationFactory.getInstance()
                    .createTagCreator(creationData.getTagId());

            if (tagCreator == null)
            {
                return null;// should never get here!
            }

            return tagCreator.createTag(creationData);
        } catch (Exception e)
        {
            _log
                    .error(
                            "Problem creating tag " + dropSourceData.getId() + " at:" + domPosition + "\n", e); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
        return null;
    }
    
    /**
     * @param uri
     * @param model
     * @return the metadata context for uri in the DOM model or null if none.
     * @deprecated - will be removed in post Indigo release
     */
    public static ITaglibDomainMetaDataModelContext getMetadataContext(
            final String uri, final IDOMModel model)
    {
//        final IStructuredDocument doc = model.getDocument()
//                .getStructuredDocument();
//
//        final IStructuredDocumentContext context = IStructuredDocumentContextFactory.INSTANCE
//                .getContext(doc, -1);
//        final IWorkspaceContextResolver resolver = IStructuredDocumentContextResolverFactory.INSTANCE
//                .getWorkspaceContextResolver(context);
//
//        final IProject project = resolver.getProject();

    	final IProject project = StructuredModelUtil.getProjectFor(model);
        final ITaglibDomainMetaDataModelContext modelContext = TaglibDomainMetaDataQueryHelper
                .createMetaDataModelContext(project, uri);
        return modelContext;
    }

    /**
     * @param uri
     * @param model
     * @return the metadata model context for uri in the DOM model or null if none.
     */
    public static IMetaDataModelContext getMetadataModelContext(
            final String uri, final IDOMModel model)
    {
    	final IFile file = StructuredModelUtil.getFileFor(model);
        final IMetaDataDomainContext modelContext = MetaDataQueryContextFactory.getInstance().createTaglibDomainModelContext(file);

        return new MetaDataModelContext(uri, modelContext);
    }
}
