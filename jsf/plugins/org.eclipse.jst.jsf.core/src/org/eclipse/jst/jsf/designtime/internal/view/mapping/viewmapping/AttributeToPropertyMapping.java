/**
 * <copyright>
 * </copyright>
 *
 * $Id: AttributeToPropertyMapping.java,v 1.1 2008/05/12 17:42:19 cbateman Exp $
 */
package org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Attribute To Property Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.AttributeToPropertyMapping#getPropertyName <em>Property Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.AttributeToPropertyMapping#isElAllowed <em>El Allowed</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.AttributeToPropertyMapping#getCustomConversionFactoryId <em>Custom Conversion Factory Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentMappingPackage#getAttributeToPropertyMapping()
 * @model
 * @generated
 */
public interface AttributeToPropertyMapping extends EObject
{
    /**
     * Returns the value of the '<em><b>Property Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Property Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Property Name</em>' attribute.
     * @see #setPropertyName(String)
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentMappingPackage#getAttributeToPropertyMapping_PropertyName()
     * @model extendedMetaData="kind='element' name='propertyName'"
     * @generated
     */
    String getPropertyName();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.AttributeToPropertyMapping#getPropertyName <em>Property Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Property Name</em>' attribute.
     * @see #getPropertyName()
     * @generated
     */
    void setPropertyName(String value);

    /**
     * Returns the value of the '<em><b>El Allowed</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>El Allowed</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>El Allowed</em>' attribute.
     * @see #setElAllowed(boolean)
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentMappingPackage#getAttributeToPropertyMapping_ElAllowed()
     * @model default="true"
     *        extendedMetaData="kind='element' name='elAllowed'"
     * @generated
     */
    boolean isElAllowed();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.AttributeToPropertyMapping#isElAllowed <em>El Allowed</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>El Allowed</em>' attribute.
     * @see #isElAllowed()
     * @generated
     */
    void setElAllowed(boolean value);

    /**
     * Returns the value of the '<em><b>Custom Conversion Factory Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Custom Conversion Factory Id</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Custom Conversion Factory Id</em>' attribute.
     * @see #setCustomConversionFactoryId(String)
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentMappingPackage#getAttributeToPropertyMapping_CustomConversionFactoryId()
     * @model extendedMetaData="kind='element' name='customConversionFactoryId'"
     * @generated
     */
    String getCustomConversionFactoryId();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.AttributeToPropertyMapping#getCustomConversionFactoryId <em>Custom Conversion Factory Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Custom Conversion Factory Id</em>' attribute.
     * @see #getCustomConversionFactoryId()
     * @generated
     */
    void setCustomConversionFactoryId(String value);

} // AttributeToPropertyMapping
