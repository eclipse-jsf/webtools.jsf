/**
 * <copyright>
 * </copyright>
 *
 * $Id: TagToViewObjectMapping.java,v 1.1 2008/05/12 17:42:19 cbateman Exp $
 */
package org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tag To View Object Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.TagToViewObjectMapping#getTypeInfo <em>Type Info</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.TagToViewObjectMapping#getMinJSFVersion <em>Min JSF Version</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.TagToViewObjectMapping#getMinLibraryVersion <em>Min Library Version</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentMappingPackage#getTagToViewObjectMapping()
 * @model
 * @generated
 */
public interface TagToViewObjectMapping extends EObject
{
    /**
     * Returns the value of the '<em><b>Type Info</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type Info</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Type Info</em>' containment reference.
     * @see #setTypeInfo(ClassTypeInfo_)
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentMappingPackage#getTagToViewObjectMapping_TypeInfo()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='typeInfo'"
     * @generated
     */
    ClassTypeInfo_ getTypeInfo();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.TagToViewObjectMapping#getTypeInfo <em>Type Info</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type Info</em>' containment reference.
     * @see #getTypeInfo()
     * @generated
     */
    void setTypeInfo(ClassTypeInfo_ value);

    /**
     * Returns the value of the '<em><b>Min JSF Version</b></em>' attribute.
     * The default value is <code>"1.1"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Min JSF Version</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Min JSF Version</em>' attribute.
     * @see #setMinJSFVersion(String)
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentMappingPackage#getTagToViewObjectMapping_MinJSFVersion()
     * @model default="1.1"
     *        extendedMetaData="kind='element' name='minVersion'"
     * @generated
     */
    String getMinJSFVersion();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.TagToViewObjectMapping#getMinJSFVersion <em>Min JSF Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Min JSF Version</em>' attribute.
     * @see #getMinJSFVersion()
     * @generated
     */
    void setMinJSFVersion(String value);

    /**
     * Returns the value of the '<em><b>Min Library Version</b></em>' attribute.
     * The default value is <code>""</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Min Library Version</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Min Library Version</em>' attribute.
     * @see #setMinLibraryVersion(String)
     * @see org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentMappingPackage#getTagToViewObjectMapping_MinLibraryVersion()
     * @model default=""
     *        extendedMetaData="kind='element' name='minLibraryVersion'"
     * @generated
     */
    String getMinLibraryVersion();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.TagToViewObjectMapping#getMinLibraryVersion <em>Min Library Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Min Library Version</em>' attribute.
     * @see #getMinLibraryVersion()
     * @generated
     */
    void setMinLibraryVersion(String value);

} // TagToViewObjectMapping
