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

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.wst.common.internal.emf.resource.Translator;

/**
 * @author xnjiang, itrimble
 *
 */
public class ApplicationTranslator extends Translator {
	/**
	 * @param domNameAndPath
	 * @param aFeature
	 */
	public ApplicationTranslator(String domNameAndPath, EStructuralFeature aFeature) {
		super(domNameAndPath, aFeature);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.internal.emf.resource.Translator#getChildren()
	 */
	public Translator[] getChildren() {
		
		FacesConfigPackage facesPackage = FacesConfigPackage.eINSTANCE;
		return new Translator[] {
				new ActionListenerTranslator("action-listener", facesPackage.getApplicationType_ActionListener()), //$NON-NLS-1$
				new DefaultRenderKitIdTranslator("default-render-kit-id", facesPackage.getApplicationType_DefaultRenderKitId()), //$NON-NLS-1$
				new MessageBundleTranslator("message-bundle", facesPackage.getApplicationType_MessageBundle()), //$NON-NLS-1$
				new NavigationHandlerTranslator("navigation-handler", facesPackage.getApplicationType_NavigationHandler()), //$NON-NLS-1$
//				new PartialTraversalTranslator("partial-traversal", facesPackage.getApplicationType_PartialTraversal()), //$NON-NLS-1$
				new ViewHandlerTranslator("view-handler", facesPackage.getApplicationType_ViewHandler()), //$NON-NLS-1$
				new StateManagerTranslator("state-manager", facesPackage.getApplicationType_StateManager()), //$NON-NLS-1$
                new ELResolverTypeTranslator("el-resolver", facesPackage.getApplicationType_ELResolver()), //$NON-NLS-1$
				new PropertyResolverTranslator("property-resolver", facesPackage.getApplicationType_PropertyResolver()), //$NON-NLS-1$
				new VariableResolverTranslator("variable-resolver", facesPackage.getApplicationType_VariableResolver()), //$NON-NLS-1$
				new ResourceHandlerTranslator("resource-handler", facesPackage.getApplicationType_ResourceHandler()), //$NON-NLS-1$
				new SystemEventListenerTranslator("system-event-listener", facesPackage.getApplicationType_SystemEventListener()), //$NON-NLS-1$
				new LocaleConfigTranslator("locale-config", facesPackage.getApplicationType_LocaleConfig()), //$NON-NLS-1$
                new ResourceBundleTranslator("resource-bundle", facesPackage.getApplicationType_ResourceBundle()), //$NON-NLS-1$
                new ApplicationExtensionTranslator("application-extension", facesPackage.getApplicationType_ApplicationExtension()), //$NON-NLS-1$
				new DefaultValidatorsTranslator("default-validators", facesPackage.getApplicationType_DefaultValidators()), //$NON-NLS-1$
				new Translator("id", facesPackage.getApplicationType_Id(), DOM_ATTRIBUTE) //$NON-NLS-1$
		};
	}
}
