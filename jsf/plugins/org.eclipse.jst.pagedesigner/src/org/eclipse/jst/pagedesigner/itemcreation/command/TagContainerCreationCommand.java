/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.itemcreation.command;

import javax.xml.namespace.QName;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.ResolverUtil;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.dom.ValidatorSupport;
import org.eclipse.jst.pagedesigner.editors.palette.ITagDropSourceData;
import org.eclipse.jst.pagedesigner.editors.palette.TagToolCreationAdapter;
import org.eclipse.jst.pagedesigner.editors.palette.impl.PaletteItemManager;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;

/**
 * @author cbateman
 * 
 *  * <p><b>Provisional API - subject to change</b></p>
 *  
 *  Clients may sub-class
 *
 */
public class TagContainerCreationCommand extends ContainerCreationCommand {

    /**
     * @param domPosition
     * @param containerTag
     * @param tagBeingCreated
     */
    public TagContainerCreationCommand(IDOMPosition domPosition,
            TagIdentifier containerTag, TagIdentifier tagBeingCreated) 
    {
        super(domPosition, containerTag, tagBeingCreated);
    }

    @Override
    protected IDOMPosition doExecute() 
    {
        final IDOMPosition domPosition = getDomPosition();
        final QName  containerQName = getContainerTag().asQName();
        boolean hasform = ValidatorSupport.checkContainer(domPosition, containerQName);
        IDOMPosition newPosition = domPosition;
        if (!hasform)
        {
            final IDOMModel model = ((IDOMNode) domPosition.getContainerNode())
                .getModel();
            
            final IFile fileForDocument = ResolverUtil.getFileForDocument(model.getStructuredDocument());            
            final ITagDropSourceData creationProvider =
                TagToolCreationAdapter.findProviderForContainer(containerQName, PaletteItemManager.createPaletteContext(fileForDocument));
            newPosition = ValidatorSupport
                    .insertContainer(domPosition, model, creationProvider, getContainerCustomizationData());
            if (newPosition == null) {
                newPosition = domPosition;
            }
        }
        return newPosition;
    }

    /**
     * Override to inject customization data into the tag creation
     * @return a customization data for the container.
     */
    protected IAdaptable getContainerCustomizationData()
    {
        return null;
    }
    
    @Override
    protected final boolean prepare()
    {
        throw new UnsupportedOperationException("don't use"); //$NON-NLS-1$
    }
}
