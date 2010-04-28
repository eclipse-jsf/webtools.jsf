package org.eclipse.jst.jsf.designtime.tests.resources;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import org.eclipse.jst.jsf.common.internal.resource.ContentTypeResolver;
import org.eclipse.jst.jsf.designtime.internal.resources.JSFResource;
import org.eclipse.jst.jsf.designtime.internal.resources.ResourceIdentifier;
import org.eclipse.jst.jsf.test.util.junit4.NoPluginEnvironment;
import org.eclipse.jst.jsf.test.util.mock.MockContentTypeManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(NoPluginEnvironment.class)
public class TestJSFResource
{
    private TestableJSFResource _xhtmlJsfResource;
    private TestableJSFResource _xhtmlNotAJSFResource;

    @Before
    public void testSetup() throws Exception
    {
        final ContentTypeResolver contentTypeResolver = new ContentTypeResolver(new MockContentTypeManager());
        _xhtmlJsfResource = new TestableJSFResource(new ResourceIdentifier("someResource.xhtml"), contentTypeResolver);
        _xhtmlNotAJSFResource = new TestableJSFResource(new ResourceIdentifier("someResource.foo"), contentTypeResolver);
    }
    
    @Test
    public void testIsContentType()
    {
        assertTrue(_xhtmlJsfResource.isContentType("org.eclipse.wst.html.core.htmlsource"));
        assertFalse(_xhtmlNotAJSFResource.isContentType("org.eclipse.wst.html.core.htmlsource"));    }
     
    
    
    private final static class TestableJSFResource extends JSFResource
    {

        private TestableJSFResource(final ResourceIdentifier id,
                final ContentTypeResolver contentTypeResolver)
        {
            super(id, contentTypeResolver);
        }

        @Override
        public boolean isAccessible()
        {
            return true;
        }
    }
}
