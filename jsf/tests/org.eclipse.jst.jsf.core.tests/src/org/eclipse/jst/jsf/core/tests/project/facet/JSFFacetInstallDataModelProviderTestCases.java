/*******************************************************************************
 * Copyright (c) 2005 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.jsf.core.tests.project.facet;

import java.util.Set;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.internal.project.facet.IJSFFacetInstallDataModelProperties;
import org.eclipse.jst.jsf.core.internal.project.facet.JSFFacetInstallDataModelProvider;
import org.eclipse.jst.jsf.core.tests.util.JSFCoreUtilHelper;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class JSFFacetInstallDataModelProviderTestCases extends TestCase {
	private static final String PROJ_2_3_NAME = "_TEST_2_3_PROJECT";
	//private static final String PROJ_2_4_NAME = "_TEST_2_4_PROJECT";
	
	private JSFFacetInstallDataModelProvider dm;
	private JSFLibrary jsfLib;
	
	protected void setUp() throws Exception {		
		//seed JSFLib registry if not present
		JSFCoreUtilHelper.createJSFLibraryRegistry();
		//create a project, if one doesn't exist in the current workspace
		IProject project = JSFCoreUtilHelper.createWebProject(PROJ_2_3_NAME);
		//create lib
		jsfLib = JSFCorePlugin.getDefault().getJSFLibraryRegistry().getDefaultImplementation();
		
		dm = new JSFFacetInstallDataModelProvider();
		IDataModel model = DataModelFactory.createDataModel(dm);	
		model.setStringProperty(IJSFFacetInstallDataModelProperties.FACET_PROJECT_NAME, project.getName());
	}

	/*
	 * Test method for 'org.eclipse.jst.jsf.core.internal.project.facet.JSFFacetInstallDataModelProvider.getPropertyNames()'
	 */
	public void testGetPropertyNames() {	
		Set names = dm.getPropertyNames();
		Assert.assertNotNull(names);
		Assert.assertTrue(names.contains(IJSFFacetInstallDataModelProperties.IMPLEMENTATION));
		Assert.assertTrue(names.contains(IJSFFacetInstallDataModelProperties.DEPLOY_IMPLEMENTATION));
		Assert.assertTrue(names.contains(IJSFFacetInstallDataModelProperties.CONFIG_PATH));
		Assert.assertTrue(names.contains(IJSFFacetInstallDataModelProperties.SERVLET_NAME));
		Assert.assertTrue(names.contains(IJSFFacetInstallDataModelProperties.SERVLET_URL_PATTERNS));
		Assert.assertTrue(names.contains(IJSFFacetInstallDataModelProperties.WEBCONTENT_DIR));
	}

	/*
	 * Test method for 'org.eclipse.jst.jsf.core.internal.project.facet.JSFFacetInstallDataModelProvider.getDefaultProperty(String)'
	 */
	public void testGetDefaultPropertyString() {
		Assert.assertTrue(dm.getDefaultProperty(IJSFFacetInstallDataModelProperties.IMPLEMENTATION) == jsfLib);
		Assert.assertTrue(dm.getDefaultProperty(IJSFFacetInstallDataModelProperties.DEPLOY_IMPLEMENTATION) == Boolean.TRUE);
		Assert.assertTrue(dm.getDefaultProperty(IJSFFacetInstallDataModelProperties.CONFIG_PATH) != null);
		Assert.assertTrue(dm.getDefaultProperty(IJSFFacetInstallDataModelProperties.SERVLET_NAME) != null);
		Assert.assertTrue(dm.getDefaultProperty(IJSFFacetInstallDataModelProperties.SERVLET_URL_PATTERNS) != null);
		Assert.assertTrue(dm.getDefaultProperty(IJSFFacetInstallDataModelProperties.WEBCONTENT_DIR) != null);
	}

	/*
	 * Test method for 'org.eclipse.jst.jsf.core.internal.project.facet.JSFFacetInstallDataModelProvider.validate(String)'

	public void testValidateString() {
		//positive tests		
		IDataModel model = dm.getDataModel();
		
		model.setStringProperty(IJSFFacetInstallDataModelProperties.CONFIG_PATH, (String)dm.getDefaultProperty(IJSFFacetInstallDataModelProperties.CONFIG_PATH));
		model.setProperty(IJSFFacetInstallDataModelProperties.IMPLEMENTATION, jsfLib);
		model.setStringProperty(IJSFFacetInstallDataModelProperties.SERVLET_NAME, (String)dm.getDefaultProperty(IJSFFacetInstallDataModelProperties.SERVLET_NAME));
		
		Assert.assertTrue(dm.validate(IJSFFacetInstallDataModelProperties.CONFIG_PATH).isOK());
		Assert.assertTrue(dm.validate(IJSFFacetInstallDataModelProperties.IMPLEMENTATION).isOK());
		Assert.assertTrue(dm.validate(IJSFFacetInstallDataModelProperties.SERVLET_NAME).isOK());
		
		model.setStringProperty(IJSFFacetInstallDataModelProperties.CONFIG_PATH, "foo.xml");
		Assert.assertTrue(dm.validate(IJSFFacetInstallDataModelProperties.CONFIG_PATH).isOK());
		//negative tests
		//config path
		model.setStringProperty(IJSFFacetInstallDataModelProperties.CONFIG_PATH, "");
		Assert.assertFalse(dm.validate(IJSFFacetInstallDataModelProperties.CONFIG_PATH).isOK());

		model.setStringProperty(IJSFFacetInstallDataModelProperties.CONFIG_PATH, "../../../foo.xml");
		Assert.assertFalse(dm.validate(IJSFFacetInstallDataModelProperties.CONFIG_PATH).isOK());
		
		model.setStringProperty(IJSFFacetInstallDataModelProperties.CONFIG_PATH, "WEB-INF/xxx.txt");
		Assert.assertFalse(dm.validate(IJSFFacetInstallDataModelProperties.CONFIG_PATH).isOK());
		
		String pathWithDevice = new Path(Platform.getLocation().getDevice(), "/temp/faces-config.xml").toOSString();
		model.setStringProperty(IJSFFacetInstallDataModelProperties.CONFIG_PATH, pathWithDevice );
		Assert.assertFalse(dm.validate(IJSFFacetInstallDataModelProperties.CONFIG_PATH).isOK());

		model.setStringProperty(IJSFFacetInstallDataModelProperties.CONFIG_PATH, "\\WEB-INF\\xxx.txt");
		Assert.assertFalse(dm.validate(IJSFFacetInstallDataModelProperties.CONFIG_PATH).isOK());

		//impl
		model.setProperty(IJSFFacetInstallDataModelProperties.IMPLEMENTATION, null);				
		Assert.assertFalse(dm.validate(IJSFFacetInstallDataModelProperties.CONFIG_PATH).isOK());
		
		//servlet name		
		//can't check null name because null set returns default value
		model.setStringProperty(IJSFFacetInstallDataModelProperties.SERVLET_NAME, "   ");
		Assert.assertFalse(dm.validate(IJSFFacetInstallDataModelProperties.SERVLET_NAME).isOK());

	}
	 */
}
