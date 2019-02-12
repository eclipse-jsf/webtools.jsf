/**
 * Copyright (c) 2008, 2009 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Oracle Corporation - initial API and implementation
 */
package org.eclipse.jst.jsf.apache.trinidad.tagsupport.converter.operations;

import java.util.Iterator;
import java.util.List;

import org.eclipse.jst.jsf.apache.trinidad.tagsupport.ITrinidadConstants;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.core.internal.tld.TagIdentifierFactory;
import org.eclipse.jst.pagedesigner.converter.ConvertPosition;
import org.w3c.dom.Element;

/**
 * ITransformOperation implementation specifically for the "panelFormLayout"
 * JSF Element.
 * 
 * <br><b>Note:</b> requires ITransformOperation.setTagConverterContext(...) to
 * have been called to provide a valid ITagConverterContext instance prior to
 * a call to the transform(...) method.
 * 
 * @author Ian Trimble - Oracle
 */
public class PanelFormLayoutOperation extends AbstractTrinidadTransformOperation {

	private static final String STYLECLASS_REQUIREDSPAN = "AFRequiredIconStyle"; //$NON-NLS-1$
	private static final String STYLECLASS_GROUPSEPARATOR = "af_panelFormLayout_separator"; //$NON-NLS-1$
	private static final String STYLECLASS_LABELCELL = "af_inputText_label af_panelFormLayout_label-cell"; //$NON-NLS-1$
	private static final String STYLECLASS_FIELDCELL = "af_panelFormLayout_content-cell"; //$NON-NLS-1$

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.operations.AbstractTransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	@Override
	public Element transform(Element srcElement, Element curElement) {
		//calculate required values
		int columnCount = calculateColumnCount(srcElement);
		int rowCount = calculateRowCount(srcElement);
		String fieldWidth = calculateFieldWidth(srcElement);
		String labelWidth = calculateLabelWidth(srcElement);

		//initialize current column/row variables
		int currentColumn = 1;
		int currentRow = 1;
		
		//build outer table
		Element outerTableElement = createElement("table"); //$NON-NLS-1$
		String styleClass = srcElement.getAttribute("styleClass"); //$NON-NLS-1$
		if (styleClass != null && styleClass.length() > 0) {
			appendAttribute(outerTableElement, "class", styleClass); //$NON-NLS-1$
		}
		String inlineStyle = srcElement.getAttribute("inlineStyle"); //$NON-NLS-1$
		if (inlineStyle != null && inlineStyle.length() > 0) {
			appendAttribute(outerTableElement, "style", inlineStyle + ";width:100%;"); //$NON-NLS-1$ //$NON-NLS-2$
		} else {
			appendAttribute(outerTableElement, "style", "width:100%;"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		appendAttribute(outerTableElement, "cellpadding", "0"); //$NON-NLS-1$ //$NON-NLS-2$
		appendAttribute(outerTableElement, "cellspacing", "0"); //$NON-NLS-1$ //$NON-NLS-2$
		appendAttribute(outerTableElement, "border", "0"); //$NON-NLS-1$ //$NON-NLS-2$
		Element outerTBodyElement = appendChildElement("tbody", outerTableElement); //$NON-NLS-1$
		Element outerTopTRElement = appendChildElement("tr", outerTBodyElement); //$NON-NLS-1$

		//build start of column
		Element currentTBodyElement = buildColumnStart(outerTopTRElement, columnCount, currentColumn, labelWidth, fieldWidth);

		//build controls
		List<Element> childElements = getChildElementsSkipFacets(srcElement);
		Iterator<Element> itChildElements = childElements.iterator();
		while (itChildElements.hasNext()) {
			Element childElement = itChildElements.next();
			if (ITrinidadConstants.TAG_IDENTIFIER_GROUP.isSameTagType(
					TagIdentifierFactory.createDocumentTagWrapper(childElement))) {
				if (currentRow > 1) {
					buildGroupSeparatorRow(currentTBodyElement);
				}
				List<Element> groupChildElements = getChildElementsSkipFacets(childElement);
				Iterator<Element> itGroupChildElements = groupChildElements.iterator();
				while (itGroupChildElements.hasNext()) {
					Element groupChildElement = itGroupChildElements.next();
					buildControlRow(groupChildElement, currentTBodyElement);
				}
				currentRow += groupChildElements.size();
				if (currentRow <= rowCount) {
					buildGroupSeparatorRow(currentTBodyElement);
				}
				if (currentRow > rowCount && currentColumn < columnCount) {
					currentRow = 1;
					currentColumn++;
					currentTBodyElement = buildColumnStart(
							outerTopTRElement, columnCount, currentColumn, labelWidth, fieldWidth);
				}
			} else {
				buildControlRow(childElement, currentTBodyElement);
				currentRow++;
				if (currentRow > rowCount && currentColumn < columnCount) {
					currentRow = 1;
					currentColumn++;
					currentTBodyElement = buildColumnStart(
							outerTopTRElement, columnCount, currentColumn, labelWidth, fieldWidth);
				}
			}
		}

		//handle "footer" facet
		Element footerElement = getChildFacetByName(srcElement, "footer"); //$NON-NLS-1$
		if (footerElement != null) {
			List<Element> footerChildElements = getChildElementsSkipFacets(footerElement);
			if (footerChildElements.size() > 0) {
				Element outerBottomTRElement = appendChildElement("tr", outerTBodyElement); //$NON-NLS-1$
				currentTBodyElement = buildFooterStart(
						outerBottomTRElement, columnCount, labelWidth);
				rowCount = countChildElements(footerElement);
				currentRow = 1;
				Iterator<Element> itFooterChildElements = footerChildElements.iterator();
				while (itFooterChildElements.hasNext()) {
					Element footerChildElement = itFooterChildElements.next();
					if (ITrinidadConstants.TAG_IDENTIFIER_GROUP.isSameTagType(
							TagIdentifierFactory.createDocumentTagWrapper(footerChildElement))) {
						if (currentRow > 1) {
							buildGroupSeparatorRow(currentTBodyElement);
						}
						List<Element> groupFooterChildElements = getChildElementsSkipFacets(footerChildElement);
						Iterator<Element> itGroupFooterChildElements = groupFooterChildElements.iterator();
						while (itGroupFooterChildElements.hasNext()) {
							Element groupFooterChildElement = itGroupFooterChildElements.next();
							if (ITrinidadConstants.TAG_IDENTIFIER_GROUP.isSameTagType(
									TagIdentifierFactory.createDocumentTagWrapper(groupFooterChildElement))) {
								if (currentRow > 1) {
									buildGroupSeparatorRow(currentTBodyElement);
								}
								List<Element> subGroupFooterChildElements = getChildElementsSkipFacets(groupFooterChildElement);
								Iterator<Element> itSubGroupFooterChildElements = subGroupFooterChildElements.iterator();
								while (itSubGroupFooterChildElements.hasNext()) {
									Element subGroupFooterChildElement = itSubGroupFooterChildElements.next();
									buildControlRow(subGroupFooterChildElement, currentTBodyElement, true);
									currentRow++;
								}
								if (currentRow <= rowCount) {
									buildGroupSeparatorRow(currentTBodyElement);
								}
							} else {
								buildControlRow(groupFooterChildElement, currentTBodyElement, true);
								currentRow++;
							}
						}
						if (currentRow <= rowCount) {
							buildGroupSeparatorRow(currentTBodyElement);
						}
					} else {
						buildControlRow(footerChildElement, currentTBodyElement, true);
						currentRow++;
					}
				}
			}
		}

		return outerTableElement;
	}

	private int calculateRowCount(Element srcElement) {
		int rowCount = Integer.MAX_VALUE;
		int specifiedRows = Integer.MAX_VALUE;
		String rows = srcElement.getAttribute("rows"); //$NON-NLS-1$
		if (rows != null && rows.length() > 0) {
			try {
				//not spec'ed, but observed that setting rows < 5 is ignored
				specifiedRows = Math.max(5, Integer.parseInt(rows));
			} catch(NumberFormatException nfe) {
				//ignore - specifiedRows will default to Integer.MAX_VALUE
			}
		}
		int specifiedMaxColumns = Integer.MAX_VALUE;
		String maxColumns = srcElement.getAttribute("maxColumns"); //$NON-NLS-1$
		if (maxColumns != null && maxColumns.length() > 0) {
			try {
				specifiedMaxColumns = Integer.parseInt(maxColumns);
			} catch(NumberFormatException nfe) {
				//ignore - specifiedMaxColumns will default to Integer.MAX_VALUE
			}
		}
		int childElementCount = countChildElements(srcElement);
		int calculatedColumns = childElementCount / specifiedRows;
		if (childElementCount % specifiedRows > 0) {
			calculatedColumns++;
		}
		if (calculatedColumns > specifiedMaxColumns) {
			rowCount = childElementCount / specifiedMaxColumns;
		} else {
			rowCount = specifiedRows;
		}
		return rowCount;
	}

	private int calculateColumnCount(Element srcElement) {
		int columnCount = Integer.MAX_VALUE;
		int specifiedRows = Integer.MAX_VALUE;
		String rows = srcElement.getAttribute("rows"); //$NON-NLS-1$
		if (rows != null && rows.length() > 0) {
			try {
				//not spec'ed, but observed that setting rows < 5 is ignored
				specifiedRows = Math.max(5, Integer.parseInt(rows));
			} catch(NumberFormatException nfe) {
				//ignore - specifiedRows will default to Integer.MAX_VALUE
			}
		}
		int specifiedMaxColumns = Integer.MAX_VALUE;
		String maxColumns = srcElement.getAttribute("maxColumns"); //$NON-NLS-1$
		if (maxColumns != null && maxColumns.length() > 0) {
			try {
				specifiedMaxColumns = Integer.parseInt(maxColumns);
			} catch(NumberFormatException nfe) {
				//ignore - specifiedMaxColumns will default to Integer.MAX_VALUE
			}
		}
		int childElementCount = countChildElements(srcElement);
		int calculatedColumns = childElementCount / specifiedRows;
		if (childElementCount % specifiedRows > 0) {
			calculatedColumns++;
		}
		columnCount = Math.min(specifiedMaxColumns, calculatedColumns);
		return columnCount;
	}

	private int countChildElements(Element srcElement) {
		int count = 0;
		List<Element> childElements = getChildElementsSkipFacets(srcElement);
		Iterator<Element> itChildElements = childElements.iterator();
		while (itChildElements.hasNext()) {
			Element childElement = itChildElements.next();
			if (ITrinidadConstants.TAG_IDENTIFIER_GROUP.isSameTagType(
					TagIdentifierFactory.createDocumentTagWrapper(childElement))) {
				count += countChildElements(childElement);
			} else {
				count++;
			}
		}
		return count;
	}

	private String calculateFieldWidth(Element srcElement) {
		String width = null;
		String fieldWidth = srcElement.getAttribute("fieldWidth"); //$NON-NLS-1$
		if (fieldWidth != null && fieldWidth.length() > 0) {
			width = fieldWidth;
		} else {
			String labelWidth = srcElement.getAttribute("labelWidth"); //$NON-NLS-1$
			if (labelWidth != null && labelWidth.length() > 1) {
				if (labelWidth.endsWith("%")) { //$NON-NLS-1$
					try {
						int iLabelWidth = Integer.parseInt(labelWidth.substring(0, labelWidth.length() - 1));
						if (iLabelWidth <= 100) {
							width = String.valueOf(100 - iLabelWidth) + "%"; //$NON-NLS-1$
						}
					} catch(NumberFormatException nfe) {
						//ignore - cannot calculate and so will return null
					}
				}
			}
		}
		return width;
	}

	private String calculateLabelWidth(Element srcElement) {
		String width = null;
		String labelWidth = srcElement.getAttribute("labelWidth"); //$NON-NLS-1$
		if (labelWidth != null && labelWidth.length() > 0) {
			width = labelWidth;
		} else {
			String fieldWidth = srcElement.getAttribute("fieldWidth"); //$NON-NLS-1$
			if (fieldWidth != null && fieldWidth.length() > 1) {
				if (fieldWidth.endsWith("%")) { //$NON-NLS-1$
					try {
						int iFieldWidth = Integer.parseInt(fieldWidth.substring(0, fieldWidth.length() - 1));
						if (iFieldWidth <= 100) {
							width = String.valueOf(100 - iFieldWidth) + "%"; //$NON-NLS-1$
						}
					} catch(NumberFormatException nfe) {
						//ignore - cannot calculate and so will return null
					}
				}
			}
		}
		return width;
	}

	private Element buildColumnStart(
			Element parentElement, int columnCount, int currentColumn,
			String labelWidth, String fieldWidth) {
		Element tdElement = appendChildElement("td", parentElement); //$NON-NLS-1$
		appendAttribute(tdElement, "style", "vertical-align:top;"); //$NON-NLS-1$ //$NON-NLS-2$
		if (currentColumn < columnCount) {
			String width = String.valueOf(100 / columnCount) + "%"; //$NON-NLS-1$
			appendAttribute(tdElement, "width", width); //$NON-NLS-1$
		}
		Element tableElement = appendChildElement("table", tdElement); //$NON-NLS-1$
		appendAttribute(tableElement, "cellpadding", "0"); //$NON-NLS-1$ //$NON-NLS-2$
		appendAttribute(tableElement, "cellspacing", "0"); //$NON-NLS-1$ //$NON-NLS-2$
		appendAttribute(tableElement, "border", "0"); //$NON-NLS-1$ //$NON-NLS-2$
		appendAttribute(tableElement, "width", "100%"); //$NON-NLS-1$ //$NON-NLS-2$
		Element tBodyElement = appendChildElement("tbody", tableElement); //$NON-NLS-1$
		Element trElement = appendChildElement("tr", tBodyElement); //$NON-NLS-1$
		Element tdLabelElement = appendChildElement("td", trElement); //$NON-NLS-1$
		if (labelWidth != null && labelWidth.length() > 0) {
			appendAttribute(tdLabelElement, "style", "width:" + labelWidth); //$NON-NLS-1$ //$NON-NLS-2$
		}
		Element tdFieldElement = appendChildElement("td", trElement); //$NON-NLS-1$
		if (fieldWidth != null && fieldWidth.length() > 0) {
			appendAttribute(tdFieldElement, "style", "width:" + fieldWidth); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return tBodyElement;
	}

	private void buildControlRow(Element srcElement, Element parentElement, boolean alignLabelsTop) {
		Element trElement = appendChildElement("tr", parentElement); //$NON-NLS-1$
		Element tdLabelElement = appendChildElement("td", trElement); //$NON-NLS-1$
		appendAttribute(tdLabelElement, "class", STYLECLASS_LABELCELL); //$NON-NLS-1$
		if (alignLabelsTop) {
			appendAttribute(tdLabelElement, "valign", "top"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		appendAttribute(tdLabelElement, "nowrap", ""); //$NON-NLS-1$ //$NON-NLS-2$
		buildLabel(srcElement, tdLabelElement);
		Element tdFieldElement = appendChildElement("td", trElement); //$NON-NLS-1$
		appendAttribute(tdFieldElement, "class", STYLECLASS_FIELDCELL); //$NON-NLS-1$
		appendAttribute(tdFieldElement, "valign", "top"); //$NON-NLS-1$ //$NON-NLS-2$
		appendAttribute(tdFieldElement, "nowrap", ""); //$NON-NLS-1$ //$NON-NLS-2$
		tagConverterContext.addChild(srcElement, new ConvertPosition(tdFieldElement, 0));
	}

	private void buildControlRow(Element srcElement, Element parentElement) {
		buildControlRow(srcElement, parentElement, false);
	}

	private void buildLabel(Element srcElement, Element parentElement) {
		TagIdentifier tagID = TagIdentifierFactory.createDocumentTagWrapper(srcElement);
		if (
				tagID.isSameTagType(ITrinidadConstants.TAG_IDENTIFIER_INPUTCOLOR) ||
				tagID.isSameTagType(ITrinidadConstants.TAG_IDENTIFIER_INPUTDATE) ||
				tagID.isSameTagType(ITrinidadConstants.TAG_IDENTIFIER_INPUTFILE) ||
				tagID.isSameTagType(ITrinidadConstants.TAG_IDENTIFIER_INPUTLISTOFVALUES) ||
				tagID.isSameTagType(ITrinidadConstants.TAG_IDENTIFIER_INPUTNUMBERSPINBOX) ||
				tagID.isSameTagType(ITrinidadConstants.TAG_IDENTIFIER_INPUTTEXT) ||
				tagID.isSameTagType(ITrinidadConstants.TAG_IDENTIFIER_SELECTBOOLEANCHECKBOX) ||
				tagID.isSameTagType(ITrinidadConstants.TAG_IDENTIFIER_SELECTBOOLEANRADIO) ||
				tagID.isSameTagType(ITrinidadConstants.TAG_IDENTIFIER_SELECTMANYCHECKBOX) ||
				tagID.isSameTagType(ITrinidadConstants.TAG_IDENTIFIER_SELECTMANYLISTBOX) ||
				tagID.isSameTagType(ITrinidadConstants.TAG_IDENTIFIER_SELECTONECHOICE) ||
				tagID.isSameTagType(ITrinidadConstants.TAG_IDENTIFIER_SELECTONELISTBOX) ||
				tagID.isSameTagType(ITrinidadConstants.TAG_IDENTIFIER_SELECTONERADIO) ||
				tagID.isSameTagType(ITrinidadConstants.TAG_IDENTIFIER_PANELLABELANDMESSAGE)) {
			String required = srcElement.getAttribute("required"); //$NON-NLS-1$
			String showRequired = srcElement.getAttribute("showRequired"); //$NON-NLS-1$
			if (Boolean.parseBoolean(required) || Boolean.parseBoolean(showRequired)) {
				Element spanElement = appendChildElement("span", parentElement); //$NON-NLS-1$
				appendAttribute(spanElement, "title", "Required"); //$NON-NLS-1$ //$NON-NLS-2$
				appendAttribute(spanElement, "class", STYLECLASS_REQUIREDSPAN); //$NON-NLS-1$
				appendChildText("* ", spanElement); //$NON-NLS-1$
			}
			String label = srcElement.getAttribute("labelAndAccessKey"); //$NON-NLS-1$
			if (label == null || label.length() < 1) {
				label = srcElement.getAttribute("label"); //$NON-NLS-1$
			}
			if (label != null && label.length() > 0) {
				Element labelElement = appendChildElement("label", parentElement); //$NON-NLS-1$
				appendChildText(label, labelElement);
			}
		}
	}

	private void buildGroupSeparatorRow(Element parentElement) {
		Element trElement = appendChildElement("tr", parentElement); //$NON-NLS-1$
		Element tdElement = appendChildElement("td", trElement); //$NON-NLS-1$
		appendAttribute(tdElement, "colspan", "2"); //$NON-NLS-1$ //$NON-NLS-2$
		Element divElement = appendChildElement("div", tdElement); //$NON-NLS-1$
		appendAttribute(divElement, "class", STYLECLASS_GROUPSEPARATOR); //$NON-NLS-1$
	}

	private Element buildFooterStart(
			Element parentElement, int columnCount, String labelWidth) {
		Element tdElement = appendChildElement("td", parentElement); //$NON-NLS-1$
		appendAttribute(tdElement, "style", "vertical-align:top;"); //$NON-NLS-1$ //$NON-NLS-2$
		appendAttribute(tdElement, "colspan", String.valueOf(columnCount)); //$NON-NLS-1$
		Element tableElement = appendChildElement("table", tdElement); //$NON-NLS-1$
		appendAttribute(tableElement, "cellpadding", "0"); //$NON-NLS-1$ //$NON-NLS-2$
		appendAttribute(tableElement, "cellspacing", "0"); //$NON-NLS-1$ //$NON-NLS-2$
		appendAttribute(tableElement, "border", "0"); //$NON-NLS-1$ //$NON-NLS-2$
		appendAttribute(tableElement, "width", "100%"); //$NON-NLS-1$ //$NON-NLS-2$
		Element tBodyElement = appendChildElement("tbody", tableElement); //$NON-NLS-1$
		Element trElement = appendChildElement("tr", tBodyElement); //$NON-NLS-1$
		String footerLabelWidth = null;
		String footerFieldWidth = null;
		if (labelWidth != null && labelWidth.length() > 0) {
			if (labelWidth.endsWith("%")) { //$NON-NLS-1$
				try {
					int iLabelWidth = Integer.parseInt(labelWidth.substring(0, labelWidth.length() - 1));
					iLabelWidth = iLabelWidth / columnCount;
					footerLabelWidth = String.valueOf(iLabelWidth) + "%"; //$NON-NLS-1$
					footerFieldWidth = String.valueOf(100 - iLabelWidth) + "%"; //$NON-NLS-1$
				} catch(NumberFormatException nfe) {
					//ignore - cannot calculate, widths remain null
				}
			} else {
				footerLabelWidth = labelWidth;
			}
		}
		Element tdLabelElement = appendChildElement("td", trElement); //$NON-NLS-1$
		if (footerLabelWidth != null && footerLabelWidth.length() > 0) {
			//appendAttribute(tdLabelElement, "style", "width:" + footerLabelWidth); //$NON-NLS-1$ //$NON-NLS-2$
			appendAttribute(tdLabelElement, "width", footerLabelWidth); //$NON-NLS-1$
		}
		Element tdFieldElement = appendChildElement("td", trElement); //$NON-NLS-1$
		if (footerFieldWidth != null && footerFieldWidth.length() > 0) {
			//appendAttribute(tdFieldElement, "style", "width:" + footerFieldWidth); //$NON-NLS-1$ //$NON-NLS-2$
			appendAttribute(tdFieldElement, "width", footerFieldWidth); //$NON-NLS-1$
		}
		return tBodyElement;
	}

}
