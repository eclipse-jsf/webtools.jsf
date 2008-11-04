package org.eclipse.jst.pagedesigner.itemcreation.customizer;

import java.util.HashMap;
import java.util.Map;

/**
 * This class contains all the data that pertains to the customization
 * of a tag's attributes. For the current PoC, this involves a HashMap
 * where the String key is the name of the attribute and the associated
 * value is the user entered value for that attribute.
 * 
 * @author prusev
 *
 */
public class AttributeData {

	private Map<String, String> attrs = new HashMap<String, String>();

	public Map<String, String> getAttributes()
	{
		return attrs;
	}

	public void setAttributes(Map<String, String> attribs)
	{
	    attrs.clear();
	    attrs.putAll(attribs);
	}
	
	public void addAttribute(String attr, String userVal)
	{
		attrs.put(attr, userVal);
	}
	
	public String getValForAttrib(String attr)
	{
		return attrs.get(attr);
	}

}
