/**
 * Copyright (c) 2007 Oracle Corporation
 *
 * $Id: QuickEditTabSections.java,v 1.2 2008/11/18 22:22:35 gkessler Exp $
 */
package org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Quick Edit Tab Sections</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.QuickEditTabSections#getSections <em>Sections</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.QuickEditTabSectionsPackage#getQuickEditTabSections()
 * @model
 * @generated
 */
public interface QuickEditTabSections extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2007 Oracle Corporation"; //$NON-NLS-1$
	
	/**
	 * trait id to use
	 * @generated NOT
	 */
	String TRAIT_ID = "quick-edit-tab"; //$NON-NLS-1$
	
	/**
	 * Returns the value of the '<em><b>Sections</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.SectionInfo}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sections</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sections</em>' containment reference list.
	 * @see org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.QuickEditTabSectionsPackage#getQuickEditTabSections_Sections()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='section'"
	 * @generated
	 */
	EList<SectionInfo> getSections();

} // QuickEditTabSections
