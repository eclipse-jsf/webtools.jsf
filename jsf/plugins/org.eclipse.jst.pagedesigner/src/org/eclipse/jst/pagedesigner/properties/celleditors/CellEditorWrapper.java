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
package org.eclipse.jst.pagedesigner.properties.celleditors;

import java.text.MessageFormat;

import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellEditorListener;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Listener;

/**
 * CellEditorWrapper is a special cell editor, that wraps an existing cell
 * editor by adding a small clickable button to end of it.
 * 
 * Due to limitation of the CellEditor framework, this wrapping technology may
 * resulting in some small inconvenience when change focus from the "wrapped"
 * cell editor to the "added" button.
 * 
 * This is an abstract class. Child class need override some methods.
 * 
 * @author mengbo
 */
public abstract class CellEditorWrapper extends CellEditor {
	/**
	 * The editor control.
	 */
	private Composite _editor;

	/**
	 * the wrapped cell editor
	 */
	private CellEditor _wrapped;

	/**
	 * The button.
	 */
	private Button _button;

	/**
	 * Internal class for laying out the dialog.
	 */
	private class DialogCellLayout extends Layout {
		public void layout(Composite editor, boolean force) {
			Rectangle bounds = editor.getClientArea();
			Point size = _button.computeSize(SWT.DEFAULT, SWT.DEFAULT, force);
			// if (_wrapped != null)
			_wrapped.getControl().setBounds(0, 0, bounds.width - size.x,
					bounds.height);
			_button.setBounds(bounds.width - size.x, 0, size.x, bounds.height);
		}

		public Point computeSize(Composite editor, int wHint, int hHint,
				boolean force) {
			if (wHint != SWT.DEFAULT && hHint != SWT.DEFAULT)
				return new Point(wHint, hHint);
			Point contentsSize = _wrapped.getControl().computeSize(SWT.DEFAULT,
					SWT.DEFAULT, force);
			Point buttonSize = _button.computeSize(SWT.DEFAULT, SWT.DEFAULT,
					force);
			// Just return the button width to ensure the button is not clipped
			// if the label is long.
			// The label will just use whatever extra width there is
			Point result = new Point(buttonSize.x, Math.max(contentsSize.y,
					buttonSize.y));
			return result;
		}
	}

	/**
	 * Default DialogCellEditor style
	 */
	private static final int defaultStyle = SWT.NONE;

	/**
	 * Creates a new dialog cell editor with no control
	 * 
	 * @since 2.1
	 */
	public CellEditorWrapper() {
		setStyle(defaultStyle);
	}

	/**
	 * Creates a new dialog cell editor parented under the given control. The
	 * cell editor value is <code>null</code> initially, and has no validator.
	 * 
	 * @param parent
	 *            the parent control
	 */
	protected CellEditorWrapper(Composite parent) {
		this(parent, defaultStyle);
	}

	/**
	 * Creates a new dialog cell editor parented under the given control. The
	 * cell editor value is <code>null</code> initially, and has no validator.
	 * 
	 * @param parent
	 *            the parent control
	 * @param style
	 *            the style bits
	 * @since 2.1
	 */
	protected CellEditorWrapper(Composite parent, int style) {
		super(parent, style);
	}

	/**
	 * Creates the button for this cell editor under the given parent control.
	 * <p>
	 * The default implementation of this framework method creates the button
	 * display on the right hand side of the dialog cell editor. Subclasses may
	 * extend or reimplement.
	 * </p>
	 * 
	 * @param parent
	 *            the parent control
	 * @return the new button control
	 */
	protected Button createButton(Composite parent) {
		Button result = new Button(parent, SWT.DOWN);
		result.setImage(getBindingImage());
		// result.setText("..."); //$NON-NLS-1$
		return result;
	}

	/**
	 * Since createButton is called from constructor, so we could only let child
	 * class override this method to provide image. Rather than setting as
	 * property.
	 * 
	 * @return image
	 */
	protected abstract Image getBindingImage();

	/**
	 * Creates the controls used to show the value of this cell editor.
	 * <p>
	 * The default implementation of this framework method creates a label
	 * widget, using the same font and background color as the parent control.
	 * </p>
	 * <p>
	 * Subclasses may re-implement. If you re-implement this method, you should
	 * also re-implement <code>updateContents</code>.
	 * </p>
	 * 
	 * @param cell
	 *            the control for this cell editor
	 * @return control
	 */
	protected Control createContents(Composite cell) {
		_wrapped = createWrappedCellEditor(cell);
		if (_wrapped == null) {
			_wrapped = new TextCellEditor(cell);
		}
		_wrapped.addListener(new ICellEditorListener() {
			public void applyEditorValue() {
				fireApplyEditorValue();
			}

			public void cancelEditor() {
				fireCancelEditor();
			}

			public void editorValueChanged(boolean oldValidState,
					boolean newValidState) {
				fireEditorValueChanged(oldValidState, newValidState);
			}
		});
		_wrapped.addPropertyChangeListener(new IPropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent event) {
				// FIXME:
			}
		});

		_wrapped.getControl().setVisible(true);
		_wrapped.getControl().addListener(SWT.Hide, new Listener() {
			public void handleEvent(Event event) {
				Display.getCurrent().asyncExec(new Runnable() {
					public void run() {
						if (_wrapped != null && _wrapped.getControl() != null
								&& !_wrapped.getControl().isDisposed()) {
							_wrapped.getControl().setVisible(true);
						} else {
							deactivate();
						}
					}
				});

			}
		});
		return _wrapped.getControl();
	}

	/**
	 * @param cell
	 * @return CellEditor
	 */
	protected abstract CellEditor createWrappedCellEditor(Composite cell);

	/*
	 * (non-Javadoc) Method declared on CellEditor.
	 */
	protected Control createControl(Composite parent) {
		Font font = parent.getFont();
		Color bg = parent.getBackground();

		_editor = new Composite(parent, getStyle());
		_editor.setFont(font);
		_editor.setBackground(bg);
		_editor.setLayout(new DialogCellLayout());

		createContents(_editor);
		// updateContents(value);

		_button = createButton(_editor);
		_button.setFont(font);

		_button.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (e.character == '\u001b') { // Escape
					fireCancelEditor();
				}
			}
		});

		_button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				Object newValue = openDialogBox(_editor);
				if (newValue != null) {
					boolean newValidState = isCorrect(newValue);
					if (newValidState) {
						markDirty();
						doSetValue(newValue);
					} else {
						// try to insert the current value into the error
						// message.
						setErrorMessage(MessageFormat.format(getErrorMessage(),
								new Object[] { newValue.toString() }));
					}
					fireApplyEditorValue();
				}
			}
		});

		setValueValid(true);

		return _editor;
	}

	/*
	 * (non-Javadoc) Method declared on CellEditor. The focus is set to the cell
	 * editor's button.
	 */
	protected void doSetFocus() {
		if (_wrapped != null && _wrapped.getControl() != null
				&& !_wrapped.getControl().isDisposed()) {
			_wrapped.setFocus();
		} else {
			_button.setFocus();
		}
	}

	/*
	 * (non-Javadoc) Method declared on CellEditor.
	 */
	protected Object doGetValue() {
		return _wrapped.getValue();
	}

	/*
	 * (non-Javadoc) Method declared on CellEditor.
	 */
	protected void doSetValue(Object value) {
		if (_wrapped != null) {
			_wrapped.setValue(value);
		}
	}

	/**
	 * Opens a dialog box under the given parent control and returns the
	 * dialog's value when it closes, or <code>null</code> if the dialog was
	 * cancelled or no selection was made in the dialog.
	 * <p>
	 * This framework method must be implemented by concrete subclasses. It is
	 * called when the user has pressed the button and the dialog box must pop
	 * up.
	 * </p>
	 * 
	 * @param cellEditorWindow
	 *            the parent control cell editor's window so that a subclass can
	 *            adjust the dialog box accordingly
	 * @return the selected value, or <code>null</code> if the dialog was
	 *         cancelled or no selection was made in the dialog
	 */
	protected abstract Object openDialogBox(Control cellEditorWindow);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.CellEditor#activate()
	 */
	public void activate() {
		super.activate();
		_wrapped.activate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.CellEditor#deactivate()
	 */
	public void deactivate() {
		super.deactivate();
		// if (_wrapped != null)
		// {
		// _wrapped.deactivate();
		// }
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.CellEditor#dispose()
	 */
	public void dispose() {
		_wrapped.dispose();
		super.dispose();
	}

}
