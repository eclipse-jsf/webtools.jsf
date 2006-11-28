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

package org.eclipse.jst.jsf.validation.internal.el;

import org.eclipse.jst.jsf.common.internal.types.SignatureBasedType;
import org.eclipse.jst.jsf.common.internal.types.ValueType;

/**
 * Used to track the evaulation of an EL expression AST
 * 
 * @author cbateman
 *
 */
class EvaluationTracker 
{
    private ValueExpressionTracker  _valueTracker;
    private SignatureBasedType      _type;
    
    /**
     * @return the current value expression tracker or null if not set
     */
    public ValueExpressionTracker getValueTracker() {
        return _valueTracker;
    }

    /**
     * @param valueTracker
     */
    public void setValueTracker(ValueExpressionTracker valueTracker) {
        _valueTracker = valueTracker;
    }

    /**
     * @return the currently resolved type or null if not yet resolved
     * or not able to resolve
     */
    public SignatureBasedType getType() {
        return _type;
    }

    /**
     * @param type
     */
    public void setType(SignatureBasedType type) {
        _type = type;
    }
    
    /**
     * @return if getType() is a ValueType, returns it otherwise null
     */
    public ValueType getValueType()
    {
        if (_type instanceof ValueType)
        {
            return (ValueType) _type;
        }
        
        return null;
    }
}
