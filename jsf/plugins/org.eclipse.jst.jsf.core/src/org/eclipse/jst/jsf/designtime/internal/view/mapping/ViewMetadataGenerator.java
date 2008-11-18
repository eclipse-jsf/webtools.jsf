package org.eclipse.jst.jsf.designtime.internal.view.mapping;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLHelperImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.MetadataFactory;
import org.eclipse.jst.jsf.common.metadata.MetadataPackage;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.runtime.internal.model.types.ClassTypeInfo;
import org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ClassTypeInfo_;
import org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentMappingFactory;
import org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentMappingPackage;
import org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.TagMapping;
import org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.TagToViewObjectMapping;

/**
 * Generates View metadata from common.runtime objects.
 * 
 * @author cbateman
 * 
 */
public class ViewMetadataGenerator
{
    private final ResourceSet      _resourceSet;
    private final ExtendedMetaData _extendedMetadata;
    private final Model            _root;
    private final ViewMetadataMapper   _mapper;

    /**
     * @param uri
     */
    public ViewMetadataGenerator(final String uri)
    {
        _root = MetadataFactory.eINSTANCE.createModel();
        _root.setId(uri);
        _root.setType("tagFile"); //$NON-NLS-1$
        _resourceSet = new ResourceSetImpl();
        _mapper = new ViewMetadataMapper();
        _extendedMetadata = new BasicExtendedMetaData(_resourceSet
                .getPackageRegistry());

        // Register the appropriate resource factory to handle all file
        // extensions.
        //
        _resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
                .put(Resource.Factory.Registry.DEFAULT_EXTENSION,
                        new XMLResourceFactoryImpl());
    }

    /**
     * The format of the strings should match OSGI version:
     * 
     * MAJOR . MINOR . BUILD . QUALIFIER
     * 
     * @param tagName
     * @param typeInfo
     * @param minJSFVersion
     *            the minimum JSF version, null for DEFAULT.
     * @param minLibVersion
     *            the minimum library version, null for NONE.
     */
    public void addTagToViewObjectMapping(final String tagName,
            final ClassTypeInfo typeInfo, final String minJSFVersion,
            final String minLibVersion)
    {
        final ClassTypeInfo_ metadata = _mapper.mapToMetadata(typeInfo);

        if (metadata != null)
        {
            final TagToViewObjectMapping viewMapping =
                ComponentMappingFactory.eINSTANCE.createTagToViewObjectMapping();
            if (minJSFVersion != null)
            {
                viewMapping.setMinJSFVersion(minJSFVersion);
            }
            viewMapping.setMinLibraryVersion(minLibVersion);
            viewMapping.setTypeInfo(metadata);

            final TagMapping tagMapping = findOrCreateTagMapping(tagName);
            tagMapping.getVersionedTagToViewMappings().add(viewMapping);

            final Trait trait = MetadataFactory.eINSTANCE.createTrait();
            trait.setId(ViewMetadataMapper.DEFAULT_MAPPING_TRAIT_ID);
            trait.setValue(tagMapping);

            final Entity entity = MetadataFactory.eINSTANCE.createEntity();
            entity.setId(tagName);
            entity.setType("tag"); //$NON-NLS-1$
            entity.getTraits().add(trait);

            _root.getChildEntities().add(entity);
        }
    }

    private TagMapping findOrCreateTagMapping(final String tagName)
    {
        for (final Entity entity : (List<Entity>)_root.getChildEntities())
        {
            if (entity.getId().equals(tagName))
            {
                for (final Trait trait : (List<Trait>)entity.getTraits())
                {
                    if (trait.getId().equals(ViewMetadataMapper.DEFAULT_MAPPING_TRAIT_ID))
                    {
                        return (TagMapping) trait.getValue();
                    }
                }
            }
        }
        return ComponentMappingFactory.eINSTANCE.createTagMapping();
    }

    /**
     * @param out
     * @throws IOException
     */
    public void save(final OutputStream out) throws IOException
    {
        final Resource res = new XMLResourceImpl()
        {
            @Override
            protected XMLHelper createXMLHelper()
            {
                return new XMLHelperImpl()
                {
                    public String getHREF(EObject obj)
                    {
                        return ComponentMappingPackage.eNS_URI;
                    }

                    @Override
                    public EPackage getNoNamespacePackage()
                    {
                        return MetadataPackage.eINSTANCE;
                    }
                };
            }
        };// _resourceSet.createResource(URI.createURI("foo.xml"));
        res.getContents().add(_root);
        // res.setURI(uri);
        // resourceSet.getResources().add(res);
        // setLoadOptions(res);
        Map options = new HashMap();
        options.put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
        options.put(XMLResource.OPTION_EXTENDED_META_DATA, _extendedMetadata);
        // options.put(XMLResource.OPTION_RESOURCE_HANDLER, res);
        options.put(XMLResource.OPTION_LAX_FEATURE_PROCESSING, Boolean.TRUE);
        options.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.FALSE);// turning
        // this
        // off
        // so
        // that
        // res.getErrors()
        // has
        // values
        // to
        // check!
        // bizarre
        // that
        // I
        // should
        // need
        // to
        // do
        // this.
        res.save(out, options);
    }

    // private void printHeader(final OutputStream out) throws IOException
    // {
    // final String header = "<md:metadatamodel \n"
    // + "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
    // + "xmlns:ecore=\"http://www.eclipse.org/emf/2002/Ecore\""
    // +
    // "xmlns:md=\"http://org.eclipse.jst.jsf.common.metadata/metadata.ecore\""
    // +
    // "xmlns:mdt=\"http://org.eclipse.jst.jsf.common.metadata/metadataTraitTypes.ecore\"
    // "
    // +
    // "xmlns:viewMap=\"http://org.eclipse.jst.jsf.core/componentMapping.ecore\""
    // + "id=\"http://java.sun.com/jsf/html\""
    // + "type\"tagFile\">";
    // out.write(header.getBytes());
    // }
}
