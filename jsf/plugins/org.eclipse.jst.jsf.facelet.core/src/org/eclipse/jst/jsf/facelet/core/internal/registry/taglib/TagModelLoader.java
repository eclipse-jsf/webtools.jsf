package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.URIUtil;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ContentHandler;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.resource.impl.URIHandlerImpl;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jst.jsf.facelet.core.internal.FaceletCorePlugin;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.DocumentRoot;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.util.FaceletTaglibResourceFactoryImpl;
import org.eclipse.wst.xml.core.internal.XMLCorePlugin;
import org.eclipse.wst.xml.core.internal.catalog.provisional.ICatalog;
import org.eclipse.wst.xml.core.internal.catalog.provisional.ICatalogEntry;
import org.eclipse.wst.xml.core.internal.catalog.provisional.INextCatalog;

/**
 * Loads the facelet taglib file model using EMF.
 * 
 * @author cbateman
 * 
 */
public class TagModelLoader
{
    private final ResourceSetImpl _resSet;
    private final String _resourceUri;
    private DocumentRoot _docRoot;
    private FaceletTaglib _faceletTaglib;
    private final static List<URIHandler> DEFAULT_URI_HANDLERS;
    static
    {
        final List<URIHandler> handlers = new ArrayList<URIHandler>();
        final URIHandler handleFacelet = new DefaultFaceletURIHandler();
        handlers.add(handleFacelet);
        handlers.addAll(URIHandler.DEFAULT_HANDLERS);
        DEFAULT_URI_HANDLERS = handlers;
    }

    /**
     * @param resourceUri
     */
    public TagModelLoader(final String resourceUri)
    {
        this(resourceUri, new ExtensibleURIConverterImpl(DEFAULT_URI_HANDLERS,
                ContentHandler.Registry.INSTANCE.contentHandlers()));
    }

    /**
     * @param resourceUri
     * @param uriConverter
     */
    public TagModelLoader(final String resourceUri,
            final URIConverter uriConverter)
    {
        _resourceUri = resourceUri;
        _resSet = new ResourceSetImpl();
        _resSet.getPackageRegistry().put("http://java.sun.com/xml/ns/javaee", //$NON-NLS-1$
                FaceletTaglibPackage.eINSTANCE);
        _resSet.getPackageRegistry().put(
                "http://java.sun.com/xml/ns/javaee/web-facelettaglibrary.xsd", //$NON-NLS-1$
                FaceletTaglibPackage.eINSTANCE);
        _resSet.setURIConverter(uriConverter);
        final ExtendedMetaData extendedMetaData = new BasicExtendedMetaData(
                _resSet.getPackageRegistry());
        _resSet.getLoadOptions().put(XMLResource.OPTION_EXTENDED_META_DATA,
                extendedMetaData);
        _resSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
                .put("xml", new FaceletTaglibResourceFactoryImpl()); //$NON-NLS-1$
    }

    /**
     * @param is
     * @throws Exception
     */
    public void loadFromInputStream(final InputStream is) throws Exception
    {
        final Resource res = _resSet.createResource(URI
                .createFileURI(_resourceUri));
        if (res != null)
        {
            Map<String, Object> options = new HashMap<String, Object>();
            options.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
            res.load(is, options);
            final EObject eObject = res.getContents().get(0);
            _docRoot = (DocumentRoot) eObject;
            _faceletTaglib = _docRoot.getFaceletTaglib();
        } else
        {
            // This is often caused if the extension of the file pointed to
            // by _resourceUri wasn't initialized in extensionToFactoryMap.
            throw new IllegalArgumentException("Could not create Resource"); //$NON-NLS-1$
        }
    }

    /**
     * @param os
     * @throws Exception
     */
    public void save(final OutputStream os) throws Exception
    {
        final Resource res = _resSet.createResource(URI
                .createFileURI(_resourceUri));
        if (res != null)
        {
            res.getContents().add(_docRoot);
            res.save(os, Collections.EMPTY_MAP);
        } else
        {
            throw new IllegalArgumentException("Could not create Resource"); //$NON-NLS-1$
        }
    }

    /**
     * @param faceletTaglib
     */
    public final void setTaglib(final FaceletTaglib faceletTaglib)
    {
        _faceletTaglib = faceletTaglib;
    }

    /**
     * @param docRoot
     */
    public final void setDocRoot(final DocumentRoot docRoot)
    {
        _docRoot = docRoot;
    }

    /**
     * @return the taglib or null if none has been loaded.
     */
    public FaceletTaglib getTaglib()
    {
        return _faceletTaglib;
    }

    /**
     * @return the doc root or null if none has been loaded.
     */
    public DocumentRoot getDocRoot()
    {
        return _docRoot;
    }

    /**
     * Handles facelet taglib dtd resolver requests
     * 
     */
    private static final class DefaultFaceletURIHandler extends URIHandlerImpl
    {
        @Override
        public boolean canHandle(final URI uri)
        {
            return "http://java.sun.com/dtd/facelet-taglib_1_0.dtd".equals(uri.toString()); //$NON-NLS-1$
        }

        @Override
        public InputStream createInputStream(final URI uri, final Map<?, ?> options)
                throws IOException
        {
            try
            {
                return findFaceletDtdInCatalog();
            } catch (final URISyntaxException e)
            {
                FaceletCorePlugin.log(
                        "While trying to load facelet dtd from catalog", e); //$NON-NLS-1$
                // fall through
            }
            return null;
        }

        private InputStream findFaceletDtdInCatalog() throws URISyntaxException, IOException
        {
            final ICatalog xmlCatalog = XMLCorePlugin.getDefault()
                    .getDefaultXMLCatalog();
            if (xmlCatalog != null)
            {
                ICatalog systemCatalog = null;
                final INextCatalog[] nextCatalogs = xmlCatalog.getNextCatalogs();
                for (final INextCatalog catalog : nextCatalogs)
                {
                    final ICatalog referencedCatalog = catalog
                            .getReferencedCatalog();
                    if (referencedCatalog != null)
                    {
                        if (XMLCorePlugin.SYSTEM_CATALOG_ID
                                .equals(referencedCatalog.getId()))
                        {
                            systemCatalog = referencedCatalog;
                        }
                    }
                }
                if (systemCatalog != null)
                {
                    ICatalogEntry foundEntry = null;
                    for (final ICatalogEntry entry : systemCatalog.getCatalogEntries())
                    {
                        if ("-//Sun Microsystems, Inc.//DTD Facelet Taglib 1.0//EN" //$NON-NLS-1$
                        .equals(entry.getKey()))
                        {
                            foundEntry = entry;
                            break;
                        }
                    }
                    if (foundEntry != null)
                    {
                        final java.net.URI uri = URIUtil.fromString(foundEntry.getURI());
                        final URL url = URIUtil.toURL(uri);
                        return url.openStream();
                    }
                }
            }
            return null;
        }
    }
}
