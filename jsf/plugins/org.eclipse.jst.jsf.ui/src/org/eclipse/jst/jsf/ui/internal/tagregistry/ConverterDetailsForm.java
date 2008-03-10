package org.eclipse.jst.jsf.ui.internal.tagregistry;

import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ConverterTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IConverterTagElement;

/**
 * Details form for a converter tag.
 * 
 * @author cbateman
 *
 */
public class ConverterDetailsForm extends AbstractDetailsForm
{
    private IConverterTagElement    _converterTagElement;
    @Override
    protected void doUpdateSelection(Object newSelection)
    {
        if (newSelection instanceof IConverterTagElement)
        {
            _converterTagElement = (IConverterTagElement) newSelection;
            final ConverterTypeInfo typeInfo = _converterTagElement.getConverter();

            if (typeInfo != null)
            {
                final String formatText = "<form><p><b>Class:</b> %s</p>  <p><b>Converter Id:</b> %s</p></form>";
                final String className = typeInfo.getClassName();
                final String converterId = typeInfo.getConverterId();

                getTextSection().setText(String.format(formatText,
                        className == null ? "" : className,
                        converterId == null ? "" : converterId), true, false);
                getTextSection().refresh();
            }
        }
    }

    @Override
    protected String getTitle()
    {
        return "Converter";
    }
}
