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
package org.eclipse.jst.jsf.contentassist.tests;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.tests.util.JSFCoreUtilHelper;
import org.eclipse.jst.jsf.core.tests.util.JSFCoreUtilHelper.ContextWrapper;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.TestFileResource;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;

/**
 * Regression test for 149224 -- replace '.' on completion with [] style of
 * map
 *
 * @author cbateman
 *
 */
public class Test_bug_149224 extends BaseTestClass
{
    private WebProjectTestEnvironment       _testEnv;
    private IFile							_jspFile;

    @Override
	protected void setUp() throws Exception
    {
        super.setUp();

        JSFTestUtil.setValidationEnabled(false);

        _testEnv = new WebProjectTestEnvironment("Test_bug_149224_"+getName());
        _testEnv.createProject(false);
        assertNotNull(_testEnv);
        assertNotNull(_testEnv.getTestProject());
        assertTrue(_testEnv.getTestProject().isAccessible());

        final JSFFacetedTestEnvironment jsfFacedEnv = new JSFFacetedTestEnvironment(_testEnv);
        jsfFacedEnv.initialize(IJSFCoreConstants.FACET_VERSION_1_1);

        _jspFile = (IFile) _testEnv.loadResourceInWebRoot(ContentAssistTestsPlugin.getDefault().getBundle(),
                                      "/testdata/bug_149224_1.jsp.data",
                                      "/bug_149224.jsp");

        assertNotNull(_jspFile);
        assertTrue(_jspFile.isAccessible());

        final JDTTestEnvironment jdtTestEnv = new JDTTestEnvironment(_testEnv);
        TestFileResource resource = new TestFileResource();
        resource.load(ContentAssistTestsPlugin.getDefault().getBundle(),
                      "/testdata/MyBean.java.data");
        jdtTestEnv.addSourceFile("src", "beans", "MyBean", resource.toString());

        resource = new TestFileResource();
        resource.load(ContentAssistTestsPlugin.getDefault().getBundle(),
                      "/testdata/bug_149224.properties.data");
        jdtTestEnv.addResourceFile("src", new ByteArrayInputStream(resource.toBytes()),
                                   "bundles", "bundle1.properties");
        
        JSFCoreUtilHelper.injectTestTagRegistryFactoryProvider(JSFCoreUtilHelper.createSimpleRegistryFactory());
    }

    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
        JSFCoreUtilHelper.injectTestTagRegistryFactoryProvider(null);
    }

    /**
     * Sanity check
     */
    public void testSanity() throws Exception
    {
        final ContextWrapper wrapper = null;

        try
        {
            JSFCoreUtilHelper.assertELSanity(_jspFile, 589, "value", "#{bundle1.}");
            JSFCoreUtilHelper.assertELSanity(_jspFile, 630, "value", "#{bundle1.x}");
            JSFCoreUtilHelper.assertELVariableSanity(_jspFile, "bundle1");
        }
        finally
        {
            if (wrapper != null)
            {
                wrapper.dispose();
            }
        }
    }

    /**
     * Test the completion:
     *
     *      # { b u n d l e 1 . }
     *                         ^
     */
	public void testCompletionAtCloseBrace() throws Exception
    {
		final List<ICompletionProposal> proposals =
			getProposals(_jspFile, 589, "bundle1.",9);
		assertNotNull(proposals);
        ICompletionProposal proposal = null;

        FIND_ARRAY_PROPOSAL:
            for (final ICompletionProposal findProp : proposals)
        {
            // TODO: this is a bit of a hack.  Would rather be able
            // to query for the actual replacement text
            if (findProp.getDisplayString().startsWith("['"))
            {
            	proposal = findProp;
                break FIND_ARRAY_PROPOSAL;
            }
        }

        assertNotNull(proposal);

        applyAndCheck(_jspFile, 589, proposal, "bundle1['prop.with.dots_x']");
    }

    /**
     * Test the completion:
     *
     *      # { b u n d l e 1 . x }
     *                         ^
     */
	public void testCompletionAtProperty() throws Exception
    {
        final ContextWrapper wrapper = null;

        try
        {
            final List<ICompletionProposal> proposals =
            	getProposals(_jspFile, 630, 9);

            ICompletionProposal proposal = null;

            FIND_ARRAY_PROPOSAL:
                for (final ICompletionProposal completionProposal : proposals) {
				    proposal = completionProposal;
				    // TODO: this is a bit of a hack.  Would rather be able
				    // to query for the actual replacement text
				    if (proposal.getDisplayString().startsWith("['"))
				    {
				        break FIND_ARRAY_PROPOSAL;
				    }
				}

            assertNotNull(proposal);
            applyAndCheck(_jspFile, 630, proposal, "bundle1['prop.with.dots_x']x");
        }
        finally
        {
            if (wrapper != null)
            {
                wrapper.dispose();
            }
        }
    }
}
