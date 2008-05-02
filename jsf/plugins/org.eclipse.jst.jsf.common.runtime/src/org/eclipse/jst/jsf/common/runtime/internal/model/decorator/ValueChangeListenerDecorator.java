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
import org.eclipse.jst.jsf.common.runtime.internal.model.event.IValueChangeListenerInfo;

/**
 * ValueChangeListener component decorator.
 * 
 * @author cbateman
 *
 */
public class ValueChangeListenerDecorator extends ComponentDecorator implements
        IValueChangeListenerInfo 
{
    /**
     * serializable id
     */
    private static final long serialVersionUID = 9045831927898936727L;

    /**
     * @param decorates
     */
    public ValueChangeListenerDecorator(ComponentInfo decorates) {
        super(decorates);
    }    
}
