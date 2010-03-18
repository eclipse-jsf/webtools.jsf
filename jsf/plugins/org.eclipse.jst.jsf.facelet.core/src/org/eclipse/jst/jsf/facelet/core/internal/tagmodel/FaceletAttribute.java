package org.eclipse.jst.jsf.facelet.core.internal.tagmodel;

import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.AbstractTagAttribute;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute;

/**
 * Adapts a FaceletTaglibTagAttribute to the ITagAttribute interface.
 * 
 * @author cbateman
 *
 */
public class FaceletAttribute extends AbstractTagAttribute
{
    
    /**
     * 
     */
    private static final long serialVersionUID = 5909354642079229663L;

    private final FaceletTaglibTagAttribute _attr;

    /**
     * @param attr
     */
    public FaceletAttribute(final FaceletTaglibTagAttribute attr)
    {
        _attr = attr;
    }

    @Override
    public String getName()
    {
        return _attr.getName();
    }

    @Override
    public String getTargetNamespace()
    {
        return null;
    }

    @Override
    public String getDescription()
    {
        return _attr.getDefaultDescription("\n"); //$NON-NLS-1$
    }

    @Override
    public String getDisplayName()
    {
        return _attr.getDefaultDescription("\n"); //$NON-NLS-1$
    }

    @Override
    public boolean isRequired()
    {
        return _attr.isRequired();
    }
}
