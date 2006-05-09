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
import java.util.List;
import java.util.Vector;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FromActionType;
import org.eclipse.jst.jsf.facesconfig.emf.FromOutcomeType;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.LargeIconType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType;
import org.eclipse.jst.jsf.facesconfig.emf.RedirectType;
import org.eclipse.jst.jsf.facesconfig.emf.SmallIconType;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLinkBendpoint;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>PF Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowLinkImpl#getPageflow <em>Pageflow</em>}</li>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowLinkImpl#getTarget <em>Target</em>}</li>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowLinkImpl#getSource <em>Source</em>}</li>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowLinkImpl#getOutcome <em>Outcome</em>}</li>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowLinkImpl#isRedirect <em>Redirect</em>}</li>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowLinkImpl#getBendPoints <em>Bend Points</em>}</li>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowLinkImpl#getSmallicon <em>Smallicon</em>}</li>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowLinkImpl#getLargeicon <em>Largeicon</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 * 
 */
public class PageflowLinkImpl extends PageflowElementImpl implements
		PageflowLink {
	/**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}'
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected PageflowNode target = null;

	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}'
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected PageflowNode source = null;

	/**
	 * The default value of the '{@link #getOutcome() <em>Outcome</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getOutcome()
	 * @generated
	 * @ordered
	 */
	protected static final String OUTCOME_EDEFAULT = null;

	/**
	 * The default value of the '{@link #isRedirect() <em>Redirect</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isRedirect()
	 * @generated
	 * @ordered
	 */
	protected static final boolean REDIRECT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #getBendPoints() <em>Bend Points</em>}'
	 * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getBendPoints()
	 * @generated
	 * @ordered
	 */
	protected EList bendPoints = null;

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
	 * The default value of the '{@link #getFromaction() <em>Fromaction</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getFromaction()
	 * @generated
	 * @ordered
	 */
	protected static final String FROMACTION_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected PageflowLinkImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EClass eStaticClass() {
		return PageflowPackage.eINSTANCE.getPFLink();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Pageflow getPageflow() {
		if (eContainerFeatureID != PageflowPackage.PF_LINK__PAGEFLOW)
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
				|| (eContainerFeatureID != PageflowPackage.PF_LINK__PAGEFLOW && newPageflow != null)) {
			if (EcoreUtil.isAncestor(this, newPageflow))
				throw new IllegalArgumentException(
						"Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newPageflow != null)
				msgs = ((InternalEObject) newPageflow).eInverseAdd(this,
						PageflowPackage.PAGEFLOW__LINKS, Pageflow.class, msgs);
			msgs = eBasicSetContainer((InternalEObject) newPageflow,
					PageflowPackage.PF_LINK__PAGEFLOW, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PageflowPackage.PF_LINK__PAGEFLOW, newPageflow, newPageflow));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public PageflowNode getTarget() {
		if (target != null && target.eIsProxy()) {
			PageflowNode oldTarget = target;
			target = (PageflowNode) eResolveProxy((InternalEObject) target);
			if (target != oldTarget) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							PageflowPackage.PF_LINK__TARGET, oldTarget, target));
			}
		}
		return target;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public PageflowNode basicGetTarget() {
		return target;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetTarget(PageflowNode newTarget,
			NotificationChain msgs) {
		PageflowNode oldTarget = target;
		target = newTarget;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET, PageflowPackage.PF_LINK__TARGET,
					oldTarget, newTarget);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setTarget(PageflowNode newTarget) {
		if (newTarget != target) {
			NotificationChain msgs = null;
			if (target != null)
				msgs = ((InternalEObject) target).eInverseRemove(this,
						PageflowPackage.PAGEFLOW_NODE__INLINKS,
						PageflowNode.class, msgs);
			if (newTarget != null)
				msgs = ((InternalEObject) newTarget).eInverseAdd(this,
						PageflowPackage.PAGEFLOW_NODE__INLINKS,
						PageflowNode.class, msgs);
			msgs = basicSetTarget(newTarget, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PageflowPackage.PF_LINK__TARGET, newTarget, newTarget));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public PageflowNode getSource() {
		if (source != null && source.eIsProxy()) {
			PageflowNode oldSource = source;
			source = (PageflowNode) eResolveProxy((InternalEObject) source);
			if (source != oldSource) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							PageflowPackage.PF_LINK__SOURCE, oldSource, source));
			}
		}
		return source;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public PageflowNode basicGetSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetSource(PageflowNode newSource,
			NotificationChain msgs) {
		// Assert.isTrue(newSource != null);
		PageflowNode oldSource = source;
		source = newSource;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET, PageflowPackage.PF_LINK__SOURCE,
					oldSource, newSource);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setSource(PageflowNode newSource) {
		// Assert.isTrue(newSource != null);
		if (newSource != source) {
			NotificationChain msgs = null;
			if (source != null)
				msgs = ((InternalEObject) source).eInverseRemove(this,
						PageflowPackage.PAGEFLOW_NODE__OUTLINKS,
						PageflowNode.class, msgs);
			if (newSource != null)
				msgs = ((InternalEObject) newSource).eInverseAdd(this,
						PageflowPackage.PAGEFLOW_NODE__OUTLINKS,
						PageflowNode.class, msgs);
			msgs = basicSetSource(newSource, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PageflowPackage.PF_LINK__SOURCE, newSource, newSource));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList getBendPoints() {
		if (bendPoints == null) {
			bendPoints = new EObjectContainmentWithInverseEList(
					PageflowLinkBendpoint.class, this,
					PageflowPackage.PF_LINK__BEND_POINTS,
					PageflowPackage.PF_LINK_BENDPOINT__LINK);
		}
		return bendPoints;
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
			case PageflowPackage.PF_LINK__PAGEFLOW:
				if (eContainer != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return eBasicSetContainer(otherEnd,
						PageflowPackage.PF_LINK__PAGEFLOW, msgs);
			case PageflowPackage.PF_LINK__TARGET:
				if (target != null)
					msgs = ((InternalEObject) target).eInverseRemove(this,
							PageflowPackage.PAGEFLOW_NODE__INLINKS,
							PageflowNode.class, msgs);
				return basicSetTarget((PageflowNode) otherEnd, msgs);
			case PageflowPackage.PF_LINK__SOURCE:
				if (source != null)
					msgs = ((InternalEObject) source).eInverseRemove(this,
							PageflowPackage.PAGEFLOW_NODE__OUTLINKS,
							PageflowNode.class, msgs);
				return basicSetSource((PageflowNode) otherEnd, msgs);
			case PageflowPackage.PF_LINK__BEND_POINTS:
				return ((InternalEList) getBendPoints()).basicAdd(otherEnd,
						msgs);
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
			case PageflowPackage.PF_LINK__PAGEFLOW:
				return eBasicSetContainer(null,
						PageflowPackage.PF_LINK__PAGEFLOW, msgs);
			case PageflowPackage.PF_LINK__TARGET:
				return basicSetTarget(null, msgs);
			case PageflowPackage.PF_LINK__SOURCE:
				return basicSetSource(null, msgs);
			case PageflowPackage.PF_LINK__BEND_POINTS:
				return ((InternalEList) getBendPoints()).basicRemove(otherEnd,
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
			case PageflowPackage.PF_LINK__PAGEFLOW:
				return ((InternalEObject) eContainer).eInverseRemove(this,
						PageflowPackage.PAGEFLOW__LINKS, Pageflow.class, msgs);
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
		case PageflowPackage.PF_LINK__NAME:
			return getName();
		case PageflowPackage.PF_LINK__COMMENT:
			return getComment();
		case PageflowPackage.PF_LINK__X:
			return new Integer(getX());
		case PageflowPackage.PF_LINK__Y:
			return new Integer(getY());
		case PageflowPackage.PF_LINK__WIDTH:
			return new Integer(getWidth());
		case PageflowPackage.PF_LINK__HEIGHT:
			return new Integer(getHeight());
		case PageflowPackage.PF_LINK__ID:
			return getId();
		case PageflowPackage.PF_LINK__PAGEFLOW:
			return getPageflow();
		case PageflowPackage.PF_LINK__TARGET:
			if (resolve)
				return getTarget();
			return basicGetTarget();
		case PageflowPackage.PF_LINK__SOURCE:
			if (resolve)
				return getSource();
			return basicGetSource();
		case PageflowPackage.PF_LINK__OUTCOME:
			return getOutcome();
		case PageflowPackage.PF_LINK__REDIRECT:
			return isRedirect() ? Boolean.TRUE : Boolean.FALSE;
		case PageflowPackage.PF_LINK__BEND_POINTS:
			return getBendPoints();
		case PageflowPackage.PF_LINK__SMALLICON:
			return getSmallicon();
		case PageflowPackage.PF_LINK__LARGEICON:
			return getLargeicon();
		case PageflowPackage.PF_LINK__FROMACTION:
			return getFromaction();
		}
		return super.eGet(eFeature, resolve);// eDynamicGet(eFeature,
		// resolve);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
		case PageflowPackage.PF_LINK__NAME:
			setName((String) newValue);
			return;
		case PageflowPackage.PF_LINK__COMMENT:
			setComment((String) newValue);
			return;
		case PageflowPackage.PF_LINK__X:
			setX(((Integer) newValue).intValue());
			return;
		case PageflowPackage.PF_LINK__Y:
			setY(((Integer) newValue).intValue());
			return;
		case PageflowPackage.PF_LINK__WIDTH:
			setWidth(((Integer) newValue).intValue());
			return;
		case PageflowPackage.PF_LINK__HEIGHT:
			setHeight(((Integer) newValue).intValue());
			return;
		case PageflowPackage.PF_LINK__ID:
			setId((String) newValue);
			return;
		case PageflowPackage.PF_LINK__PAGEFLOW:
			setPageflow((Pageflow) newValue);
			return;
		case PageflowPackage.PF_LINK__TARGET:
			setTarget((PageflowNode) newValue);
			return;
		case PageflowPackage.PF_LINK__SOURCE:
			setSource((PageflowNode) newValue);
			return;
		case PageflowPackage.PF_LINK__OUTCOME:
			setOutcome((String) newValue);
			return;
		case PageflowPackage.PF_LINK__REDIRECT:
			setRedirect(((Boolean) newValue).booleanValue());
			return;
		case PageflowPackage.PF_LINK__BEND_POINTS:
			getBendPoints().clear();
			getBendPoints().addAll((Collection) newValue);
			return;
		case PageflowPackage.PF_LINK__SMALLICON:
			setSmallicon((String) newValue);
			return;
		case PageflowPackage.PF_LINK__LARGEICON:
			setLargeicon((String) newValue);
			return;
		case PageflowPackage.PF_LINK__FROMACTION:
			setFromaction((String) newValue);
			return;
		}
		super.eSet(eFeature, newValue);// eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
		case PageflowPackage.PF_LINK__NAME:
			setName(NAME_EDEFAULT);
			return;
		case PageflowPackage.PF_LINK__COMMENT:
			setComment(COMMENT_EDEFAULT);
			return;
		case PageflowPackage.PF_LINK__X:
			setX(X_EDEFAULT);
			return;
		case PageflowPackage.PF_LINK__Y:
			setY(Y_EDEFAULT);
			return;
		case PageflowPackage.PF_LINK__WIDTH:
			setWidth(WIDTH_EDEFAULT);
			return;
		case PageflowPackage.PF_LINK__HEIGHT:
			setHeight(HEIGHT_EDEFAULT);
			return;
		case PageflowPackage.PF_LINK__ID:
			setId(ID_EDEFAULT);
			return;
		case PageflowPackage.PF_LINK__PAGEFLOW:
			setPageflow((Pageflow) null);
			return;
		case PageflowPackage.PF_LINK__TARGET:
			setTarget((PageflowNode) null);
			return;
		case PageflowPackage.PF_LINK__SOURCE:
			setSource((PageflowNode) null);
			return;
		case PageflowPackage.PF_LINK__OUTCOME:
			setOutcome(OUTCOME_EDEFAULT);
			return;
		case PageflowPackage.PF_LINK__REDIRECT:
			setRedirect(REDIRECT_EDEFAULT);
			return;
		case PageflowPackage.PF_LINK__BEND_POINTS:
			getBendPoints().clear();
			return;
		case PageflowPackage.PF_LINK__SMALLICON:
			setSmallicon(SMALLICON_EDEFAULT);
			return;
		case PageflowPackage.PF_LINK__LARGEICON:
			setLargeicon(LARGEICON_EDEFAULT);
			return;
		case PageflowPackage.PF_LINK__FROMACTION:
			setFromaction(FROMACTION_EDEFAULT);
			return;
		}
		super.eUnset(eFeature);// eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
		case PageflowPackage.PF_LINK__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT
					.equals(name);
		case PageflowPackage.PF_LINK__COMMENT:
			return COMMENT_EDEFAULT == null ? comment != null
					: !COMMENT_EDEFAULT.equals(comment);
		case PageflowPackage.PF_LINK__X:
			return x != X_EDEFAULT;
		case PageflowPackage.PF_LINK__Y:
			return y != Y_EDEFAULT;
		case PageflowPackage.PF_LINK__WIDTH:
			return width != WIDTH_EDEFAULT;
		case PageflowPackage.PF_LINK__HEIGHT:
			return height != HEIGHT_EDEFAULT;
		case PageflowPackage.PF_LINK__ID:
			return ID_EDEFAULT == null ? getId() != null : !ID_EDEFAULT
					.equals(getId());
		case PageflowPackage.PF_LINK__PAGEFLOW:
			return getPageflow() != null;
		case PageflowPackage.PF_LINK__TARGET:
			return target != null;
		case PageflowPackage.PF_LINK__SOURCE:
			return source != null;
		case PageflowPackage.PF_LINK__OUTCOME:
			return OUTCOME_EDEFAULT == null ? getOutcome() != null
					: !OUTCOME_EDEFAULT.equals(getOutcome());
		case PageflowPackage.PF_LINK__REDIRECT:
			return isRedirect() != REDIRECT_EDEFAULT;
		case PageflowPackage.PF_LINK__BEND_POINTS:
			return bendPoints != null && !bendPoints.isEmpty();
		case PageflowPackage.PF_LINK__SMALLICON:
			return SMALLICON_EDEFAULT == null ? getSmallicon() != null
					: !SMALLICON_EDEFAULT.equals(getSmallicon());
		case PageflowPackage.PF_LINK__LARGEICON:
			return LARGEICON_EDEFAULT == null ? getLargeicon() != null
					: !LARGEICON_EDEFAULT.equals(getLargeicon());
		case PageflowPackage.PF_LINK__FROMACTION:
			return FROMACTION_EDEFAULT == null ? getFromaction() != null
					: !FROMACTION_EDEFAULT.equals(getFromaction());
		}
		return super.eIsSet(eFeature);// eDynamicIsSet(eFeature);
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
		result.append(" (smallicon: ");
		result.append(getSmallicon());
		result.append(", largeicon: ");
		result.append(getLargeicon());
		result.append(", fromaction: ");
		result.append(getFromaction());
		result.append(", outcome: ");
		result.append(getOutcome());
		result.append(", redirect: ");
		result.append(isRedirect());
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc --> insert a new bendpoint to the link <!--
	 * end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void insertBendpoint(int index, PageflowLinkBendpoint point) {
		getBendPoints().add(index, point);

		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.ADD,
					PageflowPackage.PF_LINK__BEND_POINTS, null, point));
	}

	/**
	 * <!-- begin-user-doc --> remove the current bendpoint <!-- end-user-doc
	 * -->
	 * 
	 * @generated NOT
	 */
	public void removeBendpoint(int index) {
		getBendPoints().remove(index);
	}

	/**
	 * <!-- begin-user-doc --> Set the current bendpoint <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setBendpoint(int index, PageflowLinkBendpoint point) {
		getBendPoints().set(index, point);
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PageflowPackage.PF_LINK__BEND_POINTS, null, point));

	}

	/**
	 * <!-- begin-user-doc --> Set a group of bendpoints to reconstruct the link
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setBendpoints(Vector points) {
		bendPoints = (EList) points;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.ADD_MANY,
					PageflowPackage.PF_LINK__BEND_POINTS, null, points));

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isRedirect() {
		boolean result = false;
		if (!getFCElements().isEmpty()) {
			RedirectType outcome = ((NavigationCaseType) getFCElements()
					.getData().get(0)).getRedirect();
			if (outcome != null) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setRedirect(boolean newRedirect) {
		RedirectType redirect = null;
		RedirectType oldRedirect = null;
		if (!getFCElements().isEmpty()) {
			oldRedirect = ((NavigationCaseType) getFCElements().getData()
					.get(0)).getRedirect();
			if (!newRedirect) {
				redirect = null;
			} else {
				if (oldRedirect == null) {
					redirect = FacesConfigFactory.eINSTANCE
							.createRedirectType();
				}
			}
			((NavigationCaseType) getFCElements().getData().get(0))
					.setRedirect(redirect);
		}
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PageflowPackage.PF_LINK__REDIRECT, oldRedirect, redirect));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getOutcome() {
		String result = null;
		if (!getFCElements().isEmpty()) {
			FromOutcomeType outcome = ((NavigationCaseType) getFCElements()
					.getData().get(0)).getFromOutcome();
			if (outcome != null) {
				result = outcome.getTextContent();
			}
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setOutcome(String newValue) {
		FromOutcomeType oldOutcome = null;
		FromOutcomeType newOutcome = null;
		if (!getFCElements().isEmpty()) {
			oldOutcome = ((NavigationCaseType) getFCElements().getData().get(0))
					.getFromOutcome();
			if (newValue == null || newValue.length() == 0) {
				((NavigationCaseType) getFCElements().getData().get(0))
						.setFromOutcome(null);
			} else {
				if (oldOutcome == null) {
					newOutcome = FacesConfigFactory.eINSTANCE
							.createFromOutcomeType();
					newOutcome.setTextContent(newValue);
					((NavigationCaseType) getFCElements().getData().get(0))
							.setFromOutcome(newOutcome);
				} else {
					oldOutcome.setTextContent(newValue);
				}
			}
		}
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PageflowPackage.PF_LINK__OUTCOME, oldOutcome, newOutcome));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getSmallicon() {
		SmallIconType result = null;
		if (!getFCElements().isEmpty()) {
			List icons = ((NavigationCaseType) getFCElements().getData().get(0))
					.getIcon();
			if (icons.size() > 0) {
				result = ((IconType) icons.get(0)).getSmallIcon();
			}
		}
		return result != null ? result.getTextContent() : null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setSmallicon(String newValue) {
		SmallIconType oldSmallicon = null;
		SmallIconType newSmallIconType = null;
		IconType icon = null;
		if (!getFCElements().isEmpty()) {
			List icons = ((NavigationCaseType) getFCElements().getData().get(0))
					.getIcon();
			if (newValue == null || newValue.length() == 0) {
				if (icons.size() > 0) {
					if (((IconType) icons.get(0)).getSmallIcon() != null) {
						((IconType) icons.get(0)).setSmallIcon(null);
					}
				}
			} else {
				if (icons.size() == 0) {
					icon = FacesConfigFactory.eINSTANCE.createIconType();
					newSmallIconType = FacesConfigFactory.eINSTANCE
							.createSmallIconType();
					newSmallIconType.setTextContent(newValue);
					icon.setSmallIcon(newSmallIconType);
					icons.add(icon);
				} else if (((IconType) icons.get(0)).getSmallIcon() == null) {
					newSmallIconType = FacesConfigFactory.eINSTANCE
							.createSmallIconType();
					newSmallIconType.setTextContent(newValue);
					icon = ((IconType) icons.get(0));
					icon.setSmallIcon(newSmallIconType);
				} else {
					oldSmallicon = ((IconType) icons.get(0)).getSmallIcon();
					oldSmallicon.setTextContent(newValue);
				}
			}
		}
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PageflowPackage.PF_LINK__SMALLICON, oldSmallicon,
					newSmallIconType));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getLargeicon() {
		LargeIconType result = null;
		if (!getFCElements().isEmpty()) {
			List icons = ((NavigationCaseType) getFCElements().getData().get(0))
					.getIcon();
			if (icons.size() > 0) {
				result = ((IconType) icons.get(0)).getLargeIcon();
			}
		}
		return result != null ? result.getTextContent() : null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setLargeicon(String newValue) {
		LargeIconType oldLargeIcon = null;
		LargeIconType newLargeIconType = null;
		IconType icon = null;
		if (!getFCElements().isEmpty()) {
			List icons = ((NavigationCaseType) getFCElements().getData().get(0))
					.getIcon();
			if (newValue == null || newValue.length() == 0) {
				if (icons.size() > 0) {
					if (((IconType) icons.get(0)).getLargeIcon() != null) {
						((IconType) icons.get(0)).setLargeIcon(null);
					}
				}
			} else {
				if (icons.size() == 0) {
					icon = FacesConfigFactory.eINSTANCE.createIconType();
					newLargeIconType = FacesConfigFactory.eINSTANCE
							.createLargeIconType();
					newLargeIconType.setTextContent(newValue);
					icon.setLargeIcon(newLargeIconType);
					icons.add(icon);
				} else if (((IconType) icons.get(0)).getLargeIcon() == null) {
					newLargeIconType = FacesConfigFactory.eINSTANCE
							.createLargeIconType();
					newLargeIconType.setTextContent(newValue);
					icon = ((IconType) icons.get(0));
					icon.setLargeIcon(newLargeIconType);
				} else {
					oldLargeIcon = ((IconType) icons.get(0)).getLargeIcon();
					oldLargeIcon.setTextContent(newValue);
				}
			}
		}
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PageflowPackage.PF_LINK__LARGEICON, oldLargeIcon,
					newLargeIconType));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getFromaction() {
		String result = null;
		if (!getFCElements().isEmpty()) {
			FromActionType fromActionType = ((NavigationCaseType) getFCElements()
					.getData().get(0)).getFromAction();
			if (fromActionType != null) {
				result = fromActionType.getTextContent();
			}
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setFromaction(String newFromaction) {
		FromActionType oldFromActionType = null;
		FromActionType newFromActionType = null;
		if (!getFCElements().isEmpty()) {
			oldFromActionType = ((NavigationCaseType) getFCElements().getData()
					.get(0)).getFromAction();
			if (newFromaction == null || newFromaction.length() == 0) {
				((NavigationCaseType) getFCElements().getData().get(0))
						.setFromAction(null);
			} else {
				if (oldFromActionType == null) {
					newFromActionType = FacesConfigFactory.eINSTANCE
							.createFromActionType();
					newFromActionType.setTextContent(newFromaction);
					((NavigationCaseType) getFCElements().getData().get(0))
							.setFromAction(newFromActionType);
				} else {
					oldFromActionType.setTextContent(newFromaction);
				}
			}
		}
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PageflowPackage.PF_LINK__FROMACTION, oldFromActionType,
					newFromActionType));
	}

}
// PFLinkImpl
