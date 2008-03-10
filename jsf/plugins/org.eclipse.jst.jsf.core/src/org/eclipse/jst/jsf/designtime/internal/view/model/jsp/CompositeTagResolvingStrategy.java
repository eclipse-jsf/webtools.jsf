package org.eclipse.jst.jsf.designtime.internal.view.model.jsp;

import org.eclipse.jst.jsf.common.internal.policy.IIdentifiable;
import org.eclipse.jst.jsf.common.internal.policy.IIteratorPolicy;
import org.eclipse.jst.jsf.common.internal.strategy.PolicyBasedStrategyComposite;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;

/**
 * @author cbateman
 * 
 * @param <TLDELEMENT>
 */
public class CompositeTagResolvingStrategy<TLDELEMENT> extends
        PolicyBasedStrategyComposite<TLDELEMENT, ITagElement, String, ITagResolvingStrategy<TLDELEMENT, String>>
        implements IIdentifiable<String>, ITagResolvingStrategy<TLDELEMENT, String>
{
    private static final String ID = "org.eclipse.jst.jsf.designtime.CompositeTagResolvingStrategy";

    /**
     * @param policy 
     * 
     */
    public CompositeTagResolvingStrategy(final IIteratorPolicy<String> policy)
    {
        super(policy);
    }

    public String getId()
    {
        return ID;
    }

    public String getDisplayName()
    {
       return "Composite Tag Resolving Strategies";
    }

    @Override
    public ITagElement getNoResult()
    {
        return null;
    }

    public ITagElement getNotFoundIndicator()
    {
        return getNoResult();
    }

    public ITagElement resolve(TLDELEMENT element)
    {
            return perform(element);
    }
}
