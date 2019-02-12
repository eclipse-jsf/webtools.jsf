/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facelet.core.internal.cm;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.eclipse.wst.xml.core.internal.contentmodel.CMNamedNodeMap;
import org.eclipse.wst.xml.core.internal.contentmodel.CMNode;

/**
 * Represents information about Facelets brought from external sources such as
 * the JSP tag library.
 * 
 * @author cbateman
 * 
 */
public abstract class ExternalTagInfo extends TagInfo
{
    /**
     * Single instance of the null external tag info
     */
    public static TagInfo  NULL_INSTANCE = new NullExternalTagInfo();

    @Override
    public abstract CMNamedNodeMap getAttributes(final String tagName);

    @Override
    public abstract Object getTagProperty(final String tagName, final String key);

    /**
     * @author cbateman
     *
     */
    public static class NullExternalTagInfo extends ExternalTagInfo
    {
        private NullExternalTagInfo()
        {
            // no external instantitation
        }
        
        @Override
        public CMNamedNodeMap getAttributes(final String tagName)
        {
            return new NullCMNamedNodeMap();
        }

        @Override
        public Object getTagProperty(final String tagName, final String key)
        {
            // no data so always null
            return null;
        }

        private static class NullCMNamedNodeMap implements CMNamedNodeMap
        {
            public int getLength()
            {
                return 0;
            }

            public CMNode getNamedItem(final String name)
            {
                return null;
            }

            public CMNode item(final int index)
            {
                return null;
            }

            public Iterator iterator()
            {
                return new NullIterator();
            }
        }

        private static class NullIterator implements Iterator
        {
            public boolean hasNext()
            {
                return false;
            }

            public Object next()
            {
                throw new NoSuchElementException();
            }

            public void remove()
            {
                throw new UnsupportedOperationException(
                        "can not remove regions via iterator"); //$NON-NLS-1$
            }
        }
    }

}