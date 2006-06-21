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

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PlatformUI;
import org.eclipse.jst.jsf.ui.internal.classpath.JSFLibraryWizard;

import junit.framework.Assert;
import junit.framework.TestCase;

public class JSFLibraryWizardTestCases extends TestCase {

	// Test data
	private String pageName = "JSF Library";
	
	public JSFLibraryWizardTestCases(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/*
	 * Test method for 'org.eclipse.jst.jsf.ui.internal.classpath.JSFLibraryWizard.addPages()'
	 */
	public void testAddPages() {
		JSFLibraryWizard jsfLibWizard = new JSFLibraryWizard();
		
		jsfLibWizard.addPages();
		
		// Only one page added.
		Assert.assertEquals(1, jsfLibWizard.getPageCount());
		
		IWizardPage wp = jsfLibWizard.getPage(pageName);
		
		Assert.assertNotNull(wp);		
		Assert.assertEquals(pageName, wp.getName());
		
		// What about calling addPages again?
		// Shuld we exptec multiple wizard pages with the same name?
		jsfLibWizard.addPages();	
		Assert.assertEquals(2, jsfLibWizard.getPageCount());
	}

	public void testWizardDialog() {
		IWorkbenchWizard wizard = new JSFLibraryWizard();
		IWorkbench wb = PlatformUI.getWorkbench();
		wizard.init(wb, (IStructuredSelection)null);	// no selection
						
		WizardDialog dialog = new WizardDialog(wb.getActiveWorkbenchWindow().getShell(), wizard);
		
		// Dialog created successfully
		Assert.assertNotNull(dialog);
		
		dialog.setBlockOnOpen(false);
		
		// Open dialog, but what is next then?
		dialog.open();
		
		// verify control in the composite?
		
		Assert.assertFalse( wizard.canFinish() );
		
		dialog.close();
	}
	
}	// end of JSFLibraryWizardTestCases
