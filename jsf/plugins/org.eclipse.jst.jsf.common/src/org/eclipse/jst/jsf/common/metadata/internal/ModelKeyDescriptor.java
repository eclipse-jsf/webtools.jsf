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

import org.eclipse.core.resources.IProject;

public class ModelKeyDescriptor {
	private IProject project;
	private String domain;
	private String uri;
	private String key;
	public ModelKeyDescriptor(IProject project, String domain, String uri){
		this.project = project;
		this.domain = domain;
		this.uri = uri;
		StringBuffer buf = new StringBuffer(domain);
		buf.append(":");
		buf.append(uri);
		key = buf.toString();
	}
	
	public String getDomain(){
		return domain;
	}
	
	public String getUri(){
		return uri;
	}

	public IProject getProject(){
		return project;
	}
	
	public String toString(){		
		return key;
	}
	
}
