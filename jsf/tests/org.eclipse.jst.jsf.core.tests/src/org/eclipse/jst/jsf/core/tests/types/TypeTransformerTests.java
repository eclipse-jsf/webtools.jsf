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

import org.eclipse.jst.jsf.common.internal.types.CompositeType;
import org.eclipse.jst.jsf.common.internal.types.IAssignable;
import org.eclipse.jst.jsf.common.internal.types.TypeTransformer;

/**
 * @author cbateman
 *
 */
public class TypeTransformerTests extends TestCase {

    private final static CompositeType primitiveLong = 
        new CompositeType("J", IAssignable.ASSIGNMENT_TYPE_RHS);
    
    private final static CompositeType primitiveInteger =
        new CompositeType("I", IAssignable.ASSIGNMENT_TYPE_RHS);
        
    private final static CompositeType primitiveLongArray =
        new CompositeType("[J", IAssignable.ASSIGNMENT_TYPE_RHS);
    
    private final static CompositeType primitiveIntegerArrayArray =
        new CompositeType("[[I", IAssignable.ASSIGNMENT_TYPE_RHS);

    private final static CompositeType boxedLong = 
        new CompositeType("Ljava.lang.Long;", IAssignable.ASSIGNMENT_TYPE_RHS);
    
    private final static CompositeType boxedInteger =
        new CompositeType("Ljava.lang.Integer;", IAssignable.ASSIGNMENT_TYPE_RHS);
    
    private final static CompositeType boxedLongArray =
        new CompositeType("[Ljava.lang.Long;", IAssignable.ASSIGNMENT_TYPE_RHS);
    
    private final static CompositeType boxedIntegerArrayArray =
        new CompositeType("[[Ljava.lang.Integer;", IAssignable.ASSIGNMENT_TYPE_RHS);

    /**
     * Simple test for boxing primitives
     */
    public void testBoxPrimitives()
    {
        CompositeType newType = TypeTransformer.transformBoxPrimitives(primitiveLong);
        assertTrue(exactMatch(newType, boxedLong));
        newType = TypeTransformer.transformBoxPrimitives(primitiveInteger);
        assertTrue(exactMatch(newType, boxedInteger));
        newType = TypeTransformer.transformBoxPrimitives(primitiveLongArray);
        assertTrue(exactMatch(newType, boxedLongArray));
        newType = TypeTransformer.transformBoxPrimitives(primitiveIntegerArrayArray);
        assertTrue(exactMatch(newType, boxedIntegerArrayArray));
    }
    
    /**
     * Simple test for unboxing primitives
     */
    public void testUnboxPrimitives()
    {
        CompositeType newType = TypeTransformer.transformUnboxPrimitives(boxedLong);
        assertTrue(exactMatch(newType, primitiveLong));
        newType = TypeTransformer.transformUnboxPrimitives(boxedInteger);
        assertTrue(exactMatch(newType, primitiveInteger));
        newType = TypeTransformer.transformUnboxPrimitives(boxedLongArray);
        assertTrue(exactMatch(newType, primitiveLongArray));
        newType = TypeTransformer.transformUnboxPrimitives(boxedIntegerArrayArray);
        assertTrue(exactMatch(newType, primitiveIntegerArrayArray));
     }


    private static boolean exactMatch(CompositeType t1, CompositeType t2)
    {
        String[] s1 = t1.getSignatures();
        String[] s2 = t2.getSignatures();
        
        if (s1.length != s2.length)
        {
            return false;
        }
        
        for (int i = 0; i < s1.length; i++)
        {
            if (!s1[i].equals(s2[i]))
            {
                return false;
            }
        }
        
        // if we get to here, just verify assignability
        if (t1.getAssignmentTypeMask() != t2.getAssignmentTypeMask())
        {
            return false;
        }
        
        // if we get to here, then signatures and assignability match
        // exactly.  So exactMatch
        return true;
    }
}
