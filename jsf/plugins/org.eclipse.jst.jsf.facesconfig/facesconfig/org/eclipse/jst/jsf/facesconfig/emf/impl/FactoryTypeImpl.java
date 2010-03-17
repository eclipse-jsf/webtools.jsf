/***************************************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.jsf.facesconfig.emf.ApplicationFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.ExceptionHandlerFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.ExternalContextFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.FacesContextFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.FactoryExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.FactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.LifecycleFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.PartialViewContextFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.TagHandlerDelegateFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.ViewDeclarationLanguageFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.VisitContextFactoryType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Factory Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FactoryTypeImpl#getApplicationFactory <em>Application Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FactoryTypeImpl#getExceptionHandlerFactory <em>Exception Handler Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FactoryTypeImpl#getExternalContextFactory <em>External Context Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FactoryTypeImpl#getFacesContextFactory <em>Faces Context Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FactoryTypeImpl#getPartialViewContextFactory <em>Partial View Context Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FactoryTypeImpl#getLifecycleFactory <em>Lifecycle Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FactoryTypeImpl#getViewDeclarationLanguageFactory <em>View Declaration Language Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FactoryTypeImpl#getTagHandlerDelegateFactory <em>Tag Handler Delegate Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FactoryTypeImpl#getRenderKitFactory <em>Render Kit Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FactoryTypeImpl#getVisitContextFactory <em>Visit Context Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FactoryTypeImpl#getFactoryExtension <em>Factory Extension</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FactoryTypeImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FactoryTypeImpl extends EObjectImpl implements FactoryType {
    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others"; //$NON-NLS-1$

    /**
	 * The cached value of the '{@link #getApplicationFactory() <em>Application Factory</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getApplicationFactory()
	 * @generated
	 * @ordered
	 */
	protected EList applicationFactory;

    /**
	 * The cached value of the '{@link #getExceptionHandlerFactory() <em>Exception Handler Factory</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExceptionHandlerFactory()
	 * @generated
	 * @ordered
	 */
	protected EList exceptionHandlerFactory;

				/**
	 * The cached value of the '{@link #getExternalContextFactory() <em>External Context Factory</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExternalContextFactory()
	 * @generated
	 * @ordered
	 */
	protected EList externalContextFactory;

				/**
	 * The cached value of the '{@link #getFacesContextFactory() <em>Faces Context Factory</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFacesContextFactory()
	 * @generated
	 * @ordered
	 */
	protected EList facesContextFactory;

    /**
	 * The cached value of the '{@link #getPartialViewContextFactory() <em>Partial View Context Factory</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPartialViewContextFactory()
	 * @generated
	 * @ordered
	 */
	protected EList partialViewContextFactory;

				/**
	 * The cached value of the '{@link #getLifecycleFactory() <em>Lifecycle Factory</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLifecycleFactory()
	 * @generated
	 * @ordered
	 */
	protected EList lifecycleFactory;

    /**
	 * The cached value of the '{@link #getViewDeclarationLanguageFactory() <em>View Declaration Language Factory</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getViewDeclarationLanguageFactory()
	 * @generated
	 * @ordered
	 */
	protected EList viewDeclarationLanguageFactory;

				/**
	 * The cached value of the '{@link #getTagHandlerDelegateFactory() <em>Tag Handler Delegate Factory</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTagHandlerDelegateFactory()
	 * @generated
	 * @ordered
	 */
	protected EList tagHandlerDelegateFactory;

				/**
	 * The cached value of the '{@link #getRenderKitFactory() <em>Render Kit Factory</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRenderKitFactory()
	 * @generated
	 * @ordered
	 */
	protected EList renderKitFactory;

    /**
	 * The cached value of the '{@link #getVisitContextFactory() <em>Visit Context Factory</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVisitContextFactory()
	 * @generated
	 * @ordered
	 */
	protected EList visitContextFactory;

				/**
	 * The cached value of the '{@link #getFactoryExtension() <em>Factory Extension</em>}' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getFactoryExtension()
	 * @generated
	 * @ordered
	 */
    protected EList factoryExtension;

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
	protected FactoryTypeImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return FacesConfigPackage.Literals.FACTORY_TYPE;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getApplicationFactory() {
		if (applicationFactory == null) {
			applicationFactory = new EObjectContainmentEList(ApplicationFactoryType.class, this, FacesConfigPackage.FACTORY_TYPE__APPLICATION_FACTORY);
		}
		return applicationFactory;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getExceptionHandlerFactory() {
		if (exceptionHandlerFactory == null) {
			exceptionHandlerFactory = new EObjectContainmentEList(ExceptionHandlerFactoryType.class, this, FacesConfigPackage.FACTORY_TYPE__EXCEPTION_HANDLER_FACTORY);
		}
		return exceptionHandlerFactory;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getExternalContextFactory() {
		if (externalContextFactory == null) {
			externalContextFactory = new EObjectContainmentEList(ExternalContextFactoryType.class, this, FacesConfigPackage.FACTORY_TYPE__EXTERNAL_CONTEXT_FACTORY);
		}
		return externalContextFactory;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getFacesContextFactory() {
		if (facesContextFactory == null) {
			facesContextFactory = new EObjectContainmentEList(FacesContextFactoryType.class, this, FacesConfigPackage.FACTORY_TYPE__FACES_CONTEXT_FACTORY);
		}
		return facesContextFactory;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getPartialViewContextFactory() {
		if (partialViewContextFactory == null) {
			partialViewContextFactory = new EObjectContainmentEList(PartialViewContextFactoryType.class, this, FacesConfigPackage.FACTORY_TYPE__PARTIAL_VIEW_CONTEXT_FACTORY);
		}
		return partialViewContextFactory;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getLifecycleFactory() {
		if (lifecycleFactory == null) {
			lifecycleFactory = new EObjectContainmentEList(LifecycleFactoryType.class, this, FacesConfigPackage.FACTORY_TYPE__LIFECYCLE_FACTORY);
		}
		return lifecycleFactory;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getViewDeclarationLanguageFactory() {
		if (viewDeclarationLanguageFactory == null) {
			viewDeclarationLanguageFactory = new EObjectContainmentEList(ViewDeclarationLanguageFactoryType.class, this, FacesConfigPackage.FACTORY_TYPE__VIEW_DECLARATION_LANGUAGE_FACTORY);
		}
		return viewDeclarationLanguageFactory;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getTagHandlerDelegateFactory() {
		if (tagHandlerDelegateFactory == null) {
			tagHandlerDelegateFactory = new EObjectContainmentEList(TagHandlerDelegateFactoryType.class, this, FacesConfigPackage.FACTORY_TYPE__TAG_HANDLER_DELEGATE_FACTORY);
		}
		return tagHandlerDelegateFactory;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getRenderKitFactory() {
		if (renderKitFactory == null) {
			renderKitFactory = new EObjectContainmentEList(RenderKitFactoryType.class, this, FacesConfigPackage.FACTORY_TYPE__RENDER_KIT_FACTORY);
		}
		return renderKitFactory;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getVisitContextFactory() {
		if (visitContextFactory == null) {
			visitContextFactory = new EObjectContainmentEList(VisitContextFactoryType.class, this, FacesConfigPackage.FACTORY_TYPE__VISIT_CONTEXT_FACTORY);
		}
		return visitContextFactory;
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getFactoryExtension() {
		if (factoryExtension == null) {
			factoryExtension = new EObjectResolvingEList(FactoryExtensionType.class, this, FacesConfigPackage.FACTORY_TYPE__FACTORY_EXTENSION);
		}
		return factoryExtension;
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
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.FACTORY_TYPE__ID, oldId, id));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FacesConfigPackage.FACTORY_TYPE__APPLICATION_FACTORY:
				return ((InternalEList)getApplicationFactory()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.FACTORY_TYPE__EXCEPTION_HANDLER_FACTORY:
				return ((InternalEList)getExceptionHandlerFactory()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.FACTORY_TYPE__EXTERNAL_CONTEXT_FACTORY:
				return ((InternalEList)getExternalContextFactory()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.FACTORY_TYPE__FACES_CONTEXT_FACTORY:
				return ((InternalEList)getFacesContextFactory()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.FACTORY_TYPE__PARTIAL_VIEW_CONTEXT_FACTORY:
				return ((InternalEList)getPartialViewContextFactory()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.FACTORY_TYPE__LIFECYCLE_FACTORY:
				return ((InternalEList)getLifecycleFactory()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.FACTORY_TYPE__VIEW_DECLARATION_LANGUAGE_FACTORY:
				return ((InternalEList)getViewDeclarationLanguageFactory()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.FACTORY_TYPE__TAG_HANDLER_DELEGATE_FACTORY:
				return ((InternalEList)getTagHandlerDelegateFactory()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.FACTORY_TYPE__RENDER_KIT_FACTORY:
				return ((InternalEList)getRenderKitFactory()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.FACTORY_TYPE__VISIT_CONTEXT_FACTORY:
				return ((InternalEList)getVisitContextFactory()).basicRemove(otherEnd, msgs);
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
			case FacesConfigPackage.FACTORY_TYPE__APPLICATION_FACTORY:
				return getApplicationFactory();
			case FacesConfigPackage.FACTORY_TYPE__EXCEPTION_HANDLER_FACTORY:
				return getExceptionHandlerFactory();
			case FacesConfigPackage.FACTORY_TYPE__EXTERNAL_CONTEXT_FACTORY:
				return getExternalContextFactory();
			case FacesConfigPackage.FACTORY_TYPE__FACES_CONTEXT_FACTORY:
				return getFacesContextFactory();
			case FacesConfigPackage.FACTORY_TYPE__PARTIAL_VIEW_CONTEXT_FACTORY:
				return getPartialViewContextFactory();
			case FacesConfigPackage.FACTORY_TYPE__LIFECYCLE_FACTORY:
				return getLifecycleFactory();
			case FacesConfigPackage.FACTORY_TYPE__VIEW_DECLARATION_LANGUAGE_FACTORY:
				return getViewDeclarationLanguageFactory();
			case FacesConfigPackage.FACTORY_TYPE__TAG_HANDLER_DELEGATE_FACTORY:
				return getTagHandlerDelegateFactory();
			case FacesConfigPackage.FACTORY_TYPE__RENDER_KIT_FACTORY:
				return getRenderKitFactory();
			case FacesConfigPackage.FACTORY_TYPE__VISIT_CONTEXT_FACTORY:
				return getVisitContextFactory();
			case FacesConfigPackage.FACTORY_TYPE__FACTORY_EXTENSION:
				return getFactoryExtension();
			case FacesConfigPackage.FACTORY_TYPE__ID:
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
			case FacesConfigPackage.FACTORY_TYPE__APPLICATION_FACTORY:
				getApplicationFactory().clear();
				getApplicationFactory().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.FACTORY_TYPE__EXCEPTION_HANDLER_FACTORY:
				getExceptionHandlerFactory().clear();
				getExceptionHandlerFactory().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.FACTORY_TYPE__EXTERNAL_CONTEXT_FACTORY:
				getExternalContextFactory().clear();
				getExternalContextFactory().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.FACTORY_TYPE__FACES_CONTEXT_FACTORY:
				getFacesContextFactory().clear();
				getFacesContextFactory().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.FACTORY_TYPE__PARTIAL_VIEW_CONTEXT_FACTORY:
				getPartialViewContextFactory().clear();
				getPartialViewContextFactory().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.FACTORY_TYPE__LIFECYCLE_FACTORY:
				getLifecycleFactory().clear();
				getLifecycleFactory().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.FACTORY_TYPE__VIEW_DECLARATION_LANGUAGE_FACTORY:
				getViewDeclarationLanguageFactory().clear();
				getViewDeclarationLanguageFactory().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.FACTORY_TYPE__TAG_HANDLER_DELEGATE_FACTORY:
				getTagHandlerDelegateFactory().clear();
				getTagHandlerDelegateFactory().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.FACTORY_TYPE__RENDER_KIT_FACTORY:
				getRenderKitFactory().clear();
				getRenderKitFactory().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.FACTORY_TYPE__VISIT_CONTEXT_FACTORY:
				getVisitContextFactory().clear();
				getVisitContextFactory().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.FACTORY_TYPE__FACTORY_EXTENSION:
				getFactoryExtension().clear();
				getFactoryExtension().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.FACTORY_TYPE__ID:
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
			case FacesConfigPackage.FACTORY_TYPE__APPLICATION_FACTORY:
				getApplicationFactory().clear();
				return;
			case FacesConfigPackage.FACTORY_TYPE__EXCEPTION_HANDLER_FACTORY:
				getExceptionHandlerFactory().clear();
				return;
			case FacesConfigPackage.FACTORY_TYPE__EXTERNAL_CONTEXT_FACTORY:
				getExternalContextFactory().clear();
				return;
			case FacesConfigPackage.FACTORY_TYPE__FACES_CONTEXT_FACTORY:
				getFacesContextFactory().clear();
				return;
			case FacesConfigPackage.FACTORY_TYPE__PARTIAL_VIEW_CONTEXT_FACTORY:
				getPartialViewContextFactory().clear();
				return;
			case FacesConfigPackage.FACTORY_TYPE__LIFECYCLE_FACTORY:
				getLifecycleFactory().clear();
				return;
			case FacesConfigPackage.FACTORY_TYPE__VIEW_DECLARATION_LANGUAGE_FACTORY:
				getViewDeclarationLanguageFactory().clear();
				return;
			case FacesConfigPackage.FACTORY_TYPE__TAG_HANDLER_DELEGATE_FACTORY:
				getTagHandlerDelegateFactory().clear();
				return;
			case FacesConfigPackage.FACTORY_TYPE__RENDER_KIT_FACTORY:
				getRenderKitFactory().clear();
				return;
			case FacesConfigPackage.FACTORY_TYPE__VISIT_CONTEXT_FACTORY:
				getVisitContextFactory().clear();
				return;
			case FacesConfigPackage.FACTORY_TYPE__FACTORY_EXTENSION:
				getFactoryExtension().clear();
				return;
			case FacesConfigPackage.FACTORY_TYPE__ID:
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
			case FacesConfigPackage.FACTORY_TYPE__APPLICATION_FACTORY:
				return applicationFactory != null && !applicationFactory.isEmpty();
			case FacesConfigPackage.FACTORY_TYPE__EXCEPTION_HANDLER_FACTORY:
				return exceptionHandlerFactory != null && !exceptionHandlerFactory.isEmpty();
			case FacesConfigPackage.FACTORY_TYPE__EXTERNAL_CONTEXT_FACTORY:
				return externalContextFactory != null && !externalContextFactory.isEmpty();
			case FacesConfigPackage.FACTORY_TYPE__FACES_CONTEXT_FACTORY:
				return facesContextFactory != null && !facesContextFactory.isEmpty();
			case FacesConfigPackage.FACTORY_TYPE__PARTIAL_VIEW_CONTEXT_FACTORY:
				return partialViewContextFactory != null && !partialViewContextFactory.isEmpty();
			case FacesConfigPackage.FACTORY_TYPE__LIFECYCLE_FACTORY:
				return lifecycleFactory != null && !lifecycleFactory.isEmpty();
			case FacesConfigPackage.FACTORY_TYPE__VIEW_DECLARATION_LANGUAGE_FACTORY:
				return viewDeclarationLanguageFactory != null && !viewDeclarationLanguageFactory.isEmpty();
			case FacesConfigPackage.FACTORY_TYPE__TAG_HANDLER_DELEGATE_FACTORY:
				return tagHandlerDelegateFactory != null && !tagHandlerDelegateFactory.isEmpty();
			case FacesConfigPackage.FACTORY_TYPE__RENDER_KIT_FACTORY:
				return renderKitFactory != null && !renderKitFactory.isEmpty();
			case FacesConfigPackage.FACTORY_TYPE__VISIT_CONTEXT_FACTORY:
				return visitContextFactory != null && !visitContextFactory.isEmpty();
			case FacesConfigPackage.FACTORY_TYPE__FACTORY_EXTENSION:
				return factoryExtension != null && !factoryExtension.isEmpty();
			case FacesConfigPackage.FACTORY_TYPE__ID:
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

} //FactoryTypeImpl
