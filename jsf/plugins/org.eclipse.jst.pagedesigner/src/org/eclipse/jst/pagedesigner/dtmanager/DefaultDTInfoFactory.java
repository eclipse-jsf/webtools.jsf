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

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.query.ITaglibDomainMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.query.TaglibDomainMetaDataQueryHelper;
import org.eclipse.jst.jsf.core.internal.tld.CMUtil;
import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.DTInfo;
import org.eclipse.jst.pagedesigner.utils.StructuredModelUtil;
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
	public IDTInfo getDTInfo(Element element) {
		IDTInfo dtInfo = null;
		String nsURI = CMUtil.getElementNamespaceURI(element);
		IProject project = getProject(element);
		if (project != null) {
			ITaglibDomainMetaDataModelContext context = TaglibDomainMetaDataQueryHelper.createMetaDataModelContext(project, nsURI);
			if (context != null) {
				Trait trait = TaglibDomainMetaDataQueryHelper.getTrait(context, element.getLocalName(), DTINFO_TRAIT_KEY);
				if (trait != null) {
					DTInfo dtInfoModelObject = (DTInfo)trait.getValue();
					if (dtInfoModelObject != null) {
						dtInfo = new DefaultDTInfo(dtInfoModelObject, trait);
					}
				}
			}
		}
		return dtInfo;
	}

	/**
	 * Gets the IProject instance that contains the model of the specified
	 * Element.
	 * 
	 * @param element Element instance.
	 * @return IProject instance that contains the model of the specified
	 * Element.
	 */
	protected IProject getProject(Element element) {
		IProject project = null;
		if (element instanceof IDOMNode) {
			IDOMModel model = ((IDOMNode)element).getModel();
			if (model != null) {
				project = StructuredModelUtil.getProjectFor(model);
			}
		}
		return project;
	}

}
