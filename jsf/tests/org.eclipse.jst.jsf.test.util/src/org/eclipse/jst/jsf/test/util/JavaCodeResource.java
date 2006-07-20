package org.eclipse.jst.jsf.test.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.osgi.framework.Bundle;

/**
 * Represents a piece of java code (usually a full compilation unit) that is loaded
 * from a static test file somewhere.  
 * 
 * @author cbateman
 *
 */
public class JavaCodeResource 
{
    private String      _code;
    
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
            ByteArrayOutputStream bufferStream = new ByteArrayOutputStream();
            byte[]  buffer = new byte[2048];
            int bytesRead = 0;
            
            while ((bytesRead = stream.read(buffer)) != -1)
            {
                bufferStream.write(buffer, 0, bytesRead);
            }
            
            _code = bufferStream.toString();
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
     * @return the code or null if failed to load
     */
    public String getCode()
    {
        return _code;
    }
}
