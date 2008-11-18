/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.core;

/**
 * @author gekessle
 *
 */
public enum JSFVersion {
	
	/**
	 * Unknown version support
	 */
	UNKNOWN,
	/**
	 * Supports JSF Version 1.0
	 */
	V1_0,
	/**
	 * Supports JSF Version 1.1
	 */
	V1_1,
	/**
	 * Supports JSF Version 1.1
	 */
	V1_2;
	
    @Override
    public String toString() {
        switch(this)
        {
            case V1_0:
                return IJSFCoreConstants.JSF_VERSION_1_0;
            case V1_1:
                return IJSFCoreConstants.JSF_VERSION_1_1;
            case V1_2:
                return IJSFCoreConstants.JSF_VERSION_1_2;
            case UNKNOWN:
                return "unknown"; //$NON-NLS-1$
            default:
                throw new IllegalStateException("Missing string conversion: "+this.name()); //$NON-NLS-1$
        }
    }

    /**
     * @param valueAsString
     * @return the reverse mapping to an enum from toString() returns
     */
    public static JSFVersion valueOfString(String  valueAsString)
    {
        if (IJSFCoreConstants.JSF_VERSION_1_0.equals(valueAsString))
        {
            return V1_0;
        }
        else if (IJSFCoreConstants.JSF_VERSION_1_1.equals(valueAsString))
        {
            return V1_1;
        }
        else if (IJSFCoreConstants.JSF_VERSION_1_2.equals(valueAsString))
        {
            return V1_2;
        }
        else if ("unknown".equals(valueAsString)) //$NON-NLS-1$
        {
            return UNKNOWN;
        }
        else
        {
            return null;
        }
    }
}
