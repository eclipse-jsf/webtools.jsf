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
        super("/testdata/jsps/resourceBundleResolution.jsp.data", "/resourceBundle.jsp", IJSFCoreConstants.FACET_VERSION_1_2, FACES_CONFIG_FILE_NAME_1_2 );
    }

    
    protected void doStandaloneSetup() {
        super.doStandaloneSetup();
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

        // add a second res bundle to ensure that res bundle is not somehow being
        // confused by a loadBundle variable
        resource = new TestFileResource();
        resource.load(ELValidationTestPlugin.getDefault().getBundle(), 
                      "/testdata/classes/Bundle2.properties.data");
        _jdtTestEnv.addResourceFile("src", new ByteArrayInputStream(resource.toBytes()), 
                      "beans", "Bundle2.properties");
    }

    public void testSanity()
    {
        assertEquals("resBundleProp1.bundleProp2", getELText(_structuredDocument,913));
        assertEquals("noPackageBundle.bundleProp2", getELText(_structuredDocument,970));
        assertEquals("resBundleProp1.bundleProp1 && myBean.stringProperty", getELText(_structuredDocument,1031));
        assertEquals("empty resBundleProp1", getELText(_structuredDocument,1116));
        assertEquals("empty resBundleProp1.bundleProp2", getELText(_structuredDocument,1170));
        assertEquals("resBundleProp1.bundleProp2 + 5", getELText(_structuredDocument,1236));
        assertEquals("bundleProp2", getELText(_structuredDocument,1297));
        assertEquals("resBundleProp1.x.y", getELText(_structuredDocument,1339));
        assertEquals("noPackageBundle.x.y", getELText(_structuredDocument,1388));
        assertEquals("resBundleProp2.name", getELText(_structuredDocument,1442));
        assertEquals("resBundleProp2.movie", getELText(_structuredDocument,1492));
        
        assertEquals("-resBundleProp1.bundleProp1", getELText(_structuredDocument,1570));
        assertEquals("resBundleProp1.bundleProp3", getELText(_structuredDocument,1631));
        assertEquals("msg", getELText(_structuredDocument,1691));
        assertEquals("resBundleProp1.x", getELText(_structuredDocument,1728));
        assertEquals("noPackageBundle.notAProperty", getELText(_structuredDocument,1775));
        assertEquals("resBundleProp2.bundleProp2", getELText(_structuredDocument,1838));
        assertEquals("resBundleProp2.notAPropAtAll", getELText(_structuredDocument,1895));
    }

    public void testNoErrorExprs() 
    {
        assertNoError(913, TypeConstants.TYPE_STRING);
        assertNoError(970, TypeConstants.TYPE_STRING);
        assertNoError(1031, Signature.SIG_BOOLEAN);
        assertNoError(1116, Signature.SIG_BOOLEAN);
        assertNoError(1170, Signature.SIG_BOOLEAN);
        assertNoError(1236, Signature.SIG_LONG);
        //assertNoError(1297, TypeConstants.TYPE_MAP);
        assertNoError(1339, TypeConstants.TYPE_STRING);
        assertNoError(1388, TypeConstants.TYPE_STRING);
        assertNoError(1442, TypeConstants.TYPE_STRING);
        assertNoError(1492, TypeConstants.TYPE_STRING);
    }

    public void testWarningExprs() 
    {
        List problems = assertSemanticWarning(1570, null, 1);
        assertContainsProblem(problems, DiagnosticFactory.UNARY_OP_STRING_CONVERSION_NOT_GUARANTEED_ID);

        problems = assertSemanticWarning(1631, null, 1);
        assertContainsProblem(problems, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        problems = assertSemanticWarning(1691, null, 1);
        assertContainsProblem(problems, DiagnosticFactory.VARIABLE_NOT_FOUND_ID);

        problems = assertSemanticWarning(1728, null, 1);
        assertContainsProblem(problems, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        problems = assertSemanticWarning(1775, null, 1);
        assertContainsProblem(problems, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        problems = assertSemanticWarning(1838, null, 1);
        assertContainsProblem(problems, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        problems = assertSemanticWarning(1895, null, 1);
        assertContainsProblem(problems, DiagnosticFactory.MEMBER_NOT_FOUND_ID);
    }

    public void testErrorExprs() 
    {
        // no errors
    }
}
