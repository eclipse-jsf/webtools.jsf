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
package org.eclipse.jst.jsf.validation.internal.appconfig;

/**
 * Used to expose internal message data to unit tests.  Should not be used by clients
 * @author cbateman
 *
 */
public interface ILocalizedMessage {
    /**
     * @return the error code
     */
    public int getErrorCode();
}
