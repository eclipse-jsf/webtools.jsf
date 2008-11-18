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
package org.eclipse.jst.jsf.facesconfig.ui.section;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.help.HelpSystem;
import org.eclipse.help.IContext;
import org.eclipse.help.IHelpResource;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jst.jsf.facesconfig.ui.EditorPlugin;
import org.eclipse.jst.jsf.facesconfig.ui.page.IFacesConfigPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.eclipse.ui.forms.widgets.Section;


/**
 * The abstract implementation of IFacesConfigSection.
 * 
 * 
 * @author jchoi, xgzhang, sfshi
 * 
 * @version 1.0
 */
public abstract class AbstractFacesConfigSection extends SectionPart implements
		IFacesConfigSection, ISelectionProvider, ISelectionChangedListener {
	/** tool kit */
	private FormToolkit toolkit;

	/** help context id */
	private String helpContextId = null;

	/** help tool tip string */
	private String helpTooltip = null;

	/** model object */
	private Object input = null;

	private IFacesConfigPage page;

	private List selectionChangedListeners = new ArrayList();

	/** help image */
	private final static Image HELP_IMAGE = EditorPlugin.getDefault().getImage(
			"help.gif"); //$NON-NLS-1$

	/**
	 * Constructor with help option.

	 * @param parent 
	 * @param managedForm
	 * @param page 
	 * @param toolkit
	 * @param helpContextId
	 * @param helpTooltip
	 */
	public AbstractFacesConfigSection(Composite parent,
			IManagedForm managedForm, IFacesConfigPage page,
			FormToolkit toolkit, String helpContextId, String helpTooltip) {
		super(parent, toolkit, ExpandableComposite.TITLE_BAR
				| ExpandableComposite.TWISTIE | Section.DESCRIPTION
				| ExpandableComposite.EXPANDED);
		super.initialize(managedForm);
		this.page = page;
		this.toolkit = toolkit;
		this.helpContextId = helpContextId;
		this.helpTooltip = helpTooltip;
	}

	public void dispose() {
		selectionChangedListeners.clear();
		if (input != null) 
			removeAdaptersFromInput(input);		
		super.dispose();
	}
	/**
	 * 
	 */
	public void initialize() {
		if (helpContextId != null) {
			createTextClientWithHelp();
		}

		getSection().setLayout(new GridLayout());

		Composite clientContainer = toolkit.createComposite(getSection());
		GridData gd = new GridData(GridData.FILL_BOTH);
		clientContainer.setLayoutData(gd);

		createContents(clientContainer, toolkit);
		getSection().setClient(clientContainer);

	}

	/**
	 * 
	 * 
	 */
	private void createTextClientWithHelp() {
		ImageHyperlink helpImage = new ImageHyperlink(getSection(), SWT.NULL);

		toolkit.adapt(helpImage, true, true);

		helpImage.setImage(HELP_IMAGE);

		helpImage.setBackground(getSection().getTitleBarGradientBackground());
		if (helpTooltip != null) {
			helpImage.setToolTipText(helpTooltip);
		}

		helpImage.addHyperlinkListener(new HyperlinkAdapter() {
			public void linkActivated(HyperlinkEvent e) {
				IContext context = HelpSystem.getContext(helpContextId);
				if (context != null) {
					IHelpResource[] topics = context.getRelatedTopics();
					if (topics != null && topics.length == 1) {
						EditorPlugin.getDefault().getWorkbench()
								.getHelpSystem().displayHelpResource(
										topics[0].getHref());
					} else {
						EditorPlugin.getDefault().getWorkbench()
								.getHelpSystem().displayHelp(helpContextId);
					}
				}
			}
		});

		getSection().setTextClient(helpImage);
	}

	/**
	 * The sub calss should implement this method to create and fill the
	 * contents in the section
	 * 
	 * @param container
	 * @param toolkit_
	 */
	protected abstract void createContents(Composite container,
			FormToolkit toolkit_);

	/**
	 * get the input object of this section.
	 */
	public Object getInput() {
		return input;
	}

	/**
	 * set input object for this section.
	 */
	public void setInput(Object newInput) {
		if (input != null)
			removeAdaptersFromInput(input);

		input = newInput;

		if (newInput != null)
			addAdaptersOntoInput(newInput);
		// if (this.getSection().isExpanded())
		refreshAll();
	}

	/**
	 * Remove adapters from the input object when the section changes it's
	 * input. Sub classes should overwrite this method to remove the adapters
	 * that they added on.
	 * 
	 * @param oldInput
	 */
	protected void removeAdaptersFromInput(Object oldInput) {
	    // do nothing; subs should override
	}

	/**
	 * Add adapters onto the input object when the section has new input. Sub
	 * classes should their own adapters.
	 * 
	 * @param newInput
	 */
	protected void addAdaptersOntoInput(Object newInput) {
        // do nothing; subs should override
	}

	/**
	 * @return the page that this section lies in.
	 */
	public IFacesConfigPage getPage() {
		return page;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ISelectionProvider#getSelection()
	 */
	public ISelection getSelection() {
		return StructuredSelection.EMPTY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ISelectionProvider#setSelection(org.eclipse.jface.viewers.ISelection)
	 */
	public void setSelection(ISelection selection) {
	    // do nothing: no selection change
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ISelectionProvider#addSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
	 */
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		selectionChangedListeners.add(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ISelectionProvider#removeSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
	 */
	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {
		selectionChangedListeners.remove(listener);
	}

	/**
	 * transfer the selection changed event to detail part.
	 */
	public void selectionChanged(SelectionChangedEvent event) {

		for (Iterator listeners = selectionChangedListeners.iterator(); listeners
				.hasNext();) {
			ISelectionChangedListener listener = (ISelectionChangedListener) listeners
					.next();
			listener.selectionChanged(new SelectionChangedEvent(this,
					event != null ? event.getSelection()
							: StructuredSelection.EMPTY));
		}
	}

	/**
	 * the convenient method to get the AdapterFactory instance of the editor;
	 * 
	 * @return the AdapterFactory instance.
	 */
	public AdapterFactory getAdapterFactory() {
		return (AdapterFactory) getPage().getEditor().getAdapter(
				AdapterFactory.class);
	}

	/**
	 * the convenient method to get the EditingDomain instance of the editor;
	 * 
	 * @return the EditingDomain instance.
	 */
	public EditingDomain getEditingDomain() {
		return (EditingDomain) getPage().getEditor().getAdapter(
				EditingDomain.class);
	}
    
    public void clearAll()
    {
        // do nothing, sub-classes may choose to override to clear their contents
    }
}
