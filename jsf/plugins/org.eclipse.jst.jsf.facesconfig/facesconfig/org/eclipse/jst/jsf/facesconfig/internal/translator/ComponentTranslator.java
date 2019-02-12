/***************************************************************************************************
 * Copyright (c) 2005, 2008 IBM Corporation and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors: 
 *   IBM Corporation - initial API and implementation
 *   Oracle Corporation - fixed getChildren()
 **************************************************************************************************/
package org.eclipse.jst.jsf.facesconfig.internal.translator;


import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.wst.common.internal.emf.resource.Translator;

/**
 * @author xjiang, itrimble
 *
 */
public class ComponentTranslator extends Translator {
	/**
	 * @param domNameAndPath
	 * @param aFeature
	 */
	public ComponentTranslator(String domNameAndPath, EStructuralFeature aFeature) {
		super(domNameAndPath, aFeature);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.internal.emf.resource.Translator#getChildren()
	 */
	public Translator[] getChildren() {
		
		FacesConfigPackage facesPackage = FacesConfigPackage.eINSTANCE;
		return new Translator[] {
			new DescriptionTranslator("description", facesPackage.getComponentType_Description()), //$NON-NLS-1$
			new DisplayNameTranslator("display-name", facesPackage.getComponentType_DisplayName()), //$NON-NLS-1$
			new IconTranslator("icon", facesPackage.getComponentType_Icon()), //$NON-NLS-1$
			new ComponentTypeTranslator("component-type", facesPackage.getComponentType_ComponentType()), //$NON-NLS-1$
			new ComponentClassTranslator("component-class", facesPackage.getComponentType_ComponentClass()), //$NON-NLS-1$
			new FacetTranslator("facet", facesPackage.getComponentType_Facet()), //$NON-NLS-1$
			new AttributeTranslator("attribute", facesPackage.getComponentType_Attribute()), //$NON-NLS-1$
			new PropertyTranslator("property", facesPackage.getComponentType_Property()), //$NON-NLS-1$
			new ComponentExtensionTranslator("component-extension", facesPackage.getComponentType_ComponentExtension()),//ComponentExtensionTranslator("component-extension", facesPackage.getComponentType_ComponentExtension()), //$NON-NLS-1$
			new Translator("id", facesPackage.getComponentType_Id(), DOM_ATTRIBUTE) //$NON-NLS-1$
		};
	}
}
