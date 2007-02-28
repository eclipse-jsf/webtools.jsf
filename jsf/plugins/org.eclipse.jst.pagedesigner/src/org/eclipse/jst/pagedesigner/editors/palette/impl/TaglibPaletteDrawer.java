package org.eclipse.jst.pagedesigner.editors.palette.impl;

import java.util.Iterator;

import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.jst.pagedesigner.editors.palette.TagToolPaletteEntry;

public class TaglibPaletteDrawer extends PaletteDrawer {
	private String prefix;	
	
	public TaglibPaletteDrawer(String uri, String label) {
		super(label);
		super.setId(uri);
		setDrawerType(ToolEntry.PALETTE_TYPE_TOOL);
	}
	
	public String getDefaultPrefix() {
		return prefix;
	}

	public void setDefaultPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getURI(){
		return getId();
	}
	
	public void setURI(String uri){
		setId(uri);
	}
	
	public TagToolPaletteEntry getTagPaletteEntryById(String id){
		for (Iterator it=getChildren().iterator();it.hasNext();){
			TagToolPaletteEntry tag = (TagToolPaletteEntry)it.next();
			if (tag.getId().equals(id))
				return tag;
		}
		return null;
	}
	
	public TagToolPaletteEntry getTagPaletteEntryByTagName(String tagName){
		for (Iterator it=getChildren().iterator();it.hasNext();){
			TagToolPaletteEntry tag = (TagToolPaletteEntry)it.next();
			if (tag.getTagName().equalsIgnoreCase(tagName))
				return tag;
		}
		return null;
	}
	
	public boolean acceptsType(Object object){
//		if (object instanceof TagToolPaletteEntry)
			return true;
		
//		return false;
	}
	
}
