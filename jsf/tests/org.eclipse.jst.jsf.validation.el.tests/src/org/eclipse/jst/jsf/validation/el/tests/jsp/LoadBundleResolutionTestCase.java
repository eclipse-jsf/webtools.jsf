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
import org.eclipse.jst.jsf.core.tests.validation.MockValidationReporter.ReportedProblem;
import org.eclipse.jst.jsf.metadataprocessors.MetaDataEnabledProcessingFactory;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidValues;
import org.eclipse.jst.jsf.test.util.TestFileResource;
import org.eclipse.jst.jsf.validation.el.tests.ELValidationTestPlugin;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;
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

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        // add a resource bundle to the default package to test regression on bug 144525
        final TestFileResource resource = new TestFileResource();
        resource.load(ELValidationTestPlugin.getDefault().getBundle(),
        "/testdata/classes/Bundle.properties.data");
        _jdtTestEnv.addResourceFile("src", new ByteArrayInputStream(resource.toBytes()),
                "", "Bundle.properties");

    }

    @Override
    public void testSanity()
    {
        assertEquals("bundle.bundleProp2", getELText(_structuredDocument,1003));
        assertEquals("noPackageBundle.bundleProp2", getELText(_structuredDocument,1051));
        assertEquals("bundle.bundleProp1 && myBean.stringProperty", getELText(_structuredDocument,1111));
        assertEquals("empty bundle", getELText(_structuredDocument,1187));
        assertEquals("empty bundle.bundleProp2", getELText(_structuredDocument,1232));
        assertEquals("bundle.bundleProp2 + 5", getELText(_structuredDocument,1289));
        assertEquals("bundleProp2", getELText(_structuredDocument,1341));
        assertEquals("bundle.x.y", getELText(_structuredDocument,1382));
        assertEquals("noPackageBundle.x.y", getELText(_structuredDocument,1422));

        assertEquals("-bundle.bundleProp1", getELText(_structuredDocument,1496));
        assertEquals("bundle.bundleProp3", getELText(_structuredDocument,1548));
        assertEquals("msg", getELText(_structuredDocument,1599));
        assertEquals("bundle.x", getELText(_structuredDocument,1635));
        assertEquals("noPackageBundle.notAProperty", getELText(_structuredDocument,1673));
    }

    @Override
    public void testNoErrorExprs()
    {
        assertNoError(1003, TypeConstants.TYPE_STRING);
        assertNoError(1051, TypeConstants.TYPE_STRING);
        assertNoError(1111, Signature.SIG_BOOLEAN);
        assertNoError(1187, Signature.SIG_BOOLEAN);
        assertNoError(1232, Signature.SIG_BOOLEAN);
        assertNoError(1289, Signature.SIG_LONG);
        //assertNoError(1341, TypeConstants.TYPE_STRING);
        assertNoError(1382, TypeConstants.TYPE_STRING);
        // see  https://bugs.eclipse.org/bugs/show_bug.cgi?id=190671
        assertNoError(1422, TypeConstants.TYPE_STRING);
    }

    @Override
    public void testWarningExprs()
    {
        List<ReportedProblem> list = assertSemanticWarning(1496, Signature.SIG_LONG, 1);
        assertContainsProblem(list, DiagnosticFactory.UNARY_OP_STRING_CONVERSION_NOT_GUARANTEED_ID);

        list = assertSemanticWarning(1548, null, 1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);

        // ensure that we are validating that the basename for the missing bundle
        // is being validated since the related variable will not be flagged
        // this check replaces a previous check at 1640 for a variable not
        // found warning. Rather than marking loadBundle variables bad when
        // we can't find the variable, we now mark the basename bad instead
        // of the variable
        // see https://bugs.eclipse.org/bugs/show_bug.cgi?id=190671
        ensureMissingBundleValidation();

        list = assertSemanticWarning(1635, null, 1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_IS_INTERMEDIATE_ID);

        list = assertSemanticWarning(1673, null, 1);
        assertContainsProblem(list, DiagnosticFactory.MEMBER_NOT_FOUND_ID);
    }

    @Override
    public void testErrorExprs()
    {
        // no error
    }


    @SuppressWarnings("unchecked")
    private void ensureMissingBundleValidation()
    {
        final IStructuredDocumentContext context =
            IStructuredDocumentContextFactory.INSTANCE.getContext(_structuredDocument, 839);

        final IDOMContextResolver contextResolver =
            IStructuredDocumentContextResolverFactory.INSTANCE.getDOMContextResolver(context);

        final Attr attr = (Attr) contextResolver.getNode();
        String attributeVal = attr.getValue();
        assertEquals("TestMessages", attributeVal);
        assertEquals(IJSFConstants.ATTR_BASENAME, attr.getLocalName());

        // verify that attribute value validation is picking up on missing bundles
        final List vv =
            MetaDataEnabledProcessingFactory.getInstance()
            .getAttributeValueRuntimeTypeFeatureProcessors
            (IValidValues.class, context, ITLDConstants.URI_JSF_CORE
                    , IJSFConstants.TAG_LOADBUNDLE, IJSFConstants.ATTR_BASENAME);

        boolean validatesMissingBundle = false;

        for (final Iterator it = vv.iterator();it.hasNext();)
        {
            final IValidValues v = (IValidValues)it.next();
            if (attributeVal == null)
            {
                attributeVal = "";//ensure to be non-null
            }
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
