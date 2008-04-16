package org.eclipse.jst.jsf.designtime.internal.view.model.jsp.persistence;


import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.Namespace;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.JSPTagResolvingStrategy;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.TLDTagElement;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDDocument;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDElementDeclaration;
import org.eclipse.wst.xml.core.internal.contentmodel.CMDocument;

/**
 * @author cbateman
 * 
 */
public class PersistedDataTagStrategy extends JSPTagResolvingStrategy
{
    /**
     * the identifier of this strategy
     */
    public final static String  ID           = "org.eclipse.jst.jsf.designtime.PersistedDataTagStrategy";
    /**
     * the display name
     */
    public final static String  DISPLAY_NAME = "Persisted Data Tag Resolver";

    private final IProject      _project;
    private final TagRepository _repository;
    private Map<String, Namespace>  _namespaces;

    /**
     * @param project
     */
    public PersistedDataTagStrategy(final IProject project)
    {
        _project = project;
        _repository = new TagRepository(_project);
    }

    /**
     * 
     */
    public void init()
    {
        try
        {
            _namespaces = _repository.load();
        }
        catch (IOException e)
        {
            JSFCorePlugin.log(e, "JSP tag registry cached failed to load.  Strategy will not be used");
        }
        catch (ClassNotFoundException e)
        {
            JSFCorePlugin.log(e, "JSP tag registry cached failed to load.  Strategy will not be used");
        }
        _namespaces = Collections.EMPTY_MAP;
    }

    public void save(final Map<String, Namespace> namespace)
    {
        _namespaces = namespace;
        _repository.save(_namespaces);
    }

    @Override
    public ITagElement resolve(TLDElementDeclaration element)
    {
        final String uri = getUri(element);
        final String tagName = element.getElementName();

        if (uri != null && tagName != null)
        {
            Namespace ns = _namespaces.get(uri);

            if (ns != null)
            {
                final ITagElement tagElement = ns.getViewElement(tagName);

                if (tagElement instanceof TLDTagElement)
                {
                    if (!tagElement.isLocked())
                    {
                        ((TLDTagElement)tagElement).setElementDecl(element);
                        tagElement.setLocked();
                    }
                    return tagElement;
                }
            }
        }
        return getNotFoundIndicator();
    }

    public String getDisplayName()
    {
        return DISPLAY_NAME;
    }

    private static String getUri(final TLDElementDeclaration element)
    {
        final CMDocument owner = element.getOwnerDocument();

        if (owner instanceof TLDDocument)
        {
            return ((TLDDocument) owner).getUri();
        }
        return null;
    }

    @Override
    public String getId()
    {
        return ID;
    }
}
