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
package org.eclipse.jst.jsf.core.tests.strategy;

import java.util.regex.Pattern;

import org.eclipse.jst.jsf.common.internal.strategy.IIdentifiableStrategy;

class ConcatStrategy implements IIdentifiableStrategy<String, String, String>
{
    private final Pattern _pattern;
    private final String _concat;
    private final String _id;

    public ConcatStrategy(final Pattern pattern, final String toConcat, final String id)
    {
        _pattern = pattern;
        _concat = toConcat;
        _id = id;
    }

    public String perform(String input) throws Exception
    {
        if (_pattern.matcher(input).matches())
        {
            return input.concat(_concat);
        }
        return getNoResult();
    }

    public String getNoResult()
    {
        return null;
    }

    public String getId()
    {
        return _id;
    }

    public String getDisplayName()
    {
        return "Concat strategy, id="+getId();
    }
    
}