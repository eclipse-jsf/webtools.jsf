/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.runtime.internal.view.model.common;

import java.util.Map;

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

    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement#getAttributeHandlers()
     */
    public abstract Map getAttributeHandlers();
}
