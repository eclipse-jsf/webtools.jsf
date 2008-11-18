/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.ui.internal.form;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

/**
 * A detail form that uses XML text sections.
 * 
 * @author cbateman
 * 
 */
public abstract class AbstractXMLSectionsDetailsForm extends
        AbstractDetailsForm
{
    private Map<Object, XMLTextSection> _textSections;
    private Composite                   _detailFormComposite;

    /**
     * @param parent
     */
    @Override
    public final void createContents(final Composite parent)
    {
        _detailFormComposite = getToolkit().createComposite(parent, SWT.NONE);
        final RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
        rowLayout.fill = true;
        _detailFormComposite.setLayout(rowLayout);
        // take a copy of what's returned so the sub-class can't control
        // the map reference
        _textSections = Collections
                .unmodifiableMap(new HashMap<Object, XMLTextSection>(
                        createXMLTextSections(_detailFormComposite)));

        final Set<XMLTextSection> expandedSections = getInitiallyExpanded(_textSections);
        for (final Map.Entry<? extends Object, XMLTextSection> entry : _textSections
                .entrySet())
        {
            final XMLTextSection section = entry.getValue();
            if (expandedSections.contains(section))
            {
                section._section.setExpanded(true);
            }
        }
    }

    /**
     * @param parent
     *            the parent that should be used for all XMLTextSections
     * @return a map keyed by an object type understood by the sub-class
     *         containing as values the XMLTextSections
     */
    protected abstract Map<? extends Object, XMLTextSection> createXMLTextSections(
            final Composite parent);

    /**
     * @param sections
     * @return the subset of XMLTextSections in the provided map that are to be
     *         expanded. NOTE: all elements in the returned set must be in
     *         sections.getValues.
     */
    protected abstract Set<XMLTextSection> getInitiallyExpanded(
            final Map<Object, XMLTextSection> sections);

    /**
     * @return the control for this form
     */
    @Override
    public Control getControl()
    {
        return _detailFormComposite;
    }

    /**
     * @param selection
     */
    public final void selectionChanged(final ISelection selection)
    {
        if (selection instanceof IStructuredSelection)
        {
            final Object selectionObj = ((IStructuredSelection) selection)
                    .getFirstElement();
            doUpdateSelection(selectionObj);
        }
    }

    @Override
    public void commit(final boolean onSave)
    {
        // do nothing
    }

    @Override
    public void dispose()
    {
        // do nothing
    }

    @Override
    public void setFocus()
    {
        // do nothing; sub-classes should override to pick an XMLTextSection
        // where they want focus.
    }

    /**
     * An XML text section
     * 
     */
    protected final static class XMLTextSection
    {
        private final Section _section;
        private FormText      _formText;

        /**
         * @param toolkit
         * @param parent
         * @param title
         */
        public XMLTextSection(final FormToolkit toolkit,
                final Composite parent, final String title)
        {
            _section = toolkit.createSection(parent,
                    ExpandableComposite.TREE_NODE
                            | ExpandableComposite.CLIENT_INDENT);
            _section.setLayoutData(new RowData());
            _section.setText(title);

            _formText = toolkit.createFormText(_section, true);
            _formText.setText("", false, false); //$NON-NLS-1$

            _section.setClient(_formText);
        }

        /**
         * @param text
         * @param parseTags
         * @param expandURLs
         */
        public void setText(final String text, final boolean parseTags,
                final boolean expandURLs)
        {
            _formText.setText(text, parseTags, expandURLs);
        }

        /**
         * @return the parent control.
         */
        public Control getControl()
        {
            return _section;
        }

        /**
         * Force a visual relayout and update.
         */
        public void refresh()
        {
            _section.getParent().layout(true, true);
        }
    }

}
