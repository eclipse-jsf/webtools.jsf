package org.eclipse.jst.jsf.ui.internal.tagregistry;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IComponentTagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IConverterTagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IValidatorTagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.Namespace;
import org.eclipse.jst.jsf.common.ui.internal.form.AbstractDetailsForm;
import org.eclipse.jst.jsf.common.ui.internal.form.AbstractMasterDetailBlock;
import org.eclipse.jst.jsf.common.ui.internal.form.AbstractMasterForm;
import org.eclipse.jst.jsf.common.ui.internal.form.AbstractXMLSectionsDetailsForm;
import org.eclipse.jst.jsf.ui.internal.tagregistry.TaglibContentProvider.TagRegistryInstance;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * A master-detail block for selecting items in a tag registry and examining
 * their details.
 * 
 * @author cbateman
 * 
 */
public class TagRegistryMasterDetailBlock extends AbstractMasterDetailBlock
{
    ComponentDetailSubForm _componentDetails;
    TagRegistryDetailsForm _tagRegistryDetails;
    NamespaceDetailsForm   _namespaceDetails;
    ConverterDetailsForm   _converterDetails;
    ValidatorDetailsForm   _validatorDetails;


    @Override
    protected AbstractMasterForm createMasterPart(final FormToolkit toolkit)
    {
        return new TagRegistryMasterForm(toolkit);
    }


    @Override
    protected final List<AbstractDetailsForm> createDetailPages()
    {
        final List<AbstractDetailsForm>  detailForms = new ArrayList<AbstractDetailsForm>();
        _componentDetails = new ComponentDetailSubForm();
        detailForms.add(_componentDetails);

        _tagRegistryDetails = new TagRegistryDetailsForm();
        detailForms.add(_tagRegistryDetails);

        _namespaceDetails = new NamespaceDetailsForm();
        detailForms.add(_namespaceDetails);

        _converterDetails = new ConverterDetailsForm();
        detailForms.add(_converterDetails);

        _validatorDetails = new ValidatorDetailsForm();
        detailForms.add(_validatorDetails);

        return detailForms;
    }


    @Override
    protected AbstractXMLSectionsDetailsForm doSelectPage(final Object forModel)
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
        return null;
    }

}
