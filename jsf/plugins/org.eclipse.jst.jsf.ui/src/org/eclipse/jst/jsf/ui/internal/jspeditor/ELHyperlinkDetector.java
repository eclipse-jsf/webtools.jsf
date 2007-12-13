package org.eclipse.jst.jsf.ui.internal.jspeditor;

import org.eclipse.jdt.core.IType;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.hyperlink.AbstractHyperlinkDetector;
import org.eclipse.jface.text.hyperlink.IHyperlink;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;
import org.eclipse.jst.jsf.context.symbol.IBeanInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.IBeanMethodSymbol;
import org.eclipse.jst.jsf.context.symbol.IBeanPropertySymbol;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.core.internal.contentassist.el.SymbolInfo;

/** This HyperlinkDetector creates hyperlinks for symbols in JSF EL expressions inside jsp files.
 */
public class ELHyperlinkDetector extends AbstractHyperlinkDetector {

    public IHyperlink[] detectHyperlinks(final ITextViewer textViewer, final IRegion region, final boolean canShowMultipleHyperlinks) {
        final IStructuredDocumentContext context = IStructuredDocumentContextFactory.INSTANCE.getContext(textViewer,
                region.getOffset());
        final Region elRegion = JSPSourceUtil.findELRegion(context);
        if (elRegion != null) {
            final SymbolInfo symbolInfo = JSPSourceUtil.determineSymbolInfo(context, elRegion, region.getOffset());
            if (symbolInfo != null) {
                IHyperlink link = null;
                final Region linkRegion = new Region(symbolInfo.getRelativeRegion().getOffset() + elRegion.getOffset(), symbolInfo.getRelativeRegion().getLength());
                final ISymbol symbol = symbolInfo.getSymbol();
                if (symbol instanceof IBeanInstanceSymbol) {
                    link = createBeanInstanceLink(linkRegion, (IBeanInstanceSymbol) symbol);
                } else if (symbol instanceof IBeanPropertySymbol) {
                    link = createBeanPropertyLink(linkRegion, (IBeanPropertySymbol) symbol);
                } else if (symbol instanceof IBeanMethodSymbol) {
                    link = createMethodLink(linkRegion, (IBeanMethodSymbol) symbol);
                }
                if (link != null) {
                    return new IHyperlink[]{link};
                }
            }
        }
        return null;
    }

    private IHyperlink createBeanInstanceLink(final Region region, final IBeanInstanceSymbol symbol) {
        if (symbol.isTypeResolved()) {
            final IType type = symbol.getJavaTypeDescriptor().getType();
            return new JavaElementHyperlink(region, type);
        }
        return null;
    }

    private IHyperlink createBeanPropertyLink(final Region region, final IBeanPropertySymbol symbol) {
        //defer calculation of access method until user click on link (takes too long otherwise):
        return new BeanSuffixHyperlink(region, symbol);
    }

    private IHyperlink createMethodLink(final Region region, final IBeanMethodSymbol symbol) {
        //defer calculation of access method until user click on link (takes too long otherwise):
        return new BeanSuffixHyperlink(region, symbol);
    }

}
