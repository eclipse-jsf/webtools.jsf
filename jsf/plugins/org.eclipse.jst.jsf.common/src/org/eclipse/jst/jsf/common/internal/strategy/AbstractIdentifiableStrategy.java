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
package org.eclipse.jst.jsf.common.internal.strategy;

/**
 * Abstract base implementation for identifiable strategies.
 * 
 * @author cbateman
 *
 * @param <INPUT>
 * @param <OUTPUT>
 * @param <IDTYPE>
 */
public abstract class AbstractIdentifiableStrategy<INPUT, OUTPUT, IDTYPE> implements
        IIdentifiableStrategy<INPUT, OUTPUT, IDTYPE>
{
    private final IDTYPE  _id;
    private final OUTPUT _noResultValue;
    private final String _displayName;

    /**
     * @param id
     * @param displayName
     * @param noResultValue
     */
    public AbstractIdentifiableStrategy(final IDTYPE id, final String displayName,
            final OUTPUT noResultValue)
    {
        _id = id;
        _noResultValue = noResultValue;
        _displayName = displayName;
    }

    public abstract OUTPUT perform(INPUT input) throws Exception;

    public OUTPUT getNoResult()
    {
        return _noResultValue;
    }

    public IDTYPE getId()
    {
        return _id;
    }

    public String getDisplayName()
    {
        return _displayName;
    }

}
