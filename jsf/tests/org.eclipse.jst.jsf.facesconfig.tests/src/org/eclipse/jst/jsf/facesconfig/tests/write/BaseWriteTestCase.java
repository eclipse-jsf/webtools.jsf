/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.tests.write;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.facesconfig.emf.DynamicElement;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.tests.util.WizardUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;
import org.eclipse.jst.jsf.test.util.ConfigurableTestCase;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;

public class BaseWriteTestCase extends ConfigurableTestCase 
{
    final static String CONFIG_FILE_KEY = "config-file-key";
    final static String FACES_VERSION_KEY = "faces-version-key";
    
    protected IProject  project;
    protected String    _facesConfigFile;
    protected String    _facesVersion;
    
    public BaseWriteTestCase(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
        
        initialize(_testConfiguration);

        JSFTestUtil.setValidationEnabled(false);
        JSFTestUtil.setInternetProxyPreferences(true, "www-proxy.uk.oracle.com", "80");

        WizardUtil.createProject(getName());
        project = WizardUtil.getTestProject(getName());
    }
    
    protected FacesConfigArtifactEdit getArtifactEditForRead()
    {
        FacesConfigArtifactEdit edit = FacesConfigArtifactEdit
                .getFacesConfigArtifactEditForRead(project, _facesConfigFile);
        assertNotNull(edit);
        return edit;
    }
    
    protected FacesConfigArtifactEdit getArtifactEditForWrite()
    {
        FacesConfigArtifactEdit edit = FacesConfigArtifactEdit
            .getFacesConfigArtifactEditForWrite(project, _facesConfigFile);
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
    }
    
    protected DynamicElement createDynamicElement(String name)
    {
        DynamicElement element = 
            FacesConfigFactory.eINSTANCE.createDynamicElement();
        element.setName(name);
        return element;
    }
}
