/**
 * <copyright>
 * </copyright>
 *
 * $Id: TagSetImpl.java,v 1.1 2007/02/28 21:16:02 cbateman Exp $
 */
package org.eclipse.jst.jsf.validation.internal.constraints.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.eclipse.jst.jsf.validation.internal.constraints.ConstraintsPackage;
import org.eclipse.jst.jsf.validation.internal.constraints.TagId;
import org.eclipse.jst.jsf.validation.internal.constraints.TagSet;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tag Set</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.validation.internal.constraints.impl.TagSetImpl#getTags <em>Tags</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TagSetImpl extends EObjectImpl implements TagSet {
    /**
     * The cached value of the '{@link #getTags() <em>Tags</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTags()
     * @generated
     * @ordered
     */
    protected EList tags = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TagSetImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return ConstraintsPackage.Literals.TAG_SET;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getTags() {
        if (tags == null) {
            tags = new EObjectResolvingEList(TagId.class, this, ConstraintsPackage.TAG_SET__TAGS);
        }
        return tags;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ConstraintsPackage.TAG_SET__TAGS:
                return getTags();
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
            case ConstraintsPackage.TAG_SET__TAGS:
                getTags().clear();
                getTags().addAll((Collection)newValue);
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
            case ConstraintsPackage.TAG_SET__TAGS:
                getTags().clear();
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
            case ConstraintsPackage.TAG_SET__TAGS:
                return tags != null && !tags.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //TagSetImpl
