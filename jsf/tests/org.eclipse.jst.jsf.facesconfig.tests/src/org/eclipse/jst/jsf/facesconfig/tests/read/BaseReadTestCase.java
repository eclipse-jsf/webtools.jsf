/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.tests.read;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
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
                .getFacesConfigArtifactEditForRead(project, null);
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
            _facesConfigFile = _testConfiguration.get(CONFIG_FILE_KEY);
            assertNotNull(_facesConfigFile);
            _facesVersion = _testConfiguration.get(FACES_VERSION_KEY);
            assertNotNull(_facesVersion);
        }
        else
        {
            // defaults to 1.1; subs should override for higher versions
            _facesConfigFile = CONFIG_FILE_DEFAULT;
            _facesVersion = IJSFCoreConstants.JSF_VERSION_1_1;
        }
    }
}
