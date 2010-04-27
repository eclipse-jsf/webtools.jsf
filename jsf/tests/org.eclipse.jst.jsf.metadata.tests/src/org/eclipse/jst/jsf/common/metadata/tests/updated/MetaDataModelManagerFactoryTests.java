/*******************************************************************************
 * Copyright (c) 2010 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle Corporation - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.jst.jsf.common.metadata.tests.updated;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataModelManager;
import org.eclipse.jst.jsf.common.metadata.internal.MetaDataModelManagerFactory;
import org.eclipse.jst.jsf.test.util.mock.MockWorkspaceContext;

public class MetaDataModelManagerFactoryTests extends TestCase {
	
	private IProject _project;
	
	public void setUp() throws Exception {
		MockWorkspaceContext context = new MockWorkspaceContext();
		_project = context.createProject("MetaDataModelManagerFactoryTests"+"_"+getName());
	}

	public void testDefaultMDModelManagerSetup() {
		//test extension must be removed for this to work
		IMetaDataModelManager mgr = MetaDataModelManagerFactory.getMetaDataModelManagerInstance(_project);
		assertNotNull(mgr);
		assertEquals("org.eclipse.jst.jsf.common.metadata.internal.MetaDataModelManager", mgr.getClass().getName());
		
		//verify getting same instance when called the second time
		IMetaDataModelManager mgr2 = MetaDataModelManagerFactory.getMetaDataModelManagerInstance(_project);
		assertNotNull(mgr2);		
		assertEquals(mgr, mgr2);
		
	}
	
//// test extension disabled becuz it screws up tests	
//	public void testExtPtMDModelManagerSetup() {
//		IMetaDataModelManager mgr = MetaDataModelManagerFactory.getMetaDataModelManagerInstance(projectTestEnvironment.getTestProject());
//		assertNotNull(mgr);
//		assertEquals("TestMDModelManager", mgr.getClass().getSimpleName());;
//	}
	
	public void testTestableMDModelManagerSetup() throws Exception {
		_project.setSessionProperty(MetaDataModelManagerFactory.TESTABLE_FACTORY_SESSION_KEY, new TestMDModelManagerFactory());
		IMetaDataModelManager mgr = MetaDataModelManagerFactory.getMetaDataModelManagerInstance(_project);
		assertNotNull(mgr);
		assertEquals("TestMDModelManager", mgr.getClass().getSimpleName());
		
		IMetaDataModelManager mgr2 = MetaDataModelManagerFactory.getMetaDataModelManagerInstance(_project);
		assertNotNull(mgr2);
		assertEquals(mgr, mgr2);
	}
}
