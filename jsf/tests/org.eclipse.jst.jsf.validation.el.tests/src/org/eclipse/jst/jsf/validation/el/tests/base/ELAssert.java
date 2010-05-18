package org.eclipse.jst.jsf.validation.el.tests.base;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.eclipse.core.resources.IFile;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.ITextRegionContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.tests.validation.MockValidationReporter.ReportedProblem;
import org.eclipse.jst.jsf.designtime.resolver.IStructuredDocumentSymbolResolverFactory;
import org.eclipse.jst.jsf.validation.internal.ValidationPreferences;
import org.eclipse.jst.jsf.validation.internal.el.ELExpressionValidator;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;

public class ELAssert extends Assert
{

    /**
     * Asserts that the list of problems contains one whose id == code
     * 
     * @param problems
     * @param code
     */
    public static void assertContainsProblem(List<ReportedProblem> problems, int code)
    {
        ELAssert.assertContainsProblem(problems, code, -1, -1);
    }

    /**
     * @param document
     * @param docPos
     * @return the ELText at docPos in document or null if no such text
     */
    public static String getELText(IStructuredDocument document, int docPos)
    {
        final IStructuredDocumentContext context = 
            IStructuredDocumentContextFactory.INSTANCE.getContext(document, docPos);
        if (context == null)
        {
        	fail("context is null");
        }
        final ITextRegionContextResolver resolver =
            IStructuredDocumentContextResolverFactory.INSTANCE.getTextRegionResolver(context);
        return resolver.getRegionText();
    }

    /**
     * @param document
     * @param docPos
     * @param file
     * @return a new expression validator for docPos in the document
     */
    public static ELExpressionValidator createELValidator(
            IStructuredDocument document, int docPos, IFile file, MockELValidationReporter reporter,
            IStructuredDocumentSymbolResolverFactory factory)
    {
        final String elText = getELText(document, docPos);
        final IStructuredDocumentContext context = 
            IStructuredDocumentContextFactory.INSTANCE.getContext(document, docPos);
        final ValidationPreferences prefs =
            new ValidationPreferences(JSFCorePlugin.getDefault().getPreferenceStore());
        prefs.load();
        
        return new ELExpressionValidator(context, elText, factory, reporter);
    }

    /**
     * Uses assertSyntaxProblems with max severity of error.
     * 
     * @param document
     * @param docPos
     * @param file
     * @param expectedProblems
     * @return the list of found syntax problems
     */
    public static List<ReportedProblem> assertSyntaxError(IStructuredDocument document, 
            IStructuredDocumentSymbolResolverFactory factory,
            int docPos, 
            IFile file, 
            int expectedProblems)
    {
        return assertSyntaxProblems(document, factory, docPos, file, expectedProblems, IMessage.HIGH_SEVERITY/* "high" is Warning for some reason*/);
    }

    /**
     * Uses assertSyntaxProblems with max severity of warning.
     * 
     * @param document
     * @param docPos
     * @param file
     * @param expectedProblems
     * @return the list of syntax problems
     */
    public static List<ReportedProblem> assertSyntaxWarning(IStructuredDocument document, 
            IStructuredDocumentSymbolResolverFactory factory,
    
                                          int docPos, 
                                          IFile file, 
                                          int expectedProblems)
    {
        return assertSyntaxProblems(document, factory, docPos, file, expectedProblems, IMessage.NORMAL_SEVERITY/* "normal" is Warning for some reason*/);
    }

    /**
     * Checks the el expression in document at docPos in file. Asserts
     * that there exactly the number of expectedProblems expected and that
     * the highest error severity is expectedMaxSeverity.  Returns the list
     * of syntax errors
     * 
     * @param document
     * @param docPos
     * @param file
     * @param expectedProblems
     * @param expectedMaxSeverity
     * @return the (possibly empty) list of problems
     */
    public static List<ReportedProblem> assertSyntaxProblems(IStructuredDocument document, 
                                          IStructuredDocumentSymbolResolverFactory factory,
                                          int docPos, 
                                          IFile file, 
                                          int expectedProblems,
                                          int expectedMaxSeverity)
    {
        final MockELValidationReporter reporter = new MockELValidationReporter();
    
        final ELExpressionValidator validator = 
            createELValidator(document, docPos, file, reporter, factory);
        validator.validateXMLNode();
    
        final List<ReportedProblem> problems = reporter.getSyntaxProblems();
        assertEquals(expectedProblems, problems.size());
        int worstSeverity = 0;
    
        for (final ReportedProblem problem : problems)
        {
            // for some reason, the number values are lower for higher severity
            // constants
            worstSeverity = maxSeverity(worstSeverity, problem.getSeverity());
        }
    
        
        assertEquals(expectedMaxSeverity, worstSeverity);
        
        return problems;
    }

    /**
     * Asserts that the provided expression generates no problem diagnostics 
     * whatsever and that the resolved type is as expected
     * 
     * @param document
     * @param docPos
     * @param file
     * @param expectedSignature
     */
    public static void assertNoError(IStructuredDocument document,
            IStructuredDocumentSymbolResolverFactory factory, int docPos,
            IFile file, String expectedSignature)
    {
        assertNoError(document, factory, docPos, file, expectedSignature, -1);
    }

    /**
     * Asserts that the provided expression generates no problem diagnostics
     * whatsever and that the resolved type is as expected and checks
     * assignability if value positive, non-zero.
     * 
     * @param document
     * @param docPos
     * @param file
     * @param expectedSignature
     * @param assignability
     */
    public static void assertNoError(IStructuredDocument document,
            IStructuredDocumentSymbolResolverFactory factory, int docPos,
            IFile file, String expectedSignature, int assignability)
    {
        final MockELValidationReporter reporter = new MockELValidationReporter();
    
        ELExpressionValidator validator = createELValidator(document, docPos,
                file, reporter, factory);
        validator.validateXMLNode();
        assertEquals(0, reporter.getSyntaxProblems().size());
        assertEquals(0, reporter.getSemanticProblems().size());
    
        if (expectedSignature != null)
        {
            assertNotNull(validator.getExpressionType());
            assertEquals(expectedSignature, validator.getExpressionType()
                    .getSignatures()[0]);
        }
    
        if (assignability >= 0)
        {
            assertEquals(assignability, validator.getExpressionType()
                    .getAssignmentTypeMask());
        }
    }

    /**
     * Asserts that the provided expression generates one or more problem 
     * diagnostics of which the most severe is of ERROR severity and that
     * the total number expected is returned 
     * Asserts also that no syntax errors are present.
     * 
     * @param document
     * @param docPos
     * @param file
     * @param expectedSignature
     * @param expectedProblems 
     * @return the list of semantic warnings
     */
    public static List<ReportedProblem> assertSemanticError(
            IStructuredDocument document,
            IStructuredDocumentSymbolResolverFactory factory, int docPos,
            IFile file, String expectedSignature, int expectedProblems)
    {
        return assertSemanticProblems(document, factory, docPos, file,
                expectedSignature, expectedProblems, IMessage.HIGH_SEVERITY/*
                                                                            * "high"
                                                                            * is
                                                                            * Error
                                                                            * for
                                                                            * some
                                                                            * reason
                                                                            */);
    }

    /**
     * Asserts that the provided expression generates one or more problem 
     * diagnostics of which the most severe is of WARNING severity and that
     * the total number expected is returned 
     * Asserts also that no syntax errors are present.
     * 
     * @param document
     * @param docPos
     * @param file
     * @param expectedSignature
     * @param expectedProblems 
     * @return the list of semantic warnings
     */
    public static List<ReportedProblem> assertSemanticWarning(
            IStructuredDocument document,
            IStructuredDocumentSymbolResolverFactory factory, int docPos,
            IFile file, String expectedSignature, int expectedProblems)
    {
        return assertSemanticProblems(document, factory, docPos, file,
                expectedSignature, expectedProblems, IMessage.NORMAL_SEVERITY/*
                                                                              * "normal"
                                                                              * is
                                                                              * Warning
                                                                              * for
                                                                              * some
                                                                              * reason
                                                                              */);
    }

    /**
     * Asserts that the provided expression generates one or more problem 
     * diagnostics of which the most severe is of INFO (LOW) severity and that
     * the total number expected is returned .
     * Asserts also that no syntax errors are present.
     * 
     * @param document
     * @param docPos
     * @param file
     * @param expectedSignature
     * @param expectedProblems 
     * @return the list of semantic warnings
     */
    public static List<ReportedProblem> assertSemanticInfo(
            IStructuredDocument document,
            IStructuredDocumentSymbolResolverFactory factory,
            int docPos, IFile file, String expectedSignature,
            int expectedProblems)
    {
        return assertSemanticProblems(document, factory, docPos, file,
                expectedSignature, expectedProblems, IMessage.LOW_SEVERITY/*
                                                                              * "low"
                                                                              * is
                                                                              * Warning
                                                                              * for
                                                                              * some
                                                                              * reason
                                                                              */);
    }

    /**
     * @param document
     * @param docPos
     * @param file
     * @param expectedSignature
     * @param expectedProblems
     * @param expectedMaxSeverity
     * @return the list of semantic problems found
     */
    public static List<ReportedProblem> assertSemanticProblems(final IStructuredDocument document, 
            IStructuredDocumentSymbolResolverFactory factory,
    
                                          final int docPos, 
                                          final IFile file, 
                                          final String expectedSignature, 
                                          final int expectedProblems,
                                          final int expectedMaxSeverity)
    {
        final MockELValidationReporter reporter = new MockELValidationReporter();
    
        final ELExpressionValidator validator = 
                createELValidator(document, docPos, file, reporter, factory);
        validator.validateXMLNode();
    
        if (expectedSignature != null
                && validator.getExpressionType() != null)
        {
            assertEquals(expectedSignature, validator.getExpressionType().getSignatures()[0]);
        }
    
        assertEquals(0, reporter.getSyntaxProblems().size());
        final List<ReportedProblem> problems = reporter.getSemanticProblems();
        assertEquals(expectedProblems, problems.size());
        int worstSeverity = 0;
    
        for (final ReportedProblem problem : problems)
        {
            // for some reason, the number values are lower for higher severity
            // constants
            worstSeverity = maxSeverity(worstSeverity, problem.getSeverity());
        }
    
        assertEquals(expectedMaxSeverity, worstSeverity);
    
        return problems;
    }

    /**
     * Asserts that the list of problems contains one whose id == code
     * If startPos > -1, also checks the offset and length on the matching
     * problem against startPos and length
     * 
     * @param problems
     * @param code
     * @param startPos
     * @param length
     */
    public static void assertContainsProblem(List<ReportedProblem> problems, int code, int startPos, int length)
    {
        final Set<Integer>  probsFound = new HashSet<Integer>();
        
        for (final ReportedProblem problem : problems)
        {
            probsFound.add(new Integer(problem.getErrorCode()));
            if (problem.getErrorCode() == code)
            {
                assertTrue("Offset of message must be >= 0", problem.getOffset()>=0);
                assertTrue("Length of message marker must be >=0", problem.getLength()>=0);
                
                if (startPos >= 0)
                {
                    assertEquals("Offset must be == startPos", startPos, problem.getOffset());
                    assertEquals("Length must be == length", problem.getLength(), length);
                }
    
                // found the required code, so exit without throwing
                // any error assertions
                return;
            }
        }
        // if we reach this point then we have not found the asserted
        // error code
        assertTrue("Expected find error code matching "+code+" found "+probsFound.toString(), false);
    }

    static int maxSeverity(int sev1, int sev2)
    {
        if (sev1 == 0)
        {
            return sev2;
        }
        else if (sev2 == 0)
        {
            return sev1;
        }
        else
        {
            // if both are non-0, then the most sever is the lowest value
            return Math.min(sev1, sev2);
        }
    }
}
