package org.eclipse.jst.jsf.facesconfig.tests.write;

import org.eclipse.jst.jsf.core.internal.provisional.IJSFCoreConstants;
import org.eclipse.jst.jsf.facesconfig.emf.DynamicElement;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.tests.util.CommonStructuresUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

public class FacesConfigExtensionTestCase extends BaseWriteTestCase {
    private static final String FACES_CONFIG = "faces-config";

    private final static String FACES_CONFIG_EXTENSION = CommonStructuresUtil
            .createPreficedString(FACES_CONFIG, "extension");
    private final static String FACES_CONFIG_EXTENSION_ID = CommonStructuresUtil
            .createPreficedString(FACES_CONFIG_EXTENSION, "id");
    private final static String FACES_CONFIG_EXTENSION_TAG = CommonStructuresUtil
            .createPreficedString(FACES_CONFIG_EXTENSION, "tag");

    public FacesConfigExtensionTestCase(String name) {
        super(name);
    }
    
    protected void setUp() throws Exception 
    {
        super.setUp();
        // test should not be used with 1.1
        assertEquals(IJSFCoreConstants.JSF_VERSION_1_2, _facesVersion);
    }

    public void testFacesConfigExtension() {
        FacesConfigArtifactEdit edit = null;

        try {
            edit = getArtifactEditForWrite();
            assertNotNull(edit.getFacesConfig());
            FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
            FacesConfigFactory facesConfigFactory = facesConfigPackage
                    .getFacesConfigFactory();

            FacesConfigType facesConfigType = edit.getFacesConfig();

            FacesConfigExtensionType extensionType = facesConfigFactory
                    .createFacesConfigExtensionType();
            extensionType.setId(FACES_CONFIG_EXTENSION_ID);

            extensionType.getChildNodes().add(
                    createDynamicElement(FACES_CONFIG_EXTENSION_TAG));

            facesConfigType.getFacesConfigExtension().add(extensionType);

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

            assertEquals(1, facesConfig.getFacesConfigExtension().size());
            FacesConfigExtensionType extensionType = (FacesConfigExtensionType) facesConfig
                    .getFacesConfigExtension().get(0);
            assertEquals(FACES_CONFIG_EXTENSION_ID, extensionType.getId());

            assertEquals(1, extensionType.getChildNodes().size());
            DynamicElement element = (DynamicElement) extensionType
                    .getChildNodes().get(0);
            assertEquals(FACES_CONFIG_EXTENSION_TAG, element.getName());
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }
}
