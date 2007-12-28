/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.properties.internal;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.properties.DesignerPropertyTool;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.internal.views.properties.tabbed.view.SectionDescriptor;
import org.eclipse.ui.views.properties.tabbed.ISection;
import org.eclipse.ui.views.properties.tabbed.ITypeMapper;
import org.w3c.dom.Element;

/**
 * A SectionDescriptor from org.eclipse.ui.views.properties.tabbed.propertySections extension-point 
 * for use by the QuickEdit tab in the Web Page Editor.
 */
public class QuickEditTabSectionDescriptor extends SectionDescriptor {

	private static final String ATT_CLASS = "class"; //$NON-NLS-1$ 
	private IConfigurationElement _configurationElement;
	private Entity _tagEntity;

	/**
	 * Constructor for the section descriptor.
	 * 
	 * @param configurationElement
	 *            the configuration element for the section descriptor.
	 * @param typeMapper 
	 */
	public QuickEditTabSectionDescriptor(IConfigurationElement configurationElement, ITypeMapper typeMapper) {
		super(configurationElement, typeMapper);
		_configurationElement = configurationElement;
	}

	@Override
	public boolean appliesTo(IWorkbenchPart part, ISelection selection) {
		Element node = DesignerPropertyTool.getElement(part, selection);
		if (node == null) {
			return false;
		}
		return true;
	}

	@Override
	public ISection getSectionClass() {
		ISection section = null;
		try {
			Object secOrGroup = _configurationElement
					.createExecutableExtension(ATT_CLASS);
			if (secOrGroup instanceof ISection) {
				section = (ISection) secOrGroup;
			} else if (secOrGroup instanceof AttributeGroup) {
				((AttributeGroup) secOrGroup).setTagEntity(_tagEntity);
				section = new AttributeGroupSection((AttributeGroup) secOrGroup);		
			}
		} catch (CoreException exception) {
			handleSectionError(exception);
		}
		return section;
	}
	
	/**
	 * Handle the section error when an issue is found loading from the
	 * configuration element.
	 * 
	 * @param _configurationElement
	 *            the configuration element
	 * @param exception
	 *            an optional CoreException
	 */
	private void handleSectionError(CoreException exception) {
		PDPlugin.getLogger(QuickEditTabSectionDescriptor.class).error("error", //$NON-NLS-1$
				exception);
		exception.printStackTrace();
	}

	/**
	 * @param tagEntity
	 * @return ISection for the tagEntity
	 */
	/*package*/ ISection getSectionClass(Entity tagEntity) {
		_tagEntity = tagEntity;
		return getSectionClass();
	}
		
}
