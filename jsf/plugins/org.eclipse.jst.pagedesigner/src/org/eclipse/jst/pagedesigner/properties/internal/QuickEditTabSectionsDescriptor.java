/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.properties.internal;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.QuickEditTabSections;
import org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.SECTION_TYPE;
import org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.SectionInfo;
import org.eclipse.ui.views.properties.tabbed.ISection;

/**
 * Represents all of the section classes that make up the Quick Edit tab for a given tag input.   
 * Uses QuickEditTabSections meta data from tag entity
 */
public class QuickEditTabSectionsDescriptor {

	private QuickEditTabSections _sections;
	private QName _tagId;
	private List<ISection> _sectionClasses;
	private Entity _tagEntity;
	private WPETabPropertySectionDescriptorProvider sectionProvider;

	/**
	 * Constructor
	 * @param tagEntity
	 * @param tagId
	 * @param sections
	 */
	public QuickEditTabSectionsDescriptor (Entity tagEntity, QName tagId, QuickEditTabSections sections) {
		_tagEntity = tagEntity;
		_tagId = tagId;
		_sections = sections;
	}	

	/*package*/ QuickEditTabSectionsDescriptor () {
		_tagEntity = null;
		_tagId = null;
		_sections = null;
	}
	
	/**
	 * Determines section classes for the group from the available meta data
	 */
	public void calculateSections() {
		if (_sectionClasses == null || _sectionClasses.isEmpty()) {
			_sectionClasses = new ArrayList();
			List<String> attrIds = new ArrayList();
			for (SectionInfo secInfo : _sections.getSections()) {
				if (secInfo.getType() == SECTION_TYPE.ATTRIBUTE){
					//collect attrSecs and process as a group so that layout will be best
					if (secInfo.getId() != null) 
						attrIds.add(secInfo.getId());
				}
				else if (secInfo.getType() == SECTION_TYPE.SECTION) {
					//if there are any attribute sections not processed, do that now
					if (attrIds.size() > 0){
						createAttributeGroup(attrIds);
						attrIds = new ArrayList();
					}
					createSectionGroup(secInfo);
				}
			}
			if (attrIds.size() > 0){
				createAttributeGroup(attrIds);
			}
		}		
	}

	private void createSectionGroup(SectionInfo secInfo) {
		QuickEditTabSectionDescriptor sd = getSectionProvider().getNamedSectionDescriptor(secInfo.getId());
		if (sd != null) {
			ISection section = sd.getSectionClass(_tagEntity);
			if (section != null){
				_sectionClasses.add(section);
			}
		}
		
	}

	private void createAttributeGroup(List<String> attrNames) {
		ISection section = new AttributeGroupSection(_tagEntity, attrNames);
		if (section != null){		
			_sectionClasses.add(section);
		}
	}

	/**
	 * @return QName of tag that this tab descriptor is for
	 */
	public QName getTagId() {
		return _tagId;
	}

	/**
	 * @return List of current ISection classes set after calculateSections() has been called
	 */
	public List<ISection> getSections() {
		return _sectionClasses;
	}

	private WPETabPropertySectionDescriptorProvider getSectionProvider() {
		if (sectionProvider == null){
			sectionProvider = new WPETabPropertySectionDescriptorProvider();
		}
		return sectionProvider;
	}
	

}
