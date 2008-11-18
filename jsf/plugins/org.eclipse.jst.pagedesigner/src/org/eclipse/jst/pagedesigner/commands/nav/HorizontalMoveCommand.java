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
package org.eclipse.jst.pagedesigner.commands.nav;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.dom.EditHelper;
import org.eclipse.jst.pagedesigner.parts.SubNodeEditPart;
import org.eclipse.jst.pagedesigner.parts.TextEditPart;
import org.eclipse.jst.pagedesigner.validation.caret.ActionData;
import org.eclipse.jst.pagedesigner.validation.caret.IMovementMediator;
import org.eclipse.jst.pagedesigner.validation.caret.InlineEditingNavigationMediator;
import org.eclipse.jst.pagedesigner.viewer.DesignPosition;
import org.eclipse.jst.pagedesigner.viewer.DesignRange;
import org.eclipse.jst.pagedesigner.viewer.DesignRefPosition;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;

/**
 * @author mengbo
 */
public class HorizontalMoveCommand extends Command implements
		ICaretPositionMover {
	private static Logger _log = PDPlugin
			.getLogger(HorizontalMoveCommand.class);

	IHTMLGraphicalViewer _viewer;

	boolean _forward;

	boolean _onlyMoveEnd;

	/**
	 * @param viewer
	 * @param b
	 * @param c
	 */
	public HorizontalMoveCommand(IHTMLGraphicalViewer viewer, boolean b,
			boolean c) {
		_viewer = viewer;
		_forward = b;
		_onlyMoveEnd = c;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		DesignPosition position = null;
		if (_viewer.isInRangeMode()) {
			if (_viewer.getRangeSelection() != null
					&& _viewer.getRangeSelection().isValid()) {
				position = _viewer.getRangeSelection().getEndPosition();
				position = performInlineMode(_forward);
			}
		} else {
			position = performObjectMode();
		}
		setRange(position);
	}

	private DesignPosition performObjectMode() {
		DesignPosition result = null;
		IMovementMediator validator = new InlineEditingNavigationMediator(
				new ActionData(ActionData.KEYBOARD_NAVAGATION, null));
		DesignPosition position = getCurrentObjectPosition();
		if (position != null) {
			_viewer.setRange(position, position);
			if (!validator.isValidPosition(position)) {
				position = performInlineMode(_forward);
				if (validator.isValidPosition(position)) {
					result = position;
				} else {
					result = performInlineMode(!_forward);
				}
			} else {
				result = position;
			}
		}
		return result;
	}

	private DesignPosition performInlineMode(boolean forward) {
		DesignRange range = _viewer.getRangeSelection();

		if (range == null || !range.isValid()) {
			_log.error("invalid range"); //$NON-NLS-1$
			return null;
		}

		DesignPosition position = range.getEndPosition();
		if (position.getContainerPart() instanceof TextEditPart) {
			int length = ((TextEditPart) position.getContainerPart())
					.getTextData().length();
			int newoffset = position.getOffset() + (_forward ? 1 : -1);
			if (newoffset >= 0 && newoffset <= length) {
				DesignPosition newposi = new DesignPosition(position
						.getContainerPart(), newoffset);
				return newposi;
			}
		}
		DesignPosition newpos = EditHelper.moveToNextEditPosition(
				ActionData.KEYBOARD_NAVAGATION, position, forward);
		return newpos;
	}

	private void setRange(DesignPosition newpos) {
		if (_onlyMoveEnd) {
			_viewer.setRangeEndPosition(newpos);
		} else {
			_viewer.setRange(newpos, newpos);
		}
	}

	private DesignPosition getCurrentObjectPosition() {
		DesignRange result = null;
		if (_viewer.isInRangeMode()) {
			result = _viewer.getRangeSelection();
		} else {
			List parts = _viewer.getSelectedEditParts();
			if (parts.size() > 0) {
				EditPart selection = (EditPart) parts.get(0);
				if (selection instanceof SubNodeEditPart) {
					DesignPosition position = new DesignRefPosition(selection,
							_forward);
					result = new DesignRange(position, position);
				}
			}
		}
		return result != null && result.isValid() ? result.getEndPosition()
				: null;
	}
}
