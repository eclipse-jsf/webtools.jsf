/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
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
package org.eclipse.jst.jsf.validation.internal;

import org.eclipse.jst.jsf.common.dom.DOMAdapter;
import org.eclipse.jst.jsf.common.internal.policy.IIdentifiable;

/**
 * A strategy that validates JSF views that are defined in XML format.
 * 
 * @author cbateman
 *
 */
public abstract class AbstractXMLViewValidationStrategy implements IIdentifiable<String>
{
    private final String            _id;
    private final String            _displayName;
    
    /**
     * @param id
     * @param displayName 
     */
    public AbstractXMLViewValidationStrategy(final String id, final String displayName)
    {
        _id = id;
        _displayName = displayName;
    }

    /**
     * @param domAdapter
     */
    public abstract void validate(final DOMAdapter  domAdapter);

    /**
     * @param domAdapter
     * @return true if this validator is interested in validating this node
     */
    public abstract boolean isInteresting(final DOMAdapter  domAdapter);

    public final String getId()
    {
        return _id;
    }

    public String getDisplayName()
    {
        return _displayName;
    }
}
