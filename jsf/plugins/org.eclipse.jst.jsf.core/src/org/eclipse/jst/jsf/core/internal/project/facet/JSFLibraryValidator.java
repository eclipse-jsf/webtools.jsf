package org.eclipse.jst.jsf.core.internal.project.facet;

import static org.eclipse.jst.common.project.facet.core.internal.FacetedProjectFrameworkJavaPlugin.PLUGIN_ID;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jst.common.project.facet.core.libprov.user.KeyClassesValidator;
import org.eclipse.jst.common.project.facet.core.libprov.user.UserLibraryProviderInstallOperationConfig;
import org.eclipse.jst.jsf.core.internal.Messages;

/**
 * Return custom message for JSF Libraries when KeyClassesValidator returns an error status
 */
public class JSFLibraryValidator extends KeyClassesValidator {

	@Override
	public IStatus validate(UserLibraryProviderInstallOperationConfig config) {
		IStatus status = super.validate(config);
		if (status.getSeverity() == IStatus.OK)
			return status;
		
		String message = Messages.JSFLibraryValidator_MISSING_JSF_IMPLEMENTATION_CLASSES;
		return new Status( IStatus.ERROR, PLUGIN_ID, message );

	}

}
