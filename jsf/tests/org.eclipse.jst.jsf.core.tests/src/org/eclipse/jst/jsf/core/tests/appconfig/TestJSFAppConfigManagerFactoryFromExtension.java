package org.eclipse.jst.jsf.core.tests.appconfig;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.internal.managedobject.AbstractManagedObject;
import org.eclipse.jst.jsf.common.internal.managedobject.ObjectManager.ManagedObjectException;
import org.eclipse.jst.jsf.core.jsfappconfig.internal.IJSFAppConfigManager;
import org.eclipse.jst.jsf.core.jsfappconfig.internal.IJSFAppConfigManagerFactory;

public class TestJSFAppConfigManagerFactoryFromExtension 
		extends 	AbstractManagedObject
		implements 	IJSFAppConfigManagerFactory {
	
	public IJSFAppConfigManager getInstance(final IProject project) throws ManagedObjectException {
		return new TestJSFAppConfigManagerFromExtension(project);
	}

	public void dispose() {
		// TODO Auto-generated method stub		
	}

	public void destroy() {
		// TODO Auto-generated method stub		
	}

	public void checkpoint() {
		// TODO Auto-generated method stub		
	}

}