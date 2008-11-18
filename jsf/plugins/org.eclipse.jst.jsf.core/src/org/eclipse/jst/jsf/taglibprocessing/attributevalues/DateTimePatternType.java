/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation., and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.taglibprocessing.attributevalues;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.jsf.context.resolver.structureddocument.IDOMContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.metadataprocessors.AbstractRootTypeDescriptor;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.features.PossibleValue;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMAttr;
import org.w3c.dom.Node;

/**
 * Meta-data processing type representing a Date, Time and DateTime Pattern types.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 */

public class DateTimePatternType extends AbstractRootTypeDescriptor implements IPossibleValues{
	//should we ever validate the patterns, extend EnumerationType
	final private static String[] DATEPATTERNS = {"M/d/yy", "EEE, M/d/yy", "MM/dd/yyyy", "EEE, MM/dd/yyyy", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        "MMM d, yyyy", "EEE, MMM d, yyyy", "MMMM d, yyyy", "EEEE, MMMM d, yyyy", "MMMM yyyy" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ };
	};
	
	final private static String[] TIMEPATTERNS     = {"hh:mm", "hh:mm z", "HH:mm z", "HH:mm:ss z" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	};
	  
	final private static String[] DATETIMEPATTERNS = {"M/d/yy hh:mm", "EEE, M/d/yy hh:mm", "MM/dd/yyyy HH:mm:ss z", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		"EEE, MM/dd/yyyy HH:mm:ss z", "MMM d, yyyy HH:mm z", "EEE, MMM d, yyyy HH:mm z", "MMMM d, yyyy HH:mm z", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		"EEEE, MMMM d, yyyy HH:mm z" //$NON-NLS-1$
	};

	public List getPossibleValues() {
		List ret = new ArrayList();		
		String typeVal = getTypeValue();
		if ("date".equalsIgnoreCase(typeVal)){ //$NON-NLS-1$
			for (int i=0;i<DATEPATTERNS.length;i++){
				ret.add(new PossibleValue(DATEPATTERNS[i]));
			}
		}
		else if ("time".equalsIgnoreCase(typeVal)){ //$NON-NLS-1$
			for (int i=0;i<TIMEPATTERNS.length;i++){
				ret.add(new PossibleValue(TIMEPATTERNS[i]));
			}
		}
		else {	
			for (int i=0;i<DATETIMEPATTERNS.length;i++){
				ret.add(new PossibleValue(DATETIMEPATTERNS[i]));
			}
		}
		return ret;
	}
	
	private String getTypeValue() {
		if (getStructuredDocumentContext() != null){
			IDOMContextResolver resolver = IStructuredDocumentContextResolverFactory.INSTANCE.getDOMContextResolver(getStructuredDocumentContext());
			if (resolver != null){
				Node aNode = resolver.getNode();
				if (aNode instanceof IDOMAttr) {
					Node tagNode = ((IDOMAttr)aNode).getOwnerElement();
					IDOMAttr typeNode = (IDOMAttr) tagNode.getAttributes().getNamedItem("type"); //$NON-NLS-1$
					if (typeNode != null)
						return typeNode.getValue();
				}				
			}
		}
		return ""; //$NON-NLS-1$
	}


}
