/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.editors;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jst.pagedesigner.dnd.internal.DesignerSourceDropTargetListener;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.ui.texteditor.ITextEditorDropTargetListener;
import org.eclipse.wst.sse.ui.StructuredTextEditor;

/**
 * @author mengbo
 */
public class DesignerStructuredTextEditorJSP extends StructuredTextEditor
{
    private ITextEditorDropTargetListener _dropTargetListener;
    private DropTarget _dropTarget;

    @Override
    protected void initializeDrop(final ITextViewer viewer)
    {
        int operations = DND.DROP_COPY | DND.DROP_MOVE;
        _dropTarget = new DropTarget(viewer.getTextWidget(), operations);
        _dropTargetListener = 
            new DesignerSourceDropTargetListener(this);
        _dropTarget.setTransfer(_dropTargetListener.getTransfers());
        _dropTarget.addDropListener(_dropTargetListener);
    }

    @Override
    public IAction getAction(final String actionID)
    {
        try
        {
            return super.getAction(actionID);
        } catch (final Exception e)
        {
            return null;
        }
    }

    @Override
    public Object getAdapter(final Class required)
    {
        if (ITextEditorDropTargetListener.class.equals(required))
        {
            final DesignerSourceDropTargetListener listener = new DesignerSourceDropTargetListener(
                    this);
            return listener;
        }
        return super.getAdapter(required);
    }

    @Override
    public void dispose()
    {
        if (_dropTargetListener != null)
        {
            _dropTargetListener = null;
        }
        if (_dropTarget != null)
        {
            _dropTarget.dispose();
            _dropTarget = null;
        }
        
        super.dispose();
    }
}
