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
package org.eclipse.jst.jsf.designtime.tests.views.model.jsp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.ITagResolvingStrategy;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.CMDocumentFactoryTLD;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDDocument;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDElementDeclaration;
import org.eclipse.jst.jsp.core.taglib.ITaglibRecord;
import org.eclipse.wst.xml.core.internal.contentmodel.CMNamedNodeMap;
import org.eclipse.wst.xml.core.internal.contentmodel.CMNode;

/**
 * Local test utility functions.
 * 
 * @author cbateman
 * 
 */
final class TestUtil extends Assert
{
    public static TLDDocument getDocument(final ITaglibRecord record)
    {
        final CMDocumentFactoryTLD factory = new CMDocumentFactoryTLD();
        final TLDDocument doc = (TLDDocument) factory.createCMDocument(record);
        return doc;
    }
    
    public static TLDElementDeclaration getTag(final ITaglibRecord record,
            final String name)
    {
        final TLDDocument doc = getDocument(record);
        assertNotNull(doc);
        final CMNode cmNode = doc.getElements().getNamedItem(name);
        assertTrue(cmNode instanceof TLDElementDeclaration);
        return (TLDElementDeclaration) cmNode;
    }

    public static List<TLDElementDeclaration> getTags(final ITaglibRecord record)
    {
        final TLDDocument doc = getDocument(record);
        assertNotNull(doc);
        final CMNamedNodeMap nodes = doc.getElements();

        final List<TLDElementDeclaration> tags = new ArrayList<TLDElementDeclaration>();
        for (final Iterator<?> it = nodes.iterator(); it.hasNext();)
        {
            final CMNode cmNode = (CMNode) it.next();
            assertTrue(cmNode instanceof TLDElementDeclaration);
            tags.add((TLDElementDeclaration) cmNode);
        }
        return tags;
    }

    public static Map<String, ITagElement> constructTagElements(Collection<? extends ITagElement> elements)
    {
        Map<String, ITagElement> map = new HashMap<String, ITagElement>();
        
        for (ITagElement element : elements)
        {
            map.put(element.getName(), element);
        }
        
        return map;
    }
    
    public static Map<String, ITagElement> constructTagElements(
            final List<TLDElementDeclaration> tlds,
            final ITagResolvingStrategy<TLDElementDeclaration, String> strategy)
    {
        final Map<String, ITagElement> tagElements =
            new HashMap<String, ITagElement>();
        for (final TLDElementDeclaration tld : tlds)
        {
            final ITagElement tagElement = strategy.resolve(tld);
            if (tagElement != null)
            {
                assertNotNull(tagElement.getName());
                tagElements.put(tagElement.getName(), tagElement);
            }
        }
        
        return tagElements;
    }
}
