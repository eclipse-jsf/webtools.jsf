/*******************************************************************************
 * Copyright (c) 2005 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ian Trimble - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.jsf.core.internal.jsflibraryregistry;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>JSF Version</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryPackage#getJSFVersion()
 * @model
 * @generated
 * @deprecated
 */
public final class JSFVersion extends AbstractEnumerator {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2005 Oracle Corporation"; //$NON-NLS-1$

	/**
	 * The '<em><b>UNKNOWN</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>UNKNOWN</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNKNOWN_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int UNKNOWN = -1;

	/**
	 * The '<em><b>V1 1</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>V1 1</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #V1_1_LITERAL
	 * @model name="v1_1"
	 * @generated
	 * @ordered
	 */
	public static final int V1_1 = 1;

	/**
	 * The '<em><b>V1 2</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>V1 2</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #V1_2_LITERAL
	 * @model name="v1_2"
	 * @generated
	 * @ordered
	 */
	public static final int V1_2 = 2;

	/**
	 * The '<em><b>UNKNOWN</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNKNOWN
	 * @generated
	 * @ordered
	 */
	public static final JSFVersion UNKNOWN_LITERAL = new JSFVersion(UNKNOWN, "UNKNOWN", "UNKNOWN"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>V1 1</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #V1_1
	 * @generated
	 * @ordered
	 */
	public static final JSFVersion V1_1_LITERAL = new JSFVersion(V1_1, "v1_1", "v1_1"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>V1 2</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #V1_2
	 * @generated
	 * @ordered
	 */
	public static final JSFVersion V1_2_LITERAL = new JSFVersion(V1_2, "v1_2", "v1_2"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * An array of all the '<em><b>JSF Version</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final JSFVersion[] VALUES_ARRAY =
		new JSFVersion[] {
			UNKNOWN_LITERAL,
			V1_1_LITERAL,
			V1_2_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>JSF Version</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>JSF Version</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * @param literal value
	 * @return the JSF version for name value 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static JSFVersion get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			JSFVersion result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>JSF Version</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * @param name 
	 * @return JSFVersion
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static JSFVersion getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			JSFVersion result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>JSF Version</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * @param value 
	 * @return the JSFVersion for the integer 'value' 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static JSFVersion get(int value) {
		switch (value) {
			case UNKNOWN: return UNKNOWN_LITERAL;
			case V1_1: return V1_1_LITERAL;
			case V1_2: return V1_2_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private JSFVersion(int value, String name, String literal) {
		super(value, name, literal);
	}

	/**
	 * Returns the '<em><b>JSF Version</b></em>' literal with the specified
	 * name like the get(String) method does, but will return UNKNOWN instead
	 * of null if the name is not recognized.
	 * <!-- begin-user-doc -->
	 * @param name 
	 * @return the JSFVersion
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public static JSFVersion getJSFVersion(String name) {
		JSFVersion version = get(name);
		if (version == null) {
			version = UNKNOWN_LITERAL;
		}
		return version;
	}

} //JSFVersion
