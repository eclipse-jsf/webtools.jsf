/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.properties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyID;
import org.eclipse.jst.pagedesigner.dom.DOMUtil;
import org.eclipse.jst.pagedesigner.editors.HTMLEditor;
import org.eclipse.jst.pagedesigner.utils.SelectionHelper;
import org.eclipse.jst.pagedesigner.viewer.DesignRange;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.views.contentoutline.ContentOutline;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.xml.core.internal.contentmodel.CMElementDeclaration;
import org.eclipse.wst.xml.core.internal.contentmodel.CMNamedNodeMap;
import org.eclipse.wst.xml.core.internal.contentmodel.CMNode;
import org.eclipse.wst.xml.core.internal.contentmodel.modelquery.ModelQuery;
import org.eclipse.wst.xml.core.internal.document.ElementImpl;
import org.eclipse.wst.xml.core.internal.modelquery.ModelQueryUtil;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/**
 * This is util class most used by Property related operation.
 * 
 * @author mengbo
 */
public class DesignerPropertyTool {
	public static String getAttributeValue(Element fNode, CMNode attributeDesc) {
		if (attributeDesc == null) {
			return ""; //$NON-NLS-1$
		}
		String returnedValue = ""; //$NON-NLS-1$
		NamedNodeMap attrMap = fNode.getAttributes();
		if (attrMap != null) {
			Node attribute = attrMap.getNamedItem(attributeDesc.getNodeName());
			if (attribute != null) {
				if (attribute instanceof IDOMNode) {
					returnedValue = ((IDOMNode) attribute).getValueSource();
				} else {
					returnedValue = attribute.getNodeValue();
				}
			}
		}
		return returnedValue;
	}

	public static Object[] getElementReferedAttributes(Element fNode,
			String[] filter) {
		List result = new ArrayList();
		CMNamedNodeMap cmnnm = getElementDeclaredAttributes(fNode);
		for (int i = 0, n = cmnnm.getLength(); i < n; i++) {
			String name = cmnnm.item(i).getNodeName();
			if (Arrays.asList(filter).contains(name)) {
				result.add(cmnnm.item(i));
			}
		}
		return result.toArray(new CMNode[result.size()]);
	}

	public static CMNamedNodeMap getElementDeclaredAttributes(Node fNode) {
		IStructuredModel structModel = null;
		if (fNode instanceof IDOMNode) {
			structModel = ((IDOMNode) fNode).getModel();
		}
		if (null == structModel) {
			return null;
		}
		CMElementDeclaration cmde = null;
		CMNamedNodeMap cmnnm = null;
		if (fNode == null || fNode.getNodeType() != Node.ELEMENT_NODE) {
			cmde = null;
		}
		ModelQuery modelQuery = ModelQueryUtil.getModelQuery(fNode
				.getOwnerDocument());
		if (modelQuery != null) {
			cmde = modelQuery.getCMElementDeclaration((Element) fNode);
		}
		if (cmde != null) {
			cmnnm = cmde.getAttributes();
		}
		return cmnnm;
	}

	/**
	 * the selection could be different kinds of selection, including: 1.
	 * ITextSelection 2. IStructuredSelection (Node) 3. IStructuredSelection
	 * (EditPart) 4. DesignRange we want to normalize it to only #2. If the node
	 * is ATTR or TEXT/CDATA_SECTION, will use it's parent node.
	 * 
	 * @param part
	 * @param selection
	 * @return null if can't normalize.
	 */
	public static Node normalizeSelectionToElement(
			IWorkbenchPart selectingPart, ISelection selection,
			HTMLEditor _htmlEditor) {
		Node node = null;
		if (selectingPart instanceof HTMLEditor) {
			IEditorPart part = ((HTMLEditor) selectingPart).getActiveEditor();
			if (part instanceof TextEditor) {
				if (selection instanceof ITextSelection) {
					IStructuredModel model = ((HTMLEditor) selectingPart)
							.getModel();
					node = SelectionHelper.toNode(model,
							(ITextSelection) selection);
				}
			} else if (part instanceof GraphicalEditor) {
				if (selection instanceof IStructuredSelection) {
					node = SelectionHelper
							.toNode((IStructuredSelection) selection);
				} else if (selection instanceof DesignRange) {
					node = SelectionHelper.toNode((DesignRange) selection);
				}
			}
			if (node instanceof Attr) {
				node = ((Attr) node).getOwnerElement();
			} else if (node instanceof Text || node instanceof CDATASection) {
				node = node.getParentNode();
			}
		} else if (selectingPart instanceof ContentOutline) {
			if (selection instanceof IStructuredSelection
					&& ((ContentOutline) selectingPart).getCurrentPage() != null
					&& ((ContentOutline) selectingPart).getCurrentPage()
							.getControl().isFocusControl()) {
				node = SelectionHelper.toNode((IStructuredSelection) selection);
				if (node == null) {
					node = _htmlEditor.getDOMDocument();
				}
			}
		}

		return node;
	}

	// /**
	// * the selection could be different kinds of selection, including:
	// * 1. ITextSelection
	// * 2. IStructuredSelection (Node)
	// * 3. IStructuredSelection (EditPart)
	// * 4. DesignRange
	// * we want to normalize it to only #2 and #4.
	// *
	// * @param part
	// * @param selection
	// * @return null if can't normalize.
	// */
	// public static ISelection normalizeSelection(IWorkbenchPart selectingPart,
	// ISelection selection)
	// {
	// // On Attr nodes, select the owner Element. On Text nodes, select the
	// parent Element.
	// ISelection preferredSelection = null;
	// if (selection instanceof ITextSelection)
	// {
	// // FIXME: currently always normalize to a single node. should also
	// consider change into DesignRange
	// // on text selection, find the appropriate Node
	// ITextSelection textSel = (ITextSelection) selection;
	// IStructuredModel model = null;
	// if (selectingPart instanceof HTMLEditor)
	// {
	// model = ((HTMLEditor) selectingPart).getModel();
	//
	// Object inode = model.getIndexedRegion(textSel.getOffset());
	// if (inode instanceof Node)
	// {
	// Node node = (Node) inode;
	// // replace Attribute Node with its owner
	// if (node.getNodeType() == Node.ATTRIBUTE_NODE)
	// inode = ((Attr) node).getOwnerElement();
	// // replace Text Node with its parent
	// else if ((node.getNodeType() == Node.TEXT_NODE || (node.getNodeType() ==
	// Node.CDATA_SECTION_NODE)) && node.getParentNode() != null)
	// {
	// inode = node.getParentNode();
	// }
	// }
	// if (inode != null)
	// {
	// return new StructuredSelection(inode);
	// }
	// else
	// {
	// return null;
	// }
	// }
	// else
	// {
	// return null;
	// }
	// }
	// else if (selection instanceof IStructuredSelection)
	// {
	// if (((IStructuredSelection) selection).isEmpty())
	// {
	// return null;
	// }
	//
	// IStructuredSelection structuredSel = (IStructuredSelection) selection;
	// List inputList = new ArrayList(structuredSel.size());
	// for (Iterator iter = structuredSel.iterator(); iter.hasNext();)
	// {
	// Object inode = iter.next();
	// if (inode instanceof NodeEditPart)
	// {
	// inode = ((NodeEditPart) inode).getModel();
	// }
	//
	// if (inode instanceof Node)
	// {
	// inputList.add(inode);
	// }
	// }
	// if (inputList.isEmpty())
	// {
	// return null;
	// }
	// else
	// {
	// return new StructuredSelection(inputList);
	// }
	// }
	// else if (selection instanceof DesignRange)
	// {
	// return selection;
	// }
	// else
	// {
	// return null;
	// }
	// }

	public static Element getElementNode(Object node) {
		Object model;
		Element element = null;
		if (node == null) {
			return null;
		}

		if (node instanceof Element) {
			element = (Element) node;
		} else if (node instanceof AbstractEditPart) {
			model = ((AbstractEditPart) node).getModel();
			if (model instanceof Element) {
				element = (Element) model;
			}
		} else if (node instanceof ISelection) {
			element = getElement(null, (ISelection) node);
		}
		return element;
	}

	public static List getNameList(Element element, String[] filter) {
		List result = new ArrayList();
		CMNamedNodeMap attributes = getElementDeclaredAttributes(element);
		if (attributes != null) {
			for (int i = 0, n = attributes.getLength(); i < n; i++) {
				String name = attributes.item(i).getNodeName();
				if (Arrays.asList(filter).contains(name))
					result.add(name);
			}
		}
		return result;
	}

	/**
	 * @param selection
	 *            should be a normalized selection
	 * @return
	 */
	public static Node getCommonParent(ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			Object obj = ((IStructuredSelection) selection).getFirstElement();
			return (Node) obj;
		} else if (selection instanceof DesignRange) {
			DesignRange range = (DesignRange) selection;
			Node node1 = range.getStartPosition().getContainerNode();
			Node node2 = range.getEndPosition().getContainerNode();
			return DOMUtil.findCommonAncester(node1, node2);
		} else {
			// should not happen
			return null;
		}
	}

	/**
	 * The passed in selection should be normalized selection.
	 * 
	 * @param selectingPart
	 * @param selection
	 * @return
	 */
	public static Element getElement(IWorkbenchPart selectingPart,
			ISelection selection) {
		Node node = getCommonParent(selection);
		if (node instanceof Element) {
			return (Element) node;
		} else if (node != null) {
			node = node.getParentNode();
			if (node instanceof Element) {
				return (Element) node;
			}
		}
		return null;
	}

	// reserved for future native use.
	// public static void dumpChildren(Element element)
	// {
	// // In this function we are using logger to dump message out.
	// Logger logger = PDPlugin.getLogger(DesignerPropertyTool.class);
	// if (element == null || !DEBUG)
	// return;
	// NodeList nl = element.getChildNodes();
	// // It's our pattern for dumping message
	// logger.debug("\n----------------------------"); //$NON-NLS-1$
	// logger.debug("Element:" + element.getNodeName()); //$NON-NLS-1$
	// for (int i = 0; i < nl.getLength(); i++)
	// {
	// Node node = nl.item(i);
	// logger.debug("child[" + i + "]:" + node.getNodeName()); //$NON-NLS-1$
	// //$NON-NLS-2$
	// }
	// logger.debug("----------------------------\n"); //$NON-NLS-1$
	// }

	public static boolean isMultiSelection(Element element) {
		if (element.getNodeName().equalsIgnoreCase(IHTMLConstants.TAG_OPTION)) {
			return element.getAttribute(ICSSPropertyID.ATTR_MULTIPLE) != null;
		}
		return false;
	}

	public static String getElementTextSource(Element element) {
		if (element == null) {
			return null;
		}
		if (element instanceof ElementImpl) {
			return ((ElementImpl) element).getSource();
		}
		return null;
	}

	public static IJavaProject getJavaProject(Object project) {
		if (project == null) {
			return null;
		}
		if (project instanceof IJavaProject) {
			return (IJavaProject) project;
		} else if (project instanceof IProject) {
			try {
				IProjectNature nature = ((IProject) project)
						.getNature(JavaCore.NATURE_ID);
				if (nature == null) {
					return null;
				}
                return (IJavaProject) nature;
			} catch (Exception e) {
				// Error.DesignerPropertyTool.NatureQuerying = Error in project
				// java nature querying
				PDPlugin.getLogger(DesignerPropertyTool.class).error(
						"Error.DesignerPropertyTool.NatureQuerying", e); //$NON-NLS-1$
				// Should be error tolerable?
				return null;
			}
		}
		return null;
	}

	public static IProject getProject(Object project) {
		if (project instanceof IProject) {
			return (IProject) project;
		} else if (project instanceof IJavaProject) {
			return ((IJavaProject) project).getProject();
		}
		return null;
	}

}
