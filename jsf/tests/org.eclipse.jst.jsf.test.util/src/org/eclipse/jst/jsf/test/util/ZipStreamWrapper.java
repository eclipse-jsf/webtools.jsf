/*******************************************************************************
 * Copyright (c) 2004, 2010 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.test.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipInputStream;

/**
 * @author Yang Liu
 * @version 
 */
public class ZipStreamWrapper extends InputStream
{

    private ZipInputStream zipStream;

    /**
     * 
     */
    public ZipStreamWrapper(ZipInputStream stream)
    {
        super();
        this.zipStream = stream;
    }

    /* (non-Javadoc)
     * @see java.io.InputStream#read()
     */
    public int read() throws IOException
    {
        return zipStream.read();
    }

    /* (non-Javadoc)
     * @see java.io.InputStream#read(byte[])
     */
    public int read(byte[] b) throws IOException
    {
        return zipStream.read(b);
    }
    
    /* (non-Javadoc)
     * @see java.io.InputStream#read(byte[], int, int)
     */
    public int read(byte[] b, int off, int len) throws IOException
    {
        return zipStream.read(b, off, len);
    }
}
