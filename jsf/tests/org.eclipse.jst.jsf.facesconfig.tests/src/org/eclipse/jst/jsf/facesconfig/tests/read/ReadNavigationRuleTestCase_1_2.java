package org.eclipse.jst.jsf.facesconfig.tests.read;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.jsf.core.internal.provisional.IJSFCoreConstants;
import org.eclipse.jst.jsf.facesconfig.emf.DynamicElement;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

public class ReadNavigationRuleTestCase_1_2 extends ReadNavigationRuleTestCase 
{
    public ReadNavigationRuleTestCase_1_2(String name) {
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

    public void testNavigationRuleExtension() {

        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());

            EList navRules = edit.getFacesConfig().getNavigationRule();
            assertEquals(2, navRules.size());
            NavigationRuleType navigation1 = 
                (NavigationRuleType) navRules.get(0);

            assertEquals(1, navigation1.getNavigationRuleExtension().size());
            NavigationRuleExtensionType navigationRuleExtensionType = 
                (NavigationRuleExtensionType) navigation1.getNavigationRuleExtension().get(0);
            assertEquals(1, navigationRuleExtensionType.getChildNodes().size());
            DynamicElement element = (DynamicElement) navigationRuleExtensionType.getChildNodes().get(0);
            assertEquals("navigation-extension-tag", element.getName());
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }

}
