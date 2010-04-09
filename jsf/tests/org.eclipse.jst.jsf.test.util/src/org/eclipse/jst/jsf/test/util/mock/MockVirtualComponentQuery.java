package org.eclipse.jst.jsf.test.util.mock;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.internal.componentcore.AbstractVirtualComponentQuery;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;

public class MockVirtualComponentQuery extends
        AbstractVirtualComponentQuery
{

    private final IContainer _webContentFolder;

    /**
     * @param webContentFolder
     */
    public MockVirtualComponentQuery(final IContainer webContentFolder)
    {
        _webContentFolder = webContentFolder;
    }

    @Override
    public IVirtualFolder getWebContentFolder(IProject project)
    {
        return new MockVirtualFolder(_webContentFolder);
    }
}