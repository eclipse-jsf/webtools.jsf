/**
 * <copyright>
 * </copyright>
 *
 * $Id: PaletteInfos.java,v 1.1 2007/02/28 05:04:45 gkessler Exp $
 */
package org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.provisional;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Palette Infos</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.provisional.PaletteInfos#getInfos <em>Infos</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.provisional.PaletteInfosPackage#getPaletteInfos()
 * @model
 * @generated
 */
public interface PaletteInfos extends EObject {
	public static final String TRAIT_ID				= "paletteInfos";
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2007 Oracle Corporation";

	/**
	 * Returns the value of the '<em><b>Infos</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.provisional.PaletteInfo}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Infos</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Infos</em>' containment reference list.
	 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.provisional.PaletteInfosPackage#getPaletteInfos_Infos()
	 * @model type="org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.provisional.PaletteInfo" containment="true"
	 *        extendedMetaData="kind='element' name='item'"
	 * @generated
	 */
	EList getInfos();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model idRequired="true"
	 * @generated
	 */
	PaletteInfo findPaletteInfoById(String id);

} // PaletteInfos
