package org.eclipse.jst.pagedesigner.itemcreation.customizer;

import java.util.ArrayList;
import java.util.List;

/**
 * The class containing any data that pertains to the customization of a tag's
 * children will be contained here. Currently, this is empty for this PoC
 * 
 * @author prusev
 * @author Debajit Adhikary
 * 
 */
public class ChildrenData
{
    private List<ICustomizationData> childList;

    
    /**
     * Default constructor
     * 
     */
    public ChildrenData()
    {
        this.childList = new ArrayList<ICustomizationData>();
    }

    
	/**
	 * Constructor to initialize a ChildrenData object with a list of
	 * customization data for the child tags
	 * 
	 * @param childList
	 *            List of customization data objects for child tags
	 * 
	 */
    public ChildrenData(List<ICustomizationData> childList)
    {
        this.childList = childList;
    }

    
    /**
     * @return the childList
     */
    public List<ICustomizationData> getChildList()
    {
        return childList;
    }

    
    /**
     * @param childList
     *            the childList to set
     */
    public void setChildList(List<ICustomizationData> childList)
    {
        this.childList = childList;
    }

    
    /**
     * @param childData ChildData object to add 
     */
    public void add(ICustomizationData childData)
    {
        childList.add(childData);
    }
}
