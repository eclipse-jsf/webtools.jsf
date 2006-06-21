/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Gerry Kessler/Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.ui.tests.classpath;

import java.util.Iterator;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jst.jsf.ui.tests.util.JSFUITestHelper;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.PluginProvidedJSFLibrary;
import org.eclipse.jst.jsf.core.tests.util.JSFCoreUtilHelper;
import org.eclipse.jst.jsf.ui.internal.Messages;
import org.eclipse.jst.jsf.ui.internal.classpath.JSFLibrariesPreferencePage;

/**
 * Tests the <code>org.eclipse.jsf.ui.internal.classpaths.JSFLibrariesPreferencePage</code>
 * @author Gerry Kessler - Oracle
 *
 */
public class JSFLibrariesPreferencePageTestCases extends TestCase {

	public JSFLibrariesPreferencePageTestCases(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		//create registry if necessary
		//the deault library has 4 libs; one of each type
		JSFCoreUtilHelper.createJSFLibraryRegistry();
	}

	/**
	 * Tests that Add, Edit, Remove and Make default buttons are
	 * enabled/visible appropriatly for the differnt kinds of 
	 * JSF Library selections in the TreeViewer.
	 */
	public void testPreferencePage() {
		PreferenceDialog dialog = JSFUITestHelper.getPreferenceDialog("org.eclipse.jst.jsf.ui.jsfLibraryPage");

		Assert.assertNotNull(dialog);
		dialog.setBlockOnOpen(false);
		dialog.open();
		
		Assert.assertTrue(dialog.getSelectedPage() instanceof JSFLibrariesPreferencePage);
		JSFLibrariesPreferencePage page = (JSFLibrariesPreferencePage)dialog.getSelectedPage();

		Viewer tv = page.getLibraryViewer();
		
		PrefPageControls controls = new PrefPageControls((Composite)page.getControl());
		
		//Test no selection
		tv.setSelection(StructuredSelection.EMPTY);
		Assert.assertFalse(controls.getEditLibraryButton().getEnabled());
		Assert.assertTrue(controls.getNewLibraryButton().getEnabled());
		Assert.assertFalse(controls.getRemoveLibraryButton().getEnabled());
		Assert.assertFalse(controls.getMakeDefaultLibraryButton().getVisible());
		
		Iterator it = JSFCorePlugin.getDefault().getJSFLibraryRegistry().getAllJSFLibraries().iterator();
		while (it.hasNext()){
			JSFLibrary lib = (JSFLibrary)it.next();
			tv.setSelection(new StructuredSelection(lib));
			
			Assert.assertTrue(controls.getNewLibraryButton().getEnabled());
			
			if (lib.isImplementation())
				Assert.assertTrue(controls.getMakeDefaultLibraryButton().getVisible());
			else
				Assert.assertFalse(controls.getMakeDefaultLibraryButton().getVisible());
			
			if (lib instanceof PluginProvidedJSFLibrary){
				//Needs improvement to ensure that message is invoked on click
				Assert.assertTrue(controls.getEditLibraryButton().getEnabled());					
				Assert.assertTrue(controls.getRemoveLibraryButton().getEnabled());				
			}
			else {
				Assert.assertTrue(controls.getEditLibraryButton().getEnabled());					
				Assert.assertTrue(controls.getRemoveLibraryButton().getEnabled());
			}

		}
		
		//now select an Archive file of a library
		JSFLibrary lib = JSFCorePlugin.getDefault().getJSFLibraryRegistry().getDefaultImplementation();		
		tv.setSelection(new StructuredSelection(lib));
		((TreeViewer)tv).expandAll();
		tv.setSelection(new StructuredSelection(lib.getArchiveFiles().get(0)));
		Assert.assertTrue(controls.getEditLibraryButton().getEnabled());
		Assert.assertTrue(controls.getNewLibraryButton().getEnabled());
		Assert.assertFalse(controls.getRemoveLibraryButton().getEnabled());
		Assert.assertFalse(controls.getMakeDefaultLibraryButton().getVisible());		
		
		//How to test New, Edit, Make Default???
		
		dialog.close();
	}	
	
	private class PrefPageControls{
		Button btnAdd;
		Button btnRemove;
		Button btnMakeDefault;
		Button btnEdit;
		TreeViewer tv;
		
		PrefPageControls(Composite parent){
			findButtons(parent.getChildren());
		}
		
		public TreeViewer getViewer(){
			return tv;
		}
		
		public Button getNewLibraryButton(){
			return btnAdd;
		}
		
		public Button getRemoveLibraryButton(){
			return btnRemove;
		}
		
		public Button getMakeDefaultLibraryButton(){
			return btnMakeDefault;
		}
		
		public Button getEditLibraryButton(){
			return btnEdit;
		}
		private void findButtons(Control[] controls){
			for (int i=0;i < controls.length;i++){
				Control c = controls[i];
				if (c instanceof Button){
					Button b = (Button)c;
					if (b.getText().equals(Messages.JSFLibrariesPreferencePage_New))
						btnAdd = b;
					else if (b.getText().equals(Messages.JSFLibrariesPreferencePage_Remove))
						btnRemove = b;
					else if (b.getText().equals(Messages.JSFLibrariesPreferencePage_Edit))
						btnEdit = b;
					else if (b.getText().equals(Messages.JSFLibrariesPreferencePage_MakeDefault))
						btnMakeDefault = b;		
				}
				else if (c instanceof Composite){
					findButtons(((Composite)c).getChildren());
				}
			}
		}
	}
}
