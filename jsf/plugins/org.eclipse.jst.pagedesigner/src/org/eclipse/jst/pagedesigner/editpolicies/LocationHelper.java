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
package org.eclipse.jst.pagedesigner.editpolicies;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.jst.pagedesigner.css2.layout.FlowBox;
import org.eclipse.jst.pagedesigner.css2.layout.ICSSFigure;
import org.eclipse.jst.pagedesigner.parts.DocumentEditPart;
import org.eclipse.jst.pagedesigner.parts.ElementEditPart;
import org.eclipse.wst.xml.core.internal.contentmodel.CMElementDeclaration;
import org.eclipse.wst.xml.core.internal.contentmodel.modelquery.ModelQuery;
import org.eclipse.wst.xml.core.internal.modelquery.ModelQueryUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author mengbo
 */
public final class LocationHelper {
	/**
	 * @param host 
	 * @param p
	 * @param result
	 * @param tagName
	 * @param skip
	 * @return  true if insertion point is found
	 */
	public static boolean findInsertLocation(GraphicalEditPart host, Point p,
			GraphicalEditPart[] result, String tagName, Node skip) {
		if (isHostInsideSkip(host, skip))
			return false;

		while (isValidHost(host) && !canHostContainTag(host, tagName)) {
			if (host.getParent() instanceof GraphicalEditPart)
				host = (GraphicalEditPart) host.getParent();
			else
				host = null;
		}
		if (!isValidHost(host))
			return false;

		// ok, next we try to find a insertion point inside host
		result[0] = host;
		List children = host.getChildren();
		if (children.isEmpty()) {
			result[1] = null;
			return true;
		}
		// TODO: never read GraphicalEditPart ref = null;
		for (int i = 0, size = children.size(); i < size; i++) {
			GraphicalEditPart child = (GraphicalEditPart) children.get(i);
			Rectangle rect = getAbsoluteBounds(child);

			if (rect.contains(p)) {
				IFigure figure = child.getFigure();
				if (figure instanceof ICSSFigure) {
					List frags = ((ICSSFigure) figure).getFragmentsForRead();
					if (frags.size() > 1) // more than one frags, so is a zig
					// zag.
					{
						// check whether is before the first box.
						FlowBox box = (FlowBox) frags.get(0);
						Rectangle rect1 = getAbsoluteBounds(figure, box);
						if (rect1.x > p.x && rect1.y + rect1.height > p.y) {
							// p is at left/above the first box. so we think p
							// is before this child
							result[1] = child;
							return true;
						}
						// check whether is after the last box
						box = (FlowBox) frags.get(frags.size() - 1);
						rect1 = getAbsoluteBounds(figure, box);
						if (rect1.x < p.x && rect1.y < p.y) {
							continue;
						}
					}
				}
				// ok, treat as the point in a rect figure, see which side is
				// closer.
				if (p.x > rect.x + rect.width / 2) {
					continue;
				}
                result[1] = child;
                return true;
			} else if (rect.x + rect.width < p.x || rect.y + rect.height < p.y) {
				// p is at right or below rect. so the point is "after" the
				// rect.
				continue;
			} else {
				// ok, p is "before" rect.
				result[1] = child;
				return true;
			}
		}
		// we search through all.
		result[1] = null;
		return true;
	}

	/**
	 * @param figure
	 * @param box
	 * @return the bounding rectangle
	 */
	public static Rectangle getAbsoluteBounds(IFigure figure, FlowBox box) {
		Rectangle r = new Rectangle(box.getX(), box.getY(), box.getWidth(), box
				.getHeight());
		figure.translateToAbsolute(r);
		return r;
	}

	/**
	 * @param child
	 * @return the bounding rectangle
	 */
	public static Rectangle getAbsoluteBounds(GraphicalEditPart child) {
		Rectangle bounds = child.getFigure().getBounds().getCopy();
		child.getFigure().translateToAbsolute(bounds);
		return bounds;
	}

	/**
	 * @param host
	 * @param tagName
	 * @return
	 */
	private static boolean canHostContainTag(GraphicalEditPart host,
			String tagName) {
		if (host == null)
			return false;
		Node node = (Node) host.getModel();
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			ModelQuery modelQuery = getModelQuery(node);
			if (modelQuery != null) {
				CMElementDeclaration elementDecl = modelQuery
						.getCMElementDeclaration((Element) node);
				if (elementDecl == null) {
					return true;
				}
				if (elementDecl.getContentType() == CMElementDeclaration.EMPTY)
					return false;
			}
		}
		return true;
	}

	/**
	 * @param host
	 * @return
	 */
	private static boolean isValidHost(GraphicalEditPart host) {
		return host != null
				&& (host instanceof ElementEditPart || host instanceof DocumentEditPart);
	}

	/**
	 * @param host
	 * @param skip
	 * @return
	 */
	private static boolean isHostInsideSkip(GraphicalEditPart host, Node skip) {
		if (skip == null)
			return false;

		// XXX: not done.
		return false;
	}

	private static ModelQuery getModelQuery(Node node) {
        Document doc = node.getOwnerDocument();
        
		if (node.getNodeType() == Node.DOCUMENT_NODE) {
            doc = (Document) node;
		}
        return ModelQueryUtil.getModelQuery(doc);
	}

	private LocationHelper()
	{
	    // util class, no external instantiation
	}
}
