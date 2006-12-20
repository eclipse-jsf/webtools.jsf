package org.eclipse.jst.jsf.facesconfig.tests.write;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentTypeType;
import org.eclipse.jst.jsf.facesconfig.emf.DynamicAttribute;
import org.eclipse.jst.jsf.facesconfig.emf.DynamicElement;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.tests.util.CommonStructuresUtil;
import org.eclipse.jst.jsf.facesconfig.tests.util.WizardUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;

public class WriteComponentExtensionDataTestCase extends TestCase {
    private static final String COMPONENT = "component";
    
    private static final String COMPONENT_CLASS = "component-class";

    private static final String COMPONENT_TYPE = "component-type";

    private static final String WEB_INF_FACES_CONFIG1_XML = "WEB-INF/faces-config1.xml";

    IProject project = null;

    public WriteComponentExtensionDataTestCase(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
        JSFTestUtil.setValidationEnabled(false);
        
        JSFTestUtil.setInternetProxyPreferences(true, "www-proxy.uk.oracle.com", "80");

        WizardUtil.createProject(getName());
        project = WizardUtil.getTestProject(getName());
    }

    public void testWriteExtensionData()
    {
        FacesConfigArtifactEdit edit = null;
        try 
        {
            edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
                    project, WEB_INF_FACES_CONFIG1_XML);
            assertNotNull(edit.getFacesConfig());
            FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
            FacesConfigFactory facesConfigFactory = facesConfigPackage
                    .getFacesConfigFactory();

            ComponentType newComponent = facesConfigFactory
                    .createComponentType();

            {
                ComponentTypeType componentTypeType = facesConfigFactory.createComponentTypeType();
                componentTypeType.setTextContent(COMPONENT_TYPE);
                componentTypeType.setId(CommonStructuresUtil.createPreficedString(COMPONENT_TYPE
                        , CommonStructuresUtil.ID));
                newComponent.setComponentType(componentTypeType);
            }
            
            {
                ComponentClassType componentClassType = facesConfigFactory.createComponentClassType();
                componentClassType.setTextContent(COMPONENT_CLASS);
                componentClassType.setId(CommonStructuresUtil.createPreficedString(COMPONENT_CLASS
                        , CommonStructuresUtil.ID));
                newComponent.setComponentClass(componentClassType);
            }
            
            ComponentExtensionType extType =
                facesConfigFactory.createComponentExtensionType();
            extType.setId(CommonStructuresUtil.createPreficedString(COMPONENT, "extension-id"));
            
            DynamicElement singleRoot = facesConfigFactory.createDynamicElement();
            singleRoot.setName("any-data");
            DynamicAttribute attribute = facesConfigFactory.createDynamicAttribute();
            attribute.setName("some-attribute");
            attribute.setValue("some-value");
            singleRoot.getAttributes().add(attribute);
            
            DynamicElement firstChild = facesConfigFactory.createDynamicElement();
            firstChild.setName("first-child");
            singleRoot.getChildNodes().add(firstChild);
            
            DynamicElement secondChild =
                facesConfigFactory.createDynamicElement();
            secondChild.setName("second-child");
            //secondChild.setTextContent("secondChild text content");
            singleRoot.getChildNodes().add(secondChild);
            extType.getChildNodes().add(singleRoot);
            newComponent.getComponentExtension().add(extType);
            
            edit.getFacesConfig().getComponent().add(newComponent);
            edit.save(null);
        } finally {
            if (edit != null) {
                edit.dispose();
                assertTrue(edit.isDisposed());
                edit = null;
            }
        }
    }
}
