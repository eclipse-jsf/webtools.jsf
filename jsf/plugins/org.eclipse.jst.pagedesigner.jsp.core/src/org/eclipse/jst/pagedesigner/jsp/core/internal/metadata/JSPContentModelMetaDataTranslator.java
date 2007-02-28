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
package org.eclipse.jst.pagedesigner.jsp.core.internal.metadata;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.xml.type.SimpleAnyType;
import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.jst.jsf.common.metadata.internal.AbstractTagLibDomainContentModelMetaDataTranslator;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataModelMergeAssistant;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataTranslator;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.MetadataFactory;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.MetadataPackage;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.Model;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.Trait;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDElementDeclaration;
import org.eclipse.jst.jsp.core.taglib.ITaglibDescriptor;
import org.eclipse.wst.html.core.internal.contentmodel.HTMLCMDocument;
import org.eclipse.wst.html.core.internal.contentmodel.JSPCMDocument;
import org.eclipse.wst.xml.core.internal.contentmodel.CMDocument;
import org.eclipse.wst.xml.core.internal.contentmodel.CMElementDeclaration;

public class JSPContentModelMetaDataTranslator extends AbstractTagLibDomainContentModelMetaDataTranslator implements IMetaDataTranslator {

	public void translate(final IMetaDataModelMergeAssistant assistant) {
		setAssistant(assistant);
		CMDocument doc = getSourceModel();
		if (doc!= null && doc instanceof JSPCMDocument){
			doTranslate(doc);			
		}
	}


	protected String getURIDescription() {		
		return "JSP Tags";
	}

	protected String getURIDisplayLabel() {		
		return "JSP";
	}

}
