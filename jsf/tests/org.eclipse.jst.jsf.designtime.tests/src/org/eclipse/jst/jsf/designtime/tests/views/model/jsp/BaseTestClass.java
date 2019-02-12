/*******************************************************************************
 * Copyright (c) 2001, 2010 Oracle Corporation and others.
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
package org.eclipse.jst.jsf.designtime.tests.views.model.jsp;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.eclipse.jst.common.project.facet.core.JavaFacet;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.jsf.core.tests.util.JSFCoreUtilHelper;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.eclipse.jst.jsp.core.taglib.ITaglibRecord;
import org.eclipse.jst.jsp.core.taglib.TaglibIndex;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

/**
 * Does the basic setup for JSP tests.
 * 
 * @author cbateman
 * 
 */
abstract class BaseTestClass extends TestCase
{

    protected WebProjectTestEnvironment _webProjectTestEnv;
    protected Map<String, ITaglibRecord> _tagRecords;
    protected JSFVersion                 _jsfVersion;
    
    private final String ENV_JSF_VERSION = "jsfRuntimeVersion";

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        JSFTestUtil.setValidationEnabled(false);

        final String version = System.getProperty(ENV_JSF_VERSION);

        if (version == null)
        {
            fail("No version specified in " + ENV_JSF_VERSION);
        }

        _jsfVersion = JSFVersion.valueOfString(version);
        // only 1.1 and 1.2 are supported
        assertTrue(_jsfVersion == JSFVersion.V1_1
                || _jsfVersion == JSFVersion.V1_2);

        assertNotNull(JSFCoreUtilHelper.getJSFRuntimeJarsDirectory(_jsfVersion));

        final String jst_web_version = (_jsfVersion == JSFVersion.V1_1) ? "2.4"
                : ((_jsfVersion == JSFVersion.V1_2) ? "2.5" : null);
        assertNotNull(jst_web_version);

        final String jst_jsf_version = _jsfVersion.toString();

        _webProjectTestEnv = new WebProjectTestEnvironment(getClass().getName()
                + "_" + getName(), JavaFacet.VERSION_1_5, ProjectFacetsManager
                .getProjectFacet("jst.web").getVersion(jst_web_version));

        _webProjectTestEnv.createProject(false);
        assertNotNull(_webProjectTestEnv);
        assertNotNull(_webProjectTestEnv.getTestProject());
        assertTrue(_webProjectTestEnv.getTestProject().isAccessible());

        final JSFFacetedTestEnvironment jsfFacetedTestEnv = new JSFFacetedTestEnvironment(
                _webProjectTestEnv);
        jsfFacetedTestEnv.initialize(jst_jsf_version);

        assertTrue(JSFCoreUtilHelper.addJSFRuntimeJarsToClasspath(_jsfVersion,
                jsfFacetedTestEnv));

        final ITaglibRecord[] tldrecs = TaglibIndex
                .getAvailableTaglibRecords(_webProjectTestEnv.getTestProject()
                        .getFullPath());
        _tagRecords = new HashMap<String, ITaglibRecord>();
        for (final ITaglibRecord taglibRecord : tldrecs)
        {
            _tagRecords
                    .put(taglibRecord.getDescriptor().getURI(), taglibRecord);
        }

        assertNotNull(_tagRecords.get(ITLDConstants.URI_JSF_CORE));
        assertNotNull(_tagRecords.get(ITLDConstants.URI_JSF_HTML));
    }

}
