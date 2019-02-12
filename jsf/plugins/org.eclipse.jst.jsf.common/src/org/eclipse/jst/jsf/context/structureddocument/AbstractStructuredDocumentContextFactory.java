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
package org.eclipse.jst.jsf.context.structureddocument;

import org.eclipse.jst.jsf.context.AbstractDelegatingFactory;

/**
 * Abstract implementation of IStructuredDocumentContextFactory that must be used
 * by all implementers of new factories for document contexts.
 * 
 * @author cbateman
 *
 */
public abstract class AbstractStructuredDocumentContextFactory extends AbstractDelegatingFactory {

    /**
     * @param supportedDelegateTypes
     */
    protected AbstractStructuredDocumentContextFactory(Class[] supportedDelegateTypes)
    {
        super(supportedDelegateTypes);
    }
}
