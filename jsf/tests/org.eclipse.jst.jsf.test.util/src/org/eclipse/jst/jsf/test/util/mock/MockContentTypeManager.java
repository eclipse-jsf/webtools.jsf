package org.eclipse.jst.jsf.test.util.mock;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.core.runtime.content.IContentTypeManager;
import org.eclipse.core.runtime.content.IContentTypeMatcher;
import org.eclipse.core.runtime.preferences.IScopeContext;

public class MockContentTypeManager implements IContentTypeManager
{
    private final Map<String, IContentType> _idToContentType;
    private final static Map<String, IContentType>  DEFAULT_CONTENTTYPE_MAPPINGS;
    
    static
    {
        Map<String, IContentType> map = new HashMap<String, IContentType>();
        map.put("org.eclipse.wst.html.core.htmlsource", new MockContentType(Collections.singleton("xhtml")));
        DEFAULT_CONTENTTYPE_MAPPINGS = Collections.unmodifiableMap(map); 
    }
    /**
     * Construct with a default set of extension to type mappings
     */
    public MockContentTypeManager()
    {
        this(DEFAULT_CONTENTTYPE_MAPPINGS);
    }
    
    public MockContentTypeManager(
            final Map<String, IContentType> idToContentType)
    {
        _idToContentType = idToContentType;
    }

    public IContentType findContentTypeFor(final InputStream contents,
            final String fileName) throws IOException
    {
        throw new UnsupportedOperationException();
    }

    public IContentType findContentTypeFor(final String fileName)
    {

        throw new UnsupportedOperationException();
    }

    public IContentType[] findContentTypesFor(final InputStream contents,
            final String fileName) throws IOException
    {

        throw new UnsupportedOperationException();
    }

    public IContentType[] findContentTypesFor(final String fileName)
    {

        throw new UnsupportedOperationException();
    }

    public IContentDescription getDescriptionFor(final InputStream contents,
            final String fileName, final QualifiedName[] options)
            throws IOException
    {

        throw new UnsupportedOperationException();
    }

    public IContentDescription getDescriptionFor(final Reader contents,
            final String fileName, final QualifiedName[] options)
            throws IOException
    {

        throw new UnsupportedOperationException();
    }

    public void addContentTypeChangeListener(
            final IContentTypeChangeListener listener)
    {
        throw new UnsupportedOperationException();
    }

    public IContentType[] getAllContentTypes()
    {

        throw new UnsupportedOperationException();
    }

    public IContentType getContentType(final String contentTypeIdentifier)
    {
        return _idToContentType.get(contentTypeIdentifier);
    }

    public IContentTypeMatcher getMatcher(final ISelectionPolicy customPolicy,
            final IScopeContext context)
    {

        throw new UnsupportedOperationException();
    }

    public void removeContentTypeChangeListener(
            final IContentTypeChangeListener listener)
    {
        throw new UnsupportedOperationException();
    }

}
