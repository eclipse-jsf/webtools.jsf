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
package org.eclipse.jst.pagedesigner.dtmanager.converter.internal.provisional;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.jsf.common.metadata.internal.IClassLoaderProvider;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.Trait;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.AppendChildElementOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.AppendChildTextOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.ConvertAttributeToTextOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.CopyAllAttributesOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.CopyAttributeOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.CopyChildrenOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.CreateAttributeOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.CreateElementOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.MakeParentElementCurrentOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.RemoveAttributeOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.RenameAttributeOperation;
import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.provisional.Operation;
import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.provisional.Parameter;
import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.provisional.TagConvertInfo;
import org.eclipse.jst.pagedesigner.dtmanager.internal.provisional.DTManager;
import org.eclipse.jst.pagedesigner.dtmanager.internal.provisional.IDTInfo;
import org.w3c.dom.Element;

/**
 * Design-time metadata-driven HTML IOutputRenderer implementation.
 * 
 * @author Ian Trimble - Oracle
 */
public class DTHTMLOutputRenderer implements IOutputRenderer {

	private ITagConverterContext tagConverterContext;

	private Logger log = PDPlugin.getLogger(DTHTMLOutputRenderer.class);

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.internal.provisional.IOutputRenderer#render(org.eclipse.jst.pagedesigner.dtmanager.converter.internal.provisional.ITagConverterContext)
	 */
	public Element render(ITagConverterContext tagConverterContext) {
		this.tagConverterContext = tagConverterContext;
		Element srcElement = tagConverterContext.getHostElement();
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
				transformer = new DefaultTransformer();
				transformer.setTagConverterContext(tagConverterContext);
				String classname = tcInfo.getClassname();
				if (classname != null && classname.length() > 0) {
					try {
						Trait trait = dtInfo.getTrait();
						IClassLoaderProvider classLoaderProvider = (IClassLoaderProvider)trait.getSourceModelProvider().getAdapter(IClassLoaderProvider.class);
						if (classLoaderProvider != null) {
							Class opClass = classLoaderProvider.loadClass(classname);
							if (opClass != null) {
								Object opObject = opClass.newInstance();
								if (opObject instanceof ITransformOperation) {
									transformer.appendTransformOperation((ITransformOperation)opObject);
								} else {
									log.error("Warning.DTHTMLOutputRenderer.NotITransformOperation", classname);
									return null;
								}
							} else {
								log.error("Warning.DTHTMLOutputRenderer.ClassNotFound", classname);
								return null;
							}
						}
					} catch(IllegalAccessException iae) {
						log.error("Warning.DTHTMLOutputRenderer.IllegalAccess", classname, iae);
						return null;
					} catch(InstantiationException ie) {
						log.error("Warning.DTHTMLOutputRenderer.Instantiation", classname, ie);
						return null;
					}
				} else {
					EList operations = tcInfo.getOperations();
					Iterator itOperations = operations.iterator();
					while (itOperations.hasNext()) {
						Operation operation = (Operation)itOperations.next();
						String opID = operation.getId();
						if (opID != null) {
							if (opID.equals("AppendChildElementOperation")) {
								EList parameters = operation.getParameters();
								if (parameters.size() < 1) {
									log.error("Warning.DTHTMLOutputRenderer.TooFewParameters", opID);
									return null;
								}
								if (parameters.size() < 2) {
									String elementName = ((Parameter)parameters.get(0)).getValue();
									transformer.appendTransformOperation(new AppendChildElementOperation(elementName));
								} else {
									String elementName = ((Parameter)parameters.get(0)).getValue();
									boolean makeChildCurrent = Boolean.valueOf(((Parameter)parameters.get(1)).getValue()).booleanValue();
									transformer.appendTransformOperation(new AppendChildElementOperation(elementName, makeChildCurrent));
								}
							} else if (opID.equals("AppendChildTextOperation")) {
								EList parameters = operation.getParameters();
								if (parameters.size() < 1) {
									log.error("Warning.DTHTMLOutputRenderer.TooFewParameters", opID);
									return null;
								}
								String content = ((Parameter)parameters.get(0)).getValue();
								transformer.appendTransformOperation(new AppendChildTextOperation(content));
							} else if (opID.equals("ConvertAttributeToTextOperation")) {
								EList parameters = operation.getParameters();
								if (parameters.size() < 1) {
									log.error("Warning.DTHTMLOutputRenderer.TooFewParameters", opID);
									return null;
								}
								if (parameters.size() < 2) {
									String attributeName = ((Parameter)parameters.get(0)).getValue();
									transformer.appendTransformOperation(new ConvertAttributeToTextOperation(attributeName));
								} else {
									String attributeName = ((Parameter)parameters.get(0)).getValue();
									boolean removeAttribute = Boolean.valueOf(((Parameter)parameters.get(1)).getValue()).booleanValue();
									transformer.appendTransformOperation(new ConvertAttributeToTextOperation(attributeName, removeAttribute));
								}
							} else if (opID.equals("CopyAllAttributesOperation")) {
								transformer.appendTransformOperation(new CopyAllAttributesOperation());
							} else if (opID.equals("CopyAttributeOperation")) {
								EList parameters = operation.getParameters();
								if (parameters.size() < 1) {
									log.error("Warning.DTHTMLOutputRenderer.TooFewParameters", opID);
									return null;
								}
								if (parameters.size() < 3) {
									String attributeName = ((Parameter)parameters.get(0)).getValue();
									transformer.appendTransformOperation(new CopyAttributeOperation(attributeName));
								} else {
									String attributeName = ((Parameter)parameters.get(0)).getValue();
									boolean create = Boolean.valueOf(((Parameter)parameters.get(1)).getValue()).booleanValue();
									String newAttributeValue = ((Parameter)parameters.get(2)).getValue();
									transformer.appendTransformOperation(new CopyAttributeOperation(attributeName, create, newAttributeValue));
								}
							} else if (opID.equals("CopyChildrenOperation")) {
								transformer.appendTransformOperation(new CopyChildrenOperation());
							} else if (opID.equals("CreateAttributeOperation")) {
								EList parameters = operation.getParameters();
								if (parameters.size() < 2) {
									log.error("Warning.DTHTMLOutputRenderer.TooFewParameters", opID);
									return null;
								}
								String attributeName = ((Parameter)parameters.get(0)).getValue();
								String attributeValue = ((Parameter)parameters.get(1)).getValue();
								transformer.appendTransformOperation(new CreateAttributeOperation(attributeName, attributeValue));
							} else if (opID.equals("CreateElementOperation")) {
								EList parameters = operation.getParameters();
								if (parameters.size() < 1) {
									log.error("Warning.DTHTMLOutputRenderer.TooFewParameters", opID);
									return null;
								}
								String elementName = ((Parameter)parameters.get(0)).getValue();
								transformer.appendTransformOperation(new CreateElementOperation(elementName));
							} else if (opID.equals("MakeParentElementCurrentOperation")) {
								transformer.appendTransformOperation(new MakeParentElementCurrentOperation());
							} else if (opID.equals("RemoveAttributeOperation")) {
								EList parameters = operation.getParameters();
								if (parameters.size() < 1) {
									log.error("Warning.DTHTMLOutputRenderer.TooFewParameters", opID);
									return null;
								}
								String attributeName = ((Parameter)parameters.get(0)).getValue();
								transformer.appendTransformOperation(new RemoveAttributeOperation(attributeName));
							} else if (opID.equals("RenameAttributeOperation")) {
								EList parameters = operation.getParameters();
								if (parameters.size() < 2) {
									log.error("Warning.DTHTMLOutputRenderer.TooFewParameters", opID);
									return null;
								}
								String oldAttributeName = ((Parameter)parameters.get(0)).getValue();
								String newAttributeName = ((Parameter)parameters.get(1)).getValue();
								transformer.appendTransformOperation(new RenameAttributeOperation(oldAttributeName, newAttributeName));
							} else {
								log.error("Warning.DTHTMLOutputRenderer.UnknownOperationID", opID);
								return null;
							}
						}
					}
				}
			}
		}
		return transformer;
	}

}
