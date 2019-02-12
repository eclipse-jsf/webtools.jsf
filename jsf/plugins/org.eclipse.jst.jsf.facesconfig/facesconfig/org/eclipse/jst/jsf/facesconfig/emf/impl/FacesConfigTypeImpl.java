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
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.jsf.facesconfig.emf.AbsoluteOrderingType;
import org.eclipse.jst.jsf.facesconfig.emf.ApplicationType;
import org.eclipse.jst.jsf.facesconfig.emf.BehaviorType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentType;
import org.eclipse.jst.jsf.facesconfig.emf.ConverterType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.emf.FactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.LifecycleType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType;
import org.eclipse.jst.jsf.facesconfig.emf.NameType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType;
import org.eclipse.jst.jsf.facesconfig.emf.OrderingType;
import org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanType;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitType;
import org.eclipse.jst.jsf.facesconfig.emf.ValidatorType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigTypeImpl#getApplication <em>Application</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigTypeImpl#getOrdering <em>Ordering</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigTypeImpl#getAbsoluteOrdering <em>Absolute Ordering</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigTypeImpl#getFactory <em>Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigTypeImpl#getComponent <em>Component</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigTypeImpl#getConverter <em>Converter</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigTypeImpl#getManagedBean <em>Managed Bean</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigTypeImpl#getNavigationRule <em>Navigation Rule</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigTypeImpl#getReferencedBean <em>Referenced Bean</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigTypeImpl#getRenderKit <em>Render Kit</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigTypeImpl#getLifecycle <em>Lifecycle</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigTypeImpl#getValidator <em>Validator</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigTypeImpl#getBehavior <em>Behavior</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigTypeImpl#getFacesConfigExtension <em>Faces Config Extension</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.impl.FacesConfigTypeImpl#isMetadataComplete <em>Metadata Complete</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FacesConfigTypeImpl extends EObjectImpl implements FacesConfigType {
    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others"; //$NON-NLS-1$

    /**
	 * The cached value of the '{@link #getApplication() <em>Application</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getApplication()
	 * @generated
	 * @ordered
	 */
	protected EList application;

    /**
	 * The cached value of the '{@link #getOrdering() <em>Ordering</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrdering()
	 * @generated
	 * @ordered
	 */
	protected EList ordering;

				/**
	 * The cached value of the '{@link #getAbsoluteOrdering() <em>Absolute Ordering</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAbsoluteOrdering()
	 * @generated
	 * @ordered
	 */
	protected AbsoluteOrderingType absoluteOrdering;

				/**
	 * The cached value of the '{@link #getFactory() <em>Factory</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFactory()
	 * @generated
	 * @ordered
	 */
	protected EList factory;

    /**
	 * The cached value of the '{@link #getComponent() <em>Component</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponent()
	 * @generated
	 * @ordered
	 */
	protected EList component;

    /**
	 * The cached value of the '{@link #getConverter() <em>Converter</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConverter()
	 * @generated
	 * @ordered
	 */
	protected EList converter;

    /**
	 * The cached value of the '{@link #getManagedBean() <em>Managed Bean</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getManagedBean()
	 * @generated
	 * @ordered
	 */
	protected EList managedBean;

    /**
	 * The cached value of the '{@link #getName() <em>Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected NameType name;

				/**
	 * The cached value of the '{@link #getNavigationRule() <em>Navigation Rule</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNavigationRule()
	 * @generated
	 * @ordered
	 */
	protected EList navigationRule;

    /**
	 * The cached value of the '{@link #getReferencedBean() <em>Referenced Bean</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferencedBean()
	 * @generated
	 * @ordered
	 */
	protected EList referencedBean;

    /**
	 * The cached value of the '{@link #getRenderKit() <em>Render Kit</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRenderKit()
	 * @generated
	 * @ordered
	 */
	protected EList renderKit;

    /**
	 * The cached value of the '{@link #getLifecycle() <em>Lifecycle</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLifecycle()
	 * @generated
	 * @ordered
	 */
	protected EList lifecycle;

    /**
	 * The cached value of the '{@link #getValidator() <em>Validator</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValidator()
	 * @generated
	 * @ordered
	 */
	protected EList validator;

    /**
	 * The cached value of the '{@link #getBehavior() <em>Behavior</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBehavior()
	 * @generated
	 * @ordered
	 */
	protected EList behavior;

				/**
	 * The cached value of the '{@link #getFacesConfigExtension() <em>Faces Config Extension</em>}' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getFacesConfigExtension()
	 * @generated
	 * @ordered
	 */
    protected EList facesConfigExtension;

    /**
	 * The default value of the '{@link #getXmlns() <em>Xmlns</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXmlns()
	 * @generated
	 * @ordered
	 */
	protected static final String XMLNS_EDEFAULT = "http://java.sun.com/JSF/Configuration"; //$NON-NLS-1$

    /**
	 * The cached value of the '{@link #getXmlns() <em>Xmlns</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXmlns()
	 * @generated
	 * @ordered
	 */
	protected String xmlns = XMLNS_EDEFAULT;

    /**
	 * This is true if the Xmlns attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean xmlnsESet;

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
	 * The default value of the '{@link #isMetadataComplete() <em>Metadata Complete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMetadataComplete()
	 * @generated
	 * @ordered
	 */
	protected static final boolean METADATA_COMPLETE_EDEFAULT = false;

				/**
	 * The cached value of the '{@link #isMetadataComplete() <em>Metadata Complete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMetadataComplete()
	 * @generated
	 * @ordered
	 */
	protected boolean metadataComplete = METADATA_COMPLETE_EDEFAULT;

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FacesConfigTypeImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return FacesConfigPackage.Literals.FACES_CONFIG_TYPE;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getApplication() {
		if (application == null) {
			application = new EObjectContainmentEList(ApplicationType.class, this, FacesConfigPackage.FACES_CONFIG_TYPE__APPLICATION);
		}
		return application;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getOrdering() {
		if (ordering == null) {
			ordering = new EObjectContainmentEList(OrderingType.class, this, FacesConfigPackage.FACES_CONFIG_TYPE__ORDERING);
		}
		return ordering;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbsoluteOrderingType getAbsoluteOrdering() {
		return absoluteOrdering;
	}

				/**
	 * <!-- begin-user-doc -->
	 * @param newAbsoluteOrdering 
	 * @param msgs 
	 * @return NotificationChain
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAbsoluteOrdering(AbsoluteOrderingType newAbsoluteOrdering, NotificationChain msgs) {
		AbsoluteOrderingType oldAbsoluteOrdering = absoluteOrdering;
		absoluteOrdering = newAbsoluteOrdering;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.FACES_CONFIG_TYPE__ABSOLUTE_ORDERING, oldAbsoluteOrdering, newAbsoluteOrdering);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAbsoluteOrdering(AbsoluteOrderingType newAbsoluteOrdering) {
		if (newAbsoluteOrdering != absoluteOrdering) {
			NotificationChain msgs = null;
			if (absoluteOrdering != null)
				msgs = ((InternalEObject)absoluteOrdering).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.FACES_CONFIG_TYPE__ABSOLUTE_ORDERING, null, msgs);
			if (newAbsoluteOrdering != null)
				msgs = ((InternalEObject)newAbsoluteOrdering).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.FACES_CONFIG_TYPE__ABSOLUTE_ORDERING, null, msgs);
			msgs = basicSetAbsoluteOrdering(newAbsoluteOrdering, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.FACES_CONFIG_TYPE__ABSOLUTE_ORDERING, newAbsoluteOrdering, newAbsoluteOrdering));
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getFactory() {
		if (factory == null) {
			factory = new EObjectContainmentEList(FactoryType.class, this, FacesConfigPackage.FACES_CONFIG_TYPE__FACTORY);
		}
		return factory;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getComponent() {
		if (component == null) {
			component = new EObjectContainmentEList(ComponentType.class, this, FacesConfigPackage.FACES_CONFIG_TYPE__COMPONENT);
		}
		return component;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getConverter() {
		if (converter == null) {
			converter = new EObjectContainmentEList(ConverterType.class, this, FacesConfigPackage.FACES_CONFIG_TYPE__CONVERTER);
		}
		return converter;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getManagedBean() {
		if (managedBean == null) {
			managedBean = new EObjectContainmentEList(ManagedBeanType.class, this, FacesConfigPackage.FACES_CONFIG_TYPE__MANAGED_BEAN);
		}
		return managedBean;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NameType getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @param newName 
	 * @param msgs 
	 * @return NotificationChain
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetName(NameType newName, NotificationChain msgs) {
		NameType oldName = name;
		name = newName;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FacesConfigPackage.FACES_CONFIG_TYPE__NAME, oldName, newName);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(NameType newName) {
		if (newName != name) {
			NotificationChain msgs = null;
			if (name != null)
				msgs = ((InternalEObject)name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.FACES_CONFIG_TYPE__NAME, null, msgs);
			if (newName != null)
				msgs = ((InternalEObject)newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FacesConfigPackage.FACES_CONFIG_TYPE__NAME, null, msgs);
			msgs = basicSetName(newName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.FACES_CONFIG_TYPE__NAME, newName, newName));
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getNavigationRule() {
		if (navigationRule == null) {
			navigationRule = new EObjectContainmentEList(NavigationRuleType.class, this, FacesConfigPackage.FACES_CONFIG_TYPE__NAVIGATION_RULE);
		}
		return navigationRule;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getReferencedBean() {
		if (referencedBean == null) {
			referencedBean = new EObjectContainmentEList(ReferencedBeanType.class, this, FacesConfigPackage.FACES_CONFIG_TYPE__REFERENCED_BEAN);
		}
		return referencedBean;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getRenderKit() {
		if (renderKit == null) {
			renderKit = new EObjectContainmentEList(RenderKitType.class, this, FacesConfigPackage.FACES_CONFIG_TYPE__RENDER_KIT);
		}
		return renderKit;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getLifecycle() {
		if (lifecycle == null) {
			lifecycle = new EObjectContainmentEList(LifecycleType.class, this, FacesConfigPackage.FACES_CONFIG_TYPE__LIFECYCLE);
		}
		return lifecycle;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getValidator() {
		if (validator == null) {
			validator = new EObjectContainmentEList(ValidatorType.class, this, FacesConfigPackage.FACES_CONFIG_TYPE__VALIDATOR);
		}
		return validator;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getBehavior() {
		if (behavior == null) {
			behavior = new EObjectContainmentEList(BehaviorType.class, this, FacesConfigPackage.FACES_CONFIG_TYPE__BEHAVIOR);
		}
		return behavior;
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getFacesConfigExtension() {
		if (facesConfigExtension == null) {
			facesConfigExtension = new EObjectResolvingEList(FacesConfigExtensionType.class, this, FacesConfigPackage.FACES_CONFIG_TYPE__FACES_CONFIG_EXTENSION);
		}
		return facesConfigExtension;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getXmlns() {
		return xmlns;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setXmlns(String newXmlns) {
		String oldXmlns = xmlns;
		xmlns = newXmlns;
		boolean oldXmlnsESet = xmlnsESet;
		xmlnsESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.FACES_CONFIG_TYPE__XMLNS, oldXmlns, xmlns, !oldXmlnsESet));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetXmlns() {
		String oldXmlns = xmlns;
		boolean oldXmlnsESet = xmlnsESet;
		xmlns = XMLNS_EDEFAULT;
		xmlnsESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, FacesConfigPackage.FACES_CONFIG_TYPE__XMLNS, oldXmlns, XMLNS_EDEFAULT, oldXmlnsESet));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetXmlns() {
		return xmlnsESet;
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
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.FACES_CONFIG_TYPE__ID, oldId, id));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMetadataComplete() {
		return metadataComplete;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMetadataComplete(boolean newMetadataComplete) {
		boolean oldMetadataComplete = metadataComplete;
		metadataComplete = newMetadataComplete;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FacesConfigPackage.FACES_CONFIG_TYPE__METADATA_COMPLETE, oldMetadataComplete, metadataComplete));
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FacesConfigPackage.FACES_CONFIG_TYPE__APPLICATION:
				return ((InternalEList)getApplication()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.FACES_CONFIG_TYPE__ORDERING:
				return ((InternalEList)getOrdering()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.FACES_CONFIG_TYPE__ABSOLUTE_ORDERING:
				return basicSetAbsoluteOrdering(null, msgs);
			case FacesConfigPackage.FACES_CONFIG_TYPE__FACTORY:
				return ((InternalEList)getFactory()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.FACES_CONFIG_TYPE__COMPONENT:
				return ((InternalEList)getComponent()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.FACES_CONFIG_TYPE__CONVERTER:
				return ((InternalEList)getConverter()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.FACES_CONFIG_TYPE__MANAGED_BEAN:
				return ((InternalEList)getManagedBean()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.FACES_CONFIG_TYPE__NAME:
				return basicSetName(null, msgs);
			case FacesConfigPackage.FACES_CONFIG_TYPE__NAVIGATION_RULE:
				return ((InternalEList)getNavigationRule()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.FACES_CONFIG_TYPE__REFERENCED_BEAN:
				return ((InternalEList)getReferencedBean()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.FACES_CONFIG_TYPE__RENDER_KIT:
				return ((InternalEList)getRenderKit()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.FACES_CONFIG_TYPE__LIFECYCLE:
				return ((InternalEList)getLifecycle()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.FACES_CONFIG_TYPE__VALIDATOR:
				return ((InternalEList)getValidator()).basicRemove(otherEnd, msgs);
			case FacesConfigPackage.FACES_CONFIG_TYPE__BEHAVIOR:
				return ((InternalEList)getBehavior()).basicRemove(otherEnd, msgs);
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
			case FacesConfigPackage.FACES_CONFIG_TYPE__APPLICATION:
				return getApplication();
			case FacesConfigPackage.FACES_CONFIG_TYPE__ORDERING:
				return getOrdering();
			case FacesConfigPackage.FACES_CONFIG_TYPE__ABSOLUTE_ORDERING:
				return getAbsoluteOrdering();
			case FacesConfigPackage.FACES_CONFIG_TYPE__FACTORY:
				return getFactory();
			case FacesConfigPackage.FACES_CONFIG_TYPE__COMPONENT:
				return getComponent();
			case FacesConfigPackage.FACES_CONFIG_TYPE__CONVERTER:
				return getConverter();
			case FacesConfigPackage.FACES_CONFIG_TYPE__MANAGED_BEAN:
				return getManagedBean();
			case FacesConfigPackage.FACES_CONFIG_TYPE__NAME:
				return getName();
			case FacesConfigPackage.FACES_CONFIG_TYPE__NAVIGATION_RULE:
				return getNavigationRule();
			case FacesConfigPackage.FACES_CONFIG_TYPE__REFERENCED_BEAN:
				return getReferencedBean();
			case FacesConfigPackage.FACES_CONFIG_TYPE__RENDER_KIT:
				return getRenderKit();
			case FacesConfigPackage.FACES_CONFIG_TYPE__LIFECYCLE:
				return getLifecycle();
			case FacesConfigPackage.FACES_CONFIG_TYPE__VALIDATOR:
				return getValidator();
			case FacesConfigPackage.FACES_CONFIG_TYPE__BEHAVIOR:
				return getBehavior();
			case FacesConfigPackage.FACES_CONFIG_TYPE__FACES_CONFIG_EXTENSION:
				return getFacesConfigExtension();
			case FacesConfigPackage.FACES_CONFIG_TYPE__XMLNS:
				return getXmlns();
			case FacesConfigPackage.FACES_CONFIG_TYPE__ID:
				return getId();
			case FacesConfigPackage.FACES_CONFIG_TYPE__METADATA_COMPLETE:
				return isMetadataComplete() ? Boolean.TRUE : Boolean.FALSE;
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
			case FacesConfigPackage.FACES_CONFIG_TYPE__APPLICATION:
				getApplication().clear();
				getApplication().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.FACES_CONFIG_TYPE__ORDERING:
				getOrdering().clear();
				getOrdering().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.FACES_CONFIG_TYPE__ABSOLUTE_ORDERING:
				setAbsoluteOrdering((AbsoluteOrderingType)newValue);
				return;
			case FacesConfigPackage.FACES_CONFIG_TYPE__FACTORY:
				getFactory().clear();
				getFactory().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.FACES_CONFIG_TYPE__COMPONENT:
				getComponent().clear();
				getComponent().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.FACES_CONFIG_TYPE__CONVERTER:
				getConverter().clear();
				getConverter().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.FACES_CONFIG_TYPE__MANAGED_BEAN:
				getManagedBean().clear();
				getManagedBean().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.FACES_CONFIG_TYPE__NAME:
				setName((NameType)newValue);
				return;
			case FacesConfigPackage.FACES_CONFIG_TYPE__NAVIGATION_RULE:
				getNavigationRule().clear();
				getNavigationRule().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.FACES_CONFIG_TYPE__REFERENCED_BEAN:
				getReferencedBean().clear();
				getReferencedBean().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.FACES_CONFIG_TYPE__RENDER_KIT:
				getRenderKit().clear();
				getRenderKit().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.FACES_CONFIG_TYPE__LIFECYCLE:
				getLifecycle().clear();
				getLifecycle().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.FACES_CONFIG_TYPE__VALIDATOR:
				getValidator().clear();
				getValidator().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.FACES_CONFIG_TYPE__BEHAVIOR:
				getBehavior().clear();
				getBehavior().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.FACES_CONFIG_TYPE__FACES_CONFIG_EXTENSION:
				getFacesConfigExtension().clear();
				getFacesConfigExtension().addAll((Collection)newValue);
				return;
			case FacesConfigPackage.FACES_CONFIG_TYPE__XMLNS:
				setXmlns((String)newValue);
				return;
			case FacesConfigPackage.FACES_CONFIG_TYPE__ID:
				setId((String)newValue);
				return;
			case FacesConfigPackage.FACES_CONFIG_TYPE__METADATA_COMPLETE:
				setMetadataComplete(((Boolean)newValue).booleanValue());
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
			case FacesConfigPackage.FACES_CONFIG_TYPE__APPLICATION:
				getApplication().clear();
				return;
			case FacesConfigPackage.FACES_CONFIG_TYPE__ORDERING:
				getOrdering().clear();
				return;
			case FacesConfigPackage.FACES_CONFIG_TYPE__ABSOLUTE_ORDERING:
				setAbsoluteOrdering((AbsoluteOrderingType)null);
				return;
			case FacesConfigPackage.FACES_CONFIG_TYPE__FACTORY:
				getFactory().clear();
				return;
			case FacesConfigPackage.FACES_CONFIG_TYPE__COMPONENT:
				getComponent().clear();
				return;
			case FacesConfigPackage.FACES_CONFIG_TYPE__CONVERTER:
				getConverter().clear();
				return;
			case FacesConfigPackage.FACES_CONFIG_TYPE__MANAGED_BEAN:
				getManagedBean().clear();
				return;
			case FacesConfigPackage.FACES_CONFIG_TYPE__NAME:
				setName((NameType)null);
				return;
			case FacesConfigPackage.FACES_CONFIG_TYPE__NAVIGATION_RULE:
				getNavigationRule().clear();
				return;
			case FacesConfigPackage.FACES_CONFIG_TYPE__REFERENCED_BEAN:
				getReferencedBean().clear();
				return;
			case FacesConfigPackage.FACES_CONFIG_TYPE__RENDER_KIT:
				getRenderKit().clear();
				return;
			case FacesConfigPackage.FACES_CONFIG_TYPE__LIFECYCLE:
				getLifecycle().clear();
				return;
			case FacesConfigPackage.FACES_CONFIG_TYPE__VALIDATOR:
				getValidator().clear();
				return;
			case FacesConfigPackage.FACES_CONFIG_TYPE__BEHAVIOR:
				getBehavior().clear();
				return;
			case FacesConfigPackage.FACES_CONFIG_TYPE__FACES_CONFIG_EXTENSION:
				getFacesConfigExtension().clear();
				return;
			case FacesConfigPackage.FACES_CONFIG_TYPE__XMLNS:
				unsetXmlns();
				return;
			case FacesConfigPackage.FACES_CONFIG_TYPE__ID:
				setId(ID_EDEFAULT);
				return;
			case FacesConfigPackage.FACES_CONFIG_TYPE__METADATA_COMPLETE:
				setMetadataComplete(METADATA_COMPLETE_EDEFAULT);
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
			case FacesConfigPackage.FACES_CONFIG_TYPE__APPLICATION:
				return application != null && !application.isEmpty();
			case FacesConfigPackage.FACES_CONFIG_TYPE__ORDERING:
				return ordering != null && !ordering.isEmpty();
			case FacesConfigPackage.FACES_CONFIG_TYPE__ABSOLUTE_ORDERING:
				return absoluteOrdering != null;
			case FacesConfigPackage.FACES_CONFIG_TYPE__FACTORY:
				return factory != null && !factory.isEmpty();
			case FacesConfigPackage.FACES_CONFIG_TYPE__COMPONENT:
				return component != null && !component.isEmpty();
			case FacesConfigPackage.FACES_CONFIG_TYPE__CONVERTER:
				return converter != null && !converter.isEmpty();
			case FacesConfigPackage.FACES_CONFIG_TYPE__MANAGED_BEAN:
				return managedBean != null && !managedBean.isEmpty();
			case FacesConfigPackage.FACES_CONFIG_TYPE__NAME:
				return name != null;
			case FacesConfigPackage.FACES_CONFIG_TYPE__NAVIGATION_RULE:
				return navigationRule != null && !navigationRule.isEmpty();
			case FacesConfigPackage.FACES_CONFIG_TYPE__REFERENCED_BEAN:
				return referencedBean != null && !referencedBean.isEmpty();
			case FacesConfigPackage.FACES_CONFIG_TYPE__RENDER_KIT:
				return renderKit != null && !renderKit.isEmpty();
			case FacesConfigPackage.FACES_CONFIG_TYPE__LIFECYCLE:
				return lifecycle != null && !lifecycle.isEmpty();
			case FacesConfigPackage.FACES_CONFIG_TYPE__VALIDATOR:
				return validator != null && !validator.isEmpty();
			case FacesConfigPackage.FACES_CONFIG_TYPE__BEHAVIOR:
				return behavior != null && !behavior.isEmpty();
			case FacesConfigPackage.FACES_CONFIG_TYPE__FACES_CONFIG_EXTENSION:
				return facesConfigExtension != null && !facesConfigExtension.isEmpty();
			case FacesConfigPackage.FACES_CONFIG_TYPE__XMLNS:
				return isSetXmlns();
			case FacesConfigPackage.FACES_CONFIG_TYPE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case FacesConfigPackage.FACES_CONFIG_TYPE__METADATA_COMPLETE:
				return metadataComplete != METADATA_COMPLETE_EDEFAULT;
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
		result.append(" (xmlns: "); //$NON-NLS-1$
		if (xmlnsESet) result.append(xmlns); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(", metadataComplete: "); //$NON-NLS-1$
		result.append(metadataComplete);
		result.append(')');
		return result.toString();
	}

} //FacesConfigTypeImpl
