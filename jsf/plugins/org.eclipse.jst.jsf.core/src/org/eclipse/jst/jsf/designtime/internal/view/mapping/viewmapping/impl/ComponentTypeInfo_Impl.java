/**
 * <copyright>
 * </copyright>
 *
 * $Id: ComponentTypeInfo_Impl.java,v 1.2 2008/11/18 22:23:57 gkessler Exp $
 */
package org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentMappingPackage;
import org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentTypeInfo_;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component Type Info </b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.ComponentTypeInfo_Impl#getComponentType <em>Component Type</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.ComponentTypeInfo_Impl#getComponentFamily <em>Component Family</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.ComponentTypeInfo_Impl#getRenderType <em>Render Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComponentTypeInfo_Impl extends ClassTypeInfo_Impl implements ComponentTypeInfo_
{
    /**
     * The default value of the '{@link #getComponentType() <em>Component Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getComponentType()
     * @generated
     * @ordered
     */
    protected static final String COMPONENT_TYPE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getComponentType() <em>Component Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getComponentType()
     * @generated
     * @ordered
     */
    protected String componentType = COMPONENT_TYPE_EDEFAULT;

    /**
     * The default value of the '{@link #getComponentFamily() <em>Component Family</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getComponentFamily()
     * @generated
     * @ordered
     */
    protected static final String COMPONENT_FAMILY_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getComponentFamily() <em>Component Family</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getComponentFamily()
     * @generated
     * @ordered
     */
    protected String componentFamily = COMPONENT_FAMILY_EDEFAULT;

    /**
     * The default value of the '{@link #getRenderType() <em>Render Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRenderType()
     * @generated
     * @ordered
     */
    protected static final String RENDER_TYPE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRenderType() <em>Render Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRenderType()
     * @generated
     * @ordered
     */
    protected String renderType = RENDER_TYPE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ComponentTypeInfo_Impl()
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
        return ComponentMappingPackage.Literals.COMPONENT_TYPE_INFO_;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getComponentType()
    {
        return componentType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setComponentType(String newComponentType)
    {
        String oldComponentType = componentType;
        componentType = newComponentType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentMappingPackage.COMPONENT_TYPE_INFO___COMPONENT_TYPE, oldComponentType, componentType));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getComponentFamily()
    {
        return componentFamily;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setComponentFamily(String newComponentFamily)
    {
        String oldComponentFamily = componentFamily;
        componentFamily = newComponentFamily;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentMappingPackage.COMPONENT_TYPE_INFO___COMPONENT_FAMILY, oldComponentFamily, componentFamily));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getRenderType()
    {
        return renderType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRenderType(String newRenderType)
    {
        String oldRenderType = renderType;
        renderType = newRenderType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentMappingPackage.COMPONENT_TYPE_INFO___RENDER_TYPE, oldRenderType, renderType));
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
            case ComponentMappingPackage.COMPONENT_TYPE_INFO___COMPONENT_TYPE:
                return getComponentType();
            case ComponentMappingPackage.COMPONENT_TYPE_INFO___COMPONENT_FAMILY:
                return getComponentFamily();
            case ComponentMappingPackage.COMPONENT_TYPE_INFO___RENDER_TYPE:
                return getRenderType();
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
            case ComponentMappingPackage.COMPONENT_TYPE_INFO___COMPONENT_TYPE:
                setComponentType((String)newValue);
                return;
            case ComponentMappingPackage.COMPONENT_TYPE_INFO___COMPONENT_FAMILY:
                setComponentFamily((String)newValue);
                return;
            case ComponentMappingPackage.COMPONENT_TYPE_INFO___RENDER_TYPE:
                setRenderType((String)newValue);
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
            case ComponentMappingPackage.COMPONENT_TYPE_INFO___COMPONENT_TYPE:
                setComponentType(COMPONENT_TYPE_EDEFAULT);
                return;
            case ComponentMappingPackage.COMPONENT_TYPE_INFO___COMPONENT_FAMILY:
                setComponentFamily(COMPONENT_FAMILY_EDEFAULT);
                return;
            case ComponentMappingPackage.COMPONENT_TYPE_INFO___RENDER_TYPE:
                setRenderType(RENDER_TYPE_EDEFAULT);
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
            case ComponentMappingPackage.COMPONENT_TYPE_INFO___COMPONENT_TYPE:
                return COMPONENT_TYPE_EDEFAULT == null ? componentType != null : !COMPONENT_TYPE_EDEFAULT.equals(componentType);
            case ComponentMappingPackage.COMPONENT_TYPE_INFO___COMPONENT_FAMILY:
                return COMPONENT_FAMILY_EDEFAULT == null ? componentFamily != null : !COMPONENT_FAMILY_EDEFAULT.equals(componentFamily);
            case ComponentMappingPackage.COMPONENT_TYPE_INFO___RENDER_TYPE:
                return RENDER_TYPE_EDEFAULT == null ? renderType != null : !RENDER_TYPE_EDEFAULT.equals(renderType);
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
        result.append(" (componentType: "); //$NON-NLS-1$
        result.append(componentType);
        result.append(", componentFamily: "); //$NON-NLS-1$
        result.append(componentFamily);
        result.append(", renderType: "); //$NON-NLS-1$
        result.append(renderType);
        result.append(')');
        return result.toString();
    }

} //ComponentTypeInfo_Impl
