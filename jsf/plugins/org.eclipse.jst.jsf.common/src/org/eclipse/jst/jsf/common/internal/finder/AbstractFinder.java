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
package org.eclipse.jst.jsf.common.internal.finder;

import org.eclipse.jst.jsf.common.internal.strategy.AbstractIdentifiableStrategy;


/**
 * An abstract strategy that finds it's OUTPUT value in a container designated
 * by it's INPUT value.
 * 
 * @author cbateman
 *
 * @param <INPUT>
 * @param <OUTPUT>
 * @param <IDTYPE> 
 */
public abstract class AbstractFinder<INPUT, OUTPUT, IDTYPE> extends AbstractIdentifiableStrategy<INPUT, OUTPUT, IDTYPE>
{
    
    /**
     * @param id
     * @param displayName
     * @param noResultValue
     */
    public AbstractFinder(IDTYPE id, String displayName, OUTPUT noResultValue)
    {
        super(id, displayName, noResultValue);
    }
    
    /**
     * @param input
     * @return the output found in input or getNoResult() if not found.
     * @throws Exception
     */
    public final OUTPUT find(INPUT input) throws Exception
    {
        return perform(input);
    }
}
