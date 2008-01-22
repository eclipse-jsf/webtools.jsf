package org.eclipse.jst.jsf.designtime.internal.view.model.jsp;

import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.TagElement;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDDocument;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDElementDeclaration;
import org.eclipse.wst.xml.core.internal.contentmodel.CMDocument;


/**
 * A tag element for a JSP tag (TLD-defined)
 * 
 * @author cbateman
 *
 */
public class TLDTagElement extends TagElement 
{
    private final TLDElementDeclaration _elementDecl;
    
    /** 
     * @param elementDecl
     */
    public TLDTagElement(TLDElementDeclaration elementDecl)
    {
        _elementDecl = elementDecl;
    }

    @Override
    public String getName() 
    {
        return _elementDecl.getElementName();
    }

    @Override
    public String getUri()
    {
        final CMDocument owner = _elementDecl.getOwnerDocument();
        
        if (owner instanceof TLDDocument)
        {
            return ((TLDDocument)owner).getUri();
        }
        return null;
    }

    @Override
    public String getTagHandlerClassName() {
        return _elementDecl.getTagclass();
    }
}
