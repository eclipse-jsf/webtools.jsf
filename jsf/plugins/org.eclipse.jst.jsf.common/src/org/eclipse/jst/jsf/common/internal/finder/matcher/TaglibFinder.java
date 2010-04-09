package org.eclipse.jst.jsf.common.internal.finder.matcher;

import org.eclipse.jst.jsf.common.internal.strategy.AbstractIdentifiableStrategy;


/**
 * A finder strategy for Facelet tag libraries.
 * @author cbateman
 *
 * @param <INPUT>
 * @param <OUTPUT>
 */
public abstract class TaglibFinder<INPUT, OUTPUT> extends AbstractIdentifiableStrategy<INPUT, OUTPUT, String>
{
    /**
     * @param id
     * @param displayName
     * @param noResultValue
     */
    public TaglibFinder(String id, String displayName,
            OUTPUT noResultValue)
    {
        super(id, displayName, noResultValue);
    }

    @Override
    public abstract OUTPUT perform(INPUT input) throws Exception;
}