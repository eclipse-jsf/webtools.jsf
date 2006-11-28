/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/
 
package org.eclipse.jst.jsf.designtime.internal.jsp;

import java.util.Map;

import org.eclipse.core.resources.IProject;

/**
 * Factory class for acquiring a ResourceBundleMapSourceFactory outside the local
 * package.   This is intended only for internal testing and related use and 
 * should not be used for normal feature development
 * 
 * @author cbateman
 *
 */
public class ResourceBundleMapSourceFactory 
{
    /**
     * @param project
     * @param resourcePathStr
     * @return a ResourceBundleMapSource
     * @throws Exception if construction fails
     */
    public static Map getResourceBundleMapSource(final IProject project, 
                                                                     final String resourcePathStr) 
        throws Exception
    {
        return new ResourceBundleMapSource(project, resourcePathStr);
    }
}
