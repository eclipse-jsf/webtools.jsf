/**
 * <copyright>
 * </copyright>
 *
 * $Id: ComponentTypeInfo_.java,v 1.1 2008/05/12 17:42:19 cbateman Exp $
 */
package org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component Type Info </b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentTypeInfo_#getComponentType <em>Component Type</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentTypeInfo_#getComponentFamily <em>Component Family</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentTypeInfo_#getRenderType <em>Render Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentMappingPackage#getComponentTypeInfo_()
 * @model
 * @generated
 */
public interface ComponentTypeInfo_ extends ClassTypeInfo_
{
    /**
     * Returns the value of the '<em><b>Component Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Component Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Component Type</em>' attribute.
     * @see #setComponentType(String)
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentMappingPackage#getComponentTypeInfo__ComponentType()
     * @model extendedMetaData="kind='element' name='componentType'"
     * @generated
     */
    String getComponentType();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentTypeInfo_#getComponentType <em>Component Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Component Type</em>' attribute.
     * @see #getComponentType()
     * @generated
     */
    void setComponentType(String value);

    /**
     * Returns the value of the '<em><b>Component Family</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Component Family</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Component Family</em>' attribute.
     * @see #setComponentFamily(String)
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentMappingPackage#getComponentTypeInfo__ComponentFamily()
     * @model extendedMetaData="kind='element' name='componentFamily'"
     * @generated
     */
    String getComponentFamily();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentTypeInfo_#getComponentFamily <em>Component Family</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Component Family</em>' attribute.
     * @see #getComponentFamily()
     * @generated
     */
    void setComponentFamily(String value);

    /**
     * Returns the value of the '<em><b>Render Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Render Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Render Type</em>' attribute.
     * @see #setRenderType(String)
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentMappingPackage#getComponentTypeInfo__RenderType()
     * @model extendedMetaData="kind='element' name='renderType'"
     * @generated
     */
    String getRenderType();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentTypeInfo_#getRenderType <em>Render Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Render Type</em>' attribute.
     * @see #getRenderType()
     * @generated
     */
    void setRenderType(String value);

} // ComponentTypeInfo_
