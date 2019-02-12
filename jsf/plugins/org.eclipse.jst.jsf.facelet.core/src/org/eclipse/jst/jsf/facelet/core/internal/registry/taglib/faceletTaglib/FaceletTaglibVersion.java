/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/**
 * <copyright>
 * </copyright>
 *
 * $Id: FaceletTaglibVersion.java,v 1.1 2010/03/18 06:24:28 cbateman Exp $
 */
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Facelet Taglib Version</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 *                 This type contains the recognized versions of
 *                 facelet-taglib supported.
 *             
 * <!-- end-model-doc -->
 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglibVersion()
 * @model extendedMetaData="name='facelet-taglib-versionType'"
 * @generated
 */
public enum FaceletTaglibVersion implements Enumerator
{
    /**
	 * The '<em><b>20</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #_20_VALUE
	 * @generated
	 * @ordered
	 */
    _20(0, "_20", "2.0"),  //$NON-NLS-1$ //$NON-NLS-2$
    /**
	 * The '<em><b>21</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #_21_VALUE
	 * @generated
	 * @ordered
	 */
	_21(1, "_21", "2.1"),  //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>22</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #_22_VALUE
	 * @generated
	 * @ordered
	 */
	_22(2, "_22", "2.2");  //$NON-NLS-1$//$NON-NLS-2$

    /**
	 * The '<em><b>20</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>20</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #_20
	 * @model literal="2.0"
	 * @generated
	 * @ordered
	 */
    public static final int _20_VALUE = 0;

    /**
	 * The '<em><b>21</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>21</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #_21
	 * @model literal="2.1"
	 * @generated
	 * @ordered
	 */
	public static final int _21_VALUE = 1;

				/**
	 * The '<em><b>22</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>22</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #_22
	 * @model literal="2.2"
	 * @generated
	 * @ordered
	 */
	public static final int _22_VALUE = 2;

				/**
	 * An array of all the '<em><b>Version</b></em>' enumerators.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private static final FaceletTaglibVersion[] VALUES_ARRAY =
        new FaceletTaglibVersion[] {
			_20,
			_21,
			_22,
		};

    /**
	 * A public read-only list of all the '<em><b>Version</b></em>' enumerators.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static final List<FaceletTaglibVersion> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
	 * Returns the '<em><b>Version</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
     * @param literal 
     * @return the version
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static FaceletTaglibVersion get(String literal)
    {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			FaceletTaglibVersion result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

    /**
	 * Returns the '<em><b>Version</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
     * @param name 
     * @return the version
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static FaceletTaglibVersion getByName(String name)
    {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			FaceletTaglibVersion result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

    /**
	 * Returns the '<em><b>Version</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
     * @param value 
     * @return the version
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static FaceletTaglibVersion get(int value)
    {
		switch (value) {
			case _20_VALUE: return _20;
			case _21_VALUE: return _21;
			case _22_VALUE: return _22;
		}
		return null;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private final int value;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private final String name;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private final String literal;

    /**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private FaceletTaglibVersion(int value, String name, String literal)
    {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public int getValue()
    {
	  return value;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getName()
    {
	  return name;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getLiteral()
    {
	  return literal;
	}

    /**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public String toString()
    {
		return literal;
	}
    
} //FaceletTaglibVersion
