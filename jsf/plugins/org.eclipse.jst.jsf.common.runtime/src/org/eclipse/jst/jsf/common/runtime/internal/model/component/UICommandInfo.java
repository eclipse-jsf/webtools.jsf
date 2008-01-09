package org.eclipse.jst.jsf.common.runtime.internal.model.component;

import java.util.Iterator;
import java.util.List;

import org.eclipse.jst.jsf.common.runtime.internal.model.behavioural.IActionSource2Info;
import org.eclipse.jst.jsf.common.runtime.internal.model.behavioural.IActionSourceInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ActionListenerDecorator;

/**
 * An design time analog of the standard UICommand.
 * @author cbateman
 *
 */
public class UICommandInfo extends ComponentInfo implements IActionSource2Info {
    private final String _actionExpression;
    private final String _actionListener;
    private final boolean _isImmediate;

    /**
     * serialization id
     */
    private static final long serialVersionUID = -9025172832535840949L;

    /**
     * @param id
     * @param parent
     * @param componentTypeInfo
     * @param isRendered
     * @param actionSourceInfo
     */
    protected UICommandInfo(final String id, final ComponentInfo parent,
            final ComponentTypeInfo componentTypeInfo,
            final boolean isRendered, final IActionSourceInfo actionSourceInfo) {
        super(id, parent, componentTypeInfo, isRendered);

        if (actionSourceInfo == null) {
            _actionExpression = null;
            _actionListener = null;
            _isImmediate = false;
        } else {
            // TODO: having action and actionExpression will come back to
            // to bite us.
            _actionExpression = actionSourceInfo.getAction();
            _actionListener = actionSourceInfo.getActionListener();
            _isImmediate = actionSourceInfo.isImmediate();

            for (final Iterator it = actionSourceInfo.getActionListeners()
                    .iterator(); it.hasNext();) {
                final ActionListenerDecorator actionListener = 
                    (ActionListenerDecorator) it.next();
                addActionListener(actionListener);
            }
        }
    }

    public String getActionExpression() {
        return _actionExpression;
    }

    public final void addActionListener(final ActionListenerDecorator actionListener) {
        addDecorator(actionListener, ComponentFactory.ACTION_LISTENER);
    }

    public final String getAction() {
        return _actionExpression;
    }

    public final String getActionListener() {
        return _actionListener;
    }

    public final List getActionListeners() {
        return getDecorators(ComponentFactory.ACTION_LISTENER);
    }

    public final boolean isImmediate() {
        return _isImmediate;
    }
}
