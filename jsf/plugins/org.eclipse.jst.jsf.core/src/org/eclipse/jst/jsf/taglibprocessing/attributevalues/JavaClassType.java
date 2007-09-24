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
package org.eclipse.jst.jsf.taglibprocessing.attributevalues;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.core.search.SearchMatch;
import org.eclipse.jdt.core.search.SearchParticipant;
import org.eclipse.jdt.core.search.SearchPattern;
import org.eclipse.jdt.core.search.SearchRequestor;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IWorkspaceContextResolver;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidValues;
import org.eclipse.jst.jsf.metadataprocessors.features.PossibleValue;
import org.eclipse.jst.jsf.metadataprocessors.features.ValidationMessage;

/**
 * Provides possible values and validates attribute values that should be fully qualified Java types.
 * A type can be verified against multiple "valid-interfaces" and/or a "valid-superclass" from meta-data.
 * Code checks to ensure the class can be instantiated (i.e. not abstract, anonymous or inner class)
 * Search is scoped to within the current project only. 
 * 
 * (Until https://bugs.eclipse.org/bugs/show_bug.cgi?id=142044 is fixed, only the first found will be used)
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * @author Gerry Kessler - Oracle
 *
 */
public class JavaClassType extends ObjectType implements IPossibleValues, IValidValues{
	/**
	 * Trait name for valid interfaces
	 */
	public static final String POSSIBLE_VALUES_INTERFACES_PROP_NAME = "valid-interfaces"; //$NON-NLS-1$
	/**
	 * Trait name for valid superclass
	 */
	public static final String POSSIBLE_VALUES_SUPERCLASS_PROP_NAME = "valid-superclass"; //$NON-NLS-1$
	
	private List validationMsgs;
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues#getPossibleValues()
	 */
	public List getPossibleValues() {
		List results = getTypes();
		if (results != null && !results.isEmpty()){
			
			Set vals = new HashSet(results.size());
			Set checkedTypes = new HashSet();
			for (Iterator it = results.iterator();it.hasNext();){
				SearchMatch match = (SearchMatch)it.next();
				IType res = (IType)match.getElement();
				addValidSubClasses(res, vals, checkedTypes);					
			}
			return createPossibleValues(vals);
		}						
		return new ArrayList(0);
	}
	
	private List createPossibleValues(Set vals) {
		List list = new ArrayList(vals.size());
		Iterator it = vals.iterator();
		while(it.hasNext()){
			IJavaElement elem = (IJavaElement)it.next();
			list.add(createPossibleValue(elem));
		}
		return list;
	}

	private void addValidSubClasses(IType res, Set vals, Set checkedTypes) {

		try {
			//check to see if we have already checked the hiearchy
			if (checkedTypes.contains(res))
				return;
						
			//should we add itself?
			if (isInnerOrAnonymousClass(res))
				return;
			if (!isAbstractClass(res))
				vals.add(res);  //since it is a set, dupes will not be added
			

			ITypeHierarchy hierarchy = res.newTypeHierarchy(getJavaProject(), null);			
			IType[] subclasses = hierarchy.getSubclasses(res);
			checkedTypes.add(res);
			for (int i=0;i<subclasses.length;i++){
				addValidSubClasses(subclasses[i], vals, checkedTypes);
			}
		} catch (JavaModelException e) {
			//ignore
		}
	}

	private IWorkspaceContextResolver getWorkspaceContextResolver(){
		if (getStructuredDocumentContext() == null)
			return null;
		
		 return IStructuredDocumentContextResolverFactory.INSTANCE.getWorkspaceContextResolver(getStructuredDocumentContext());
	}
	
	private List getTypes(){
		IJavaProject jp = getJavaProject();
		if (jp == null)
			return null;
		
		List elems = new ArrayList();
		elems.addAll(getInterfaces(jp));
		IType sc = getSuperClass(jp);
		if (sc != null)
			elems.add(sc);
			
		if (elems.size() > 0){				
			SearchRequestor requestor = new Searcher();
			SearchEngine engine = new SearchEngine();
			
			IJavaSearchScope scope = SearchEngine.createJavaSearchScope(new IJavaElement[]{jp});//, IJavaSearchScope.SOURCES | IJavaSearchScope.APPLICATION_LIBRARIES | IJavaSearchScope.);
			SearchPattern combined = SearchPattern.createPattern((IJavaElement)elems.get(0), IJavaSearchConstants.IMPLEMENTORS, 0);

//			 Until this bug is fixed, stub it out...  only the first interface/superclass will be used.
//							https://bugs.eclipse.org/bugs/show_bug.cgi?id=142044
//							for(int i=1;i<elems.size();i++){
//								final SearchPattern other = SearchPattern.createPattern((IJavaElement)elems.get(i), IJavaSearchConstants.IMPLEMENTORS, 0);
//								combined = SearchPattern.createAndPattern(combined, other);
//							}
			
			try {
				engine.search(combined, new SearchParticipant[] {SearchEngine.getDefaultSearchParticipant()}, scope, requestor, null);
				
			} catch (CoreException e) {
				//ignore
			}

			return ((Searcher)requestor).getResults();	
		}

		return Collections.EMPTY_LIST;
	}
	
	private IJavaProject getJavaProject() {
		IWorkspaceContextResolver resolver = getWorkspaceContextResolver();
		if (resolver != null){
			IProject proj = resolver.getProject();
			if (proj != null)
				return JavaCore.create(proj);
		}
		return null;
	}

	private List getInterfaces(IJavaProject jp) {
		List ret = new ArrayList();
		List propVals = getInterfaceNames();		
		
		for (Iterator it = propVals.iterator();it.hasNext();){
			String propVal = (String)it.next();
			IType interfase = null;
			try {
				interfase = findType(jp, propVal);
				if (interfase != null){
					ret.add(interfase);
				}
			} catch (JavaModelException e) {
                // suppress and fall-through to return empty list
			}

		}
		return ret;
	}
	
	private IType getSuperClass(IJavaProject jp){
		IType superclass = null;
		try {
			String sc = getSuperClassName();
			if (sc != null && !sc.trim().equals("")){ //$NON-NLS-1$
				superclass = findType(jp, sc );
				if (superclass != null){
					return superclass;
				}
			}
		} catch (JavaModelException e) {
			//ignore
		}
		return null;
	}
	
	private PossibleValue createPossibleValue(IJavaElement val) {
		return new PossibleValue(((IType)val).getFullyQualifiedName());		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.features.IValidValues#getValidationMessages()
	 */
	public List getValidationMessages() {
		if (validationMsgs == null){
			validationMsgs = new ArrayList();			
		}
		return validationMsgs;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.features.IValidValues#isValidValue(java.lang.String)
	 */
	public boolean isValidValue(String value) {
		if (value == null || value.trim().equals("")){ //$NON-NLS-1$
			getValidationMessages().add(new ValidationMessage(Messages.JavaClassType_invalid_type));
			return false;
		}
		IJavaProject jp = getJavaProject();
		if (jp == null)
			return false;
		
		//first verify that the value specified is a resolvable type
		IType type = getTypeForValue(jp, value);
		if (type != null){			
			//ensure that it is not abstract or anonymous
			if (!isInnerOrAnonymousClass(type) && !isAbstractClass(type)){
				//now verify that it meets the criteria
				ITypeHierarchy hierarchy;
				try {
					hierarchy = type.newTypeHierarchy(jp, null);
				} catch (JavaModelException e) {
					return false;
				}
						
				//check that all interfaces
				List<String> interfaceNames = getInterfaceNames();
				for (Iterator<String> it=interfaceNames.iterator();it.hasNext();){
					//check that all interfaces are satisfied by type
					IType interfase = getTypeForValue(jp, it.next());
					if (interfase == null){
						addNewValidationMessage(Messages.JavaClassType_not_found);
						return false;
					}
					else if (! containsType(hierarchy.getAllSupertypes(type), interfase)){
						addNewValidationMessage(Messages.JavaClassType_not_found);
						return false;
					}
				}
				//interfaces have been satisfied now check the superclass if specified				
				IType superClass = getSuperClass(jp); 
				if (superClass != null && superClass.equals(type))
					return true;
				else if (superClass != null && !containsType(hierarchy.getAllSuperclasses(type), superClass )){
					addNewValidationMessage(Messages.JavaClassType_not_found);
					return false;
				}
				return true;
//				List results = getTypes();
//				if (!results.isEmpty()){					
//					for (Iterator it = results.iterator();it.hasNext();){
//						SearchMatch match = (SearchMatch)it.next();
//						IType res = (IType)match.getElement();
//						if (!isInnerOrAnonymousClass(res) ){
//							//if this is the class, then optimize to reduce expense of creating hierarchy
//							if (!isAbstractClass(type) && (res.getFullyQualifiedName().equals(value)) )
//								return true;
//							//check to see if value is a subtype in the hierarchy
//							try {
//								ITypeHierarchy hierarchy = res.newTypeHierarchy(jp, null);
//								if (containsType(hierarchy.getAllSubtypes(res), type)) 									
//									return true;
//								
//							} catch (JavaModelException e) {
//								//ignore
//							}
//						}
//					}
//				}
			}
		}
		addNewValidationMessage(Messages.JavaClassType_not_found);
		return false;
	}
	
	private boolean containsType(IType[] types, IType type) {
		for (int i=0;i < types.length;i++) {
			if (type.equals(types[i]))
				return true;
		}
		return false;
	}

	private IType getTypeForValue(IJavaProject jp, String value) {
		try {
			return findType(jp, value);
		} catch (JavaModelException e) {
            // suppress and fall through to return null
		}
		return null;
	}

	/**
	 * @return String value of {@link #POSSIBLE_VALUES_SUPERCLASS_PROP_NAME}
	 */
	protected String getSuperClassName(){
		return getTraitValueAsString(POSSIBLE_VALUES_SUPERCLASS_PROP_NAME);
//		return CMAnnotationHelper.getCMAttributePropertyValue(getMetaDataContext().getBundleId(), getMetaDataContext().getUri(),
//				getMetaDataContext().getElementName(), getMetaDataContext().getAttributeName(),
//				POSSIBLE_VALUES_SUPERCLASS_PROP_NAME);

	}
	
	/**
	 * @return List of values from {@link #POSSIBLE_VALUES_INTERFACES_PROP_NAME}
	 */
	protected List getInterfaceNames(){
		return getTraitValueAsListOfStrings(POSSIBLE_VALUES_INTERFACES_PROP_NAME);
		
//		return CMAnnotationHelper.getCMAttributePropertyValues(getMetaDataContext().getBundleId(), getMetaDataContext().getUri(),
//				getMetaDataContext().getElementName(), getMetaDataContext().getAttributeName(),
//				POSSIBLE_VALUES_INTERFACES_PROP_NAME);

	}

	/**
	 * Create a {@link ValidationMessage} from metadata or use default message
	 * and add it to the collection of validation messages
	 * @param defaultMsg
	 */
	protected void addNewValidationMessage(String defaultMsg) {
		//TODO: need to refactor below as this as also in Enumeration
		String msg = getCMValidationMessage();
		if (msg == null || msg.equals("")) //$NON-NLS-1$
			msg = defaultMsg;
		
		String code = getValidationCode();
		int severity = getValidationSeverity();
		ValidationMessage val = new ValidationMessage(msg, code, severity);
		getValidationMessages().add(val);
	}
	
	
	/**
	 * @return validation message from meta-data using {@link IValidValues}.VALID_VALUES_MESSAGE_PROP_NAME trait.   Can be null.
	 */
	protected String getCMValidationMessage() {
		return getTraitValueAsString(IValidValues.VALID_VALUES_MESSAGE_PROP_NAME);			
	}
	
	/**
	 * @return validation severity as int from meta-data using {@link IValidValues}.VALID_VALUES_SEVERITY_PROP_NAME trait.   IStatus.WARNING is default.
	 */
	protected int getValidationSeverity() {
		String val = getTraitValueAsString(IValidValues.VALID_VALUES_SEVERITY_PROP_NAME);		
		if (val == null)
			return IStatus.WARNING;
		
		int severity = Integer.valueOf(val).intValue();
		return severity;
	}

	/**
	 * @return validation code as String from meta-data.   Can be null.
	 */
	protected String getValidationCode() {
		return getTraitValueAsString(IValidValues.VALID_VALUES_CODE_PROP_NAME);		
	}
	
	private boolean isInnerOrAnonymousClass(IType res) {
		try {
			if (res.isClass() && (res.isAnonymous() || 
									(Flags.isPrivate(res.getFlags())) || 
									res.getFullyQualifiedName().indexOf("$") > 0)) //must be better way to discover if it is an inner class //$NON-NLS-1$
				return true;
		} catch (JavaModelException e) {
			//ignore
		}
		return false;
	}


	private boolean isAbstractClass(IType res) {	
		try {
			if (res.isClass() && Flags.isAbstract(res.getFlags()))
				return true;
		} catch (JavaModelException e) {
			//ignore
		}
		return false;
	}
	
	private static class Searcher extends SearchRequestor{
		private List results = new ArrayList();
		public void acceptSearchMatch(SearchMatch match) throws CoreException {
			results.add(match);
		}
		
		/**
		 * @return list of serach results
		 */
		public List getResults(){
			return results;
		}
	}
	
/////////////////  ///////////////////////////////////////////////////////////////////////
//remainder of this class copied from org.eclipse.jdt.internal.corext.util.JavaCoreUtil //
//TODO: find public version of this functionality										//
//////////////////////////////////////////////////////////////////////////////////////////
	private IType findType(IJavaProject jproject, String fullyQualifiedName) throws JavaModelException {
		//workaround for bug 22883
		IType type= jproject.findType(fullyQualifiedName);
		if (type != null)
			return type;

		IPackageFragmentRoot[] roots= jproject.getPackageFragmentRoots();
		for (int i= 0; i < roots.length; i++) {
			IPackageFragmentRoot root= roots[i];
			type= findType(root, fullyQualifiedName);
			if (type != null && type.exists())
				return type;
		}	
		return null;
	}
	
	private IType findType(IPackageFragmentRoot root, String fullyQualifiedName) throws JavaModelException{
		IJavaElement[] children= root.getChildren();
		for (int i= 0; i < children.length; i++) {
			IJavaElement element= children[i];
			if (element.getElementType() == IJavaElement.PACKAGE_FRAGMENT){
				IPackageFragment pack= (IPackageFragment)element;
				if (! fullyQualifiedName.startsWith(pack.getElementName()))
					continue;
				IType type= findType(pack, fullyQualifiedName);
				if (type != null && type.exists())
					return type;
			}
		}		
		return null;
	}
	
	private IType findType(IPackageFragment pack, String fullyQualifiedName) throws JavaModelException{
		ICompilationUnit[] cus= pack.getCompilationUnits();
		for (int i= 0; i < cus.length; i++) {
			ICompilationUnit unit= cus[i];
			IType type= findType(unit, fullyQualifiedName);
			if (type != null && type.exists())
				return type;
		}
		return null;
	}
	
	private IType findType(ICompilationUnit cu, String fullyQualifiedName) throws JavaModelException{
		IType[] types= cu.getAllTypes();
		for (int i= 0; i < types.length; i++) {
			IType type= types[i];
			if (getFullyQualifiedName(type).equals(fullyQualifiedName))
				return type;
		}
		return null;
	}
	
	private String getFullyQualifiedName(IType type) {
		try {
			if (type.isBinary() && !type.isAnonymous()) {
				IType declaringType= type.getDeclaringType();
				if (declaringType != null) {
					return getFullyQualifiedName(declaringType) + '.' + type.getElementName();
				}
			}
		} catch (JavaModelException e) {
			// ignore
		}		
		return type.getFullyQualifiedName('.');
	}
////////////////////////////////////////////////////////////////////////
}
