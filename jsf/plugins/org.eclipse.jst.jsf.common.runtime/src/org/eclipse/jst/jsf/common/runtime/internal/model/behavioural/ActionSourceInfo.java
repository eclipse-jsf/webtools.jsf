package org.eclipse.jst.jsf.common.runtime.internal.model.behavioural;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jst.jsf.common.runtime.internal.model.IDesigntimeAdapter;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentFactory;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ActionListenerDecorator;

/**
 * Implementation of the ActionSourceInfo.
 * 
 * @author cbateman
 *
 */
public class ActionSourceInfo implements IActionSourceInfo, IDesigntimeAdapter
{
    /**
     * serializable id
     */
    private static final long serialVersionUID = 6531166406473466685L;
    private static final String[]                INTERFACE = new String[]{ComponentFactory.INTERFACE_ACTIONSOURCE};
    private final String                                    _action;
    private final String                                    _actionListener;
    private final boolean                                   _immediate;
    private List/*<ActionListenerDecorator>*/               _actionListeners;
    
    /**
     * @param action
     * @param actionListener
     * @param immediate
     */
    public ActionSourceInfo(final String action, final String actionListener,
            final boolean immediate) 
    {
        super();
        _action = action;
        _actionListener = actionListener;
        _immediate = immediate;
    }

    public void addActionListener(final ActionListenerDecorator actionListener) {
        if (_actionListeners == null)
        {
            _actionListeners = new ArrayList(2);
        }
        _actionListeners.add(actionListener);
    }

    public String getAction() {
        return _action;
    }

    public String getActionListener() {
        return _actionListener;
    }

    public List getActionListeners() {
        if (_actionListeners == null)
        {
            return Collections.EMPTY_LIST;
        }
        return Collections.unmodifiableList(_actionListeners);
    }

    public boolean isImmediate() {
        return _immediate;
    }

    public String[] getInterfaces()
    {
        return INTERFACE;
    }
}
