/***************************************************************************************************
 * Copyright (c) 2005, 2010 IBM Corporation and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors: 
 *   IBM Corporation - initial API and implementation
 *   Oracle Corporation - revision
 **************************************************************************************************/
package org.eclipse.jst.jsf.facesconfig.emf.impl;

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
import org.eclipse.jst.jsf.facesconfig.emf.ClientBehaviorRendererType;
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitClassType;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitIdType;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitType;
import org.eclipse.jst.jsf.facesconfig.emf.RendererType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Render Kit Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RenderKitTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RenderKitTypeImpl#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RenderKitTypeImpl#getIcon <em>Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RenderKitTypeImpl#getRenderKitId <em>Render Kit Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RenderKitTypeImpl#getRenderKitClass <em>Render Kit Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RenderKitTypeImpl#getRenderer <em>Renderer</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RenderKitTypeImpl#getClientBehaviorRenderer <em>Client Behavior Renderer</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RenderKitTypeImpl#getRenderKitExtension <em>Render Kit Extension</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.RenderKitTypeImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RenderKitTypeImpl extends EObjectImpl implements RenderKitType {
    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others"; //$NON-NLS-1$

    /**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected EList description;

    /**
	 * The cached value of the '{@link #getDisplayName() <em>Display Name</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplayName()
	 * @generated
	 * @ordered
	 */
	protected EList displayName;

    /**
	 * The cached value of the '{@link #getIcon() <em>Icon</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIcon()
	 * @generated
	 * @ordered
	 */
	protected EList icon;

    /**
	 * The cached value of the '{@link #getRenderKitId() <em>Render Kit Id</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRenderKitId()
	 * @generated
	 * @ordered
	 */
	protected RenderKitIdType renderKitId;

    /**
	 * The cached value of the '{@link #getRenderKitClass() <em>Render Kit Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRenderKitClass()
	 * @generated
	 * @ordered
	 */
	protected RenderKitClassType renderKitClass;

    /**
	 * The cached value of the '{@link #getRenderer() <em>Renderer</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRenderer()
	 * @generated
	 * @ordered
	 */
	protected EList renderer;

    /**
	 * The cached value of the '{@link #getClientBehaviorRenderer() <em>Client Behavior Renderer</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClientBehaviorRenderer()
	 * @generated
	 * @ordered
	 */
	protected EList clientBehaviorRenderer;

				/**
	 * The cached value of the '{@link #getRenderKitExtension() <em>Render Kit Extension</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getRenderKitExtension()
	 * @generated
	 * @ordered
	 */
    protected EList renderKitExtension;

    /**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RenderKitTypeImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return FacesConfigPackage.Literals.RENDER_KIT_TYPE;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getDescription() {
		if (description == null) {
			description = new EObjectContainmentEList(DescriptionType.class, this, FacesConfigPackage.RENDER_KIT_TYPE__DESCRIPTION);
		}
		return description;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getDisplayName() {
		if (displayName == null) {
			displayName = new EObjectContainmentEList(DisplayNameType.class, this, FacesConfigPackage.RENDER_KIT_TYPE__DISPLAY_NAME);
		}
		return displayName;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getIcon() {
		if (icon == null) {
			icon = new EObjectContainmentEList(IconType.class, this, FacesConfigPackage.RENDER_KIT_TYPE__ICON);
		}
		return icon;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RenderKitIdType getRenderKitId() {
		return renderKitId;
	}

    /**
	 * <!-- begin-user-doc -->
     * @param newRenderKitId 
     * @param msgs 
     * @return the notification chain 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRenderKitId(RenderKitIdType newRenderKitId, NotificationChain msgs) {
		RenderKitIdType oldRenderKitId = renderKitId;
		renderKitId = newRenderKitId;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.RENDER_KIT_TYPE__RENDER_KIT_ID, oldRenderKitId, newRenderKitId);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRenderKitId(RenderKitIdType newRenderKitId) {
		if (newRenderKitId != renderKitId) {
			NotificationChain msgs = null;
			if (renderKitId != null)
				msgs = ((InternalEObject)renderKitId).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.RENDER_KIT_TYPE__RENDER_KIT_ID, null, msgs);
			if (newRenderKitId != null)
				msgs = ((InternalEObject)newRenderKitId).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.RENDER_KIT_TYPE__RENDER_KIT_ID, null, msgs);
			msgs = basicSetRenderKitId(newRenderKitId, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.RENDER_KIT_TYPE__RENDER_KIT_ID, newRenderKitId, newRenderKitId));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RenderKitClassType getRenderKitClass() {
		return renderKitClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * @param newRenderKitClass 
     * @param msgs 
     * @return the notification chain 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRenderKitClass(RenderKitClassType newRenderKitClass, NotificationChain msgs) {
		RenderKitClassType oldRenderKitClass = renderKitClass;
		renderKitClass = newRenderKitClass;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.RENDER_KIT_TYPE__RENDER_KIT_CLASS, oldRenderKitClass, newRenderKitClass);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRenderKitClass(RenderKitClassType newRenderKitClass) {
		if (newRenderKitClass != renderKitClass) {
			NotificationChain msgs = null;
			if (renderKitClass != null)
				msgs = ((InternalEObject)renderKitClass).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.RENDER_KIT_TYPE__RENDER_KIT_CLASS, null, msgs);
			if (newRenderKitClass != null)
				msgs = ((InternalEObject)newRenderKitClass).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.RENDER_KIT_TYPE__RENDER_KIT_CLASS, null, msgs);
			msgs = basicSetRenderKitClass(newRenderKitClass, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.RENDER_KIT_TYPE__RENDER_KIT_CLASS, newRenderKitClass, newRenderKitClass));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getRenderer() {
		if (renderer == null) {
			renderer = new EObjectContainmentEList(RendererType.class, this, FacesConfigPackage.RENDER_KIT_TYPE__RENDERER);
		}
		return renderer;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getClientBehaviorRenderer() {
		if (clientBehaviorRenderer == null) {
			clientBehaviorRenderer = new EObjectContainmentEList(ClientBehaviorRendererType.class, this, FacesConfigPackage.RENDER_KIT_TYPE__CLIENT_BEHAVIOR_RENDERER);
		}
		return clientBehaviorRenderer;
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getRenderKitExtension() {
		if (renderKitExtension == null) {
			renderKitExtension = new EObjectContainmentEList(RenderKitExtensionType.class, this, FacesConfigPackage.RENDER_KIT_TYPE__RENDER_KIT_EXTENSION);
		}
		return renderKitExtension;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.RENDER_KIT_TYPE__ID, oldId, id));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FacesConfigPackage.RENDER_KIT_TYPE__DESCRIPTION:
				return ((InternalEList)getDescription()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.RENDER_KIT_TYPE__DISPLAY_NAME:
				return ((InternalEList)getDisplayName()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.RENDER_KIT_TYPE__ICON:
				return ((InternalEList)getIcon()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.RENDER_KIT_TYPE__RENDER_KIT_ID:
				return basicSetRenderKitId(null, msgs);
			case FacesConfigPackage.RENDER_KIT_TYPE__RENDER_KIT_CLASS:
				return basicSetRenderKitClass(null, msgs);
			case FacesConfigPackage.RENDER_KIT_TYPE__RENDERER:
				return ((InternalEList)getRenderer()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.RENDER_KIT_TYPE__CLIENT_BEHAVIOR_RENDERER:
				return ((InternalEList)getClientBehaviorRenderer()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.RENDER_KIT_TYPE__RENDER_KIT_EXTENSION:
				return ((InternalEList)getRenderKitExtension()).basicRemove(otherEnd, msgs);
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
			case FacesConfigPackage.RENDER_KIT_TYPE__DESCRIPTION:
				return getDescription();
			case FacesConfigPackage.RENDER_KIT_TYPE__DISPLAY_NAME:
				return getDisplayName();
			case FacesConfigPackage.RENDER_KIT_TYPE__ICON:
				return getIcon();
			case FacesConfigPackage.RENDER_KIT_TYPE__RENDER_KIT_ID:
				return getRenderKitId();
			case FacesConfigPackage.RENDER_KIT_TYPE__RENDER_KIT_CLASS:
				return getRenderKitClass();
			case FacesConfigPackage.RENDER_KIT_TYPE__RENDERER:
				return getRenderer();
			case FacesConfigPackage.RENDER_KIT_TYPE__CLIENT_BEHAVIOR_RENDERER:
				return getClientBehaviorRenderer();
			case FacesConfigPackage.RENDER_KIT_TYPE__RENDER_KIT_EXTENSION:
				return getRenderKitExtension();
			case FacesConfigPackage.RENDER_KIT_TYPE__ID:
				return getId();
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
			case FacesConfigPackage.RENDER_KIT_TYPE__DESCRIPTION:
				getDescription().clear();
				getDescription().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.RENDER_KIT_TYPE__DISPLAY_NAME:
				getDisplayName().clear();
				getDisplayName().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.RENDER_KIT_TYPE__ICON:
				getIcon().clear();
				getIcon().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.RENDER_KIT_TYPE__RENDER_KIT_ID:
				setRenderKitId((RenderKitIdType)newValue);
				return;
			case FacesConfigPackage.RENDER_KIT_TYPE__RENDER_KIT_CLASS:
				setRenderKitClass((RenderKitClassType)newValue);
				return;
			case FacesConfigPackage.RENDER_KIT_TYPE__RENDERER:
				getRenderer().clear();
				getRenderer().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.RENDER_KIT_TYPE__CLIENT_BEHAVIOR_RENDERER:
				getClientBehaviorRenderer().clear();
				getClientBehaviorRenderer().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.RENDER_KIT_TYPE__RENDER_KIT_EXTENSION:
				getRenderKitExtension().clear();
				getRenderKitExtension().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.RENDER_KIT_TYPE__ID:
				setId((String)newValue);
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
			case FacesConfigPackage.RENDER_KIT_TYPE__DESCRIPTION:
				getDescription().clear();
				return;
			case FacesConfigPackage.RENDER_KIT_TYPE__DISPLAY_NAME:
				getDisplayName().clear();
				return;
			case FacesConfigPackage.RENDER_KIT_TYPE__ICON:
				getIcon().clear();
				return;
			case FacesConfigPackage.RENDER_KIT_TYPE__RENDER_KIT_ID:
				setRenderKitId((RenderKitIdType)null);
				return;
			case FacesConfigPackage.RENDER_KIT_TYPE__RENDER_KIT_CLASS:
				setRenderKitClass((RenderKitClassType)null);
				return;
			case FacesConfigPackage.RENDER_KIT_TYPE__RENDERER:
				getRenderer().clear();
				return;
			case FacesConfigPackage.RENDER_KIT_TYPE__CLIENT_BEHAVIOR_RENDERER:
				getClientBehaviorRenderer().clear();
				return;
			case FacesConfigPackage.RENDER_KIT_TYPE__RENDER_KIT_EXTENSION:
				getRenderKitExtension().clear();
				return;
			case FacesConfigPackage.RENDER_KIT_TYPE__ID:
				setId(ID_EDEFAULT);
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
			case FacesConfigPackage.RENDER_KIT_TYPE__DESCRIPTION:
				return description != null && !description.isEmpty();
			case FacesConfigPackage.RENDER_KIT_TYPE__DISPLAY_NAME:
				return displayName != null && !displayName.isEmpty();
			case FacesConfigPackage.RENDER_KIT_TYPE__ICON:
				return icon != null && !icon.isEmpty();
			case FacesConfigPackage.RENDER_KIT_TYPE__RENDER_KIT_ID:
				return renderKitId != null;
			case FacesConfigPackage.RENDER_KIT_TYPE__RENDER_KIT_CLASS:
				return renderKitClass != null;
			case FacesConfigPackage.RENDER_KIT_TYPE__RENDERER:
				return renderer != null && !renderer.isEmpty();
			case FacesConfigPackage.RENDER_KIT_TYPE__CLIENT_BEHAVIOR_RENDERER:
				return clientBehaviorRenderer != null && !clientBehaviorRenderer.isEmpty();
			case FacesConfigPackage.RENDER_KIT_TYPE__RENDER_KIT_EXTENSION:
				return renderKitExtension != null && !renderKitExtension.isEmpty();
			case FacesConfigPackage.RENDER_KIT_TYPE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
		}
		return super.eIsSet(featureID);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //RenderKitTypeImpl
