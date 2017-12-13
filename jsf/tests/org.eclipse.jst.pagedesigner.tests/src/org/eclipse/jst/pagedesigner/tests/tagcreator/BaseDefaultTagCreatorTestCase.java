package org.eclipse.jst.pagedesigner.tests.tagcreator;

import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.pagedesigner.itemcreation.ITagCreator;
import org.eclipse.jst.pagedesigner.itemcreation.internal.TagCreationFactory;
import org.eclipse.jst.pagedesigner.tests.tagcreator.base.BaseTagCreatorTestCase;

/**
 * Base test case for tag creation using DefaultTagCreator
 * 
 * @author Debajit Adhikary
 */
public class BaseDefaultTagCreatorTestCase extends BaseTagCreatorTestCase {

	public BaseDefaultTagCreatorTestCase(String defaultPrefix, String compareDataSubDir) 
	{
		super(defaultPrefix, compareDataSubDir);
	}

	@Override
	protected ITagCreator getTagCreator(TagIdentifier tagId) 
	{
		return TagCreationFactory.getInstance().createTagCreator(tagId);
	}

}
