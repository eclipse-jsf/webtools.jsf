/***************************************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.jsf.facesconfig.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.wst.common.componentcore.internal.util.ComponentUtilities;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

/**
 * @version $Id$
 */
public class FacesConfigRestartServerResourceDeltaVisitor implements IResourceDeltaVisitor {

	/** Set of IProjects. */
	private Set components = new HashSet();

	/** Maps IProject --> Set of Strings. */
	//private Map configMap = new HashMap();

	/**
	 * @see org.eclipse.core.resources.IResourceDeltaVisitor#visit(org.eclipse.core.resources.IResourceDelta)
	 */
	public boolean visit(IResourceDelta delta) throws CoreException {

		if (shouldCheckForRestartProject()) {
			int kind = delta.getKind();
			if ((kind == IResourceDelta.ADDED)
					|| (kind == IResourceDelta.REMOVED)
					|| ((kind == IResourceDelta.CHANGED) && ((delta.getFlags() & (IResourceDelta.CONTENT
							| IResourceDelta.TYPE | IResourceDelta.SYNC | IResourceDelta.REPLACED)) != 0))) {
				primCheckForRestartComponent(delta.getResource());
			}
		}

		// Done.
		return true;
	}

	private boolean shouldCheckForRestartProject() {
		//TODO: XN: we don't have preference page for this?
		//return FacesconfigPlugin.getPlugin().getFacesPreferences().getRestart();
		return true;
	}

	private void primCheckForRestartComponent(IResource resource) {
		if (resource.getType() == IResource.FILE) {
			IFile file = (IFile) resource;
			if (FacesConfigUtil.isFacesConfigFile(file)) {
				IVirtualComponent component = ComponentUtilities.findComponent(file);
				if (component != null) {
					components.add(component);
				}
			}
				
		}
	}

	/**
	 * @return the components
	 */
	public Collection getComponents() {
		return components;
	}
}