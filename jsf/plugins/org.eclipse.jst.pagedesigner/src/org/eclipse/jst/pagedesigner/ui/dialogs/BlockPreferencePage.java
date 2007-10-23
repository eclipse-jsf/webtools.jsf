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
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.css.core.internal.util.declaration.CSSPropertyContext;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

/**
 * @author mengbo
 * @version 1.5
 */
public class BlockPreferencePage extends PreferencePage {
	private CSSPropertyContext _style;

	private StyleCombo _wordSpacingNumberCombo, _wordSpacingUnitCombo,
			_letterSpacingNumberCombo, _letterSpacingUnitCombo,
			_verticalAlignNumberCombo, _verticalAlignUnitCombo,
			_textAlignCombo, _textIndentUnitCombo, _whiteSpaceCombo,
			_displayCombo;

	private Text _textIndentText;

	/**
	 * @param element
	 * @param style
	 */
	public BlockPreferencePage(IDOMElement element, CSSPropertyContext style) {
		super();
		_style = style;

		setTitle(DialogsMessages.getString("BlockPreferencePage.Title")); //$NON-NLS-1$
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

		Label wordSpacingLabel = new Label(top, SWT.NONE);
		wordSpacingLabel.setText(DialogsMessages
				.getString("BlockPreferencePage.WordSpacing")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		wordSpacingLabel.setLayoutData(data);

		_wordSpacingNumberCombo = new StyleCombo(top, SWT.NONE);
		_wordSpacingNumberCombo.setItems(IStyleConstants.NORMAL);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_wordSpacingNumberCombo.setLayoutData(data);
		_wordSpacingNumberCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				_wordSpacingUnitCombo.setEnabled(true);
				try {
					Integer.valueOf(_wordSpacingNumberCombo.getText());
				} catch (NumberFormatException ex) {
					_wordSpacingUnitCombo.setEnabled(false);
				}

				String spacing = _wordSpacingNumberCombo.getText();
				if (_wordSpacingUnitCombo.isEnabled()) {
					spacing += _wordSpacingUnitCombo.getText();
				}

				_style.setWordSpacing(spacing);
			}
		});

		_wordSpacingUnitCombo = new StyleCombo(top, SWT.READ_ONLY);
		_wordSpacingUnitCombo.setItems(IStyleConstants.SIZE_UNIT);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_wordSpacingUnitCombo.setLayoutData(data);
		_wordSpacingUnitCombo.select(0);
		_wordSpacingUnitCombo.setEnabled(false);
		_wordSpacingUnitCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String spacing = _wordSpacingNumberCombo.getText();
				if (_wordSpacingUnitCombo.isEnabled()) {
					spacing += _wordSpacingUnitCombo.getText();
				}

				_style.setWordSpacing(spacing);

			}
		});

		Label letterSpacingLabel = new Label(top, SWT.NONE);
		letterSpacingLabel.setText(DialogsMessages
				.getString("BlockPreferencePage.LetterSpacing")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		letterSpacingLabel.setLayoutData(data);

		_letterSpacingNumberCombo = new StyleCombo(top, SWT.NONE);
		_letterSpacingNumberCombo.setItems(IStyleConstants.NORMAL);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_letterSpacingNumberCombo.setLayoutData(data);
		_letterSpacingNumberCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				_letterSpacingUnitCombo.setEnabled(true);
				try {
					Integer.valueOf(_letterSpacingNumberCombo.getText());
				} catch (NumberFormatException ex) {
					_letterSpacingUnitCombo.setEnabled(false);
				}

				String spacing = _letterSpacingNumberCombo.getText();
				if (_letterSpacingUnitCombo.isEnabled()) {
					spacing += _letterSpacingUnitCombo.getText();
				}

				_style.setLetterSpacing(spacing);
			}
		});

		_letterSpacingUnitCombo = new StyleCombo(top, SWT.READ_ONLY);
		_letterSpacingUnitCombo.setItems(IStyleConstants.SIZE_UNIT);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_letterSpacingUnitCombo.setLayoutData(data);
		_letterSpacingUnitCombo.select(0);
		_letterSpacingUnitCombo.setEnabled(false);
		_letterSpacingUnitCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String spacing = _letterSpacingNumberCombo.getText();
				if (_letterSpacingUnitCombo.isEnabled()) {
					spacing += _letterSpacingUnitCombo.getText();
				}

				_style.setLetterSpacing(spacing);
			}
		});

		Label verticalAlignLabel = new Label(top, SWT.NONE);
		verticalAlignLabel.setText(DialogsMessages
				.getString("BlockPreferencePage.VerticalAlign")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		verticalAlignLabel.setLayoutData(data);

		_verticalAlignNumberCombo = new StyleCombo(top, SWT.NONE);
		_verticalAlignNumberCombo.setItems(IStyleConstants.VERTICAL_ALIGN);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_verticalAlignNumberCombo.setLayoutData(data);
		_verticalAlignNumberCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				_verticalAlignUnitCombo.setEnabled(true);
				try {
					Integer.valueOf(_verticalAlignNumberCombo.getText());
				} catch (NumberFormatException ex) {
					_verticalAlignUnitCombo.setEnabled(false);
				}

				String align = _verticalAlignNumberCombo.getText();
				if (_verticalAlignUnitCombo.isEnabled()) {
					align += _verticalAlignUnitCombo.getText();
				}

				_style.setVerticalAlign(align);
			}
		});

		_verticalAlignUnitCombo = new StyleCombo(top, SWT.READ_ONLY);
		_verticalAlignUnitCombo.setItems(IStyleConstants.PERCENT);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_verticalAlignUnitCombo.setLayoutData(data);
		_verticalAlignUnitCombo.select(0);
		_verticalAlignUnitCombo.setEnabled(false);
		_verticalAlignUnitCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String align = _verticalAlignNumberCombo.getText();
				if (_verticalAlignUnitCombo.isEnabled()) {
					align += _verticalAlignUnitCombo.getText();
				}

				_style.setVerticalAlign(align);
			}
		});

		Label textAlignLabel = new Label(top, SWT.NONE);
		textAlignLabel.setText(DialogsMessages
				.getString("BlockPreferencePage.TextAlign")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		textAlignLabel.setLayoutData(data);

		_textAlignCombo = new StyleCombo(top, SWT.NONE);
		_textAlignCombo.setItems(IStyleConstants.TEXT_ALIGN);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_textAlignCombo.setLayoutData(data);
		_textAlignCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String align = _textAlignCombo.getText();

				_style.setTextAlign(align);
			}
		});

		new Label(top, SWT.NONE);

		Label textIndentLabel = new Label(top, SWT.NONE);
		textIndentLabel.setText(DialogsMessages
				.getString("BlockPreferencePage.TextIndent")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		textIndentLabel.setLayoutData(data);

		_textIndentText = new Text(top, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_textIndentText.setLayoutData(data);
		_textIndentText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				_textIndentUnitCombo.setEnabled(true);
				try {
					Integer.valueOf(_textIndentText.getText());
				} catch (NumberFormatException ex) {
					_textIndentUnitCombo.setEnabled(false);
				}

				String indent = _textIndentText.getText();
				if (_textIndentUnitCombo.isEnabled()) {
					indent += _textIndentUnitCombo.getText();
				}

				_style.setTextIndent(indent);
			}
		});

		_textIndentUnitCombo = new StyleCombo(top, SWT.READ_ONLY);
		_textIndentUnitCombo.setItems(IStyleConstants.SIZE_UNIT);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_textIndentUnitCombo.setLayoutData(data);
		_textIndentUnitCombo.select(0);
		_textIndentUnitCombo.setEnabled(false);
		_textIndentUnitCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String indent = _textIndentText.getText();
				if (_textIndentUnitCombo.isEnabled()) {
					indent += _textIndentUnitCombo.getText();
				}

				_style.setTextIndent(indent);
			}
		});

		Label whiteSpaceLabel = new Label(top, SWT.NONE);
		whiteSpaceLabel.setText(DialogsMessages
				.getString("BlockPreferencePage.WhiteSpace")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		whiteSpaceLabel.setLayoutData(data);

		_whiteSpaceCombo = new StyleCombo(top, SWT.NONE);
		_whiteSpaceCombo.setItems(IStyleConstants.WHITE_SPACE);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_whiteSpaceCombo.setLayoutData(data);
		_whiteSpaceCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String space = _whiteSpaceCombo.getText();

				_style.setWhiteSpace(space);
			}
		});

		new Label(top, SWT.NONE);

		Label displayLabel = new Label(top, SWT.NONE);
		displayLabel.setText(DialogsMessages
				.getString("BlockPreferencePage.Display")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		displayLabel.setLayoutData(data);

		_displayCombo = new StyleCombo(top, SWT.NONE);
		_displayCombo.setItems(IStyleConstants.DISPLAY);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_displayCombo.setLayoutData(data);
		_displayCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String display = _displayCombo.getText();

				_style.setDisplay(display);
			}
		});

		initializeControls();

		return top;
	}

	private void initializeControls() {
		// word-spacing
		String spacing = _style.getWordSpacing();
		if (!isEmptyString(spacing)) {
			int index = _wordSpacingNumberCombo.indexOf(spacing);
			if (index != -1) {
				_wordSpacingNumberCombo.select(index);
			} else {
				_wordSpacingNumberCombo.setText(spacing);
			}
		}

		// letter-spacing
		spacing = _style.getLetterSpacing();
		if (!isEmptyString(spacing)) {
			int index = _letterSpacingNumberCombo.indexOf(spacing);
			if (index != -1) {
				_letterSpacingNumberCombo.select(index);
			} else {
				_letterSpacingNumberCombo.setText(spacing);
			}
		}

		// veritcal-align
		String align = _style.getVerticalAlign();
		if (!isEmptyString(align)) {
			int index = _verticalAlignNumberCombo.indexOf(align);
			if (index != -1) {
				_verticalAlignNumberCombo.select(index);
			} else {
				_verticalAlignNumberCombo.setText(align);
			}
		}

		// text-align
		align = _style.getTextAlign();
		if (!isEmptyString(align)) {
			int index = _textAlignCombo.indexOf(align);
			if (index != -1) {
				_textAlignCombo.select(index);
			} else {
				_textAlignCombo.setText(align);
			}
		}

		// text-indent
		String indent = _style.getTextIndent();
		if (!isEmptyString(indent)) {
			_textIndentText.setText(indent);
		}

		// white-space
		String space = _style.getWhiteSpace();
		if (!isEmptyString(space)) {
			int index = _whiteSpaceCombo.indexOf(space);
			if (index != -1) {
				_whiteSpaceCombo.select(index);
			} else {
				_whiteSpaceCombo.setText(space);
			}
		}

		// display
		String display = _style.getDisplay();
		if (!isEmptyString(display)) {
			int index = _displayCombo.indexOf(display);
			if (index != -1) {
				_displayCombo.select(index);
			} else {
				_displayCombo.setText(display);
			}
		}
	}

	public void setVisible(boolean visible) {
		super.setVisible(visible);

		getApplyButton().setVisible(false);
		getDefaultsButton().setVisible(false);
	}

    // TODO: seeing this method repeated in on other preference pages..
	private boolean isEmptyString(String str) {
		if (str == null || str.length() == 0) {
			return true;
		}
        return false;
	}
}
