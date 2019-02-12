/*******************************************************************************
 * Copyright (c) 2006, 2007 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.ui.internal.guiutils;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

/**
 * Creates a class to be used to build a control for a collapseable area inside
 * a scrollable composite. This will take care of scrollbar resize.
 * 
 * Example use:
 * 
 * CollapsableSection cs = new CollapsableSection(toolkit, scomp, "General",
 * true) { public void setCollapsableContent(Composite composite) { // place
 * your content on this composite.... } }); cs.createControl(composite,
 * horizontalSpan);
 * 
 * @author mengbo
 * 
 */
public abstract class CollapsableSection {
	// internal fields needed for doing the section and computing the scrollbar
	// on a collapse.
	private FormToolkit _toolkit;

	private Section _section;

	private ScrolledComposite _scomp;

	private String _title;

	private int _expansionStyle;

	/**
	 * Creates a class to be used to build the control for a collapseable area.
	 * 
	 * @param toolkit 
	 * 
	 * @param scomp
	 *            the scrollable composite used inside the container. This is
	 *            used to force resizing of the bars on dinking the control.
	 * @param title
	 *            the string to place next to the collapsable icon.
	 * @param bCollapsed
	 *            should this area start collapsed?
	 */
	public CollapsableSection(FormToolkit toolkit, ScrolledComposite scomp,
			String title, boolean bCollapsed) {
		_toolkit = toolkit;
		_scomp = scomp;
		_title = title;
		_expansionStyle = ExpandableComposite.TWISTIE
				| ExpandableComposite.FOCUS_TITLE;
		if (bCollapsed) {
			_expansionStyle |= ExpandableComposite.COMPACT;
		} else {
			_expansionStyle |= ExpandableComposite.EXPANDED;
		}
	}

	/**
	 * Build the GUI parts of the Collapsable area into the given composite.
	 * NOTE: you must set the layout on this composite before adding swt
	 * widgets.
	 * 
	 * @param composite
	 */
	abstract public void setCollapsableContent(Composite composite);

	/**
	 * A convient way to build the widget into a control and place it on the
	 * page. NOTE: this method will build the Section and call
	 * setCollapsableContent with a composite to fill in the inner area.
	 * 
	 * @param parent
	 * @param horizontalSpan
	 *            how many columns should this control span.
	 * @return Control that was added to the composite.
	 */
	public Control createControl(Composite parent, int horizontalSpan) {
		_section = _toolkit.createSection(parent, _expansionStyle);
		_section.clientVerticalSpacing = 5; // space between the dink and the
		// controls in when opened.
		// _section.marginHeight = 5; // indents the entire section.
		// _section.marginWidth = 5; // indents the entire section.
		_section.setText(_title);
		_toolkit.createCompositeSeparator(_section);
		_section.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent event) {
				SWTUtils.computeScrollArea(_scomp, (Composite) _scomp
						.getContent());
			}
		});

		Composite content = new Composite(_section, SWT.NULL);
		setCollapsableContent(content);

		_section.setClient(content);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = horizontalSpan;
		_section.setLayoutData(gd);

		return _section;
	}

	/**
	 * Programmatically changes expanded state.
	 * 
	 * @param expanded
	 *            the new expanded state
	 */
	public void setExpanded(boolean expanded) {
		if (_section != null) {
			_section.setExpanded(expanded);

			// NOTE: internal calling of expanding doesn't fire
			// the event to resize. We do it here.
			SWTUtils.computeScrollArea(_scomp, (Composite) _scomp.getContent());
		}
	}

	/**
	 * Return the internal Section control for more advanced things.
	 * 
	 * @return Section
	 */
	public Section getSection() {
		return _section;
	}
}
