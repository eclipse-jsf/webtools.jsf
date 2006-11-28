/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Gerry Kessler/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.taglibprocessing.internal.provisional.attributevalues;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.provisional.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.provisional.IWorkspaceContextResolver;
import org.eclipse.jst.jsf.core.internal.provisional.jsfappconfig.JSFAppConfigManager;
import org.eclipse.jst.jsf.facesconfig.edit.provider.FacesConfigEditPlugin;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType;
import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.features.IValidationMessage;
import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.features.PossibleValue;
import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.features.ValidationMessage;
import org.eclipse.osgi.util.NLS;
import org.osgi.framework.Bundle;

/**
 * Meta-data processing type representing an "action" attribute
 * @author Gerry Kessler - Oracle
 */
public class ActionType extends MethodBindingType implements IPossibleValues{
	protected static final ImageDescriptor MISSING_IMAGE = ImageDescriptor.getMissingImageDescriptor();
	
	private static final String IMAGE_NAME = "/icons/full/obj16/NavigationCaseType.gif"; //$NON-NLS-1$
	private ImageDescriptor imageDescriptor;
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.taglibprocessing.internal.provisional.attributevalues.MethodBindingType#isValidValue(java.lang.String)
	 */
	public boolean isValidValue(String value){
		if (value != null && value.length() > 0)	
			return true;
		// what other coercion rules apply???
		
		IValidationMessage msg = new ValidationMessage(Messages.ActionType_invalid_value);
		getValidationMessages().add(msg);
		return false;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.internal.provisional.features.IPossibleValues#getPossibleValues()
	 */
	public List getPossibleValues() {
		// TODO getNavigationRules for this page from JSFAppMgr
		List ret = new ArrayList();
		IWorkspaceContextResolver wr = IStructuredDocumentContextResolverFactory.INSTANCE.getWorkspaceContextResolver(getStructuredDocumentContext());
		if (wr != null){
			IFile jsp = (IFile)wr.getResource();
			List rules = JSFAppConfigManager.getInstance(wr.getProject()).getNavigationRulesForPage(jsp);
			for(Iterator it=rules.iterator();it.hasNext();){
				NavigationRuleType rule = (NavigationRuleType)it.next();
				if (rule != null)
					ret.addAll(createProposals(rule));
			}
		}
		return ret;
	}

	private List createProposals(NavigationRuleType rule) {
		List ret = new ArrayList();
		List cases = rule.getNavigationCase();
		for(Iterator it=cases.iterator();it.hasNext();){
			NavigationCaseType navCase = (NavigationCaseType)it.next();
			PossibleValue pv = createProposal(rule, navCase);
			if (pv != null)
				ret.add(pv);
		}
		return ret;
	}

	private PossibleValue createProposal(NavigationRuleType rule, NavigationCaseType navCase) {
		PossibleValue pv = null;
		String value = null;
		String ruleDisp = null;
		String navDisplay = null;
		String navAction = null;
		String toViewId = null;
		
		if (navCase.getFromOutcome() != null)
			value = navCase.getFromOutcome().getTextContent();
		if (navCase.getToViewId()!= null)
			toViewId = navCase.getToViewId().getTextContent();
		if (rule.getFromViewId() != null)
			ruleDisp = rule.getFromViewId().getTextContent();
		if (navCase.getDisplayName() != null
				&& navCase.getDisplayName().size() > 0) {
			navDisplay = ((DisplayNameType) navCase
					.getDisplayName().get(0)).getTextContent();	
		}
		if (navCase.getFromAction() != null) {
			navAction= navCase.getFromAction().getTextContent();	
		}
		if (navDisplay == null || navDisplay.trim().equals("")) //$NON-NLS-1$
			navDisplay = NLS.bind(Messages.ActionType_navcase_display, new String[]{value, toViewId});
		
		if (value != null){
			pv = new PossibleValue(value, navDisplay);
			pv.setIcon(getNavCaseImageDescriptor());
			pv.setAdditionalInformation("from-outcome: "+value  //$NON-NLS-1$
										+ "<br>to-view-id: " + toViewId //$NON-NLS-1$
										+ "<br>from-view-id: " + ruleDisp //$NON-NLS-1$
										+ "<br>from-action: " + (navAction == null ? "null" : navAction)); //$NON-NLS-1$ //$NON-NLS-2$
		}

		return pv;
	}

	
	private ImageDescriptor getNavCaseImageDescriptor() {
		ImageDescriptor ret = super.getImage();
		if (ret != null && ret != MISSING_IMAGE)
			return ret;

		if (imageDescriptor == null){				
			imageDescriptor = getImageDesc(IMAGE_NAME);
		}
		return imageDescriptor;

	}
	
	private ImageDescriptor getImageDesc(String img) 
    {
		Bundle bundle = FacesConfigEditPlugin.getPlugin().getBundle();
		URL url = FileLocator.find(bundle, new Path(img), null);
		ImageDescriptor desc = ImageDescriptor.createFromURL(url);
		if (desc == MISSING_IMAGE){
			return null;
		}
		return desc;
	}
}
