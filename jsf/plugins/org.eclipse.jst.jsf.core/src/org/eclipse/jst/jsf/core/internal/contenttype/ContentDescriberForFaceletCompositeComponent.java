package org.eclipse.jst.jsf.core.internal.contenttype;

import java.util.regex.Pattern;

/**
 * Content Describer for a "facelet composite component" (document element has an xmlns attribute
 * with a value that matches "http://java.sun.com/jsf/composite").
 * 
 */
public class ContentDescriberForFaceletCompositeComponent extends AbstractContentDescriberForFacelets {

	@Override
	protected Pattern[] getNSValuePatterns() {
		return new Pattern[] {
			Pattern.compile("http://java.sun.com/jsf/composite") //$NON-NLS-1$
		};
	}

}
