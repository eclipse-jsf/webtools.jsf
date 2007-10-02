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

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.wst.common.internal.emf.resource.Translator;

/**
 * Translator for the base name on a message bundle
 * 
 * @author cbateman
 *
 */
public class BaseNameTranslator extends Translator {

    /**
     * @param domNameAndPath
     * @param feature
     */
    public BaseNameTranslator(String domNameAndPath, EStructuralFeature feature) {
        super(domNameAndPath, feature);
    }

    public Translator[] getChildren()
    {
        FacesConfigPackage facesPackage = FacesConfigPackage.eINSTANCE;

        return new Translator[] {
            new Translator(TEXT_ATTRIBUTE_VALUE, facesPackage.getBaseNameType_TextContent()),
            new Translator("id", facesPackage.getBaseNameType_Id(), DOM_ATTRIBUTE) //$NON-NLS-1$
        };
    }
}
