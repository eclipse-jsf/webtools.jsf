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
package org.eclipse.jst.jsf.designtime.internal.view.model.jsp;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ConverterTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ValidatorTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.types.TypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IHandlerTagElement.TagHandlerType;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.internal.tld.TagIdentifierFactory;
import org.eclipse.jst.jsf.core.jsfappconfig.JSFAppConfigUtils;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDElementDeclaration;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

/**
 * ** TEMPORARY CLASS UNTIL META-DATA STRATEGY IS AVAILABLE **
 * 
 * @author cbateman
 * 
 */
public class DefaultJSPTagResolver extends JSPTagResolvingStrategy
{
    /**
     * strategy id
     */
    public final static String ID = "org.eclipse.jst.jsf.THISISTEMPORARY";
    /**
     * displayable nameb
     */
    public final static String DISPLAY_NAME = "Meta-data Driven Resolver";

    private final IProject _project;

    /**
     * @param project
     */
    public DefaultJSPTagResolver(final IProject project)
    {
        _project = project;
    }

    @Override
    public ITagElement resolve(final TLDElementDeclaration elementDecl)
    {
        final IProjectFacetVersion version = JSFAppConfigUtils
                .getProjectFacet(_project);
        final String versionAsString = version.getVersionString();
        final JSFVersion jsfVersion = JSFVersion.valueOfString(versionAsString);

        final TagIdentifier tagId = TagIdentifierFactory
                .createTLDTagWrapper(elementDecl);
        DefaultTagTypeInfo defaultTagTypeInfo = new DefaultTagTypeInfo();
        final TypeInfo elementType = defaultTagTypeInfo.getTypeInfo(tagId, jsfVersion);

        if (elementType instanceof ComponentTypeInfo)
        {
            return new TLDComponentTagElement(elementDecl,
                    (ComponentTypeInfo) elementType);
        }
        else if (elementType instanceof ConverterTypeInfo)
        {
            return new TLDConverterTagElement(elementDecl,
                    (ConverterTypeInfo) elementType);
        }
        else if (elementType instanceof ValidatorTypeInfo)
        {
            return new TLDValidatorTagElement(elementDecl,
                    (ValidatorTypeInfo) elementType);
        }
        else if (elementType instanceof TagHandlerType)
        {
            return new TLDTagHandlerElement(elementDecl,
                    (TagHandlerType) elementType);
        }
        else if (defaultTagTypeInfo.isDefaultLib(tagId.getUri()))
        {
            return new TLDTagElement(elementDecl);
        }

        // not found
        return null;
    }


    @Override
    public String getId()
    {
        return ID;
    }

    public String getDisplayName()
    {
        return DISPLAY_NAME;
    }



}
