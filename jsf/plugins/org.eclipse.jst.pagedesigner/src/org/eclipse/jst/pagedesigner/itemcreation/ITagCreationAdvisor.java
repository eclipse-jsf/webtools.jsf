package org.eclipse.jst.pagedesigner.itemcreation;

import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.w3c.dom.Element;

/**
 * An advisor object that allows customization of tag creation in an ITagCreator
 * 
 * Clients should *not* implement this interface.  Extend AbstractTagCreationAdvisor
 * or DefaultTagCreationAdvisor instead.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * @author cbateman
 *
 */
public interface ITagCreationAdvisor
{
    /**
     * @param model
     * @param domPosition
     * @return a new IDOMPosition adjust for required containers or domPosition
     * if no adjust is required.  ???May return null???
     */
    IDOMPosition checkAndApplyNecessaryContainers(IDOMModel model,
            IDOMPosition domPosition);

    /**
     * Apply customization to tagElement.  May use to modify
     * attributes and child elements of the tag.
     * 
     * @param model
     * @param tagElement
     * @param tagToolItem
     */
    void applyCustomization(IDOMModel model, Element tagElement);
}
