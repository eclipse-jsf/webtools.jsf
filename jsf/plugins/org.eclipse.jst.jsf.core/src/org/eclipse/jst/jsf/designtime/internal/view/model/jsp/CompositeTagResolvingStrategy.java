package org.eclipse.jst.jsf.designtime.internal.view.model.jsp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;

/**
 * @author cbateman
 * 
 * @param <TLDELEMENT>
 */
public class CompositeTagResolvingStrategy<TLDELEMENT> extends
        AbstractTagResolvingStrategy<TLDELEMENT, String>
{
    private static final String ID = "org.eclipse.jst.jsf.designtime.CompositeTagResolvingStrategy";

    private final Map<String, ITagResolvingStrategy<TLDELEMENT, String>> _strategies;
    private IdentifierOrderedIteratorPolicy<String> _policy;

    /**
     * 
     */
    public CompositeTagResolvingStrategy()
    {
        _strategies = new HashMap<String, ITagResolvingStrategy<TLDELEMENT, String>>();
    }

    @Override
    public ITagElement resolve(final TLDELEMENT element)
    {
        // by default just go in the order added
        Iterator<String> strategyIterator = _strategies.keySet().iterator();
        
        if (_policy != null)
        {
            strategyIterator = _policy.getIterator(_strategies.keySet());
        }
        
        while (strategyIterator.hasNext())
        {
            final String key = strategyIterator.next();
            final ITagResolvingStrategy<TLDELEMENT, String> strategy =
                _strategies.get(key);
            
            final ITagElement tagElement = strategy.resolve(element);
            
            if (tagElement != strategy.getNotFoundIndicator())
            {
                return tagElement;
            }
        }
        
        return getNotFoundIndicator();
    }

    /**
     * Set the optional ordering policy. If set to non-null, then it will be
     * used to decide order in which the strategies are executed.
     * 
     * @param policy
     */
    public void setOrderingPolicy(final IdentifierOrderedIteratorPolicy<String> policy)
    {
        _policy = policy;
    }

    /**
     * Adds the strategy to the composite using the getId to judge uniqueness
     * @param strategy
     */
    public void addStrategy(final ITagResolvingStrategy<TLDELEMENT, String> strategy)
    {
        _strategies.put(strategy.getId(), strategy);
    }

    /**
     * Remove the strategy corresponding to id.
     * 
     * @param id
     * @return the item removed or null
     */
    public ITagResolvingStrategy<TLDELEMENT, String> removeStrategy(final String id)
    {
        return _strategies.remove(id);
    }
    
    @Override
    public String getId()
    {
        return ID;
    }

}
