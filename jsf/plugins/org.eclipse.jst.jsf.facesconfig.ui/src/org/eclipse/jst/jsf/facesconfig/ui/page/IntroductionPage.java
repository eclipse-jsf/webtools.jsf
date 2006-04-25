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
package org.eclipse.jst.jsf.facesconfig.ui.page;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jst.jsf.facesconfig.common.CommonPlugin;
import org.eclipse.jst.jsf.facesconfig.common.guiutils.IntroductionSection;
import org.eclipse.jst.jsf.facesconfig.ui.FacesConfigEditor;
import org.eclipse.jst.jsf.facesconfig.ui.IconResources;
import org.eclipse.jst.jsf.facesconfig.ui.NewEditorResourcesNLS;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

/**
 * Handle the Introduction page for the pageflow editor.
 * 
 * @author jchoi
 */
public class IntroductionPage extends FormPage implements ISelectionProvider{
	/**
	 * @param facesConfigEditor
	 */
	public IntroductionPage(FacesConfigEditor facesConfigEditor) {
		super(facesConfigEditor, IntroductionPage.class.getName(),
				NewEditorResourcesNLS.editor_pageflow_page_intro_name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.forms.editor.FormPage#createFormContent(org.eclipse.ui.forms.IManagedForm)
	 */
	protected void createFormContent(IManagedForm managedForm) {
		// get the form
		ScrolledForm form = managedForm.getForm();
		FormToolkit toolkit = getEditor().getToolkit();
		form.setText(NewEditorResourcesNLS.editor_pageflow_page_intro_title);

		form.setBackgroundImage(CommonPlugin.getDefault().getImage(
				IconResources.getString("Common.formbanner")));

		// create a layout
		TableWrapLayout layout = new TableWrapLayout();
		layout.numColumns = 1;
		layout.makeColumnsEqualWidth = true;
		form.getBody().setLayout(layout);

		IntroductionSection introPart = new IntroductionSection(
				FacesConfigEditor.EDITOR_ID, managedForm, toolkit,
				NewEditorResourcesNLS.editor_pageflow_page_intro_help_HelpContextID,
				NewEditorResourcesNLS.editor_pageflow_page_intro_help_HelpToolTip);

		TableWrapData td = new TableWrapData(TableWrapData.FILL_GRAB);
		td.colspan = 1;
		introPart.setLayoutData(td);

		getSite().setSelectionProvider(this);
	}

	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.forms.editor.IFormPage#isEditor()
	 */
	public boolean isEditor() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ISelectionProvider#getSelection()
	 */
	public ISelection getSelection() {
		return StructuredSelection.EMPTY;
	}

	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
		// TODO Auto-generated method stub
		
	}

	public void setSelection(ISelection selection) {
		// TODO Auto-generated method stub
		
	}

}
