/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http:// www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.jsf.ui.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.gef.EditPart;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.pagedesigner.IJSFConstants;
import org.eclipse.jst.pagedesigner.actions.link.AbstractLinkCreator;
import org.eclipse.jst.pagedesigner.actions.link.LinkUtil;
import org.eclipse.jst.pagedesigner.parts.TextEditPart;
import org.eclipse.jst.pagedesigner.utils.JSPUtil;
import org.eclipse.jst.pagedesigner.utils.StructuredModelUtil;
import org.eclipse.jst.pagedesigner.viewer.DesignPosition;
import org.eclipse.jst.pagedesigner.viewer.DesignRange;

/**
 * @author mengbo
 * @version 1.5
 */
public class JSFCommandLinkCreator extends AbstractLinkCreator
{
    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.actions.link.ILinkCreator#makeLinkElement(org.eclipse.gef.EditPart, org.eclipse.jst.pagedesigner.viewer.DesignRange)
     */
    public Element makeLinkElement(EditPart part, DesignRange range)
    {
        if (part instanceof TextEditPart)
        {
            Text middleNode = LinkUtil.splitDomText(part, range);
            EditPart parent = part.getParent();
            Node parentNode = (Node) parent.getModel();
            Document doc = (parentNode instanceof Document) ? (Document) parentNode : (parentNode.getOwnerDocument());

            IDOMModel model = ((IDOMNode) parentNode).getModel();
            String prefix = JSPUtil.getOrCreatePrefix(model, ITLDConstants.URI_JSF_HTML, "h");
            JSPUtil.getOrCreatePrefix(model, ITLDConstants.URI_JSF_CORE, "f");

            Element commandLink = doc.createElement(IJSFConstants.TAG_COMMANDLINK);
            commandLink.setPrefix(prefix);
            Element outputText = doc.createElement(IJSFConstants.TAG_OUTPUTTEXT);
            outputText.setPrefix(prefix);
            outputText.setAttribute(IJSFConstants.ATTR_VALUE, middleNode.getNodeValue());
            commandLink.appendChild(outputText);

            parentNode.replaceChild(commandLink, middleNode);
            return commandLink;
        }

        return null;

    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.actions.link.ILinkCreator#canExcute(org.eclipse.jst.pagedesigner.viewer.DesignRange)
     */
    public boolean canExcute(DesignRange range)
    {
        DesignPosition startPosition = range.getStartPosition();
        EditPart part = startPosition.getContainerPart();
        IDOMNode node = (IDOMNode) part.getModel();
        IDOMModel model = node.getModel();
        IFile openedFile = StructuredModelUtil.getFileFor(model);
        boolean canSupportJSF = JSPUtil.supportTaglib(ITLDConstants.URI_JSF_HTML, openedFile);
        if (!canSupportJSF)
        {
            return false;
        }

        return super.canExecute(range);
    }

    public String getSourcePreview(EditPart part, DesignRange range)
    {
        if (part instanceof TextEditPart)
        {
            TextEditPart textPart = (TextEditPart) part;
            int[] offsets = textPart.getSelectedRange();

            String displayData = textPart.getTextData();
            String linkExp = displayData.substring(offsets[0], offsets[1]);

            StringBuffer sb = new StringBuffer();
            sb.append("<h:commandLink>\n<h:outputText value=\"");
            sb.append(linkExp);
            sb.append("\">");
            sb.append("</h:outputText>\n</h:commandLink>");
            return sb.toString();
        }
        return null;
    }
}
