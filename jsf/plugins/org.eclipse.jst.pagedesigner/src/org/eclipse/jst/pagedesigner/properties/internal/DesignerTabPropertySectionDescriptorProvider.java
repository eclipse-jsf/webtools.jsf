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
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.wst.common.ui.properties.internal.provisional.ISectionDescriptor;
import org.eclipse.wst.common.ui.properties.internal.provisional.ISectionDescriptorProvider;

/**
 * The DesignerTagPropertySectionDescriptorProvider will provide the section
 * descriptiors. Now section descriptor come from two sources.
 * 
 * One is reading from the "org.eclipse.jst.pagedesigner.propertyContributor"
 * extension point. This extension point will contribute additional section
 * providers, which provides a set of sections.
 * 
 * One is reading from the "org.eclipse.jst.pagedesigner.propertySections"
 * extension point. This extension provide a single section.
 * 
 * @author mengbo
 * @version 1.5
 */
public class DesignerTabPropertySectionDescriptorProvider implements
		ISectionDescriptorProvider {
	private static final String EXTPT_SECTIONS = "propertySections"; //$NON-NLS-1$

	private static final String ELEMENT_SECTION = "propertySection"; //$NON-NLS-1$

	private static final String EXTPT_SECTIONDESCRIPTORPROVIDER = "propertyContributor";

	private static final String ELEMENT_PROPERTYCONTRIBUTOR = "propertyContributor";

	private static final String ATTR_SECTIONDESCRIPTORPROVIDER = "sectionDescriptorProvider";

	ISectionDescriptor[] _descriptors = null;

	/**
	 * 
	 */
	public DesignerTabPropertySectionDescriptorProvider() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.ui.properties.internal.provisional.ISectionDescriptorProvider#getSectionDescriptors()
	 */
	public ISectionDescriptor[] getSectionDescriptors() {
		if (_descriptors == null) {
			List result = new ArrayList();
			List contributedSections = readSectionDescriptors();
			result.addAll(contributedSections);

			List providers = readAdditionalSectionDescriptorProviders();
			for (int i = 0, size = providers.size(); i < size; i++) {
				try {
					ISectionDescriptorProvider provider = (ISectionDescriptorProvider) providers
							.get(i);
					ISectionDescriptor[] sections = provider
							.getSectionDescriptors();
					if (sections != null) {
						result.addAll(Arrays.asList(sections));
					}
				} catch (Exception ex) {
					// ignore
					ex.printStackTrace();
				}
			}
			_descriptors = new ISectionDescriptor[result.size()];
			result.toArray(_descriptors);
		}
		return _descriptors;
	}

	protected List readAdditionalSectionDescriptorProviders() {
		List result = new ArrayList();
		IConfigurationElement[] extensions = getConfigurationElements(EXTPT_SECTIONDESCRIPTORPROVIDER);
		for (int i = 0; i < extensions.length; i++) {
			IConfigurationElement extension = extensions[i];
			if (ELEMENT_PROPERTYCONTRIBUTOR.equals(extension.getName())) {
				IConfigurationElement contributor = extension;
				try {
					Object obj = contributor
							.createExecutableExtension(ATTR_SECTIONDESCRIPTORPROVIDER);
					if (obj instanceof ISectionDescriptorProvider) {
						result.add(obj);
					}
				} catch (CoreException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	protected List readSectionDescriptors() {
		List result = new ArrayList();
		IConfigurationElement[] extensions = getConfigurationElements(EXTPT_SECTIONS);
		for (int i = 0; i < extensions.length; i++) {
			IConfigurationElement extension = extensions[i];
			IConfigurationElement[] sections = extension
					.getChildren(ELEMENT_SECTION);
			for (int j = 0; j < sections.length; j++) {
				IConfigurationElement section = sections[j];
				ISectionDescriptor descriptor = new DesignerSectionDescriptor(
						section);
				result.add(descriptor);
			}
		}
		return result;
	}

	public static IConfigurationElement[] getConfigurationElements(
			String extensionPointId) {
		IExtensionPoint extensionPoint = Platform.getExtensionRegistry()
				.getExtensionPoint(PDPlugin.getPluginId(), extensionPointId);

		if (extensionPoint == null) {
			return null;
		}
		return extensionPoint.getConfigurationElements();
	}
}
