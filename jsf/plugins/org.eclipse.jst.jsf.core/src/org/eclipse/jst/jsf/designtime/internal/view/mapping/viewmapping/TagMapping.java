/**
 * <copyright>
 * </copyright>
 *
 * $Id: TagMapping.java,v 1.1 2008/05/12 17:42:19 cbateman Exp $
 */
package org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tag Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.TagMapping#getVersionedTagToViewMappings <em>Versioned Tag To View Mappings</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.TagMapping#getCustomConversionFactoryId <em>Custom Conversion Factory Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.TagMapping#getBeanMappedProperties <em>Bean Mapped Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentMappingPackage#getTagMapping()
 * @model
 * @generated
 */
public interface TagMapping extends EObject
{
    /**
     * Returns the value of the '<em><b>Versioned Tag To View Mappings</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.TagToViewObjectMapping}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Versioned Tag To View Mappings</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Versioned Tag To View Mappings</em>' containment reference list.
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentMappingPackage#getTagMapping_VersionedTagToViewMappings()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='versionedTagToViewMappings'"
     * @generated
     */
    EList<TagToViewObjectMapping> getVersionedTagToViewMappings();

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
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentMappingPackage#getTagMapping_CustomConversionFactoryId()
     * @model extendedMetaData="kind='element' name='customConversionFactoryId'"
     * @generated
     */
    String getCustomConversionFactoryId();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.TagMapping#getCustomConversionFactoryId <em>Custom Conversion Factory Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Custom Conversion Factory Id</em>' attribute.
     * @see #getCustomConversionFactoryId()
     * @generated
     */
    void setCustomConversionFactoryId(String value);

    /**
     * Returns the value of the '<em><b>Bean Mapped Properties</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Bean Mapped Properties</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Bean Mapped Properties</em>' attribute list.
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentMappingPackage#getTagMapping_BeanMappedProperties()
     * @model default=""
     *        extendedMetaData="kind='element' name='beanMappedProperties'"
     * @generated
     */
    EList<String> getBeanMappedProperties();

    /**
     * <!-- begin-user-doc -->
     * @param jsfVersion 
     * @param libVersion 
     * <!-- end-user-doc -->
     * @model
     * @generated
     */
    void findBestMapping(String jsfVersion, String libVersion);

} // TagMapping
