/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.taglibprocessing;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.jsf.context.resolver.structureddocument.IDOMContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.impl.StructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.metadataprocessors.AbstractRootTypeDescriptor;
import org.eclipse.jst.jsf.metadataprocessors.IMetaDataEnabledFeature;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValue;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.features.PossibleValue;
import org.eclipse.jst.pagedesigner.css2.CSSUtil;
import org.w3c.dom.Document;

/**
 * Represents a style class attribute type
 *
 */
public class CSSClassType extends AbstractRootTypeDescriptor implements
		IMetaDataEnabledFeature, IPossibleValues {

	private List<IPossibleValue> _pvs;
	
	public List<IPossibleValue> getPossibleValues() {
		if (_pvs == null){
			IDOMContextResolver resolver = StructuredDocumentContextResolverFactory.getInstance().getDOMContextResolver(getStructuredDocumentContext());
			_pvs = getCSSClasses(resolver.getDOMDocument());
		}
		return _pvs;
	}

	private List<IPossibleValue> getCSSClasses(Document doc) {
		List result = new ArrayList();
		String [] names = CSSUtil.getCSSClasses(doc);
		for (int i=0;i<names.length;i++){
			IPossibleValue pv = new PossibleValue(names[i]);
			result.add(pv);
		}
		return result;
	}

}
