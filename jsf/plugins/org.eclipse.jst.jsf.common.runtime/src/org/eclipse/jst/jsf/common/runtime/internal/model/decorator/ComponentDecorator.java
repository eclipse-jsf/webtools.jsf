package org.eclipse.jst.jsf.common.runtime.internal.model.decorator;

import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;

/**
 * @author cbateman
 *
 */
public abstract class ComponentDecorator extends Decorator 
{
    /**
     * 
     */
    private static final long serialVersionUID = 4378142984217256364L;
    
    private final ComponentInfo     _decorates;
    
    /**
     * @param decorates
     */
    public ComponentDecorator(final ComponentInfo decorates)
    {
        _decorates = decorates;
    }

    /**
     * @return the component that this decorator decorates.
     */
    public final ComponentInfo getDecorates() {
        return _decorates;
    }
}