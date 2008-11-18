/**
 * <copyright>
 * </copyright>
 *
 * $Id: AttributeToPropertyMappingImpl.java,v 1.2 2008/11/18 22:23:57 gkessler Exp $
 */
package org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.AttributeToPropertyMapping;
import org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentMappingPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Attribute To Property Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.AttributeToPropertyMappingImpl#getPropertyName <em>Property Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.AttributeToPropertyMappingImpl#isElAllowed <em>El Allowed</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.AttributeToPropertyMappingImpl#getCustomConversionFactoryId <em>Custom Conversion Factory Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AttributeToPropertyMappingImpl extends EObjectImpl implements AttributeToPropertyMapping
{
    /**
     * The default value of the '{@link #getPropertyName() <em>Property Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPropertyName()
     * @generated
     * @ordered
     */
    protected static final String PROPERTY_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPropertyName() <em>Property Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPropertyName()
     * @generated
     * @ordered
     */
    protected String propertyName = PROPERTY_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #isElAllowed() <em>El Allowed</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isElAllowed()
     * @generated
     * @ordered
     */
    protected static final boolean EL_ALLOWED_EDEFAULT = true;

    /**
     * The cached value of the '{@link #isElAllowed() <em>El Allowed</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isElAllowed()
     * @generated
     * @ordered
     */
    protected boolean elAllowed = EL_ALLOWED_EDEFAULT;

    /**
     * The default value of the '{@link #getCustomConversionFactoryId() <em>Custom Conversion Factory Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCustomConversionFactoryId()
     * @generated
     * @ordered
     */
    protected static final String CUSTOM_CONVERSION_FACTORY_ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getCustomConversionFactoryId() <em>Custom Conversion Factory Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCustomConversionFactoryId()
     * @generated
     * @ordered
     */
    protected String customConversionFactoryId = CUSTOM_CONVERSION_FACTORY_ID_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AttributeToPropertyMappingImpl()
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
        return ComponentMappingPackage.Literals.ATTRIBUTE_TO_PROPERTY_MAPPING;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getPropertyName()
    {
        return propertyName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPropertyName(String newPropertyName)
    {
        String oldPropertyName = propertyName;
        propertyName = newPropertyName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentMappingPackage.ATTRIBUTE_TO_PROPERTY_MAPPING__PROPERTY_NAME, oldPropertyName, propertyName));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isElAllowed()
    {
        return elAllowed;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setElAllowed(boolean newElAllowed)
    {
        boolean oldElAllowed = elAllowed;
        elAllowed = newElAllowed;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentMappingPackage.ATTRIBUTE_TO_PROPERTY_MAPPING__EL_ALLOWED, oldElAllowed, elAllowed));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getCustomConversionFactoryId()
    {
        return customConversionFactoryId;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCustomConversionFactoryId(String newCustomConversionFactoryId)
    {
        String oldCustomConversionFactoryId = customConversionFactoryId;
        customConversionFactoryId = newCustomConversionFactoryId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentMappingPackage.ATTRIBUTE_TO_PROPERTY_MAPPING__CUSTOM_CONVERSION_FACTORY_ID, oldCustomConversionFactoryId, customConversionFactoryId));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType)
    {
        switch (featureID)
        {
            case ComponentMappingPackage.ATTRIBUTE_TO_PROPERTY_MAPPING__PROPERTY_NAME:
                return getPropertyName();
            case ComponentMappingPackage.ATTRIBUTE_TO_PROPERTY_MAPPING__EL_ALLOWED:
                return isElAllowed() ? Boolean.TRUE : Boolean.FALSE;
            case ComponentMappingPackage.ATTRIBUTE_TO_PROPERTY_MAPPING__CUSTOM_CONVERSION_FACTORY_ID:
                return getCustomConversionFactoryId();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue)
    {
        switch (featureID)
        {
            case ComponentMappingPackage.ATTRIBUTE_TO_PROPERTY_MAPPING__PROPERTY_NAME:
                setPropertyName((String)newValue);
                return;
            case ComponentMappingPackage.ATTRIBUTE_TO_PROPERTY_MAPPING__EL_ALLOWED:
                setElAllowed(((Boolean)newValue).booleanValue());
                return;
            case ComponentMappingPackage.ATTRIBUTE_TO_PROPERTY_MAPPING__CUSTOM_CONVERSION_FACTORY_ID:
                setCustomConversionFactoryId((String)newValue);
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
            case ComponentMappingPackage.ATTRIBUTE_TO_PROPERTY_MAPPING__PROPERTY_NAME:
                setPropertyName(PROPERTY_NAME_EDEFAULT);
                return;
            case ComponentMappingPackage.ATTRIBUTE_TO_PROPERTY_MAPPING__EL_ALLOWED:
                setElAllowed(EL_ALLOWED_EDEFAULT);
                return;
            case ComponentMappingPackage.ATTRIBUTE_TO_PROPERTY_MAPPING__CUSTOM_CONVERSION_FACTORY_ID:
                setCustomConversionFactoryId(CUSTOM_CONVERSION_FACTORY_ID_EDEFAULT);
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
            case ComponentMappingPackage.ATTRIBUTE_TO_PROPERTY_MAPPING__PROPERTY_NAME:
                return PROPERTY_NAME_EDEFAULT == null ? propertyName != null : !PROPERTY_NAME_EDEFAULT.equals(propertyName);
            case ComponentMappingPackage.ATTRIBUTE_TO_PROPERTY_MAPPING__EL_ALLOWED:
                return elAllowed != EL_ALLOWED_EDEFAULT;
            case ComponentMappingPackage.ATTRIBUTE_TO_PROPERTY_MAPPING__CUSTOM_CONVERSION_FACTORY_ID:
                return CUSTOM_CONVERSION_FACTORY_ID_EDEFAULT == null ? customConversionFactoryId != null : !CUSTOM_CONVERSION_FACTORY_ID_EDEFAULT.equals(customConversionFactoryId);
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
        result.append(" (propertyName: "); //$NON-NLS-1$
        result.append(propertyName);
        result.append(", elAllowed: "); //$NON-NLS-1$
        result.append(elAllowed);
        result.append(", customConversionFactoryId: "); //$NON-NLS-1$
        result.append(customConversionFactoryId);
        result.append(')');
        return result.toString();
    }

} //AttributeToPropertyMappingImpl
