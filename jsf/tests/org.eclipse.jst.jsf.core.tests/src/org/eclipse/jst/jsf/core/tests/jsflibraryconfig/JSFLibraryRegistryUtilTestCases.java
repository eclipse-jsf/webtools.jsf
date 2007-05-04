package org.eclipse.jst.jsf.core.tests.jsflibraryconfig;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryInternalReference;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryRegistryUtil;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistry;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryFactory;
import org.eclipse.jst.jsf.core.tests.util.JSFCoreUtilHelper;

public class JSFLibraryRegistryUtilTestCases extends TestCase {
	private JSFLibraryRegistry libReg = null;
	private  JSFLibraryRegistryUtil libUtilInstance = null;
	private int numCompLibs;
	
	protected void setUp() throws Exception {
		super.setUp();

		JSFCoreUtilHelper.createJSFLibraryRegistry();
		libReg = JSFCoreUtilHelper.getJSFLibraryRegistryFromJSFLibraryHelper();
		numCompLibs = libReg.getNonImplJSFLibraries().size();
		libUtilInstance = JSFLibraryRegistryUtil.getInstance();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		
		libReg = null;
	}

	public void testGetJSFLibraryRegistry() {
		Assert.assertNotNull(libUtilInstance.getJSFLibraryRegistry());
		Assert.assertEquals(libReg, libUtilInstance.getJSFLibraryRegistry());
	}

	public void testGetDefaultJSFImplementationLibrary() {
		JSFLibraryInternalReference dftImplLib = libUtilInstance.getDefaultJSFImplementationLibrary();
		Assert.assertNotNull(dftImplLib);
		Assert.assertEquals(libReg.getDefaultImplementation().getID(), dftImplLib.getID());
	}

	public void testGetJSFLibryReferencebyID() {
		JSFLibraryInternalReference lib = libUtilInstance.getJSFLibraryReferencebyID(libReg.getDefaultImplementationID());
		Assert.assertNotNull(lib);
		Assert.assertEquals(libReg.getDefaultImplementation().getID(), lib.getID());
	}

	public void testAddJSFLibrary() {
		JSFLibrary jsfLib = JSFLibraryRegistryFactory.eINSTANCE.createJSFLibrary();		
//		jsfLib.setID("124365879");
		jsfLib.setName("A_COMP_LIB");
		jsfLib.setImplementation(false);
				
		JSFLibraryInternalReference jsfLibRef = new JSFLibraryInternalReference(jsfLib, 
	   											   			false,	// selected 
	   											   			false);	// to be deployed
		Assert.assertTrue(libReg.getNonImplJSFLibraries().size() == numCompLibs);
		libUtilInstance.addJSFLibrary(jsfLibRef);
		Assert.assertTrue(libReg.getNonImplJSFLibraries().size() == (numCompLibs + 1));		
	}

}
