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
package org.eclipse.jst.pagedesigner.actions.link;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.Window;
import org.eclipse.jst.jsf.common.ui.internal.dialogs.CommonWizardDialog;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.parts.TextEditPart;
import org.eclipse.jst.pagedesigner.viewer.DesignPosition;
import org.eclipse.jst.pagedesigner.viewer.DesignRange;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wst.sse.core.internal.provisional.INodeNotifier;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author mengbo
 * @version 1.5
 */
public class MakeLinkAction extends Action {
	private final static String MAKE_LINK = PDPlugin
			.getResourceString("ActionGroup.Submenu.Link"); //$NON-NLS-1$

	private final static String WIZARD_PAGE_TITLE = PDPlugin
			.getResourceString("MakeLinkAction.Wizard.PageTitle"); //$NON-NLS-1$

	private EditPart _editPart;

	private DesignRange _range;

	private String _linkType;

	/**
	 * @param range
	 */
	public MakeLinkAction(DesignRange range) {
		super(MAKE_LINK);
		_range = range;
		_editPart = convertToEditPart(_range);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {
		Map<String, ILinkCreator> map = calAvailableLinkCreator();
		if (map.size() > 1) {
			CreateLinkWizard wizard = new CreateLinkWizard(_editPart, _range,
					map);
			wizard.setPageTitle(WIZARD_PAGE_TITLE);
			CommonWizardDialog wizardDialog = new CommonWizardDialog(
					getShell(), wizard);
			wizardDialog.create();
			if (wizardDialog.open() == Window.OK) {
				_linkType = wizard.getChosenLinkType();
			}
		}
		// else must be html link
		else if (map.size() == 1) {
			Set<Map.Entry<String, ILinkCreator>> set = map.entrySet();
			Iterator<Map.Entry<String,ILinkCreator>> itr = set.iterator();
			while (itr.hasNext()) {
				ILinkCreator creator =  itr.next().getValue();
				_linkType = creator.getLinkIdentifier();
			}
		}

		if (_linkType != null) {
			Request request = new LinkRequest(_linkType, _range);
			Command cmd = _editPart.getCommand(request);
			if (cmd != null && cmd.canExecute()) {
				cmd.execute();
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.IAction#isEnabled()
	 */
	public boolean isEnabled() {
		if (_editPart == null) {
			return false;
		}
		return super.isEnabled();
	}

	private EditPart convertToEditPart(DesignRange range) {
		DesignPosition startPosition = range.getStartPosition();
		EditPart startPart = startPosition.getContainerPart();

		DesignPosition endPosition = range.getEndPosition();
		EditPart endPart = endPosition.getContainerPart();

		if (startPosition == endPosition) {
			return null;
		}

		if (startPart instanceof TextEditPart
				&& endPart instanceof TextEditPart) {
			if ((startPart == endPart)) {
				return startPart;
			}
		} else if (!(startPart instanceof TextEditPart)
				&& !(endPart instanceof TextEditPart)) {
			Node[] startNodeOptions = null;
			startNodeOptions = getSideNodes(startPosition);
			Node[] endNodeOptions = null;
			endNodeOptions = getSideNodes(endPosition);
			Node selectedNode = null;
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					if (startNodeOptions[i] == endNodeOptions[j]) {
						selectedNode = startNodeOptions[i];
						break;
					}
				}
				if (selectedNode != null) {
					break;
				}
			}
			if (selectedNode != null) {
				EditPart part = (EditPart) ((INodeNotifier) selectedNode)
						.getAdapterFor(EditPart.class);
				return part;
			}
		} else {
			if (startPart instanceof TextEditPart) {
				Node[] endNodeOptions = null;
				endNodeOptions = getSideNodes(endPosition);
				if (startPart.getModel() == endNodeOptions[0]
						|| startPart.getModel() == endNodeOptions[1]) {
					return startPart;
				}
			}
			if (endPart instanceof TextEditPart) {
				Node[] startNodeOptions = null;
				startNodeOptions = getSideNodes(startPosition);
				if (endPart.getModel() == startNodeOptions[0]
						|| endPart.getModel() == startNodeOptions[1]) {
					return endPart;
				}
			}
		}

		return null;
	}

	private Node[] getSideNodes(DesignPosition pos) {
		Node[] nodes = new Node[2];

		EditPart part = pos.getContainerPart();
		Node node = (Node) part.getModel();
		NodeList list = node.getChildNodes();

		if (list.getLength() == pos.getOffset()) {
			nodes[0] = list.item(pos.getOffset() - 1);
			nodes[1] = list.item(pos.getOffset() - 1);
		} else if (pos.getOffset() == 0) {
			nodes[0] = list.item(0);
			nodes[1] = list.item(0);
		} else if (pos.getOffset() > 0 && pos.getOffset() < list.getLength()) {
			nodes[0] = list.item(pos.getOffset() - 1);
			nodes[1] = list.item(pos.getOffset());
		}

		return nodes;
	}

	private Shell getShell() {
		if (_editPart != null) {
			IHTMLGraphicalViewer viewer = (IHTMLGraphicalViewer) _editPart
					.getViewer();
			return viewer.getControl().getShell();
		}
		return null;
	}

	private Map<String, ILinkCreator> calAvailableLinkCreator() {
		Map<String, ILinkCreator> map = new HashMap<String, ILinkCreator>();
		List<ILinkCreator> linkCreators = ExtensionReader.getAllLinkHandlers();
		for (ILinkCreator linkCreator : linkCreators) {
			String identifier = linkCreator.getLinkIdentifier();
			boolean canExecute = linkCreator.canExecute(_range);
			if (canExecute) {
				map.put(identifier, linkCreator);
			}
		}
		return map;
	}
}
