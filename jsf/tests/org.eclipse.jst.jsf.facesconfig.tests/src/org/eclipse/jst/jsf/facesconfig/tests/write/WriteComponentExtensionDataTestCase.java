package org.eclipse.jst.jsf.facesconfig.tests.write;

import org.eclipse.jst.jsf.facesconfig.emf.ComponentClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentTypeType;
import org.eclipse.jst.jsf.facesconfig.emf.DynamicAttribute;
import org.eclipse.jst.jsf.facesconfig.emf.DynamicElement;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.tests.util.CommonStructuresUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

public class WriteComponentExtensionDataTestCase extends BaseWriteTestCase {
    private static final String COMPONENT = "component";
    
    private static final String COMPONENT_CLASS = "component-class";

    private static final String COMPONENT_TYPE = "component-type";

    public WriteComponentExtensionDataTestCase(String name) {
        super(name);
    }


    public void testWriteExtensionData()
    {
        FacesConfigArtifactEdit edit = null;
        try 
        {
            edit = getArtifactEditForWrite();
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
