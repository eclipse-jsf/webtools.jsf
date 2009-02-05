/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.tests.tabbed.properties.sections;


import java.io.IOException;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogField;
import org.eclipse.jst.pagedesigner.editors.HTMLEditor;
import org.eclipse.jst.pagedesigner.editors.properties.IPropertyPageDescriptor;
import org.eclipse.jst.pagedesigner.properties.internal.AttributeGroupSection;
import org.eclipse.jst.pagedesigner.properties.internal.NullQuickEditTabSection;
import org.eclipse.jst.pagedesigner.properties.internal.QuickEditTabManager;
import org.eclipse.jst.pagedesigner.properties.internal.QuickEditTabSectionsDescriptor;
import org.eclipse.jst.pagedesigner.ui.dialogfields.DialogFieldWrapper;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.views.properties.tabbed.ISection;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class BasicTabbedPropertyPageTests extends BaseTestCase {
	public void testSanity() {
		HTMLEditor ed = null;
		try {			
			
			final IFile file = getJSPFile("/testdata/propertypages/testPropertyPage.jsp.data", "/testPropertyPage.jsp");
			assertNotNull(file);
			
			IViewPart view = showPropertiesView();
			assertNotNull(view);
			ed = openHTMLEditor(file);	
			assertNotNull(ed);
					
			Node node = getNode(file, 425);
            assertTrue(node instanceof Element);
            assertEquals("tagWithQuickEditMD", node.getLocalName()); 
            
            ISelection sel = getSelection(node);
            view.getViewSite().getSelectionProvider().setSelection(sel);
            ISelection selection = view.getViewSite().getSelectionProvider().getSelection();
            assertEquals(sel, selection);                        
            
		} catch(Exception ex) {
			ex.printStackTrace();
			fail(ex.getLocalizedMessage());
		} finally {
			if (ed != null)
				closeEditor(ed);
		}
	}
	
	
	/**
	 * A little white-box testing covering basic sanity of the framework 
	 * including sanity check of ITagAttributeCellEditorFactory
	 */
	public void testQuickEditTabManager() {
		IFile file = null;
		QuickEditTabManager mgr = null;
		HTMLEditor ed = null;
		try {
			file = getJSPFile("/testdata/propertypages/testPropertyPage.jsp.data", "/testPropertyPage.jsp");
			assertNotNull(file);
			ed = openHTMLEditor(file);	
			assertNotNull(ed);
			showPropertiesView();
			mgr = getWPETabbedPropertySheetPage(ed).getTabManager();
			assertNotNull(mgr);
			
			//Node with QuickEdit MD
			Node node1 = getNode(file, 425);            
			ISelection selection = getSelection(node1);			
			mgr.selectionChanged(ed, selection);
			QuickEditTabSectionsDescriptor tsg1 = mgr.getCurrentTabGroupDescriptor();
			assertNotNull("QuickEditTabSectionsDescriptor was null for Qtag with QE MD", tsg1);
			
			List<ISection> sections = tsg1.getSections();
			assertEquals("Expected 3 sections for tag qith QE MD", 3, sections.size());
			//check for expected sections
			//section 1 is a section group with 3 attributes
			ISection sec = sections.get(0);
			assertTrue("section was not AttributeGroupSection", sec instanceof AttributeGroupSection);
			//check expected DialogFields
			DialogField[] fields = ((AttributeGroupSection)sec).getAttributeGroup().getDialogFields();
			assertEquals("Expecting 4 dialog fields for the 4 attributes", 4, fields.length);
			
			//check dialog fields - sanity check of tag attribute cell editor factory
				//aBoolean
				DialogField fld = fields[0];
				assertTrue("IPropertyPageDescriptor not located for tagWithQuickEditMD", fld.getAttachedData("KEY_ATTR") instanceof IPropertyPageDescriptor);
				IPropertyPageDescriptor ppd = (IPropertyPageDescriptor)fld.getAttachedData("KEY_ATTR");
				assertEquals("aBoolean", ppd.getAttributeName());
				assertTrue("aBoolean fld should be a DialogFieldWrapper", fld instanceof DialogFieldWrapper);
				assertTrue("aBoolean should be a MDEnabledComboDialogField", ((DialogFieldWrapper)fld).getWrappedDialogField().getClass().getName().equals("org.eclipse.jst.pagedesigner.properties.dialogfields.MDEnabledComboDialogField"));
			
				//aStyle
				fld = fields[1];
				assertEquals("aStyle", ((IPropertyPageDescriptor)fld.getAttachedData("KEY_ATTR")).getAttributeName());
				assertTrue("aStyle should be a StyleButtonDialogField", ((DialogFieldWrapper)fld).getWrappedDialogField().getClass().getName().equals("org.eclipse.jst.pagedesigner.ui.dialogfields.StyleButtonDialogField"));

				//aStringPropWithMd
				fld = fields[2];
				assertEquals("aStringPropWithMd", ((IPropertyPageDescriptor)fld.getAttachedData("KEY_ATTR")).getAttributeName());
				assertTrue("aStringPropWithMd should be a MDEnabledComboDialogField", ((DialogFieldWrapper)fld).getWrappedDialogField().getClass().getName().equals("org.eclipse.jst.pagedesigner.properties.dialogfields.MDEnabledComboDialogField"));
				
				//aStringPropNoMd
				fld = fields[3];
				assertEquals("aStringPropNoMd", ((IPropertyPageDescriptor)fld.getAttachedData("KEY_ATTR")).getAttributeName());
				assertTrue("aStringPropNoMd should be a StringDialogField", ((DialogFieldWrapper)fld).getWrappedDialogField().getClass().getName().equals("org.eclipse.jst.jsf.common.ui.internal.dialogfield.StringDialogField"));

			///
				
			//2nd section in tabSectionDesc
			sec = sections.get(1);
			assertTrue("section was not FakePropertySection", sec instanceof FakePropertySection);
			//
			sec = sections.get(2);
			assertTrue("section was not FakePropertySectionUsingDialogField", sec instanceof FakePropertySectionUsingDialogField);
			
			//Node without QuickEdit MD
			Node node2 = getNode(file, 475);
			selection = getSelection(node2);			
			mgr.selectionChanged(ed, selection);
			QuickEditTabSectionsDescriptor tsg2 = mgr.getCurrentTabGroupDescriptor();
			assertNotNull("QuickEditTabSectionsDescriptor was null", tsg2);
			
			sections = tsg2.getSections();
			assertEquals("Expected single section", 1, sections.size());
			//check for expected sections
			sec = sections.get(0);
			assertTrue("section was not NullQuickEditTabSection", sec instanceof NullQuickEditTabSection);			
			
			//set back to QuikEditMD node... should be same descriptor
			selection = getSelection(node1);			
			mgr.selectionChanged(ed, selection);
			assertSame("Desc w/MD not same", tsg1, mgr.getCurrentTabGroupDescriptor());
			
			//null desc
			selection = getSelection(node2);			
			mgr.selectionChanged(ed, selection);			
			assertSame("Desc wo/MD not same", tsg2, mgr.getCurrentTabGroupDescriptor());
			
			//test tag with bad QE MD			
			Node node3 = getNode(file, 530);
			selection = getSelection(node3);			
			mgr.selectionChanged(ed, selection);
			QuickEditTabSectionsDescriptor tsg3 = mgr.getCurrentTabGroupDescriptor();
			assertNotNull("QuickEditTabSectionsDescriptor was null for Qtag with bad QE MD", tsg3);
			
			sections = tsg3.getSections();
			assertEquals("Expected 2 sections for tag with bad QE MD", 2, sections.size());//missing section is skipped
			//check for expected sections
			//section 1 is a section group with 2 attributes.  One bad attr was skipped
			sec = sections.get(0);
			assertTrue("section was not AttributeGroupSection", sec instanceof AttributeGroupSection);
			//
			sec = sections.get(1);
			assertTrue("section was not FakePropertySectionUsingDialogField", sec instanceof FakePropertySectionUsingDialogField);

		} catch (IOException ex) {
			ex.printStackTrace();
			fail(ex.getLocalizedMessage());
		} catch (CoreException ex) {
			ex.printStackTrace();
			fail(ex.getLocalizedMessage());
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getLocalizedMessage());
		} finally {
			mgr = null;
			if (ed != null)
				closeEditor(ed);
		
		}				
	}

	public void testMultipleJSPs(){
		QuickEditTabManager mgr1 = null;
		QuickEditTabManager mgr2 = null;
		HTMLEditor ed1 = null;
		HTMLEditor ed2 = null; 
		try {
			IFile file = getJSPFile("/testdata/propertypages/testPropertyPage.jsp.data", "/testPropertyPage.jsp");
			assertNotNull(file);
			ed1 = openHTMLEditor(file);	
			assertNotNull(ed1);
			showPropertiesView();
			mgr1 = getWPETabbedPropertySheetPage(ed1).getTabManager();			
			assertNotNull(mgr1);
			
			//Node with QuickEdit MD
			Node node1 = getNode(file, 425);            
			ISelection selection = getSelection(node1);			
			mgr1.selectionChanged(ed1, selection);
			QuickEditTabSectionsDescriptor tsg1 = mgr1.getCurrentTabGroupDescriptor();
			assertNotNull(tsg1);
			
			//open 2nd page
			file = getJSPFile("/testdata/propertypages/testPropertyPage.jsp.data", "/testPropertyPage2.jsp");
			assertNotNull(file);
			ed2 = openHTMLEditor(file);	
			assertNotNull(ed2);
			showPropertiesView();
			mgr2 = getWPETabbedPropertySheetPage(ed2).getTabManager();
			assertNotNull(mgr2);
			
			//Manager instances should be the same for different WPE instances within same project
			assertSame("Manager instances should be the same for different WPE instances within same project", mgr1.getQuickEditTabSectionsManager(), mgr2.getQuickEditTabSectionsManager());
			
			Node node2 = getNode(file, 425);            
			selection = getSelection(node2);			
			mgr2.selectionChanged(ed2, selection);
			QuickEditTabSectionsDescriptor tsg2= mgr2.getCurrentTabGroupDescriptor();
			assertSame("section descriptor for tag on different pages should be the same", tsg1, tsg2);
						
			
		} catch (IOException ex) {
			ex.printStackTrace();
			fail(ex.getLocalizedMessage());
		} catch (CoreException ex) {
			ex.printStackTrace();
			fail(ex.getLocalizedMessage());
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getLocalizedMessage());
		} finally {				
			mgr1 = null;
			mgr2 = null;
			if (ed1 != null) {
				closeEditor(ed1);
				ed1 = null;
			}
			if (ed2 != null){
				//should cause disposal of QuickEditTabManager and SectionManager
				closeEditor(ed2);
				ed2 = null;
			}
		}
	}
	
	public void testMultiProject() {
		QuickEditTabManager mgr1 = null;
		QuickEditTabManager mgr2 = null;
		HTMLEditor ed1 = null;
		HTMLEditor ed2 = null; 
		try {
			IFile file = getJSPFile("/testdata/propertypages/testPropertyPage.jsp.data", "/testPropertyPage.jsp");
			assertNotNull(file);
			ed1 = openHTMLEditor(file);	
			assertNotNull(ed1);
			showPropertiesView();
			mgr1 = getWPETabbedPropertySheetPage(ed1).getTabManager();			
			assertNotNull(mgr1);
			
			//Node with QuickEdit MD
			Node node1 = getNode(file, 425);            
			ISelection selection = getSelection(node1);			
			mgr1.selectionChanged(ed1, selection);
			QuickEditTabSectionsDescriptor tsg1 = mgr1.getCurrentTabGroupDescriptor();
			assertNotNull(tsg1);
			
			//open 2nd page from second project
			file = getJSPFileFromSecondProject("/testdata/propertypages/testPropertyPage.jsp.data", "/testPropertyPage2.jsp");
			assertNotNull(file);
			ed2 = openHTMLEditor(file);	
			assertNotNull(ed2);
			showPropertiesView();
			mgr2 = getWPETabbedPropertySheetPage(ed2).getTabManager();
			assertNotNull(mgr2);
			
			//Manager instances should be the DIFFERENT for different WPE instances within DIFFERENT projects
			assertNotSame("Should have different QuickEditMgrInstances for different projects", mgr1.getQuickEditTabSectionsManager(), mgr2.getQuickEditTabSectionsManager());
			
			Node node2 = getNode(file, 425);            
			selection = getSelection(node2);			
			mgr2.selectionChanged(ed2, selection);
			QuickEditTabSectionsDescriptor tsg2= mgr2.getCurrentTabGroupDescriptor();
			assertNotSame("section descriptor for tag on different pages from different projects should be different", tsg1, tsg2);
						
		} catch (IOException ex) {
			ex.printStackTrace();
			fail(ex.getLocalizedMessage());
		} catch (CoreException ex) {
			ex.printStackTrace();
			fail(ex.getLocalizedMessage());
		} catch (Exception ex) {
			ex.printStackTrace();
			fail(ex.getLocalizedMessage());
		} finally {				
			mgr1 = null;
			mgr2 = null;
			if (ed1 != null) {
				//should cause disposal of QuickEditTabManager and SectionManager
				closeEditor(ed1);
				ed1 = null;
			}
			if (ed2 != null){
				//should cause disposal of QuickEditTabManager and SectionManager
				closeEditor(ed2);
				ed2 = null;
			}
		}
	}
	
}
