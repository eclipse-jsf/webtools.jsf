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

import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.DTInfo;
import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.DTInfoPackage;
import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagConvertInfo;
import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DT Info</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.DTInfoImpl#getTagConvertInfo <em>Tag Convert Info</em>}</li>
 *   <li>{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.DTInfoImpl#getTagDecorateInfos <em>Tag Decorate Infos</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DTInfoImpl extends EObjectImpl implements DTInfo {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2007 Oracle Corporation"; //$NON-NLS-1$

	/**
	 * The cached value of the '{@link #getTagConvertInfo() <em>Tag Convert Info</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTagConvertInfo()
	 * @generated
	 * @ordered
	 */
	protected TagConvertInfo tagConvertInfo;

	/**
	 * The cached value of the '{@link #getTagDecorateInfos() <em>Tag Decorate Infos</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTagDecorateInfos()
	 * @generated
	 * @ordered
	 */
	protected EList tagDecorateInfos;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DTInfoImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DTInfoPackage.Literals.DT_INFO;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TagConvertInfo getTagConvertInfo() {
		return tagConvertInfo;
	}

	/**
	 * <!-- begin-user-doc -->
     * @param newTagConvertInfo 
     * @param msgs 
     * @return the notification chaing 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTagConvertInfo(TagConvertInfo newTagConvertInfo, NotificationChain msgs) {
		TagConvertInfo oldTagConvertInfo = tagConvertInfo;
		tagConvertInfo = newTagConvertInfo;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DTInfoPackage.DT_INFO__TAG_CONVERT_INFO, oldTagConvertInfo, newTagConvertInfo);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTagConvertInfo(TagConvertInfo newTagConvertInfo) {
		if (newTagConvertInfo != tagConvertInfo) {
			NotificationChain msgs = null;
			if (tagConvertInfo != null)
				msgs = ((InternalEObject)tagConvertInfo).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DTInfoPackage.DT_INFO__TAG_CONVERT_INFO, null, msgs);
			if (newTagConvertInfo != null)
				msgs = ((InternalEObject)newTagConvertInfo).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DTInfoPackage.DT_INFO__TAG_CONVERT_INFO, null, msgs);
			msgs = basicSetTagConvertInfo(newTagConvertInfo, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DTInfoPackage.DT_INFO__TAG_CONVERT_INFO, newTagConvertInfo, newTagConvertInfo));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getTagDecorateInfos() {
		if (tagDecorateInfos == null) {
			tagDecorateInfos = new EObjectContainmentEList(TagDecorateInfo.class, this, DTInfoPackage.DT_INFO__TAG_DECORATE_INFOS);
		}
		return tagDecorateInfos;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DTInfoPackage.DT_INFO__TAG_CONVERT_INFO:
				return basicSetTagConvertInfo(null, msgs);
			case DTInfoPackage.DT_INFO__TAG_DECORATE_INFOS:
				return ((InternalEList)getTagDecorateInfos()).basicRemove(otherEnd, msgs);
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
			case DTInfoPackage.DT_INFO__TAG_CONVERT_INFO:
				return getTagConvertInfo();
			case DTInfoPackage.DT_INFO__TAG_DECORATE_INFOS:
				return getTagDecorateInfos();
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
			case DTInfoPackage.DT_INFO__TAG_CONVERT_INFO:
				setTagConvertInfo((TagConvertInfo)newValue);
				return;
			case DTInfoPackage.DT_INFO__TAG_DECORATE_INFOS:
				getTagDecorateInfos().clear();
				getTagDecorateInfos().addAll((Collection)newValue);
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
			case DTInfoPackage.DT_INFO__TAG_CONVERT_INFO:
				setTagConvertInfo((TagConvertInfo)null);
				return;
			case DTInfoPackage.DT_INFO__TAG_DECORATE_INFOS:
				getTagDecorateInfos().clear();
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
			case DTInfoPackage.DT_INFO__TAG_CONVERT_INFO:
				return tagConvertInfo != null;
			case DTInfoPackage.DT_INFO__TAG_DECORATE_INFOS:
				return tagDecorateInfos != null && !tagDecorateInfos.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //DTInfoImpl
