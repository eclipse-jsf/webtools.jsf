/*******************************************************************************
 * Copyright (c) 2004, 2006 Sybase, Inc. and others.
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.graph.DirectedGraph;
import org.eclipse.draw2d.graph.DirectedGraphLayout;
import org.eclipse.draw2d.graph.Edge;
import org.eclipse.draw2d.graph.EdgeList;
import org.eclipse.draw2d.graph.Node;
import org.eclipse.draw2d.graph.NodeList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowFactory;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLinkBendpoint;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.util.PageflowModelManager;

/**
 * This class is a singleton adpater to create the directed graph for the
 * specified pageflow model or update the location information of pageflow model
 * according to directed graph layout algorithms.
 * 
 * @author Xiao-guang Zhang
 */
public class PageflowLayoutManager {
	/** node's default size */
	private static final int NODE_DEFAULT_WIDTH = 50;

	private static final int NODE_DEFAULT_HEIGHT = 50;

	/**
	 * the margin of the top and left , and margin between each connnected
	 * directed graph.
	 */
	private static final int X_SPACE = 50;

	private static final int Y_SPACE = 50;

	/** node's default padding */
	private static final int DEFAULT_PADDING = 30;

	private static PageflowLayoutManager manager;

	private Map copiedLinks = null;

	/**
	 * constructor of PageflowDirectedGraphAdapter
	 * 
	 */
	private PageflowLayoutManager() {
	    // no external instantiation
	}

	/**
	 * return the singleton instance of PageflowDirectedGraphAdapter
	 * 
	 * @return - the singleton instance of PageflowDirectedGraphAdapter
	 */
	static public PageflowLayoutManager getInstance() {
		if (manager == null) {
			manager = new PageflowLayoutManager();
		}
		return manager;
	}

	/**
	 * layout pageflow using directed graph layout algorithms
	 * 
	 * @param pageflow -
	 *            pageflow model
	 */
	public void layoutPageflow(Pageflow pageflow) {
		List selfLoopLinks = removeSelfLoopLinks(pageflow);

		List lstGraph = createGraphs(pageflow);
		for (Iterator iter = lstGraph.iterator(); iter.hasNext();) {
			DirectedGraph graph = (DirectedGraph) iter.next();
			new DirectedGraphLayout().visit(graph);// .visit(graph);
		}
		updatePageflow(lstGraph);

		if (selfLoopLinks != null) {
			addSelfLoopLinks(pageflow, selfLoopLinks);
		}
	}

	/**
	 * remove the self loop links from pageflow, because the layout algorithm
	 * does not allow it.
	 * 
	 * @param pageflow
	 * @return
	 */
	private List removeSelfLoopLinks(Pageflow pageflow) {
		List selfLoopLinks = new ArrayList();

		if (pageflow.getLinks() != null) {
			for (Iterator iter = pageflow.getLinks().iterator(); iter.hasNext();) {
				PageflowLink link = (PageflowLink) iter.next();

				if (link.getSource() == link.getTarget()) {
					iter.remove();
					link.getBendPoints().clear();
					selfLoopLinks.add(link);
				}
			}
		}
		return selfLoopLinks;
	}

	/**
	 * add back the self loop links with updated bendpoints.
	 * 
	 * @param pageflow
	 * @param selfLoopLinks
	 */
	private void addSelfLoopLinks(Pageflow pageflow, List selfLoopLinks) {
		if (pageflow.getLinks() != null && selfLoopLinks != null
				&& selfLoopLinks.size() > 0) {
			EList links = pageflow.getLinks();
			for (Iterator iter = selfLoopLinks.iterator(); iter.hasNext();) {
				PageflowLink link = (PageflowLink) iter.next();
				updateSelfLoopLink(link);
				links.add(link);
			}
		}
	}

	/**
	 * Update the bendpoints of the self loop link Follow the following style:
	 * 
	 * LeftTop-Top | | Left----Node
	 * 
	 * @param selfLoopLink
	 */
	public static void updateSelfLoopLink(PageflowLink selfLoopLink) {
		PageflowNode sourceNode = selfLoopLink.getSource();
		Rectangle rectNode = getPageflowNodeBounds(sourceNode);

		EList outLinks = sourceNode.getOutlinks();
		if (outLinks != null && outLinks.size() > 0) {
			for (Iterator iter = outLinks.iterator(); iter.hasNext();) {
				PageflowLink anotherSelfLoopLink = (PageflowLink) iter.next();
				if (anotherSelfLoopLink != selfLoopLink
						&& anotherSelfLoopLink.getTarget() == sourceNode) {
					rectNode = getFitnessRectangle(rectNode,
							anotherSelfLoopLink);
				}
			}
		}

		PageflowFactory factory = PageflowModelManager.getFactory();
		PageflowLinkBendpoint bpTop = factory.createPFLinkBendpoint();
		PageflowLinkBendpoint bpLeftTop = factory.createPFLinkBendpoint();
		PageflowLinkBendpoint bpLeft = factory.createPFLinkBendpoint();

		bpTop.setD1Height(-rectNode.height);
		bpTop.setD2Height(-rectNode.height);

		bpLeftTop.setD1Width(-rectNode.width);
		bpLeftTop.setD1Height(-rectNode.height);
		bpLeftTop.setD2Width(-rectNode.width);
		bpLeftTop.setD2Height(-rectNode.height);

		bpLeft.setD1Width(-rectNode.width);
		bpLeft.setD2Width(-rectNode.width);

		selfLoopLink.getBendPoints().add(bpTop);
		selfLoopLink.getBendPoints().add(bpLeftTop);
		selfLoopLink.getBendPoints().add(bpLeft);
	}

	/**
	 * Calculate the fitness rectangle without conflict with the existing self
	 * loop's rectangle.
	 * 
	 * @param rectDefault
	 * @param anotherSelfLoopLink
	 */
	private static Rectangle getFitnessRectangle(Rectangle rectDefault,
			PageflowLink anotherSelfLoopLink) {
		EList bendPoints = anotherSelfLoopLink.getBendPoints();
		if (bendPoints != null && bendPoints.size() > 0) {
			for (Iterator iterBendPoint = bendPoints.iterator(); iterBendPoint
					.hasNext();) {
				PageflowLinkBendpoint bendPoint = (PageflowLinkBendpoint) iterBendPoint
						.next();
				if (bendPoint.getD1Width() == -rectDefault.width
						&& bendPoint.getD1Height() == -rectDefault.height) {
					rectDefault = new Rectangle(0, 0, rectDefault.width
							+ DEFAULT_PADDING, rectDefault.height
							+ DEFAULT_PADDING);
					break;
				}
			}
		}
		return rectDefault;
	}

	/**
	 * get the pageflow node's bounds, the orginal point is (0,0)
	 * 
	 * @param pfNode
	 * @return
	 */
	private static Rectangle getPageflowNodeBounds(PageflowNode pfNode) {
		return new Rectangle(0, 0, 64, 36);
	}

	/**
	 * get the pageflow node's border rectangle
	 * 
	 * @param pfNode
	 * @return
	 */
	private static Rectangle getPageflowNodeRectangle(PageflowNode pfNode) {
		Rectangle rectNode = null;

		Rectangle bounds = getPageflowNodeBounds(pfNode);

		rectNode = new Rectangle(pfNode.getX(), pfNode.getY(), bounds.width,
				bounds.height);

		return rectNode;
	}

	/**
	 * create the connected subgraphs for the pageflow model, because there
	 * maybe more than one connected graph in one pageflow definition.
	 * 
	 * @param pageflow -
	 *            Pageflow model
	 * @return - the connected subgraphs
	 */
	private List createGraphs(Pageflow pageflow) {
		/** the connected subgraphs */
		List lstGraph = null;

		if (pageflow != null) {
			// Graph is not connected totally.
			DirectedGraph graph = null;
			HashMap nodesMap = new HashMap();

			NodeList nodes = new NodeList();
			EdgeList edges = new EdgeList();

			// get all nodes in the pageflow
			List pfNodes = pageflow.getNodes();
			if (pfNodes != null) {
				for (Iterator iter = pfNodes.iterator(); iter.hasNext();) {
					PageflowNode pfNode = (PageflowNode) iter.next();
					Node node = new Node(pfNode);
					Rectangle rectNode = null;
					rectNode = getPageflowNodeBounds(pfNode);
					if (rectNode != null) {
						node.width = rectNode.width * 2;
						node.height = rectNode.height * 2;
						node.setPadding(new Insets(node.height, node.width,
								node.height, node.width));
					} else {
						node.width = NODE_DEFAULT_WIDTH;
						node.height = NODE_DEFAULT_HEIGHT;
						node.setPadding(new Insets(DEFAULT_PADDING));
					}
					nodesMap.put(pfNode, node);
					nodes.add(node);
				}
			}
			// get all edges in the pageflow
			List pfLinks = pageflow.getLinks();
			for (Iterator iter = pfLinks.iterator(); iter.hasNext();) {
				PageflowLink link = (PageflowLink) iter.next();
				PageflowNode source = link.getSource();
				PageflowNode target = link.getTarget();
				Node sourceNode = (Node) nodesMap.get(source);
				Node targetNode = (Node) nodesMap.get(target);

				if (sourceNode != null && targetNode != null) {
					Edge edge = new Edge(sourceNode, targetNode);
					edges.add(edge);
				}
			}

			graph = new DirectedGraph();

			graph.nodes = nodes;
			graph.edges = edges;

			// get the connected subgraphs.
			lstGraph = new ArrayList();
			lstGraph.add(graph);// ..getConnectedSubGraphs();
		}
		return lstGraph;
	}

	/**
	 * update the pageflow according to layout results which are stored in the
	 * connected subgraphs
	 * 
	 * @param lstGraph -
	 *            connected subgraphs
	 * 
	 */
	private void updatePageflow(List lstGraph) {
		// y coordiantion of the subgraph's start point
		int topSubGraph = 0;

		for (Iterator iter = lstGraph.iterator(); iter.hasNext();) {
			// sub graph's bottom
			int bottomSubGraph = 0;
			DirectedGraph graph = (DirectedGraph) iter.next();
			for (int i = 0; i < graph.nodes.size(); i++) {
				Node node = graph.nodes.getNode(i);
				if (!(node.data instanceof PageflowNode)) {
					continue;
				}
				PageflowNode pfNode = (PageflowNode) node.data;
				pfNode.setX(X_SPACE + node.y);

				pfNode.setY(Y_SPACE + node.x + topSubGraph);

				if ((Y_SPACE + node.x + topSubGraph) > bottomSubGraph) {
					bottomSubGraph = Y_SPACE + node.x + topSubGraph;
				}
			}
			topSubGraph = bottomSubGraph + Y_SPACE;
		}
	}

	/**
	 * update new pageflow's layout using the existing one.
	 * 
	 * @param pageflow
	 * @param model
	 */
	public void updatePageflowLayout(Pageflow newPageflow, Pageflow oldPageflow) {
		List notUpdatedNodes = new ArrayList();

		copiedLinks = new HashMap();

		if (oldPageflow.getNodes().size() > 0) {
			for (Iterator iter = newPageflow.getNodes().iterator(); iter
					.hasNext();) {
				PageflowNode newNode = (PageflowNode) iter.next();

				if (!updatePageflowNode(newNode, oldPageflow)) {
					notUpdatedNodes.add(newNode);
				}
			}
		} else {
			notUpdatedNodes.addAll(newPageflow.getNodes());
		}

		if (oldPageflow.getLinks().size() > 0) {
			for (Iterator iter = newPageflow.getLinks().iterator(); iter
					.hasNext();) {
				PageflowLink newLink = (PageflowLink) iter.next();

				updatePFLink(newLink, oldPageflow);
			}
		}

		// if there are still some nodes which are not updated,
		// they should be check whether there are some layout conflict.
		if (notUpdatedNodes.size() > 0) {
			resolveConflict(newPageflow, notUpdatedNodes);
		}

	}

	/**
	 * Update pageflow node using the same node
	 * 
	 * @param object
	 * @return - the old pageflow node.
	 */
	private boolean updatePageflowNode(PageflowNode newNode,
			Pageflow oldPageflow) {
		for (Iterator iter = oldPageflow.getNodes().iterator(); iter.hasNext();) {
			PageflowNode oldNode = (PageflowNode) iter.next();

			if (oldNode instanceof PageflowPage
					&& newNode instanceof PageflowPage) {
				if (((PageflowPage) oldNode).getPath().trim().equals(
						((PageflowPage) newNode).getPath().trim())) {
					updatePageflowNode(newNode, oldNode);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Update pageflow node using the same node
	 * 
	 * @param object
	 * @return
	 */
	private void updatePageflowNode(PageflowNode newNode, PageflowNode node) {
		newNode.setX(node.getX());
		newNode.setY(node.getY());
	}

	/**
	 * If there are conflict for the nodes, it will resolve it.
	 * 
	 * @param newPageflow
	 * @param notUpdatedNodes
	 */
	private void resolveConflict(Pageflow newPageflow, List notUpdatedNodes) {
		for (Iterator iter = notUpdatedNodes.iterator(); iter.hasNext();) {
			resolveConflict(newPageflow, (PageflowNode) iter.next());
		}
	}

	/**
	 * Resolve the layout conflict
	 * 
	 * @param newPageflow
	 * @param node
	 */
	private void resolveConflict(Pageflow newPageflow, PageflowNode node) {
		Rectangle nodeRect = getPageflowNodeRectangle(node);
		boolean bModified = false;
		for (int i = 0, size = newPageflow.getNodes().size(); i < size; i++) {
			PageflowNode fixedNode = (PageflowNode) newPageflow.getNodes().get(
					i);
			if (node == fixedNode) {
				continue;
			}
			Rectangle fixedNodeRect = getPageflowNodeRectangle(fixedNode);
			if (fixedNodeRect.intersects(nodeRect)) {
				nodeRect.x += fixedNodeRect.width + X_SPACE;
				nodeRect.y += fixedNodeRect.height + Y_SPACE;
				bModified = true;
			}
		}
		if (bModified) {
			node.setX(nodeRect.x);
			node.setY(nodeRect.y);
		}
	}

	/**
	 * Update the link using the same link in the old pageflow
	 * 
	 * @param newLink
	 * @param oldPageflow
	 */
	private void updatePFLink(PageflowLink newLink, Pageflow oldPageflow) {
		for (Iterator iter = oldPageflow.getLinks().iterator(); iter.hasNext();) {
			PageflowLink oldLink = (PageflowLink) iter.next();

			if (copiedLinks.get(oldLink) != null) {
				continue;
			}

			if (isSameLink(newLink, oldLink)) {
				updatePFLink(newLink, oldLink);
				break;
			}
		}
	}

	/**
	 * Check whether this two links in differnet pageflow are same or not.
	 * 
	 * @param newLink
	 * @param oldLink
	 * @return
	 */
	private boolean isSameLink(PageflowLink newLink, PageflowLink oldLink) {
		PageflowNode newSource = newLink.getSource();
		PageflowNode newTarget = newLink.getTarget();

		PageflowNode oldSource = oldLink.getSource();
		PageflowNode oldTarget = oldLink.getTarget();

		// Page-Page
		if (newSource instanceof PageflowPage
				&& oldSource instanceof PageflowPage
				&& newTarget instanceof PageflowPage
				&& oldTarget instanceof PageflowPage) {
			if (((PageflowPage) newSource).getPath().trim().equalsIgnoreCase(
					((PageflowPage) oldSource).getPath().trim())
					&& ((PageflowPage) newTarget)
							.getPath()
							.trim()
							.equalsIgnoreCase(
									((PageflowPage) oldTarget).getPath().trim())) {
				if ((newLink.getOutcome() == null && oldLink.getOutcome() == null)
						|| (newLink.getOutcome() != null
								&& oldLink.getOutcome() != null && newLink
								.getOutcome().trim().equals(
										oldLink.getOutcome().trim()))) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Update the link using the same link
	 * 
	 * @param nodesMap
	 * @param object
	 * @return
	 */
	private PageflowLink updatePFLink(PageflowLink newLink, PageflowLink link) {
		PageflowFactory factory = PageflowModelManager.getFactory();

		newLink.getBendPoints().clear();

		for (Iterator iter = link.getBendPoints().iterator(); iter.hasNext();) {
			PageflowLinkBendpoint bendPoint = (PageflowLinkBendpoint) iter
					.next();
			PageflowLinkBendpoint newBendPoint = factory
					.createPFLinkBendpoint();
			newBendPoint.setD1Height(bendPoint.getD1Height());
			newBendPoint.setD1Width(bendPoint.getD1Width());
			newBendPoint.setD2Height(bendPoint.getD2Height());
			newBendPoint.setD2Width(bendPoint.getD2Width());
			newLink.getBendPoints().add(newBendPoint);
		}

		copiedLinks.put(link, link);
		return newLink;
	}

}
