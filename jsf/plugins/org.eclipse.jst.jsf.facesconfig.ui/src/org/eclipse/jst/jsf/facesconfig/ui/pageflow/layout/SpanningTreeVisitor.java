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
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.layout;

/**
 * @version orignal version is from Draw2D
 */
abstract class SpanningTreeVisitor extends GraphVisitor {

Edge getParentEdge(Node node) {
	return (Edge)node.workingData[1];
}

EdgeList getSpanningTreeChildren(Node node) {
	return (EdgeList)node.workingData[0];
}

protected Node getTreeHead(Edge edge) {
	if (getParentEdge(edge.source) == edge)
		return edge.target;
	return edge.source;
}

Node getTreeParent(Node node) {
	Edge e = getParentEdge(node);
	if (e == null)
		return null;
	return e.opposite(node);
}

protected Node getTreeTail(Edge edge) {
	if (getParentEdge(edge.source) == edge)
		return edge.source;
	return edge.target;
}

void setParentEdge(Node node, Edge edge) {
	node.workingData[1] = edge;
}

}
