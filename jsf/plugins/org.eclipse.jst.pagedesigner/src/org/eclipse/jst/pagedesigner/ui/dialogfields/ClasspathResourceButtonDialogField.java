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
package org.eclipse.jst.pagedesigner.ui.dialogfields;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.window.Window;
import org.eclipse.jst.jsf.common.ui.IFileFolderConstants;
import org.eclipse.jst.jsf.common.ui.JSFUICommonPlugin;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogField;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.IStringButtonAdapter;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.StringButtonDialogField;
import org.eclipse.jst.jsf.common.ui.internal.dialogs.ResourceOnClasspathDialog;
import org.eclipse.jst.pagedesigner.properties.attrgroup.IElementContextable;
import org.eclipse.jst.pagedesigner.utils.StructuredModelUtil;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

/**
 * @author mengbo
 */
public class ClasspathResourceButtonDialogField extends StringButtonDialogField implements IElementContextable
{

	private static final String[] PROPERTIES_FILES_SUFFIXS = new String[] { IFileFolderConstants.EXT_PROPERTIES };

	private static class MyStringButtonAdapter implements IStringButtonAdapter
	{
		private IProject _project;

		public void changeControlPressed(DialogField field) {
			browseButtonPressed(field);
		}

		private void browseButtonPressed(DialogField field) 
		{
			if (field instanceof ClasspathResourceButtonDialogField)
			{
				ClasspathResourceButtonDialogField classPathField = 
					(ClasspathResourceButtonDialogField) field;
				Shell shell = field.getShell();
				ResourceOnClasspathDialog dialog = 
					new ResourceOnClasspathDialog(shell, getJavaProject());
				dialog.setTitle(JSFUICommonPlugin
						.getResourceString("DialogField.ResourceButton.SelectFile"));//$NON-NLS-1$
				dialog.setSuffixs(PROPERTIES_FILES_SUFFIXS);
				if (dialog.open() == Window.OK) {
					String oldValue = classPathField.getText();
	
					String newValue = (String) dialog.getResult()[0];
					if (oldValue != newValue) {
						classPathField.setText(newValue);
					}
				}
			}
		}
		
		private IJavaProject getJavaProject() {
			try {
				if (_project != null && _project.hasNature(JavaCore.NATURE_ID)) {
					return JavaCore.create(_project);
				}
	            return null;
			} catch (CoreException e) {
				return null;
			}
		}
	}
	
	/**
s	 */
	public ClasspathResourceButtonDialogField() {
		super(new MyStringButtonAdapter());
	}

	public void setElementContext(
			org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode ancester,
			IDOMElement element) {
		IProject prj = StructuredModelUtil.getProjectFor(ancester.getModel());
		
		IStringButtonAdapter adapter = getStringButtonAdapter();
		
		if (adapter instanceof MyStringButtonAdapter)
		{
			((MyStringButtonAdapter)adapter)._project = prj;
		}
	}
}
