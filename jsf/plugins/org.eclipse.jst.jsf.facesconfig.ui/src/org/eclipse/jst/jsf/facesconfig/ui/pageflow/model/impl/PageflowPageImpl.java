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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FromViewIdType;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.LargeIconType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType;
import org.eclipse.jst.jsf.facesconfig.emf.SmallIconType;
import org.eclipse.jst.jsf.facesconfig.emf.ToViewIdType;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.synchronization.FC2PFTransformer;

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

	public NavigationRuleType resolveRuleFromFCElement(Object object) {
		if (object instanceof FromViewIdType) {
			return (NavigationRuleType) ((FromViewIdType) object).eContainer();
		}
		return null;
	}

	public NavigationRuleType resolveRuleFromPFElement(Object object) {
		if (object instanceof PageflowLink) {
			NavigationCaseType caseType = (NavigationCaseType) ((PageflowLink) object)
					.getFCElements().getData().get(0);
			return (NavigationRuleType) caseType.eContainer();
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getPath() {
		String result = null;
		if (!getFCElements().isEmpty()) {
			for (int i = 0, n = getFCElements().getData().size(); i < n; i++) {
				if (getFCElements().getData().get(i) instanceof FromViewIdType) {
					result = ((FromViewIdType) getFCElements().getData().get(i))
							.getTextContent();
					break;
				}
			}
			if (result == null
					&& getFCElements().getData().get(0) instanceof ToViewIdType) {
				result = ((ToViewIdType) getFCElements().getData().get(0))
						.getTextContent();
			}
		}
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
		Object oldPath = null;
		Object newPath = null;
		path = newValue;
		if (!getFCElements().isEmpty()) {
			for (int i = 0, n = getFCElements().getData().size(); i < n; i++) {
				if (getFCElements().getData().get(i) instanceof FromViewIdType) {
					oldPath = getFCElements().getData().get(i);
					((FromViewIdType) oldPath).setTextContent(newValue);
				} else if (getFCElements().getData().get(i) instanceof FromViewIdType) {
					oldPath = getFCElements().getData().get(i);
					((ToViewIdType) oldPath).setTextContent(newValue);
				}
			}
		}
		// Create new fromViewID or toViewID node as needed.
		else if (newValue != null && !"*".equals(newValue)) {
			if (getOutlinks().size() > 0) {
				List links = getOutlinks();
				for (int i = 0, n = links.size(); i < n; i++) {
					PageflowLink link = (PageflowLink) links.get(i);
					NavigationRuleType rule = resolveRuleFromPFElement(link);
					newPath = FC2PFTransformer.getInstance()
							.createRLFromViewID(newValue);
					rule.setFromViewId((FromViewIdType) newPath);
					getFCElements().add((EObject) newPath);
				}
				links = getInlinks();
				for (int i = 0, n = links.size(); i < n; i++) {
					PageflowLink link = (PageflowLink) links.get(i);
					NavigationCaseType caseType = (NavigationCaseType) link
							.getFCElements().getData().get(0);
					newPath = FC2PFTransformer.getInstance().createFCToViewID(
							newValue);
					caseType.setToViewId((ToViewIdType) newPath);
					getFCElements().add((EObject) newPath);
				}
			}
		}

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
		DescriptionType oldComment = null;
		DescriptionType description = null;
		if (!getFCElements().isEmpty()) {
			for (int i = 0, n = getFCElements().getData().size(); i < n; i++) {
				NavigationRuleType rule = null;
				if ((rule = resolveRuleFromFCElement(getFCElements().getData()
						.get(i))) != null) {
					if (rule.getDescription().size() > 0) {
						oldComment = (DescriptionType) rule.getDescription()
								.get(0);
					}
					rule.getDescription().clear();
					description = FacesConfigFactory.eINSTANCE
							.createDescriptionType();
					description.setTextContent(newValue);
					rule.getDescription().add(description);
				}
			}
		}
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PageflowPackage.PAGEFLOW_ELEMENT__COMMENT, oldComment,
					description));

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */

	public String getComment() {
		DescriptionType result = null;
		if (!getFCElements().isEmpty()) {
			for (int i = 0, n = getFCElements().getData().size(); i < n; i++) {
				NavigationRuleType rule = null;
				if ((rule = resolveRuleFromFCElement(getFCElements().getData()
						.get(i))) != null) {
					List descriptions = rule.getDescription();
					if (descriptions.size() > 0) {
						result = (DescriptionType) descriptions.get(0);
						break;
					}
				}
			}
		}
		return result == null && getFCElements().isEmpty() ? comment
				: (result != null ? result.getTextContent()
						: PageflowElementImpl.COMMENT_EDEFAULT);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */

	public String getName() {
		String result = null;
		// To see if it is in navigation rule.
		if (!getFCElements().isEmpty() && getOutlinks().size() > 0) {
			for (int i = 0, n = getFCElements().getData().size(); i < n; i++) {
				NavigationRuleType rule = null;
				if ((rule = resolveRuleFromFCElement(getFCElements().getData()
						.get(i))) != null) {
					List displaynames = rule.getDisplayName();
					if (displaynames.size() > 0) {
						result = ((DisplayNameType) displaynames.get(0))
								.getTextContent();
						break;
					}
				}
			}
			if (result == null) {
				result = getPath();
			}
		} else {
			result = super.getName();
		}
		return result == null && getFCElements().isEmpty() ? PageflowElementImpl.NAME_EDEFAULT
				: (result == null ? null : result);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */

	public void setName(String newName) {
		DescriptionType oldComment = null;
		DisplayNameType newDisplayNameType = null;
		super.setName(newName);
		if (!getFCElements().isEmpty()) {
			for (int i = 0, n = getFCElements().getData().size(); i < n; i++) {
				NavigationRuleType rule = null;
				if ((rule = resolveRuleFromFCElement(getFCElements().getData()
						.get(i))) != null) {
					oldComment = rule.getDescription().size() > 0 ? (DescriptionType) rule
							.getDescription().get(0)
							: null;
					rule.getDisplayName().clear();
					if (newName != null && newName.length() > 0) {
						newDisplayNameType = FacesConfigFactory.eINSTANCE
								.createDisplayNameType();
						newDisplayNameType.setTextContent(newName);
						rule.getDisplayName().add(newDisplayNameType);
					}
				}
			}
		}
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PageflowPackage.PAGEFLOW_ELEMENT__COMMENT, oldComment,
					newDisplayNameType));

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getSmallicon() {
		SmallIconType result = null;
		if (!getFCElements().isEmpty()) {
			for (int i = 0, n = getFCElements().getData().size(); i < n; i++) {
				NavigationRuleType rule = null;
				if ((rule = resolveRuleFromFCElement(getFCElements().getData()
						.get(i))) != null) {
					List icons = rule.getIcon();
					if (icons.size() > 0) {
						result = ((IconType) icons.get(0)).getSmallIcon();
						break;
					}
				}
			}
		}
		return result == null && getFCElements().isEmpty() ? smallIcon
				: (result != null ? result.getTextContent()
						: SMALLICON_EDEFAULT);
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
		smallIcon = newValue;
		if (!getFCElements().isEmpty()) {
			for (int i = 0, n = getFCElements().getData().size(); i < n; i++) {
				NavigationRuleType rule = null;
				if ((rule = resolveRuleFromFCElement(getFCElements().getData()
						.get(i))) != null) {
					List icons = rule.getIcon();
					if (newValue == null || newValue.length() == 0) {
						if (icons.size() > 0) {
							if (((IconType) icons.get(0)).getSmallIcon() != null) {
								((IconType) icons.get(0)).setSmallIcon(null);
							}
						}
					} else {
						if (icons.size() == 0) {
							icon = FacesConfigFactory.eINSTANCE
									.createIconType();
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
							oldSmallicon = ((IconType) icons.get(0))
									.getSmallIcon();
							oldSmallicon.setTextContent(newValue);
						}
					}
				}
			}
		}

		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PageflowPackage.PF_PAGE__SMALLICON, oldSmallicon,
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
			for (int i = 0, n = getFCElements().getData().size(); i < n; i++) {
				NavigationRuleType rule = null;
				if ((rule = resolveRuleFromFCElement(getFCElements().getData()
						.get(i))) != null) {
					List icons = rule.getIcon();
					if (icons.size() > 0) {
						result = ((IconType) icons.get(0)).getLargeIcon();
						break;
					}
				}
			}
		}
		return result == null && getFCElements().isEmpty() ? largeIcon
				: (result != null ? result.getTextContent()
						: LARGEICON_EDEFAULT);
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
		largeIcon = newValue;
		if (!getFCElements().isEmpty()) {
			for (int i = 0, n = getFCElements().getData().size(); i < n; i++) {
				NavigationRuleType rule = null;
				if ((rule = resolveRuleFromFCElement(getFCElements().getData()
						.get(i))) != null) {
					List icons = rule.getIcon();
					if (newValue == null || newValue.length() == 0) {
						if (icons.size() > 0) {
							if (((IconType) icons.get(0)).getLargeIcon() != null) {
								((IconType) icons.get(0)).setLargeIcon(null);
							}
						}
					} else {
						if (icons.size() == 0) {
							icon = FacesConfigFactory.eINSTANCE
									.createIconType();
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
							oldLargeIcon = ((IconType) icons.get(0))
									.getLargeIcon();
							oldLargeIcon.setTextContent(newValue);
						}
					}
				}
			}
		}

		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PageflowPackage.PF_PAGE__LARGEICON, oldLargeIcon,
					newLargeIconType));
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
		super.eSet(eFeature, newValue);// eDynamicSet(eFeature, newValue);
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
		super.eUnset(eFeature);// eDynamicUnset(eFeature);
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
		result.append(" (path: ");
		result.append(getPath());
		result.append(", smallicon: ");
		result.append(getSmallicon());
		result.append(", largeicon: ");
		result.append(getLargeicon());
		result.append(')');
		return result.toString();
	}

}
// PFPageImpl
