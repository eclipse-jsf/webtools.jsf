package org.eclipse.jst.jsf.core.internal.contenttype;

import java.util.regex.Pattern;

/**
 * Content Describer for a "facelet" (document element has an xmlns attribute with a value that
 * matches ".*jsf.*" or ".*faces.*").
 * 
 */
public class ContentDescriberForFacelet extends AbstractContentDescriberForFacelets {

	@Override
	protected Pattern[] getNSValuePatterns() {
		return new Pattern[] {
			Pattern.compile(".*jsf.*"), //$NON-NLS-1$
			Pattern.compile(".*faces.*") //$NON-NLS-1$
		};
	}

}
