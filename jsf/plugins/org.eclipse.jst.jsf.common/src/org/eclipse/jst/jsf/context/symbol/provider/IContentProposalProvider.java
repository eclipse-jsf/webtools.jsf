/*******************************************************************************
 * Copyright (c) 2006, 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
 * <p><b>Provisional API - subject to change</b></p>
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
