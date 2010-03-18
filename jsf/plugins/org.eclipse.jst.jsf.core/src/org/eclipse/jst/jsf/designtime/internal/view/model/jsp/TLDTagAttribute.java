package org.eclipse.jst.jsf.designtime.internal.view.model.jsp;

import java.io.IOException;
import java.io.Serializable;

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

    private final TLDAttributeData _tldData;

    /**
     * @param decl 
     */
    public TLDTagAttribute(final TLDAttributeDeclaration decl)
    {
        _tldData = new DocumentAttributeData(decl);
    }

    @Override
    public String getName()
    {
        return _tldData.getName();
    }

    @Override
    public String getDisplayName()
    {
        return _tldData.getDisplayName();
    }

    @Override
    public String getDescription() {
        return _tldData.getDescription();
    }

    @Override
    public String getTargetNamespace()
    {
        return _tldData.getTargetNamespace();
    }

    public boolean isRequired()
    {
        return _tldData.isRequired();
    }

    /**
     * Diagnostic only.  For testing only. Should never be exposed on ITagAttribute.
     * 
     * @return true if this instance wraps a SerializedTLDAttributeData (the
     * instance was created by readObject).  False if it is wrapping a 
     * TLDAttributeDeclaration.
     */
    public boolean hasBeenDeserialized()
    {
       return _tldData instanceof SerializedTLDAttributeData; 
    }
    @Override
    public String toString()
    {
        return String.format("Attribute: name=%s, displayName=%s, description=%s\n" //$NON-NLS-1$
                , getName(), getDisplayName(), getDescription());
    }

    private static class DocumentAttributeData extends TLDAttributeData
    {
        /**
         * 
         */
        private static final long serialVersionUID = -5974753636507938515L;
        private final TLDAttributeDeclaration       _decl;
        
        
        public DocumentAttributeData(TLDAttributeDeclaration decl)
        {
            super();
            _decl = decl;
        }

        private Object writeReplace()
        {
            return new SerializedTLDAttributeData(getName(), getDisplayName(), getDescription()
                    , getTargetNamespace(), isRequired());
        }

        @SuppressWarnings("unused")
        private void readObject(java.io.ObjectInputStream in)
                        throws IOException, ClassNotFoundException
        {
            throw new UnsupportedOperationException("This object should be serialized; writeReplace"); //$NON-NLS-1$
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

    /**
     * @author cbateman
     *
     */
    private static class SerializedTLDAttributeData extends TLDAttributeData
    {
        /**
         * 
         */
        private static final long serialVersionUID = -1094006883222087189L;

        private final String _name;
        private final String _displayName;
        private final String _description;
        private final String _targetNamespace;
        private final boolean _isRequired;
        
        
        
        public SerializedTLDAttributeData(String name, String displayName,
                String description, String targetNamespace, boolean isRequired)
        {
            super();
            _name = name;
            _displayName = displayName;
            _description = description;
            _targetNamespace = targetNamespace;
            _isRequired = isRequired;
        }

        @Override
        public String getName()
        {
            return _name;
        }

        @Override
        public String getDisplayName()
        {
            return _displayName;
        }

        @Override
        public String getDescription()
        {
            return _description;
        }

        @Override
        public String getTargetNamespace()
        {
            return _targetNamespace;
        }

        @Override
        public boolean isRequired()
        {
            return _isRequired;
        }

    }

    private static abstract class TLDAttributeData implements Serializable
    {
        /**
         * 
         */
        private static final long serialVersionUID = 8376571212994363562L;
        public abstract String getName();
        public abstract String getDisplayName();
        public abstract String getDescription();
        public abstract String getTargetNamespace();
        public abstract boolean isRequired();
    }
}
