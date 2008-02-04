package org.eclipse.jst.jsf.core.tests.validation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.wst.validation.internal.provisional.core.IValidationContext;

public class MockIValidationContext implements IValidationContext
{
    private final ArrayList<String> _uris;

    public MockIValidationContext(final List<String> uris)
    {
        _uris = new ArrayList<String>(uris);
    }

    public String[] getURIs()
    {
        return _uris.toArray(new String[0]);
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
