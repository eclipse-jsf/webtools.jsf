/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.converter;

import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.utils.DOMUtil;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/**
 * This tag converter is for those unsupported jsp tags.
 * 
 * @author mengbo
 * @version 1.5
 */
public class DefaultUnknownTagConverter extends AbstractTagConverter {

	private static final int NO_ELEMENT = 0;
	private static final int TABLE_ELEMENT = 1;
	private static final int TABLE_ROW_ELEMENT = 2;

	/**
	 * @param host
	 * @param mode 
	 */
	public DefaultUnknownTagConverter(Element host, int  mode) {
		super(host);
        setMode(mode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.converter.AbstractTagConverter#doConvertRefresh()
	 */
	protected Element doConvertRefresh() {
		Element hostEle = this.getHostElement();

		// Test to see if the src element is contained in an
		// element that renders as a table. If so, render this
		// element accordingly as content in the table.
		// This is done to address the use case where tags
		// (such as JSTL) are used in collaboration within HTML
		// tables. The CSS layout for tables does not handle
		// invalid HTML so this change tries to produce valid
		// HTML. If the table layout code gets updated to handle
		// invalid HTML tables, then this code can be removed.
		// See https://bugs.eclipse.org/bugs/show_bug.cgi?id=253974
		Node containingElement = ConverterUtil.findConvertedParentElement(hostEle, null);
		String name = null;
		if (containingElement != null) {
			name = containingElement.getNodeName(); 
		}
		if (name != null) {
			if (IHTMLConstants.TAG_TABLE.equalsIgnoreCase(name)
					|| IHTMLConstants.TAG_TBODY.equalsIgnoreCase(name)
					|| IHTMLConstants.TAG_TFOOT.equalsIgnoreCase(name)) {
				// this element is contained in a table, tbody or tfoot
				return renderAsTableRow(hostEle, false, TABLE_ELEMENT);
			} else if (IHTMLConstants.TAG_THEAD.equalsIgnoreCase(name)) {
				// this element is contained in a thead
				return renderAsTableRow(hostEle, true, TABLE_ELEMENT);
			} else if (IHTMLConstants.TAG_TR.equalsIgnoreCase(name)) {
				// this element is contained in a tr
				return renderAsTableCell(hostEle, false, TABLE_ROW_ELEMENT);
			}
		}
		
		// Otherwise, use the default rendering for an unknown tag
		return renderDefault(hostEle, NO_ELEMENT);
	}

	private Element renderDefault(Element hostEle, int tableElement) {
		// rendering for host element not contained in a table
		Element divEle = createElement("div"); //$NON-NLS-1$
		String style = DOMUtil.getAttributeIgnoreCase(hostEle, "style"); //$NON-NLS-1$
		if (style == null) {
			style = ""; //$NON-NLS-1$
		}
		if (style.length() > 0 && !style.endsWith(";")) { //$NON-NLS-1$
			style += ";"; //$NON-NLS-1$
		}
		style += "border: none; padding: 0; margin: 0"; //$NON-NLS-1$
		divEle.setAttribute("style", style); //$NON-NLS-1$
		Element div2 = createElement("span"); //$NON-NLS-1$
        String border = isPreviewMode() ? "border-style: solid;border-width: 1px" : "border:none"; //$NON-NLS-1$ //$NON-NLS-2$
		div2.setAttribute("style", "background-color: white;"+border+";color:gray"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		Text txt = createText(hostEle.getTagName());
		div2.appendChild(txt);

		divEle.appendChild(div2);

		Element childContainer = null;
		switch (tableElement) {
		case TABLE_ELEMENT:
			childContainer = createElement(IHTMLConstants.TAG_TABLE);
			copyChildren(getHostElement(), childContainer);
			break;
		case TABLE_ROW_ELEMENT:
			childContainer = createElement(IHTMLConstants.TAG_TABLE);
			Element trElem = createElement(IHTMLConstants.TAG_TR);
			childContainer.appendChild(trElem);
			copyChildren(getHostElement(), trElem);
			break;
		case NO_ELEMENT:
		default:
			childContainer = createElement("div"); //$NON-NLS-1$
			childContainer.setAttribute("style", "margin: 0; padding: 0"); //$NON-NLS-1$ //$NON-NLS-2$
			copyChildren(getHostElement(), childContainer);
			break;
		}

		divEle.appendChild(childContainer);

		return divEle;
	}

	/*
	 * Creates a table row, and adds either a table cell (data
	 * or a header depending on the boolean flag).
	 */
	private Element renderAsTableRow(Element hostEle, boolean isHeader, int tableElement) {
		Element trElem = createElement(IHTMLConstants.TAG_TR);
		Element tdElem = renderAsTableCell(hostEle, isHeader, tableElement);
		trElem.appendChild(tdElem);
		return trElem;
	}

	/*
	 * Creates a table cell, as either data or a header depending
	 * on the boolean flag.
	 */
	private Element renderAsTableCell(Element hostEle, boolean isHeader, int tableElement) {
		Element tdElem = null;
		if (isHeader) {
			tdElem = createElement(IHTMLConstants.TAG_TH);
		} else {
			tdElem = createElement(IHTMLConstants.TAG_TD);
		}
		tdElem.appendChild(renderDefault(hostEle, tableElement));
		return tdElem;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.converter.ITagConverter#isMultiLevel()
	 */
	public boolean isMultiLevel() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.style.ITagEditInfo#isWidget()
	 */
	public boolean isWidget() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.converter.AbstractTagConverter#needBorderDecorator()
	 */
	public boolean needBorderDecorator() {
		return true;
	}
}
