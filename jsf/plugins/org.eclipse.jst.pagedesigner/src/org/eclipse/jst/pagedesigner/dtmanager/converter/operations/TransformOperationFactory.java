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

import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.IClassLoaderProvider;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.dtmanager.converter.ITransformOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.AppendChildElementOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.AppendChildTextFromXPathOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.AppendChildTextOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.ConvertAttributeToTextOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.CopyAllAttributesOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.CopyAttributeOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.CopyChildrenOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.CreateAttributeFromXPathOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.CreateAttributeOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.CreateElementOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.IfNotOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.IfOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.IterateOverElementsOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.MakeParentElementCurrentOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.RemoveAttributeOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.RenameAttributeOperation;

/**
 * Factory responsible for producing ITransformOperation instances from a known
 * set.
 * 
 * @author Ian Trimble - Oracle
 */
public class TransformOperationFactory {

	/**
	 * AppendChildElementOperation
	 */
	public static final String OP_AppendChildElementOperation = "AppendChildElementOperation";
	/**
	 * AppendChildTextFromXPathOperation
	 */
	public static final String OP_AppendChildTextFromXPathOperation = "AppendChildTextFromXPathOperation";
	/**
	 * AppendChildTextOperation
	 */
	public static final String OP_AppendChildTextOperation = "AppendChildTextOperation";
	/**
	 * ConvertAttributeToTextOperation
	 */
	public static final String OP_ConvertAttributeToTextOperation = "ConvertAttributeToTextOperation";
	/**
	 * CopyAllAttributesOperation
	 */
	public static final String OP_CopyAllAttributesOperation = "CopyAllAttributesOperation";
	/**
	 * CopyAttributeOperation
	 */
	public static final String OP_CopyAttributeOperation = "CopyAttributeOperation";
	/**
	 * CopyChildrenOperation
	 */
	public static final String OP_CopyChildrenOperation = "CopyChildrenOperation";
	/**
	 * CreateAttributeFromXPathOperation
	 */
	public static final String OP_CreateAttributeFromXPathOperation = "CreateAttributeFromXPathOperation";
	/**
	 * CreateAttributeOperation
	 */
	public static final String OP_CreateAttributeOperation = "CreateAttributeOperation";
	/**
	 * CreateElementOperation
	 */
	public static final String OP_CreateElementOperation = "CreateElementOperation";
	/**
	 * CustomTransformOperation
	 */
	public static final String OP_CustomTransformOperation = "CustomTransformOperation";
	/**
	 * IfNotOperation
	 */
	public static final String OP_IfNotOperation = "IfNotOperation";
	/**
	 * IfOperation
	 */
	public static final String OP_IfOperation = "IfOperation";
	/**
	 * IterateOverElementsOperation
	 */
	public static final String OP_IterateOverElementsOperation = "IterateOverElementsOperation";
	/**
	 * MakeParentElementCurrentOperation
	 */
	public static final String OP_MakeParentElementCurrentOperation = "MakeParentElementCurrentOperation";
	/**
	 * RemoveAttributeOperation
	 */
	public static final String OP_RemoveAttributeOperation = "RemoveAttributeOperation";
	/**
	 * RenameAttributeOperation
	 */
	public static final String OP_RenameAttributeOperation = "RenameAttributeOperation";

	private static TransformOperationFactory instance;

	private Logger log = PDPlugin.getLogger(TransformOperationFactory.class);

	/**
	 * Instaniates an instance.
	 */
	private TransformOperationFactory() {
		//no external instantiation
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
	 * @param opID Operation ID.
	 * @param params Parameter array.
	 * @return ITransformOperation instance (may be null).
	 */
	public ITransformOperation getTransformOperation(String opID, String[] params) {
		return getTransformOperation(opID, params, null);
	}

	/**
	 * Gets an ITransformOperation instance for the specified operation ID and
	 * parameters.
	 * 
	 * @param opID Operation ID.
	 * @param params Parameter array.
	 * @param trait Trait instance used for classloading of custom operations.
	 * @return ITransformOperation instance (may be null).
	 */
	public ITransformOperation getTransformOperation(String opID, String[] params, Trait trait) {
		ITransformOperation operation = null;
		if (opID.equals(OP_AppendChildElementOperation)) {
			if (params.length < 1) {
				log.error("Warning.TransformOperationFactory.TooFewParameters", opID);
			} else if (params.length < 2) {
				String elementName = params[0];
				operation = new AppendChildElementOperation(elementName);
			} else {
				String elementName = params[0];
				boolean makeChildCurrent = Boolean.valueOf(params[1]).booleanValue();
				operation = new AppendChildElementOperation(elementName, makeChildCurrent);
			}
		} else if (opID.equals(OP_AppendChildTextFromXPathOperation)) {
			if (params.length < 1) {
				log.error("Warning.TransformOperationFactory.TooFewParameters", opID);
			} else {
				String xPathExpression = params[0];
				operation = new AppendChildTextFromXPathOperation(xPathExpression);
			}
		} else if (opID.equals(OP_AppendChildTextOperation)) {
			if (params.length < 1) {
				log.error("Warning.TransformOperationFactory.TooFewParameters", opID);
			} else {
				String content = params[0];
				operation = new AppendChildTextOperation(content);
			}
		} else if (opID.equals(OP_ConvertAttributeToTextOperation)) {
			if (params.length < 1) {
				log.error("Warning.TransformOperationFactory.TooFewParameters", opID);
			} else if (params.length < 2) {
				String attrName = params[0];
				operation = new ConvertAttributeToTextOperation(attrName);
			} else {
				String attrName = params[0];
				boolean removeAttr = Boolean.valueOf(params[1]).booleanValue();
				operation = new ConvertAttributeToTextOperation(attrName, removeAttr);
			}
		} else if (opID.equals(OP_CopyAllAttributesOperation)) {
			operation = new CopyAllAttributesOperation();
		} else if (opID.equals(OP_CopyAttributeOperation)) {
			if (params.length < 1) {
				log.error("Warning.TransformOperationFactory.TooFewParameters", opID);
			} else if (params.length < 3) {
				String attrName = params[0];
				operation = new CopyAttributeOperation(attrName);
			} else {
				String attrName = params[0];
				boolean create = Boolean.valueOf(params[1]).booleanValue();
				String newAttrValue = params[2];
				operation = new CopyAttributeOperation(attrName, create, newAttrValue);
			}
		} else if (opID.equals(OP_CopyChildrenOperation)) {
			operation = new CopyChildrenOperation();
		} else if (opID.equals(OP_CreateAttributeFromXPathOperation)) {
			if (params.length < 2) {
				log.error("Warning.TransformOperationFactory.TooFewParameters", opID);
			} else {
				String attrName = params[0];
				String xPathExpression = params[1];
				operation = new CreateAttributeFromXPathOperation(attrName, xPathExpression);
			}
		} else if (opID.equals(OP_CreateAttributeOperation)) {
			if (params.length < 2) {
				log.error("Warning.TransformOperationFactory.TooFewParameters", opID);
			} else {
				String attrName = params[0];
				String attrValue = params[1];
				operation = new CreateAttributeOperation(attrName, attrValue);
			}
		} else if (opID.equals(OP_CreateElementOperation)) {
			if (params.length < 1) {
				log.error("Warning.TransformOperationFactory.TooFewParameters", opID);
			} else {
				String elementName = params[0];
				operation = new CreateElementOperation(elementName);
			}
		} else if (opID.equals(OP_CustomTransformOperation)) {
			if (params.length < 1) {
				log.error("Warning.TransformOperationFactory.TooFewParameters", opID);
			} else {
				String className = params[0];
				if (trait == null) {
					log.error("Warning.TransformOperationFactory.ClassNotFound", className);
				} else {
					try {
						IClassLoaderProvider classLoaderProvider = (IClassLoaderProvider)trait.getSourceModelProvider().getAdapter(IClassLoaderProvider.class);
						if (classLoaderProvider != null) {
							Class opClass = classLoaderProvider.loadClass(className);
							if (opClass != null) {
								Object opObject = opClass.newInstance();
								if (opObject instanceof ITransformOperation) {
									operation = (ITransformOperation)opObject;
								} else {
									log.error("Warning.TransformOperationFactory.NotITransformOperation", className);
								}
							} else {
								log.error("Warning.TransformOperationFactory.ClassNotFound", className);
							}
						}
					} catch(IllegalAccessException iae) {
						log.error("Warning.TransformOperationFactory.IllegalAccess", className, iae);
					} catch(InstantiationException ie) {
						log.error("Warning.TransformOperationFactory.Instantiation", className, ie);
					}
				}
			}
		} else if (opID.equals(OP_IfNotOperation)) {
			if (params.length < 1) {
				log.error("Warning.TransformOperationFactory.TooFewParameters", opID);
			} else {
				String xPathExpression = params[0];
				operation = new IfNotOperation(xPathExpression);
			}
		} else if (opID.equals(OP_IfOperation)) {
			if (params.length < 1) {
				log.error("Warning.TransformOperationFactory.TooFewParameters", opID);
			} else {
				String xPathExpression = params[0];
				operation = new IfOperation(xPathExpression);
			}
		} else if (opID.equals(OP_IterateOverElementsOperation)) {
			if (params.length < 1) {
				log.error("Warning.TransformOperationFactory.TooFewParameters", opID);
			} else {
				String xPathExpression = params[0];
				operation = new IterateOverElementsOperation(xPathExpression);
			}
		} else if (opID.equals(OP_MakeParentElementCurrentOperation)) {
			operation = new MakeParentElementCurrentOperation();
		} else if (opID.equals(OP_RemoveAttributeOperation)) {
			if (params.length < 1) {
				log.error("Warning.TransformOperationFactory.TooFewParameters", opID);
			} else {
				String attrName = params[0];
				operation = new RemoveAttributeOperation(attrName);
			}
		} else if (opID.equals(OP_RenameAttributeOperation)) {
			if (params.length < 2) {
				log.error("Warning.TransformOperationFactory.TooFewParameters", opID);
			} else {
				String oldAttrName = params[0];
				String newAttrName = params[1];
				operation = new RenameAttributeOperation(oldAttrName, newAttrName);
			}
		} else {
			log.error("Warning.TransformOperationFactory.UnknownOperationID", opID);
		}
		return operation;
	}

}
