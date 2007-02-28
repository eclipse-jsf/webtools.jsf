/**
 * <copyright>
 * </copyright>
 *
 * $Id: TagCreationAttribute.java,v 1.1 2007/02/28 05:04:45 gkessler Exp $
 */
package org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.provisional;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tag Creation Attribute</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.provisional.TagCreationAttribute#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.provisional.TagCreationAttribute#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.provisional.PaletteInfosPackage#getTagCreationAttribute()
 * @model
 * @generated
 */
public interface TagCreationAttribute extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2007 Oracle Corporation";

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.provisional.PaletteInfosPackage#getTagCreationAttribute_Id()
	 * @model required="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.provisional.TagCreationAttribute#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.provisional.PaletteInfosPackage#getTagCreationAttribute_Value()
	 * @model
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.provisional.TagCreationAttribute#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

} // TagCreationAttribute
