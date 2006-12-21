package org.eclipse.jst.jsf.facesconfig.tests.read;

import org.eclipse.jst.jsf.facesconfig.emf.ComponentType;
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyType;
import org.eclipse.jst.jsf.facesconfig.tests.util.FacesConfigModelUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

/**
 * Test component property
 * 
 * @author cbateman
 *
 */
public class ReadPropertyComponentTestCase extends BaseReadTestCase {

    public ReadPropertyComponentTestCase(String name) {
        super(name);
    }
    
    public void testProperty()
    {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());
            
            PropertyType attribute1 = getProperty1(edit.getFacesConfig());
            assertNotNull(attribute1);
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }

    }
    
    private ComponentType getComponent1(FacesConfigType facesConfigType)
    {
        return (ComponentType) FacesConfigModelUtil
            .findEObjectElementById
               (facesConfigType.getComponent(), "component1");
    }
    
    private PropertyType getProperty1(FacesConfigType facesConfigType)
    {
        ComponentType component1 = getComponent1(facesConfigType);
        assertNotNull(component1);
        return (PropertyType) FacesConfigModelUtil
           .findEObjectElementById
               (component1.getProperty(), "componentProperty1");
    }
    /*
     * This is to test the description child inside of Attribute
     * 
     */

    public void testDescription() {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());
            
            PropertyType propertyType = 
                 getProperty1(edit.getFacesConfig());
            assertNotNull(propertyType);
            
            DescriptionType descType =
                (DescriptionType)FacesConfigModelUtil.findEObjectElementById
                    (propertyType.getDescription()
                     ,"componentProperty1_descripton1");
            assertEquals("myDescript blah blah"
                         , descType.getTextContent().trim());
        } 
        finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }
    
    /*
     * A simple test to check if the Display Name is present 
     * within the faces-config.xml file
     */
    
    public void testDisplayName() {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());
    
            PropertyType propertyType = 
                getProperty1(edit.getFacesConfig());
            assertNotNull(propertyType);
    
            final DisplayNameType displayNameType =
                (DisplayNameType)FacesConfigModelUtil.findEObjectElementById
                    (propertyType.getDisplayName()
                            ,"componentProperty1_displayName1");
            assertEquals("Component Property 1"
                 , displayNameType.getTextContent().trim());        
        } 
        finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }
    
    /*
     * Checks  to see if there is an icon defined 
     * 
     */
    public void testIcon() {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());
            
            PropertyType propertyType = getProperty1(edit.getFacesConfig());
            assertNotNull(propertyType);
            
            IconType iconType =
                (IconType) FacesConfigModelUtil
                    .findEObjectElementById
                        (propertyType.getIcon(), "componentProperty1_icon1");
            assertNotNull(iconType);
            
            assertEquals("property-small-icon",
                         iconType.getSmallIcon().getTextContent());
            assertEquals("property-large-icon",
                         iconType.getLargeIcon().getTextContent());
        }  finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }
    
    /*
     * This one tests for the existence of two items.
     * They are the required items by all renderers
     * They are : attribute-name and attribute-class.
     * It thought it was better to put them together instead of
     * writing single -separate methods for each of them.
     *Simply, extract the names and check if same the one
     *in faces-config.xml 
     */
    public void testPropertyNameAndClass() {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());
            
            PropertyType property1 = getProperty1(edit.getFacesConfig());
            assertNotNull(property1);
            
            assertEquals("property1"
                    ,property1.getPropertyName().getTextContent());
            assertEquals("ComponentProperty"
                    ,property1.getPropertyClass().getTextContent());
        }  finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }
    
    /*
     * Checks for the item suggested-value within attribute
     * 
     */
    public void testSuggestedValue() {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());
            
            PropertyType propertyType = 
                getProperty1(edit.getFacesConfig());
            assertNotNull(propertyType);
            
            assertEquals("suggestedValue"
                    ,propertyType.getSuggestedValue().getTextContent());
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }
    
    public void testDefaultValue()
    {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());
            
            PropertyType propertyType = 
                getProperty1(edit.getFacesConfig());
            assertNotNull(propertyType);
            
            assertEquals("defaultValue6"
                    , propertyType.getDefaultValue().getTextContent());
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }
    
    /**
     * Checks for the item attribute-extension within attribute
     * TODO: not currently supported
     */ 
//    public void testPropertyExtension() {
//        FacesConfigArtifactEdit edit = null;
//        try {
//            edit = FacesConfigArtifactEdit
//                    .getFacesConfigArtifactEditForRead(project);
//            if (edit.getFacesConfig() != null) {
//                EList comp = edit.getFacesConfig().getComponent();
//                assertTrue(!comp.isEmpty());
//                for (int i = 0; i < comp.size(); i++) {
//                    ComponentType compType = (ComponentType) comp
//                            .get(i);
//                    assertTrue(!compType.getAttribute().isEmpty());
//                    
//                    EList attr = compType.getAttribute();
//                    for (int k = 0; k < attr.size(); k++) {
//                        AttributeType attrType = (AttributeType) attr.get(k);
//                        EList ext= attrType.getAttributeExtension();
//                        assertTrue(ext.size()!=0);
//                        System.out.println("The size of attribute-extension is >>?? " + ext.size() );
//                    }
//                }
//            }
//        } finally {
//            if (edit != null) {
//                edit.dispose();
//            }
//        }
//    }

}
