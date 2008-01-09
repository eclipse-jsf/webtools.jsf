package org.eclipse.jst.jsf.common.runtime.internal.model.behavioural;

public class ActionSourceInfo2 extends ActionSourceInfo implements
        IActionSource2Info 
{
    /**
     * serializable id
     */
    private static final long serialVersionUID = 5811194815559772378L;
    private final String        _actionExpression;
    
    public ActionSourceInfo2(final String action, final String actionListener,
            final boolean immediate, final String actionExpression) {
        super(action, actionListener, immediate);
        _actionExpression = actionExpression;
    }

    public String getActionExpression() {
        return _actionExpression;
    }
}
