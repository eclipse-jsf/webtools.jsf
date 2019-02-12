/*******************************************************************************
 * Copyright (c) 2004, 2007 Sybase, Inc. and others.
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
package org.eclipse.jst.jsf.facesconfig.ui.dialog;

import java.text.Collator;

import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jst.jsf.common.ui.IFileFolderConstants;

/**
 * @author sfshi
 * @version
 */
public class MessageBundleViewerSorter extends ViewerSorter {

	/**
	 * 
	 */
	public MessageBundleViewerSorter() {
		super();
	}

	/**
	 * @param collator
	 */
	public MessageBundleViewerSorter(Collator collator) {
		super(collator);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ViewerSorter#category(java.lang.Object)
	 */
	public int category(Object element) {
		if (element instanceof IPackageFragmentRoot
				&& !((IPackageFragmentRoot)element).isArchive()) {
			IPackageFragmentRoot root = (IPackageFragmentRoot) element;
			if (IFileFolderConstants.FOLDER_SOURCE
					.equals(root.getElementName())) {
				return 0;
			}
			return 1;
		}

		return 2;
	}
}