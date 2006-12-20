package org.eclipse.jst.jsf.facesconfig.tests.read;

import java.util.List;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentType;
import org.eclipse.jst.jsf.facesconfig.emf.DynamicAttribute;
import org.eclipse.jst.jsf.facesconfig.emf.DynamicElement;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.tests.util.FacesConfigModelUtil;
import org.eclipse.jst.jsf.facesconfig.tests.util.WizardUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

public class ReadExtensionDataTestCase extends TestCase 
{
    IProject project = null;

    public ReadExtensionDataTestCase(String name) {
        super(name);
    }

    
    
    protected void setUp() throws Exception {
        super.setUp();
        WizardUtil.createProject(getName());
        project = WizardUtil.getTestProject(getName());
    }
    
    /*
     * Check for hte Component-Extension inside Component
     */

    public void testSingleElementRoot() {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = FacesConfigArtifactEdit
                    .getFacesConfigArtifactEditForRead(project, 
                            "WEB-INF/faces-config-ext-data1.xml");
            assertNotNull(edit.getFacesConfig());
            ComponentType component1 = getComponent1(edit.getFacesConfig());
            assertNotNull(component1);
            
            ComponentExtensionType extType = 
                (ComponentExtensionType) FacesConfigModelUtil
                .findEObjectElementById(component1.getComponentExtension()
                                        , "singleRootComponentExt");
            assertNotNull(extType);
            List elements = extType.getChildNodes();
                assertEquals(1, elements.size());
            DynamicElement singleRoot = (DynamicElement) elements.get(0);
            assertEquals("any-data", singleRoot.getName());

            assertEquals(1, singleRoot.getAttributes().size());
            DynamicAttribute attribute = 
                (DynamicAttribute) singleRoot.getAttributes().get(0);
            assertEquals("attribute", attribute.getName());
            assertEquals("any-data-attribute", attribute.getValue());
            
            assertEquals(2, singleRoot.getChildNodes().size());
            
            DynamicElement firstChild = 
                (DynamicElement) singleRoot.getChildNodes().get(0);
            assertEquals("first-child", firstChild.getName());
//            assertEquals("someData", firstChild.getTextContent());
            
            DynamicElement secondChild =
                (DynamicElement) singleRoot.getChildNodes().get(1);
            assertEquals("second-child", secondChild.getName());
//            assertEquals("second Child's data", secondChild.getTextContent());            
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }

    private ComponentType getComponent1(FacesConfigType facesConfig)
    {
        return (ComponentType) FacesConfigModelUtil
            .findEObjectElementById(facesConfig.getComponent(), "component1");
    }
}
