package org.eclipse.jst.jsf.core.tests.jsflibraryconfig;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryDecorator;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.ArchiveFile;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryFactory;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFVersion;
import org.eclipse.jst.jsf.core.tests.TestsPlugin;

import junit.framework.Assert;
import junit.framework.TestCase;

public class JSFLibraryDecoratorTestCases extends TestCase {
	private String JSF_NAME = "MockJSFLib";
	private String JSF_ID = "123456789";
	private boolean isImpl = false;
	
	public JSFLibraryDecoratorTestCases(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testJSFLibraryDecorator() {		
		JSFLibrary jsfLib = new MockJSFLib(JSF_NAME, 
				JSF_ID, 
				isImpl);
				
		JSFLibraryDecorator jsfLibDctr = new JSFLibraryDecorator(jsfLib, true, true);
		Assert.assertNotNull(jsfLibDctr);		
		Assert.assertTrue(jsfLibDctr.isSelected());
		Assert.assertTrue(jsfLibDctr.isCheckedToBeDeployed());
		
		jsfLibDctr.setSelected(false);
		Assert.assertFalse(jsfLibDctr.isSelected());
		
		jsfLibDctr.setToBeDeployed(false);
		Assert.assertFalse(jsfLibDctr.isCheckedToBeDeployed());
		
		Assert.assertEquals("MockJSFLib", jsfLibDctr.getName());
		Assert.assertEquals("123456789", jsfLibDctr.getID());
		Assert.assertTrue(false == jsfLibDctr.isImplementation());
	}	
	
	class MockJSFLib implements JSFLibrary {
		private String MockName;
		private String MockID;
		private boolean isImpl;
		MockJSFLib(String name, String id, boolean isImpl) {
			this.MockName = name;
			this.MockID = id;
			this.isImpl = isImpl;
		}
		
		public boolean containsArchiveFile(String fullPath) {
			return false;
		}

		public boolean copyTo(String baseDestLocation) {
			return false;
		}

		public EList getArchiveFiles() {
			return null;
		}

		public String getID() {
			return MockID;
		}

		public JSFVersion getJSFVersion() {
			return null;
		}

		public String getName() {
			return MockName;
		}

		public JSFLibrary getWorkingCopy() {
			return null;
		}

		public boolean isDeployed() {
			return false;
		}

		public boolean isImplementation() {
			return isImpl;
		}

		public void setDeployed(boolean value) {
			
		}

		public void setID(String value) {
			
		}

		public void setImplementation(boolean value) {
			
		}

		public void setJSFVersion(JSFVersion value) {
			
		}

		public void setName(String value) {
			
		}

		public void updateValues(JSFLibrary otherLibrary) {
			
		}

		public TreeIterator eAllContents() {
			return null;
		}

		public EClass eClass() {
			return null;
		}

		public EObject eContainer() {
			return null;
		}

		public EStructuralFeature eContainingFeature() {
			return null;
		}

		public EReference eContainmentFeature() {
			return null;
		}

		public EList eContents() {
			return null;
		}

		public EList eCrossReferences() {
			return null;
		}

		public Object eGet(EStructuralFeature feature) {
			return null;
		}

		public Object eGet(EStructuralFeature feature, boolean resolve) {
			return null;
		}

		public boolean eIsProxy() {
			return false;
		}

		public boolean eIsSet(EStructuralFeature feature) {
			return false;
		}

		public Resource eResource() {
			return null;
		}

		public void eSet(EStructuralFeature feature, Object newValue) {
			
		}

		public void eUnset(EStructuralFeature feature) {
			
		}

		public EList eAdapters() {
			return null;
		}

		public boolean eDeliver() {
			return false;
		}

		public void eNotify(Notification notification) {
			
		}

		public void eSetDeliver(boolean deliver) {
			
		}
		
	}
	
}
