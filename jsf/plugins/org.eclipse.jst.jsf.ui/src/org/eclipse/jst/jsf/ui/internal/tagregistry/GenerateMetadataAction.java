/*******************************************************************************
 * Copyright (c) 2008, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.ui.internal.tagregistry;

import java.io.IOException;
import java.util.Collection;

import org.eclipse.jface.action.Action;
import org.eclipse.jst.jsf.common.runtime.internal.model.types.ClassTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IComponentTagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IConverterTagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IValidatorTagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.Namespace;
import org.eclipse.jst.jsf.designtime.internal.view.mapping.ViewMetadataGenerator;

/**
 * @author cbateman
 * 
 */
class GenerateMetadataAction extends Action
{
    private Namespace _ns;

    public GenerateMetadataAction()
    {
        super("Generate metadata"); //$NON-NLS-1$
    }

    public void setNamespace(final Namespace ns)
    {
        _ns = ns;
    }

    @Override
    public void run()
    {
        if (_ns != null)
        {
            // create resource set and resource
            final ViewMetadataGenerator generator = new ViewMetadataGenerator(_ns
                    .getNSUri());
            for (final ITagElement element : (Collection<? extends ITagElement>) _ns
                    .getViewElements())
            {
                final String tagName = element.getName();
                final ClassTypeInfo typeInfo = getTypeInfo(element);
                if (typeInfo != null)
                {
                    generator.addTagToViewObjectMapping(tagName, typeInfo, null, null);
                }
            }

            try
            {
                generator.save(System.out);
            }
            catch (final IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // final IProject project = (IProject) _viewer.getInput();
            // final ITaglibDomainMetaDataModelContext modelContext =
            // TaglibDomainMetaDataQueryHelper.createMetaDataModelContext(project,
            // "http://java.sun.com/jsf/html");
            // final Entity entity = TaglibDomainMetaDataQueryHelper.getEntity(
            // modelContext, "outputText");
            // if (entity != null)
            // {
            // final Trait trait = TaglibDomainMetaDataQueryHelper.getTrait(
            // entity, "viewElementMapping");
            // if (trait != null)
            // {
            // final TagToViewObjectMapping tagConstraint =
            // (TagToViewObjectMapping) trait.getValue();
            // System.err.println(tagConstraint.toString());
            // }
            // }

        }
    }

    private ClassTypeInfo getTypeInfo(final ITagElement element)
    {
        ClassTypeInfo typeInfo = null;
        if (element instanceof IComponentTagElement)
        {
            typeInfo = ((IComponentTagElement) element).getComponent();
        }
        else if (element instanceof IConverterTagElement)
        {
            typeInfo = ((IConverterTagElement) element).getConverter();
        }
        else if (element instanceof IValidatorTagElement)
        {
            typeInfo = ((IValidatorTagElement) element).getValidator();
        }

        return typeInfo;
    }
}
