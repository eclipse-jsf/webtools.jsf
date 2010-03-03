package org.eclipse.jst.jsf.test.util.mock;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.IModelProviderListener;

public class MockModelProvider implements IModelProvider
{
    private final Object _modelObject;

    public MockModelProvider(final Object modelObject)
    {
        _modelObject = modelObject;
    }
    public Object getModelObject()
    {
        return _modelObject;
    }

    public Object getModelObject(IPath modelPath)
    {
        throw new UnsupportedOperationException();
    }

    public void modify(Runnable runnable, IPath modelPath)
    {
        throw new UnsupportedOperationException();
    }

    public IStatus validateEdit(IPath modelPath, Object context)
    {
        throw new UnsupportedOperationException();
    }

    public void addListener(IModelProviderListener listener)
    {
       throw new UnsupportedOperationException();
    }

    public void removeListener(IModelProviderListener listener)
    {
        throw new UnsupportedOperationException();
    }

}
