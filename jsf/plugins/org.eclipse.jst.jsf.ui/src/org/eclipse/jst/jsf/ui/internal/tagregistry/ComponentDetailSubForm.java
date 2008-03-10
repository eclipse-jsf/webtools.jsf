package org.eclipse.jst.jsf.ui.internal.tagregistry;

import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IComponentTagElement;

/**
 * Details page for component details.
 * 
 * @author cbateman
 * 
 */
public class ComponentDetailSubForm extends AbstractDetailsForm
{
    private IComponentTagElement _curTagElement;


    @Override
    protected void doUpdateSelection(final Object newSelection)
    {
        if (newSelection instanceof IComponentTagElement)
        {
            _curTagElement = (IComponentTagElement) newSelection;
            final ComponentTypeInfo typeInfo = _curTagElement.getComponent();
            if (typeInfo != null)
            {
                final String formatText = "<form><p><b>Component Type:</b> %s</p> <p><b>Component Family:</b> %s</p> <p><b>Render Type:</b> %s</p></form>";
                final String componentType = typeInfo.getComponentType();
                final String componentFamily = typeInfo.getComponentFamily();
                final String renderType = typeInfo.getRenderFamily();

                getTextSection().setText(String.format(formatText,
                        componentType == null ? "" : componentType,
                        componentFamily == null ? "" : componentFamily,
                        renderType == null ? "" : renderType), true, false);
                getTextSection().refresh();
            }
        }
    }


    @Override
    protected String getTitle()
    {
        return "JSF Component Handler";
    }

}
