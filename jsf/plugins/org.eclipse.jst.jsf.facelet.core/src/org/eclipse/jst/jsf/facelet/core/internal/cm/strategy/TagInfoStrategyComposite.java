/*******************************************************************************
 * Copyright (c) 2008, 2010 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facelet.core.internal.cm.strategy;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.common.internal.policy.IdentifierOrderedIteratorPolicy;
import org.eclipse.jst.jsf.common.internal.strategy.IteratorPolicyBasedStrategyComposite;
import org.eclipse.jst.jsf.facelet.core.internal.cm.ExternalTagInfo;
import org.eclipse.jst.jsf.facelet.core.internal.cm.TagInfo;

/**
 * A composite of strategies for deriving external tag metadata.
 * 
 * @author cbateman
 * 
 */
public class TagInfoStrategyComposite
        extends
        IteratorPolicyBasedStrategyComposite<TagIdentifier, TagInfo, TagInfo, String, IExternalMetadataStrategy>
{
    private final Iterable<String> _policyOrder;

    /**
     * @param policyOrder
     */
    public TagInfoStrategyComposite(final Iterable<String> policyOrder)
    {
        super(new MyIteratorPolicy(policyOrder));
        _policyOrder = policyOrder;
    }

    @Override
    public TagInfo getNoResult()
    {
        return ExternalTagInfo.NULL_INSTANCE;
    }

    /**
     * 
     */
    public void resetIterator()
    {
        setPolicy(new MyIteratorPolicy(_policyOrder));
    }

    private static class MyIteratorPolicy extends
            IdentifierOrderedIteratorPolicy<String>
    {
        private Iterator<String> _iterator;

        public MyIteratorPolicy(final Iterable<String> policyOrder)
        {
            super(policyOrder);
            setExcludeNonExplicitValues(true);
        }

        @Override
        public Iterator<String> getIterator(
                final Collection<String> forCollection)
        {
            if (_iterator == null)
            {
                _iterator = super.getIterator(forCollection);
            }
            return _iterator;
        }
    }
}
