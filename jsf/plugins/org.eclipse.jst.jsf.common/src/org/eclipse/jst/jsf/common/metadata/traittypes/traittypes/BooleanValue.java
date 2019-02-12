/**
 * Copyright (c) 2007, 2010 Oracle Corporation
 *
 * $Id: BooleanValue.java,v 1.1 2010/01/21 00:01:44 gkessler Exp $
 */
package org.eclipse.jst.jsf.common.metadata.traittypes.traittypes;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Boolean Value</b></em>'.
 * 
 * Only String value of 'true' in xml will result in isTrue() being true.
 * All other String values will result in false.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.BooleanValue#isTrue <em>True</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.TraitTypesPackage#getBooleanValue()
 * @model extendedMetaData="kind='simple'"
 * @generated
 */
public interface BooleanValue extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	String copyright = "Copyright (c) 2007 Oracle Corporation"; //$NON-NLS-1$

	/**
	 * Returns the value of the '<em><b>True</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>True</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>True</em>' attribute.
	 * @see #setTrue(boolean)
	 * @see org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.TraitTypesPackage#getBooleanValue_True()
	 * @model required="true"
	 *        extendedMetaData="kind='simple'"
	 * @generated
	 */
	boolean isTrue();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.BooleanValue#isTrue <em>True</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>True</em>' attribute.
	 * @see #isTrue()
	 * @generated
	 */
	void setTrue(boolean value);

} // BooleanValue
