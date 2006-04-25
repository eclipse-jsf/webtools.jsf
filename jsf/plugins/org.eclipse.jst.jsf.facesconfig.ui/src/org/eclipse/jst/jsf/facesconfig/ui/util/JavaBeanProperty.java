/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.ui.util;

import org.eclipse.jdt.core.IMethod;

/**
 * This class is used to encapsulate the property following the java bean's
 * spec.
 * 
 * @author xgzhang
 * @version
 */
public class JavaBeanProperty {
	private String name;

	private String qualifiedType;

	private String signatureType;

	private IMethod getterMethod;

	private IMethod setterMethod;

	/**
	 * 
	 */
	public JavaBeanProperty(String name, String signatureType,
			IMethod getterMethod, IMethod setterMethod) {
		this.name = name;
		this.signatureType = signatureType;

		this.getterMethod = getterMethod;
		this.setterMethod = setterMethod;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return Returns the setterMethod.
	 */
	public IMethod getSetterMethod() {
		return setterMethod;
	}

	/**
	 * @return Returns the setterMethod.
	 */
	public IMethod getGetterMethod() {
		return getterMethod;
	}

	/**
	 * @return Returns the type.
	 */
	public String getQualifiedType() {
		if (qualifiedType == null) {
			IMethod getterSetterMethod = getterMethod != null ? getterMethod
					: setterMethod;

			qualifiedType = JavaClassUtils.getQualifiedTypeNameInTypeHierarchy(
					getterSetterMethod.getDeclaringType(), signatureType);
		}
		return qualifiedType;
	}

	/**
	 * @return Returns the signatureType.
	 */
	public String getSignatureType() {
		return signatureType;
	}
}
