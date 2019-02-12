/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
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
