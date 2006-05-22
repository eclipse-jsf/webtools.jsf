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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowNodeImpl#getPageflow <em>Pageflow</em>}</li>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowNodeImpl#getOutlinks <em>Outlinks</em>}</li>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowNodeImpl#getInlinks <em>Inlinks</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public abstract class PageflowNodeImpl extends PageflowElementImpl implements
		PageflowNode {
	/**
	 * The cached value of the '{@link #getOutlinks() <em>Outlinks</em>}'
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getOutlinks()
	 * @generated
	 * @ordered
	 */
	protected EList outlinks = null;

	/**
	 * The cached value of the '{@link #getInlinks() <em>Inlinks</em>}'
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getInlinks()
	 * @generated
	 * @ordered
	 */
	protected EList inlinks = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected PageflowNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EClass eStaticClass() {
		return PageflowPackage.eINSTANCE.getPageflowNode();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Pageflow getPageflow() {
		if (eContainerFeatureID != PageflowPackage.PAGEFLOW_NODE__PAGEFLOW)
			return null;
		return (Pageflow) eContainer;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setPageflow(Pageflow newPageflow) {
		if (newPageflow != eContainer
				|| (eContainerFeatureID != PageflowPackage.PAGEFLOW_NODE__PAGEFLOW && newPageflow != null)) {
			if (EcoreUtil.isAncestor(this, newPageflow))
				throw new IllegalArgumentException(
						"Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newPageflow != null)
				msgs = ((InternalEObject) newPageflow).eInverseAdd(this,
						PageflowPackage.PAGEFLOW__NODES, Pageflow.class, msgs);
			msgs = eBasicSetContainer((InternalEObject) newPageflow,
					PageflowPackage.PAGEFLOW_NODE__PAGEFLOW, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PageflowPackage.PAGEFLOW_NODE__PAGEFLOW, newPageflow,
					newPageflow));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList getOutlinks() {
		if (outlinks == null) {
			outlinks = new EObjectWithInverseResolvingEList(PageflowLink.class, this,
					PageflowPackage.PAGEFLOW_NODE__OUTLINKS,
					PageflowPackage.PF_LINK__SOURCE);
		}
		return outlinks;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList getInlinks() {
		if (inlinks == null) {
			inlinks = new EObjectWithInverseResolvingEList(PageflowLink.class, this,
					PageflowPackage.PAGEFLOW_NODE__INLINKS,
					PageflowPackage.PF_LINK__TARGET);
		}
		return inlinks;
	}

	/**
	 * @generated NOT
	 */
	public PageflowLink findLinkTo(PageflowNode target) {
		//
		java.util.Iterator i = this.getOutlinks().iterator();
		while (i.hasNext()) {
			PageflowLink link = (PageflowLink) i.next();
			if (link.getTarget() == target)
				return link;
		}
		return null;

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
			case PageflowPackage.PAGEFLOW_NODE__PAGEFLOW:
				if (eContainer != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return eBasicSetContainer(otherEnd,
						PageflowPackage.PAGEFLOW_NODE__PAGEFLOW, msgs);
			case PageflowPackage.PAGEFLOW_NODE__OUTLINKS:
				return ((InternalEList) getOutlinks()).basicAdd(otherEnd, msgs);
			case PageflowPackage.PAGEFLOW_NODE__INLINKS:
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
			case PageflowPackage.PAGEFLOW_NODE__PAGEFLOW:
				return eBasicSetContainer(null,
						PageflowPackage.PAGEFLOW_NODE__PAGEFLOW, msgs);
			case PageflowPackage.PAGEFLOW_NODE__OUTLINKS:
				return ((InternalEList) getOutlinks()).basicRemove(otherEnd,
						msgs);
			case PageflowPackage.PAGEFLOW_NODE__INLINKS:
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
			case PageflowPackage.PAGEFLOW_NODE__PAGEFLOW:
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
		case PageflowPackage.PAGEFLOW_NODE__NAME:
			return getName();
		case PageflowPackage.PAGEFLOW_NODE__COMMENT:
			return getComment();
		case PageflowPackage.PAGEFLOW_NODE__X:
			return new Integer(getX());
		case PageflowPackage.PAGEFLOW_NODE__Y:
			return new Integer(getY());
		case PageflowPackage.PAGEFLOW_NODE__WIDTH:
			return new Integer(getWidth());
		case PageflowPackage.PAGEFLOW_NODE__HEIGHT:
			return new Integer(getHeight());
		case PageflowPackage.PAGEFLOW_NODE__ID:
			return getId();
		case PageflowPackage.PAGEFLOW_NODE__PAGEFLOW:
			return getPageflow();
		case PageflowPackage.PAGEFLOW_NODE__OUTLINKS:
			return getOutlinks();
		case PageflowPackage.PAGEFLOW_NODE__INLINKS:
			return getInlinks();
		}
		return super.eGet(eFeature, resolve);//eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
		case PageflowPackage.PAGEFLOW_NODE__NAME:
			setName((String) newValue);
			return;
		case PageflowPackage.PAGEFLOW_NODE__COMMENT:
			setComment((String) newValue);
			return;
		case PageflowPackage.PAGEFLOW_NODE__X:
			setX(((Integer) newValue).intValue());
			return;
		case PageflowPackage.PAGEFLOW_NODE__Y:
			setY(((Integer) newValue).intValue());
			return;
		case PageflowPackage.PAGEFLOW_NODE__WIDTH:
			setWidth(((Integer) newValue).intValue());
			return;
		case PageflowPackage.PAGEFLOW_NODE__HEIGHT:
			setHeight(((Integer) newValue).intValue());
			return;
		case PageflowPackage.PAGEFLOW_NODE__ID:
			setId((String) newValue);
			return;
		case PageflowPackage.PAGEFLOW_NODE__PAGEFLOW:
			setPageflow((Pageflow) newValue);
			return;
		case PageflowPackage.PAGEFLOW_NODE__OUTLINKS:
			getOutlinks().clear();
			getOutlinks().addAll((Collection) newValue);
			return;
		case PageflowPackage.PAGEFLOW_NODE__INLINKS:
			getInlinks().clear();
			getInlinks().addAll((Collection) newValue);
			return;
		}
		super.eSet(eFeature, newValue);//eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
		case PageflowPackage.PAGEFLOW_NODE__NAME:
			setName(NAME_EDEFAULT);
			return;
		case PageflowPackage.PAGEFLOW_NODE__COMMENT:
			setComment(COMMENT_EDEFAULT);
			return;
		case PageflowPackage.PAGEFLOW_NODE__X:
			setX(X_EDEFAULT);
			return;
		case PageflowPackage.PAGEFLOW_NODE__Y:
			setY(Y_EDEFAULT);
			return;
		case PageflowPackage.PAGEFLOW_NODE__WIDTH:
			setWidth(WIDTH_EDEFAULT);
			return;
		case PageflowPackage.PAGEFLOW_NODE__HEIGHT:
			setHeight(HEIGHT_EDEFAULT);
			return;
		case PageflowPackage.PAGEFLOW_NODE__ID:
			setId(ID_EDEFAULT);
			return;
		case PageflowPackage.PAGEFLOW_NODE__PAGEFLOW:
			setPageflow((Pageflow) null);
			return;
		case PageflowPackage.PAGEFLOW_NODE__OUTLINKS:
			getOutlinks().clear();
			return;
		case PageflowPackage.PAGEFLOW_NODE__INLINKS:
			getInlinks().clear();
			return;
		}
		super.eUnset(eFeature);//eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
		case PageflowPackage.PAGEFLOW_NODE__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT
					.equals(name);
		case PageflowPackage.PAGEFLOW_NODE__COMMENT:
			return COMMENT_EDEFAULT == null ? comment != null
					: !COMMENT_EDEFAULT.equals(comment);
		case PageflowPackage.PAGEFLOW_NODE__X:
			return x != X_EDEFAULT;
		case PageflowPackage.PAGEFLOW_NODE__Y:
			return y != Y_EDEFAULT;
		case PageflowPackage.PAGEFLOW_NODE__WIDTH:
			return width != WIDTH_EDEFAULT;
		case PageflowPackage.PAGEFLOW_NODE__HEIGHT:
			return height != HEIGHT_EDEFAULT;
		case PageflowPackage.PAGEFLOW_NODE__ID:
			return ID_EDEFAULT == null ? getId() != null : !ID_EDEFAULT
					.equals(getId());
		case PageflowPackage.PAGEFLOW_NODE__PAGEFLOW:
			return getPageflow() != null;
		case PageflowPackage.PAGEFLOW_NODE__OUTLINKS:
			return outlinks != null && !outlinks.isEmpty();
		case PageflowPackage.PAGEFLOW_NODE__INLINKS:
			return inlinks != null && !inlinks.isEmpty();
		}
		return super.eIsSet(eFeature);//eDynamicIsSet(eFeature);
	}

}
// PageflowNodeImpl
