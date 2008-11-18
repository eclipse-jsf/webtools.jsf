/**
 * Copyright (c) 2007 Oracle Corporation
 *
 * $Id: PaletteInfosFactory.java,v 1.2 2008/11/18 22:23:54 gkessler Exp $
 */
package org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfosPackage
 * @generated
 */
public interface PaletteInfosFactory extends EFactory {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2007 Oracle Corporation"; //$NON-NLS-1$

	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PaletteInfosFactory eINSTANCE = org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.impl.PaletteInfosFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Palette Infos</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Palette Infos</em>'.
	 * @generated
	 */
	PaletteInfos createPaletteInfos();

	/**
	 * Returns a new object of class '<em>Palette Info</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Palette Info</em>'.
	 * @generated
	 */
	PaletteInfo createPaletteInfo();

	/**
	 * Returns a new object of class '<em>Tag Creation Info</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tag Creation Info</em>'.
	 * @generated
	 */
	TagCreationInfo createTagCreationInfo();

	/**
	 * Returns a new object of class '<em>Tag Creation Template</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tag Creation Template</em>'.
	 * @generated
	 */
	TagCreationTemplate createTagCreationTemplate();

	/**
	 * Returns a new object of class '<em>Tag Creation Attribute</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tag Creation Attribute</em>'.
	 * @generated
	 */
	TagCreationAttribute createTagCreationAttribute();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	PaletteInfosPackage getPaletteInfosPackage();

} //PaletteInfosFactory
