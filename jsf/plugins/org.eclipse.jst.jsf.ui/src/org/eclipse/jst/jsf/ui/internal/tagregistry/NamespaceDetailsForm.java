package org.eclipse.jst.jsf.ui.internal.tagregistry;

import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.Namespace;


/**
 * Details form for a namespace
 * 
 * @author cbateman
 *
 */
public class NamespaceDetailsForm extends AbstractDetailsForm
{
    
    private Namespace _namespace;

    @Override
    protected void doUpdateSelection(Object newSelection)
    {
        if (newSelection instanceof Namespace)
            
        {
            _namespace = (Namespace) newSelection;

            final String formatText = "<form><p><b>Name:</b> %s</p> <p><b>Uri:</b> %s</p> </form>";
            final String displayName = _namespace.getDisplayName();
            final String uri = _namespace.getNSUri();

            getTextSection().setText(String.format(formatText,
                    displayName == null ? "" : displayName,
                    uri == null ? "" : uri
                    ), true, false);
            getTextSection().refresh();
        }
    }
    @Override
    protected String getTitle()
    {
        return "Library";
    }


}
