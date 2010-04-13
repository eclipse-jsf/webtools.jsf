package org.eclipse.jst.jsf.core.tests.appconfig;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.core.jsfappconfig.internal.IJSFAppConfigManager;
import org.eclipse.jst.jsf.core.jsfappconfig.internal.IJSFAppConfigManagerFactory;

public class TestJSFAppConfigManagerFactory implements IJSFAppConfigManagerFactory {

	public IJSFAppConfigManager getInstance(IProject project) {		
		try {
			return new TestJSFAppConfigManager(project, new TestLocatorProvider());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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

	public boolean isDisposed() {
		// TODO Auto-generated method stub
		return false;
	}

}
