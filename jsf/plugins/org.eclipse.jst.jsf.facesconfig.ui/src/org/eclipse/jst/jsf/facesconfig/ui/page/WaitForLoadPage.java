package org.eclipse.jst.jsf.facesconfig.ui.page;

import org.eclipse.jst.jsf.facesconfig.ui.EditorMessages;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.ScrolledForm;

/**
 * A page the exists only be used before the editor's model is fully loaded
 * as a status placeholder while the user waits.
 * 
 * @author cbateman
 *
 */
public class WaitForLoadPage extends FormPage 
{

    /**
     * @param editor the parent
     * @param id  the id of the editor within its parent
     * @param title the title displayed to the user on the editor's tab
     */
    public WaitForLoadPage(FormEditor editor, String id, String title) {
        super(editor, id, title);
    }

    @Override
    protected void createFormContent(IManagedForm managedForm) {
        // get the form
        ScrolledForm form = managedForm.getForm();
        //FormToolkit toolkit = getEditor().getToolkit();
        form.setText(EditorMessages.FacesConfigEditor_WaitForLoadPage_Title);
    }

}
