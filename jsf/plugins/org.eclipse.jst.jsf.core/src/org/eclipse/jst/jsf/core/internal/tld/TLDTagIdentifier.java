package org.eclipse.jst.jsf.core.internal.tld;

import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDDocument;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDElementDeclaration;

final class TLDTagIdentifier extends TagIdentifier
{
    private final TLDElementDeclaration _tldDecl;

    public TLDTagIdentifier(final TLDElementDeclaration tldDecl)
    {
        _tldDecl = tldDecl;
    }
    
    @Override
    public String getTagName()
    {
        return _tldDecl.getElementName();
    }

    @Override
    public String getUri()
    {
        return  ((TLDDocument)_tldDecl.getOwnerDocument()).getUri();
    }

    @Override
    public boolean isJSPTag()
    {
        return true;
    }
}
