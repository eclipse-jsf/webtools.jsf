package org.eclipse.jst.jsf.designtime.tests.resources;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import org.eclipse.core.runtime.Path;
import org.eclipse.jst.jsf.common.internal.resource.ContentTypeResolver;
import org.eclipse.jst.jsf.designtime.internal.resources.ResourceIdentifierFactory;
import org.eclipse.jst.jsf.designtime.internal.resources.ResourceIdentifierFactory.InvalidIdentifierException;
import org.eclipse.jst.jsf.designtime.internal.resources.WorkspaceJSFResource;
import org.eclipse.jst.jsf.test.util.junit4.NoPluginEnvironment;
import org.eclipse.jst.jsf.test.util.mock.MockContentTypeManager;
import org.eclipse.jst.jsf.test.util.mock.MockFile;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(NoPluginEnvironment.class)
public class TestWorkspaceBasedJSFResource
{
    private WorkspaceJSFResource _res;
    private MockFile _file;

    @Before
    public void setUp() throws Exception
    {
        _file = new MockFile(new Path("SomeProject/WebContent/resources/mylib/tag1.xhtml"));
        _res = new WorkspaceJSFResource(new ResourceIdentifierFactory().createLibraryResource("mylib/tag1.xhtml"), _file, new ContentTypeResolver(new MockContentTypeManager()));
    }
    
    @Test
    public void testIsAccessible() throws InvalidIdentifierException
    {
        // isAccessible on this type of JSFResource is simply a pass through
        // to isAccessible on the underlying resource
        _file.setExists(true);
        assertTrue(_res.isAccessible());

        _file.setExists(false);
        assertFalse(_res.isAccessible());
    }
    
    @Test
    public void testGetResource()
    {
        assertEquals(_file, _res.getResource());
    }
}
