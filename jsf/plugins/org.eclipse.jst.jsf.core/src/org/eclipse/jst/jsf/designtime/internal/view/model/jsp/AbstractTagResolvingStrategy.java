package org.eclipse.jst.jsf.designtime.internal.view.model.jsp;

import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;

/**
 * Implements the tag resolving strategy.
 * EWJ 
 * @author cbateman
 *
 * @param <TLDELEMENT>
 * @param <IDTYPE> 
 */
public abstract class AbstractTagResolvingStrategy<TLDELEMENT, IDTYPE> implements
        ITagResolvingStrategy<TLDELEMENT, IDTYPE>
{

    public ITagElement getNotFoundIndicator()
    {
        return null;
    }

    public abstract ITagElement resolve(TLDELEMENT element);

    public abstract IDTYPE getId();   
}
