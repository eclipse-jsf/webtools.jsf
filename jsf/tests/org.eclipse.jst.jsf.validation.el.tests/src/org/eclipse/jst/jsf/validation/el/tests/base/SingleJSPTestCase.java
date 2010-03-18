/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.validation.el.tests.base;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.tests.validation.MockValidationReporter.ReportedProblem;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;

public abstract class SingleJSPTestCase extends JSPTestCase 
{
    
    public static final String FACES_CONFIG_FILE_NAME_1_1 = "/testdata/web/faces-config_1_1.xml.data";
    public static final String FACES_CONFIG_FILE_NAME_1_2 = "/testdata/web/faces-config_1_2.xml.data";
    
    /**
     * The file handle to the JSP in the workspace
     */
    protected IFile                             _testJSP;
    /**
     * The SSE structured model for the JSP
     */
    protected IStructuredModel                  _structuredModel;
    /**
     * The SSE structured document for the JSP
     */
    protected IStructuredDocument               _structuredDocument;
    /**
     * Name of the test data file containing the JSP source for this test
     */
    private final String                        _srcFileName;
    
    /**
     * Name of the file and path where the JSP source should be put in the
     * test project
     */
    protected final String                      _destFileName;
    

    protected SingleJSPTestCase(final String srcFileName, final String destFileName, final JSFVersion defaultJSFVersion, final String defaultFacesConfigFile)
    {
        super(defaultJSFVersion, defaultFacesConfigFile);
        _srcFileName = srcFileName;
        _destFileName = destFileName;
    }
    

    protected void setUp() throws Exception 
    {
        super.setUp();

        _testJSP = loadJSP(_srcFileName, _destFileName);
        
        _structuredModel = StructuredModelManager.getModelManager().getModelForRead(_testJSP);
        _structuredDocument = _structuredModel.getStructuredDocument();
    }

    protected void tearDown() throws Exception 
    {
        super.tearDown();
        
        if (_structuredModel != null)
        {
            _structuredModel.releaseFromRead();
        }
    }

    /**
     * Tests for expressions that generate no problems.
     */
    public abstract void testNoErrorExprs();
    /**
     * Tests for expressions that generate problems with the highest severity being WARNING
     */
    public abstract void testWarningExprs();
    /**
     * Tests for expressions that generate problems with the highest severity being ERROR
     */
    public abstract void testErrorExprs();

    
    /**
     * Same as BaseTestCase.assertNoError but automatically uses _testJSP for the file
     * and _structuredDocument.
     * 
     * @param document
     * @param docPos
     * @param expectedSignature
     */
    protected void assertNoError(int docPos, String expectedSignature) {
        ELAssert.assertNoError(_structuredDocument, _symbolResolverFactory, docPos, _testJSP, expectedSignature);
    }
    
    /**
     * Calls BaseTestCase.assertNoError using the already known document and file handle
     * for this JSP test case.
     * 
     * @param docPos
     * @param expectedSignature
     * @param assignability
     */
    protected void assertNoError(int docPos, String expectedSignature, int assignability)
    {
        ELAssert.assertNoError(_structuredDocument, _symbolResolverFactory, docPos, _testJSP, expectedSignature, assignability);
        
    }

    /**
     * Calls BaseTestCase.assertSyntax using the already known document and file handle
     * for this JSP test case.
     * 
     * @param docPos
     * @param expectedProblems
     * @return the list of syntax errors (may be empty)
     */
    protected List<ReportedProblem> assertSyntaxError(int docPos, int expectedProblems)
    {
        return ELAssert.assertSyntaxError(_structuredDocument, _symbolResolverFactory, docPos, _testJSP, 
                expectedProblems);
    }

    /**
     * Calls BaseTestCase.assertSyntax using the already known document and file handle
     * for this JSP test case.
     * 
     * @param docPos
     * @param expectedProblems
     * @return the list of syntax warnings found (may be empty)
     */
    protected List<ReportedProblem> assertSyntaxWarning(int docPos, int expectedProblems)
    {
        return ELAssert.assertSyntaxWarning(_structuredDocument, _symbolResolverFactory, docPos, _testJSP, 
                expectedProblems);
    }
    
    /**
     * Same as BaseTestCase.assertSemanticError but automatically uses _testJSP for the file
     * 
     * @param document
     * @param docPos
     * @param expectedSignature
     * @param expectedProblems
     * @return the list of errors found
     */
    protected List<ReportedProblem> assertSemanticError(int docPos, String expectedSignature, int expectedProblems) {
        return ELAssert.assertSemanticError(_structuredDocument, _symbolResolverFactory, docPos, _testJSP, expectedSignature,
                expectedProblems);
    }

    /**
     * Same as BaseTestCase.assertSemanticWarning but automatically uses _testJSP for the file
     * and _structuredDocument for the document
     * @param document
     * @param docPos
     * @param expectedSignature
     * @param expectedProblems
     * @return the list of errors found
     */
    protected List<ReportedProblem> assertSemanticWarning(int docPos, String expectedSignature, int expectedProblems) {
        return ELAssert.assertSemanticWarning(_structuredDocument, _symbolResolverFactory, docPos, _testJSP, expectedSignature,
                expectedProblems);
    }
    
    protected List<ReportedProblem> assertSemanticInfo(int docPos, String expectedSignature, int expectedProblems)
    {
        return ELAssert.assertSemanticInfo(_structuredDocument, _symbolResolverFactory, docPos, _testJSP, expectedSignature,
                expectedProblems);
    }
    
}
