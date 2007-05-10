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
import org.eclipse.jst.jsf.core.jsflibraryconfiguration.JSFVersion;
import org.eclipse.jst.jsf.core.jsflibraryconfiguration.internal.JSFLibraryReferenceFacadeFactory;
import org.eclipse.jst.jsf.core.tests.util.JSFCoreUtilHelper;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;

public class JSFLibraryReferenceTestCases extends TestCase {
	WebProjectTestEnvironment projectTestEnvironment;
	JDTTestEnvironment jdtTestEnv;
	
	protected void setUp() throws Exception {
		super.setUp();
		
        JSFTestUtil.setValidationEnabled(false);
        JSFTestUtil.setInternetProxyPreferences(true, "www-proxy.us.oracle.com", "80");
        
        projectTestEnvironment = 
            new WebProjectTestEnvironment("JSFLibraryReferenceTestCases");
        boolean created = projectTestEnvironment.createProject(true);

        assertNotNull(projectTestEnvironment);       
        assertNotNull(projectTestEnvironment.getTestProject());
        assertTrue(projectTestEnvironment.getTestProject().isAccessible());
        
        if (created) {
	        // initialize test case for faces 1.1
	        JSFFacetedTestEnvironment jsfFacedEnv = new JSFFacetedTestEnvironment(projectTestEnvironment);
	        jsfFacedEnv.initialize(IJSFCoreConstants.FACET_VERSION_1_1);
	        
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
		ppJSFLib.setLabel("PluginProvidedLib");
		ppJSFLib.setJSFVersion(org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFVersion.V1_1_LITERAL);
				
		jsfLibRegistry.addJSFLibrary(implJSFLib);
        jsfFacedEnv.addJSFLibraryReference(implJSFLib, false);
        
		jsfLibRegistry.addJSFLibrary(nonimplJSFLib);
		jsfFacedEnv.addJSFLibraryReference(nonimplJSFLib, false);
		
		jsfLibRegistry.addJSFLibrary(ppJSFLib);
		jsfFacedEnv.addJSFLibraryReference(ppJSFLib, true);
	}
	
	public void testGetJSFLibraryReferences() {
		Collection<JSFLibraryReference> results = JSFLibraryConfigurationHelper.getJSFLibraryReferences(projectTestEnvironment.getTestProject());
		Assert.assertNotNull(results);
		Assert.assertTrue(results.size() >= 3);		
	}

	public void testIsJSFLibraryContainer() throws JavaModelException, CoreException {
		IClasspathEntry[] entries = null;
		entries = getJDTTestEnv().getJavaProject().getRawClasspath();			
		for (int i=0;i<entries.length;i++) {
			IClasspathEntry cpEntry = entries[i];
			boolean isJsfLib = JSFLibraryConfigurationHelper.isJSFLibraryContainer(cpEntry);
			if (cpEntry.getEntryKind() == IClasspathEntry.CPE_CONTAINER &&
					cpEntry.getPath().segment(0).equals(JSFLibraryConfigurationHelper.JSF_LIBRARY_CP_CONTAINER_ID)) {
				Assert.assertTrue("Is a JSF LIB: "+cpEntry.toString(), isJsfLib);
			}
			else
				Assert.assertFalse("Is NOT a JSF LIB: "+cpEntry.toString(), isJsfLib);
		}
	}
	
	public void testJSFLibraryReferenceFacadeFactoryCreate() throws CoreException{
		IClasspathEntry[] entries = null;
		try {
			entries = getJDTTestEnv().getJavaProject().getRawClasspath();			
			for (int i=0;i<entries.length;i++){
				IClasspathEntry cpEntry = entries[i];
				boolean isJsfLib = JSFLibraryConfigurationHelper.isJSFLibraryContainer(cpEntry);
				JSFLibraryReference ref = JSFLibraryReferenceFacadeFactory.create(cpEntry);
				if (isJsfLib) {
					String libID = getLibId(cpEntry);
					Assert.assertNotNull(libID+": ref", ref);
					if (libID.equals("JSFLIBIMPL_NAME")){
						doAsserts(ref, "JSFLibraryReferenceUserDefinedImpl", "JSFLIBIMPL_NAME", "JSFLIBIMPL_NAME", false, true, JSFVersion.V1_1 );	
					}
					else if (libID.equals("JSFLIBNONIMPL_NAME")){
						doAsserts(ref, "JSFLibraryReferenceUserDefinedImpl", "JSFLIBNONIMPL_NAME", "JSFLIBNONIMPL_NAME", false, false,JSFVersion.V1_2 );	
					}
					else if (libID.equals("null$$PP-JSFLIBNONIMPL_NAME")){
						doAsserts(ref, "JSFLibraryReferencePluginProvidedImpl", "null$$PP-JSFLIBNONIMPL_NAME", "PluginProvidedLib", true, false, JSFVersion.V1_1 );
					}
					else if (libID.equals("AN-IMPL-LIB")){
						doAsserts(ref, "JSFLibraryReferenceUserDefinedImpl", "AN-IMPL-LIB", "AN-IMPL-LIB", true, true, JSFVersion.UNKNOWN );					
					}
					else if (libID.equals("AN-IMPL-LIB-PP")){
						doAsserts(ref, "JSFLibraryReferencePluginProvidedImpl", "AN-IMPL-LIB-PP", "AN-IMPL-LIB-PP", true, true, JSFVersion.UNKNOWN);					
					}
				}
				else {
					Assert.assertNull(ref);
				}
			}

//			JSFLibraryReferenceFacadeFactory.create(cpEntry)
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private JDTTestEnvironment getJDTTestEnv() throws CoreException {
		if (jdtTestEnv == null){
			jdtTestEnv = new JDTTestEnvironment(projectTestEnvironment);
		}
		return jdtTestEnv;
	}

	private void doAsserts(JSFLibraryReference ref, String instanceName, String id, String label, boolean isDeployed, boolean isImpl, JSFVersion version ) {
		Assert.assertTrue(id+":instanceName", ref.getClass().getSimpleName().equals(instanceName));
		Assert.assertEquals(id+": id",id, ref.getId());
		Assert.assertEquals(id+": label",label, ref.getLabel());
		Assert.assertEquals(id+": isDeployed",isDeployed, ref.isDeployed());
		Assert.assertEquals(id+": isImpl", isImpl, ref.isJSFImplementation());
		Assert.assertEquals(id+": version", version, ref.getMaxSupportedVersion());
	}
	

	
	private String getLibId(IClasspathEntry cpEntry){
		return cpEntry.getPath().segment(1);
	}
	
//	public void testCreateServerSuppliedJSFLibRef() {
//		fail("Not yet implemented");
//	}

}
