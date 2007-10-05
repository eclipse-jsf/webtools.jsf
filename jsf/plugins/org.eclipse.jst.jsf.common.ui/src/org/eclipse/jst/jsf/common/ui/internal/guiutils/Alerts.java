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
package org.eclipse.jst.jsf.common.ui.internal.guiutils;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * This class will provide UI alert dialogs to the user. These will
 * automatically get the message from the plugin's resource bundle.
 * 
 * NOTE: there are some generic exception methods included in this file. They
 * need resource bundles also, and they make the alerts bettern when the
 * exception is caught at a later stage.
 * 
 * @author mengbo
 */
public class Alerts {
	// this is used for junit testing to turn off all alerts and return the
	// default on yes/no dialogs.
	private static boolean _noAlerts = false;

	private ResourceBundle _rb;

	private String _pluginId;

	/**
	 * Construct a alerts with the provided attributes. This constructor should
	 * be used from the root plugin that has access to the resource bundle for
	 * this plugin.
	 * 
	 * @param plugin 
	 * @param rb
	 *            the resource bundle to use.
	 */
	public Alerts(AbstractUIPlugin plugin, ResourceBundle rb) {
		_rb = rb;
		if (_rb == null) {
			throw new NullPointerException(
					"No resource bundle was provided to the Alerts.");
		}
		if (plugin == null) {
			throw new NullPointerException(
					"No plugin was provided to the Alerts.");
		}
		_pluginId = plugin.getBundle().getSymbolicName();
	}

	/**
	 * Returns the text entered by a user in a simple request dialog.
	 * 
	 * @param titleKey -
	 *            the title resource bundle key for the message
	 * @param msgKey -
	 *            message resource bundle key for the message displayed to the
	 *            user
	 * @param initialValue -
	 *            initial value in the text area.
	 * @param validator -
	 *            something that checks for validity on the name.
	 * @return null is returned if the user hits cancel, otherwise it is the
	 *         text they entered.
	 */
	public String getInput(String titleKey, String msgKey, String initialValue,
			IInputValidator validator) {
		if (_noAlerts) {
			return null;
		}

		InputDialog inDialog = new InputDialog(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getShell(),
				getResourceString(titleKey), getResourceString(msgKey),
				initialValue, validator);
		inDialog.open();
		return inDialog.getValue();
	}

	/**
	 * Displays an Error message to the user.
	 * 
	 * @param titleKey -
	 *            the title resource bundle key for the message
	 * @param msgKey -
	 *            message resource bundle key for the message displayed to the
	 *            user
	 */
	public void error(String titleKey, String msgKey) {
		if (_noAlerts) {
			return;
		}

		MessageDialog.openError(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getShell(),
				getResourceString(titleKey), getResourceString(msgKey));
	}

	/**
	 * Displays an Error message to the user.
	 * 
	 * @param titleKey -
	 *            the title resource bundle key for the message
	 * @param msgKey -
	 *            message resource bundle key for the message displayed to the
	 *            user
	 * @param arg0 -
	 *            arg to place into the resource bundle message.
	 */
	public void error(String titleKey, String msgKey, Object arg0) {
		if (_noAlerts) {
			return;
		}

		Object[] args = new Object[1];
		args[0] = arg0;

		MessageFormat formatter = new MessageFormat(getResourceString(msgKey));

		MessageDialog.openError(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getShell(),
				getResourceString(titleKey), formatter.format(args));
	}

	/**
	 * Displays an Error message to the user.
	 * 
	 * @param titleKey -
	 *            the title resource bundle key for the message
	 * @param msgKey -
	 *            message resource bundle key for the message displayed to the
	 *            user
	 * @param arg0 -
	 *            arg to place into the resource bundle message.
	 * @param arg1 -
	 *            arg to place into the resource bundle message.
	 */
	public void error(String titleKey, String msgKey, Object arg0, Object arg1) {
		if (_noAlerts) {
			return;
		}

		Object[] args = new Object[2];
		args[0] = arg0;
		args[1] = arg1;

		MessageFormat formatter = new MessageFormat(getResourceString(msgKey));

		MessageDialog.openError(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getShell(),
				getResourceString(titleKey), formatter.format(args));
	}

	/**
	 * Displays an Error message to the user.
	 * 
	 * @param titleKey -
	 *            the title resource bundle key for the message
	 * @param msgKey -
	 *            message resource bundle key for the message displayed to the
	 *            user
	 * @param arg0 -
	 *            arg to place into the resource bundle message.
	 * @param arg1 -
	 *            arg to place into the resource bundle message.
	 * @param arg2 -
	 *            arg to place into the resource bundle message.
	 */
	public void error(String titleKey, String msgKey, Object arg0, Object arg1,
			Object arg2) {
		if (_noAlerts) {
			return;
		}

		Object[] args = new Object[3];
		args[0] = arg0;
		args[1] = arg1;
		args[2] = arg2;

		MessageFormat formatter = new MessageFormat(getResourceString(msgKey));

		MessageDialog.openError(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getShell(),
				getResourceString(titleKey), formatter.format(args));
	}

	/**
	 * Displays an Error message to the user.
	 * 
	 * @param titleKey -
	 *            the title resource bundle key for the message
	 * @param msgKey -
	 *            message resource bundle key for the message displayed to the
	 *            user
	 * @param arg0 -
	 *            arg to place into the resource bundle message.
	 * @param arg1 -
	 *            arg to place into the resource bundle message.
	 * @param arg2 -
	 *            arg to place into the resource bundle message.
	 * @param arg3 -
	 *            arg to place into the resource bundle message.
	 */
	public void error(String titleKey, String msgKey, Object arg0, Object arg1,
			Object arg2, Object arg3) {
		if (_noAlerts) {
			return;
		}

		Object[] args = new Object[4];
		args[0] = arg0;
		args[1] = arg1;
		args[2] = arg2;
		args[3] = arg3;

		MessageFormat formatter = new MessageFormat(getResourceString(msgKey));

		MessageDialog.openError(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getShell(),
				getResourceString(titleKey), formatter.format(args));
	}

	/**
	 * Displays an Error message to the user.
	 * 
	 * @param shell -
	 *            the shell for the error dialog
	 * @param titleKey -
	 *            the title resource bundle key for the message
	 * @param msgKey -
	 *            message resource bundle key for the message displayed to the
	 *            user
	 */
	public void error(Shell shell, String titleKey, String msgKey) {
		if (_noAlerts) {
			return;
		}

		MessageDialog.openError(shell, getResourceString(titleKey),
				getResourceString(msgKey));
	}

	/**
	 * Displays an Error message to the user with a status using the Display
	 * background thread.
	 * 
	 * @param titleKey -
	 *            the title resource bundle key for the message
	 * @param msgKey -
	 *            message resource bundle key for the message displayed to the
	 *            user
	 */
	public void detailError(String titleKey, String msgKey) {
		if (_noAlerts) {
			return;
		}

		final IStatus fstatus = new Status(IStatus.ERROR, _pluginId,
				IStatus.OK, getResourceString(msgKey), null);
		final String ftitle = getResourceString(titleKey);

		Display display = getStandardDisplay();
		display.asyncExec(new Runnable() {
			public void run() {
				ErrorDialog.openError(null, ftitle, null, fstatus);
			}
		});
	}

	/**
	 * Displays an Error message to the user with a status using the Display
	 * background thread. This will give the details button.
	 * 
	 * @param titleKey -
	 *            the title resource bundle key for the message
	 * @param msgKey -
	 *            message resource bundle key for the message displayed to the
	 *            user
	 * @param tt -
	 *            exception to place in the details.
	 */
	public void detailError(String titleKey, String msgKey, Throwable tt) {
		if (_noAlerts) {
			return;
		}

		internalDetailError(getResourceString(titleKey),
				getResourceString(msgKey), tt);
	}

	/**
	 * Displays an Error message to the user with a status using the Display
	 * background thread. This will give the details button.
	 * 
	 * @param titleKey -
	 *            the title resource bundle key for the message
	 * @param msgKey -
	 *            message resource bundle key for the message displayed to the
	 *            user
	 * @param arg0 -
	 *            arg to place into the resource bundle message.
	 * @param tt -
	 *            exception to place in the details.
	 */
	public void detailError(String titleKey, String msgKey, Object arg0,
			Throwable tt) {
		if (_noAlerts) {
			return;
		}

		Object[] args = new Object[1];
		args[0] = arg0;

		MessageFormat formatter = new MessageFormat(getResourceString(msgKey));

		internalDetailError(getResourceString(titleKey),
				formatter.format(args), tt);
	}

	/**
	 * Displays an Error message to the user with a status using the Display
	 * background thread. This will give the details button.
	 * 
	 * @param titleKey -
	 *            the title resource bundle key for the message
	 * @param msgKey -
	 *            message resource bundle key for the message displayed to the
	 *            user
	 * @param arg0 -
	 *            arg to place into the resource bundle message.
	 * @param arg1 -
	 *            arg to place into the resource bundle message.
	 * @param tt -
	 *            exception to place in the details.
	 */
	public void detailError(String titleKey, String msgKey, Object arg0,
			Object arg1, Throwable tt) {
		if (_noAlerts) {
			return;
		}

		Object[] args = new Object[1];
		args[0] = arg0;
		args[1] = arg1;

		MessageFormat formatter = new MessageFormat(getResourceString(msgKey));

		internalDetailError(getResourceString(titleKey),
				formatter.format(args), tt);
	}

	/**
	 * Displays an Error message to the user with a status using the Display
	 * background thread. This will give the details button.
	 * 
	 * @param title -
	 *            the title message
	 * @param message -
	 *            message displayed to the user
	 */
	private void internalDetailError(String title, String msg, Throwable tt) {
		if (_noAlerts) {
			return;
		}

		if (tt instanceof InvocationTargetException) {
			tt = ((InvocationTargetException) tt).getTargetException();
		}

		IStatus status = null;
		if (tt instanceof CoreException) {
			status = ((CoreException) tt).getStatus();

			if ((status instanceof MultiStatus) == false) {
				// convert to a multistatus to show the exception details.

				Throwable th = status.getException();
				if (th == null) {
					th = tt;
				}

				status = buildStackTraceStatus(IStatus.ERROR, status
						.getPlugin(), status.getMessage(), th);
			}
		} else {
			status = buildStackTraceStatus(IStatus.ERROR, _pluginId, tt
					.getMessage(), tt);
		}

		final String ftitle = title;
		final IStatus fstatus = status;
		final String fmsg = msg;
		Display display = getStandardDisplay();
		display.asyncExec(new Runnable() {
			public void run() {
				ErrorDialog.openError(null, ftitle, fmsg, fstatus);
			}
		});
	}

	/**
	 * Builds a multistatus, so that the stack trace shows up in the alery
	 * message for detailed alerts.
	 * 
	 * @param code
	 * @param pluginId
	 * @param message
	 * @param tt
	 * @return the result of the operation
	 */
	public IStatus buildStackTraceStatus(int code, String pluginId,
			String message, Throwable tt) {
		if (tt == null) {
			return new Status(code, pluginId, IStatus.OK, message, tt);
		}

		MultiStatus mstat = new MultiStatus(pluginId, code, message, tt);

		StackTraceElement[] trace = tt.getStackTrace();

		mstat.add(new Status(code, pluginId, IStatus.OK, tt.toString(), null));
		for (int ii = 0; ii < trace.length; ii++) {
			mstat.add(new Status(code, pluginId, IStatus.OK, trace[ii]
					.toString(), null));
		}
		return mstat;
	}

	/**
	 * Displays an Error message to the user with a status using the Display
	 * background thread. This will give the details button.
	 * 
	 * @param status -
	 *            the status
	 * @param title -
	 *            the title of the message
	 */
	public void detailError(String title, IStatus status) {
		if (_noAlerts) {
			return;
		}

		final IStatus fstatus = status;
		final String ftitle = title;
		Display display = getStandardDisplay();
		display.asyncExec(new Runnable() {
			public void run() {
				ErrorDialog.openError(null, ftitle, null, fstatus);
			}
		});
	}

	/**
	 * Displays an information message to the user.
	 * 
	 * @param titleKey -
	 *            the title resource bundle key for the message
	 * @param msgKey -
	 *            message resource bundle key for the message displayed to the
	 *            user
	 */
	public void info(String titleKey, String msgKey) {
		if (_noAlerts) {
			return;
		}

		MessageDialog.openInformation(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getShell(),
				getResourceString(titleKey), getResourceString(msgKey));
	}

	/**
	 * Displays an information message to the user.
	 * 
	 * @param titleKey -
	 *            the title resource bundle key for the message
	 * @param msgKey -
	 *            message resource bundle key for the message displayed to the
	 *            user
	 * @param arg0 -
	 *            arg to place into the resource bundle message.
	 */
	public void info(String titleKey, String msgKey, Object arg0) {
		if (_noAlerts) {
			return;
		}

		Object[] args = new Object[1];
		args[0] = arg0;

		MessageFormat formatter = new MessageFormat(getResourceString(msgKey));

		MessageDialog.openInformation(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getShell(),
				getResourceString(titleKey), formatter.format(args));
	}

	/**
	 * Displays an confirmation message to the user.
	 * 
	 * @param titleKey -
	 *            the title resource bundle key for the message
	 * @param msgKey -
	 *            message resource bundle key for the message displayed to the
	 *            user
	 * @return true if the user presses the OK button, false otherwise
	 */
	public boolean confirm(String titleKey, String msgKey) {
		if (_noAlerts) {
			return true;
		}

		return MessageDialog.openConfirm(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getShell(),
				getResourceString(titleKey), getResourceString(msgKey));
	}

	/**
	 * Displays an confirmation message to the user.
	 * 
	 * @param titleKey -
	 *            the title resource bundle key for the message
	 * @param msgKey -
	 *            message resource bundle key for the message displayed to the
	 *            user
	 * @param arg0 -
	 *            arg to place into the resource bundle message.
	 * @return true if the user presses the OK button, false otherwise
	 */
	public boolean confirm(String titleKey, String msgKey, Object arg0) {
		if (_noAlerts) {
			return true;
		}

		Object[] args = new Object[1];
		args[0] = arg0;

		MessageFormat formatter = new MessageFormat(getResourceString(msgKey));

		return MessageDialog.openConfirm(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getShell(),
				getResourceString(titleKey), formatter.format(args));
	}

	/**
	 * Displays an question message to the user.
	 * 
	 * @param titleKey -
	 *            the title resource bundle key for the message
	 * @param msgKey -
	 *            message resource bundle key for the message displayed to the
	 *            user
	 * @return true if the user presses the "yes" button, false otherwise
	 */
	public boolean question(String titleKey, String msgKey) {
		if (_noAlerts) {
			return true;
		}

		return MessageDialog.openQuestion(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getShell(),
				getResourceString(titleKey), getResourceString(msgKey));
	}

	/**
	 * Displays an question message to the user.
	 * 
	 * @param titleKey -
	 *            the title resource bundle key for the message
	 * @param msgKey -
	 *            message resource bundle key for the message displayed to the
	 *            user
	 * @param arg0 -
	 *            arg to place into the resource bundle message.
	 * @return true if the user presses the "yes" button, false otherwise
	 */
	public boolean question(String titleKey, String msgKey, Object arg0) {
		if (_noAlerts) {
			return true;
		}

		Object[] args = new Object[1];
		args[0] = arg0;

		MessageFormat formatter = new MessageFormat(getResourceString(msgKey));

		return MessageDialog.openQuestion(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getShell(),
				getResourceString(titleKey), formatter.format(args));
	}

	/**
	 * Displays an warning message to the user.
	 * 
	 * @param titleKey -
	 *            the title resource bundle key for the message
	 * @param msgKey -
	 *            message resource bundle key for the message displayed to the
	 *            user
	 */
	public void warning(String titleKey, String msgKey) {
		if (_noAlerts) {
			return;
		}

		MessageDialog.openWarning(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getShell(),
				getResourceString(titleKey), getResourceString(msgKey));
	}

	/**
	 * Displays an warning message to the user.
	 * 
	 * @param titleKey -
	 *            the title resource bundle key for the message
	 * @param msgKey -
	 *            message resource bundle key for the message displayed to the
	 *            user
	 * @param arg0 -
	 *            arg to place into the resource bundle message.
	 */
	public void warning(String titleKey, String msgKey, Object arg0) {
		if (_noAlerts) {
			return;
		}

		Object[] args = new Object[1];
		args[0] = arg0;

		MessageFormat formatter = new MessageFormat(getResourceString(msgKey));

		MessageDialog.openWarning(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getShell(),
				getResourceString(titleKey), formatter.format(args));
	}

	/**
	 * Returns the standard display to be used. The method first checks, if the
	 * thread calling this method has an associated dispaly. If so, this display
	 * is returned. Otherwise the method returns the default display.
	 * 
	 * TODO: should probably use the workbench display
	 */
	private Display getStandardDisplay() {
		Display display = Display.getCurrent();
		if (display == null) {
			display = Display.getDefault();
		}
		return display;
	}

	/**
	 * Returns the string from the resource bundle, or 'key' if not found.
	 */
	private String getResourceString(String key) {
		if (key == null) {
			return null;
		}

		try {
			return _rb.getString(key);
		} catch (MissingResourceException ee) {
			return key;
		}
	}

	/**
	 * Throw a core exception built around the message with the correct plugin
	 * id and resource bundle string.
	 * 
	 * @param msgKey -
	 *            message resource bundle key for the message displayed to the
	 *            user
	 * @throws CoreException
	 */
	public void throwCoreException(String msgKey) throws CoreException {
		IStatus status = new Status(IStatus.ERROR, _pluginId, IStatus.OK,
				getResourceString(msgKey), null);
		throw new CoreException(status);
	}

	/**
	 * Throw a core exception built around the message with the correct plugin
	 * id and resource bundle string.
	 * 
	 * @param ee -
	 *            the real exception.
	 * @throws CoreException
	 */
	public void throwCoreException(Throwable ee) throws CoreException {
		IStatus status = new Status(IStatus.ERROR, _pluginId, IStatus.OK, ee
				.getMessage(), ee);
		throw new CoreException(status);
	}

	/**
	 * Throw a core exception built around the message with the correct plugin
	 * id and resource bundle string.
	 * 
	 * @param msgKey -
	 *            message resource bundle key for the message displayed to the
	 *            user
	 * @param ee -
	 *            the real exception.
	 * @throws CoreException
	 */
	public void throwCoreException(String msgKey, Throwable ee)
			throws CoreException {
		IStatus status = new Status(IStatus.ERROR, _pluginId, IStatus.OK,
				getResourceString(msgKey), ee);
		throw new CoreException(status);
	}

	/**
	 * Throw a core exception built around the message with the correct plugin
	 * id and resource bundle string.
	 * 
	 * @param msgKey -
	 *            message resource bundle key for the message displayed to the
	 *            user
	 * @param arg0 -
	 *            arg to place into the resource bundle message.
	 * @throws CoreException
	 */
	public void throwCoreException(String msgKey, Object arg0)
			throws CoreException {
		Object[] args = new Object[1];
		args[0] = arg0;

		MessageFormat formatter = new MessageFormat(getResourceString(msgKey));

		IStatus status = new Status(IStatus.ERROR, _pluginId, IStatus.OK,
				formatter.format(args), null);
		throw new CoreException(status);
	}

	/**
	 * Throw a core exception built around the message with the correct plugin
	 * id and resource bundle string.
	 * 
	 * @param msgKey -
	 *            message resource bundle key for the message displayed to the
	 *            user
	 * @param arg0 -
	 *            arg to place into the resource bundle message.
	 * @param ee -
	 *            the real exception.
	 * @throws CoreException
	 */
	public void throwCoreException(String msgKey, Object arg0, Throwable ee)
			throws CoreException {
		Object[] args = new Object[1];
		args[0] = arg0;

		MessageFormat formatter = new MessageFormat(getResourceString(msgKey));

		IStatus status = new Status(IStatus.ERROR, _pluginId, IStatus.OK,
				formatter.format(args), ee);
		throw new CoreException(status);
	}

	/**
	 * Throw a core exception built around the message with the correct plugin
	 * id and resource bundle string.
	 * 
	 * @param msgKey -
	 *            message resource bundle key for the message displayed to the
	 *            user
	 * @param arg0 -
	 *            arg to place into the resource bundle message.
	 * @param arg1 -
	 *            arg to place into the resource bundle message.
	 * @throws CoreException
	 */
	public void throwCoreException(String msgKey, Object arg0, Object arg1)
			throws CoreException {
		Object[] args = new Object[2];
		args[0] = arg0;
		args[1] = arg1;

		MessageFormat formatter = new MessageFormat(getResourceString(msgKey));

		IStatus status = new Status(IStatus.ERROR, _pluginId, IStatus.OK,
				formatter.format(args), null);
		throw new CoreException(status);
	}

	/**
	 * Throw a core exception built around the message with the correct plugin
	 * id and resource bundle string.
	 * 
	 * @param msgKey -
	 *            message resource bundle key for the message displayed to the
	 *            user
	 * @param arg0 -
	 *            arg to place into the resource bundle message.
	 * @param arg1 -
	 *            arg to place into the resource bundle message.
	 * @param ee -
	 *            the real exception.
	 * @throws CoreException
	 */
	public void throwCoreException(String msgKey, Object arg0, Object arg1,
			Throwable ee) throws CoreException {
		Object[] args = new Object[2];
		args[0] = arg0;
		args[1] = arg1;

		MessageFormat formatter = new MessageFormat(getResourceString(msgKey));

		IStatus status = new Status(IStatus.ERROR, _pluginId, IStatus.OK,
				formatter.format(args), ee);
		throw new CoreException(status);
	}
}
