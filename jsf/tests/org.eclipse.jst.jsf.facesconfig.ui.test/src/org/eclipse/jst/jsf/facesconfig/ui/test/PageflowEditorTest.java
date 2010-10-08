/*******************************************************************************
 * Copyright (c) 2004, 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.ui.test;



import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType;
import org.eclipse.jst.jsf.facesconfig.emf.ToViewIdType;
import org.eclipse.jst.jsf.facesconfig.ui.FacesConfigEditor;
import org.eclipse.jst.jsf.facesconfig.ui.page.IntroductionPage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.PageflowEditor;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.command.AddConnectionCommand;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.command.AddNodeCommand;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.command.DelegatingCommandStack;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.command.DeleteConnectionCommand;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.command.DeleteNodeCommand;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.command.ReconnectConnectionCommand;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowFactory;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.synchronization.TransformUtil;
import org.eclipse.ui.actions.ActionFactory;

/**
 * @author hmeng
 */

public class PageflowEditorTest extends FacesConfigEditorTest {
	private static final String LIST_JSP = "/list.jsp";

	private static final String INDEX_JSP = "/index.jsp";

	private static final String INDEX1_JSP = "/index1.jsp";

	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		editor.setActiveEditorPage(PageflowEditor.PAGE_ID);
	}

	public void testAddElements() {
		editor.setActiveEditorPage(PageflowEditor.PAGE_ID);
		Pageflow pageflow = getPageflow();
		PageflowPage source = createPage(INDEX_JSP);
		assertTrue(pageflow.getNodes().contains(source));

		PageflowPage target = createPage(LIST_JSP);
		assertTrue(pageflow.getNodes().contains(target));

		PageflowLink link = createLink(source, target);

		assertTrue(pageflow.getLinks().contains(link));
		FacesConfigType facesConfig = getFacesConfig();
		NavigationRuleType rule = (NavigationRuleType) facesConfig
				.getNavigationRule().get(0);
		NavigationCaseType caseType = (NavigationCaseType) rule
				.getNavigationCase().get(0);
		assertTrue(rule.getFromViewId().getTextContent().equals(INDEX_JSP));
		assertTrue(caseType.getToViewId().getTextContent().equals(LIST_JSP));
	}

	private PageflowLink createLink(PageflowPage source, PageflowPage target) {
		AddConnectionCommand connectionCommand = new AddConnectionCommand();
		connectionCommand.setSource(source);
		connectionCommand.setTarget(target);
		PageflowLink link = PageflowFactory.eINSTANCE.createPFLink();
		connectionCommand.setPFLink(link);
		editor.getDelegatingCommandStack().execute(
				connectionCommand);
		return link;
	}

	private PageflowPage createPage(String sourcePath) {
		PageflowPage source = PageflowFactory.eINSTANCE.createPFPage();
		source.setPath(sourcePath);
		AddNodeCommand command = new AddNodeCommand();
		command.setParent(getPageflow());
		command.setChild(source);
		DelegatingCommandStack stack = editor.getDelegatingCommandStack();
		assertNotNull("PageflowPage will not be added to the Pageflow, as the underlying command stack is null", stack.getCurrentCommandStack());
		stack.execute(command);
		return source;
	}

	private FacesConfigType getFacesConfig() {
		return editor.getFacesConfig();
	}

	public void testDeleteNode() {
		testAddElements();
		Pageflow pageflow = getPageflow();
		DeleteNodeCommand command = new DeleteNodeCommand(pageflow);
		command.setParent(pageflow);
		PageflowPage page = TransformUtil.findPage(INDEX_JSP, pageflow);
		command.setChild(page);
		command.execute();
		assertTrue(!pageflow.getNodes().contains(page));
		assertTrue(pageflow.getLinks().size() == 0);
		assertTrue(getFacesConfig().getNavigationRule().size() == 0);
	}

	public void testDeleteLink() {
		testAddElements();
		Pageflow pageflow = getPageflow();
		DeleteConnectionCommand command = new DeleteConnectionCommand();
		PageflowPage page = TransformUtil.findPage(INDEX_JSP, pageflow);
		PageflowLink link = (PageflowLink) page.getOutlinks().get(0);
		command.setSource(link.getSource());
		command.setTarget(link.getTarget());
		command.setPFLink(link);
		command.execute();
		assertTrue(link.getFCElements().isEmpty());
		assertTrue(link.eAdapters().size() == 0);
		assertTrue(!pageflow.getLinks().contains(link));
		assertTrue(pageflow.getLinks().size() == 0);
		assertTrue(getFacesConfig().getNavigationRule().size() == 0);
	}

	public void testAddNavigationCase() {
		testAddElements();
		NavigationRuleType rule = (NavigationRuleType) getFacesConfig()
				.getNavigationRule().get(0);
		rule.getNavigationCase().remove(0);
		assertTrue(getPageflow().getLinks().size() == 0);
	}

	public void testSetPFProperty() {
		testAddElements();
		PageflowPage page = TransformUtil.findPage(INDEX_JSP, getPageflow());
		page.setPath(INDEX1_JSP);
		NavigationRuleType rule = (NavigationRuleType) getFacesConfig()
				.getNavigationRule().get(0);
		assertTrue(getFacesConfig().getNavigationRule().size() == 1);
		assertTrue(rule.getFromViewId().getTextContent().equals(INDEX1_JSP));
	}

	public void testSetFCProperty() {
		testAddElements();
		NavigationRuleType rule = (NavigationRuleType) getFacesConfig()
				.getNavigationRule().get(0);
		rule.getFromViewId().setTextContent(INDEX1_JSP);
		assertTrue(getPageflow().getLinks().size() == 1);
		assertTrue(((PageflowPage) ((PageflowLink) getPageflow().getLinks()
				.get(0)).getSource()).getPath().equals(INDEX1_JSP));
	}

	private Pageflow getPageflow() {
		return editor.getPageflowPage().getPageflow();
	}

	public void testChangeLinkTarget() {
		testAddElements();
		ReconnectConnectionCommand command = new ReconnectConnectionCommand();
		PageflowPage page = createPage(INDEX1_JSP);
		command.setSource(page);
		PageflowLink link = (PageflowLink) getPageflow().getLinks().get(0);
		command.setPFLink(link);
		command.execute();
		NavigationRuleType rule = (NavigationRuleType) getFacesConfig()
				.getNavigationRule().get(0);
		Object element1 = link.getSource().getFCElements().getData().get(0);
		Object element2 = link.getTarget().getFCElements().getData().get(0);
		assertTrue(link.getSource().getFCElements().getData().size() == 1);
		assertTrue(link.getTarget().getFCElements().getData().size() == 1);
		assertTrue(element1 == rule.getFromViewId());

		assertTrue(((ToViewIdType) element2).eContainer().eContainer() == rule);
		assertTrue(getFacesConfig().getNavigationRule().size() == 1);
		assertTrue(rule.getFromViewId().getTextContent().equals(INDEX1_JSP));
	}

	public void testUndo() {
		testAddElements();
		Pageflow pageflow = getPageflow();
		DeleteConnectionCommand command = new DeleteConnectionCommand();
		PageflowPage page = TransformUtil.findPage(INDEX_JSP, pageflow);
		PageflowLink link = (PageflowLink) page.getOutlinks().get(0);
		command.setPFLink(link);
		editor.setActiveEditorPage(PageflowEditor.PAGE_ID);
		editor.getDelegatingCommandStack().execute(command);
		assertTrue(getFacesConfig().getNavigationRule().size() == 0);
		assertTrue(getPageflow().getLinks().size() == 0);
		editor.getDelegatingCommandStack().undo();
		assertTrue(getFacesConfig().getNavigationRule().size() == 1);
		assertTrue(getPageflow().getLinks().size() == 1);
	}

	public void testRedo() {
		testUndo();
		editor.getDelegatingCommandStack().redo();
		assertTrue(getFacesConfig().getNavigationRule().size() == 0);
		assertTrue(getPageflow().getLinks().size() == 0);
	}

	public void testEditorSwitch() throws Exception {
		editor.setFocus();
		editor.setActivePage(IntroductionPage.class.getName());
		assertTrue(editor.getActionBarContributor().getActionBars()
				.getGlobalActionHandler(ActionFactory.UNDO.getId()) == null);
		assertTrue(editor.getActionBarContributor().getActionBars()
				.getGlobalActionHandler(ActionFactory.REDO.getId()) == null);
		testAddElements();
		editor.setActivePage(PageflowEditor.PAGE_ID);
		assertTrue(editor.getActionBarContributor().getActionBars()
						.getGlobalActionHandler(ActionFactory.UNDO.getId())
						.isEnabled());

		editor.setActivePage(IntroductionPage.class.getName());
		assertTrue(editor.getActionBarContributor().getActionBars()
				.getGlobalActionHandler(ActionFactory.UNDO.getId()) == null);
		assertTrue(editor.getActionBarContributor().getActionBars()
				.getGlobalActionHandler(ActionFactory.REDO.getId()) == null);
		FacesConfigEditor anotherEditor = (FacesConfigEditor) openWithEditor("WebContent/WEB-INF/faces-config1.xml");
		anotherEditor.setFocus();
		assertTrue(anotherEditor.getActionBarContributor().getActionBars()
				.getGlobalActionHandler(ActionFactory.UNDO.getId()) == null);
		assertTrue(anotherEditor.getActionBarContributor().getActionBars()
				.getGlobalActionHandler(ActionFactory.REDO.getId()) == null);
		editor.setFocus();
		editor.setActiveEditorPage(PageflowEditor.PAGE_ID);
		assertTrue(editor.getActionBarContributor().getActionBars()
						.getGlobalActionHandler(ActionFactory.UNDO.getId())
						.isEnabled());
	}
}
