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
/**
 * 
 */
package org.eclipse.jst.pagedesigner.editpolicies;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jst.pagedesigner.parts.ElementEditPart;
import org.eclipse.jst.pagedesigner.parts.NonVisualComponentEditPart;

/**
 * A menu bar for a specific element edit part
 * 
 * @author cbateman
 *
 */
public class ElementMenuBar extends Figure implements ISelectionProvider, ISelectionChangedListener
{
    private ElementEditPart   _hostPart;
    /**
     * Should always be accessed for use through getChildParts().  Not automatically
     * allocated because of high probability that a host part will have no children of interest
     */
    private Set                    _childParts;
    private List                    _selectionListeners;
    private IStructuredSelection    _selection;

    /**
     * @param hostPart
     */
    public ElementMenuBar(ElementEditPart hostPart)
    {
        hostPart.getViewer().addSelectionChangedListener(this);
        
        _hostPart = hostPart;
        ToolbarLayout layout = new ToolbarLayout(false);
        layout.setSpacing(4);
        setLayoutManager(layout);
    }

    /**
     * @return true if there are child parts in the menu
     */
    public boolean hasChildParts()
    {
        return _childParts != null &&_childParts.size() > 0;
    }
    
    /**
     * 
     */
    public void dispose()
    {
        _hostPart.getViewer().removeSelectionChangedListener(this);
        if (_childParts != null)
        {
            _childParts.clear();
            _childParts = null;
        }
        
        if (_selectionListeners != null)
        {
            _selectionListeners.clear();
            _selectionListeners = null;
        }
        
        _selection = null;
    }
    
    /**
     * @param editpart
     */
    public void addNonVisualChild(NonVisualComponentEditPart editpart)
    {
        add(editpart.getFigure());
        getChildParts().add(editpart);
    }
    
    /**
     * Removes editpart from the list of non-visual edit parts that this menu
     * bar is managing the visuals for. 
     * @param editpart
     */
    public void removeNonVisualChild(final NonVisualComponentEditPart editpart)
    {
        // TODO: I have relaxed the checking to see if editpart is valid
        // child because of the way ElementEditPart refreshes itself
        // when the model changes.  It's possible this will get called more
        // than once for the same editpart argument
        getChildParts().remove(editpart);
        if (editpart.getFigure().getParent() == this)
        {
            remove(editpart.getFigure());
        }
    }

    /**
     * @return true if a member of the menu bar has edit part focus
     */
    public boolean hasEditPartFocus()
    {
        for (Iterator it = getChildParts().iterator(); it.hasNext();)
        {
            EditPart editPart = (EditPart) it.next();
            
            if (editPart.getSelected() == EditPart.SELECTED_PRIMARY)
            {
                return true;
            }
        }
        return false;
    }
    
    private Set getChildParts()
    {
        if (_childParts == null) 
        {
            _childParts = new HashSet();
        }

        return _childParts;
    }

    protected void paintChildren(Graphics graphics) 
    {
        // force all children to paint with my alpha setting
        graphics.setAlpha(getAlpha());
        //push the current state so it is what the children revert to
        graphics.pushState();
        super.paintChildren(graphics);
        // pop that state we pushed above
        graphics.popState();
    }

    private int getAlpha()
    {
        return isEnabled() ? 255 : 75;
    }

    public void addSelectionChangedListener(ISelectionChangedListener listener) {
        List listeners = getSelectionListeners();
        
        if (!listeners.contains(listener))
        {
            listeners.add(listener);
        }
    }

    public ISelection getSelection() {
        return _selection;
    }

    public void removeSelectionChangedListener(
            ISelectionChangedListener listener) {
        getSelectionListeners().remove(listener);
    }

    public void setSelection(ISelection selection) {
        // no external selection change supported
    }
    
    private List getSelectionListeners()
    {
        if (_selectionListeners == null)
        {
            _selectionListeners = new ArrayList(1);
        }
        
        return _selectionListeners;
    }

    private void fireSelectionChanged() 
    {
        for (final Iterator it = _selectionListeners.iterator(); it.hasNext();)
        {
            ISelectionChangedListener listener = (ISelectionChangedListener) it.next();
            listener.selectionChanged(new SelectionChangedEvent(this, _selection));
        }
    }

    public void selectionChanged(SelectionChangedEvent event) {
        ISelection selection = event.getSelection();
        
        if (selection instanceof IStructuredSelection)
        {
            // the number of selections we care about will be no bigger than the total number of selections
            final List mySelections = new ArrayList(((IStructuredSelection)selection).size());
            
            if (_childParts != null)
            {
                for (final Iterator it = ((IStructuredSelection)selection).iterator()
                        ; it.hasNext();)
                {
                    Object selectedObj = it.next();
                    if (_childParts.contains(selectedObj))
                    {
                        mySelections.add(selectedObj);
                    }
                }
            }

            _selection = new StructuredSelection(mySelections);
            
            fireSelectionChanged();
        }
    }
}