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
package org.eclipse.jst.jsf.common.runtime.internal.model.behavioural;

import org.eclipse.jst.jsf.common.runtime.internal.model.IDesigntimeAdapter;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentFactory;

/**
 * Implementation of the IActionSource2Info
 * 
 * @author cbateman
 *
 */
public class ActionSourceInfo2 extends ActionSourceInfo implements
        IActionSource2Info, IDesigntimeAdapter
{
    /**
     * serializable id
     */
    private static final long serialVersionUID = 5811194815559772378L;
    private static final String[]                INTERFACE = new String[]{ComponentFactory.INTERFACE_ACTIONSOURCE2};

    private final String        _actionExpression;
    
    /**
     * @param action
     * @param actionListener
     * @param immediate
     * @param actionExpression
     */
    public ActionSourceInfo2(final String action, final String actionListener,
            final boolean immediate, final String actionExpression) {
        super(action, actionListener, immediate);
        _actionExpression = actionExpression;
    }

    public String getActionExpression() {
        return _actionExpression;
    }

    public String[] getInterfaces()
    {
        return INTERFACE;
    }
    
}
