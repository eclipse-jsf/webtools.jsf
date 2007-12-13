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

    public String getHoverInfo(ITextViewer textViewer, IRegion hoverRegion) {
        // hoveredSymbol set by getHoverRegion, which is called first
        if (hoveredSymbol instanceof IDescribedInDetail) {
            return ((IDescribedInDetail) hoveredSymbol).getDetailedDescription();
        }
        return null;
    }

    public IRegion getHoverRegion(ITextViewer textViewer, int documentPosition) {
        final IStructuredDocumentContext context = IStructuredDocumentContextFactory.INSTANCE.getContext(textViewer,
                documentPosition);
        hoveredSymbol = null;
        Region elRegion = JSPSourceUtil.findELRegion(context);
        if (elRegion != null) {
            SymbolInfo symbolInfo = JSPSourceUtil.determineSymbolInfo(context, elRegion, documentPosition);
            if (symbolInfo == null) {
                return null;
            }
            final Region relativeRegion = symbolInfo.getRelativeRegion();
            Region symbolRegion = new Region(elRegion.getOffset()
                    + relativeRegion.getOffset(), relativeRegion.getLength());
            hoveredSymbol = symbolInfo.getSymbol();
            return symbolRegion;
        }
        return null;
    }
    
}
