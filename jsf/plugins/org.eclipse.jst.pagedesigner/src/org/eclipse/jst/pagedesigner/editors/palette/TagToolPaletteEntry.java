package org.eclipse.jst.pagedesigner.editors.palette;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.pagedesigner.editors.palette.impl.TaglibPaletteDrawer;
import org.eclipse.jst.pagedesigner.itemcreation.ITagCreator;
import org.eclipse.jst.pagedesigner.itemcreation.TagCreationFactory;
import org.eclipse.jst.pagedesigner.itemcreation.ItemCreationTool;

public class TagToolPaletteEntry extends ToolEntry implements IAdaptable{

	private String tagName;
	
	public TagToolPaletteEntry(String tagName, String label, String shortDescription,
			ImageDescriptor iconSmall, ImageDescriptor iconLarge) {
		super(label, shortDescription, iconSmall, iconLarge);
		this.tagName = tagName;
	}

	public String getTagName(){
		return tagName;
	}
	
	public String getDefaultPrefix(){
		return ((TaglibPaletteDrawer)getParent()).getDefaultPrefix();
	}

	public String getURI(){
		return ((TaglibPaletteDrawer)getParent()).getURI();
	}

	public Object getAdapter(Class adapter) {
		if (adapter == ITagCreator.class){
			return TagCreationFactory.getInstance().createTagCreator(this);
		}
		return null;
	}

	public Tool createTool() {
		return new ItemCreationTool(this);
//		return super.createTool();
	}
	

}
