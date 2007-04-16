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

/**
 * This interface is used to decouple the faces context from the 
 * DesignTimeApplicationManager
 * 
 * Client must implement or subclass
 * 
 * @author cbateman
 *
 */
public interface IExternalContextFactoryLocator 
{
    /**
     * @return the current external context factory
     */
    AbstractDTExternalContextFactory getFactory();
}
