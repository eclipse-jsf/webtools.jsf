package org.eclipse.jst.jsf.test.util.sanity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;

import junit.framework.TestCase;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.jsf.test.util.Activator;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.TestFileResource;

public class TestJSFTestUtil extends TestCase 
{
    public void testSavePlatformRelative()
    {
        FileInputStream checkFile = null;
        try
        {
            final  String       relativeSavePath = ".metadata/.plugins/dummyFile";
            
            TestFileResource res = new TestFileResource();
            res.load(Activator.getDefault().getBundle(), "/testdata/dummyFile");
            
            JSFTestUtil.savePlatformRelative(res, relativeSavePath);
            
            checkFile = new FileInputStream(new File(new URL(Platform.getInstanceLocation().getURL(), relativeSavePath).toURI()));
            
            // NOTE: DON'T use areEqual in JSFTestUtil, since that module is under test
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            byte[]  inBuffer = new byte[1024];
            int bytesRead;
            int curPos = 0;
            while ((bytesRead = checkFile.read(inBuffer)) != -1)
            {
                buffer.write(inBuffer,curPos, bytesRead);
                curPos+=bytesRead;
            }
            
            // ensure the file that was written is the same as the source
            assertTrue(Arrays.equals(buffer.toByteArray(), res.toBytes()));
        }
        catch(IOException ioe)
        {
            throw new RuntimeException(ioe);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        finally
        {
            if (checkFile != null)
            {
                try
                {
                    checkFile.close();
                }
                catch (IOException ioe)
                {
                    throw new RuntimeException(ioe);
                }
            }
        }
    }
}
