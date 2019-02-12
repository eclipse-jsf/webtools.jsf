/*******************************************************************************
 * Copyright (c) 2008, 2010 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facelet.core.internal.registry;

import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.ITagResolvingStrategy;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag;

/**
 * @author cbateman
 *
 */
public interface IFaceletTagResolvingStrategy extends
        ITagResolvingStrategy<IFaceletTagResolvingStrategy.TLDWrapper, String>
{

    /**
     * Wraps data for Facelet tag information to used by the resolving strategy
     * 
     * @author cbateman
     *
     */
    public static class TLDWrapper
    {
        private final String  _uri;
        private final FaceletTaglibTag _tagDefn;

        /**
         * @param tagDefn
         * @param uri
         */
        public TLDWrapper(FaceletTaglibTag tagDefn, String uri)
        {
            super();
            _tagDefn = tagDefn;
            _uri = uri;
        }

        /**
         * @return the uri for the tld namespace
         */
        public final String getUri()
        {
            return _uri;
        }

        /**
         * @return the tag definition information
         */
        public final FaceletTaglibTag getTagDefn()
        {
            return _tagDefn;
        }

    }
}
