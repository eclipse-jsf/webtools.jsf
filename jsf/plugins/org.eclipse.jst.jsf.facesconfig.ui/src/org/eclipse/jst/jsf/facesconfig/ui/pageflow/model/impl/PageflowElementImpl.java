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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowElementImpl#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowElementImpl#getComment <em>Comment</em>}</li>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowElementImpl#getX <em>X</em>}</li>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowElementImpl#getY <em>Y</em>}</li>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowElementImpl#getWidth <em>Width</em>}</li>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowElementImpl#getHeight <em>Height</em>}</li>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowElementImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public abstract class PageflowElementImpl extends EObjectImpl implements
		PageflowElement {
	protected static final String idPrefix = "pf";

	protected String id;

	protected static int counter = 0;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = "unnamed";

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getComment() <em>Comment</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getComment()
	 * @generated
	 * @ordered
	 */
	protected static final String COMMENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getComment() <em>Comment</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getComment()
	 * @generated
	 * @ordered
	 */
	protected String comment = COMMENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getX() <em>X</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getX()
	 * @generated
	 * @ordered
	 */
	protected static final int X_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getX() <em>X</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getX()
	 * @generated
	 * @ordered
	 */
	protected int x = X_EDEFAULT;

	/**
	 * The default value of the '{@link #getY() <em>Y</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getY()
	 * @generated
	 * @ordered
	 */
	protected static final int Y_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getY() <em>Y</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getY()
	 * @generated
	 * @ordered
	 */
	protected int y = Y_EDEFAULT;

	/**
	 * The default value of the '{@link #getWidth() <em>Width</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getWidth()
	 * @generated
	 * @ordered
	 */
	protected static final int WIDTH_EDEFAULT = -1;

	/**
	 * The cached value of the '{@link #getWidth() <em>Width</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getWidth()
	 * @generated
	 * @ordered
	 */
	protected int width = WIDTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getHeight() <em>Height</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getHeight()
	 * @generated
	 * @ordered
	 */
	protected static final int HEIGHT_EDEFAULT = -1;

	/**
	 * The default value of the '{@link #getReferenceLink() <em>Reference Link</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getReferenceLink()
	 * @generated
	 * @ordered
	 */
	protected static final String REFERENCE_LINK_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getReferenceLink() <em>Reference Link</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getReferenceLink()
	 * @generated
	 * @ordered
	 */
	protected String referenceLink = REFERENCE_LINK_EDEFAULT;

	/**
	 * The cached value of the '{@link #getHeight() <em>Height</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getHeight()
	 * @generated
	 * @ordered
	 */
	protected int height = HEIGHT_EDEFAULT;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected PageflowElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EClass eStaticClass() {
		return PageflowPackage.eINSTANCE.getPageflowElement();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PageflowPackage.PAGEFLOW_ELEMENT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setComment(String newComment) {
		String oldComment = comment;
		comment = newComment;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PageflowPackage.PAGEFLOW_ELEMENT__COMMENT, oldComment,
					comment));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public int getX() {
		return x;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setX(int newX) {
		int oldX = x;
		x = newX;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PageflowPackage.PAGEFLOW_ELEMENT__X, oldX, x));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public int getY() {
		return y;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setY(int newY) {
		int oldY = y;
		y = newY;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PageflowPackage.PAGEFLOW_ELEMENT__Y, oldY, y));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setWidth(int newWidth) {
		int oldWidth = width;
		width = newWidth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PageflowPackage.PAGEFLOW_ELEMENT__WIDTH, oldWidth, width));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setHeight(int newHeight) {
		int oldHeight = height;
		height = newHeight;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PageflowPackage.PAGEFLOW_ELEMENT__HEIGHT, oldHeight, height));
	}

	/**
	 * Generate (and cache) an id as needed
	 * 
	 * @generated NOT
	 */
	public String getId() {
		if (id == null) {
			id = generateId();
		}
		return id;
	}

	/**
	 * Generate a random id based on the current time
	 * 
	 * @return the generated id
	 * 
	 * @generated NOT
	 */
	public synchronized String generateId() {
		long current = System.currentTimeMillis();
		return idPrefix + current + counter++;
	}

	/**
	 * Set or generate an Id This method should not be called except when
	 * populating from the serialization
	 * 
	 * @generated NOT
	 */
	public void setId(String newId) {
		if (newId == null && id == null) {
			id = generateId();
		} else {
			id = newId;
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getReferenceLink() {
		return referenceLink;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setReferenceLink(String newReferenceLink) {
		String oldReferenceLink = referenceLink;
		referenceLink = newReferenceLink;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PageflowPackage.PAGEFLOW_ELEMENT__REFERENCE_LINK,
					oldReferenceLink, referenceLink));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
		case PageflowPackage.PAGEFLOW_ELEMENT__NAME:
			return getName();
		case PageflowPackage.PAGEFLOW_ELEMENT__COMMENT:
			return getComment();
		case PageflowPackage.PAGEFLOW_ELEMENT__X:
			return new Integer(getX());
		case PageflowPackage.PAGEFLOW_ELEMENT__Y:
			return new Integer(getY());
		case PageflowPackage.PAGEFLOW_ELEMENT__WIDTH:
			return new Integer(getWidth());
		case PageflowPackage.PAGEFLOW_ELEMENT__HEIGHT:
			return new Integer(getHeight());
		case PageflowPackage.PAGEFLOW_ELEMENT__ID:
			return getId();
		case PageflowPackage.PAGEFLOW_ELEMENT__REFERENCE_LINK:
			return getReferenceLink();
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
		case PageflowPackage.PAGEFLOW_ELEMENT__NAME:
			setName((String) newValue);
			return;
		case PageflowPackage.PAGEFLOW_ELEMENT__COMMENT:
			setComment((String) newValue);
			return;
		case PageflowPackage.PAGEFLOW_ELEMENT__X:
			setX(((Integer) newValue).intValue());
			return;
		case PageflowPackage.PAGEFLOW_ELEMENT__Y:
			setY(((Integer) newValue).intValue());
			return;
		case PageflowPackage.PAGEFLOW_ELEMENT__WIDTH:
			setWidth(((Integer) newValue).intValue());
			return;
		case PageflowPackage.PAGEFLOW_ELEMENT__HEIGHT:
			setHeight(((Integer) newValue).intValue());
			return;
		case PageflowPackage.PAGEFLOW_ELEMENT__ID:
			setId((String) newValue);
			return;
		case PageflowPackage.PAGEFLOW_ELEMENT__REFERENCE_LINK:
			setReferenceLink((String) newValue);
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
		case PageflowPackage.PAGEFLOW_ELEMENT__NAME:
			setName(NAME_EDEFAULT);
			return;
		case PageflowPackage.PAGEFLOW_ELEMENT__COMMENT:
			setComment(COMMENT_EDEFAULT);
			return;
		case PageflowPackage.PAGEFLOW_ELEMENT__X:
			setX(X_EDEFAULT);
			return;
		case PageflowPackage.PAGEFLOW_ELEMENT__Y:
			setY(Y_EDEFAULT);
			return;
		case PageflowPackage.PAGEFLOW_ELEMENT__WIDTH:
			setWidth(WIDTH_EDEFAULT);
			return;
		case PageflowPackage.PAGEFLOW_ELEMENT__HEIGHT:
			setHeight(HEIGHT_EDEFAULT);
			return;
		case PageflowPackage.PAGEFLOW_ELEMENT__ID:
			setId(ID_EDEFAULT);
			return;
		case PageflowPackage.PAGEFLOW_ELEMENT__REFERENCE_LINK:
			setReferenceLink(REFERENCE_LINK_EDEFAULT);
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
		case PageflowPackage.PAGEFLOW_ELEMENT__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT
					.equals(name);
		case PageflowPackage.PAGEFLOW_ELEMENT__COMMENT:
			return COMMENT_EDEFAULT == null ? comment != null
					: !COMMENT_EDEFAULT.equals(comment);
		case PageflowPackage.PAGEFLOW_ELEMENT__X:
			return x != X_EDEFAULT;
		case PageflowPackage.PAGEFLOW_ELEMENT__Y:
			return y != Y_EDEFAULT;
		case PageflowPackage.PAGEFLOW_ELEMENT__WIDTH:
			return width != WIDTH_EDEFAULT;
		case PageflowPackage.PAGEFLOW_ELEMENT__HEIGHT:
			return height != HEIGHT_EDEFAULT;
		case PageflowPackage.PAGEFLOW_ELEMENT__ID:
			return ID_EDEFAULT == null ? getId() != null : !ID_EDEFAULT
					.equals(getId());
		case PageflowPackage.PAGEFLOW_ELEMENT__REFERENCE_LINK:
			return REFERENCE_LINK_EDEFAULT == null ? referenceLink != null
					: !REFERENCE_LINK_EDEFAULT.equals(referenceLink);
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
		result.append(" (name: ");
		result.append(name);
		result.append(", comment: ");
		result.append(comment);
		result.append(", x: ");
		result.append(x);
		result.append(", y: ");
		result.append(y);
		result.append(", width: ");
		result.append(width);
		result.append(", height: ");
		result.append(height);
		result.append(", referenceLink: ");
		result.append(referenceLink);
		result.append(')');
		return result.toString();
	}

	private List fcElements = new ArrayList();

	public List getFCElements() {
		// TODO Auto-generated method stub
		return fcElements;
	}
}
// PageflowElementImpl
