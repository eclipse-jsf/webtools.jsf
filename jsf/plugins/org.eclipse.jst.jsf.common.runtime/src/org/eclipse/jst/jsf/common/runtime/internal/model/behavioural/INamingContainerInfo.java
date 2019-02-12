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
package org.eclipse.jst.jsf.common.runtime.internal.model.behavioural;

import java.io.Serializable;

import org.eclipse.jst.jsf.common.runtime.internal.model.IDesigntimeAdapter;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentFactory;

/**
 * Design time analog for the NamingContainer interface.
 * 
 * @author cbateman
 * 
 */
public interface INamingContainerInfo extends Serializable
{
    // tagging interface

    /**
     * Used as an adapter impl
     */
    public final static INamingContainerInfo ADAPTER = new NamingContainerInfo();
    
    /**
     * A default naming container info
     * @author cbateman
     *
     */
    public static class NamingContainerInfo implements INamingContainerInfo, IDesigntimeAdapter
    {
        /**
         * 
         */
        private static final long serialVersionUID = 7214529928361444126L;

        public String[] getInterfaces()
        {
            return new String[] {ComponentFactory.INTERFACE_NAMINGCONTAINER};
        }
        
    }
}
