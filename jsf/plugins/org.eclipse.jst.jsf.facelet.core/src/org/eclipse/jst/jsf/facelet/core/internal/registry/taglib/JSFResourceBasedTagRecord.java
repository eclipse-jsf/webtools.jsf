package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.jsf.designtime.internal.resources.JSFResource;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibFactory;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag;

/**
 * A facelet tag record is derived from a JSF locatable resource (ezcomp).
 * 
 * @author cbateman
 * 
 */
public class JSFResourceBasedTagRecord extends FaceletTagRecord
{
    private final List<FaceletTaglibTag> _tags;
    private final String _uri;

    /**
     * @param uri
     * @param tags
     */
    public JSFResourceBasedTagRecord(final String uri,
            final List<FaceletTaglibTag> tags)
    {
        _uri = uri;
        _tags = tags;
    }

    /**
     * 
     */
    private static final long serialVersionUID = 5944923828112777373L;

    public int getNumTags()
    {
        return _tags.size();
    }

    @Override
    public String getURI()
    {
        return _uri;
    }

    @Override
    public FaceletTaglibTag getTag(final String name)
    {
        for (final FaceletTaglibTag tag : _tags)
        {
            if (tag.getTagName().equals(name))
            {
                return tag;
            }
        }
        return null;
    }

    @Override
    public Collection<? extends FaceletTaglibTag> getTags()
    {
        return Collections.unmodifiableCollection(_tags);
    }

    /**
     * A builder for tag record.
     * 
     * @author cbateman
     *
     */
    public static class Builder
    {
        private final Map<String, List<FaceletTaglibTag>> _tags = new HashMap<String, List<FaceletTaglibTag>>();

        /**
         * @param jsfResource
         */
        public void addTag(final JSFResource jsfResource)
        {
            final String uri = String
                    .format(
                            "http://java.sun.com/jsf/composite/%s", jsfResource.getId().getLibraryName()); //$NON-NLS-1$
            List<FaceletTaglibTag> tags = _tags.get(uri);
            if (tags == null)
            {
                tags = new ArrayList<FaceletTaglibTag>();
                _tags.put(uri, tags);
            }
            final String resourceName = jsfResource.getId().getResourceName();
            final IPath resourceNamePath = new Path(resourceName).removeFileExtension();
            final FaceletTaglibTag tag = FaceletTaglibFactory.eINSTANCE
                    .createFaceletTaglibTag();
            tag.setTagName(resourceNamePath.toString());
            tags.add(tag);
        }

        /**
         * @return the built list of tag records.
         */
        public Map<String, ? extends IFaceletTagRecord> build()
        {
            final Map<String, JSFResourceBasedTagRecord> records = new HashMap<String, JSFResourceBasedTagRecord>();
            for (final Map.Entry<String, List<FaceletTaglibTag>> entry : _tags
                    .entrySet())
            {
                JSFResourceBasedTagRecord newRecord = new JSFResourceBasedTagRecord(entry.getKey(), entry.getValue());
                records.put(entry.getKey(), newRecord);
            }

            return records;
        }
    }
}
