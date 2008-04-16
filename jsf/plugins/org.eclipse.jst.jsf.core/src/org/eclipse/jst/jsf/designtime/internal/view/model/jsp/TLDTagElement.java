package org.eclipse.jst.jsf.designtime.internal.view.model.jsp;

import java.util.concurrent.atomic.AtomicBoolean;

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
    /**
     * 
     */
    private static final long serialVersionUID = -874272538404837105L;
    private transient TLDElementDeclaration _elementDecl;
    private final transient AtomicBoolean   _locked = new AtomicBoolean(false);

    /** 
     * @param elementDecl
     */
    public TLDTagElement(TLDElementDeclaration elementDecl)
    {
        _elementDecl = elementDecl;
    }

    /**
     * Change the element decl.  This should only be used during deserialization
     * to set the transient field.  Will throw an UnsupportedOperationException
     * if called after setLocked()
     * 
     * @param elementDecl
     */
    public final void setElementDecl(final TLDElementDeclaration elementDecl)
    {
        if (_locked.get())
        {
            throw new UnsupportedOperationException("object is locked");
        }
        _elementDecl = elementDecl;
    }

    /**
     * Signals that the class should become immutable and throw exceptions
     * if mutation is attempted on non-final data
     */
    public final void setLocked()
    {
        _locked.set(true);
    }

    /**
     * @return true if setLocked has been called.
     */
    public final boolean isLocked()
    {
        return _locked.get();
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
