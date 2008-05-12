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
package org.eclipse.jst.jsf.designtime.internal.view.model.jsp;

import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IJSFTagElement;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDElementDeclaration;

/**
 * A JSF tag element.
 * 
 * @author cbateman
 * 
 */
public abstract class TLDJSFTagElement extends TLDTagElement implements
        IJSFTagElement
{
    /**
     * 
     */
    private static final long serialVersionUID = -7629153104201317346L;

    /**
     * @param elementDecl
     * @param advisor 
     */
    public TLDJSFTagElement(final TLDElementDeclaration elementDecl
            , final IAttributeAdvisor advisor)
    {
        super(elementDecl, advisor);
    }

    public abstract TagType getType();

}
