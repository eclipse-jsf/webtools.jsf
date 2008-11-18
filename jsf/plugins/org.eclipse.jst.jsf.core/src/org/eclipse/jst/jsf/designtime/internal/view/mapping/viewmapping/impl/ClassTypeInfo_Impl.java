/**
 * <copyright>
 * </copyright>
 *
 * $Id: ClassTypeInfo_Impl.java,v 1.2 2008/11/18 22:23:57 gkessler Exp $
 */
package org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

import org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ClassTypeInfo_;
import org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentMappingPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Class Type Info </b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.ClassTypeInfo_Impl#getClassName <em>Class Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.ClassTypeInfo_Impl#getSuperClasses <em>Super Classes</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.ClassTypeInfo_Impl#getInterfaces <em>Interfaces</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ClassTypeInfo_Impl extends EObjectImpl implements ClassTypeInfo_
{
    /**
     * The default value of the '{@link #getClassName() <em>Class Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getClassName()
     * @generated
     * @ordered
     */
    protected static final String CLASS_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getClassName() <em>Class Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getClassName()
     * @generated
     * @ordered
     */
    protected String className = CLASS_NAME_EDEFAULT;

    /**
     * The cached value of the '{@link #getSuperClasses() <em>Super Classes</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSuperClasses()
     * @generated
     * @ordered
     */
    protected EList<String> superClasses;

    /**
     * The cached value of the '{@link #getInterfaces() <em>Interfaces</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInterfaces()
     * @generated
     * @ordered
     */
    protected EList<String> interfaces;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ClassTypeInfo_Impl()
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
        return ComponentMappingPackage.Literals.CLASS_TYPE_INFO_;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getClassName()
    {
        return className;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setClassName(String newClassName)
    {
        String oldClassName = className;
        className = newClassName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentMappingPackage.CLASS_TYPE_INFO___CLASS_NAME, oldClassName, className));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getSuperClasses()
    {
        if (superClasses == null)
        {
            superClasses = new EDataTypeUniqueEList<String>(String.class, this, ComponentMappingPackage.CLASS_TYPE_INFO___SUPER_CLASSES);
        }
        return superClasses;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getInterfaces()
    {
        if (interfaces == null)
        {
            interfaces = new EDataTypeUniqueEList<String>(String.class, this, ComponentMappingPackage.CLASS_TYPE_INFO___INTERFACES);
        }
        return interfaces;
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
            case ComponentMappingPackage.CLASS_TYPE_INFO___CLASS_NAME:
                return getClassName();
            case ComponentMappingPackage.CLASS_TYPE_INFO___SUPER_CLASSES:
                return getSuperClasses();
            case ComponentMappingPackage.CLASS_TYPE_INFO___INTERFACES:
                return getInterfaces();
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
            case ComponentMappingPackage.CLASS_TYPE_INFO___CLASS_NAME:
                setClassName((String)newValue);
                return;
            case ComponentMappingPackage.CLASS_TYPE_INFO___SUPER_CLASSES:
                getSuperClasses().clear();
                getSuperClasses().addAll((Collection<? extends String>)newValue);
                return;
            case ComponentMappingPackage.CLASS_TYPE_INFO___INTERFACES:
                getInterfaces().clear();
                getInterfaces().addAll((Collection<? extends String>)newValue);
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
            case ComponentMappingPackage.CLASS_TYPE_INFO___CLASS_NAME:
                setClassName(CLASS_NAME_EDEFAULT);
                return;
            case ComponentMappingPackage.CLASS_TYPE_INFO___SUPER_CLASSES:
                getSuperClasses().clear();
                return;
            case ComponentMappingPackage.CLASS_TYPE_INFO___INTERFACES:
                getInterfaces().clear();
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
            case ComponentMappingPackage.CLASS_TYPE_INFO___CLASS_NAME:
                return CLASS_NAME_EDEFAULT == null ? className != null : !CLASS_NAME_EDEFAULT.equals(className);
            case ComponentMappingPackage.CLASS_TYPE_INFO___SUPER_CLASSES:
                return superClasses != null && !superClasses.isEmpty();
            case ComponentMappingPackage.CLASS_TYPE_INFO___INTERFACES:
                return interfaces != null && !interfaces.isEmpty();
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
        result.append(" (className: "); //$NON-NLS-1$
        result.append(className);
        result.append(", superClasses: "); //$NON-NLS-1$
        result.append(superClasses);
        result.append(", interfaces: "); //$NON-NLS-1$
        result.append(interfaces);
        result.append(')');
        return result.toString();
    }

} //ClassTypeInfo_Impl
