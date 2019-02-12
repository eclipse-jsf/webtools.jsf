/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.runtime.internal.model.bean;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;

/**
 * A special object used in place of Object to ensure that when an interface
 * requires a generic #{@link java.lang.Object}, it won't throw serialization
 * exception when a containing object is serialized.
 * 
 * @author cbateman
 * 
 */
public class SerializableObject implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 9133733048469500692L;
    private Object      _maybeSerializable;
    
    /**
     * @param maybeSerializable
     */
    public SerializableObject(Object maybeSerializable)
    {
        _maybeSerializable = maybeSerializable;
    }
    
    
//    /**
//     * Provided to support serialization.  Should not be used by sub-classes
//     * or clients except in this regard.
//     */
//    protected SerializableObject()
//    {
//        _maybeSerializable = null;
//    }
    
    /**
     * @return the actual value
     */
    public final Object getMaybeSerializable() {
        return _maybeSerializable;
    }


    private void writeObject(java.io.ObjectOutputStream out)
        throws IOException
    {
        try
        {
            out.writeObject(_maybeSerializable);
        }
        catch (NotSerializableException nse)
        {
            // do nothing, the object isn't guaranteed to be serializable,
            // but we don't want this be an error
            out.writeObject(null);
        }
    }
    
    private void readObject(java.io.ObjectInputStream in)
        throws IOException, ClassNotFoundException
    {
        _maybeSerializable = in.readObject();
    }
}
