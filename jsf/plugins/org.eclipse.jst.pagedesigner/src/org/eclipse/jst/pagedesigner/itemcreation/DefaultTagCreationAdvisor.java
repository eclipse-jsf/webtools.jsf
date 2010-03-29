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
package org.eclipse.jst.pagedesigner.itemcreation;

import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.itemcreation.command.ContainerCreationCommand;
import org.eclipse.jst.pagedesigner.itemcreation.command.ElementCustomizationCommand;
import org.eclipse.jst.pagedesigner.itemcreation.command.SingletonContainerCreationCommand;
import org.eclipse.jst.pagedesigner.itemcreation.command.TagContainerCreationCommand;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.w3c.dom.Element;

/**
 * The default advisor.
 * 
 * Clients may extend this class.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * @author cbateman
 *
 */
public class DefaultTagCreationAdvisor extends AbstractTagCreationAdvisor 
{
    /**
     * @param creationData
     */
    public DefaultTagCreationAdvisor(CreationData creationData) 
    {
        super(creationData);
    }

    
    @Override
    protected ElementCustomizationCommand getElementCustomizationCommand(
            IDOMModel model, Element tagElement) {
        return new ElementCustomizationCommand(model, tagElement, _creationData);
    }

    /**
     * @param position the initial drop position
     * @return position after creating required containers
     */
    protected ContainerCreationCommand getContainerCreationCommand(final IDOMPosition position) {
    	if (_creationData.isJSFComponent()) {
            return getJSFContainerCommand(position);
        }
        else if (!(_creationData.isJSFComponent()) && _creationData.isHTMLFormRequired()){
            return getHtmlFormCommand(position);
        }        
        return null;
    }

	/**
     * @param position
     * @return the default container creation command for a JSF tag
     */
    protected ContainerCreationCommand getJSFContainerCommand(final IDOMPosition position)
    {
        ContainerCreationCommand command = null; 
        	
        if (_creationData.isJSFViewTagRequired())
            command = new SingletonContainerCreationCommand(position, IJSFConstants.TAG_IDENTIFIER_VIEW, _creationData.getTagId());
        
        if (_creationData.isHTMLFormRequired())
        {
        	final ContainerCreationCommand htmlFormCommand = new TagContainerCreationCommand(position, IJSFConstants.TAG_IDENTIFIER_FORM, _creationData.getTagId());
        	if (command != null)
        		command.chain(htmlFormCommand);
        	else
        		command = htmlFormCommand;
        }
        
        return command;
    }
    
    /**
     * @param position
     * @return the default container creation command for an HTML form tag
     */
    protected ContainerCreationCommand getHtmlFormCommand(final IDOMPosition position)
    {
        return new TagContainerCreationCommand(position, IHTMLConstants.TAG_IDENTIFIER_HTML_FORM, _creationData.getTagId());
    }
}
