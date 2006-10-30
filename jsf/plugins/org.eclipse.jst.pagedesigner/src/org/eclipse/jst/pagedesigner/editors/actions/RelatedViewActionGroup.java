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
package org.eclipse.jst.pagedesigner.editors.actions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.pagedesigner.editors.PageDesignerActionConstants;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.actions.ActionGroup;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * @author mengbo
 * @version 1.5
 */
public class RelatedViewActionGroup extends ActionGroup {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.action.IMenuManager)
	 */
	public void fillContextMenu(IMenuManager menu) {
		super.fillContextMenu(menu);

		final IMenuManager viewMgr = new MenuManager(ActionsMessages
				.getString("RelatedViewActionGroup.Menu.ShowView"),//$NON-NLS-1$
				PageDesignerActionConstants.MENUMGR_VIEW_ID);
		viewMgr.add(new Action() {

		});
		viewMgr.setRemoveAllWhenShown(true);
		viewMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				String[] views = { "org.eclipse.ui.views.PropertySheet",
						"org.eclipse.gef.ui.palette_view"};
//						"org.eclipse.jst.pagedesigner.databinding.ui.views.DataBindingsView" };
				Map icons = getIconForView(views);
				if (manager.find(PropertiesViewAction.ID) == null) {
					Action action = new PropertiesViewAction();
					action.setId(PropertiesViewAction.ID);
					action.setImageDescriptor((ImageDescriptor) icons
							.get(views[0]));
					manager.add(action);
				}
				if (manager.find(PaletteViewAction.ID) == null) {
					Action action = new PaletteViewAction();
					action.setId(PaletteViewAction.ID);
					action.setImageDescriptor((ImageDescriptor) icons
							.get(views[1]));
					manager.add(action);
				}
				/*
				if (manager.find(DataBindingViewAction.ID) == null) {
					Action action = new DataBindingViewAction();
					action.setId(DataBindingViewAction.ID);
					action.setImageDescriptor((ImageDescriptor) icons
							.get(views[2]));
					manager.add(action);
				}
				*/
			}
		});
		menu.appendToGroup(IWorkbenchActionConstants.MB_ADDITIONS, viewMgr);
	}

	private Map getIconForView(String[] viewids) {
		List views = Arrays.asList(viewids);
		Map icons = new HashMap();

		IConfigurationElement[] elements = Platform.getExtensionRegistry()
				.getConfigurationElementsFor("org.eclipse.ui.views");
		for (int i = 0; i < elements.length; i++) {
			String name = elements[i].getName();
			String id = elements[i].getAttribute("id");
			if ("view".equals(name) && views.contains(id)) {
				String iconPath = elements[i].getAttribute("icon");
				if (iconPath != null) {
					icons.put(id, AbstractUIPlugin.imageDescriptorFromPlugin(
							elements[i].getDeclaringExtension().getNamespace(),
							iconPath));
				} else {
					icons.put(id, null);
				}
				if (icons.size() == viewids.length) {
					break;
				}
			}
		}
		return icons;
	}
}
