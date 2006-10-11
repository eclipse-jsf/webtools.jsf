package org.eclipse.jst.jsf.core.tests.jsflibraryconfig;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.J2EEModuleDependencyDelegate;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.tests.util.JSFCoreUtilHelper;

import junit.framework.Assert;
import junit.framework.TestCase;

public class J2EEModuleDependencyDelegateTestCases extends TestCase {
	private static final String PROJ_NAME = "_TEST_CFGMODELADAPTER_PROJECT";
	private IProject project;
	private JSFLibrary jsfLib; 

	public J2EEModuleDependencyDelegateTestCases(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		JSFCoreUtilHelper.createJSFLibraryRegistry();
		project = JSFCoreUtilHelper.createWebProject(PROJ_NAME);
		jsfLib = JSFCorePlugin.getDefault().getJSFLibraryRegistry().getDefaultImplementation();		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		
		jsfLib = null;
		project = null;		
	}

	public void testJ2EEModuleDependencyDelegate() {
		J2EEModuleDependencyDelegate dep = new J2EEModuleDependencyDelegate(project);
		Assert.assertNotNull(dep);
		
		// To do more...
	}

	public void testUpdateProjectDependencies() {
		//fail("Not yet implemented");
	}

}
