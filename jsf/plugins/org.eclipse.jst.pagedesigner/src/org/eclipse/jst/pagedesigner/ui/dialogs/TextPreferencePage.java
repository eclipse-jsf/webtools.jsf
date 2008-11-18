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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wst.css.core.internal.util.declaration.CSSPropertyContext;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

/**
 * @author mengbo
 * @version 1.5
 */
public class TextPreferencePage extends PreferencePage {
	private CSSPropertyContext _style;

	private StyleCombo _fontFamilyCombo, _fontSizeNumberCombo,
			_fontSizeUnitCombo, _fontWeightCombo, _fontStyleCombo,
			_fontVariantCombo, _fontLineHeightNumberCombo,
			_fontLineHeightUnitCombo, _textTransformCombo;

	private ColorButtonDialogField _colorField;

	private Button _textDecorationUnderlineButton,
			_textDecorationOverlineButton, _textDecorationLineThroughButton,
			_textDecorationBlinkButton, _textDecorationNoneButton;

	/**
	 * @param element
	 * @param style
	 */
	public TextPreferencePage(IDOMElement element, CSSPropertyContext style) {
		super();
		_style = style;

		// Set the preference store for the preference page.
		// IPreferenceStore store =
		// JMTIntegrationPlugin.getDefault().getPreferenceStore();
		// setPreferenceStore(store);
		setTitle(DialogsMessages.getString("TextPreferencePage.Title")); //$NON-NLS-1$
	}

	/**
	 * @see org.eclipse.jface.preference.
	 *      PreferencePage#createContents(Composite)
	 */
	protected Control createContents(Composite parent) {
		GridLayout layout;
		GridData data;

		Composite top = new Composite(parent, SWT.NONE);
		layout = new GridLayout(5, false);
		data = new GridData(GridData.FILL_BOTH);
		top.setLayout(layout);
		top.setLayoutData(data);

		Label fontLabel = new Label(top, SWT.NONE);
		fontLabel.setText(DialogsMessages.getString("TextPreferencePage.Font")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		fontLabel.setLayoutData(data);

		_fontFamilyCombo = new StyleCombo(top, SWT.NONE);
		_fontFamilyCombo.setItems(IStyleConstants.FONT_FAMILY);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 4;
		_fontFamilyCombo.setLayoutData(data);
		_fontFamilyCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				_style.setFontFamily(_fontFamilyCombo.getText());
			}
		});

		Label sizeLabel = new Label(top, SWT.NONE);
		sizeLabel.setText(DialogsMessages.getString("TextPreferencePage.Size")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		sizeLabel.setLayoutData(data);

		_fontSizeNumberCombo = new StyleCombo(top, SWT.NONE);
		_fontSizeNumberCombo.setItems(IStyleConstants.FONT_SIZE_NUMBER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_fontSizeNumberCombo.setLayoutData(data);
		_fontSizeNumberCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				_fontSizeUnitCombo.setEnabled(true);
				try {
					Integer.valueOf(_fontSizeNumberCombo.getText());
				} catch (NumberFormatException ex) {
					_fontSizeUnitCombo.setEnabled(false);
				}

				String size = _fontSizeNumberCombo.getText();
				if (_fontSizeUnitCombo.isEnabled()) {
					size += _fontSizeUnitCombo.getText();
				}

				_style.setFontSize(size);
			}
		});

		_fontSizeUnitCombo = new StyleCombo(top, SWT.READ_ONLY);
		_fontSizeUnitCombo.setItems(IStyleConstants.SIZE_UNIT);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_fontSizeUnitCombo.setLayoutData(data);
		_fontSizeUnitCombo.select(0);
		_fontSizeUnitCombo.setEnabled(false);
		_fontSizeUnitCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String size = _fontSizeNumberCombo.getText();
				if (_fontSizeUnitCombo.isEnabled()) {
					size += _fontSizeUnitCombo.getText();
				}

				_style.setFontSize(size);
			}
		});

		Label weightLabel = new Label(top, SWT.NONE);
		weightLabel.setText(DialogsMessages
				.getString("TextPreferencePage.Weight")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		weightLabel.setLayoutData(data);

		_fontWeightCombo = new StyleCombo(top, SWT.NONE);
		_fontWeightCombo.setItems(IStyleConstants.FONT_WEIGHT);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_fontWeightCombo.setLayoutData(data);
		_fontWeightCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String weight = _fontWeightCombo.getText();
				_style.setFontWeight(weight);
			}
		});

		Label stylesLabel = new Label(top, SWT.NONE);
		stylesLabel.setText(DialogsMessages
				.getString("TextPreferencePage.Style")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		stylesLabel.setLayoutData(data);

		_fontStyleCombo = new StyleCombo(top, SWT.NONE);
		_fontStyleCombo.setItems(IStyleConstants.FONT_STYLE);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_fontStyleCombo.setLayoutData(data);
		_fontStyleCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String style = _fontStyleCombo.getText();
				_style.setFontStyle(style);
			}
		});

		new Label(top, SWT.NONE);

		Label variantLabel = new Label(top, SWT.NONE);
		variantLabel.setText(DialogsMessages
				.getString("TextPreferencePage.Variant")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		variantLabel.setLayoutData(data);

		_fontVariantCombo = new StyleCombo(top, SWT.NONE);
		_fontVariantCombo.setItems(IStyleConstants.FONT_VARIANT);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_fontVariantCombo.setLayoutData(data);
		_fontVariantCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String variant = _fontVariantCombo.getText();
				_style.setFontVariant(variant);
			}
		});

		Label lineHeightLabel = new Label(top, SWT.NONE);
		lineHeightLabel.setText(DialogsMessages
				.getString("TextPreferencePage.LineHeight")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lineHeightLabel.setLayoutData(data);

		_fontLineHeightNumberCombo = new StyleCombo(top, SWT.NONE);
		_fontLineHeightNumberCombo.setItems(IStyleConstants.NORMAL);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_fontLineHeightNumberCombo.setLayoutData(data);
		_fontLineHeightNumberCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				_fontLineHeightUnitCombo.setEnabled(true);
				try {
					Integer.valueOf(_fontLineHeightNumberCombo.getText());
				} catch (NumberFormatException ex) {
					_fontLineHeightUnitCombo.setEnabled(false);
				}

				String height = _fontLineHeightNumberCombo.getText();
				if (_fontLineHeightUnitCombo.isEnabled()) {
					height += _fontLineHeightUnitCombo.getText();
				}

				_style.setLineHeight(height);
			}
		});

		_fontLineHeightUnitCombo = new StyleCombo(top, SWT.READ_ONLY);
		_fontLineHeightUnitCombo.setItems(IStyleConstants.SIZE_UNIT);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_fontLineHeightUnitCombo.setLayoutData(data);
		_fontLineHeightUnitCombo.select(0);
		_fontLineHeightUnitCombo.setEnabled(false);
		_fontLineHeightUnitCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String height = _fontLineHeightNumberCombo.getText();
				if (_fontLineHeightUnitCombo.isEnabled()) {
					height += _fontLineHeightUnitCombo.getText();
				}

				_style.setLineHeight(height);
			}
		});

		Label caseLabel = new Label(top, SWT.NONE);
		caseLabel.setText(DialogsMessages.getString("TextPreferencePage.Case")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		caseLabel.setLayoutData(data);

		_textTransformCombo = new StyleCombo(top, SWT.NONE);
		_textTransformCombo.setItems(IStyleConstants.FONT_TEXTTRANSFORM);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_textTransformCombo.setLayoutData(data);
		_textTransformCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String textTransform = _textTransformCombo.getText();
				_style.setTextTransform(textTransform);
			}
		});

		Label decorationLabel = new Label(top, SWT.NONE);
		decorationLabel.setText(DialogsMessages
				.getString("TextPreferencePage.Decoration")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		decorationLabel.setLayoutData(data);

		Composite decorationComposite = new Composite(top, SWT.NONE);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 4;
		decorationComposite.setLayoutData(data);
		layout = new GridLayout(5, true);
		layout.marginWidth = 0;
		decorationComposite.setLayout(layout);

		_textDecorationUnderlineButton = new Button(decorationComposite,
				SWT.CHECK);
		_textDecorationUnderlineButton
				.setText(IStyleConstants.TEXT_DECORATION[0]);
		_textDecorationUnderlineButton
				.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						if (_textDecorationUnderlineButton.getSelection()) {
							_textDecorationNoneButton.setSelection(false);
						}
						_style.setTextDecoration(getTextDecoration());
					}
				});

		_textDecorationOverlineButton = new Button(decorationComposite,
				SWT.CHECK);
		_textDecorationOverlineButton
				.setText(IStyleConstants.TEXT_DECORATION[1]);
		_textDecorationOverlineButton
				.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						if (_textDecorationOverlineButton.getSelection()) {
							_textDecorationNoneButton.setSelection(false);
						}
						_style.setTextDecoration(getTextDecoration());
					}
				});

		_textDecorationLineThroughButton = new Button(decorationComposite,
				SWT.CHECK);
		_textDecorationLineThroughButton
				.setText(IStyleConstants.TEXT_DECORATION[2]);
		_textDecorationLineThroughButton
				.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						if (_textDecorationLineThroughButton.getSelection()) {
							_textDecorationNoneButton.setSelection(false);
						}
						_style.setTextDecoration(getTextDecoration());
					}
				});

		_textDecorationBlinkButton = new Button(decorationComposite, SWT.CHECK);
		_textDecorationBlinkButton.setText(IStyleConstants.TEXT_DECORATION[3]);
		_textDecorationBlinkButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (_textDecorationBlinkButton.getSelection()) {
					_textDecorationNoneButton.setSelection(false);
				}
				_style.setTextDecoration(getTextDecoration());
			}
		});

		_textDecorationNoneButton = new Button(decorationComposite, SWT.CHECK);
		_textDecorationNoneButton.setText(IStyleConstants.TEXT_DECORATION[4]);
		_textDecorationNoneButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (_textDecorationNoneButton.getSelection()) {
					_textDecorationUnderlineButton.setSelection(false);
					_textDecorationOverlineButton.setSelection(false);
					_textDecorationLineThroughButton.setSelection(false);
					_textDecorationBlinkButton.setSelection(false);
				}
				_style.setTextDecoration(getTextDecoration());
			}
		});

		_colorField = new ColorButtonDialogField(SWT.BORDER, new ColorUtil());
		_colorField.setLabelText(DialogsMessages
				.getString("TextPreferencePage.Color")); //$NON-NLS-1$

		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		_colorField.getLabelControl(null, top).setLayoutData(data);

		data = new GridData();
		data.horizontalAlignment = GridData.FILL;
		data.grabExcessHorizontalSpace = false;
		_colorField.getComboControl(null, top).setLayoutData(data);

		data = new GridData();
		_colorField.getChangeControl(null, top).setLayoutData(data);
		_colorField
				.setDialogFieldApplyListener(new IDialogFieldApplyListener() {
					public void dialogFieldApplied(DialogField field) {
						String color = _colorField.getText();
						_style.setColor(color);
					}
				});

		initializeControls();

		return top;
	}

	private void initializeControls() {
		// font-family
		String fontFamily = _style.getFontFamily();
		if (!isEmptyString(fontFamily)) {
			int index = _fontFamilyCombo.indexOf(fontFamily);
			if (index != -1) {
				_fontFamilyCombo.select(index);
			} else {
				_fontFamilyCombo.setText(fontFamily);
			}
		}

		// font-size
		String fontSize = _style.getFontSize();
		if (!isEmptyString(fontSize)) {
			int index = _fontSizeNumberCombo.indexOf(fontSize);
			if (index != -1) {
				_fontSizeNumberCombo.select(index);
			} else {
				_fontSizeNumberCombo.setText(fontSize);
			}
		}

		// font-weight
		String fontWeight = _style.getFontWeight();
		if (!isEmptyString(fontWeight)) {
			int index = _fontWeightCombo.indexOf(fontWeight);
			if (index != -1) {
				_fontWeightCombo.select(index);
			} else {
				_fontWeightCombo.setText(fontWeight);
			}
		}

		// font-style
		String fontStyle = _style.getFontStyle();
		if (!isEmptyString(fontStyle)) {
			int index = _fontStyleCombo.indexOf(fontStyle);
			if (index != -1) {
				_fontStyleCombo.select(index);
			} else {
				_fontStyleCombo.setText(fontStyle);
			}
		}

		// font-variant
		String fontVariant = _style.getFontVariant();
		if (!isEmptyString(fontVariant)) {
			int index = _fontVariantCombo.indexOf(fontVariant);
			if (index != -1) {
				_fontVariantCombo.select(index);
			} else {
				_fontVariantCombo.setText(fontVariant);
			}
		}

		// line-height
		String lineHeight = _style.getLineHeight();
		if (!isEmptyString(lineHeight)) {
			int index = _fontLineHeightNumberCombo.indexOf(lineHeight);
			if (index != -1) {
				_fontLineHeightNumberCombo.select(index);
			} else {
				_fontLineHeightNumberCombo.setText(lineHeight);
			}
		}

		// text-transform
		String textTransform = _style.getTextTransform();
		if (!isEmptyString(textTransform)) {
			int index = _textTransformCombo.indexOf(textTransform);
			if (index != -1) {
				_textTransformCombo.select(index);
			} else {
				_textTransformCombo.setText(textTransform);
			}
		}

		// text-decoration
		String textDecoration = _style.getTextDecoration();
		if (!isEmptyString(textDecoration)) {
			int index = textDecoration
					.indexOf(IStyleConstants.TEXT_DECORATION[0]);
			if (index != -1) {
				_textDecorationUnderlineButton.setSelection(true);
			} else {
				_textDecorationUnderlineButton.setSelection(false);
			}

			index = textDecoration.indexOf(IStyleConstants.TEXT_DECORATION[1]);
			if (index != -1) {
				_textDecorationOverlineButton.setSelection(true);
			} else {
				_textDecorationOverlineButton.setSelection(false);
			}

			index = textDecoration.indexOf(IStyleConstants.TEXT_DECORATION[2]);
			if (index != -1) {
				_textDecorationLineThroughButton.setSelection(true);
			} else {
				_textDecorationLineThroughButton.setSelection(false);
			}

			index = textDecoration.indexOf(IStyleConstants.TEXT_DECORATION[3]);
			if (index != -1) {
				_textDecorationBlinkButton.setSelection(true);
			} else {
				_textDecorationBlinkButton.setSelection(false);
			}

			index = textDecoration.indexOf(IStyleConstants.TEXT_DECORATION[4]);
			if (index != -1) {
				_textDecorationNoneButton.setSelection(true);
			} else {
				_textDecorationNoneButton.setSelection(false);
			}
		}

		// color
		String color = _style.getColor();
		if (!isEmptyString(color)) {
			_colorField.setTextWithoutUpdate(color);
		}
	}

	private String getTextDecoration() {
		StringBuffer textDecoration = new StringBuffer();
		if (_textDecorationUnderlineButton.getSelection()) {
			textDecoration.append(_textDecorationUnderlineButton.getText())
					.append(' ');
		}
		if (_textDecorationOverlineButton.getSelection()) {
			textDecoration.append(_textDecorationOverlineButton.getText())
					.append(' ');
		}
		if (_textDecorationLineThroughButton.getSelection()) {
			textDecoration.append(_textDecorationLineThroughButton.getText())
					.append(' ');
		}
		if (_textDecorationBlinkButton.getSelection()) {
			textDecoration.append(_textDecorationBlinkButton.getText()).append(
					' ');
		}
		if (_textDecorationNoneButton.getSelection()) {
			textDecoration.append(_textDecorationNoneButton.getText()).append(
					' ');
		}

		return textDecoration.toString().trim();
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
