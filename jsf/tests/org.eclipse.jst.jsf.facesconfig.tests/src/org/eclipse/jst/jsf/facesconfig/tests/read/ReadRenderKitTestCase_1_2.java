package org.eclipse.jst.jsf.facesconfig.tests.read;

import org.eclipse.jst.jsf.core.internal.provisional.IJSFCoreConstants;
import org.eclipse.jst.jsf.facesconfig.emf.DynamicElement;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

public class ReadRenderKitTestCase_1_2 extends ReadRenderKitTestCase {
    public ReadRenderKitTestCase_1_2(String name) {
        super(name);
    }

    protected void initialize(TestConfiguration testConfiguration) {
        // override base when not in a configurable test suite
        if(_testConfiguration == null)
        {
            _facesConfigFile = "WEB-INF/faces-config_1_2.xml";
            _facesVersion = IJSFCoreConstants.JSF_VERSION_1_2;
        }
        else
        {
            super.initialize(testConfiguration);
        }
    }

    public void testValidatorExtension() {

        FacesConfigArtifactEdit edit = null;
        try 
        {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());

            RenderKitType renderKit1 = getRenderKit1(edit.getFacesConfig());
            assertNotNull(renderKit1);

            assertEquals(1, renderKit1.getRenderKitExtension().size());
            RenderKitExtensionType renderKitExtensionType = 
                (RenderKitExtensionType) renderKit1.getRenderKitExtension().get(0);
            assertEquals(1, renderKitExtensionType.getChildNodes().size());
            DynamicElement element = (DynamicElement) renderKitExtensionType.getChildNodes().get(0);
            assertEquals("render-kit-extension-tag", element.getName());
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }
}
