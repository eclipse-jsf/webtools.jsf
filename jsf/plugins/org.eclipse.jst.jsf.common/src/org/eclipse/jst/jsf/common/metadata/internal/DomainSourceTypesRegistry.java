/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.common.metadata.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;
import org.eclipse.jst.jsf.common.metadata.internal.DomainSourceModelTypeDescriptor.DomainSourceModelTypeImpl;

/**
 * Registry of Domain Source Types loaded from domainSourceModelTypes ext-pt.
 */
public class DomainSourceTypesRegistry{
	private static final String EXTENSION_POINT_ID = "domainSourceModelTypes"; //$NON-NLS-1$
	private static DomainSourceTypesRegistry INSTANCE;
	private Map/*<String, List/*<DomainSourceModelTypeDescriptor>>*/ domainSourceTypeDescriptors;
	
	private DomainSourceTypesRegistry(){
		init();
	}

	/**
	 * @return singleton instance of DomainSourceTypesRegistry
	 */
	public synchronized static DomainSourceTypesRegistry getInstance() {
		if (INSTANCE == null){
			INSTANCE = new DomainSourceTypesRegistry();
		}
		return INSTANCE;
	}

	/**
	 * @param domain identifier
	 * @return list of <code>IDomainSourceModelType</code> sorted in descending order by ordinal
	 */
	public List/*<IDomainSourceModelType>*/ getDomainSourceTypes(String domain){		
		List/*<DomainSourceModelTypeDescriptor>*/ list = getDomainSourceModelDescriptors(domain);
		List/*<IDomainSourceModelType>*/ types = new ArrayList/*<IDomainSourceModelType>*/();
		for(Iterator/*<DomainSourceModelTypeDescriptor>*/ it=list.iterator();it.hasNext();){
			DomainSourceModelTypeDescriptor d = (DomainSourceModelTypeDescriptor)it.next();
			types.add(d.getInstance());
		}
		//default sort descending by ordinal 
		Collections.sort(types, new Comparator(){
			public int compare(Object o1, Object o2) {
				DomainSourceModelTypeImpl desc1 = (DomainSourceModelTypeImpl)o1;
				DomainSourceModelTypeImpl desc2 = (DomainSourceModelTypeImpl)o2;
				if (desc1.getOrdinal() == desc2.getOrdinal())
						return 0;
				if (desc1.getOrdinal() > desc2.getOrdinal())
					return -1;
				
				return 1;
			}
			
		});
		
		return types;
	}
	
	private List getDomainSourceModelDescriptors(String domain) {
		List ret = (List)getDescriptors().get(domain);
		if (ret != null && ret.size()>0)
			return ret;
		
		return getDefaultSourceModelDescriptor();
	}

	private List getDefaultSourceModelDescriptor() {
		List ret = new ArrayList();
		DomainSourceModelTypeDescriptor desc = new DomainSourceModelTypeDescriptor();
		ret.add(desc);
		return ret;
	}

	private synchronized void init() {
		IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
		IExtensionPoint point = extensionRegistry.getExtensionPoint(JSFCommonPlugin.PLUGIN_ID, EXTENSION_POINT_ID );
		if (point != null) {
			IConfigurationElement[] elements = point.getConfigurationElements();
			for (int i = 0; i < elements.length; i++) {
				IConfigurationElement element = elements[i];
				addDomainSourceTypeDescriptor(element);
			}
		}
	}

	private void addDomainSourceTypeDescriptor(IConfigurationElement element) {
		String domainId = element.getAttribute("domainId"); //$NON-NLS-1$
		String srcHdlrId = element.getAttribute("domainSourceModelTypeId"); //$NON-NLS-1$
		String locator = element.getAttribute("locator"); //$NON-NLS-1$
		String ordinalStr = element.getAttribute("ordinal"); //$NON-NLS-1$
		int ordinal = 1;
		if (ordinalStr!=null && !ordinalStr.equals("")){ //$NON-NLS-1$
			ordinal = Integer.parseInt(ordinalStr);
		}
		DomainSourceModelTypeDescriptor d = new DomainSourceModelTypeDescriptor(domainId, srcHdlrId, locator, element.getContributor().getName(), ordinal);
		List/*<DomainSourceTypeDescriptor>*/ descs = (List)getDescriptors().get(domainId);
		if (descs == null){
			descs = new ArrayList/*<DomainSourceTypeDescriptor>*/();
			getDescriptors().put(domainId, descs);
		}
		descs.add(d);
	}

	private Map/*<String, List/*<DomainSourceTypeDescriptor>>*/ getDescriptors() {
		if (domainSourceTypeDescriptors == null){
			domainSourceTypeDescriptors = new HashMap/*<String, List/*<DomainSourceTypeDescriptor>>*/();
		}
		return domainSourceTypeDescriptors;
	}
	
//	private Map/*<String, List/*<IDomainSourceModelType>>*/ getDomainSourceTypes() {
//		if (domainSourceTypes == null){
//			domainSourceTypes = new HashMap/*<String, List/*<IDomainSourceModelType>>*/();
//		}
//		return domainSourceTypes;
//	}

}