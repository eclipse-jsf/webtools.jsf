package org.eclipse.jst.jsf.ui.internal.jspeditor;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.hyperlink.IHyperlink;
import org.eclipse.jst.jsf.common.util.JDTBeanProperty;
import org.eclipse.jst.jsf.context.symbol.IBeanMethodSymbol;
import org.eclipse.jst.jsf.context.symbol.IBeanPropertySymbol;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.context.symbol.internal.impl.JavaUtil;
import org.eclipse.jst.jsf.ui.internal.JSFUiPlugin;
import org.eclipse.ui.IEditorPart;

/**
 * Hyperlink for IBeanPropertySymbol and IBeanMethodSymbol el symbols. This Class only exists
 *  in order to defer determining the (access) method to link to until user actually clicks
 *  on the link, since determining takes too long.
 */
class BeanSuffixHyperlink implements IHyperlink,ITestHyperlink {
	private final IRegion _fRegion;
	private final ISymbol _symbol;

    /**
     * Creates a BeanSuffixHyperlink for a symbol
     * @param region - the region of the hyperlink
     * @param symbol
     */
    public BeanSuffixHyperlink(final IRegion region, final ISymbol symbol) {
        _fRegion = region;
        _symbol = symbol;
    }

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.jface.text.hyperlink.IHyperlink#getHyperlinkRegion()
	 */
	public IRegion getHyperlinkRegion() {
		return _fRegion;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.jface.text.hyperlink.IHyperlink#getTypeLabel()
	 */
	public String getTypeLabel() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.jface.text.hyperlink.IHyperlink#getHyperlinkText()
	 */
	public String getHyperlinkText() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.jface.text.hyperlink.IHyperlink#open()
	 */
	public void open() {
	    final IJavaElement element = determineJavaElement();
	    if (element != null) {
	        try {
	            final IEditorPart editor = JavaUI.openInEditor(element);
	            if (editor != null) {
	                JavaUI.revealInEditor(editor, element);
	            }
	        }
	        catch (final Exception e) {
	            JSFUiPlugin.log(IStatus.WARNING, e.getMessage(), e);
	        }
	    }
	}

    public IJavaElement determineJavaElement() {
        if (_symbol instanceof IBeanPropertySymbol) {
            return determinePropertyElement((IBeanPropertySymbol) _symbol);
        }
        if (_symbol instanceof IBeanMethodSymbol) {
            return JavaUtil.findCorrespondingMethod((IBeanMethodSymbol) _symbol);
        }
        return null;
    }

    private IMethod determinePropertyElement(final IBeanPropertySymbol propertySymbol) {
        final JDTBeanProperty property = JavaUtil.findCorrespondingJDTProperty(propertySymbol);
        if (property != null) {
            if (property.getGetter() != null) {
                return property.getGetter();
            }
            if (property.getSetter() != null) {
                return property.getSetter();
            }
        }
        return null;
    }


}
