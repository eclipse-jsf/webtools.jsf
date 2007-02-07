/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http:// www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.jsf.ui.elementedit.jsfhtml;

import java.util.Iterator;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.jst.pagedesigner.IJSFConstants;
import org.eclipse.jst.pagedesigner.dom.TagIdentifier;
import org.eclipse.jst.pagedesigner.editpolicies.ColumnHelper;
import org.eclipse.jst.pagedesigner.editpolicies.ColumnResizableEditPolicy;
import org.eclipse.jst.pagedesigner.parts.ElementEditPart;
import org.eclipse.jst.pagedesigner.parts.NodeEditPart;
import org.eclipse.jst.pagedesigner.validation.caret.IPositionMediator;
import org.eclipse.jst.pagedesigner.validation.caret.Target;
import org.eclipse.jst.pagedesigner.viewer.CaretPositionResolver;
import org.eclipse.jst.pagedesigner.viewer.DefaultDropLocationStrategy;
import org.eclipse.jst.pagedesigner.viewer.DesignPosition;
import org.eclipse.jst.pagedesigner.viewer.IDropLocationStrategy;
import org.w3c.dom.Element;

/**
 * @author mengbo
 * @version 1.5
 */
public class ColumnElementEdit extends DefaultJSFHTMLElementEdit
{

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.elementedit.AbstractElementEdit#handleModelChange(org.w3c.dom.Element, org.eclipse.jst.pagedesigner.parts.ElementEditPart)
     */
    public boolean handleModelChange(Element ele, ElementEditPart part, boolean recursive)
    {
        EditPart parent = part.getParent();
        if (parent instanceof ElementEditPart)
        {
            ((ElementEditPart) parent).refreshModelChange(recursive);
            return true;
        }
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.elementedit.IElementEdit#createEditPolicies(org.eclipse.jst.pagedesigner.parts.ElementEditPart)
     */
    public void createEditPolicies(ElementEditPart part)
    {
        part.installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new ColumnResizableEditPolicy());
    }
    
    public IDropLocationStrategy getDropRequestorLocationStrategy(TagIdentifier tag, EditPartViewer viewer) {
        return new MyDropLocationStrategy(viewer);
    }

    private static class MyDropLocationStrategy extends DefaultDropLocationStrategy
    {
        public MyDropLocationStrategy(EditPartViewer viewer) {
            super(viewer);
        }

        public DesignPosition calculateDesignPosition(EditPart target,
                Point p, IPositionMediator validator) 
        {
            // get the nearest ancestor part to target that is 
            // editable or target if it is editable
            target = validator.getEditableContainer(new Target(target));

            // target must be an element part
            if (target instanceof ElementEditPart)
            {
                // can only try to drop a column into a table.  If we are
                // targetting a column, then we can see if its table will take it
                TagIdentifier tagId = ((ElementEditPart)target).getTagIdentifier();
                
                if (IJSFConstants.TAG_IDENTIFIER_DATA_TABLE.isSameTagType(tagId))
                {
                    // if we're on the table, try to figure out which column we are above
                    for (Iterator it = target.getChildren().iterator(); it.hasNext();)
                    {
                        final NodeEditPart nodeEditPart = (NodeEditPart) it.next();
                        TagIdentifier tagId2 = nodeEditPart.getTagIdentifier();
                        
                        if (IJSFConstants.TAG_IDENTIFIER_COLUMN.isSameTagType(tagId2))
                        {
                            final Rectangle columnBounds = 
                                ColumnHelper.getAdjustedColumnBoundingRectangle(nodeEditPart);
                            if (columnBounds.contains(p))
                            {
                                target = nodeEditPart;
                                break;
                            }
                        }
                    }
                }

                // recalculate the id because it may have changed
                tagId = ((ElementEditPart)target).getTagIdentifier();
                
                if (IJSFConstants.TAG_IDENTIFIER_COLUMN.isSameTagType(tagId))
                {
                    DesignPosition position = null;
                    final Rectangle columnBounds = 
                        ColumnHelper.getAdjustedColumnBoundingRectangle((GraphicalEditPart) target);
                    if (CaretPositionResolver.toXMiddle(columnBounds, p) < 0)
                    {
                        position = DesignPosition.createPositionBeforePart(target);
                    }
                    else
                    {
                        position = DesignPosition.createPositionAfterPart(target);
                    }
                    
                     
                    if (validator.isValidPosition(position))
                    {
                        return position;
                    }
                }
            }
            
            // otherwise, no valid position
            return null;
        }

        protected Rectangle createCaretBounds(DesignPosition position) {
            Rectangle bounds = super.createCaretBounds(position);
            
            // we want to extend the insert caret to be the height of the column container (i.e. dataTable)
            // and positioned so it starts at the top of the container in the column where the insertion will occur
            GraphicalEditPart parent = 
                (GraphicalEditPart) position.getContainerPart();
            Rectangle containerBounds = parent.getFigure().getBounds().getCopy();
            parent.getFigure().translateToAbsolute(containerBounds);
            bounds.height = containerBounds.height;
            bounds.y = containerBounds.y;
            return bounds;
        }
    }
}
