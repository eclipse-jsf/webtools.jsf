/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
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
 * Translator for extension types
 *
 */
public abstract class ExtensionTypeTranslator extends Translator 
{
    /**
     * @param domNameAndPath
     * @param aFeature
     */
    public ExtensionTypeTranslator(String domNameAndPath, EStructuralFeature aFeature) 
    {
        super(domNameAndPath, aFeature);
    }
    
    public Translator[] getChildren() {
        
        FacesConfigPackage facesPackage = FacesConfigPackage.eINSTANCE;
        return new Translator[] {
              new Translator("id", facesPackage.getExtensionType_Id(), DOM_ATTRIBUTE), //$NON-NLS-1$
              new DynamicElementTranslator("*", facesPackage.getExtensionType_ChildNodes())
        };
    }
}
