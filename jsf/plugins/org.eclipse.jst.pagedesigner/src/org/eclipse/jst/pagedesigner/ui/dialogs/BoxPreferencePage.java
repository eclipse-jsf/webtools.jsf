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
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.css.core.internal.util.declaration.CSSPropertyContext;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

/**
 * @author mengbo
 * @version 1.5
 */
public class BoxPreferencePage extends PreferencePage {
	private CSSPropertyContext _style;

	private StyleCombo _widthNumberCombo, _widthUnitCombo, _heightNumberCombo,
			_heightUnitCombo, _clearCombo;

	private Text _paddingTopNumberText, _paddingRightNumberText,
			_paddingBottomNumberText, _paddingLeftNumberText;

	private StyleCombo _paddingTopUnitCombo, _paddingRightUnitCombo,
			_paddingBottomUnitCombo, _paddingLeftUnitCombo;

	private StyleCombo _marginTopNumberCombo, _marginRightNumberCombo,
			_marginBottomNumberCombo, _marginLeftNumberCombo;

	private StyleCombo _marginTopUnitCombo, _marginRightUnitCombo,
			_marginBottomUnitCombo, _marginLeftUnitCombo;

	/**
	 * @param element
	 * @param style
	 */
	public BoxPreferencePage(IDOMElement element, CSSPropertyContext style) {
		super();
		_style = style;

		setTitle(DialogsMessages.getString("BoxPreferencePage.Title")); //$NON-NLS-1$
	}

	/**
	 * @see org.eclipse.jface.preference.
	 *      PreferencePage#createContents(Composite)
	 */
	protected Control createContents(Composite parent) {
		GridLayout layout;
		GridData data;

		Composite top = new Composite(parent, SWT.NONE);
		layout = new GridLayout(6, false);
		data = new GridData(GridData.FILL_BOTH);
		top.setLayout(layout);
		top.setLayoutData(data);

		Label widthLabel = new Label(top, SWT.NONE);
		widthLabel
				.setText(DialogsMessages.getString("BoxPreferencePage.Width")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		widthLabel.setLayoutData(data);

		_widthNumberCombo = new StyleCombo(top, SWT.NONE);
		_widthNumberCombo.setItems(IStyleConstants.AUTO);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_widthNumberCombo.setLayoutData(data);
		_widthNumberCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				_widthUnitCombo.setEnabled(true);
				try {
					Integer.valueOf(_widthNumberCombo.getText());
				} catch (NumberFormatException ex) {
					_widthUnitCombo.setEnabled(false);
				}

				String width = _widthNumberCombo.getText();
				if (_widthUnitCombo.isEnabled()) {
					width += _widthUnitCombo.getText();
				}

				_style.setWidth(width);
			}
		});

		_widthUnitCombo = new StyleCombo(top, SWT.READ_ONLY);
		_widthUnitCombo.setItems(IStyleConstants.SIZE_UNIT);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_widthUnitCombo.setLayoutData(data);
		_widthUnitCombo.select(0);
		_widthUnitCombo.setEnabled(false);
		_widthUnitCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String width = _widthNumberCombo.getText();
				if (_widthUnitCombo.isEnabled()) {
					width += _widthUnitCombo.getText();
				}

				_style.setWidth(width);
			}
		});

		Label clearLabel = new Label(top, SWT.NONE);
		clearLabel
				.setText(DialogsMessages.getString("BoxPreferencePage.Clear")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		clearLabel.setLayoutData(data);

		_clearCombo = new StyleCombo(top, SWT.NONE);
		_clearCombo.setItems(IStyleConstants.CLEAR);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_clearCombo.setLayoutData(data);
		_clearCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String clear = _clearCombo.getText();
				_style.setClear(clear);
			}
		});

		new Label(top, SWT.NONE);

		Label heightLabel = new Label(top, SWT.NONE);
		heightLabel.setText(DialogsMessages
				.getString("BoxPreferencePage.Height")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		heightLabel.setLayoutData(data);

		_heightNumberCombo = new StyleCombo(top, SWT.NONE);
		_heightNumberCombo.setItems(IStyleConstants.AUTO);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_heightNumberCombo.setLayoutData(data);
		_heightNumberCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				_heightUnitCombo.setEnabled(true);
				try {
					Integer.valueOf(_heightNumberCombo.getText());
				} catch (NumberFormatException ex) {
					_heightUnitCombo.setEnabled(false);
				}

				String height = _heightNumberCombo.getText();
				if (_heightUnitCombo.isEnabled()) {
					height += _heightUnitCombo.getText();
				}

				_style.setHeight(height);
			}
		});

		_heightUnitCombo = new StyleCombo(top, SWT.READ_ONLY);
		_heightUnitCombo.setItems(IStyleConstants.SIZE_UNIT);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_heightUnitCombo.setLayoutData(data);
		_heightUnitCombo.select(0);
		_heightUnitCombo.setEnabled(false);
		_heightUnitCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String height = _heightNumberCombo.getText();
				if (_heightUnitCombo.isEnabled()) {
					height += _heightNumberCombo.getText();
				}

				_style.setHeight(height);
			}
		});

		new Label(top, SWT.NONE);
		new Label(top, SWT.NONE);
		new Label(top, SWT.NONE);

		Group paddingGroup = new Group(top, SWT.NONE);
		paddingGroup.setText(DialogsMessages
				.getString("BoxPreferencePage.Padding")); //$NON-NLS-1$
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 3;
		data.verticalSpan = 4;
		paddingGroup.setLayoutData(data);
		layout = new GridLayout(3, false);
		paddingGroup.setLayout(layout);

		Label paddingTopLabel = new Label(paddingGroup, SWT.NONE);
		paddingTopLabel.setText(DialogsMessages
				.getString("BoxPreferencePage.Top")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		paddingTopLabel.setLayoutData(data);

		_paddingTopNumberText = new Text(paddingGroup, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_paddingTopNumberText.setLayoutData(data);
		_paddingTopNumberText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				_paddingTopUnitCombo.setEnabled(true);
				try {
					Integer.valueOf(_paddingTopNumberText.getText());
				} catch (NumberFormatException ex) {
					_paddingTopUnitCombo.setEnabled(false);
				}

				String padding = _paddingTopNumberText.getText();
				if (_paddingTopUnitCombo.isEnabled()) {
					padding += _paddingTopUnitCombo.getText();
				}

				_style.setPaddingTop(padding);
			}
		});

		_paddingTopUnitCombo = new StyleCombo(paddingGroup, SWT.READ_ONLY);
		_paddingTopUnitCombo.setItems(IStyleConstants.SIZE_UNIT);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_paddingTopUnitCombo.setLayoutData(data);
		_paddingTopUnitCombo.select(0);
		_paddingTopUnitCombo.setEnabled(false);
		_paddingTopUnitCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String padding = _paddingTopNumberText.getText();
				if (_paddingTopUnitCombo.isEnabled()) {
					padding += _paddingTopUnitCombo.getText();
				}

				_style.setPaddingTop(padding);
			}
		});

		Label paddingRightLabel = new Label(paddingGroup, SWT.NONE);
		paddingRightLabel.setText(DialogsMessages
				.getString("BoxPreferencePage.Right")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		paddingRightLabel.setLayoutData(data);

		_paddingRightNumberText = new Text(paddingGroup, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_paddingRightNumberText.setLayoutData(data);
		_paddingRightNumberText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				_paddingRightUnitCombo.setEnabled(true);
				try {
					Integer.valueOf(_paddingRightNumberText.getText());
				} catch (NumberFormatException ex) {
					_paddingRightUnitCombo.setEnabled(false);
				}

				String padding = _paddingRightNumberText.getText();
				if (_paddingRightUnitCombo.isEnabled()) {
					padding += _paddingRightUnitCombo.getText();
				}

				_style.setPaddingRight(padding);
			}
		});

		_paddingRightUnitCombo = new StyleCombo(paddingGroup, SWT.READ_ONLY);
		_paddingRightUnitCombo.setItems(IStyleConstants.SIZE_UNIT);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_paddingRightUnitCombo.setLayoutData(data);
		_paddingRightUnitCombo.select(0);
		_paddingRightUnitCombo.setEnabled(false);
		_paddingRightUnitCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String padding = _paddingRightNumberText.getText();
				if (_paddingRightUnitCombo.isEnabled()) {
					padding += _paddingRightUnitCombo.getText();
				}

				_style.setPaddingRight(padding);
			}
		});

		Label paddingBottomLabel = new Label(paddingGroup, SWT.NONE);
		paddingBottomLabel.setText(DialogsMessages
				.getString("BoxPreferencePage.Bottom")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		paddingBottomLabel.setLayoutData(data);

		_paddingBottomNumberText = new Text(paddingGroup, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_paddingBottomNumberText.setLayoutData(data);
		_paddingBottomNumberText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				_paddingBottomUnitCombo.setEnabled(true);
				try {
					Integer.valueOf(_paddingBottomNumberText.getText());
				} catch (NumberFormatException ex) {
					_paddingBottomUnitCombo.setEnabled(false);
				}

				String padding = _paddingBottomNumberText.getText();
				if (_paddingBottomUnitCombo.isEnabled()) {
					padding += _paddingBottomUnitCombo.getText();
				}

				_style.setPaddingBottom(padding);
			}
		});

		_paddingBottomUnitCombo = new StyleCombo(paddingGroup, SWT.READ_ONLY);
		_paddingBottomUnitCombo.setItems(IStyleConstants.SIZE_UNIT);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_paddingBottomUnitCombo.setLayoutData(data);
		_paddingBottomUnitCombo.select(0);
		_paddingBottomUnitCombo.setEnabled(false);
		_paddingBottomUnitCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String padding = _paddingBottomNumberText.getText();
				if (_paddingBottomUnitCombo.isEnabled()) {
					padding += _paddingBottomUnitCombo.getText();
				}

				_style.setPaddingBottom(padding);
			}
		});

		Label paddingLeftLabel = new Label(paddingGroup, SWT.NONE);
		paddingLeftLabel.setText(DialogsMessages
				.getString("BoxPreferencePage.Left")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		paddingLeftLabel.setLayoutData(data);

		_paddingLeftNumberText = new Text(paddingGroup, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_paddingLeftNumberText.setLayoutData(data);
		_paddingLeftNumberText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				_paddingLeftUnitCombo.setEnabled(true);
				try {
					Integer.valueOf(_paddingLeftNumberText.getText());
				} catch (NumberFormatException ex) {
					_paddingLeftUnitCombo.setEnabled(false);
				}

				String top1 = _paddingLeftNumberText.getText();
				if (_paddingLeftUnitCombo.isEnabled()) {
					top1 += _paddingLeftUnitCombo.getText();
				}

				_style.setPaddingLeft(top1);
			}
		});

		_paddingLeftUnitCombo = new StyleCombo(paddingGroup, SWT.READ_ONLY);
		_paddingLeftUnitCombo.setItems(IStyleConstants.SIZE_UNIT);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_paddingLeftUnitCombo.setLayoutData(data);
		_paddingLeftUnitCombo.select(0);
		_paddingLeftUnitCombo.setEnabled(false);
		_paddingLeftUnitCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String top1 = _paddingLeftNumberText.getText();
				if (_paddingLeftUnitCombo.isEnabled()) {
					top1 += _paddingLeftUnitCombo.getText();
				}

				_style.setPaddingLeft(top1);
			}
		});

		Group marginGroup = new Group(top, SWT.NONE);
		marginGroup.setText(DialogsMessages
				.getString("BoxPreferencePage.Margin")); //$NON-NLS-1$
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 3;
		data.verticalSpan = 4;
		marginGroup.setLayoutData(data);
		layout = new GridLayout(3, false);
		marginGroup.setLayout(layout);

		Label marginTopLabel = new Label(marginGroup, SWT.NONE);
		marginTopLabel.setText(DialogsMessages
				.getString("BoxPreferencePage.Top")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		marginTopLabel.setLayoutData(data);

		_marginTopNumberCombo = new StyleCombo(marginGroup, SWT.NONE);
		_marginTopNumberCombo.setItems(IStyleConstants.AUTO);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_marginTopNumberCombo.setLayoutData(data);
		_marginTopNumberCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				_marginTopUnitCombo.setEnabled(true);
				try {
					Integer.valueOf(_marginTopNumberCombo.getText());
				} catch (NumberFormatException ex) {
					_marginTopUnitCombo.setEnabled(false);
				}

				String margin = _marginTopNumberCombo.getText();
				if (_marginTopUnitCombo.isEnabled()) {
					margin += _marginTopUnitCombo.getText();
				}

				_style.setMarginTop(margin);
			}
		});

		_marginTopUnitCombo = new StyleCombo(marginGroup, SWT.READ_ONLY);
		_marginTopUnitCombo.setItems(IStyleConstants.SIZE_UNIT);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_marginTopUnitCombo.setLayoutData(data);
		_marginTopUnitCombo.select(0);
		_marginTopUnitCombo.setEnabled(false);
		_marginTopUnitCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String margin = _marginTopNumberCombo.getText();
				if (_marginTopUnitCombo.isEnabled()) {
					margin += _marginTopUnitCombo.getText();
				}

				_style.setMarginTop(margin);
			}
		});

		Label marginRightLabel = new Label(marginGroup, SWT.NONE);
		marginRightLabel.setText(DialogsMessages
				.getString("BoxPreferencePage.Right")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		marginRightLabel.setLayoutData(data);

		_marginRightNumberCombo = new StyleCombo(marginGroup, SWT.NONE);
		_marginRightNumberCombo.setItems(IStyleConstants.AUTO);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_marginRightNumberCombo.setLayoutData(data);
		_marginRightNumberCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				_marginRightUnitCombo.setEnabled(true);
				try {
					Integer.valueOf(_marginRightNumberCombo.getText());
				} catch (NumberFormatException ex) {
					_marginRightUnitCombo.setEnabled(false);
				}

				String margin = _marginRightNumberCombo.getText();
				if (_marginRightUnitCombo.isEnabled()) {
					margin += _marginRightUnitCombo.getText();
				}

				_style.setMarginRight(margin);
			}
		});

		_marginRightUnitCombo = new StyleCombo(marginGroup, SWT.READ_ONLY);
		_marginRightUnitCombo.setItems(IStyleConstants.SIZE_UNIT);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_marginRightUnitCombo.setLayoutData(data);
		_marginRightUnitCombo.select(0);
		_marginRightUnitCombo.setEnabled(false);
		_marginRightUnitCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String margin = _marginRightNumberCombo.getText();
				if (_marginRightUnitCombo.isEnabled()) {
					margin += _marginRightUnitCombo.getText();
				}

				_style.setMarginRight(margin);
			}
		});

		Label marginBottomLabel = new Label(marginGroup, SWT.NONE);
		marginBottomLabel.setText(DialogsMessages
				.getString("BoxPreferencePage.Bottom")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		marginBottomLabel.setLayoutData(data);

		_marginBottomNumberCombo = new StyleCombo(marginGroup, SWT.NONE);
		_marginBottomNumberCombo.setItems(IStyleConstants.AUTO);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_marginBottomNumberCombo.setLayoutData(data);
		_marginBottomNumberCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				_marginBottomUnitCombo.setEnabled(true);
				try {
					Integer.valueOf(_marginBottomNumberCombo.getText());
				} catch (NumberFormatException ex) {
					_marginBottomUnitCombo.setEnabled(false);
				}

				String margin = _marginBottomNumberCombo.getText();
				if (_marginBottomUnitCombo.isEnabled()) {
					margin += _marginBottomUnitCombo.getText();
				}

				_style.setMarginBottom(margin);
			}
		});

		_marginBottomUnitCombo = new StyleCombo(marginGroup, SWT.READ_ONLY);
		_marginBottomUnitCombo.setItems(IStyleConstants.SIZE_UNIT);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_marginBottomUnitCombo.setLayoutData(data);
		_marginBottomUnitCombo.select(0);
		_marginBottomUnitCombo.setEnabled(false);
		_marginBottomUnitCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String margin = _marginBottomNumberCombo.getText();
				if (_marginBottomUnitCombo.isEnabled()) {
					margin += _marginBottomUnitCombo.getText();
				}

				_style.setMarginBottom(margin);
			}
		});

		Label marginLeftLabel = new Label(marginGroup, SWT.NONE);
		marginLeftLabel.setText(DialogsMessages
				.getString("BoxPreferencePage.Left")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		marginLeftLabel.setLayoutData(data);

		_marginLeftNumberCombo = new StyleCombo(marginGroup, SWT.NONE);
		_marginLeftNumberCombo.setItems(IStyleConstants.AUTO);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_marginLeftNumberCombo.setLayoutData(data);
		_marginLeftNumberCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				_marginLeftUnitCombo.setEnabled(true);
				try {
					Integer.valueOf(_marginLeftNumberCombo.getText());
				} catch (NumberFormatException ex) {
					_marginLeftUnitCombo.setEnabled(false);
				}

				String top1 = _marginLeftNumberCombo.getText();
				if (_marginLeftUnitCombo.isEnabled()) {
					top1 += _marginLeftUnitCombo.getText();
				}

				_style.setMarginLeft(top1);
			}
		});

		_marginLeftUnitCombo = new StyleCombo(marginGroup, SWT.READ_ONLY);
		_marginLeftUnitCombo.setItems(IStyleConstants.SIZE_UNIT);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_marginLeftUnitCombo.setLayoutData(data);
		_marginLeftUnitCombo.select(0);
		_marginLeftUnitCombo.setEnabled(false);
		_marginLeftUnitCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String top1 = _marginLeftNumberCombo.getText();
				if (_marginLeftUnitCombo.isEnabled()) {
					top1 += _marginLeftUnitCombo.getText();
				}

				_style.setMarginLeft(top1);
			}
		});

		initializeControls();

		return top;
	}

	private void initializeControls() {
		// width
		String width = _style.getWidth();
		if (!isEmptyString(width)) {
			int index = _widthNumberCombo.indexOf(width);
			if (index != -1) {
				_widthNumberCombo.select(index);
			} else {
				_widthNumberCombo.setText(width);
			}
		}

		// height
		String height = _style.getHeight();
		if (!isEmptyString(height)) {
			int index = _heightNumberCombo.indexOf(height);
			if (index != -1) {
				_heightNumberCombo.select(index);
			} else {
				_heightNumberCombo.setText(height);
			}
		}

		// clear
		String clear = _style.getClear();
		if (!isEmptyString(clear)) {
			int index = _clearCombo.indexOf(clear);
			if (index != -1) {
				_clearCombo.select(index);
			} else {
				_clearCombo.setText(clear);
			}
		}

		// padding
		String padding = _style.getPaddingTop();
		if (!isEmptyString(padding)) {
			_paddingTopNumberText.setText(padding);
		}
		padding = _style.getPaddingRight();
		if (!isEmptyString(padding)) {
			_paddingRightNumberText.setText(padding);
		}
		padding = _style.getPaddingBottom();
		if (!isEmptyString(padding)) {
			_paddingBottomNumberText.setText(padding);
		}
		padding = _style.getPaddingLeft();
		if (!isEmptyString(padding)) {
			_paddingLeftNumberText.setText(padding);
		}

		// margin
		String margin = _style.getMarginTop();
		if (!isEmptyString(margin)) {
			int index = _marginTopNumberCombo.indexOf(margin);
			if (index != -1) {
				_marginTopNumberCombo.select(index);
			} else {
				_marginTopNumberCombo.setText(margin);
			}
		}
		margin = _style.getMarginRight();
		if (!isEmptyString(margin)) {
			int index = _marginRightNumberCombo.indexOf(margin);
			if (index != -1) {
				_marginRightNumberCombo.select(index);
			} else {
				_marginRightNumberCombo.setText(margin);
			}
		}
		margin = _style.getMarginBottom();
		if (!isEmptyString(margin)) {
			int index = _marginBottomNumberCombo.indexOf(margin);
			if (index != -1) {
				_marginBottomNumberCombo.select(index);
			} else {
				_marginBottomNumberCombo.setText(margin);
			}
		}
		margin = _style.getMarginLeft();
		if (!isEmptyString(margin)) {
			int index = _marginLeftNumberCombo.indexOf(margin);
			if (index != -1) {
				_marginLeftNumberCombo.select(index);
			} else {
				_marginLeftNumberCombo.setText(margin);
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
