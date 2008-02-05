package org.eclipse.jst.jsf.core.tests.validation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.wst.validation.internal.provisional.core.IValidationContext;

public class MockIValidationContext implements IValidationContext
{
    private final List<IFile> 			_files;

    public MockIValidationContext(final List<IFile> files)
    {
    	_files = files;
    }
    
    public String[] getURIs()
    {
        final List<String>  uris = new ArrayList<String>();
        
        for (final IFile file : _files)
        {
        	uris.add(file.getFullPath().toString());
        }
        return uris.toArray(new String[uris.size()]);
    }

    public Object loadModel(String symbolicName)
    {
        throw new UnsupportedOperationException("currently not used in testing");
    }

    public Object loadModel(String symbolicName, Object[] parms)
    {
        throw new UnsupportedOperationException("currently not used in testing");
    }

}
