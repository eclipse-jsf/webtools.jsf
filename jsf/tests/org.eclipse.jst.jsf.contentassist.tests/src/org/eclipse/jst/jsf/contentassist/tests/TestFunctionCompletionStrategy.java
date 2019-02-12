/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.tests.util.JSFCoreUtilHelper;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.TestFileResource;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;

/**
 * Basic unit test for class FunctionCompletionStrategy
 * @author cbateman
 *
 */
public class TestFunctionCompletionStrategy extends BaseTestClass
{
    private WebProjectTestEnvironment       _testEnv;
    private IFile							_jspFile;
    private IType							_myBeanType;

    @Override
	protected void setUp() throws Exception
    {
        super.setUp();

        JSFTestUtil.setValidationEnabled(false);

        _testEnv = new WebProjectTestEnvironment(getClass().getName()+"_"+getName());
        _testEnv.createProject(false);
        assertNotNull(_testEnv);
        assertNotNull(_testEnv.getTestProject());
        assertTrue(_testEnv.getTestProject().isAccessible());

        final JSFFacetedTestEnvironment jsfFacedEnv = new JSFFacetedTestEnvironment(_testEnv);
        jsfFacedEnv.initialize(IJSFCoreConstants.FACET_VERSION_1_1);

        _testEnv.loadResourceInWebRoot(ContentAssistTestsPlugin.getDefault().getBundle(),
                                      "/testdata/faces-config_basic.xml.data",
                                      "/WEB-INF/faces-config.xml");
        _jspFile = (IFile) _testEnv.loadResourceInWebRoot(ContentAssistTestsPlugin.getDefault().getBundle(),
                                      "/testdata/basicELExpressions.jsp.data",
                                      "/basicELExpressions.jsp");
        assertNotNull(_jspFile);
        assertTrue(_jspFile.isAccessible());

        final JDTTestEnvironment jdtTestEnv = new JDTTestEnvironment(_testEnv);
        final TestFileResource resource = new TestFileResource();
        resource.load(ContentAssistTestsPlugin.getDefault().getBundle(),
                      "/testdata/MyBean.java.data");
        jdtTestEnv.addSourceFile("src", "beans", "MyBean", resource.toString());
        _myBeanType = JavaCore.create(_testEnv.getTestProject()).findType("beans.MyBean");
        assertNotNull(_myBeanType);
        assertTrue(_myBeanType.exists());
    }

    /**
     * Sanity check
     */
    public void testSanity() throws Exception
    {
    	JSFCoreUtilHelper.assertELSanity(_jspFile, 614, "value", "#{myBean.property}");
    	JSFCoreUtilHelper.assertELSanity(_jspFile, 658, "value", "#{paramValues.foo}");
    	JSFCoreUtilHelper.assertELSanity(_jspFile, 706, "action", "#{myBean.actionMethod}");

    	JSFCoreUtilHelper.assertELVariableSanity(_jspFile, "myBean");
    }

    public void testFunctionCompletionStrategy() throws Exception
    {
    	// normal value binding only has properties
    	List<ICompletionProposal>  proposals = getProposals(_jspFile, 614, 8);

    	{
	    	final Set<String> propNames = new HashSet<String>();
	    	propNames.add("property");
	    	propNames.add("class");
	    	assertDisplayNamesMatch(propNames, proposals);
    	}

    	// method binding includes methods and also properties
    	proposals = getProposals(_jspFile, 706, 8);

    	{
	    	final Set<String> propNames = new HashSet<String>();
	    	propNames.add("property");
	    	propNames.add("class");
	    	propNames.add("getProperty");
	    	propNames.add("actionMethod");
	    	propNames.add("equals");
	    	propNames.add("getClass");
	    	propNames.add("hashCode");
	    	propNames.add("notify");
	    	propNames.add("notifyAll");
	    	propNames.add("toString");
	    	propNames.add("wait");
	    	propNames.add("wait");
	    	propNames.add("wait");

	    	assertEquals(13, proposals.size());
	    	// have to loop through explicitly here because wait appears
	    	// in the list thrice, but can only be in the set once
	    	for (final ICompletionProposal prop : proposals)
	    	{
	    		assertTrue(propNames.contains(prop.getDisplayString()));
	    	}
    	}
    }

}
