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
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;

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
    private final CompositeType writeOnlyObject =
        new CompositeType("Ljava.lang.Object;",IAssignable.ASSIGNMENT_TYPE_LHS);
    
    // method types
    private final static String actionMethodSig = 
        Signature.createMethodSignature(new String[0]
                                      , "Ljava.lang.String;");
    private final static String actionListenerSig = 
        Signature.createMethodSignature
            (new String[]{"Ljavax.faces.event.ActionEvent;"}
                          , Signature.SIG_VOID);
    private final static String hasReturnAndArgSig =
        Signature.createMethodSignature
            (new String[]{"Ljavax.faces.event.ActionEvent;"}
                        , "Ljava.lang.String;");
    private final static String sameNumArgsDiffTypeSig =
        Signature.createMethodSignature
        (new String[]{"Ljava.lang.String;"}
                    , "Ljava.lang.String;");
    
    private final CompositeType actionMethod =
        new CompositeType(actionMethodSig, 
                IAssignable.ASSIGNMENT_TYPE_NONE);
    private final CompositeType actionListener = 
        new CompositeType(actionListenerSig, 
                IAssignable.ASSIGNMENT_TYPE_NONE);
    private final CompositeType hasReturnAndArg =
        new CompositeType(hasReturnAndArgSig, 
                IAssignable.ASSIGNMENT_TYPE_NONE);
    
    private final CompositeType actionAndActionListener =
        new CompositeType(new String[] {actionMethodSig, actionListenerSig}
                         , IAssignable.ASSIGNMENT_TYPE_NONE);
    private final CompositeType sameNumArgsDiffType =
        new CompositeType(new String[] {sameNumArgsDiffTypeSig}
                         , IAssignable.ASSIGNMENT_TYPE_NONE);
    
    private final CompositeType takesUnboxedInt =
        new CompositeType(Signature.createMethodSignature(new String[]{Signature.SIG_INT}, Signature.SIG_VOID)
        , IAssignable.ASSIGNMENT_TYPE_NONE);
    private final CompositeType takesBoxedInt =
        new CompositeType(Signature.createMethodSignature(new String[]{TypeConstants.TYPE_BOXED_INTEGER}, Signature.SIG_VOID)
        , IAssignable.ASSIGNMENT_TYPE_NONE);
        
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

    public void testBooleanCoercions()
    {
        // noop: boolean should always coerce to boolean
        Diagnostic result =
            TypeComparator.calculateTypeCompatibility
                (simpleBoolean, simpleBoolean);
        assertTrue(result.getSeverity() == Diagnostic.OK);
        
        // noop: works also for boxed
        result =
            TypeComparator.calculateTypeCompatibility
                (simpleBoolean, boxedBoolean);
        assertTrue(result.getSeverity() == Diagnostic.OK);

        // noop: works also for boxed
        result =
            TypeComparator.calculateTypeCompatibility
                (boxedBoolean, simpleBoolean);
        assertTrue(result.getSeverity() == Diagnostic.OK);

        // always coerce strings to booleans
        result =
            TypeComparator.calculateTypeCompatibility
                (simpleBoolean, simpleString);
        assertTrue(result.getSeverity() == Diagnostic.OK);
        
        // no number -> boolean coercion
        result =
            TypeComparator.calculateTypeCompatibility
                (simpleBoolean, simpleDouble);
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
        
        // this should fail because a readable object is expected, but one
        // is not provided (i.e. "not gettable")
        result =
            TypeComparator.calculateTypeCompatibility(readWriteObject, writeOnlyObject);
        assertFalse(result.getSeverity() == Diagnostic.OK);
    }
    
    public void testMethodSignatures()
    {
        // compare a method to itself should always work; return type, no args
        Diagnostic result = 
            TypeComparator.calculateTypeCompatibility(actionMethod, actionMethod);
        assertTrue(result.getSeverity() == Diagnostic.OK);
        
        // compare same when method has no return but args
        result =
            TypeComparator.calculateTypeCompatibility(actionListener, actionListener);
        assertTrue(result.getSeverity() == Diagnostic.OK);
        
        // methods are not compatible
        result =
            TypeComparator.calculateTypeCompatibility(actionMethod, actionListener);
        assertFalse(result.getSeverity() == Diagnostic.OK);

        // reverse order makes no diff
        result =
            TypeComparator.calculateTypeCompatibility(actionListener,actionMethod);
        assertFalse(result.getSeverity() == Diagnostic.OK);

        // same signature must succeed
        result =
            TypeComparator.calculateTypeCompatibility(hasReturnAndArg,hasReturnAndArg);
        assertTrue(result.getSeverity() == Diagnostic.OK);

        // boxed vs. unboxed arguments should succeed for same type
        result = TypeComparator.calculateTypeCompatibility(takesBoxedInt, takesUnboxedInt);
        assertTrue(result.getSeverity() == Diagnostic.OK);

        // and in reverse...
        result = TypeComparator.calculateTypeCompatibility(takesUnboxedInt, takesBoxedInt);
        assertTrue(result.getSeverity() == Diagnostic.OK);

        // won't satify, event though return matches
        result = TypeComparator.calculateTypeCompatibility(actionMethod, hasReturnAndArg);
        assertFalse(result.getSeverity() == Diagnostic.OK);

        // nor in reverse..
        result = TypeComparator.calculateTypeCompatibility(hasReturnAndArg, actionMethod);
        assertFalse(result.getSeverity() == Diagnostic.OK);

        // won't satify, event though args matches
        result = TypeComparator.calculateTypeCompatibility(actionListener, hasReturnAndArg);
        assertFalse(result.getSeverity() == Diagnostic.OK);

        // nor in reverse...
        result = TypeComparator.calculateTypeCompatibility(hasReturnAndArg, actionListener);
        assertFalse(result.getSeverity() == Diagnostic.OK);
        
        // check same return and arg count, diff arg
        result = TypeComparator.calculateTypeCompatibility(hasReturnAndArg, sameNumArgsDiffType);
        assertFalse(result.getSeverity() == Diagnostic.OK);

        
    // test multiple
        
        // this will succeed because actionMethod is in the list
        result = TypeComparator.calculateTypeCompatibility(actionAndActionListener, actionMethod);
        assertTrue(result.getSeverity() == Diagnostic.OK);
        
        // this will succeed because actionListener is in the list
        result = TypeComparator.calculateTypeCompatibility(actionAndActionListener, actionListener);
        assertTrue(result.getSeverity() == Diagnostic.OK);

        // this will fail because hasReturnAndArg is not in the list
        result = TypeComparator.calculateTypeCompatibility(actionAndActionListener, hasReturnAndArg);
        assertFalse(result.getSeverity() == Diagnostic.OK);
    }
    
    public void testValueMethodMix()
    {
        // a value binding will never be compatible with a method binding
        Diagnostic result = 
            TypeComparator.calculateTypeCompatibility(actionMethod, readWriteObject);
        assertFalse(result.getSeverity() == Diagnostic.OK);
        
        // nor in reverse...
        result = 
            TypeComparator.calculateTypeCompatibility(readWriteObject,actionMethod);
        assertFalse(result.getSeverity() == Diagnostic.OK);
    }
}
