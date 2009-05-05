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
package org.eclipse.jst.pagedesigner.editpolicies;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.eclipse.gef.requests.DropRequest;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.core.internal.tld.TagIdentifierFactory;
import org.eclipse.jst.pagedesigner.editors.palette.IDropSourceData;
import org.eclipse.jst.pagedesigner.editors.palette.ITagDropSourceData;
import org.eclipse.jst.pagedesigner.elementedit.ElementEditFactoryRegistry;
import org.eclipse.jst.pagedesigner.elementedit.IElementEdit;
import org.eclipse.jst.pagedesigner.itemcreation.ItemCreationRequest;
import org.eclipse.jst.pagedesigner.parts.NodeEditPart;
import org.eclipse.jst.pagedesigner.validation.caret.IPositionMediator;
import org.eclipse.jst.pagedesigner.validation.caret.DropActionData.DropData;
import org.eclipse.jst.pagedesigner.viewer.DefaultDropLocationStrategy;
import org.eclipse.jst.pagedesigner.viewer.DesignPosition;
import org.eclipse.jst.pagedesigner.viewer.IDropLocationStrategy;

/**
 * @author cbateman
 *
 */
public abstract class DropEditPolicy extends GraphicalEditPolicy 
{
    private List        _feedbackFigures;

    /**
     * @param r
     * @return a mediator that can validate valid model drops into the
     * host's edit part
     */
    protected IPositionMediator createDropChildValidator(DropRequest r)
    {
        // sub-class may override to customize the drop container validator
        return null;
    }
    
    /**
     * @param r
     * @return the appropriate design position for the drop request
     */
    protected abstract DesignPosition findPosition(DropRequest r);
    
    /**
     * @param data
     * @return a default validation mediator to be used if nothing custom is provided
     */
    protected abstract IPositionMediator createDefaultDropChildValidator(DropData data);
    
    /**
     * @param r
     * @return the validator to be used to validate the 'request' to drop
     * the edit parts specified by 'r' into this policy's host edit part
     */
    protected final IPositionMediator getDropChildValidator(DropRequest r)
    {
        IPositionMediator mediator = createDropChildValidator(r);
        
        if (mediator == null)
        {
            DropData data = createDropData(r);
            
            if (data == null)
            {
                return null;
            }
            mediator = createDefaultDropChildValidator(data);
        }
       
        return mediator;
    }
    
    /**
     * @param request
     * @return the data about the drop request
     */
    protected DropData createDropData(DropRequest request)
    {
        if (request instanceof GroupRequest)
        {
            List editParts = ((GroupRequest)request).getEditParts();
            
            // TODO: currently we only support one drop item
            if (editParts.size() > 0)
            {
                EditPart part = (EditPart) editParts.get(0);
                if (part instanceof NodeEditPart)
                {
                    NodeEditPart nodePart = (NodeEditPart) part;
                    TagIdentifier tagId = nodePart.getTagIdentifier();
                    if (tagId != null)
                    {
                        List tagIds = new ArrayList();
                        tagIds.add(tagId);
                        return new DropData(tagIds);
                    }
                }
            }
        }
        else if (request instanceof ItemCreationRequest)
        {
            IDropSourceData creationProvider = 
                ((ItemCreationRequest)request).getTagCreationProvider();
            String tagName = creationProvider.getId();
            if (creationProvider instanceof ITagDropSourceData)
            {
                tagName = ((ITagDropSourceData)creationProvider).getTagName();
            }
            TagIdentifier tagId = 
                TagIdentifierFactory.
                    createJSPTagWrapper(creationProvider.getNamespace(),
                            tagName);
            List tagIds = new ArrayList();
            tagIds.add(tagId);
            return new DropData(tagIds);
        }
        
        return null;
    }
    
    /**
     * @param r
     * @return the drop location strategy
     */
    protected final IDropLocationStrategy createDropLocationStrategy(DropRequest r)
    {
        DropData dropData = createDropData(r);
        if (dropData != null && dropData.getTagIdentifiers().size() > 0)
        {
            // TODO: only supporting single item drop currently
            TagIdentifier tagIdentifier = 
                (TagIdentifier) dropData.getTagIdentifiers().get(0);

            IElementEdit elementEdit = 
                ElementEditFactoryRegistry.getInstance()
                    .createElementEdit(tagIdentifier); 

            // element edit not guaranteed to exist
            if (elementEdit != null)
            {
                IDropLocationStrategy strategy = 
                    elementEdit.getDropRequestorLocationStrategy(tagIdentifier, getHost().getViewer());
                
                if (strategy != null)
                {
                    return strategy;
                }
            }
        }
        
        // by default, return the default strategy
        return new DefaultDropLocationStrategy(getHost().getViewer());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#eraseTargetFeedback(org.eclipse.gef.Request)
     */
    public void eraseTargetFeedback(Request request) {
        if (_feedbackFigures != null)
        {
            for (final Iterator it = _feedbackFigures.iterator(); it.hasNext();)
            {
                final IFigure figure = (IFigure) it.next();
                
                if (figure != null)
                {
                    removeFeedback(figure);
                }
            }
            
            _feedbackFigures.clear();
            _feedbackFigures = null;
        }
    }
    
    public void showTargetFeedback(Request request) 
    {
        if (request instanceof DropRequest) 
        {
            DesignPosition position = findPosition((DropRequest)request);
            if (position != null) {
                // erase any prior feedback
                eraseTargetFeedback(request);
                // add figures to feedback layer and save them in _feedbackFigures
                // for later.
                _feedbackFigures = createDropLocationStrategy((DropRequest)request).showTargetFeedback(getHost(), position, (DropRequest)request); 
            }
        }
    }
}
