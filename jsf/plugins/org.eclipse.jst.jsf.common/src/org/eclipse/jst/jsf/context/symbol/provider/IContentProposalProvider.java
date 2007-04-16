/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.context.symbol.provider;

import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.swt.graphics.Image;

/**
 * Adapts an object to appropriate content proposal
 * 
 * @author cbateman
 *
 */
public interface IContentProposalProvider 
{
	/**
	 * @param target 
	 * @param proposalFactory 
	 * @return an content proposal for target or null
	 * if nothing appropriate can be created
	 */
	ICompletionProposal[]  getProposals(Object target, 
                                        IProposalCreationFactory proposalFactory);
    
    /**
     * Implementors of the class can configure certain aspects of the returned
     * proposals
     * 
     * @author cbateman
     *
     */
    public interface IProposalCreationFactory
    {
        /**
         * @param replacementText  The text to be replaced
         * @param displayText  The text to be displayed in the assist window
         * @param additionalText The text to be displayed in the "addition info"
         *                          or null
         * @param displayImage The image to be displayed or null
         * @param sourceObject  The original source object.  Implementors should
         * make no assumptions (i.e. always test instanceof before casting)
         * @return a configured completion proposal for the parameters
         */
        ICompletionProposal createProposal(final String  replacementText,
                final String  displayText,
                final String  additionalText,
                final Image   displayImage,
                final Object  sourceObject);
    }
}
