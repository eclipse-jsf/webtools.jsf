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
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wst.css.core.internal.util.declaration.CSSPropertyContext;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

/**
 * @author mengbo
 * @version 1.5
 */
public class BorderPreferencePage extends PreferencePage {
	private CSSPropertyContext _style;

	private StyleCombo _styleTopCombo, _styleRightCombo, _styleBottomCombo,
			_styleLeftCombo;

	private StyleCombo _widthTopNumberCombo, _widthRightNumberCombo,
			_widthBottomNumberCombo, _widthLeftNumberCombo;

	private StyleCombo _widthTopUnitCombo, _widthRightUnitCombo,
			_widthBottomUnitCombo, _widthLeftUnitCombo;

	private ColorButtonDialogField _colorTopField, _colorRightField,
			_colorBottomField, _colorLeftField;

	/**
	 * @param element
	 * @param style
	 */
	public BorderPreferencePage(IDOMElement element, CSSPropertyContext style) {
		super();
		_style = style;

		setTitle(DialogsMessages.getString("BorderPreferencePage.Title")); //$NON-NLS-1$
	}

	/**
	 * @see org.eclipse.jface.preference.
	 *      PreferencePage#createContents(Composite)
	 */
	protected Control createContents(Composite parent) {
		GridLayout layout;
		GridData data;

		Composite top = new Composite(parent, SWT.NONE);
		layout = new GridLayout(1, false);
		data = new GridData(GridData.FILL_BOTH);
		top.setLayout(layout);
		top.setLayoutData(data);

		Group styleGroup = new Group(top, SWT.NONE);
		styleGroup.setText(DialogsMessages
				.getString("BorderPreferencePage.Style")); //$NON-NLS-1$
		data = new GridData(GridData.FILL_HORIZONTAL);
		styleGroup.setLayoutData(data);
		layout = new GridLayout(2, false);
		styleGroup.setLayout(layout);

		Label styleTopLabel = new Label(styleGroup, SWT.NONE);
		styleTopLabel.setText(DialogsMessages
				.getString("BorderPreferencePage.Top")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		styleTopLabel.setLayoutData(data);

		_styleTopCombo = new StyleCombo(styleGroup, SWT.NONE);
		_styleTopCombo.setItems(IStyleConstants.BORDER_STYLE);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_styleTopCombo.setLayoutData(data);
		_styleTopCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String style = _styleTopCombo.getText();
				_style.setBorderTopStyle(style);
			}
		});

		Label styleRightLabel = new Label(styleGroup, SWT.NONE);
		styleRightLabel.setText(DialogsMessages
				.getString("BorderPreferencePage.Right")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		styleRightLabel.setLayoutData(data);

		_styleRightCombo = new StyleCombo(styleGroup, SWT.NONE);
		_styleRightCombo.setItems(IStyleConstants.BORDER_STYLE);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_styleRightCombo.setLayoutData(data);
		_styleRightCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String style = _styleRightCombo.getText();
				_style.setBorderRightStyle(style);
			}
		});

		Label styleBottomLabel = new Label(styleGroup, SWT.NONE);
		styleBottomLabel.setText(DialogsMessages
				.getString("BorderPreferencePage.Bottom")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		styleBottomLabel.setLayoutData(data);

		_styleBottomCombo = new StyleCombo(styleGroup, SWT.NONE);
		_styleBottomCombo.setItems(IStyleConstants.BORDER_STYLE);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_styleBottomCombo.setLayoutData(data);
		_styleBottomCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String style = _styleBottomCombo.getText();
				_style.setBorderBottomStyle(style);
			}
		});

		Label styleLeftLabel = new Label(styleGroup, SWT.NONE);
		styleLeftLabel.setText(DialogsMessages
				.getString("BorderPreferencePage.Left")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		styleLeftLabel.setLayoutData(data);

		_styleLeftCombo = new StyleCombo(styleGroup, SWT.NONE);
		_styleLeftCombo.setItems(IStyleConstants.BORDER_STYLE);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_styleLeftCombo.setLayoutData(data);
		_styleLeftCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String style = _styleLeftCombo.getText();
				_style.setBorderLeftStyle(style);
			}
		});

		Group colorGroup = new Group(top, SWT.NONE);
		colorGroup.setText(DialogsMessages
				.getString("BorderPreferencePage.Color")); //$NON-NLS-1$
		data = new GridData(GridData.FILL_HORIZONTAL);
		colorGroup.setLayoutData(data);
		layout = new GridLayout(3, false);
		colorGroup.setLayout(layout);

		_colorTopField = new ColorButtonDialogField(SWT.BORDER, new ColorUtil());
		_colorTopField.setLabelText(DialogsMessages
				.getString("BorderPreferencePage.Top")); //$NON-NLS-1$

		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		_colorTopField.getLabelControl(null, colorGroup).setLayoutData(data);

		data = new GridData(GridData.FILL_HORIZONTAL);
		_colorTopField.getComboControl(null, colorGroup).setLayoutData(data);

		data = new GridData();
		_colorTopField.getChangeControl(null, colorGroup).setLayoutData(data);
		_colorTopField
				.setDialogFieldApplyListener(new IDialogFieldApplyListener() {
					public void dialogFieldApplied(DialogField field) {
						String color = _colorTopField.getText();
						_style.setBorderTopColor(color);
					}
				});

		_colorRightField = new ColorButtonDialogField(SWT.BORDER, new ColorUtil());
		_colorRightField.setLabelText(DialogsMessages
				.getString("BorderPreferencePage.Right")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		_colorRightField.getLabelControl(null, colorGroup).setLayoutData(data);

		data = new GridData(GridData.FILL_HORIZONTAL);
		_colorRightField.getComboControl(null, colorGroup).setLayoutData(data);

		data = new GridData();
		_colorRightField.getChangeControl(null, colorGroup).setLayoutData(data);
		_colorRightField
				.setDialogFieldApplyListener(new IDialogFieldApplyListener() {
					public void dialogFieldApplied(DialogField field) {
						String color = _colorRightField.getText();
						_style.setBorderRightColor(color);
					}
				});

		_colorBottomField = new ColorButtonDialogField(SWT.BORDER, new ColorUtil());
		_colorBottomField.setLabelText(DialogsMessages
				.getString("BorderPreferencePage.Bottom")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		_colorBottomField.getLabelControl(null, colorGroup).setLayoutData(data);

		data = new GridData(GridData.FILL_HORIZONTAL);
		_colorBottomField.getComboControl(null, colorGroup).setLayoutData(data);

		data = new GridData();
		_colorBottomField.getChangeControl(null, colorGroup)
				.setLayoutData(data);
		_colorBottomField
				.setDialogFieldApplyListener(new IDialogFieldApplyListener() {
					public void dialogFieldApplied(DialogField field) {
						String color = _colorBottomField.getText();
						_style.setBorderBottomColor(color);
					}
				});

		_colorLeftField = new ColorButtonDialogField(SWT.BORDER, new ColorUtil());
		_colorLeftField.setLabelText(DialogsMessages
				.getString("BorderPreferencePage.Left")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		_colorLeftField.getLabelControl(null, colorGroup).setLayoutData(data);

		data = new GridData(GridData.FILL_HORIZONTAL);
		_colorLeftField.getComboControl(null, colorGroup).setLayoutData(data);

		data = new GridData();
		_colorLeftField.getChangeControl(null, colorGroup).setLayoutData(data);
		_colorLeftField
				.setDialogFieldApplyListener(new IDialogFieldApplyListener() {
					public void dialogFieldApplied(DialogField field) {
						String color = _colorLeftField.getText();
						_style.setBorderLeftColor(color);
					}
				});

		Group widthGroup = new Group(top, SWT.NONE);
		widthGroup.setText(DialogsMessages
				.getString("BorderPreferencePage.Width")); //$NON-NLS-1$
		data = new GridData(GridData.FILL_HORIZONTAL);
		widthGroup.setLayoutData(data);
		layout = new GridLayout(3, false);
		widthGroup.setLayout(layout);

		Label widthTopLabel = new Label(widthGroup, SWT.NONE);
		widthTopLabel.setText(DialogsMessages
				.getString("BorderPreferencePage.Top")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		widthTopLabel.setLayoutData(data);

		_widthTopNumberCombo = new StyleCombo(widthGroup, SWT.NONE);
		_widthTopNumberCombo.setItems(IStyleConstants.BORDER_WIDTH);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_widthTopNumberCombo.setLayoutData(data);
		_widthTopNumberCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				_widthTopUnitCombo.setEnabled(true);
				try {
					Integer.valueOf(_widthTopNumberCombo.getText());
				} catch (NumberFormatException ex) {
					_widthTopUnitCombo.setEnabled(false);
				}

				String width = _widthTopNumberCombo.getText();
				if (_widthTopUnitCombo.isEnabled()) {
					width += _widthTopUnitCombo.getText();
				}

				_style.setBorderTopWidth(width);
			}
		});

		_widthTopUnitCombo = new StyleCombo(widthGroup, SWT.READ_ONLY);
		_widthTopUnitCombo.setItems(IStyleConstants.SIZE_UNIT);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_widthTopUnitCombo.setLayoutData(data);
		_widthTopUnitCombo.select(0);
		_widthTopUnitCombo.setEnabled(false);
		_widthTopUnitCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String width = _widthTopNumberCombo.getText();
				if (_widthTopUnitCombo.isEnabled()) {
					width += _widthTopUnitCombo.getText();
				}

				_style.setBorderTopWidth(width);
			}
		});

		Label widthRightLabel = new Label(widthGroup, SWT.NONE);
		widthRightLabel.setText(DialogsMessages
				.getString("BorderPreferencePage.Right")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		widthRightLabel.setLayoutData(data);

		_widthRightNumberCombo = new StyleCombo(widthGroup, SWT.NONE);
		_widthRightNumberCombo.setItems(IStyleConstants.BORDER_WIDTH);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_widthRightNumberCombo.setLayoutData(data);
		_widthRightNumberCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				_widthRightUnitCombo.setEnabled(true);
				try {
					Integer.valueOf(_widthRightNumberCombo.getText());
				} catch (NumberFormatException ex) {
					_widthRightUnitCombo.setEnabled(false);
				}

				String width = _widthRightNumberCombo.getText();
				if (_widthRightUnitCombo.isEnabled()) {
					width += _widthRightUnitCombo.getText();
				}

				_style.setBorderRightWidth(width);
			}
		});

		_widthRightUnitCombo = new StyleCombo(widthGroup, SWT.READ_ONLY);
		_widthRightUnitCombo.setItems(IStyleConstants.SIZE_UNIT);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_widthRightUnitCombo.setLayoutData(data);
		_widthRightUnitCombo.select(0);
		_widthRightUnitCombo.setEnabled(false);
		_widthRightUnitCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String width = _widthRightNumberCombo.getText();
				if (_widthRightUnitCombo.isEnabled()) {
					width += _widthRightUnitCombo.getText();
				}

				_style.setBorderRightWidth(width);
			}
		});

		Label widthBottomLabel = new Label(widthGroup, SWT.NONE);
		widthBottomLabel.setText(DialogsMessages
				.getString("BorderPreferencePage.Bottom")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		widthBottomLabel.setLayoutData(data);

		_widthBottomNumberCombo = new StyleCombo(widthGroup, SWT.NONE);
		_widthBottomNumberCombo.setItems(IStyleConstants.BORDER_WIDTH);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_widthBottomNumberCombo.setLayoutData(data);
		_widthBottomNumberCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				_widthBottomUnitCombo.setEnabled(true);
				try {
					Integer.valueOf(_widthBottomNumberCombo.getText());
				} catch (NumberFormatException ex) {
					_widthBottomUnitCombo.setEnabled(false);
				}

				String width = _widthBottomNumberCombo.getText();
				if (_widthBottomUnitCombo.isEnabled()) {
					width += _widthBottomUnitCombo.getText();
				}

				_style.setBorderBottomWidth(width);
			}
		});

		_widthBottomUnitCombo = new StyleCombo(widthGroup, SWT.READ_ONLY);
		_widthBottomUnitCombo.setItems(IStyleConstants.SIZE_UNIT);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_widthBottomUnitCombo.setLayoutData(data);
		_widthBottomUnitCombo.select(0);
		_widthBottomUnitCombo.setEnabled(false);
		_widthBottomUnitCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String width = _widthBottomNumberCombo.getText();
				if (_widthBottomUnitCombo.isEnabled()) {
					width += _widthBottomUnitCombo.getText();
				}

				_style.setBorderBottomWidth(width);
			}
		});

		Label widthLeftLabel = new Label(widthGroup, SWT.NONE);
		widthLeftLabel.setText(DialogsMessages
				.getString("BorderPreferencePage.Left")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		widthLeftLabel.setLayoutData(data);

		_widthLeftNumberCombo = new StyleCombo(widthGroup, SWT.NONE);
		_widthLeftNumberCombo.setItems(IStyleConstants.BORDER_WIDTH);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_widthLeftNumberCombo.setLayoutData(data);
		_widthLeftNumberCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				_widthLeftUnitCombo.setEnabled(true);
				try {
					Integer.valueOf(_widthLeftNumberCombo.getText());
				} catch (NumberFormatException ex) {
					_widthLeftUnitCombo.setEnabled(false);
				}

				String width = _widthLeftNumberCombo.getText();
				if (_widthLeftUnitCombo.isEnabled()) {
					width += _widthLeftUnitCombo.getText();
				}

				_style.setBorderLeftWidth(width);
			}
		});

		_widthLeftUnitCombo = new StyleCombo(widthGroup, SWT.READ_ONLY);
		_widthLeftUnitCombo.setItems(IStyleConstants.SIZE_UNIT);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_widthLeftUnitCombo.setLayoutData(data);
		_widthLeftUnitCombo.select(0);
		_widthLeftUnitCombo.setEnabled(false);
		_widthLeftUnitCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String width = _widthLeftNumberCombo.getText();
				if (_widthLeftUnitCombo.isEnabled()) {
					width += _widthLeftUnitCombo.getText();
				}

				_style.setBorderLeftWidth(width);
			}
		});

		initializeControls();

		return top;
	}

	private void initializeControls() {
		// border-style
		String style = _style.getBorderTopStyle();
		if (!isEmptyString(style)) {
			int index = _styleTopCombo.indexOf(style);
			if (index != -1) {
				_styleTopCombo.select(index);
			} else {
				_styleTopCombo.setText(style);
			}
		}
		style = _style.getBorderRightStyle();
		if (!isEmptyString(style)) {
			int index = _styleRightCombo.indexOf(style);
			if (index != -1) {
				_styleRightCombo.select(index);
			} else {
				_styleRightCombo.setText(style);
			}
		}
		style = _style.getBorderBottomStyle();
		if (!isEmptyString(style)) {
			int index = _styleBottomCombo.indexOf(style);
			if (index != -1) {
				_styleBottomCombo.select(index);
			} else {
				_styleBottomCombo.setText(style);
			}
		}
		style = _style.getBorderLeftStyle();
		if (!isEmptyString(style)) {
			int index = _styleLeftCombo.indexOf(style);
			if (index != -1) {
				_styleLeftCombo.select(index);
			} else {
				_styleLeftCombo.setText(style);
			}
		}

		// border-color
		String color = _style.getBorderTopColor();
		if (!isEmptyString(color)) {
			_colorTopField.setTextWithoutUpdate(color);
		}
		color = _style.getBorderRightColor();
		if (!isEmptyString(color)) {
			_colorRightField.setTextWithoutUpdate(color);
		}
		color = _style.getBorderBottomColor();
		if (!isEmptyString(color)) {
			_colorBottomField.setTextWithoutUpdate(color);
		}
		color = _style.getBorderLeftColor();
		if (!isEmptyString(color)) {
			_colorLeftField.setTextWithoutUpdate(color);
		}

		// border-width
		String width = _style.getBorderTopWidth();
		if (!isEmptyString(width)) {
			int index = _widthTopNumberCombo.indexOf(width);
			if (index != -1) {
				_widthTopNumberCombo.select(index);
			} else {
				_widthTopNumberCombo.setText(width);
			}
		}
		width = _style.getBorderRightWidth();
		if (!isEmptyString(width)) {
			int index = _widthRightNumberCombo.indexOf(width);
			if (index != -1) {
				_widthRightNumberCombo.select(index);
			} else {
				_widthRightNumberCombo.setText(width);
			}
		}
		width = _style.getBorderBottomWidth();
		if (!isEmptyString(width)) {
			int index = _widthBottomNumberCombo.indexOf(width);
			if (index != -1) {
				_widthBottomNumberCombo.select(index);
			} else {
				_widthBottomNumberCombo.setText(width);
			}
		}
		width = _style.getBorderLeftWidth();
		if (!isEmptyString(width)) {
			int index = _widthLeftNumberCombo.indexOf(width);
			if (index != -1) {
				_widthLeftNumberCombo.select(index);
			} else {
				_widthLeftNumberCombo.setText(width);
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
