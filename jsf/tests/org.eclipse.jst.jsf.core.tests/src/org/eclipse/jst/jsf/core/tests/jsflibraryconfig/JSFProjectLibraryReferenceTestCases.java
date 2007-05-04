package org.eclipse.jst.jsf.core.tests.jsflibraryconfig;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryInternalReference;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.ArchiveFile;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryFactory;
import org.eclipse.jst.jsf.core.tests.TestsPlugin;

public class JSFProjectLibraryReferenceTestCases extends TestCase {
	private String JSF_NAME = "MockJSFLib";
//	private String JSF_ID = "123456789";
	private boolean isImpl = false;
	private JSFLibrary jsfLib = null;
	private JSFLibraryInternalReference jsfLibRef = null;
	
	public JSFProjectLibraryReferenceTestCases(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		jsfLib = JSFLibraryRegistryFactory.eINSTANCE.createJSFLibrary();		
		jsfLib.setName(JSF_NAME);
		jsfLib.setImplementation(false);
				
		jsfLibRef = new JSFLibraryInternalReference(jsfLib, 
	   											   true,	// selected 
												   true);	// to be deployed
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		
		jsfLib = null;
		jsfLibRef = null;
	}

	/*
	 * General test class for JSFLibraryInternalReference class
	 */
	public void testJSFProjectLibraryReference() {		
		JSFLibraryInternalReference jsfLibRef_ = new JSFLibraryInternalReference(jsfLib, true, true);
		
		Assert.assertNotNull(jsfLibRef_);		
		Assert.assertTrue(jsfLibRef_.isSelected());
		Assert.assertTrue(jsfLibRef_.isCheckedToBeDeployed());		
		Assert.assertTrue(JSF_NAME.equals(jsfLibRef_.getName()));
		Assert.assertTrue(JSF_NAME.equals(jsfLibRef_.getID()));
		Assert.assertTrue(JSF_NAME.equals(jsfLibRef_.getLabel()));
		Assert.assertTrue(isImpl == jsfLibRef_.isImplementation());
	}		
	
	/*
	 * test method for JSFLibraryInternalReference.getLibrary()
	 */
	public void testGetLibrary() {
		Assert.assertNotNull(jsfLibRef);
		Assert.assertEquals(jsfLibRef.getLibrary(), jsfLib);		
	}

	/*
	 * test method for JSFLibraryInternalReference.isCheckedToBeDeployed()
	 */
	public void testIsCheckedToBeDeployed() {
		JSFLibraryInternalReference jsfLibRef_ = new JSFLibraryInternalReference(jsfLib, true, true);
		Assert.assertNotNull(jsfLibRef_);		
		Assert.assertTrue(jsfLibRef_.isCheckedToBeDeployed());
	}

	/*
	 * test method for JSFLibraryInternalReference.setSelected()
	 */
	public void testSetSelected() {
		JSFLibraryInternalReference jsfLibRef_ = new JSFLibraryInternalReference(jsfLib, true, true);
		Assert.assertNotNull(jsfLibRef_);		
		
		Assert.assertTrue(jsfLibRef_.isSelected());
		
		jsfLibRef_.setSelected(false);
		Assert.assertFalse(jsfLibRef_.isSelected());
	}

	/*
	 * test method for JSFLibraryInternalReference.isImplementation()
	 */
	public void testIsImplementation() {
		Assert.assertFalse(jsfLibRef.isImplementation());
	}

	/*
	 * test method for JSFLibraryInternalReferenceJSFLibraryInternalReference.getArchiveFiles()
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
