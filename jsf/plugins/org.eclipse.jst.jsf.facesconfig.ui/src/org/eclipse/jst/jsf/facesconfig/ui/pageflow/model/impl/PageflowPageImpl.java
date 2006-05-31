/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>PF Page</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowPageImpl#getPath <em>Path</em>}</li>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowPageImpl#getSmallicon <em>Smallicon</em>}</li>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowPageImpl#getLargeicon <em>Largeicon</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class PageflowPageImpl extends PageflowNodeImpl implements PageflowPage {
	private String path;

	private String smallIcon;

	private String largeIcon;

	/**
	 * The default value of the '{@link #getPath() <em>Path</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getPath()
	 * @generated
	 * @ordered
	 */
	protected static final String PATH_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getSmallicon() <em>Smallicon</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getSmallicon()
	 * @generated
	 * @ordered
	 */
	protected static final String SMALLICON_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getLargeicon() <em>Largeicon</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getLargeicon()
	 * @generated
	 * @ordered
	 */
	protected static final String LARGEICON_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected PageflowPageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EClass eStaticClass() {
		return PageflowPackage.eINSTANCE.getPFPage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getPath() {
		String result = (String) getFCElements().get(
				PageflowPackage.PF_PAGE__PATH);
		// Try to return reasonable result.
		return result == null && getFCElements().isEmpty() ? path
				: (result != null ? result : PATH_EDEFAULT);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setPath(String newValue) {
		String oldPath = getPath();
		path = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PageflowPackage.PF_PAGE__PATH, oldPath, newValue));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */

	public void setComment(String newValue) {
		super.setComment(newValue);
		String oldComment = getComment();
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PageflowPackage.PAGEFLOW_ELEMENT__COMMENT, oldComment,
					newValue));

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */

	public String getComment() {
		String result = (String) getFCElements().get(
				PageflowPackage.PF_PAGE__COMMENT);
		return result == null
				&& (getFCElements().isEmpty() || ((PageReferenceElement) getFCElements())
						.isEndOnly()) ? super.getComment()
				: (result != null ? result
						: PageflowElementImpl.COMMENT_EDEFAULT);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */

	public String getName() {
		String result;
		// To see if it is in navigation rule.
		result = (String) getFCElements().get(PageflowPackage.PF_PAGE__NAME);
		if (result == null) {
			if (super.getName() == null || super.getName().trim().length() == 0) {
				result = getPath();
			} else {
				result = super.getName();
			}
		}
		return result == null
				&& (getFCElements().isEmpty() || ((PageReferenceElement) getFCElements())
						.isEndOnly()) ? PageflowElementImpl.NAME_EDEFAULT
				: (result == null ? null : result);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */

	public void setName(String newName) {
		super.setName(newName);
		String oldName = getName();
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PageflowPackage.PF_PAGE__NAME, oldName, newName));

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getSmallicon() {
		String result = (String) getFCElements().get(
				PageflowPackage.PF_PAGE__SMALLICON);

		return result == null
				&& (getFCElements().isEmpty() || ((PageReferenceElement) getFCElements())
						.isEndOnly()) ? smallIcon : (result != null ? result
				: SMALLICON_EDEFAULT);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setSmallicon(String newValue) {
		String oldSmallicon = getSmallicon();
		smallIcon = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PageflowPackage.PF_PAGE__SMALLICON, oldSmallicon, newValue));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getLargeicon() {
		String result = (String) getFCElements().get(
				PageflowPackage.PF_PAGE__LARGEICON);

		return result == null
				&& (getFCElements().isEmpty() || ((PageReferenceElement) getFCElements())
						.isEndOnly()) ? largeIcon : (result != null ? result
				: LARGEICON_EDEFAULT);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setLargeicon(String newValue) {
		String oldLargeIcon = getLargeicon();
		largeIcon = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PageflowPackage.PF_PAGE__LARGEICON, oldLargeIcon, newValue));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd,
			int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
			case PageflowPackage.PF_PAGE__PAGEFLOW:
				if (eContainer != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return eBasicSetContainer(otherEnd,
						PageflowPackage.PF_PAGE__PAGEFLOW, msgs);
			case PageflowPackage.PF_PAGE__OUTLINKS:
				return ((InternalEList) getOutlinks()).basicAdd(otherEnd, msgs);
			case PageflowPackage.PF_PAGE__INLINKS:
				return ((InternalEList) getInlinks()).basicAdd(otherEnd, msgs);
			default:
				return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
			}
		}
		if (eContainer != null)
			msgs = eBasicRemoveFromContainer(msgs);
		return eBasicSetContainer(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
			case PageflowPackage.PF_PAGE__PAGEFLOW:
				return eBasicSetContainer(null,
						PageflowPackage.PF_PAGE__PAGEFLOW, msgs);
			case PageflowPackage.PF_PAGE__OUTLINKS:
				return ((InternalEList) getOutlinks()).basicRemove(otherEnd,
						msgs);
			case PageflowPackage.PF_PAGE__INLINKS:
				return ((InternalEList) getInlinks()).basicRemove(otherEnd,
						msgs);
			default:
				return eDynamicInverseRemove(otherEnd, featureID, baseClass,
						msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs) {
		if (eContainerFeatureID >= 0) {
			switch (eContainerFeatureID) {
			case PageflowPackage.PF_PAGE__PAGEFLOW:
				return ((InternalEObject) eContainer).eInverseRemove(this,
						PageflowPackage.PAGEFLOW__NODES, Pageflow.class, msgs);
			default:
				return eDynamicBasicRemoveFromContainer(msgs);
			}
		}
		return ((InternalEObject) eContainer).eInverseRemove(this,
				EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
		case PageflowPackage.PF_PAGE__NAME:
			return getName();
		case PageflowPackage.PF_PAGE__COMMENT:
			return getComment();
		case PageflowPackage.PF_PAGE__X:
			return new Integer(getX());
		case PageflowPackage.PF_PAGE__Y:
			return new Integer(getY());
		case PageflowPackage.PF_PAGE__WIDTH:
			return new Integer(getWidth());
		case PageflowPackage.PF_PAGE__HEIGHT:
			return new Integer(getHeight());
		case PageflowPackage.PF_PAGE__ID:
			return getId();
		case PageflowPackage.PF_PAGE__PAGEFLOW:
			return getPageflow();
		case PageflowPackage.PF_PAGE__OUTLINKS:
			return getOutlinks();
		case PageflowPackage.PF_PAGE__INLINKS:
			return getInlinks();
		case PageflowPackage.PF_PAGE__PATH:
			return getPath();
		case PageflowPackage.PF_PAGE__SMALLICON:
			return getSmallicon();
		case PageflowPackage.PF_PAGE__LARGEICON:
			return getLargeicon();
		}
		return super.eGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
		case PageflowPackage.PF_PAGE__NAME:
			setName((String) newValue);
			return;
		case PageflowPackage.PF_PAGE__COMMENT:
			setComment((String) newValue);
			return;
		case PageflowPackage.PF_PAGE__X:
			setX(((Integer) newValue).intValue());
			return;
		case PageflowPackage.PF_PAGE__Y:
			setY(((Integer) newValue).intValue());
			return;
		case PageflowPackage.PF_PAGE__WIDTH:
			setWidth(((Integer) newValue).intValue());
			return;
		case PageflowPackage.PF_PAGE__HEIGHT:
			setHeight(((Integer) newValue).intValue());
			return;
		case PageflowPackage.PF_PAGE__ID:
			setId((String) newValue);
			return;
		case PageflowPackage.PF_PAGE__PAGEFLOW:
			setPageflow((Pageflow) newValue);
			return;
		case PageflowPackage.PF_PAGE__OUTLINKS:
			getOutlinks().clear();
			getOutlinks().addAll((Collection) newValue);
			return;
		case PageflowPackage.PF_PAGE__INLINKS:
			getInlinks().clear();
			getInlinks().addAll((Collection) newValue);
			return;
		case PageflowPackage.PF_PAGE__PATH:
			setPath((String) newValue);
			return;
		case PageflowPackage.PF_PAGE__SMALLICON:
			setSmallicon((String) newValue);
			return;
		case PageflowPackage.PF_PAGE__LARGEICON:
			setLargeicon((String) newValue);
			return;
		}
		super.eSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
		case PageflowPackage.PF_PAGE__NAME:
			setName(NAME_EDEFAULT);
			return;
		case PageflowPackage.PF_PAGE__COMMENT:
			setComment(COMMENT_EDEFAULT);
			return;
		case PageflowPackage.PF_PAGE__X:
			setX(X_EDEFAULT);
			return;
		case PageflowPackage.PF_PAGE__Y:
			setY(Y_EDEFAULT);
			return;
		case PageflowPackage.PF_PAGE__WIDTH:
			setWidth(WIDTH_EDEFAULT);
			return;
		case PageflowPackage.PF_PAGE__HEIGHT:
			setHeight(HEIGHT_EDEFAULT);
			return;
		case PageflowPackage.PF_PAGE__ID:
			setId(ID_EDEFAULT);
			return;
		case PageflowPackage.PF_PAGE__PAGEFLOW:
			setPageflow((Pageflow) null);
			return;
		case PageflowPackage.PF_PAGE__OUTLINKS:
			getOutlinks().clear();
			return;
		case PageflowPackage.PF_PAGE__INLINKS:
			getInlinks().clear();
			return;
		case PageflowPackage.PF_PAGE__PATH:
			setPath(PATH_EDEFAULT);
			return;
		case PageflowPackage.PF_PAGE__SMALLICON:
			setSmallicon(SMALLICON_EDEFAULT);
			return;
		case PageflowPackage.PF_PAGE__LARGEICON:
			setLargeicon(LARGEICON_EDEFAULT);
			return;
		}
		super.eUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
		case PageflowPackage.PF_PAGE__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT
					.equals(name);
		case PageflowPackage.PF_PAGE__COMMENT:
			return COMMENT_EDEFAULT == null ? comment != null
					: !COMMENT_EDEFAULT.equals(comment);
		case PageflowPackage.PF_PAGE__X:
			return x != X_EDEFAULT;
		case PageflowPackage.PF_PAGE__Y:
			return y != Y_EDEFAULT;
		case PageflowPackage.PF_PAGE__WIDTH:
			return width != WIDTH_EDEFAULT;
		case PageflowPackage.PF_PAGE__HEIGHT:
			return height != HEIGHT_EDEFAULT;
		case PageflowPackage.PF_PAGE__ID:
			return ID_EDEFAULT == null ? getId() != null : !ID_EDEFAULT
					.equals(getId());
		case PageflowPackage.PF_PAGE__PAGEFLOW:
			return getPageflow() != null;
		case PageflowPackage.PF_PAGE__OUTLINKS:
			return outlinks != null && !outlinks.isEmpty();
		case PageflowPackage.PF_PAGE__INLINKS:
			return inlinks != null && !inlinks.isEmpty();
		case PageflowPackage.PF_PAGE__PATH:
			return PATH_EDEFAULT == null ? getPath() != null : !PATH_EDEFAULT
					.equals(getPath());
		case PageflowPackage.PF_PAGE__SMALLICON:
			return SMALLICON_EDEFAULT == null ? getSmallicon() != null
					: !SMALLICON_EDEFAULT.equals(getSmallicon());
		case PageflowPackage.PF_PAGE__LARGEICON:
			return LARGEICON_EDEFAULT == null ? getLargeicon() != null
					: !LARGEICON_EDEFAULT.equals(getLargeicon());
		}
		return super.eIsSet(eFeature);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (path: ");
		result.append(getPath());
		result.append(", smallicon: ");
		result.append(getSmallicon());
		result.append(", largeicon: ");
		result.append(getLargeicon());
		result.append(')');
		return result.toString();
	}

	public ReferenceElement getFCElements() {

		if (refElement == null) {
			refElement = new PageReferenceElement(this);
		}
		return refElement;
	}

}
// PFPageImpl
