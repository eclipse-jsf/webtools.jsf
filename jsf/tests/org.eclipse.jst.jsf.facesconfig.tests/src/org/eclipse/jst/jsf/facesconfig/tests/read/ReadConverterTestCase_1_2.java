package org.eclipse.jst.jsf.facesconfig.tests.read;

import org.eclipse.jst.jsf.facesconfig.emf.ConverterExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.ConverterType;
import org.eclipse.jst.jsf.facesconfig.emf.DynamicElement;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

public class ReadConverterTestCase_1_2 extends ReadConverterTestCase 
{
    public ReadConverterTestCase_1_2(String name) {
        super(name);
    }

    protected void initialize(TestConfiguration testConfiguration) {
        // override base when not in a configurable test suite
        if(_testConfiguration == null)
        {
            _facesConfigFile = "WEB-INF/faces-config_1_2.xml";
            _facesVersion = "1.2";
        }
        else
        {
            super.initialize(testConfiguration);
        }
    }

    public void testConverterExtension() {

        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());

            ConverterType converter1 = getConverter1(edit.getFacesConfig());
            assertNotNull(converter1);

            assertEquals(1, converter1.getConverterExtension().size());
            ConverterExtensionType converterExtensionType = 
                (ConverterExtensionType) converter1.getConverterExtension().get(0);
            assertEquals(1, converterExtensionType.getChildNodes().size());
            DynamicElement element = (DynamicElement) converterExtensionType.getChildNodes().get(0);
            assertEquals("converter-extension-tag", element.getName());
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }
}
