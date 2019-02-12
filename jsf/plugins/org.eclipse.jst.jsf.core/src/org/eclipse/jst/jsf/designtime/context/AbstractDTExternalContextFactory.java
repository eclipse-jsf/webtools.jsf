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
 * Super-class of all external context factories
 * 
 * @author cbateman
 *
 */
public abstract class AbstractDTExternalContextFactory 
{
    /**
     * @param containerContext
     * @return a new IDTExternalContext for the containerContext refered 
     * to by containerContext.
     */
    public abstract IDTExternalContext create(IAdaptable containerContext);
}
