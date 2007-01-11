package org.eclipse.jst.pagedesigner.editors.actions;

import org.eclipse.ui.IPageLayout;

public class OutlineViewAction extends ShowViewAction 
{
    public final static String ID = "org.eclipse.jst.pagedesigner.editors.actions.OutlineViewAction"; //$NON-NLS-1$

    public OutlineViewAction() 
    {
        super(ActionsMessages
                .getString("OutlineViewAction.Menu.OutlineView")
              , IPageLayout.ID_OUTLINE); //$NON-NLS-1$
    }
}
