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
/**
 * 
 */
package org.eclipse.jst.jsf.core.internal.region;

import org.eclipse.jst.jsf.common.dom.AttrDOMAdapter;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegion;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMAttr;
import org.w3c.dom.Node;

/**
 * A AttrDOMAdapter that adapts from a ITextRegions. Instances of class can only
 * be obtained from and are always relative to, an owning Region2ElementAdapter
 * 
 * @author cbateman
 * 
 */
public class Region2AttrAdapter extends AttrDOMAdapter
{
    private final Node _attr;

    /**
     * @param owner
     * @param attr
     */
    Region2AttrAdapter(final Region2ElementAdapter owner, final Node attr)
    {
        super(owner);
        _attr = attr;
    }

    @Override
    public Region2ElementAdapter getOwningElement()
    {
        return (Region2ElementAdapter) super.getOwningElement();
    }

    @Override
    public String getLocalName()
    {
        return _attr.getLocalName();
    }

    @Override
    public String getNodeName()
    {
        return _attr.getNodeName();
    }

    @Override
    public String getPrefix()
    {
        return _attr.getPrefix();
    }

    @Override
    public String getValue()
    {
        return _attr.getNodeValue();
    }

    /**
     * @return the structured document context
     */
    public IStructuredDocumentContext getDocumentContext()
    {
        return IStructuredDocumentContextFactory.INSTANCE.getContext
            (getOwningElement().getDocumentContext().getStructuredDocument(),
                getStartOffset());
    }
    
    /**
     * @return the absolute document offset where the attribute starts.
     */
    public int getStartOffset()
    {
        return getOwningElement().getTextRegion().getStartOffset()+getAttributeNameRegion().getStart();
    }

    /**
     * Offsets in the region will be relative to the parent element, not the
     * document
     * 
     * @return the ITextRegion for the attribute name of the attribute adapted
     *         by attrAdapter
     * @throws IllegalArgumentException
     *             if attrAdapter.getOwningElement != this
     */
    // see https://bugs.eclipse.org/bugs/show_bug.cgi?id=217523 for deprecation
    @SuppressWarnings("deprecation")
    public ITextRegion getAttributeNameRegion()
    {

        return getDOMAttr(_attr).getNameRegion();
    }

    /**
     * Offsets in the region will be relative to the parent element, not the
     * document
     * 
     * @return the ITextRegion for the attribute value of the attribute adapted
     *         by attrAdapter
     * @throws IllegalArgumentException
     *             if attrAdapter.getOwningElement != this
     */
    @SuppressWarnings("deprecation")
    // see https://bugs.eclipse.org/bugs/show_bug.cgi?id=217523 for deprecation
    public ITextRegion getAttributeValueRegion()
    {
        return getDOMAttr(_attr).getValueRegion();
    }

    private IDOMAttr getDOMAttr(final Node attrAsNode)
    {
        assert attrAsNode instanceof IDOMAttr;

        return ((IDOMAttr) attrAsNode);
    }

    @Override
    public String toString()
    {
        return String.format("Region2AttrAdapter: attr=%s, owningElement=%s" //$NON-NLS-1$
                , _attr.getNodeName(), getOwningElement().getNodeName());
        
    }
    
    
}