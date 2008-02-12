package org.eclipse.jst.jsf.designtime.internal.view.model.jsp;

import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IComponentTagElement;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDElementDeclaration;



/**
 * A TLD-defined tag (i.e. JSP) that maps one-to-one with a JSF UIComponent
 * @author cbateman
 *
 */
public class TLDComponentTagElement extends TLDJSFTagElement implements
        IComponentTagElement 
{
    private final ComponentTypeInfo     _componentTypeInfo;
    
    /**
     * @param elementDecl
     * @param componentTypeInfo
     */
    public TLDComponentTagElement(final TLDElementDeclaration elementDecl, final ComponentTypeInfo componentTypeInfo) 
    {
        super(elementDecl);
        _componentTypeInfo = componentTypeInfo;
    }

    public ComponentTypeInfo getComponent() 
    {
        return _componentTypeInfo;
    }

    @Override
    public TagType getType()
    {
        return TagType.COMPONENT;
    }

    @Override
    public String toString()
    {
        return _componentTypeInfo.toString();
    }
}
