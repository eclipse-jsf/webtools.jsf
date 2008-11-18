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
package org.eclipse.jst.jsf.common.ui.internal.guiutils;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.help.HelpSystem;
import org.eclipse.help.IContext;
import org.eclipse.help.IHelpResource;
import org.eclipse.jface.action.IAction;
import org.eclipse.jst.jsf.common.ui.JSFUICommonPlugin;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.eclipse.ui.forms.widgets.Section;


/**
 * @author jchoi
 * @version
 */
public class OverviewSection extends SectionPart {

	private static final String HELP_IMAGE_FILE = "help.gif"; //$NON-NLS-1$

	private static Logger log = JSFUICommonPlugin.getLogger(OverviewSection.class);

	private String editorId;

	private FormToolkit toolkit;

	private ImageHyperlink helpImage;

	private Composite textClient;

	private String helpContextId;

	private String helpTooltip;

	/**
	 * @param editorId 
	 * @param managedForm 
	 * @param toolkit
	 */
	public OverviewSection(String editorId, IManagedForm managedForm,
			FormToolkit toolkit) {
		this(editorId, managedForm, toolkit, null, null);

	}

	/**
	 * Constructor with help option.
	 * 
	 * @param editorId
	 *            id of the editor this page is for.
	 * @param managedForm
	 * @param toolkit
	 * @param helpContextId
	 * @param helpTooltip
	 */
	public OverviewSection(String editorId, IManagedForm managedForm,
			FormToolkit toolkit, String helpContextId, String helpTooltip) {
		super(managedForm.getForm().getBody(), toolkit,
				ExpandableComposite.TITLE_BAR | Section.DESCRIPTION);
		super.initialize(managedForm);
		this.editorId = editorId;
		this.toolkit = toolkit;
		this.helpContextId = helpContextId;
		this.helpTooltip = helpTooltip;

		this.textClient = this.toolkit.createComposite(getSection(), SWT.NONE);
		this.textClient.setSize(32, 16);

		init();

		Composite client = this.toolkit.createComposite(getSection());
		//createClient(client, this.toolkit);
		getSection().setClient(client);
	}

	/**
	 * @param editor 
	 * @param managedForm 
	 * @param toolkit
	 * @param style
	 */
	public OverviewSection(FormEditor editor, IManagedForm managedForm,
			FormToolkit toolkit, int style) {
		super(managedForm.getForm().getBody(), toolkit,
				ExpandableComposite.TITLE_BAR | style);
		super.initialize(managedForm);
		this.toolkit = toolkit;

		this.textClient = this.toolkit.createComposite(getSection(), SWT.NONE);
		this.textClient.setSize(32, 16);

		init();
	}

	/**
	 * @param editor
	 * @param managedForm
	 * @param toolkit
	 * @param style
	 * @param helpContextId
	 * @param helpTooltip
	 */
	public OverviewSection(FormEditor editor, IManagedForm managedForm,
			FormToolkit toolkit, int style, String helpContextId,
			String helpTooltip) {
		super(managedForm.getForm().getBody(), toolkit,
				ExpandableComposite.TITLE_BAR | style);
		super.initialize(managedForm);
		this.toolkit = toolkit;

		this.textClient = toolkit.createComposite(getSection(), SWT.NONE);
		this.textClient.setSize(32, 16);
		this.helpContextId = helpContextId;
		this.helpTooltip = helpTooltip;
		init();
	}

	/**
	 * 
	 */
	public void initialize() {
		Composite client = toolkit.createComposite(getSection());
		//createClient(client, toolkit);
		getSection().setClient(client);
	}

	private void init() {
		RowLayout rowLayout = new RowLayout();
		rowLayout.wrap = false;
		rowLayout.pack = false;
		rowLayout.justify = true;
		rowLayout.type = SWT.HORIZONTAL;
		rowLayout.marginLeft = 0;
		rowLayout.marginTop = 0;
		rowLayout.marginRight = 0;
		rowLayout.marginBottom = 0;
		rowLayout.spacing = 0;
		textClient.setLayout(rowLayout);

		helpImage = new ImageHyperlink(textClient, SWT.NONE);
		toolkit.adapt(helpImage, true, true);
		toolkit.adapt(textClient, true, true);
		helpImage.setImage(JSFUICommonPlugin.getDefault().getImage(HELP_IMAGE_FILE)); 
		getSection().setTextClient(textClient);
		if (helpTooltip != null) {
			helpImage.setToolTipText(helpTooltip);
		}
		helpImage.setBackground(getSection().getTitleBarGradientBackground());
		helpImage.addHyperlinkListener(new HyperlinkAdapter() {
			public void linkActivated(HyperlinkEvent e) {
				IContext context = HelpSystem.getContext(helpContextId);
				if (context != null) {
					IHelpResource[] topics = context.getRelatedTopics();
					if (topics != null && topics.length == 1) {
                        PlatformUI.getWorkbench().getHelpSystem().displayHelpResource(topics[0].getHref());
					} else {
                        PlatformUI.getWorkbench().getHelpSystem().displayHelp(helpContextId);
					}
				}
			}
		});

		/*
		 * Composite client = _toolkit.createComposite(getSection());
		 * createClient(client, _toolkit); getSection().setClient(client);
		 */
	}

	/**
	 * set the page details from the extensionpoint
	 * 
	 * @param element
	 */
	protected void setPageDetails(IConfigurationElement element) {
		setText(element.getAttribute("name")); //$NON-NLS-1$
		setDescription(element.getAttribute("description")); //$NON-NLS-1$
	}

	/**
	 * add the extension elements to the page
	 * 
	 * @param parent
	 * @param toolkit_
	 * @param element
	 */
	public void processItems(Composite parent, FormToolkit toolkit_,
			IConfigurationElement element) {
		String hyperlink = element.getAttribute("hyperlink"); //$NON-NLS-1$      
		String text = element.getAttribute("text"); //$NON-NLS-1$
		String action = element.getAttribute("hyperlinkaction"); //$NON-NLS-1$
		//String actionparameters = element.getAttribute("actionparameters"); //$NON-NLS-1$

		if (hyperlink != null && hyperlink.length() > 0) {
			Hyperlink hypr = toolkit_.createHyperlink(parent, hyperlink,
					SWT.NONE);
			if (action != null && action.length() > 0) {
				try {
					final IAction thisAction = (IAction) element
							.createExecutableExtension("hyperlinkaction"); //$NON-NLS-1$
					hypr.addHyperlinkListener(new HyperlinkAdapter() {
						public void linkActivated(HyperlinkEvent e) {
							thisAction.run();
						}
					});
				} catch (Exception ee) {
					// log.IntroductionSection.action.error=Failed to launch the
					// link {0}.
					log
							.error("log.OverviewSection.action.error", //$NON-NLS-1$
									hyperlink, ee);
					JSFUICommonPlugin.getAlerts().detailError(hyperlink,
							"log.OverviewSection.action.error", hyperlink, ee); //$NON-NLS-1$
				}
			}
		}

		if (text != null && text.length() > 0) {
			FormText form = toolkit_.createFormText(parent, false);
			form.setText(text, false, false);
		}
	}

	/**
	 * Get the extension elements for the Introduction pages
	 * @param extensionPoint 
	 * @return the configuration element
	 */
	public IConfigurationElement getExtensions(String extensionPoint) {
		// find all service editor page extensions
		IConfigurationElement element = null;

		// find all service editor parameter dialog extensions
		IConfigurationElement[] elements = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(extensionPoint);
		if (elements.length > 0) {
			for (int ii = 0; ii < elements.length; ii++) {
				// get extensions for this dialog
				// String extPluginId =
				// elements[ii].getDeclaringExtension().getNamespace();
				String anEditorId = elements[ii].getDeclaringExtension()
						.getSimpleIdentifier();

				// see if we have any contributuins of dialogs
				if (this.editorId.equals(anEditorId)) {
					element = elements[ii];
					break;
				}
			}
		}
		return element;
	}

	private void setText(String text) {
		getSection().setText(text);
	}

	private void setDescription(String text) {
		getSection().setDescription(text);

	}
}
