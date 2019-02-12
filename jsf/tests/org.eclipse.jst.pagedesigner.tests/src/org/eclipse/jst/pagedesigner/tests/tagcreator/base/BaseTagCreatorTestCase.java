/*******************************************************************************
 * Copyright (c) 2001, 2010 Oracle Corporation and others.
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
package org.eclipse.jst.pagedesigner.tests.tagcreator.base;

import org.eclipse.core.resources.IFile;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.pagedesigner.itemcreation.ITagCreator;
import org.eclipse.jst.pagedesigner.itemcreation.customizer.ICustomizationData;
import org.eclipse.jst.pagedesigner.tests.PageDesignerTestsPlugin;
import org.eclipse.wst.xml.core.internal.document.ElementImpl;
import org.w3c.dom.Element;

public abstract class BaseTagCreatorTestCase extends BaseTestClass 
{
//    private Map<String, TaglibPaletteDrawer> _drawers = new HashMap<String, TaglibPaletteDrawer>();
    protected final String _defaultPrefix;
    protected TagIdentifier tagIdentifier;
    
    public BaseTagCreatorTestCase(final String defaultPrefix, final String compareDataSubDir)
    {
        super(compareDataSubDir);
        this._defaultPrefix = defaultPrefix;
    }
    

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    
    public final TagIdentifier getTagIdentifier() 
    {
		return tagIdentifier;
	}


	public final void setTagIdentifier(TagIdentifier tagIdentifier) 
	{
		this.tagIdentifier = tagIdentifier;
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
	 * @param customizationData TODO
     * @throws Exception
     */
    protected final void doCreateTest(final TagIdentifier tagId, final String inExt, final String outExt, int offset, boolean forceResultTagEmpty, ICustomizationData customizationData) throws Exception 
    {
        final String uri = tagId.getUri();
        final String tagName = tagId.getTagName();

        IFile file = (IFile) _webProjectTestEnv.loadResourceInWebRoot(
                PageDesignerTestsPlugin.getDefault().getBundle(),
                "/testdata/tagcreator/tagCreator."+inExt+".data", "/tagCreator_"+tagName+"."+inExt);
        
        setTagIdentifier(tagId);

        ITagCreator tagCreator = getTagCreator(getTagIdentifier());

        Element element = tagCreator.createTag(getCreationData(uri, tagName,
                _defaultPrefix, file, offset, customizationData));

        // this is a hack that is required because we do a literal comparison
        // between the modified source file and test data file on a character
        // by character basis.  However, the MyFaces and RI (and possibly other)
        // impls cause the tags to be generated differently (MyFaces with no
        // end tag because body-content = empty and RI with an end tag because
        // body-content = JSP), so if the caller sets forceResultTagEmpty, we force
        // the tag to have no end tag.  This should not invalidate the test as long as
        // the caller doesn't set forceResultTagEmpty on a tag that may have children.
        // the tag name and attributes should be cloned.
        if (forceResultTagEmpty &&
                element instanceof ElementImpl)
        {
            forceTagEmpty((ElementImpl) element);
        }

        System.out.println(element.toString());

        assertExpectedResult(file, tagName, outExt);
    
    }

    
    protected abstract ITagCreator getTagCreator(TagIdentifier tagId);
}
