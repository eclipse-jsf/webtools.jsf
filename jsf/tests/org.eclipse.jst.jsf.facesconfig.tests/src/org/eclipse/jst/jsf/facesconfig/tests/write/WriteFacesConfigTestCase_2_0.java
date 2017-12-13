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
package org.eclipse.jst.jsf.facesconfig.tests.write;

import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.facesconfig.emf.AbsoluteOrderingType;
import org.eclipse.jst.jsf.facesconfig.emf.AttributeType;
import org.eclipse.jst.jsf.facesconfig.emf.BehaviorClassType;
import org.eclipse.jst.jsf.facesconfig.emf.BehaviorExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.BehaviorIdType;
import org.eclipse.jst.jsf.facesconfig.emf.BehaviorType;
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.DynamicElement;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.NameType;
import org.eclipse.jst.jsf.facesconfig.emf.OrderingOrderingType;
import org.eclipse.jst.jsf.facesconfig.emf.OrderingOthersType;
import org.eclipse.jst.jsf.facesconfig.emf.OrderingType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyType;
import org.eclipse.jst.jsf.facesconfig.tests.util.CommonStructuresUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

public class WriteFacesConfigTestCase_2_0 extends FacesConfigExtensionTestCase {
    
	private final static String FACESCONFIG_NAME = "facesconfig-name";
//	private final static String ORDERING_ID = "ordering-id";
	private final static String ABSOLUTE_ORDERING_NAME = "ordering-name";
	private final static String ABSOLUTE_ORDERING_OTHERS_ID = "ordering-name-others-id";
	private final static String BEFORE_ORDERING_NAME = "before-ordering-name";
	private final static String BEFORE_ORDERING_OTHERS_ID = "before-ordering-name-others-id";
	private final static String AFTER_ORDERING_NAME = "after-ordering-name";
	private final static String AFTER_ORDERING_OTHERS_ID = "after-ordering-name-others-id";
	private final static String BEHAVIOR = "behavior";
//	private final static String BEHAVIOR_ID = "behavior-id";
	private final static String BEHAVIOR_ID_ID = "behavior-id-id";
	private final static String BEHAVIOR_CLASS = "behavior-class";
	private final static String BEHAVIOR_EXTENSION_ID = "behavior-extension-id";
	private final static String BEHAVIOR_EXTENSION = "behavior-extension";

	public WriteFacesConfigTestCase_2_0(String name) {
        super(name);
    }
    
    protected void setUp() throws Exception 
    {
        super.setUp();
        assertEquals(IJSFCoreConstants.JSF_VERSION_2_0, _facesVersion);
    }

    public void testFacesConfig20() {
        FacesConfigArtifactEdit edit = null;

        try {
            edit = getArtifactEditForWrite();
            assertNotNull(edit.getFacesConfig());
            FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
            FacesConfigFactory facesConfigFactory = facesConfigPackage.getFacesConfigFactory();

            FacesConfigType facesConfigType = edit.getFacesConfig();
            
            {
                //Ordering
                OrderingType ordering = facesConfigFactory.createOrderingType();
//                ordering.setId(ORDERING_ID);
                facesConfigType.getOrdering().add(ordering);
                OrderingOrderingType before = facesConfigFactory.createOrderingOrderingType();
                NameType beforeOrderingName = facesConfigFactory.createNameType();
                beforeOrderingName.setTextContent(BEFORE_ORDERING_NAME);
                before.getName().add(beforeOrderingName);
                OrderingOthersType others = facesConfigFactory.createOrderingOthersType();
                others.setId(BEFORE_ORDERING_OTHERS_ID);
                before.setOthers(others);
                ordering.setBefore(before);
                OrderingOrderingType after = facesConfigFactory.createOrderingOrderingType();
                NameType afterOrderingName = facesConfigFactory.createNameType();
                afterOrderingName.setTextContent(AFTER_ORDERING_NAME);
                after.getName().add(afterOrderingName);
                OrderingOthersType others2 = facesConfigFactory.createOrderingOthersType();
                others2.setId(AFTER_ORDERING_OTHERS_ID);
                after.setOthers(others2);
                ordering.setAfter(after);
            }
            
            {
                //Absolute Ordering
                AbsoluteOrderingType absoluteOrdering = facesConfigFactory.createAbsoluteOrderingType();
                NameType orderingName = facesConfigFactory.createNameType();
                orderingName.setTextContent(ABSOLUTE_ORDERING_NAME);
                absoluteOrdering.getName().add(orderingName);
                OrderingOthersType others = facesConfigFactory.createOrderingOthersType();
                others.setId(ABSOLUTE_ORDERING_OTHERS_ID);
                absoluteOrdering.setOthers(others);
                facesConfigType.setAbsoluteOrdering(absoluteOrdering);
            }
            
            //Name
            NameType name = facesConfigFactory.createNameType();
            name.setTextContent(FACESCONFIG_NAME);
            facesConfigType.setName(name);
            
            {
                //Behavior
                BehaviorType behavior = facesConfigFactory.createBehaviorType();
//                behavior.setId(BEHAVIOR_ID);
                facesConfigType.getBehavior().add(behavior);

                behavior.getDescription().add(CommonStructuresUtil.createDescription(BEHAVIOR));
                behavior.getDisplayName().add(CommonStructuresUtil.createDisplayName(BEHAVIOR));
                behavior.getIcon().add(CommonStructuresUtil.createIcon(BEHAVIOR));
                
                BehaviorIdType behaviorId = facesConfigFactory.createBehaviorIdType();
                behaviorId.setTextContent(BEHAVIOR_ID_ID);
                behavior.setBehaviorId(behaviorId);
                
                BehaviorClassType behaviorClass = facesConfigFactory.createBehaviorClassType();
                behaviorClass.setTextContent(BEHAVIOR_CLASS);
                behavior.setBehaviorClass(behaviorClass);
                
                AttributeType attribute = facesConfigFactory.createAttributeType();
                behavior.getAttribute().add(attribute);
                
                PropertyType property = facesConfigFactory.createPropertyType();
                behavior.getProperty().add(property);
                
                BehaviorExtensionType extensionType = facesConfigFactory.createBehaviorExtensionType();
                extensionType.setId(BEHAVIOR_EXTENSION_ID);
                extensionType.getChildNodes().add(createDynamicElement(BEHAVIOR_EXTENSION));
                behavior.getBehaviorExtension().add(extensionType);
            }
            
            //Metadata Complete
            facesConfigType.setMetadataComplete(true);
            
            edit.save(null);
        } finally {
            if (edit != null) {
                edit.dispose();
                assertTrue(edit.isDisposed());
                edit = null;
            }
        }

        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());

            FacesConfigType facesConfig = edit.getFacesConfig();

            {
                //Ordering
            	assertEquals(1, facesConfig.getOrdering().size());
            	OrderingType ordering = (OrderingType)facesConfig.getOrdering().get(0);
//            	assertEquals(ORDERING_ID, ordering.getId());
            	
            	OrderingOrderingType before = ordering.getBefore();
            	assertEquals(1, before.getName().size());
            	NameType name = (NameType)before.getName().get(0);
            	assertEquals(BEFORE_ORDERING_NAME, name.getTextContent().trim());
            	assertEquals(BEFORE_ORDERING_OTHERS_ID, before.getOthers().getId());
            	
            	OrderingOrderingType after = ordering.getAfter();
            	assertEquals(1, after.getName().size());
            	NameType afterNname = (NameType)after.getName().get(0);
            	assertEquals(AFTER_ORDERING_NAME, afterNname.getTextContent().trim());
            	assertEquals(AFTER_ORDERING_OTHERS_ID, after.getOthers().getId());
            }

            {
                //Absolute Ordering
                AbsoluteOrderingType absoluteOrdering = facesConfig.getAbsoluteOrdering();
                assertNotNull(absoluteOrdering);
                assertEquals(1, absoluteOrdering.getName().size());
                assertEquals(ABSOLUTE_ORDERING_NAME, ((NameType)absoluteOrdering.getName().get(0)).getTextContent().trim());
                assertNotNull(absoluteOrdering.getOthers());
                assertEquals(ABSOLUTE_ORDERING_OTHERS_ID, absoluteOrdering.getOthers().getId().trim());
            }

            {
                //Name
                NameType name = facesConfig.getName();
                assertNotNull(name);
                assertEquals(FACESCONFIG_NAME, name.getTextContent().trim());
            }
            
            {
            	//Behavior
            	assertEquals(1, facesConfig.getBehavior().size());
            	BehaviorType behavior = (BehaviorType)facesConfig.getBehavior().get(0);
                assertEquals(1, behavior.getDescription().size());
                CommonStructuresUtil.assertMatchesDescription
                    (BEHAVIOR, (DescriptionType) behavior.getDescription().get(0));
                
                assertEquals(1, behavior.getDisplayName().size());
                CommonStructuresUtil.assertMatchesDisplayName
                    (BEHAVIOR, (DisplayNameType)behavior.getDisplayName().get(0));
                
                assertEquals(1, behavior.getIcon().size());
                CommonStructuresUtil.assertMatchesIcon
                    (BEHAVIOR, (IconType)behavior.getIcon().get(0));

                BehaviorIdType behaviorId = behavior.getBehaviorId();
                assertEquals(BEHAVIOR_ID_ID, behaviorId.getTextContent().trim());
                
                BehaviorClassType behaviorClass = behavior.getBehaviorClass();
                assertEquals(BEHAVIOR_CLASS, behaviorClass.getTextContent().trim());

                assertEquals(1, behavior.getAttribute().size());
                assertEquals(1, behavior.getProperty().size());
                
                assertEquals(1, behavior.getBehaviorExtension().size());
                BehaviorExtensionType ext =
                    (BehaviorExtensionType) behavior.getBehaviorExtension().get(0);
                assertEquals(BEHAVIOR_EXTENSION_ID, ext.getId());
                assertEquals(1, ext.getChildNodes().size());
                DynamicElement element = (DynamicElement) ext.getChildNodes().get(0);
                assertEquals(BEHAVIOR_EXTENSION, element.getName());
            }
            
            //Metadata Complete
            assertEquals(true, facesConfig.isMetadataComplete());
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }
}
