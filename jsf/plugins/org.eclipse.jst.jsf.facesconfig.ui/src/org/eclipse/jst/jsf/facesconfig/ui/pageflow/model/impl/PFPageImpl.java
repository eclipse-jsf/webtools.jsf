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
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PFPage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage;
import org.eclipse.wtp.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.wtp.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.wtp.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.wtp.jsf.facesconfig.emf.FromViewIdType;
import org.eclipse.wtp.jsf.facesconfig.emf.IconType;
import org.eclipse.wtp.jsf.facesconfig.emf.LargeIconType;
import org.eclipse.wtp.jsf.facesconfig.emf.NavigationRuleType;
import org.eclipse.wtp.jsf.facesconfig.emf.SmallIconType;
import org.eclipse.wtp.jsf.facesconfig.emf.ToViewIdType;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>PF Page</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PFPageImpl#getPath <em>Path</em>}</li>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PFPageImpl#getSmallicon <em>Smallicon</em>}</li>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PFPageImpl#getLargeicon <em>Largeicon</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class PFPageImpl extends PageflowNodeImpl implements PFPage {
	private String path;

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
	protected PFPageImpl() {
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

	public NavigationRuleType resolveRule(Object object) {
		if (object instanceof FromViewIdType) {
			return (NavigationRuleType) ((FromViewIdType) object).eContainer();
		}
		return null;
	}

	public void setInitPath(String path) {
		this.path = path;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getPath() {
		// String result = null;
		// if (getFCElements().size() > 0) {
		// for (int i = 0, n = getFCElements().size(); i < n; i++) {
		// if (getFCElements().get(i) instanceof FromViewIdType) {
		// result = ((FromViewIdType) getFCElements().get(i))
		// .getTextContent();
		// }
		// }
		// if (result == null) {
		// result = ((ToViewIdType) getFCElements().get(0))
		// .getTextContent();
		// }
		// }
		// return result;
		return path;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setPath(String newPath) {
		String oldPath = path;
		if (getFCElements().size() > 0) {
			for (int i = 0, n = getFCElements().size(); i < n; i++) {
				if (getFCElements().get(i) instanceof FromViewIdType) {
					((FromViewIdType) getFCElements().get(i))
							.setTextContent(newPath);
				} else {
					((ToViewIdType) getFCElements().get(i))
							.setTextContent(newPath);
				}
			}
		}
		this.path = newPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PageflowPackage.PF_PAGE__PATH, oldPath, newPath));
	}

	public void setComment(String newComment) {
		String oldComment = null;
		if (getFCElements().size() > 0) {
			for (int i = 0, n = getFCElements().size(); i < n; i++) {
				NavigationRuleType rule = null;
				if ((rule = resolveRule(getFCElements().get(i))) != null) {
					rule.getDescription().clear();
					DescriptionType description = FacesConfigFactory.eINSTANCE
							.createDescriptionType();
					description.setTextContent(newComment);
					rule.getDescription().add(description);
				}
			}
		}
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PageflowPackage.PAGEFLOW_ELEMENT__COMMENT, oldComment,
					comment));

	}

	public String getComment() {
		String result = null;
		if (getFCElements().size() > 0) {
			for (int i = 0, n = getFCElements().size(); i < n; i++) {
				NavigationRuleType rule = null;
				if ((rule = resolveRule(getFCElements().get(i))) != null) {
					List descriptions = rule.getDescription();
					if (descriptions.size() > 0) {
						result = ((DescriptionType) descriptions.get(0))
								.getTextContent();
					}
				}
			}
		}
		return result;
	}

	public String getName() {
		if (this.getOutlinks().size() == 0) {
			return getPath();
		} else {
			String result = null;
			if (getFCElements().size() > 0) {
				for (int i = 0, n = getFCElements().size(); i < n; i++) {
					NavigationRuleType rule = null;
					if ((rule = resolveRule(getFCElements().get(i))) != null) {
						List displaynames = rule.getDisplayName();
						if (displaynames.size() > 0) {
							result = ((DisplayNameType) displaynames.get(0))
									.getTextContent();
							break;
						}
					}
				}
			}
			return result;
		}
	}

	public void setName(String newName) {
		String oldComment = null;
		if (getOutlinks().size() == 0) {
			super.setName(newName);
		} else {
			if (getFCElements().size() > 0) {
				for (int i = 0, n = getFCElements().size(); i < n; i++) {
					NavigationRuleType rule = null;
					if ((rule = resolveRule(getFCElements().get(i))) != null) {
						rule.getDisplayName().clear();
						DisplayNameType dsiplayname = FacesConfigFactory.eINSTANCE
								.createDisplayNameType();
						dsiplayname.setTextContent(newName);
						rule.getDisplayName().add(dsiplayname);
					}
				}
			}
			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.SET,
						PageflowPackage.PAGEFLOW_ELEMENT__COMMENT, oldComment,
						comment));

		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getSmallicon() {
		SmallIconType result = null;
		if (getFCElements().size() > 0) {
			for (int i = 0, n = getFCElements().size(); i < n; i++) {
				NavigationRuleType rule = null;
				if ((rule = resolveRule(getFCElements().get(i))) != null) {
					List icons = rule.getIcon();
					if (icons.size() > 0) {
						result = ((IconType) icons.get(0)).getSmallIcon();
					}
				}
			}
		}
		return result == null ? null : result.getTextContent();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setSmallicon(String newValue) {
		SmallIconType oldSmallicon = null;
		SmallIconType newSmallIconType = null;
		IconType icon = null;
		if (getFCElements().size() > 0) {
			for (int i = 0, n = getFCElements().size(); i < n; i++) {
				NavigationRuleType rule = null;
				if ((rule = resolveRule(getFCElements().get(i))) != null) {
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
					PageflowPackage.PF_PAGE__SMALLICON, oldSmallicon, newValue));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getLargeicon() {
		LargeIconType result = null;
		if (getFCElements().size() > 0) {
			for (int i = 0, n = getFCElements().size(); i < n; i++) {
				NavigationRuleType rule = null;
				if ((rule = resolveRule(getFCElements().get(i))) != null) {
					List icons = rule.getIcon();
					if (icons.size() > 0) {
						result = ((IconType) icons.get(0)).getLargeIcon();
					}
				}
			}
		}
		return result == null ? null : result.getTextContent();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setLargeicon(String newValue) {
		// String oldLargeicon = null;
		// IconType icon = null;
		LargeIconType oldLargeIcon = null;
		LargeIconType newLargeIconType = null;
		IconType icon = null;
		if (getFCElements().size() > 0) {
			for (int i = 0, n = getFCElements().size(); i < n; i++) {
				NavigationRuleType rule = null;
				if ((rule = resolveRule(getFCElements().get(i))) != null) {
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
