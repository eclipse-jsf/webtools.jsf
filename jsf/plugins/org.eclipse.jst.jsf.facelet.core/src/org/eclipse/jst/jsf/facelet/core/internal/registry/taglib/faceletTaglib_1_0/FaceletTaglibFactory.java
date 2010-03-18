/**
 * <copyright>
 * </copyright>
 *
 * $Id: FaceletTaglibFactory.java,v 1.1 2010/03/18 06:24:40 cbateman Exp $
 */
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletTaglibPackage
 * @generated
 */
public interface FaceletTaglibFactory extends EFactory
{
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    FaceletTaglibFactory eINSTANCE = org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.impl.FaceletTaglibFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Facelet Library Class Tag Lib</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Facelet Library Class Tag Lib</em>'.
     * @generated
     */
    FaceletLibraryClassTagLib createFaceletLibraryClassTagLib();

    /**
     * Returns a new object of class '<em>Facelet XML Defn Taglib</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Facelet XML Defn Taglib</em>'.
     * @generated
     */
    FaceletXMLDefnTaglib createFaceletXMLDefnTaglib();

    /**
     * Returns a new object of class '<em>Defn</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Defn</em>'.
     * @generated
     */
    FaceletTaglibDefn createFaceletTaglibDefn();

    /**
     * Returns a new object of class '<em>Component Tag Defn</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Component Tag Defn</em>'.
     * @generated
     */
    ComponentTagDefn createComponentTagDefn();

    /**
     * Returns a new object of class '<em>Validator Tag Defn</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Validator Tag Defn</em>'.
     * @generated
     */
    ValidatorTagDefn createValidatorTagDefn();

    /**
     * Returns a new object of class '<em>Converter Tag Defn</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Converter Tag Defn</em>'.
     * @generated
     */
    ConverterTagDefn createConverterTagDefn();

    /**
     * Returns a new object of class '<em>Handler Tag Defn</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Handler Tag Defn</em>'.
     * @generated
     */
    HandlerTagDefn createHandlerTagDefn();

    /**
     * Returns a new object of class '<em>Source Tag Defn</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Source Tag Defn</em>'.
     * @generated
     */
    SourceTagDefn createSourceTagDefn();

    /**
     * Returns a new object of class '<em>Tag Defn</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Tag Defn</em>'.
     * @generated
     */
    TagDefn createTagDefn();

    /**
     * Returns a new object of class '<em>Function Defn</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Function Defn</em>'.
     * @generated
     */
    FunctionDefn createFunctionDefn();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    FaceletTaglibPackage getFaceletTaglibPackage();

} //FaceletTaglibFactory
