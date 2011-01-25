package org.eclipse.jst.jsf.test.util.mock;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

public class MockMarker implements IMarker
{
    private final Map<String, Object> _attributes = new HashMap<String, Object>();
    private final IResource _resource;

    public MockMarker(final IResource resource)
    {
        _resource = resource;
    }

    public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter)
    {
        throw new UnsupportedOperationException();

    }

    public void delete() throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public boolean exists()
    {
        return true;
    }

    public Object getAttribute(String attributeName) throws CoreException
    {
        return _attributes.get(attributeName);
    }

    public int getAttribute(String attributeName, int defaultValue)
    {
        Object object = _attributes.get(attributeName);
        if (object instanceof Integer)
        {
            return ((Integer)object).intValue();
        }
        return defaultValue;
    }

    public String getAttribute(String attributeName, String defaultValue)
    {
        Object object = _attributes.get(attributeName);
        if (object instanceof String)
        {
            return ((String)object);
        }
        return defaultValue;
    }

    public boolean getAttribute(String attributeName, boolean defaultValue)
    {
        Object object = _attributes.get(attributeName);
        if (object instanceof Boolean)
        {
            return ((Boolean)object).booleanValue();
        }
        return defaultValue;
    }

    public Map<String, Object> getAttributes() throws CoreException
    {
        return Collections.unmodifiableMap(_attributes);
    }

    public Object[] getAttributes(String[] attributeNames) throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public long getCreationTime() throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public long getId()
    {
        throw new UnsupportedOperationException();
    }

    public IResource getResource()
    {
        return _resource;
    }

    public String getType() throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public boolean isSubtypeOf(String superType) throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public void setAttribute(String attributeName, int value)
            throws CoreException
    {
        setAttribute(attributeName, Integer.valueOf(value));
    }

    public void setAttribute(String attributeName, Object value)
            throws CoreException
    {
        _attributes.put(attributeName, value);
    }

    public void setAttribute(String attributeName, boolean value)
            throws CoreException
    {
        setAttribute(attributeName, Boolean.valueOf(value));
    }

    public void setAttributes(String[] attributeNames, Object[] values)
            throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    @SuppressWarnings("unchecked")
    public void setAttributes(@SuppressWarnings("rawtypes") Map attributes)
            throws CoreException
    {
        _attributes.putAll(attributes);
    }

}
