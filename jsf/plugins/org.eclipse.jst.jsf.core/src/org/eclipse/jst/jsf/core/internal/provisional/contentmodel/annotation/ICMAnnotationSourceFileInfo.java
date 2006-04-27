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

package org.eclipse.jst.jsf.core.internal.provisional.contentmodel.annotation;

/**
 * The interface used to access information about the annotationFile extension
 * 
 * Not intended to be implemented by clients.
 * @author Gerry Kessler - Oracle
 *
 */
public interface ICMAnnotationSourceFileInfo {
	public String getAnnotationFileLocation();
	public String getBundleId();
	public String getParserClassName();
	public String getAnnotationFileLocatorClassname();
}
