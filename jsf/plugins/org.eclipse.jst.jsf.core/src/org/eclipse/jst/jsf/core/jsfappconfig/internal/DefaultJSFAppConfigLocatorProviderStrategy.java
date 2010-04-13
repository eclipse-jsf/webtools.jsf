package org.eclipse.jst.jsf.core.jsfappconfig.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.core.jsfappconfig.ContextParamSpecifiedJSFAppConfigLocater;
import org.eclipse.jst.jsf.core.jsfappconfig.DefaultJSFAppConfigLocater;
import org.eclipse.jst.jsf.core.jsfappconfig.IJSFAppConfigLocater;
import org.eclipse.jst.jsf.core.jsfappconfig.ImplicitRuntimeJSFAppConfigLocater;
import org.eclipse.jst.jsf.core.jsfappconfig.RuntimeClasspathJSFAppConfigLocater;

/**
 * The platforms default LocatorProviderStrategy.
 * <p>
 * Will return:
 * <ol>
 * <li>ImplicitRuntimeJSFAppConfigLocater</li>
 * <li>DefaultJSFAppConfigLocater</li>
 * <li>ContextParamSpecifiedJSFAppConfigLocater</li>
 * <li>RuntimeClasspathJSFAppConfigLocater</li>
 * <ol>
 *
 */
public class DefaultJSFAppConfigLocatorProviderStrategy 
		extends 	JSFAppConfigLocatorProviderStrategy 
		implements 	IJSFAppConfigLocatorProvider {
	
	public IJSFAppConfigLocatorProvider perform(IProject project)  {
		return this;
	}

	public List<IJSFAppConfigLocater> getLocators() {
		List<IJSFAppConfigLocater> ret = new ArrayList<IJSFAppConfigLocater>();
		
		// implicit runtime-provided configuration
		IJSFAppConfigLocater implicitRuntimeConfigLocater = new ImplicitRuntimeJSFAppConfigLocater();
		ret.add(implicitRuntimeConfigLocater);
		
		// default ("/WEB-INF/faces-config.xml") locater
		IJSFAppConfigLocater defaultConfigLocater = new DefaultJSFAppConfigLocater();
		ret.add(defaultConfigLocater);
		
		// web.xml context-parameter specified locater
		IJSFAppConfigLocater contextParamConfigLocater = new ContextParamSpecifiedJSFAppConfigLocater();
		ret.add(contextParamConfigLocater);
		
		// runtime classpath locater
		IJSFAppConfigLocater classpathConfigLocater = new RuntimeClasspathJSFAppConfigLocater();
		ret.add(classpathConfigLocater);
		
		return Collections.unmodifiableList(ret);
	}

}
