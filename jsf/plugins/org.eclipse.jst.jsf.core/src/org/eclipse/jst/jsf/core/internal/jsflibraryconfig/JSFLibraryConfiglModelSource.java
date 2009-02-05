/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Justin Chen
 *******************************************************************************/
package org.eclipse.jst.jsf.core.internal.jsflibraryconfig;

import java.util.List;

import org.eclipse.jst.jsf.core.internal.project.facet.IJSFFacetInstallDataModelProperties.IMPLEMENTATION_TYPE;

/**
 * Interface for data source to instanciate a <b>JSFLibraryConfigModel</b> object. 
 * 
 * @author Justin Chen - Oracle
 */
public interface JSFLibraryConfiglModelSource {
 	/**
	 * Return a saved JSF implementation library.  
	 * Depends on the model source, it could be sticky values from DialogSettings or 
	 * project property values.  
	 * 
	 * A null could be returned when creating first web project in a new workspace.
	 * 
 	 * @return JSFLibraryInternalReference
 	 */
 	public JSFLibraryInternalReference getJSFImplementationLibrary();
 	
 	/**
	 * Return a list of saved JSF component libraries.
	 * Otheriwse, return an empty list.
	 *  
 	 * @return List
 	 */
 	public List getJSFComponentLibraries(); 
 	
 	/**
	 * Return type of implementation
	 *  
 	 * @return {@link IMPLEMENTATION_TYPE}
 	 */
 	public IMPLEMENTATION_TYPE getImplementationType(); 	
}
