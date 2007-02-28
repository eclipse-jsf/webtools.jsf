/**
 * <copyright>
 * </copyright>
 *
 * $Id: TagCreationTemplate.java,v 1.1 2007/02/28 05:04:45 gkessler Exp $
 */
package org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.provisional;

import org.apache.xerces.dom.DocumentFragmentImpl;
import org.eclipse.emf.ecore.EObject;
import org.w3c.dom.NodeList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tag Creation Template</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.provisional.TagCreationTemplate#getTemplate <em>Template</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.provisional.PaletteInfosPackage#getTagCreationTemplate()
 * @model
 * @generated
 */
public interface TagCreationTemplate extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2007 Oracle Corporation";

	/**
	 * Returns the value of the '<em><b>Template</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Template</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Template</em>' containment reference.
	 * @see #setTemplate(EObject)
	 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.provisional.PaletteInfosPackage#getTagCreationTemplate_Template()
	 * @model containment="true"
	 * @generated
	 */
	EObject getTemplate();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.provisional.TagCreationTemplate#getTemplate <em>Template</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Template</em>' containment reference.
	 * @see #getTemplate()
	 * @generated
	 */
	void setTemplate(EObject value);

} // TagCreationTemplate
