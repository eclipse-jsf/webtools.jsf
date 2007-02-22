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
package org.eclipse.jst.pagedesigner.jsf.ui.converter;

import java.util.Iterator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.pagedesigner.IJSFConstants;
import org.eclipse.jst.pagedesigner.dom.TagIdentifier;
import org.eclipse.jst.pagedesigner.dom.TagIdentifierFactory;
import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.provisional.Operation;
import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.provisional.Parameter;
import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.provisional.TagConvertInfo;
import org.eclipse.jst.pagedesigner.dtmanager.internal.provisional.DTManager;
import org.eclipse.jst.pagedesigner.dtmanager.internal.provisional.IDTInfo;
import org.eclipse.jst.pagedesigner.jsf.ui.JSFUIPlugin;
import org.eclipse.jst.pagedesigner.jsf.ui.converter.operations.AppendChildElementOperation;
import org.eclipse.jst.pagedesigner.jsf.ui.converter.operations.ConvertAttributeToTextOperation;
import org.eclipse.jst.pagedesigner.jsf.ui.converter.operations.CopyAllAttributesOperation;
import org.eclipse.jst.pagedesigner.jsf.ui.converter.operations.CopyAttributeOperation;
import org.eclipse.jst.pagedesigner.jsf.ui.converter.operations.CopyChildrenOperation;
import org.eclipse.jst.pagedesigner.jsf.ui.converter.operations.CreateAttributeOperation;
import org.eclipse.jst.pagedesigner.jsf.ui.converter.operations.CreateElementOperation;
import org.eclipse.jst.pagedesigner.jsf.ui.converter.operations.MakeParentElementCurrentOperation;
import org.eclipse.jst.pagedesigner.jsf.ui.converter.operations.RemoveAttributeOperation;
import org.eclipse.jst.pagedesigner.jsf.ui.converter.operations.RenameAttributeOperation;
import org.eclipse.jst.pagedesigner.jsf.ui.converter.operations.jsf.PanelGridOperation;
import org.eclipse.jst.pagedesigner.jsf.ui.util.JSFUIPluginResourcesUtil;
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
	 * @see org.eclipse.jst.pagedesigner.jsf.ui.converter.IOutputRenderer#render(org.eclipse.jst.pagedesigner.jsf.ui.converter.ITagConverterContext)
	 */
	public Element render(ITagConverterContext tagConverterContext) {
		this.tagConverterContext = tagConverterContext;
		Element srcElement = tagConverterContext.getHostElement();
		Element resultElement = null;
		if (srcElement != null) {
			ITransformer transformer = createTransformerFromDTInfo(srcElement);
			if (transformer == null) {
				transformer = createTransformer(srcElement);
			}
			resultElement = transformer.transform(srcElement);
		}
		return resultElement;
	}

	protected ITransformer createTransformerFromDTInfo(Element element) {
		ITransformer transformer = null;
		DTManager dtManager = new DTManager();
		IDTInfo dtInfo = dtManager.getDTInfo(element);
		if (dtInfo != null) {
			TagConvertInfo tcInfo = dtInfo.getTagConvertInfo();
			if (tcInfo != null) {
				transformer = new DefaultTransformer();
				transformer.setTagConverterContext(tagConverterContext);
				String classname = tcInfo.getClassname();
				if (classname != null && classname.length() > 0) {
					try {
						Class opClass = Class.forName(classname);
						Object opObject = opClass.newInstance();
						if (opObject instanceof ITransformOperation) {
							transformer.appendTransformOperation((ITransformOperation)opObject);
							return null;
						} else {
							JSFUIPlugin.log(
									IStatus.WARNING,
									JSFUIPluginResourcesUtil.getInstance().getString("Warning.DTHTMLOutputRenderer.NotITransformOperation") + " (\"" + classname + "\")");
						}
					} catch(ClassNotFoundException cnfe) {
						JSFUIPlugin.log(
								IStatus.WARNING,
								JSFUIPluginResourcesUtil.getInstance().getString("Warning.DTHTMLOutputRenderer.ClassNotFound") + " (\"" + classname + "\")",
								cnfe);
						return null;
					} catch(IllegalAccessException iae) {
						JSFUIPlugin.log(
								IStatus.WARNING,
								JSFUIPluginResourcesUtil.getInstance().getString("Warning.DTHTMLOutputRenderer.IllegalAccess") + " (\"" + classname + "\")",
								iae);
						return null;
					} catch(InstantiationException ie) {
						JSFUIPlugin.log(
								IStatus.WARNING,
								JSFUIPluginResourcesUtil.getInstance().getString("Warning.DTHTMLOutputRenderer.Instantiation") + " (\"" + classname + "\")",
								ie);
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
									JSFUIPlugin.log(
											IStatus.WARNING,
											JSFUIPluginResourcesUtil.getInstance().getString("Warning.DTHTMLOutputRenderer.TooFewParameters") + " (\"" + opID + "\")");
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
							} else if (opID.equals("ConvertAttributeToTextOperation")) {
								EList parameters = operation.getParameters();
								if (parameters.size() < 1) {
									JSFUIPlugin.log(
											IStatus.WARNING,
											JSFUIPluginResourcesUtil.getInstance().getString("Warning.DTHTMLOutputRenderer.TooFewParameters") + " (\"" + opID + "\")");
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
									JSFUIPlugin.log(
											IStatus.WARNING,
											JSFUIPluginResourcesUtil.getInstance().getString("Warning.DTHTMLOutputRenderer.TooFewParameters") + " (\"" + opID + "\")");
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
									JSFUIPlugin.log(
											IStatus.WARNING,
											JSFUIPluginResourcesUtil.getInstance().getString("Warning.DTHTMLOutputRenderer.TooFewParameters") + " (\"" + opID + "\")");
									return null;
								}
								String attributeName = ((Parameter)parameters.get(0)).getValue();
								String attributeValue = ((Parameter)parameters.get(1)).getValue();
								transformer.appendTransformOperation(new CreateAttributeOperation(attributeName, attributeValue));
							} else if (opID.equals("CreateElementOperation")) {
								EList parameters = operation.getParameters();
								if (parameters.size() < 1) {
									JSFUIPlugin.log(
											IStatus.WARNING,
											JSFUIPluginResourcesUtil.getInstance().getString("Warning.DTHTMLOutputRenderer.TooFewParameters") + " (\"" + opID + "\")");
									return null;
								}
								String elementName = ((Parameter)parameters.get(0)).getValue();
								transformer.appendTransformOperation(new CreateElementOperation(elementName));
							} else if (opID.equals("MakeParentElementCurrentOperation")) {
								transformer.appendTransformOperation(new MakeParentElementCurrentOperation());
							} else if (opID.equals("RemoveAttributeOperation")) {
								EList parameters = operation.getParameters();
								if (parameters.size() < 1) {
									JSFUIPlugin.log(
											IStatus.WARNING,
											JSFUIPluginResourcesUtil.getInstance().getString("Warning.DTHTMLOutputRenderer.TooFewParameters") + " (\"" + opID + "\")");
									return null;
								}
								String attributeName = ((Parameter)parameters.get(0)).getValue();
								transformer.appendTransformOperation(new RemoveAttributeOperation(attributeName));
							} else if (opID.equals("RenameAttributeOperation")) {
								EList parameters = operation.getParameters();
								if (parameters.size() < 2) {
									JSFUIPlugin.log(
											IStatus.WARNING,
											JSFUIPluginResourcesUtil.getInstance().getString("Warning.DTHTMLOutputRenderer.TooFewParameters") + " (\"" + opID + "\")");
									return null;
								}
								String oldAttributeName = ((Parameter)parameters.get(0)).getValue();
								String newAttributeName = ((Parameter)parameters.get(1)).getValue();
								transformer.appendTransformOperation(new RenameAttributeOperation(oldAttributeName, newAttributeName));
							} else {
								JSFUIPlugin.log(
										IStatus.WARNING,
										JSFUIPluginResourcesUtil.getInstance().getString("Warning.DTHTMLOutputRenderer.UnknownOperationID") + " (\"" + opID + "\")");
								return null;
							}
						}
					}
				}
			}
		}
		return transformer;
	}

	/**
	 * Configures and returns an ITransformer instance for the specified source
	 * Element.
	 * 
	 * @param srcElement Source Element for which to configure and return an
	 * ITransformer instance.
	 * @return Configured ITransformer instance for the specified source
	 * Element.
	 */
	protected ITransformer createTransformer(Element srcElement) {
		ITransformer transformer = new DefaultTransformer();
		transformer.setTagConverterContext(tagConverterContext);
        
        TagIdentifier srcTagIdentifier = 
            TagIdentifierFactory.createDocumentTagWrapper(srcElement); 
        
		if (IJSFConstants.TAG_IDENTIFIER_VIEW.isSameTagType(srcTagIdentifier)) {
			transformer.appendTransformOperation(new CreateElementOperation("div"));
			transformer.appendTransformOperation(new CopyChildrenOperation());
		} else if (IJSFConstants.TAG_IDENTIFIER_FACET.isSameTagType(srcTagIdentifier)) {
			transformer.appendTransformOperation(new CreateElementOperation("span"));
			transformer.appendTransformOperation(new CopyChildrenOperation());
		} else if (IJSFConstants.TAG_IDENTIFIER_VERBATIM.isSameTagType(srcTagIdentifier)) {
			transformer.appendTransformOperation(new CreateElementOperation("span"));
			transformer.appendTransformOperation(new CopyChildrenOperation());
		} else if (IJSFConstants.TAG_IDENTIFIER_FORM.isSameTagType(srcTagIdentifier)) {
            transformer.appendTransformOperation(new CreateElementOperation("form"));
            transformer.appendTransformOperation(new CopyAllAttributesOperation());
            transformer.appendTransformOperation(new RenameAttributeOperation("styleClass", "class"));
            transformer.appendTransformOperation(new CopyChildrenOperation());
        } else if (IJSFConstants.TAG_IDENTIFIER_INPUTTEXT.isSameTagType(srcTagIdentifier)) {
			transformer.appendTransformOperation(new CreateElementOperation("input"));
			transformer.appendTransformOperation(new CopyAllAttributesOperation());
			transformer.appendTransformOperation(new RenameAttributeOperation("styleClass", "class"));
			transformer.appendTransformOperation(new CreateAttributeOperation("type", "text"));
        } else if (IJSFConstants.TAG_IDENTIFIER_INPUTSECRET.isSameTagType(srcTagIdentifier)) {
			transformer.appendTransformOperation(new CreateElementOperation("input"));
			transformer.appendTransformOperation(new CopyAllAttributesOperation());
			transformer.appendTransformOperation(new RenameAttributeOperation("styleClass", "class"));
			transformer.appendTransformOperation(new CreateAttributeOperation("type", "password"));
        } else if (IJSFConstants.TAG_IDENTIFIER_INPUTTEXTAREA.isSameTagType(srcTagIdentifier)) {
			transformer.appendTransformOperation(new CreateElementOperation("textarea"));
			transformer.appendTransformOperation(new CopyAllAttributesOperation());
			transformer.appendTransformOperation(new RenameAttributeOperation("styleClass", "class"));
			transformer.appendTransformOperation(new ConvertAttributeToTextOperation("value"));
        } else if (IJSFConstants.TAG_IDENTIFIER_OUTPUTTEXT.isSameTagType(srcTagIdentifier)) {
			transformer.appendTransformOperation(new CreateElementOperation("span"));
			transformer.appendTransformOperation(new CopyAllAttributesOperation());
			transformer.appendTransformOperation(new RenameAttributeOperation("styleClass", "class"));
			transformer.appendTransformOperation(new ConvertAttributeToTextOperation("value"));
        } else if (IJSFConstants.TAG_IDENTIFIER_OUTPUTLABEL.isSameTagType(srcTagIdentifier)) {
			transformer.appendTransformOperation(new CreateElementOperation("label"));
			transformer.appendTransformOperation(new CopyAllAttributesOperation());
			transformer.appendTransformOperation(new RenameAttributeOperation("styleClass", "class"));
			transformer.appendTransformOperation(new ConvertAttributeToTextOperation("value"));
			transformer.appendTransformOperation(new CopyChildrenOperation());
        } else if (IJSFConstants.TAG_IDENTIFIER_GRAPHICIMAGE.isSameTagType(srcTagIdentifier)) {
			transformer.appendTransformOperation(new CreateElementOperation("img"));
			transformer.appendTransformOperation(new CopyAllAttributesOperation());
			transformer.appendTransformOperation(new RenameAttributeOperation("styleClass", "class"));
			transformer.appendTransformOperation(new RenameAttributeOperation("url", "src"));
			//"value" overrides "url" as new "src" attribute
			transformer.appendTransformOperation(new RenameAttributeOperation("value", "src"));
		} else if (IJSFConstants.TAG_IDENTIFIER_PANEL_GRID.isSameTagType(srcTagIdentifier)) {
			transformer.appendTransformOperation(new PanelGridOperation());
		}
		return transformer;
	}

}
