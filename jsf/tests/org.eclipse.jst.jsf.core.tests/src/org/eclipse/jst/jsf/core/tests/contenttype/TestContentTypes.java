/*******************************************************************************
 * Copyright (c) 2010 Oracle Corporation and others.
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
package org.eclipse.jst.jsf.core.tests.contenttype;

import java.util.zip.ZipFile;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jst.common.project.facet.core.JavaFacet;
import org.eclipse.jst.jsf.core.tests.TestsPlugin;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.osgi.framework.Bundle;

/**
 * Test that content types are being calculated correctly.
 * 
 * @author Ian Trimble - Oracle
 */
public class TestContentTypes extends TestCase {

	private static final String FACELET_FILENAME =
		"WebContent/contentType_jsf_facelet.xhtml"; //$NON-NLS-1$
	private static final String FACELET_COMPOSITE_FILENAME =
		"WebContent/resources/ezcomp/contentType_jsf_facelet_composite.xhtml"; //$NON-NLS-1$

	private WebProjectTestEnvironment _webProject;

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();

		JSFTestUtil.setValidationEnabled(false);
		JSFTestUtil.setInternetProxyPreferences(true, "www-proxy.uk.oracle.com", "80"); //$NON-NLS-1$ //$NON-NLS-2$

		final ZipFile zipFile = JSFTestUtil.createZipFile(
				TestsPlugin.getDefault().getBundle(),
				"/testfiles/testzips/TestProject1.zip"); //$NON-NLS-1$
		_webProject = new WebProjectTestEnvironment(
				this,
				JavaFacet.VERSION_1_5,
				ProjectFacetsManager.getProjectFacet("jst.web").getVersion("2.5")); //$NON-NLS-1$ //$NON-NLS-2$
		_webProject.createFromZip2(zipFile, true);
		Job.getJobManager().beginRule(_webProject.getTestProject(), null);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		Job.getJobManager().endRule(_webProject.getTestProject());
		super.tearDown();
	}

	/**
	 * Sanity check.
	 * @throws Exception when one or more errors occur during test.
	 */
	public void testSanity() throws Exception {
		final IProject project = _webProject.getTestProject();
		assertNotNull(project);
		assertTrue(project.isAccessible());

		final IFile faceletFile = project.getFile(new Path(FACELET_FILENAME));
		assertTrue(faceletFile.isAccessible());

		final IFile faceletCompositeFile = project.getFile(new Path(FACELET_COMPOSITE_FILENAME));
		assertTrue(faceletCompositeFile.isAccessible());
	}

	/**
	 * Test that accessing describer class does not change the bundle state.
	 * @throws Exception when one or more errors occur during test.
	 */
	public void testNoBundleStateChange() throws Exception {
		//assert bundle is available
		final Bundle bundle = Platform.getBundle("org.eclipse.jst.jsf.core"); //$NON-NLS-1$
		assertNotNull(bundle);

		//get initial bundle state
		final int initialBundleState = bundle.getState();

		//assert test file has expected content type (and, therefore, describer class was loaded)
		final IFile faceletFile = _webProject.getTestProject().getFile(new Path(FACELET_FILENAME));
		assertTrue(hasContentType(
				faceletFile.getFullPath().toString(), "jsf.facelet")); //$NON-NLS-1$

		//assert bundle state has not been changed
		assertEquals(
				"Bundle state changed while querying content type", //$NON-NLS-1$
				initialBundleState, bundle.getState());
	}

	/**
	 * Test that the "jsf.facelet" content type is being calculated correctly.
	 * @throws Exception when one or more errors occur during test.
	 */
	public void testJSFFaceletContentType() throws Exception {
		final IFile faceletFile = _webProject.getTestProject().getFile(
				new Path(FACELET_FILENAME));
		assertTrue(hasContentType(
				faceletFile.getFullPath().toString(), "jsf.facelet")); //$NON-NLS-1$
	}

	/**
	 * Test that the "jsf.facelet.composite" content type is being calculated correctly.
	 * @throws Exception when one or more errors occur during test.
	 */
	public void testJSFFaceletCompositeContentType() throws Exception {
		final IFile faceletFile = _webProject.getTestProject().getFile(
				new Path(FACELET_COMPOSITE_FILENAME));
		assertTrue(hasContentType(
				faceletFile.getFullPath().toString(), "jsf.facelet.composite")); //$NON-NLS-1$
	}

	private boolean hasContentType(String filename, String contentTypeId) {
		boolean hasContentType = false;
		final IContentType[] contentTypes =
			Platform.getContentTypeManager().findContentTypesFor(filename);
		for (final IContentType contentType: contentTypes) {
			if (contentTypeId.equals(contentType.getId())) {
				hasContentType = true;
				break;
			}
		}
		return hasContentType;
	}

}
