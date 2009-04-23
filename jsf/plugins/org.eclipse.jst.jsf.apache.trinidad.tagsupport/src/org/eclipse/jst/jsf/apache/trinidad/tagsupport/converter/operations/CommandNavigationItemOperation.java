/**
 * Copyright (c) 2009 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle Corporation - initial API and implementation
 */
package org.eclipse.jst.jsf.apache.trinidad.tagsupport.converter.operations;

import org.eclipse.jst.jsf.apache.trinidad.tagsupport.ITrinidadConstants;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.jsf.core.internal.tld.TagIdentifierFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * ITransformOperation implementation specifically for the
 * "commandNavigationItem" JSF Element.
 * 
 * <br><b>Note:</b> requires ITransformOperation.setTagConverterContext(...) to
 * have been called to provide a valid ITagConverterContext instance prior to
 * a call to the transform(...) method.
 * 
 * @author Ian Trimble - Oracle
 */
public class CommandNavigationItemOperation extends AbstractTrinidadTransformOperation {

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.operations.AbstractTransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	@Override
	public Element transform(Element srcElement, Element curElement) {
		Element topElement = null;
		if (isDescendentOf(ITrinidadConstants.TAG_IDENTIFIER_BREADCRUMBS, srcElement)) {
			topElement = transformForBreadCrumbs(srcElement);
		} else if (isDescendentOf(ITrinidadConstants.TAG_IDENTIFIER_NAVIGATIONPANE, srcElement)) {
			Element navPane = getAncestor(ITrinidadConstants.TAG_IDENTIFIER_NAVIGATIONPANE, srcElement);
			if (navPane != null) {
				String navPaneHint = navPane.getAttribute("hint"); //$NON-NLS-1$
				if ("choice".equalsIgnoreCase(navPaneHint)) { //$NON-NLS-1$
					topElement = transformForNavigationPane_Choice(srcElement);
				} else {
					topElement = doDefaultTransform(srcElement);
				}
			}
		} else {
			topElement = doDefaultTransform(srcElement);
		}
		return topElement;
	}

	private Element transformForBreadCrumbs(Element srcElement) {
		Element anchor = createElement("a"); //$NON-NLS-1$
		if (!isDisabledOrLastCmdNavItem(srcElement)) {
			appendAttribute(anchor, "href", "#"); //$NON-NLS-1$ //$NON-NLS-2$
			appendAttribute(anchor, "class", "af_breadCrumbs_step"); //$NON-NLS-1$ //$NON-NLS-2$
		} else {
			appendAttribute(anchor, "class", "af_breadCrumbs_selected-step"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		appendChildText(getText(srcElement), anchor);
		return anchor;
	}

	private Element transformForNavigationPane_Choice(Element srcElement) {
		Element option = createElement("option"); //$NON-NLS-1$
		if (isSelected(srcElement)) {
			appendAttribute(option, "selected", "selected"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		appendChildText(getText(srcElement), option);
		return option;
	}

	private Element doDefaultTransform(Element srcElement) {
		Element anchor = createElement("a"); //$NON-NLS-1$
		if (!isDisabled(srcElement)) {
			appendAttribute(anchor, "href", "#"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		appendChildText(getText(srcElement), anchor);
		return anchor;
	}

	private boolean isDescendentOf(TagIdentifier tagIdentifier, Element srcElement) {
		boolean isDescendent = false;
		if (tagIdentifier != null && srcElement != null) {
			Node parentNode = srcElement.getParentNode();
			if (parentNode instanceof Element) {
				if (tagIdentifier.isSameTagType(
						TagIdentifierFactory.createDocumentTagWrapper((Element)parentNode))) {
					isDescendent = true;
				} else if (IJSFConstants.TAG_IDENTIFIER_FACET.isSameTagType(
						TagIdentifierFactory.createDocumentTagWrapper((Element)parentNode))) {
					parentNode = parentNode.getParentNode();
					if (parentNode instanceof Element) {
						if (tagIdentifier.isSameTagType(
								TagIdentifierFactory.createDocumentTagWrapper((Element)parentNode))) {
							isDescendent = true;
						}
					}
				}
			}
		}
		return isDescendent;
	}

	private Element getAncestor(TagIdentifier tagIdentifier, Element srcElement) {
		Element ancestor = null;
		if (tagIdentifier != null && srcElement != null) {
			Node parentNode = srcElement.getParentNode();
			if (parentNode instanceof Element) {
				if (tagIdentifier.isSameTagType(
						TagIdentifierFactory.createDocumentTagWrapper((Element)parentNode))) {
					ancestor = (Element)parentNode;
				} else if (IJSFConstants.TAG_IDENTIFIER_FACET.isSameTagType(
						TagIdentifierFactory.createDocumentTagWrapper((Element)parentNode))) {
					parentNode = parentNode.getParentNode();
					if (parentNode instanceof Element) {
						if (tagIdentifier.isSameTagType(
								TagIdentifierFactory.createDocumentTagWrapper((Element)parentNode))) {
							ancestor = (Element)parentNode;
						}
					}
				}
			}
		}
		return ancestor;
	}

	private String getText(Element srcElement) {
		String text = "commandNavigationItem"; //$NON-NLS-1$
		if (srcElement != null) {
			String newText = srcElement.getAttribute(ITrinidadConstants.ATTR_TEXTANDACCESSKEY);
			if (newText != null && newText.length() > 0) {
				text = newText;
			} else {
				newText = srcElement.getAttribute(ITrinidadConstants.ATTR_TEXT);
				if (newText != null && newText.length() > 0) {
					text = newText;
				} else {
					newText = srcElement.getAttribute(ITrinidadConstants.ATTR_ACTION);
					if (newText != null && newText.length() > 0) {
						text = newText;
					} else {
						newText = srcElement.getAttribute(ITrinidadConstants.ATTR_DESTINATION);
						if (newText != null && newText.length() > 0) {
							text = newText;
						}
					}
				}
			}
		}
		return text;
	}

	private boolean isDisabled(Element srcElement) {
		String disabledAttr = srcElement.getAttribute(ITrinidadConstants.ATTR_DISABLED);
		return Boolean.TRUE.toString().equalsIgnoreCase(disabledAttr);
	}

	private boolean isDisabledOrLastCmdNavItem(Element srcElement) {
		boolean ret = false;
		String disabledAttr = srcElement.getAttribute(ITrinidadConstants.ATTR_DISABLED);
		if (Boolean.TRUE.toString().equalsIgnoreCase(disabledAttr)) {
			ret = true;
		} else {
			Node nextSibling = srcElement;
			while (nextSibling != null) {
				nextSibling = nextSibling.getNextSibling();
				if (nextSibling == null) {
					ret = true;
				} else {
					if (nextSibling instanceof Element) {
						if (TagIdentifierFactory.createDocumentTagWrapper((Element)nextSibling).isSameTagType(ITrinidadConstants.TAG_IDENTIFIER_COMMANDNAVIGATIONITEM)) {
							break;
						}
					}
				}
			}
		}
		return ret;
	}

	private boolean isSelected(Element srcElement) {
		String selectedAttr = srcElement.getAttribute(ITrinidadConstants.ATTR_SELECTED);
		return Boolean.TRUE.toString().equalsIgnoreCase(selectedAttr);
	}

}
