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

import java.util.ArrayList;
import java.util.List;

/**
 * UnconnectedDirectedGraph is used to define a graph which has more than one
 * directed graphs. In this class, the connected subgraphes can be retrieved by
 * getConnectedSubGraphs().
 * 
 * @author Xiao-guang Zhang
 */
public class UnconnectedDirectedGraph extends DirectedGraph {
	private EdgeList candidateEdges = null;

	private NodeList subNodes = null;

	private EdgeList subEdges = null;

	public List getConnectedSubGraphs() {
		List lstGraph = new ArrayList();
		UnconnectedDirectedGraph graph = new UnconnectedDirectedGraph();

		graph.nodes = nodes;
		graph.edges = edges;
		graph.nodes.resetFlags();
		graph.edges.resetFlags(false);

		while (graph.nodes.size() > 0) {
			DirectedGraph subGraph = getConnectedSubGraph(graph);
			lstGraph.add(subGraph);

			graph.nodes.removeAll(subGraph.nodes);
			graph.edges.removeAll(subGraph.edges);
		}
		return lstGraph;
	}

	private DirectedGraph getConnectedSubGraph(UnconnectedDirectedGraph graph) {
		DirectedGraph subGraph = new DirectedGraph();
		Node root = graph.nodes.getNode(0);
		subNodes = new NodeList();
		subEdges = new EdgeList();
		candidateEdges = new EdgeList();
		addNode(root);
		while (subNodes.size() < graph.nodes.size()) {
			// graph is not fully connected
			if (candidateEdges.size() == 0) {
				break;
			}

			Edge edge = candidateEdges.getEdge(0);

			Node node = null;
			// if the edge's target or source has not been added to the
			// subgraph's edges, add it.
			if (!edge.target.flag) {
				node = edge.target;
			}
			if (!edge.source.flag) {
				node = edge.source;
			}

			if (node != null) {
				addNode(node);
			}
		}

		subNodes.resetFlags();
		subEdges.resetFlags(false);

		subGraph.nodes = subNodes;
		subGraph.edges = subEdges;
		return subGraph;
	}

	private void addNode(Node node) {
		if (node == null) {
			return;
		}

		Edge e;
		EdgeList list = node.incoming;
		// if the edge's source has been flaged, the edge should be removed and
		// means the edges has been visited.
		// otherwise, add the edge.
		for (int i = 0; i < list.size(); i++) {
			e = list.getEdge(i);
			if (!e.source.flag) {
				if (!candidateEdges.contains(e)) {
					candidateEdges.add(e);
					// if the edge has not been added in the subgraph edges, add
					// it
					if (!subEdges.contains(e)) {
						subEdges.add(e);
					}

				}
			} else {
				candidateEdges.remove(e);
			}
		}

		// if the edge's target has been flaged, the edge should be removed and
		// means the edges has been visited.
		// otherwise, add the edge.
		list = node.outgoing;
		for (int i = 0; i < list.size(); i++) {
			e = list.getEdge(i);
			if (!e.target.flag) {
				if (!candidateEdges.contains(e)) {
					candidateEdges.add(e);
					// if the edge has not been added in the subgraph edges, add
					// it
					if (!subEdges.contains(e)) {
						subEdges.add(e);
					}
				}
			} else {
				candidateEdges.remove(e);
			}
		}

		if (!node.flag) {
			node.flag = true;
			subNodes.add(node);
		}
	}
}
