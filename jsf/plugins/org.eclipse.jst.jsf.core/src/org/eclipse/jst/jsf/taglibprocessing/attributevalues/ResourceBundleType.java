/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Vadim Dmitriev - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.taglibprocessing.attributevalues;

import java.text.MessageFormat;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.jsf.core.internal.tld.LoadBundleUtil;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidValues;
import org.eclipse.jst.jsf.metadataprocessors.features.ValidationMessage;

/**
 * Meta-data processing type representing a path to resource bundle on classpath
 * Patch by Vadim Dmitriev.  See https://bugs.eclipse.org/bugs/show_bug.cgi?id=203307.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * @author Vadim Dmitriev
 * 
 * 
 */
public class ResourceBundleType extends PathType implements IValidValues 
{
	public boolean isValidValue( String value )
	{
		try
		{
			IProject project = getProject();
			IStorage bundle = LoadBundleUtil.getLoadBundleResource( project , value );
			if( bundle != null )
			{
				return true;
			}
		}
		catch (CoreException e) 
		{
			//error message is generated later
		}
		
		final String message = 
			MessageFormat.format(Messages.Bundle_not_found_rb, value); 
		getValidationMessages().add(new ValidationMessage(message, "", IStatus.ERROR)); //$NON-NLS-1$
		return false;
	}
}
