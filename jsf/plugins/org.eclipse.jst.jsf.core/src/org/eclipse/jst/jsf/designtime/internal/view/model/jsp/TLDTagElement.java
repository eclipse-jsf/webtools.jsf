package org.eclipse.jst.jsf.designtime.internal.view.model.jsp;

import java.io.Serializable;

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
    private TLDElementData                  _tldData;
//    private final transient AtomicBoolean   _locked;

    /** 
     * @param elementDecl
     */
    public TLDTagElement(TLDElementDeclaration elementDecl)
    {
        _tldData = new DocumentElementData(elementDecl);
//        _locked = new AtomicBoolean(false);
    }
//    /**
//     * DO NOT use directly.
//     * 
//     * Used to initialize transient fields during deserialization.
//     */
//    protected TLDTagElement()
//    {
//        _locked = new AtomicBoolean(false);
//    }
//    /**
//     * Change the element decl.  This should only be used during deserialization
//     * to set the transient field.  Will throw an UnsupportedOperationException
//     * if called after setLocked()
//     * 
//     * @param elementDecl
//     */
//    public final void setElementDecl(final TLDElementDeclaration elementDecl)
//    {
//        if (_locked.get())
//        {
//            throw new UnsupportedOperationException("object is locked");
//        }
//        _tldData = new DocumentElementData(elementDecl);
//    }

    /**
     * Signals that the class should become immutable and throw exceptions
     * if mutation is attempted on non-final data
     */
//    public final void setLocked()
//    {
//        _locked.set(true);
//    }

    /**
     * @return true if setLocked has been called.
     */
//    public final boolean isLocked()
//    {
//        return _locked.get();
//    }

    @Override
    public String getName() 
    {
        return _tldData.getName();
    }

    @Override
    public String getUri()
    {
        return _tldData.getUri();
    }

    @Override
    public String getTagHandlerClassName() {
        return _tldData.getTagHandlerClassName();
    }
    
    @Override
    public String toString()
    {
        return String.format("Tag: Tag Handler: name=%s, uri=%s, tagHandlerClassName=%s"
                , getName(), getUri(), getTagHandlerClassName());
    }

    private static class DocumentElementData extends TLDElementData
    {
        /**
         * 
         */
        private static final long serialVersionUID = -6160324802818766058L;
        private final TLDElementDeclaration _tldDoc;

        public DocumentElementData(final TLDElementDeclaration tldDoc)
        {
            _tldDoc = tldDoc;
        }

        @Override
        public String getName()
        {
            return _tldDoc.getElementName();
        }

        @Override
        public String getTagHandlerClassName()
        {
            return _tldDoc.getTagclass();
        }

        @Override
        public String getUri()
        {
            final CMDocument owner = _tldDoc.getOwnerDocument();
            
            if (owner instanceof TLDDocument)
            {
                return ((TLDDocument)owner).getUri();
            }
            return null;
        }
        
        private Object writeReplace()
        {
            return new SerializedTLDElementData(getName(), getTagHandlerClassName(), getUri());
        }
    }
    
    private static class SerializedTLDElementData extends TLDElementData
    {
        /**
         * 
         */
        private static final long serialVersionUID = 4008084060638384114L;
        private final String        _name;
        private final String        _uri;
        private final String        _tagHandlerClassName;

        
        /**
         * @param name
         * @param tagHandlerClassName
         * @param uri
         */
        private SerializedTLDElementData(String name,
                String tagHandlerClassName, String uri)
        {
            super();
            _name = name;
            _tagHandlerClassName = tagHandlerClassName;
            _uri = uri;
        }

        @Override
        public String getName()
        {
            return _name;
        }

        @Override
        public String getTagHandlerClassName()
        {
            return _tagHandlerClassName;
        }

        @Override
        public String getUri()
        {
            return _uri;
        }
        
    }
    private static abstract class TLDElementData implements Serializable
    {
        /**
         * 
         */
        private static final long serialVersionUID = -2494388738109839064L;
        public abstract String getTagHandlerClassName();
        public abstract String getName();
        public abstract String getUri();
    }
}
