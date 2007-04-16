/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.jst.jsf.context.symbol;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IList Type Descriptor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.context.symbol.IListTypeDescriptor#getListSource <em>List Source</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.context.symbol.SymbolPackage#getIListTypeDescriptor()
 * @model
 * @generated
 */
public interface IListTypeDescriptor extends ITypeDescriptor {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright 2006 Oracle";

    /**
     * Returns the value of the '<em><b>List Source</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>List Source</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>List Source</em>' attribute.
     * @see #setListSource(EList)
     * @see org.eclipse.jst.jsf.context.symbol.SymbolPackage#getIListTypeDescriptor_ListSource()
     * @model many="false"
     * @generated
     */
    EList getListSource();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.context.symbol.IListTypeDescriptor#getListSource <em>List Source</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>List Source</em>' attribute.
     * @see #getListSource()
     * @generated
     */
    void setListSource(EList value);

} // IListTypeDescriptor
