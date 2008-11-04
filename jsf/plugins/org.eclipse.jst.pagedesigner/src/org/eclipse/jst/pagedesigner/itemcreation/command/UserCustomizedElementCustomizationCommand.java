package org.eclipse.jst.pagedesigner.itemcreation.command;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.TagCreationAttribute;
import org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.TagCreationInfo;
import org.eclipse.jst.pagedesigner.itemcreation.CreationData;
import org.eclipse.jst.pagedesigner.itemcreation.customizer.ChildrenData;
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
            ChildrenData childrenData = data.getChildrenData();
            
            if (childrenData != null)
            {
                for (ICustomizationData child : childrenData.getChildList())
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
                attributes.put(id, attrs.get(id) == null ? "" : attrs.get(id));
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
                    attributes.put(attr.getId(), (attr.getValue() == null ? ""
                            : attr.getValue()));
                }
            }
        }
    }

}
