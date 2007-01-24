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
package org.eclipse.jst.jsf.contentmodel.annotation.internal.provisional;

import org.eclipse.jst.jsf.contentmodel.annotation.internal.ICMAnnotationAdvisor;

/**
 * Interface used to allow parsing of meta-data files that do not conform to the default schema provided.
 *  
 * @author Gerry Kessler - Oracle
 * 
 * @see ext-pt: org.eclipse.jst.jsf.contentmodel.annotations.annotationFile  
 * @see xml schema defn: <code>http://org.eclipse.jst.jsf.contentmodel.annotations/grammarAnnotationSchema</code>
 * @see <code>org.eclipse.jst.jsf.contentmodel.annotation.internal.ICMAnnotationAdvisor</code>
 * @see <code>org.eclipse.jst.jsf.contentmodel.annotation.internal.provisional.CMAnnotationSourceFileLocator</code>
 * 
 * @deprecated see common.metadata package
 */
public interface ICMAnnotationFileParser {

	/**
	 * Parse the annotation file using the InputStream provided by the locator and   
	 * using the ICMAnnotationAdvisor methods to fill the internal model.
	 * 
	 * The implementor is responsible for closing the inputstream when parse is complete.
	 * 
	 * @param ICMAnnotationAdvisor advisor
	 * @param ICMAnnotationSourceFileLocator locator
	 * @throws Exception
	 */
	public void parse(ICMAnnotationAdvisor advisor, CMAnnotationSourceFileLocator locator) throws Exception;
}
