package org.eclipse.jst.jsf.common.internal.strategy;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jst.jsf.common.internal.policy.IIteratorPolicy;

/**
 * A strategy composite that uses an iterator policy to provide the iterator
 * used to decide what order to execute the strategy in.
 * 
 * @author cbateman
 * 
 * @param <INPUT>
 * @param <OUTPUT>
 * @param <IDTYPE>
 * @param <STRATEGYTYPE>
 */
public abstract class PolicyBasedStrategyComposite<INPUT, OUTPUT, IDTYPE, STRATEGYTYPE extends IIdentifiableStrategy<INPUT, OUTPUT, IDTYPE>>
        extends StrategyComposite<INPUT, OUTPUT, IDTYPE, STRATEGYTYPE>
{
    private final Map<IDTYPE, STRATEGYTYPE> _strategies;
    private IIteratorPolicy<IDTYPE>         _policy;

    /**
     * @param policy 
     */
    protected PolicyBasedStrategyComposite(final IIteratorPolicy<IDTYPE> policy)
    {
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
        _strategies.put(strategy.getId(), strategy);
    }

    /**
     * @param strategy
     */
    public final void removeStrategy(final STRATEGYTYPE strategy)
    {
        _strategies.remove(strategy.getId());
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
        _policy = policy;
    }

    @Override
    public final Iterator<STRATEGYTYPE> getIterator()
    {
        final Iterator<IDTYPE> idType = _policy.getIterator(_strategies
                .keySet());
        return new StrategyIterator<IDTYPE, STRATEGYTYPE>(_strategies, idType);
    }

    @Override
    public abstract OUTPUT getNoResult();

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
