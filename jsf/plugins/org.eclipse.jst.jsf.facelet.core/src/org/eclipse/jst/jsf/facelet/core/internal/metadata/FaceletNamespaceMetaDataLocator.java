package org.eclipse.jst.jsf.facelet.core.internal.metadata;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.jst.jsf.common.metadata.internal.AbstractMetaDataLocator;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataChangeNotificationEvent;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataLocator;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataObserver;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataSourceModelProvider;
import org.eclipse.jst.jsf.common.metadata.internal.IPathSensitiveMetaDataLocator;
import org.eclipse.jst.jsf.common.metadata.internal.MetaDataChangeNotificationEvent;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.Namespace;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.internal.CompositeTagRegistryFactory;
import org.eclipse.jst.jsf.core.internal.CompositeTagRegistryFactory.TagRegistryIdentifier;
import org.eclipse.jst.jsf.core.jsfappconfig.JSFAppConfigUtils;
import org.eclipse.jst.jsf.core.metadata.internal.INamespaceModelProvider;
import org.eclipse.jst.jsf.designtime.internal.view.model.ITagRegistry;
import org.eclipse.jst.jsf.designtime.internal.view.model.ITagRegistry.ITagRegistryListener;
import org.eclipse.jst.jsf.designtime.internal.view.model.ITagRegistry.TagRegistryChangeEvent;
import org.eclipse.jst.jsf.facelet.core.internal.FaceletCorePlugin;
import org.eclipse.wst.common.project.facet.core.FacetedProjectFramework;
import org.eclipse.wst.common.project.facet.core.events.IFacetedProjectEvent;
import org.eclipse.wst.common.project.facet.core.events.IFacetedProjectEvent.Type;
import org.eclipse.wst.common.project.facet.core.events.IFacetedProjectListener;
import org.eclipse.wst.common.project.facet.core.events.IProjectFacetActionEvent;


/**
 * Locates Facelet {@link Namespace} metadata
 */
public class FaceletNamespaceMetaDataLocator 
		extends AbstractMetaDataLocator
		implements IPathSensitiveMetaDataLocator, ITagRegistryListener, IFacetedProjectListener {
	
	//in the future if there is an explicit xhtml content type id, we will need to use that here
	private static final IContentType XHTML_CONTENTTYPE = 
			Platform.getContentTypeManager().getContentType("org.eclipse.wst.html.core.htmlsource"); //$NON-NLS-1$
	
	private IProject _project;
	private ITagRegistry _reg; 

	public List<IMetaDataSourceModelProvider> locateMetaDataModelProviders(final String uri) {
		final List<IMetaDataSourceModelProvider> providers = new ArrayList<IMetaDataSourceModelProvider>();
		if (_reg != null) {
			final Namespace ns = _reg.getTagLibrary(uri);
		    
			if (ns != null) {
		    	providers.add(new NamespaceSourceModel(ns));
		    }
		}
        return providers;
	}

	public void startLocating() {
		if (_project != null && JSFAppConfigUtils.isValidJSFProject(_project) 
			&& JSFVersion.valueOfProject(_project).compareTo(JSFVersion.V2_0) >=0 ) {
				
				final TagRegistryIdentifier tagRegId = new TagRegistryIdentifier(_project, XHTML_CONTENTTYPE);
				_reg = CompositeTagRegistryFactory.getInstance().getRegistry(tagRegId);
				if (_reg != null) {
					_reg.addListener(this);
				}
		}
		
		//add faceted project listener that will check for JSF facet version
		//being added and the facelet registry not being initialized
		FacetedProjectFramework.addListener(this, Type.POST_INSTALL);
	}

	public void stopLocating() {
		if (_reg != null) {
			_reg.removeListener(this);
			_reg = null;
		}
		FacetedProjectFramework.removeListener(this);
	}

	public void setProjectContext(final IProject project) {
		_project = project;
	}

	public void registryChanged(final TagRegistryChangeEvent changeEvent) {
		for (final Namespace ns : changeEvent.getAffectedObjects()) {
			if (adaptTagRegistryEvent(changeEvent) != IMetaDataChangeNotificationEvent.ADDED) {
				final IMetaDataChangeNotificationEvent mdEvent = new MetaDataChangeNotificationEvent(this, ns.getNSUri(), adaptTagRegistryEvent(changeEvent));
				fireEvent(mdEvent);
			}
		}
	}

	private int adaptTagRegistryEvent(final TagRegistryChangeEvent event) {
		switch (event.getType()) {
		case ADDED_NAMESPACE:
			return IMetaDataChangeNotificationEvent.ADDED;
		case REMOVED_NAMESPACE:			
		case REGISTRY_DISPOSED:
			return IMetaDataChangeNotificationEvent.REMOVED;
		default:
			return IMetaDataChangeNotificationEvent.CHANGED;
		}
	}
	
	private void fireEvent(final IMetaDataChangeNotificationEvent event) {
		SafeRunnable.run(new ISafeRunnable(){

			public void handleException(Throwable exception) {
				FaceletCorePlugin.log("Error while firing metadataChangeNotificationEvent" , exception)	; //$NON-NLS-1$
			}
	
			public void run() throws Exception {				
				for (final IMetaDataObserver observer : getObservers()){					
					observer.notifyMetadataChanged(event);
				}
			}
	
		});
	}
	
	public void handleEvent(final IFacetedProjectEvent event) {
		if (event.getProject().getProject() == _project) {
			final IProjectFacetActionEvent ev = (IProjectFacetActionEvent)event;
			if (ev.getProjectFacet().getId()
					.equals(IJSFCoreConstants.JSF_CORE_FACET_ID)) { 	
				//if jsf facet has been added, it may be a 2.0 faceted project now
				//call start locating again to add listener to facelet registry
				//if not already doing so.
				//only have to look at POST_INSTALL event since there is no version_changes option
				//currently with the JSF Facet
				if (ev.getType() == Type.POST_INSTALL && _reg == null) {
					startLocating();
				}
			}
		}
	}
	private class NamespaceSourceModel implements INamespaceModelProvider {

		private Namespace ns;
		private IMetaDataLocator locator;

		NamespaceSourceModel(final Namespace ns){
			this.ns = ns;
		}
		
		public Object getSourceModel() {
			return ns;
		}

		public IMetaDataLocator getLocator() {
			return locator;
		}

		public void setLocator(IMetaDataLocator locator) {
			this.locator = locator;			
		}

		public Object getAdapter(Class klass) {
			return null;
		}

		public Namespace getNamespace() {
			return ns;
		}
	}

}
