package org.eclipse.jst.jsf.common.runtime.internal.model.behavioural;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ActionListenerDecorator;

public class ActionSourceInfo implements IActionSourceInfo 
{
    /**
     * serializable id
     */
    private static final long serialVersionUID = 6531166406473466685L;

    private final String                                    _action;
    private final String                                    _actionListener;
    private final boolean                                   _immediate;
    private List/*<ActionListenerDecorator>*/               _actionListeners;
    
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
}
