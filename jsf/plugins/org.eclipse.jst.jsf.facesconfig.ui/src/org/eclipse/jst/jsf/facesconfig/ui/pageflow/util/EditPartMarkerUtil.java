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
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement;
import org.eclipse.jst.jsf.facesconfig.ui.util.WebrootUtil;

/**
 * 
 * Utility class to add and remove edit part markers.
 * 
 * FIXME: add the identification mechanism of the model and remove the
 * dependency on the PageflowElement
 * 
 * @author Xiao-guang Zhang
 * 
 * 
 */
public class EditPartMarkerUtil {

	/**
	 * Removes all markers that were created using createMarker()
	 * 
	 * @param model -
	 *            the emf model related with edit part
	 */
	public static void removeAllMarkers(EObject model) throws CoreException {
		IResource resource = getResource(model);

		if (resource != null) {
			String markerTypes[] = { IMarker.PROBLEM, IMarker.TASK };

			for (int iMT = 0; iMT < markerTypes.length; iMT++) {
				IMarker[] markers = resource.findMarkers(markerTypes[iMT],
						false, IResource.DEPTH_ONE);

				for (int iMark = 0; iMark < markers.length; iMark++) {
					markers[iMark].delete();
				}
			}
		}
	}

	/**
	 * Removes a specific problem marker type with the given severity.
	 * 
	 * @param model -
	 *            the model related with this edit part.
	 * @param sMarkerType -
	 *            the marker type, either IMarker.PROBLEM or IMarker.TASK
	 * @param iSeverity -
	 *            severity/priority: if sMarkerType is IMarker.PROBLEM, then
	 *            this value is one of the PROBLEM severities; if sMarkerType is
	 *            IMarker.TASK, this is a task priority.
	 * @throws CoreException
	 *             passed on from IResource.findMarkers()
	 */
	public static void removeAllMarkers(EObject model, String sMarkerType,
			int iSeverity) throws CoreException {
		IResource resource = getResource(model);

		if (resource != null) {
			IMarker[] markers = resource.findMarkers(sMarkerType, false,
					IResource.DEPTH_ONE);

			for (int i = 0; i < markers.length; i++) {
				IMarker marker = markers[i];
				int iMarkerSeverity = marker.getAttribute(IMarker.SEVERITY,
						IMarker.SEVERITY_ERROR);

				if (iMarkerSeverity == iSeverity || iSeverity == -1) {
					markers[i].delete();
				}
			}
		}
	}

	/**
	 * get the resouce according to part or model.
	 * 
	 * @param part
	 * @param model
	 * @return
	 */
	private static IResource getResource(EObject model) {
		IResource resource = null;
		resource = WebrootUtil.getResource(model);
		return resource;
	}

	/**
	 * Create a problem marker.
	 * 
	 * @param part -
	 *            the target editpart. It can be null, then the editpart will
	 *            not be updated.
	 * @param model -
	 *            the model related with this edit part.
	 * @param severity -
	 *            the problem severity, one of IMarker.SEVERITY_ERROR,
	 *            IMarker.SEVERITY_WARNING or IMarker.SEVERITY_INFO
	 * @param message -
	 *            the text associated with this marker
	 * @param location -
	 *            a unique ID that the application can use to locate the
	 *            editpart
	 * 
	 * that corresponds to this marker; this is typically the DiagramElement's
	 * ID string
	 * @throws CoreException
	 */
	public static void createMarker(EObject model, String sMarkerType,
			int severity, String message, String location) throws CoreException {
		IResource resource = getResource(model);

		if (resource != null) {
			IMarker marker = resource.createMarker(sMarkerType);
			marker.setAttribute(IMarker.MESSAGE, message);
			marker.setAttribute(IMarker.SEVERITY, severity);
			// The problem view doesn't show the location - only the line number
			// is shown in the "location" column. Doh!
			if (location != null && location.length() > 0) {
				marker.setAttribute(IMarker.LOCATION, location);
			}
		}
	}

	/**
	 * Removes a specific problem marker type with the given severity.
	 * 
	 * @param part -
	 *            the target editpart. It can be null, then the editpart will
	 *            not be updated.
	 * @param model -
	 *            the model related with this edit part.
	 * @param sMarkerType -
	 *            the marker type, either IMarker.PROBLEM or IMarker.TASK
	 * @param iSeverity -
	 *            severity/priority: if sMarkerType is IMarker.PROBLEM, then
	 *            this value is one of the PROBLEM severities; if sMarkerType is
	 *            IMarker.TASK, this is a task priority.
	 * @throws CoreException
	 *             passed on from IResource.findMarkers()
	 */
	public static void removeMarker(EObject model, String sMarkerType)
			throws CoreException {
		IResource resource = getResource(model);
		if (resource != null) {
			IMarker[] markers = resource.findMarkers(sMarkerType, false,
					IResource.DEPTH_ONE);

			for (int i = 0; i < markers.length; i++) {
				IMarker marker = markers[i];
				// get the marker's location
				Object id = marker.getAttribute(IMarker.LOCATION);

				if (model instanceof PageflowElement) {
					if (((PageflowElement) model).getId().equals(id)) {
						markers[i].delete();
					}
				}
			}
		}
	}

	/**
	 * Searches the hierarchy starting at the given EditPart for a given model
	 * object ID string (the DiagramElement.ID value) and returns that EditPart
	 * 
	 * @param containerPart
	 * @param id
	 * @return the EditPart that corresponds to the given ID string, or null if
	 *         not found
	 */
	public static GraphicalEditPart findEditPart(
			GraphicalEditPart containerPart, String id) {
		Object model = containerPart.getModel();
		if (model instanceof PageflowElement) {
			String modelId = ((PageflowElement) model).getId();
			if (modelId != null && modelId.equals(id)) {
				return containerPart;
			}
		}

		Iterator iterNode = containerPart.getChildren().iterator();
		while (iterNode.hasNext()) {
			GraphicalEditPart child = (GraphicalEditPart) iterNode.next();
			GraphicalEditPart foundPart = findEditPart(child, id);
			if (foundPart != null) {
				return foundPart;
			}
		}

		Iterator iterConnection = containerPart.getSourceConnections()
				.iterator();
		while (iterConnection.hasNext()) {
			GraphicalEditPart child = (GraphicalEditPart) iterConnection.next();
			GraphicalEditPart foundPart = findEditPart(child, id);
			if (foundPart != null) {
				return foundPart;
			}
		}

		return null;
	}

}
