package org.eclipse.jst.jsf.contentassist.tests;

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.jsf.context.symbol.IBeanInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.IBeanMethodSymbol;
import org.eclipse.jst.jsf.context.symbol.IBeanPropertySymbol;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.internal.contentassist.el.ContentAssistParser;
import org.eclipse.jst.jsf.core.internal.contentassist.el.SymbolInfo;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.TestFileResource;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;

public class TestContentAssistParser extends BaseTestClass
{
	private WebProjectTestEnvironment _testEnv;
	private IFile _jspFile;
	private IType _myBeanType;

	@Override
	protected void setUp() throws Exception
	{
        super.setUp();

        JSFTestUtil.setValidationEnabled(false);

        _testEnv = new WebProjectTestEnvironment(getClass().getName()+"_"+getName());
        _testEnv.createProject(false);
        assertNotNull(_testEnv);
        assertNotNull(_testEnv.getTestProject());
        assertTrue(_testEnv.getTestProject().isAccessible());

        final JSFFacetedTestEnvironment jsfFacedEnv = new JSFFacetedTestEnvironment(_testEnv);
        jsfFacedEnv.initialize(IJSFCoreConstants.FACET_VERSION_1_1);

        _testEnv.loadResourceInWebRoot(ContentAssistTestsPlugin.getDefault().getBundle(),
                                      "/testdata/faces-config_basic.xml.data",
                                      "/WEB-INF/faces-config.xml");
        _jspFile = (IFile) _testEnv.loadResourceInWebRoot(ContentAssistTestsPlugin.getDefault().getBundle(),
                                      "/testdata/basicELExpressions.jsp.data",
                                      "/basicELExpressions.jsp");
        assertNotNull(_jspFile);
        assertTrue(_jspFile.isAccessible());

        final JDTTestEnvironment jdtTestEnv = new JDTTestEnvironment(_testEnv);
        final TestFileResource resource = new TestFileResource();
        resource.load(ContentAssistTestsPlugin.getDefault().getBundle(),
                      "/testdata/MyBean.java.data");
        jdtTestEnv.addSourceFile("src", "beans", "MyBean", resource.toString());
        _myBeanType = JavaCore.create(_testEnv.getTestProject()).findType("beans.MyBean");
        assertNotNull(_myBeanType);
        assertTrue(_myBeanType.exists());
    }

	public void testSanity() throws Exception
	{
		assertELSanity(_jspFile, 518, "value", "#{}");
		assertELSanity(_jspFile, 547, "value", "#{   }");
		assertELSanity(_jspFile, 579, "value", "#{myBean}");
		assertELSanity(_jspFile, 614, "value", "#{myBean.property}");
		assertELSanity(_jspFile, 658, "value", "#{paramValues.foo}");
		assertELSanity(_jspFile, 706, "action", "#{myBean.actionMethod}");

		assertELVariableSanity(_jspFile, "myBean");
	}


	public void testGetPrefix() {
		// for now there's a enough coverage through TestIdCompletionStrategy
	}

	public void testGetSymbolInfo() throws Exception
	{
		assertNull(ContentAssistParser.getSymbolInfo(getDocumentContext(_jspFile, 518).getContext(), 1, null));
		assertNull(ContentAssistParser.getSymbolInfo(getDocumentContext(_jspFile, 518).getContext(), 1, ""));
		assertNull(ContentAssistParser.getSymbolInfo(getDocumentContext(_jspFile, 547).getContext(), 1, "   "));

		// variable test
		SymbolInfo symbolInfo =
			ContentAssistParser.getSymbolInfo(getDocumentContext(_jspFile, 579).getContext(), 1, "myBean");
		assertNotNull(symbolInfo);
		assertEquals("myBean", symbolInfo.getSymbol().getName());
		assertTrue(symbolInfo.getSymbol() instanceof IBeanInstanceSymbol);
		assertNotNull(symbolInfo.getRelativeRegion());
		assertEquals(6, symbolInfo.getRelativeRegion().getLength());

		// property test
		symbolInfo =
			ContentAssistParser.getSymbolInfo(getDocumentContext(_jspFile, 614).getContext(), 8, "myBean.property");
		assertNotNull(symbolInfo);
		assertEquals("property", symbolInfo.getSymbol().getName());
		assertTrue(symbolInfo.getSymbol() instanceof IBeanPropertySymbol);
		assertNotNull(symbolInfo.getRelativeRegion());

		// method test
		symbolInfo =
			ContentAssistParser.getSymbolInfo(getDocumentContext(_jspFile, 706).getContext(), 8, "myBean.actionMethod");
		assertNotNull(symbolInfo);
		assertEquals("actionMethod", symbolInfo.getSymbol().getName());
		assertTrue(symbolInfo.getSymbol() instanceof IBeanMethodSymbol);
		assertNotNull(symbolInfo.getRelativeRegion());
	}

}
