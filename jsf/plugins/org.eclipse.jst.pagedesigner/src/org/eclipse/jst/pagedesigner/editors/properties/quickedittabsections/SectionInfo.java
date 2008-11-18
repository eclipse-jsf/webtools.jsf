/**
 * Copyright (c) 2007 Oracle Corporation
 *
 * $Id: SectionInfo.java,v 1.2 2008/11/18 22:22:35 gkessler Exp $
 */
package org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Section Info</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.SectionInfo#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.SectionInfo#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.QuickEditTabSectionsPackage#getSectionInfo()
 * @model
 * @generated
 */
public interface SectionInfo extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2007 Oracle Corporation"; //$NON-NLS-1$

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
	 * @see org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.QuickEditTabSectionsPackage#getSectionInfo_Id()
	 * @model required="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.SectionInfo#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The default value is <code>"ATTRIBUTE"</code>.
	 * The literals are from the enumeration {@link org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.SECTION_TYPE}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.SECTION_TYPE
	 * @see #setType(SECTION_TYPE)
	 * @see org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.QuickEditTabSectionsPackage#getSectionInfo_Type()
	 * @model default="ATTRIBUTE" unique="false"
	 * @generated
	 */
	SECTION_TYPE getType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.SectionInfo#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.SECTION_TYPE
	 * @see #getType()
	 * @generated
	 */
	void setType(SECTION_TYPE value);

} // SectionInfo
