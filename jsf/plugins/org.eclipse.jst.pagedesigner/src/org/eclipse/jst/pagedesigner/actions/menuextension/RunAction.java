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
package org.eclipse.jst.pagedesigner.actions.menuextension;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * @author mengbo
 * @version 1.5
 */
public class RunAction extends Action {
	/**
	 * constant for debug launch mode
	 */
	public static final String LAUNCH_MODE_DEBUG = "debug";

	/**
	 * constant for run launch mode
	 */
	public static final String LAUNCH_MODE_RUN = "run";

	private final String ID = "org.eclipse.jst.pagedesigner.actions.menuextension.RunAction";

	//private EditorPart _editor;

	private String _mode;

	/**
	 * @param editor
	 * @param mode
	 */
	public RunAction(EditorPart editor, String mode) {
		//_editor = editor;
		_mode = mode;
	}

	public void run() {
	    PDPlugin.getLogger(getClass()).error("Obsolete.  Shouldn't be used", new Throwable());
	    // FIXME: we don't support this feature and it seems to depend
	    // on debugger internal code anyway...
	    //		List allShortCuts = DebugUIPlugin.getDefault()
//				.getLaunchConfigurationManager().getLaunchShortcuts();
//		Iterator iter = allShortCuts.iterator();
//		LaunchShortcutExtension ext = null;
//		while (iter.hasNext()) {
//			ext = (LaunchShortcutExtension) iter.next();
//			try {
//				if (ext.getId().equals("org.eclipse.wst.server.launchShortcut")) {
//					break;
//				}
//			} catch (Exception e) {
//				// not supported
//			}
//		}
//		if (ext != null) {
//			ext.launch(getSelection(), _mode);
//		}
	}

//	private IStructuredSelection getSelection() {
//		IEditorInput input = ((HTMLEditor) _editor).getEditorInput();
//		List elements = new ArrayList();
//		if (input instanceof FileEditorInput) {
//			elements.add(((FileEditorInput) input).getFile());
//		}
//		return new StructuredSelection(elements);
//	}

	public String getId() {
		return ID;
	}

	public String getText() {
		return _mode.substring(0, 1).toUpperCase()
				+ _mode.substring(1, _mode.length());
	}

	private ImageDescriptor getImageDescriptorForModel(String id) {
		IConfigurationElement[] elements = Platform.getExtensionRegistry()
				.getConfigurationElementsFor("org.eclipse.ui.actionSets");
		for (int i = 0; i < elements.length; i++) {
			if ("actionSet".equals(elements[i].getName())) {
				IConfigurationElement[] actions = elements[i]
						.getChildren("action");
				for (int j = 0; j < actions.length; j++) {
					if (id.equals(actions[j].getAttribute("id"))) {
						String iconPath = actions[j].getAttribute("icon");
						if (iconPath != null) {
							return AbstractUIPlugin.imageDescriptorFromPlugin(
									actions[j].getDeclaringExtension()
											.getContributor().getName(), iconPath);
						}
                        return null;
					}
				}
			}
		}
		return null;
	}

	public ImageDescriptor getImageDescriptor() {
		if (_mode == LAUNCH_MODE_DEBUG) {
			return getImageDescriptorForModel("org.eclipse.debug.internal.ui.actions.DebugDropDownAction");
		} else if (_mode == LAUNCH_MODE_RUN) {
			return getImageDescriptorForModel("org.eclipse.debug.internal.ui.actions.RunDropDownAction");
		} else {
			return super.getImageDescriptor();
		}
	}
}
