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
/**
 * 
 */
package org.eclipse.jst.pagedesigner.itemcreation.internal;

import org.eclipse.jst.pagedesigner.itemcreation.CreationData;
import org.eclipse.jst.pagedesigner.itemcreation.ITagCreationAdvisor;
import org.eclipse.jst.pagedesigner.itemcreation.UserCustomizedTagCreationAdvisor;

/**
 * Tag creation advisor that returns a new ElementCustomizationCommand
 * subclass that will perform customization of attributes, child tags and parent tags 
 * 
 * @author prusev
 *
 */
public class UserCustomizedTagCreator extends DefaultTagCreator {

	/**
	 * 
	 */
	public UserCustomizedTagCreator()
	{
		// empty
	}
	
    @Override
    protected ITagCreationAdvisor doSelectCreationAdvisor(CreationData creationData) {
        // Return a new tag creation advisor
        return new UserCustomizedTagCreationAdvisor(creationData);
    }
	
}
