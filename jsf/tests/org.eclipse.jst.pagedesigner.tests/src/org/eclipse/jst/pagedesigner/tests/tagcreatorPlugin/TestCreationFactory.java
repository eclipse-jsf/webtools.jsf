/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.tests.tagcreatorPlugin;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.core.internal.tld.TagIdentifierFactory;
import org.eclipse.jst.pagedesigner.elementedit.AbstractElementEdit;
import org.eclipse.jst.pagedesigner.elementedit.AbstractElementEditFactory;
import org.eclipse.jst.pagedesigner.elementedit.IElementEdit;
import org.eclipse.jst.pagedesigner.itemcreation.AbstractTagCreator;
import org.eclipse.jst.pagedesigner.itemcreation.CreationData;
import org.eclipse.jst.pagedesigner.itemcreation.DefaultTagCreationAdvisor;
import org.eclipse.jst.pagedesigner.itemcreation.ITagCreationAdvisor;
import org.eclipse.jst.pagedesigner.itemcreation.ITagCreator;
import org.eclipse.jst.pagedesigner.itemcreation.command.ElementCustomizationCommand;
import org.eclipse.jst.pagedesigner.itemcreation.customizer.AbstractDropCustomizer;
import org.eclipse.jst.pagedesigner.itemcreation.customizer.IDropCustomizer;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.w3c.dom.Element;

/**
 * Factory for testing purposes
 * 
 * @author cbateman
 *
 */
public class TestCreationFactory extends AbstractElementEditFactory
{
    public final static String TAGCREATOR_URI_1 = 
        "org.eclipse.jst.pagedesigner.tests.tagCreatorFactory1";
    
    public final static TagIdentifier TAG_WITH_REQUIRED_ATTR = 
        TagIdentifierFactory.createJSPTagWrapper
            (TAGCREATOR_URI_1, "tagWithRequiredAttr");

    protected static final TagIdentifier TAG_WITH_UNSET_REQUIRED_ATTR = 
        TagIdentifierFactory.createJSPTagWrapper
            (TAGCREATOR_URI_1, "tagWithRequiredAttr2");

    public final static String REQUIRED_ATTR_1 = "requiredAttr1";
    public final static String REQUIRED_ATTR_2 = "requiredAttr2";

    public TestCreationFactory() 
    {
        super(TAGCREATOR_URI_1);
    }

    @Override
    public IElementEdit createElementEdit(TagIdentifier tag) 
    {
        return new AbstractElementEdit()
        {
            @Override
            public ITagCreator getTagCreator(TagIdentifier tagId) 
            {
                return doCreateTagCreator();
            }

            @Override
            public IDropCustomizer getDropCustomizer(TagIdentifier tagId) {
                return  new MyDropCustomizer();
            }
            
            
        };
    }

    public ITagCreator doCreateTagCreator() {
        return new MyTagCreator();
    }

    private static class MyTagCreator extends AbstractTagCreator
    {
        @Override
        protected ITagCreationAdvisor doSelectCreationAdvisor(
                CreationData creationData) {
            return new MyTagCreationAdvisor(creationData);
        }
    }

    private static class MyTagCreationAdvisor extends DefaultTagCreationAdvisor
    {
        public MyTagCreationAdvisor(CreationData creationData) 
        {
            super(creationData);
        }

        @Override
        protected ElementCustomizationCommand getElementCustomizationCommand(
                IDOMModel model, Element tagElement) 
        {
            return new ElementCustomizationCommand(model, tagElement, _creationData)
            {
                @Override
                protected void applyAttributeCustomization() 
                {
                    super.applyAttributeCustomization();

                    final TagIdentifier tagId = 
                        TagIdentifierFactory.createJSPTagWrapper
                            (_creationData.getUri(), _creationData.getTagName());

                    if (tagId.equals(TAG_WITH_REQUIRED_ATTR))
                    {
                        _element.setAttribute(REQUIRED_ATTR_2
                                , getDefaultAttributeValue(tagId, REQUIRED_ATTR_2));
                    }
                    
                    if (tagId.equals(TAG_WITH_UNSET_REQUIRED_ATTR))
                    {
                        ensureRequiredAttrs(_element, _creationData);
                    }
                }
            };
        }
    }

    public static String getDefaultAttributeValue(TagIdentifier tagId, String attributeName)
    {
        if (TAG_WITH_REQUIRED_ATTR.equals(tagId))
        {
            if (REQUIRED_ATTR_1.equals(attributeName))
            {
                return null;
            }
            else if (REQUIRED_ATTR_2.equals(attributeName))
            {
                return "foo";
            }
        }

        // if case not covered, return null
        return null;
    }
 
    
    // used by MyDropCustomizer so external JUnits can set the expected status
    // and adaptable values
    private static IStatus STATUS;
    private static IAdaptable ADAPTABLE;

    /**
     * @param status
     * @param adaptable
     */
    public static synchronized void setTestParametersForDropCustomer(IStatus status, IAdaptable adaptable)
    {
        STATUS = status;
        ADAPTABLE = adaptable;
    }
    
    private static synchronized IStatus getTestStatus()
    {
        return STATUS;
    }
    
    private static synchronized IAdaptable getTestAdaptable()
    {
        return ADAPTABLE;
    }

    private static class MyDropCustomizer extends AbstractDropCustomizer
    {
        @Override
        public IAdaptable getDropCustomizationData() {
            return getTestAdaptable();
        }

        @Override
        public IStatus runCustomizer() 
        {
            return getTestStatus();
        }
        
    }
}
