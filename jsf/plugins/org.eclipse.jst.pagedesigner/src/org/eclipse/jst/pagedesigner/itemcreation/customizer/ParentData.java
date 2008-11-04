package org.eclipse.jst.pagedesigner.itemcreation.customizer;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to represent parent tag customization data for a tag
 * 
 * @author Debajit Adhikary
 * 
 */
public class ParentData
{
    private List<ICustomizationData> parentList;

    
    public ParentData ()
    {
        parentList = new ArrayList<ICustomizationData>();
    }
    
    
    public void add (ICustomizationData parentData)
    {
        this.parentList.add(parentData);
    }

    
    public List<ICustomizationData> getParentCustomizationData ()
    {
        return parentList;
    }

    
    public void setParentCustomizationData (List<ICustomizationData> parentList)
    {
        this.parentList = parentList;
    }
}
