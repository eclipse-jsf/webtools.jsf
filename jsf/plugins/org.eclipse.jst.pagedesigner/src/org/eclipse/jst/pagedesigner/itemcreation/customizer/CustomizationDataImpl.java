package org.eclipse.jst.pagedesigner.itemcreation.customizer;


import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;


/**
 * Container class for all customization data collected from the tag wizard.
 * Through the Adaptable interface it will return the desired subdata piece such
 * as tag AttributeData.
 * 
 * @author prusev
 * @author Debajit Adhikary
 * 
 */

public class CustomizationDataImpl implements ICustomizationData, IAdaptable
{
    private TagIdentifier tagIdentifier;
    private AttributeData _attrs;
    private ChildrenData childrenData;
    private ParentData parentData;
    
   
    public CustomizationDataImpl(final TagIdentifier target)
    {
        tagIdentifier = target;
        _attrs = new AttributeData();
        parentData = new ParentData();
        childrenData = new ChildrenData();
    }

    
    public Object getAdapter(Class adapter)
    {
        if (ICustomizationData.class.equals(adapter))
        {
            return this;
        }
        
        return null;
    }

    
    //------------------------------------------------------------
    // Methods to handle the tag identifier
    //------------------------------------------------------------

    
    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.jst.pagedesigner.itemcreation.customizer.ICustomizationData
     * #getTagIdentifier()
     */
    public TagIdentifier getTagIdentifier()
    {
        return tagIdentifier;
    }

    
    public void setTagIdentifier(TagIdentifier tagId)
    {
        this.tagIdentifier = tagId;
    }
    
    
    //------------------------------------------------------------
    // Methods to handle attributes
    //------------------------------------------------------------

    
    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.jst.pagedesigner.itemcreation.customizer.ICustomizationData
     * #getAttributeData()
     */
    public final AttributeData getAttributeData()
    {
        return _attrs;
    }

    
    public void setAttributes(AttributeData attrs)
    {
        _attrs = attrs;
    }

    
    public void addAttribute(String attributeName, String attributeValue)
    {
        getAttributeData().addAttribute(attributeName, attributeValue);
    }

    
    //------------------------------------------------------------
    // Methods to handle children
    //------------------------------------------------------------

    
    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.jst.pagedesigner.itemcreation.customizer.ICustomizationData
     * #getChildrenData()
     */
    public final ChildrenData getChildrenData()
    {
        return childrenData;
    }    

    
    public void setChildrenData(ChildrenData data)
    {
        childrenData = data;
    }

    
    public void addChildrenData (ICustomizationData childrenData)
    {
        getChildrenData().add(childrenData);
    }
    
    
    //------------------------------------------------------------
    // Methods to handle parents
    //------------------------------------------------------------
    
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.jst.pagedesigner.itemcreation.customizer.ICustomizationData
     * #getParentData()
     */
    public final ParentData getParentData()
    {
        return parentData;
    }

    
    public void setParentData(ParentData parentData)
    {
        this.parentData = parentData;
    }

    
    /**
     * Attaches a parent to a tag (The customization data for a parent is added
     * to the tag's customization data). The oldest parent (outermost container
     * tag) should be attached (added) first.
     * 
     * @param parentData
     *            Parent customization data
     */
    public void addParentData (ICustomizationData parentData)
    {
        getParentData().add(parentData);
    }
}
