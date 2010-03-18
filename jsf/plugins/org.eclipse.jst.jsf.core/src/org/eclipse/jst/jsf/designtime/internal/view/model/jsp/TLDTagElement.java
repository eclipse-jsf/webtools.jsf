/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.designtime.internal.view.model.jsp;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagAttribute;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagAttributeHandler;
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
    private final TLDElementData                  _tldData;

    /**
     * @param elementDecl
     * @param advisor 
     */
    public TLDTagElement(final TLDElementDeclaration elementDecl
            , final IAttributeAdvisor advisor)
    {
        _tldData = new DocumentElementData(elementDecl, advisor);
    }

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
        return String.format("Tag: Tag Handler: name=%s, uri=%s, tagHandlerClassName=%s\n" //$NON-NLS-1$
                , getName(), getUri(), getTagHandlerClassName())
                + constructAttributesString();
    }

    /**
     * @return a string representation of the attributes.
     */
    protected String constructAttributesString()
    {
        String attributes = ""; //$NON-NLS-1$

        for (final Iterator it = getAttributeHandlers().entrySet().iterator(); it.hasNext();)
        {
            final Map.Entry entry = (Entry) it.next();
            final String name = (String) entry.getKey();
            final ITagAttributeHandler handler = (ITagAttributeHandler) entry.getValue();

            attributes += String.format("\t\t\tAttribute: name=%s, customHandler=%s, propertyName=%s, isELAllowed=%b\n",  //$NON-NLS-1$
                    name, handler.getCustomHandler(), handler.getName(),
                    Boolean.valueOf(handler.isELAllowed()));
        }
        return attributes;
    }

    @Override
    public Map getAttributeHandlers()
    {
        return _tldData.getAttributeHandlers();
    }

    public Map<String, ? extends ITagAttribute> getAttributes()
    {
        return _tldData.getAttributes();
    }
    
    private static class DocumentElementData extends TLDElementData
    {
        /**
         * serialization id
         */
        private static final long serialVersionUID = -6160324802818766058L;
        private final TLDElementDeclaration _tldDoc;
        private final CMNodeNamedMapAdapter _adapter;
        private Map<String, ? extends ITagAttribute> _tldAttributes;

        public DocumentElementData(final TLDElementDeclaration tldDoc,
                final IAttributeAdvisor advisor)
        {
            _tldDoc = tldDoc;
            _adapter = new CMNodeNamedMapAdapter(tldDoc, advisor);
            _tldAttributes = advisor.getAttributes();
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
            return new SerializedTLDElementData(getName(), getTagHandlerClassName(), getUri()
                    , _adapter, _tldAttributes);
        }

        @SuppressWarnings("unused")
        private void readObject(java.io.ObjectInputStream in)
                        throws IOException, ClassNotFoundException
        {
            throw new UnsupportedOperationException("This object should be serialized; writeReplace"); //$NON-NLS-1$
        }

        @Override
        public Map<String, ? extends ITagAttributeHandler> getAttributeHandlers()
        {
            return _adapter;
        }

        @Override
        public Map<String, ? extends ITagAttribute> getAttributes()
        {
            return _tldAttributes;
        }
    }

    /**
     * @author cbateman
     *
     */
    private static class SerializedTLDElementData extends TLDElementData
    {
        /**
         * 
         */
        private static final long serialVersionUID = 4008084060638384114L;
        private final String        _name;
        private final String        _uri;
        private final String        _tagHandlerClassName;
        private final Map<String, ? extends ITagAttributeHandler>  _tagAttributes;
        private final Map<String, ? extends ITagAttribute> _actualTagAttributes;

        /**
         * @param name
         * @param tagHandlerClassName
         * @param uri
         * @param tagAttributes
         */
        private SerializedTLDElementData(final String name,
                final String tagHandlerClassName, final String uri,
                final Map<String, ? extends ITagAttributeHandler> tagAttributes,
                final Map<String, ? extends ITagAttribute> actualTagAttributes)
        {
            super();
            _name = name;
            _tagHandlerClassName = tagHandlerClassName;
            _uri = uri;
            // copy the map, because we don't if it is simply delta
            _tagAttributes = new HashMap(tagAttributes);
            _actualTagAttributes = new HashMap(actualTagAttributes);
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

        @Override
        public Map<String, ? extends ITagAttributeHandler> getAttributeHandlers()
        {
            return _tagAttributes;
        }

        @Override
        public Map<String, ? extends ITagAttribute> getAttributes()
        {
            return _actualTagAttributes;
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
        public abstract Map<String, ? extends ITagAttributeHandler>  getAttributeHandlers();
        public abstract Map<String, ? extends ITagAttribute>         getAttributes();
        
    }


}
