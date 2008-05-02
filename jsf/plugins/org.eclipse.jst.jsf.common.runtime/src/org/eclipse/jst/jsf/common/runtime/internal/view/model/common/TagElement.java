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
package org.eclipse.jst.jsf.common.runtime.internal.view.model.common;

/**
 * Abstract a tag element used to construct a view element
 * 
 * @author cbateman
 *
 */
public abstract class TagElement implements ITagElement 
{
    /**
     * 
     */
    private static final long serialVersionUID = 7885641652240047924L;

    /* (non-Javadoc)
     * @see viewhandlerprototype.model.ITagElement#getName()
     */
    public abstract String getName();

    
    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement#getUri()
     */
    public abstract String getUri();

    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement#getTagHandlerClassName()
     */
    public abstract String getTagHandlerClassName();
}
