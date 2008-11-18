/**
 * <copyright>
 * </copyright>
 *
 * $Id: ConverterTypeInfo_Impl.java,v 1.2 2008/11/18 22:23:57 gkessler Exp $
 */
package org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl;

import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentMappingPackage;
import org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ConverterTypeInfo_;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Converter Type Info </b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.ConverterTypeInfo_Impl#getConverterId <em>Converter Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.ConverterTypeInfo_Impl#getForClasses <em>For Classes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConverterTypeInfo_Impl extends ClassTypeInfo_Impl implements ConverterTypeInfo_
{
    /**
     * The default value of the '{@link #getConverterId() <em>Converter Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getConverterId()
     * @generated
     * @ordered
     */
    protected static final String CONVERTER_ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getConverterId() <em>Converter Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getConverterId()
     * @generated
     * @ordered
     */
    protected String converterId = CONVERTER_ID_EDEFAULT;

    /**
     * The cached value of the '{@link #getForClasses() <em>For Classes</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getForClasses()
     * @generated
     * @ordered
     */
    protected EList<String> forClasses;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ConverterTypeInfo_Impl()
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
        return ComponentMappingPackage.Literals.CONVERTER_TYPE_INFO_;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getConverterId()
    {
        return converterId;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setConverterId(String newConverterId)
    {
        String oldConverterId = converterId;
        converterId = newConverterId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentMappingPackage.CONVERTER_TYPE_INFO___CONVERTER_ID, oldConverterId, converterId));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getForClasses()
    {
        if (forClasses == null)
        {
            forClasses = new EDataTypeUniqueEList<String>(String.class, this, ComponentMappingPackage.CONVERTER_TYPE_INFO___FOR_CLASSES);
        }
        return forClasses;
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
            case ComponentMappingPackage.CONVERTER_TYPE_INFO___CONVERTER_ID:
                return getConverterId();
            case ComponentMappingPackage.CONVERTER_TYPE_INFO___FOR_CLASSES:
                return getForClasses();
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
            case ComponentMappingPackage.CONVERTER_TYPE_INFO___CONVERTER_ID:
                setConverterId((String)newValue);
                return;
            case ComponentMappingPackage.CONVERTER_TYPE_INFO___FOR_CLASSES:
                getForClasses().clear();
                getForClasses().addAll((Collection<? extends String>)newValue);
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
            case ComponentMappingPackage.CONVERTER_TYPE_INFO___CONVERTER_ID:
                setConverterId(CONVERTER_ID_EDEFAULT);
                return;
            case ComponentMappingPackage.CONVERTER_TYPE_INFO___FOR_CLASSES:
                getForClasses().clear();
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
            case ComponentMappingPackage.CONVERTER_TYPE_INFO___CONVERTER_ID:
                return CONVERTER_ID_EDEFAULT == null ? converterId != null : !CONVERTER_ID_EDEFAULT.equals(converterId);
            case ComponentMappingPackage.CONVERTER_TYPE_INFO___FOR_CLASSES:
                return forClasses != null && !forClasses.isEmpty();
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
        result.append(" (converterId: "); //$NON-NLS-1$
        result.append(converterId);
        result.append(", forClasses: "); //$NON-NLS-1$
        result.append(forClasses);
        result.append(')');
        return result.toString();
    }

} //ConverterTypeInfo_Impl
