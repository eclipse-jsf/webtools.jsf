/*******************************************************************************
 * Copyright (c) 2001, 2009 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.tests.tagcreatorPlugin;

import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.core.internal.tld.TagIdentifierFactory;
import org.eclipse.jst.pagedesigner.elementedit.AbstractElementEdit;
import org.eclipse.jst.pagedesigner.elementedit.AbstractElementEditFactory;
import org.eclipse.jst.pagedesigner.elementedit.IElementEdit;
import org.eclipse.jst.pagedesigner.itemcreation.ITagCreator;
import org.eclipse.jst.pagedesigner.itemcreation.customizer.IDropCustomizer;
import org.eclipse.jst.pagedesigner.itemcreation.internal.UserCustomizedTagCreator;

/**
 * ElementEdit factory for testing tag drop customization
 * 
 * @author Debajit Adhikary
 *
 */
public class UserCustomizedElementEditFactory extends AbstractElementEditFactory
{
    public static final String TAGCREATOR_URI_1 = "http://com.foo/test";

    public static final TagIdentifier TAG1 = TagIdentifierFactory.createJSPTagWrapper(TAGCREATOR_URI_1, "tag1");
    public static final TagIdentifier TAG2 = TagIdentifierFactory.createJSPTagWrapper(TAGCREATOR_URI_1, "tag2");
    public static final TagIdentifier TAG3 = TagIdentifierFactory.createJSPTagWrapper(TAGCREATOR_URI_1, "tag3");
    public static final TagIdentifier TAG4 = TagIdentifierFactory.createJSPTagWrapper(TAGCREATOR_URI_1, "tag4");
    public static final TagIdentifier TAG5 = TagIdentifierFactory.createJSPTagWrapper(TAGCREATOR_URI_1, "tag5");
    public static final TagIdentifier TAG6 = TagIdentifierFactory.createJSPTagWrapper(TAGCREATOR_URI_1, "tag6");
    public static final TagIdentifier TAG7 = TagIdentifierFactory.createJSPTagWrapper(TAGCREATOR_URI_1, "tag7");
    public static final TagIdentifier TAG8 = TagIdentifierFactory.createJSPTagWrapper(TAGCREATOR_URI_1, "tag8");
    public static final TagIdentifier TAG9 = TagIdentifierFactory.createJSPTagWrapper(TAGCREATOR_URI_1, "tag9");
    

    public UserCustomizedElementEditFactory() 
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
                throw new UnsupportedOperationException("Not implemented");
            }
        };
    }

    
    public ITagCreator doCreateTagCreator() 
    {
        return new UserCustomizedTagCreator();
    }
}
