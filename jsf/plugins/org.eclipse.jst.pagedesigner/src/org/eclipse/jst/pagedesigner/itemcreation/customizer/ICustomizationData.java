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

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;

/**
 * Represents a read-only customization data object. Customization data for a
 * tag includes its identifier, attribute data, child tag information and parent
 * tag information.
 * 
 * @see IWritableCustomizationData
 * 
 * @author prusev
 * @author Debajit Adhikary
 * 
 */
public interface ICustomizationData extends IAdaptable  
{
    /**
     * Returns the TagIdentifier for the tag whose customization data this is
     * 
     * @return the TagIdentifier 
     */
    public TagIdentifier getTagIdentifier();
    
    
    /**
     * Returns the parent data associated with this customization data
     * 
     * @return the attribute information for this customization data
     */
    public AttributeData getAttributeData();

    
    /**
     * Returns the children data associated with this customization data
     * 
     * @return the ChildrenData for this customization data
     */
    public ChildrenData getChildrenData();

    
    /**
     * Returns the parent data associated with this customization data
     * 
     * @return the parent data for this customization data 
     */
    public ParentData getParentData();
}
