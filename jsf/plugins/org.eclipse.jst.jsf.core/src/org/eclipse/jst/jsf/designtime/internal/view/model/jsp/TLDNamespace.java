package org.eclipse.jst.jsf.designtime.internal.view.model.jsp;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.jobs.ILock;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.Namespace;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDDocument;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDElementDeclaration;
import org.eclipse.wst.xml.core.internal.contentmodel.CMNamedNodeMap;
import org.eclipse.wst.xml.core.internal.contentmodel.CMNode;

/**
 * Represents a JSP tag library namespace
 * 
 * @author cbateman
 * 
 */
public class TLDNamespace extends Namespace
{
    private final TLDDocument _tldDoc;
    private final ITagResolvingStrategy<TLDElementDeclaration, String> _tagResolver;
    private final ILock _tagMapReadWriteLock;

    private Map<String, ITagElement> _tags; // lazy init in initializeTags

    /**
     * @param tldDoc
     * @param tagResolver
     */
    public TLDNamespace(
            final TLDDocument tldDoc,
            final ITagResolvingStrategy<TLDElementDeclaration, String> tagResolver)
    {
        _tldDoc = tldDoc;
        _tagResolver = tagResolver;
        _tagMapReadWriteLock = Job.getJobManager().newLock();
    }

    @Override
    public final String getDisplayName()
    {
        String displayName = _tldDoc.getDisplayName();
        if (displayName == null || "".equals(displayName.trim()))
        {
            displayName = getNSUri();
        }
        return displayName;
    }

    @Override
    public final String getNSUri()
    {
        return _tldDoc.getUri();
    }

    
    @Override
    public boolean isInitialized()
    {
        int numTags = _tldDoc.getElements().getLength();
        int tagMapSize = getOrCreateMap(3).size();
        
        // we are only initialized if the tag map is the equal in size
        // to the number of tags in the tld.
        return numTags == tagMapSize;
    }

    /**
     * mapSizeHint will be used
     * 
     * @param mapSizeHint
     * @return the map
     */
    private synchronized Map<String, ITagElement> getOrCreateMap(
            final int mapSizeHint)
    {
        if (_tags == null)
        {
            final float loadFactor = 0.75f;
            final int initSize = ((int) (mapSizeHint / loadFactor)) + 1;
            _tags = Collections
                    .synchronizedMap(new HashMap<String, ITagElement>(initSize));
        }
        return _tags;
    }

    private ITagElement getOrCreateTagElement(final String name,
            final Map<String, ITagElement> tags)
    {
        ITagElement tagElement = null;

        _tagMapReadWriteLock.acquire();

        try
        {
            tagElement = tags.get(name);

            if (tagElement == null)
            {
                CMNode cmNode = _tldDoc.getElements().getNamedItem(name);

                if (cmNode instanceof TLDElementDeclaration)
                {
                    tagElement = _tagResolver
                            .resolve((TLDElementDeclaration) cmNode);

                    if (tagElement != _tagResolver.getNotFoundIndicator())
                    {
                        tags.put(tagElement.getName(), tagElement);
                    }
                    else
                    {
                        // the not-found indicator may not be null.
                        tagElement = null;
                    }
                }
            }
        }
        finally
        {
            _tagMapReadWriteLock.release();
        }

        return tagElement;
    }

    public final ITagElement getViewElement(final String name)
    {
        // bias the table small if this creates it
        final Map<String, ITagElement> tags = getOrCreateMap(3);

        return getOrCreateTagElement(name, tags);
    }

    @Override
    public final Collection<? extends ITagElement> getViewElements()
    {
        return Collections.unmodifiableCollection(initializeTags().values());
    }

    @Override
    public final boolean hasViewElements()
    {
        return _tldDoc.getElements().getLength() > 0;
    }

    /**
     * Initialize the tag definitions inn the namespace
     */
    private final Map<String, ITagElement> initializeTags()
    {
        final CMNamedNodeMap elementMap = _tldDoc.getElements();
        // if we create the table with this call, bias the table size
        // to be 1 bigger than the number of tags, since we know if it's
        // smaller, it'll cause at least one buffer resize
        final Map<String, ITagElement> tags = getOrCreateMap(elementMap
                .getLength());

        for (int i = 0; i < elementMap.getLength(); i++)
        {
            final CMNode cmNode = elementMap.item(i);
            getOrCreateTagElement(cmNode.getNodeName(), tags);
        }
        
        return tags;
    }
}
