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
package org.eclipse.jst.pagedesigner.ui.dialogs;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wst.css.core.internal.util.declaration.CSSPropertyContext;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

/**
 * @author mengbo
 * @version 1.5
 */
public class ListPreferencePage extends PreferencePage {
	private CSSPropertyContext _style;

	private StyleCombo _typeCombo, _imageCombo, _positionCombo;

	/**
	 * @param element
	 * @param style
	 */
	public ListPreferencePage(IDOMElement element, CSSPropertyContext style) {
		super();
		_style = style;

		setTitle(DialogsMessages.getString("ListPreferencePage.Title")); //$NON-NLS-1$
	}

	/**
	 * @see org.eclipse.jface.preference.
	 *      PreferencePage#createContents(Composite)
	 */
	protected Control createContents(Composite parent) {
		GridLayout layout;
		GridData data;

		Composite top = new Composite(parent, SWT.NONE);
		layout = new GridLayout(2, false);
		data = new GridData(GridData.FILL_BOTH);
		top.setLayout(layout);
		top.setLayoutData(data);

		Label typeLabel = new Label(top, SWT.NONE);
		typeLabel.setText(DialogsMessages.getString("ListPreferencePage.Type")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		typeLabel.setLayoutData(data);

		_typeCombo = new StyleCombo(top, SWT.NONE);
		_typeCombo.setItems(IStyleConstants.LIST_TYPE);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_typeCombo.setLayoutData(data);
		_typeCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String type = _typeCombo.getText();
				_style.setListStyleType(type);
			}
		});

		Label imageLabel = new Label(top, SWT.NONE);
		imageLabel.setText(DialogsMessages
				.getString("ListPreferencePage.Image")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		imageLabel.setLayoutData(data);

		_imageCombo = new StyleCombo(top, SWT.NONE);
		_imageCombo.setItems(IStyleConstants.NONE);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_imageCombo.setLayoutData(data);
		_imageCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String image = _imageCombo.getText();
				_style.setListStyleImage(image);
			}
		});

		Label positionLabel = new Label(top, SWT.NONE);
		positionLabel.setText(DialogsMessages
				.getString("ListPreferencePage.Position")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		positionLabel.setLayoutData(data);

		_positionCombo = new StyleCombo(top, SWT.NONE);
		_positionCombo.setItems(IStyleConstants.LIST_POSITION);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_positionCombo.setLayoutData(data);
		_positionCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String position = _positionCombo.getText();
				_style.setListStylePosition(position);
			}
		});

		initializeControls();

		return top;
	}

	private void initializeControls() {
		// list-style-tyle
		String type = _style.getListStyleType();
		if (!isEmptyString(type)) {
			int index = _typeCombo.indexOf(type);
			if (index != -1) {
				_typeCombo.select(index);
			} else {
				_typeCombo.setText(type);
			}
		}

		// list-style-image
		String image = _style.getListStyleImage();
		if (!isEmptyString(image)) {
			int index = _imageCombo.indexOf(image);
			if (index != -1) {
				_imageCombo.select(index);
			} else {
				_imageCombo.setText(image);
			}
		}

		// list-style-position
		String position = _style.getListStylePosition();
		if (!isEmptyString(position)) {
			int index = _positionCombo.indexOf(position);
			if (index != -1) {
				_positionCombo.select(index);
			} else {
				_positionCombo.setText(position);
			}
		}
	}

	public void setVisible(boolean visible) {
		super.setVisible(visible);

		getApplyButton().setVisible(false);
		getDefaultsButton().setVisible(false);
	}

	private boolean isEmptyString(String str) {
		if (str == null || str.length() == 0) {
			return true;
		}
        return false;
	}
}
