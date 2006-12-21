package org.eclipse.jst.jsf.facesconfig.tests.write;

import org.eclipse.jst.jsf.facesconfig.emf.ConverterExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.ConverterType;
import org.eclipse.jst.jsf.facesconfig.emf.DynamicElement;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.tests.util.CommonStructuresUtil;
import org.eclipse.jst.jsf.facesconfig.tests.util.FacesConfigModelUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

public class WriteConverterTestCase_1_2 extends WriteConverterTestCase {

    private final static String EXTENDED_CONVERTER_ID = "extended-converter-id";
        
    private final static String CONVERTER_EXTENSION = CommonStructuresUtil
            .createPreficedString(CONVERTER, "extension");
    private final static String CONVERTER_EXTENSION_ID = CommonStructuresUtil
            .createPreficedString(CONVERTER_EXTENSION, "id");
    private final static String CONVERTER_EXTENSION_TAG = CommonStructuresUtil
            .createPreficedString(CONVERTER_EXTENSION, "tag");

    public WriteConverterTestCase_1_2(String name) {
        super(name);
    }

    public void testConverterExtension() {
        FacesConfigArtifactEdit edit = null;

        try {
            edit = getArtifactEditForWrite();
            assertNotNull(edit.getFacesConfig());
            FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
            FacesConfigFactory facesConfigFactory = facesConfigPackage
                    .getFacesConfigFactory();

            ConverterType converter = facesConfigFactory.createConverterType();
            converter.setId(EXTENDED_CONVERTER_ID);

            ConverterExtensionType extensionType =
                facesConfigFactory.createConverterExtensionType();
            extensionType.setId(CONVERTER_EXTENSION_ID);

            extensionType.getChildNodes().add(createDynamicElement(CONVERTER_EXTENSION_TAG));
            
            converter.getConverterExtension().add(extensionType);
            edit.getFacesConfig().getConverter().add(converter);
            edit.save(null);
        } finally {
            if (edit != null) {
                edit.dispose();
                assertTrue(edit.isDisposed());
                edit = null;
            }
        }
        
        try 
        {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());

            ConverterType converter = 
                (ConverterType) FacesConfigModelUtil
                    .findEObjectElementById(edit.getFacesConfig().getConverter(), EXTENDED_CONVERTER_ID);
            assertNotNull(converter);
            
            assertEquals(1, converter.getConverterExtension().size());
            ConverterExtensionType extensionType =
                (ConverterExtensionType) converter.getConverterExtension().get(0);
            assertEquals(CONVERTER_EXTENSION_ID, extensionType.getId());

            assertEquals(1, extensionType.getChildNodes().size());
            DynamicElement element = (DynamicElement) extensionType.getChildNodes().get(0);
            assertEquals(CONVERTER_EXTENSION_TAG, element.getName());
        }
        finally
        {
            
            if (edit != null) {
                edit.dispose();
            }
        }
    }
}