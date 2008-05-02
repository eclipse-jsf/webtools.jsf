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
package org.eclipse.jst.jsf.common.internal.managedobject;

/**
 * Sub-class for managed objects.
 *
 */
public abstract class AbstractManagedObject implements IManagedObject 
{
    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.common.internal.managedobject.IManagedObject#dispose()
     */
    public abstract void dispose();

    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.common.internal.managedobject.IManagedObject#checkpoint()
     */
    public abstract void checkpoint();

    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.common.internal.managedobject.IManagedObject#destroy()
     */
    public abstract void destroy();

}
