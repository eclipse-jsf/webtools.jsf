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
package org.eclipse.jst.jsf.common.runtime.internal.debug;

import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;

/**
 * Creates JSF remote monitor messages.
 * 
 * @author cbateman
 *
 */
public class MonitorMessageFactory 
{
    /**
     * @param viewId
     * @param root
     * @param renderRoot
     * @return a new component tree message for the parameters
     */
    public static ComponentTreeMessage createTreeMessage(final String viewId, final ComponentInfo root, final RenderNode renderRoot)
    {
        ComponentTreeMessage message = new ComponentTreeMessage();
        message.setViewId(viewId);
        message.setTreeRoot(root);
        message.setRenderRoot(renderRoot);
        return message;
    }
}
