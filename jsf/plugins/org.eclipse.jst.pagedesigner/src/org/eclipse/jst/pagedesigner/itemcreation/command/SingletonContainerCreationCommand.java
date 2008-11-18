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

import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.utils.BodyHelper;
import org.eclipse.jst.pagedesigner.validation.caret.JSFRootContainerPositionRule;
import org.w3c.dom.Document;

/**
 * Used to create a container that should only occur once per document. Typically
 * used for HTML body and JSF f:view tags.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * Clients may reference but not sub-class.
 * @author cbateman
 *
 */
public final class SingletonContainerCreationCommand extends ContainerCreationCommand 
{
    private static final int  MAX_DEPTH_TO_SEARCH_FOR_CONTAINER = 10;

    /**
     * @param domPosition
     * @param containerTag
     * @param tagBeingCreated 
     */
    public SingletonContainerCreationCommand(IDOMPosition domPosition,
            TagIdentifier containerTag, TagIdentifier tagBeingCreated) 
    {
        super(domPosition, containerTag, tagBeingCreated);
    }

    @Override
    protected IDOMPosition doExecute() 
    {
       return insertSingletonIfNotPresent();
    }
    
    private IDOMPosition insertSingletonIfNotPresent()
    {
        IDOMPosition domPosition = getDomPosition();
        final TagIdentifier containerTag = getContainerTag();
        final TagIdentifier tagBeingCreated = getTagBeingCreated();
        
        final Document document = EditModelQuery.getDocumentNode(domPosition
                .getContainerNode());
        
        final boolean isContainerAlreadyPresent =
            JSFRootContainerPositionRule.hasBasicContainers
                (document, MAX_DEPTH_TO_SEARCH_FOR_CONTAINER);

        if (isContainerAlreadyPresent) 
        {
            // if the container and element to be created are the same,
            // then return null indicating to abort
            if (containerTag.equals(tagBeingCreated))
            {
                domPosition =  null;
            }
            // otherwise, simply fall through keeping domPosition unchanged
        }
        // if the container isn't already present
        else
        {
            
            if (!containerTag.equals(tagBeingCreated)) 
            {
            // if the container is different and doesn't exist, then add it.
                domPosition = BodyHelper.insertBody(domPosition, containerTag.asQName(), "f"); //$NON-NLS-1$
            }
            // otherwise, fall through leaving domPosition unchanged
        }

        return domPosition;
    }

    @Override
    protected boolean prepare()
    {
        throw new UnsupportedOperationException("don't use"); //$NON-NLS-1$
    }
}
