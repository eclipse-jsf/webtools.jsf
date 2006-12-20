/***************************************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM Corporation - initial API and implementation
 *   Oracle Corporation - revision
 **************************************************************************************************/
package org.eclipse.jst.jsf.facesconfig.emf.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jst.jsf.facesconfig.emf.DynamicAttribute;
import org.eclipse.jst.jsf.facesconfig.emf.DynamicElement;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Dynamic Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DynamicElementImpl#getChildNodes <em>Child Nodes</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DynamicElementImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DynamicElementImpl#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.DynamicElementImpl#getTextContent <em>Text Content</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DynamicElementImpl extends EObjectImpl implements DynamicElement {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others";

    /**
     * The cached value of the '{@link #getChildNodes() <em>Child Nodes</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getChildNodes()
     * @generated
     * @ordered
     */
    protected EList childNodes = null;

    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = NAME_EDEFAULT;

    /**
     * The cached value of the '{@link #getAttributes() <em>Attributes</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAttributes()
     * @generated
     * @ordered
     */
    protected EList attributes = null;

    /**
     * The default value of the '{@link #getTextContent() <em>Text Content</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTextContent()
     * @generated
     * @ordered
     */
    protected static final String TEXT_CONTENT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTextContent() <em>Text Content</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTextContent()
     * @generated
     * @ordered
     */
    protected String textContent = TEXT_CONTENT_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DynamicElementImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return FacesConfigPackage.Literals.DYNAMIC_ELEMENT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getChildNodes() {
        if (childNodes == null) {
            childNodes = new EObjectContainmentEList(DynamicElement.class, this, FacesConfigPackage.DYNAMIC_ELEMENT__CHILD_NODES);
        }
        return childNodes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getAttributes() {
        if (attributes == null) {
            attributes = new EObjectResolvingEList(DynamicAttribute.class, this, FacesConfigPackage.DYNAMIC_ELEMENT__ATTRIBUTES);
        }
        return attributes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getTextContent() {
        return textContent;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTextContent(String newTextContent) {
        String oldTextContent = textContent;
        textContent = newTextContent;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.DYNAMIC_ELEMENT__TEXT_CONTENT, oldTextContent, textContent));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.DYNAMIC_ELEMENT__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FacesConfigPackage.DYNAMIC_ELEMENT__CHILD_NODES:
                return ((InternalEList)getChildNodes()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case FacesConfigPackage.DYNAMIC_ELEMENT__CHILD_NODES:
                return getChildNodes();
            case FacesConfigPackage.DYNAMIC_ELEMENT__NAME:
                return getName();
            case FacesConfigPackage.DYNAMIC_ELEMENT__ATTRIBUTES:
                return getAttributes();
            case FacesConfigPackage.DYNAMIC_ELEMENT__TEXT_CONTENT:
                return getTextContent();
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
            case FacesConfigPackage.DYNAMIC_ELEMENT__CHILD_NODES:
                getChildNodes().clear();
                getChildNodes().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.DYNAMIC_ELEMENT__NAME:
                setName((String)newValue);
                return;
            case FacesConfigPackage.DYNAMIC_ELEMENT__ATTRIBUTES:
                getAttributes().clear();
                getAttributes().addAll((Collection)newValue);
                return;
            case FacesConfigPackage.DYNAMIC_ELEMENT__TEXT_CONTENT:
                setTextContent((String)newValue);
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
            case FacesConfigPackage.DYNAMIC_ELEMENT__CHILD_NODES:
                getChildNodes().clear();
                return;
            case FacesConfigPackage.DYNAMIC_ELEMENT__NAME:
                setName(NAME_EDEFAULT);
                return;
            case FacesConfigPackage.DYNAMIC_ELEMENT__ATTRIBUTES:
                getAttributes().clear();
                return;
            case FacesConfigPackage.DYNAMIC_ELEMENT__TEXT_CONTENT:
                setTextContent(TEXT_CONTENT_EDEFAULT);
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
            case FacesConfigPackage.DYNAMIC_ELEMENT__CHILD_NODES:
                return childNodes != null && !childNodes.isEmpty();
            case FacesConfigPackage.DYNAMIC_ELEMENT__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case FacesConfigPackage.DYNAMIC_ELEMENT__ATTRIBUTES:
                return attributes != null && !attributes.isEmpty();
            case FacesConfigPackage.DYNAMIC_ELEMENT__TEXT_CONTENT:
                return TEXT_CONTENT_EDEFAULT == null ? textContent != null : !TEXT_CONTENT_EDEFAULT.equals(textContent);
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
        result.append(" (name: ");
        result.append(name);
        result.append(", textContent: ");
        result.append(textContent);
        result.append(')');
        return result.toString();
    }

} //DynamicElementImpl
