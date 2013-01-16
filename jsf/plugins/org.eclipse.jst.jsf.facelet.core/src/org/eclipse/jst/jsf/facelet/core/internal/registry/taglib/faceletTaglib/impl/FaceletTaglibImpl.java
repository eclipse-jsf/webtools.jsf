/**
 * <copyright>
 * </copyright>
 *
 * $Id: FaceletTaglibImpl.java,v 1.1 2010/03/18 06:24:38 cbateman Exp $
 */
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibExtension;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibFunction;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibVersion;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FullyQualifiedClass;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.IdentifiableStringValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Facelet Taglib</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibImpl#getLibraryClass <em>Library Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibImpl#getNamespace <em>Namespace</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibImpl#getCompositeLibraryName <em>Composite Library Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibImpl#getTag <em>Tag</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibImpl#getFunction <em>Function</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibImpl#getTaglibExtension <em>Taglib Extension</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibImpl#getNamespaceUri <em>Namespace Uri</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibImpl#getShortName <em>Short Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FaceletTaglibImpl extends UserVisibleTaglibObjectImpl implements FaceletTaglib
{
    /**
	 * The cached value of the '{@link #getLibraryClass() <em>Library Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getLibraryClass()
	 * @generated
	 * @ordered
	 */
    protected FullyQualifiedClass libraryClass;

    /**
	 * The cached value of the '{@link #getNamespace() <em>Namespace</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getNamespace()
	 * @generated
	 * @ordered
	 */
    protected IdentifiableStringValue namespace;

    /**
	 * The cached value of the '{@link #getCompositeLibraryName() <em>Composite Library Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getCompositeLibraryName()
	 * @generated
	 * @ordered
	 */
    protected FullyQualifiedClass compositeLibraryName;

    /**
	 * The cached value of the '{@link #getGroup() <em>Group</em>}' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getGroup()
	 * @generated
	 * @ordered
	 */
    protected FeatureMap group;

    /**
	 * The cached value of the '{@link #getTaglibExtension() <em>Taglib Extension</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getTaglibExtension()
	 * @generated
	 * @ordered
	 */
    protected EList<FaceletTaglibExtension> taglibExtension;

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
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
    protected static final FaceletTaglibVersion VERSION_EDEFAULT = FaceletTaglibVersion._20;

    /**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
    protected FaceletTaglibVersion version = VERSION_EDEFAULT;

    /**
	 * This is true if the Version attribute has been set.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    protected boolean versionESet;

    /**
	 * The default value of the '{@link #getNamespaceUri() <em>Namespace Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getNamespaceUri()
	 * @generated
	 * @ordered
	 */
    protected static final String NAMESPACE_URI_EDEFAULT = null;

    /**
	 * The default value of the '{@link #getShortName() <em>Short Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShortName()
	 * @generated
	 * @ordered
	 */
	protected static final String SHORT_NAME_EDEFAULT = null;

				/**
	 * The cached value of the '{@link #getShortName() <em>Short Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShortName()
	 * @generated
	 * @ordered
	 */
	protected String shortName = SHORT_NAME_EDEFAULT;

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected FaceletTaglibImpl()
    {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    protected EClass eStaticClass()
    {
		return FaceletTaglibPackage.Literals.FACELET_TAGLIB;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public FullyQualifiedClass getLibraryClass()
    {
		return libraryClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * @param newLibraryClass 
     * @param msgs 
     * @return the notification chain
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetLibraryClass(FullyQualifiedClass newLibraryClass, NotificationChain msgs)
    {
		FullyQualifiedClass oldLibraryClass = libraryClass;
		libraryClass = newLibraryClass;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB__LIBRARY_CLASS, oldLibraryClass, newLibraryClass);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setLibraryClass(FullyQualifiedClass newLibraryClass)
    {
		if (newLibraryClass != libraryClass) {
			NotificationChain msgs = null;
			if (libraryClass != null)
				msgs = ((InternalEObject)libraryClass).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB__LIBRARY_CLASS, null, msgs);
			if (newLibraryClass != null)
				msgs = ((InternalEObject)newLibraryClass).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB__LIBRARY_CLASS, null, msgs);
			msgs = basicSetLibraryClass(newLibraryClass, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB__LIBRARY_CLASS, newLibraryClass, newLibraryClass));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public IdentifiableStringValue getNamespace()
    {
		return namespace;
	}

    /**
	 * <!-- begin-user-doc -->
     * @param newNamespace 
     * @param msgs 
     * @return the notification chain
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetNamespace(IdentifiableStringValue newNamespace, NotificationChain msgs)
    {
		IdentifiableStringValue oldNamespace = namespace;
		namespace = newNamespace;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB__NAMESPACE, oldNamespace, newNamespace);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setNamespace(IdentifiableStringValue newNamespace)
    {
		if (newNamespace != namespace) {
			NotificationChain msgs = null;
			if (namespace != null)
				msgs = ((InternalEObject)namespace).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB__NAMESPACE, null, msgs);
			if (newNamespace != null)
				msgs = ((InternalEObject)newNamespace).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB__NAMESPACE, null, msgs);
			msgs = basicSetNamespace(newNamespace, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB__NAMESPACE, newNamespace, newNamespace));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public FullyQualifiedClass getCompositeLibraryName()
    {
		return compositeLibraryName;
	}

    /**
	 * <!-- begin-user-doc -->
     * @param newCompositeLibraryName 
     * @param msgs 
     * @return the notification chain
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetCompositeLibraryName(FullyQualifiedClass newCompositeLibraryName, NotificationChain msgs)
    {
		FullyQualifiedClass oldCompositeLibraryName = compositeLibraryName;
		compositeLibraryName = newCompositeLibraryName;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB__COMPOSITE_LIBRARY_NAME, oldCompositeLibraryName, newCompositeLibraryName);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setCompositeLibraryName(FullyQualifiedClass newCompositeLibraryName)
    {
		if (newCompositeLibraryName != compositeLibraryName) {
			NotificationChain msgs = null;
			if (compositeLibraryName != null)
				msgs = ((InternalEObject)compositeLibraryName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB__COMPOSITE_LIBRARY_NAME, null, msgs);
			if (newCompositeLibraryName != null)
				msgs = ((InternalEObject)newCompositeLibraryName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB__COMPOSITE_LIBRARY_NAME, null, msgs);
			msgs = basicSetCompositeLibraryName(newCompositeLibraryName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB__COMPOSITE_LIBRARY_NAME, newCompositeLibraryName, newCompositeLibraryName));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public FeatureMap getGroup()
    {
		if (group == null) {
			group = new BasicFeatureMap(this, FaceletTaglibPackage.FACELET_TAGLIB__GROUP);
		}
		return group;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<FaceletTaglibTag> getTag()
    {
		return getGroup().list(FaceletTaglibPackage.Literals.FACELET_TAGLIB__TAG);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<FaceletTaglibFunction> getFunction()
    {
		return getGroup().list(FaceletTaglibPackage.Literals.FACELET_TAGLIB__FUNCTION);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<FaceletTaglibExtension> getTaglibExtension()
    {
		if (taglibExtension == null) {
			taglibExtension = new EObjectContainmentEList<FaceletTaglibExtension>(FaceletTaglibExtension.class, this, FaceletTaglibPackage.FACELET_TAGLIB__TAGLIB_EXTENSION);
		}
		return taglibExtension;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getId()
    {
		return id;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setId(String newId)
    {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB__ID, oldId, id));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public FaceletTaglibVersion getVersion()
    {
		return version;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setVersion(FaceletTaglibVersion newVersion)
    {
		FaceletTaglibVersion oldVersion = version;
		version = newVersion == null ? VERSION_EDEFAULT : newVersion;
		boolean oldVersionESet = versionESet;
		versionESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB__VERSION, oldVersion, version, !oldVersionESet));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void unsetVersion()
    {
		FaceletTaglibVersion oldVersion = version;
		boolean oldVersionESet = versionESet;
		version = VERSION_EDEFAULT;
		versionESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, FaceletTaglibPackage.FACELET_TAGLIB__VERSION, oldVersion, VERSION_EDEFAULT, oldVersionESet));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isSetVersion()
    {
		return versionESet;
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public String getNamespaceUri()
    {
        return (String) Util.getSimplifiedNestedField(this, FaceletTaglibPackage.Literals.FACELET_TAGLIB__NAMESPACE, FaceletTaglibPackage.Literals.IDENTIFIABLE_STRING_VALUE__VALUE);
    }

    /**
     * <!-- begin-user-doc -->
     * Sets the namespace value on the this taglib.  If the wrapping IdentifiableStringValue
     * does not exist, then it is created. 
     * 
     * NOTE: event listeners will receive an event for the value set on the
     * the IdentifiableStringValue if it was non-null but will receive an
     * event for set on the whole getNamespace() featuer if it was null.
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void setNamespaceUri(String newNamespaceUri)
    {
        Util.setSimplifiedNestedField(this,  FaceletTaglibPackage.Literals.FACELET_TAGLIB__NAMESPACE, FaceletTaglibPackage.Literals.IDENTIFIABLE_STRING_VALUE__VALUE, newNamespaceUri);
    }

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getShortName() {
		return shortName;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setShortName(String newShortName) {
		String oldShortName = shortName;
		shortName = newShortName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB__SHORT_NAME, oldShortName, shortName));
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
    {
		switch (featureID) {
			case FaceletTaglibPackage.FACELET_TAGLIB__LIBRARY_CLASS:
				return basicSetLibraryClass(null, msgs);
			case FaceletTaglibPackage.FACELET_TAGLIB__NAMESPACE:
				return basicSetNamespace(null, msgs);
			case FaceletTaglibPackage.FACELET_TAGLIB__COMPOSITE_LIBRARY_NAME:
				return basicSetCompositeLibraryName(null, msgs);
			case FaceletTaglibPackage.FACELET_TAGLIB__GROUP:
				return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
			case FaceletTaglibPackage.FACELET_TAGLIB__TAG:
				return ((InternalEList<?>)getTag()).basicRemove(otherEnd, msgs);
			case FaceletTaglibPackage.FACELET_TAGLIB__FUNCTION:
				return ((InternalEList<?>)getFunction()).basicRemove(otherEnd, msgs);
			case FaceletTaglibPackage.FACELET_TAGLIB__TAGLIB_EXTENSION:
				return ((InternalEList<?>)getTaglibExtension()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType)
    {
		switch (featureID) {
			case FaceletTaglibPackage.FACELET_TAGLIB__LIBRARY_CLASS:
				return getLibraryClass();
			case FaceletTaglibPackage.FACELET_TAGLIB__NAMESPACE:
				return getNamespace();
			case FaceletTaglibPackage.FACELET_TAGLIB__COMPOSITE_LIBRARY_NAME:
				return getCompositeLibraryName();
			case FaceletTaglibPackage.FACELET_TAGLIB__GROUP:
				if (coreType) return getGroup();
				return ((FeatureMap.Internal)getGroup()).getWrapper();
			case FaceletTaglibPackage.FACELET_TAGLIB__TAG:
				return getTag();
			case FaceletTaglibPackage.FACELET_TAGLIB__FUNCTION:
				return getFunction();
			case FaceletTaglibPackage.FACELET_TAGLIB__TAGLIB_EXTENSION:
				return getTaglibExtension();
			case FaceletTaglibPackage.FACELET_TAGLIB__ID:
				return getId();
			case FaceletTaglibPackage.FACELET_TAGLIB__VERSION:
				return getVersion();
			case FaceletTaglibPackage.FACELET_TAGLIB__NAMESPACE_URI:
				return getNamespaceUri();
			case FaceletTaglibPackage.FACELET_TAGLIB__SHORT_NAME:
				return getShortName();
		}
		return super.eGet(featureID, resolve, coreType);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue)
    {
		switch (featureID) {
			case FaceletTaglibPackage.FACELET_TAGLIB__LIBRARY_CLASS:
				setLibraryClass((FullyQualifiedClass)newValue);
				return;
			case FaceletTaglibPackage.FACELET_TAGLIB__NAMESPACE:
				setNamespace((IdentifiableStringValue)newValue);
				return;
			case FaceletTaglibPackage.FACELET_TAGLIB__COMPOSITE_LIBRARY_NAME:
				setCompositeLibraryName((FullyQualifiedClass)newValue);
				return;
			case FaceletTaglibPackage.FACELET_TAGLIB__GROUP:
				((FeatureMap.Internal)getGroup()).set(newValue);
				return;
			case FaceletTaglibPackage.FACELET_TAGLIB__TAG:
				getTag().clear();
				getTag().addAll((Collection<? extends FaceletTaglibTag>)newValue);
				return;
			case FaceletTaglibPackage.FACELET_TAGLIB__FUNCTION:
				getFunction().clear();
				getFunction().addAll((Collection<? extends FaceletTaglibFunction>)newValue);
				return;
			case FaceletTaglibPackage.FACELET_TAGLIB__TAGLIB_EXTENSION:
				getTaglibExtension().clear();
				getTaglibExtension().addAll((Collection<? extends FaceletTaglibExtension>)newValue);
				return;
			case FaceletTaglibPackage.FACELET_TAGLIB__ID:
				setId((String)newValue);
				return;
			case FaceletTaglibPackage.FACELET_TAGLIB__VERSION:
				setVersion((FaceletTaglibVersion)newValue);
				return;
			case FaceletTaglibPackage.FACELET_TAGLIB__NAMESPACE_URI:
				setNamespaceUri((String)newValue);
				return;
			case FaceletTaglibPackage.FACELET_TAGLIB__SHORT_NAME:
				setShortName((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public void eUnset(int featureID)
    {
		switch (featureID) {
			case FaceletTaglibPackage.FACELET_TAGLIB__LIBRARY_CLASS:
				setLibraryClass((FullyQualifiedClass)null);
				return;
			case FaceletTaglibPackage.FACELET_TAGLIB__NAMESPACE:
				setNamespace((IdentifiableStringValue)null);
				return;
			case FaceletTaglibPackage.FACELET_TAGLIB__COMPOSITE_LIBRARY_NAME:
				setCompositeLibraryName((FullyQualifiedClass)null);
				return;
			case FaceletTaglibPackage.FACELET_TAGLIB__GROUP:
				getGroup().clear();
				return;
			case FaceletTaglibPackage.FACELET_TAGLIB__TAG:
				getTag().clear();
				return;
			case FaceletTaglibPackage.FACELET_TAGLIB__FUNCTION:
				getFunction().clear();
				return;
			case FaceletTaglibPackage.FACELET_TAGLIB__TAGLIB_EXTENSION:
				getTaglibExtension().clear();
				return;
			case FaceletTaglibPackage.FACELET_TAGLIB__ID:
				setId(ID_EDEFAULT);
				return;
			case FaceletTaglibPackage.FACELET_TAGLIB__VERSION:
				unsetVersion();
				return;
			case FaceletTaglibPackage.FACELET_TAGLIB__NAMESPACE_URI:
				setNamespaceUri(NAMESPACE_URI_EDEFAULT);
				return;
			case FaceletTaglibPackage.FACELET_TAGLIB__SHORT_NAME:
				setShortName(SHORT_NAME_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public boolean eIsSet(int featureID)
    {
		switch (featureID) {
			case FaceletTaglibPackage.FACELET_TAGLIB__LIBRARY_CLASS:
				return libraryClass != null;
			case FaceletTaglibPackage.FACELET_TAGLIB__NAMESPACE:
				return namespace != null;
			case FaceletTaglibPackage.FACELET_TAGLIB__COMPOSITE_LIBRARY_NAME:
				return compositeLibraryName != null;
			case FaceletTaglibPackage.FACELET_TAGLIB__GROUP:
				return group != null && !group.isEmpty();
			case FaceletTaglibPackage.FACELET_TAGLIB__TAG:
				return !getTag().isEmpty();
			case FaceletTaglibPackage.FACELET_TAGLIB__FUNCTION:
				return !getFunction().isEmpty();
			case FaceletTaglibPackage.FACELET_TAGLIB__TAGLIB_EXTENSION:
				return taglibExtension != null && !taglibExtension.isEmpty();
			case FaceletTaglibPackage.FACELET_TAGLIB__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case FaceletTaglibPackage.FACELET_TAGLIB__VERSION:
				return isSetVersion();
			case FaceletTaglibPackage.FACELET_TAGLIB__NAMESPACE_URI:
				return NAMESPACE_URI_EDEFAULT == null ? getNamespaceUri() != null : !NAMESPACE_URI_EDEFAULT.equals(getNamespaceUri());
			case FaceletTaglibPackage.FACELET_TAGLIB__SHORT_NAME:
				return SHORT_NAME_EDEFAULT == null ? shortName != null : !SHORT_NAME_EDEFAULT.equals(shortName);
		}
		return super.eIsSet(featureID);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public String toString()
    {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (group: "); //$NON-NLS-1$
		result.append(group);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(", version: "); //$NON-NLS-1$
		if (versionESet) result.append(version); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", shortName: "); //$NON-NLS-1$
		result.append(shortName);
		result.append(')');
		return result.toString();
	}

} //FaceletTaglibImpl
