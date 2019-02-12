/*******************************************************************************
 * Copyright (c) 2007, 2010 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.common.metadata.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;

/**
 * Default class used for loading metadata.  
 * Loads the source types from extensions defined against the domain.
 * 
 * see org.eclipse.jst.jsf.common.domainLoadingStrategies ext-pt
 */
public class DomainLoadingStrategy implements IDomainLoadingStrategy, IMetaDataObserver {

	/**
	 * Domain id
	 */
	protected String domain;

	private MetaDataModel _model;
	private List <IDomainSourceModelType> _sourceTypes;
	private List <IMetaDataSourceModelProvider> _sources;
	
	/**
	 * Constructor
	 * @param domain
	 */
	public DomainLoadingStrategy(String domain){
		this.domain = domain;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.IDomainLoadingStrategy#load(org.eclipse.jst.jsf.common.metadata.internal.MetaDataModel)
	 */
	public void load(MetaDataModel model) {
		this._model = model;
		_sourceTypes = loadDomainSourceModelTypes();
		sortSourceTypes(_sourceTypes);
		_sources = locateMetaDataSourceInstances(_sourceTypes, model);
	    mergeModel(model, _sources);		
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.IDomainLoadingStrategy#reload()
	 */
	public void reload() throws ModelNotSetException {
		//System.out.println("reload");//debug //$NON-NLS-1$
		if (_model == null)
			throw new ModelNotSetException();
		
		removeOldLocatorObservers();
		_sources = locateMetaDataSourceInstances(_sourceTypes, _model);
	    mergeModel(_model, _sources);		
	}
	
	/**
	 * Responsible for iterating through the sorted list of <code>IMetaDataSourceModelProvider</code>
	 * and merging the models after first translating the source model as required, into a single mreged model of
	 * standard metadata Entities and Traits.
	 * @param model 
	 * @param sources
	 */
	protected void mergeModel(final MetaDataModel model, final List <IMetaDataSourceModelProvider> sources) {		

		StandardModelFactory.debug(">> Begin Merge: "+model.getModelContext()+"("+sources.size()+ " sources)", StandardModelFactory.DEBUG_MD_LOAD); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		final IMetaDataModelMergeAssistant assistant = createModelMergeAssistant(model);
		for (final IMetaDataSourceModelProvider mds : sources){
			final Iterator translators = mds.getLocator().getDomainSourceModelType().getTranslators().iterator();
			while (translators.hasNext()){
				final IMetaDataTranslator translator = (IMetaDataTranslator)translators.next();
				if (translator.canTranslate(mds)){
					StandardModelFactory.debug(">>> Merging: "+model.getModelContext()+"::"+mds, StandardModelFactory.DEBUG_MD_LOAD);  //$NON-NLS-1$//$NON-NLS-2$
					assistant.setSourceModelProvider(mds);
					try {
						translator.translate(assistant);
					} catch (Exception e) {							
						StandardModelFactory.debug(">>>> Error during translate/merge of: "+model.getModelContext()+": "+mds, StandardModelFactory.DEBUG_MD_LOAD);															 //$NON-NLS-1$ //$NON-NLS-2$
						JSFCommonPlugin.log(IStatus.ERROR, "Error during load of: "+mds, e); //$NON-NLS-1$
					}
				}				
			}
		}
		assistant.setMergeComplete();
		StandardModelFactory.debug(">> End Merge: "+model.getModelContext(),StandardModelFactory.DEBUG_MD_LOAD); //$NON-NLS-1$
	}
	
	/**
	 * @param model
	 * @return an instance of a IMetaDataModelMergeAssistant to be used while merging source models
	 */
	protected IMetaDataModelMergeAssistant createModelMergeAssistant(MetaDataModel model){
		return new MetaDataModelMergeAssistantImpl(model);		
	}

	/**
	 * Allows for subclasses to override the default mechanism for sorting the source types.
	 * @param sourceTypes
	 */
	protected void sortSourceTypes(List <IDomainSourceModelType> sourceTypes) {
		//allows override
	}

	/**
	 * @return list of <code>IDomainSourceModelType</code>s located in the <code>DomainSourceTypesRegistry</code> 
	 * for the specified uri
	 */
	protected List <IDomainSourceModelType> loadDomainSourceModelTypes() {
		return DomainSourceTypesRegistry.getInstance().getDomainSourceTypes(domain); 
	}

	/**
	 * @param sourceTypes
	 * @param model
	 * @return list of <code>IMetaDataSourceModelProvider</code> instances from the domain source types applicable for 
	 * this domain for this particular uri specified in the model
	 */
	protected List <IMetaDataSourceModelProvider> locateMetaDataSourceInstances(final List <IDomainSourceModelType> sourceTypes, MetaDataModel model) {
		final List<IMetaDataSourceModelProvider> sources = new ArrayList<IMetaDataSourceModelProvider>();	
		final IProject project = getProject(model);
		for (final IDomainSourceModelType sourceType : sourceTypes){
			final IMetaDataLocator locator = sourceType.getLocator(project);
			if (locator != null) {
				//We MUST set the sourceType here to associate the handler with locator to use for the source models
				locator.setDomainSourceModelType(sourceType);
								
				final List <IMetaDataSourceModelProvider> providers = locator.locateMetaDataModelProviders(model.getModelContext().getModelIdentifier());
				if (providers != null && !providers.isEmpty()){
					for (final IMetaDataSourceModelProvider provider : providers){
						//We MUST set the sourceType here to associate the translators to use for the source models
						provider.setLocator(locator);
						sources.add(provider);
					}
				}
				//listen for changes
				locator.addObserver(this);
			}
		}
		return sources;
	}
	
	private IProject getProject(final MetaDataModel model) {
		return (IProject)model.getModelContext().getAdapter(IProject.class);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.IMetaDataObserver#notifyMetadataChanged(org.eclipse.jst.jsf.common.metadata.internal.IMetaDataChangeNotificationEvent)
	 */
	public void notifyMetadataChanged(final IMetaDataChangeNotificationEvent event) {
		//for now, if any event occurs, we need to flush the _model so that it will rebuild
		_model.setNeedsRefresh();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.IDomainLoadingStrategy#cleanup()
	 */
	public void cleanup(){
		removeOldLocatorObservers();
		_sources = null;
		_sourceTypes = null;
		_model = null;
	}
	
	private void removeOldLocatorObservers(){
		if (_sources != null){
			for (final IMetaDataSourceModelProvider provider :  _sources){							
				if (provider != null) {
					final IMetaDataLocator locator = provider.getLocator();
					if (locator != null){
						locator.removeObserver(this);		
						locator.setDomainSourceModelType(null);
						provider.setLocator(null);
					}
				}
			}
		}
	}

}
