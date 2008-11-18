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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jst.jsf.common.metadata.query.ITaglibDomainMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.query.TaglibDomainMetaDataQueryHelper;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IWorkspaceContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.editors.palette.TagToolPaletteEntry;
import org.eclipse.jst.pagedesigner.itemcreation.CreationData;
import org.eclipse.jst.pagedesigner.itemcreation.ITagCreator;
import org.eclipse.jst.pagedesigner.itemcreation.internal.TagCreationFactory;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.w3c.dom.Element;

/**
 * @author mengbo
 * @version 1.5
 */
public class CommandUtil {
	private static final Logger _log = PDPlugin.getLogger(CommandUtil.class);

	/**
	 * @param tagItem
	 * @param model
	 * @param domPosition
	 * @param customizationData 
	 * @return the element inserted or null if failed
	 */
	public static Element excuteInsertion(TagToolPaletteEntry tagItem,
			IDOMModel model, IDOMPosition domPosition, IAdaptable customizationData) {
		try 
		{	
		    IStructuredDocument doc = model.getDocument().getStructuredDocument();
		    
		    IStructuredDocumentContext context =
		        IStructuredDocumentContextFactory.INSTANCE.getContext(doc, -1);
		    IWorkspaceContextResolver  resolver =
		        IStructuredDocumentContextResolverFactory.INSTANCE.getWorkspaceContextResolver(context);
		    
		    IProject project = resolver.getProject();
		    
            final ITaglibDomainMetaDataModelContext modelContext = 
                TaglibDomainMetaDataQueryHelper
                    .createMetaDataModelContext(project, tagItem.getURI());

            // TODO: note that the constructor currently causes the prefix defn
            // to be added to the target doc.  This should be moved into
            // an ensure method in the AbstractTagCreator
            final CreationData creationData = 
                new CreationData(tagItem, model, domPosition, modelContext, customizationData);

		    ITagCreator tagCreator = 
			    TagCreationFactory.getInstance().createTagCreator(creationData.getTagId());
			
			if (tagCreator == null)
			{
				return null;//should never get here!
			}

			return tagCreator.createTag(creationData);
		} catch (Exception e) {
			_log.error("Problem creating tag "+tagItem.getTagName()+" at:" + domPosition + "\n", e); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
        return null;
	}

}
