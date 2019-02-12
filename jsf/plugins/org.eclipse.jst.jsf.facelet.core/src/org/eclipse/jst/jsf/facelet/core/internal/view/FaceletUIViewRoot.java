/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facelet.core.internal.view;

import org.eclipse.jst.jsf.designtime.context.DTFacesContext;
import org.eclipse.jst.jsf.designtime.internal.view.DefaultDTUIViewRoot;

/**
 * Facelet view root
 * 
 * @author cbateman
 *
 */
public class FaceletUIViewRoot extends DefaultDTUIViewRoot
{
    /**
     * 
     */
    private static final long serialVersionUID = -7289148553566455867L;

    /**
     * @param facesContext
     */
    public FaceletUIViewRoot(final DTFacesContext facesContext)
    {
        super(facesContext);
    }
}
