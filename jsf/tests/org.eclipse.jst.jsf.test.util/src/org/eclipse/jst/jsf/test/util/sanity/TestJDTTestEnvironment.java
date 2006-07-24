package org.eclipse.jst.jsf.test.util.sanity;

import junit.framework.TestCase;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.jsf.test.util.Activator;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.TestFileResource;
import org.eclipse.jst.jsf.test.util.ProjectTestEnvironment;

/**
 * @author cbateman
 *
 */
public class TestJDTTestEnvironment extends TestCase 
{
	private  ProjectTestEnvironment  		_projectTestEnvironment;
	private  String 			     		_testClass1;
	
	protected void setUp() throws Exception {
		super.setUp();
		_projectTestEnvironment = new ProjectTestEnvironment("JDTTestProject");
		_projectTestEnvironment.createProject();
        TestFileResource codeRes = new TestFileResource();
        codeRes.load(Activator.getDefault().getBundle(), "/testdata/TestClass1.java.data");
        _testClass1 = codeRes.toString();
	}

	/**
	 * Test basic java class file creation
	 */
	public void testCreateJavaClassFile()
	{
		JDTTestEnvironment  jdtTestEnvironment = new JDTTestEnvironment(_projectTestEnvironment);
		
		ICompilationUnit compUnit = null;
		try
		{
			compUnit =
				jdtTestEnvironment.
					addSourceFile("src", "com.test", "TestClass1", _testClass1.toString());

		}
		catch (Exception e)
		{
			fail(e.getLocalizedMessage());
		}
		
		assertNotNull(compUnit);
		assertTrue(compUnit.getResource().isAccessible());
        
        try
        {
            IType type = jdtTestEnvironment.getJavaProject().findType("com.test.TestClass1");
            assertNotNull(type);
            assertTrue(type.getMethods().length == 1);
            assertTrue(type.getMethods()[0].getElementName().equals("amethod"));
        }
        catch (JavaModelException jme)
        {
            fail(jme.getLocalizedMessage());
        }
	}
}
