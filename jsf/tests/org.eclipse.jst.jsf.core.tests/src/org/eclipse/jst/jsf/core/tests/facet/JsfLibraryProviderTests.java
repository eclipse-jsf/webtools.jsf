/*******************************************************************************
 * Copyright (c) 2001, 2009 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Debajit Adhikary - initial API and implementation
 *******************************************************************************/


package org.eclipse.jst.jsf.core.tests.facet;

import junit.framework.TestCase;

import org.eclipse.jst.common.project.facet.core.libprov.LibraryProviderOperationConfig;
import org.eclipse.jst.common.project.facet.core.libprov.NoOpLibraryProviderInstallOperationConfig;
import org.eclipse.jst.common.project.facet.core.libprov.osgi.OsgiBundlesLibraryProviderInstallOperationConfig;
import org.eclipse.jst.common.project.facet.core.libprov.user.UserLibraryProviderInstallOperationConfig;
import org.eclipse.jst.j2ee.internal.common.classpath.WtpUserLibraryProviderInstallOperationConfig;
import org.eclipse.jst.jsf.core.internal.project.facet.JSFFacetInstallDelegate;


/**
 * @author Debajit Adhikary
 *
 */
public class JsfLibraryProviderTests extends TestCase
{
    /**
     * @param name
     */
    public JsfLibraryProviderTests (final String name)
    {
        super(name);
    }


    public void testGetUserLibProviderFromLibConfig ()
    {
        assertNull(JSFFacetInstallDelegate.getUserLibConfig(new LibraryProviderOperationConfig()));
        assertNull(JSFFacetInstallDelegate.getUserLibConfig(new NoOpLibraryProviderInstallOperationConfig()));
        assertNull(JSFFacetInstallDelegate.getUserLibConfig(new OsgiBundlesLibraryProviderInstallOperationConfig()));

        assertNotNull(JSFFacetInstallDelegate.getUserLibConfig(new UserLibraryProviderInstallOperationConfig()));
        assertNotNull(JSFFacetInstallDelegate.getUserLibConfig(new WtpUserLibraryProviderInstallOperationConfig()));
    }
}
