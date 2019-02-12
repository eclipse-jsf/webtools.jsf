/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.metadata.tests.pagedesigner;

import org.eclipse.core.resources.IFile;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataDomainContext;
import org.eclipse.jst.jsf.common.metadata.query.TaglibDomainMetaDataQueryHelper;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryContextFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.taglib.ITaglibDomainMetaDataQuery;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.jsf.metadata.tests.util.IJSFRuntimeRequiredV11;
import org.eclipse.jst.jsf.metadata.tests.util.JSPTestCase;
import org.eclipse.jst.jsp.core.taglib.ITaglibRecord;
import org.eclipse.jst.jsp.core.taglib.TaglibIndex;
import org.eclipse.jst.pagedesigner.editors.palette.IPaletteContext;
import org.eclipse.jst.pagedesigner.editors.palette.TagToolPaletteEntry;
import org.eclipse.jst.pagedesigner.editors.palette.impl.PaletteItemManager;
import org.eclipse.jst.pagedesigner.editors.palette.impl.TaglibPaletteDrawer;
import org.eclipse.wst.html.core.internal.contentmodel.HTMLCMDocumentFactory;
import org.eclipse.wst.xml.core.internal.contentmodel.CMDocument;
import org.eclipse.wst.xml.core.internal.provisional.contentmodel.CMDocType;

/**
 * Spot tests the palette exercising the PaletteItemManager and PaletteHelper to ensure that the metadata is being loaded properly.
 * 
 * Assumes Sun v1.1 implementation and that the JSTL v1.1 jstl.jar and standard.jar are present
 */
public class PaletteTests extends JSPTestCase implements IJSFRuntimeRequiredV11 {

	private static String HTML_ID = "HTML";
	private static String HTML_URI = ITLDConstants.URI_HTML.toUpperCase();
	private static String HTML_LABEL = "HTML 4.0";
	private static String HTML_DESC = "HTML Tags";
	private static String HTML_BUTTON_ID = "INPUT.BUTTON";
	private static String HTML_BUTTON_LABEL = "Button"; 
	private static String HTML_BUTTON_DESC = "An INPUT element with TYPE=SUBMIT represents an input option, typically a button, that instructs the user agent to submit the form";
	private static String HTML_BUTTON_SMALL_ICON = "HTML_INPUT_BUTTON.gif";
	private static String HTML_A_ID = "a"; 
	private static String HTML_A_LABEL = "Link"; 
	private static String HTML_A_DESC = "This element allows the user to navigate the content of the document";
	private static String HTML_A_SMALL_ICON = "HTML_A.gif";
		
	private static String JSP_ID = "JSP11";//was lower cased... should be http://java.sun.com/jsp/Page
	private static String JSP_URI = "JSP11";//was lower cased... should be http://java.sun.com/jsp/Page
	private static String JSP_LABEL = "JSP";
	private static String JSP_DESC = "JSP Tags";
	private static String JSP_DIR_INCLUDE_ID = "jsp:directive.include";
	private static String JSP_DIR_INCLUDE_LABEL = "Directive.Include";
	private static String JSP_DIR_INCLUDE_DESC = "Includes a resource of text or code when the JSP page is translated";
	private static String JSP_DIR_INCLUDE_SMALL_ICON = "JSP_DIRECTIVE.INCLUDE.gif";
	
	private static String JSFHTML_LABEL = "JSF HTML";
	private static String JSFHTML_DESC = "This tag library contains JavaServer Faces component tags for all UIComponent + HTML RenderKit Renderer combinations defined in the JavaServer Faces Specification.";
	private static String JSFHTML_DEFAULT_PREFIX = "h";
	private static String JSFHTML_COMMAND_BTN_ID = "commandButton";
	private static String JSFHTML_COMMAND_BTN_LABEL = "Command Button";
	private static String JSFHTML_COMMAND_BTN_DESC = "Renders an HTML \"input\" element.Decode BehaviorObtain the Map from the \"requestParameterMap\" property of the ExternalContext. If the value in theMap for the value of the \"clientId\" property of the component is null, create a String by concatenating t...";
	private static String JSFHTML_COMMAND_BTN_SMALL_ICON = "JSF_COMMANDBUTTON.gif";
	
	private static String JSFCORE_LABEL = "JSF Core";
	private static String JSFCORE_DESC = "The core JavaServer Faces custom actions that are independent of any particular RenderKit.";
	private static String JSFCORE_DEFAULT_PREFIX = "f";
	private static String JSFCORE_ACTION_LISTENER_ID = "actionListener";
	private static String JSFCORE_ACTION_LISTENER_LABEL = "actionListener";
	private static String JSFCORE_ACTION_LISTENER__DESC = "Register an ActionListener instance on the UIComponent associated with the closest parent UIComponent custom action.";
	private static String JSFCORE_ACTION_LISTENER_ICON = "JSF_ACTIONLISTENER.gif";
	
	private static String JSTL_CORE_URI = "http://java.sun.com/jsp/jstl/core";
	private static String JSTLCORE_LABEL = "JSTL core";
	private static String JSTLCORE_DESC = "JSTL 1.1 core library";
	private static String JSTLCORE_DEFAULT_PREFIX = "c";
	private static String JSTLCORE_IF_ID = "if";
	private static String JSTLCORE_IF_LABEL = "if";
	private static String JSTLCORE_IF_DESC = "Simple conditional tag, which evalutes its body if the 	supplied condition is true and optionally exposes a Boolean 	scripting variable representing the evaluation of this condition";
	private static String JSTLCORE_IF_ICON = "PD_Palette_Default.gif";
	
	public PaletteTests() {
		super(	JSFVersion.V1_1,
		"/testfiles/web/faces-config_1_1.xml.data");

	}
	
	@SuppressWarnings("deprecation")
	public void testLoadJSPModel() {
		Model JSP11Model = TaglibDomainMetaDataQueryHelper.getModel(
				TaglibDomainMetaDataQueryHelper.createMetaDataModelContext(
						_testEnv.getTestProject(), CMDocType.JSP11_DOC_TYPE));
		
		assertNotNull(JSP11Model);
		
		Model jsp11Model = TaglibDomainMetaDataQueryHelper.getModel(
				TaglibDomainMetaDataQueryHelper.createMetaDataModelContext(
						_testEnv.getTestProject(), "jsp11"));
		
		
		IMetaDataDomainContext context = MetaDataQueryContextFactory.getInstance().createTaglibDomainModelContext(_testEnv.getTestProject());
		ITaglibDomainMetaDataQuery query = MetaDataQueryFactory.getInstance().createQuery(context);
		Model JSP11Model_2 = query.getQueryHelper().getModel(CMDocType.JSP11_DOC_TYPE);
		assertNotNull(JSP11Model_2);
		assertSame(jsp11Model, JSP11Model_2);
		
		Model jsp11Model_2 = query.getQueryHelper().getModel("jsp11");
		assertNotNull(jsp11Model_2);
		assertSame(jsp11Model, jsp11Model_2);
	}
	
	public void testPalletteDrawers() {
		final IFile file = _testEnv.getTestProject().getFile("xxx.jsp");//note jsp does not need to exist
		final IPaletteContext context = PaletteItemManager.createPaletteContext(file);
		final PaletteItemManager mgr = PaletteItemManager.getInstance(context);
		assertNotNull(mgr);
		
		CMDocument doc = HTMLCMDocumentFactory.getCMDocument(CMDocType.HTML_DOC_TYPE);
		TaglibPaletteDrawer drawer = mgr.getPaletteHelper().getOrCreateTaglibPaletteDrawer(mgr, doc, CMDocType.HTML_DOC_TYPE);
		verifyHTMLDrawer(drawer);
		
		doc = HTMLCMDocumentFactory.getCMDocument(CMDocType.JSP11_DOC_TYPE);
		drawer = mgr.getPaletteHelper().getOrCreateTaglibPaletteDrawer(mgr, doc, CMDocType.JSP11_DOC_TYPE);
		verifyJSPDrawer(drawer);
			
		drawer = getTaglibPaletteDrawer(mgr, ITLDConstants.URI_JSF_HTML);
		if (drawer != null)
			verifyJSFHTMLDrawer(drawer);
		
		drawer = getTaglibPaletteDrawer(mgr, ITLDConstants.URI_JSF_CORE);
		if (drawer != null)
			verifyJSFCoreDrawer(drawer);
		
		drawer = getTaglibPaletteDrawer(mgr, JSTL_CORE_URI);
		if (drawer != null)
			verifyJSTLCoreDrawer(drawer);
		
		mgr.release(context);
	}

	private TaglibPaletteDrawer getTaglibPaletteDrawer(final PaletteItemManager mgr, final String uri) {
		ITaglibRecord[] tldrecs = TaglibIndex.getAvailableTaglibRecords(_testEnv.getTestProject().getFullPath());
		for (int i=0;i<tldrecs.length;i++){	
			if (uri.equals(tldrecs[i].getDescriptor().getURI())) {
				return mgr.getPaletteHelper().configPaletteItemsByTLD(mgr, tldrecs[i]);
			}
		}
		return null;
	}

	private void verifyJSFHTMLDrawer(final TaglibPaletteDrawer drawer) {
		assertNotNull(drawer);		
		verifyDrawer(drawer, "JSF HTML", ITLDConstants.URI_JSF_HTML, ITLDConstants.URI_JSF_HTML, JSFHTML_DEFAULT_PREFIX, JSFHTML_LABEL, JSFHTML_DESC );
		for (final Object o : drawer.getChildren()) {
			verifyJSFHTMLTagItem((TagToolPaletteEntry)o);			
		}
		
	}
	
	private void verifyJSFCoreDrawer(final TaglibPaletteDrawer drawer) {
		assertNotNull(drawer);		
		verifyDrawer(drawer, "JSF Core", ITLDConstants.URI_JSF_CORE, ITLDConstants.URI_JSF_CORE, JSFCORE_DEFAULT_PREFIX, JSFCORE_LABEL, JSFCORE_DESC );
		for (final Object o : drawer.getChildren()) {
			verifyJSFCoreTagItem((TagToolPaletteEntry)o);			
		}
		
	}
	
	private void verifyHTMLDrawer(final TaglibPaletteDrawer drawer) {
		assertNotNull(drawer);		
		verifyDrawer(drawer, "HTML", HTML_ID, HTML_URI, null, HTML_LABEL, HTML_DESC );
		for (final Object o : drawer.getChildren()) {
			verifyHTMLTagItem((TagToolPaletteEntry)o);			
		}
		
	}

	private void verifyJSPDrawer(final TaglibPaletteDrawer drawer) {
		assertNotNull(drawer);		
		verifyDrawer(drawer, "JSP", JSP_ID, JSP_URI, null, JSP_LABEL, JSP_DESC );				
		for (final Object o : drawer.getChildren()) {
			verifyJSPTagItem((TagToolPaletteEntry)o);			
		}
		
	}
	private void verifyHTMLTagItem(final TagToolPaletteEntry tagTool) {
		assertNotNull(tagTool);
		if (tagTool.getId().equals(HTML_BUTTON_ID)) {
			verifyTagTool(tagTool, HTML_BUTTON_LABEL, HTML_BUTTON_DESC, HTML_BUTTON_SMALL_ICON);
		}
		else if (tagTool.getId().equals(HTML_A_ID)) {
			verifyTagTool(tagTool, HTML_A_LABEL, HTML_A_DESC, HTML_A_SMALL_ICON);
		}
		
	}

	private void verifyJSFHTMLTagItem(final TagToolPaletteEntry tagTool) {
		assertNotNull(tagTool);
		if (tagTool.getId().equals(JSFHTML_COMMAND_BTN_ID)) {
			verifyTagTool(tagTool, JSFHTML_COMMAND_BTN_LABEL, JSFHTML_COMMAND_BTN_DESC, JSFHTML_COMMAND_BTN_SMALL_ICON);
		}
		
	}
	
	private void verifyJSFCoreTagItem(final TagToolPaletteEntry tagTool) {
		assertNotNull(tagTool);
		if (tagTool.getId().equals(JSFCORE_ACTION_LISTENER_ID)) {
			verifyTagTool(tagTool, JSFCORE_ACTION_LISTENER_LABEL, JSFCORE_ACTION_LISTENER__DESC, JSFCORE_ACTION_LISTENER_ICON);
		}
		
	}
	private void verifyJSPTagItem(final TagToolPaletteEntry tagTool) {
		assertNotNull(tagTool);
		if (tagTool.getId().equals(JSP_DIR_INCLUDE_ID)) {
			verifyTagTool(tagTool, JSP_DIR_INCLUDE_LABEL, JSP_DIR_INCLUDE_DESC, JSP_DIR_INCLUDE_SMALL_ICON);
		}		
	}
	
	private void verifyJSTLCoreDrawer(final TaglibPaletteDrawer drawer) {
		assertNotNull(drawer);		
		verifyDrawer(drawer, "JSTL Core", JSTL_CORE_URI, JSTL_CORE_URI, JSTLCORE_DEFAULT_PREFIX, JSTLCORE_LABEL, JSTLCORE_DESC);				
		for (final Object o : drawer.getChildren()) {
			verifyJSTLCoreTagItem((TagToolPaletteEntry)o);			
		}
		
	}
	private void verifyJSTLCoreTagItem(final TagToolPaletteEntry tagTool) {
		assertNotNull(tagTool);
		if (tagTool.getId().equals(JSTLCORE_IF_ID)) {
			verifyTagTool(tagTool, JSTLCORE_IF_LABEL, JSTLCORE_IF_DESC, JSTLCORE_IF_ICON);
		}		
	}
	
	private void verifyDrawer(final TaglibPaletteDrawer drawer, String drawerID, final String id , final String uri, final String prefix,
			final String label, final String desc) {

		assertEquals(drawerID+": bad id", id, drawer.getId());
		assertEquals(drawerID+": bad uri", uri , drawer.getURI());
		assertEquals(drawerID+": bad label",label, drawer.getLabel());
		assertEquals(drawerID+": bad desc", desc, drawer.getDescription());
		assertEquals(drawerID+": bad default prefix", prefix, drawer.getDefaultPrefix());
	}

	private void verifyTagTool(final TagToolPaletteEntry tagTool,
			final String label, final String desc, final String iconName) {
		
		assertEquals(tagTool.getId()+": bad label", label, tagTool.getLabel());
		assertEquals(tagTool.getId()+": bad desc", desc, tagTool.getDescription());
		assertNotNull(tagTool.getId()+": missing small icon", tagTool.getSmallIcon());

		//the following is not very safe... relying on image.toString() returning something in the form of the following example:
		//	 URLImageDescriptor(bundleentry://379.fwk23328673/icons/palette/HTML/small/HTML_INPUT_BUTTON.gif)
		final String actualSmallIcon = tagTool.getSmallIcon().toString();
		final String frag = actualSmallIcon.substring(actualSmallIcon.length() - iconName.length() - 1);
		assertTrue(tagTool.getId()+": bad image", frag.indexOf(iconName) == 0);
	}
	
	public void testNullProjectPalette() {
		final IFile file = null;
		final IPaletteContext context = PaletteItemManager.createPaletteContext(file);
		final PaletteItemManager mgr = PaletteItemManager.getInstance(context);
		assertNotNull(mgr);
		
		//should have JSP tag categories - HTML and JSP only
		assertTrue(mgr.getTagRegistryIdentifier().getContentType().isAssociatedWith("xxx.jsp"));
		assertTrue(mgr.getAllCategories().size() == 2);
		assertNotNull(mgr.getTaglibPalletteDrawer(CMDocType.HTML_DOC_TYPE));
		assertNotNull(mgr.getTaglibPalletteDrawer(CMDocType.JSP11_DOC_TYPE));
	}
}
