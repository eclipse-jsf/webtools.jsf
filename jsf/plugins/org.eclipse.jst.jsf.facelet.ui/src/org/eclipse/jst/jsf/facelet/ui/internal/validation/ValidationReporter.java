package org.eclipse.jst.jsf.facelet.ui.internal.validation;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jst.jsf.ui.internal.validation.ValidationMessageFactory;
import org.eclipse.jst.jsf.validation.internal.IJSFViewValidator;
import org.eclipse.jst.jsf.validation.internal.ValidationPreferences;
import org.eclipse.jst.jsf.validation.internal.strategy.DiagnosticFactory;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.eclipse.wst.validation.internal.provisional.core.IReporter;
import org.eclipse.wst.validation.internal.provisional.core.IValidator;

/*package*/class ValidationReporter implements
        IJSFViewValidator.IValidationReporter
{
    private final IValidator            _validator;
    private final IReporter             _reporter;
    private final IFile                 _file;
    private final ValidationMessageFactory  _factory;


    public ValidationReporter(final IValidator validator,
            final IReporter reporter, final IFile file,
            final ValidationPreferences prefs)
    {
        _validator = validator;
        _reporter = reporter;
        _file = file;
        _factory = new ValidationMessageFactory(prefs);
    }

    public void report(final Diagnostic problem, final int start,
            final int length)
    {
    	if (shouldReportProblem(problem.getCode())) {
	        final IMessage message = _factory.createFromDiagnostic(
	                problem, start, length, _file);
	
	        if ((message.getSeverity() & IMessage.ALL_MESSAGES) != 0)
	        {	        	
	        	_reporter.addMessage(_validator, message);
	        }
    	}
    }

    /**
     * @param problemCode
     * @return filters out problems to be reported by code
     */
    private boolean shouldReportProblem(final int problemCode) {
    	switch (problemCode) {
    		case DiagnosticFactory.CONTAINMENT_ERROR_MISSING_VIEW:
    			return false;
    		default:
    			return true;
    	}
	}

	public void report(IMessage message)
    {
		//not capable of filtering problems by code...   should not be used
        if ((message.getSeverity() & IMessage.ALL_MESSAGES) != 0)
        {
            _reporter.addMessage(_validator, message);
        }
    }
}
