package org.eclipse.jst.jsf.core.tests.jsflibraryconfig;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFProjectLibraryReference;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.ArchiveFile;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryFactory;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryPackage;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFVersion;
import org.eclipse.jst.jsf.core.tests.TestsPlugin;

import junit.framework.Assert;
import junit.framework.TestCase;

public class JSFProjectLibraryReferenceTestCases extends TestCase {
	private String JSF_NAME = "MockJSFLib";
	private String JSF_ID = "123456789";
	private boolean isImpl = false;
	private JSFLibrary jsfLib = null;
	private JSFProjectLibraryReference jsfLibRef = null;
	
	public JSFProjectLibraryReferenceTestCases(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		jsfLib = JSFLibraryRegistryFactory.eINSTANCE.createJSFLibrary();		
		jsfLib.setID(JSF_ID);
		jsfLib.setName(JSF_NAME);
		jsfLib.setImplementation(false);
				
		jsfLibRef = new JSFProjectLibraryReference(jsfLib, 
	   											   true,	// selected 
												   true);	// to be deployed
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		
		jsfLib = null;
		jsfLibRef = null;
	}

	/*
	 * General test class for JSFProjectLibraryReference class
	 */
	public void testJSFProjectLibraryReference() {		
		JSFProjectLibraryReference jsfLibRef = new JSFProjectLibraryReference(jsfLib, true, true);
		
		Assert.assertNotNull(jsfLibRef);		
		Assert.assertTrue(jsfLibRef.isSelected());
		Assert.assertTrue(jsfLibRef.isCheckedToBeDeployed());		
		Assert.assertTrue(JSF_NAME.equals(jsfLibRef.getName()));
		Assert.assertTrue(JSF_ID.equals(jsfLibRef.getID()));
		Assert.assertTrue(isImpl == jsfLibRef.isImplementation());
	}		
	
	/*
	 * test method for JSFProjectLibraryReference.getLibrary()
	 */
	public void testGetLibrary() {
		Assert.assertNotNull(jsfLibRef);
		Assert.assertEquals(jsfLibRef.getLibrary(), jsfLib);		
	}

	/*
	 * test method for JSFProjectLibraryReference.isCheckedToBeDeployed()
	 */
	public void testIsCheckedToBeDeployed() {
		JSFProjectLibraryReference jsfLibRef = new JSFProjectLibraryReference(jsfLib, true, true);
		Assert.assertNotNull(jsfLibRef);		
		Assert.assertTrue(jsfLibRef.isCheckedToBeDeployed());
	}

	/*
	 * test method for JSFProjectLibraryReference.setSelected()
	 */
	public void testSetSelected() {
		JSFProjectLibraryReference jsfLibRef = new JSFProjectLibraryReference(jsfLib, true, true);
		Assert.assertNotNull(jsfLibRef);		
		
		Assert.assertTrue(jsfLibRef.isSelected());
		
		jsfLibRef.setSelected(false);
		Assert.assertFalse(jsfLibRef.isSelected());
	}

	/*
	 * test method for JSFProjectLibraryReference.isImplementation()
	 */
	public void testIsImplementation() {
		Assert.assertFalse(jsfLibRef.isImplementation());
	}

	/*
	 * test method for JSFProjectLibraryReference.getArchiveFiles()
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
