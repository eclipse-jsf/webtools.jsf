/**
 * <copyright>
 * </copyright>
 *
 * $Id: FaceletTaglibTagAttributeImpl.java,v 1.1 2010/03/18 06:24:38 cbateman Exp $
 */
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibCanonicalName;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FullyQualifiedClass;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.GenericBoolean;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.GenericBooleanBase;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.IdentifiableStringValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Facelet Taglib Tag Attribute</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagAttributeImpl#getNameElement <em>Name Element</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagAttributeImpl#getRequiredElement <em>Required Element</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagAttributeImpl#getTypeElement <em>Type Element</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagAttributeImpl#getMethodSignatureElement <em>Method Signature Element</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagAttributeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagAttributeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagAttributeImpl#isRequired <em>Required</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagAttributeImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagAttributeImpl#getMethodSignature <em>Method Signature</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FaceletTaglibTagAttributeImpl extends UserVisibleTaglibObjectImpl implements FaceletTaglibTagAttribute
{
    /**
     * The cached value of the '{@link #getNameElement() <em>Name Element</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNameElement()
     * @generated
     * @ordered
     */
    protected FaceletTaglibCanonicalName nameElement;

    /**
     * The cached value of the '{@link #getRequiredElement() <em>Required Element</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRequiredElement()
     * @generated
     * @ordered
     */
    protected GenericBoolean requiredElement;

    /**
     * The cached value of the '{@link #getTypeElement() <em>Type Element</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTypeElement()
     * @generated
     * @ordered
     */
    protected FullyQualifiedClass typeElement;

    /**
     * The cached value of the '{@link #getMethodSignatureElement() <em>Method Signature Element</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMethodSignatureElement()
     * @generated
     * @ordered
     */
    protected IdentifiableStringValue methodSignatureElement;

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
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The default value of the '{@link #isRequired() <em>Required</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isRequired()
     * @generated
     * @ordered
     */
    protected static final boolean REQUIRED_EDEFAULT = false;

    /**
     * The default value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected static final String TYPE_EDEFAULT = null;

    /**
     * The default value of the '{@link #getMethodSignature() <em>Method Signature</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMethodSignature()
     * @generated
     * @ordered
     */
    protected static final String METHOD_SIGNATURE_EDEFAULT = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected FaceletTaglibTagAttributeImpl()
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
        return FaceletTaglibPackage.Literals.FACELET_TAGLIB_TAG_ATTRIBUTE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FaceletTaglibCanonicalName getNameElement()
    {
        return nameElement;
    }

    /**
     * <!-- begin-user-doc -->
     * @param newNameElement 
     * @param msgs 
     * @return the notification chain
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetNameElement(FaceletTaglibCanonicalName newNameElement, NotificationChain msgs)
    {
        FaceletTaglibCanonicalName oldNameElement = nameElement;
        nameElement = newNameElement;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__NAME_ELEMENT, oldNameElement, newNameElement);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNameElement(FaceletTaglibCanonicalName newNameElement)
    {
        if (newNameElement != nameElement)
        {
            NotificationChain msgs = null;
            if (nameElement != null)
                msgs = ((InternalEObject)nameElement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__NAME_ELEMENT, null, msgs);
            if (newNameElement != null)
                msgs = ((InternalEObject)newNameElement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__NAME_ELEMENT, null, msgs);
            msgs = basicSetNameElement(newNameElement, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__NAME_ELEMENT, newNameElement, newNameElement));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GenericBoolean getRequiredElement()
    {
        return requiredElement;
    }

    /**
     * <!-- begin-user-doc -->
     * @param newRequiredElement 
     * @param msgs 
     * @return 
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetRequiredElement(GenericBoolean newRequiredElement, NotificationChain msgs)
    {
        GenericBoolean oldRequiredElement = requiredElement;
        requiredElement = newRequiredElement;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__REQUIRED_ELEMENT, oldRequiredElement, newRequiredElement);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRequiredElement(GenericBoolean newRequiredElement)
    {
        if (newRequiredElement != requiredElement)
        {
            NotificationChain msgs = null;
            if (requiredElement != null)
                msgs = ((InternalEObject)requiredElement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__REQUIRED_ELEMENT, null, msgs);
            if (newRequiredElement != null)
                msgs = ((InternalEObject)newRequiredElement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__REQUIRED_ELEMENT, null, msgs);
            msgs = basicSetRequiredElement(newRequiredElement, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__REQUIRED_ELEMENT, newRequiredElement, newRequiredElement));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FullyQualifiedClass getTypeElement()
    {
        return typeElement;
    }

    /**
     * <!-- begin-user-doc -->
     * @param newTypeElement 
     * @param msgs 
     * @return 
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTypeElement(FullyQualifiedClass newTypeElement, NotificationChain msgs)
    {
        FullyQualifiedClass oldTypeElement = typeElement;
        typeElement = newTypeElement;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__TYPE_ELEMENT, oldTypeElement, newTypeElement);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTypeElement(FullyQualifiedClass newTypeElement)
    {
        if (newTypeElement != typeElement)
        {
            NotificationChain msgs = null;
            if (typeElement != null)
                msgs = ((InternalEObject)typeElement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__TYPE_ELEMENT, null, msgs);
            if (newTypeElement != null)
                msgs = ((InternalEObject)newTypeElement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__TYPE_ELEMENT, null, msgs);
            msgs = basicSetTypeElement(newTypeElement, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__TYPE_ELEMENT, newTypeElement, newTypeElement));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IdentifiableStringValue getMethodSignatureElement()
    {
        return methodSignatureElement;
    }

    /**
     * <!-- begin-user-doc -->
     * @param newMethodSignatureElement 
     * @param msgs 
     * @return 
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetMethodSignatureElement(IdentifiableStringValue newMethodSignatureElement, NotificationChain msgs)
    {
        IdentifiableStringValue oldMethodSignatureElement = methodSignatureElement;
        methodSignatureElement = newMethodSignatureElement;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__METHOD_SIGNATURE_ELEMENT, oldMethodSignatureElement, newMethodSignatureElement);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMethodSignatureElement(IdentifiableStringValue newMethodSignatureElement)
    {
        if (newMethodSignatureElement != methodSignatureElement)
        {
            NotificationChain msgs = null;
            if (methodSignatureElement != null)
                msgs = ((InternalEObject)methodSignatureElement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__METHOD_SIGNATURE_ELEMENT, null, msgs);
            if (newMethodSignatureElement != null)
                msgs = ((InternalEObject)newMethodSignatureElement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__METHOD_SIGNATURE_ELEMENT, null, msgs);
            msgs = basicSetMethodSignatureElement(newMethodSignatureElement, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__METHOD_SIGNATURE_ELEMENT, newMethodSignatureElement, newMethodSignatureElement));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public String getName()
    {
        return (String) Util.getSimplifiedNestedField(this, FaceletTaglibPackage.Literals.FACELET_TAGLIB_TAG_ATTRIBUTE__NAME_ELEMENT, FaceletTaglibPackage.Literals.FACELET_TAGLIB_CANONICAL_NAME__VALUE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void setName(String newName)
    {
        Util.setSimplifiedNestedField(
                this,
                FaceletTaglibPackage.Literals.FACELET_TAGLIB_TAG_ATTRIBUTE__NAME_ELEMENT,
                FaceletTaglibPackage.Literals.FACELET_TAGLIB_CANONICAL_NAME__VALUE,
                newName);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public boolean isRequired()
    {
        String requiredStr = (String) Util.getSimplifiedNestedField(this, FaceletTaglibPackage.Literals.FACELET_TAGLIB_TAG_ATTRIBUTE__REQUIRED_ELEMENT, FaceletTaglibPackage.Literals.IDENTIFIABLE_STRING_VALUE__VALUE);
        GenericBooleanBase enumValue = GenericBooleanBase.get(requiredStr);
        if (enumValue == GenericBooleanBase.TRUE || enumValue == GenericBooleanBase.YES)
        {
            return true;
        }
        return false;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void setRequired(boolean newRequired)
    {
        String newValue = newRequired ? GenericBooleanBase.TRUE.toString() : GenericBooleanBase.FALSE.toString();
        Util.setSimplifiedNestedField(this, FaceletTaglibPackage.Literals.FACELET_TAGLIB_TAG_ATTRIBUTE__REQUIRED_ELEMENT, FaceletTaglibPackage.Literals.IDENTIFIABLE_STRING_VALUE__VALUE, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public String getType()
    {
        return (String) Util.getSimplifiedNestedField(this, FaceletTaglibPackage.Literals.FACELET_TAGLIB_TAG_ATTRIBUTE__TYPE_ELEMENT, FaceletTaglibPackage.Literals.IDENTIFIABLE_STRING_VALUE__VALUE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void setType(String newType)
    {
        Util.setSimplifiedNestedField(this, FaceletTaglibPackage.Literals.FACELET_TAGLIB_TAG_ATTRIBUTE__TYPE_ELEMENT, FaceletTaglibPackage.Literals.IDENTIFIABLE_STRING_VALUE__VALUE, newType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public String getMethodSignature()
    {
        return (String) Util.getSimplifiedNestedField(this, FaceletTaglibPackage.Literals.FACELET_TAGLIB_TAG_ATTRIBUTE__METHOD_SIGNATURE_ELEMENT, FaceletTaglibPackage.Literals.IDENTIFIABLE_STRING_VALUE__VALUE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void setMethodSignature(String newMethodSignature)
    {
        Util.setSimplifiedNestedField(this, FaceletTaglibPackage.Literals.FACELET_TAGLIB_TAG_ATTRIBUTE__METHOD_SIGNATURE_ELEMENT, FaceletTaglibPackage.Literals.IDENTIFIABLE_STRING_VALUE__VALUE, newMethodSignature);
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
            eNotify(new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__ID, oldId, id));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
    {
        switch (featureID)
        {
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__NAME_ELEMENT:
                return basicSetNameElement(null, msgs);
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__REQUIRED_ELEMENT:
                return basicSetRequiredElement(null, msgs);
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__TYPE_ELEMENT:
                return basicSetTypeElement(null, msgs);
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__METHOD_SIGNATURE_ELEMENT:
                return basicSetMethodSignatureElement(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("boxing")
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType)
    {
        switch (featureID)
        {
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__NAME_ELEMENT:
                return getNameElement();
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__REQUIRED_ELEMENT:
                return getRequiredElement();
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__TYPE_ELEMENT:
                return getTypeElement();
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__METHOD_SIGNATURE_ELEMENT:
                return getMethodSignatureElement();
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__ID:
                return getId();
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__NAME:
                return getName();
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__REQUIRED:
                return isRequired();
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__TYPE:
                return getType();
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__METHOD_SIGNATURE:
                return getMethodSignature();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings({ "unchecked", "boxing" })
    @Override
    public void eSet(int featureID, Object newValue)
    {
        switch (featureID)
        {
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__NAME_ELEMENT:
                setNameElement((FaceletTaglibCanonicalName)newValue);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__REQUIRED_ELEMENT:
                setRequiredElement((GenericBoolean)newValue);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__TYPE_ELEMENT:
                setTypeElement((FullyQualifiedClass)newValue);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__METHOD_SIGNATURE_ELEMENT:
                setMethodSignatureElement((IdentifiableStringValue)newValue);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__ID:
                setId((String)newValue);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__NAME:
                setName((String)newValue);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__REQUIRED:
                setRequired((Boolean)newValue);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__TYPE:
                setType((String)newValue);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__METHOD_SIGNATURE:
                setMethodSignature((String)newValue);
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
        switch (featureID)
        {
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__NAME_ELEMENT:
                setNameElement((FaceletTaglibCanonicalName)null);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__REQUIRED_ELEMENT:
                setRequiredElement((GenericBoolean)null);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__TYPE_ELEMENT:
                setTypeElement((FullyQualifiedClass)null);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__METHOD_SIGNATURE_ELEMENT:
                setMethodSignatureElement((IdentifiableStringValue)null);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__ID:
                setId(ID_EDEFAULT);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__NAME:
                setName(NAME_EDEFAULT);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__REQUIRED:
                setRequired(REQUIRED_EDEFAULT);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__TYPE:
                setType(TYPE_EDEFAULT);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__METHOD_SIGNATURE:
                setMethodSignature(METHOD_SIGNATURE_EDEFAULT);
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
        switch (featureID)
        {
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__NAME_ELEMENT:
                return nameElement != null;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__REQUIRED_ELEMENT:
                return requiredElement != null;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__TYPE_ELEMENT:
                return typeElement != null;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__METHOD_SIGNATURE_ELEMENT:
                return methodSignatureElement != null;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__NAME:
                return NAME_EDEFAULT == null ? getName() != null : !NAME_EDEFAULT.equals(getName());
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__REQUIRED:
                return isRequired() != REQUIRED_EDEFAULT;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__TYPE:
                return TYPE_EDEFAULT == null ? getType() != null : !TYPE_EDEFAULT.equals(getType());
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_ATTRIBUTE__METHOD_SIGNATURE:
                return METHOD_SIGNATURE_EDEFAULT == null ? getMethodSignature() != null : !METHOD_SIGNATURE_EDEFAULT.equals(getMethodSignature());
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
        result.append(" (id: "); //$NON-NLS-1$
        result.append(id);
        result.append(')');
        return result.toString();
    }

} //FaceletTaglibTagAttributeImpl
