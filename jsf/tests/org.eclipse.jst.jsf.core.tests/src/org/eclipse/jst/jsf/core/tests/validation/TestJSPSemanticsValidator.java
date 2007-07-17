package org.eclipse.jst.jsf.core.tests.validation;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IDOMContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.internal.tld.CMUtil;
import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.jsf.core.tests.TestsPlugin;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.eclipse.jst.jsf.validation.internal.IJSPSemanticValidatorTest;
import org.eclipse.jst.jsf.validation.internal.JSPSemanticsValidator;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.eclipse.wst.validation.internal.provisional.core.IReporter;
import org.eclipse.wst.validation.internal.provisional.core.IValidator;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * White box testing that validation of tag containment skips JSP fragments 
 * and only marks the first instance of a tag containment problem
 * 
 * @author cbateman
 *
 */
public class TestJSPSemanticsValidator extends TestCase 
{
	private WebProjectTestEnvironment 	_testEnv;
	private IFile					  	_jspFile;
	private IFile					  	_jspFragmentFile;
	private IStructuredModel 			_jspStructuredModel;
	private IStructuredDocument 		_jspStructuredDocument;
	private IStructuredModel 			_jspFragStructuredModel;
	private IStructuredDocument 		_jspFragStructuredDocument;

	
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
        JSFFacetedTestEnvironment jsfFacedEnv = new JSFFacetedTestEnvironment(_testEnv);
        jsfFacedEnv.initialize(IJSFCoreConstants.FACET_VERSION_1_1);
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

	public void testContainmentInJSP()
	{
		IDOMContextResolver  resolver = null;

		// instantiate the validator once since this is how it would
		// called against a single file.  This is critical to test
		// that only the first instance of improper containment 
		// gets flagged.
		final IJSPSemanticValidatorTest validator = 
			new JSPSemanticsValidator().getTestInterface();

		IStructuredDocumentContext correctlyNested =
			IStructuredDocumentContextFactory.INSTANCE
				.getContext(_jspStructuredDocument, 487);

		IStructuredDocumentContext firstIncorrectlyNested =
			IStructuredDocumentContextFactory.INSTANCE
				.getContext(_jspStructuredDocument, 538);
		
		IStructuredDocumentContext secondIncorrectlyNested =
			IStructuredDocumentContextFactory.INSTANCE
				.getContext(_jspStructuredDocument, 568);
		checkTag(correctlyNested, validator, _jspFile, 0);
		checkTag(firstIncorrectlyNested, validator, _jspFile, 1);
		checkTag(secondIncorrectlyNested, validator, _jspFile, 0);
	}
	
	public void testContainmentInJSPFrag()
	{
		IDOMContextResolver  resolver = null;

		// instantiate the validator once since this is how it would
		// called against a single file.  This is critical to test
		// that only the first instance of improper containment 
		// gets flagged.
		final IJSPSemanticValidatorTest validator = 
			new JSPSemanticsValidator().getTestInterface();

		IStructuredDocumentContext correctlyNested =
			IStructuredDocumentContextFactory.INSTANCE
				.getContext(_jspFragStructuredDocument, 487);

		IStructuredDocumentContext firstIncorrectlyNested =
			IStructuredDocumentContextFactory.INSTANCE
				.getContext(_jspFragStructuredDocument, 538);
		
		IStructuredDocumentContext secondIncorrectlyNested =
			IStructuredDocumentContextFactory.INSTANCE
				.getContext(_jspFragStructuredDocument, 568);
		
		// should all be zero, since no contain validation in jsp frags
		checkTag(correctlyNested, validator, _jspFragmentFile, 0);
		checkTag(firstIncorrectlyNested, validator, _jspFragmentFile, 0);
		checkTag(secondIncorrectlyNested, validator, _jspFragmentFile, 0);
	}
	
	private void checkTag(IStructuredDocumentContext context
						, IJSPSemanticValidatorTest validator
						, IFile file
						, int expectedCount)
	{
		IDOMContextResolver resolver = 
			IStructuredDocumentContextResolverFactory.INSTANCE
				.getDOMContextResolver(context);
		
		Node node = resolver.getNode();
		assertTrue(node instanceof Element);
		Element elem = (Element) node;
		assertEquals(IJSFConstants.TAG_INPUTTEXT, elem.getLocalName());
		System.out.println(CMUtil.getElementNamespaceURI(elem));

		MyReporter reporter = new MyReporter();
		validator.validateContainment(elem, ITLDConstants.URI_JSF_HTML
				, IJSFConstants.TAG_INPUTTEXT, reporter, file, context);

		assertEquals(expectedCount, reporter.getMessages().size());
	}
	
	private class MyReporter implements IReporter
	{
		List<IMessage>   messages = new ArrayList<IMessage>();
		
		public void addMessage(IValidator origin, IMessage message) 
		{
			messages.add(message);
		}

		public void displaySubtask(IValidator validator, IMessage message) {
			throw new UnsupportedOperationException("This reporter is for specific test purposes only");
		}

		public List getMessages() {
			return messages;
		}

		public boolean isCancelled() {
			// do nothing; unused.
			return false;
		}

		public void removeAllMessages(IValidator origin) 
		{
			throw new UnsupportedOperationException("This reporter is for specific test purposes only");
		}

		public void removeAllMessages(IValidator origin, Object object) 
		{
			throw new UnsupportedOperationException("This reporter is for specific test purposes only");
		}

		public void removeMessageSubset(IValidator validator, Object obj,
				String groupName) 
		{
			throw new UnsupportedOperationException("This reporter is for specific test purposes only");
		}
		
	}
}
