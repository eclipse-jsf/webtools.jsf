package org.eclipse.jst.jsf.contentassist.tests;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.TestFileResource;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;

public class TestIdCompletionStrategy extends BaseTestClass
{
	private final static Set<String>   DISPLAY_NAMES;

	static
	{
		final Set<String>  displayNames = new HashSet<String>();
		displayNames.add("requestScope");
		displayNames.add("cookie");
		displayNames.add("header");
		displayNames.add("headerValues");
		displayNames.add("param");
		displayNames.add("paramValues");
		displayNames.add("facesContext");
		displayNames.add("view");
		displayNames.add("initParam");
		displayNames.add("sessionScope");
		displayNames.add("applicationScope");
		displayNames.add("myBean");
		DISPLAY_NAMES = Collections.unmodifiableSet(displayNames);
	}
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

		assertEquals(12, DISPLAY_NAMES.size());
	}

	public void testGetProposals() {

	}

	public void testIdCompletionStrategy() throws Exception
	{
		// empty string has all
		assertDisplayNamesMatch(DISPLAY_NAMES, getProposals(_jspFile, 518, "", 1));

		// empty whitespace has all
		assertDisplayNamesMatch(DISPLAY_NAMES, getProposals(_jspFile, 547, 1));

		// at the very start of a variable, have all
		assertDisplayNamesMatch(DISPLAY_NAMES, getProposals(_jspFile, 579, 1));
		// one character in, only ones matching prefix
		assertDisplayNamesMatch(Collections.singleton("myBean"), getProposals(_jspFile, 579, 2));

		// having properties should not change the variable resolved
		assertDisplayNamesMatch(DISPLAY_NAMES, getProposals(_jspFile, 614, 1));
		// one character in, only ones matching prefix
		assertDisplayNamesMatch(Collections.singleton("myBean"), getProposals(_jspFile, 614, 2));

		// having properties should not change the variable resolved
		assertDisplayNamesMatch(DISPLAY_NAMES, getProposals(_jspFile, 658, 1));
		// one character in, only ones matching prefix
		final Set<String> paramNames = new HashSet<String>();
		paramNames.add("param");
		paramNames.add("paramValues");
		assertDisplayNamesMatch(Collections.unmodifiableSet(paramNames), getProposals(_jspFile, 658, 2));
	}
}
