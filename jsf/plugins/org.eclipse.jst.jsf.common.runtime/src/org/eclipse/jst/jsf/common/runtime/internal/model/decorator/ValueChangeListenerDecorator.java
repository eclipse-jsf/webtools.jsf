package org.eclipse.jst.jsf.common.runtime.internal.model.decorator;

import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.event.IValueChangeListenerInfo;

/**
 * ValueChangeListener component decorator.
 * 
 * @author cbateman
 *
 */
public class ValueChangeListenerDecorator extends ComponentDecorator implements
        IValueChangeListenerInfo 
{
    /**
     * serializable id
     */
    private static final long serialVersionUID = 9045831927898936727L;

    /**
     * @param decorates
     */
    public ValueChangeListenerDecorator(ComponentInfo decorates) {
        super(decorates);
    }    
}
