package org.eclipse.jst.jsf.ui.internal.tagregistry;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IComponentTagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IConverterTagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IValidatorTagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.Namespace;
import org.eclipse.jst.jsf.ui.internal.tagregistry.TaglibContentProvider.TagRegistryInstance;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * A master-detail block for selecting items in a tag registry and examining
 * their details.
 * 
 * @author cbateman
 * 
 */
public class TagRegistryMasterDetailBlock implements ISelectionChangedListener
{
    private ComponentDetailSubForm _componentDetails;
    private TagRegistryDetailsForm _tagRegistryDetails;
    private NamespaceDetailsForm   _namespaceDetails;
    private ConverterDetailsForm   _converterDetails;
    private ValidatorDetailsForm   _validatorDetails;
    private BlankDetailsForm       _blankDetails;

    private TagRegistryMasterForm  _masterForm;
    private Composite              _detailsPanel;
    private AbstractDetailsForm    _curPage;
    private FormToolkit            _toolkit;
    private StackLayout             _detailLayout;


    /**
     * @param toolkit
     * @param form
     */
    public void createContent(final FormToolkit toolkit, final Form form)
    {
        _toolkit = toolkit;

        final GridLayout layout = new GridLayout();
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        form.getBody().setLayout(layout);
        final SashForm sashForm = new SashForm(form.getBody(), SWT.NULL);
        // sashForm.setData("form", managedForm); //$NON-NLS-1$
        toolkit.adapt(sashForm, false, false);
        sashForm.setMenu(form.getBody().getMenu());
        sashForm.setLayoutData(new GridData(GridData.FILL_BOTH));
        createMasterPart(toolkit, sashForm);
        createDetailsPart(sashForm);

        _masterForm.contributeToHead(form);
        createToolBarActions(form);
        form.updateToolBar();
    }

    /**
     * Disposes the master detail form
     */
    public void dispose()
    {
        _masterForm.dispose();
    }
    
    private void createMasterPart(final FormToolkit toolkit,
            final Composite parent)
    {
        _masterForm = new TagRegistryMasterForm(toolkit);
        _masterForm.initialize(this);
        _masterForm.createContents(parent);
    }

    private void createToolBarActions(final Form form)
    {
        _masterForm.contributeActions(form.getToolBarManager());
    }

    private void createDetailsPart(final Composite parent)
    {
        _detailsPanel = new Composite(parent, SWT.NONE);
        _detailLayout = new StackLayout();
        _detailsPanel.setLayout(_detailLayout);
        registerPages(_detailsPanel);

        _detailLayout.topControl = _curPage.getControl();
        _detailsPanel.layout();
    }

    private void registerPages(final Composite detailsPanel)
    {
        _componentDetails = new ComponentDetailSubForm();
        _componentDetails.initialize(_toolkit);
        _componentDetails.createContents(detailsPanel);

        _tagRegistryDetails = new TagRegistryDetailsForm();
        _tagRegistryDetails.initialize(_toolkit);
        _tagRegistryDetails.createContents(detailsPanel);

        _namespaceDetails = new NamespaceDetailsForm();
        _namespaceDetails.initialize(_toolkit);
        _namespaceDetails.createContents(detailsPanel);

        _converterDetails = new ConverterDetailsForm();
        _converterDetails.initialize(_toolkit);
        _converterDetails.createContents(detailsPanel);

        _validatorDetails = new ValidatorDetailsForm();
        _validatorDetails.initialize(_toolkit);
        _validatorDetails.createContents(detailsPanel);

        _blankDetails = new BlankDetailsForm();
        _blankDetails.initialize(_toolkit);
        _blankDetails.createContents(detailsPanel);
        
        _curPage = _blankDetails;
    }

    private AbstractDetailsForm selectPage(final Object forModel)
    {
        if (forModel instanceof IComponentTagElement)
        {
            return _componentDetails;
        }
        else if (forModel instanceof IConverterTagElement)
        {
            return _converterDetails;
        }
        else if (forModel instanceof IValidatorTagElement)
        {
            return _validatorDetails;
        }
        else if (forModel instanceof TagRegistryInstance)
        {
            return _tagRegistryDetails;
        }
        else if (forModel instanceof Namespace)
        {
            return _namespaceDetails;
        }
        return _blankDetails;
    }

    public void selectionChanged(final SelectionChangedEvent event)
    {
        final Object selectedObj = ((IStructuredSelection) event.getSelection())
        .getFirstElement();
        final AbstractDetailsForm page = selectPage(selectedObj);
        if (page != null)
        {
            final AbstractDetailsForm fpage = page;
            BusyIndicator.showWhile(_detailsPanel.getDisplay(), new Runnable()
            {
                public void run()
                {
                    final AbstractDetailsForm oldPage = _curPage;
                    _curPage = fpage;
                    // commit the current page
                    if (oldPage != null && oldPage.isDirty())
                    {
                        oldPage.commit(false);
                    }
                    // refresh the new page
                    if (fpage.isStale())
                    {
                        fpage.refresh();
                    }
                    _curPage.selectionChanged(event.getSelection());
                    //_pageBook.showPage(_curPage.getTextSection().getControl());
                    _detailLayout.topControl = _curPage.getControl();
                    _detailsPanel.layout();
                }
            });
        }
    }
}
