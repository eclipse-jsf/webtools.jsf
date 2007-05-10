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
package org.eclipse.jst.pagedesigner.viewer;

import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.requests.DropRequest;
import org.eclipse.jst.pagedesigner.validation.caret.IPositionMediator;

/**
 * A strategy used to determine the closest valid drop location given
 * a current position.
 * 
 * Note: clients should not implement or sub-class this interface.  Instead,
 * sub-class AbstractDropLocationStrategy.
 * @author cbateman
 *
 */
public interface IDropLocationStrategy
{
    /**
     * @param host
     * @param p
     * @param validator
     * @return the closest valid design positionn to p, starting from host
     * that is valid based on validator, or null if there is no such position
     * (drop not valid).  validator.isValidPosition must be honoured.
     */
    DesignPosition calculateDesignPosition(EditPart host, Point p, IPositionMediator validator);
    
    /**
     * May choose to contribute target feedback given a target part host and given
     * the proposed drops location given by position
     * 
     * @param host 
     * @param position
     * @param request 
     * @return must contain all IFigure objects that were added to the feedback layer by
     * this call.  Once returned, these IFigure's must not be kept as state information
     * by the strategy because they may be manipulated at any time after return to the caller.
     * This includes erasing them from the feedback layer.  List must be modifiable by
     * the caller.
     * 
     * Note: most feedback in Draw2D requires that it is added to the feedback layer
     * before it can be fully initialized, which is why this call adds feedback and then
     * returns it.  The caller is responsible for erasing the figures in the list.
     */
    List showTargetFeedback(EditPart host, DesignPosition position, DropRequest request);
}
