package org.eclipse.jst.pagedesigner.itemcreation;

import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.editors.palette.TagToolPaletteEntry;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.w3c.dom.Element;

public interface ITagCreator {
	/**
	 * @param tagItem
	 * @param model
	 * @param domPosition
	 * @return W3C Element for the  tag at the correct position in the DOM.   May be null if creation cannot occur.
	 */
	public Element createTag(TagToolPaletteEntry tagItem, IDOMModel model, IDOMPosition domPosition);
}
