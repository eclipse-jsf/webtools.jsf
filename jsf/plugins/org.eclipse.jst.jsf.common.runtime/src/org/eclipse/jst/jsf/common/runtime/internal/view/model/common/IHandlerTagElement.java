/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.runtime.internal.view.model.common;

import java.io.Serializable;

import org.eclipse.jst.jsf.common.runtime.internal.model.types.TypeInfo;

/**
 * Generic tag handler that has effect when processed by the JSF ViewHandler.
 * 
 * @author cbateman
 *
 */
public interface IHandlerTagElement extends IJSFTagElement
{
    /**
     * Enumerates known types of built-in tag handlers.
     * 
     * @author cbateman
     *
     */
    static class TagHandlerType extends TypeInfo implements Serializable
    {
        /**
         * serializable id
         */
        private static final long serialVersionUID = 5062853948108253861L;
        
        private final static int HANDLER_TYPE_FACET = 0;
        private final static int HANDLER_TYPE_ACTIONLISTENER = 1;
        private final static int HANDLER_TYPE_VALUECHANGELISTENER = 2;
        private final static int HANDLER_TYPE_ATTRIBUTE = 3;

        private final static String[]  strValues =
            {"facet", "actionListener", "valueChangeListener"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

        private final int _intValue;
        
        public TagHandlerType(int intValue)
        {
            _intValue = intValue;
        }

        public String toString()
        {
            return strValues[_intValue];
        }

        protected final int intValue()
        {
            return _intValue;
        }
       
        public final static TagHandlerType FACET = 
            new TagHandlerType(HANDLER_TYPE_FACET);
        public final static TagHandlerType ACTIONLISTENER = 
            new TagHandlerType(HANDLER_TYPE_ACTIONLISTENER);
        public final static TagHandlerType VALUECHANGELISTENER = 
            new TagHandlerType(HANDLER_TYPE_VALUECHANGELISTENER);
        public final static TagHandlerType ATTRIBUTE = 
            new TagHandlerType(HANDLER_TYPE_ATTRIBUTE);
        
    }
    
    /**
     * @return the tag handler type as defined by standard known types of 
     * tags that do not (necessarily) translate into standard objects at 
     * runtime but may modify the view in some way.
     */
    TagHandlerType getTagHandlerType();
}
