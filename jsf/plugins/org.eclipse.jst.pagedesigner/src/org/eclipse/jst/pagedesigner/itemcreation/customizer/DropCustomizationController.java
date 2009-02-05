package org.eclipse.jst.pagedesigner.itemcreation.customizer;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.ResolverUtil;
import org.eclipse.jst.jsf.core.internal.tld.TagIdentifierFactory;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.commands.ICustomizableCommand;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.elementedit.ElementEditFactoryRegistry;
import org.eclipse.jst.pagedesigner.elementedit.IElementEdit;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument;

/**
 * Not API -- Do not use
 * 
 * @author cbateman
 *
 */
public final class DropCustomizationController
{
    private final ICustomizableCommand _command;
    private final String _uri;
    private final String _name;
    private final IDOMDocument  _domDocument;
    private final IDOMPosition _domPosition;

    /**
     * @param command
     * @param uri
     * @param name
     * @param domDocument 
     * @param domPosition 
     */
    public DropCustomizationController(final ICustomizableCommand command,
            final String uri, final String name, final IDOMDocument domDocument, final IDOMPosition domPosition)
    {
        _command = command;
        _uri = uri;
        _name = name;
        _domDocument = domDocument;
        _domPosition = domPosition;
    }

    /**
     * @return the result of the customization
     */
    public IStatus performCustomization()
    {
        final TagIdentifier tagId = TagIdentifierFactory.createJSPTagWrapper(
                _uri, _name);
        final IElementEdit elementEdit = ElementEditFactoryRegistry.getInstance()
                .createElementEdit(tagId);

        IStatus status = Status.OK_STATUS;
        if (elementEdit != null)
        {
            final IDropCustomizer customizer = elementEdit.getDropCustomizer(tagId);

            if (customizer != null)
            {
                final IFile file = getFile(_domDocument);
                if (file != null)
                {
                    status = customizer.runCustomizer(file, _domPosition);
                }
                else 
                {
                    PDPlugin.log("Could not find file.", new Exception("Not a real exception")); //$NON-NLS-1$ //$NON-NLS-2$
                    status = Status.CANCEL_STATUS;
                }

                if (status.getSeverity() == IStatus.OK)
                {
                    _command.setCustomizationData(customizer
                            .getDropCustomizationData());
                }
            }
        }
        return status;
    }

    private static IFile getFile(IDOMDocument domDoc)
    {
        final IStructuredDocument sdoc = domDoc.getStructuredDocument();
        if (sdoc != null)
        {
            return ResolverUtil.getFileForDocument(sdoc);
        }
        return null;
    }
}
