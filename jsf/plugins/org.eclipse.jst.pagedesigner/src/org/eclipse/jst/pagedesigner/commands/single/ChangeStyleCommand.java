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

import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.pagedesigner.commands.CommandResources;
import org.eclipse.jst.pagedesigner.dom.DOMStyleUtil;
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

	/**
	 * @param node
	 * @param map
	 */
	public ChangeStyleCommand(IDOMElement node, Map map) {
		super(CommandResources
				.getString("ChangeStyleCommand.Label.ChangeStyle"), node); //$NON-NLS-1$
		_styleProperties = map;
	}

	/**
	 * @param node
	 * @param context
	 */
	public ChangeStyleCommand(IDOMElement node, CSSPropertyContext context) {
		super(CommandResources
				.getString("ChangeStyleCommand.Label.ChangeStyle"), node); //$NON-NLS-1$
		_context = context;
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
				DOMStyleUtil.insertStyle(original, _styleProperties);
			} else if (_context != null) {
				ICSSStyleDeclaration styleDeclaration = (ICSSStyleDeclaration) ((ElementCSSInlineStyle) getOriginalElement())
						.getStyle();

				if (styleDeclaration == null) {
					getOriginalElement().setAttribute(IJSFConstants.ATTR_STYLE,
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
