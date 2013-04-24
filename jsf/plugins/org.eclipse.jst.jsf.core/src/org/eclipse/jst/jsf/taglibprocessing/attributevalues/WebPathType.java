/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 		Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.taglibprocessing.attributevalues;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.jsf.metadataprocessors.IMetaDataEnabledFeature;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidValues;
import org.eclipse.jst.jsf.metadataprocessors.features.ValidationMessage;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

/**
 * Path relative to web root
 * <b>EXPERIMENTAL</b> - may change or dissappear
 *
 */
public class WebPathType extends PathType implements
	IMetaDataEnabledFeature, IValidValues{

	public boolean isValidValue(String value) {
		//is this a relative path, or absolute url?
		try {
			URL url = new URL(value);			
			//if file protocol, see if it is valid?  Otherwise assume ok.
			if (url.getProtocol().equals("file")){ //$NON-NLS-1$
				validateFileRelativeToWebRoot(url.getPath());
			} 
		} catch (MalformedURLException e) {
			//is this a valid path relative to the
			if (value != null && value.length() > 1 && value.charAt(0) == '/') { 
				validateFileRelativeToWebRoot(value);						
			} else {
				//Bug 325490 - [JSF2.0] False warning from facelet validator when working with facelet pages in a sub-folder
				validateFileRelativeToCurrentFile(value);
			}
		}
		return getValidationMessages().size() == 0;

	}

    private void validateFileRelativeToWebRoot(String value) {
		IContainer webRoot = getWebRoot();
		if (webRoot == null)
		{
		    return;
		}
		if (! webRoot.exists()){
			getValidationMessages().add(new ValidationMessage( Messages.WebPathType_1));
		} 
		else {
			IFile file = webRoot.getFile(new Path(value));
			if (!file.exists()) {
				//was this a valid file path string, or bogus url?
				getValidationMessages().add(new ValidationMessage(Messages.WebPathType_2));
			}									
			//we could also validate the expected file-extensions from meta data
		}
		
	}

	/**
	 * @return the web root
	 */
	protected IContainer getWebRoot()
    {
        IProject project = getProject2();
        if (project != null)
        {
            IVirtualComponent component = ComponentCore.createComponent(project);
            if (component != null)
            {
                return component.getRootFolder().getUnderlyingFolder();
            }
        }
        return null;
    }

	//Bug 325490 - [JSF2.0] False warning from facelet validator when working with facelet pages in a sub-folder
    private void validateFileRelativeToCurrentFile(String value) {
		IProject project = getProject2();
		if (project != null)
		{
            IVirtualComponent component = ComponentCore.createComponent(project);
    		if (component != null)
    		{
                IPath webContentPath = component.getRootFolder().getUnderlyingFolder().getFullPath();
        		IResource resource = getFile2();
        		if (resource != null)
        		{
                    IPath filePath = resource.getFullPath();
            		if (filePath.matchingFirstSegments(webContentPath) == webContentPath.segmentCount()) {
            			filePath = filePath.removeFirstSegments(webContentPath.segmentCount());
            			filePath = filePath.removeLastSegments(1);
            			filePath = filePath.append(value);
            			IFile file = getWebRoot().getFile(filePath);
            			if (!file.exists()){
            				getValidationMessages().add(new ValidationMessage(Messages.WebPathType_2));
            			}
            		}
        		}
    		}
		}
	}

}