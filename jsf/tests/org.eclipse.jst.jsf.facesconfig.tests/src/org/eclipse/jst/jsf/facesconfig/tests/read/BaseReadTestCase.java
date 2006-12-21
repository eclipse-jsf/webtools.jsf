package org.eclipse.jst.jsf.facesconfig.tests.read;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.facesconfig.tests.util.WizardUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;
import org.eclipse.jst.jsf.test.util.ConfigurableTestCase;

public class BaseReadTestCase extends ConfigurableTestCase 
{
    final static String CONFIG_FILE_KEY = "config-file-key";
    final static String FACES_VERSION_KEY = "faces-version-key";
    final static String CONFIG_FILE_DEFAULT = "";
    
    protected IProject  project;
    protected String    _facesConfigFile;
    protected String    _facesVersion;
    
    public BaseReadTestCase(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
        
        initialize(_testConfiguration);
        
        WizardUtil.createProject(getName());
        project = WizardUtil.getTestProject(getName());
    }
    
    protected FacesConfigArtifactEdit getArtifactEditForRead()
    {
        FacesConfigArtifactEdit edit = null;
        
        if (CONFIG_FILE_DEFAULT.equals(_facesConfigFile))
        {
            edit = FacesConfigArtifactEdit
                .getFacesConfigArtifactEditForRead(project);
        }
        else
        {
            edit = FacesConfigArtifactEdit
                .getFacesConfigArtifactEditForRead(project, _facesConfigFile);
        }

        assertNotNull(edit);
        return edit;
    }
    
    protected void initialize(TestConfiguration testConfiguration)
    {
        if (_testConfiguration != null)
        {
            _facesConfigFile = (String) _testConfiguration.get(CONFIG_FILE_KEY);
            assertNotNull(_facesConfigFile);
            _facesVersion = (String) _testConfiguration.get(FACES_VERSION_KEY);
            assertNotNull(_facesVersion);
        }
        else
        {
            // defaults to 1.1; subs should override for higher versions
            _facesConfigFile = CONFIG_FILE_DEFAULT;
            _facesVersion = "1.1";
        }
    }
}
