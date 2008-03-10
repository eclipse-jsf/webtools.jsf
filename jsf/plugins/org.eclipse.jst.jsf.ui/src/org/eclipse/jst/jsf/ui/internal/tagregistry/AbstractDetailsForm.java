package org.eclipse.jst.jsf.ui.internal.tagregistry;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

abstract class AbstractDetailsForm
{
    private Form   _form;
    private XMLTextSection _textSection;
    private FormToolkit _toolkit;

    public void createContents(Composite parent)
    {
//        final RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
//        rowLayout.fill = true;
//        parent.setLayout(rowLayout);
        _textSection = new XMLTextSection(_toolkit, parent, getTitle());
        _textSection._section.setExpanded(true);
    }

    public Control getControl()
    {
        return getTextSection().getControl();
    }
    
    protected XMLTextSection getTextSection()
    {
        return _textSection;
    }

    protected final FormToolkit getToolkit()
    {
        return  _toolkit;
    }
    
    protected abstract String getTitle();

    public final void selectionChanged(ISelection selection)
    {
        if (selection instanceof IStructuredSelection)
        {
            Object selectionObj = ((IStructuredSelection) selection)
                    .getFirstElement();
            doUpdateSelection(selectionObj);
        }
    }

    protected abstract void doUpdateSelection(final Object newSelection);

    public void commit(boolean onSave)
    {
        // do nothing
    }

    public void dispose()
    {
        // do nothing
    }

    public final void initialize(final FormToolkit toolkit)
    {
        _toolkit = toolkit;
    }

    protected final Form getForm()
    {
        return _form;
    }

    public boolean isDirty()
    {
        // never dirty
        return false;
    }

    public boolean isStale()
    {
        // always stale
        return true;
    }

    public void refresh()
    {
        // useless since model isn't refershed until after selection changed
    }

    public void setFocus()
    {
        if (_textSection != null)
        {
            _textSection._formText.setFocus();
        }
    }

    public boolean setFormInput(Object input)
    {
        // never called, do nothing
        return false;
    }

    protected static class XMLTextSection
    {
        private Section            _section;
        //private Composite          _componentTypeInfoComposite;
        private FormText           _formText;
        private Composite _detailSubForm;

        public XMLTextSection(final FormToolkit toolkit, final Composite parent,
                final String title)
        {
            _detailSubForm = toolkit.createComposite(parent);
            _detailSubForm.setLayout(new GridLayout(1, false));

            _section = toolkit.createSection(_detailSubForm,
                    ExpandableComposite.TREE_NODE
                            | ExpandableComposite.CLIENT_INDENT);
            _section.setLayout(new GridLayout(1, true));
            _section.setText(title);

            // _componentTypeInfoComposite = toolkit.createComposite(_section,
            // SWT.BORDER);
            // _componentTypeInfoComposite.setLayoutData(new GridData(SWT.FILL,
            // SWT.FILL, true, true));
            // _componentTypeInfoComposite.setLayout(new GridLayout(1, true));

            _formText = toolkit.createFormText(_section, true);
            _formText.setText("", false, false);

            _section.setClient(_formText);
        }

        public void setText(final String text, final boolean parseTags,
                final boolean expandURLs)
        {
            _formText.setText(text, parseTags, expandURLs);
        }

        public Control getControl()
        {
            return _detailSubForm;
        }
        
        public void refresh()
        {
            _detailSubForm.layout();
            _detailSubForm.redraw();
            _detailSubForm.update();
        }
    }

}
