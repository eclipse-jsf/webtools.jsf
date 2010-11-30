package org.eclipse.jst.jsf.core.jsfappconfig;

import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jst.jsf.common.internal.managedobject.ObjectManager.ManagedObjectException;
import org.eclipse.jst.jsf.common.internal.resource.ResourceSingletonObjectManager;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.jsfappconfig.internal.AbstractJSFAppConfigManager;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;

/**
 * Default JSF AppConfig Manager
 * <p>
 * Mostly represents a merged representation of the Faces Configuration that the runtime would see
 * <p>
 * @deprecated - Helios
 */
public class JSFAppConfigManager 
		extends AbstractJSFAppConfigManager{
	
	/**
	 * UNUSED - here only for compatibility with original 
	 */
	public static final QualifiedName KEY_SESSIONPROPERTY =
		new QualifiedName(JSFCorePlugin.PLUGIN_ID, "JSFAppConfigManager"); //$NON-NLS-1$

	/**
	 * Constructor
	 * @param project
	 * 
	 */
	private JSFAppConfigManager(final IProject project) {
		super(project);	
	}

	private static JSFAppConfigManagerFactory FACTORY = new JSFAppConfigManagerFactory();

	
	/**
	 * @param project
	 * @return JSFAppConfigManager
	 * @deprecated 
	 */
	public static JSFAppConfigManager getInstance(final IProject project) {
		try {
		    return FACTORY.getInstance(project);
		} catch (ManagedObjectException e) {
			JSFCorePlugin.log(e, "Failed to get JSFAppConfigManager instance for "+project.getName()); //$NON-NLS-1$
		}
		return null;
	}
	
	/**
	 * @return Set of {@link IJSFAppConfigProvider}s
	 * @deprecated - SHOULD NOT USE
	 */
	public Set getJSFAppConfigProviders() {
		return super.getJSFAppConfigProviders();
	}

	/**
	 * Gets all {@link FacesConfigType} instances from all
	 * {@link IJSFAppConfigProvider} instances.
	 * 
	 * @return List of all {@link FacesConfigType} instances.
	 * @deprecated - SHOULD NOT USE
	 */
	public List getFacesConfigModels() {
		return super.getFacesConfigModels();
	}

	public List getManagedBeans() {
		return super.getManagedBeans();
	}

	public List getValidators() {
		return super.getValidators();
	}

	public List getConverters() {
		return super.getConverters();
	}

	public List getNavigationRules() {
		return super.getNavigationRules();
	}

	public List getNavigationRulesForPage(final IFile pageFile) {
		return super.getNavigationRulesForPage(pageFile);
	}

	public List getApplications() {
		return super.getApplications();
	}

	public List getFactories() {
		return super.getFactories();
	}

	public List getComponents() {
		return super.getComponents();
	}

	public List getReferencedBeans() {
		return super.getReferencedBeans();
	}

	public List getRenderKits() {
		return super.getRenderKits();
	}

	public List getLifecycles() {
		return super.getLifecycles();
	}

	public List getResourceBundles() {
		return super.getResourceBundles();
	}
	
	public List getFacesConfigExtensions() {
		return super.getFacesConfigExtensions();
	}

	private static class JSFAppConfigManagerFactory 
		extends ResourceSingletonObjectManager<JSFAppConfigManager, IProject> {

		@Override
        protected IAdaptable unsafeRunBeforeGetInstance(IProject project) {
            super.unsafeRunBeforeGetInstance(project);

            if (!isInstance(project) && Job.getJobManager().currentRule() == null) {
                    //Acquire a scheduling rule on the project so creation of a JSFAppConfigManager
                    //does not attempt to acquire a rule which may be unavailable while still
                    //synchronized on the ResourceSingletonObjectManager (JSFAppConfigManagerFactory)
                    Job.getJobManager().beginRule(project, null);
                    return new AdaptableBoolean(true);
            }
            return null;
        }

        @Override
        protected void unsafeRunAfterGetInstance(IProject project, IAdaptable stateObject) {
            super.unsafeRunAfterGetInstance(project, stateObject);

            if (stateObject instanceof AdaptableBoolean && ((AdaptableBoolean)stateObject).acquiredRule) {
                Job.getJobManager().endRule(project);
            }
        }

        protected JSFAppConfigManagerFactory() {
			super(ResourcesPlugin.getWorkspace());			
		}

		@Override
		protected JSFAppConfigManager createNewInstance(final IProject project) {			
			return new JSFAppConfigManager(project);
		}
		
		private static class AdaptableBoolean implements IAdaptable {
		    private final boolean acquiredRule;
		    
		    public AdaptableBoolean(boolean acquiredRule) {
		        this.acquiredRule = acquiredRule;
		    }

            public Object getAdapter(Class adapter) {
                return null;
            }
		    
		    
		}
		
	}
}
