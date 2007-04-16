/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.jst.jsf.context.symbol;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IBounded Type Descriptor</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.eclipse.jst.jsf.context.symbol.SymbolPackage#getIBoundedTypeDescriptor()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface IBoundedTypeDescriptor extends ITypeDescriptor {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright 2006 Oracle";

    /**
     * <!-- begin-user-doc -->
     * @param typeSignature 
     * @return true if this type can have unbounded property key for the
     * indicated type.  For example, a Java 1.4 Map could potentially have
     * a value of any time for a particular key, so this method would always return
     * true.  However a Java5 Map<String, String> would only return true if 
     * typeSignature is-a String.
     * <!-- end-user-doc -->
     * @model
     * @generated
     */
    boolean isUnboundedForType(String typeSignature);

    /**
     * <!-- begin-user-doc -->
     * @param name 
     * @param typeSignature 
     * @return a symbol corresponding to the property of this type desc
     * with name called 'name' of type 'typeSignature'.  Must return null if
     * isPropNameUnconstrainedForType returns false for 'typeSignature'.
     * Generally should return something meaningful (even if just an
     * object) when isPropNameUnconstrainedForType returns true.
     * <!-- end-user-doc -->
     * @model
     * @generated
     */
    ISymbol getUnboundedProperty(Object name, String typeSignature);

} // IBoundedTypeDescriptor