package org.eclipse.jst.jsf.ui.internal.jspeditor;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Region;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.ITextRegionContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.core.internal.contentassist.el.ContentAssistParser;
import org.eclipse.jst.jsf.core.internal.contentassist.el.SymbolInfo;
import org.eclipse.jst.jsp.core.internal.regions.DOMJSPRegionContexts;
import org.eclipse.wst.xml.core.internal.regions.DOMRegionContext;

/**
 * Utility class to access EL symbols in jsp file.
 */
public final class JSPSourceUtil {

    private JSPSourceUtil() {
        // utility class, no instances.
    }

    /**Find the Region
     * @param context - the IStructuredDocumentContext
     * @return region of el expression, null if context doesn't point to an el expression
     */
    public static Region findELRegion(final IStructuredDocumentContext context) {
        if (context != null) {
            final ITextRegionContextResolver resolver = IStructuredDocumentContextResolverFactory.INSTANCE
                    .getTextRegionResolver(context);

            if (resolver != null) {
                final String regionType = resolver.getRegionType();

                if (regionType != null
                        && resolver.matchesRelative(new String[] { DOMRegionContext.XML_TAG_ATTRIBUTE_VALUE })) {

                    // if we are in the EL content, then get the current region
                    // text
                    if (DOMJSPRegionContexts.JSP_VBL_CONTENT.equals(regionType)) {
                        return new Region(resolver.getStartOffset(), resolver.getLength());
                    }
                }
            }
        }
        return null;
    }

    /**Determines symbol and symbol region at a given document position
     * @param context - the IStructuredDocumentContext
     * @param elRegion - the region of the el expression to consider
     * @param documentPosition - the document position to get the symbol for
     * @return SymbolInfo
     */
    public static SymbolInfo determineSymbolInfo(final IStructuredDocumentContext context, final Region elRegion,
            final int documentPosition) {
        if (context != null && elRegion != null) {
            try {
                String elText;
                elText = context.getStructuredDocument().get(elRegion.getOffset(), elRegion.getLength());
                final SymbolInfo symbolInfo = ContentAssistParser.getSymbolInfo(context, documentPosition
                        - elRegion.getOffset() + 1, elText);
                return symbolInfo;
            } catch (final BadLocationException e) {
                // well, so we simply have no symbol, no reason to worry (or
                // log...)
                return null;
            }
        }
        return null;
    }
}
