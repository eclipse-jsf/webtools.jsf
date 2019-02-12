/*******************************************************************************
 * Copyright (c) 2007, 2009 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.core.metadata.internal;

import org.eclipse.jst.jsf.common.metadata.internal.AbstractTagLibDomainContentModelMetaDataTranslator;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataModelMergeAssistant;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataSourceModelProvider;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataTranslator;
import org.eclipse.wst.html.core.internal.contentmodel.HTMLCMDocument;
import org.eclipse.wst.xml.core.internal.contentmodel.CMDocument;

/**
 * Translates the HTML CMDocument to standard metadata model entities and traits
 */
public class HTMLContentModelMetaDataTranslator extends AbstractTagLibDomainContentModelMetaDataTranslator implements IMetaDataTranslator {

	public boolean canTranslate(IMetaDataSourceModelProvider modelProvider) {		
		if (modelProvider != null &&			
				modelProvider.getSourceModel() instanceof HTMLCMDocument)
			return true;
		return false;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.IMetaDataTranslator#translate(org.eclipse.jst.jsf.common.metadata.internal.IMetaDataModelMergeAssistant)
	 */
	public void translate(final IMetaDataModelMergeAssistant assistant) {
		_assistant = assistant;
		CMDocument doc = getSourceModel();
		if (doc instanceof HTMLCMDocument){
			doTranslate(doc);			
		}
	}

	/**
	 *  @return getURIDisplayLabel()
	 */
	protected String getURIDescription() {
		return Messages.HTMLContentModelMetaDataTranslator_Desc;
	}

	/**
	 * return "HTML 4.0"
	 */
	protected String getURIDisplayLabel() {
		return Messages.HTMLContentModelMetaDataTranslator_Label;
	}

}
