package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.DocumentRoot;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.util.FaceletTaglibResourceFactoryImpl;

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

    /**
     * @param resourceUri
     */
    public TagModelLoader(final String resourceUri)
    {
        _resourceUri = resourceUri;
        _resSet = new ResourceSetImpl();

        _resSet.getPackageRegistry().put("http://java.sun.com/xml/ns/javaee", //$NON-NLS-1$
                FaceletTaglibPackage.eINSTANCE);
        _resSet.getPackageRegistry().put(
                "http://java.sun.com/xml/ns/javaee/web-facelettaglibrary.xsd", //$NON-NLS-1$
                FaceletTaglibPackage.eINSTANCE);
        final ExtendedMetaData extendedMetaData = new BasicExtendedMetaData(
                _resSet.getPackageRegistry());
        _resSet.getLoadOptions().put(XMLResource.OPTION_EXTENDED_META_DATA,
                extendedMetaData);
        _resSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(
                "xml", new FaceletTaglibResourceFactoryImpl()); //$NON-NLS-1$

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
            res.load(is, Collections.EMPTY_MAP);
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
    public final void setTaglib(FaceletTaglib faceletTaglib)
    {
        _faceletTaglib = faceletTaglib;
    }

    /**
     * @param docRoot
     */
    public final void setDocRoot(DocumentRoot docRoot)
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

}
