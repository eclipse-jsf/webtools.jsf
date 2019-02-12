/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.internal.finder.matcher;

import org.eclipse.jst.jsf.common.internal.strategy.AbstractIdentifiableStrategy;


/**
 * A finder strategy for Facelet tag libraries.
 * @author cbateman
 *
 * @param <INPUT>
 * @param <OUTPUT>
 */
public abstract class TaglibFinder<INPUT, OUTPUT> extends AbstractIdentifiableStrategy<INPUT, OUTPUT, String>
{
    /**
     * @param id
     * @param displayName
     * @param noResultValue
     */
    public TaglibFinder(String id, String displayName,
            OUTPUT noResultValue)
    {
        super(id, displayName, noResultValue);
    }

    @Override
    public abstract OUTPUT perform(INPUT input) throws Exception;
}