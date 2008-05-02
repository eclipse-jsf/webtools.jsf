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
package org.eclipse.jst.pagedesigner.properties.internal;

import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import org.eclipse.core.resources.IProject;

/**
 * Cache of quickEditTabGroupDescriptors keyed by tagID as QName.  
 */
public class QuickEditTabSectionsManager {
	
	private static QuickEditTabSectionsManagerMgr _mgrInstance = new QuickEditTabSectionsManagerMgr();
	private Map<QName, QuickEditTabSectionsDescriptor> map = new HashMap<QName, QuickEditTabSectionsDescriptor>(5);
	private IProject _project;
	private int clientCount = 0;
	
	/**
	 * Returns instance of QuickEditTabSectionsManager for the given project.   Caller must release the instance when caller is being disposed.
	 * @param project
	 * @return instance of QuickEditTabSectionsManager
	 */
	public static synchronized QuickEditTabSectionsManager acquireInstance(IProject project){
		QuickEditTabSectionsManager instance = _mgrInstance.getSectionsManager(project);
		instance.clientCount++;
		return instance;	
	}
	
	/**
	 * Releases instance of QuickEditTabSectionsManager and removes from the QuickEditSectionGroupsManagerMgr if it is the last reference
	 */
	public synchronized void releaseInstance(){
		int refCount = --clientCount;
		if (refCount == 0){
			_mgrInstance.removeSectionsManager(_project);
		}	
	}

	/**
	 * private constructor
	 */
	private QuickEditTabSectionsManager(){
		//
	}
	
	/**
	 * @return IProject that this section manager applies to
	 */
	public IProject getProject(){
		return _project;
	}
	
	/**
	 * Get QuickEditTabSectionsDescriptor for passed tag
	 * 
	 * @param tagId
	 * @return QuickEditTabSectionsDescriptor
	 */
	public QuickEditTabSectionsDescriptor getQuickEditTabSectionsFor(QName tagId) {
		return map.get(tagId);
	}

	/**
	 * Adds QuickEditTabSectionsDescriptor to managed registry keyed by the tagId
	 * QName
	 * 
	 * @param group
	 */
	public void addQuickEditTabGroupDescriptor(QuickEditTabSectionsDescriptor group) {
		map.put(group.getTagId(), group);
	}

	/**
	 * Removes and disposes a cached {@link QuickEditTabSectionsDescriptor}
	 * 
	 * @param tagId
	 */
	public void removeQuickEditTabGroup(QName tagId) {
		QuickEditTabSectionsDescriptor grp = map.get(tagId);
		if (grp != null) {
			map.remove(tagId);
		}
	}

	/**
	 * Manages the QuickEditTabSectionsManager instances.  Ensures one per project.	 
	 */
	private static class QuickEditTabSectionsManagerMgr {		
		private Map <IProject,QuickEditTabSectionsManager>_map = new HashMap<IProject,QuickEditTabSectionsManager>();
		/**
		 * @param project - may be null
		 * @return QuickEditTabManager for project
		 */
		public QuickEditTabSectionsManager getSectionsManager(IProject project){
			if (_map.containsKey(project))
				return _map.get(project);

			QuickEditTabSectionsManager instance = new QuickEditTabSectionsManager();
			instance._project = project;
			_map.put(project, instance);
			return instance;
		}
		
		/**
		 * Removes QuickEditTabManager from mgr for given project
		 * @param project
		 */
		public void removeSectionsManager(IProject project){
			if (_map.containsKey(project))
				_map.remove(project);
		}
		
	}
}
