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

import java.util.Iterator;

import org.eclipse.jst.jsf.common.JSFCommonPlugin;

/**
 * A composite strategy whos role is to iterate through a number of child
 * stategies until one returns a valid value for an input.
 * 
 * @author cbateman
 * 
 * @param <INPUT>
 * @param <OUTPUT>
 * @param <RESULTOUTPUT> 
 * @param <IDTYPE>
 * @param <STRATEGYTYPE>
 */
public abstract class StrategyComposite<INPUT, OUTPUT, RESULTOUTPUT, IDTYPE, STRATEGYTYPE extends IIdentifiableStrategy<INPUT, OUTPUT, IDTYPE>>
implements ISimpleStrategy<INPUT, RESULTOUTPUT>
{
    private final AbstractCompositionStrategy<INPUT, OUTPUT, RESULTOUTPUT, STRATEGYTYPE> _compositionStrategy;

    /**
     * @param compositionStrategy
     */
    protected StrategyComposite(
            final AbstractCompositionStrategy<INPUT, OUTPUT, RESULTOUTPUT, STRATEGYTYPE> compositionStrategy)
    {
        _compositionStrategy = compositionStrategy;
    }

    /**
     * Default constructor: composite returns the first value found.
     */
    protected StrategyComposite()
    {
        // by default, the composition strategy selects the first value
        this(
                new DefaultCompositionStrategy<INPUT, OUTPUT, RESULTOUTPUT, STRATEGYTYPE>());
    }

    public final RESULTOUTPUT perform(final INPUT input)
    {
        final Iterator<STRATEGYTYPE> sIt = getIterator();

        RESULTOUTPUT result = getNoResult();
        boolean finishedComposing = false;

        EXECUTE_LOOP: while (sIt.hasNext())
        {
            final STRATEGYTYPE strategy = sIt.next();
            try
            {
                finishedComposing = _compositionStrategy
                        .compose(new CompositionArguments<INPUT, OUTPUT, STRATEGYTYPE>(
                                strategy, input));
                // returns true if we are done composing
                if (finishedComposing)
                {
                    result = _compositionStrategy.getComposedResult();
                    break EXECUTE_LOOP;
                }
            } catch (final Exception e)
            {
                JSFCommonPlugin.log(e);
            }
        }

        if (finishedComposing)
        {
            return result;
        }
        return getNoResult();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.jst.jsf.common.internal.strategy.ISimpleStrategy#getNoResult
     * ()
     */
    public abstract RESULTOUTPUT getNoResult();

    /**
     * @return an iterator that will return the next strategy to be executed
     */
    public abstract Iterator<STRATEGYTYPE> getIterator();

    /**
     * @author cbateman
     * @param <INPUT>
     * @param <OUTPUT>
     * @param <RESULTOUTPUT> 
     * @param <STRATEGY>
     */
    public abstract static class AbstractCompositionStrategy<INPUT, OUTPUT, RESULTOUTPUT, STRATEGY extends ISimpleStrategy<INPUT, OUTPUT>>
    implements ISimpleStrategy<CompositionArguments<INPUT, OUTPUT, STRATEGY>, Boolean>
    {
        private final Boolean _noResultValue;

        /**
         * 
         */
        protected AbstractCompositionStrategy()
        {
            _noResultValue = null;
            reset();
        }

        /**
         * @param input
         * @return true if composing is finished based on the input, false
         *         otherwise
         * @throws Exception 
         */
        public abstract boolean compose(CompositionArguments<INPUT, OUTPUT, STRATEGY> input) throws Exception;

        @SuppressWarnings("boxing")
        public final Boolean perform(final CompositionArguments<INPUT, OUTPUT, STRATEGY> input) throws Exception
        {
            return compose(input);
        }

        /**
         * @return the result of composing.
         */
        public abstract RESULTOUTPUT getComposedResult();

        public Boolean getNoResult()
        {
            return _noResultValue;
        }

        /**
         * Clear any composed result and make strategy reusable as if it were
         * newly constructed.  This method is only called automatically at construction.
         * 
         * Owners should call to control the contents of their result list.
         */
        public abstract void reset();
    }

    /**
     * The default composition strategy.  This causes the first strategy that returns
     * a non-NoResult value to have it's value returned.
     *
     * @param <INPUT>
     * @param <OUTPUT>
     * @param <RESULTOUTPUT> 
     * @param <STRATEGY>
     */
    public final static class DefaultCompositionStrategy<INPUT, OUTPUT, RESULTOUTPUT, STRATEGY extends ISimpleStrategy<INPUT, OUTPUT>>
    extends AbstractCompositionStrategy<INPUT, OUTPUT, RESULTOUTPUT, STRATEGY>
    {
        private RESULTOUTPUT _result;

        /**
         * 
         */
        public DefaultCompositionStrategy()
        {
            super();
        }

        @Override
        public boolean compose(final CompositionArguments<INPUT, OUTPUT, STRATEGY> arg) throws Exception
        {
            final OUTPUT result = arg.getStrategy().perform(arg.getInput());
            if (result != arg.getStrategy().getNoResult())
            {
                _result = (RESULTOUTPUT)result;
                return true;
            }
            return false;
        }

        @Override
        public RESULTOUTPUT getComposedResult()
        {
            return _result;
        }

        @Override
        public void reset()
        {
            _result = null;
        }
    }

    /**
     * Bundle values needed by the composing strategies into a single arg object.
     *
     * @param <INPUT>
     * @param <OUTPUT>
     * @param <STRATEGY>
     */
    public final static class CompositionArguments<INPUT, OUTPUT, STRATEGY extends ISimpleStrategy<INPUT, OUTPUT>>
    {
        private final STRATEGY _strategy;
        private final INPUT _input;

        /**
         * @param strategy
         * @param input
         */
        public CompositionArguments(final STRATEGY strategy, final INPUT input)
        {
            _strategy = strategy;
            _input = input;
        }

        /**
         * @return the strategy
         */
        public STRATEGY getStrategy()
        {
            return _strategy;
        }

        /**
         * @return the input
         */
        public INPUT getInput()
        {
            return _input;
        }
    }
}
