package org.eclipse.jst.jsf.core.tests.jsflibraryconfig;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryReference;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.ArchiveFile;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryFactory;
import org.eclipse.jst.jsf.core.tests.TestsPlugin;

public class JSFProjectLibraryReferenceTestCases extends TestCase {
	private String JSF_NAME = "MockJSFLib";
//	private String JSF_ID = "123456789";
	private boolean isImpl = false;
	private JSFLibrary jsfLib = null;
	private JSFLibraryReference jsfLibRef = null;
	
	public JSFProjectLibraryReferenceTestCases(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		jsfLib = JSFLibraryRegistryFactory.eINSTANCE.createJSFLibrary();		
//		jsfLib.setID(JSF_ID);
		jsfLib.setName(JSF_NAME);
		jsfLib.setImplementation(false);
				
		jsfLibRef = new JSFLibraryReference(jsfLib, 
	   											   true,	// selected 
												   true);	// to be deployed
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		
		jsfLib = null;
		jsfLibRef = null;
	}

	/*
	 * General test class for JSFLibraryReference class
	 */
	public void testJSFProjectLibraryReference() {		
		JSFLibraryReference jsfLibRef_ = new JSFLibraryReference(jsfLib, true, true);
		
		Assert.assertNotNull(jsfLibRef_);		
		Assert.assertTrue(jsfLibRef_.isSelected());
		Assert.assertTrue(jsfLibRef_.isCheckedToBeDeployed());		
		Assert.assertTrue(JSF_NAME.equals(jsfLibRef_.getName()));
		Assert.assertTrue(JSF_NAME.equals(jsfLibRef_.getID()));
		Assert.assertTrue(isImpl == jsfLibRef_.isImplementation());
	}		
	
	/*
	 * test method for JSFLibraryReference.getLibrary()
	 */
	public void testGetLibrary() {
		Assert.assertNotNull(jsfLibRef);
		Assert.assertEquals(jsfLibRef.getLibrary(), jsfLib);		
	}

	/*
	 * test method for JSFLibraryReference.isCheckedToBeDeployed()
	 */
	public void testIsCheckedToBeDeployed() {
		JSFLibraryReference jsfLibRef_ = new JSFLibraryReference(jsfLib, true, true);
		Assert.assertNotNull(jsfLibRef_);		
		Assert.assertTrue(jsfLibRef_.isCheckedToBeDeployed());
	}

	/*
	 * test method for JSFLibraryReference.setSelected()
	 */
	public void testSetSelected() {
		JSFLibraryReference jsfLibRef_ = new JSFLibraryReference(jsfLib, true, true);
		Assert.assertNotNull(jsfLibRef_);		
		
		Assert.assertTrue(jsfLibRef_.isSelected());
		
		jsfLibRef_.setSelected(false);
		Assert.assertFalse(jsfLibRef_.isSelected());
	}

	/*
	 * test method for JSFLibraryReference.isImplementation()
	 */
	public void testIsImplementation() {
		Assert.assertFalse(jsfLibRef.isImplementation());
	}

	/*
	 * test method for JSFLibraryReference.getArchiveFiles()
	 */	
	public void testGetArchiveFiles() {
		String path2TestJAR = TestsPlugin.getInstallLocation().getPath() + "testfiles/faces-all-bogus.jar";

		ArchiveFile af = JSFLibraryRegistryFactory.eINSTANCE.createArchiveFile();	
		af.setRelativeToWorkspace(false);
		af.setSourceLocation(path2TestJAR);		
		af.setJSFLibrary(jsfLib);
		
		Assert.assertNotNull(jsfLib.getArchiveFiles());
		Assert.assertTrue(jsfLib.getArchiveFiles().size() == 1);
	}	
}
