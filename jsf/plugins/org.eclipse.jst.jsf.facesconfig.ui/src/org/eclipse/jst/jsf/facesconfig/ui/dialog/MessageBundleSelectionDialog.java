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
import java.util.List;

import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jst.jsf.facesconfig.common.IFileFolderConstants;
import org.eclipse.jst.jsf.facesconfig.common.dialogs.ResourceOnClasspathDialog;
import org.eclipse.jst.jsf.facesconfig.emf.MessageBundleType;
import org.eclipse.jst.jsf.facesconfig.ui.EditorMessages;
import org.eclipse.swt.widgets.Shell;

/**
 * @author sfshi
 * @version
 */
public class MessageBundleSelectionDialog extends ResourceOnClasspathDialog {
	/** A list contains the resoursebundles existing in faces config */
	private List resourceBundles;

	private String selectPropertyFileMessage = EditorMessages.MessageBundleSection_Dialog_Message_SelectPropertyFile; 

	private String alreadyExistStatusMessage = EditorMessages.MessageBundleSection_Dialog_Message_AlreadyExists; 

	private static final String[] PROPERTIES_FILES_SUFFIXS = new String[] { IFileFolderConstants.EXT_PROPERTIES };

	/**
	 * @param parentShell
	 * @param project
	 * @param resourceBundles
	 */
	public MessageBundleSelectionDialog(Shell parentShell,
			IJavaProject project, List resourceBundles) {
		super(parentShell, project);
		this.resourceBundles = resourceBundles;
		setSuffixs(PROPERTIES_FILES_SUFFIXS);
		
		setTitle(EditorMessages.MessageBundleSection_Dialog_Title);
		setViewerSorter(new MessageBundleViewerSorter());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.common.dialogs.TreeViewerSelectionDialog#isValidSelection(java.lang.Object)
	 */
	protected boolean isValidSelection(Object selection) {
		if (!(super.isValidSelection(selection))) {
			setStatusMessage(selectPropertyFileMessage);
			return false;
		}

		List selectionList = new ArrayList();
		selectionList.add(selection);
		this.setResult(selectionList);
		Object result = super.getResult()[0];
		if (null == result) {
			setStatusMessage(selectPropertyFileMessage);
			return false;
		}

		String newResourceBundle = (String) result;

		if (isAlreadyExist(newResourceBundle)) {
			this.setStatusMessage(alreadyExistStatusMessage);
			return false;
		}

		return true;
	}

	/**
	 * Determine whether this resource bundle file has already been defined in
	 * faces config.
	 * 
	 * @return boolean
	 */
	private boolean isAlreadyExist(String resourceBundleString) {
		if (null == resourceBundleString || resourceBundleString.length() == 0) {
			return true;
		}

		if (null == resourceBundles) {
			return false;
		}

		for (int i = 0; i < resourceBundles.size(); i++) {
			MessageBundleType node = (MessageBundleType) resourceBundles.get(i);
			String rbName = node.getTextContent();
			if (rbName != null && rbName.equalsIgnoreCase(resourceBundleString)) {
				return true;
			}
		}

		return false;
	}
}
