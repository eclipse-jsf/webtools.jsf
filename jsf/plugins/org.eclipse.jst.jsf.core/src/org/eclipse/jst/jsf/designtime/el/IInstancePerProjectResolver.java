/*******************************************************************************
 * Copyright (c) 2008 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 * 
 ********************************************************************************/

package org.eclipse.jst.jsf.designtime.el;

import org.eclipse.core.resources.IProject;

/**
 * See the variable and properyresolver extension points attribute instancePerProject.
 * 
 * Clients may implement.
 * 
 * @author cbateman
 *
 */
public interface IInstancePerProjectResolver
{
    /**
     * Called by the design time application manager before using the 
     * resolver with the project it associated with.  Resolvers need not 
     * implement unless they wish to be instancePerProject resolvers.
     * 
     * @param project
     */
    void setProject(final IProject project);
}
