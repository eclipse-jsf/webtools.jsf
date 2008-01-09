package org.eclipse.jst.pagedesigner.itemcreation.command;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.jsf.core.internal.tld.CMUtil;
import org.eclipse.jst.pagedesigner.editors.palette.impl.PaletteElementTemplateHelper;
import org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.TagCreationAttribute;
import org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.TagCreationInfo;
import org.eclipse.jst.pagedesigner.itemcreation.CreationData;
import org.eclipse.jst.pagedesigner.itemcreation.internal.AbstractCreationCommand;
import org.eclipse.wst.xml.core.internal.contentmodel.CMAttributeDeclaration;
import org.eclipse.wst.xml.core.internal.contentmodel.CMElementDeclaration;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.w3c.dom.Element;

/**
 * A command to customize a new tag element
 * <p>
 * <b>Provisional API - subject to change</b>
 * </p>
 * 
 * Clients may sub-class.
 * 
 * @author cbateman
 * 
 */
public class ElementCustomizationCommand extends AbstractCreationCommand
{
    /**
     * the dom model where the element will be added
     */
    protected final IDOMModel _model;
    /**
     * the element to be customized
     */
    protected final Element _element;
    /**
     * the creation data for the new tag
     */
    protected final CreationData _creationData;

    /**
     * @param model
     * @param element
     * @param creationData
     */
    public ElementCustomizationCommand(final IDOMModel model,
            final Element element, final CreationData creationData)
    {
        _model = model;
        _element = element;
        _creationData = creationData;
    }

    @Override
    public void execute()
    {
        applyChildElementCustomization();
        applyAttributeCustomization();
    }

    @Override
    protected boolean prepare()
    {
        // do nothing by default; return true to indicate can execute
        return true;
    }

    /**
     * Override to customize the children of the tag being created
     * 
     * Default implementation uses meta-data to load a template. This is the
     * prefered method for static child values (i.e. those that are not
     * calculated dynamically at runtime).
     */
    protected void applyChildElementCustomization()
    {
        final TagCreationInfo tagCreationInfo = _creationData.getTagCreationInfo();
        if (tagCreationInfo != null)
        {
            PaletteElementTemplateHelper.applyTemplate(_model, _element,
                    _creationData.getTagEntry(), tagCreationInfo);
        }
    }

    /**
     * Add required attributes and default values, if set, to the created tag
     * element
     * 
     * To customize the attributes that get added, use TagCreationInfo metadata
     * 
     * This method is provided as a utility for clients. It is not used by
     * default
     * 
     * @param element
     * @param creationData
     */
    protected final void ensureRequiredAttrs(final Element element,
            final CreationData creationData)
    {
        final CMElementDeclaration ed = CMUtil.getTLDElementDeclaration(creationData
                .getUri(), creationData.getTagName(), creationData.getModel()
                .getDocument().getStructuredDocument());

        if (ed != null)
        {
            for (final Iterator it = ed.getAttributes().iterator(); it.hasNext();)
            {
                final CMAttributeDeclaration attr = (CMAttributeDeclaration) it
                        .next();
                if (attr.getUsage() == CMAttributeDeclaration.REQUIRED
                        && element.getAttribute(attr.getAttrName()) == null)
                {
                    element.setAttribute(attr.getAttrName(), attr
                            .getDefaultValue());
                }
            }
        }
    }

    /**
     * Override to customize the attributes of the tag being created
     * 
     * Default implementation uses meta-data to set the attribut values. This is
     * the prefered method for static attribute values (i.e. those that are not
     * calculated dynamically at runtime).
     * 
     */
    protected void applyAttributeCustomization()
    {
        final TagCreationInfo info = _creationData.getTagCreationInfo();
        if (info != null)
        {
            final EList list = info.getAttributes();
            if (list != null)
            {
                for (final Iterator it = list.iterator(); it.hasNext();)
                {
                    final TagCreationAttribute attr = (TagCreationAttribute) it
                            .next();
                    _element.setAttribute(attr.getId(),
                            (attr.getValue() == null ? "" : attr.getValue()));
                }
            }
        }
    }

    @Override
    public final Command chain(final Command command)
    {
        return super.chain(command);
    }

    @Override
    public final Collection<Element> getResult()
    {
        return Collections.singletonList(_element);
    }
}
