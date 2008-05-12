/**
 * <copyright>
 * </copyright>
 *
 * $Id: ComponentMappingFactory.java,v 1.1 2008/05/12 17:42:19 cbateman Exp $
 */
package org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentMappingPackage
 * @generated
 */
public interface ComponentMappingFactory extends EFactory
{
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    ComponentMappingFactory eINSTANCE = org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.impl.ComponentMappingFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Tag Mapping</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Tag Mapping</em>'.
     * @generated
     */
    TagMapping createTagMapping();

    /**
     * Returns a new object of class '<em>Tag To View Object Mapping</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Tag To View Object Mapping</em>'.
     * @generated
     */
    TagToViewObjectMapping createTagToViewObjectMapping();

    /**
     * Returns a new object of class '<em>Class Type Info </em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Class Type Info </em>'.
     * @generated
     */
    ClassTypeInfo_ createClassTypeInfo_();

    /**
     * Returns a new object of class '<em>Component Type Info </em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Component Type Info </em>'.
     * @generated
     */
    ComponentTypeInfo_ createComponentTypeInfo_();

    /**
     * Returns a new object of class '<em>Converter Type Info </em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Converter Type Info </em>'.
     * @generated
     */
    ConverterTypeInfo_ createConverterTypeInfo_();

    /**
     * Returns a new object of class '<em>Validator Type Info </em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Validator Type Info </em>'.
     * @generated
     */
    ValidatorTypeInfo_ createValidatorTypeInfo_();

    /**
     * Returns a new object of class '<em>Attribute To Property Mapping</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Attribute To Property Mapping</em>'.
     * @generated
     */
    AttributeToPropertyMapping createAttributeToPropertyMapping();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    ComponentMappingPackage getComponentMappingPackage();

} //ComponentMappingFactory
