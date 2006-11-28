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

package org.eclipse.jst.jsf.core.tests.types;


import junit.framework.TestCase;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.CompositeType;
import org.eclipse.jst.jsf.common.internal.types.IAssignable;
import org.eclipse.jst.jsf.common.internal.types.TypeComparator;

/**
 * Unit tests for the composite type comparator.  Indirectly exercises
 * type transfomer and coercers.
 * 
 * @author cbateman
 *
 */
public class TypeComparatorTests extends TestCase 
{
    // basic types
    private final CompositeType simpleString = 
        new CompositeType("Ljava.lang.String;", IAssignable.ASSIGNMENT_TYPE_RHS);
    private final CompositeType simpleInteger = 
        new CompositeType(Signature.SIG_INT, IAssignable.ASSIGNMENT_TYPE_RHS);
    private final CompositeType simpleLong =
        new CompositeType(Signature.SIG_LONG, IAssignable.ASSIGNMENT_TYPE_RHS);
    private final CompositeType simpleBoolean =
        new CompositeType(Signature.SIG_BOOLEAN, IAssignable.ASSIGNMENT_TYPE_RHS);
    private final CompositeType simpleDouble =
        new CompositeType(Signature.SIG_DOUBLE, IAssignable.ASSIGNMENT_TYPE_RHS);
    
    // boxed types
    private final CompositeType boxedInteger = 
        new CompositeType("Ljava.lang.Integer;", IAssignable.ASSIGNMENT_TYPE_RHS);
    private final CompositeType boxedLong =
        new CompositeType("Ljava.lang.Long;", IAssignable.ASSIGNMENT_TYPE_RHS);
    private final CompositeType boxedBoolean =
        new CompositeType("Ljava.lang.Boolean;", IAssignable.ASSIGNMENT_TYPE_RHS);
    private final CompositeType boxedDouble =
        new CompositeType("Ljava.lang.Double;", IAssignable.ASSIGNMENT_TYPE_RHS);
    
    // single interface types
    private final CompositeType comparableType = 
        new CompositeType("Ljava.lang.Comparable;", IAssignable.ASSIGNMENT_TYPE_RHS);
    private final CompositeType mapType = 
        new CompositeType("Ljava.util.Map;", IAssignable.ASSIGNMENT_TYPE_RHS);
    
    
    // multi-types
    private final CompositeType objectAndComparable =
        new CompositeType(new String[] {"Ljava.lang.Object;", "Ljava.lang.Comparable;"}
                            , IAssignable.ASSIGNMENT_TYPE_RHS);
    private final CompositeType objectAndMap =
        new CompositeType(new String[] {"Ljava.lang.Object;", "Ljava.util.Map;"}
                            , IAssignable.ASSIGNMENT_TYPE_RHS);
    private final CompositeType objectAndCollection =
        new CompositeType(new String[] {"Ljava.lang.Object;", "Ljava.util.Collection;"}
                            , IAssignable.ASSIGNMENT_TYPE_RHS);
    
    // read/write types
    private final CompositeType readWritePrimitiveLong =
        new CompositeType(Signature.SIG_LONG, 
                IAssignable.ASSIGNMENT_TYPE_RHS | IAssignable.ASSIGNMENT_TYPE_LHS);
    private final CompositeType readWriteString =
        new CompositeType("Ljava.lang.String;", 
                IAssignable.ASSIGNMENT_TYPE_LHS | IAssignable.ASSIGNMENT_TYPE_RHS);
    private final CompositeType readWriteObject =
        new CompositeType("Ljava.lang.Object;",
                IAssignable.ASSIGNMENT_TYPE_LHS | IAssignable.ASSIGNMENT_TYPE_RHS);
    
    /**
     * Sanity check on simple types
     */
    public void testSimpleTypes()
    {
        // simple integers always same
        Diagnostic result = 
            TypeComparator.
                calculateTypeCompatibility(simpleInteger, simpleInteger);
        assertTrue(result.getSeverity() == Diagnostic.OK);

        // simple longs always same
        result = 
            TypeComparator.
                calculateTypeCompatibility(simpleLong, simpleLong);
        assertTrue(result.getSeverity() == Diagnostic.OK);

        // simple double always same
        result = 
            TypeComparator.
                calculateTypeCompatibility(simpleDouble, simpleDouble);
        assertTrue(result.getSeverity() == Diagnostic.OK);

        // simple boolean always same
        result = 
            TypeComparator.
                calculateTypeCompatibility(simpleBoolean, simpleBoolean);
        assertTrue(result.getSeverity() == Diagnostic.OK);
        
        // simple strings always same
        result = 
            TypeComparator.
                calculateTypeCompatibility(simpleString, simpleString);
        assertTrue(result.getSeverity() == Diagnostic.OK);
    }
    
    /**
     * test type compatibility coercing to integer
     */
    public void testIntegerCoercions()
    {
        // simple longs can coerce to integer
        Diagnostic result = 
            TypeComparator.
                calculateTypeCompatibility(simpleInteger, simpleLong);
        assertTrue(result.getSeverity() == Diagnostic.OK);
        
        // simple doubles can coerce to integer
        result = 
            TypeComparator.
                calculateTypeCompatibility(simpleInteger, simpleDouble);
        assertTrue(result.getSeverity() == Diagnostic.OK);

        // strings can coerce to integer
        result = 
            TypeComparator.
                calculateTypeCompatibility(simpleInteger, simpleString);
        assertTrue(result.getSeverity() == Diagnostic.OK);
        
        // booleans cannot coerce to integer
        result = 
            TypeComparator.
                calculateTypeCompatibility(simpleInteger, simpleBoolean);
        assertFalse(result.getSeverity() == Diagnostic.OK);
    }

    /**
     * test type compatibility coercing to long
     */
    public void testLongCoercions()
    {
        // simple integer can coerce to long
        Diagnostic result = 
            TypeComparator.
                calculateTypeCompatibility(simpleLong, simpleInteger);
        assertTrue(result.getSeverity() == Diagnostic.OK);
        
        // simple doubles can coerce to long
        result = 
            TypeComparator.
                calculateTypeCompatibility(simpleLong, simpleDouble);
        assertTrue(result.getSeverity() == Diagnostic.OK);

        // strings can coerce to long
        result = 
            TypeComparator.
                calculateTypeCompatibility(simpleLong, simpleString);
        assertTrue(result.getSeverity() == Diagnostic.OK);
        
        // booleans cannot coerce to long
        result = 
            TypeComparator.
                calculateTypeCompatibility(simpleLong, simpleBoolean);
        assertFalse(result.getSeverity() == Diagnostic.OK);
    }

    /**
     * test type compatibility coercing to double
     */
    public void testDoubleCoercions()
    {
        // simple longs can coerce to double
        Diagnostic result = 
            TypeComparator.
                calculateTypeCompatibility(simpleDouble, simpleLong);
        assertTrue(result.getSeverity() == Diagnostic.OK);
        
        // simple integer can coerce to double
        result = 
            TypeComparator.
                calculateTypeCompatibility(simpleDouble, simpleInteger);
        assertTrue(result.getSeverity() == Diagnostic.OK);

        // strings can coerce to double
        result = 
            TypeComparator.
                calculateTypeCompatibility(simpleDouble, simpleString);
        
        assertTrue(result.getSeverity() == Diagnostic.OK);
        
        // booleans cannot coerce to double
        result = 
            TypeComparator.
                calculateTypeCompatibility(simpleDouble, simpleBoolean);
        
        assertFalse(result.getSeverity() == Diagnostic.OK);
    }
    /**
     * test type compatibility coercing to string
     */
    public void testStringCoercions()
    {
        // everything coerces to String
        Diagnostic result = 
            TypeComparator.
                calculateTypeCompatibility(simpleString, simpleLong);
        assertTrue(result.getSeverity() == Diagnostic.OK);
        
        // everything coerces to String
        result = 
            TypeComparator.
                calculateTypeCompatibility(simpleString, simpleDouble);
        assertTrue(result.getSeverity() == Diagnostic.OK);

        // everything coerces to String
        result = 
            TypeComparator.
                calculateTypeCompatibility(simpleString, simpleInteger);
        assertTrue(result.getSeverity() == Diagnostic.OK);
        
        // everything coerces to String
        result = 
            TypeComparator.
                calculateTypeCompatibility(simpleString, simpleBoolean);
        assertTrue(result.getSeverity() == Diagnostic.OK);
    }
    
    /**
     * Test compatibilty of boxed types when their simple type is expected
     */
    public void testSimpleToBoxConversions()
    {
        // Integer should convert to int
        Diagnostic result =
            TypeComparator.
                calculateTypeCompatibility(simpleInteger, boxedInteger);
        assertTrue(result.getSeverity() == Diagnostic.OK);
        
        // Long should convert to long
        result =
            TypeComparator.
                calculateTypeCompatibility(simpleLong, boxedLong);
        assertTrue(result.getSeverity() == Diagnostic.OK);
        
        // Double should convert to double
        result =
            TypeComparator.
                calculateTypeCompatibility(simpleDouble, boxedDouble);
        assertTrue(result.getSeverity() == Diagnostic.OK);

        // Boolean should convert to boolean
        result =
            TypeComparator.
                calculateTypeCompatibility(simpleBoolean, boxedBoolean);
        assertTrue(result.getSeverity() == Diagnostic.OK);
    }
    
    /**
     * Test compatibilty of simple types when their boxed type is expected
     */
    public void testBoxToSimpleConversions()
    {
        // int should convert to Integer
        Diagnostic result =
            TypeComparator.
                calculateTypeCompatibility(boxedInteger, simpleInteger);
        assertTrue(result.getSeverity() == Diagnostic.OK);
        
        // long should convert to Long
        result =
            TypeComparator.
                calculateTypeCompatibility(boxedLong, simpleLong);
        assertTrue(result.getSeverity() == Diagnostic.OK);
        
        // double should convert to Double
        result =
            TypeComparator.
                calculateTypeCompatibility(boxedDouble, simpleDouble);
        assertTrue(result.getSeverity() == Diagnostic.OK);

        // boolean should convert to Boolean
        result =
            TypeComparator.
                calculateTypeCompatibility(boxedBoolean, simpleBoolean);
        assertTrue(result.getSeverity() == Diagnostic.OK);
    }
    
    /**
     * Test compatiblity when an interface is expected against a list of
     * possible types
     */
    public void testObjectLists()
    {
        // check for comparable
        Diagnostic result =
            TypeComparator.
                calculateTypeCompatibility(comparableType, objectAndComparable);
        assertTrue(result.getSeverity() == Diagnostic.OK);
        
        // not comparable
        result = TypeComparator.
                calculateTypeCompatibility(comparableType, objectAndCollection);
        assertFalse(result.getSeverity() == Diagnostic.OK);

        // check for map
        result =
            TypeComparator.
                calculateTypeCompatibility(mapType, objectAndMap);
        assertTrue(result.getSeverity() == Diagnostic.OK);

        // not map
        result = TypeComparator.
                calculateTypeCompatibility(mapType, objectAndCollection);
        assertFalse(result.getSeverity() == Diagnostic.OK);
    }
    
    /**
     * Tests assignability, for example when read/write expected, but only
     * read-only returned.
     */
    public void testAssignability()
    {
        // can assign read/write to read-only
        Diagnostic result =
            TypeComparator.calculateTypeCompatibility(simpleLong, readWritePrimitiveLong);
        assertTrue(result.getSeverity() == Diagnostic.OK);
        
        // can NOT assign read-only to read/write
        result =
            TypeComparator.calculateTypeCompatibility(readWritePrimitiveLong, simpleLong);
        assertFalse(result.getSeverity() == Diagnostic.OK);
        
        // check bi-directional type comparison -- e.g when a read/write string
        // is expected, the read side will always work fine because you can always
        // coerce to string.  But the other direction may not work because
        // you may not be able to coerce the string to the other thing
        result =
            TypeComparator.calculateTypeCompatibility(readWriteString, readWriteObject);
        assertFalse(result.getSeverity() == Diagnostic.OK);
    }
    
}
