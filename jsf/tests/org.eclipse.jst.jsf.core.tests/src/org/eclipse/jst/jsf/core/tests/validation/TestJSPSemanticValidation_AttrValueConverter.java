/*******************************************************************************
 * Copyright (c) 2008, 2010 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.core.tests.validation;

import java.util.zip.ZipFile;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.common.project.facet.core.JavaFacet;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.tests.TestsPlugin;
import org.eclipse.jst.jsf.core.tests.util.JSFCoreUtilHelper;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.eclipse.jst.jsf.validation.internal.XMLViewDefnValidator;
import org.eclipse.jst.jsp.core.internal.domdocument.DOMModelForJSP;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;

public class TestJSPSemanticValidation_AttrValueConverter extends TestCase
{
    private WebProjectTestEnvironment _webProject;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        JSFTestUtil.setValidationEnabled(false);
        JSFTestUtil.setInternetProxyPreferences(true,
                "www-proxy.uk.oracle.com", "80");

        final ZipFile zipFile = JSFTestUtil.createZipFile(TestsPlugin
                .getDefault().getBundle(),
                "/testfiles/testzips/valueHolderTest11.zip");

        _webProject = new WebProjectTestEnvironment(this,
                JavaFacet.VERSION_1_5, ProjectFacetsManager.getProjectFacet(
                        "jst.web").getVersion("2.4"));
        _webProject.createFromZip(zipFile, true);

        // initialize test case for faces 1.1
        final JSFFacetedTestEnvironment jsfFacedEnv = new JSFFacetedTestEnvironment(
                _webProject);
        // jsfFacedEnv.initialize(IJSFCoreConstants.FACET_VERSION_1_1);

        JSFCoreUtilHelper.addJSFRuntimeJarsToClasspath(JSFVersion.V1_1,
                jsfFacedEnv);
    }

    public void testSanity() throws Exception
    {
        final IProject project = _webProject.getTestProject();
        assertNotNull(project);
        assertTrue(project.isAccessible());

        final IFile jspFile = project.getFile(new Path(
                "WebContent/Case1_NoExplicitConverter.jspx"));
        assertTrue(jspFile.isAccessible());

        IStructuredModel jspModel = null;
        try
        {
            jspModel = StructuredModelManager.getModelManager()
                    .getModelForRead(jspFile);
            assert (jspModel instanceof DOMModelForJSP);
        }
        finally
        {
            if (jspModel != null)
            {
                jspModel.releaseFromRead();
            }
        }
    }

    public void testCase1_NoExplicitConverter() throws Exception
    {
        final IProject project = _webProject.getTestProject();
        assertNotNull(project);
        assertTrue(project.isAccessible());

        final IFile jspFile = project.getFile(new Path(
                "WebContent/Case1_NoExplicitConverter.jspx"));
        assertTrue(jspFile.isAccessible());

        final MockValidationReporter mockReporter = new MockValidationReporter();
        final XMLViewDefnValidator validator = new XMLViewDefnValidator();
        validator.validateView(jspFile, mockReporter);
        assertEquals(1, mockReporter.getReportedProblems().size());

        mockReporter.assertExpectedMessage(10683, 27, IMessage.NORMAL_SEVERITY);
    }

    public void testCase1a_NoExplicitConverterWithForClass() throws Exception
    {
        final IProject project = _webProject.getTestProject();
        assertNotNull(project);
        assertTrue(project.isAccessible());

        final IFile jspFile = project.getFile(new Path(
                "WebContent/Case1a_NoExplicitConverterWithForClass.jspx"));
        assertTrue(jspFile.isAccessible());

        final MockValidationReporter mockReporter = new MockValidationReporter();
        final XMLViewDefnValidator validator = new XMLViewDefnValidator();
        validator.validateView(jspFile, mockReporter);
        assertEquals(0, mockReporter.getReportedProblems().size());
    }
    
    public void testCase2_ExplicitConverterKnown() throws Exception
    {
        final IProject project = _webProject.getTestProject();
        assertNotNull(project);
        assertTrue(project.isAccessible());

        final IFile jspFile = project.getFile(new Path(
                "WebContent/Case2_ExplicitConverterKnown.jspx"));
        assertTrue(jspFile.isAccessible());

        final MockValidationReporter mockReporter = new MockValidationReporter();
        final XMLViewDefnValidator validator = new XMLViewDefnValidator();
        validator.validateView(jspFile, mockReporter);
        assertEquals(11, mockReporter.getReportedProblems().size());

        mockReporter.assertExpectedMessage(2410, 29, IMessage.NORMAL_SEVERITY);
        mockReporter.assertExpectedMessage(2509, 29, IMessage.NORMAL_SEVERITY);
        mockReporter.assertExpectedMessage(2612, 29, IMessage.NORMAL_SEVERITY);
        mockReporter.assertExpectedMessage(2717, 29, IMessage.NORMAL_SEVERITY);
        mockReporter.assertExpectedMessage(2832, 29, IMessage.NORMAL_SEVERITY);
        mockReporter.assertExpectedMessage(2950, 29, IMessage.NORMAL_SEVERITY);
        mockReporter.assertExpectedMessage(3060, 29, IMessage.NORMAL_SEVERITY);
        mockReporter.assertExpectedMessage(3169, 29, IMessage.NORMAL_SEVERITY);
        mockReporter.assertExpectedMessage(3282, 29, IMessage.NORMAL_SEVERITY);
        mockReporter.assertExpectedMessage(3398, 29, IMessage.NORMAL_SEVERITY);
        mockReporter.assertExpectedMessage(3510, 29, IMessage.NORMAL_SEVERITY);
    }

    public void testCase2_ExplicitUnknownConverter() throws Exception
    {
        final IProject project = _webProject.getTestProject();
        assertNotNull(project);
        assertTrue(project.isAccessible());

        final IFile jspFile = project.getFile(new Path(
                "WebContent/Case3_ExplicitUnknownConverter.jspx"));
        assertTrue(jspFile.isAccessible());

        final MockValidationReporter mockReporter = new MockValidationReporter();
        final XMLViewDefnValidator validator = new XMLViewDefnValidator();
        validator.validateView(jspFile, mockReporter);
        assertEquals(1, mockReporter.getReportedProblems().size());

        mockReporter.assertExpectedMessage(804, 29, IMessage.NORMAL_SEVERITY);
    }

}
