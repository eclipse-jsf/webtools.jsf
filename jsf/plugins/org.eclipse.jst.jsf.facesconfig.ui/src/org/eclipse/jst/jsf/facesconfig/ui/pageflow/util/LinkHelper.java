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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PFLink;

/**
 * Helper link class, store the information of link, its' previous and
 * subsequent links.
 * 
 * @author Xiao-guang Zhang
 */
public class LinkHelper {
	/** the pflink is visited or not */
	private boolean bVisited = false;

	/** subsequent links */
	private List subsequentLinks = null;

	/* previous links */
	private List previousLinks = null;

	/** the pflink */
	private PFLink element = null;

	/**
	 * constructor
	 * 
	 * @param element -
	 *            pflink
	 */
	public LinkHelper(PFLink element) {
		this.element = element;
	}

	/**
	 * get the encapsulated pflink
	 * 
	 * @return - the encapsulated pflink
	 */
	public Object getModel() {
		return element;
	}

	/**
	 * check the pflink is visited or not.
	 * 
	 * @return - the pflink is visited or not.
	 */
	public boolean isVisited() {
		return bVisited;
	}

	/**
	 * get the subsequent links
	 * 
	 * @return - the subsequent links
	 */
	public List getSubsequentLinks() {
		return subsequentLinks;
	}

	/**
	 * add the subsequent link
	 * 
	 * @param link -
	 *            one subsequent link
	 */
	public void addSubsequentLink(LinkHelper link) {
		if (subsequentLinks == null) {
			subsequentLinks = new ArrayList();
		}
		subsequentLinks.add(link);
	}

	/**
	 * add a previous link
	 * 
	 * @return - a previous link
	 */
	public void addPreviousLink(LinkHelper link) {
		if (previousLinks == null) {
			previousLinks = new ArrayList();
		}
		previousLinks.add(link);
	}

	/**
	 * get previous links
	 * 
	 * @return - previous links
	 */
	public List getPreviousLink() {
		return previousLinks;
	}

	/**
	 * set the visit status
	 * 
	 */
	public void setVisit(boolean bVisited) {
		this.bVisited = bVisited;
	}

}
