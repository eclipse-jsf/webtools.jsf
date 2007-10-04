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
package org.eclipse.jst.jsf.facesconfig.ui.preference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Image;

/**
 * The <code>BaseNodeFigure</code> class is the base class for all the figures
 * representing DiagramNodes, ie those that can have ports with maxConnections
 * to other nodes.
 * 
 * Assumes that all NodeFigures have some number of input and output ports
 * 
 * @author ddean
 * 
 */
/*package*/ class BaseNodeFigure extends Figure implements IBaseFigure {
	private IBaseFigure baseFigure;

	private boolean anchorsVisible = true;

	private Hashtable connectionAnchors = new Hashtable(7);

	private Vector inputConnectionAnchors = new Vector(2, 2);

	private Vector outputConnectionAnchors = new Vector(2, 2);

	private Vector topConnectionAnchors = new Vector();

	private Vector bottomConnectionAnchors = new Vector();

	private Vector leftConnectionAnchors = new Vector();

	private Vector rightConnectionAnchors = new Vector();

	/**
	 * @param figure
	 */
	public BaseNodeFigure(IBaseFigure figure) {
		setBaseFigure(figure);
		ObserveVisibleXYLayout layout = new ObserveVisibleXYLayout();
		layout.setObserveVisibility(true);
		setLayoutManager(layout);
	}

	/**
	 * @param figure
	 */
	public void setBaseFigure(IBaseFigure figure) {
		setBaseFigure(figure, new Rectangle(0, 0, -1, -1));
	}

	public void setHighlight(boolean flag) {
		if (baseFigure != null) {
			moveToTop(this, baseFigure);
			baseFigure.setHighlight(flag);
		}
	}

	/**
	 * @param figure
	 * @param constraint
	 */
	public void setBaseFigure(IBaseFigure figure, Rectangle constraint) {
		if (!getChildren().contains(figure)) {
			if (getChildren().contains(baseFigure))
				remove(baseFigure);

			baseFigure = figure;
			if (!getChildren().contains(baseFigure)) {
				// CR389070: Figures are abbreviating rule figures names and
				// making them unreadable
				if (getParent() != null
						&& getParent().getLayoutManager() != null) {
					Rectangle r = getBounds().getCopy();
					r.width = -1;
					r.height = -1;
					getParent().getLayoutManager().setConstraint(this, r);
				}
				// CR389495: Working with nested complex activities causes in
				// the BP editor causes lockup
				// ensure that constraints are set on figure before add() - this
				// will cause a layout()
				this.add(baseFigure, constraint, 0);
			}
		}
	}

	/**
	 * @return the base figure
	 */
	public IBaseFigure getBaseFigure() {
		return baseFigure;
	}

	/***************************************************************************
	 * Input/Output Ports
	 **************************************************************************/

	/**
	 * Add an input port and its anchor
	 * 
	 * @param portName
	 *            unique name to refer to the port
	 */
	public void addInput(String portName) {
		InputPortFigure inputPort = new InputPortFigure();
		add(inputPort);
		inputPort.setToolTipText(portName);

		PortConnectionAnchor anchor = new PortConnectionAnchor(inputPort);
		getTargetConnectionAnchors().add(anchor);
		connectionAnchors.put(portName, anchor);
	}

	/**
	 * Add an output port and its anchor
	 * 
	 * @param portName
	 *            unique name to refer to the port
	 * @return the connection anchor
	 */
	public PortConnectionAnchor addOutput(String portName) {
		OutputPortFigure outputPort = new OutputPortFigure();
		add(outputPort);
		outputPort.setToolTipText(portName);

		PortConnectionAnchor anchor = new PortConnectionAnchor(outputPort);
		getSourceConnectionAnchors().add(anchor);
		connectionAnchors.put(portName, anchor);
		return anchor;
	}

	/**
	 * Searches for and returns the anchor on this figure that is closest to the
	 * reference point <code>p</code>
	 * 
	 * @param p
	 *            the reference point
	 * @return the anchor on this figure that is closest to <code>p</code>
	 */
	public ConnectionAnchor connectionAnchorAt(Point p) {
		ConnectionAnchor closest = null;
		long min = Long.MAX_VALUE;

		Enumeration e = getSourceConnectionAnchors().elements();
		while (e.hasMoreElements()) {
			ConnectionAnchor c = (ConnectionAnchor) e.nextElement();
			Point p2 = c.getLocation(null);
			long d = p.getDistance2(p2);
			if (d < min) {
				min = d;
				closest = c;
			}
		}
		e = getTargetConnectionAnchors().elements();
		while (e.hasMoreElements()) {
			ConnectionAnchor c = (ConnectionAnchor) e.nextElement();
			Point p2 = c.getLocation(null);
			long d = p.getDistance2(p2);
			if (d < min) {
				min = d;
				closest = c;
			}
		}
		return closest;
	}

	/**
	 * returns an anchor given its name
	 * 
	 * @param portName
	 *            name of the anchor
	 * @return the anchor with the name <code>portName</code>
	 */
	public ConnectionAnchor getConnectionAnchor(String portName) {
		return (ConnectionAnchor) connectionAnchors.get(portName);
	}

	/**
	 * returns the name of the specified anchor
	 * 
	 * @param c
	 *            the anchor whose name is requested
	 * @return the name of the specifed anchor
	 */
	public String getConnectionAnchorName(ConnectionAnchor c) {
		Enumeration enumer = connectionAnchors.keys();
		String key;
		while (enumer.hasMoreElements()) {
			key = (String) enumer.nextElement();
			if (connectionAnchors.get(key).equals(c))
				return key;
		}
		return null;
	}

	/**
	 * returns the source connection anchor that is closest to the reference
	 * point
	 * 
	 * @param p
	 *            the reference point
	 * @return the closest connection anchor to <code>p</code>
	 */
	public ConnectionAnchor getSourceConnectionAnchorAt(Point p) {
		ConnectionAnchor closest = null;
		long min = Long.MAX_VALUE;

		Enumeration e = getSourceConnectionAnchors().elements();
		while (e.hasMoreElements()) {
			ConnectionAnchor c = (ConnectionAnchor) e.nextElement();
			Point p2 = c.getLocation(null);
			long d = p.getDistance2(p2);
			if (d < min) {
				min = d;
				closest = c;
			}
		}
		return closest;
	}

	/**
	 * returns all the source connection anchors on this node figure
	 * 
	 * @return a vector of all the source connection anchors for this figure
	 */
	public Vector getSourceConnectionAnchors() {
		return outputConnectionAnchors;
	}

	/**
	 * returns the target connection anchor that is closest to the reference
	 * point
	 * 
	 * @param p
	 *            the reference point
	 * @return the closest target connection anchor to <code>p</code>
	 */
	public ConnectionAnchor getTargetConnectionAnchorAt(Point p) {
		ConnectionAnchor closest = null;
		long min = Long.MAX_VALUE;

		Enumeration e = getTargetConnectionAnchors().elements();
		while (e.hasMoreElements()) {
			ConnectionAnchor c = (ConnectionAnchor) e.nextElement();
			Point p2 = c.getLocation(null);
			long d = p.getDistance2(p2);
			if (d < min) {
				min = d;
				closest = c;
			}
		}
		return closest;
	}

	/**
	 * returns all the target connection anchors on this node figure
	 * 
	 * @return a vector of all the target connection anchors for this figure
	 */
	public Vector getTargetConnectionAnchors() {
		return inputConnectionAnchors;
	}

	/**
	 * Returns the name of the specified anchor
	 * 
	 * @param anchor
	 *            the connectio anchor whose name is requested
	 * @return the anchor's name
	 */
	public String getNameForAnchor(ConnectionAnchor anchor) {
		if (anchor != null) {
			Iterator it = connectionAnchors.keySet().iterator();
			String name;

			while (it.hasNext()) {
				name = (String) it.next();

				if (anchor.equals(connectionAnchors.get(name))) {
					return name;
				}
			}
		}
		return null;
	}

	/**
	 * @param visible
	 */
	public void setAnchorsVisible(boolean visible) {
		if (anchorsVisible != visible) {
			anchorsVisible = visible;
			//ConnectionAnchor port;
			List all = new ArrayList();
			all.addAll(getTargetConnectionAnchors());
			all.addAll(getSourceConnectionAnchors());
			Iterator it = all.iterator();
			while (it.hasNext()) {
				IFigure fig = ((ConnectionAnchor) it.next()).getOwner();
				fig.setVisible(visible);
			}
			validate();
		}
	}

	/**
	 * Determine which side of the figure to place each anchor based on the
	 * relative position (north, south, east or west) of the connection line's
	 * opposite anchor. If the anchor is not connected to anything else, the
	 * default is to place target (input) anchors on the left and source
	 * (output) anchors on the right.
	 */
	private void determineAnchorPositions() {
		Iterator it;
		topConnectionAnchors.clear();
		bottomConnectionAnchors.clear();
		leftConnectionAnchors.clear();
		rightConnectionAnchors.clear();

		if (anchorsVisible) {
			//final Dimension size = baseFigure.getPreferredSize();
			PortConnectionAnchor port;

			// start with outputs
			it = getSourceConnectionAnchors().iterator();
			while (it.hasNext()) {
				port = (PortConnectionAnchor) it.next();
				// CR386077: SplitActivity True and False outputs switch
				// positions based on their targets
				int direction = port.getOrientation();
				if (direction == PositionConstants.NONE) {
					Point loc = port.getOwner().getParent().getBounds()
							.getLocation();
					// loc.x += size.width/2;
					// loc.y += size.height/2;
					Iterator itc = port.getConnections().iterator();
					if (itc.hasNext()) {
						while (itc.hasNext()) {
							PolylineConnection conn = (PolylineConnection) itc
									.next();
							ConnectionAnchor otherPort;
							if (port == conn.getSourceAnchor())
								otherPort = conn.getTargetAnchor();
							else
								otherPort = conn.getSourceAnchor();
							if (otherPort.getOwner() == null)
								continue;
							Point otherLoc = otherPort.getOwner().getParent()
									.getBounds().getLocation();
							// Dimension otherSize =
							// ((BaseNodeFigure)otherPort.getOwner().getParent()).getBaseFigure().getPreferredSize();
							// otherLoc.x += otherSize.width/2;
							// otherLoc.y += otherSize.height/2;
							direction = loc.getPosition(otherLoc);
							if (direction == PositionConstants.NORTH)
								topConnectionAnchors.add(port);
							else if (direction == PositionConstants.SOUTH)
								bottomConnectionAnchors.add(port);
							else if (direction == PositionConstants.WEST)
								leftConnectionAnchors.add(port);
							else
								rightConnectionAnchors.add(port);
							((PortFigure) port.getOwner())
									.setOrientation(direction);
							break; // currently, only the first connection is
									// considered
						}
					} else {
						rightConnectionAnchors.add(port);
						((PortFigure) port.getOwner())
								.setOrientation(PositionConstants.EAST);
					}
				} else {
					// CR386077: SplitActivity True and False outputs switch
					// positions based on their targets
					if (direction == PositionConstants.NORTH)
						topConnectionAnchors.add(port);
					else if (direction == PositionConstants.SOUTH)
						bottomConnectionAnchors.add(port);
					else if (direction == PositionConstants.WEST)
						leftConnectionAnchors.add(port);
					else
						rightConnectionAnchors.add(port);
				}
			}
			it = getTargetConnectionAnchors().iterator();
			while (it.hasNext()) {
				port = (PortConnectionAnchor) it.next();
				// CR386077: SplitActivity True and False outputs switch
				// positions based on their targets
				int direction = port.getOrientation();
				if (direction == PositionConstants.NONE) {
					Point loc = port.getOwner().getParent().getBounds()
							.getLocation();
					// loc.x += size.width/2;
					// loc.y += size.height/2;
					Iterator itc = port.getConnections().iterator();
					if (itc.hasNext()) {
						while (itc.hasNext()) {
							PolylineConnection conn = (PolylineConnection) itc
									.next();
							ConnectionAnchor otherPort;
							if (port == conn.getSourceAnchor())
								otherPort = conn.getTargetAnchor();
							else
								otherPort = conn.getSourceAnchor();
							if (otherPort.getOwner() == null)
								continue;
							Point otherLoc = otherPort.getOwner().getParent()
									.getBounds().getLocation();
							// Dimension otherSize =
							// ((BaseNodeFigure)otherPort.getOwner().getParent()).getBaseFigure().getPreferredSize();
							// otherLoc.x += otherSize.width/2;
							// otherLoc.y += otherSize.height/2;
							direction = loc.getPosition(otherLoc);
							if (direction == PositionConstants.NORTH)
								topConnectionAnchors.add(port);
							else if (direction == PositionConstants.SOUTH)
								bottomConnectionAnchors.add(port);
							else if (direction == PositionConstants.EAST)
								rightConnectionAnchors.add(port);
							else
								leftConnectionAnchors.add(port);
							((PortFigure) port.getOwner())
									.setOrientation(direction);
							break; // currently, only the first connection is
									// considered
						}
					} else {
						leftConnectionAnchors.add(port);
						((PortFigure) port.getOwner())
								.setOrientation(PositionConstants.WEST);
					}
				} else {
					// CR386077: SplitActivity True and False outputs switch
					// positions based on their targets
					if (direction == PositionConstants.NORTH)
						topConnectionAnchors.add(port);
					else if (direction == PositionConstants.SOUTH)
						bottomConnectionAnchors.add(port);
					else if (direction == PositionConstants.EAST)
						rightConnectionAnchors.add(port);
					else
						leftConnectionAnchors.add(port);
				}
			}

			Comparator comparePorts = new Comparator() {
				public int compare(Object arg0, Object arg1) {
					PortConnectionAnchor port0 = (PortConnectionAnchor) arg0;
					if (port0.getConnections().size() < 1)
						return 0;
					PortConnectionAnchor port1 = (PortConnectionAnchor) arg1;
					if (port1.getConnections().size() < 1)
						return 0;
					// CR386077: SplitActivity True and False outputs switch
					// positions based on their targets
					if (port0.getOrientation() != PositionConstants.NONE)
						return 0;
					if (port1.getOrientation() != PositionConstants.NONE)
						return 0;

					PolylineConnection conn0 = (PolylineConnection) port0
							.getConnections().get(0);
					ConnectionAnchor otherPort0;
					if (port0 == conn0.getSourceAnchor())
						otherPort0 = conn0.getTargetAnchor();
					else
						otherPort0 = conn0.getSourceAnchor();
					Point otherLoc0 = otherPort0.getOwner().getParent()
							.getBounds().getLocation();
					// Dimension otherSize0 =
					// ((BaseNodeFigure)otherPort0.getOwner().getParent()).getBaseFigure().getPreferredSize();
					// otherLoc0.x += otherSize0.width/2;
					// otherLoc0.y += otherSize0.height/2;

					PolylineConnection conn1 = (PolylineConnection) port1
							.getConnections().get(0);
					ConnectionAnchor otherPort1;
					if (port1 == conn1.getSourceAnchor())
						otherPort1 = conn1.getTargetAnchor();
					else
						otherPort1 = conn1.getSourceAnchor();
					Point otherLoc1 = otherPort1.getOwner().getParent()
							.getBounds().getLocation();
//					Dimension otherSize1 = ((BaseNodeFigure) otherPort1
//							.getOwner().getParent()).getBaseFigure()
//							.getPreferredSize();
					// otherLoc1.x += otherSize1.width/2;
					// otherLoc1.y += otherSize1.height/2;

					switch (((PortFigure) port0.getOwner()).getOrientation()) {
					case PositionConstants.NORTH:
					case PositionConstants.SOUTH:
						return otherLoc0.x - otherLoc1.x;
					case PositionConstants.EAST:
					case PositionConstants.WEST:
						return otherLoc0.y - otherLoc1.y;
					}
					return 0;
				}
			};

			// order the anchors on each side so connection lines don't cross
			Collections.sort(topConnectionAnchors, comparePorts);
			Collections.sort(leftConnectionAnchors, comparePorts);
			Collections.sort(bottomConnectionAnchors, comparePorts);
			Collections.sort(rightConnectionAnchors, comparePorts);
		}
	}

	private void placeAnchors() {
		determineAnchorPositions();
		if (anchorsVisible) {
			Iterator it;
			PortConnectionAnchor port;
			int y, x;

			Dimension sz = baseFigure.getPreferredSize();
			LayoutManager layout = getLayoutManager();
			// CR389070: Figures are abbreviating rule figures names and making
			// them unreadable
			Dimension d = getAnchorSize();

			// left ports
			x = 0;
			y = FigureConstants.PORT_SPACING / 2;
			// CR374981: Long activity labels do not get wrapped or truncated
			// keep a constant inset on all 4 sides of the icon
			// if ( topConnectionAnchors.size()>0 )
			y += d.height;
			y += (sz.height - (leftConnectionAnchors.size() * (d.height + FigureConstants.PORT_SPACING))) / 2;
			it = leftConnectionAnchors.iterator();
			while (it.hasNext()) {
				port = (PortConnectionAnchor) it.next();

				Rectangle r = new Rectangle(x, y, -1, -1);
				Rectangle oldrect = (Rectangle) layout.getConstraint(port
						.getOwner());
				if (!r.equals(oldrect))
					layout.setConstraint(port.getOwner(), new Rectangle(x, y,
							-1, -1));

				y += d.height + FigureConstants.PORT_SPACING;
			}

			// right ports
			x = sz.width;
			// CR374981: Long activity labels do not get wrapped or truncated
			// if ( leftConnectionAnchors.size()>0 )
			x += d.width;
			y = FigureConstants.PORT_SPACING / 2;
			// CR374981: Long activity labels do not get wrapped or truncated
			// if ( topConnectionAnchors.size()>0 )
			y += d.height;
			y += (sz.height - (rightConnectionAnchors.size() * (d.height + FigureConstants.PORT_SPACING))) / 2;
			it = rightConnectionAnchors.iterator();
			while (it.hasNext()) {
				port = (PortConnectionAnchor) it.next();

				Rectangle r = new Rectangle(x, y, -1, -1);
				Rectangle oldrect = (Rectangle) layout.getConstraint(port
						.getOwner());
				if (!r.equals(oldrect))
					layout.setConstraint(port.getOwner(), new Rectangle(x, y,
							-1, -1));

				y += d.height + FigureConstants.PORT_SPACING;
			}

			// top ports
			y = 0;
			x = FigureConstants.PORT_SPACING / 2;
			// CR374981: Long activity labels do not get wrapped or truncated
			// if ( leftConnectionAnchors.size()>0 )
			x += d.width;
			x += (sz.width - (topConnectionAnchors.size() * (d.width + FigureConstants.PORT_SPACING))) / 2;
			it = topConnectionAnchors.iterator();
			while (it.hasNext()) {
				port = (PortConnectionAnchor) it.next();

				Rectangle r = new Rectangle(x, y, -1, -1);
				Rectangle oldrect = (Rectangle) layout.getConstraint(port
						.getOwner());
				if (!r.equals(oldrect))
					layout.setConstraint(port.getOwner(), new Rectangle(x, y,
							-1, -1));

				x += d.width + FigureConstants.PORT_SPACING;
			}

			// bottom ports
			y = sz.height;
			// CR374981: Long activity labels do not get wrapped or truncated
			// if ( topConnectionAnchors.size()>0 )
			y += d.height;
			x = FigureConstants.PORT_SPACING / 2;
			// CR374981: Long activity labels do not get wrapped or truncated
			// if ( leftConnectionAnchors.size()>0 )
			x += d.width;
			x += (sz.width - (bottomConnectionAnchors.size() * (d.width + FigureConstants.PORT_SPACING))) / 2;
			it = bottomConnectionAnchors.iterator();
			while (it.hasNext()) {
				port = (PortConnectionAnchor) it.next();

				Rectangle r = new Rectangle(x, y, -1, -1);
				Rectangle oldrect = (Rectangle) layout.getConstraint(port
						.getOwner());
				if (!r.equals(oldrect))
					layout.setConstraint(port.getOwner(), new Rectangle(x, y,
							-1, -1));

				x += d.width + FigureConstants.PORT_SPACING;
			}
		}
	}

	/**
	 * CR389070: Figures are abbreviating rule figures names and making them
	 * unreadable Anchor size is now dependent on icon size, not constant. Asks
	 * the connection anchor for its size.
	 */
	private Dimension getAnchorSize() {
		Dimension d = new Dimension(FigureConstants.PORT_SIDE,
				FigureConstants.PORT_SIDE);
		if (!connectionAnchors.isEmpty()) {
			Iterator iter = connectionAnchors.values().iterator();
			PortConnectionAnchor a = (PortConnectionAnchor) iter.next();
			d = a.getOwner().getPreferredSize();
		}
		return d;
	}

	/**
	 * @return the anchor insets
	 */
	protected final Insets getAnchorInsets() {
		Insets in = new Insets(0, 0, 0, 0);
		// CR389070: Figures are abbreviating rule figures names and making them
		// unreadable
		Dimension d = getAnchorSize();
		// CR374981: Long activity labels do not get wrapped or truncated
		// if ( leftConnectionAnchors.size()>0 )
		in.left = d.width;
		// CR374981: Long activity labels do not get wrapped or truncated
		// if ( rightConnectionAnchors.size()>0 )
		in.right = d.width;
		// CR374981: Long activity labels do not get wrapped or truncated
		// if ( topConnectionAnchors.size()>0 )
		in.top = d.height;
		// CR374981: Long activity labels do not get wrapped or truncated
		// if ( bottomConnectionAnchors.size()>0 )
		in.bottom = d.height;
		return in;
	}

	public void invalidate() {
		super.invalidate();
	}

	/**
	 * validate method override to arrange the input and output ports around the
	 * links of the node figures
	 */
	public void validate() {
		placeAnchors();
		// CR389070: Figures are abbreviating rule figures names and making them
		// unreadable
		int x = 0;
		int y = 0;
		// if anchors are not visible (CompoundNodeFigure is maximized) always
		// set position at 0,0
		if (anchorsVisible) {
			Dimension d = getAnchorSize();
			// CR374981: Long activity labels do not get wrapped or truncated
			// if ( leftConnectionAnchors.size()>0 )
			x = d.width;
			// CR374981: Long activity labels do not get wrapped or truncated
			// if ( topConnectionAnchors.size()>0 )
			y = d.height;
		}
		getLayoutManager().setConstraint(baseFigure,
				new Rectangle(x, y, -1, -1));
		layout();
		super.validate();
	}

	/***************************************************************************
	 * Miscellaneous
	 **************************************************************************/

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.Figure#useLocalCoordinates()
	 */
	protected boolean useLocalCoordinates() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.IFigure#setBounds(org.eclipse.draw2d.geometry.Rectangle)
	 */
	public void setBounds(Rectangle rect) {
		Rectangle r = rect.getCopy();
		r.x = 0;
		r.y = 0;
		if (anchorsVisible) {
			// CR389070: Figures are abbreviating rule figures names and making
			// them unreadable
			Dimension d = getAnchorSize();
			// CR374981: Long activity labels do not get wrapped or truncated
			// if ( leftConnectionAnchors.size()>0 )
			{
				r.x += d.width;
				r.width -= d.width;
			}
			// CR374981: Long activity labels do not get wrapped or truncated
			// if ( rightConnectionAnchors.size()>0 )
			r.width -= d.width;
			// CR374981: Long activity labels do not get wrapped or truncated
			// if ( topConnectionAnchors.size()>0 )
			{
				r.y += d.height;
				r.height -= d.height;
			}
			// CR374981: Long activity labels do not get wrapped or truncated
			// if ( bottomConnectionAnchors.size()>0 )
			r.height -= d.height;
		}
		baseFigure.setBounds(r);
		r.setLocation(rect.getLocation());
		super.setBounds(r);
	}

	public Rectangle getBounds() {
		Dimension size = baseFigure.getBounds().getSize();
		if (anchorsVisible) {
			// CR389070: Figures are abbreviating rule figures names and making
			// them unreadable
			Dimension d = getAnchorSize();
			// CR374981: Long activity labels do not get wrapped or truncated
			// if ( leftConnectionAnchors.size()>0 )
			size.width += d.width;
			// CR374981: Long activity labels do not get wrapped or truncated
			// if ( rightConnectionAnchors.size()>0 )
			size.width += d.width;
			// CR374981: Long activity labels do not get wrapped or truncated
			// if ( topConnectionAnchors.size()>0 )
			size.height += d.height;
			// CR374981: Long activity labels do not get wrapped or truncated
			// if ( bottomConnectionAnchors.size()>0 )
			size.height += d.height;
		}
		Rectangle r = super.getBounds();
		r.setSize(size);
		return r;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.IFigure#getPreferredSize(int, int)
	 */
	public Dimension getPreferredSize(int wHint, int hHint) {
		Dimension size = baseFigure.getPreferredSize(wHint, hHint).getCopy();
		if (anchorsVisible) {
			// CR389070: Figures are abbreviating rule figures names and making
			// them unreadable
			Dimension d = getAnchorSize();
			// CR374981: Long activity labels do not get wrapped or truncated
			// if ( leftConnectionAnchors.size()>0 )
			size.width += d.width;
			// CR374981: Long activity labels do not get wrapped or truncated
			// if ( rightConnectionAnchors.size()>0 )
			size.width += d.width;
			// CR374981: Long activity labels do not get wrapped or truncated
			// if ( topConnectionAnchors.size()>0 )
			size.height += d.height;
			// CR374981: Long activity labels do not get wrapped or truncated
			// if ( bottomConnectionAnchors.size()>0 )
			size.height += d.height;
		}
		return size;
	}

	private static void moveToTop(IFigure parent, IFigure child) {
		parent.getChildren().remove(child);
		parent.getChildren().add(child);
	}

	/**
	 * move to top layer
	 */
	protected final void moveToTop() {
		IFigure parent = getParent();
		IFigure child = this;
		while (parent != null && !(parent instanceof Layer)) {
			child = parent;
			parent = parent.getParent();
		}
		if (parent instanceof Layer) {
			parent.getChildren().remove(child);
			parent.getChildren().add(child);
		}
	}

	/***************************************************************************
	 * IBaseFigure methods delegate to the baseFigure
	 **************************************************************************/

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.gem.diagram.editor.figures.IBaseFigure#setText(java.lang.String)
	 */
	public void setText(String text) {
		baseFigure.setText(text);
	}

	public void setIcon(Image image) {
		baseFigure.setIcon(image);
	}

	/**
	 * The description property of a DiagramNode is displayed as a tool tip.
	 * This method is used to set it. Passing a null or zero-length string will
	 * remove the tool tip
	 * 
	 * @param text
	 *            the description to use for this node's tool tip
	 * 
	 * @see IBaseFigure#setToolTipText(java.lang.String)
	 */
	public void setToolTipText(String text) {
		baseFigure.setToolTipText(text);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.gem.diagram.editor.figures.IBaseFigure#addDecorator(com.sybase.stf.gem.diagram.editor.figures.BaseFigureDecorator)
	 */
	public void addDecorator(BaseFigureDecorator decorator) {
		baseFigure.addDecorator(decorator);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.gem.diagram.editor.figures.IBaseFigure#removeDecorator()
	 */
	public void removeDecorator() {
		baseFigure.removeDecorator();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.gem.diagram.editor.figures.IBaseFigure#removeDecorator(int)
	 */
	public void removeDecorator(int position) {
		baseFigure.removeDecorator(position);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.gem.diagram.editor.figures.IBaseFigure#getDecorators()
	 */
	public List getDecorators() {
		return baseFigure.getDecorators();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.gem.diagram.editor.figures.IBaseFigure#getText()
	 */
	public String getText() {
		return baseFigure.getText();
	}

	/*
	 * CR374981: Long activity labels do not get wrapped or truncated This
	 * method was added to the IBaseFigure interface to support direct edit of
	 * figure labels on the canvas.
	 */
	public Rectangle getTextBounds() {
		Rectangle r = baseFigure.getTextBounds().getCopy();
		r.x += getBounds().x;
		r.y += getBounds().y;
		return r;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.gem.diagram.editor.figures.IBaseFigure#getIcon()
	 */
	public Image getIcon() {
		return baseFigure.getIcon();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.gem.diagram.editor.figures.IBaseFigure#getToolTipText()
	 */
	public String getToolTipText() {
		return baseFigure.getToolTipText();
	}
}