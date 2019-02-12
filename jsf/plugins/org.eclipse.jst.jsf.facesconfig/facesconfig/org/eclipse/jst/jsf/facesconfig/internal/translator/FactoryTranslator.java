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
 * @author Eric Bordeau, itrimble
 */
public class FactoryTranslator extends Translator {

	/**
	 * @param domNameAndPath
	 * @param aFeature
	 */
	public FactoryTranslator(String domNameAndPath, EStructuralFeature aFeature) {
		super(domNameAndPath, aFeature);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.internal.emf.resource.Translator#getChildren()
	 */
	public Translator[] getChildren() {
		
		FacesConfigPackage facesPackage = FacesConfigPackage.eINSTANCE;
		return new Translator[] {
			new ApplicationFactoryTranslator("application-factory", facesPackage.getFactoryType_ApplicationFactory()), //$NON-NLS-1$
			new ExceptionHandlerFactoryTranslator("exception-handler-factory", facesPackage.getFactoryType_ExceptionHandlerFactory()), //$NON-NLS-1$
			new ExternalContextFactoryTranslator("external-context-factory", facesPackage.getFactoryType_ExternalContextFactory()), //$NON-NLS-1$
			new FacesContextFactoryTranslator("faces-context-factory", facesPackage.getFactoryType_FacesContextFactory()), //$NON-NLS-1$
			new PartialViewContextFactoryTranslator("partial-view-context-factory", facesPackage.getFactoryType_PartialViewContextFactory()), //$NON-NLS-1$
			new LifecycleFactoryTranslator("lifecycle-factory", facesPackage.getFactoryType_LifecycleFactory()), //$NON-NLS-1$
			new ViewDeclarationLanguageFactoryTranslator("view-declaration-language-factory", facesPackage.getFactoryType_ViewDeclarationLanguageFactory()), //$NON-NLS-1$
			new TagHandlerDelegateFactoryTranslator("tag-handler-delegate-factory", facesPackage.getFactoryType_TagHandlerDelegateFactory()), //$NON-NLS-1$
			new RenderKitFactoryTranslator("render-kit-factory", facesPackage.getFactoryType_RenderKitFactory()), //$NON-NLS-1$
			new VisitContextFactoryTranslator("visit-context-factory", facesPackage.getFactoryType_VisitContextFactory()), //$NON-NLS-1$
            new FactoryExtensionTranslator("factory-extension", facesPackage.getFactoryType_FactoryExtension()), //$NON-NLS-1$
			new Translator("id", facesPackage.getFactoryType_Id(), DOM_ATTRIBUTE) //$NON-NLS-1$
		};
	}
}
