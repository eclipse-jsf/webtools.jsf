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

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLinkBendpoint;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>PF Link Bendpoint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowLinkBendpointImpl#getD1Width <em>D1 Width</em>}</li>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowLinkBendpointImpl#getD1Height <em>D1 Height</em>}</li>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowLinkBendpointImpl#getD2Width <em>D2 Width</em>}</li>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowLinkBendpointImpl#getD2Height <em>D2 Height</em>}</li>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowLinkBendpointImpl#getWeight <em>Weight</em>}</li>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowLinkBendpointImpl#getLink <em>Link</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class PageflowLinkBendpointImpl extends EObjectImpl implements PageflowLinkBendpoint {
	/**
	 * The default value of the '{@link #getD1Width() <em>D1 Width</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getD1Width()
	 * @generated
	 * @ordered
	 */
	protected static final int D1_WIDTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getD1Width() <em>D1 Width</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getD1Width()
	 * @generated
	 * @ordered
	 */
	protected int d1Width = D1_WIDTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getD1Height() <em>D1 Height</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getD1Height()
	 * @generated
	 * @ordered
	 */
	protected static final int D1_HEIGHT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getD1Height() <em>D1 Height</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getD1Height()
	 * @generated
	 * @ordered
	 */
	protected int d1Height = D1_HEIGHT_EDEFAULT;

	/**
	 * The default value of the '{@link #getD2Width() <em>D2 Width</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getD2Width()
	 * @generated
	 * @ordered
	 */
	protected static final int D2_WIDTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getD2Width() <em>D2 Width</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getD2Width()
	 * @generated
	 * @ordered
	 */
	protected int d2Width = D2_WIDTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getD2Height() <em>D2 Height</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getD2Height()
	 * @generated
	 * @ordered
	 */
	protected static final int D2_HEIGHT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getD2Height() <em>D2 Height</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getD2Height()
	 * @generated
	 * @ordered
	 */
	protected int d2Height = D2_HEIGHT_EDEFAULT;

	/**
	 * The default value of the '{@link #getWeight() <em>Weight</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getWeight()
	 * @generated
	 * @ordered
	 */
	protected static final float WEIGHT_EDEFAULT = 0.5F;

	/**
	 * The cached value of the '{@link #getWeight() <em>Weight</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getWeight()
	 * @generated
	 * @ordered
	 */
	protected float weight = WEIGHT_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	private Dimension dimStart, dimEnd;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected PageflowLinkBendpointImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EClass eStaticClass() {
		return PageflowPackage.eINSTANCE.getPFLinkBendpoint();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public int getD1Width() {
		return d1Width;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setD1Width(int newD1Width) {
		int oldD1Width = d1Width;
		d1Width = newD1Width;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PageflowPackage.PF_LINK_BENDPOINT__D1_WIDTH, oldD1Width,
					d1Width));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public int getD1Height() {
		return d1Height;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setD1Height(int newD1Height) {
		int oldD1Height = d1Height;
		d1Height = newD1Height;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PageflowPackage.PF_LINK_BENDPOINT__D1_HEIGHT, oldD1Height,
					d1Height));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public int getD2Width() {
		return d2Width;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setD2Width(int newD2Width) {
		int oldD2Width = d2Width;
		d2Width = newD2Width;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PageflowPackage.PF_LINK_BENDPOINT__D2_WIDTH, oldD2Width,
					d2Width));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public int getD2Height() {
		return d2Height;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setD2Height(int newD2Height) {
		int oldD2Height = d2Height;
		d2Height = newD2Height;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PageflowPackage.PF_LINK_BENDPOINT__D2_HEIGHT, oldD2Height,
					d2Height));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public float getWeight() {
		return weight;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setWeight(float newWeight) {
		float oldWeight = weight;
		weight = newWeight;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PageflowPackage.PF_LINK_BENDPOINT__WEIGHT, oldWeight,
					weight));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public PageflowLink getLink() {
		if (eContainerFeatureID != PageflowPackage.PF_LINK_BENDPOINT__LINK)
			return null;
		return (PageflowLink) eContainer;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setLink(PageflowLink newLink) {
		if (newLink != eContainer
				|| (eContainerFeatureID != PageflowPackage.PF_LINK_BENDPOINT__LINK && newLink != null)) {
			if (EcoreUtil.isAncestor(this, newLink))
				throw new IllegalArgumentException(
						"Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newLink != null)
				msgs = ((InternalEObject) newLink).eInverseAdd(this,
						PageflowPackage.PF_LINK__BEND_POINTS, PageflowLink.class,
						msgs);
			msgs = eBasicSetContainer((InternalEObject) newLink,
					PageflowPackage.PF_LINK_BENDPOINT__LINK, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PageflowPackage.PF_LINK_BENDPOINT__LINK, newLink, newLink));
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
			case PageflowPackage.PF_LINK_BENDPOINT__LINK:
				if (eContainer != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return eBasicSetContainer(otherEnd,
						PageflowPackage.PF_LINK_BENDPOINT__LINK, msgs);
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
			case PageflowPackage.PF_LINK_BENDPOINT__LINK:
				return eBasicSetContainer(null,
						PageflowPackage.PF_LINK_BENDPOINT__LINK, msgs);
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
			case PageflowPackage.PF_LINK_BENDPOINT__LINK:
				return ((InternalEObject) eContainer).eInverseRemove(this,
						PageflowPackage.PF_LINK__BEND_POINTS, PageflowLink.class,
						msgs);
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
		case PageflowPackage.PF_LINK_BENDPOINT__D1_WIDTH:
			return new Integer(getD1Width());
		case PageflowPackage.PF_LINK_BENDPOINT__D1_HEIGHT:
			return new Integer(getD1Height());
		case PageflowPackage.PF_LINK_BENDPOINT__D2_WIDTH:
			return new Integer(getD2Width());
		case PageflowPackage.PF_LINK_BENDPOINT__D2_HEIGHT:
			return new Integer(getD2Height());
		case PageflowPackage.PF_LINK_BENDPOINT__WEIGHT:
			return new Float(getWeight());
		case PageflowPackage.PF_LINK_BENDPOINT__LINK:
			return getLink();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
		case PageflowPackage.PF_LINK_BENDPOINT__D1_WIDTH:
			setD1Width(((Integer) newValue).intValue());
			return;
		case PageflowPackage.PF_LINK_BENDPOINT__D1_HEIGHT:
			setD1Height(((Integer) newValue).intValue());
			return;
		case PageflowPackage.PF_LINK_BENDPOINT__D2_WIDTH:
			setD2Width(((Integer) newValue).intValue());
			return;
		case PageflowPackage.PF_LINK_BENDPOINT__D2_HEIGHT:
			setD2Height(((Integer) newValue).intValue());
			return;
		case PageflowPackage.PF_LINK_BENDPOINT__WEIGHT:
			setWeight(((Float) newValue).floatValue());
			return;
		case PageflowPackage.PF_LINK_BENDPOINT__LINK:
			setLink((PageflowLink) newValue);
			return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
		case PageflowPackage.PF_LINK_BENDPOINT__D1_WIDTH:
			setD1Width(D1_WIDTH_EDEFAULT);
			return;
		case PageflowPackage.PF_LINK_BENDPOINT__D1_HEIGHT:
			setD1Height(D1_HEIGHT_EDEFAULT);
			return;
		case PageflowPackage.PF_LINK_BENDPOINT__D2_WIDTH:
			setD2Width(D2_WIDTH_EDEFAULT);
			return;
		case PageflowPackage.PF_LINK_BENDPOINT__D2_HEIGHT:
			setD2Height(D2_HEIGHT_EDEFAULT);
			return;
		case PageflowPackage.PF_LINK_BENDPOINT__WEIGHT:
			setWeight(WEIGHT_EDEFAULT);
			return;
		case PageflowPackage.PF_LINK_BENDPOINT__LINK:
			setLink((PageflowLink) null);
			return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
		case PageflowPackage.PF_LINK_BENDPOINT__D1_WIDTH:
			return d1Width != D1_WIDTH_EDEFAULT;
		case PageflowPackage.PF_LINK_BENDPOINT__D1_HEIGHT:
			return d1Height != D1_HEIGHT_EDEFAULT;
		case PageflowPackage.PF_LINK_BENDPOINT__D2_WIDTH:
			return d2Width != D2_WIDTH_EDEFAULT;
		case PageflowPackage.PF_LINK_BENDPOINT__D2_HEIGHT:
			return d2Height != D2_HEIGHT_EDEFAULT;
		case PageflowPackage.PF_LINK_BENDPOINT__WEIGHT:
			return weight != WEIGHT_EDEFAULT;
		case PageflowPackage.PF_LINK_BENDPOINT__LINK:
			return getLink() != null;
		}
		return eDynamicIsSet(eFeature);
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
		result.append(" (d1Width: ");
		result.append(d1Width);
		result.append(", d1Height: ");
		result.append(d1Height);
		result.append(", d2Width: ");
		result.append(d2Width);
		result.append(", d2Height: ");
		result.append(d2Height);
		result.append(", weight: ");
		result.append(weight);
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Dimension getFirstRelativeDimension() {
		dimStart = new Dimension(getD1Width(), getD1Height());
		return dimStart;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Dimension getSecondRelativeDimension() {
		dimEnd = new Dimension(getD2Width(), getD2Height());
		return dimEnd;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setRelativeDimensions(Dimension dim1, Dimension dim2) {
		dimStart = dim1;
		dimEnd = dim2;
		setD1Width(dimStart.width);
		setD1Height(dimStart.height);

		setD2Width(dimEnd.width);
		setD2Height(dimEnd.height);
	}

}
// PFLinkBendpointImpl
