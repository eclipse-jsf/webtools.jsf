package org.eclipse.jst.pagedesigner.itemcreation.customizer;


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

public class CustomizationDataImpl implements IWritableCustomizationData
{
    private TagIdentifier tagIdentifier;
    private AttributeData _attrs;
    private ChildrenData childrenData;
    private ParentData parentData;

    
	/**
	 * Constructor.
	 * 
	 * @param target
	 *            Tag identifier for the tag whose customization data this is
	 * 
	 */
    public CustomizationDataImpl(final TagIdentifier target)
    {
        tagIdentifier = target;
        _attrs = new AttributeData();
        parentData = new ParentData();
        childrenData = new ChildrenData();
    }

    
    /* (non-Javadoc)
     * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
     */
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

    
    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.itemcreation.customizer.ICustomizationData#getTagIdentifier()
     */
    public TagIdentifier getTagIdentifier()
    {
        return tagIdentifier;
    }

    
    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.itemcreation.customizer.IWritableCustomizationData#setTagIdentifier(org.eclipse.jst.jsf.common.dom.TagIdentifier)
     */
    public void setTagIdentifier(TagIdentifier tagId)
    {
        this.tagIdentifier = tagId;
    }
    
    
    //------------------------------------------------------------
    // Methods to handle attributes
    //------------------------------------------------------------

    
    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.itemcreation.customizer.ICustomizationData#getAttributeData()
     */
    public final AttributeData getAttributeData()
    {
        return _attrs;
    }

    
    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.itemcreation.customizer.IWritableCustomizationData#setAttributes(org.eclipse.jst.pagedesigner.itemcreation.customizer.AttributeData)
     */
    public void setAttributes(AttributeData attrs)
    {
        _attrs = attrs;
    }

    
    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.itemcreation.customizer.IWritableCustomizationData#addAttribute(java.lang.String, java.lang.String)
     */
    public void addAttribute(String attributeName, String attributeValue)
    {
        getAttributeData().addAttribute(attributeName, attributeValue);
    }

    
    //------------------------------------------------------------
    // Methods to handle children
    //------------------------------------------------------------

    
    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.itemcreation.customizer.ICustomizationData#getChildrenData()
     */
    public final ChildrenData getChildrenData()
    {
        return childrenData;
    }    

    
    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.itemcreation.customizer.IWritableCustomizationData#setChildrenData(org.eclipse.jst.pagedesigner.itemcreation.customizer.ChildrenData)
     */
    public void setChildrenData(ChildrenData data)
    {
        childrenData = data;
    }

    
    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.itemcreation.customizer.IWritableCustomizationData#addChildrenData(org.eclipse.jst.pagedesigner.itemcreation.customizer.ICustomizationData)
     */
    public void addChildData (ICustomizationData childData)
    {
        getChildrenData().add(childData);
    }
    
    
    //------------------------------------------------------------
    // Methods to handle parents
    //------------------------------------------------------------
    
    
    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.itemcreation.customizer.ICustomizationData#getParentData()
     */
    public final ParentData getParentData()
    {
        return parentData;
    }

    
    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.itemcreation.customizer.IWritableCustomizationData#setParentData(org.eclipse.jst.pagedesigner.itemcreation.customizer.ParentData)
     */
    public void setParentData(ParentData parentData)
    {
        this.parentData = parentData;
    }

    
    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.itemcreation.customizer.IWritableCustomizationData#addParentData(org.eclipse.jst.pagedesigner.itemcreation.customizer.ICustomizationData)
     */
    public void addParentData (ICustomizationData parentData_)
    {
        getParentData().add(parentData_);
    }
}
