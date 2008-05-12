/**
 * <copyright>
 * </copyright>
 *
 * $Id: ComponentMappingSwitch.java,v 1.1 2008/05/12 17:42:21 cbateman Exp $
 */
package org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * @param <T> 
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentMappingPackage
 * @generated
 */
public class ComponentMappingSwitch<T>
{
    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static ComponentMappingPackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ComponentMappingSwitch()
    {
        if (modelPackage == null)
        {
            modelPackage = ComponentMappingPackage.eINSTANCE;
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * @param theEObject 
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    public T doSwitch(EObject theEObject)
    {
        return doSwitch(theEObject.eClass(), theEObject);
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * @param theEClass 
     * @param theEObject 
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected T doSwitch(EClass theEClass, EObject theEObject)
    {
        if (theEClass.eContainer() == modelPackage)
        {
            return doSwitch(theEClass.getClassifierID(), theEObject);
        }
        List<EClass> eSuperTypes = theEClass.getESuperTypes();
        return
            eSuperTypes.isEmpty() ?
                defaultCase(theEObject) :
                doSwitch(eSuperTypes.get(0), theEObject);
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * @param classifierID 
     * @param theEObject 
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected T doSwitch(int classifierID, EObject theEObject)
    {
        switch (classifierID)
        {
            case ComponentMappingPackage.TAG_MAPPING:
            {
                TagMapping tagMapping = (TagMapping)theEObject;
                T result = caseTagMapping(tagMapping);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ComponentMappingPackage.TAG_TO_VIEW_OBJECT_MAPPING:
            {
                TagToViewObjectMapping tagToViewObjectMapping = (TagToViewObjectMapping)theEObject;
                T result = caseTagToViewObjectMapping(tagToViewObjectMapping);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ComponentMappingPackage.CLASS_TYPE_INFO_:
            {
                ClassTypeInfo_ classTypeInfo_ = (ClassTypeInfo_)theEObject;
                T result = caseClassTypeInfo_(classTypeInfo_);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ComponentMappingPackage.COMPONENT_TYPE_INFO_:
            {
                ComponentTypeInfo_ componentTypeInfo_ = (ComponentTypeInfo_)theEObject;
                T result = caseComponentTypeInfo_(componentTypeInfo_);
                if (result == null) result = caseClassTypeInfo_(componentTypeInfo_);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ComponentMappingPackage.CONVERTER_TYPE_INFO_:
            {
                ConverterTypeInfo_ converterTypeInfo_ = (ConverterTypeInfo_)theEObject;
                T result = caseConverterTypeInfo_(converterTypeInfo_);
                if (result == null) result = caseClassTypeInfo_(converterTypeInfo_);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ComponentMappingPackage.VALIDATOR_TYPE_INFO_:
            {
                ValidatorTypeInfo_ validatorTypeInfo_ = (ValidatorTypeInfo_)theEObject;
                T result = caseValidatorTypeInfo_(validatorTypeInfo_);
                if (result == null) result = caseClassTypeInfo_(validatorTypeInfo_);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ComponentMappingPackage.ATTRIBUTE_TO_PROPERTY_MAPPING:
            {
                AttributeToPropertyMapping attributeToPropertyMapping = (AttributeToPropertyMapping)theEObject;
                T result = caseAttributeToPropertyMapping(attributeToPropertyMapping);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Tag Mapping</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Tag Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTagMapping(TagMapping object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Tag To View Object Mapping</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Tag To View Object Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTagToViewObjectMapping(TagToViewObjectMapping object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Class Type Info </em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Class Type Info </em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseClassTypeInfo_(ClassTypeInfo_ object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Component Type Info </em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Component Type Info </em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseComponentTypeInfo_(ComponentTypeInfo_ object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Converter Type Info </em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Converter Type Info </em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseConverterTypeInfo_(ConverterTypeInfo_ object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Validator Type Info </em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Validator Type Info </em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseValidatorTypeInfo_(ValidatorTypeInfo_ object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Attribute To Property Mapping</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Attribute To Property Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAttributeToPropertyMapping(AttributeToPropertyMapping object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch, but this is the last case anyway.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    public T defaultCase(EObject object)
    {
        return null;
    }

} //ComponentMappingSwitch
