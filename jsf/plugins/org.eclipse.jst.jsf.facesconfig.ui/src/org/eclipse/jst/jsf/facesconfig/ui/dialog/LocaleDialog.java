/*******************************************************************************
 * Copyright (c) 2004, 2007 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.ui.dialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jst.jsf.common.ui.internal.guiutils.SWTUtils;
import org.eclipse.jst.jsf.facesconfig.ui.EditorMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @author Zhi-peng Zhang
 * @version
 */
public class LocaleDialog extends StatusDialog {
	private Text inputText;

	private TableViewer tableViewer;

	private String newLocale;

	private List existedLocaleList;

	private List itemsList;

	/** The mini width for the text control */
	private static final int TEXT_MINI_WIDTH = 350;

	/** The mini width for the table viewer control */
	private static final int TABLEVIEWER_MINI_WIDTH = 200;

	class LocaleViewerFilter extends ViewerFilter {
		private String locale;

		LocaleViewerFilter(String locale) {
			super();
			this.locale = locale;
		}

		public boolean select(Viewer v, Object parent, Object object) {
			if (object instanceof String) {
				if (null == locale || locale.length() == 0) {
					return true;
				}
                String aLocale = (String) object;
                return aLocale.startsWith(this.locale);
			}
			return false;
		}
	}

	/**
	 * 
	 * @param parentShell
	 * @param existedLocaleList
	 */
	public LocaleDialog(Shell parentShell, List existedLocaleList) {
		super(parentShell);
		this.existedLocaleList = existedLocaleList;
		Locale[] locales = Locale.getAvailableLocales();
		itemsList = new ArrayList(locales.length);

		for (int i = 0, n = locales.length; i < n; i++) {
			itemsList.add(locales[i].toString());
		}
		
		setStatusMessage(EditorMessages.LocaleConfigSection_Wizard_Page_AlreadyExistsError);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(EditorMessages.LocaleConfigSection_Wizard_WindowTitle);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.jsf.facesconfig.ui.dialog.StatusDialog#createDialogContents(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createDialogContents(Composite parent) {
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		parent.setLayout(layout);

		SWTUtils
				.createLabel(
						parent,
						EditorMessages.LocaleConfigSection_Wizard_Page_LabelText,
						1);

		inputText = SWTUtils.createTextBox(parent, 1);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = TEXT_MINI_WIDTH;
		inputText.setLayoutData(gd);

		inputText.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				newLocale = inputText.getText();
				tableViewer.resetFilters();
				tableViewer.addFilter(new LocaleViewerFilter(newLocale));
				tableViewer.add(itemsList.toArray());
				updateStatus();
			}
		});

		createTableViewer(parent);
		return parent;
	}

	/**
	 * @param composite
	 */
	private void createTableViewer(Composite composite) {
		tableViewer = new TableViewer(composite, SWT.BORDER | SWT.H_SCROLL
				| SWT.V_SCROLL);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = TABLEVIEWER_MINI_WIDTH;
		tableViewer.getControl().setLayoutData(gd);
		tableViewer.add(itemsList.toArray());

		tableViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event) {

						IStructuredSelection selection = (IStructuredSelection) event
								.getSelection();
						if (selection != null) {
							String text = (String) selection.getFirstElement();
							if (text != null && text.length() > 0) {
								newLocale = text;
								inputText.setText(text);
								updateStatus();
							}
						}
					}
				});

	}
	
	protected boolean isValid()
	{
		if (existedLocaleList != null && existedLocaleList.contains(newLocale)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the result
	 */
	public String getResult()
	{
		return newLocale;
	}
}
