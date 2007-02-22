/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.provisional.DTInfoPackage;
import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.provisional.Operation;
import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.provisional.TagConvertInfo;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tag Convert Info</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.TagConvertInfoImpl#getOperations <em>Operations</em>}</li>
 *   <li>{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.TagConvertInfoImpl#getClassname <em>Classname</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TagConvertInfoImpl extends EObjectImpl implements TagConvertInfo {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2007 Oracle Corporation";

	/**
	 * The cached value of the '{@link #getOperations() <em>Operations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperations()
	 * @generated
	 * @ordered
	 */
	protected EList operations = null;

	/**
	 * The default value of the '{@link #getClassname() <em>Classname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassname()
	 * @generated
	 * @ordered
	 */
	protected static final String CLASSNAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getClassname() <em>Classname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassname()
	 * @generated
	 * @ordered
	 */
	protected String classname = CLASSNAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TagConvertInfoImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DTInfoPackage.Literals.TAG_CONVERT_INFO;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getOperations() {
		if (operations == null) {
			operations = new EObjectContainmentEList(Operation.class, this, DTInfoPackage.TAG_CONVERT_INFO__OPERATIONS);
		}
		return operations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getClassname() {
		return classname;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClassname(String newClassname) {
		String oldClassname = classname;
		classname = newClassname;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DTInfoPackage.TAG_CONVERT_INFO__CLASSNAME, oldClassname, classname));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DTInfoPackage.TAG_CONVERT_INFO__OPERATIONS:
				return ((InternalEList)getOperations()).basicRemove(otherEnd, msgs);
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
			case DTInfoPackage.TAG_CONVERT_INFO__OPERATIONS:
				return getOperations();
			case DTInfoPackage.TAG_CONVERT_INFO__CLASSNAME:
				return getClassname();
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
			case DTInfoPackage.TAG_CONVERT_INFO__OPERATIONS:
				getOperations().clear();
				getOperations().addAll((Collection)newValue);
				return;
			case DTInfoPackage.TAG_CONVERT_INFO__CLASSNAME:
				setClassname((String)newValue);
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
			case DTInfoPackage.TAG_CONVERT_INFO__OPERATIONS:
				getOperations().clear();
				return;
			case DTInfoPackage.TAG_CONVERT_INFO__CLASSNAME:
				setClassname(CLASSNAME_EDEFAULT);
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
			case DTInfoPackage.TAG_CONVERT_INFO__OPERATIONS:
				return operations != null && !operations.isEmpty();
			case DTInfoPackage.TAG_CONVERT_INFO__CLASSNAME:
				return CLASSNAME_EDEFAULT == null ? classname != null : !CLASSNAME_EDEFAULT.equals(classname);
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
		result.append(" (classname: ");
		result.append(classname);
		result.append(')');
		return result.toString();
	}

} //TagConvertInfoImpl
