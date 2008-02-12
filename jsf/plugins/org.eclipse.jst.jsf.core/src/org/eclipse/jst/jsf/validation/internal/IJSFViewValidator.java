package org.eclipse.jst.jsf.validation.internal;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;

/**
 * A validator for JSF views
 * 
 * @author cbateman
 * 
 */
public interface IJSFViewValidator
{
    /**
     * Validate the entire view file and report an problems using reporter.
     * 
     * @param viewFile
     * @param reporter
     */
    void validateView(final IFile viewFile, final IValidationReporter reporter);

    /**
     * Validate only those regions specified in the viewFile and report problems
     * using reporter.
     * 
     * @param viewFile
     * @param regions
     * @param reporter 
     */
    void validateView(final IFile viewFile,
            final IStructuredDocumentRegion[] regions,
            final IValidationReporter reporter);

    /**
     * Implemented by callers of the view validator to receive notification
     * of problems discovered during validation.
     *
     */
    public interface IValidationReporter
    {
        /**
         * @param problem
         * @param start
         * @param length
         */
        void report(final Diagnostic problem, final int start, final int length);

        /**
         * @param message
         * @deprecated internally by design
         */
        void report(final IMessage message);
    }
    
    /**
     * An empty adapter implementation of IValidationReporter.  All methods
     * are noops.
     * 
     * @author cbateman
     *
     */
    public class ReporterAdapter implements IValidationReporter
    {

        public void report(Diagnostic problem, int start, int length)
        {
            // do nothing
        }

        public void report(IMessage message)
        {
            // do nothing
        }
        
    }
}
