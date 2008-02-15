/*******************************************************************************
 * Copyright (c) 2005 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ian Trimble - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.pagedesigner.dtmanager.converter.operations;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.pagedesigner.IJMTConstants;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.dtmanager.converter.ITransformOperation;
import org.osgi.framework.Bundle;

/**
 * Factory responsible for producing {@link org.eclipse.jst.pagedesigner.dtmanager.converter.ITransformOperation} instances from the <br>
 * <code>org.eclipse.jst.pagedesigner.pageDesignerExtension.tagTransformOperation</code> extensions 
 * <br>
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * @author Ian Trimble - Oracle
 */
public final class TransformOperationFactory {
	/**
	 * AppendChildElementOperation
	 */
	public static final String OP_AppendChildElementOperation = PDPlugin.getPluginId()+".AppendChildElementOperation"; //$NON-NLS-1$
	/**
	 * AppendChildTextFromXPathOperation
	 */
	public static final String OP_AppendChildTextFromXPathOperation = PDPlugin.getPluginId()+".AppendChildTextFromXPathOperation"; //$NON-NLS-1$
	/**
	 * AppendChildTextOperation
	 */
	public static final String OP_AppendChildTextOperation = PDPlugin.getPluginId()+".AppendChildTextOperation"; //$NON-NLS-1$
	/**
	 * ConvertAttributeToTextOperation
	 */
	public static final String OP_ConvertAttributeToTextOperation = PDPlugin.getPluginId()+".ConvertAttributeToTextOperation"; //$NON-NLS-1$
	/**
	 * CopyAllAttributesOperation
	 */
	public static final String OP_CopyAllAttributesOperation = PDPlugin.getPluginId()+".CopyAllAttributesOperation"; //$NON-NLS-1$
	/**
	 * CopyAttributeOperation
	 */
	public static final String OP_CopyAttributeOperation = PDPlugin.getPluginId()+".CopyAttributeOperation"; //$NON-NLS-1$
	/**
	 * CopyAttributeWithRenameOperation
	 */
	public static final String OP_CopyAttributeWithRenameOperation = PDPlugin.getPluginId()+".CopyAttributeWithRenameOperation"; //$NON-NLS-1$
	/**
	 * CopyChildrenOperation
	 */
	public static final String OP_CopyChildrenOperation = PDPlugin.getPluginId()+".CopyChildrenOperation"; //$NON-NLS-1$
	/**
	 * CreateAttributeFromXPathOperation
	 */
	public static final String OP_CreateAttributeFromXPathOperation = PDPlugin.getPluginId()+".CreateAttributeFromXPathOperation"; //$NON-NLS-1$
	/**
	 * CreateAttributeOperation
	 */
	public static final String OP_CreateAttributeOperation = PDPlugin.getPluginId()+".CreateAttributeOperation"; //$NON-NLS-1$
	/**
	 * CreateElementOperation
	 */
	public static final String OP_CreateElementOperation = PDPlugin.getPluginId()+".CreateElementOperation"; //$NON-NLS-1$
	/**
	 * CustomTransformOperation
	 */
	public static final String OP_CustomTransformOperation = PDPlugin.getPluginId()+".CustomTransformOperation"; //$NON-NLS-1$
	/**
	 * IfNotOperation
	 */
	public static final String OP_IfNotOperation = PDPlugin.getPluginId()+".IfNotOperation"; //$NON-NLS-1$
	/**
	 * IfOperation
	 */
	public static final String OP_IfOperation = PDPlugin.getPluginId()+".IfOperation"; //$NON-NLS-1$
	/**
	 * IterateOverElementsOperation
	 */
	public static final String OP_IterateOverElementsOperation = PDPlugin.getPluginId()+".IterateOverElementsOperation"; //$NON-NLS-1$
	/**
	 * MakeParentElementCurrentOperation
	 */
	public static final String OP_MakeParentElementCurrentOperation = PDPlugin.getPluginId()+".MakeParentElementCurrentOperation"; //$NON-NLS-1$
	/**
	 * RemoveAttributeOperation
	 */
	public static final String OP_RemoveAttributeOperation = PDPlugin.getPluginId()+".RemoveAttributeOperation"; //$NON-NLS-1$
	/**
	 * RenameAttributeOperation
	 */
	public static final String OP_RenameAttributeOperation = PDPlugin.getPluginId()+".RenameAttributeOperation"; //$NON-NLS-1$

	private static TransformOperationFactory instance;

	private Logger log = PDPlugin.getLogger(TransformOperationFactory.class);
	
	private Map<String, Class> _tagTransformOpsRegistry;

	/**
	 * Instantiates an instance.
	 */
	private TransformOperationFactory() {
		//no external instantiation
		
		//read extensions
		readAllTagTransformOperations();
	}

	
	/**
	 * Gets the singleton instance.
	 * 
	 * @return The singleton instance.
	 */
	public static synchronized TransformOperationFactory getInstance() {
		if (instance == null) {
			instance = new TransformOperationFactory();
		}
		return instance;
	}

	/**
	 * Gets an ITransformOperation instance for the specified operation ID and
	 * parameters.
	 * 
	 * @param opID - the plugin-qualified Operation extension ID.
	 * @param params - array of String parameters
	 * @return ITransformOperation
	 */
	public ITransformOperation getTransformOperation(String opID, String[] params) {
		Class opClass = null;
		try {
			opClass = _tagTransformOpsRegistry.get(opID);
			if (opClass != null) {
				Object opObject = opClass.newInstance();
				if (opObject instanceof AbstractTransformOperation) {
					((AbstractTransformOperation)opObject).setParameters(params);
					((AbstractTransformOperation)opObject).setTransformOperationID(opID);			
					return (AbstractTransformOperation)opObject;
				}
				log.error("Warning.TransformOperationFactory.NotAbstractTransformOperation", opClass.getName()); //$NON-NLS-1$
			} else {
				log.error("Warning.TransformOperationFactory.ExtensionNotFound", opID); //$NON-NLS-1$
			}		
		} catch(IllegalAccessException iae) {
			log.error("Warning.TransformOperationFactory.IllegalAccess", opClass.getName(), iae); //$NON-NLS-1$
		} catch(InstantiationException ie) {
			log.error("Warning.TransformOperationFactory.Instantiation", opClass.getName(), ie); //$NON-NLS-1$
		}
		return null;
	}
	
	/**
	 * Reads the custom tag transform operations from WPE registry
	 */
	private void readAllTagTransformOperations() {
		try {
			_tagTransformOpsRegistry = new HashMap<String, Class>();
			IExtensionPoint point = Platform.getExtensionRegistry().getExtensionPoint(PDPlugin.getPluginId(), IJMTConstants.EXTENSION_POINT_PAGEDESIGNER);
			IExtension[] extensions = point.getExtensions();
			for (int i=0;i < extensions.length;i++){
				IExtension ext = extensions[i];
				for (int j=0;j < ext.getConfigurationElements().length;j++){
					if (ext.getConfigurationElements()[j].getName()
							.equals(IJMTConstants.TAG_TRANSFORM_OPERATION)) {							
						registerTransformOperation(ext.getConfigurationElements()[j]);					
					}				
				}
			}
		} catch (InvalidRegistryObjectException e) {
			log.error("Warning.TransformOperationFactory.RegistryError", PDPlugin.getPluginId()+IJMTConstants.EXTENSION_POINT_PAGEDESIGNER+"."+IJMTConstants.TAG_TRANSFORM_OPERATION, e); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	private void registerTransformOperation(final IConfigurationElement element) {
	
		final Bundle bundle = Platform.getBundle(element.getContributor().getName());        
		final String id = element.getContributor().getName()+"."+element.getAttribute("id"); //$NON-NLS-1$ //$NON-NLS-2$
        if (bundle != null) {
            try {
                final Class transformClass = bundle.loadClass(element.getAttribute("class"));                 //$NON-NLS-1$
                // Not checking instance type here.  Class gets checked as ITransformOperation at 
                //instantiation time...  
                //best to log error then rather than now.
                _tagTransformOpsRegistry.put(id, transformClass);
            }
            catch (Exception e) {
                log.error("Warning.TransformOperationFactory.CannotLoadOpClass",id,  e);  //$NON-NLS-1$
            }
        }		
	}
	
	
}
