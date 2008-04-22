package org.eclipse.jst.jsf.common.runtime.internal.model.decorator;

import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;

/**
 * Design time ActionListener decorator
 * 
 * @author cbateman
 *
 */
public class ActionListenerDecorator extends ComponentDecorator {

    /**
     * 
     */
    private static final long serialVersionUID = 3806862878381550874L;

    /**
     * @param decorates
     */
    public ActionListenerDecorator(final ComponentInfo decorates) {
        super(decorates);
    }

}
