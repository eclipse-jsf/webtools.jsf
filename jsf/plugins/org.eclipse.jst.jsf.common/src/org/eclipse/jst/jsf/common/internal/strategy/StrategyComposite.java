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
 * @param <IDTYPE> 
 * @param <STRATEGYTYPE>
 */
public abstract class StrategyComposite<INPUT, OUTPUT, IDTYPE, STRATEGYTYPE extends IIdentifiableStrategy<INPUT, OUTPUT, IDTYPE>>
        implements ISimpleStrategy<INPUT, OUTPUT>
{
    public final OUTPUT perform(final INPUT input)
    {
        final Iterator<STRATEGYTYPE> sIt = getIterator();

        OUTPUT result = getNoResult();
        boolean resultFound = false;

        EXECUTE_LOOP: while (sIt.hasNext())
        {
            final STRATEGYTYPE strategy = sIt.next();
            try
            {
                result = strategy.perform(input);

                if (result != strategy.getNoResult())
                {
                    resultFound = true;
                    break EXECUTE_LOOP;
                }
            }
            catch (Exception e)
            {
                JSFCommonPlugin.log(e);
            }
        }

        if (resultFound)
        {
            return result;
        }
        return getNoResult();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jst.jsf.common.internal.strategy.ISimpleStrategy#getNoResult()
     */
    public abstract OUTPUT getNoResult();

    /**
     * @return an iterator that will return the next strategy to be executed
     */
    public abstract Iterator<STRATEGYTYPE> getIterator();
}
