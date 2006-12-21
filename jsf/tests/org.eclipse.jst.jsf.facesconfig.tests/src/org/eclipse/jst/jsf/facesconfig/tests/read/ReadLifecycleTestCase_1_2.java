package org.eclipse.jst.jsf.facesconfig.tests.read;

import org.eclipse.jst.jsf.facesconfig.emf.DynamicElement;
import org.eclipse.jst.jsf.facesconfig.emf.LifecycleExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.LifecycleType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

public class ReadLifecycleTestCase_1_2 extends ReadLifecycleTestCase {

    public ReadLifecycleTestCase_1_2(String name) {
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

    public void testLifecycleExtension() {

        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());

            LifecycleType lifecycle1 = getLifecycleType1(edit.getFacesConfig());
            assertNotNull(lifecycle1);

            assertEquals(1, lifecycle1.getLifecycleExtension().size());
            LifecycleExtensionType lifecycleExtensionType = 
                (LifecycleExtensionType) lifecycle1.getLifecycleExtension().get(0);
            assertEquals(1, lifecycleExtensionType.getChildNodes().size());
            DynamicElement element = (DynamicElement) lifecycleExtensionType.getChildNodes().get(0);
            assertEquals("lifecycle-extension-tag", element.getName());
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }

}
