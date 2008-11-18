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
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.ColorButtonDialogField;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogField;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.IDialogFieldApplyListener;
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
public class BackgroundPreferencePage extends PreferencePage {
	private CSSPropertyContext _style;

	private StyleCombo _backgroundImageCombo, _backgroundRepeatCombo,
			_backgroundAttachmentCombo, _horizontalNumberCombo,
			_horizontalUnitCombo, _verticalNumberCombo, _verticalUnitCombo;

	private ColorButtonDialogField _backgroundColorField;

	/**
	 * @param element
	 * @param style
	 */
	public BackgroundPreferencePage(IDOMElement element,
			CSSPropertyContext style) {
		super();
		_style = style;

		setTitle(DialogsMessages.getString("BackgroundPreferencePage.Title")); //$NON-NLS-1$
	}

	/**
	 * @see org.eclipse.jface.preference.
	 *      PreferencePage#createContents(Composite)
	 */
	protected Control createContents(Composite parent) {
		GridLayout layout;
		GridData data;

		Composite top = new Composite(parent, SWT.NONE);
		layout = new GridLayout(3, false);
		data = new GridData(GridData.FILL_BOTH);
		top.setLayout(layout);
		top.setLayoutData(data);

		_backgroundColorField = new ColorButtonDialogField(SWT.BORDER, new ColorUtil());
		_backgroundColorField.setLabelText(DialogsMessages
				.getString("BackgroundBoxPreferencePage.BackgroundColor")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		_backgroundColorField.getLabelControl(null, top).setLayoutData(data);

		data = new GridData(GridData.FILL_HORIZONTAL);
		_backgroundColorField.getComboControl(null, top).setLayoutData(data);

		data = new GridData();
		_backgroundColorField.getChangeControl(null, top).setLayoutData(data);
		_backgroundColorField
				.setDialogFieldApplyListener(new IDialogFieldApplyListener() {
					public void dialogFieldApplied(DialogField field) {
						String color = _backgroundColorField.getText();

						_style.setBackgroundColor(color);
					}
				});

		Label backgroundImageLabel = new Label(top, SWT.NONE);
		backgroundImageLabel.setText(DialogsMessages
				.getString("BackgroundBoxPreferencePage.BackgroundImage")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		backgroundImageLabel.setLayoutData(data);

		_backgroundImageCombo = new StyleCombo(top, SWT.NONE);
		_backgroundImageCombo.setItems(IStyleConstants.NONE);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_backgroundImageCombo.setLayoutData(data);
		_backgroundImageCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String image = _backgroundImageCombo.getText();

				_style.setBackgroundImage(image);
			}
		});

		new Label(top, SWT.NONE);

		Label backgroundRepeatLabel = new Label(top, SWT.NONE);
		backgroundRepeatLabel
				.setText(DialogsMessages
						.getString("BackgroundBoxPreferencePage.BackgroundRepeatLabel")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		backgroundRepeatLabel.setLayoutData(data);

		_backgroundRepeatCombo = new StyleCombo(top, SWT.NONE);
		_backgroundRepeatCombo.setItems(IStyleConstants.REPEAT);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_backgroundRepeatCombo.setLayoutData(data);
		_backgroundRepeatCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String repeat = _backgroundRepeatCombo.getText();

				_style.setBackgroundRepeat(repeat);
			}
		});

		new Label(top, SWT.NONE);

		Label backgroundAttachmentLabel = new Label(top, SWT.NONE);
		backgroundAttachmentLabel
				.setText(DialogsMessages
						.getString("BackgroundBoxPreferencePage.BackgroundAttachmentLabel")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		backgroundAttachmentLabel.setLayoutData(data);

		_backgroundAttachmentCombo = new StyleCombo(top, SWT.NONE);
		_backgroundAttachmentCombo.setItems(IStyleConstants.ATTACHMENT);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_backgroundAttachmentCombo.setLayoutData(data);
		_backgroundAttachmentCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String attachment = _backgroundAttachmentCombo.getText();

				_style.setBackgroundAttachment(attachment);
			}
		});

		new Label(top, SWT.NONE);

		Label backgroundHorizontalLabel = new Label(top, SWT.NONE);
		backgroundHorizontalLabel.setText(DialogsMessages
				.getString("BackgroundBoxPreferencePage.HorizontalLabel")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		backgroundHorizontalLabel.setLayoutData(data);

		_horizontalNumberCombo = new StyleCombo(top, SWT.NONE);
		_horizontalNumberCombo.setItems(IStyleConstants.POSITION);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_horizontalNumberCombo.setLayoutData(data);
		_horizontalNumberCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				_horizontalUnitCombo.setEnabled(true);
				try {
					Integer.valueOf(_horizontalNumberCombo.getText());
				} catch (NumberFormatException ex) {
					_horizontalUnitCombo.setEnabled(false);
				}

				String position = _horizontalNumberCombo.getText();
				if (_horizontalUnitCombo.isEnabled()) {
					position += _horizontalUnitCombo.getText();
				}

				_style.setBackgroundPositionX(position);
			}
		});

		_horizontalUnitCombo = new StyleCombo(top, SWT.READ_ONLY);
		_horizontalUnitCombo.setItems(IStyleConstants.SIZE_UNIT);
		data = new GridData(GridData.FILL);
		_horizontalUnitCombo.setLayoutData(data);
		_horizontalUnitCombo.select(0);
		_horizontalUnitCombo.setEnabled(false);
		_horizontalUnitCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String position = _horizontalNumberCombo.getText();
				if (_horizontalUnitCombo.isEnabled()) {
					position += _horizontalUnitCombo.getText();
				}

				_style.setBackgroundPositionX(position);

			}
		});

		Label backgroundVerticalLabel = new Label(top, SWT.NONE);
		backgroundVerticalLabel.setText(DialogsMessages
				.getString("BackgroundBoxPreferencePage.VerticalLabel")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		backgroundVerticalLabel.setLayoutData(data);

		_verticalNumberCombo = new StyleCombo(top, SWT.NONE);
		_verticalNumberCombo.setItems(IStyleConstants.POSITION);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_verticalNumberCombo.setLayoutData(data);
		_verticalNumberCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				_verticalUnitCombo.setEnabled(true);
				try {
					Integer.valueOf(_verticalNumberCombo.getText());
				} catch (NumberFormatException ex) {
					_verticalUnitCombo.setEnabled(false);
				}

				String position = _verticalNumberCombo.getText();
				if (_verticalUnitCombo.isEnabled()) {
					position += _verticalUnitCombo.getText();
				}

				_style.setBackgroundPositionY(position);
			}
		});

		_verticalUnitCombo = new StyleCombo(top, SWT.READ_ONLY);
		_verticalUnitCombo.setItems(IStyleConstants.SIZE_UNIT);
		data = new GridData(GridData.FILL);
		_verticalUnitCombo.setLayoutData(data);
		_verticalUnitCombo.select(0);
		_verticalUnitCombo.setEnabled(false);
		_verticalUnitCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String position = _verticalNumberCombo.getText();
				if (_verticalUnitCombo.isEnabled()) {
					position += _verticalUnitCombo.getText();
				}

				_style.setBackgroundPositionY(position);
			}
		});

		initializeControls();

		return top;
	}

	private void initializeControls() {
		// background-color
		String color = _style.getBackgroundColor();
		if (!isEmptyString(color)) {
			_backgroundColorField.setTextWithoutUpdate(color);
		}

		// background-image
		String image = _style.getBackgroundImage();
		if (!isEmptyString(image)) {
			int index = _backgroundImageCombo.indexOf(image);
			if (index != -1) {
				_backgroundImageCombo.select(index);
			} else {
				_backgroundImageCombo.setText(image);
			}
		}

		// background-repeat
		String repeat = _style.getBackgroundRepeat();
		if (!isEmptyString(repeat)) {
			int index = _backgroundRepeatCombo.indexOf(repeat);
			if (index != -1) {
				_backgroundRepeatCombo.select(index);
			} else {
				_backgroundRepeatCombo.setText(repeat);
			}
		}

		// background-attachment
		String attachment = _style.getBackgroundAttachment();
		if (!isEmptyString(attachment)) {
			int index = _backgroundAttachmentCombo.indexOf(repeat);
			if (index != -1) {
				_backgroundAttachmentCombo.select(index);
			} else {
				_backgroundAttachmentCombo.setText(attachment);
			}
		}

		// background-position
		String position = _style.getBackgroundPositionX();
		if (!isEmptyString(position)) {
			int index = _horizontalNumberCombo.indexOf(position);
			if (index != -1) {
				_horizontalNumberCombo.select(index);
			} else {
				_horizontalNumberCombo.setText(position);
			}
		}
		position = _style.getBackgroundPositionY();
		if (!isEmptyString(position)) {
			int index = _verticalNumberCombo.indexOf(position);
			if (index != -1) {
				_verticalNumberCombo.select(index);
			} else {
				_verticalNumberCombo.setText(position);
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
