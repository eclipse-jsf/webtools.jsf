/*******************************************************************************
 * Copyright (c) 2006, 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.common.internal.types;


/**
 * Encodes a type signature and a string that has some literal value based on
 * the type signature.  The class represents only a value object -- no attempt
 * is made to assert that the literalValue is really of the type specified.
 * 
 * @author cbateman
 *
 */
public abstract class LiteralType extends ValueType
{
    
    /**
     * Consider a new literal type
     * 
     * @param signature
     */
    protected LiteralType(final String signature)
    {
        super(signature, IAssignable.ASSIGNMENT_TYPE_RHS);
    }

    /**
     * @return the literal value string (unparsed)
     */
    public abstract String getLiteralValue();

    /**
     * @return the actual untranslated literal value as an object
     */
    public abstract Object getLiteralValueRaw();
    
    /**
     * @return the type signature
     */
    public final String getSignature() {
        return super.getSignature();
    }
    
    /**
     * @return a type signature for the kind of number this literal will coerce
     * into when asked to become a number or null if this cannot be determined
     * @throws TypeCoercionException if this literal has no legal coercion into
     * a number
     */
    public String getNumberCoercion() throws TypeCoercionException
    {
       // always box before coercion 
       return
           TypeCoercer.coerceToNumber(
                   TypeTransformer.transformBoxPrimitives(getSignature()));
    }
    
    /**
     * @param T
     * @return a Number coercion of the literal's value, null if indeterminate
     * @throws TypeCoercionException if the coercion is illegal
     */
    public abstract Number coerceToNumber(Class T) throws TypeCoercionException;
    
    /**
     * @return a Boolean coercion of the literal's value, null if indeterminate
     * @throws TypeCoercionException if the coercion is illegal
     */
    public abstract Boolean coerceToBoolean() throws TypeCoercionException;
}
