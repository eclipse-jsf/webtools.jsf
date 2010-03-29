package org.eclipse.jst.jsf.validation.internal.strategy;

import java.util.Locale;

import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.validation.internal.appconfig.ILocalizedMessage;
import org.eclipse.osgi.util.NLS;
import org.eclipse.wst.validation.internal.core.Message;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;

/**
 * Diagnostic factory for JSF views 
 *
 */
public final class DiagnosticFactory {
	
    /**
     * The id used in the source field of all Diagnostic's created by this factory
     * to uniquely identify tag validation as their source type.
     */
    public final static String SOURCE_ID = "org.eclipse.jst.jsf.validation.tag.Diagnostics";//$NON-NLS-1$

    /**
     * Problem id
     */
    public final static int CONTAINMENT_ERROR_MISSING_VIEW = 1;
    
    /**
     * Problem id
     */
    public final static int CONTAINMENT_ERROR_MISSING_FORM = 2;

    /**
     * Problem id
     */
	public static final int CONTAINMENT_ERROR_MISSING_ANCESTOR = 3;
    

    /**
     * @param tagName 
     * @return message indicating a JSF view tag is missing as an ancestor     
     */
    public static Diagnostic create_CONTAINMENT_ERROR_MISSING_VIEW(final String tagName)
    {
    	return create(CONTAINMENT_ERROR_MISSING_VIEW,
    			NLS.bind(Messages.CONTAINMENT_ERROR_MISSING_VIEW, tagName));                
    }
    
    /**
     * @param tagName 
     * @return message indicating a JSF form tag is missing as an ancestor     
     */
    public static Diagnostic create_CONTAINMENT_ERROR_MISSING_FORM(final String tagName)
    {    	
        return create(CONTAINMENT_ERROR_MISSING_FORM,
               NLS.bind(Messages.CONTAINMENT_ERROR_MISSING_FORM, tagName));
    }
    
    /**  
     * @param tagName 
     * @param missingTag 
     * @return message indicating some tag is missing as an ancestor     
     */
    public static Diagnostic create_CONTAINMENT_ERROR_MISSING_ANCESTOR(final String tagName, final TagIdentifier missingTag)
    {
    	
        return create(CONTAINMENT_ERROR_MISSING_ANCESTOR,
        		NLS.bind(Messages.CONTAINMENT_ERROR_MISSING_ANCESTOR, 
        				new Object[]{ tagName, missingTag.getUri(), missingTag.getTagName()}));
    }

    private static BasicDiagnostic create(final int diagnosticId, final String message)
    {
        final int severity = IMessage.NORMAL_SEVERITY;//ELValidationPreferences.getDefaultSeverity(diagnosticId);
        return new BasicDiagnostic(severity, SOURCE_ID 
                , diagnosticId
                , message
                , null);
    }
	/**
     * Customized localizable message for view validation
     * @author cbateman
     *
     */
    static class MyLocalizedMessage extends Message implements ILocalizedMessage
    {
        private final String _message;
        private final int    _errorCode;

        /**
         * @param severity
         * @param messageText
         * @param targetObject
         * @param errorCode 
         */
        public MyLocalizedMessage(int severity, String messageText, IResource targetObject, int errorCode) {
            this(severity, messageText, (Object) targetObject, errorCode);
        }

        /**
         * @param severity
         * @param messageText
         * @param targetObject
         * @param errorCode 
         */
        private MyLocalizedMessage(int severity, String messageText, Object targetObject, int errorCode) {
            super(JSFCorePlugin.getDefault().getBundle().getSymbolicName(), severity, 
                    messageText);
            _message = messageText;
            setTargetObject(targetObject);
            _errorCode = errorCode;
        }

        /**
         * @return the localized message
         */
        public String getLocalizedMessage() {
            return _message;
        }

        /**
         * @see org.eclipse.wst.validation.internal.core.Message#getText()
         */
        public String getText() {
            return getLocalizedMessage();
        }

        /**
         * @see org.eclipse.wst.validation.internal.core.Message#getText(java.lang.ClassLoader)
         */
        public String getText(ClassLoader cl) {
            return getLocalizedMessage();
        }

        /**
         * @see org.eclipse.wst.validation.internal.core.Message#getText(java.util.Locale)
         */
        public String getText(Locale l) {
            return getLocalizedMessage();
        }

        public String getText(Locale l, ClassLoader cl) {
            return getLocalizedMessage();
        }

        /**
         * @return the error code related to this message
         */
        public int getErrorCode() {
            return _errorCode;
        }


        /**
         * @param offset
         * @return true if this message applies to document offset
         */
        public boolean appliesTo(int offset)
        {
            return (offset >= getOffset() && offset < getOffset()+getLength());
        }
    }


}
