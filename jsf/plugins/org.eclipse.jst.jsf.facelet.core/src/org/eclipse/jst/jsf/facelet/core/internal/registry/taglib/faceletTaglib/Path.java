/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/**
 * <copyright>
 * </copyright>
 *
 * $Id: Path.java,v 1.1 2010/03/18 06:24:37 cbateman Exp $
 */
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Path</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *     The elements that use this type designate either a relative
 *     path or an absolute path starting with a "/".
 * 
 *     In elements that specify a pathname to a file within the
 *     same Deployment File, relative filenames (i.e., those not
 *     starting with "/") are considered relative to the root of
 *     the Deployment File's namespace.  Absolute filenames (i.e.,
 *     those starting with "/") also specify names in the root of
 *     the Deployment File's namespace.  In general, relative names
 *     are preferred.  The exception is .war files where absolute
 *     names are preferred for consistency with the Servlet API.
 * 
 *       
 * <!-- end-model-doc -->
 *
 *
 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getPath()
 * @model extendedMetaData="name='pathType' kind='simple'"
 * @generated
 */
public interface Path extends IdentifiableStringValue
{
 // Path
} 
