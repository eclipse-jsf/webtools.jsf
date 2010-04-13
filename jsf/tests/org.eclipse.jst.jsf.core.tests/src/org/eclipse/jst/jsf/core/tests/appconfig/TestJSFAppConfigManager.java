package org.eclipse.jst.jsf.core.tests.appconfig;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.jst.jsf.core.jsfappconfig.IFacesConfigChangeListener;
import org.eclipse.jst.jsf.core.jsfappconfig.IJSFAppConfigProvider;
import org.eclipse.jst.jsf.core.jsfappconfig.IJSFAppConfigProvidersChangeListener;
import org.eclipse.jst.jsf.core.jsfappconfig.JSFAppConfigManager;
import org.eclipse.jst.jsf.core.jsfappconfig.internal.CompositeJSFAppConfigLocatorProviderStrategy;
import org.eclipse.jst.jsf.core.jsfappconfig.internal.IJSFAppConfigManager;
import org.eclipse.jst.jsf.facesconfig.emf.ApplicationType;
import org.eclipse.jst.jsf.facesconfig.emf.BehaviorType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentType;
import org.eclipse.jst.jsf.facesconfig.emf.ConverterType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.emf.FactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.LifecycleType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType;
import org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanType;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitType;
import org.eclipse.jst.jsf.facesconfig.emf.ResourceBundleType;
import org.eclipse.jst.jsf.facesconfig.emf.ValidatorType;

public class TestJSFAppConfigManager implements IJSFAppConfigManager {

	private IProject _project;
	private IJSFAppConfigManager _manager;
	private final AtomicBoolean _isDisposed = new AtomicBoolean(false);

	public TestJSFAppConfigManager(final IProject project,
			final TestLocatorProvider testLocatorProvider) throws Exception{
		
		_project = project;
		_project.setSessionProperty(CompositeJSFAppConfigLocatorProviderStrategy.TESTABLE_FACTORY_SESSION_KEY, testLocatorProvider);
		
	}

	public IProject getProject() {		
		return _project;
	}

	private IJSFAppConfigManager getManager() {
		if (_manager == null) {
			//deprecated is to be expected
			_manager = JSFAppConfigManager.getInstance(_project);
		}
		return _manager;
	}
	public boolean addJSFAppConfigProvidersChangeListener(
			IJSFAppConfigProvidersChangeListener listener) {
		return getManager().addJSFAppConfigProvidersChangeListener(listener);
	}

	public boolean removeJSFAppConfigProvidersChangeListener(
			IJSFAppConfigProvidersChangeListener listener) {
		return getManager().removeJSFAppConfigProvidersChangeListener(listener);
	}

	public void notifyJSFAppConfigProvidersChangeListeners(
			IJSFAppConfigProvider configProvider, int eventType) {	
		
		getManager().notifyJSFAppConfigProvidersChangeListeners(configProvider, eventType);
	}

	@SuppressWarnings("rawtypes")
	public Object addFacesConfigChangeListener(Class emfClass,
			IFacesConfigChangeListener listener) {
		
		return getManager().addFacesConfigChangeListener(emfClass, listener);
	}

	@SuppressWarnings("rawtypes")
	public Object removeFacesConfigChangeListener(Class emfClass) {	
		return getManager().removeFacesConfigChangeListener(emfClass);
	}

	public void notifyFacesConfigChangeListeners(Notification notification) {
		getManager().notifyFacesConfigChangeListeners(notification);
	}

//	public Set<IJSFAppConfigProvider> getJSFAppConfigProviders() {
//		return getManager().getJSFAppConfigProviders();
//	}

//	public List<FacesConfigType> getFacesConfigModels() {
//		return getManager().getFacesConfigModels();
//	}

	public List<ManagedBeanType> getManagedBeans() {
		return getManager().getManagedBeans();
	}

	public List<String> getPropertyResolvers() {
		return getManager().getPropertyResolvers();
	}

	public List<ValidatorType> getValidators() {
		return getManager().getValidators();
	}

	public List<String> getVariableResolvers() {
		return getManager().getVariableResolvers();
	}

	public List<ConverterType> getConverters() {
		return getManager().getConverters();
	}

	public List<NavigationRuleType> getNavigationRules() {
		return getManager().getNavigationRules();
	}

	public List<NavigationRuleType> getNavigationRulesForPage(IFile pageFile) {
		return getManager().getNavigationRulesForPage(pageFile);
	}

	public List<ApplicationType> getApplications() {
		return getManager().getApplications();
	}

	public List<FactoryType> getFactories() {
		return getManager().getFactories();
	}

	public List<ComponentType> getComponents() {
		return getManager().getComponents();
	}

	public List<ReferencedBeanType> getReferencedBeans() {
		return getManager().getReferencedBeans();
	}

	public List<RenderKitType> getRenderKits() {
		return getManager().getRenderKits();
	}

	public List<LifecycleType> getLifecycles() {
		return getManager().getLifecycles();
	}

	public List<ResourceBundleType> getResourceBundles() {
		return getManager().getResourceBundles();
	}

	public List<FacesConfigExtensionType> getFacesConfigExtensions() {
		return getManager().getFacesConfigExtensions();
	}

	public List<BehaviorType> getBehaviors() {
		return getManager().getBehaviors();
	}

	public void addFacesConfigChangeAdapter(FacesConfigType facesConfig) {
		getManager().addFacesConfigChangeAdapter(facesConfig);

	}

	public void removeFacesConfigChangeAdapter(FacesConfigType facesConfig) {
		getManager().removeFacesConfigChangeAdapter(facesConfig);
	}

	public void dispose() {
		_isDisposed.compareAndSet(false, true);
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void checkpoint() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean isDisposed() {
		return _isDisposed.get();
	}

}
