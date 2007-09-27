package org.eclipse.jst.jsf.validation.el.tests.jsp;

import java.io.ByteArrayInputStream;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IDOMContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.jsf.metadataprocessors.MetaDataEnabledProcessingFactory;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidValues;
import org.eclipse.jst.jsf.test.util.TestFileResource;
import org.eclipse.jst.jsf.validation.el.tests.ELValidationTestPlugin;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.w3c.dom.Attr;

/**
 * Test cases for load bundle resolution
 * 
 * @author cbateman
 */
public class LoadBundleResolutionTestCase extends SingleJSPTestCase 
{
    public LoadBundleResolutionTestCase() 
    {
        super("/testdata/jsps/loadBundleResolution.jsp.data", "/loadBundleResolution.jsp", JSFVersion.V1_1,FACES_CONFIG_FILE_NAME_1_1);
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
        // see  https://bugs.eclipse.org/bugs/show_bug.cgi?id=190671
        assertNoError(1640, TypeConstants.TYPE_JAVAOBJECT);
    }

    public void testWarningExprs() 
    {
        List<IMessage> list = assertSemanticWarning(1535, Signature.SIG_LONG, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_STRING_CONVERSION_NOT_GUARANTEED_ID);

        list = assertSemanticWarning(1588, null, 1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        // ensure that we are validating that the basename for the missing bundle
        // is being validated since the related variable will not be flagged
        // this check replaces a previous check at 1640 for a variable not 
        // found warning. Rather than marking loadBundle variables bad when
        // we can't find the variable, we now mark the basename bad instead
        // of the variable
        // see https://bugs.eclipse.org/bugs/show_bug.cgi?id=190671
        ensureMissingBundleValidation();
        
        list = assertSemanticWarning(1677, null, 1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_IS_INTERMEDIATE_ID);
        
        list = assertSemanticWarning(1716, null, 1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);
    }

    public void testErrorExprs() 
    {
        // no error
    }
    
        
    @SuppressWarnings("unchecked")
	private void ensureMissingBundleValidation()
    {
        final IStructuredDocumentContext context = 
            IStructuredDocumentContextFactory.INSTANCE.getContext(_structuredDocument, 872);

        final IDOMContextResolver contextResolver =
        	IStructuredDocumentContextResolverFactory.INSTANCE.getDOMContextResolver(context);
        
        final Attr attr = (Attr) contextResolver.getNode();
        String attributeVal = attr.getValue();
        assertEquals("TestMessages", attributeVal);
        assertEquals(IJSFConstants.ATTR_BASENAME, attr.getLocalName());
        
        // verify that attribute value validation is picking up on missing bundles
		List vv = 
			MetaDataEnabledProcessingFactory.getInstance()
				.getAttributeValueRuntimeTypeFeatureProcessors
					(IValidValues.class, context, ITLDConstants.URI_JSF_CORE
						, IJSFConstants.TAG_LOADBUNDLE, IJSFConstants.ATTR_BASENAME);

		boolean validatesMissingBundle = false;
		
		for (Iterator it = vv.iterator();it.hasNext();)
		{
			IValidValues v = (IValidValues)it.next();
			if (attributeVal == null) attributeVal = "";//ensure to be non-null
			if (!v.isValidValue(attributeVal.trim())){	
				if (v.getValidationMessages().size() > 0)
				{
					validatesMissingBundle = true;
					break;
				}
			}
		}
		
		assertTrue(validatesMissingBundle);
    }
}
