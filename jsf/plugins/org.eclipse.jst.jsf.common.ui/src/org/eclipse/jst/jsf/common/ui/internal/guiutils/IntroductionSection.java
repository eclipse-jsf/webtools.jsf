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
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.help.HelpSystem;
import org.eclipse.help.IContext;
import org.eclipse.help.IHelpResource;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jst.jsf.common.ui.JSFUICommonPlugin;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * Provides a standard looking introduction section for a intro page for the
 * editors. This was taken and is suppose to look like the standard Sybase
 * workspace service editors intro page.
 * 
 * this was original written by Collinsc
 * 
 * @author collinsc,jchoi
 */
public class IntroductionSection extends SectionPart {
	private static Logger _log = JSFUICommonPlugin
			.getLogger(IntroductionSection.class);

	private String _editorId;

	private FormToolkit _toolkit;

	// private ImageHyperlink _helpImage;
	private Composite _textClient;

	private String _helpContextId;

	private String _helpTooltip;

	/**
	 * Basic constructor - no help with this one.
	 * 
	 * @param editorId
	 *            id of the editor this page is for.
	 * @param managedForm
	 * @param toolkit
	 */
	public IntroductionSection(String editorId, IManagedForm managedForm,
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
	public IntroductionSection(String editorId, IManagedForm managedForm,
			FormToolkit toolkit, final String contextId, String helpTooltip) {
		super(managedForm.getForm().getBody(), toolkit,
				ExpandableComposite.TITLE_BAR | Section.DESCRIPTION);
		super.initialize(managedForm);
		this._editorId = editorId;
		this._toolkit = toolkit;
		this._helpContextId = contextId;
		this._helpTooltip = helpTooltip;

		this._textClient = this._toolkit.createComposite(getSection(), SWT.NONE);
		this._textClient.setSize(32, 16);

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
		this._textClient.setLayout(rowLayout);

		this._toolkit.adapt(this._textClient, true, true);
		getSection().setTextClient(this._textClient);

		if (this._helpContextId != null) {
			// setup the help image.
			ImageHyperlink helpImage = new ImageHyperlink(this._textClient,
					SWT.NONE);
			this._toolkit.adapt(helpImage, true, true);
			helpImage.setImage(JSFUICommonPlugin.getDefault().getImage("help.gif"));
			if (this._helpTooltip != null) {
				helpImage.setToolTipText(this._helpTooltip);
			}
			helpImage.setBackground(getSection()
					.getTitleBarGradientBackground());
			helpImage.addHyperlinkListener(new HyperlinkAdapter() {
				public void linkActivated(HyperlinkEvent e) {
					IContext context = HelpSystem.getContext(_helpContextId);
					if (context != null) {
						IHelpResource[] topics = context.getRelatedTopics();
						if (topics != null && topics.length == 1) {
                            PlatformUI.getWorkbench().getHelpSystem().displayHelpResource(topics[0]
									.getHref());
						} else {
                            PlatformUI.getWorkbench().getHelpSystem().displayHelp(_helpContextId);
						}
					}
				}
			});
		}

		Composite client = this._toolkit.createComposite(getSection());
		createClient(client, this._toolkit);
		getSection().setClient(client);
	}

	private Composite createClient(Composite container, FormToolkit factory) {
		TableWrapLayout layout = new TableWrapLayout();
		layout.numColumns = 2;
		layout.makeColumnsEqualWidth = false;
		layout.horizontalSpacing = 20;
		layout.verticalSpacing = 20;
		container.setLayout(layout);

		IConfigurationElement element = getExtensions();

		if (element != null) {
			setPageDetails(element);

			IConfigurationElement[] children = element.getChildren();
			for (int ii = 0; ii < children.length; ii++) {
				processItems(container, factory, children[ii]);
			}
		} else {
			setText("No Introduction");

			setDescription("No Introduction page configuration found in the plugin.xml");
		}

		factory.paintBordersFor(container);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(container, _helpContextId);
		return container;
	}

	/**
	 * set the page details from the extensionpoint
	 * 
	 * @param element
	 */
	private void setPageDetails(IConfigurationElement element) {
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
	private void processItems(Composite parent, FormToolkit toolkit_,
			IConfigurationElement element) {
		String hyperlink = element.getAttribute("hyperlink"); //$NON-NLS-1$
		String iconPath = element.getAttribute("icon"); //$NON-NLS-1$
		String text = element.getAttribute("text"); //$NON-NLS-1$
		String heading = element.getAttribute("heading"); //$NON-NLS-1$
		String action = element.getAttribute("hyperlinkaction"); //$NON-NLS-1$
		//String actionparameters = element.getAttribute("actionparameters"); //$NON-NLS-1$

		if (iconPath != null && iconPath.length() > 0) {
			// add an icon to the page

			if (iconPath != null) {
				String iconName;
				if (iconPath.indexOf(IPath.SEPARATOR) != -1) {
					iconName = new Path(iconPath).lastSegment();
				} else {
					iconName = iconPath;
				}

				Plugin plugin = Platform.getPlugin(element
						.getDeclaringExtension().getContributor().getName());
				if (plugin instanceof AbstractUIPlugin) {
					ImageRegistry imageRegistry = ((AbstractUIPlugin) plugin)
							.getImageRegistry();
					Image image = imageRegistry.get(iconName);
					if (image == null) {
						ImageDescriptor imageDescriptor = AbstractUIPlugin
								.imageDescriptorFromPlugin(
										element.getDeclaringExtension()
												.getContributor().getName(), iconPath);
						imageRegistry.put(iconName, imageDescriptor);
						image = imageRegistry.get(iconName);
					}

					ImageContainer img = new ImageContainer(parent);
					img.setImage(image);
					TableWrapData td = new TableWrapData();
					td.rowspan = 2;
					img.setLayoutData(td);
				}
			}

			
		}

		if (heading != null && heading.length() > 0) {
			// add a header
			Label lbl = toolkit_.createLabel(parent, heading);
			lbl.setFont(JFaceResources.getHeaderFont());
		}

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
					_log.error("log.IntroductionSection.action.error",
							hyperlink, ee);
					JSFUICommonPlugin.getAlerts().detailError(hyperlink,
							"log.IntroductionSection.action.error", hyperlink,
							ee);
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
	 */
	private IConfigurationElement getExtensions() {
		// find all service editor page extensions
		IConfigurationElement element = null;

		// find all service editor parameter dialog extensions
		IConfigurationElement[] elements = Platform
				.getExtensionRegistry()
				.getConfigurationElementsFor(
						"org.eclipse.jst.jsf.common.ui.introductionPage");
		if (elements.length > 0) {
			for (int ii = 0; ii < elements.length; ii++) {
				// get extensions for this dialog
				// String extPluginId =
				// elements[ii].getDeclaringExtension().getNamespace();
				String editorId1 = elements[ii].getDeclaringExtension()
						.getSimpleIdentifier();

				// see if we have any contributuins of dialogs
				if (this._editorId.equals(editorId1)) {
					element = elements[ii];
					break;
				}
			}
		}
		return element;
	}

	public void setText(String text) {
		getSection().setText(text);
	}

	public void setDescription(String text) {
		getSection().setDescription(text);
	}

	public void setLayoutData(Object layoutData) {
		getSection().setLayoutData(layoutData);
	}

	public void setExpanded(boolean expanded) {
		getSection().setExpanded(expanded);
	}
}