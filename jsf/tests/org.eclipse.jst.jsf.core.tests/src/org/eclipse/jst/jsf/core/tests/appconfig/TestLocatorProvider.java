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
package org.eclipse.jst.jsf.core.tests.appconfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jst.jsf.core.jsfappconfig.AbstractJSFAppConfigLocater;
import org.eclipse.jst.jsf.core.jsfappconfig.AbstractJSFAppConfigProvider;
import org.eclipse.jst.jsf.core.jsfappconfig.IJSFAppConfigLocater;
import org.eclipse.jst.jsf.core.jsfappconfig.IJSFAppConfigProvider;
import org.eclipse.jst.jsf.core.jsfappconfig.internal.DefaultJSFAppConfigLocatorProviderStrategy;
import org.eclipse.jst.jsf.core.jsfappconfig.internal.IJSFAppConfigLocatorProvider;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanNameType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType;

/**
 * An {@link IJSFAppConfigLocatorProvider} that adds a faked {@link IJSFAppConfigLocater} and all of the default platform locators 
 *
 */
public class TestLocatorProvider implements IJSFAppConfigLocatorProvider {

	public static final String 	MANAGED_BEAN_NAME_PREFIX 	= "MyManagedBean";
	public static final String 	MANAGED_BEAN_CLASS_PREFIX 	= "com.foo.MyManagedBeanClass";
	public static final int     MANAGED_BEAN_COUNT			= 10;
	public int 					managed_bean_count 			= 0;

	public TestLocatorProvider(int count) {
		managed_bean_count = count;
	}
	
	public TestLocatorProvider() {
		managed_bean_count = MANAGED_BEAN_COUNT;
	}

	public List<IJSFAppConfigLocater> getLocators() {
		List<IJSFAppConfigLocater> ret = new ArrayList<IJSFAppConfigLocater>();
		ret.add(new FakeLocator());
		ret.addAll(new DefaultJSFAppConfigLocatorProviderStrategy().getLocators());
		return Collections.unmodifiableList(ret);
	}
	
	/**
	 * Set how many beans should be created by the Provider
	 * @param count
	 */
	public void setBeanCreationCount(int count) {
		managed_bean_count = count;
	}

	private class FakeLocator extends AbstractJSFAppConfigLocater {

		private Set<IJSFAppConfigProvider> _providers;

		@Override
		public Set<IJSFAppConfigProvider> getJSFAppConfigProviders() {
			if (_providers == null) {
				_providers = new HashSet<IJSFAppConfigProvider>();
				_providers.add(new FakeProvider());
			}
			return Collections.unmodifiableSet(_providers);
		}

		@Override
		public void startLocating() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void stopLocating() {
			// TODO Auto-generated method stub		
		}		
	}
	
	private class FakeProvider extends AbstractJSFAppConfigProvider {

		private FacesConfigType _model;

		@Override
		public FacesConfigType getFacesConfigModel() {
			if (_model == null) {
				FacesConfigFactory fac = FacesConfigFactory.eINSTANCE;
				_model = fac.createFacesConfigType();
				addFakeBeans(fac, _model);
			}
			return _model;
		}

		@SuppressWarnings("unchecked")
		private void addFakeBeans(FacesConfigFactory fac, FacesConfigType model) {
			for (int i=0; i<TestLocatorProvider.this.managed_bean_count; i++) {
				ManagedBeanType bean = fac.createManagedBeanType();
				
				ManagedBeanNameType name = fac.createManagedBeanNameType();
				name.setTextContent(MANAGED_BEAN_NAME_PREFIX+i);
				bean.setManagedBeanName(name);
				
				ManagedBeanClassType klass = fac.createManagedBeanClassType();
				klass.setTextContent(MANAGED_BEAN_CLASS_PREFIX+i);				
				bean.setManagedBeanClass(klass);
				
				model.getManagedBean().add(bean);
			}			
		}

		@Override
		public void releaseFacesConfigModel() {
			_model = null;			
		}
		
	}
}
