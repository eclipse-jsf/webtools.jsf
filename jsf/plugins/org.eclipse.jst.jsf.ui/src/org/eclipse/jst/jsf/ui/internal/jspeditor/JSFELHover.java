package org.eclipse.jst.jsf.ui.internal.jspeditor;

import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Region;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;
import org.eclipse.jst.jsf.context.symbol.IDescribedInDetail;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.core.internal.contentassist.el.SymbolInfo;

/** This class creates hovers for ISymbols in an el expression that have a detailedDescription.
 */
public class JSFELHover implements ITextHover {

    private ISymbol hoveredSymbol = null;

    public final String getHoverInfo(final ITextViewer textViewer, final IRegion hoverRegion) {
    	return getHoverInfo();
    }

    /**
     * For testing
     *
     * @return the hover info
     */
    protected String getHoverInfo()
    {
        // hoveredSymbol set by getHoverRegion, which is called first
        if (hoveredSymbol instanceof IDescribedInDetail) {
            return ((IDescribedInDetail) hoveredSymbol).getDetailedDescription();
        }
        return null;
    }

    public final IRegion getHoverRegion(final ITextViewer textViewer, final int documentPosition) {
        final IStructuredDocumentContext context = IStructuredDocumentContextFactory.INSTANCE.getContext(textViewer,
                documentPosition);
        return getHoverRegion(context, documentPosition);
    }

    /**
     * For testing
     *
     * @param context
     * @param documentPosition
     * @return the hover region
     */
    protected IRegion getHoverRegion(final IStructuredDocumentContext context, final int documentPosition)
    {
        hoveredSymbol = null;
        final Region elRegion = JSPSourceUtil.findELRegion(context);
        if (elRegion != null) {
            final SymbolInfo symbolInfo = JSPSourceUtil.determineSymbolInfo(context, elRegion, documentPosition);
            if (symbolInfo == null) {
                return null;
            }
            final Region relativeRegion = symbolInfo.getRelativeRegion();
            final Region symbolRegion = new Region(elRegion.getOffset()
                    + relativeRegion.getOffset(), relativeRegion.getLength());
            hoveredSymbol = symbolInfo.getSymbol();
            return symbolRegion;
        }
        return null;
    }
}
