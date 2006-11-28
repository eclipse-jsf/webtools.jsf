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

package org.eclipse.jst.jsf.validation.internal.el;


/**
 * @author cbateman
 *
 */
public class QuickFixAssistant //extends CorrectionAssistantProvider
{
/*	public IContentAssistant getCorrectionAssistant(ISourceViewer sourceViewer) {
		IContentAssistant ca = null;

		if (sourceViewer != null) {
			ContentAssistant assistant = new ContentAssistant();

			if (sourceViewer != null) {
				IContentAssistProcessor correctionProcessor = new CorrectionProcessorXML(sourceViewer);
				assistant.setContentAssistProcessor(correctionProcessor, IJSPPartitions.JSP_DEFAULT_EL2);
			}
			ca = assistant;
		}

		return ca;

	}

	private class CorrectionProcessorXML extends StructuredCorrectionProcessor {
		/**
		 * quick assist processor
		 */
/*		protected IQuickAssistProcessor fQuickAssistProcessor;
		/**
		 * quick fix processor
		 */
/*		protected IQuickFixProcessor fQuickFixProcessor;

		/**
		 * @param sourceViewer
		 */
/*		public CorrectionProcessorXML(ISourceViewer sourceViewer) {
			super(sourceViewer);
		}

		protected IQuickAssistProcessor getQuickAssistProcessor() {
			if (fQuickAssistProcessor == null)
				fQuickAssistProcessor = new IQuickAssistProcessor()
				{
					public boolean canAssist(StructuredTextViewer viewer, int offset)
					{
						return true;
					}
	
					/**
					 * Collects proposals for assistant at the given offset.
					 */
/*					public ICompletionProposal[] getProposals(StructuredTextViewer viewer, int offset) throws CoreException
					{
						final IStructuredDocumentContext context = IStructuredDocumentContextFactory.INSTANCE.getContext(viewer, offset);
						final ITextRegionContextResolver resolver = IStructuredDocumentContextResolverFactory.INSTANCE.getTextRegionResolver(context);
						
						if (resolver == null)
						{
							return new ICompletionProposal[0];
						}
						
						final String regionType = resolver.getRegionType();
						final List   proposals = new ArrayList();
						
						if (DOMJSPRegionContexts.JSP_VBL_CONTENT.equals(regionType))
						{
							final String elText = resolver.getRegionText();
							final StringReader  reader = new StringReader(elText);
							final JSPELParser  parser = new JSPELParser(reader);
							
							try
							{
								final ASTExpression expr = parser.Expression();
								final IExpressionSemanticValidator validator = new ASTSemanticValidator(expr, context);
								validator.validate();
								
//								for (final Iterator it = validator.getMessages().iterator(); it.hasNext();)
//								{
//									// TODO:MyLocalizedMessage message = (MyLocalizedMessage) it.next();
//									
//									if (message.appliesTo(offset))
//									{
                                       throw new UnsupportedOperationException("TODO:");
//										switch(message.getErrorCode())
//										{
                                        //TODO:
//											case Messages.ERROR_CODE_BEANNAME_NOT_FOUND:
//											{
//												final String beanName = 
//													(String) message.getAttribute("name");
//												proposals.add(new ELContentAssistProposal(beanName, ((IFile)message.getTargetObject()).getProject()));
//											}
//											break;
//										}
//									}
//								}
							}
							catch (ParseException pe)
							{
								// left empty on purpose
							}
						}
						
						return (ICompletionProposal[]) proposals.toArray(new ICompletionProposal[0]);
					}
				};

			return fQuickAssistProcessor;
		}

		protected IQuickFixProcessor getQuickFixProcessor() {
			if (fQuickFixProcessor == null)
			{
				// TODO: should use the approach where possible, but the Annotation doesn't pass
				// enough info
				fQuickFixProcessor = new QuickFixProcessorXML()
				{
					public boolean canFix(Annotation annnotation)
					{
						return true;
					}

					public ICompletionProposal[] getProposals(Annotation annnotation) throws CoreException
					{
						return new ICompletionProposal[0];
					}
				};
			}

			return fQuickFixProcessor;
		}
	}*/
}
