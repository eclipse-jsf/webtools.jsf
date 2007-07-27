/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.core.tests.jsflibraryconfiguration;

import java.util.Collection;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryRegistryUtil;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistry;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.PluginProvidedJSFLibrary;
import org.eclipse.jst.jsf.core.jsflibraryconfiguration.JSFLibraryConfigurationHelper;
import org.eclipse.jst.jsf.core.jsflibraryconfiguration.JSFLibraryReference;
import org.eclipse.jst.jsf.core.jsflibraryconfiguration.JSFLibraryReferenceServerSupplied;
import org.eclipse.jst.jsf.core.jsflibraryconfiguration.JSFVersion;
import org.eclipse.jst.jsf.core.jsflibraryconfiguration.internal.JSFLibraryReferenceFacadeFactory;
import org.eclipse.jst.jsf.core.tests.util.JSFCoreUtilHelper;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

public class JSFLibraryServerSuppliedReferenceTestCases extends TestCase {
	WebProjectTestEnvironment projectTestEnvironment;
	JDTTestEnvironment jdtTestEnv;
	
	protected void setUp() throws Exception {
		super.setUp();
		
        JSFTestUtil.setValidationEnabled(false);
        JSFTestUtil.setInternetProxyPreferences(true, "www-proxy.us.oracle.com", "80");
        
        projectTestEnvironment = 
            new WebProjectTestEnvironment("JSFLibraryServerSuppliedReferenceTestCases", ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_JAVA ).getVersion( "5.0" )
                    , ProjectFacetsManager.getProjectFacet( "jst.web" ).getVersion("2.5"));
        boolean created = projectTestEnvironment.createProject(true);

        assertNotNull(projectTestEnvironment);       
        assertNotNull(projectTestEnvironment.getTestProject());
        assertTrue(projectTestEnvironment.getTestProject().isAccessible());
        
        if (created) {
	        // initialize test case for faces 1.2
	        JSFFacetedTestEnvironment jsfFacedEnv = new JSFFacetedTestEnvironment(projectTestEnvironment);
	        jsfFacedEnv.initialize(IJSFCoreConstants.FACET_VERSION_1_2);
	        
	        createRegistryAndAddreferences(projectTestEnvironment, jsfFacedEnv);
        }
	}

	private void createRegistryAndAddreferences(WebProjectTestEnvironment projectTestEnvironment, JSFFacetedTestEnvironment jsfFacedEnv) throws CoreException {
		JSFLibraryRegistry jsfLibRegistry = JSFLibraryRegistryUtil.getInstance().getJSFLibraryRegistry();

		String[] archivefiles1 = {
				"faces-all-bogu.jar",
				"faces-api-bogus.jar", 
				"faces-impl-bogus.jar", 
				"tomahawk-bogus.jar"};
		
		String[] archivefiles2 = {
				"faces-all-bogu2.jar",
				"faces-api-bogus2.jar", 
				"faces-impl-bogus2.jar", 
				"tomahawk-bogus2.jar"};

		JSFLibrary implJSFLib = JSFCoreUtilHelper.constructJSFLib("JSFLIBIMPL_NAME", 
				"JSFLIBIMPL_NAME", 
				archivefiles1, 
				true);
		implJSFLib.setJSFVersion(org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFVersion.V1_1_LITERAL);
		
		JSFLibrary nonimplJSFLib = JSFCoreUtilHelper.constructJSFLib("JSFLIBNONIMPL_NAME",
				"JSFLIBNONIMPL_NAME",
				archivefiles2,
				false);
		nonimplJSFLib.setJSFVersion(org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFVersion.V1_2_LITERAL);
		
		PluginProvidedJSFLibrary ppJSFLib = (PluginProvidedJSFLibrary)JSFCoreUtilHelper.constructJSFLib("PP-JSFLIBNONIMPL_NAME", 
				"testfiles/JSFLib", 
				false, 
				true);
		ppJSFLib.setPluginID("PluginProvidedLib");
		ppJSFLib.setLabel("PluginProvidedLib");
		ppJSFLib.setJSFVersion(org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFVersion.V1_1_LITERAL);
				
		jsfLibRegistry.addJSFLibrary(implJSFLib);
//        jsfFacedEnv.addJSFLibraryReference(implJSFLib, false);
        
		jsfLibRegistry.addJSFLibrary(nonimplJSFLib);
//		jsfFacedEnv.addJSFLibraryReference(nonimplJSFLib, false);
		
		jsfLibRegistry.addJSFLibrary(ppJSFLib);
//		jsfFacedEnv.addJSFLibraryReference(ppJSFLib, true);
	}
	
	public void testGetJSFLibraryReferencesAndServerSuppliedRef() throws CoreException{
	   Collection<JSFLibraryReference> libs = JSFLibraryConfigurationHelper.getJSFLibraryReferences(projectTestEnvironment.getTestProject());
	   Assert.assertNotNull(libs);
	   Assert.assertEquals(1, libs.size());
	   JSFLibraryReference ref = (JSFLibraryReference)libs.iterator().next();
	   Assert.assertTrue(ref instanceof JSFLibraryReferenceServerSupplied);
	   doAsserts(ref, "JSFLibraryReferenceServerSuppliedImpl", "_ServerSupplied_", "_ServerSupplied_", "Server Supplied", false, true, JSFVersion.UNKNOWN, 0 );	
				
	}

	private void doAsserts(JSFLibraryReference ref, String instanceName, String id, String name, String label, boolean isDeployed, boolean isImpl, JSFVersion version, int jarCount ) {		
		Assert.assertTrue(id+":instanceName", ref.getClass().getSimpleName().equals(instanceName));
		Assert.assertEquals(id+": id",id, ref.getId());
		Assert.assertEquals(id+": name", name, ref.getName());
		Assert.assertEquals(id+": label",label, ref.getLabel());
		Assert.assertEquals(id+": isDeployed",isDeployed, ref.isDeployed());
		Assert.assertEquals(id+": isImpl", isImpl, ref.isJSFImplementation());
		Assert.assertEquals(id+": version", version, ref.getMaxSupportedVersion());
		Assert.assertEquals(id+": jarCount", jarCount, ref.getJars().size());
		Assert.assertNotNull(ref.toString());
	}
	

}
