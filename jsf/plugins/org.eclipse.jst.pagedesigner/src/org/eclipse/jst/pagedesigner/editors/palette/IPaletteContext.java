/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.editors.palette;

import org.eclipse.core.resources.IFile;
import org.eclipse.jst.jsf.context.IModelContext;

/**
 * Context in which the WPE palette is operating
 *
 */
public interface IPaletteContext extends IModelContext {
	/**
	 * @return IFile
	 */
	public IFile getFile();
}
