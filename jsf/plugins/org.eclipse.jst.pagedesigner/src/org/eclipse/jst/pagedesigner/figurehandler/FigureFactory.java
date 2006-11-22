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
package org.eclipse.jst.pagedesigner.figurehandler;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.css2.layout.CSSFigure;
import org.eclipse.jst.pagedesigner.css2.layout.CSSTextFigure;
import org.eclipse.jst.pagedesigner.css2.provider.ICSSTextProvider;
import org.eclipse.jst.pagedesigner.utils.HTMLUtil;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * @author mengbo
 * @version 1.5
 */
public class FigureFactory {
	// public static IFigure createFigure(Element ele, boolean deep)
	// {
	// IFigureHandler handler = createAndAdapt(ele);
	// IFigure figure = handler.createFigure(ele);
	//
	// if (deep && !handler.isWidget())
	// {
	// NodeList children = ele.getChildNodes();
	// for (int i = 0, size = children.getLength(); i < size; i++)
	// {
	// createFigureDeep(figure, children.item(i));
	// }
	// }
	// return figure;
	// }

	/**
	 * @param figure
	 * @param node
	 */
	private static void createFigureDeep(IFigure parentFigure, final Node node) {
		if (node instanceof Element) {
			IFigureHandler handler = createAndAdapt((Element) node);
			if (handler instanceof HiddenFigureHandler) {
				// for deep hidden element, we don't create figure for them.
				// this will remove the small <> icon for data window.
				return;
			}
			CSSFigure figure = new CSSFigure();
			handler.updateFigure((Element) node, figure);
			// IFigure figure = handler.createFigure((Element) node);
			parentFigure.add(figure);
			if (!handler.isWidget()) {
				NodeList children = node.getChildNodes();

				for (int i = 0, size = children.getLength(); i < size; i++) {
					createFigureDeep(figure, children.item(i));
				}
			}
		} else if (node instanceof Text) {
			final String data = HTMLUtil.compactWhitespaces((Text) node, node
					.getNodeValue());
			// XXX: seemed there is some bug in handling whitespace (unnecessary
			// take additional space)
			// so skip it here.
			if (data.trim().length() == 0) {
				return;
			}
			// XXX: currently creating of CSSTextFigure is distributed both here
			// and TextEditPart. We may want to unify them later.
			CSSTextFigure figure = new CSSTextFigure(new ICSSTextProvider() {
				/*
				 * (non-Javadoc)
				 * 
				 * @see org.eclipse.jst.pagedesigner.css2.provider.ICSSTextProvider#getCSSStyle()
				 */
                //  TODO: dead?
//				public ICSSStyle getCSSStyle() {
//					INodeNotifier notifier = (INodeNotifier) node
//							.getParentNode();
//					return (ICSSStyle) notifier.getAdapterFor(ICSSStyle.class);
//				}

				/*
				 * (non-Javadoc)
				 * 
				 * @see org.eclipse.jst.pagedesigner.css2.provider.ICSSTextProvider#getTextData()
				 */
				public String getTextData() {
					return data;
				}

				/*
				 * (non-Javadoc)
				 * 
				 * @see org.eclipse.jst.pagedesigner.css2.provider.ICSSTextProvider#getSelectedRange()
				 */
				public int[] getSelectedRange() {
					// text figure created here will not be real text in
					// original doc.
					// so don't support their selection.
					return null;
				}
			});
			parentFigure.add(figure);
		}
	}

	public static IFigure updateDeepFigure(Element ele, Element old,
			CSSFigure figure) {
		recursiveDisposeAndUnadapt(old);
		List figureChildren = figure.getChildren();
		for (int i = figureChildren.size() - 1; i >= 0; i--) {
			figure.remove((IFigure) figureChildren.get(i));
		}

		IFigureHandler handler = createAndAdapt(ele);
		handler.updateFigure(ele, figure);
		if (!handler.isWidget()) {
			NodeList children = ele.getChildNodes();
			for (int i = 0, size = children.getLength(); i < size; i++) {
				createFigureDeep(figure, children.item(i));
			}
		}
		return figure;
	}

	public static void updateNonDeepFigure(Element ele, CSSFigure figure) {
		IFigureHandler handler = getHandler(ele);
		if (handler == null) {
			handler = createAndAdapt(ele);
		}
		handler.updateFigure(ele, figure);
	}

	static void recursiveDisposeAndUnadapt(Element ele) {
		disposeAndUnadapt(ele);
		NodeList nl = ele.getChildNodes();
		for (int i = 0, size = nl.getLength(); i < size; i++) {
			Node n = nl.item(i);
			if (n instanceof Element) {
				recursiveDisposeAndUnadapt((Element) n);
			}
		}
	}

	static void disposeAndUnadapt(Element ele) {
		IFigureHandler handler = getHandler(ele);
		if (handler != null) {
			handler.dispose();
			((IDOMElement) ele).removeAdapter(handler);
		}
	}

	static IFigureHandler getHandler(Element ele) {
		if (ele instanceof IDOMElement) {
			IDOMElement xmlele = (IDOMElement) ele;
			return (IFigureHandler) xmlele.getAdapterFor(IFigureHandler.class);
		}
		return null;
	}

	static IFigureHandler createAndAdapt(Element ele) {
		IFigureHandler handler = createFigureHandler(ele);
		if (ele instanceof IDOMElement) {
			((IDOMElement) ele).addAdapter(handler);
		}
		return handler;
	}

	static IFigureHandler createFigureHandler(Element ele) {
		String tag = ele.getTagName();
		if ("input".equalsIgnoreCase(tag)) {
			return new InputFigureHandler();
		} else if ("select".equalsIgnoreCase(tag)) {
			return new SelectFigureHandler();
		} else if ("img".equalsIgnoreCase(tag)) {
			return new ImgFigureHandler();
		} else if ("object".equalsIgnoreCase(tag)) {
			return new ObjectFigureHandler();
		} else if ("textarea".equalsIgnoreCase(tag)) {
			return new TextareaFigureHandler();
		} else if ("br".equalsIgnoreCase(tag)) {
			return new BRFigureHandler();
		} else if (!HTMLUtil.isVisualHtmlElement(tag)) {
			return new HiddenFigureHandler(getSharedHTMLImage(tag));
		} else {
			return new DefaultFigureHandler();
		}
	}

	/**
	 * @param tag
	 * @return
	 */
	private static Image getSharedHTMLImage(String tag) {
		Image image = PDPlugin.getDefault().getImage(
				"palette/HTML/small/HTML_" + tag.toUpperCase() + ".gif");
		ImageData imageData = image.getImageData();
		if (imageData.width < 16 || imageData.height < 16) {
			return PDPlugin.getDefault().getImage(
					"palette/GENERIC/small/PD_Palette_Default.gif");
		}
		return image;
	}
}
