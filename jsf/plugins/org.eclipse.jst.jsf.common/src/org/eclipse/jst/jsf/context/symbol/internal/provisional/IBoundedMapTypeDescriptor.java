/**
 * <copyright>
 * </copyright>
 *
 * $Id: IBoundedMapTypeDescriptor.java,v 1.3 2006/12/05 18:36:43 cbateman Exp $
 */
package org.eclipse.jst.jsf.context.symbol.internal.provisional;


/**
 * <!-- begin-user-doc -->
 * Describes a IMapTypeDescriptor for which the possible keyed values are
 * unbounded for some set of possible value types.  For example, a managed
 * bean that implements Map, is unconstrained for all values in Java 1.4 and
 * for whatever the template value type is in Java5.
 * <!-- end-user-doc -->
 *
 *
 * @see org.eclipse.jst.jsf.context.symbol.SymbolPackage#getIBoundedMapTypeDescriptor()
 * @model
 * @generated
 */
public interface IBoundedMapTypeDescriptor extends IMapTypeDescriptor, IBoundedTypeDescriptor {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright 2006 Oracle";

} // IBoundedMapTypeDescriptor