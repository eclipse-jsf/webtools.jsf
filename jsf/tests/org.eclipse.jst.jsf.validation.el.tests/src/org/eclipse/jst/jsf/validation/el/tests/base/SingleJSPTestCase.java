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
    protected String                  _srcFileName;
    protected String                  _destFileName;
    
    protected IFile                   _testJSP;
    protected IStructuredModel        _structuredModel;
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
    
    protected void assertNoError(int docPos, String expectedSignature, int assignability)
    {
        super.assertNoError(_structuredDocument, docPos, _testJSP, expectedSignature, assignability);
        
    }

    protected List assertSyntaxError(int docPos, int expectedProblems)
    {
        return super.assertSyntaxError(_structuredDocument, docPos, _testJSP, 
                expectedProblems);
    }

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
    
    public abstract void testNoErrorExprs();
    public abstract void testWarningExprs();
    public abstract void testErrorExprs();
}
