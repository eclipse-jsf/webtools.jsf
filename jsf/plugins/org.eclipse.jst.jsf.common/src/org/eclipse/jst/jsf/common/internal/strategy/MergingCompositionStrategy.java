package org.eclipse.jst.jsf.common.internal.strategy;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jst.jsf.common.internal.strategy.StrategyComposite.AbstractCompositionStrategy;
import org.eclipse.jst.jsf.common.internal.strategy.StrategyComposite.CompositionArguments;

/**
 * A composition strategy that merges the result of all composed strategies. The
 * merge policy is left to concrete implementations. Three concrete impls are
 * provided: one that uses compose all (List) and one that uses compose unique
 * (Set) and a third that uses Map.
 * 
 * @author cbateman
 * 
 * @param <INPUT>
 * @param <MERGETYPE>
 * @param <STRATEGY>
 */
/**
 * @author cbateman
 * 
 * @param <INPUT>
 * @param <OUTPUT>
 * @param <MERGETYPE>
 * @param <STRATEGY>
 */
public abstract class MergingCompositionStrategy<INPUT, OUTPUT, MERGETYPE, STRATEGY extends ISimpleStrategy<INPUT, OUTPUT>>
extends AbstractCompositionStrategy<INPUT, OUTPUT, MERGETYPE, STRATEGY>
{
    /**
     * The result of the merge.
     */
    protected final MERGETYPE _composedResult;

    /**
     * @param composedResult
     * @param noResultValue
     */
    public MergingCompositionStrategy(final MERGETYPE composedResult,
            final MERGETYPE noResultValue)
    {
        super();
        _composedResult = composedResult;
    }

    @Override
    public boolean compose(final CompositionArguments<INPUT, OUTPUT, STRATEGY> args)
    throws Exception
    {
        final OUTPUT result = args.getStrategy().perform(args.getInput());
        if (result != args.getStrategy().getNoResult())
        {
            doCompose(result);
        }
        // never stop composing early
        return false;
    }

    /**
     * Sub-classes must implement to do the detail composition for their result
     * type.
     * 
     * @param result
     */
    protected abstract void doCompose(OUTPUT result);

    @Override
    public MERGETYPE getComposedResult()
    {
        return _composedResult;
    }

    @Override
    public abstract void reset();

    /**
     * An implementation that uses a List to implement the list policy. The
     * result is to add all results from all composed strategies to a single
     * list. Duplicates may occur.
     * 
     * @param <INPUT>
     * @param <OUTPUT>
     * @param <MERGETYPE>
     * @param <STRATEGY>
     *            `
     */
    public static class ListMergingCompositionStrategy<INPUT, OUTPUT, MERGETYPE extends List, STRATEGY extends ISimpleStrategy<INPUT, OUTPUT>>
    extends
    MergingCompositionStrategy<INPUT, OUTPUT, MERGETYPE, STRATEGY>
    {
        /**
         * @param composedResult
         * @param noResultValue
         */
        public ListMergingCompositionStrategy(final MERGETYPE composedResult,
                final MERGETYPE noResultValue)
        {
            super(composedResult, noResultValue);
        }

        @Override
        protected void doCompose(final OUTPUT result)
        {
            if (result instanceof Collection)
            {
                _composedResult.addAll((Collection) result);
            } else
            {
                _composedResult.add(result);
            }
        }

        @Override
        public void reset()
        {
            _composedResult.clear();
        }
    }

    /**
     * An implementation that uses a List to implement the list policy. The
     * result is to add all results from all composed strategies to a single
     * list. Duplicates will not occur.
     * 
     * @param <INPUT>
     * @param <OUTPUT>
     * @param <MERGETYPE>
     * @param <STRATEGY>
     */
    public static class SetMergingCompositionStrategy<INPUT, OUTPUT, MERGETYPE extends Set, STRATEGY extends ISimpleStrategy<INPUT, OUTPUT>>
    extends
    MergingCompositionStrategy<INPUT, OUTPUT, MERGETYPE, STRATEGY>
    {
        /**
         * @param composedResult
         * @param noResultValue
         */
        public SetMergingCompositionStrategy(final MERGETYPE composedResult,
                final MERGETYPE noResultValue)
        {
            super(composedResult, noResultValue);
        }

        @Override
        protected void doCompose(final OUTPUT result)
        {
            if (result instanceof Collection)
            {
                _composedResult.addAll((Collection) result);
            } else
            {
                _composedResult.add(result);
            }
        }

        @Override
        public void reset()
        {
            _composedResult.clear();
        }
    }

    /**
     * @author cbateman
     * 
     * @param <INPUT>
     * @param <OUTPUT>
     * @param <MERGETYPE>
     * @param <STRATEGY>
     */
    public abstract static class MapMergingCompositionStrategy<INPUT, OUTPUT, MERGETYPE extends Map, STRATEGY extends ISimpleStrategy<INPUT, OUTPUT>>
    extends
    MergingCompositionStrategy<INPUT, OUTPUT, MERGETYPE, STRATEGY>
    {
        /**
         * @param composedResult
         * @param noResultValue
         */
        public MapMergingCompositionStrategy(final MERGETYPE composedResult,
                final MERGETYPE noResultValue)
        {
            super(composedResult, noResultValue);
        }

        @Override
        protected void doCompose(final OUTPUT result)
        {
            if (result instanceof Map)
            {
                _composedResult.putAll((Map) result);
            } else
            {
                _composedResult.put(calculateKey(result), result);
            }
        }

        /**
         * @param result
         * @return the key to be used for result in any composed map.
         */
        protected abstract Object calculateKey(final OUTPUT result);

        @Override
        public void reset()
        {
            _composedResult.clear();
        }
    }
}
