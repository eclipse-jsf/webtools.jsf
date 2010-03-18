package org.eclipse.jst.jsf.common.runtime.internal.view.model.common;

import java.io.Serializable;

/**
 * Information about a tags attribute
 * @author cbateman
 *
 */
public interface ITagAttribute extends Serializable
{
    /**
     * @return the name of the attribute.  In XML this is the "local" name of
     * the attribute: that is, it's name without any namespace qualification.
     */
    String getName();
    
    /**
     * @return the unique namespace of the attribute or null if it is always the
     * same as it's owner (the typical case in JSF).
     */
    String getTargetNamespace();
    
    /**
     * @return a long-form piece of text, intended for end-user consumption,
     * that describes the attribute
     */
    String getDescription();
    
    /**
     * @return a short, user visible label text for the attribute.
     */
    String getDisplayName();
    
    /**
     * @return true if the attribute is required to be populated on the 
     * element.
     */
    boolean isRequired();
}
