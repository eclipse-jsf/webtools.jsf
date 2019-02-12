/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
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
package org.eclipse.jst.jsf.contentassist.tests;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.tests.util.JSFCoreUtilHelper;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.TestFileResource;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;

/**
 * Test a situation where no expected return type information is
 * available for a tag.
 *
 * @author cbateman
 *
 */
public class Test_bug_149743 extends BaseTestClass
{
    private WebProjectTestEnvironment       _testEnv;
    private IFile							_jspFile;

    @Override
	protected void setUp() throws Exception
    {
        super.setUp();

        JSFTestUtil.setValidationEnabled(false);

        _testEnv = new WebProjectTestEnvironment("Test_bug_149743_"+getName());
        _testEnv.createProject(false);
        assertNotNull(_testEnv);
        assertNotNull(_testEnv.getTestProject());
        assertTrue(_testEnv.getTestProject().isAccessible());

        final JSFFacetedTestEnvironment jsfFacedEnv = new JSFFacetedTestEnvironment(_testEnv);
        jsfFacedEnv.initialize(IJSFCoreConstants.FACET_VERSION_1_1);

        _testEnv.loadResourceInWebRoot(ContentAssistTestsPlugin.getDefault().getBundle(),
                                      "/testdata/faces-config_bug149743.xml.data",
                                      "/WEB-INF/faces-config.xml");
        _jspFile = (IFile) _testEnv.loadResourceInWebRoot(ContentAssistTestsPlugin.getDefault().getBundle(),
                                      "/testdata/bug_149743.jsp.data",
                                      "/bug_149743.jsp");

        final JDTTestEnvironment jdtTestEnv = new JDTTestEnvironment(_testEnv);
        final TestFileResource resource = new TestFileResource();
        resource.load(ContentAssistTestsPlugin.getDefault().getBundle(),
                      "/testdata/MyBean.java.data");
        jdtTestEnv.addSourceFile("src", "beans", "MyBean", resource.toString());
    }

    /**
     * Sanity check
     */
    public void testSanity() throws Exception
    {
    	JSFCoreUtilHelper.assertELSanity(_jspFile, 529, "value" ,"#{myBean.property}");
    	JSFCoreUtilHelper.assertELVariableSanity(_jspFile, "myBean");
    }

    /**
     * Checks the scenario for Test_bug_149743
     */
    public void testCompletionProposalsForId() throws Exception
    {
        final List<ICompletionProposal> proposals =
        	getProposals(_jspFile, 529, 8);
        assertEquals(2, proposals.size());
        final Set<String>  names = new HashSet<String>();

        for (final ICompletionProposal proposal : proposals)
        {
            names.add(proposal.getDisplayString());
        }
        assertTrue(names.contains("class"));
        assertTrue(names.contains("property"));
    }




}
