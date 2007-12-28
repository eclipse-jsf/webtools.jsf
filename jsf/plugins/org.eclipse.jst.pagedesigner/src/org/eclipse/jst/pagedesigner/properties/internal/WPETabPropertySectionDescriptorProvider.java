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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.pagedesigner.editors.HTMLEditor;
import org.eclipse.ui.views.properties.tabbed.ISectionDescriptor;
import org.eclipse.ui.views.properties.tabbed.ISectionDescriptorProvider;

/**
 * Provides sections registered for the Web Page Editor whose contributor ID is,
 * "org.eclipse.jst.pagedesigner.tabPropertyContributor" using the
 * org.eclipse.ui.views.properties.tabbed.propertyTabs extension-points.  
 * 
 * The sections declared using the org.eclipse.ui.views.properties.tabbed.propertySections extension-point 
 * for the WPE will only be displayed by the Quick Edit tab is the selected tag has metadata to choose 
 * the section.  TypeMappers/filters, etc. are ignored by this sectionDescriptorProvider.
 * 
 * See jsf_html.xml in the org.eclipse.jst.jsf.standard.tagsupport plugin for examples on how to use.  
 */
public class WPETabPropertySectionDescriptorProvider implements
		ISectionDescriptorProvider {
	
	private static final String EXTPT_SECTIONS = "propertySections"; //$NON-NLS-1$
	private static final String ELEMENT_SECTION = "propertySection"; //$NON-NLS-1$
	private ISectionDescriptor[] _descriptors = null;

	/**
	 * Constructor
	 */
	public WPETabPropertySectionDescriptorProvider() {
		super();
	}

	public ISectionDescriptor[] getSectionDescriptors() {
		if (_descriptors == null) {
			List result = new ArrayList();
			List contributedSections = readSectionDescriptors();
			result.addAll(contributedSections);

//			if (1 == 0){ //disabled for now... do we want to allow other mechanisms to add sections?
//				List providers = readAdditionalSectionDescriptorProviders();
//				for (int i = 0, size = providers.size(); i < size; i++) {
//					try {
//						ISectionDescriptorProvider provider = (ISectionDescriptorProvider) providers
//								.get(i);
//						ISectionDescriptor[] sections = provider
//								.getSectionDescriptors();
//						if (sections != null) {
//							result.addAll(Arrays.asList(sections));
//						}
//					} catch (Exception ex) {
//						// ignore
//						ex.printStackTrace();
//					}
//				}
//			}
			_descriptors = new ISectionDescriptor[result.size()];
			result.toArray(_descriptors);
		}
		return _descriptors;
	}

	/**
	 * @return registered section descriptors for the WPE QuickEdit tab
	 */
	protected List<QuickEditTabSectionDescriptor> readSectionDescriptors() {
		List result = new ArrayList();
			
		IConfigurationElement[] extensions = getConfigurationElements(EXTPT_SECTIONS);
		for (int i = 0; i < extensions.length; i++) {
			IConfigurationElement extension = extensions[i];
			if (extension.getAttribute("contributorId").equals(HTMLEditor.TABBED_PROPERTIES_CONTRIBUTOR_ID)){ //$NON-NLS-1$
				IConfigurationElement[] sections = extension
						.getChildren(ELEMENT_SECTION);
				for (int j = 0; j < sections.length; j++) {
					IConfigurationElement section = sections[j];
					ISectionDescriptor descriptor = new QuickEditTabSectionDescriptor(
							section, null);//ITypeMapper set to null
					result.add(descriptor);
				}
			}
		}
		return result;
	}

	/**
	 * @param extensionPointId
	 * @return IConfigurationElement[] 
	 */
	private static IConfigurationElement[] getConfigurationElements(
			String extensionPointId) {
		IExtensionPoint extensionPoint = Platform.getExtensionRegistry()
				.getExtensionPoint("org.eclipse.ui.views.properties.tabbed", extensionPointId); //$NON-NLS-1$
		if (extensionPoint == null) {
			return null;
		}
		return extensionPoint.getConfigurationElements();
	}
	
	/**
	 * @param name of section
	 * @return {@link QuickEditTabSectionDescriptor} or null if not located
	 */
	public QuickEditTabSectionDescriptor getNamedSectionDescriptor(String name) {
		for (int i=0;i<getSectionDescriptors().length;i++){
			QuickEditTabSectionDescriptor sd = (QuickEditTabSectionDescriptor)getSectionDescriptors()[i];
			if (name.equals(sd.getId()))
				return sd;
		}
		return null;
	}
}
