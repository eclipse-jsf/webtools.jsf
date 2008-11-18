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
package org.eclipse.jst.pagedesigner.preview;

import java.io.File;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.widgets.Composite;

/**
 * Models the windows IE browser for preview
 *
 */
public class WindowsIEBrowser implements ProgressListener {
	private Browser _browser;

	/**
	 * @param composite
	 * @param i
	 */
	public void create(Composite composite, int i) {
		_browser = new Browser(composite, i);
		_browser.addProgressListener(this);
	}

	/**
	 * @param file
	 */
	public synchronized void loadFile(File file) {
		if (_browser == null) {
			return;
		}
        String s = "file:" + file.getAbsolutePath(); //$NON-NLS-1$
        _browser.setUrl(s);
        return;
	}

	/**
	 * dispose the instance
	 */
	public void dispose() {
		if (_browser == null) {
			return;
		}
        _browser.dispose();
        _browser = null;
        return;
	}

	public void changed(ProgressEvent progressevent) {
        // do nothing
	}

	public void completed(ProgressEvent progressevent) {
	    // do nothing
	}

	/**
	 * @return the underlying swt Browser instance
	 */
	public Browser getBrowser() {
		return _browser;
	}

}
