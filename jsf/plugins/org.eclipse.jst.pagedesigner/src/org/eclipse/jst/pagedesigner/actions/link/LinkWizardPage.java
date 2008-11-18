/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.actions.link;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.viewer.DesignRange;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

/**
 * @author mengbo
 * @version 1.5
 */
/*package*/ class LinkWizardPage extends WizardPage {
	private static final String GROUP_TITLE = PDPlugin
			.getResourceString("LinkWizardPage.GroupTitle"); //$NON-NLS-1$

	private static final String PREVIEW_TAG_LABEL = PDPlugin
			.getResourceString("LinkWizardPage.PreviewLabel"); //$NON-NLS-1$

	private StyledText _text = null;

	private final Map<String, ILinkCreator> _linkMap;

	private String _linkType = null;

	private final EditPart _part;

	private final DesignRange _range;

	/**
	 * @param pageName
	 * @param title
	 * @param editPart
	 * @param range
	 * @param linkMap
	 */
	public LinkWizardPage(String pageName, String title, EditPart editPart,
			DesignRange range, Map<String, ILinkCreator> linkMap) {
		super(pageName, title, null);
		this._part = editPart;
		this._range = range;
		this._linkMap = linkMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		GridLayout layout;
		GridData data;

		layout = new GridLayout();
		layout.marginWidth = 20;
		parent.setLayout(layout);
		data = new GridData(GridData.FILL_BOTH | GridData.CENTER);
		parent.setLayoutData(data);

		Group group = new Group(parent, SWT.NONE);
		group.setText(GROUP_TITLE);
		layout = new GridLayout();
		group.setLayout(layout);
		data = new GridData(GridData.FILL_HORIZONTAL);
		group.setLayoutData(data);

		String defaultLink = ""; //$NON-NLS-1$
		Set<String> set = this._linkMap.keySet();
		int size = set.size();
		String[] keys = new String[size];
		Iterator<String> itr = set.iterator();
		int i = 0;
		while (itr.hasNext()) {
			String key = itr.next();
			keys[i++] = key;
		}
		Arrays.sort(keys);
		for (int j = 0; j < size; j++) {
			Button bt = new Button(group, SWT.RADIO);
			data = new GridData(GridData.FILL_HORIZONTAL);
			bt.setLayoutData(data);
			bt.setText(keys[j]);
			if (j == 0) {
				bt.setSelection(true);
				defaultLink = keys[j];
			}
			bt.addSelectionListener(new SelectLinkListener(keys[j]));
		}

		Label label = new Label(parent, SWT.NONE);
		label.setText(PREVIEW_TAG_LABEL);

		_text = new StyledText(parent, SWT.WRAP | SWT.V_SCROLL | SWT.BORDER);
		data = new GridData(GridData.FILL_BOTH);
		data.heightHint = 50;
		_text.setLayoutData(data);

		ILinkCreator creator = _linkMap.get(defaultLink);
		_linkType = creator.getLinkIdentifier();
		String previewText = creator.getSourcePreview(_part, _range);
		previewText = previewText == null ? "" : previewText; //$NON-NLS-1$
		_text.setText(previewText);
		_text.setEditable(false);

		super.setControl(group);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.IWizardPage#isPageComplete()
	 */
	public boolean isPageComplete() {
		return true;
	}

	/**
	 * @return the link type
	 */
	public String getChosenLinkType() {
		return this._linkType;
	}

    class SelectLinkListener extends SelectionAdapter {
		private String _key;

		/**
		 * @param key
		 */
		public SelectLinkListener(String key) {
			this._key = key;
		}

		public void widgetSelected(SelectionEvent e) {
			ILinkCreator creator = _linkMap.get(this._key);
			_linkType = creator.getLinkIdentifier();
			String previewText = creator.getSourcePreview(_part, _range);
			previewText = previewText == null ? "" : previewText; //$NON-NLS-1$
			_text.setText(previewText);
			super.widgetSelected(e);
		}
	}
}
