/*******************************************************************************
 * Copyright (c) 2001, 2010 Oracle Corporation and others.
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
package org.eclipse.jst.jsf.core.tests.validation;

import java.util.zip.ZipFile;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.common.project.facet.core.JavaFacet;
import org.eclipse.jst.jsf.core.tests.TestsPlugin;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

/**
 * General testing for the XMLViewDefnValidator.
 * 
 * @author cbateman
 * 
 */
public class XMLViewDefnValidatorTests extends TestCase
{
    private WebProjectTestEnvironment _webProject;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        final ZipFile zipFile = JSFTestUtil.createZipFile(TestsPlugin.getDefault().getBundle()
                , "/testfiles/testzips/ValidationTestProject1.zip");

        _webProject = new WebProjectTestEnvironment(this, JavaFacet.VERSION_1_5, ProjectFacetsManager.getProjectFacet( "jst.web" ).getVersion("2.4"));
        _webProject.createFromZip(zipFile, true);
    }

    public void testSanity()
    {
    	final IProject project = _webProject.getTestProject();
    	assertNotNull(project);
    	assertTrue(project.isAccessible());
    	
    	final IFile jspFile = project.getFile(new Path("WebContent/NonELValidation.jsp"));
    	assertTrue(jspFile.isAccessible());
    }
    
    public void testUnzip()
    {

    }
}
