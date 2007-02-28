/**
 * <copyright>
 * </copyright>
 *
 * $Id: SetGeneratorImpl.java,v 1.1 2007/02/28 21:12:40 cbateman Exp $
 */
package org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.internal.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.internal.provisional.SetGenerator;
import org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.internal.provisional.TraitTypesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Set Generator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.internal.impl.SetGeneratorImpl#getAlgorithm <em>Algorithm</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.internal.impl.SetGeneratorImpl#getExpression <em>Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SetGeneratorImpl extends EObjectImpl implements SetGenerator {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2007 Oracle Corporation";

    /**
     * The default value of the '{@link #getAlgorithm() <em>Algorithm</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAlgorithm()
     * @generated
     * @ordered
     */
    protected static final String ALGORITHM_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getAlgorithm() <em>Algorithm</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAlgorithm()
     * @generated
     * @ordered
     */
    protected String algorithm = ALGORITHM_EDEFAULT;

    /**
     * The default value of the '{@link #getExpression() <em>Expression</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExpression()
     * @generated
     * @ordered
     */
    protected static final String EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getExpression() <em>Expression</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExpression()
     * @generated
     * @ordered
     */
    protected String expression = EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SetGeneratorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return TraitTypesPackage.Literals.SET_GENERATOR;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getAlgorithm() {
        return algorithm;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAlgorithm(String newAlgorithm) {
        String oldAlgorithm = algorithm;
        algorithm = newAlgorithm;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TraitTypesPackage.SET_GENERATOR__ALGORITHM, oldAlgorithm, algorithm));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getExpression() {
        return expression;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setExpression(String newExpression) {
        String oldExpression = expression;
        expression = newExpression;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TraitTypesPackage.SET_GENERATOR__EXPRESSION, oldExpression, expression));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case TraitTypesPackage.SET_GENERATOR__ALGORITHM:
                return getAlgorithm();
            case TraitTypesPackage.SET_GENERATOR__EXPRESSION:
                return getExpression();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case TraitTypesPackage.SET_GENERATOR__ALGORITHM:
                setAlgorithm((String)newValue);
                return;
            case TraitTypesPackage.SET_GENERATOR__EXPRESSION:
                setExpression((String)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eUnset(int featureID) {
        switch (featureID) {
            case TraitTypesPackage.SET_GENERATOR__ALGORITHM:
                setAlgorithm(ALGORITHM_EDEFAULT);
                return;
            case TraitTypesPackage.SET_GENERATOR__EXPRESSION:
                setExpression(EXPRESSION_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case TraitTypesPackage.SET_GENERATOR__ALGORITHM:
                return ALGORITHM_EDEFAULT == null ? algorithm != null : !ALGORITHM_EDEFAULT.equals(algorithm);
            case TraitTypesPackage.SET_GENERATOR__EXPRESSION:
                return EXPRESSION_EDEFAULT == null ? expression != null : !EXPRESSION_EDEFAULT.equals(expression);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (algorithm: ");
        result.append(algorithm);
        result.append(", expression: ");
        result.append(expression);
        result.append(')');
        return result.toString();
    }

} //SetGeneratorImpl
