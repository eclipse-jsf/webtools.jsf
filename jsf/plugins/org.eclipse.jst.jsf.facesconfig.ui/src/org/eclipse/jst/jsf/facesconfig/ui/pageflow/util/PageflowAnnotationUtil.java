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
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.util;

import java.util.Iterator;

import org.eclipse.core.resources.IMarker;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.editpart.IAnnotationEditPart;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.editpart.PFValidator;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.editpart.PageflowElementEditPart;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.editpart.PageflowLinkEditPart;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.editpart.PageflowNodeEditPart;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPage;

/**
 * This class can be used to add annotation for the current pageflow model and
 * decoration for view.
 * 
 * @author Xiao-guang Zhang
 * 
 * 
 */
public class PageflowAnnotationUtil {

	/**
	 * 
	 * validate the pageflow and its edit part
	 * 
	 * @param containerPart -
	 *            it can be null, then the edit part will not be updated
	 */
	public static void validatePageflow(GraphicalEditPart containerPart) {
		if (containerPart == null) {
			return;
		}

		// removeAllAnnotations(containerPart);

		// validate the nodes including page and actions.
		Iterator iterChild = containerPart.getChildren().iterator();
		while (iterChild.hasNext()) {

			PageflowElementEditPart element = (PageflowElementEditPart) iterChild
					.next();
			// String errorMessage = null;
			if (element instanceof PFValidator) {
				((PFValidator) element).validate();
			}
			// if (element.getModel() instanceof PageflowPage) {
			// errorMessage = PageflowValidation.getInstance().getNotifyMessage(
			// (PageflowPage) element.getModel());
			// }

			// if (errorMessage != null) {
			// addAnnotation(element, errorMessage);
			// } else {
			// removeAnnotation(element);
			// }

			// validate the connections.
			Iterator iterLinks = element.getSourceConnections().iterator();
			while (iterLinks.hasNext()) {
				PageflowLinkEditPart link = (PageflowLinkEditPart) iterLinks
						.next();
				link.validate();
				// validateLink(link);
			}
		}

	}

	/**
	 * validate the page based on model and its's edit part.
	 * 
	 * @param pagePart -
	 *            it can be null, then the edit part will not be updated
	 */
	public static void validatePage(PageflowNodeEditPart pagePart) {
		if (pagePart == null && pagePart.getParent() != null) {
			return;
		}

		String errorMessage = null;

		removeAnnotation(pagePart);

		errorMessage = PageflowValidation.getInstance().getNotifyMessage(
				(PageflowPage) pagePart.getModel());

		if (errorMessage != null) {
			addAnnotation(pagePart, errorMessage);
		} else {
			removeAnnotation(pagePart);
		}

		// validate the connections.
		// Iterator iterLinks = pagePart.getSourceConnections().iterator();
		// while (iterLinks.hasNext()) {
		// PageflowLinkEditPart link = (PageflowLinkEditPart) iterLinks.next();
		//
		// validateLink(link);
		// }
	}

	/**
	 * validate the link based on model and its's edit part.
	 * 
	 * @param linkPart -
	 *            it can be null, then the edit part will not be updated
	 */
	public static void validateLink(PageflowLinkEditPart linkPart) {
		if (linkPart != null && linkPart.getParent() != null) {
			String errorMessage = null;

			removeAnnotation(linkPart);

			errorMessage = PageflowValidation.getInstance().isValidLink(
					(PageflowLink) linkPart.getModel());

			if (errorMessage != null) {
				addAnnotation(linkPart, errorMessage);
			} else {
				removeAnnotation(linkPart);
			}
		}
	}

	/**
	 * add a marker with IMarker.PROBLEM type, IMarker.SEVERITY_ERROR severity,
	 * and message.
	 * 
	 * @param editPart
	 * @param model
	 * @param message
	 */
	private static void addAnnotation(GraphicalEditPart editPart, String message) {
		if (editPart != null && editPart instanceof IAnnotationEditPart) {
			Annotation annotation = new Annotation(IMarker.PROBLEM, false,
					message);
			((IAnnotationEditPart) editPart).addAnnotation(annotation);
		}
	}

	/**
	 * remove a marker
	 * 
	 * @param editPart
	 * @param model
	 */
	private static void removeAnnotation(GraphicalEditPart editPart) {
		if (editPart != null && editPart instanceof IAnnotationEditPart) {
			((IAnnotationEditPart) editPart).removeAnnotation();
		}
	}

}
