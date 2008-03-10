package org.eclipse.jst.jsf.designtime.internal.view.model.jsp;

import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;

/**
 * Implements the tag resolving strategy.
 * 
 * @author cbateman
 * 
 * @param <TLDELEMENT>
 * @param <IDTYPE>
 */
public abstract class AbstractTagResolvingStrategy<TLDELEMENT, IDTYPE>
        implements ITagResolvingStrategy<TLDELEMENT, IDTYPE>
{
    private final static ITagElement        NOT_FOUND_INDICATOR = null;
    
    public ITagElement getNotFoundIndicator()
    {
        return NOT_FOUND_INDICATOR;
    }

    public abstract ITagElement resolve(TLDELEMENT element);

    public abstract IDTYPE getId();

    /**
     * Must always be the same as resolve.
     */
    public final ITagElement perform(TLDELEMENT element)
    {
        return resolve(element);
    }

    /**
     * Must always be the same as getNotFoundIndicator.
     */
    public final ITagElement getNoResult()
    {
        return getNotFoundIndicator();
    }
}
