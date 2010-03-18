/**
 * <copyright>
 * </copyright>
 *
 * $Id: FaceletTaglibFunction.java,v 1.1 2010/03/18 06:24:29 cbateman Exp $
 */
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Facelet Taglib Function</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 *                 If the tag library XML file contains individual function
 *                 declarations rather than pointing to a library-class or a
 *                 declaring a composite-library name, the individual functions are
 *                 enclosed in function elements.
 *             
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibFunction#getFunctionName <em>Function Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibFunction#getFunctionClass <em>Function Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibFunction#getFunctionSignature <em>Function Signature</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglibFunction()
 * @model extendedMetaData="name='facelet-taglib-functionType' kind='elementOnly'"
 * @generated
 */
public interface FaceletTaglibFunction extends UserVisibleTaglibObject
{
    /**
     * Returns the value of the '<em><b>Function Name</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Function Name</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Function Name</em>' containment reference.
     * @see #setFunctionName(IdentifiableStringValue)
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglibFunction_FunctionName()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='function-name' namespace='##targetNamespace'"
     * @generated
     */
    IdentifiableStringValue getFunctionName();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibFunction#getFunctionName <em>Function Name</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Function Name</em>' containment reference.
     * @see #getFunctionName()
     * @generated
     */
    void setFunctionName(IdentifiableStringValue value);

    /**
     * Returns the value of the '<em><b>Function Class</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Function Class</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Function Class</em>' containment reference.
     * @see #setFunctionClass(FullyQualifiedClass)
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglibFunction_FunctionClass()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='function-class' namespace='##targetNamespace'"
     * @generated
     */
    FullyQualifiedClass getFunctionClass();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibFunction#getFunctionClass <em>Function Class</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Function Class</em>' containment reference.
     * @see #getFunctionClass()
     * @generated
     */
    void setFunctionClass(FullyQualifiedClass value);

    /**
     * Returns the value of the '<em><b>Function Signature</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Function Signature</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Function Signature</em>' containment reference.
     * @see #setFunctionSignature(IdentifiableStringValue)
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglibFunction_FunctionSignature()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='function-signature' namespace='##targetNamespace'"
     * @generated
     */
    IdentifiableStringValue getFunctionSignature();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibFunction#getFunctionSignature <em>Function Signature</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Function Signature</em>' containment reference.
     * @see #getFunctionSignature()
     * @generated
     */
    void setFunctionSignature(IdentifiableStringValue value);

} // FaceletTaglibFunction
