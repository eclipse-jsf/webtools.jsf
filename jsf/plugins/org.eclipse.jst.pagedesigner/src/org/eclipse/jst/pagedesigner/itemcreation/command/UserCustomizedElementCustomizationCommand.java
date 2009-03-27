package org.eclipse.jst.pagedesigner.itemcreation.command;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.jsf.common.metadata.query.ITaglibDomainMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.query.TaglibDomainMetaDataQueryHelper;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.ResolverUtil;
import org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.TagCreationAttribute;
import org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.TagCreationInfo;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.dom.DOMPosition;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.editors.palette.TagToolPaletteEntry;
import org.eclipse.jst.pagedesigner.editors.palette.impl.PaletteItemManager;
import org.eclipse.jst.pagedesigner.editors.palette.impl.TaglibPaletteDrawer;
import org.eclipse.jst.pagedesigner.itemcreation.CreationData;
import org.eclipse.jst.pagedesigner.itemcreation.customizer.ICustomizationData;
import org.eclipse.jst.pagedesigner.utils.JSPUtil;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.w3c.dom.Element;

/**
 * Added extra functionality to use the customization data to set tag attributes
 * based on user enter values from drop wizard display.
 * 
 * @author prusev
 * @author Debajit Adhikary
 * 
 */
public class UserCustomizedElementCustomizationCommand extends
        ElementCustomizationCommand
{

    /**
     * @param model
     * @param element
     * @param creationData
     */
    public UserCustomizedElementCustomizationCommand(IDOMModel model,
            Element element, CreationData creationData)
    {
        super(model, element, creationData);
    }

    
    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.itemcreation.command.ElementCustomizationCommand#execute()
     */
    @Override
    public void execute()
    {
        super.execute();
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.itemcreation.command.ElementCustomizationCommand#applyChildElementCustomization()
     */
    @Override
    protected void applyChildElementCustomization()
    {
        // TODO: Use this to get child information from metadata?
        // super.applyChildElementCustomization();

        // Get customization data
        if (_creationData.getDropCustomizationData() != null)
        {
            final ICustomizationData data = (ICustomizationData) _creationData.getDropCustomizationData().getAdapter(ICustomizationData.class);
            
            if (data.getChildrenData() != null)
            {
            	int childCount = 0;
                CHILDREN_LOOP: for (ICustomizationData child : data.getChildrenData().getChildList())
                {
                    assert (_element.getOwnerDocument() != null);
                    
                    // Setup child node
                    Element childNode = _element.getOwnerDocument().createElement(child.getTagIdentifier().getTagName());
                    String prefix = JSPUtil.getOrCreatePrefix(_model, child.getTagIdentifier().getUri(), null);
                    childNode.setPrefix(prefix);

                    // Attach to child the attributes from the customization data 
                    Map<String, String> attrMap = child.getAttributeData().getAttributes();
                    for (String attrName : attrMap.keySet())
                    {
                        String attrValue = attrMap.get(attrName);
                        childNode.setAttribute(attrName, attrValue);
                    }
                    
                    // Attach child node to element
                    _element.appendChild(childNode);
                    
                    // Set up other attributes and child tags for this child
                    IFile fileForDocument = ResolverUtil.getFileForDocument(_model.getStructuredDocument());
                    if (fileForDocument == null)
                    {
                    	PDPlugin.log("File not found for model: "+_model.toString(), new Exception("Stack trace only"));  //$NON-NLS-1$//$NON-NLS-2$
                    	continue CHILDREN_LOOP;
                    }
                    final IProject project = fileForDocument.getProject();
                    PaletteItemManager paletteManager = PaletteItemManager.getInstance(project);
                    
                    if (paletteManager == null)
                    {
                    	PDPlugin.log("paletteManager not found for project: "+project.toString(), new Exception("Stack trace only")); //$NON-NLS-1$ //$NON-NLS-2$
                    	continue CHILDREN_LOOP;
                    }
                    
                    TaglibPaletteDrawer drawer = paletteManager.findCategoryByURI(child.getTagIdentifier().getUri());
                    
                    if (drawer == null)
                    {
                    	PDPlugin.log("Drawer not found for uri: "+child.getTagIdentifier().getUri(), new Exception("Stack trace only")); //$NON-NLS-1$ //$NON-NLS-2$
                    	continue CHILDREN_LOOP;
                    }

                    TagToolPaletteEntry paletteEntry = drawer.getTagPaletteEntryByTagName(child.getTagIdentifier().getTagName());
                    
                    if (paletteEntry == null)
                    {
                    	PDPlugin.log("Palette entry not found for drawer: "+drawer.toString(), new Exception("Stack trace only")); //$NON-NLS-1$ //$NON-NLS-2$
                    	continue CHILDREN_LOOP;
                    }
                    final ITaglibDomainMetaDataModelContext modelContext = 
                        TaglibDomainMetaDataQueryHelper
                            .createMetaDataModelContext(project, child.getTagIdentifier().getUri());
                    IDOMPosition domPosition = new DOMPosition(_element, childCount++);
                    CreationData creationData = new CreationData(paletteEntry,_model, domPosition, modelContext, child);
                    
                    ElementCustomizationCommand command = new UserCustomizedElementCustomizationCommand(_model, childNode, creationData);
                    command.execute();
                }
            }
        }
    }

    /**
     * Override to customize the attributes of the tag being created
     * 
     * Default implementation uses meta-data to set the attribute values. This
     * is the preferred method for static attribute values (i.e. those that are
     * not calculated dynamically at runtime).
     * 
     * (non-Javadoc)
     * 
     * @see org.eclipse.jst.pagedesigner.itemcreation.command.ElementCustomizationCommand#applyAttributeCustomization()
     */
    protected void applyAttributeCustomization()
    {
        final Map<String, String> attributes = new HashMap<String, String>();
        addAttributesFromPaletteMetadata(attributes);
        addAttributesFromDropCustomization(attributes);
        /*
         * Added by Pete: Use the attribute data within the customization data
         * container to configure the tag attributes
         */
        for (final Map.Entry<String, String> entry : attributes.entrySet())
        {
            _element.setAttribute(entry.getKey(), entry.getValue());
        }
    }

    private void addAttributesFromDropCustomization(
            Map<String, String> attributes)
    {
        if (_creationData.getDropCustomizationData() != null)
        {
            // Pull out the attribute data using the IAdaptable interface
            ICustomizationData data = (ICustomizationData) _creationData
                    .getDropCustomizationData().getAdapter(
                            ICustomizationData.class);
            Map<String, String> attrs = data.getAttributeData().getAttributes();
            // Go through each attribute/value pair and configure it
            for (String id : attrs.keySet())
            {
                attributes.put(id, attrs.get(id) == null ? "" : attrs.get(id)); //$NON-NLS-1$
            }
        }
    }

    private void addAttributesFromPaletteMetadata(Map<String, String> attributes)
    {
        final TagCreationInfo info = _creationData.getTagCreationInfo();
        if (info != null)
        {
            final EList list = info.getAttributes();
            if (list != null)
            {
                for (final Iterator it = list.iterator(); it.hasNext();)
                {
                    final TagCreationAttribute attr = (TagCreationAttribute) it
                            .next();
                    attributes.put(attr.getId(), (attr.getValue() == null ? "" //$NON-NLS-1$
                            : attr.getValue()));
                }
            }
        }
    }

}
