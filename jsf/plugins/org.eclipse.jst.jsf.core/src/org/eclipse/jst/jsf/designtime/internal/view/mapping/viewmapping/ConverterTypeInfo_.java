/**
 * <copyright>
 * </copyright>
 *
 * $Id: ConverterTypeInfo_.java,v 1.1 2008/05/12 17:42:19 cbateman Exp $
 */
package org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Converter Type Info </b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ConverterTypeInfo_#getConverterId <em>Converter Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ConverterTypeInfo_#getForClasses <em>For Classes</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentMappingPackage#getConverterTypeInfo_()
 * @model
 * @generated
 */
public interface ConverterTypeInfo_ extends ClassTypeInfo_
{
    /**
     * Returns the value of the '<em><b>Converter Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Converter Id</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Converter Id</em>' attribute.
     * @see #setConverterId(String)
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentMappingPackage#getConverterTypeInfo__ConverterId()
     * @model extendedMetaData="kind='element' name='converterId'"
     * @generated
     */
    String getConverterId();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ConverterTypeInfo_#getConverterId <em>Converter Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Converter Id</em>' attribute.
     * @see #getConverterId()
     * @generated
     */
    void setConverterId(String value);

    /**
     * Returns the value of the '<em><b>For Classes</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>For Classes</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>For Classes</em>' attribute list.
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentMappingPackage#getConverterTypeInfo__ForClasses()
     * @model extendedMetaData="kind='element' name='forClass'"
     * @generated
     */
    EList<String> getForClasses();

} // ConverterTypeInfo_
