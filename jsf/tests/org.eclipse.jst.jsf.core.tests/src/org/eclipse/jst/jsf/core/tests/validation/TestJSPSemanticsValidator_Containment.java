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
package org.eclipse.jst.jsf.core.tests.validation;


import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.region.Region2ElementAdapter;
import org.eclipse.jst.jsf.core.internal.tld.CMUtil;
import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.jsf.core.tests.TestsPlugin;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.designtime.DTAppManagerUtil;
import org.eclipse.jst.jsf.designtime.internal.view.IDTViewHandler;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.eclipse.jst.jsf.validation.internal.JSFValidationContext;
import org.eclipse.jst.jsf.validation.internal.ValidationPreferences;
import org.eclipse.jst.jsf.validation.internal.IJSFViewValidator.IValidationReporter;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;
import org.eclipse.jst.jsf.validation.internal.strategy.ContainmentValidatingStrategy;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.sse.core.internal.provisional.IndexedRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * White box testing that validation of tag containment skips JSP fragments
 * and only marks the first instance of a tag containment problem
 * 
 * @author cbateman
 *
 */
public class TestJSPSemanticsValidator_Containment extends TestCase
{
    private WebProjectTestEnvironment   _testEnv;
    private IFile                       _jspFile;
    private IFile                       _jspFragmentFile;
    private IStructuredModel            _jspStructuredModel;
    private IStructuredDocument         _jspStructuredDocument;
    private IStructuredModel            _jspFragStructuredModel;
    private IStructuredDocument         _jspFragStructuredDocument;
    private boolean						_containmentValidationEnabled;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        _testEnv = new WebProjectTestEnvironment("ContainmentValidationTest"+this.getClass().getName()+"_"+getName());
        _testEnv.createProject(false);
        assertNotNull(_testEnv);
        assertNotNull(_testEnv.getTestProject());
        assertTrue(_testEnv.getTestProject().isAccessible());

        // load a dummy tld for core
        _testEnv.loadResourceInWebRoot(TestsPlugin.getDefault().getBundle()
                , "/testfiles/jsf-core.tld.data", "META-INF/jsf-core.tld");

        _testEnv.loadResourceInWebRoot(TestsPlugin.getDefault().getBundle()
                , "/testfiles/myfaces_html.tld.data", "META-INF/myfaces_html.tld");

        _jspFile = (IFile)
        _testEnv.loadResourceInWebRoot(TestsPlugin.getDefault().getBundle()
                , "/testfiles/jsps/testContainment.jsp.data", "testContainment.jsp");

        _jspStructuredModel = StructuredModelManager.getModelManager().getModelForRead(_jspFile);
        _jspStructuredDocument = _jspStructuredModel.getStructuredDocument();

        _jspFragmentFile = (IFile)
        _testEnv.loadResourceInWebRoot(TestsPlugin.getDefault().getBundle()
                , "/testfiles/jsps/testContainment.jsp.data", "testContainment.jsf");

        _jspFragStructuredModel = StructuredModelManager.getModelManager().getModelForRead(_jspFile);
        _jspFragStructuredDocument = _jspFragStructuredModel.getStructuredDocument();

        // 	initialize test case for faces 1.1
        final JSFFacetedTestEnvironment jsfFacedEnv = new JSFFacetedTestEnvironment(_testEnv);
        jsfFacedEnv.initialize(IJSFCoreConstants.FACET_VERSION_1_1);
        
        _containmentValidationEnabled = ContainmentValidatingStrategy.isEnabled();
    }

	@Override
    protected void tearDown() throws Exception {
        super.tearDown();

        if (_jspStructuredModel != null)
        {
            _jspStructuredModel.releaseFromRead();
        }

        if (_jspFragStructuredModel != null)
        {
            _jspFragStructuredModel.releaseFromRead();
        }
    }

    public void testContainmentInJSP() throws Exception
    {
        // instantiate the validator once since this is how it would
        // called against a single file.  This is critical to test
        // that only the first instance of improper containment
        // gets flagged.
        final MockValidationReporter reporter = new MockValidationReporter();
        final ContainmentValidatingStrategy validator =
            createContainmentValidator(_jspFile, reporter);

        final IStructuredDocumentContext correctlyNested =
            IStructuredDocumentContextFactory.INSTANCE
                .getContext(_jspStructuredDocument, 487);
        checkTag(correctlyNested, validator, reporter, 0);
        reporter.reset();

        final IStructuredDocumentContext firstIncorrectlyNested =
            IStructuredDocumentContextFactory.INSTANCE
                .getContext(_jspStructuredDocument, 538);
        checkTag(firstIncorrectlyNested, validator, reporter, _containmentValidationEnabled ? 1 : 0);
        reporter.reset();

        final IStructuredDocumentContext secondIncorrectlyNested =
            IStructuredDocumentContextFactory.INSTANCE
                .getContext(_jspStructuredDocument, 568);
        checkTag(secondIncorrectlyNested, validator, reporter, 0);
        reporter.reset();
    }

    public void testContainmentInJSPFrag() throws Exception
    {
        // instantiate the validator once since this is how it would
        // called against a single file.  This is critical to test
        // that only the first instance of improper containment
        // gets flagged.
        final MockValidationReporter reporter = new MockValidationReporter();
        final ContainmentValidatingStrategy validator =
            createContainmentValidator(_jspFragmentFile, reporter);

        { // should all be zero, since no contain validation in jsp frags
            final IStructuredDocumentContext correctlyNested =
                IStructuredDocumentContextFactory.INSTANCE
                .getContext(_jspFragStructuredDocument, 487);
            checkTag(correctlyNested, validator, reporter, 0);
            reporter.reset();
        }

        { // should all be zero, since no contain validation in jsp frags
            final IStructuredDocumentContext firstIncorrectlyNested =
                IStructuredDocumentContextFactory.INSTANCE
                .getContext(_jspFragStructuredDocument, 538);
            checkTag(firstIncorrectlyNested, validator, reporter, 0);
            reporter.reset();
        }
        
        { // should all be zero, since no contain validation in jsp frags
            final IStructuredDocumentContext secondIncorrectlyNested =
                IStructuredDocumentContextFactory.INSTANCE
                .getContext(_jspFragStructuredDocument, 568);
            checkTag(secondIncorrectlyNested, validator, reporter, 0);
            reporter.reset();
        }
    }

    private void checkTag(final IStructuredDocumentContext context
            , final ContainmentValidatingStrategy validator
            , MockValidationReporter reporter
            , final int expectedCount) throws Exception
    {
        final IndexedRegion region = JSFTestUtil.getIndexedRegion
            ((IStructuredDocument) context.getStructuredDocument()
                    , context.getDocumentPosition());

        final IDOMNode domNode = (IDOMNode) region;
        final Node node = (Node) region;
        assertTrue(node instanceof Element);
        final Element elem = (Element) node;
        assertEquals(IJSFConstants.TAG_INPUTTEXT, elem.getLocalName());
        System.out.println(CMUtil.getElementNamespaceURI(elem));

        validator.validate(
                new Region2ElementAdapter(domNode.getFirstStructuredDocumentRegion()));

        assertEquals(expectedCount, reporter.getReportedProblems().size());
    }
    
    private ContainmentValidatingStrategy createContainmentValidator(final IFile file, 
            IValidationReporter reporter)
    {
        final ValidationPreferences prefs = new ValidationPreferences(
                JSFCorePlugin.getDefault().getPreferenceStore());
        prefs.load();
        final DiagnosticFactory diagnosticFactory = new DiagnosticFactory();
    
        final IDTViewHandler viewHandler = DTAppManagerUtil
                .getViewHandler(file.getProject());
    
        final JSFValidationContext validationContext = 
            new JSFValidationContext(
                false, prefs, viewHandler,
                diagnosticFactory, file, reporter,
                null);
        
        return new ContainmentValidatingStrategy(validationContext);
    }
}
