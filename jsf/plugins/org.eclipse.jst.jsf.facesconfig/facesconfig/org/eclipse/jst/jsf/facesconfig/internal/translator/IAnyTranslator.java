package org.eclipse.jst.jsf.facesconfig.internal.translator;

import org.eclipse.jst.jsf.facesconfig.emf.DynamicElement;
import org.eclipse.wst.common.internal.emf.resource.Translator;
import org.w3c.dom.Node;

/**
 * A translator that can handle the DTD ANY and XSD anyType XML
 * 
 * @author cbateman
 *
 */
public interface IAnyTranslator {
    /**
     * Allows an ANY translator for elements to 
     * dynamically inject attribute translators
     * based on the contents of the Element node's
     * runtime attribute values
     * 
     * @return a list of translators for attributes
     * of dynamic elements
     */
    Translator[]  getDynamicAttributeTranslators(Node element);
    
    /**
     * Allows an ANY translator for elements to
     * dynamically inject attribute translators
     * based on the contents of an DynamicElement that
     * may not already be in the corresponding DOM element
     * 
     * @param element
     * @return a list of translators for attributes of dynamic elements
     */
    Translator[]  getDynamicAttributeTranslators(DynamicElement element);
}
