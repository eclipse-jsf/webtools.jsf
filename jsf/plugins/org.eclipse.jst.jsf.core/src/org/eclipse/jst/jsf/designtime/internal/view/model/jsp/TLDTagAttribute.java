package org.eclipse.jst.jsf.designtime.internal.view.model.jsp;

import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.AbstractTagAttribute;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDAttributeDeclaration;

/**
 * Adapts a TLDAttributeDeclaration to the ITagAttribute interface.
 * 
 * @author cbateman
 *
 */
public class TLDTagAttribute extends AbstractTagAttribute
{

    /**
     * 
     */
    private static final long serialVersionUID = 4327701042556836452L;

    private final TLDAttributeDeclaration _decl;

    /**
     * @param decl 
     */
    public TLDTagAttribute(final TLDAttributeDeclaration decl)
    {
        _decl = decl;
    }

    @Override
    public String getName()
    {
        return _decl.getAttrName();
    }

    @Override
    public String getTargetNamespace()
    {
        return null;
    }

    @Override
    public String getDescription()
    {
        return _decl.getDescription();
    }

    @Override
    public String getDisplayName()
    {
        return _decl.getAttrName();
    }

    @Override
    public boolean isRequired()
    {
        return _decl.isRequired();
    }

}
