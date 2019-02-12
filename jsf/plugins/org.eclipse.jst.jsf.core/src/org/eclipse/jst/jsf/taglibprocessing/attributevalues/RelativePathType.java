/*******************************************************************************
 * Copyright (c) 2007, 2013 Oracle Corporation.
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
package org.eclipse.jst.jsf.taglibprocessing.attributevalues;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.jsf.metadataprocessors.IMetaDataEnabledFeature;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidValues;
import org.eclipse.jst.jsf.metadataprocessors.features.ValidationMessage;

/**
 * Path is relative to project
 * <b>EXPERIMENTAL</b> - may change or dissappear
 * 
 */
public class RelativePathType extends PathType implements
		IMetaDataEnabledFeature, IValidValues{

	public boolean isValidValue(String value) {
		//is this a relative path, or absolute url?
		try {
			new URL(value);			
			//not much else we what to do for now
			return true;
		} catch (MalformedURLException e) {
			//is this a valid path relative to the 			
			IProject project = getProject2();
			if (project != null)
			{
    			IFile  file= project.getFile(new Path(value));
    			if (! file.exists())
    				getValidationMessages().add(new ValidationMessage( value+Messages.RelativePathType_0));
			}			
			return getValidationMessages().size() == 0;
		}
	}

}
