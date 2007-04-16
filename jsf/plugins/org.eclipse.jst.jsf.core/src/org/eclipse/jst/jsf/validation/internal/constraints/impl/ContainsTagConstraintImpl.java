/**
 * <copyright>
 * </copyright>
 *
 * $Id: ContainsTagConstraintImpl.java,v 1.3 2007/04/16 19:53:19 itrimble Exp $
 */
package org.eclipse.jst.jsf.validation.internal.constraints.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.SetGenerator;

import org.eclipse.jst.jsf.validation.internal.constraints.ConstraintsPackage;
import org.eclipse.jst.jsf.validation.internal.constraints.ContainsTagConstraint;
import org.eclipse.jst.jsf.validation.internal.constraints.TagSet;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Contains Tag Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.validation.internal.constraints.impl.ContainsTagConstraintImpl#getSetGenerator <em>Set Generator</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.validation.internal.constraints.impl.ContainsTagConstraintImpl#getSatisfiesSet <em>Satisfies Set</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ContainsTagConstraintImpl extends EObjectImpl implements ContainsTagConstraint {
    /**
     * The cached value of the '{@link #getSetGenerator() <em>Set Generator</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSetGenerator()
     * @generated
     * @ordered
     */
    protected SetGenerator setGenerator = null;

    /**
     * The cached value of the '{@link #getSatisfiesSet() <em>Satisfies Set</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSatisfiesSet()
     * @generated
     * @ordered
     */
    protected TagSet satisfiesSet = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ContainsTagConstraintImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return ConstraintsPackage.Literals.CONTAINS_TAG_CONSTRAINT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SetGenerator getSetGenerator() {
        if (setGenerator != null && setGenerator.eIsProxy()) {
            InternalEObject oldSetGenerator = (InternalEObject)setGenerator;
            setGenerator = (SetGenerator)eResolveProxy(oldSetGenerator);
            if (setGenerator != oldSetGenerator) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ConstraintsPackage.CONTAINS_TAG_CONSTRAINT__SET_GENERATOR, oldSetGenerator, setGenerator));
            }
        }
        return setGenerator;
    }

    /**
     * <!-- begin-user-doc -->
     * @return the set generator
     * <!-- end-user-doc -->
     * @generated
     */
    public SetGenerator basicGetSetGenerator() {
        return setGenerator;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSetGenerator(SetGenerator newSetGenerator) {
        SetGenerator oldSetGenerator = setGenerator;
        setGenerator = newSetGenerator;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConstraintsPackage.CONTAINS_TAG_CONSTRAINT__SET_GENERATOR, oldSetGenerator, setGenerator));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TagSet getSatisfiesSet() {
        if (satisfiesSet != null && satisfiesSet.eIsProxy()) {
            InternalEObject oldSatisfiesSet = (InternalEObject)satisfiesSet;
            satisfiesSet = (TagSet)eResolveProxy(oldSatisfiesSet);
            if (satisfiesSet != oldSatisfiesSet) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ConstraintsPackage.CONTAINS_TAG_CONSTRAINT__SATISFIES_SET, oldSatisfiesSet, satisfiesSet));
            }
        }
        return satisfiesSet;
    }

    /**
     * <!-- begin-user-doc -->
     * @return the tag set that must be satisfied 
     * <!-- end-user-doc -->
     * @generated
     */
    public TagSet basicGetSatisfiesSet() {
        return satisfiesSet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSatisfiesSet(TagSet newSatisfiesSet) {
        TagSet oldSatisfiesSet = satisfiesSet;
        satisfiesSet = newSatisfiesSet;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConstraintsPackage.CONTAINS_TAG_CONSTRAINT__SATISFIES_SET, oldSatisfiesSet, satisfiesSet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ConstraintsPackage.CONTAINS_TAG_CONSTRAINT__SET_GENERATOR:
                if (resolve) return getSetGenerator();
                return basicGetSetGenerator();
            case ConstraintsPackage.CONTAINS_TAG_CONSTRAINT__SATISFIES_SET:
                if (resolve) return getSatisfiesSet();
                return basicGetSatisfiesSet();
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
            case ConstraintsPackage.CONTAINS_TAG_CONSTRAINT__SET_GENERATOR:
                setSetGenerator((SetGenerator)newValue);
                return;
            case ConstraintsPackage.CONTAINS_TAG_CONSTRAINT__SATISFIES_SET:
                setSatisfiesSet((TagSet)newValue);
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
            case ConstraintsPackage.CONTAINS_TAG_CONSTRAINT__SET_GENERATOR:
                setSetGenerator((SetGenerator)null);
                return;
            case ConstraintsPackage.CONTAINS_TAG_CONSTRAINT__SATISFIES_SET:
                setSatisfiesSet((TagSet)null);
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
            case ConstraintsPackage.CONTAINS_TAG_CONSTRAINT__SET_GENERATOR:
                return setGenerator != null;
            case ConstraintsPackage.CONTAINS_TAG_CONSTRAINT__SATISFIES_SET:
                return satisfiesSet != null;
        }
        return super.eIsSet(featureID);
    }

} //ContainsTagConstraintImpl
