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
package org.eclipse.jst.jsf.designtime.internal.view.model.jsp;

import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;

/**
 * Implements the tag resolving strategy.
 * 
 * @author cbateman
 * 
 * @param <TLDELEMENT>
 * @param <IDTYPE>
 */
public abstract class AbstractTagResolvingStrategy<TLDELEMENT, IDTYPE>
        implements ITagResolvingStrategy<TLDELEMENT, IDTYPE>
{
    private final static ITagElement        NOT_FOUND_INDICATOR = null;
    
    public ITagElement getNotFoundIndicator()
    {
        return NOT_FOUND_INDICATOR;
    }

    public abstract ITagElement resolve(TLDELEMENT element);

    public abstract IDTYPE getId();

    /**
     * Must always be the same as resolve.
     */
    public final ITagElement perform(TLDELEMENT element)
    {
        return resolve(element);
    }

    /**
     * Must always be the same as getNotFoundIndicator.
     */
    public final ITagElement getNoResult()
    {
        return getNotFoundIndicator();
    }
}
