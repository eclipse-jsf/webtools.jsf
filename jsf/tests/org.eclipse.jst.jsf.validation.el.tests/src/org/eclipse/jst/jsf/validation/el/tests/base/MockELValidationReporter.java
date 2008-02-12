/**
 * 
 */
package org.eclipse.jst.jsf.validation.el.tests.base;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jst.jsf.core.tests.validation.MockValidationReporter.ReportedProblem;
import org.eclipse.jst.jsf.validation.internal.IJSFViewValidator.IValidationReporter;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;

public class MockELValidationReporter implements IValidationReporter
{
    final List<ReportedProblem>         _syntaxProblems;
    final List<ReportedProblem>         _semanticProblems;
    
    public MockELValidationReporter()
    {
        _syntaxProblems = new ArrayList<ReportedProblem>();
        _semanticProblems = new ArrayList<ReportedProblem>();
    }

    public void report(Diagnostic problem, int start, int length)
    {
        if (isASyntaxError(problem))
        {
            _syntaxProblems.add(new ReportedProblem(problem, start, length));
        }
        else
        {
            _semanticProblems.add(new ReportedProblem(problem, start, length));
        }
    }

    public List<ReportedProblem> getSyntaxProblems()
    {
        return _syntaxProblems;
    }

    public List<ReportedProblem> getSemanticProblems()
    {
        return _semanticProblems;
    }

    private boolean isASyntaxError(final Diagnostic problem)
    {
        return problem.getCode() == DiagnosticFactory.GENERAL_SYNTAX_ERROR_ID;
    }
    
    public void report(IMessage message)
    {
        throw new UnsupportedOperationException();
    }
}