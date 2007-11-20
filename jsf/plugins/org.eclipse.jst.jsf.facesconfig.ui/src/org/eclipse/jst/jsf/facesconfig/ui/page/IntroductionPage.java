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

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jst.jsf.common.ui.internal.guiutils.IntroductionSection;
import org.eclipse.jst.jsf.facesconfig.ui.EditorMessages;
import org.eclipse.jst.jsf.facesconfig.ui.EditorPlugin;
import org.eclipse.jst.jsf.facesconfig.ui.FacesConfigEditor;
import org.eclipse.jst.jsf.facesconfig.ui.preference.GEMPreferences;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
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
public class IntroductionPage extends FormPage implements IFacesConfigPage, ISelectionProvider, IPropertyChangeListener{
	
	private Button noIntroPageButton = null;

	/**
	 * @param facesConfigEditor
	 */
	public IntroductionPage(FacesConfigEditor facesConfigEditor) {
		super(facesConfigEditor, IntroductionPage.class.getName(),
				EditorMessages.editor_pageflow_page_intro_name);
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
		form.setText(EditorMessages.editor_pageflow_page_intro_title);

		form.setBackgroundImage(EditorPlugin.getDefault().getImage("form_banner.gif"));

		// create a layout
		TableWrapLayout layout = new TableWrapLayout();
		layout.numColumns = 1;
		layout.makeColumnsEqualWidth = true;
		layout.verticalSpacing = 20;
		form.getBody().setLayout(layout);

		IntroductionSection introPart = new IntroductionSection(
				FacesConfigEditor.EDITOR_ID, managedForm, toolkit,
				EditorMessages.editor_pageflow_page_intro_help_HelpContextID,
				EditorMessages.editor_pageflow_page_intro_help_HelpToolTip);
		
		noIntroPageButton = toolkit.createButton(form.getBody(), EditorMessages.editor_pageflow_page_intro_NoIntroPage, SWT.CHECK);
		noIntroPageButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				boolean selection = ((Button) e.widget).getSelection();
				EditorPlugin.getDefault().getPreferenceStore().setValue(GEMPreferences.SHOW_INTRO_EDITOR, !selection);
			}
		});
		TableWrapData td = new TableWrapData(TableWrapData.FILL_GRAB);
		td.colspan = 1;
		introPart.setLayoutData(td);

		getSite().setSelectionProvider(this);
		
		IPreferenceStore preferenceStore = EditorPlugin.getDefault().getPreferenceStore();
		preferenceStore.addPropertyChangeListener(this);
		updatePrefenceControls(preferenceStore);
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

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.facesconfig.ui.page.IFacesConfigPage#getInput()
	 */
	public Object getInput() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.facesconfig.ui.page.IFacesConfigPage#refresh()
	 */
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.facesconfig.ui.page.IFacesConfigPage#setInput(java.lang.Object)
	 */
	public void setInput(Object input) {
		// TODO Auto-generated method stub
		
	}

	public void propertyChange(PropertyChangeEvent event) {
		if (event != null) {
			String property = event.getProperty();
			if (property == null || property.equals(GEMPreferences.SHOW_INTRO_EDITOR)) {
				//assume that we are in an ui thread, as other listeners assume this too:
				updatePrefenceControls(EditorPlugin.getDefault().getPreferenceStore());
			}
		}
	}

	private void updatePrefenceControls(IPreferenceStore preferenceStore) {
		boolean noIntroPage = !GEMPreferences.getShowIntroEditor();
		noIntroPageButton.setSelection(noIntroPage);
	}

	public void dispose() {
		EditorPlugin.getDefault().getPreferenceStore().removePropertyChangeListener(this);
		super.dispose();
	}

}
