/**
 * <copyright>
 * </copyright>
 *
 * $Id: FaceletTaglibTagComponent.java,v 1.1 2010/03/18 06:24:29 cbateman Exp $
 */
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Facelet Taglib Tag Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 *                 Within a tag element, the component element encapsulates
 *                 information specific to a JSF UIComponent.
 *             
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagComponent#getComponentType <em>Component Type</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagComponent#getRendererType <em>Renderer Type</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagComponent#getHandlerClass <em>Handler Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagComponent#getComponentExtension <em>Component Extension</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglibTagComponent()
 * @model extendedMetaData="name='facelet-taglib-tag-componentType' kind='elementOnly'"
 * @generated
 */
public interface FaceletTaglibTagComponent extends UserVisibleTaglibObject
{
    /**
     * Returns the value of the '<em><b>Component Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Component Type</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Component Type</em>' containment reference.
     * @see #setComponentType(IdentifiableStringValue)
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglibTagComponent_ComponentType()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='component-type' namespace='##targetNamespace'"
     * @generated
     */
    IdentifiableStringValue getComponentType();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagComponent#getComponentType <em>Component Type</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Component Type</em>' containment reference.
     * @see #getComponentType()
     * @generated
     */
    void setComponentType(IdentifiableStringValue value);

    /**
     * Returns the value of the '<em><b>Renderer Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Renderer Type</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Renderer Type</em>' containment reference.
     * @see #setRendererType(IdentifiableStringValue)
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglibTagComponent_RendererType()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='renderer-type' namespace='##targetNamespace'"
     * @generated
     */
    IdentifiableStringValue getRendererType();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagComponent#getRendererType <em>Renderer Type</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Renderer Type</em>' containment reference.
     * @see #getRendererType()
     * @generated
     */
    void setRendererType(IdentifiableStringValue value);

    /**
     * Returns the value of the '<em><b>Handler Class</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Handler Class</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Handler Class</em>' containment reference.
     * @see #setHandlerClass(FullyQualifiedClass)
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglibTagComponent_HandlerClass()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='handler-class' namespace='##targetNamespace'"
     * @generated
     */
    FullyQualifiedClass getHandlerClass();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagComponent#getHandlerClass <em>Handler Class</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Handler Class</em>' containment reference.
     * @see #getHandlerClass()
     * @generated
     */
    void setHandlerClass(FullyQualifiedClass value);

    /**
     * Returns the value of the '<em><b>Component Extension</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagComponentExtension}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Component Extension</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Component Extension</em>' containment reference list.
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglibTagComponent_ComponentExtension()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='component-extension' namespace='##targetNamespace'"
     * @generated
     */
    EList<FaceletTaglibTagComponentExtension> getComponentExtension();

} // FaceletTaglibTagComponent
