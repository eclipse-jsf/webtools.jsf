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
