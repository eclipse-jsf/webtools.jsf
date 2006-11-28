/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.ui.internal.logging;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.Bundle;

public class Logger {
	private ResourceBundle resourceBundle;

	private ILog log;

	private String bundleId;

	public Logger(Bundle bundle, ResourceBundle resourceBundle) {
		log = Platform.getLog(bundle);
		this.resourceBundle = resourceBundle;
		bundleId = bundle.getSymbolicName();
		if (resourceBundle == null) {
			IStatus status = new Status(IStatus.ERROR, bundleId, IStatus.OK,
					"The resource Bundle for logger could not be null!", null);
			log.log(status);
		}
	}
	public void info(String key, String arg0) {
		Object[] args = new Object[1];
		args[0] = arg0;

		MessageFormat formatter = new MessageFormat(resourceBundle
				.getString(key));
		String message = formatter.format(args);
		IStatus status = new Status(IStatus.INFO, bundleId, IStatus.OK,
				message, null);
		log.log(status);
	}

	public void info(String message) {
		IStatus status = new Status(IStatus.INFO, bundleId, IStatus.OK,
				message, null);
		log.log(status);
	}

	public void info(String key, Throwable e) {
		String message = resourceBundle.getString(key);
		IStatus status = new Status(IStatus.INFO, bundleId, IStatus.OK,
				message, e);
		log.log(status);
	}

	public void info(String key, String arg0, Throwable e) {
		Object[] args = new Object[1];
		args[0] = arg0;

		MessageFormat formatter = new MessageFormat(resourceBundle
				.getString(key));
		String message = formatter.format(args);
		IStatus status = new Status(IStatus.INFO, bundleId, IStatus.OK,
				message, null);
		log.log(status);
	}
	
	public void info(String key, String arg0, String arg1, Throwable e) {
		Object[] args = new Object[1];
		args[0] = arg0;
		args[1] = arg1;

		MessageFormat formatter = new MessageFormat(resourceBundle
				.getString(key));
		String message = formatter.format(args);
		IStatus status = new Status(IStatus.INFO, bundleId, IStatus.OK,
				message, e);
		log.log(status);
	}

    public void error(String key) {
        String message = resourceBundle.getString(key);
        IStatus status = new Status(IStatus.ERROR, bundleId, IStatus.OK,
                message, null);
        log.log(status);
    }

	public void error(Throwable e) {
		IStatus status = new Status(IStatus.ERROR, bundleId, IStatus.OK, "", e);
		log.log(status);
	}

	public void error(String key, Throwable e) {
		String message = resourceBundle.getString(key);
		IStatus status = new Status(IStatus.ERROR, bundleId, IStatus.OK,
				message, e);
		log.log(status);
	}
	
	public void error(String key, String arg0) {
		Object[] args = new Object[1];
		args[0] = arg0;

		MessageFormat formatter = new MessageFormat(resourceBundle
				.getString(key));
		String message = formatter.format(args);
		IStatus status = new Status(IStatus.ERROR, bundleId, IStatus.OK,
				message, null);
		log.log(status);
	}

	public void error(String key, String arg, Throwable e) {
		Object[] args = new Object[1];
		args[0] = arg;

		MessageFormat formatter = new MessageFormat(resourceBundle
				.getString(key));
		String message = formatter.format(args);
		IStatus status = new Status(IStatus.ERROR, bundleId, IStatus.OK,
				message, e);
		log.log(status);
	}

	public void error(String key, String arg0, String arg1, Throwable e) {
		Object[] args = new Object[1];
		args[0] = arg0;
		args[1] = arg1;

		MessageFormat formatter = new MessageFormat(resourceBundle
				.getString(key));
		String message = formatter.format(args);
		IStatus status = new Status(IStatus.ERROR, bundleId, IStatus.OK,
				message, e);
		log.log(status);
	}
}
