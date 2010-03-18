/*******************************************************************************
 * Copyright (c) 2008 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.jsf.designtime.internal.view.model.jsp;

import java.util.Collections;
import java.util.Map;

import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagAttribute;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagAttributeHandler;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.TagAttributeHandler;

/**
 * @author cbateman
 *
 */
public interface IAttributeAdvisor
{

    /**
     * @param name
     * @return a tag attribute handler for the name.
     * @throws UnknownAttributeException if name does not match a known attribute.
     */
    ITagAttributeHandler createAttributeHandler(String name) throws UnknownAttributeException;

    /**
     * @return a map of attributes indexed by local name
     */
    Map<String, ? extends ITagAttribute> getAttributes();

    /**
     * @author cbateman
     *
     */
    public static class NullAttributeAdvisor implements IAttributeAdvisor
    {
        public ITagAttributeHandler createAttributeHandler(String name)
                throws UnknownAttributeException
        {
            return new TagAttributeHandler(null, name, false);
        }

        public Map<String, ? extends ITagAttribute> getAttributes()
        {
            return Collections.emptyMap();
        }
    }

    /**
     * Indicates an unknown attribute was requested or encountered
     * @author cbateman
     *
     */
    public static class UnknownAttributeException extends Exception
    {
        /**
         * 
         */
        private static final long serialVersionUID = -988838913707929315L;

        /**
         * 
         */
        public UnknownAttributeException()
        {
            super();
        }

        /**
         * @param message
         * @param cause
         */
        public UnknownAttributeException(String message, Throwable cause)
        {
            super(message, cause);
        }

        /**
         * @param message
         */
        public UnknownAttributeException(String message)
        {
            super(message);
        }

        /**
         * @param cause
         */
        public UnknownAttributeException(Throwable cause)
        {
            super(cause);
        }
    }
}
