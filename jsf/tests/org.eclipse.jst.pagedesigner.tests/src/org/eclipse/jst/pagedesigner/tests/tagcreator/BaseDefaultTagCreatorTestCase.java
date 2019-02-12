/*******************************************************************************
 * Copyright (c) 2009, 2019 IBM Corporation and others.
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
