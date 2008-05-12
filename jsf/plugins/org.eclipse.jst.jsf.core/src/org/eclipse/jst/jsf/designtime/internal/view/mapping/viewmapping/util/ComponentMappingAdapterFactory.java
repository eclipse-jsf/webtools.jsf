/**
 * <copyright>
 * </copyright>
 *
 * $Id: ComponentMappingAdapterFactory.java,v 1.1 2008/05/12 17:42:21 cbateman Exp $
 */
package org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentMappingPackage
 * @generated
 */
public class ComponentMappingAdapterFactory extends AdapterFactoryImpl
{
    /**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static ComponentMappingPackage modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ComponentMappingAdapterFactory()
    {
        if (modelPackage == null)
        {
            modelPackage = ComponentMappingPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc -->
     * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
     * <!-- end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object)
    {
        if (object == modelPackage)
        {
            return true;
        }
        if (object instanceof EObject)
        {
            return ((EObject)object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ComponentMappingSwitch<Adapter> modelSwitch =
        new ComponentMappingSwitch<Adapter>()
        {
            @Override
            public Adapter caseTagMapping(TagMapping object)
            {
                return createTagMappingAdapter();
            }
            @Override
            public Adapter caseTagToViewObjectMapping(TagToViewObjectMapping object)
            {
                return createTagToViewObjectMappingAdapter();
            }
            @Override
            public Adapter caseClassTypeInfo_(ClassTypeInfo_ object)
            {
                return createClassTypeInfo_Adapter();
            }
            @Override
            public Adapter caseComponentTypeInfo_(ComponentTypeInfo_ object)
            {
                return createComponentTypeInfo_Adapter();
            }
            @Override
            public Adapter caseConverterTypeInfo_(ConverterTypeInfo_ object)
            {
                return createConverterTypeInfo_Adapter();
            }
            @Override
            public Adapter caseValidatorTypeInfo_(ValidatorTypeInfo_ object)
            {
                return createValidatorTypeInfo_Adapter();
            }
            @Override
            public Adapter caseAttributeToPropertyMapping(AttributeToPropertyMapping object)
            {
                return createAttributeToPropertyMappingAdapter();
            }
            @Override
            public Adapter defaultCase(EObject object)
            {
                return createEObjectAdapter();
            }
        };

    /**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target)
    {
        return modelSwitch.doSwitch((EObject)target);
    }


    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.TagMapping <em>Tag Mapping</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.TagMapping
     * @generated
     */
    public Adapter createTagMappingAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.TagToViewObjectMapping <em>Tag To View Object Mapping</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.TagToViewObjectMapping
     * @generated
     */
    public Adapter createTagToViewObjectMappingAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ClassTypeInfo_ <em>Class Type Info </em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ClassTypeInfo_
     * @generated
     */
    public Adapter createClassTypeInfo_Adapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentTypeInfo_ <em>Component Type Info </em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentTypeInfo_
     * @generated
     */
    public Adapter createComponentTypeInfo_Adapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ConverterTypeInfo_ <em>Converter Type Info </em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ConverterTypeInfo_
     * @generated
     */
    public Adapter createConverterTypeInfo_Adapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ValidatorTypeInfo_ <em>Validator Type Info </em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ValidatorTypeInfo_
     * @generated
     */
    public Adapter createValidatorTypeInfo_Adapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.AttributeToPropertyMapping <em>Attribute To Property Mapping</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.AttributeToPropertyMapping
     * @generated
     */
    public Adapter createAttributeToPropertyMappingAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc -->
     * This default implementation returns null.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter()
    {
        return null;
    }

} //ComponentMappingAdapterFactory
