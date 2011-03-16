/*******************************************************************************
 * Copyright (c) 2005 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ian Trimble - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.pagedesigner.dtmanager;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataDomainContext;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryContextFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.taglib.ITaglibDomainMetaDataQuery;
import org.eclipse.jst.jsf.core.internal.tld.CMUtil;
import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.DTInfo;
import org.eclipse.jst.pagedesigner.utils.StructuredModelUtil;
import org.eclipse.wst.xml.core.internal.provisional.contentmodel.CMDocType;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.w3c.dom.Element;

/**
 * Default IDTInfoFactory implementation.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * @author Ian Trimble - Oracle
 */
public class DefaultDTInfoFactory implements IDTInfoFactory {

	/**
	 * Key (in metadata) of IDTInfo trait.
	 */
	public static final String DTINFO_TRAIT_KEY = "dt-info"; //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.internal.provisional.IDTInfoFactory#getDTInfo(org.w3c.dom.Element)
	 */
	public IDTInfo getDTInfo(final Element element) {
		IDTInfo dtInfo = null;
		final String nsURI = getURI(element);
		if (nsURI != null) {
			final IFile file = getFile(element);
			if (file != null) {
				final IMetaDataDomainContext context = MetaDataQueryContextFactory.getInstance().createTaglibDomainModelContext(file);
				final ITaglibDomainMetaDataQuery query = MetaDataQueryFactory.getInstance().createQuery(context);
				if (query != null) {
					final Trait trait = query.getQueryHelper().getTrait(nsURI, element.getLocalName(), DTINFO_TRAIT_KEY);
					if (trait != null) {
						final DTInfo dtInfoModelObject = (DTInfo)trait.getValue();
						if (dtInfoModelObject != null) {
							dtInfo = new DefaultDTInfo(dtInfoModelObject, trait);
						}
					}
				}
			}
		}
		return dtInfo;
	}


	private String getURI(Element element) {
        String uri = CMUtil.getElementNamespaceURI(element);
        
        // give the content model priority
        if (uri == null)
        {
            uri = element.getNamespaceURI();            
        }

		if (uri == null) //may occur when taglib not setup correctly or incomplete tag elements
			return null;
        if (uri.equals("jsp")) //$NON-NLS-1$
        	uri = CMDocType.JSP11_DOC_TYPE;
        if (uri.equals("html")) //$NON-NLS-1$
        	uri = CMDocType.HTML_DOC_TYPE;
        return uri;
    }
	

	/**
	 * Gets the IProject instance that contains the model of the specified
	 * Element.
	 * 
	 * @param element Element instance.
	 * @return IProject instance that contains the model of the specified
	 * Element.
	 */
	protected IProject getProject(final Element element) {
		IProject project = null;
		if (element instanceof IDOMNode) {
			final IDOMModel model = ((IDOMNode)element).getModel();
			if (model != null) {
				project = StructuredModelUtil.getProjectFor(model);
			}
		}
		return project;
	}

	/**
	 * Gets the IFile of the specified
	 * Element.
	 * 
	 * @param element Element instance.
	 * @return IFile instance that contains the specified
	 * Element.
	 */
	protected IFile getFile(final Element element) {
		IFile file = null;
		if (element instanceof IDOMNode) {
			final IDOMModel model = ((IDOMNode)element).getModel();
			if (model != null) {
				file = StructuredModelUtil.getFileFor(model);
			}
		}
		return file;
	}
}
