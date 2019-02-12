/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.internal;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.core.runtime.content.IContentTypeManager;

/**
 * Utility JSP methods
 * @author cbateman
 *
 */
public final class JSPUtil 
{
    private final static String  CTYPE_JSPSOURCE = 
        "org.eclipse.jst.jsp.core.jspsource"; //$NON-NLS-1$
    private final static String  CTYPE_JSPFRAGMENTSOURCE = 
        "org.eclipse.jst.jsp.core.jspfragmentsource"; //$NON-NLS-1$
    /**
     * @param contentType
     * @return true if contentType is one of the content types registered
     * for JSP files
     */
    public static boolean isJSPContentType(final String contentType)
    {
        return CTYPE_JSPSOURCE.equals(contentType)
                    || CTYPE_JSPFRAGMENTSOURCE.equals(contentType);
    }
    
    /**
     * @param file
     * @return true if file is associated with a JSP or JSP fragment content type
     */
    public static boolean isJSPContentType(final IFile file)
    {
    	final boolean isJSPSource = isJSPSource(file);
        if (isJSPSource)
        {
            return true;
        }

        final boolean isJSPFragment = isJSPFragment(file);
        
        if  (isJSPFragment)
        {
        	return true;
        }
        
        return false;
    }
    
    private JSPUtil()
    {
        // no instantiation
    }

	/**
	 * @param file
	 * @return true if file is associated with the JSP source content type
	 * (returns if JSP fragment)
	 */
	public static boolean isJSPSource(IFile file) {
		return isAssociatedWithContentType(file, CTYPE_JSPSOURCE);
	}

	/**
	 * @param file
	 * @return true if the file is associated with the JSP fragment content type
	 */
	public static boolean isJSPFragment(IFile file) {
		return isAssociatedWithContentType(file, CTYPE_JSPFRAGMENTSOURCE);
	}
	
	private static boolean isAssociatedWithContentType(final IFile file, final String contentType)
	{
        final IContentTypeManager typeManager = Platform.getContentTypeManager();
        IContentType jspContentType = 
            typeManager.getContentType(contentType);
        if (jspContentType != null
                && jspContentType.isAssociatedWith(file.getName()))
        {
            return true;
        }
      
        return false;
	}
}
