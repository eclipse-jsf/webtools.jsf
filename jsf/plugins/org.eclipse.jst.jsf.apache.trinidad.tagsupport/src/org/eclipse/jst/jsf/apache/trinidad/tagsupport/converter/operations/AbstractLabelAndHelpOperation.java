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

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jst.jsf.apache.trinidad.tagsupport.ITrinidadConstants;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.jsf.core.internal.tld.TagIdentifierFactory;
import org.eclipse.jst.pagedesigner.converter.ConvertPosition;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/**
 * Abstract ITransformOperation implementation for tags which may be preceded by
 * a label and may have a "help" facet.
 * 
 * <br><b>Note:</b> requires ITransformOperation.setTagConverterContext(...) to
 * have been called to provide a valid ITagConverterContext instance prior to
 * a call to the transform(...) method.
 * 
 * @author Ian Trimble - Oracle
 */
public abstract class AbstractLabelAndHelpOperation extends AbstractTrinidadTransformOperation {

	private static final String STYLECLASS_OUTERELEMENT = "af_inputText p_AFRequired"; //$NON-NLS-1$
	private static final String STYLECLASS_LABELTD = "af_inputText_label"; //$NON-NLS-1$
	private static final String STYLECLASS_REQUIREDSPAN = "AFRequiredIconStyle"; //$NON-NLS-1$
	private static final String STYLECLASS_CONTROLTD = "AFContentCell"; //$NON-NLS-1$
	protected static final String STYLECLASS_CONTROLELEMENT = "af_inputText_content"; //$NON-NLS-1$
	private static final String STYLECLASS_HELPTD = "AFComponentMessageCell"; //$NON-NLS-1$
	private static final String STYLECLASS_HELPSPAN = "OraInlineInfoText"; //$NON-NLS-1$
	
	/**
	 * Subclasses must implement this method to append the desired DOM structure
	 * that represents the HTML control(s) to parentElement.
	 * 
	 * @param srcElement Source Element representing source document tag.
	 * @param parentElement Parent Element to which structure is appended.
	 */
	protected abstract void appendControl(Element srcElement, Element parentElement);

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.operations.AbstractTransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	@Override
	public Element transform(Element srcElement, Element curElement) {
		Element outerElement;

		//create outer Element
		String simple = srcElement.getAttribute("simple"); //$NON-NLS-1$
		if (Boolean.valueOf(simple)) {
			outerElement = createElement("span"); //$NON-NLS-1$
		} else {
			outerElement = createElement("table"); //$NON-NLS-1$
			//append table-specific attributes
			appendAttribute(outerElement, "cellpadding", "0"); //$NON-NLS-1$ //$NON-NLS-2$
			appendAttribute(outerElement, "cellspacing", "0"); //$NON-NLS-1$ //$NON-NLS-2$
			appendAttribute(outerElement, "border", "0"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		//append common attributes
		appendAttribute(outerElement, "class", //$NON-NLS-1$
				calculateStyleClass(STYLECLASS_OUTERELEMENT, srcElement, "styleClass")); //$NON-NLS-1$
		String inlineStyle = calculateStyle(null, srcElement, "inlineStyle"); //$NON-NLS-1$
		if (inlineStyle != null) {
			appendAttribute(outerElement, "style", inlineStyle); //$NON-NLS-1$
		}		

		if (Boolean.valueOf(simple)) {
			//continue building simple variant
			appendControl(srcElement, outerElement);
		} else {
			//continue building non-simple variant
			Element trElement = appendChildElement("tr", outerElement); //$NON-NLS-1$

			if (isRequired(srcElement) || getLabel(srcElement) != null) {
				//if either required or has label, build top-left table cell
				Element tdElement = appendChildElement("td", trElement); //$NON-NLS-1$
				appendAttribute(tdElement, "class", STYLECLASS_LABELTD); //$NON-NLS-1$
				appendAttribute(tdElement, "valign", "top"); //$NON-NLS-1$ //$NON-NLS-2$
				appendAttribute(tdElement, "nowrap", ""); //$NON-NLS-1$ //$NON-NLS-2$

				if (isRequired(srcElement)) {
					Element spanElement = appendChildElement("span", tdElement); //$NON-NLS-1$
					appendAttribute(spanElement, "title", "Required"); //$NON-NLS-1$ //$NON-NLS-2$
					appendAttribute(spanElement, "class", STYLECLASS_REQUIREDSPAN); //$NON-NLS-1$
					appendChildText("* ", spanElement); //$NON-NLS-1$
				}

				if (getLabel(srcElement) != null) {
					Element labelElement = appendChildElement("label", tdElement); //$NON-NLS-1$
					appendChildText(getLabel(srcElement), labelElement);
				}
			}

			//build content table cell
			Element tdElement = appendChildElement("td", trElement); //$NON-NLS-1$
			appendAttribute(tdElement, "class", STYLECLASS_CONTROLTD); //$NON-NLS-1$
			appendAttribute(tdElement, "valign", "top"); //$NON-NLS-1$ //$NON-NLS-2$
			appendAttribute(tdElement, "nowrap", ""); //$NON-NLS-1$ //$NON-NLS-2$
			appendControl(srcElement, tdElement);

			List<Node> helpFacetChildNodes = getHelpFacetChildNodes(srcElement);
			if (helpFacetChildNodes.size() > 0) {
				//build help table row
				trElement = appendChildElement("tr", outerElement); //$NON-NLS-1$
				if (isRequired(srcElement) || getLabel(srcElement) != null) {
					tdElement = appendChildElement("td", trElement); //$NON-NLS-1$
				}
				tdElement = appendChildElement("td", trElement); //$NON-NLS-1$
				appendAttribute(tdElement, "class", STYLECLASS_HELPTD); //$NON-NLS-1$
				Element spanElement = appendChildElement("span", tdElement); //$NON-NLS-1$
				appendAttribute(spanElement, "class", //$NON-NLS-1$
						calculateStyle(STYLECLASS_HELPSPAN, srcElement, "styleClass")); //$NON-NLS-1$
				if (inlineStyle != null) {
					appendAttribute(spanElement, "style", inlineStyle); //$NON-NLS-1$
				}
				Iterator<Node> itHelpFacetChildNodes = helpFacetChildNodes.iterator();
				int curPos = 0;
				while (itHelpFacetChildNodes.hasNext()) {
					Node curNode = itHelpFacetChildNodes.next();
					if (curNode instanceof Text) {
						if (curNode.getNodeValue() != null && curNode.getNodeValue().length() > 0) {
							tagConverterContext.addChild(
									curNode,
									new ConvertPosition(spanElement, curPos));
						}
					} else {
						tagConverterContext.addChild(
								curNode,
								new ConvertPosition(spanElement, curPos));
					}
					curPos++;
				}
			}
		}

		return outerElement;
	}

	protected String calculateStyle(
			String baseStyle, Element srcElement, String styleAttributeName) {
		String style = baseStyle;
		if (srcElement != null && styleAttributeName != null) {
			String srcElementStyle = srcElement.getAttribute(styleAttributeName);
			if (srcElementStyle != null && srcElementStyle.length() > 0) {
				if (style != null) {
					if (!style.endsWith(";")) { //$NON-NLS-1$
						style += ";"; //$NON-NLS-1$
					}
					style += srcElementStyle;
				} else {
					style = srcElementStyle;
				}
			}
		}
		return style;
	}

	protected String calculateStyleClass(
			String baseStyleClass, Element srcElement, String styleClassAttributeName) {
		String styleClass = baseStyleClass;
		if (srcElement != null && styleClassAttributeName != null) {
			String srcElementStyleClass = srcElement.getAttribute(styleClassAttributeName);
			if (srcElementStyleClass != null && srcElementStyleClass.length() > 0) {
				if (styleClass != null) {
					if (!styleClass.endsWith(",")) { //$NON-NLS-1$
						styleClass += ","; //$NON-NLS-1$
					}
					styleClass += srcElementStyleClass;
				} else {
					styleClass = srcElementStyleClass;
				}
			}
		}
		return styleClass;
	}

	protected boolean isRequired(Element srcElement) {
		boolean required = false;
		if (!isChildOfPanelFormLayout(srcElement)) {
			if (srcElement != null) {
				String requiredVal = srcElement.getAttribute("required"); //$NON-NLS-1$
				String showRequiredVal = srcElement.getAttribute("showRequired"); //$NON-NLS-1$
				//if either are true, for the purposes of tag conversion, consider required to be true
				required =
					Boolean.parseBoolean(requiredVal) ||
					Boolean.parseBoolean(showRequiredVal);
			}
		}
		return required;
	}

	protected String getLabel(Element srcElement) {
		String label = null;
		if (!isChildOfPanelFormLayout(srcElement)) {
			if (srcElement != null) {
				String labelAndAccessKeyVal = srcElement.getAttribute("labelAndAccessKey"); //$NON-NLS-1$
				if (labelAndAccessKeyVal != null && labelAndAccessKeyVal.length() > 0) {
					label = labelAndAccessKeyVal;
				} else {
					String labelVal = srcElement.getAttribute("label"); //$NON-NLS-1$
					if (labelVal != null && labelVal.length() > 0) {
						label = labelVal;
					}
				}
			}
		}
		return label;
	}

	protected List<Node> getHelpFacetChildNodes(Element srcElement) {
		@SuppressWarnings("unchecked")
		List<Node> children = Collections.EMPTY_LIST;
		Element helpFacet = getChildFacetByName(srcElement, "help"); //$NON-NLS-1$
		if (helpFacet != null) {
			children = getCopyChildrenNodes(helpFacet);
		}
		return children;
	}

	protected int getColumns(Element srcElement) {
		int columns = 30;
		if (srcElement != null) {
			String columnsValue = srcElement.getAttribute("columns"); //$NON-NLS-1$
			if (columnsValue != null && columnsValue.length() > 0) {
				try {
					columns = Integer.parseInt(columnsValue);
				} catch(NumberFormatException nfe) {
					//ignore; default value will be returned
				}
			}
		}
		return columns;
	}

	protected boolean isChildOfPanelFormLayout(Element srcElement) {
		boolean isChild = false;
		if (srcElement != null) {
			Node parent = srcElement.getParentNode();
			if (parent instanceof Element) {
				TagIdentifier tagID = TagIdentifierFactory.createDocumentTagWrapper((Element)parent);
				if (ITrinidadConstants.TAG_IDENTIFIER_PANELFORMLAYOUT.isSameTagType(tagID)) {
					isChild = true;
				} else if (ITrinidadConstants.TAG_IDENTIFIER_GROUP.isSameTagType(tagID)) {
					isChild = isChildOfPanelFormLayout((Element)parent);
				} else if (IJSFConstants.TAG_IDENTIFIER_FACET.isSameTagType(tagID)) {
					isChild = isChildOfPanelFormLayout((Element)parent);
				}
			}
		}
		return isChild;
	}

}
