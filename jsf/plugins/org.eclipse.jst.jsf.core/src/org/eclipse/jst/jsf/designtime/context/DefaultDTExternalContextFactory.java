/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
