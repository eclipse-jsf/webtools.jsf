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
package org.eclipse.jst.pagedesigner.utils;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.pagedesigner.IJMTConstants;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.dom.DOMPositionHelper;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.dom.JSFValidatorSupport;
import org.eclipse.jst.pagedesigner.editors.palette.TagToolPaletteEntry;
import org.eclipse.jst.pagedesigner.editors.palette.impl.PaletteElementTemplateHelper;
import org.eclipse.jst.pagedesigner.itemcreation.ITagCreator;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.w3c.dom.Element;

/**
 * @author mengbo
 * @version 1.5
 */
public class CommandUtil {
	private static final Logger _log = PDPlugin.getLogger(CommandUtil.class);

	public static Element excuteInsertion(TagToolPaletteEntry itemDes,
			IHTMLGraphicalViewer viewer, IDOMPosition domPosition) {
		return excuteInsertion(itemDes, viewer.getModel(), domPosition);
	}

	public static Element excuteInsertion(TagToolPaletteEntry tagItem,
			IDOMModel model, IDOMPosition domPosition) {
		try {						
			ITagCreator tagCreator = (ITagCreator)tagItem.getAdapter(ITagCreator.class);
			if (tagCreator == null)
				return null;//should never get here!
			
			return tagCreator.createTag(tagItem, model, domPosition);
			
		} catch (Exception e) {
			_log.info("Invalid insertion in position:" + domPosition + "\n", e);
			return null;
		}
	}

}
