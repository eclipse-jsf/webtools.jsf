package org.eclipse.jst.jsf.validation.el.tests.base;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.jst.jsf.validation.el.tests.ELValidationTestPlugin;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;

/**
 * Test case for a group of tests in a single JSP file
 * @author cbateman
 *
 */
public abstract class SingleJSPTestCase extends BaseTestCase
{
    /**
     * Name of the test data file containing the JSP source for this test
     */
    protected String                  _srcFileName;
    /**
     * Name of the file and path where the JSP source should be put in the
     * test project
     */
    protected String                  _destFileName;
    
    /**
     * The file handle to the JSP in the workspace
     */
    protected IFile                   _testJSP;
    /**
     * The SSE structured model for the JSP
     */
    protected IStructuredModel        _structuredModel;
    /**
     * The SSE structured document for the JSP
     */
    protected IStructuredDocument     _structuredDocument;

    protected void setUp() throws Exception 
    {
        super.setUp();

        _testJSP = (IFile) _testEnv.loadResourceInWebRoot
                            (ELValidationTestPlugin.getDefault().getBundle(),
                               _srcFileName, _destFileName);
        
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
     * Same as BaseTestCase.assertNoError but automatically uses _testJSP for the file
     * and _structuredDocument.
     * 
     * @param document
     * @param docPos
     * @param expectedSignature
     */
    protected void assertNoError(int docPos, String expectedSignature) {
        super.assertNoError(_structuredDocument, docPos, _testJSP, expectedSignature);
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
        super.assertNoError(_structuredDocument, docPos, _testJSP, expectedSignature, assignability);
        
    }

    /**
     * Calls BaseTestCase.assertSyntax using the already known document and file handle
     * for this JSP test case.
     * 
     * @param docPos
     * @param expectedProblems
     * @return the list of syntax errors (may be empty)
     */
    protected List assertSyntaxError(int docPos, int expectedProblems)
    {
        return super.assertSyntaxError(_structuredDocument, docPos, _testJSP, 
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
    protected List assertSyntaxWarning(int docPos, int expectedProblems)
    {
        return super.assertSyntaxWarning(_structuredDocument, docPos, _testJSP, 
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
    protected List assertSemanticError(int docPos, String expectedSignature, int expectedProblems) {
        return super.assertSemanticError(_structuredDocument, docPos, _testJSP, expectedSignature,
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
    protected List assertSemanticWarning(int docPos, String expectedSignature, int expectedProblems) {
        return super.assertSemanticWarning(_structuredDocument, docPos, _testJSP, expectedSignature,
                expectedProblems);
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
}
