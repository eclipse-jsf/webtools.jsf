package org.eclipse.jst.jsf.designtime.tests;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;
import org.eclipse.jst.jsf.context.symbol.IInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.context.symbol.source.ISymbolConstants;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.designtime.internal.jsp.JSPDefaultSymbolFactory;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.TestFileResource;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;

public class TestJSPDefaultSymbolFactory extends TestCase 
{
	private IFile 						_testJSP;
	private JSFFacetedTestEnvironment 	_jsfFactedTestEnvironment;
	private IStructuredModel 			_structuredModel;
	private IStructuredDocument 		_structuredDocument;

	@Override
    protected void setUp() throws Exception 
	{
        super.setUp();
        JSFTestUtil.setValidationEnabled(false);
        JSFTestUtil.setInternetProxyPreferences(true, "www-proxy.us.oracle.com","80");

        final WebProjectTestEnvironment  projectTestEnvironment = 
            new WebProjectTestEnvironment("TestJSPDefaultSymbolFactory_"+getName());
        projectTestEnvironment.createProject(false);

        final JDTTestEnvironment jdtTestEnvironment = 
        	new JDTTestEnvironment(projectTestEnvironment);

        final TestFileResource input = new TestFileResource();
        input.load(DesignTimeTestsPlugin.getDefault().getBundle(), 
        		"/testdata/bundle1.resources.data");
        jdtTestEnvironment.addResourceFile("src"
        		, new ByteArrayInputStream(input.toBytes())
        		, "bundles", "bundle1.properties");
        
        final IResource res = 
        	projectTestEnvironment.loadResourceInWebRoot(DesignTimeTestsPlugin.getDefault().getBundle()
        		, "/testdata/testdata1.jsp.data", "testdata1.jsp");
        _testJSP = (IFile) res;

        _jsfFactedTestEnvironment = new JSFFacetedTestEnvironment(projectTestEnvironment);
        _jsfFactedTestEnvironment.initialize(IJSFCoreConstants.FACET_VERSION_1_1);
        
        _structuredModel = StructuredModelManager.getModelManager().getModelForRead(_testJSP);
        _structuredDocument = _structuredModel.getStructuredDocument();

    }

	@Override
    protected void tearDown() throws Exception 
	{
		super.tearDown();
		_structuredModel.releaseFromRead();
	}

	public void testSupports()
	{
		final JSPDefaultSymbolFactory factory = new JSPDefaultSymbolFactory();

		// must be a structured document context
		assertFalse(factory.supports(_testJSP));

		final IStructuredDocumentContext context =
			IStructuredDocumentContextFactory.INSTANCE
				.getContext(_structuredDocument, 0);

		// must work for a JSP document context
		assertTrue(factory.supports(context));
	}

	@SuppressWarnings("unchecked")
    public void testCreate()
	{
		final JSPDefaultSymbolFactory factory = new JSPDefaultSymbolFactory();

		final IStructuredDocumentContext context =
			IStructuredDocumentContextFactory.INSTANCE
				.getContext(_structuredDocument, 0);

		ISymbol var =
			factory.create("notAVariable"
				, ISymbolConstants.SYMBOL_SCOPE_REQUEST, context, new ArrayList(), null);
		assertNull(var);

		var = factory.create("bundle"
			, ISymbolConstants.SYMBOL_SCOPE_REQUEST
			, IStructuredDocumentContextFactory.INSTANCE.getContext(_structuredDocument, 552)
			, new ArrayList(), null);
		
		// load bundle introduces a variable
		assertNotNull(var);
		assertTrue(var instanceof IInstanceSymbol);
		assertEquals("bundle", var.getName());
		
		var = factory.create("row"
				, ISymbolConstants.SYMBOL_SCOPE_REQUEST
				, IStructuredDocumentContextFactory.INSTANCE.getContext(_structuredDocument, 1361)
				, new ArrayList(), null);
		
		// data table introduces a variable
		assertNotNull(var);
		assertTrue(var instanceof IInstanceSymbol);
		assertEquals("row", var.getName());
	}
}
