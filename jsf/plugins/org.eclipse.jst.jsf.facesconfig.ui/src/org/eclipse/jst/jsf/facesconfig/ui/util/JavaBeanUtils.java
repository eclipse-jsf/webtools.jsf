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

import java.beans.Introspector;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.ui.internal.utils.JavaModelUtil;

/**
 * This utility class is used to access java bean class, e.g., get java bean's
 * property
 * 
 * @author xgzhang
 * @version
 */
public final class JavaBeanUtils {
	/**
	 * fully qualified name of a List
	 */
	private static final String JAVA_UTIL_LIST = "java.util.List"; //$NON-NLS-1$

	/**
	 * fully qualifed name of a Map
	 */
	private static final String JAVA_UTIL_MAP = "java.util.Map"; //$NON-NLS-1$

	/**
	 * 
	 */
	private JavaBeanUtils() {
		super();
	}

	/**
	 * get the getter method according to property name
	 * 
	 * @param type
	 * @param propertyName
	 * @return - can be <b>null</b>, if not found
	 * @throws JavaModelException
	 * @throws JavaModelException
	 */
	private static IMethod getPropertyGetterMethod(IType type,
			String propertyName)  {
		if (type == null || !type.exists() || propertyName == null) {
			return null;
		}
		IMethod getterMethod = null;

		String methodBaseName = null;
		// Uppercase 1st letter
		if (propertyName.length() == 1) {
			methodBaseName = propertyName.substring(0, 1).toUpperCase();
		} else {
			methodBaseName = propertyName.substring(0, 1).toUpperCase()
					+ propertyName.substring(1);
		}

		String getterMethodName = "get" + methodBaseName; //$NON-NLS-1$

		getterMethod = type.getMethod(getterMethodName, null);
		if (getterMethod == null || !getterMethod.exists()
				|| !JavaClassUtils.isPublicMethod(getterMethod)) {
			getterMethodName = "is" + methodBaseName; //$NON-NLS-1$
			getterMethod = type.getMethod(getterMethodName, null);

			if (getterMethod == null || !getterMethod.exists()
					|| !JavaClassUtils.isPublicMethod(getterMethod)) {
				getterMethod = null;
			}
		}
		return getterMethod;
	}

	/**
	 * get the getter method in the type hierarchy according to property name
	 * 
	 * @param type
	 * @param propertyName
	 * @return - can be <b>null</b>, if not found
	 * @throws JavaModelException
	 * @throws JavaModelException
	 */
	private static IMethod getPropertyGetterMethodInTypeHierarchy(IType type,
			String propertyName) throws JavaModelException {
		if (type == null || !type.exists() || propertyName == null) {
			return null;
		}
		IMethod getterMethod = null;

		getterMethod = getPropertyGetterMethod(type, propertyName);

		if (getterMethod == null) {
			ITypeHierarchy typeHierarchy = null;
			typeHierarchy = type.newSupertypeHierarchy(null);

			if (typeHierarchy == null) {
				return null;
			}

			IType[] superTypes = typeHierarchy.getAllSuperclasses(type);

			if (superTypes == null || superTypes.length == 0) {
				return null;
			}
			for (int i = 0; i < superTypes.length; i++) {
				if (!superTypes[i].getFullyQualifiedName().equals(
						"java.lang.Object")) { //$NON-NLS-1$
					getterMethod = getPropertyGetterMethod(superTypes[i],
							propertyName);
					if (getterMethod != null) {
						break;
					}
				}
			}
		}
		return getterMethod;
	}

	/**
	 * get the setter method in the type hierarchy according to property name
	 * 
	 * @param type
	 * @param propertyName
	 * @return - can be <b>null</b>, if not found
	 * @throws JavaModelException
	 */
	private static IMethod getPropertySetterMethodInTypeHierarchy(IType type,
			String propertyName) throws JavaModelException {
		if (type == null || !type.exists() || propertyName == null) {
			return null;
		}
		IMethod setterMethod = null;

		setterMethod = getPropertySetterMethod(type, propertyName);

		if (setterMethod == null) {
			ITypeHierarchy typeHierarchy = null;
			typeHierarchy = type.newSupertypeHierarchy(null);

			if (typeHierarchy == null) {
				return null;
			}

			IType[] superTypes = typeHierarchy.getAllSuperclasses(type);

			if (superTypes == null || superTypes.length == 0) {
				return null;
			}
			for (int i = 0; i < superTypes.length; i++) {
				if (!superTypes[i].getFullyQualifiedName().equals(
						"java.lang.Object")) { //$NON-NLS-1$
					setterMethod = getPropertySetterMethod(superTypes[i],
							propertyName);
					if (setterMethod != null) {
						break;
					}
				}
			}
		}

		return setterMethod;
	}

	/**
	 * get the setter method according to property name
	 * 
	 * @param type
	 * @param propertyName
	 * @return - can be <b>null</b>, if not found
	 * @throws JavaModelException
	 */
	private static IMethod getPropertySetterMethod(IType type,
			String propertyName) throws JavaModelException {
		if (type == null || !type.exists() || propertyName == null) {
			return null;
		}
		IMethod setterMethod = null;

		String methodBaseName = null;
		// Uppercase 1st letter
		if (propertyName.length() == 1) {
			methodBaseName = propertyName.substring(0, 1).toUpperCase();
		} else {
			methodBaseName = propertyName.substring(0, 1).toUpperCase()
					+ propertyName.substring(1);
		}

		String setterMethodName = "set" + methodBaseName; //$NON-NLS-1$

		IMethod[] methods = null;

		methods = type.getMethods();

		if (methods == null || methods.length == 0) {
			return null;
		}

		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getElementName().equals(setterMethodName)) {
				if (methods[i] == null || !methods[i].exists()
						|| !JavaClassUtils.isPublicMethod(methods[i])) {
					continue;
				}

				// Method must return void
				String returnType = methods[i].getReturnType();
				if (!returnType.equals(Signature.SIG_VOID)) {
					continue;
				}

				String params[] = methods[i].getParameterTypes();
				// method must have only one argument
				if (params.length != 1) {
					continue;
				}
				setterMethod = methods[i];
			}
		}

		return setterMethod;
	}

	/**
	 * Check whether the propertyName is bean's property or not.
	 * 
	 * @param baseType
	 * @param propertyName
	 * 
	 * @return - True means the property name is valid bean's property,
	 *         otherwise, not.
	 * 
	 */
	public static boolean isBeanProperty(IType baseType, String propertyName)
    {
		if (baseType == null || !baseType.exists() || propertyName == null) {
			return false;
		}

		return (getBeanPropertyType(baseType, propertyName) != null);
	}

	/**
	 * get the bean's property type
	 * 
	 * @param baseType
	 * @param propertyName
	 * @return - can be <b>null</b>, if not found
	 * 
	 */
	public static IType getBeanPropertyType(IType baseType, String propertyName) {
		if (baseType == null || !baseType.exists() || propertyName == null) {
			return null;
		}

		String typeSignature = null;
		IMethod getterMethod = null;
		IMethod setterMethod = null;

		IType declaredType = baseType;
		try {
			getterMethod = getPropertyGetterMethodInTypeHierarchy(baseType,
					propertyName);
			setterMethod = getPropertySetterMethodInTypeHierarchy(baseType,
					propertyName);
		} catch (JavaModelException e1) {
			// Need not any error handling.
		}

		if (getterMethod != null && setterMethod == null) {
			declaredType = getterMethod.getDeclaringType();
			try {
				typeSignature = getterMethod.getReturnType();
			} catch (JavaModelException e2) {
				// Need not any error handling.
			}
		} else if (setterMethod != null && getterMethod == null) {
			declaredType = setterMethod.getDeclaringType();
			typeSignature = setterMethod.getParameterTypes()[0];
		} else if (setterMethod != null && getterMethod != null) {
			declaredType = getterMethod.getDeclaringType();
			try {
				// FIXME: should check the type hierachy
				if (getterMethod.getReturnType().equals(
						setterMethod.getParameterTypes()[0])) {
					typeSignature = getterMethod.getReturnType();
				}
			} catch (JavaModelException e2) {
				// Need not any error handling.
			}
		}

		if (typeSignature == null) {
			return null;
		}

		IType type = null;

		try {
			String typeName = JavaModelUtil.getResolvedTypeName(typeSignature,
					declaredType);
			if (typeName != null) {
				type = baseType.getJavaProject().findType(typeName);
			}
		} catch (JavaModelException e) {
			// Need not any error handling.
		}
		return type;
	}

	/**
	 * get the bean's property's getter and setter methods.
	 * 
	 * @param baseType
	 * @param propertyName
	 * @return - IMethod[], the first is getter and the second is setter method,
	 *         however, both of them can be null.
	 */
	public static IMethod[] getBeanPropertyMethods(IType baseType,
			String propertyName) {
		if (baseType == null || !baseType.exists() || propertyName == null) {
			return null;
		}

		IMethod[] methods = new IMethod[2];

		IMethod getterMethod = null;
		IMethod setterMethod = null;
		try {
			getterMethod = getPropertyGetterMethodInTypeHierarchy(baseType,
					propertyName);

			setterMethod = getPropertySetterMethodInTypeHierarchy(baseType,
					propertyName);
		} catch (JavaModelException e) {
			// Need not any error handling.
		}

		if (getterMethod != null && setterMethod == null) {
			methods[0] = getterMethod;
		} else if (setterMethod != null && getterMethod == null) {
			methods[1] = setterMethod;
		} else if (setterMethod != null && getterMethod != null) {
			try {
				// FIXME: should check the type hierachy
				if (getterMethod.getReturnType().equals(
						setterMethod.getParameterTypes()[0])) {
					methods[0] = getterMethod;
					methods[1] = setterMethod;
				}
			} catch (JavaModelException e1) {
				// Need not any error handling.
			}
		}

		return methods;
	}

	/**
	 * check whether the type implements <code>java.util.List</code>
	 * 
	 * @param type
	 * @return - True if the type is the sub class of
	 *         <code>java.util.List</code>, otherwise, not.
	 */
	public static boolean isListType(IType type) {
		if (type == null) {
			return false;
		}
		if (type.getFullyQualifiedName().equalsIgnoreCase(JAVA_UTIL_LIST)) {
			return true;
		}

		return JavaClassUtils.isSubClassOf(type.getJavaProject(), type
				.getFullyQualifiedName(), JAVA_UTIL_LIST);
	}

	/**
	 * check whether the type implements <code>java.util.Map</code>
	 * 
	 * @param type
	 * @return - True if the type is the sub class of <code>java.uitl.Map</code>,
	 *         otherwise, not.
	 */
	public static boolean isMapType(IType type) {
		if (type == null) {
			return false;
		}
		if (type.getFullyQualifiedName().equalsIgnoreCase(JAVA_UTIL_MAP)) {
			return true;
		}

		return JavaClassUtils.isSubClassOf(type.getJavaProject(), type
				.getFullyQualifiedName(), JAVA_UTIL_MAP);
	}

	/**
	 * Test for method inclusion in bindings list.
	 * <p>
	 * This test has the following conditions:
	 * </p>
	 * <ul>
	 * <li>method starts with <code>get</code></li>
	 * <li>method has no arguments</li>
	 * <li>method does not return void</li>
	 * </ul>
	 * 
	 * @param method -
	 *            the IMethod to examine
	 * @return boolean - true, if method satisfies the condition test
	 */
	public static boolean isGetterMethod(IMethod method) {
		try {
			if (!JavaClassUtils.isPublicMethod(method)) {
				return false;
			}
			String params[] = method.getParameterTypes();
			// Has no arguments
			if (params.length > 0) {
				return false;
			}

			// Starts with "get"
			if (!(method.getElementName().startsWith("get") || method.getElementName().startsWith("is"))) //$NON-NLS-1$ //$NON-NLS-2$
			{
				return false;
			}
			// Does not return void
			String rtn = method.getReturnType();
			if (!rtn.equals(Signature.SIG_VOID)) {
				return true;
			}
		} catch (JavaModelException e) {
			// Need not any error handling.
		}
		return false;
	}

	/**
	 * Test for method inclusion in bindings list.
	 * <p>
	 * This test has the following conditions:
	 * </p>
	 * <ul>
	 * <li>method starts with <code>set</code></li>
	 * <li>method returns void</li>
	 * </ul>
	 * 
	 * @param method -
	 *            the IMethod to examine
	 * @return boolean - true, if method satisfies the condition test
	 */
	public static boolean isSetterMethod(IMethod method) {
		try {
			if (!JavaClassUtils.isPublicMethod(method)) {
				return false;
			}
			// Starts with "set"
			if (!method.getElementName().startsWith("set")) //$NON-NLS-1$
			{
				return false;
			}

			// the parameter's number should be one.
			if (method.getParameterTypes().length != 1) {
				return false;
			}
			// Returns void
			String rtn = method.getReturnType();
			if (rtn.equals(Signature.SIG_VOID)) {
				return true;
			}
		} catch (JavaModelException e) {
			// Need not any error handling.
		}
		return false;
	}

	/**
	 * set the first character into low case.
	 * 
	 * @param str
	 * @return str with the first char lower cased
	 */
	public static String toLowCaseFirstChar(String str) {
		// change the first alphabet to lowcase.
		if (str != null && str.length() > 0) {
			if (str.length() == 1) {
				str = str.toLowerCase();
			} else {
				str = str.substring(0, 1).toLowerCase() + str.substring(1);
			}
		}
		return str;
	}

	/**
	 * set the first character into low case.
	 * 
	 * @param str
	 * @return str with the first char upper-cased
	 */
	public static String toUpperCaseFirstChar(String str) {
		// change the first alphabet to lowcase.
		if (str != null && str.length() > 0) {
			if (str.length() == 1) {
				str = str.toUpperCase();
			} else {
				str = str.substring(0, 1).toUpperCase() + str.substring(1);
			}
		}
		return str;
	}

	/**
	 * get property name from getter method.
	 * 
	 * @param method
	 * @return - can be <b>null</b>, if the method is not a valid getter method
	 */
	public static String getPropertyNameFromGetterMethod(IMethod method) {
		if (!isGetterMethod(method)) {
			return null;
		}
		String methodName = method.getElementName();
		String propertyName = null;
		// Starts with "get"
		if (methodName.startsWith("get") && methodName.length() > 3) { //$NON-NLS-1$
			propertyName = methodName.substring(3);
		} else if (methodName.startsWith("is") && methodName.length() > 2) // Starts //$NON-NLS-1$
		// with
		// "is"
		{
			propertyName = methodName.substring(2);
		}
		propertyName = Introspector.decapitalize(propertyName);
		return propertyName;
	}

	/**
	 * get property name from setter class.
	 * 
	 * @param method
	 * @return - can be <b>null</b>, if the method is not a valid setter method
	 */
	public static String getPropertyNameFromSetterMethod(IMethod method) {
		if (!isSetterMethod(method)) {
			return null;
		}
		String methodName = method.getElementName();
		String propertyName = null;
		// Starts with "get"
		if (methodName.startsWith("set") && methodName.length() > 3) { //$NON-NLS-1$
			propertyName = methodName.substring(3);
		}
		propertyName = Introspector.decapitalize(propertyName);
		return propertyName;
	}

	/**
	 * get the method with the same parameters
	 * 
	 * @param methods
	 * @param visitedMethods
	 * @param foundMethod
	 * @param foundMethodName
	 * @param foundParamTypes
	 * @return
	 */
	private static IMethod getMethodWithSameParamters(IMethod[] methods,
			Map visitedMethods, IMethod foundMethod, String foundMethodName,
			String[] foundParamTypes) {
		// get all qualified type name for the found method's parameters.
		String[] foundParamQulifiedTypeNames = null;
		if (foundParamTypes != null && foundParamTypes.length > 0) {
			foundParamQulifiedTypeNames = new String[foundParamTypes.length];
			for (int i = 0; i < foundParamTypes.length; i++) {
				foundParamQulifiedTypeNames[i] = JavaClassUtils
						.getQualifiedTypeNameInTypeHierarchy(foundMethod
								.getDeclaringType(), foundParamTypes[i]);
			}
		}
		for (int i = 0; i < methods.length; i++) {
			if (visitedMethods.get(methods[i]) != null) {
				continue;
			}

			if (!methods[i].getElementName().equals(foundMethodName)) {
				continue;
			}
			if (methods[i].getParameterTypes() == null
					&& foundParamTypes == null) {
				return methods[i];
			} else if (methods[i].getParameterTypes() != null
					&& foundParamTypes != null
					&& foundParamTypes.length == methods[i].getParameterTypes().length) {
				boolean bSameParams = true;
				String[] methodParamTypes = methods[i].getParameterTypes();
				for (int j = 0; j < foundParamQulifiedTypeNames.length; j++) {
					String methodParamQualifiedTypeName = JavaClassUtils
							.getQualifiedTypeNameInTypeHierarchy(methods[i]
									.getDeclaringType(), methodParamTypes[j]);
					// if the qualified type name is not same or not subclass or
					// supper class between each other.
					if (!methodParamQualifiedTypeName
							.equals(foundParamQulifiedTypeNames[j])
							&& !JavaClassUtils.isSubClassOf(methods[i]
									.getJavaProject(),
									methodParamQualifiedTypeName,
									foundParamQulifiedTypeNames[j])
							&& !JavaClassUtils.isSubClassOf(methods[i]
									.getJavaProject(),
									foundParamQulifiedTypeNames[j],
									methodParamQualifiedTypeName)) {
						bSameParams = false;
						break;
					}
				}
				if (bSameParams) {
					return methods[i];
				}
			}
		}
		return null;
	}

	/**
	 * Creates an array of bean properties
	 * 
	 * 
	 * @param classType
	 * @return it can be <b>null</b>, if property is not found.
	 */
	public static JavaBeanProperty[] getBeanProperties(IType classType) {
		IMethod[] methods;
		try {
			methods = JavaClassUtils.getMethods(classType);
		} catch (JavaModelException e2) {
			return null;
		}

		return getBeanProperties(classType, methods);
	}

	/**
	 * Creates an array of bean properties
	 * 
	 * @param type
	 * @param methods
	 * 
	 * @return - the array of java bean properties.
	 */
	public static JavaBeanProperty[] getBeanProperties(IType type,
			IMethod[] methods) {
		if (methods == null || methods.length == 0) {
			return null;
		}

		List properties = new ArrayList();
		Map visitedMethods = new HashMap();
		for (int m = 0; m < methods.length; m++) {
			String propertyName = null;
			// if a property's getter method or setter method already visited,
			// just skip it.
			if (visitedMethods.get(methods[m]) != null) {
				continue;
			}

			visitedMethods.put(methods[m], methods[m]);

			// Check getter firstly
			propertyName = JavaBeanUtils
					.getPropertyNameFromGetterMethod(methods[m]);

			if (propertyName != null && propertyName.length() > 0)
			{
				String setterMethodName = "set" //$NON-NLS-1$
						+ JavaBeanUtils.toUpperCaseFirstChar(propertyName);

				String getterReturnType = null;
				try {
					getterReturnType = methods[m].getReturnType();
				} catch (JavaModelException e1) {
					continue;
				}
				IMethod setterMethod = getMethodWithSameParamters(methods,
						visitedMethods, methods[m], setterMethodName,
						new String[] { getterReturnType });
				if (setterMethod != null && setterMethod.exists()) {
					visitedMethods.put(setterMethod, setterMethod);
				}

				properties.add(new JavaBeanProperty(propertyName,
						getterReturnType, methods[m], setterMethod));
				continue;
			}

			// Check setter secondly.
			propertyName = JavaBeanUtils
					.getPropertyNameFromSetterMethod(methods[m]);

			if (propertyName != null && propertyName.length() > 0)
			{
				// first form of getter method, "get..."
				String getterMethodName = "get" //$NON-NLS-1$
						+ JavaBeanUtils.toUpperCaseFirstChar(propertyName);
				IMethod getterMethod = getMethodWithSameParamters(methods,
						visitedMethods, methods[m], getterMethodName, null);

				if (getterMethod != null && getterMethod.exists()) {
					try {
						if (getterMethod.getReturnType().equals(
								methods[m].getParameterTypes()[0])) {
							visitedMethods.put(getterMethod, getterMethod);
						}
					} catch (JavaModelException e) {
						// need not error logging
					}
				} else {
					// another form of getter method, "is...".
					getterMethodName = "is" //$NON-NLS-1$
							+ JavaBeanUtils.toUpperCaseFirstChar(propertyName);
					getterMethod = getMethodWithSameParamters(methods,
							visitedMethods, methods[m], getterMethodName, null);
					try {
						if (getterMethod != null
								&& getterMethod.exists()
								&& getterMethod.getReturnType().equals(
										methods[m].getParameterTypes()[0])) {
							visitedMethods.put(getterMethod, getterMethod);
						}
					} catch (JavaModelException e) {
						// need not error logging
					}
				}

				properties.add(new JavaBeanProperty(propertyName, methods[m]
						.getParameterTypes()[0], getterMethod, methods[m]));
				continue;
			}
		}

		JavaBeanProperty[] propertyArray = (JavaBeanProperty[]) properties
				.toArray(new JavaBeanProperty[properties.size()]);

		Arrays.sort(propertyArray, new Comparator() {
			public int compare(Object o1, Object o2) {
				String name1 = ((JavaBeanProperty) o1).getName();
				String name2 = ((JavaBeanProperty) o2).getName();
				return name1.compareTo(name2);
			}
		});
		return propertyArray;
	}
}
