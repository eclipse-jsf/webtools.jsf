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
package org.eclipse.jst.jsf.core.internal.contentassist.el;

import java.util.List;

import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;

/**
 * Encapsulates an EL content assist completion prefix
 * 
 * @author cbateman
 *
 */
public abstract class ContentAssistStrategy
{
	/**
	 * ContentAssistStrategy type for a dot content assist completion
	 */
	public final static int  PREFIX_TYPE_DOT_COMPLETION = 0;
	/**
	 * ContentAssistStrategy type for an id content assist completion
	 */
	public final static int  PREFIX_TYPE_ID_COMPLETION = 1;
    /**
     * Completions for an empty expression
     */
    public final static int  PREFIX_TYPE_EMPTY_EXPRESSION = 2;
	
	private final int type;
	private final String value;
	private final String proposalStart;
	
	/**
	 * @param type
	 * @param value
	 * @param proposalStart 
	 */
	public ContentAssistStrategy(final int type, final String value, final String proposalStart) 
	{
		super();
		this.type = type;
		this.value = value;
		this.proposalStart = proposalStart;
	}
    
	/**
	 * @return the type of this completion prefix as defined by
	 * one of the PREFIX_TYPE constants
	 */
	public final int getType() 
	{
		return type;
	}
    
	/**
	 * @return the token which this prefix is for
	 */
	public final String getValue() 
	{
		return value;
	}
    
    /**
     * @param context
     * @return the list of proposals for this strategy.  Contents of the list
     * must be of type ICompletionProposal.  List should be considered immutable.
     */
    public abstract List<ICompletionProposal> getProposals(IStructuredDocumentContext context);

	/**
	 * @return the part of the proposal which was already typed when user invoked autocomplete
	 */
	public String getProposalStart() {
		return proposalStart;
	}

	/**
	 * @param completionList
	 * @param proposal
	 */
	protected void addProposalsMatchingProposalStart(final List completionList, final ICompletionProposal[] proposal) {
		final String start = getProposalStart();
		for (int j = 0; j < proposal.length; j++) {
            final String proposalString = proposal[j].getDisplayString();
			//TODO Should use "real" proposal replacement instead of displayString:
			if (proposalString.length() >= start.length() &&
                    proposalString.substring(0, start.length()).equalsIgnoreCase(start))
            {
				completionList.add(proposal[j]);
			}
		}
	}
}
