package org.eclipse.jst.jsf.common.runtime.internal.model.decorator;

import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;

public class FacetDecorator extends ComponentDecorator 
{
    /**
     * 
     */
    private static final long serialVersionUID = 94806944978127012L;

    private final String _name;

    public FacetDecorator(final String name, final ComponentInfo component) {
        super(component);
        _name = name;
    }

    public final String getName() {
        return _name;
    }
}
