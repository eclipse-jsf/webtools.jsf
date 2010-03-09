/*******************************************************************************
 * Copyright (c) 2004, 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.ui.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.utils.EditorUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * This class represents a preference page that is contributed to the
 * Preferences dialog. By subclassing <samp>FieldEditorPreferencePage </samp>,
 * we can use the field support built into JFace that allows us to create a page
 * that is small and knows how to save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They are stored in the
 * preference store that belongs to the main plug-in class. That way,
 * preferences can be accessed directly via the preference store.
 * 
 * C.B: Copied from the GEMPreferences in the Faces Config Editor.
 */

public final class PDPreferencePage extends FieldEditorPreferencePage implements
        IWorkbenchPreferencePage
{

    // appearance

    private Group _cssLayoutGroup;

    // private BooleanField _enableAbsolute;
    //
    // private IntegerFieldEditor _artificialCellpadding;

    private class BooleanField extends BooleanFieldEditor
    {
        // private Composite parent;

        /**
         * @param name
         * @param label
         * @param parent
         */
        public BooleanField(String name, String label, Composite parent)
        {
            super(name, label, parent);
            // this.parent = parent;
        }

        // /**
        // * @return the change control button
        // */
        // public Button getButton() {
        // return getChangeControl(parent);
        // }
    }

    /**
     * Constructor
     */
    public PDPreferencePage()
    {
        super(GRID);
        setPreferenceStore(PDPlugin.getDefault().getPreferenceStore());
        setDescription(PreferenceMessages.PDPreferences_description);
    }

    /**
     * Creates the field editors. Field editors are abstractions of the common
     * GUI blocks needed to manipulate various types of preferences. Each field
     * editor knows how to save and restore itself.
     */
    public void createFieldEditors()
    {
        _cssLayoutGroup = new Group(getFieldEditorParent(), SWT.NULL);

        // note, we aren't saving the reference. It's assumed that parent
        // worries about destruction, persistence etc.
        /* _enableAbsolute = */addBooleanField(
                PDPreferences.CSS_ENABLE_ABSOLUTE_POSITIONING,
                PreferenceMessages.EditorPreferences_LABEL_CSSEnableAbsolutePositioning,
                _cssLayoutGroup);
        /* _artificialCellpadding = */addIntegerField(
                PDPreferences.CSS_USE_ARTIFICAL_CELL_PADDING,
                PreferenceMessages.EditorPreferences_LABEL_CSSArtificalCellPadding,
                _cssLayoutGroup);
    }

    
    @Override
    public boolean performOk()
    {
        final boolean succeeded = super.performOk();
        if (succeeded)
        {
            EditorUtil.refreshAllWPEDesignViewers();
        }
        return succeeded;
    }

    
    protected void initialize()
    {
        // Color use: Default canvas colors should pick up system defaults
        // enable or disable all of the color and font selection controls in the
        // preference dialog
        // depending on whether the "Use System Colors" checkbox is selected.
        super.initialize();

        ((GridLayout) getFieldEditorParent().getLayout()).numColumns = 1;
        _cssLayoutGroup.setLayout(new GridLayout(2, false));
        _cssLayoutGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL
                | GridData.VERTICAL_ALIGN_BEGINNING));
    }

    public void init(IWorkbench workbench)
    {
        // no initialization
    }

    private IntegerFieldEditor addIntegerField(String name, String labelText,
            Composite parent)
    {
        IntegerFieldEditor f = new IntegerFieldEditor(name, labelText, parent);
        addField(f);
        return f;
    }

    private BooleanField addBooleanField(String name, String labelText,
            Composite parent)
    {
        BooleanField f = new BooleanField(name, labelText, parent);
        addField(f);
        return f;
    }
}