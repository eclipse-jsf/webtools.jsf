/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.context.symbol.internal.provisional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * Enumerates the possible sources of an instance symbol
 * at runtime.  In the default implementation these can
 * be: from the built-in variables, from the managed bean
 * facility, and those defined in tags.  This information
 * is provided to allow the VariableResolver to resolve
 * conflicts when instance symbols come from more than one
 * source. 
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.jsf.context.symbol.SymbolPackage#getERuntimeSource()
 * @model
 * @generated
 */
public final class ERuntimeSource extends AbstractEnumerator {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright 2006 Oracle";

    /**
     * The '<em><b>BUILT IN SYMBOL</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>BUILT IN SYMBOL</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #BUILT_IN_SYMBOL_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int BUILT_IN_SYMBOL = 0;

    /**
     * The '<em><b>MANAGED BEAN SYMBOL</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>MANAGED BEAN SYMBOL</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #MANAGED_BEAN_SYMBOL_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int MANAGED_BEAN_SYMBOL = 1;

    /**
     * The '<em><b>TAG INSTANTIATED SYMBOL</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>TAG INSTANTIATED SYMBOL</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #TAG_INSTANTIATED_SYMBOL_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int TAG_INSTANTIATED_SYMBOL = 2;

    /**
     * The '<em><b>OTHER</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>OTHER</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #OTHER_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int OTHER = 3;

    /**
     * The '<em><b>BUILT IN SYMBOL</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #BUILT_IN_SYMBOL
     * @generated
     * @ordered
     */
    public static final ERuntimeSource BUILT_IN_SYMBOL_LITERAL = new ERuntimeSource(BUILT_IN_SYMBOL, "BUILT_IN_SYMBOL", "BUILT_IN_SYMBOL");

    /**
     * The '<em><b>MANAGED BEAN SYMBOL</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #MANAGED_BEAN_SYMBOL
     * @generated
     * @ordered
     */
    public static final ERuntimeSource MANAGED_BEAN_SYMBOL_LITERAL = new ERuntimeSource(MANAGED_BEAN_SYMBOL, "MANAGED_BEAN_SYMBOL", "MANAGED_BEAN_SYMBOL");

    /**
     * The '<em><b>TAG INSTANTIATED SYMBOL</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #TAG_INSTANTIATED_SYMBOL
     * @generated
     * @ordered
     */
    public static final ERuntimeSource TAG_INSTANTIATED_SYMBOL_LITERAL = new ERuntimeSource(TAG_INSTANTIATED_SYMBOL, "TAG_INSTANTIATED_SYMBOL", "TAG_INSTANTIATED_SYMBOL");

    /**
     * The '<em><b>OTHER</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #OTHER
     * @generated
     * @ordered
     */
    public static final ERuntimeSource OTHER_LITERAL = new ERuntimeSource(OTHER, "OTHER", "OTHER");

    /**
     * An array of all the '<em><b>ERuntime Source</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final ERuntimeSource[] VALUES_ARRAY =
        new ERuntimeSource[] {
            BUILT_IN_SYMBOL_LITERAL,
            MANAGED_BEAN_SYMBOL_LITERAL,
            TAG_INSTANTIATED_SYMBOL_LITERAL,
            OTHER_LITERAL,
        };

    /**
     * A public read-only list of all the '<em><b>ERuntime Source</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>ERuntime Source</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * @param literal 
     * @return the enumeration for a string literal representation 
     * <!-- end-user-doc -->
     * @generated
     */
    public static ERuntimeSource get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            ERuntimeSource result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>ERuntime Source</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * @param name 
     * @return the enumeration for the name of the literal value 
     * <!-- end-user-doc -->
     * @generated
     */
    public static ERuntimeSource getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            ERuntimeSource result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>ERuntime Source</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * @param value 
     * @return the enumeration for it's integral value 
     * <!-- end-user-doc -->
     * @generated
     */
    public static ERuntimeSource get(int value) {
        switch (value) {
            case BUILT_IN_SYMBOL: return BUILT_IN_SYMBOL_LITERAL;
            case MANAGED_BEAN_SYMBOL: return MANAGED_BEAN_SYMBOL_LITERAL;
            case TAG_INSTANTIATED_SYMBOL: return TAG_INSTANTIATED_SYMBOL_LITERAL;
            case OTHER: return OTHER_LITERAL;
        }
        return null;	
    }

    /**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private ERuntimeSource(int value, String name, String literal) {
        super(value, name, literal);
    }

} //ERuntimeSource
