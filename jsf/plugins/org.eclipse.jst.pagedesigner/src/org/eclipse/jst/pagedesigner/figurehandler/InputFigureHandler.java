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

import org.eclipse.jst.jsf.common.ui.JSFUICommonPlugin;
import org.eclipse.jst.jsf.common.ui.internal.utils.JSFSharedImages;
import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyID;
import org.eclipse.jst.pagedesigner.css2.provider.ICSSWidgetProvider;
import org.eclipse.jst.pagedesigner.css2.widget.ButtonWidgetProvider;
import org.eclipse.jst.pagedesigner.css2.widget.CheckboxWidgetProvider;
import org.eclipse.jst.pagedesigner.css2.widget.HiddenProvider;
import org.eclipse.jst.pagedesigner.css2.widget.ImageWidgetProvider;
import org.eclipse.jst.pagedesigner.css2.widget.InputFileWidgetProvider;
import org.eclipse.jst.pagedesigner.css2.widget.RadioWidgetProvider;
import org.eclipse.jst.pagedesigner.css2.widget.TextInputWidgetProvider;
import org.eclipse.jst.pagedesigner.utils.DOMUtil;
import org.eclipse.jst.pagedesigner.utils.ImageResolver;
import org.eclipse.swt.graphics.Image;
import org.w3c.dom.Element;

/**
 * @author mengbo
 */
/*package*/ class InputFigureHandler extends WidgetFigureHandler {
	private Image _image;

	void initializeImage(Element node) {
		if (_image != null) {
			_image.dispose();
		}
		_image = ImageResolver.initializeImage(node, "src"); //$NON-NLS-1$
	}

	ImageWidgetProvider getImageProvider(Element node) {
		initializeImage(node);
		ImageWidgetProvider provider = new ImageWidgetProvider(_image,
				getCSSStyle(node));
		return provider;
	}

	ICSSWidgetProvider getButtonProvider(Element node) {
		ButtonWidgetProvider provider = new ButtonWidgetProvider(
				getCSSStyle(node));
		provider.setValue(getButtonValue(node));
		return provider;
	}

	ICSSWidgetProvider getFileProvider(Element node) {
		// ICSSWidgetProvider textprovider = getTextInputProvider();
		// // XXX: should we use the defaultstyle for the button?
		// ButtonWidgetProvider browsebutton = new
		// ButtonWidgetProvider(getCSSStyle());
		// browsebutton.setValue("Browse...");
		// CompositeWidgetProvider provider = new
		// CompositeWidgetProvider(getCSSStyle(), textprovider, browsebutton,
		// false);
		// return provider;
		ICSSWidgetProvider textprovider = getTextInputProvider(node);
		// XXX: should we use the defaultstyle for the button?
		ButtonWidgetProvider browsebutton = new ButtonWidgetProvider(
				getCSSStyle(node));
		browsebutton.setValue(Messages.InputFigureHandler_Browse);
		InputFileWidgetProvider provider = new InputFileWidgetProvider(
				getCSSStyle(node), textprovider, browsebutton);
		return provider;
	}

	/**
	 * should not return null
	 * 
	 * @return the image
	 */
	protected Image getHiddenImage() {
		return JSFUICommonPlugin.getDefault().getImage(
				JSFSharedImages.DEFAULT_PALETTE_TAG_IMG);
	}

	ICSSWidgetProvider getHiddenProvider(Element node) {
		return new HiddenProvider(getHiddenImage(), node);
	}

	ICSSWidgetProvider getPasswordProvider(Element node) {
		TextInputWidgetProvider provider = new TextInputWidgetProvider(
				getCSSStyle(node), TextInputWidgetProvider.PWD_SIZE);
		provider.setSize(getSize(node));
		provider.setValue("********"); //$NON-NLS-1$
		return provider;
	}

	ICSSWidgetProvider getTextInputProvider(Element node) {
		TextInputWidgetProvider provider = new TextInputWidgetProvider(
				getCSSStyle(node));
		provider.setSize(getSize(node));
		provider.setValue(getValue(node));
		return provider;
	}

	private int getSize(Element node) {
		String s = DOMUtil.getAttributeIgnoreCase(node, "size"); //$NON-NLS-1$
		try {
			if (s != null) {
				return Integer.parseInt(s);
			}
		} catch (NumberFormatException  ex) {
            // suppress and fall-through; return 0
		}
        return 0;
	}

	/**
	 * @return
	 */
	private String getValue(Element node) {
		return DOMUtil.getAttributeIgnoreCase(node, "value"); //$NON-NLS-1$
	}

	private String getButtonValue(Element node) {
		String value = getValue(node);
		if (value == null) {
			String type = DOMUtil.getAttributeIgnoreCase(node,
					ICSSPropertyID.ATTR_TYPE);
			if (type.equalsIgnoreCase(ICSSPropertyID.VAL_SUBMIT)) {
				return IHTMLConstants.SUBMIT_LABEL;
			} else if (type.equalsIgnoreCase(ICSSPropertyID.VAL_RESET)) {
				return IHTMLConstants.RESET_LABEL;
			}
		}
		return value;
	}

	/**
	 * @return the widget provider
	 */
	protected ICSSWidgetProvider initializeWidgetProvider(Element node) {
		reset();

		String type = DOMUtil.getAttributeIgnoreCase(node,
				ICSSPropertyID.ATTR_TYPE);

		if (type == null) {
			return getTextInputProvider(node);
		} else if (type.equalsIgnoreCase(ICSSPropertyID.VAL_SUBMIT)
				|| type.equalsIgnoreCase(ICSSPropertyID.VAL_RESET)
				|| type.equalsIgnoreCase(ICSSPropertyID.VAL_BUTTON)) {
			return getButtonProvider(node);
		} else if (type.equalsIgnoreCase(ICSSPropertyID.VAL_IMAGE)) {
			return getImageProvider(node);
		} else if (type.equalsIgnoreCase(ICSSPropertyID.VAL_FILE)) {
			return getFileProvider(node);
		} else if (type.equalsIgnoreCase(ICSSPropertyID.VAL_RADIO)) {
			RadioWidgetProvider provider = new RadioWidgetProvider(
					getCSSStyle(node));
			provider.setChecked(node.hasAttribute("checked")); //$NON-NLS-1$
			return provider;
		} else if (type.equalsIgnoreCase(ICSSPropertyID.VAL_CHECKBOX)) {
			CheckboxWidgetProvider provider = new CheckboxWidgetProvider(
					getCSSStyle(node));
			provider.setChecked(node.hasAttribute("checked")); //$NON-NLS-1$
			return provider;
		} else if (type.equalsIgnoreCase(ICSSPropertyID.VAL_HIDDEN)) {
			return getHiddenProvider(node);
		}
		if (type.equalsIgnoreCase(ICSSPropertyID.VAL_PASSWORD)) {
			return getPasswordProvider(node);
		}
        return getTextInputProvider(node);
	}

	/**
	 * 
	 */
	private void reset() {
		if (_image != null) {
			_image.dispose();
			_image = null;
		}
	}

	public void dispose() {
		reset();
	}

}
