package org.eclipse.jst.jsf.ui.internal.tagregistry;

import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ValidatorTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IValidatorTagElement;

/**
 * Details form for validator tags.
 * @author cbateman
 *
 */
public class ValidatorDetailsForm extends AbstractDetailsForm
{

    private IValidatorTagElement    _validatorTagElement;
    @Override
    protected void doUpdateSelection(Object newSelection)
    {
        if (newSelection instanceof IValidatorTagElement)
        {
            _validatorTagElement = (IValidatorTagElement) newSelection;
            final ValidatorTypeInfo typeInfo = _validatorTagElement.getValidator();

            if (typeInfo != null)
            {
                final String formatText = "<form><p><b>Class:</b> %s</p>  <p><b>Validator Id:</b> %s</p></form>";
                final String className = typeInfo.getClassName();
                final String validatorId = typeInfo.getValidatorId();

                getTextSection().setText(String.format(formatText,
                        className == null ? "" : className,
                        validatorId == null ? "" : validatorId), true, false);
                getTextSection().refresh();
            }
        }
    }

    @Override
    protected String getTitle()
    {
        return "Validator";
    }

}
