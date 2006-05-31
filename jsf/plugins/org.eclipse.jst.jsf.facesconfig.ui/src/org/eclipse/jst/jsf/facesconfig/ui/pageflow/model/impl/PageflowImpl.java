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
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.util.PageflowValidation;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Pageflow</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowImpl#getNodes <em>Nodes</em>}</li>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowImpl#getLinks <em>Links</em>}</li>
 * <li>{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowImpl#getConfigfile <em>Configfile</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class PageflowImpl extends PageflowElementImpl implements Pageflow {

	/**
	 * The cached value of the '{@link #getNodes() <em>Nodes</em>}'
	 * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getNodes()
	 * @generated
	 * @ordered
	 */
	protected EList nodes = null;

	/**
	 * The cached value of the '{@link #getLinks() <em>Links</em>}'
	 * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getLinks()
	 * @generated
	 * @ordered
	 */
	protected EList links = null;

	/**
	 * The default value of the '{@link #getConfigfile() <em>Configfile</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getConfigfile()
	 * @generated
	 * @ordered
	 */
	protected static final String CONFIGFILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConfigfile() <em>Configfile</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getConfigfile()
	 * @generated
	 * @ordered
	 */
	protected String configfile = CONFIGFILE_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected PageflowImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EClass eStaticClass() {
		return PageflowPackage.eINSTANCE.getPageflow();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList getNodes() {
		if (nodes == null) {
			nodes = new EObjectContainmentWithInverseEList(PageflowNode.class,
					this, PageflowPackage.PAGEFLOW__NODES,
					PageflowPackage.PAGEFLOW_NODE__PAGEFLOW);
		}
		return nodes;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList getLinks() {
		if (links == null) {
			links = new EObjectContainmentWithInverseEList(PageflowLink.class,
					this, PageflowPackage.PAGEFLOW__LINKS,
					PageflowPackage.PF_LINK__PAGEFLOW);
		}
		return links;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getConfigfile() {
		return configfile;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setConfigfile(String newConfigfile) {
		String oldConfigfile = configfile;
		configfile = newConfigfile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PageflowPackage.PAGEFLOW__CONFIGFILE, oldConfigfile,
					configfile));
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
			case PageflowPackage.PAGEFLOW__NODES:
				return ((InternalEList) getNodes()).basicAdd(otherEnd, msgs);
			case PageflowPackage.PAGEFLOW__LINKS:
				return ((InternalEList) getLinks()).basicAdd(otherEnd, msgs);
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
			case PageflowPackage.PAGEFLOW__NODES:
				return ((InternalEList) getNodes()).basicRemove(otherEnd, msgs);
			case PageflowPackage.PAGEFLOW__LINKS:
				return ((InternalEList) getLinks()).basicRemove(otherEnd, msgs);
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
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
		case PageflowPackage.PAGEFLOW__NAME:
			return getName();
		case PageflowPackage.PAGEFLOW__COMMENT:
			return getComment();
		case PageflowPackage.PAGEFLOW__X:
			return new Integer(getX());
		case PageflowPackage.PAGEFLOW__Y:
			return new Integer(getY());
		case PageflowPackage.PAGEFLOW__WIDTH:
			return new Integer(getWidth());
		case PageflowPackage.PAGEFLOW__HEIGHT:
			return new Integer(getHeight());
		case PageflowPackage.PAGEFLOW__ID:
			return getId();
		case PageflowPackage.PAGEFLOW__NODES:
			return getNodes();
		case PageflowPackage.PAGEFLOW__LINKS:
			return getLinks();
		case PageflowPackage.PAGEFLOW__CONFIGFILE:
			return getConfigfile();
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
		case PageflowPackage.PAGEFLOW__NAME:
			setName((String) newValue);
			return;
		case PageflowPackage.PAGEFLOW__COMMENT:
			setComment((String) newValue);
			return;
		case PageflowPackage.PAGEFLOW__X:
			setX(((Integer) newValue).intValue());
			return;
		case PageflowPackage.PAGEFLOW__Y:
			setY(((Integer) newValue).intValue());
			return;
		case PageflowPackage.PAGEFLOW__WIDTH:
			setWidth(((Integer) newValue).intValue());
			return;
		case PageflowPackage.PAGEFLOW__HEIGHT:
			setHeight(((Integer) newValue).intValue());
			return;
		case PageflowPackage.PAGEFLOW__ID:
			setId((String) newValue);
			return;
		case PageflowPackage.PAGEFLOW__NODES:
			getNodes().clear();
			getNodes().addAll((Collection) newValue);
			return;
		case PageflowPackage.PAGEFLOW__LINKS:
			getLinks().clear();
			getLinks().addAll((Collection) newValue);
			return;
		case PageflowPackage.PAGEFLOW__CONFIGFILE:
			setConfigfile((String) newValue);
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
		case PageflowPackage.PAGEFLOW__NAME:
			setName(NAME_EDEFAULT);
			return;
		case PageflowPackage.PAGEFLOW__COMMENT:
			setComment(COMMENT_EDEFAULT);
			return;
		case PageflowPackage.PAGEFLOW__X:
			setX(X_EDEFAULT);
			return;
		case PageflowPackage.PAGEFLOW__Y:
			setY(Y_EDEFAULT);
			return;
		case PageflowPackage.PAGEFLOW__WIDTH:
			setWidth(WIDTH_EDEFAULT);
			return;
		case PageflowPackage.PAGEFLOW__HEIGHT:
			setHeight(HEIGHT_EDEFAULT);
			return;
		case PageflowPackage.PAGEFLOW__ID:
			setId(ID_EDEFAULT);
			return;
		case PageflowPackage.PAGEFLOW__NODES:
			getNodes().clear();
			return;
		case PageflowPackage.PAGEFLOW__LINKS:
			getLinks().clear();
			return;
		case PageflowPackage.PAGEFLOW__CONFIGFILE:
			setConfigfile(CONFIGFILE_EDEFAULT);
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
		case PageflowPackage.PAGEFLOW__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT
					.equals(name);
		case PageflowPackage.PAGEFLOW__COMMENT:
			return COMMENT_EDEFAULT == null ? comment != null
					: !COMMENT_EDEFAULT.equals(comment);
		case PageflowPackage.PAGEFLOW__X:
			return x != X_EDEFAULT;
		case PageflowPackage.PAGEFLOW__Y:
			return y != Y_EDEFAULT;
		case PageflowPackage.PAGEFLOW__WIDTH:
			return width != WIDTH_EDEFAULT;
		case PageflowPackage.PAGEFLOW__HEIGHT:
			return height != HEIGHT_EDEFAULT;
		case PageflowPackage.PAGEFLOW__ID:
			return ID_EDEFAULT == null ? getId() != null : !ID_EDEFAULT
					.equals(getId());
		case PageflowPackage.PAGEFLOW__NODES:
			return nodes != null && !nodes.isEmpty();
		case PageflowPackage.PAGEFLOW__LINKS:
			return links != null && !links.isEmpty();
		case PageflowPackage.PAGEFLOW__CONFIGFILE:
			return CONFIGFILE_EDEFAULT == null ? configfile != null
					: !CONFIGFILE_EDEFAULT.equals(configfile);
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
		result.append(" (configfile: ");
		result.append(configfile);
		result.append(')');
		return result.toString();
	}

	/**
	 * set the valid link's source and target node.
	 * 
	 * @generated NOT
	 */
	public void connect(PageflowNode source, PageflowNode target,
			PageflowLink link) {
		if (link != null) {
			if (PageflowValidation.getInstance().isValidLinkForCreation(source,
					target)) {
				this.getLinks().add(link);
				link.setSource(source);
				link.setTarget(target);
			}
		}
	}

	/** node prefixs map */
	private HashMap mapNodePrefix = null;

	/**
	 * get the prefix's map, and if the map is not existed, create it.
	 * 
	 * @return - map for prefix
	 * 
	 * @generated NOT
	 */
	private HashMap getNodePrefixMap() {
		if (mapNodePrefix == null) {
			mapNodePrefix = new HashMap();
			mapNodePrefix.put(PageflowPageImpl.class, new String("Page"));
		}
		return mapNodePrefix;
	}

	/**
	 * get the default node name according current nodetype
	 * 
	 * @param nodeType -
	 *            node type, such as PFBeginImpl, PFPageImpl, etc.
	 * @return - the default node name.
	 * 
	 * @generated NOT
	 */
	public String getDefaultNodeName(Class nodeType) {
		HashMap mapNodePrefix = getNodePrefixMap();

		String strNodePrefix = (String) mapNodePrefix.get(nodeType);

		String strNodeName = null;
		boolean bFound = true;
		int numNode = 1;
		while (bFound) {
			strNodeName = strNodePrefix + numNode;
			boolean bExisted = false;
			for (Iterator iter = getNodes().iterator(); iter.hasNext();) {
				PageflowNode node = (PageflowNode) iter.next();
				if (strNodeName.equalsIgnoreCase(node.getName())) {
					bExisted = true;
					break;
				}
			}
			if (!bExisted) {
				bFound = false;
			}
			numNode++;
		}

		return strNodeName;

	}

	/**
	 * When faces-config has been changed, the pageflow model will be notified
	 * to enable refresher do some update in pageflow.
	 * 
	 * @generated NOT
	 */
	public void notifyModelChanged(Notification notification) {
		TreeIterator children = eAllContents();
		while (children.hasNext()) {
			Object next = children.next();
			if (next instanceof PageflowElement) {
				((PageflowElement) next).update();
			}
		}
		super.notifyModelChanged(notification);
	}

	/**
	 * @generated NOT
	 */
	public void dispose() {
		TreeIterator children = eAllContents();
		while (children.hasNext()) {
			Object next = children.next();
			if (next instanceof PageflowElement) {
				((PageflowElement) next).dispose();
			}
		}
		eAdapters.clear();
	}
}
// PageflowImpl
