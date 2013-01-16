/**
 * <copyright>
 * </copyright>
 *
 * $Id: GenericBooleanBase.java,v 1.1 2010/03/18 06:24:28 cbateman Exp $
 */
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Generic Boolean Base</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getGenericBooleanBase()
 * @model extendedMetaData="name='generic-booleanType_._base'"
 * @generated
 */
public enum GenericBooleanBase implements Enumerator
{
    /**
	 * The '<em><b>True</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #TRUE_VALUE
	 * @generated
	 * @ordered
	 */
    TRUE(0, "true", "true"),  //$NON-NLS-1$//$NON-NLS-2$

    /**
	 * The '<em><b>False</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #FALSE_VALUE
	 * @generated
	 * @ordered
	 */
    FALSE(1, "false", "false"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
	 * The '<em><b>Yes</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #YES_VALUE
	 * @generated
	 * @ordered
	 */
    YES(2, "yes", "yes"),  //$NON-NLS-1$//$NON-NLS-2$

    /**
	 * The '<em><b>No</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #NO_VALUE
	 * @generated
	 * @ordered
	 */
    NO(3, "no", "no"); //$NON-NLS-1$ //$NON-NLS-2$

    /**
	 * The '<em><b>True</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>True</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #TRUE
	 * @model name="true"
	 * @generated
	 * @ordered
	 */
    public static final int TRUE_VALUE = 0;

    /**
	 * The '<em><b>False</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>False</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #FALSE
	 * @model name="false"
	 * @generated
	 * @ordered
	 */
    public static final int FALSE_VALUE = 1;

    /**
	 * The '<em><b>Yes</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Yes</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #YES
	 * @model name="yes"
	 * @generated
	 * @ordered
	 */
    public static final int YES_VALUE = 2;

    /**
	 * The '<em><b>No</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>No</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #NO
	 * @model name="no"
	 * @generated
	 * @ordered
	 */
    public static final int NO_VALUE = 3;

    /**
	 * An array of all the '<em><b>Generic Boolean Base</b></em>' enumerators.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private static final GenericBooleanBase[] VALUES_ARRAY =
        new GenericBooleanBase[] {
			TRUE,
			FALSE,
			YES,
			NO,
		};

    /**
	 * A public read-only list of all the '<em><b>Generic Boolean Base</b></em>' enumerators.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static final List<GenericBooleanBase> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
	 * Returns the '<em><b>Generic Boolean Base</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
     * @param literal 
     * @return value
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static GenericBooleanBase get(String literal)
    {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			GenericBooleanBase result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

    /**
	 * Returns the '<em><b>Generic Boolean Base</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
     * @param name 
     * @return the boolean base.
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static GenericBooleanBase getByName(String name)
    {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			GenericBooleanBase result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

    /**
	 * Returns the '<em><b>Generic Boolean Base</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
     * @param value 
     * @return the boolean base. 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static GenericBooleanBase get(int value)
    {
		switch (value) {
			case TRUE_VALUE: return TRUE;
			case FALSE_VALUE: return FALSE;
			case YES_VALUE: return YES;
			case NO_VALUE: return NO;
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
    private GenericBooleanBase(int value, String name, String literal)
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
    
} //GenericBooleanBase
