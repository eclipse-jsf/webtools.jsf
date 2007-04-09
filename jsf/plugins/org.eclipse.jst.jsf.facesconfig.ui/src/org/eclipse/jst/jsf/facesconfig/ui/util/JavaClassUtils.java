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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageDeclaration;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jst.jsf.common.ui.IFileFolderConstants;
import org.eclipse.jst.jsf.common.ui.internal.utils.JavaModelUtil;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;

/**
 * This utility class defines typical operations on java class, such as check
 * public constructor, and check type is primitive or not, etc.
 * 
 * @author Jane Cantz, Xiao-guang Zhang
 */
public class JavaClassUtils {
	/**
	 * Determines if a string contains any illegal characters
	 * 
	 * @param text -
	 *            the string to test
	 * @return boolean - true if an illegal character is found, otherwise false
	 */
	public static boolean hasIllegalCharacters(String text) {
		if (text.length() == 0
				|| !Character.isJavaIdentifierStart(text.charAt(0))) {
			return true;
		}
		for (int i = 1; i < text.length(); i++) {
			if (!Character.isJavaIdentifierPart(text.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Test for constructor.
	 * <p>
	 * This test determines if the class has any constructors
	 * </p>
	 * 
	 * @param methods -
	 *            the IMethod array to examine
	 * @return boolean - true, if method has no constructors
	 */
	public static boolean hasNoConstructor(IMethod[] methods) {
		for (int m = 0; m < methods.length; m++) {
			try {
				if (methods[m].isConstructor()) {
					return false;
				}
			} catch (JavaModelException e) {
                // suppress: this is possible; fall through
			}
		}
		return true;
	}

	/**
	 * check whether the method is public or not.
	 * 
	 * @param method
	 * @return true if method is public
	 */
	public static boolean isPublicMethod(IMethod method) {
		int accessFlags = 0;

		try {
			accessFlags = method.getFlags();
		} catch (JavaModelException e) {
			return false;
		}

		boolean isPublic = Flags.isPublic(accessFlags);
		if ((!Flags.isPrivate(accessFlags))
				&& (!Flags.isProtected(accessFlags))
				&& (!Flags.isPublic(accessFlags))) {
			// No specific modifier was set, so assume to be public
			isPublic = true;
		}
		if (!isPublic) {
			return false;
		}
		return true;
	}

	/**
	 * Test for constructor.
	 * <p>
	 * This test has the following conditions:
	 * </p>
	 * <ul>
	 * <li>method takes 0 arguments and the method name is the class name:
	 * <ul>
	 * <li>takes 0 arguments</li>
	 * <li>the method name is the class name</li>
	 * <li>the method returns void</code></li>
	 * </ul>
	 * </li>
	 * </ul>
	 * 
	 * @param methods -
	 *            the IMethod array to examine
	 * @return boolean - true, if method is a constructor
	 */
	public static boolean hasPublicConstructor(IMethod[] methods) {
		for (int m = 0; m < methods.length; m++) {
			try {
				if (methods[m].isConstructor() && isPublicMethod(methods[m])) {
					// Constructor must have the following 0 arguments
					String[] params = methods[m].getParameterTypes();
					if (params.length == 0) {
						// And must return a void
						String rtn = methods[m].getReturnType();
						if (rtn.equals(Signature.SIG_VOID)) {
							return true;
						}
						break;
					}
				}
			} catch (JavaModelException e) {
				// Nothing to do.
			}
		}
		return false;
	}

	/**
	 * Determines if a datatype is primitive type or part of java.lang or
	 * java.util package. If not, it is considered to be a bean reference
	 * 
	 * @param classType -
	 *            the parent class compilation unit
	 * @param signatureName -
	 *            the datatype of the property
	 * @return boolean - true, if the datatype is primitive or part of java.lang
	 *         or java.util package
	 */
	public static boolean isPrimitiveType(IType classType, String signatureName) {
		while (signatureName.startsWith("[")) {
			signatureName = signatureName.substring(1);
		}
		int kind = Signature.getTypeSignatureKind(signatureName);
		if (kind == Signature.BASE_TYPE_SIGNATURE
				|| signatureName.equals(Signature.SIG_VOID)) {
			// These are true primitive types
			return true;
		}

		String qualifiedName = getQualifiedTypeNameInTypeHierarchy(classType,
				signatureName);

		if ((qualifiedName.startsWith("java.lang")) || (qualifiedName.startsWith("java.util"))) //$NON-NLS-1$
		{
			return true;
		}
		return false;
	}

	/**
	 * get the type from the input class name
	 * 
	 * @param project
	 * @param className
	 * @return - can be null.
	 */
	public static IType getType(IProject project, String className) {
		if (project == null) {
			return null;
		}
		IType cunit = null;
		if (className.length() > 0) {

			IJavaProject jProject = JavaCore.create(project);
			try {
				cunit = jProject.findType(className);
			} catch (JavaModelException e) {
                // suppress: fall-through and return null
			}
		}
		return cunit;
	}

	/**
	 * open the type in the editor.
	 * 
	 * @param type
	 * @return true if the type could opened in an editor
	 */
	public static boolean openType(IType type) {
		if (type == null || !type.exists()) {
			return false;
		}

		try {
			IEditorPart editorPart = JavaUI.openInEditor(type
					.getPrimaryElement());
			if (editorPart != null) {
				JavaUI.revealInEditor(editorPart, type.getPrimaryElement());
				return true;
			}
		} catch (PartInitException e) {
			// ignore this error.
		} catch (JavaModelException e) {
			// ignore this error.
		}
		return false;
	}

	/**
	 * get package name from java source file
	 * 
	 * @param javaFile
	 * @return - can be null.
	 */
	public static String getPackageName(IFile javaFile) {
		if (javaFile == null) {
			return null;
		}
		String ext = "." + javaFile.getFileExtension(); //$NON-NLS-1$
		// See if the file is a java file
		if (!ext.equalsIgnoreCase(IFileFolderConstants.EXT_JAVA)) {
			return null;
		}
		String packagename = new String();
		ICompilationUnit cunit = JavaCore.createCompilationUnitFrom(javaFile);
		try {
			IPackageDeclaration[] packages = cunit.getPackageDeclarations();
			if (packages.length == 0) {
				packagename = new String();
			} else {
				packagename = packages[0].getElementName();
			}
		} catch (JavaModelException jme) {
            // suppress: fall-through and return an empty string?? TODO:?
		}
		return packagename;
	}

	/**
	 * copy the array to the list.
	 * 
	 * @param methodList
	 * @param methods
	 */
	private static void copyToMethodList(List methodList, IMethod[] methods) {
		if (methods != null && methods.length > 0) {
			for (int i = 0; i < methods.length; i++) {
				if (!isDuplicateMethod(methodList, methods[i])) {
					methodList.add(methods[i]);
				}
			}
		}
	}

	/**
	 * check whether this method is duplicated or not in the existing method
	 * list.
	 * 
	 * @param methodList
	 * @param method
	 * @return
	 */
	private static boolean isDuplicateMethod(List methodList, IMethod method) {
		if (method == null || !method.exists()) {
			return false;
		}

		String[] paramTypes = method.getParameterTypes();
		String methodName = method.getElementName();

		for (Iterator iter = methodList.iterator(); iter.hasNext();) {
			IMethod existedMethod = (IMethod) iter.next();
			if (isSameMethodSignature(methodName, paramTypes, existedMethod)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Tests if a method equals to the given signature. Parameter types are only
	 * compared by the simple name, no resolving for the fully qualified type
	 * name is done. Constructors are only compared by parameters, not the name.
	 * 
	 * @param name
	 *            Name of the method
	 * @param paramTypes
	 *            The type signatures of the parameters e.g.
	 *            <code>{"QString;","I"}</code>
	 * @param curr 
	 * @return Returns <code>true</code> if the method has the given name and
	 *         parameter types and constructor state.
	 */
	public static boolean isSameMethodSignature(String name,
			String[] paramTypes, IMethod curr) {
		if (name.equals(curr.getElementName())) {
			String[] currParamTypes = curr.getParameterTypes();
			if (paramTypes.length == currParamTypes.length) {
				for (int i = 0; i < paramTypes.length; i++) {
					String t1 = Signature.getSimpleName(Signature
							.toString(paramTypes[i]));
					String t2 = Signature.getSimpleName(Signature
							.toString(currParamTypes[i]));
					if (!t1.equals(t2)) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * get methods for the class Type including its super class
	 * 
	 * @param classType
	 * @return - can be null
	 * @throws JavaModelException
	 */
	public static IMethod[] getMethods(IType classType)
			throws JavaModelException {
		if (classType == null) {
			return null;
		}
		List methodList = new ArrayList();
		IMethod[] methods = classType.getMethods();
		copyToMethodList(methodList, methods);

		ITypeHierarchy typeHierarchy = classType.newSupertypeHierarchy(null);

		if (typeHierarchy != null) {
			IType[] superTypes = typeHierarchy.getAllSuperclasses(classType);

			if (superTypes != null && superTypes.length > 0) {
				for (int i = 0; i < superTypes.length; i++) {
					if (!superTypes[i].getFullyQualifiedName().equals(
							"java.lang.Object")) {
						methods = superTypes[i].getMethods();

						copyToMethodList(methodList, methods);
					}
				}
			}
		}

		if (methodList != null && methodList.size() > 0) {
			IMethod[] validMethods = (IMethod[]) methodList
					.toArray(new IMethod[methodList.size()]);

			Arrays.sort(validMethods, new Comparator() {
				public int compare(Object o1, Object o2) {
					String name1 = ((IMethod) o1).getElementName();
					String name2 = ((IMethod) o2).getElementName();
					return name1.compareTo(name2);
				}
			});
			return validMethods;
		}
		return null;
	}

	/**
	 * resolve and get the qualified name for the incomplete typename
	 * 
	 * @param classType
	 * @param signatureName
	 * @return - at least equal to Signature.toString(signatureName).
	 */
	public static String getQualifiedTypeNameInTypeHierarchy(IType classType,
			String signatureName) {
		int arrayNum = 0;
		while (signatureName.startsWith("[")) {
			arrayNum++;
			signatureName = signatureName.substring(1);
		}

		String qualifiedTypeName = Signature.toString(signatureName);
		int kind = Signature.getTypeSignatureKind(signatureName);
		if (kind == Signature.BASE_TYPE_SIGNATURE
				|| signatureName.equals(Signature.SIG_VOID)) {
			// Add back array identifiers
			while (arrayNum > 0) {
				qualifiedTypeName = qualifiedTypeName + "[]";
				arrayNum--;
			}
			return qualifiedTypeName;
		}

		String typeName = Signature.toString(signatureName);

		String foundName = getQualifiedTypeName(classType, typeName);
		// if found in current type
		if (foundName != null) {
			qualifiedTypeName = foundName;
		} else // else found in the type hierachy.
		{
			ITypeHierarchy typeHierarchy = null;
			try {
				typeHierarchy = classType.newSupertypeHierarchy(null);
			} catch (JavaModelException e) {
				// Nothing to do.
			}
			if (typeHierarchy != null) {
				IType[] superTypes = typeHierarchy.getAllSupertypes(classType);

				if (superTypes != null && superTypes.length > 0) {
					for (int i = 0; i < superTypes.length; i++) {
						if (!superTypes[i].getFullyQualifiedName().equals(
								"java.lang.Object")) {
							foundName = getQualifiedTypeName(superTypes[i],
									typeName);
							if (foundName != null) {
								qualifiedTypeName = foundName;
								break;
							}
						}
					}
				}
			}
		}

		// Add back array identifiers
		while (arrayNum > 0) {
			qualifiedTypeName = qualifiedTypeName + "[]";
			arrayNum--;
		}
		return qualifiedTypeName;
	}

	/**
	 * resolve and get the qualified name for the incomplete typename
	 * 
	 * @param classType
	 * @param typeName
	 * @return can be null.
	 */
	public static String getQualifiedTypeName(IType classType, String typeName) {
		String qualifiedTypeName = null;

		try {
			String[][] resolvedNames = classType.resolveType(typeName);
			if (resolvedNames != null && resolvedNames.length > 0) {
				qualifiedTypeName = JavaModelUtil.concatenateName(
						resolvedNames[0][0], resolvedNames[0][1]);
			}
		} catch (JavaModelException e1) {
			// Nothing to do.
		}

		return qualifiedTypeName;
	}

	/**
	 * check whether subclass is sub class of supperclass
	 * 
	 * @param jProject
	 * @param subClass -
	 *            fully qualified name of sub class
	 * @param superClass -
	 *            fully qualified name of super class
	 * 
	 * @return true if subClass is a sub  of  superClass
	 */
	public static boolean isSubClassOf(IJavaProject jProject, String subClass,
			String superClass) {
		if (jProject == null || subClass == null || superClass == null) {
			return false;
		}

		try {
			IType subClassType = jProject.findType(subClass);

			if (subClassType != null) {
				ITypeHierarchy typeHierarchy = null;
				try {
					typeHierarchy = subClassType.newSupertypeHierarchy(null);
				} catch (JavaModelException e) {
					// Nothing to do.
				}
				IType[] superTypes = typeHierarchy
						.getAllSupertypes(subClassType);

				if (superTypes != null && superTypes.length > 0) {
					for (int i = 0; i < superTypes.length; i++) {
						if (superTypes[i].getFullyQualifiedName().equals(
								superClass)) {
							return true;
						}
					}
				}
			}
		} catch (JavaModelException e) {
			// Nothing to do.
		}
		return false;
	}
}
