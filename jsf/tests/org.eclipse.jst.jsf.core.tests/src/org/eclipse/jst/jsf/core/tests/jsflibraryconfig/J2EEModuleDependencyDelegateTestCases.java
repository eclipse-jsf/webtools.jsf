package org.eclipse.jst.jsf.core.tests.jsflibraryconfig;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.J2EEModuleDependencyDelegate;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryConfigDialogSettingData;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryConfigModel;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryConfiglModelSource;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryConfigModel.JSFLibraryConfigModelFactory;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistry;
import org.eclipse.jst.jsf.core.internal.project.facet.JSFUtils;
import org.eclipse.jst.jsf.core.tests.util.JSFCoreUtilHelper;

import junit.framework.Assert;
import junit.framework.TestCase;

public class J2EEModuleDependencyDelegateTestCases extends TestCase {	
	private static final String PROJ_NAME = "_TEST_CFGMODULEDEPENDENCY_PROJECT";
	private IProject project; 

	public J2EEModuleDependencyDelegateTestCases(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		JSFCoreUtilHelper.createJSFLibraryRegistry();
		project = JSFCoreUtilHelper.createWebProject(PROJ_NAME);		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		
		project = null;		
	}

	public void testJ2EEModuleDependencyDelegate() {
		J2EEModuleDependencyDelegate dep = new J2EEModuleDependencyDelegate(project);
		Assert.assertNotNull(dep);

		// Test updateProjectDependencies method()
		JSFLibraryRegistry libReg = JSFCoreUtilHelper.getJSFLibraryRegistryFromJSFCorePlugin();

		String[] compLibs = new String[1];
		JSFLibrary lib = (JSFLibrary)libReg.getNonImplJSFLibraries().get(0);
		compLibs[0] = lib.getID() + ":" + "true";	// deployed
		
		JSFLibraryConfiglModelSource modelsrc = new JSFLibraryConfigDialogSettingData(true, compLibs);
		JSFLibraryConfigModel model = JSFLibraryConfigModelFactory.createInstance(modelsrc);
		dep.updateProjectDependencies(model, new NullProgressMonitor());
		// Validate updated dependencies??
	}
	
}
