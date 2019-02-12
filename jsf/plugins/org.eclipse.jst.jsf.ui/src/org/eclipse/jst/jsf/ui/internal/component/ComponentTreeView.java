/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.ui.internal.component;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jst.jsf.common.ui.JSFUICommonPlugin;
import org.eclipse.jst.jsf.common.ui.internal.utils.JSFSharedImages;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.ResolverUtil;
import org.eclipse.jst.jsf.designtime.DesignTimeApplicationManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.IPage;
import org.eclipse.ui.part.MessagePage;
import org.eclipse.ui.part.Page;
import org.eclipse.ui.part.PageBook;
import org.eclipse.ui.part.PageBookView;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;

/**
 * A basic page-based view, similar to outline, that shows the design-time
 * component tree for a JSF view definition.
 */

public class ComponentTreeView extends PageBookView
{

    private FormToolkit _toolkit;

    @Override
    public void init(IViewSite site) throws PartInitException
    {
        super.init(site);
        setTitleImage(JSFUICommonPlugin.getDefault().getImage(JSFSharedImages.GENERIC_VIEWROOT_IMG));
    }

    @Override
    protected IPage createDefaultPage(final PageBook book)
    {
        final MessagePage page = new MessagePage();
        initPage(page);
        page.createControl(book);
        page.setMessage(Messages.ComponentTreeView_NothingToDisplayMessage);
        return page;
    }

    @Override
    protected PageRec doCreatePage(final IWorkbenchPart part)
    {
        _toolkit = new FormToolkit(getPageBook().getDisplay());
        final ComponentPage page = new ComponentPage(getDocumentFromPart(part),
                _toolkit);
        initPage(page);
        page.createControl(getPageBook());
        return new PageRec(part, page);
    }

    @Override
    protected void doDestroyPage(final IWorkbenchPart part,
            final PageRec pageRecord)
    {
        pageRecord.page.dispose();
        pageRecord.dispose();
    }

    private IDocument getDocumentFromPart(final IWorkbenchPart part)
    {
        return (IDocument) part.getAdapter(IDocument.class);
    }

    /**
     * The view shows the palette associated with the active editor.
     * 
     * @see PageBookView#getBootstrapPart()
     */
    @Override
    protected IWorkbenchPart getBootstrapPart()
    {
        final IWorkbenchPage page = getSite().getPage();
        if (page != null)
        {
            return page.getActiveEditor();
        }
        return null;
    }

    @Override
    protected boolean isImportant(final IWorkbenchPart part)
    {
        final IDocument document = getDocumentFromPart(part);

        if (document != null)
        {
            final IFile file = ResolverUtil.getFileForDocument(document);

            if (file != null)
            {
                final DesignTimeApplicationManager manager = DesignTimeApplicationManager
                .getInstance(file.getProject());

                if (manager != null)
                {
                    return manager.hasDTFacesContext(file);
                }
            }
        }

        // fall through, then no, not important.
        return false;
    }

    private static class ComponentPage extends Page
    {
        private final IDocument            _document;
        private final FormToolkit          _toolkit;
        private ComponentMasterDetailBlock _masterDetailBlock;
        private Form                       _form;
        private DTJSFViewModel             _model;

        public ComponentPage(final IDocument document, final FormToolkit toolkit)
        {
            _document = document;
            _toolkit = toolkit;
        }

        @Override
        public void createControl(final Composite parent)
        {
            _model = new DTJSFViewModel(
                    (IStructuredDocument) _document);

            _form = _toolkit.createForm(parent);
            _form.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
            _masterDetailBlock = new ComponentMasterDetailBlock(_model);
            _masterDetailBlock.createContent(_toolkit, _form);
        }

        @Override
        public void dispose()
        {
            super.dispose();
            _model.dispose();
        }

        @Override
        public Control getControl()
        {
            return _form;
        }

        @Override
        public void setFocus()
        {
            // do nothing
        }
        
        
    }
}