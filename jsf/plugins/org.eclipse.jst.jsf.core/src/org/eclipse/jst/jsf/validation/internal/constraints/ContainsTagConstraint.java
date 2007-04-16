/**
 * <copyright>
 * </copyright>
 *
 * $Id: ContainsTagConstraint.java,v 1.2 2007/04/16 19:53:29 itrimble Exp $
 */
package org.eclipse.jst.jsf.validation.internal.constraints;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.SetGenerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Contains Tag Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.validation.internal.constraints.ContainsTagConstraint#getSetGenerator <em>Set Generator</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.validation.internal.constraints.ContainsTagConstraint#getSatisfiesSet <em>Satisfies Set</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.validation.internal.constraints.ConstraintsPackage#getContainsTagConstraint()
 * @model
 * @generated
 */
public interface ContainsTagConstraint extends EObject {
    /**
     * Returns the value of the '<em><b>Set Generator</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Set Generator</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Set Generator</em>' reference.
     * @see #setSetGenerator(SetGenerator)
     * @see org.eclipse.jst.jsf.validation.internal.constraints.ConstraintsPackage#getContainsTagConstraint_SetGenerator()
     * @model extendedMetaData="kind='element' name='set-generator'"
     * @generated
     */
    SetGenerator getSetGenerator();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.validation.internal.constraints.ContainsTagConstraint#getSetGenerator <em>Set Generator</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Set Generator</em>' reference.
     * @see #getSetGenerator()
     * @generated
     */
    void setSetGenerator(SetGenerator value);

    /**
     * Returns the value of the '<em><b>Satisfies Set</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Satisfies Set</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Satisfies Set</em>' reference.
     * @see #setSatisfiesSet(TagSet)
     * @see org.eclipse.jst.jsf.validation.internal.constraints.ConstraintsPackage#getContainsTagConstraint_SatisfiesSet()
     * @model extendedMetaData="kind='element' name='satisfies-set'"
     * @generated
     */
    TagSet getSatisfiesSet();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.validation.internal.constraints.ContainsTagConstraint#getSatisfiesSet <em>Satisfies Set</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Satisfies Set</em>' reference.
     * @see #getSatisfiesSet()
     * @generated
     */
    void setSatisfiesSet(TagSet value);

} // ContainsTagConstraint
