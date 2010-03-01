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
package org.eclipse.jst.jsf.common.internal.strategy;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jst.jsf.common.JSFCommonPlugin;
import org.eclipse.jst.jsf.common.internal.policy.IIteratorPolicy;

/**
 * A strategy composite that uses an iterator policy to provide the iterator
 * used to decide what order to execute the strategy in.
 * 
 * This composite represents a grouping of strategies which represent N ways
 * to perform a particular calculation and which any number for those N ways
 * may be applicable to any particular situation given the policy in place.
 * 
 * By default, the first such strategy in policy order to provide the calculation 
 * wins and it's result is returned.  You can modify the way the result is composed
 * by providing your own composition strategy using the two-arg constructor.
 * 
 *
 * 
 * @author cbateman
 * 
 * @param <INPUT>
 * @param <OUTPUT>
 * @param <RESULTTYPE> 
 * @param <IDTYPE>
 * @param <STRATEGYTYPE>
 */
public abstract class IteratorPolicyBasedStrategyComposite<INPUT, OUTPUT, RESULTTYPE, IDTYPE, STRATEGYTYPE extends IIdentifiableStrategy<INPUT, OUTPUT, IDTYPE>>
        extends StrategyComposite<INPUT, OUTPUT, RESULTTYPE, IDTYPE, STRATEGYTYPE>
{
    private final Map<IDTYPE, STRATEGYTYPE> _strategies;
    private IIteratorPolicy<IDTYPE>         _policy;
    private final Object                    _changeLock = new Object();

    /**
     * @param policy 
     */
    protected IteratorPolicyBasedStrategyComposite(final IIteratorPolicy<IDTYPE> policy)
    {
        super();
        _policy = policy;
        _strategies = new HashMap<IDTYPE, STRATEGYTYPE>();
    }

    /**
     * @param policy
     * @param compositionStrategy
     */
    protected IteratorPolicyBasedStrategyComposite(final IIteratorPolicy<IDTYPE> policy, AbstractCompositionStrategy<INPUT, OUTPUT, RESULTTYPE, STRATEGYTYPE> compositionStrategy)
    {
        super(compositionStrategy);
        _policy = policy;
        _strategies = new HashMap<IDTYPE, STRATEGYTYPE>();
    }

    /**
     * Add strategy if not already present.
     * 
     * @param strategy
     */
    public final void addStrategy(final STRATEGYTYPE strategy)
    {
        synchronized(_changeLock)
        {
            _strategies.put(strategy.getId(), strategy);
        }
    }

    /**
     * @param strategy
     */
    public final void removeStrategy(final STRATEGYTYPE strategy)
    {
        synchronized(_changeLock)
        {
            _strategies.remove(strategy.getId());
        }
    }

    /**
     * Change the active policy used to select the order in which the composite
     * calls it's child strategies.
     * 
     * If the policy is not set, then strategies are called in 
     * 
     * @param policy
     */
    public final void setPolicy(final IIteratorPolicy<IDTYPE>  policy)
    {
        // policy may not be null
        if (policy == null)
        {
            JSFCommonPlugin.log(new Exception("stack trace only"), "Policy can't be null"); //$NON-NLS-1$ //$NON-NLS-2$
            return;
        }
        
        // protect access in case getIterator is being called simulataneously
        synchronized(_changeLock)
        {
            _policy = policy;
        }
    }

    @Override
    public final Iterator<STRATEGYTYPE> getIterator()
    {
        IIteratorPolicy<IDTYPE> policy = null;
        Map<IDTYPE, STRATEGYTYPE>  strategies = Collections.emptyMap();

        synchronized(_changeLock)
        {
            policy = _policy;
            strategies = Collections.unmodifiableMap(new HashMap<IDTYPE, STRATEGYTYPE>(_strategies));
        }

        final Iterator<IDTYPE> iterator = policy.getIterator(strategies.keySet());
        return new StrategyIterator<IDTYPE, STRATEGYTYPE>(strategies, iterator);
    }

    @Override
    public abstract RESULTTYPE getNoResult();

    private static class StrategyIterator<IDTYPE, STRATEGYTYPE> implements
            Iterator<STRATEGYTYPE>
    {
        private final Map<IDTYPE, STRATEGYTYPE> _map;
        private final Iterator<IDTYPE>          _policyIterator;

        private StrategyIterator(final Map<IDTYPE, STRATEGYTYPE> map,
                final Iterator<IDTYPE> policyIterator)
        {
            _map = map;
            _policyIterator = policyIterator;
        }

        public boolean hasNext()
        {
            return _policyIterator.hasNext();
        }

        public STRATEGYTYPE next()
        {
            IDTYPE id = _policyIterator.next();
            return _map.get(id);
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }
}
