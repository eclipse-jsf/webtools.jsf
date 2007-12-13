package org.eclipse.jst.jsf.ui.internal.jspeditor;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.hyperlink.IHyperlink;
import org.eclipse.jst.jsf.ui.internal.JSFUiPlugin;
import org.eclipse.ui.IEditorPart;

/**
 * Hyperlink for a IJavaElement to the corresponding java source
 */
class JavaElementHyperlink implements IHyperlink {
	private final IRegion fRegion;
	private final IJavaElement fElement;

	/**Creates a new hyperlink for a given IJavaElement
	 * @param region - region of the hyperlink
	 * @param element - hyperlink links to the source of <code>element</code>
	 */
	public JavaElementHyperlink(final IRegion region, final IJavaElement element) {
		fRegion = region;
		fElement = element;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.jface.text.hyperlink.IHyperlink#getHyperlinkRegion()
	 */
	public IRegion getHyperlinkRegion() {
		return fRegion;
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
		try {
			final IEditorPart editor = JavaUI.openInEditor(fElement);
			if (editor != null) {
				JavaUI.revealInEditor(editor, fElement);
			}
		}
		catch (final Exception e) {
		    JSFUiPlugin.log(IStatus.WARNING, e.getMessage(), e);
		}
	}
}
