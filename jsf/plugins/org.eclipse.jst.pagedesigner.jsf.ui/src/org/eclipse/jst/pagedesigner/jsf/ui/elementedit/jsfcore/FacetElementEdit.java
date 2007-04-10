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
package org.eclipse.jst.pagedesigner.jsf.ui.elementedit.jsfcore;

import org.eclipse.gef.EditPart;
import org.eclipse.jst.pagedesigner.elementedit.AbstractElementEdit;
import org.eclipse.jst.pagedesigner.parts.ElementEditPart;
import org.w3c.dom.Element;

/**
 * Element edit for a f:facet
 * 
 * @author cbateman
 *
 */
public class FacetElementEdit extends AbstractElementEdit
{
    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.elementedit.AbstractElementEdit#handleModelChange(org.w3c.dom.Element, org.eclipse.jst.pagedesigner.parts.ElementEditPart)
     */
    public boolean handleModelChange(Element ele, ElementEditPart part,boolean recursive)
    {
        EditPart parent = part.getParent();
        if (parent instanceof ElementEditPart)
        {
            ((ElementEditPart) parent).refreshModelChange(recursive);
            return true;
        }
        return false;
    }
}
