/**
 * Copyright (c) 2007 Oracle Corporation
 *
 * $Id: PaletteInfosFactoryImpl.java,v 1.2 2008/11/18 22:24:03 gkessler Exp $
 */
package org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfo;
import org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfos;
import org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfosFactory;
import org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfosPackage;
import org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.TagCreationAttribute;
import org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.TagCreationInfo;
import org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.TagCreationTemplate;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PaletteInfosFactoryImpl extends EFactoryImpl implements PaletteInfosFactory {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2007 Oracle Corporation"; //$NON-NLS-1$

	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
     * @return the palette info factory 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PaletteInfosFactory init() {
		try {
			PaletteInfosFactory thePaletteInfosFactory = (PaletteInfosFactory)EPackage.Registry.INSTANCE.getEFactory("http://org.eclipse.jsf.pagedesigner/paletteInfos.ecore");  //$NON-NLS-1$
			if (thePaletteInfosFactory != null) {
				return thePaletteInfosFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new PaletteInfosFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PaletteInfosFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case PaletteInfosPackage.PALETTE_INFOS: return createPaletteInfos();
			case PaletteInfosPackage.PALETTE_INFO: return createPaletteInfo();
			case PaletteInfosPackage.TAG_CREATION_INFO: return createTagCreationInfo();
			case PaletteInfosPackage.TAG_CREATION_TEMPLATE: return createTagCreationTemplate();
			case PaletteInfosPackage.TAG_CREATION_ATTRIBUTE: return createTagCreationAttribute();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PaletteInfo createPaletteInfo() {
		PaletteInfoImpl paletteInfo = new PaletteInfoImpl();
		return paletteInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TagCreationInfo createTagCreationInfo() {
		TagCreationInfoImpl tagCreationInfo = new TagCreationInfoImpl();
		return tagCreationInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TagCreationTemplate createTagCreationTemplate() {
		TagCreationTemplateImpl tagCreationTemplate = new TagCreationTemplateImpl();
		return tagCreationTemplate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TagCreationAttribute createTagCreationAttribute() {
		TagCreationAttributeImpl tagCreationAttribute = new TagCreationAttributeImpl();
		return tagCreationAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PaletteInfos createPaletteInfos() {
		PaletteInfosImpl paletteInfos = new PaletteInfosImpl();
		return paletteInfos;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PaletteInfosPackage getPaletteInfosPackage() {
		return (PaletteInfosPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
     * @return the palette info package 
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	public static PaletteInfosPackage getPackage() {
		return PaletteInfosPackage.eINSTANCE;
	}

} //PaletteInfosFactoryImpl
