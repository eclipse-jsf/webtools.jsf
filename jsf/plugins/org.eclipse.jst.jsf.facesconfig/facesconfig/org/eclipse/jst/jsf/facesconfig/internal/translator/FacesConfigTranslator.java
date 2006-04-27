/***************************************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM Corporation - initial API and implementation
 *   Oracle Corporation - added id attribute
 **************************************************************************************************/
package org.eclipse.jst.jsf.facesconfig.internal.translator;


import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.wst.common.internal.emf.resource.Translator;
import org.eclipse.wst.common.internal.emf.resource.RootTranslator;

/**
 * @author xjiang, itrimble
 *
 */
public class FacesConfigTranslator extends RootTranslator {
	
	public static FacesConfigTranslator INSTANCE = new FacesConfigTranslator();

    public FacesConfigTranslator() {
		super("faces-config", FacesConfigPackage.eINSTANCE.getFacesConfigType()); //$NON-NLS-1$
    }

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.internal.emf.resource.Translator#getChildren()
	 */
	public Translator[] getChildren() {
		
		FacesConfigPackage facesPackage = FacesConfigPackage.eINSTANCE;
		return new Translator[] {
				new ApplicationTranslator("application", facesPackage.getFacesConfigType_Application()), //$NON-NLS-1$
				new FactoryTranslator("factory", facesPackage.getFacesConfigType_Factory()), //$NON-NLS-1$
				new ComponentTranslator("component", facesPackage.getFacesConfigType_Component()), //$NON-NLS-1$
				new ConverterTranslator("converter", facesPackage.getFacesConfigType_Converter()), //$NON-NLS-1$
				new ManagedBeanTranslator("managed-bean", facesPackage.getFacesConfigType_ManagedBean()), //$NON-NLS-1$
				new NavigationRuleTranslator("navigation-rule", facesPackage.getFacesConfigType_NavigationRule()), //$NON-NLS-1$
				new ReferencedBeanTranslator("referenced-bean", facesPackage.getFacesConfigType_ReferencedBean()), //$NON-NLS-1$
				new RenderKitTranslator("render-kit", facesPackage.getFacesConfigType_RenderKit()), //$NON-NLS-1$
				new LifecycleTranslator("lifecycle", facesPackage.getFacesConfigType_Lifecycle()), //$NON-NLS-1$
				new ValidatorTranslator("validator", facesPackage.getFacesConfigType_Validator()), //$NON-NLS-1$
				new Translator("xmlns", facesPackage.getFacesConfigType_Xmlns(), DOM_ATTRIBUTE), //$NON-NLS-1$
				new Translator("id", facesPackage.getFacesConfigType_Id(), DOM_ATTRIBUTE) //$NON-NLS-1$
		};
	}
}
