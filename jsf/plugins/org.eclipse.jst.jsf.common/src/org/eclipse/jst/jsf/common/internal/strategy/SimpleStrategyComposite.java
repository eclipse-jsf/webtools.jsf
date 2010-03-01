package org.eclipse.jst.jsf.common.internal.strategy;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 * A simple concrete implementation that uses a constructor provided values
 * to implement abstract methods.
 * 
 * @author cbateman
 *
 * @param <INPUT>
 * @param <OUTPUT>
 * @param <RESULTTYPE> 
 * @param <IDTYPE>
 * @param <STRATEGYTYPE>
 */
public class SimpleStrategyComposite<INPUT, OUTPUT, RESULTTYPE, IDTYPE, STRATEGYTYPE extends IIdentifiableStrategy<INPUT,OUTPUT,IDTYPE>> extends
StrategyComposite<INPUT, OUTPUT, RESULTTYPE, IDTYPE, STRATEGYTYPE>
{

    private final RESULTTYPE _noResultValue;
    private final Collection<STRATEGYTYPE> _strategies;

    /**
     * Use the list of strategies and null as the no result value.
     * @param strategies
     */
    public SimpleStrategyComposite(final Collection<STRATEGYTYPE> strategies)
    {
        this(strategies, (RESULTTYPE) null);
    }
    /**
     * Use the list of strategies and the provided no result value.
     * @param strategies
     * @param noResultValue
     */
    public SimpleStrategyComposite(final Collection<STRATEGYTYPE> strategies, final RESULTTYPE noResultValue)
    {
        super();
        _strategies = strategies;
        _noResultValue = noResultValue;
    }

    /**
     * Use the provided strategies, composition strategy and null for the no result value
     * @param strategies
     * @param compositionStrategy
     */
    public SimpleStrategyComposite(final Collection<STRATEGYTYPE> strategies,
            final AbstractCompositionStrategy<INPUT, OUTPUT, RESULTTYPE, STRATEGYTYPE> compositionStrategy)
    {
        this(strategies, null, compositionStrategy);
    }

    /**
     * Use the provided strategies, composition strategy and null for the no result value
     * @param strategies
     * @param noResultValue
     * @param compositionStrategy
     */
    public SimpleStrategyComposite(final Collection<STRATEGYTYPE> strategies, final RESULTTYPE noResultValue,
            final AbstractCompositionStrategy<INPUT, OUTPUT, RESULTTYPE, STRATEGYTYPE> compositionStrategy)
    {
        super(compositionStrategy);
        _strategies = strategies;
        _noResultValue = noResultValue;
    }

    @Override
    public RESULTTYPE getNoResult()
    {
        return _noResultValue;
    }

    @Override
    public Iterator<STRATEGYTYPE> getIterator()
    {
        return Collections.unmodifiableCollection(_strategies).iterator();
    }

}
