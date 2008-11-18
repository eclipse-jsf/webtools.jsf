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
import org.eclipse.swt.widgets.Combo;
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
public class PositioningPreferencePage extends PreferencePage {
	private CSSPropertyContext _style;

	private Combo _typeCombo, _visibilityCombo, _widthNumberCombo,
			_widthUnitCombo, _zIndexCombo, _heightNumberCombo,
			_heightUnitCombo, _overflowCombo;

	private Combo _placementTopNumberCombo, _placementRightNumberCombo,
			_placementBottomNumberCombo, _placementLeftNumberCombo;

	private Combo _placementTopUnitCombo, _placementRightUnitCombo,
			_placementBottomUnitCombo, _placementLeftUnitCombo;

	private Combo _clipTopNumberCombo, _clipRightNumberCombo,
			_clipBottomNumberCombo, _clipLeftNumberCombo;

	private Combo _clipTopUnitCombo, _clipRightUnitCombo, _clipBottomUnitCombo,
			_clipLeftUnitCombo;

	/**
	 * @param element
	 * @param style
	 */
	public PositioningPreferencePage(IDOMElement element,
			CSSPropertyContext style) {
		super();
		_style = style;

		setTitle(DialogsMessages.getString("PositioningPreferencePage.Title")); //$NON-NLS-1$
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

		Label typeLabel = new Label(top, SWT.NONE);
		typeLabel.setText(DialogsMessages
				.getString("PositioningPreferencePage.Type")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		typeLabel.setLayoutData(data);

		_typeCombo = new Combo(top, SWT.NONE);
		_typeCombo.setItems(IStyleConstants.POSITIONING_TYPE);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 2;
		_typeCombo.setLayoutData(data);
		_typeCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String type = _typeCombo.getText();
				_style.setPosition(type);
			}
		});

		Label visibilityLabel = new Label(top, SWT.NONE);
		visibilityLabel.setText(DialogsMessages
				.getString("PositioningPreferencePage.Visibility")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		visibilityLabel.setLayoutData(data);

		_visibilityCombo = new Combo(top, SWT.NONE);
		_visibilityCombo.setItems(IStyleConstants.VISIBILITY);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_visibilityCombo.setLayoutData(data);
		_visibilityCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String visibility = _visibilityCombo.getText();
				_style.setVisibility(visibility);
			}
		});

		new Label(top, SWT.NONE);

		Label widthLabel = new Label(top, SWT.NONE);
		widthLabel.setText(DialogsMessages
				.getString("PositioningPreferencePage.Width")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		widthLabel.setLayoutData(data);

		_widthNumberCombo = new Combo(top, SWT.NONE);
		_widthNumberCombo.setItems(IStyleConstants.LIST_POSITION);
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

		_widthUnitCombo = new Combo(top, SWT.READ_ONLY);
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

		Label zIndexLabel = new Label(top, SWT.NONE);
		zIndexLabel.setText(DialogsMessages
				.getString("PositioningPreferencePage.ZIndex")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		zIndexLabel.setLayoutData(data);

		_zIndexCombo = new Combo(top, SWT.NONE);
		_zIndexCombo.setItems(IStyleConstants.AUTO);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_zIndexCombo.setLayoutData(data);
		_zIndexCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String index = _zIndexCombo.getText();
				_style.setZIndex(index);
			}
		});

		new Label(top, SWT.NONE);

		Label heightLabel = new Label(top, SWT.NONE);
		heightLabel.setText(DialogsMessages
				.getString("PositioningPreferencePage.Height")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		heightLabel.setLayoutData(data);

		_heightNumberCombo = new Combo(top, SWT.NONE);
		_heightNumberCombo.setItems(IStyleConstants.LIST_POSITION);
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

		_heightUnitCombo = new Combo(top, SWT.READ_ONLY);
		_heightUnitCombo.setItems(IStyleConstants.SIZE_UNIT);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_heightUnitCombo.setLayoutData(data);
		_heightUnitCombo.select(0);
		_heightUnitCombo.setEnabled(false);
		_heightUnitCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String height = _heightNumberCombo.getText();
				if (_heightUnitCombo.isEnabled()) {
					height += _heightUnitCombo.getText();
				}

				_style.setHeight(height);
			}
		});

		Label overflowLabel = new Label(top, SWT.NONE);
		overflowLabel.setText(DialogsMessages
				.getString("PositioningPreferencePage.Overflow")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		overflowLabel.setLayoutData(data);

		_overflowCombo = new Combo(top, SWT.NONE);
		_overflowCombo.setItems(IStyleConstants.OVERFLOW);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_overflowCombo.setLayoutData(data);
		_overflowCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String overflow = _overflowCombo.getText();
				_style.setOverflow(overflow);
			}
		});

		Group placementGroup = new Group(top, SWT.NONE);
		placementGroup.setText(DialogsMessages
				.getString("PositioningPreferencePage.Placement")); //$NON-NLS-1$
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 3;
		data.verticalSpan = 4;
		placementGroup.setLayoutData(data);
		layout = new GridLayout(3, false);
		placementGroup.setLayout(layout);

		Label placementTopLabel = new Label(placementGroup, SWT.NONE);
		placementTopLabel.setText(DialogsMessages
				.getString("BoxPreferencePage.Top")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		placementTopLabel.setLayoutData(data);

		_placementTopNumberCombo = new Combo(placementGroup, SWT.NONE);
		_placementTopNumberCombo.setItems(IStyleConstants.AUTO);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_placementTopNumberCombo.setLayoutData(data);
		_placementTopNumberCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				_placementTopUnitCombo.setEnabled(true);
				try {
					Integer.valueOf(_placementTopNumberCombo.getText());
				} catch (NumberFormatException ex) {
					_placementTopUnitCombo.setEnabled(false);
				}

				String placement = _placementTopNumberCombo.getText();
				if (_placementTopUnitCombo.isEnabled()) {
					placement += _placementTopUnitCombo.getText();
				}

				_style.setTop(placement);
			}
		});

		_placementTopUnitCombo = new Combo(placementGroup, SWT.READ_ONLY);
		_placementTopUnitCombo.setItems(IStyleConstants.SIZE_UNIT);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_placementTopUnitCombo.setLayoutData(data);
		_placementTopUnitCombo.select(0);
		_placementTopUnitCombo.setEnabled(false);
		_placementTopUnitCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String placement = _placementTopNumberCombo.getText();
				if (_placementTopUnitCombo.isEnabled()) {
					placement += _placementTopUnitCombo.getText();
				}

				_style.setTop(placement);
			}
		});

		Label placementRightLabel = new Label(placementGroup, SWT.NONE);
		placementRightLabel.setText(DialogsMessages
				.getString("BoxPreferencePage.Right")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		placementRightLabel.setLayoutData(data);

		_placementRightNumberCombo = new Combo(placementGroup, SWT.NONE);
		_placementRightNumberCombo.setItems(IStyleConstants.AUTO);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_placementRightNumberCombo.setLayoutData(data);
		_placementRightNumberCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				_placementRightUnitCombo.setEnabled(true);
				try {
					Integer.valueOf(_placementRightNumberCombo.getText());
				} catch (NumberFormatException ex) {
					_placementRightUnitCombo.setEnabled(false);
				}

				String placement = _placementRightNumberCombo.getText();
				if (_placementRightUnitCombo.isEnabled()) {
					placement += _placementRightUnitCombo.getText();
				}

				_style.setRight(placement);
			}
		});

		_placementRightUnitCombo = new Combo(placementGroup, SWT.READ_ONLY);
		_placementRightUnitCombo.setItems(IStyleConstants.SIZE_UNIT);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_placementRightUnitCombo.setLayoutData(data);
		_placementRightUnitCombo.select(0);
		_placementRightUnitCombo.setEnabled(false);
		_placementRightUnitCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String placement = _placementRightNumberCombo.getText();
				if (_placementRightUnitCombo.isEnabled()) {
					placement += _placementRightUnitCombo.getText();
				}

				_style.setRight(placement);
			}
		});

		Label placementBottomLabel = new Label(placementGroup, SWT.NONE);
		placementBottomLabel.setText(DialogsMessages
				.getString("BoxPreferencePage.Bottom")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		placementBottomLabel.setLayoutData(data);

		_placementBottomNumberCombo = new Combo(placementGroup, SWT.NONE);
		_placementBottomNumberCombo.setItems(IStyleConstants.AUTO);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_placementBottomNumberCombo.setLayoutData(data);
		_placementBottomNumberCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				_placementBottomUnitCombo.setEnabled(true);
				try {
					Integer.valueOf(_placementBottomNumberCombo.getText());
				} catch (NumberFormatException ex) {
					_placementBottomUnitCombo.setEnabled(false);
				}

				String placement = _placementBottomNumberCombo.getText();
				if (_placementBottomUnitCombo.isEnabled()) {
					placement += _placementBottomUnitCombo.getText();
				}

				_style.setBottom(placement);
			}
		});

		_placementBottomUnitCombo = new Combo(placementGroup, SWT.READ_ONLY);
		_placementBottomUnitCombo.setItems(IStyleConstants.SIZE_UNIT);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_placementBottomUnitCombo.setLayoutData(data);
		_placementBottomUnitCombo.select(0);
		_placementBottomUnitCombo.setEnabled(false);
		_placementBottomUnitCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String placement = _placementBottomNumberCombo.getText();
				if (_placementBottomUnitCombo.isEnabled()) {
					placement += _placementBottomUnitCombo.getText();
				}

				_style.setBottom(placement);
			}
		});

		Label placementLeftLabel = new Label(placementGroup, SWT.NONE);
		placementLeftLabel.setText(DialogsMessages
				.getString("BoxPreferencePage.Left")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		placementLeftLabel.setLayoutData(data);

		_placementLeftNumberCombo = new Combo(placementGroup, SWT.NONE);
		_placementLeftNumberCombo.setItems(IStyleConstants.AUTO);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_placementLeftNumberCombo.setLayoutData(data);
		_placementLeftNumberCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				_placementLeftUnitCombo.setEnabled(true);
				try {
					Integer.valueOf(_placementLeftNumberCombo.getText());
				} catch (NumberFormatException ex) {
					_placementLeftUnitCombo.setEnabled(false);
				}

				String top1 = _placementLeftNumberCombo.getText();
				if (_placementLeftUnitCombo.isEnabled()) {
					top1 += _placementLeftUnitCombo.getText();
				}

				_style.setLeft(top1);
			}
		});

		_placementLeftUnitCombo = new Combo(placementGroup, SWT.READ_ONLY);
		_placementLeftUnitCombo.setItems(IStyleConstants.SIZE_UNIT);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_placementLeftUnitCombo.setLayoutData(data);
		_placementLeftUnitCombo.select(0);
		_placementLeftUnitCombo.setEnabled(false);
		_placementLeftUnitCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String top1 = _placementLeftNumberCombo.getText();
				if (_placementLeftUnitCombo.isEnabled()) {
					top1 += _placementLeftUnitCombo.getText();
				}

				_style.setLeft(top1);
			}
		});

		Group clipGroup = new Group(top, SWT.NONE);
		clipGroup.setText(DialogsMessages
				.getString("PositioningPreferencePage.Clip")); //$NON-NLS-1$
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 3;
		data.verticalSpan = 4;
		clipGroup.setLayoutData(data);
		layout = new GridLayout(3, false);
		clipGroup.setLayout(layout);

		Label clipTopLabel = new Label(clipGroup, SWT.NONE);
		clipTopLabel
				.setText(DialogsMessages.getString("BoxPreferencePage.Top")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		clipTopLabel.setLayoutData(data);

		_clipTopNumberCombo = new Combo(clipGroup, SWT.NONE);
		_clipTopNumberCombo.setItems(IStyleConstants.AUTO);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_clipTopNumberCombo.setLayoutData(data);
		_clipTopNumberCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				_clipTopUnitCombo.setEnabled(true);
				try {
					Integer.valueOf(_clipTopNumberCombo.getText());
				} catch (NumberFormatException ex) {
					_clipTopUnitCombo.setEnabled(false);
				}

				String clip = _clipTopNumberCombo.getText();
				if (_clipTopUnitCombo.isEnabled()) {
					clip += _clipTopUnitCombo.getText();
				}

				_style.setClipTop(clip);
			}
		});

		_clipTopUnitCombo = new Combo(clipGroup, SWT.READ_ONLY);
		_clipTopUnitCombo.setItems(IStyleConstants.SIZE_UNIT);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_clipTopUnitCombo.setLayoutData(data);
		_clipTopUnitCombo.select(0);
		_clipTopUnitCombo.setEnabled(false);
		_clipTopUnitCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String clip = _clipTopNumberCombo.getText();
				if (_clipTopUnitCombo.isEnabled()) {
					clip += _clipTopUnitCombo.getText();
				}

				_style.setClipTop(clip);
			}
		});

		Label clipRightLabel = new Label(clipGroup, SWT.NONE);
		clipRightLabel.setText(DialogsMessages
				.getString("PositioningPreferencePage.Right")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		clipRightLabel.setLayoutData(data);

		_clipRightNumberCombo = new Combo(clipGroup, SWT.NONE);
		_clipRightNumberCombo.setItems(IStyleConstants.AUTO);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_clipRightNumberCombo.setLayoutData(data);
		_clipRightNumberCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				_clipRightUnitCombo.setEnabled(true);
				try {
					Integer.valueOf(_clipRightNumberCombo.getText());
				} catch (NumberFormatException ex) {
					_clipRightUnitCombo.setEnabled(false);
				}

				String clip = _clipRightNumberCombo.getText();
				if (_clipRightUnitCombo.isEnabled()) {
					clip += _clipRightUnitCombo.getText();
				}

				_style.setClipRight(clip);
			}
		});

		_clipRightUnitCombo = new Combo(clipGroup, SWT.READ_ONLY);
		_clipRightUnitCombo.setItems(IStyleConstants.SIZE_UNIT);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_clipRightUnitCombo.setLayoutData(data);
		_clipRightUnitCombo.select(0);
		_clipRightUnitCombo.setEnabled(false);
		_clipRightUnitCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String clip = _clipRightNumberCombo.getText();
				if (_clipRightUnitCombo.isEnabled()) {
					clip += _clipRightUnitCombo.getText();
				}

				_style.setClipRight(clip);
			}
		});

		Label clipBottomLabel = new Label(clipGroup, SWT.NONE);
		clipBottomLabel.setText(DialogsMessages
				.getString("PositioningPreferencePage.Bottom")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		clipBottomLabel.setLayoutData(data);

		_clipBottomNumberCombo = new Combo(clipGroup, SWT.NONE);
		_clipBottomNumberCombo.setItems(IStyleConstants.AUTO);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_clipBottomNumberCombo.setLayoutData(data);
		_clipBottomNumberCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				_clipBottomUnitCombo.setEnabled(true);
				try {
					Integer.valueOf(_clipBottomNumberCombo.getText());
				} catch (NumberFormatException ex) {
					_clipBottomUnitCombo.setEnabled(false);
				}

				String clip = _clipBottomNumberCombo.getText();
				if (_clipBottomUnitCombo.isEnabled()) {
					clip += _clipBottomUnitCombo.getText();
				}

				_style.setClipBottom(clip);
			}
		});

		_clipBottomUnitCombo = new Combo(clipGroup, SWT.READ_ONLY);
		_clipBottomUnitCombo.setItems(IStyleConstants.SIZE_UNIT);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_clipBottomUnitCombo.setLayoutData(data);
		_clipBottomUnitCombo.select(0);
		_clipBottomUnitCombo.setEnabled(false);
		_clipBottomUnitCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String clip = _clipBottomNumberCombo.getText();
				if (_clipBottomUnitCombo.isEnabled()) {
					clip += _clipBottomUnitCombo.getText();
				}

				_style.setClipBottom(clip);
			}
		});

		Label clipLeftLabel = new Label(clipGroup, SWT.NONE);
		clipLeftLabel.setText(DialogsMessages
				.getString("PositioningPreferencePage.Left")); //$NON-NLS-1$
		data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		clipLeftLabel.setLayoutData(data);

		_clipLeftNumberCombo = new Combo(clipGroup, SWT.NONE);
		_clipLeftNumberCombo.setItems(IStyleConstants.AUTO);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_clipLeftNumberCombo.setLayoutData(data);
		_clipLeftNumberCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				_clipLeftUnitCombo.setEnabled(true);
				try {
					Integer.valueOf(_clipLeftNumberCombo.getText());
				} catch (NumberFormatException ex) {
					_clipLeftUnitCombo.setEnabled(false);
				}

				String top1 = _clipLeftNumberCombo.getText();
				if (_clipLeftUnitCombo.isEnabled()) {
					top1 += _clipLeftUnitCombo.getText();
				}

				_style.setClipLeft(top1);
			}
		});

		_clipLeftUnitCombo = new Combo(clipGroup, SWT.READ_ONLY);
		_clipLeftUnitCombo.setItems(IStyleConstants.SIZE_UNIT);
		data = new GridData(GridData.FILL_HORIZONTAL);
		_clipLeftUnitCombo.setLayoutData(data);
		_clipLeftUnitCombo.select(0);
		_clipLeftUnitCombo.setEnabled(false);
		_clipLeftUnitCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String top1 = _clipLeftNumberCombo.getText();
				if (_clipLeftUnitCombo.isEnabled()) {
					top1 += _clipLeftUnitCombo.getText();
				}

				_style.setClipLeft(top1);
			}
		});

		initializeControls();

		return top;
	}

	private void initializeControls() {
		// position
		String position = _style.getPosition();
		if (!isEmptyString(position)) {
			int index = _typeCombo.indexOf(position);
			if (index != -1) {
				_typeCombo.select(index);
			} else {
				_typeCombo.setText(position);
			}
		}

		// visibility
		String visibility = _style.getVisibility();
		if (!isEmptyString(visibility)) {
			int index = _visibilityCombo.indexOf(visibility);
			if (index != -1) {
				_visibilityCombo.select(index);
			} else {
				_visibilityCombo.setText(visibility);
			}
		}

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

		// z-index
		String zindex = _style.getZIndex();
		if (!isEmptyString(zindex)) {
			int index = _zIndexCombo.indexOf(zindex);
			if (index != -1) {
				_zIndexCombo.select(index);
			} else {
				_zIndexCombo.setText(zindex);
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

		// overflow
		String overflow = _style.getOverflow();
		if (!isEmptyString(overflow)) {
			int index = _overflowCombo.indexOf(overflow);
			if (index != -1) {
				_overflowCombo.select(index);
			} else {
				_overflowCombo.setText(overflow);
			}
		}

		// placement
		String placement = _style.getTop();
		if (!isEmptyString(placement)) {
			int index = _placementTopNumberCombo.indexOf(placement);
			if (index != -1) {
				_placementTopNumberCombo.select(index);
			} else {
				_placementTopNumberCombo.setText(placement);
			}
		}
		placement = _style.getRight();
		if (!isEmptyString(placement)) {
			int index = _placementRightNumberCombo.indexOf(placement);
			if (index != -1) {
				_placementRightNumberCombo.select(index);
			} else {
				_placementRightNumberCombo.setText(placement);
			}
		}
		placement = _style.getBottom();
		if (!isEmptyString(placement)) {
			int index = _placementBottomNumberCombo.indexOf(placement);
			if (index != -1) {
				_placementBottomNumberCombo.select(index);
			} else {
				_placementBottomNumberCombo.setText(placement);
			}
		}
		placement = _style.getLeft();
		if (!isEmptyString(placement)) {
			int index = _placementLeftNumberCombo.indexOf(placement);
			if (index != -1) {
				_placementLeftNumberCombo.select(index);
			} else {
				_placementLeftNumberCombo.setText(placement);
			}
		}

		// clip
		String clip = _style.getClipTop();
		if (!isEmptyString(clip)) {
			int index = _clipTopNumberCombo.indexOf(clip);
			if (index != -1) {
				_clipTopNumberCombo.select(index);
			} else {
				_clipTopNumberCombo.setText(clip);
			}
		}
		clip = _style.getClipRight();
		if (!isEmptyString(clip)) {
			int index = _clipRightNumberCombo.indexOf(clip);
			if (index != -1) {
				_clipRightNumberCombo.select(index);
			} else {
				_clipRightNumberCombo.setText(clip);
			}
		}
		clip = _style.getClipBottom();
		if (!isEmptyString(clip)) {
			int index = _clipBottomNumberCombo.indexOf(clip);
			if (index != -1) {
				_clipBottomNumberCombo.select(index);
			} else {
				_clipBottomNumberCombo.setText(clip);
			}
		}
		clip = _style.getClipLeft();
		if (!isEmptyString(clip)) {
			int index = _clipLeftNumberCombo.indexOf(clip);
			if (index != -1) {
				_clipLeftNumberCombo.select(index);
			} else {
				_clipLeftNumberCombo.setText(clip);
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
