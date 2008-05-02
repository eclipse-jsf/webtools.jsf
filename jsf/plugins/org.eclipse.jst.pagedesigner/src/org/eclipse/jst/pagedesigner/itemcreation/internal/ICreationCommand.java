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
package org.eclipse.jst.pagedesigner.itemcreation.internal;


/**
 * Implemented by commands that do tag model creation.  NOTE: this interface is highly provisional
 * and mainly exists to provide a facade to the future use of an existing Command infrastructure:
 * probably the EMF commands.  We haven't decided yet so it is important that:
 * 
 * This interface should NOT be implemented by clients
 * 
 * @author cbateman
 *
 */
public interface ICreationCommand 
{
    // tagging interface only
}
