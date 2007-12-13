package org.eclipse.jst.jsf.core.internal.contentassist.el;

import org.eclipse.jface.text.Region;
import org.eclipse.jst.jsf.context.symbol.ISymbol;

/**
 * class contains a symbol and it's region
 *
 */
public class SymbolInfo {
    
    private final ISymbol symbol;
    private final Region relativeRegion;
    
    /**
     * @param symbol
     * @param relativeRegion
     */
    public SymbolInfo(ISymbol symbol, Region relativeRegion) {
        super();
        this.symbol = symbol;
        this.relativeRegion = relativeRegion;
    }

	/**
	 * @return the symbol
	 */
	public ISymbol getSymbol() {
		return symbol;
	}

	/**
	 * @return the relative region
	 */
	public Region getRelativeRegion() {
		return relativeRegion;
	}
}
