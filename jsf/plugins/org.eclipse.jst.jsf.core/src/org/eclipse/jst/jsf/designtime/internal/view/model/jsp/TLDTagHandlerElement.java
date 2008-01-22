package org.eclipse.jst.jsf.designtime.internal.view.model.jsp;

import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IHandlerTagElement;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDElementDeclaration;

/**
 * A TLD-defined tag (i.e. JSP) that maps to a known tag handler.
 * 
 * @author cbateman
 *
 */
public class TLDTagHandlerElement extends TLDJSFTagElement implements
        IHandlerTagElement
{
    private TagHandlerType      _tagHandlerType;
    
    /**
     * @param elementDecl
     * @param tagHandlerType
     */
    public TLDTagHandlerElement(TLDElementDeclaration elementDecl, TagHandlerType tagHandlerType)
    {
        super(elementDecl);
        _tagHandlerType = tagHandlerType;
    }

    public TagHandlerType getTagHandlerType()
    {
        return _tagHandlerType;
    }

    @Override
    public TagType getType()
    {
        return TagType.HANDLER;
    }
}
