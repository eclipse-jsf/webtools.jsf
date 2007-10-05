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
package org.eclipse.jst.jsf.common.ui.internal.dialogfield;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.jst.jsf.common.ui.internal.utils.StyleCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * This class provides a common color choice DialogFiled.
 * 
 * @author mengbo
 */
public class ColorButtonDialogField extends StyleComboDialogField {
	// color selection button
	private Button _button;

	// the button enable flag
	private boolean _buttonEnabled;

	// the current color
	private Color _color;

	// the current color RGB
	private RGB _colorValue;

	// the combo control
	private StyleCombo _combo;

	// when no color is set or selected, using the empty image
	private Image _emptyImage;

	// size of color image
	private Point _extent;

	// the color image
	private Image _image;
    
    private ColorPalette    _colorPalette;

	/**
	 * @param flags
	 * @param colorPalette
	 */
	public ColorButtonDialogField(int flags, ColorPalette colorPalette) {
		super(flags);
		_buttonEnabled = true;
        _colorPalette = colorPalette;
	}

	private void buttonPressed() {
		ColorDialog colorDialog = new ColorDialog(_button.getShell());
		colorDialog.setRGB(_colorValue);
		RGB newColor = colorDialog.open();
		if (newColor != null && !newColor.equals(_colorValue)) {
			_colorValue = newColor;
			setText(ColorPalette.getStringColor(newColor));
			updateColorImage();
		}
	}

	/**
	 * Compute the size of the image to be displayed.
	 * 
	 * @param window -
	 *            the window used to calculate
	 * @return <code>Point</code>
	 */
	private Point computeImageSize(Control window) {
		GC gc = new GC(window);
		Font f = _button.getFont();
		gc.setFont(f);
		int height = gc.getFontMetrics().getHeight();

		Point p = new Point(height * 3 - 6, height - 2);
		gc.dispose();
		return p;
	}

	private void disposeResources() {
		if (_image != null) {
			_image.dispose();
			_image = null;
		}
		if (_emptyImage != null) {
			_emptyImage.dispose();
			_emptyImage = null;
		}
		if (_color != null) {
			_color.dispose();
			_color = null;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogField#doFillIntoGrid(org.eclipse.ui.forms.widgets.FormToolkit,
	 *      org.eclipse.swt.widgets.Composite, int)
	 */
	public Control[] doFillIntoGrid(FormToolkit toolkit, Composite parent,
			int nColumns) {
		assertEnoughColumns(nColumns);

		Control requiredLabel = getRequiredLabelControl(toolkit, parent);
		requiredLabel.setLayoutData(gridDataForLabel(1));

		Control label = getLabelControl(toolkit, parent);
		label.setLayoutData(gridDataForLabel(1));

		StyleCombo combo = getComboControl(toolkit, parent);
		combo.setLayoutData(gridDataForCombo(nColumns - 3));

		_button = getChangeControl(toolkit, parent);
		_button.setLayoutData(gridDataForButton(1));

		return new Control[] { requiredLabel, label, combo, _button };
	}

	/**
	 * Sets the enable state of the button.
	 * @param enable 
	 */
	public void enableButton(boolean enable) {
		if (isOkToUse(_button)) {
			_button.setEnabled(isEnabled() && enable);
		}
		_buttonEnabled = enable;
	}

	/**
	 * Creates or returns the created buttom widget.
	 * @param toolkit 
	 * 
	 * @param parent
	 *            The parent composite or <code>null</code> if the widget has
	 *            already been created.
	 * @return  the button widget
	 */
	public Button getChangeControl(FormToolkit toolkit, Composite parent) {
		if (_button == null) {
			assertCompositeNotNull(parent);
			if (toolkit != null) {
				_button = toolkit.createButton(parent, "", SWT.PUSH);
			} else {
				_button = new Button(parent, SWT.PUSH);
			}
			_button.setEnabled(isEnabled() && _buttonEnabled);

			_button.addSelectionListener(new SelectionListener() {
				public void widgetDefaultSelected(SelectionEvent e) {
					buttonPressed();
				}

				public void widgetSelected(SelectionEvent e) {
					buttonPressed();
				}
			});

			_button.addDisposeListener(new DisposeListener() {
				public void widgetDisposed(DisposeEvent event) {
					disposeResources();
				}
			});

			_extent = computeImageSize(parent);
			_image = new Image(parent.getDisplay(), _extent.x, _extent.y);
			_emptyImage = new Image(parent.getDisplay(), _extent.x, _extent.y);
			initEmptyImage(_button);
			updateColorImage();
		}

		return _button;
	}

	private String[] getColorList() {
		Map map = _colorPalette.getExtendedColorMap();
		List list = new ArrayList(map.keySet());
		Collections.sort(list);
		return (String[]) list.toArray(new String[list.size()]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.common.ui.internal.dialogfield.StringDialogField#getTextControl(org.eclipse.ui.forms.widgets.FormToolkit,
	 *      org.eclipse.swt.widgets.Composite)
	 */
	public StyleCombo getComboControl(FormToolkit toolkit, Composite parent) {

		if (_combo == null) {
			_combo = super.getComboControl(toolkit, parent);

			_combo.addSelectionListener(new SelectionListener() {

				public void widgetDefaultSelected(SelectionEvent e) {
					updateImageAfterChanged();
				}

				public void widgetSelected(SelectionEvent e) {
					updateImageAfterChanged();
				}
			});
			_combo.addFocusListener(new FocusAdapter() {
				public void focusLost(FocusEvent e) {
					updateImageAfterChanged();
				}
			});
			_combo.setItems(getColorList());
		}
		return _combo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogField#getNumberOfControls()
	 */
	public int getNumberOfControls() {
		return 4;
	}
	private GridData gridDataForButton(int span) {
		GridData gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = false;
		gd.horizontalSpan = span;
		gd.widthHint = LayoutUtil.getButtonWidthHint(_button);
		gd.heightHint = _combo.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
		return gd;
	}

	private void initEmptyImage(Control parent) {
		Color transparentColor = parent.getForeground();
		ImageData imageData = _emptyImage.getImageData();
		imageData.transparentPixel = 0;
		GC gc = new GC(_emptyImage);
		gc.setBackground(transparentColor);
		gc.fillRectangle(0, 0, _emptyImage.getBounds().width, _emptyImage
				.getBounds().height);
	}

	/**
	 * Set the current color value and update the control.
	 * 
	 * @param rgb
	 *            The new color.
	 */
	public void setColorValue(RGB rgb) {
		_colorValue = rgb;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.common.ui.internal.dialogfield.ComboDialogField#setTextWithoutUpdate(java.lang.String)
	 */
	public void setTextWithoutUpdate(String text) {
		super.setTextWithoutUpdate(text);
		updateImageAfterChanged();
	}

	/**
	 * Update the image being displayed on the button using the current color
	 * setting.
	 */
	protected void updateColorImage() {
		if (_button.isDisposed()) {
			return;
		}

		if (_colorValue == null) {
			_button.setImage(_emptyImage);
			return;
		}

		Display display = _button.getDisplay();
		GC gc = new GC(_image);
		gc.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
		gc.drawRectangle(0, 0, _extent.x - 1, _extent.y - 1);
		if (_color != null) {
			_color.dispose();
		}
		_color = new Color(display, _colorValue);
		gc.setBackground(_color);
		gc.fillRectangle(1, 1, _extent.x - 2, _extent.y - 2);
		gc.dispose();
		_button.setImage(_image);
	}

	/*
	 * @see DialogField#updateEnableState
	 */
	protected void updateEnableState() {
		super.updateEnableState();
		if (isOkToUse(_button)) {
			_button.setEnabled(isEnabled() && _buttonEnabled);
		}
	}

	private void updateImageAfterChanged() {
		String newColor = getText().trim();
		setColorValue(_colorPalette.getExtendedColorRGB(newColor));
		updateColorImage();
	}
}
