/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.test.util.sanity;

import junit.framework.TestCase;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.jsf.test.util.Activator;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.ProjectTestEnvironment;
import org.eclipse.jst.jsf.test.util.TestFileResource;

/**
 * @author cbateman
 *
 */
public class TestJDTTestEnvironment extends TestCase 
{
	private  ProjectTestEnvironment  		_projectTestEnvironment;
    private  JDTTestEnvironment             _jdtTestEnv;
	private  String 			     		_testClass1;
	
	protected void setUp() throws Exception {
		super.setUp();
		_projectTestEnvironment = new ProjectTestEnvironment("JDTTestProject");
		_projectTestEnvironment.createProject();
        _jdtTestEnv = new JDTTestEnvironment(_projectTestEnvironment);
        TestFileResource codeRes = new TestFileResource();
        codeRes.load(Activator.getDefault().getBundle(), "/testdata/TestClass1.java.data");
        _testClass1 = codeRes.toString();
	}

	/**
	 * Test basic java class file creation
	 */
	public void testCreateJavaClassFile()
	{
		ICompilationUnit compUnit = null;
		try
		{
			compUnit =
                _jdtTestEnv.
					addSourceFile("src", "com.test", "TestClass1", _testClass1.toString());

		}
		catch (Exception e)
		{
			fail(e.getLocalizedMessage());
		}
		
		assertNotNull(compUnit);
        assertTrue(compUnit.exists());
		assertTrue(compUnit.getResource().isAccessible());
        
        try
        {
            IType type = _jdtTestEnv.getJavaProject().findType("com.test.TestClass1");
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
