/*******************************************************************************
 * Copyright (c) 2001, 2006 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
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
     * @param element 
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
