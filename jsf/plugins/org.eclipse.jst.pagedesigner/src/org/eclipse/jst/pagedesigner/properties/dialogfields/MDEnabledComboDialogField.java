/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *
 ********************************************************************************/
package org.eclipse.jst.pagedesigner.properties.dialogfields;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.jst.jsf.common.ui.internal.dialogfield.StyleComboDialogField;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;
import org.eclipse.jst.jsf.metadataprocessors.features.IDefaultValue;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValue;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.jst.pagedesigner.editors.properties.IPropertyPageDescriptor;
import org.eclipse.jst.pagedesigner.properties.attrgroup.IElementContextable;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;

/**
 * This combo dialog field defers loading the combos until the element context has been set
 *
 */
public class MDEnabledComboDialogField extends StyleComboDialogField implements IElementContextable {

	/**
	 * Constructor
	 * @param flags
	 */
	public MDEnabledComboDialogField(int flags) {
		super(flags);
	}

	public void setElementContext(IDOMNode ancester, IDOMElement element) {
		IStructuredDocumentContext sdContext =IStructuredDocumentContextFactory.INSTANCE.getContext(element.getStructuredDocument(), element);
		IPropertyPageDescriptor ppd = (IPropertyPageDescriptor)getAttachedData("KEY_ATTR");//FIXME use constant //$NON-NLS-1$
		ppd.setStructuredDocumentContext(sdContext);
		IPossibleValues pvs = (IPossibleValues)ppd.getAdapter(IPossibleValues.class);
		IDefaultValue def = (IDefaultValue)ppd.getAdapter(IDefaultValue.class);
		if (def != null)
			setDefaultValue(def.getDefaultValue());
		if (pvs.getPossibleValues() != null)
			setEntryMap(getMapOfPossibleValues(pvs));
	}

	private Map getMapOfPossibleValues(IPossibleValues pvs) {
		Map map = new TreeMap<String, String>();
		for (Iterator<IPossibleValue> it=pvs.getPossibleValues().iterator();it.hasNext();){
			IPossibleValue pv = it.next();
			map.put(pv.getValue(), pv.getDisplayValue());
		}
		return map;
	}

}
