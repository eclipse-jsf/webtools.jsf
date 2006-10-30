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

import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.common.utils.StringUtil;
import org.eclipse.jst.pagedesigner.properties.DesignerPropertyTool;
import org.eclipse.jst.pagedesigner.properties.ISectionFilter;
import org.eclipse.jst.pagedesigner.properties.attrgroup.AttributeGroup;
import org.eclipse.jst.pagedesigner.properties.attrgroup.AttributeGroupSection;
import org.eclipse.jst.pagedesigner.utils.CMUtil;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.wst.common.ui.properties.internal.provisional.ISection;
import org.eclipse.wst.common.ui.properties.internal.provisional.ISectionDescriptor;
import org.eclipse.wst.common.ui.properties.internal.provisional.ITypeMapper;
import org.w3c.dom.Element;

/**
 * @author mengbo
 * @version 1.5
 */
public class DesignerSectionDescriptor implements ISectionDescriptor {
	private static final String ATT_ID = "id"; //$NON-NLS-1$

	private static final String ATT_TARGET_TAB = "tab"; //$NON-NLS-1$

	private static final String ATT_AFTER_SECTION = "afterSection"; //$NON-NLS-1$  

	private static final String ATT_CLASS = "class"; //$NON-NLS-1$ 

	private static final String ATT_FILTER = "filter"; //$NON-NLS-1$

	private static final String TOP = "top"; //$NON-NLS-1$

	private static final String TAGFILTER = "tagFilter";

	private static final String CASESENSITIVE = "caseSensitive";

	private static final String TAGNAME = "tagName";

	private static final String URI = "uri";

	private String _id;

	private String _targetTab;

	private String _afterSection;

	private ISectionFilter _sectionFilter;

	private IConfigurationElement _configurationElement;

	private TagFilter[] _tagFilters;

	private static class TagFilter {
		public String uri;

		public String tag;

		public boolean caseSensitive = false;
	}

	/**
	 * Constructor for the section descriptor.
	 * 
	 * @param configurationElement
	 *            the configuration element for the section descriptor.
	 */
	public DesignerSectionDescriptor(IConfigurationElement configurationElement) {
		_configurationElement = configurationElement;

		_id = getConfigurationElement().getAttribute(ATT_ID);
		_targetTab = getConfigurationElement().getAttribute(ATT_TARGET_TAB);
		_afterSection = getConfigurationElement().getAttribute(
				ATT_AFTER_SECTION);

		if (_id == null || _targetTab == null) {
			// the section id and tab are mandatory - log error
			handleSectionError(null);
		}
		if (getAfterSection() == null) {
			_afterSection = TOP;
		}

		String filterClass = getConfigurationElement().getAttribute(ATT_FILTER);
		if (filterClass != null) {
			try {
				Object obj = getConfigurationElement()
						.createExecutableExtension(ATT_FILTER);
				if (obj instanceof ISectionFilter) {
					_sectionFilter = (ISectionFilter) obj;
				}
			} catch (CoreException ex) {
				handleSectionError(ex);
			}
		}

		IConfigurationElement[] elements = getConfigurationElement()
				.getChildren(TAGFILTER);
		if (elements != null && elements.length > 0) {
			_tagFilters = new TagFilter[elements.length];
			for (int i = 0; i < _tagFilters.length; i++) {
				_tagFilters[i] = new TagFilter();
				_tagFilters[i].uri = elements[i].getAttribute(URI);
				_tagFilters[i].tag = elements[i].getAttribute(TAGNAME);
				_tagFilters[i].caseSensitive = Boolean.TRUE.toString()
						.equalsIgnoreCase(
								elements[i].getAttribute(CASESENSITIVE));
			}
		}
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
		PDPlugin.getLogger(DesignerSectionDescriptor.class).error("error",
				exception);
		exception.printStackTrace();
		// String pluginId = PDPlugin.getPluginId();
		// String message = MessageFormat.format(SECTION_ERROR, new Object[] {
		// pluginId});
		// IStatus status = new Status(IStatus.ERROR, pluginId,
		// CommonUIPropertiesStatusCodes.GENERAL_UI_FAILURE, message,
		// exception);
		// CommonUIPropertiesPlugin.getPlugin().getLog().log(status);
	}

	/**
	 * @see org.eclipse.wst.common.ui.properties.ITabbedPropertySectionDescriptor#getId()
	 */
	public String getId() {
		return _id;
	}

	/**
	 * @see org.eclipse.wst.common.ui.properties.ITabbedPropertySectionDescriptor#getTargetTab()
	 */
	public String getTargetTab() {
		return _targetTab;
	}

	/**
	 * @see org.eclipse.wst.common.ui.properties.internal.provisional.ISectionDescriptor#appliesTo(org.eclipse.ui.IWorkbenchPart,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	public boolean appliesTo(IWorkbenchPart part, ISelection selection) {
		Element node = DesignerPropertyTool.getElement(part, selection);
		if (node == null) {
			return false;
		}
		if (_tagFilters != null) {
			String uri = CMUtil.getElementNamespaceURI(node);
			String tag = node.getLocalName();
			for (int i = 0; i < _tagFilters.length; i++) {
				if (!match(uri, tag, _tagFilters[i])) {
					return false;
				}
			}
		}
		if (_sectionFilter != null) {
			if (!_sectionFilter.appliesTo(node)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param uri2
	 * @param tag
	 * @param filter
	 * @return
	 */
	private boolean match(String uri, String tag, TagFilter filter) {
		if (!StringUtil.isSameString(uri, filter.uri)) {
			return false;
		}
		if (uri != null && filter.uri != null && !uri.equals(filter.uri)) {
			return false;
		}
		if (filter.caseSensitive) {
			return tag.equals(filter.tag);
		} else {
			return tag.equalsIgnoreCase(filter.tag);
		}
	}

	/**
	 * @see org.eclipse.wst.common.ui.properties.ITabbedPropertySectionDescriptor#getAfterSection()
	 */
	public String getAfterSection() {
		return _afterSection;
	}

	/**
	 * Creates an instance of a section described by this descriptor
	 * 
	 * @see org.eclipse.wst.common.ui.properties.ITabbedPropertySectionDescriptor#getSectionClass()
	 */
	public ISection getSectionClass() {
		ISection section = null;
		try {
			Object secOrGroup = getConfigurationElement()
					.createExecutableExtension(ATT_CLASS);
			if (secOrGroup instanceof ISection) {
				section = (ISection) secOrGroup;
			} else if (secOrGroup instanceof AttributeGroup) {
				section = new AttributeGroupSection((AttributeGroup) secOrGroup);
			}
		} catch (CoreException exception) {
			handleSectionError(exception);
		}

		return section;
	}

	/**
	 * Gets the input types that are valid for this section.
	 * 
	 * @see org.eclipse.wst.common.ui.properties.ITabbedPropertySectionDescriptor#getInputTypes()
	 */
	public List getInputTypes() {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return getId();
	}

	/**
	 * @return Returns the configurationElement.
	 */
	private IConfigurationElement getConfigurationElement() {
		return _configurationElement;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.ui.properties.internal.provisional.ISectionDescriptor#getFilter()
	 */
	public ITypeMapper getFilter() {
		return null;
	}

	public int getEnablesFor() {
		return 1;
	}
}
