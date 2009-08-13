/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.validation.el.tests.jsp;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.tests.validation.MockValidationReporter.ReportedProblem;
import org.eclipse.jst.jsf.test.util.TestFileResource;
import org.eclipse.jst.jsf.validation.el.tests.ELValidationTestPlugin;
import org.eclipse.jst.jsf.validation.el.tests.base.ELAssert;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

/**
 * Tests the introduction of symbols through resource-bundle element the
 * application configuration file (faces-config.xml)
 * 
 * JSF 1.2 and later only
 * 
 * @author cbateman
 *
 */
public class ResourceBundleTestCase extends SingleJSPTestCase {

    public ResourceBundleTestCase()
    {
        super("/testdata/jsps/resourceBundleResolution.jsp.data", "/resourceBundle.jsp", JSFVersion.V1_2, FACES_CONFIG_FILE_NAME_1_2 );
    }


    @Override
    protected void doStandaloneSetup() {
        super.doStandaloneSetup();
    }


    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        // add a resource bundle to the default package to test regression on bug 144525
        TestFileResource resource = new TestFileResource();
        resource.load(ELValidationTestPlugin.getDefault().getBundle(),
        "/testdata/classes/Bundle.properties.data");
        _jdtTestEnv.addResourceFile("src", new ByteArrayInputStream(resource.toBytes()),
                "", "Bundle.properties");

        // add a second res bundle to ensure that res bundle is not somehow being
        // confused by a loadBundle variable
        resource = new TestFileResource();
        resource.load(ELValidationTestPlugin.getDefault().getBundle(),
        "/testdata/classes/Bundle2.properties.data");
        _jdtTestEnv.addResourceFile("src", new ByteArrayInputStream(resource.toBytes()),
                "beans", "Bundle2.properties");
    }

    @Override
    public void testSanity()
    {
        assertEquals("resBundleProp1.bundleProp2", ELAssert.getELText(_structuredDocument,887));
        assertEquals("noPackageBundle.bundleProp2", ELAssert.getELText(_structuredDocument,943));
        assertEquals("resBundleProp1.bundleProp1 && myBean.stringProperty", ELAssert.getELText(_structuredDocument,1003));
        assertEquals("empty resBundleProp1", ELAssert.getELText(_structuredDocument,1087));
        assertEquals("empty resBundleProp1.bundleProp2", ELAssert.getELText(_structuredDocument,1140));
        assertEquals("resBundleProp1.bundleProp2 + 5", ELAssert.getELText(_structuredDocument,1205));
        assertEquals("bundleProp2", ELAssert.getELText(_structuredDocument,1265));
        assertEquals("resBundleProp1.x.y", ELAssert.getELText(_structuredDocument,1306));
        assertEquals("noPackageBundle.x.y", ELAssert.getELText(_structuredDocument,1354));
        assertEquals("resBundleProp2.name", ELAssert.getELText(_structuredDocument,1406));
        assertEquals("resBundleProp2.movie", ELAssert.getELText(_structuredDocument,1455));

        assertEquals("-resBundleProp1.bundleProp1", ELAssert.getELText(_structuredDocument,1530));
        assertEquals("resBundleProp1.bundleProp3", ELAssert.getELText(_structuredDocument,1590));
        assertEquals("msg", ELAssert.getELText(_structuredDocument,1649));
        assertEquals("resBundleProp1.x", ELAssert.getELText(_structuredDocument,1685));
        assertEquals("noPackageBundle.notAProperty", ELAssert.getELText(_structuredDocument,1731));
        assertEquals("resBundleProp2.bundleProp2", ELAssert.getELText(_structuredDocument,1792));
        assertEquals("resBundleProp2.notAPropAtAll", ELAssert.getELText(_structuredDocument,1848));
    }

    @Override
    public void testNoErrorExprs()
    {
        assertNoError(887, TypeConstants.TYPE_STRING);
        assertNoError(943, TypeConstants.TYPE_STRING);
        assertNoError(1003, Signature.SIG_BOOLEAN);
        assertNoError(1087, Signature.SIG_BOOLEAN);
        assertNoError(1140, Signature.SIG_BOOLEAN);
        assertNoError(1205, Signature.SIG_LONG);
        //assertNoError(1265, TypeConstants.TYPE_MAP);
        assertNoError(1306, TypeConstants.TYPE_STRING);
        assertNoError(1354, TypeConstants.TYPE_STRING);
        assertNoError(1406, TypeConstants.TYPE_STRING);
        assertNoError(1455, TypeConstants.TYPE_STRING);
    }

    @Override
    public void testWarningExprs()
    {
        List<ReportedProblem> problems = assertSemanticWarning(1530, null, 1);
        ELAssert.assertContainsProblem(problems, DiagnosticFactory.UNARY_OP_STRING_CONVERSION_NOT_GUARANTEED_ID);

        problems = assertSemanticWarning(1590, null, 1);
        ELAssert.assertContainsProblem(problems, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        problems = assertSemanticWarning(1649, null, 1);
        ELAssert.assertContainsProblem(problems, DiagnosticFactory.VARIABLE_NOT_FOUND_ID);

        problems = assertSemanticWarning(1685, null, 1);
        ELAssert.assertContainsProblem(problems, DiagnosticFactory.MEMBER_IS_INTERMEDIATE_ID);

        problems = assertSemanticWarning(1731, null, 1);
        ELAssert.assertContainsProblem(problems, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        problems = assertSemanticWarning(1792, null, 1);
        ELAssert.assertContainsProblem(problems, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        problems = assertSemanticWarning(1848, null, 1);
        ELAssert.assertContainsProblem(problems, DiagnosticFactory.MEMBER_NOT_FOUND_ID);
    }

    @Override
    public void testErrorExprs()
    {
        // no errors
    }
}
