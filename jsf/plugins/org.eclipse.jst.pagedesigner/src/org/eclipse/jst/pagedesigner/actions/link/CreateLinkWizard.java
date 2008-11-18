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
package org.eclipse.jst.pagedesigner.actions.link;

import java.util.Map;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.viewer.DesignRange;

/**
 * @author mengbo
 * @version 1.5
 */
public class CreateLinkWizard extends Wizard {
	private static final String WIZARD_TITLE = PDPlugin
			.getResourceString("CreateLinkWizard.Title"); //$NON-NLS-1$

	private static final String INTIAL_DEFAULT_PAGE_IMAGE = "newsuade_wiz.gif"; //$NON-NLS-1$

	private static final String PAGE_NAME = "first"; //$NON-NLS-1$

	private String _pageTitle;

	private EditPart _part;

	private DesignRange _range;

	private Map<String, ILinkCreator> _linkMap;

	private String _linkType;

	/**
	 * @param part
	 * @param range
	 * @param linkMap
	 */
	public CreateLinkWizard(EditPart part, DesignRange range, Map<String, ILinkCreator> linkMap) {
		this._part = part;
		this._range = range;
		this._linkMap = linkMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.IWizard#addPages()
	 */
	public void addPages() {
		addPage(new LinkWizardPage(PAGE_NAME, _pageTitle, this._part,
				this._range, this._linkMap));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.IWizard#canFinish()
	 */
	public boolean canFinish() {
		return super.canFinish();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.IWizard#performFinish()
	 */
	public boolean performFinish() {
		LinkWizardPage page = (LinkWizardPage) getPage(PAGE_NAME);
		this._linkType = page.getChosenLinkType();
		return true;
	}

	/**
	 * @param pageTitle
	 */
	public void setPageTitle(String pageTitle) {
		_pageTitle = pageTitle;
		initializeDefaultPageImageDescriptor();
	}

	/**
	 * 
	 */
	protected void initializeDefaultPageImageDescriptor() {
		ImageDescriptor desc = PDPlugin.getDefault().getImageDescriptor(
				INTIAL_DEFAULT_PAGE_IMAGE);
		setDefaultPageImageDescriptor(desc);
		setWindowTitle(WIZARD_TITLE);
	}

	/**
	 * @return the link type
	 */
	public String getChosenLinkType() {
		return this._linkType;
	}
}
