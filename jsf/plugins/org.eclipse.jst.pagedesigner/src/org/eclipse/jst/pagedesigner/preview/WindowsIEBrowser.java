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

public class WindowsIEBrowser implements ProgressListener {
	private Browser _browser;

	private File _file;

	public void create(Composite composite, int i) {
		_browser = new Browser(composite, i);
		_browser.addProgressListener(this);
	}

	public synchronized void loadFile(File file) {
		if (_browser == null) {
			return;
		}
        _file = file;
        String s = "file:" + file.getAbsolutePath();
        _browser.setUrl(s);
        return;
	}

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

	public synchronized void completed(ProgressEvent progressevent) {
		if (_file != null) {
			// XXX: don't delete, for debug purpose.
			// _file.delete();
			// _file = null;
		}
	}

	/**
	 * 
	 */
	public Browser getBrowser() {
		return _browser;
	}

}
