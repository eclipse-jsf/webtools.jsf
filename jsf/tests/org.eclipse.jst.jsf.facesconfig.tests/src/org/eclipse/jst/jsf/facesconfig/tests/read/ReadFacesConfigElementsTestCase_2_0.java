/*******************************************************************************
 * Copyright (c) 2001, 2010 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.tests.read;

import java.util.List;

import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.facesconfig.emf.AbsoluteOrderingType;
import org.eclipse.jst.jsf.facesconfig.emf.AttributeClassType;
import org.eclipse.jst.jsf.facesconfig.emf.AttributeExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.AttributeNameType;
import org.eclipse.jst.jsf.facesconfig.emf.AttributeType;
import org.eclipse.jst.jsf.facesconfig.emf.BehaviorExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.BehaviorType;
import org.eclipse.jst.jsf.facesconfig.emf.DefaultValueType;
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.DynamicElement;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.NameType;
import org.eclipse.jst.jsf.facesconfig.emf.OrderingOrderingType;
import org.eclipse.jst.jsf.facesconfig.emf.OrderingType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyType;
import org.eclipse.jst.jsf.facesconfig.emf.SuggestedValueType;
import org.eclipse.jst.jsf.facesconfig.tests.util.FacesConfigModelUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

public class ReadFacesConfigElementsTestCase_2_0 extends
        ReadFacesConfigElementsTestCase 
{
    public ReadFacesConfigElementsTestCase_2_0(String name) {
        super(name);
    }

    protected void initialize(TestConfiguration testConfiguration) {
        // override base when not in a configurable test suite
        if(_testConfiguration == null)
        {
            _facesConfigFile = "WEB-INF/faces-config_2_0.xml";
            _facesVersion = IJSFCoreConstants.JSF_VERSION_2_0;
        }
        else
        {
            super.initialize(testConfiguration);
        }
    }

	public final void testOrdering() {

		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
			assertNotNull(edit.getFacesConfig());
			assertEquals(1, edit.getFacesConfig().getOrdering().size());
			
			OrderingType ordering = (OrderingType)edit.getFacesConfig().getOrdering().get(0);
//                (OrderingType) 
//                    FacesConfigModelUtil.findEObjectElementById
//                        (edit.getFacesConfig().getOrdering(), "ordering-id");
            assertNotNull(ordering);
            
            OrderingOrderingType after = ordering.getAfter();
            assertNotNull(after);
            assertNotNull(after.getName());            
            assertEquals("afterName", ((NameType)after.getName().get(0)).getTextContent().trim());     
            assertNotNull(after.getOthers());
            assertEquals("after-others-id",  after.getOthers().getId().trim());     
            
            OrderingOrderingType before = ordering.getBefore();
            assertNotNull(before);
            assertNotNull(before.getName());            
            assertEquals("beforeName", ((NameType)before.getName().get(0)).getTextContent().trim());     
            assertNotNull(before.getOthers());
            assertEquals("before-others-id",  before.getOthers().getId().trim());     
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	public final void testAbsoluteOrdering() {

		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
			assertNotNull(edit.getFacesConfig());

			AbsoluteOrderingType absoluteOrdering = edit.getFacesConfig().getAbsoluteOrdering();
            assertNotNull(absoluteOrdering);

            assertNotNull(absoluteOrdering.getName());            
            assertEquals("absoluteOrderingName", ((NameType)absoluteOrdering.getName().get(0)).getTextContent().trim());     
            assertNotNull(absoluteOrdering.getOthers());
            assertEquals("absolute-ordering-others-id",  absoluteOrdering.getOthers().getId().trim());     
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	public final void testName() {

		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
			assertNotNull(edit.getFacesConfig());
			assertNotNull(edit.getFacesConfig().getName());
			assertEquals("facesconfigName", edit.getFacesConfig().getName().getTextContent().trim());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	public final void testBehavior() {

		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
			assertNotNull(edit.getFacesConfig());
            assertEquals(1, edit.getFacesConfig().getBehavior().size());

            BehaviorType behavior = (BehaviorType)edit.getFacesConfig().getBehavior().get(0);
//                (BehaviorType) 
//                    FacesConfigModelUtil.findEObjectElementById
//                        (edit.getFacesConfig().getBehavior(), "behavior-id");
            assertNotNull(behavior);

            DescriptionType descriptionType = (DescriptionType) FacesConfigModelUtil.
                findEObjectElementById(behavior.getDescription(), "behaviorDescription1");
            assertNotNull(descriptionType);
            assertEquals("my behavior", descriptionType.getTextContent().trim());

            final DisplayNameType displayNameType =
                (DisplayNameType) FacesConfigModelUtil.
                findEObjectElementById(behavior.getDisplayName(), "behaviorDisplayName1");
            assertNotNull(displayNameType);
            assertEquals("My behavior", displayNameType.getTextContent().trim());

            final IconType iconType =
                (IconType) FacesConfigModelUtil.
                    findEObjectElementById(behavior.getIcon(), "behaviorIcon1");
            assertNotNull(iconType);
            
            assertEquals("small-icon", iconType.getSmallIcon().getTextContent().trim());
            assertEquals("large-icon", iconType.getLargeIcon().getTextContent().trim());

            assertEquals("behavior-id-id", behavior.getBehaviorId().getTextContent().trim());
            assertEquals("behavior-class", behavior.getBehaviorClass().getTextContent().trim());
            assertEquals(1, behavior.getAttribute().size());
            assertEquals(1, behavior.getProperty().size());
		
            assertEquals(1, behavior.getBehaviorExtension().size());
            BehaviorExtensionType behaviorExtensionType = 
                (BehaviorExtensionType) behavior.getBehaviorExtension().get(0);
            assertEquals(2, behaviorExtensionType.getChildNodes().size());
            DynamicElement element = (DynamicElement) behaviorExtensionType.getChildNodes().get(0);
            assertEquals("a", element.getName());
            element = (DynamicElement) behaviorExtensionType.getChildNodes().get(1);
            assertEquals("behavior-extension-tag", element.getName());
            
            //behavior attrs
            assertEquals(1,behavior.getAttribute().size());
            AttributeType attr = (AttributeType)behavior.getAttribute().get(0);
            assertNotNull(attr);
            AttributeNameType attrName = attr.getAttributeName();
            assertEquals("behavior1AttributeName", attrName.getId());
            assertEquals("behavior1AttributeName", attrName.getTextContent());
            AttributeClassType attrClass = attr.getAttributeClass();
            assertEquals("behavior1AttributeClass", attrClass.getId());
            assertEquals("behavior1AttributeClass", attrClass.getTextContent());
            List<DescriptionType> descs = attr.getDescription();
            assertEquals(2, descs.size());
            DescriptionType desc = (DescriptionType)descs.get(0);
            assertEquals("behavior1AttributeDescription1", desc.getId());
            assertEquals("behavior1AttributeDescription1", desc.getTextContent());
            List<DisplayNameType> disps= attr.getDisplayName();
            assertEquals(2, disps.size());
            DisplayNameType disp = (DisplayNameType)disps.get(0);
            assertEquals("behavior1AttributeDisplayName1", disp.getId());
            assertEquals("fr", disp.getLang());
            assertEquals("behavior1AttributeDisplayName1", disp.getTextContent());
            List<IconType> icons = attr.getIcon();
            assertEquals(1, icons.size());
            IconType icon = (IconType)icons.get(0);
            assertEquals("behavior1AttributeSmallIcon", icon.getSmallIcon().getTextContent());
            assertEquals("behavior1AttributeLargeIcon", icon.getLargeIcon().getTextContent());
            DefaultValueType defVal = attr.getDefaultValue();
            assertNotNull(defVal);
            assertEquals("behavior1AttributeDefaultValue", defVal.getId());
            assertEquals("behavior1AttributeDefaultValue", defVal.getTextContent());
            SuggestedValueType sugVal = attr.getSuggestedValue();
            assertNotNull(sugVal);
            assertEquals("behavior1AttributeSuggestedValue", sugVal.getId());
            assertEquals("behavior1AttributeSuggestedValue", sugVal.getTextContent());
            List<AttributeExtensionType> attrExts = attr.getAttributeExtension();
            assertEquals(2, attrExts.size());
            AttributeExtensionType attrExt = (AttributeExtensionType)attrExts.get(0);
            assertEquals("behavior1AttributeExtension1", attrExt.getId());
//            assertEquals("<a>B</a>", attrExt.getTextContent()); //NOT WORKING
            assertEquals(1, attrExt.getChildNodes().size());
            
            //behavior props
            List<PropertyType> props = behavior.getProperty();
            assertEquals(1, props.size());
            PropertyType prop = (PropertyType)props.get(0);
            assertEquals("behavior1Property", prop.getId());
            assertNull(prop.getDefaultValue());
            assertEquals(1, prop.getDescription().size());
            desc = (DescriptionType)prop.getDescription().get(0);
            assertNull(desc.getId());
            assertEquals("behavior1PropertyDescription", desc.getTextContent());
            disp = (DisplayNameType)prop.getDisplayName().get(0);
            assertNull(disp.getId());
            assertEquals("behavior1PropertyDisplayName", disp.getTextContent());
            assertEquals(1, prop.getIcon().size());
            icon = (IconType)prop.getIcon().get(0);
            assertEquals("behavior1PropertySmallIcon", icon.getSmallIcon().getId());
            assertEquals("behavior1PropertySmallIcon", icon.getSmallIcon().getTextContent());
            assertEquals("behavior1PropertyName", prop.getPropertyName().getTextContent());
            assertEquals("behavior1PropertyClass", prop.getPropertyClass().getTextContent());
            
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	public final void testMetadataComplete() {

		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
			assertNotNull(edit.getFacesConfig());
			assertEquals(true, edit.getFacesConfig().isMetadataComplete());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

}
