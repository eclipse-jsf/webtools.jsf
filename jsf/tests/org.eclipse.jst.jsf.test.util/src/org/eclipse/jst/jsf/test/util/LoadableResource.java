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

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.osgi.framework.Bundle;

/**
 * A resource that can be loaded into memory in a test plugin friendly way
 * 
 * @author cbateman
 */
public abstract class LoadableResource 
{

    /**
     * Attempts to load the code from the path relative to bundle
     * 
     * @param bundle
     * @param path
     * @throws IOException
     */
    public void load(Bundle  bundle, String path) throws IOException 
    {
        URL url = bundle.getEntry(path);
        
        if (url == null)
        {
            throw new IOException("Could not locate "+path+" in bundle "+bundle.getSymbolicName());
        }
        
        InputStream stream = null;
        
        try
        {
            stream = url.openStream();
            byte[]  buffer = new byte[2048];
            int bytesRead = 0;
            
            while ((bytesRead = stream.read(buffer)) != -1)
            {
                bufferLoaded(buffer, bytesRead);
            }
        }
        finally
        {
            if (stream != null)
            {
                stream.close();
            }
        }
    }

    /**
     * Called by load to indicate that numBytes starting from offset 0
     * have been loaded.  Sub-classes must implement to handle the buffer,
     * usually it will be appended into a running buffer until loadCompleted
     * is called to indicate that all data has been loaded. 
     * 
     * @param buffer
     * @param numBytes
     */
    protected abstract void bufferLoaded(byte[] buffer, int numBytes);
    
}
