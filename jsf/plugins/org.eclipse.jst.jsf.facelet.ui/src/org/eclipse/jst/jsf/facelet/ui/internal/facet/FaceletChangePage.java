/*******************************************************************************
 * Copyright (c) 2008, 2022 Oracle Corporation, and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facelet.ui.internal.facet;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.typed.BeanProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jst.jsf.facelet.core.internal.facet.ChangeActionType;
import org.eclipse.jst.jsf.facelet.core.internal.facet.FacetChangeModel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.wst.common.project.facet.ui.AbstractFacetWizardPage;

/**
 * A common facet change UI page, mainly used for facet install/uninstall.
 * 
 * @author cbateman
 *
 */
public abstract class FaceletChangePage extends AbstractFacetWizardPage
{
    private Button             _chgDefaultSuffix;
    private Button             _chgViewHandler;
    private Button             _chgConfigureListener;
    private Button             _chgWebappLifecycleListener;

    private DataBindingContext _bindingContext;
    private FacetChangeModel _dataModel;

    /**
     * @param name
     */
    public FaceletChangePage(String name)
    {
        super(name);
    }

    public void setConfig(final Object config)
    {
        _dataModel = (FacetChangeModel) config;
    }

    /**
     * @return the name of the change based on the actionType enumeration.
     */
    protected String getTextForChangeType()
    {
        final ChangeActionType actionType = _dataModel.getChangeActionType();

        switch (actionType)
        {
            case ADD:
                return "Add";

            case REMOVE:
                return "Remove";
        }

        return "*ERROR*";
    }

    private void initDefaultSuffixButton(final Composite parent)
    {
        _chgDefaultSuffix = new Button(parent, SWT.CHECK);
        _chgDefaultSuffix.setText(getTextForChangeType() + " '.xhtml' DEFAULT_SUFFIX parameter");
        _chgDefaultSuffix.setSelection(_dataModel.isChgDefaultSuffix());
        _chgDefaultSuffix.setLayoutData(new RowData());
        IObservableValue<Boolean> modelObservable = BeanProperties.value(FacetChangeModel.class, "chgDefaultSuffix", boolean.class).observe(_dataModel);
        ISWTObservableValue<Boolean> buttonObservable = WidgetProperties.buttonSelection().observe(_chgDefaultSuffix);

        _bindingContext.bindValue(buttonObservable, modelObservable);
    }

    private void initViewHandlerButton(final Composite parent)
    {
        _chgViewHandler = new Button(parent, SWT.CHECK);
        _chgViewHandler.setText(getTextForChangeType() + " Facelet view handler");
        _chgViewHandler.setSelection(_dataModel.isChgViewHandler());
        _chgViewHandler.setLayoutData(new RowData());
        IObservableValue<Boolean> modelObservable = BeanProperties.value(FacetChangeModel.class, "chgViewHandler", boolean.class).observe(_dataModel);
        ISWTObservableValue<Boolean> buttonObservable = WidgetProperties.buttonSelection().observe(_chgViewHandler);

        _bindingContext.bindValue(buttonObservable, modelObservable);
    }

    private void initConfigureListener(final Composite parent)
    {
        _chgConfigureListener = new Button(parent, SWT.CHECK);
        _chgConfigureListener
                .setText(getTextForChangeType() + " configure listener (needed by some Tomcat containers)");
        _chgConfigureListener.setSelection(_dataModel.isChgConfigureListener());
        _chgConfigureListener.setLayoutData(new RowData());
        IObservableValue<Boolean> modelObservable = BeanProperties.value(FacetChangeModel.class, "chgConfigureListener", boolean.class).observe(_dataModel);
        ISWTObservableValue<Boolean> buttonObservable = WidgetProperties.buttonSelection().observe(_chgConfigureListener);

        _bindingContext.bindValue(buttonObservable, modelObservable);
    }

    private void initWebappLifecycleListener(final Composite parent)
    {
        _chgWebappLifecycleListener = new Button(parent, SWT.CHECK);
        _chgWebappLifecycleListener
                .setText(getTextForChangeType() + " web application lifecycle listener (needed by some Tomcat containers)");
        _chgWebappLifecycleListener.setSelection(_dataModel
                .isChgConfigureListener());
        _chgWebappLifecycleListener.setLayoutData(new RowData());
        IObservableValue<Boolean> modelObservable = BeanProperties.value(FacetChangeModel.class, "chgWebAppLifecycleListener", boolean.class).observe(_dataModel);
        ISWTObservableValue<Boolean> buttonObservable = WidgetProperties.buttonSelection().observe(_chgWebappLifecycleListener);

        _bindingContext.bindValue(buttonObservable, modelObservable);
    }

    public void createControl(final Composite parent)
    {
        setTitle("Facelet");
        setMessage("Configure Facelet settings");

        _bindingContext = new DataBindingContext();

        // WizardPageSupport.create(this, _bindingContext);

        final Composite control = new Composite(parent, SWT.NONE);
        final RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
        rowLayout.fill = true;
        control.setLayout(rowLayout);

        final Group webXMLGroup = new Group(control, SWT.NONE);
        webXMLGroup.setLayout(rowLayout);
        webXMLGroup.setText("Deployment Descriptor (web.xml) Configuration");
        initDefaultSuffixButton(webXMLGroup);
        initConfigureListener(webXMLGroup);
        initWebappLifecycleListener(webXMLGroup);

        final Group facesConfigGroup = new Group(control, SWT.NONE);
        facesConfigGroup.setLayout(rowLayout);
        facesConfigGroup
                .setText("Application (faces-config.xml) Configuration");
        initViewHandlerButton(facesConfigGroup);

        setControl(control);
    }
}
