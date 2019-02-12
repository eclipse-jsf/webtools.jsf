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

package org.eclipse.jst.jsf.facesconfig.ui.pageflow.editpart;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.text.source.Annotation;

/**
 * Edit part's Annotation interface
 * 
 * @author Xiao-guang Zhang
 * 
 */
public interface IAnnotationEditPart {
	/**
	 * decorate current Edit Part.
	 * @param annotation 
	 */
	void addAnnotation(Annotation annotation);

	/**
	 * un-decorate current EditPart
	 * 
	 */
	void removeAnnotation();

	/**
	 * get the maker's resource instance.
	 * 
	 * @return marker resource
	 */
	IResource getMarkerResource();

}
