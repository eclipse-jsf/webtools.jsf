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
package org.eclipse.jst.jsf.facesconfig.ui.dialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.window.Window;
import org.eclipse.jst.jsf.facesconfig.common.dialogfield.JavaUIHelper;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitIdType;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitType;
import org.eclipse.jst.jsf.facesconfig.ui.NewEditorResourcesNLS;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.SelectionDialog;

/**
 * @author Zhi-peng Zhang
 * @version
 */
public class DialogUtil {

	public static String openClassDialog(Shell shell, IProject project,
			String superType, int tyle) {
		SelectionDialog dialog = (SelectionDialog) JavaUIHelper
				.openSelectionDialog(shell, project, superType, tyle);
		dialog.setTitle(NewEditorResourcesNLS.FindType);
		dialog.setMessage(NewEditorResourcesNLS.FindType_Filter);
		if (dialog.open() == SelectionDialog.OK) {
			Object[] result = dialog.getResult();
			IType searchedType = (IType) result[0];
			return searchedType.getFullyQualifiedName();
		}
		return null;
	}

	public static String openRenderKitDialog(Shell shell,
			FacesConfigType facesConfig) {
		if (facesConfig != null) {
			EList list = facesConfig.getRenderKit();
			ArrayList ids = new ArrayList();
			for (int i = 0, n = list.size(); i < n; i++) {
				RenderKitIdType renderKitId = ((RenderKitType) list.get(i))
						.getRenderKitId();
				if (renderKitId != null) {
					if (!"".equals(renderKitId.getTextContent())) {
						ids.add(renderKitId.getTextContent());
					}
				}
			}
			String[] idArray = (String[]) ids.toArray(new String[ids.size()]);
			Arrays.sort(idArray);
			ListChoiceDialog dialog = new ListChoiceDialog(
					shell,
					idArray,
					NewEditorResourcesNLS.DefaultRenderKitIDSection_Wizard_Page_LabelText);
			if (dialog.open() == SelectionDialog.OK) {
				String result = dialog.getResult();
				if (!"".equals(result)) {
					return result;
				}
			}
		}
		return null;
	}

	public static String openLocaleDialog(Shell shell, List existedLocaleList) {
		LocaleDialog dialog = new LocaleDialog(shell, existedLocaleList);
		if (dialog.open() == Window.OK) {
			String result = dialog.getResult();
			if (!"".equals(result)) {
				return result;
			}
		}
		return null;
	}

	public static String openMessageBundleDialog(Shell shell, IProject project,
			List existedBundleList) {
		IJavaProject javaProject = null;

		try {
			if (project != null && project.hasNature(JavaCore.NATURE_ID)) {
				javaProject = JavaCore.create(project);
			} else {
				return null;
			}
		} catch (CoreException e) {
			// Ignore
		}
		MessageBundleSelectionDialog dialog = new MessageBundleSelectionDialog(
				shell, javaProject, existedBundleList);

		if (dialog.open() == SelectionDialog.OK) {
			String newValue = (String) dialog.getResult()[0];
			if (!"".equalsIgnoreCase(newValue)) {
				return newValue;
			}
		}
		return null;
	}
}
