/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.core.jsfappconfig.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.core.jsfappconfig.AnnotationJSFAppConfigLocator;
import org.eclipse.jst.jsf.core.jsfappconfig.ContextParamSpecifiedJSFAppConfigLocater;
import org.eclipse.jst.jsf.core.jsfappconfig.DefaultJSFAppConfigLocater;
import org.eclipse.jst.jsf.core.jsfappconfig.IJSFAppConfigLocater;
import org.eclipse.jst.jsf.core.jsfappconfig.ImplicitRuntimeJSFAppConfigLocater;
import org.eclipse.jst.jsf.core.jsfappconfig.RuntimeClasspathJSFAppConfigLocater;

/**
 * The platform's default JSFAppConfigLocatorProviderStrategy.
 * <p>
 * Will return:
 * <ol>
 * <li>ImplicitRuntimeJSFAppConfigLocater</li>
 * <li>DefaultJSFAppConfigLocater</li>
 * <li>ContextParamSpecifiedJSFAppConfigLocater</li>
 * <li>RuntimeClasspathJSFAppConfigLocater</li>
 * <li>AnnotationJSFAppConfigLocator</li>
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

		// annotation config locator
		IJSFAppConfigLocater annotationConfigLocator = new AnnotationJSFAppConfigLocator();
		ret.add(annotationConfigLocator);

		return Collections.unmodifiableList(ret);
	}

}
