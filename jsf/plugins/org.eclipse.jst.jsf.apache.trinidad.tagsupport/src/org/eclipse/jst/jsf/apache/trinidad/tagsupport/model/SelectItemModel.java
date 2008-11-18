/**
 * Copyright (c) 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle Corporation - initial API and implementation
 */
package org.eclipse.jst.jsf.apache.trinidad.tagsupport.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.jsf.apache.trinidad.tagsupport.ITrinidadConstants;
import org.eclipse.jst.jsf.apache.trinidad.tagsupport.Messages;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.jsf.core.internal.tld.TagIdentifierFactory;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Provides a simple "selectItem"-like model to facilitate handling of both
 * standard JSF core selectItem tags and Trinidad selectItem tags, or a sample
 * model in the absence of either.  
 * 
 * @author Ian Trimble - Oracle
 */
public class SelectItemModel {

	private static final List<SelectItem> SAMPLE_MODEL = new ArrayList<SelectItem>();
	static {
		SAMPLE_MODEL.add(new SelectItem(
				Messages.SelectItemModel_SampleItem1Label,
				Messages.SelectItemModel_SampleItem1Value));
		SAMPLE_MODEL.add(new SelectItem(
				Messages.SelectItemModel_SampleItem2Label,
				Messages.SelectItemModel_SampleItem2Value));
		SAMPLE_MODEL.add(new SelectItem(
				Messages.SelectItemModel_SampleItem3Label,
				Messages.SelectItemModel_SampleItem3Value));
	}

	/**
	 * Gets a model as a List of SelectItem instances, derived from child
	 * selectItem Elements, or a sample model if no suitable child Elements are
	 * found.
	 * 
	 * @param parentElement Parent Element instance to scan for child selectItem
	 * Elements.
	 * @return List of SelectItem instances.
	 */
	public static final List<SelectItem> getModel(Element parentElement) {
		return getModel(parentElement, true);
	}

	/**
	 * Gets a model as a List of SelectItem instances, derived from child
	 * selectItem Elements, or (optionally) a sample model if no suitable
	 * child Elements are found.
	 * 
	 * @param parentElement Parent Element instance to scan for child selectItem
	 * Elements.
	 * @param fallbackOnSampleModel If true and no suitable child Elements are
	 * found, return sample model.
	 * @return List of SelectItem instances (may be empty, but not null).
	 */
	public static final List<SelectItem> getModel(
			Element parentElement, boolean fallbackOnSampleModel) {
		List<SelectItem> selectItems = new ArrayList<SelectItem>();
		if (parentElement != null) {
			NodeList childNodes = parentElement.getChildNodes();
			for (int i = 0; i < childNodes.getLength(); i++) {
				if (childNodes.item(i) instanceof Element) {
					Element childElement = (Element)childNodes.item(i);
					TagIdentifier tagID =
						TagIdentifierFactory.createDocumentTagWrapper(childElement);
					if (tagID.isSameTagType(ITrinidadConstants.TAG_IDENTIFIER_SELECTITEM)) {
						String label = childElement.getAttribute("label"); //$NON-NLS-1$
						if (label == null || label.length() < 1) {
							label = childElement.getAttribute("value"); //$NON-NLS-1$
						}
						selectItems.add(new SelectItem(
								label, childElement.getAttribute("value"))); //$NON-NLS-1$
					} else if (tagID.isSameTagType(IJSFConstants.TAG_IDENTIFIER_SELECTITEM)) {
						String value = childElement.getAttribute("value"); //$NON-NLS-1$
						if (value == null || value.length() < 1) {
							value = childElement.getAttribute("itemValue"); //$NON-NLS-1$
						}
						String label = childElement.getAttribute("itemLabel"); //$NON-NLS-1$
						if (label == null || label.length() < 1) {
							label = value;
						}
						selectItems.add(new SelectItem(label, value));
					}
				}
			}
		}
		if (selectItems.size() > 0 || !fallbackOnSampleModel) {
			return selectItems;
		} else {
			return getSampleModel();
		}
	}

	/**
	 * Gets a sample model as a List of SelectItem instances.
	 * 
	 * @return Sample model as a List of SelectItem instances.
	 */
	public static final List<SelectItem> getSampleModel() {
		return SAMPLE_MODEL;
	}

}
