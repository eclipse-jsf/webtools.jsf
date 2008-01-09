package org.eclipse.jst.jsf.common.runtime.internal.model.behavioural;

/**
 * Design-time analog for the ActionSource2 interface.
 * 
 * @author cbateman
 *
 */
public interface IActionSource2Info extends IActionSourceInfo 
{
    /**
     * TODO: this method is similar to getAction on ActionSource from
     * our perspective since the actual string rep of the EL is basically
     * the same...
     * 
     * @return an EL method expression that represents a call to 
     * an action method.
     */
    String getActionExpression();
}
