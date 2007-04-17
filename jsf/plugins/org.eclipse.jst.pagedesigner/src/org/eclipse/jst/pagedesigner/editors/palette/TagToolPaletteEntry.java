package org.eclipse.jst.pagedesigner.editors.palette;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.pagedesigner.editors.palette.impl.TaglibPaletteDrawer;
import org.eclipse.jst.pagedesigner.itemcreation.ITagCreator;
import org.eclipse.jst.pagedesigner.itemcreation.ItemCreationTool;
import org.eclipse.jst.pagedesigner.itemcreation.TagCreationFactory;

/**
 * Represents a tag palette item entry in the web page designer palette
 *
 */
public class TagToolPaletteEntry extends ToolEntry implements IAdaptable{

	private String tagName;
	
	/**
	 * Constructor
	 * @param tagName
	 * @param label
	 * @param shortDescription
	 * @param iconSmall
	 * @param iconLarge
	 */
	public TagToolPaletteEntry(String tagName, String label, String shortDescription,
			ImageDescriptor iconSmall, ImageDescriptor iconLarge) {
		super(label, shortDescription, iconSmall, iconLarge);
		this.tagName = tagName;
	}

	/**
	 * @return tagName
	 */
	public String getTagName(){
		return tagName;
	}
	
	/**
	 * Convenience method returning the tag libraries default prefix, if applicable
	 * @return default prefix 
	 */
	public String getDefaultPrefix(){
		return ((TaglibPaletteDrawer)getParent()).getDefaultPrefix();
	}

	/**
	 * @return uri of the tag's library
	 */
	public String getURI(){
		return ((TaglibPaletteDrawer)getParent()).getURI();
	}

	/**
	 * Return an ITagCreator adpater or null
	 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
	 */
	public Object getAdapter(Class adapter) {
		if (adapter == ITagCreator.class){
			return TagCreationFactory.getInstance().createTagCreator(this);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.palette.ToolEntry#createTool()
	 */
	public Tool createTool() {
		return new ItemCreationTool(this);
	}
	

}
