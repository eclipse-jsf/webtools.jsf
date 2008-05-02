/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.dom;

/**
 * Uniquely identifies a named attribute on a tag usint TagIdentifier as a way
 * to uniquely identify the host tag.  All instances should be considered 
 * immutable and idempotent.  Factories may cache copies as transparent 
 * singletons for unique TagIdentifier/attribute.
 * 
 * THIS CLASS IS NOT API AND SHOULD NOT BE USED
 * 
 * @author cbateman
 *
 */
public abstract class AttributeIdentifier
{
    /**
     * @return the attribute name (local name, namespace prefix is currently ignored)
     */
    public abstract String getName();
    
    /**
     * @return the tag identifier
     */
    public abstract TagIdentifier getTagIdentifier();
    
    public final boolean equals(Object compareTo)
    {
        if (compareTo instanceof AttributeIdentifier)
        {
            return isSameAttributeType((AttributeIdentifier) compareTo);
        }
        return false;
    }
    
    public final int hashCode()
    {
        // use toLowerCase to ensure equals matches
        int hashCode = getName().toLowerCase().hashCode();
        
        int tagCode = getTagIdentifier().hashCode();
       
        hashCode = hashCode ^ tagCode;
        
        return hashCode;
    }

    /**
     * @param attributeId
     * @return true if attributeId represents the same attribute as this.
     */
    public final boolean isSameAttributeType(AttributeIdentifier attributeId)
    {
        // if same object, always true
        if (attributeId == this)
        {
            return true;
        }
        

        // if tag identifiers not same, then always false.
        if (!getTagIdentifier().isSameTagType(attributeId.getTagIdentifier()))
        {
            return false;
        }

        // if tag id is the same, the tag name must be too.
        return getName().toLowerCase().equals(attributeId.getName().toLowerCase());
    }

}
