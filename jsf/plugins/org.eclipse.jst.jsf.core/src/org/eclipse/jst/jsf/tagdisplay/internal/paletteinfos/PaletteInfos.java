/**
 * Copyright (c) 2007 Oracle Corporation
 *
 * $Id: PaletteInfos.java,v 1.3 2008/11/18 22:23:54 gkessler Exp $
 */
package org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos;

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
 *   <li>{@link org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfos#getInfos <em>Infos</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfosPackage#getPaletteInfos()
 * @model
 * @generated
 */
public interface PaletteInfos extends EObject {
	/**
	 * the palette infos trait id
	 */
	public static final String TRAIT_ID				= "paletteInfos"; //$NON-NLS-1$
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2007 Oracle Corporation"; //$NON-NLS-1$

	/**
	 * Returns the value of the '<em><b>Infos</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfo}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Infos</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Infos</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfosPackage#getPaletteInfos_Infos()
	 * @model type="org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfo" containment="true"
	 *        extendedMetaData="kind='element' name='item'"
	 * @generated
	 */
	EList getInfos();

	/**
	 * <!-- begin-user-doc -->
     * @param id 
     * @return the pallette info corresponding to id. 
	 * <!-- end-user-doc -->
	 * @model idRequired="true"
	 * @generated
	 */
	PaletteInfo findPaletteInfoById(String id);

} // PaletteInfos
