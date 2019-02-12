/*******************************************************************************
 * Copyright (c) 2008, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.itemcreation.customizer;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to represent parent tag customization data for a tag. This information
 * is stored as a List of ICustomizationData objects, where each
 * ICustomizationData object belongs to one parent tag. (The outermost parent tag
 * (oldest ancestor) should be added first to this list)
 * 
 * @author Debajit Adhikary
 * 
 */
public class ParentData
{
	/**
	 * List of ICustomizationData objects to store parent tag information. The
	 * outermost parent tag should be added first. 
	 */
	private List<ICustomizationData> parentList;

    
    /**
     * Default constructor
     * 
     */
    public ParentData ()
    {
        parentList = new ArrayList<ICustomizationData>();
    }
    
    
    /**
	 * @param parentData
	 *            Customization data to add for the parent tag
	 *            
	 */
    public void add (ICustomizationData parentData)
    {
        this.parentList.add(parentData);
    }

    
    /**
	 * Returns the Parent customization data as a list of customization objects.
	 * 
	 * @return The parent customization data
	 * 
	 */
    public List<ICustomizationData> getParentCustomizationData ()
    {
        return parentList;
    }

    
    /**
     * @param parentList List of ICustomizationData to set it to
     * 
     */
    public void setParentCustomizationData (List<ICustomizationData> parentList)
    {
        this.parentList = parentList;
    }
}
