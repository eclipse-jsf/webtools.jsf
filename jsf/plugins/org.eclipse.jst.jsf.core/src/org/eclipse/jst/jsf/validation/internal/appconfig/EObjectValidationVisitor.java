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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;

/**
 *  
 * @author cbateman
 */
public abstract class EObjectValidationVisitor 
{
    private EStructuralFeature    _structuralFeature;
    private Map                   _childFeatures; // == null; lazy initialized in validate
    private final String          _version;
    
    /**
     * Used to indicate no child nodes
     */
    protected final static EObjectValidationVisitor[] NO_CHILDREN = 
        new EObjectValidationVisitor[0];
    
    /**
     * @param version 
     * 
     */
    public EObjectValidationVisitor(String version)
    {
        _version = version;
    }

    private EObjectValidationVisitor getVisitorForFeature(EStructuralFeature feature)
    {
        if (_childFeatures == null)
        {
            _childFeatures = new HashMap();

            EObjectValidationVisitor[] children = getChildNodeValidators();
            
            for (int i = 0; i < children.length; i++)
            {
                final EObjectValidationVisitor child = children[i];
                _childFeatures.put(child.getStructuralFeature(), child);
            }
        }

        return (EObjectValidationVisitor) _childFeatures.get(feature);
    }
    
    /**
     * @param structuralFeature 
     * @param version 
     */
    public EObjectValidationVisitor(EStructuralFeature structuralFeature, String version)
    {
        this(version);
        _structuralFeature = structuralFeature;
    }

    /**
     * @param eObject 
     * @param messages
     * @param file 
     */
    public final void validate(EObject eObject, List messages, IFile file)
    {
        doValidate(eObject, messages, file);
        
        List features = eObject.eClass().getEAllStructuralFeatures();
        
        for (final Iterator it = features.iterator(); it.hasNext();)
        {
            final EStructuralFeature feature = (EStructuralFeature) it.next();
            final EObjectValidationVisitor visitor = 
                getVisitorForFeature(feature);
            
            if (visitor != null)
            {
                final Object obj = eObject.eGet(feature);
                
                if (obj instanceof List
                        && feature.isMany())
                {
                    for (final Iterator childIt = ((List)obj).iterator(); 
                            childIt.hasNext();)
                    {
                        Object child = childIt.next();
                        if (child instanceof EObject)
                        {
                            visitor.validate((EObject)child, messages, file);
                        }
                    }
                }
                else if (obj instanceof EObject)
                {
                    visitor.validate((EObject)obj , messages, file);
                }
            }
        }
    }

    /**
     * Do the validation for this visitor on this node.  Add any Message's to
     * the messages list
     * 
     * @param eObject 
     * @param messages
     * @param file 
     */
    protected abstract void doValidate(EObject eObject, List messages, IFile file);
    
    /**
     * @return an array of visitors that validate children of the current node
     */
    protected abstract EObjectValidationVisitor[] getChildNodeValidators();

    /**
     * @return the structural feature
     */
    protected final EStructuralFeature getStructuralFeature() 
    {
        return _structuralFeature;
    }
    
    /**
     * @return the version of the runtime
     */
    protected final String getVersion()
    {
        return _version;
    }
    
    
    /**
     * @return {@link JSFVersion} of this file
     */
    protected JSFVersion getJSFVersion() {    	
    	return JSFVersion.valueOfString(_version);
    }
    /**
     * @param messages 
     * @param message
     * @param eObj
     * @param file
     */
    protected static void addMessageInfo(List messages, IMessage message, EObject eObj, IFile file)
    {
        if (message != null)
        {
            message.setOffset(AppConfigValidationUtil.getStartOffset(eObj));
            message.setLength(AppConfigValidationUtil.getLength(eObj));
            message.setTargetObject(file);
            messages.add(message);
        }
    }
}
