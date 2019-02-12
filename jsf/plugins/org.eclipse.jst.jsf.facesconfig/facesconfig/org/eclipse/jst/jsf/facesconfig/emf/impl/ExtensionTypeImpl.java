/***************************************************************************************************
 * Copyright (c) 2005, 2010 IBM Corporation and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors: 
 *   IBM Corporation - initial API and implementation
 *   Oracle Corporation - revision
 **************************************************************************************************/
package org.eclipse.jst.jsf.facesconfig.emf.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.jst.jsf.facesconfig.emf.DynamicElement;
import org.eclipse.jst.jsf.facesconfig.emf.ExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Extension Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ExtensionTypeImpl#getChildNodes <em>Child Nodes</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ExtensionTypeImpl#getTextContent <em>Text Content</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.ExtensionTypeImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ExtensionTypeImpl extends EObjectImpl implements ExtensionType {
    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static final String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others"; //$NON-NLS-1$

    /**
	 * The cached value of the '{@link #getChildNodes() <em>Child Nodes</em>}' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getChildNodes()
	 * @generated
	 * @ordered
	 */
    protected EList childNodes;

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
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
    protected static final String ID_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
    protected String id = ID_EDEFAULT;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected ExtensionTypeImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return FacesConfigPackage.Literals.EXTENSION_TYPE;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getChildNodes() {
		if (childNodes == null) {
			childNodes = new EObjectResolvingEList(DynamicElement.class, this, FacesConfigPackage.EXTENSION_TYPE__CHILD_NODES);
		}
		return childNodes;
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
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.EXTENSION_TYPE__TEXT_CONTENT, oldTextContent, textContent));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getId() {
		return id;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.EXTENSION_TYPE__ID, oldId, id));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FacesConfigPackage.EXTENSION_TYPE__CHILD_NODES:
				return getChildNodes();
			case FacesConfigPackage.EXTENSION_TYPE__TEXT_CONTENT:
				return getTextContent();
			case FacesConfigPackage.EXTENSION_TYPE__ID:
				return getId();
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
			case FacesConfigPackage.EXTENSION_TYPE__CHILD_NODES:
				getChildNodes().clear();
				getChildNodes().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.EXTENSION_TYPE__TEXT_CONTENT:
				setTextContent((String)newValue);
				return;
			case FacesConfigPackage.EXTENSION_TYPE__ID:
				setId((String)newValue);
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
			case FacesConfigPackage.EXTENSION_TYPE__CHILD_NODES:
				getChildNodes().clear();
				return;
			case FacesConfigPackage.EXTENSION_TYPE__TEXT_CONTENT:
				setTextContent(TEXT_CONTENT_EDEFAULT);
				return;
			case FacesConfigPackage.EXTENSION_TYPE__ID:
				setId(ID_EDEFAULT);
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
			case FacesConfigPackage.EXTENSION_TYPE__CHILD_NODES:
				return childNodes != null && !childNodes.isEmpty();
			case FacesConfigPackage.EXTENSION_TYPE__TEXT_CONTENT:
				return TEXT_CONTENT_EDEFAULT == null ? textContent != null : !TEXT_CONTENT_EDEFAULT.equals(textContent);
			case FacesConfigPackage.EXTENSION_TYPE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
		}
		return super.eIsSet(featureID);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated NOT
	 */
    public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (textContent: ");//$NON-NLS-1$
		result.append(textContent);
		result.append(", id: ");//$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //ExtensionTypeImpl
