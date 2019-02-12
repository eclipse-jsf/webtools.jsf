/*******************************************************************************
 * Copyright (c) 2006, 2008 Sybase, Inc. and others.
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
package org.eclipse.jst.jsf.common.ui.internal.guiutils;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.jst.jsf.common.ui.JSFUICommonPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.LocationAdapter;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.browser.OpenWindowListener;
import org.eclipse.swt.browser.StatusTextEvent;
import org.eclipse.swt.browser.StatusTextListener;
import org.eclipse.swt.browser.WindowEvent;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

/**
 * Brings up a browser into a separate window.
 * 
 * @author mengbo.  
 * 
 * Unused???
 */
public class BrowserWindow extends ApplicationWindow {

	private static final String RELOAD_ITEM_IMAGE_FILE = "reload_nav_16.gif"; //$NON-NLS-1$

	private static final String REFRESH_ITEM_IMAGE_FILE = "refresh_nav_16.gif"; //$NON-NLS-1$

	private static final String STOP_ITEM_IMAGE_FILE = "stop_nav_16.gif"; //$NON-NLS-1$

	private static final String FORWARD_ITEM_IMAGE_FILE = "forward_nav_16.gif"; //$NON-NLS-1$

	private static final String BACK_ITEM_IMAGE_FILE = "back_nav_16.gif"; //$NON-NLS-1$

	
	/**
	 * Command to send to the browser.
	 */
	private static final int BROWSER_CMD_BACK = 1;

	private static final int BROWSER_CMD_FORWARD = 2;

	private static final int BROWSER_CMD_STOP = 3;

	private static final int BROWSER_CMD_REFRESH = 4;

	private static final int BROWSER_CMD_RELOAD = 5;

	private boolean _bIncludeToolbar;

	private boolean _bPopups;

	private String _title;

	private ScrolledComposite _scomp;

	private Composite _browserComposite;

	private Browser _browser;

	private String _startLocation;

	private String _startContent;

	// these items are browser context sensitive.
	private ToolItem _backItem;

	private ToolItem _forwardItem;

	/**
	 * @param parentShell
	 *            owner of this window.
	 */
	public BrowserWindow(Shell parentShell) {
		this(parentShell, false, null, false);
	}

	/**
	 * @param parentShell
	 *            owner of this window.
	 * @param bPopups
	 *            allow popup windows?
	 * @param title
	 *            Tittle on window.
	 */
	public BrowserWindow(Shell parentShell, boolean bPopups, String title) {
		this(parentShell, bPopups, title, false);
	}

	/**
	 * @param parentShell
	 *            owner of this window.
	 * @param bPopups
	 *            allow popup windows?
	 * @param title
	 *            Tittle on window.
	 * @param bModal
	 *            pretend to a be a dialog?
	 */
	public BrowserWindow(Shell parentShell, boolean bPopups, String title,
			boolean bModal) {
		super(parentShell);
		addStatusLine();
		_bPopups = bPopups;
		_title = title;
		includeToolbar(true);

		if (bModal && (parentShell != null)) {
			// APPLICATION_MODAL, MODELESS, PRIMARY_MODAL, SYSTEM_MODAL
			setShellStyle(getShellStyle() | SWT.APPLICATION_MODAL);
			setBlockOnOpen(true);
		}
	}

	/**
	 * Default is true, set whether to include the toolbar at the top. This must
	 * be called before the window is opened.
	 * 
	 * @param bIncludeToolbar
	 */
	public void includeToolbar(boolean bIncludeToolbar) {
		_bIncludeToolbar = bIncludeToolbar;
	}

	/**
	 * @param title
	 */
	public void setTitle(String title) {
		if ((title != null) && (super.getShell() != null)) {
			super.getShell().setText(title);
		}
	}

	/**
	 * @param startLocation
	 */
	/**
	 * @param startLocation
	 */
	public void setStartLocation(String startLocation) {
		_startLocation = startLocation;
	}

	/**
	 * @param startContent
	 */
	public void setStartContent(String startContent) {
		_startContent = startContent;
	}

	/**
	 * @return the browser object
	 */
	public Browser getBrowser() {
		return _browser;
	}

	protected Control createContents(Composite parent) {
		// Create the scroll composite as the one inside the parent.
		_scomp = new ScrolledComposite(parent, SWT.V_SCROLL | SWT.H_SCROLL);

		// Create the composite that goes inside the scroller. We will use this
		// for
		// everything else from here.
		Composite innerComposite = SWTUtils.createComposite(_scomp, 1, 10, -1);
		_scomp.setContent(innerComposite);
		ControlListener listener = new ControlAdapter() {
			// this will handle resizing the browser when the window is resized.
			public void controlResized(ControlEvent e) {
				Point pt = _scomp.getParent().getSize();
				Rectangle rect = _browserComposite.getBounds();

				pt.x -= rect.x;
				pt.y -= rect.y;

				// spacing offsets in the composites.
				pt.x -= 10;
				pt.y -= 60; // need status bar at bottom...30 more than normal.

				// do the sanity check here since it prevents further checks.
				if ((pt.x <= 0) || (pt.y <= 0)) {
					return;
				}

				Rectangle innerRect = _scomp.getContent().getBounds();
				Rectangle outerRect = _scomp.getBounds();

				// substract if the scroll bars are visible.
				if (innerRect.width > outerRect.width) {
					pt.y -= _scomp.getHorizontalBar().getSize().y;
				}
				if (innerRect.height > outerRect.height) {
					pt.x -= _scomp.getVerticalBar().getSize().x;
				}

				_browserComposite.setSize(pt);
				_browserComposite.getParent().layout(true);
			}
		};
		_scomp.addControlListener(listener);

		if (_bIncludeToolbar) {
			// ////////////////////////////////////////
			// toobar
			// ////////////////////////////////////////
			ToolBar tb = new ToolBar(innerComposite, SWT.HORIZONTAL | SWT.FLAT);
			fillToolbar(tb);
		}

		// ////////////////////////////////////////
		// browser
		// ////////////////////////////////////////
		_browserComposite = new Composite(innerComposite, SWT.NULL);
		_browserComposite.setLayout(new FillLayout());

		_browser = new Browser(_browserComposite, SWT.NULL);

		if (_startLocation != null) {
			_browser.setUrl(_startLocation);
		} else if (_startContent != null) {
			_browser.setText(_startContent);
		} else {
			_browser.setText(Messages.BrowserWindow_loading);
		}

		// send the browser status messages to eclipse.
		_browser.addStatusTextListener(new StatusTextListener() {
			public void changed(StatusTextEvent event) {
				setStatus(event.text);
			}
		});

		if (_bIncludeToolbar) {
			// check for change in location so we can enable the back/forward
			// buttons.
			_browser.addLocationListener(new LocationAdapter() {
				public void changed(LocationEvent event) {
					// check if there is something in the forward or back queue.
					_backItem.setEnabled(_browser.isBackEnabled());
					_forwardItem.setEnabled(_browser.isForwardEnabled());
				}
			});
		}

		// check is we need to open a new window.
		_browser.addOpenWindowListener(new OpenWindowListener() {
			public void open(WindowEvent event) {
				if (_bPopups) {
					BrowserWindow window = new BrowserWindow(getShell());
					window.open();
					event.browser = window.getBrowser();
				} else {
					event.browser = getBrowser();
				}
			}
		});

		setTitle(_title);
		SWTUtils.computeScrollArea(_scomp, innerComposite);
		return _scomp;
	}

	/**
	 * Fill the toolbar for this window. This method can be overwritten to place
	 * other toolbar controls in the bar.
	 * 
	 * @param tb
	 */
	protected void fillToolbar(ToolBar tb) {
		// Back browser
		_backItem = new ToolItem(tb, SWT.PUSH);
		_backItem.setImage(JSFUICommonPlugin.getDefault()
				.getImage(BACK_ITEM_IMAGE_FILE));
		_backItem.setToolTipText(JSFUICommonPlugin
				.getResourceString("BrowserWindow.back.tooltip")); //$NON-NLS-1$
		SelectionListener backSelectionListener = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				browserCmd(BROWSER_CMD_BACK);
			}
		};
		_backItem.addSelectionListener(backSelectionListener);

		// Forward Browser
		_forwardItem = new ToolItem(tb, SWT.PUSH);
		_forwardItem.setImage(JSFUICommonPlugin.getDefault().getImage(
				FORWARD_ITEM_IMAGE_FILE));
		_forwardItem.setToolTipText(JSFUICommonPlugin
				.getResourceString("BrowserWindow.forward.tooltip")); //$NON-NLS-1$
		SelectionListener forwardSelectionListener = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				browserCmd(BROWSER_CMD_FORWARD);
			}
		};
		_forwardItem.addSelectionListener(forwardSelectionListener);

		// Stop Browser
		ToolItem stopItem = new ToolItem(tb, SWT.PUSH);
		stopItem
				.setImage(JSFUICommonPlugin.getDefault().getImage(STOP_ITEM_IMAGE_FILE));
		stopItem.setToolTipText(JSFUICommonPlugin
				.getResourceString("BrowserWindow.stop.tooltip")); //$NON-NLS-1$
		SelectionListener stopSelectionListener = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				browserCmd(BROWSER_CMD_STOP);
			}
		};
		stopItem.addSelectionListener(stopSelectionListener);

		// Refresh Browser
		ToolItem refreshItem = new ToolItem(tb, SWT.PUSH);
		refreshItem.setImage(JSFUICommonPlugin.getDefault().getImage(
				REFRESH_ITEM_IMAGE_FILE));
		refreshItem.setToolTipText(JSFUICommonPlugin
				.getResourceString("BrowserWindow.refresh.tooltip")); //$NON-NLS-1$
		SelectionListener refreshServiceSelectionListener = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				browserCmd(BROWSER_CMD_REFRESH);
			}
		};
		refreshItem.addSelectionListener(refreshServiceSelectionListener);

		if ((_startLocation != null) || (_startContent != null)) {
			// Reload Browser.
			ToolItem reloadItem = new ToolItem(tb, SWT.PUSH);
			reloadItem.setImage(JSFUICommonPlugin.getDefault().getImage(
					RELOAD_ITEM_IMAGE_FILE));
			reloadItem.setToolTipText(JSFUICommonPlugin
					.getResourceString("BrowserWindow.reload.tooltip")); //$NON-NLS-1$
			SelectionListener reloadSelectionListener = new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
					browserCmd(BROWSER_CMD_RELOAD);
				}
			};
			reloadItem.addSelectionListener(reloadSelectionListener);
		}
	}

	/**
	 * send the browser widget a command as defined by the cmd constant defines.
	 * @param cmd
	 * 
	 * @return true if the command was successful.
	 */
	public boolean browserCmd(int cmd) {
		switch (cmd) {
		case BROWSER_CMD_BACK:
			return _browser.back();
		case BROWSER_CMD_FORWARD:
			return _browser.forward();
		case BROWSER_CMD_STOP:
			_browser.stop();
			return true;
		case BROWSER_CMD_REFRESH:
			_browser.refresh();
			return true;
		case BROWSER_CMD_RELOAD:
			if (_startLocation != null) {
				_browser.setUrl(_startLocation);
			} else if (_startContent != null) {
				_browser.setText(_startContent);
			}
			return true;

		}
		return false;
	}
}
