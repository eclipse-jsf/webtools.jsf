/***************************************************************************************************
 * Copyright (c) 2005, 2010 IBM Corporation and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors: 
 *   IBM Corporation - initial API and implementation
 *   Oracle Corporation - added id attribute
 **************************************************************************************************/
package org.eclipse.jst.jsf.facesconfig.internal.translator;


import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.wst.common.internal.emf.resource.RootTranslator;
import org.eclipse.wst.common.internal.emf.resource.Translator;

/**
 * @author xjiang, itrimble
 *
 */
public class FacesConfigTranslator extends RootTranslator {
	
	/**
	 * Instance of translator
	 */
	public static FacesConfigTranslator INSTANCE = new FacesConfigTranslator();

    /**
     * Default constructor
     */
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
				new OrderingTranslator("ordering", facesPackage.getFacesConfigType_Ordering()), //$NON-NLS-1$
				new AbsoluteOrderingTranslator("absolute-ordering", facesPackage.getFacesConfigType_AbsoluteOrdering()), //$NON-NLS-1$
				new FactoryTranslator("factory", facesPackage.getFacesConfigType_Factory()), //$NON-NLS-1$
				new ComponentTranslator("component", facesPackage.getFacesConfigType_Component()), //$NON-NLS-1$
				new ConverterTranslator("converter", facesPackage.getFacesConfigType_Converter()), //$NON-NLS-1$
				new ManagedBeanTranslator("managed-bean", facesPackage.getFacesConfigType_ManagedBean()), //$NON-NLS-1$
				new NameTranslator("name", facesPackage.getFacesConfigType_Name()), //$NON-NLS-1$
				new NavigationRuleTranslator("navigation-rule", facesPackage.getFacesConfigType_NavigationRule()), //$NON-NLS-1$
				new ReferencedBeanTranslator("referenced-bean", facesPackage.getFacesConfigType_ReferencedBean()), //$NON-NLS-1$
				new RenderKitTranslator("render-kit", facesPackage.getFacesConfigType_RenderKit()), //$NON-NLS-1$
				new LifecycleTranslator("lifecycle", facesPackage.getFacesConfigType_Lifecycle()), //$NON-NLS-1$
				new ValidatorTranslator("validator", facesPackage.getFacesConfigType_Validator()), //$NON-NLS-1$
				new BehaviorTranslator("behavior", facesPackage.getFacesConfigType_Behavior()), //$NON-NLS-1$
				new FacesConfigExtensionTranslator("faces-config-extension", facesPackage.getFacesConfigType_FacesConfigExtension()), //$NON_NLS-1$ //$NON-NLS-1$
				new Translator("xmlns", facesPackage.getFacesConfigType_Xmlns(), DOM_ATTRIBUTE), //$NON-NLS-1$
				new Translator("metadata-complete", facesPackage.getFacesConfigType_MetadataComplete(), DOM_ATTRIBUTE), //$NON-NLS-1$
				new Translator("id", facesPackage.getFacesConfigType_Id(), DOM_ATTRIBUTE) //$NON-NLS-1$
		};
	}
}
