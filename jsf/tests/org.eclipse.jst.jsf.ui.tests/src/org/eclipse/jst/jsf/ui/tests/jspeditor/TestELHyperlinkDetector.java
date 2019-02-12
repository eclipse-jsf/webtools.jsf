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
package org.eclipse.jst.jsf.ui.tests.jspeditor;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.hyperlink.IHyperlink;
import org.eclipse.jst.jsf.contentassist.tests.ContentAssistTestsPlugin;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.tests.util.JSFCoreUtilHelper;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.core.tests.util.JSFCoreUtilHelper.ContextWrapper;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.TestFileResource;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.eclipse.jst.jsf.ui.internal.jspeditor.JSPELHyperlinkDetector;
import org.eclipse.jst.jsf.ui.internal.jspeditor.ITestHyperlink;
import org.eclipse.jst.jsf.ui.internal.jspeditor.JSPSourceUtil;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

public class TestELHyperlinkDetector extends TestCase {

	private WebProjectTestEnvironment _testEnv;
	private IFile _jspFile;
	private IType _myBeanType;

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		JSFTestUtil.setValidationEnabled(false);

		_testEnv = new WebProjectTestEnvironment(getClass().getName() + "_"
				+ getName());
		_testEnv.createProject(false);
		assertNotNull(_testEnv);
		assertNotNull(_testEnv.getTestProject());
		assertTrue(_testEnv.getTestProject().isAccessible());

		final JSFFacetedTestEnvironment jsfFacedEnv = new JSFFacetedTestEnvironment(
				_testEnv);
		jsfFacedEnv.initialize(IJSFCoreConstants.FACET_VERSION_1_1);

		_testEnv.loadResourceInWebRoot(ContentAssistTestsPlugin.getDefault()
				.getBundle(), "/testdata/faces-config_basic.xml.data",
				"/WEB-INF/faces-config.xml");
		_jspFile = (IFile) _testEnv.loadResourceInWebRoot(
				ContentAssistTestsPlugin.getDefault().getBundle(),
				"/testdata/basicELExpressions.jsp.data",
				"/basicELExpressions.jsp");
		assertNotNull(_jspFile);
		assertTrue(_jspFile.isAccessible());

		final JDTTestEnvironment jdtTestEnv = new JDTTestEnvironment(_testEnv);
		final TestFileResource resource = new TestFileResource();
		resource.load(ContentAssistTestsPlugin.getDefault().getBundle(),
				"/testdata/MyBean.java.data");
		jdtTestEnv.addSourceFile("src", "beans", "MyBean", resource.toString());
		_myBeanType = JavaCore.create(_testEnv.getTestProject()).findType(
				"beans.MyBean");
		assertNotNull(_myBeanType);
		assertTrue(_myBeanType.exists());
	}

	public void testSanity() throws Exception {
		JSFCoreUtilHelper.assertELSanity(_jspFile, 579, "value", "#{myBean}");
		JSFCoreUtilHelper.assertELSanity(_jspFile, 614, "value",
				"#{myBean.property}");
		JSFCoreUtilHelper.assertELSanity(_jspFile, 706, "action",
				"#{myBean.actionMethod}");

		JSFCoreUtilHelper.assertELVariableSanity(_jspFile, "myBean");
	}

	public void testDetectHyperlinks()
			throws Exception {
		// hyperlink from MyBean
		testJavaHyperlink(579, 0,"MyBean.java", IJavaElement.TYPE, "MyBean");
		// hyperlink from MyBean
		testJavaHyperlink(614, 0, "MyBean.java", IJavaElement.TYPE, "MyBean");
		// hyperlink from MyBean
		testJavaHyperlink(706, 0, "MyBean.java", IJavaElement.TYPE, "MyBean");

		// hyperlink from property
		testJavaHyperlink(614, 8, "MyBean.java", IJavaElement.METHOD, "getProperty");

		// hyperlink from method
		testJavaHyperlink(706, 8, "MyBean.java", IJavaElement.METHOD, "actionMethod");
	}

	private void testJavaHyperlink(final int regionStartOffset, final int relativeOffset
			, final String classFileName, final int javaElementType, final String javaElementName) throws Exception
	{
		final IWorkbenchPage  curPage =
            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		final TestableELHyperlinkDetector hyperlinkDetector = new TestableELHyperlinkDetector();
		final ContextWrapper context = JSFCoreUtilHelper.getDocumentContext(_jspFile,
				regionStartOffset);
		Region elRegion = JSPSourceUtil.findELRegion(context.getContext());
		elRegion = new Region(elRegion.getOffset()+relativeOffset, elRegion.getLength()-relativeOffset);
		final IHyperlink[] links = hyperlinkDetector.detectHyperlinks(context
				.getContext(), elRegion);
		assertEquals(1, links.length);
		final IHyperlink link = links[0];

		assertTrue(link instanceof ITestHyperlink);
		assertNotNull(link.getHyperlinkRegion());

		// as a reminder if add optional values
		assertNull(link.getTypeLabel());
		assertNotNull(link.getHyperlinkText());

		{
			final ITestHyperlink testHyperlink = (ITestHyperlink) link;
			final IJavaElement javaElement = testHyperlink.determineJavaElement();
			assertNotNull(javaElement);
			assertEquals(javaElementType, javaElement.getElementType());
			assertEquals(javaElementName, javaElement.getElementName());
		}

		link.open();

		final IEditorPart editorPart = curPage.getActiveEditor();
		assertNotNull(editorPart);
		final IEditorInput editorInput = editorPart.getEditorInput();
		assertTrue(editorInput instanceof FileEditorInput);
		final IFile file = ((FileEditorInput)editorInput).getFile();
		assertEquals(classFileName, file.getName());
		curPage.closeEditor(editorPart, false);
	}

	private static class TestableELHyperlinkDetector extends
			JSPELHyperlinkDetector {
		@Override
		public IHyperlink[] detectHyperlinks(
				final IStructuredDocumentContext context, final IRegion region) {
			return super.detectHyperlinks(context, region);
		}
	}
}
