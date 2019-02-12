/*******************************************************************************
 * Copyright (c) 2008 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
