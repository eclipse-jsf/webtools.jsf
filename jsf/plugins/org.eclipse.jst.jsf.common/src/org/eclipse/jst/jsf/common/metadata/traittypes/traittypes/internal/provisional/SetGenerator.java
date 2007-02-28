/**
 * <copyright>
 * </copyright>
 *
 * $Id: SetGenerator.java,v 1.1 2007/02/28 21:12:40 cbateman Exp $
 */
package org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.internal.provisional;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Set Generator</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.internal.provisional.TraitTypesPackage#getSetGenerator()
 * @model
 * @generated
 */
public interface SetGenerator extends EObject {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2007 Oracle Corporation";

    /**
     * Returns the value of the '<em><b>Algorithm</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Algorithm</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Algorithm</em>' attribute.
     * @see #setAlgorithm(String)
     * @see org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.internal.provisional.TraitTypesPackage#getSetGenerator_Algorithm()
     * @model extendedMetaData="kind='element'"
     * @generated
     */
    String getAlgorithm();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.internal.provisional.SetGenerator#getAlgorithm <em>Algorithm</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Algorithm</em>' attribute.
     * @see #getAlgorithm()
     * @generated
     */
    void setAlgorithm(String value);

    /**
     * Returns the value of the '<em><b>Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Expression</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Expression</em>' attribute.
     * @see #setExpression(String)
     * @see org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.internal.provisional.TraitTypesPackage#getSetGenerator_Expression()
     * @model extendedMetaData="kind='element'"
     * @generated
     */
    String getExpression();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.internal.provisional.SetGenerator#getExpression <em>Expression</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Expression</em>' attribute.
     * @see #getExpression()
     * @generated
     */
    void setExpression(String value);

} // SetGenerator
