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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.util.Assert;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.events.IHyperlinkListener;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;

/**
 * Base class of all Dialog fields. Dialog fields manage controls together with
 * the model, independed from the creation time of the widgets. - support for
 * automated layouting. - enable / disable, set focus a concept of the base
 * class. DialogField have a label.
 * 
 * DialogField may be used in two different context:
 * <ol>
 * <li> In side dialog. In this case, whenever there is anything change in the
 * dialog field, such as user type anything, the dialog should listen to the
 * dialogFieldChanged() events and do things like validation. When user press
 * the "OK" button, dialog should call getXXX to get the value from the dialog
 * field and apply them.
 * <li> In side form based editor or properties view. In this case, whenever
 * there is anything change in the dialog field, such as user type anything, the
 * editor/view should listen to the dialogFieldChanged() events and do things
 * like validation. When user press "Enter" or move the focus out of the control
 * (finish editing), the dialog field will fire out dialogFieldApplied() events,
 * and the editor/view should listen to this event and apply the value to the
 * underlying model.
 * </ol>
 * 
 * The basic idea of the DialogField framework is comming from
 * <code>org.eclipse.jface.preference.FieldEditor</code> and
 * <code>org.eclipse.jdt.internal.ui.wizards.dialogfields.DialogField</code>
 * 
 * @author mengbo
 */
public class DialogFieldBase implements DialogField {
	private Label _label;

	private Label _requiredLabel;

	protected String _labelText;

	private IDialogFieldChangeListener _dialogFieldChangeListener;

	private IDialogFieldApplyListener _dialogFieldApplyListener;

	private boolean _enabled;

	private FontMetrics _fontMetrics;

	private IHyperlinkListener _listener;

	private Hyperlink _hyperlink;

	private Map _attachedData;

	private boolean _isRequired;

	private String toolTip;

	public DialogFieldBase() {
		_enabled = true;
		_label = null;
		_requiredLabel = null;
		_hyperlink = null;
		_labelText = ""; //$NON-NLS-1$
	}

	/**
	 * this method must be called directly after constructor, in this case,
	 * system will create a hyper link label, and when the hyper link is
	 * clicked, the corresponding method on the listene will be called. A
	 * RuntimeException will throw out if this method is called after the label
	 * has been created.
	 * 
	 * @param listener
	 *            can't be null
	 */
	public void setHyperLink(IHyperlinkListener listener) {
		if (_label != null) {
			throw new RuntimeException(
					"The Label instance does not support the listener");
		}
        this._listener = listener;
	}

	/**
	 * Sets the label of the dialog field.
	 */
	public void setLabelText(String labeltext) {
		_labelText = labeltext == null ? "" : labeltext;
		// if (_isRequired)
		// {
		// _labelText = "* " + _labelText;
		// }
		// else
		// {
		// _labelText = " " + _labelText;
		// }
		if (_label != null && !_label.isDisposed()) {
			_label.setText(_labelText);
		} else if (_hyperlink != null && !_hyperlink.isDisposed()) {
			_hyperlink.setText(_labelText);
		}
	}

	protected Shell getShell() {
		if (_label != null && !_label.isDisposed()) {
			return _label.getShell();
		} else if (_hyperlink != null && !_hyperlink.isDisposed()) {
			return _hyperlink.getShell();
		}
		return null;
	}

	// ------ change listener

	/**
	 * Defines the listener for this dialog field.
	 */
	public final void setDialogFieldChangeListener(
			IDialogFieldChangeListener listener) {
		_dialogFieldChangeListener = listener;
	}

	public final void setDialogFieldApplyListener(
			IDialogFieldApplyListener listener) {
		_dialogFieldApplyListener = listener;
	}

	/**
	 * fire both dialogFieldChanged and dialogFieldApplied events.
	 */
	public void dialogFieldChangedAndApplied() {
		if (_dialogFieldChangeListener != null) {
			_dialogFieldChangeListener.dialogFieldChanged(this);
		}
		if (_dialogFieldApplyListener != null) {
			_dialogFieldApplyListener.dialogFieldApplied(this);
		}
	}

	/**
	 * fire dialogFieldChanged event.
	 * 
	 */
	public void dialogFieldChanged() {
		if (_dialogFieldChangeListener != null) {
			_dialogFieldChangeListener.dialogFieldChanged(this);
		}
	}

	/**
	 * fire dialogFieldApplied event.
	 * 
	 */
	public void dialogFieldApplied() {
		if (_dialogFieldApplyListener != null) {
			_dialogFieldApplyListener.dialogFieldApplied(this);
		}
	}

	// ------- focus management

	/**
	 * Tries to set the focus to the dialog field. Returns <code>true</code>
	 * if the dialog field can take focus. To be reimplemented by dialog field
	 * implementors.
	 */
	public boolean setFocus() {
		return false;
	}

	//
	// /**
	// * Posts <code>setFocus</code> to the display event queue.
	// */
	// public void postSetFocusOnDialogField(Display display)
	// {
	// if (display != null)
	// {
	// display.asyncExec(new Runnable()
	// {
	// public void run()
	// {
	// setFocus();
	// }
	// }
	// );
	// }
	// }

	// ------- layout helpers

	/**
	 * Creates all controls of the dialog field and fills it to a composite. The
	 * composite is assumed to have <code>MGridLayout</code> as layout. The
	 * dialog field will adjust its controls' spans to the number of columns
	 * given. To be reimplemented by dialog field implementors.
	 */
	public Control[] doFillIntoGrid(FormToolkit toolkit, Composite parent,
			int nColumns) {
		assertEnoughColumns(nColumns);

		Control label = getLabelControl(toolkit, parent);
		label.setLayoutData(gridDataForLabel(nColumns));

		return new Control[] { label };
	}

	/**
	 * Initializes the computation of horizontal and vertical dialog units based
	 * on the size of current font.
	 * <p>
	 * This method must be called before any of the dialog unit based conversion
	 * methods are called.
	 * </p>
	 * 
	 * @param control
	 *            a control from which to obtain the current font
	 */
	protected FontMetrics getDialogUnits(Control control) {
		if (_fontMetrics == null) {
			// Compute and store a font metric
			GC gc = new GC(control);
			gc.setFont(control.getFont());
			_fontMetrics = gc.getFontMetrics();
			gc.dispose();
		}
		return _fontMetrics;
	}

	/**
	 * Returns the number of columns of the dialog field. To be reimplemented by
	 * dialog field implementors.
	 */
	public int getNumberOfControls() {
		return 1;
	}

	protected static GridData gridDataForLabel(int span) {
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gd.horizontalSpan = span;
		return gd;
	}

	// ------- ui creation

	/**
	 * Creates or returns the created label widget.
	 * 
	 * @param parent
	 *            The parent composite or <code>null</code> if the widget has
	 *            already been created.
	 */
	public Control getLabelControl(FormToolkit _formToolkit, Composite parent) {
		Control control = null;
		if (_label == null && _hyperlink == null) {
			assertCompositeNotNull(parent);

			String label = null;
			if (_labelText != null && !"".equals(_labelText)) {
				//$NON-NLS-1$
				label = _labelText;
			} else {
				label = ".";
			}

			if (_listener == null) {
				control = createLabel(_formToolkit, parent, label);
			} else {
				control = createHyperlink(_formToolkit, parent, label);
			}
			/**
			 * if(isRequired) { FontData[] fontData =
			 * parent.getFont().getFontData(); FontData[] newFontData = new
			 * FontData[fontData.length]; for(int i=0; i<fontData.length; i++) {
			 * newFontData[i] = new FontData(fontData[i].getName(),
			 * fontData[i].getHeight(), fontData[i].getStyle() | SWT.BOLD); }
			 * final Font font = new Font(control.getDisplay(),newFontData);
			 * control.setFont(font); control.addDisposeListener(new
			 * DisposeListener() {
			 * 
			 * public void widgetDisposed(DisposeEvent e) { font.dispose(); }
			 * }); } else { control.setFont(parent.getFont()); }
			 */
			control.setFont(parent.getFont());
			control.setEnabled(_enabled);
		} else {
			if (_label != null) {
				control = _label;
			} else {
				control = _hyperlink;
			}
		}
		return control;
	}

	public Control getRequiredLabelControl(FormToolkit _formToolkit,
			Composite parent) {
		if (_requiredLabel == null) {
			if (_formToolkit == null) {
				_requiredLabel = new Label(parent, SWT.LEFT | SWT.WRAP);
			} else {
				_requiredLabel = _formToolkit.createLabel(parent, "", SWT.LEFT
						| SWT.WRAP);
				_requiredLabel.setForeground(getLabelColor());
			}
			if (_isRequired) {
				_requiredLabel.setText(DialogFieldResources.getInstance()
						.getString("DialogFieldBase.Label.RequiredSymbol"));
			}
		}
		return _requiredLabel;
	}

	private Control createLabel(FormToolkit _formToolkit, Composite parent,
			String labelString) {
		if (_formToolkit == null) {
			_label = new Label(parent, SWT.LEFT | SWT.WRAP);
			_label.setText(labelString);
		} else {
			_label = _formToolkit.createLabel(parent, labelString, SWT.LEFT
					| SWT.WRAP);
			_label.setForeground(getLabelColor());
		}
		return _label;
	}

	/**
	 * get color for lable
	 */
	private Color getLabelColor() {
		String osname = System.getProperty("os.name").toLowerCase();
		if (osname.startsWith("mac os")) {
			return Display.getCurrent().getSystemColor(
					SWT.COLOR_LIST_FOREGROUND);
		}
        return Display.getCurrent()
                .getSystemColor(SWT.COLOR_LIST_SELECTION);
	}

	private Control createHyperlink(FormToolkit _formToolkit, Composite parent,
			String label) {
		if (_formToolkit == null) {
			_hyperlink = new Hyperlink(parent, SWT.LEFT | SWT.WRAP);
			_hyperlink.setForeground(getLabelColor());
			_hyperlink.setUnderlined(true);
			_hyperlink.addMouseTrackListener(new MouseTrackAdapter() {

				public void mouseEnter(MouseEvent e) {
					_hyperlink.setForeground(Display.getCurrent()
							.getSystemColor(SWT.COLOR_BLUE));
				}

				public void mouseExit(MouseEvent e) {
					_hyperlink.setForeground(getLabelColor());
				}
			});
			_hyperlink.setText(label);
		} else {
			_hyperlink = _formToolkit.createHyperlink(parent, label, SWT.LEFT
					| SWT.WRAP);
		}
		_hyperlink.addHyperlinkListener(_listener);
		return _hyperlink;
	}

	/**
	 * Creates a spacer control.
	 * 
	 * @param parent
	 *            The parent composite
	 */
	public Control createEmptySpace(FormToolkit toolkit, Composite parent) {
		return createEmptySpace(toolkit, parent, 1);
	}

	/**
	 * Creates a spacer control with the given span. The composite is assumed to
	 * have <code>MGridLayout</code> as layout.
	 * 
	 * @param parent
	 *            The parent composite
	 */
	public Control createEmptySpace(FormToolkit toolkit, Composite parent,
			int span) {
		Label label;
		if (toolkit != null) {
			label = toolkit.createLabel(parent, "");
		} else {
			label = new Label(parent, SWT.LEFT);
		}
		GridData gd = new GridData();
		gd.horizontalAlignment = GridData.BEGINNING;
		gd.grabExcessHorizontalSpace = false;
		gd.horizontalSpan = span;
		gd.horizontalIndent = 0;
		gd.widthHint = 0;
		gd.heightHint = 0;
		label.setLayoutData(gd);
		return label;
	}

	/**
	 * Tests is the control is not <code>null</code> and not disposed.
	 */
	protected final boolean isOkToUse(Control control) {
		return (control != null) && !(control.isDisposed());
	}

	// --------- enable / disable management

	/**
	 * Sets the enable state of the dialog field.
	 */
	public final void setEnabled(boolean enabled) {
		if (enabled != _enabled) {
			_enabled = enabled;
			updateEnableState();
		}
	}

	/**
	 * Called when the enable state changed. To be extended by dialog field
	 * implementors.
	 */
	protected void updateEnableState() {
		if (_label != null && !_label.isDisposed()) {
			_label.setEnabled(_enabled);
		}
		if (_hyperlink != null && !_hyperlink.isDisposed()) {
			_hyperlink.setEnabled(_enabled);
		}
	}

	/**
	 * Gets the enable state of the dialog field.
	 */
	public final boolean isEnabled() {
		return _enabled;
	}

	protected final void assertCompositeNotNull(Composite comp) {
		Assert.isNotNull(comp,
				"uncreated control requested with composite null"); //$NON-NLS-1$
	}

	protected final void assertEnoughColumns(int nColumns) {
		Assert.isTrue(nColumns >= getNumberOfControls(),
				"given number of columns is too small"); //$NON-NLS-1$
	}

	/**
	 * Get attached data by key.
	 * 
	 * @param key
	 * @return
	 */
	public Object getAttachedData(Object key) {
		if (_attachedData != null) {
			return _attachedData.get(key);
		}
        return null;
	}

	/**
	 * You can attach any data to the DialogField, and get it using the
	 * <code>getAttachedData</code> method.
	 * 
	 * @param key
	 * @param value
	 */
	public void putAttachedData(Object key, Object value) {
		if (_attachedData == null) {
			_attachedData = new HashMap();
		}
		_attachedData.put(key, value);
	}

	/**
	 * this method give the DialogField a chance to set the correct column to
	 * grab horizontal space. In the implementation of this method, should only
	 * change the GridData of control, should not do anything else.
	 * 
	 * The caller is responsible to make sure the controls for the dialog field
	 * has been created before calling this method.
	 */
	public void handleGrabHorizontal() {
		// do nothing.
	}

	public boolean isRequired() {
		return _isRequired;
	}

	public void setRequired(boolean isRequired) {
		this._isRequired = isRequired;
	}

	protected String getToolTip() {
		return toolTip;
	}

	public void setToolTip(String toolTip) {
		this.toolTip = toolTip;
	}
}
