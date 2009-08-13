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

import java.util.List;

import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.tests.validation.MockValidationReporter.ReportedProblem;
import org.eclipse.jst.jsf.validation.el.tests.base.ELAssert;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

public class DataTableResolutionTestCase extends SingleJSPTestCase
{
    public DataTableResolutionTestCase()
    {
        super("/testdata/jsps/dataTableResolution.jsp.data", "/dataTableResolution.jsp", JSFVersion.V1_1,FACES_CONFIG_FILE_NAME_1_1);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    @Override
    public void testSanity()
    {
        assertEquals("foo.x", ELAssert.getELText(_structuredDocument,879));
        assertEquals("row1.name", ELAssert.getELText(_structuredDocument,1036));
        assertEquals("row3.stringProperty", ELAssert.getELText(_structuredDocument,1188));
        assertEquals("row4.anyField", ELAssert.getELText(_structuredDocument,1363));

        assertEquals("row2WrongVar.x", ELAssert.getELText(_structuredDocument,1554));
        assertEquals("row2.wrongMember", ELAssert.getELText(_structuredDocument,1687));
    }

    @Override
    public void testNoErrorExprs()
    {
        assertNoError(879, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(1036, TypeConstants.TYPE_STRING);
        assertNoError(1188, TypeConstants.TYPE_STRING);
        assertNoError(1363, TypeConstants.TYPE_JAVAOBJECT);
    }

    @Override
    public void testWarningExprs()
    {
        List<ReportedProblem> problems = assertSemanticWarning(1554, null, 1);
        ELAssert.assertContainsProblem(problems, DiagnosticFactory.VARIABLE_NOT_FOUND_ID);

        problems = assertSemanticWarning(1687, null, 1);
        ELAssert.assertContainsProblem(problems, DiagnosticFactory.MEMBER_NOT_FOUND_ID);
    }

    @Override
    public void testErrorExprs() {
        // no errors
    }
}
