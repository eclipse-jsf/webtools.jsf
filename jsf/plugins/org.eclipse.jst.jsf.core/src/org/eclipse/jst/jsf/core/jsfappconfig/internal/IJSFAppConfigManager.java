package org.eclipse.jst.jsf.core.jsfappconfig.internal;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.jst.jsf.common.internal.managedobject.IManagedObject;
import org.eclipse.jst.jsf.core.jsfappconfig.IFacesConfigChangeListener;
import org.eclipse.jst.jsf.core.jsfappconfig.IJSFAppConfigProvider;
import org.eclipse.jst.jsf.core.jsfappconfig.IJSFAppConfigProvidersChangeListener;
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

/**
 * Mostly represents a web applications JSF configuration.
 * <p>
 * This is a merged view of all the faces-config elements.
 *
 * @noimplement - must extend {@link AbstractJSFAppConfigManager}
 */
public interface IJSFAppConfigManager extends IManagedObject{

	/**
	 * Gets this instance's IProject instance.
	 * 
	 * @return This instance's IProject instance.
	 */
	public IProject getProject();

	/**
	 * Adds an instance of {@link IJSFAppConfigProvidersChangeListener}.
	 * 
	 * @param listener An instance of {@link IJSFAppConfigProvidersChangeListener}.
	 * @return true if added, else false.
	 */
	public boolean addJSFAppConfigProvidersChangeListener(
			IJSFAppConfigProvidersChangeListener listener);

	/**
	 * Removes an instance of {@link IJSFAppConfigProvidersChangeListener}.
	 * 
	 * @param listener an instance of {@link IJSFAppConfigProvidersChangeListener}.
	 * @return true if removed, else false.
	 */
	public boolean removeJSFAppConfigProvidersChangeListener(
			IJSFAppConfigProvidersChangeListener listener);

	/**
	 * Notifies all {@link IJSFAppConfigProvidersChangeListener} instances of
	 * a change in the Set of {@link IJSFAppConfigProvider} instances.
	 * 
	 * @param configProvider {@link IJSFAppConfigProvider} instance that has
	 * changed.
	 * @param eventType Event type.
	 */
	public void notifyJSFAppConfigProvidersChangeListeners(
			IJSFAppConfigProvider configProvider, int eventType);

	/**
	 * Adds an instance of {@link IFacesConfigChangeListener}. <br>
	 * <br>
	 * <b>NOTE:</b> Calling this method should cause all application
	 * configuration models to be loaded, to ensure that a
	 * FacesConfigChangeAdapter has been added to each model
	 * if the model is updateable.
	 * 
	 * @param emfClass EMF class in which the listener is interested.
	 * @param listener {@link IFacesConfigChangeListener} instance.
	 * @return Previous {@link IFacesConfigChangeListener}, or null.
	 */
	public Object addFacesConfigChangeListener(Class emfClass,
			IFacesConfigChangeListener listener);

	/**
	 * Removes an instance of {@link IFacesConfigChangeListener}.
	 * 
	 * @param emfClass EMF class in which the listener was interested.
	 * @return Removed {@link IFacesConfigChangeListener}, or null.
	 */
	public Object removeFacesConfigChangeListener(Class emfClass);

	/**
	 * Notifies {@link IFacesConfigChangeListener} instances of model changes
	 * in which they registered interest.
	 * 
	 * @param notification EMF {@link Notification} instance that describes the
	 * model change.
	 */
	public void notifyFacesConfigChangeListeners(
			Notification notification);

//	/**
//	 * Gets all {@link IJSFAppConfigProvider} instances from all
//	 * {@link IJSFAppConfigLocater} instances.
//	 * 
//	 * @return Set of all {@link IJSFAppConfigProvider} instances.
//	 */
//	public Set<IJSFAppConfigProvider> getJSFAppConfigProviders();

//	/**
//	 * Gets all {@link FacesConfigType} instances from all
//	 * {@link IJSFAppConfigProvider} instances.
//	 * 
//	 * @return List of all {@link FacesConfigType} instances.
//	 */
//	public List<FacesConfigType> getFacesConfigModels();

	/**
	 * Gets list of all ManagedBeanType instances from all known faces-config
	 * models; list may be empty.
	 * 
	 * @return List of all ManagedBeanType instances from all known
	 * faces-config models (list may be empty).
	 */
	public List<ManagedBeanType> getManagedBeans();

	/**
	 * @return List of all variable resolver class names registered. 
	 */
	public List<String> getPropertyResolvers();

	/**
	 * Gets list of all ValidatorType instances from all known faces-config
	 * models; list may be empty.
	 * 
	 * @return List of all ValidatorType instances from all known faces-config
	 * models (list may be empty).
	 */
	public List<ValidatorType> getValidators();

	/**
	 * @return List of all variable resolver class names registered. 
	 */
	public List<String> getVariableResolvers();

	/**
	 * Gets list of all ConverterType instances from all known faces-config
	 * models; list may be empty.
	 * 
	 * @return List of all ConverterType instances from all known faces-config
	 * models (list may be empty).
	 */
	public List<ConverterType> getConverters();

	/**
	 * Gets list of all NavigationRuleType instances from all known
	 * faces-config models; list may be empty.
	 * 
	 * @return List of all NavigationRuleType instances from all known
	 * faces-config models (list may be empty).
	 */
	public List<NavigationRuleType> getNavigationRules();

	/**
	 * Gets list of all NavigationRuleType instances from all known
	 * faces-config models where the navigation-rule's from-view-id value
	 * matches the web content folder-relative value of the passed IFile
	 * instance; list may be empty. Matching is performed in the same manner
	 * as for a JSF implementation's default NavigationHandler.
	 * 
	 * @param pageFile IFile instance to match against the from-view-id value
	 * of all NavigationRuleType instances. File is assumed to be relative to
	 * the web content folder, but may be expressed in a more complete form;
	 * its path will be calculated relative to the web content folder.
	 * @return List of all NavigationRuleType instances from all known
	 * faces-config models where the navigation-rule's from-view-id value
	 * matches the web content folder-relative value of the passed IFile
	 * instance (list may be empty).
	 */
	public List<NavigationRuleType> getNavigationRulesForPage(IFile pageFile);

	/**
	 * Gets list of all ApplicationType instances from all known
	 * faces-config models; list may be empty.
	 * 
	 * @return List of all ApplicationType instances from all known
	 * faces-config models (list may be empty).
	 */
	public List<ApplicationType> getApplications();

	/**
	 * Gets list of all FactoryType instances from all known faces-config
	 * models; list may be empty.
	 * 
	 * @return List of all FactoryType instances from all known faces-config
	 * models (list may be empty).
	 */
	public List<FactoryType> getFactories();

	/**
	 * Gets list of all ComponentType instances from all known faces-config
	 * models; list may be empty.
	 * 
	 * @return List of all ComponentType instances from all known faces-config
	 * models (list may be empty).
	 */
	public List<ComponentType> getComponents();

	/**
	 * Gets list of all ReferencedBeanType instances from all known
	 * faces-config models; list may be empty.
	 * 
	 * @return List of all ReferencedBeanType instances from all known
	 * faces-config models (list may be empty).
	 */
	public List<ReferencedBeanType> getReferencedBeans();

	/**
	 * Gets list of all RenderKitType instances from all known faces-config
	 * models; list may be empty.
	 * 
	 * @return List of all RenderKitType instances from all known faces-config
	 * models (list may be empty).
	 */
	public List<RenderKitType> getRenderKits();

	/**
	 * Gets list of all LifecycleType instances from all known faces-config
	 * models; list may be empty.
	 * 
	 * @return List of all LifecycleType instances from all known faces-config
	 * models (list may be empty).
	 */
	public List<LifecycleType> getLifecycles();

	/**
	 * @return the list of all resource bundles declared in all the FacesConfig
	 * configurations found.
	 */
	public List<ResourceBundleType> getResourceBundles();
	
	/**
	 * @return the list of faces config extensions declared in all FacesConfig 
	 * configurations found
	 */
	public List<FacesConfigExtensionType> getFacesConfigExtensions();
	
/* --------------------------  JSF 2.0 ---------------------------------------- */
	
	/**
	 * @return the list of behaviors declared in all FacesConfig configurations found
	 */
	public List<BehaviorType> getBehaviors();
	
	//do we want ordering?
	
	
/* ---------------------------------------------------------------------------- */
	/**
	 * Adds this instance's FacesConfigChangeAdapter instance to the
	 * passed application configuration model's adapters collection.
	 * 
	 * @param facesConfig Application configuration model's root object.
	 */
	public void addFacesConfigChangeAdapter(FacesConfigType facesConfig);

	/**
	 * Removes this instance's FacesConfigChangeAdapter instance from
	 * the passed application configuration model's adapters collection.
	 * 
	 * @param facesConfig Application configuration model's root object.
	 */
	public void removeFacesConfigChangeAdapter(
			FacesConfigType facesConfig);

}