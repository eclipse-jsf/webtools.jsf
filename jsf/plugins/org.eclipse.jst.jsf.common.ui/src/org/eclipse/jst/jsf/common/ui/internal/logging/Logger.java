/*******************************************************************************
 * Copyright (c) 2004, 2008 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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

/**
 * A resource bundle driven logger
 *
 */
public class Logger {
	private ResourceBundle resourceBundle;

	private ILog log;

	private String bundleId;

	/**
	 * @param bundle
	 * @param resourceBundle
	 */
	public Logger(Bundle bundle, ResourceBundle resourceBundle) {
		log = Platform.getLog(bundle);
		this.resourceBundle = resourceBundle;
		bundleId = bundle.getSymbolicName();
		if (resourceBundle == null) {
			IStatus status = new Status(IStatus.ERROR, bundleId, IStatus.OK,
					"The resource Bundle for logger could not be null!", null); //$NON-NLS-1$
			log.log(status);
		}
	}
	/**
	 * @param key
	 * @param arg0
	 */
	public void info(String key, String arg0) {
		Object[] args = new Object[1];
		args[0] = arg0;

		MessageFormat formatter = new MessageFormat(getString(key));
		String message = formatter.format(args);
		IStatus status = new Status(IStatus.INFO, bundleId, IStatus.OK,
				message, null);
		log.log(status);
	}

	/**
	 * @param message
	 */
	public void info(String message) {
		IStatus status = new Status(IStatus.INFO, bundleId, IStatus.OK,
				message, null);
		log.log(status);
	}

	/**
	 * @param key
	 * @param e
	 */
	public void info(String key, Throwable e) {
		String message = getString(key);
		IStatus status = new Status(IStatus.INFO, bundleId, IStatus.OK,
				message, e);
		log.log(status);
	}

	/**
	 * @param key
	 * @param arg0
	 * @param e
	 */
	public void info(String key, String arg0, Throwable e) {
		Object[] args = new Object[1];
		args[0] = arg0;

		MessageFormat formatter = new MessageFormat(getString(key));
		String message = formatter.format(args);
		IStatus status = new Status(IStatus.INFO, bundleId, IStatus.OK,
				message, null);
		log.log(status);
	}
	
	/**
	 * @param key
	 * @param arg0
	 * @param arg1
	 * @param e
	 */
	public void info(String key, String arg0, String arg1, Throwable e) {
		Object[] args = new Object[1];
		args[0] = arg0;
		args[1] = arg1;

		MessageFormat formatter = new MessageFormat(getString(key));
		String message = formatter.format(args);
		IStatus status = new Status(IStatus.INFO, bundleId, IStatus.OK,
				message, e);
		log.log(status);
	}

    /**
     * @param key
     */
    public void error(String key) {
        
        String message = getString(key);
        IStatus status = new Status(IStatus.ERROR, bundleId, IStatus.OK,
                message, null);
        log.log(status);
    }

	/**
	 * @param e
	 */
	public void error(Throwable e) {
		IStatus status = new Status(IStatus.ERROR, bundleId, IStatus.OK, "", e); //$NON-NLS-1$
		log.log(status);
	}

	/**
	 * @param key
	 * @param e
	 */
	public void error(String key, Throwable e) {
		String message = getString(key);
		IStatus status = new Status(IStatus.ERROR, bundleId, IStatus.OK,
				message, e);
		log.log(status);
	}
	
	/**
	 * @param key
	 * @param arg0
	 */
	public void error(String key, String arg0) {
		Object[] args = new Object[1];
		args[0] = arg0;

		MessageFormat formatter = new MessageFormat(getString(key));
		String message = formatter.format(args);
		IStatus status = new Status(IStatus.ERROR, bundleId, IStatus.OK,
				message, null);
		log.log(status);
	}

	/**
	 * @param key
	 * @param arg
	 * @param e
	 */
	public void error(String key, String arg, Throwable e) {
		Object[] args = new Object[1];
		args[0] = arg;

		MessageFormat formatter = new MessageFormat(getString(key));
		String message = formatter.format(args);
		IStatus status = new Status(IStatus.ERROR, bundleId, IStatus.OK,
				message, e);
		log.log(status);
	}

	/**
	 * @param key
	 * @param arg0
	 * @param arg1
	 * @param e
	 */
	public void error(String key, String arg0, String arg1, Throwable e) {
		Object[] args = new Object[1];
		args[0] = arg0;
		args[1] = arg1;

		MessageFormat formatter = new MessageFormat(getString(key));
		String message = formatter.format(args);
		IStatus status = new Status(IStatus.ERROR, bundleId, IStatus.OK,
				message, e);
		log.log(status);
	}
    
    /**
     * @param key
     * @return the resource for the key, or an error message if
     * resourceBundle.getString(key) throws an Exception
     */
    private String getString(String key)
    {
        try
        {
            return resourceBundle.getString(key);
        }
        // suppress non-error exceptions so that the logging operation
        // itself (usually called in response to an exception) does not
        // throw a new exception
        catch(Exception e)
        {
            return "!!missing resource: " + key + "!!"; //$NON-NLS-1$ //$NON-NLS-2$
        }
    }
}
