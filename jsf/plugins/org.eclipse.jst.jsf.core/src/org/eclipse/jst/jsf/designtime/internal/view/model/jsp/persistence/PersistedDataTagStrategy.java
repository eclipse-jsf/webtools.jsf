package org.eclipse.jst.jsf.designtime.internal.view.model.jsp.persistence;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.JSFCoreTraceOptions;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.JSPTagResolvingStrategy;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.TLDNamespace;
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
    public final static String                    ID              = "org.eclipse.jst.jsf.designtime.PersistedDataTagStrategy";
    /**
     * the display name
     */
    public final static String                    DISPLAY_NAME    = "Cached Data Tag Resolver";

    private final IProject                        _project;
    private final TagRepository                   _repository;
    private Map<String, SerializableTLDNamespace> _namespaces;
    private final transient AtomicBoolean         _reentrancyFlag = new AtomicBoolean(
                                                                          false);

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
        if (JSFCoreTraceOptions.TRACE_JSPTAGPERSISTENCE)
        {
            JSFCoreTraceOptions
                    .log("Initializing PersistedDataTagStrategy for project: "
                            + _project.toString());
        }
        try
        {
            _namespaces = _repository.load();
            return;
        }
        catch (final IOException e)
        {
            JSFCorePlugin
                    .log(e,
                            "JSP tag registry cached failed to load.  Strategy will not be used");
        }
        catch (final ClassNotFoundException e)
        {
            JSFCorePlugin
                    .log(e,
                            "JSP tag registry cached failed to load.  Strategy will not be used");
        }
        _namespaces = new HashMap<String, SerializableTLDNamespace>();
    }

    /**
     * @param namespace
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public void save(final Map<String, TLDNamespace> namespace)
            throws IOException, ClassNotFoundException
    {
        if (JSFCoreTraceOptions.TRACE_JSPTAGPERSISTENCE)
        {
            JSFCoreTraceOptions
                    .log("Saving PersistedDataTagStrategy for project: "
                            + _project.toString());
        }

        // namespace is considered authoritative for every key it contains
        // so copy them all in. However, preserve anything in our own namespace
        // map not found in namespace
        for (final Map.Entry<String, TLDNamespace> namespaceEntry : namespace
                .entrySet())
        {
            final TLDNamespace ns = namespaceEntry.getValue();
            final String nsName = namespaceEntry.getKey();
            final SerializableTLDNamespace myNs = _namespaces.get(nsName);

            if (myNs == null)
            {
                if (JSFCoreTraceOptions.TRACE_JSPTAGPERSISTENCE)
                {
                    JSFCoreTraceOptions.log(String.format(
                            "Adding namespace %s for project", ns.getNSUri(),
                            _project.toString()));
                }

                _namespaces.put(nsName, new SerializableTLDNamespace(ns));
            }
            else
            {
                if (JSFCoreTraceOptions.TRACE_JSPTAGPERSISTENCE)
                {
                    JSFCoreTraceOptions.log(String.format(
                            "Updating namespace %s for project", ns.getNSUri(),
                            _project.toString()));
                }

                for (final Map.Entry<String, ITagElement> elementEntry : ns
                        .getCurrentElements().entrySet())
                {
                    if (JSFCoreTraceOptions.TRACE_JSPTAGPERSISTENCE)
                    {
                        JSFCoreTraceOptions.log(String.format(
                                "Putting element %s", elementEntry.getKey()));
                    }
                    myNs.put(elementEntry.getKey(), elementEntry.getValue());
                }
            }
        }

        _repository.save(_namespaces);
    }

    @Override
    public ITagElement resolve(final TLDElementDeclaration element)
    {
        if (JSFCoreTraceOptions.TRACE_JSPTAGPERSISTENCE)
        {
            JSFCoreTraceOptions.log(String.format(
                    "Attempting to resolve element %s for project %s", element
                            .getElementName(), _project));
        }

        try
        {
            if (!_reentrancyFlag.compareAndSet(false, true))
            {
                throw new IllegalStateException("Reentrant call to resolve");
            }

            final String uri = getUri(element);
            final String tagName = element.getElementName();

            if (uri != null && tagName != null)
            {
                final SerializableTLDNamespace ns = _namespaces.get(uri);

                if (ns != null)
                {
                    final ITagElement tagElement = ns.getViewElement(tagName);

                    if (tagElement instanceof TLDTagElement)
                    {
                        if (JSFCoreTraceOptions.TRACE_JSPTAGPERSISTENCE)
                        {
                            JSFCoreTraceOptions.log(String.format(
                                    "Resolved element %s for project %s",
                                    element.getElementName(), _project));
                        }
                        return tagElement;
                    }
                }
            }
            return getNotFoundIndicator();
        }
        finally
        {
            _reentrancyFlag.set(false);
        }
    }

    /**
     * Clears all cached data and removes it permanently from disk.
     */
    public void clear()
    {
        if (JSFCoreTraceOptions.TRACE_JSPTAGPERSISTENCE)
        {
            JSFCoreTraceOptions.log(String.format(
                    "Flushing all data for project %s", _project));
        }

        try
        {
            _repository.clearAll();
        }
        catch (IOException e)
        {
            JSFCorePlugin.log(e, "Failure during cache flushing on project: "
                    + _project);
        }
        catch (ClassNotFoundException e)
        {
            JSFCorePlugin.log(e, "Failure during cache flushing on project: "
                    + _project);
        }
        _namespaces.clear();
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
