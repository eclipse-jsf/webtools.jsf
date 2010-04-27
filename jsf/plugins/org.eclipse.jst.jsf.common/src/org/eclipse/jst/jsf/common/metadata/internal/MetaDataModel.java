/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.common.metadata.internal;

import org.eclipse.jst.jsf.common.metadata.Model;

/**
 * Responsible for loading and holding onto the standard metadata model using the IDomainLoadingStrategy.
 * Wraps the model (root) with the strategy used for loading it, along with the identifying key (modelKeyDescriptor)
 */
public class MetaDataModel {

	private Object root;
	private IMetaDataModelContext modelContext;	
	private ModelKeyDescriptor modelKeyDescriptor;
	private IDomainLoadingStrategy strategy;
	private boolean refresh;


	
	/**
	 * Constructor
	 * @param context 
	 * @param strategy
	 */
	public MetaDataModel(final IMetaDataModelContext context, final IDomainLoadingStrategy strategy){
		this.modelContext = context;
		this.strategy = strategy;
	}

	/**
	 * @return the root of the model.  
	 */
	public Object getRoot(){
		return root;
	}
	
	/**
	 * @param root 
	 */
	public void setRoot(Object root){
		this.root = root;
		if (root != null) {
			//setCurrentModelContext has been deprecated... ModelKeyDescriptor can die after that
			Model m = (Model)root;
			m.setCurrentModelContext(getModelKey());
		}
	}
	
	private ModelKeyDescriptor getModelKey() {
		if (modelKeyDescriptor == null) {
			modelKeyDescriptor = (ModelKeyDescriptor)modelContext.getAdapter(ModelKeyDescriptor.class);
		}
		return modelKeyDescriptor;
	}
	
	/**
	 * @return modelContext
	 */
	public IMetaDataModelContext getModelContext(){
		return modelContext;
	}

//	public void accept(IEntityVisitor visitor){
//		if (getRoot() instanceof Model)
//			visitor.visit((Model)getRoot());
//	}

	/**
	 * @return true if the model is null or is not, in fact, a {@link Model}
	 */
	public boolean isEmpty() {
		if (root == null || !(root instanceof Model))
			return true;
				
		return false;
	}
	
	/**
	 * Load the model.  Delegates to the strategy.
	 */
	public synchronized void load(){
		StandardModelFactory.debug("> Begin Loading: "+modelContext.toString(), StandardModelFactory.DEBUG_MD_LOAD); //$NON-NLS-1$
		strategy.load(this);
		StandardModelFactory.debug("> End Loading: "+modelContext.toString(),StandardModelFactory.DEBUG_MD_LOAD); //$NON-NLS-1$
	}
	
	/**
	 * Reloads the model delegating to strategy reload
	 * @throws ModelNotSetException
	 */
	public void reload()throws ModelNotSetException{
		setRoot(null);
		refresh = false;
		strategy.reload();
	}

	/**
	 * @return flag indicating that the model is stale
	 */
	public boolean needsRefresh() {		
		return refresh;
	}
	
	/**
	 * Flag that model is stale
	 */
	public void setNeedsRefresh() {		
		refresh = true;
	}
	
	/**
	 * Cleans up the model releasing references.
	 */
	public void cleanup(){
		if (strategy != null)
			strategy.cleanup();
		strategy = null;
		root = null;
//		modelKeyDescriptor = null;
		modelContext = null;
	}
	
	public String toString() {
		StringBuffer buf = new StringBuffer("MetaDataModel: "); //$NON-NLS-1$
		buf.append(modelContext.toString());
		return buf.toString();
	}

}
