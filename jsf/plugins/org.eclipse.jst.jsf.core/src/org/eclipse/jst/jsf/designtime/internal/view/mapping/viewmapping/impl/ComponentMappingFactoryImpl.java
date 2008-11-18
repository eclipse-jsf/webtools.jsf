/**
 * <copyright>
 * </copyright>
 *
 * $Id: ComponentMappingFactoryImpl.java,v 1.2 2008/11/18 22:23:57 gkessler Exp $
 */
package org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ComponentMappingFactoryImpl extends EFactoryImpl implements ComponentMappingFactory
{
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * @return the factory
     * <!-- end-user-doc -->
     * @generated
     */
    public static ComponentMappingFactory init()
    {
        try
        {
            ComponentMappingFactory theComponentMappingFactory = (ComponentMappingFactory)EPackage.Registry.INSTANCE.getEFactory("http://org.eclipse.jst.jsf.core/componentMapping.ecore");  //$NON-NLS-1$
            if (theComponentMappingFactory != null)
            {
                return theComponentMappingFactory;
            }
        }
        catch (Exception exception)
        {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new ComponentMappingFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ComponentMappingFactoryImpl()
    {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass)
    {
        switch (eClass.getClassifierID())
        {
            case ComponentMappingPackage.TAG_MAPPING: return createTagMapping();
            case ComponentMappingPackage.TAG_TO_VIEW_OBJECT_MAPPING: return createTagToViewObjectMapping();
            case ComponentMappingPackage.CLASS_TYPE_INFO_: return createClassTypeInfo_();
            case ComponentMappingPackage.COMPONENT_TYPE_INFO_: return createComponentTypeInfo_();
            case ComponentMappingPackage.CONVERTER_TYPE_INFO_: return createConverterTypeInfo_();
            case ComponentMappingPackage.VALIDATOR_TYPE_INFO_: return createValidatorTypeInfo_();
            case ComponentMappingPackage.ATTRIBUTE_TO_PROPERTY_MAPPING: return createAttributeToPropertyMapping();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TagMapping createTagMapping()
    {
        TagMappingImpl tagMapping = new TagMappingImpl();
        return tagMapping;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TagToViewObjectMapping createTagToViewObjectMapping()
    {
        TagToViewObjectMappingImpl tagToViewObjectMapping = new TagToViewObjectMappingImpl();
        return tagToViewObjectMapping;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ClassTypeInfo_ createClassTypeInfo_()
    {
        ClassTypeInfo_Impl classTypeInfo_ = new ClassTypeInfo_Impl();
        return classTypeInfo_;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ComponentTypeInfo_ createComponentTypeInfo_()
    {
        ComponentTypeInfo_Impl componentTypeInfo_ = new ComponentTypeInfo_Impl();
        return componentTypeInfo_;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ConverterTypeInfo_ createConverterTypeInfo_()
    {
        ConverterTypeInfo_Impl converterTypeInfo_ = new ConverterTypeInfo_Impl();
        return converterTypeInfo_;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ValidatorTypeInfo_ createValidatorTypeInfo_()
    {
        ValidatorTypeInfo_Impl validatorTypeInfo_ = new ValidatorTypeInfo_Impl();
        return validatorTypeInfo_;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AttributeToPropertyMapping createAttributeToPropertyMapping()
    {
        AttributeToPropertyMappingImpl attributeToPropertyMapping = new AttributeToPropertyMappingImpl();
        return attributeToPropertyMapping;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ComponentMappingPackage getComponentMappingPackage()
    {
        return (ComponentMappingPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * @return  the package
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static ComponentMappingPackage getPackage()
    {
        return ComponentMappingPackage.eINSTANCE;
    }

} //ComponentMappingFactoryImpl
