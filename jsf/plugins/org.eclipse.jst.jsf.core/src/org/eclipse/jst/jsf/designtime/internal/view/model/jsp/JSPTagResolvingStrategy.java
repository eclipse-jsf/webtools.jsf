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

import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDElementDeclaration;

/**
 * A strategy for resolving JSP TLDs.
 * 
 * @author cbateman
 *
 */
public abstract class JSPTagResolvingStrategy extends
        AbstractTagResolvingStrategy<TLDElementDeclaration, String>
{
    @Override
    public abstract ITagElement resolve(TLDElementDeclaration element);

    public abstract String getId();
    
    public abstract String getDisplayName();
    
    /**
     * A descriptor for JSP tag resolving strategies
     * 
     * @author cbateman
     *
     */
    public final static class StrategyDescriptor
    {
        private final String _id;
        private final String _displayName;

        /**
         * @param id
         * @param displayName
         */
        public StrategyDescriptor(final String id, final String displayName)
        {
            _id = id;
            _displayName = displayName;
        }
        
        /**
         * @return the id of the strategy
         */
        public String getId()
        {
            return _id;
        }
        /**
         * @return the human-readable displayable name of the strategy
         */
        public String getDisplayName()
        {
            return _displayName;
        }
    }
}
