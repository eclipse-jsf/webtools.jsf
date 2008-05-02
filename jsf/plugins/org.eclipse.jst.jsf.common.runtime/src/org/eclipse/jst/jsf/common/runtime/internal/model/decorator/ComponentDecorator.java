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
package org.eclipse.jst.jsf.common.runtime.internal.model.decorator;

import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;

/**
 * @author cbateman
 *
 */
public abstract class ComponentDecorator extends Decorator 
{
    /**
     * 
     */
    private static final long serialVersionUID = 4378142984217256364L;
    
    private final ComponentInfo     _decorates;
    
    /**
     * @param decorates
     */
    public ComponentDecorator(final ComponentInfo decorates)
    {
        _decorates = decorates;
    }

    /**
     * @return the component that this decorator decorates.
     */
    public final ComponentInfo getDecorates() {
        return _decorates;
    }
}
