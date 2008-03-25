package org.eclipse.jst.jsf.ui.tests.jspeditor;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jst.jsf.contentassist.tests.ContentAssistTestsPlugin;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.tests.util.JSFCoreUtilHelper;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.core.tests.util.JSFCoreUtilHelper.ContextWrapper;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.TestFileResource;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.eclipse.jst.jsf.ui.internal.jspeditor.JSFELHover;

public class TestJSFELHover extends TestCase {

	private WebProjectTestEnvironment _testEnv;
	private IFile _jspFile;
	private IType _myBeanType;

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		JSFTestUtil.setValidationEnabled(false);

		_testEnv = new WebProjectTestEnvironment(getClass().getName() + "_"
				+ getName());
		_testEnv.createProject(false);
		assertNotNull(_testEnv);
		assertNotNull(_testEnv.getTestProject());
		assertTrue(_testEnv.getTestProject().isAccessible());

		final JSFFacetedTestEnvironment jsfFacedEnv = new JSFFacetedTestEnvironment(
				_testEnv);
		jsfFacedEnv.initialize(IJSFCoreConstants.FACET_VERSION_1_1);

		_testEnv.loadResourceInWebRoot(ContentAssistTestsPlugin.getDefault()
				.getBundle(), "/testdata/faces-config_basic.xml.data",
				"/WEB-INF/faces-config.xml");
		_jspFile = (IFile) _testEnv.loadResourceInWebRoot(
				ContentAssistTestsPlugin.getDefault().getBundle(),
				"/testdata/basicELExpressions.jsp.data",
				"/basicELExpressions.jsp");
		assertNotNull(_jspFile);
		assertTrue(_jspFile.isAccessible());

		final JDTTestEnvironment jdtTestEnv = new JDTTestEnvironment(_testEnv);
		final TestFileResource resource = new TestFileResource();
		resource.load(ContentAssistTestsPlugin.getDefault().getBundle(),
				"/testdata/MyBean.java.data");
		jdtTestEnv.addSourceFile("src", "beans", "MyBean", resource.toString());
		_myBeanType = JavaCore.create(_testEnv.getTestProject()).findType(
				"beans.MyBean");
		assertNotNull(_myBeanType);
		assertTrue(_myBeanType.exists());
	}

	public void testSanity() throws Exception {
		JSFCoreUtilHelper.assertELSanity(_jspFile, 579, "value", "#{myBean}");
		JSFCoreUtilHelper.assertELSanity(_jspFile, 614, "value",
				"#{myBean.property}");
		JSFCoreUtilHelper.assertELSanity(_jspFile, 706, "action",
				"#{myBean.actionMethod}");

		JSFCoreUtilHelper.assertELVariableSanity(_jspFile, "myBean");
	}

	public void testGetHoverInfo() throws Exception {
		// hyperlink from MyBean
		testJavaHoverHelp(579, "MyBean.java", "<p><b>Name: </b>myBean</p><p><b>Type: </b>beans.MyBean</p><p><b>Scope: </b>none</p>");
		// hyperlink from MyBean
		testJavaHoverHelp(614, "MyBean.java", "<p><b>Name: </b>myBean</p><p><b>Type: </b>beans.MyBean</p><p><b>Scope: </b>none</p>");
		// hyperlink from MyBean
		testJavaHoverHelp(706, "MyBean.java", "<p><b>Name: </b>myBean</p><p><b>Type: </b>beans.MyBean</p><p><b>Scope: </b>none</p>");

		// hyperlink from property
		testJavaHoverHelp(622, "MyBean.java", "<p><b>Type: </b>java.lang.String[]</p><p><b>Access: </b>read-only</p>");

		// hyperlink from method
		testJavaHoverHelp(714, "MyBean.java", "<p><b>Signature:</b> String actionMethod()</p>");
	}

	private void testJavaHoverHelp(final int regionStartOffset,
			final String classFileName, final String expectedInfo)
			throws Exception {
		final TestableJSFELHover hoverHelp = new TestableJSFELHover();
		final ContextWrapper context = JSFCoreUtilHelper.getDocumentContext(
				_jspFile, regionStartOffset);
		final IRegion region = hoverHelp.getHoverRegion(context.getContext(),
				regionStartOffset);
		assertNotNull(region);
		assertEquals(expectedInfo, hoverHelp.getHoverInfo());
	}

	private static class TestableJSFELHover extends JSFELHover {

		@Override
		public String getHoverInfo() {
			return super.getHoverInfo();
		}

		@Override
		public IRegion getHoverRegion(final IStructuredDocumentContext context,
				final int documentPosition) {
			return super.getHoverRegion(context, documentPosition);
		}
	}
}
