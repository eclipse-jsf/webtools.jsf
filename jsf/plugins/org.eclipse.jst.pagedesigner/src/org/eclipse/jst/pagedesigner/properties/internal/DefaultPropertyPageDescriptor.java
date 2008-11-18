/*******************************************************************************
 * Copyright (c) 2007, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.properties.internal;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogField;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.metadataprocessors.MetaDataContext;
import org.eclipse.jst.pagedesigner.editors.properties.IPropertyPageDescriptor;
import org.eclipse.jst.pagedesigner.meta.EditorCreator;
import org.eclipse.jst.pagedesigner.meta.IAttributeRuntimeValueType;
import org.eclipse.jst.pagedesigner.meta.internal.CellEditorFactoryRegistry;
import org.eclipse.jst.pagedesigner.properties.ITabbedPropertiesConstants;
import org.eclipse.swt.widgets.Composite;
import org.w3c.dom.Element;

/**
 * Property descriptor implementation for when meta data is available, but the
 * meta data type is not adaptable to a {@link IPropertyPageDescriptor}.
 * 
 * It is assumed that the value type is
 * {@link org.eclipse.jst.jsf.taglibprocessing.attributevalues.StringType}.
 */
public class DefaultPropertyPageDescriptor implements IPropertyPageDescriptor
{
    private Entity _tagEntity;
    private Entity _attrEntity;

    private IStructuredDocumentContext _sdContext;
    private MetaDataContext _mdContext;

    /**
     * Constructor
     * 
     * @param tagEntity
     * @param attrEntity
     */
    public DefaultPropertyPageDescriptor(Entity tagEntity, Entity attrEntity)
    {
        _tagEntity = tagEntity;
        _attrEntity = attrEntity;
    }

    public String getAttributeName()
    {
        return _attrEntity.getId();
    }

    public String getCategory()
    {
        return ITabbedPropertiesConstants.OTHER_CATEGORY;
    }

    public CellEditor getCellEditor(Composite parent)
    {
        Element element = (Element) IStructuredDocumentContextResolverFactory.INSTANCE
                .getDOMContextResolver(getStructuredDocumentContext())
                .getNode();
        return CellEditorFactoryRegistry.getInstance().createCellEditor(parent,
                this, element);
    }

    public String getDescription()
    {
        return null;
    }

    public DialogField getDialogFieldEditor()
    {
        EditorCreator creator = EditorCreator.getInstance();
        return creator.createDialogFieldWithWrapper(getUri(), getTagName(),
                this, null);
    }

    public String getLabel()
    {
        return getAttributeName() + ":"; //$NON-NLS-1$
    }

    public String getTagName()
    {
        return _tagEntity.getId();
    }

    public String getUri()
    {
        return _tagEntity.getModel().getCurrentModelContext().getUri();
    }

    public String getValueType()
    {
        return IAttributeRuntimeValueType.STRING;
    }

    public boolean isRequired()
    {
        return false;
    }

    public MetaDataContext getMetaDataContext()
    {
        return _mdContext;
    }

    public IStructuredDocumentContext getStructuredDocumentContext()
    {
        return _sdContext;
    }

    public void setMetaDataContext(MetaDataContext context)
    {
        _mdContext = context;
    }

    public void setStructuredDocumentContext(IStructuredDocumentContext context)
    {
        _sdContext = context;
    }

    public Object getAdapter(Class adapter)
    {
        return null;
    }

    public String toString()
    {
        return getUri()
                + "/" + getTagName() + "/" + getAttributeName() + " (Default Descriptor)"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }
}
