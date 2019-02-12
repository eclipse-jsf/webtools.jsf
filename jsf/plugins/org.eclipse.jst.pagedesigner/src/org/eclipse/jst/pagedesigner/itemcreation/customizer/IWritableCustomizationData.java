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
/**
 * 
 */
package org.eclipse.jst.pagedesigner.itemcreation.customizer;

import org.eclipse.jst.jsf.common.dom.TagIdentifier;


/**
 * Represents a writable customization data object.
 * 
 * @see ICustomizationData 
 * 
 * @author Debajit Adhikary
 *
 */
public interface IWritableCustomizationData extends ICustomizationData
{
    //------------------------------------------------------------
    // Methods to set the tag identifier information  
    //------------------------------------------------------------

    
    /**
     * Sets the tag identifier associated with a customization data object
     * 
     * @param tagId
     *            The tag identifier to set it to
     *            
     */
    public void setTagIdentifier(TagIdentifier tagId);
    
    
    //------------------------------------------------------------
    // Methods to set attribute data
    //------------------------------------------------------------

    
    /**
     * Sets the attribute data for an existing customization data object
     * 
     * @param attrs
     *            The AttributeData object to set it to
     *            
     */
    public void setAttributes(AttributeData attrs);

    
    /**
     * Adds one attribute to an existing customization data object.
     * 
     * @param attributeName
     *            The name of the attribute
     * @param attributeValue
     *            The value of this attribute
     * 
     */
    public void addAttribute(String attributeName, String attributeValue);

    
    //------------------------------------------------------------
    // Methods to set child data 
    //------------------------------------------------------------
    

    /**
     * Sets the child tag(s) customization information for an existing customization
     * data object
     * 
     * @param data
     *            The ChildrenData object to set it to
     * 
     */
    public void setChildrenData(ChildrenData data);
    
    
    /**
     * Adds a child to an existing tag. (Adds the child's customization data to
     * the existing tag's customization data)
     * 
     * @param childrenData
     *            Customization Data for a child
     * 
     */
    public void addChildData (ICustomizationData childrenData);

    
    //------------------------------------------------------------
    // Methods to set parent customization data
    //------------------------------------------------------------
    
    /**
     * Sets parent customization information for an existing tag's customization
     * data
     * 
     * @param parentData
     *            The parent data to set it to
     * 
     */
    public void setParentData(ParentData parentData);

    
    /**
     * Attaches a parent to a tag (The customization data for a parent is added
     * to the tag's customization data). The oldest parent (outermost container
     * tag) should be attached (added) first.
     * 
     * @param parentData
     *            Parent customization data
     */
    public void addParentData (ICustomizationData parentData);
    
}
