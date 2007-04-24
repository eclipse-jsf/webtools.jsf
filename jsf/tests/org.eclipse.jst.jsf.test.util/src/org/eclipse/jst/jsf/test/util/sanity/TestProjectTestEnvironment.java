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

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.test.util.ProjectTestEnvironment;

/**
 * Test the base Project Test Environment
 * 
 * @author cbateman
 *
 */
public class TestProjectTestEnvironment extends TestCase 
{
	/**
	 * Test creating a basic test project environment
	 */
	public void testCreateProject()
	{
		ProjectTestEnvironment testEnv = new ProjectTestEnvironment("TestProject1");
		testEnv.createProject(false);
		assertTrue(testEnv.isProjectCreated());
		
		IProject project = testEnv.getTestProject();
		
		assertNotNull(project);
		assertTrue(project.isAccessible());
	}
}
