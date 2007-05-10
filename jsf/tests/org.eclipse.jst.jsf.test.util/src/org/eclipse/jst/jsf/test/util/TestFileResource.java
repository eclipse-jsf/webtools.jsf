/*******************************************************************************
 * Copyright (c) 2001, 2006 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.test.util;

import java.io.ByteArrayOutputStream;



/**
 * Represents a piece of java code (usually a full compilation unit) that is loaded
 * from a static test file somewhere.  
 * 
 * @author cbateman
 *
 */
public class TestFileResource extends LoadableResource
{
    private ByteArrayOutputStream   _buffer = new ByteArrayOutputStream();
    
    /**
     * @return the contents
     */
    public String toString()
    {
        return _buffer.toString();
    }

    /**
     * @return the contents as a byte array
     */
    public byte[] toBytes()
    {
        return _buffer.toByteArray();
    }
    
    protected void bufferLoaded(byte[] buffer, int numBytes) 
    {
        _buffer.write(buffer, 0, numBytes);
    }
}
