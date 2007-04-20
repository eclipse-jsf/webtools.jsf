package org.eclipse.jst.jsf.validation.el.tests.jsp;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.test.util.TestFileResource;
import org.eclipse.jst.jsf.validation.el.tests.ELValidationTestPlugin;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

/**
 * Test cases for load bundle resolution
 * 
 * @author cbateman
 */
public class LoadBundleResolutionTestCase extends SingleJSPTestCase 
{
    public LoadBundleResolutionTestCase() 
    {
        super("/testdata/jsps/loadBundleResolution.jsp.data", "/loadBundleResolution.jsp", IJSFCoreConstants.FACET_VERSION_1_1,FACES_CONFIG_FILE_NAME_1_1);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        
        // add a resource bundle to the default package to test regression on bug 144525
        TestFileResource resource = new TestFileResource();
        resource.load(ELValidationTestPlugin.getDefault().getBundle(), 
                      "/testdata/classes/Bundle.properties.data");
        _jdtTestEnv.addResourceFile("src", new ByteArrayInputStream(resource.toBytes()), 
                      "", "Bundle.properties");

    }

    public void testSanity()
    {
        assertEquals("bundle.bundleProp2", getELText(_structuredDocument,1031));
        assertEquals("noPackageBundle.bundleProp2", getELText(_structuredDocument,1080));
        assertEquals("bundle.bundleProp1 && myBean.stringProperty", getELText(_structuredDocument,1141));
        assertEquals("empty bundle", getELText(_structuredDocument,1218));
        assertEquals("empty bundle.bundleProp2", getELText(_structuredDocument,1264));
        assertEquals("bundle.bundleProp2 + 5", getELText(_structuredDocument,1322));
        assertEquals("bundleProp2", getELText(_structuredDocument,1375));
        assertEquals("bundle.x.y", getELText(_structuredDocument,1417));
        assertEquals("noPackageBundle.x.y", getELText(_structuredDocument,1458));
        
        assertEquals("-bundle.bundleProp1", getELText(_structuredDocument,1535));
        assertEquals("bundle.bundleProp3", getELText(_structuredDocument,1588));
        assertEquals("msg", getELText(_structuredDocument,1640));
        assertEquals("bundle.x", getELText(_structuredDocument,1677));
        assertEquals("noPackageBundle.notAProperty", getELText(_structuredDocument,1716));
    }

    public void testNoErrorExprs() 
    {
        assertNoError(1031, TypeConstants.TYPE_STRING);
        assertNoError(1080, TypeConstants.TYPE_STRING);
        assertNoError(1141, Signature.SIG_BOOLEAN);
        assertNoError(1218, Signature.SIG_BOOLEAN);
        assertNoError(1264, Signature.SIG_BOOLEAN);
        assertNoError(1322, Signature.SIG_LONG);
        //assertNoError(1375, TypeConstants.TYPE_STRING);
        assertNoError(1417, TypeConstants.TYPE_STRING);
        assertNoError(1458, TypeConstants.TYPE_STRING);
    }

    public void testWarningExprs() 
    {
        List list = assertSemanticWarning(1535, Signature.SIG_LONG, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_STRING_CONVERSION_NOT_GUARANTEED_ID);

        list = assertSemanticWarning(1588, null, 1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        list = assertSemanticWarning(1640, null, 1);
        assertContainsProblem(list, DiagnosticFactory.VARIABLE_NOT_FOUND_ID);
        
        list = assertSemanticWarning(1677, null, 1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);
        
        list = assertSemanticWarning(1716, null, 1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);
    }

    public void testErrorExprs() 
    {
        // no error
    }
}
