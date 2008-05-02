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
package org.eclipse.jst.pagedesigner.tests.tagcreator.base;

import org.eclipse.core.resources.IFile;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.pagedesigner.tests.PageDesignerTestsPlugin;
import org.eclipse.wst.xml.core.internal.document.ElementImpl;
import org.w3c.dom.Element;

public class BaseItemCreationToolTestCase extends BaseTestClass 
{

    public BaseItemCreationToolTestCase(final String compareDataSubDir) {
        super(compareDataSubDir);
    }

    @Override
    protected void setUp() throws Exception 
    {
        super.setUp();
    }
    
    /**
     * @param tagId
     * @param inExt
     * @param outExt
     * @param offset
     * @param forceResultTagEmpty this is a workaround flag due to the fact that some
     * TLD body definitions differ between RI and MyFaces, causing some tags to be
     * generated as <tag></tag> in RI and <tag/> in MyFaces.  NEVER SET TO TRUE ON A TAG ID
     * whose instances may have child elements.

     * @throws Exception
     */
    protected final void doCreateTest(final TagIdentifier tagId, final String inExt, final String outExt, int offset, boolean forceResultTagEmpty) throws Exception
    {
        final String tagName = tagId.getTagName();

        final IFile file = (IFile) _webProjectTestEnv.loadResourceInWebRoot(
                PageDesignerTestsPlugin.getDefault().getBundle(),
                "/testdata/tagcreator/tagCreator."+inExt+".data", "/tagCreator_"+tagName+"."+inExt);

        MockItemCreationTool tool = createMockItemCreationTool(file, offset, tagId);

        tool.customizeDropAndMaybeExecute(0);
        
        // this is a hack that is required because we do a literal comparison
        // between the modified source file and test data file on a character
        // by character basis.  However, the MyFaces and RI (and possibly other)
        // impls cause the tags to be generated differently (MyFaces with no
        // end tag because body-content = empty and RI with an end tag because
        // body-content = JSP), so if the caller sets forceResultTagEmpty, we force
        // the tag to have no end tag.  This should not invalidate the test as long as
        // the caller doesn't set forceResultTagEmpty on a tag that may have children.
        // the tag name and attributes should be cloned.
        if (forceResultTagEmpty)
        {
            Element element = ((MockCreateItemCommand)tool.getExecutedCommand()).getResult();
            
            if (element instanceof ElementImpl)
            {
                forceTagEmpty((ElementImpl) element);
            }
        }
        assertExpectedResult(file, tagName, outExt);
    }
}
