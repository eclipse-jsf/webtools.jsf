/*******************************************************************************
 * Copyright (c) 2007, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.taglibprocessing.attributevalues;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.jst.jsf.context.resolver.structureddocument.IDOMContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.impl.StructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.metadataprocessors.AbstractRootTypeDescriptor;
import org.eclipse.jst.jsf.metadataprocessors.IMetaDataEnabledFeature;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValue;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.features.PossibleValue;
import org.eclipse.wst.css.core.internal.provisional.adapters.IStyleSheetListAdapter;
import org.eclipse.wst.css.core.internal.provisional.document.ICSSNode;
import org.eclipse.wst.css.core.internal.util.CSSClassTraverser;
import org.eclipse.wst.sse.core.internal.provisional.INodeNotifier;
import org.w3c.dom.Document;
import org.w3c.dom.stylesheets.StyleSheetList;

/**
 * Represents a style class attribute type
 * <p><b>Provisional API - subject to change</b></p>
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
		String [] names = internalGetCSSClasses(doc);
		for (int i=0;i<names.length;i++){
			IPossibleValue pv = new PossibleValue(names[i]);
			result.add(pv);
		}
		return result;
	}
	
	//code duplicated from pagedesigner CSSUtils.  Uses internal CSSClassTraverser
	private static String[] internalGetCSSClasses(Document doc) {
		Collection c = Collections.EMPTY_SET;
		if (doc instanceof INodeNotifier) {
			IStyleSheetListAdapter adapter = (IStyleSheetListAdapter) ((INodeNotifier) doc)
					.getAdapterFor(IStyleSheetListAdapter.class);
			StyleSheetList ssl = (adapter == null ? null : adapter
					.getStyleSheets());

			CSSClassTraverser traverser = new CSSClassTraverser();
			if (ssl != null) {
				for (int i = 0, numStyles = ssl.getLength(); i < numStyles; i++) {
					// loop for styles (<style> and <link>)
					org.w3c.dom.stylesheets.StyleSheet ss = ssl.item(i);

					try {
						traverser.apply((ICSSNode) ss);
					} catch (ClassCastException ex) {
						JSFCorePlugin.log(ex, "Unable to cast to CSS style"); //$NON-NLS-1$
					}
				}
				c = traverser.getClassNames();
			}
		}
		String[] result = new String[c.size()];
		c.toArray(result);
		return result;
	}
}
