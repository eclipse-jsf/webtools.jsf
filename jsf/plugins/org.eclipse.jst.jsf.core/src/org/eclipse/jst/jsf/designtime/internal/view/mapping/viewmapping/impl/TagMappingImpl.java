/**
 * <copyright>
 * </copyright>
 *
 * $Id: TagMappingImpl.java,v 1.2 2008/11/18 22:23:57 gkessler Exp $
 */
package org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentMappingPackage;
import org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.TagMapping;
import org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.TagToViewObjectMapping;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tag Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.TagMappingImpl#getVersionedTagToViewMappings <em>Versioned Tag To View Mappings</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.TagMappingImpl#getCustomConversionFactoryId <em>Custom Conversion Factory Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.TagMappingImpl#getBeanMappedProperties <em>Bean Mapped Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TagMappingImpl extends EObjectImpl implements TagMapping
{
    /**
     * The cached value of the '{@link #getVersionedTagToViewMappings() <em>Versioned Tag To View Mappings</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVersionedTagToViewMappings()
     * @generated
     * @ordered
     */
    protected EList<TagToViewObjectMapping> versionedTagToViewMappings;

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
     * The cached value of the '{@link #getBeanMappedProperties() <em>Bean Mapped Properties</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBeanMappedProperties()
     * @generated
     * @ordered
     */
    protected EList<String> beanMappedProperties;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TagMappingImpl()
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
        return ComponentMappingPackage.Literals.TAG_MAPPING;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<TagToViewObjectMapping> getVersionedTagToViewMappings()
    {
        if (versionedTagToViewMappings == null)
        {
            versionedTagToViewMappings = new EObjectContainmentEList<TagToViewObjectMapping>(TagToViewObjectMapping.class, this, ComponentMappingPackage.TAG_MAPPING__VERSIONED_TAG_TO_VIEW_MAPPINGS);
        }
        return versionedTagToViewMappings;
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
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentMappingPackage.TAG_MAPPING__CUSTOM_CONVERSION_FACTORY_ID, oldCustomConversionFactoryId, customConversionFactoryId));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getBeanMappedProperties()
    {
        if (beanMappedProperties == null)
        {
            beanMappedProperties = new EDataTypeUniqueEList<String>(String.class, this, ComponentMappingPackage.TAG_MAPPING__BEAN_MAPPED_PROPERTIES);
        }
        return beanMappedProperties;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void findBestMapping(String jsfVersion, String libVersion)
    {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
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
            case ComponentMappingPackage.TAG_MAPPING__VERSIONED_TAG_TO_VIEW_MAPPINGS:
                return ((InternalEList<?>)getVersionedTagToViewMappings()).basicRemove(otherEnd, msgs);
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
        switch (featureID)
        {
            case ComponentMappingPackage.TAG_MAPPING__VERSIONED_TAG_TO_VIEW_MAPPINGS:
                return getVersionedTagToViewMappings();
            case ComponentMappingPackage.TAG_MAPPING__CUSTOM_CONVERSION_FACTORY_ID:
                return getCustomConversionFactoryId();
            case ComponentMappingPackage.TAG_MAPPING__BEAN_MAPPED_PROPERTIES:
                return getBeanMappedProperties();
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
        switch (featureID)
        {
            case ComponentMappingPackage.TAG_MAPPING__VERSIONED_TAG_TO_VIEW_MAPPINGS:
                getVersionedTagToViewMappings().clear();
                getVersionedTagToViewMappings().addAll((Collection<? extends TagToViewObjectMapping>)newValue);
                return;
            case ComponentMappingPackage.TAG_MAPPING__CUSTOM_CONVERSION_FACTORY_ID:
                setCustomConversionFactoryId((String)newValue);
                return;
            case ComponentMappingPackage.TAG_MAPPING__BEAN_MAPPED_PROPERTIES:
                getBeanMappedProperties().clear();
                getBeanMappedProperties().addAll((Collection<? extends String>)newValue);
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
            case ComponentMappingPackage.TAG_MAPPING__VERSIONED_TAG_TO_VIEW_MAPPINGS:
                getVersionedTagToViewMappings().clear();
                return;
            case ComponentMappingPackage.TAG_MAPPING__CUSTOM_CONVERSION_FACTORY_ID:
                setCustomConversionFactoryId(CUSTOM_CONVERSION_FACTORY_ID_EDEFAULT);
                return;
            case ComponentMappingPackage.TAG_MAPPING__BEAN_MAPPED_PROPERTIES:
                getBeanMappedProperties().clear();
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
            case ComponentMappingPackage.TAG_MAPPING__VERSIONED_TAG_TO_VIEW_MAPPINGS:
                return versionedTagToViewMappings != null && !versionedTagToViewMappings.isEmpty();
            case ComponentMappingPackage.TAG_MAPPING__CUSTOM_CONVERSION_FACTORY_ID:
                return CUSTOM_CONVERSION_FACTORY_ID_EDEFAULT == null ? customConversionFactoryId != null : !CUSTOM_CONVERSION_FACTORY_ID_EDEFAULT.equals(customConversionFactoryId);
            case ComponentMappingPackage.TAG_MAPPING__BEAN_MAPPED_PROPERTIES:
                return beanMappedProperties != null && !beanMappedProperties.isEmpty();
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
        result.append(" (customConversionFactoryId: "); //$NON-NLS-1$
        result.append(customConversionFactoryId);
        result.append(", beanMappedProperties: "); //$NON-NLS-1$
        result.append(beanMappedProperties);
        result.append(')');
        return result.toString();
    }

} //TagMappingImpl
