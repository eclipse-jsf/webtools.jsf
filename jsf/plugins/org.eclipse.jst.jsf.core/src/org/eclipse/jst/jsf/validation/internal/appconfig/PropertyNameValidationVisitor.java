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
package org.eclipse.jst.jsf.validation.internal.appconfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.jsf.common.util.JDTBeanIntrospector;
import org.eclipse.jst.jsf.common.util.JDTBeanProperty;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyNameType;

/**
 * Validate the validation property name
 * 
 * @author cbateman
 */
class PropertyNameValidationVisitor extends EObjectValidationVisitor
{
    private final EStructuralFeature   _parentClassNameFeature;
	private final Map<IType, Map<String, JDTBeanProperty>> _propertyCache;
	private final Map<String, IType>		_typeCache;
    
    /**
     * @param feature 
     * @param parentClassNameFeature 
     * @param version
     */
    public PropertyNameValidationVisitor(EStructuralFeature feature,
            EStructuralFeature parentClassNameFeature, String version)
    {
        super(feature, version);
        _parentClassNameFeature = parentClassNameFeature;
        _propertyCache = new HashMap<IType, Map<String, JDTBeanProperty>>();
        _typeCache = new HashMap<String, IType>();
    }

    protected EObjectValidationVisitor[] getChildNodeValidators() 
    {
        return PropertyValidationVisitor.NO_CHILDREN;
    }

    protected void doValidate(EObject object, List messages, IFile file) 
    {
        final String parentClassType = getParentClassType(object);
        
        if (parentClassType != null)
        {
            final boolean isBeanProperty = 
                validateProperty((PropertyNameType)object
                        , file.getProject(), parentClassType);
            final String propertyName = 
                ((PropertyNameType)object).getTextContent();
            
            if (!isBeanProperty)
            {
                PropertyValidationVisitor.addMessageInfo(messages,
                        DiagnosticFactory
                            .create_BEAN_PROPERTY_NOT_FOUND
                                (propertyName, parentClassType)
                        , object, file);
            }
        }
    }

    private String getParentClassType(EObject object)
    {
        String parentClassType = null;
        
        // need to derive the parent's type
        final EObject property = object.eContainer();
        if (property != null)
        {
            EObject owningObject = property.eContainer();
            
            if (owningObject != null)
            {
                final EObject parentClassTypeObject =
                    (EObject) owningObject.eGet(_parentClassNameFeature);

                if (parentClassTypeObject != null)
                {
                    final EStructuralFeature feature =
                        parentClassTypeObject.eClass()
                            .getEStructuralFeature("textContent"); //$NON-NLS-1$
                    
                    if (feature != null)
                    {
                        parentClassType = (String) 
                            parentClassTypeObject.eGet(feature);
                    }
                }
            }
        }
        
        return parentClassType;
    }
    
	private boolean validateProperty(PropertyNameType object, IProject project, String parentClassType)
    {
        boolean isBeanProperty = false;

        final IType type = getType(parentClassType, project);

        if (type != null)
        {
            final String propertyName = object.getTextContent(); 

            Map<String, JDTBeanProperty>  cachedType = _propertyCache.get(type);
            if (cachedType == null)
            {
            	cachedType = getProperties(type, project);
            	_propertyCache.put(type, cachedType);
            }

            final JDTBeanProperty beanProperty = cachedType.get(propertyName);

            if (beanProperty != null)
            {
            	isBeanProperty = true; 
            }
        }
        return isBeanProperty;
    }

	private Map<String, JDTBeanProperty> getProperties(final IType type, final IProject project) 
	{
		final JDTBeanIntrospector introspector = new JDTBeanIntrospector(type);
		return introspector.getProperties();
	}
	
	private IType getType(final String typeName, final IProject project)
	{
		IType type = _typeCache.get(typeName);
		if (type == null)
		{
	        IJavaProject javaProject = JavaCore.create(project);
	        try 
	        {
				type = javaProject.findType(typeName);
				_typeCache.put(typeName, type);
			} 
	        catch (JavaModelException e) 
			{
				JSFCorePlugin
                .log(new Exception(e), 
                     "Problem validating on parent: "+typeName); //$NON-NLS-1$
			}
		}
		return type;
	}
}