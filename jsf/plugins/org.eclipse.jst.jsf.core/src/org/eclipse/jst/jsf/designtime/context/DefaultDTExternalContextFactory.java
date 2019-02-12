/*******************************************************************************
 * Copyright (c) 2006, 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.designtime.context;

import org.eclipse.core.runtime.IAdaptable;

/**
 * Creates external contexts for JSPs
 * 
 * Clients may sub-class
 * 
 * @author cbateman
 *
 */
public class DefaultDTExternalContextFactory extends
        AbstractDTExternalContextFactory 
{

    /**
     * @param containerContext -- must be adaptable to an IFile of content type
     * JSP
     *  
     * @return a new DT external context
     */
    public IDTExternalContext create(final IAdaptable containerContext) 
    {
        return new DTJSPExternalContext(containerContext);
    }

}
