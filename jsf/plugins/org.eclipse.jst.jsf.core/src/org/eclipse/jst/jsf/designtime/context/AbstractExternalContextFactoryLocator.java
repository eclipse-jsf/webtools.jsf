/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.designtime.context;

/**
 * Parent of all implementers of IExternalContextFactoryLocator
 * @author cbateman
 *
 */
public abstract class AbstractExternalContextFactoryLocator implements
        IExternalContextFactoryLocator 
{
    /**
     * @return the current external context factory
     */
    public abstract AbstractDTExternalContextFactory getFactory();
}
