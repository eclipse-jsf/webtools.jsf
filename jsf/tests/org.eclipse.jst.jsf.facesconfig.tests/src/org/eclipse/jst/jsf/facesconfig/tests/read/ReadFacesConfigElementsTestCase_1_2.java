package org.eclipse.jst.jsf.facesconfig.tests.read;

import org.eclipse.jst.jsf.facesconfig.emf.DynamicElement;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigExtensionType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

public class ReadFacesConfigElementsTestCase_1_2 extends
        ReadFacesConfigElementsTestCase 
{
    public ReadFacesConfigElementsTestCase_1_2(String name) {
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

    public void testFacesConfigExtension() {

        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());
            assertEquals(1, edit.getFacesConfig().getFacesConfigExtension().size());
            FacesConfigExtensionType facesConfigExt = 
                (FacesConfigExtensionType) edit.getFacesConfig().getFacesConfigExtension().get(0);
            assertEquals(1, facesConfigExt.getChildNodes().size());
            DynamicElement element = (DynamicElement) facesConfigExt.getChildNodes().get(0);
            assertEquals("faces-config-extended-data", element.getName());
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }

}
