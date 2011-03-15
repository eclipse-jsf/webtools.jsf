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
package org.eclipse.jst.pagedesigner.commands.single;

import java.util.Map;

import org.eclipse.jst.pagedesigner.commands.CommandResources;
import org.eclipse.jst.pagedesigner.dom.DOMStyleUtil;
import org.eclipse.jst.pagedesigner.properties.celleditors.CSSStyleDeclarationFactory;
import org.eclipse.wst.css.core.internal.provisional.document.ICSSStyleDeclaration;
import org.eclipse.wst.css.core.internal.util.declaration.CSSPropertyContext;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.w3c.dom.css.ElementCSSInlineStyle;

/**
 * @author mengbo
 * @version 1.5
 */
public class ChangeStyleCommand extends SingleNodeCommand {
	private Map _styleProperties = null;

	private CSSPropertyContext _context = null;

	private String _styleAttrName;

	/**
	 * @param node
	 * @param map
	 */
	public ChangeStyleCommand(IDOMElement node, Map map) {
		super(CommandResources
				.getString("ChangeStyleCommand.Label.ChangeStyle"), node); //$NON-NLS-1$
		_styleProperties = map;
		_styleAttrName = "style"; //$NON-NLS-1$
	}
	
	/**
	 * @param node
	 * @param styleAttrName 
	 * @param map
	 */
	public ChangeStyleCommand(IDOMElement node, String styleAttrName, Map map) {
		super(CommandResources
				.getString("ChangeStyleCommand.Label.ChangeStyle"), node); //$NON-NLS-1$
		_styleProperties = map;
		_styleAttrName = styleAttrName;
	}

	/**
	 * @param node
	 * @param context
	 * @deprecated
	 */
	public ChangeStyleCommand(IDOMElement node, CSSPropertyContext context) {
		super(CommandResources
				.getString("ChangeStyleCommand.Label.ChangeStyle"), node); //$NON-NLS-1$
		_context = context;
		_styleAttrName = "style"; //$NON-NLS-1$
	}
	
	/**
	 * @param node
	 * @param styleAttrName 
	 * @param context
	 */
	public ChangeStyleCommand(IDOMElement node, String styleAttrName, CSSPropertyContext context) {
		super(CommandResources
				.getString("ChangeStyleCommand.Label.ChangeStyle"), node); //$NON-NLS-1$
		_context = context;
		_styleAttrName = styleAttrName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#doExecute()
	 */
	protected void doExecute() {
		getOriginalElement().getModel().beginRecording(this);
		try {
			if (_styleProperties != null) {
				IDOMElement original = this.getOriginalElement();
				DOMStyleUtil.insertStyle(original, _styleAttrName, _styleProperties);
			} else if (_context != null) {
				ICSSStyleDeclaration styleDeclaration = CSSStyleDeclarationFactory.getInstance().getStyleDeclaration(getOriginalElement(), _styleAttrName);

				if (styleDeclaration == null) {
					getOriginalElement().setAttribute(_styleAttrName,
							""); //$NON-NLS-1$
					styleDeclaration = (ICSSStyleDeclaration) ((ElementCSSInlineStyle) getOriginalElement())
							.getStyle();
				}
				_context.applyModified(styleDeclaration);
			}
		} finally {
			getOriginalElement().getModel().endRecording(this);
		}
	}
}
