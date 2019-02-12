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
package org.eclipse.jst.jsf.common.runtime.internal.model.decorator;

import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;

/**
 * Design time ActionListener decorator
 * 
 * @author cbateman
 *
 */
public class ActionListenerDecorator extends ComponentDecorator {

    /**
     * 
     */
    private static final long serialVersionUID = 3806862878381550874L;

    /**
     * @param decorates
     */
    public ActionListenerDecorator(final ComponentInfo decorates) {
        super(decorates);
    }

}
