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
	 * 
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
	 * @return
	 */
	IResource getMarkerResource();

}
