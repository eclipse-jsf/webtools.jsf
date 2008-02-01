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
package org.eclipse.jst.pagedesigner.dtmanager.converter.internal;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.pagedesigner.dtmanager.DTManager;
import org.eclipse.jst.pagedesigner.dtmanager.IDTInfo;
import org.eclipse.jst.pagedesigner.dtmanager.converter.IOutputRenderer;
import org.eclipse.jst.pagedesigner.dtmanager.converter.ITagConverterContext;
import org.eclipse.jst.pagedesigner.dtmanager.converter.ITransformOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.ITransformer;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.TransformOperationFactory;
import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.Operation;
import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.Parameter;
import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagConvertInfo;
import org.w3c.dom.Element;

/**
 * Design-time metadata-driven HTML IOutputRenderer implementation.
 * 
 * @author Ian Trimble - Oracle
 */
public class DTHTMLOutputRenderer implements IOutputRenderer {

	private ITagConverterContext tagConverterContext;

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.IOutputRenderer#render(org.eclipse.jst.pagedesigner.dtmanager.converter.ITagConverterContext)
	 */
	public Element render(ITagConverterContext newTagConverterContext) {
		this.tagConverterContext = newTagConverterContext;
		Element srcElement = newTagConverterContext.getHostElement();
		Element resultElement = null;
		if (srcElement != null) {
			ITransformer transformer = createTransformerFromDTInfo(srcElement);
			if (transformer != null) {
				resultElement = transformer.transform(srcElement);
			}
		}
		return resultElement;
	}

	/**
	 * Configures and returns an ITransformer instance for the specified source
	 * Element.
	 * 
	 * @param element Element for which to configure and return an ITransformer
	 * instance.
	 * @return Configured ITransformer instance for the specified Element.
	 */
	protected ITransformer createTransformerFromDTInfo(Element element) {
		ITransformer transformer = null;
		DTManager dtManager = DTManager.getInstance();
		IDTInfo dtInfo = dtManager.getDTInfo(element);
		if (dtInfo != null) {
			TagConvertInfo tcInfo = dtInfo.getTagConvertInfo();
			if (tcInfo != null) {
				transformer = new DefaultTransformer(tagConverterContext);
				EList operations = tcInfo.getOperations();
				if (!appendOperationsToTransformer(transformer, operations, dtInfo)) {
					transformer = null;
				}
			}
		}
		return transformer;
	}

	private boolean appendOperationsToTransformer(ITransformer transformer, EList operations, IDTInfo dtInfo) {
		Iterator itOperations = operations.iterator();
		while (itOperations.hasNext()) {
			Operation operation = (Operation)itOperations.next();
			ITransformOperation currentTransformOperation = null;
			String opID = operation.getId();
			String[] params = getParamsArray(operation);
			currentTransformOperation =
				TransformOperationFactory.getInstance().getTransformOperation(opID, params);
			if (currentTransformOperation != null) {
				transformer.appendTransformOperation(currentTransformOperation);
				EList childOperations = operation.getOperations();
				if (childOperations != null && childOperations.size() > 0) {
					if (!appendChildOperations(currentTransformOperation, childOperations, dtInfo)) {
						return false;
					}
				}
			} else {
				return false;
			}
		}
		return true;
	}

	private boolean appendChildOperations(ITransformOperation parentOperation, EList operations, IDTInfo dtInfo) {
		Iterator itOperations = operations.iterator();
		while (itOperations.hasNext()) {
			Operation operation = (Operation)itOperations.next();
			ITransformOperation currentTransformOperation = null;
			String opID = operation.getId();
			String[] params = getParamsArray(operation);
			currentTransformOperation =
				TransformOperationFactory.getInstance().getTransformOperation(opID, params);
			if (currentTransformOperation != null) {
				parentOperation.appendChildOperation(currentTransformOperation);
				EList childOperations = operation.getOperations();
				if (childOperations != null && childOperations.size() > 0) {
					if (!appendChildOperations(currentTransformOperation, childOperations, dtInfo)) {
						return false;
					}
				}
				
			} else {
				return false;
			}
		}
		return true;
	}

	private String[] getParamsArray(Operation operation) {
		EList paramsList = operation.getParameters();
		if (paramsList != null) {
			Iterator itParamsList = paramsList.iterator();
			String[] paramsArray = new String[paramsList.size()];
			int index = 0;
			while (itParamsList.hasNext()) {
				Parameter param = (Parameter)itParamsList.next();
				paramsArray[index++] = param.getValue();
			}
			return paramsArray;
		}
		//fall through - no params
		return new String[0];
	}

}
