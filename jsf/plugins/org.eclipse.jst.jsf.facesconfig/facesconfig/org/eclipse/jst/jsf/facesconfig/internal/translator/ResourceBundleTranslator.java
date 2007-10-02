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
 * Translator for resource bundle element 
 *
 */
public class ResourceBundleTranslator extends Translator {

    /**
     * @param domNameAndPath
     * @param feature
     */
    public ResourceBundleTranslator(String domNameAndPath,
            EStructuralFeature feature) {
        super(domNameAndPath, feature);
    }

    protected Translator[] getChildren() {
        
        FacesConfigPackage facesPackage = FacesConfigPackage.eINSTANCE;
        return new Translator[] {
            new DescriptionTranslator("description", facesPackage.getResourceBundleType_Description()), //$NON-NLS-1$
            new DisplayNameTranslator("display-name", facesPackage.getResourceBundleType_DisplayName()), //$NON-NLS-1$
            new IconTranslator("icon", facesPackage.getResourceBundleType_Icon()), //$NON-NLS-1$
            new BaseNameTranslator("base-name", facesPackage.getResourceBundleType_BaseName()), //$NON-NLS-1$
            new VarTranslator("var", facesPackage.getResourceBundleType_Var()), //$NON-NLS-1$
            new Translator("id", facesPackage.getResourceBundleType_Id(), DOM_ATTRIBUTE) //$NON-NLS-1$
        };
    }

}
