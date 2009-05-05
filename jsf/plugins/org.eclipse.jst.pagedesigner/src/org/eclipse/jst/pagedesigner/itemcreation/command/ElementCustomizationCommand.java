/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.itemcreation.command;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.jst.jsf.core.internal.tld.CMUtil;
import org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.TagCreationAttribute;
import org.eclipse.jst.pagedesigner.editors.palette.ITagDropSourceData;
import org.eclipse.jst.pagedesigner.editors.palette.MetadataTagDropSourceData;
import org.eclipse.jst.pagedesigner.editors.palette.TagToolCreationAdapter;
import org.eclipse.jst.pagedesigner.editors.palette.impl.PaletteElementTemplateHelper;
import org.eclipse.jst.pagedesigner.itemcreation.CreationData;
import org.eclipse.jst.pagedesigner.itemcreation.internal.AbstractCreationCommand;
import org.eclipse.wst.xml.core.internal.contentmodel.CMAttributeDeclaration;
import org.eclipse.wst.xml.core.internal.contentmodel.CMElementDeclaration;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.w3c.dom.Element;

/**
 * A command to customize a new tag element
 * <p>
 * <b>Provisional API - subject to change</b>
 * </p>
 * 
 * Clients may sub-class.
 * 
 * @author cbateman
 * 
 */
public class ElementCustomizationCommand extends AbstractCreationCommand
{
    /**
     * the dom model where the element will be added
     */
    protected final IDOMModel _model;
    /**
     * the element to be customized
     */
    protected final Element _element;
    /**
     * the creation data for the new tag
     */
    protected final CreationData _creationData;

    /**
     * @param model
     * @param element
     * @param creationData
     */
    public ElementCustomizationCommand(final IDOMModel model,
            final Element element, final CreationData creationData)
    {
        _model = model;
        _element = element;
        _creationData = creationData;
    }

    
    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.itemcreation.internal.AbstractCreationCommand#execute()
     */
    @Override
    public void execute()
    {
        applyChildElementCustomization();
        applyAttributeCustomization();
    }

    
    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.itemcreation.internal.AbstractCreationCommand#prepare()
     */
    @Override
    protected boolean prepare()
    {
        // do nothing by default; return true to indicate can execute
        return true;
    }

    /**
     * Override to customize the children of the tag being created
     * 
     * Default implementation uses meta-data to load a template. This is the
     * prefered method for static child values (i.e. those that are not
     * calculated dynamically at runtime).
     */
    protected void applyChildElementCustomization()
    {
        final ITagDropSourceData tagDropSourceData = 
            _creationData.getTagCreationProvider();
        if (tagDropSourceData != null)
        {
            MetadataTagDropSourceData provider =
                TagToolCreationAdapter.createMdTagCreationProvider(tagDropSourceData, _model);
            PaletteElementTemplateHelper.applyTemplate(_model, _element,
                    provider);
        }
    }

    /**
     * Add required attributes and default values, if set, to the created tag
     * element
     * 
     * To customize the attributes that get added, use TagCreationInfo metadata
     * 
     * This method is provided as a utility for clients. It is not used by
     * default
     * 
     * @param element
     * @param creationData
     */
    @SuppressWarnings("deprecation")
    protected final void ensureRequiredAttrs(final Element element,
            final CreationData creationData)
    {
        final CMElementDeclaration ed = CMUtil.getTLDElementDeclaration(creationData
                .getUri(), creationData.getTagName(), creationData.getModel()
                .getDocument().getStructuredDocument());

        if (ed != null)
        {
            for (final Iterator it = ed.getAttributes().iterator(); it.hasNext();)
            {
                final CMAttributeDeclaration attr = (CMAttributeDeclaration) it
                        .next();
                if (attr.getUsage() == CMAttributeDeclaration.REQUIRED
                        && element.getAttribute(attr.getAttrName()) == null)
                {
                    element.setAttribute(attr.getAttrName(), attr
                            .getDefaultValue());
                }
            }
        }
    }

    /**
     * Override to customize the attributes of the tag being created
     * 
     * Default implementation uses meta-data to set the attribut values. This is
     * the prefered method for static attribute values (i.e. those that are not
     * calculated dynamically at runtime).
     * 
     */
    protected void applyAttributeCustomization()
    {
        final ITagDropSourceData info = _creationData.getTagCreationProvider();
        if (info != null)
        {
            final MetadataTagDropSourceData provider =
                TagToolCreationAdapter.createMdTagCreationProvider(info, _model);
            final List<TagCreationAttribute> list = provider.getAttributes();
            if (list != null)
            {
                for (final TagCreationAttribute attr : list)
                {
                    _element.setAttribute(attr.getId(),
                            (attr.getValue() == null ? "" : attr.getValue())); //$NON-NLS-1$
                }
            }
        }
    }

    
    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.itemcreation.internal.AbstractCreationCommand#chain(org.eclipse.emf.common.command.Command)
     */
    @Override
    public final Command chain(final Command command)
    {
        return super.chain(command);
    }

    
    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.itemcreation.internal.AbstractCreationCommand#getResult()
     */
    @Override
    public final Collection<Element> getResult()
    {
        return Collections.singletonList(_element);
    }
}
