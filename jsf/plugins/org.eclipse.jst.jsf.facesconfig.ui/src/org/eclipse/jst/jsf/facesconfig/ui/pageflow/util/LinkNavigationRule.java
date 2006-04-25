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

import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PFLink;
import org.eclipse.wtp.jsf.facesconfig.emf.NavigationCaseType;
import org.eclipse.wtp.jsf.facesconfig.emf.NavigationRuleType;

/**
 * This class stores the relationship between link and navigation rules.
 * 
 * @author Xiao-guang Zhang
 */
public class LinkNavigationRule {
	/** related the navigation rule and case */
	private NavigationRuleType navigationRule = null;

	private NavigationCaseType navigationCase = null;

	/** the first pflink */
	private PFLink firstLink = null;

	/** the second pflink, maybe is null */
	private PFLink secondLink = null;

	/**
	 * get the generated navigation rule for this link.
	 * 
	 * @return - the generated navigation rule for this link
	 */
	public NavigationRuleType getNavigationRule() {
		return navigationRule;
	}

	/**
	 * get the generated navigation case for this link.
	 * 
	 * @return - the generated navigation case for this link
	 */
	public NavigationCaseType getNavigationCase() {
		return navigationCase;
	}

	/**
	 * set the generated navigation rule and case for this link.
	 * 
	 * @param navigationRule
	 * @param navigationCase
	 */
	public void setNavigationRule(NavigationRuleType navigationRule,
			NavigationCaseType navigationCase) {
		this.navigationRule = navigationRule;
		this.navigationCase = navigationCase;
	}

	/**
	 * set two links for the navigation rules
	 * 
	 * @param link
	 * @param nextLink
	 */
	public void setLinks(PFLink firstlink, PFLink secondLink) {
		this.firstLink = firstlink;
		this.secondLink = secondLink;
	}

	/**
	 * get the first link
	 * 
	 * @return - the first link
	 */
	public PFLink getFirstLink() {
		return firstLink;
	}

	/**
	 * get the second link
	 * 
	 * @return - the second link
	 */
	public PFLink getSecondLink() {
		return secondLink;
	}

}
