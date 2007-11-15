/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.validation.internal.appconfig;

import java.text.MessageFormat;
import java.util.Locale;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.wst.validation.internal.core.Message;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;

/**
 * Factory for constructing diagnostic objects for AppConfig file validation
 * 
 * @author cbateman
 *
 */
public final class DiagnosticFactory 
{
    /**
     * Problem id
     */
    public final static int EL_EXPR_MUST_BE_IN_HASH_BRACES_ID = 0;
    
    /**
     * Problem id
     */
    public final static int SYNTAX_ERROR_IN_EL_ID = 1;
    
    /**
     * Problem id
     */
    public final static int CANNOT_FIND_CLASS_NAME_ID = 2;
    
    /**
     * Problem id
     */
    public final static int FULLY_QUALIFIED_NAME_MUST_BE_A_CLASS_ID = 3;
    
    /**
     * Problem id
     */
    public final static int CLASS_MUST_BE_INSTANCE_OF_ID = 4;
    
    /**
     * Problem id
     */
    public final static int CLASS_MUST_BE_CONCRETE_ID = 5;
    
    /**
     * TODO:
     */
    public final static int CLASS_MUST_HAVE_DEFAULT_OR_ADAPTER_CONSTRUCTOR_ID = 6;

    /**
     * Problem id
     */
    public final static int API_DEPRECATED_AFTER_VERSION_ID = 7;
    
    /**
     * Problem id
     */
    public final static int BEAN_PROPERTY_NOT_FOUND_ID = 8;
    
    /**
     * Problem id
     */
    public final static int MUST_BE_A_VALID_JAVA_IDENT_ID = 9;
    
    /**
     * Problem id
     */
    public final static int BEAN_SCOPE_NOT_VALID_ID = 10;
    
    /**
     * Problem id
     */
    public final static int MAP_ENTRIES_CAN_ONLY_BE_SET_ON_MAP_TYPE_ID = 11;
    
    /**
     * Problem id
     */
    public final static int LIST_ENTRIES_CAN_ONLY_BE_SET_ON_LIST_TYPE_ID = 12;
    
    /**
     * Problem id
     */
    public final static int API_NOT_AVAILABLE_BEFORE_VERSION_ID = 13;
    
    /**
     * Problem id
     */
    public final static int APP_CONFIG_IS_NEWER_THAN_JSF_VERSION_ID = 14;
    /**
     * Problem id
     */
    public final static int APP_CONFIG_IS_OLDER_THAN_JSF_VERSION_ID = 15;
    
    /**
     * Problem id
     */
    public final static int LOCALE_FORMAT_NOT_VALID_ID = 16;
    
    /**
     * @return message indicating text that should be EL was
     * not found sorrounded in #{} values
     */
    public static IMessage create_EL_EXPR_MUST_BE_IN_HASH_BRACES()
    {
        return new MyLocalizedMessage(IMessage.NORMAL_SEVERITY,
                Messages.EL_EXPR_MUST_BE_IN_HASH_BRACES_ID,
                null, EL_EXPR_MUST_BE_IN_HASH_BRACES_ID);
    }
    
    /**
     * @return message indicating that EL was found that
     * was empty or did not parse
     */
    public static IMessage create_SYNTAX_ERROR_IN_EL()
    {
        return new MyLocalizedMessage(IMessage.NORMAL_SEVERITY,
                Messages.SYNTAX_ERROR_IN_EL_ID,
                null, SYNTAX_ERROR_IN_EL_ID);
    }
    
    /**
     * @param className
     * @return message indicating that className can't be found as 
     * a valid fully qualified class name
     */
    public static IMessage create_CANNOT_FIND_CLASS_NAME(final String className)
    {
        return new MyLocalizedMessage(IMessage.NORMAL_SEVERITY,
                MessageFormat.format(Messages.CANNOT_FIND_CLASS_NAME_ID,new Object[]{className}),
                null, CANNOT_FIND_CLASS_NAME_ID);
    }
    
    /**
     * @param fullyQualifiedName
     * @return the error message for a fullyQualifiedName that resolves to a type
     * that is not a class (i.e. an interface or enum) but is expected to be
     */
    public static IMessage create_FULLY_QUALIFIED_NAME_MUST_BE_A_CLASS(final String fullyQualifiedName)
    {
        return new MyLocalizedMessage(IMessage.NORMAL_SEVERITY,
                MessageFormat.format(Messages.FULLY_QUALIFIED_NAME_MUST_BE_A_CLASS_ID
                        ,new Object[]{fullyQualifiedName})
                        ,null, FULLY_QUALIFIED_NAME_MUST_BE_A_CLASS_ID);
    }

    /**
     * @param className
     * @param extensionType 
     * @param expectedSuper
     * @return an error when a class name must be of type expectedSuper
     */
    public static IMessage create_CLASS_MUST_BE_INSTANCE_OF(final String className, 
                                                            final String extensionType,
                                                            final String expectedSuper)
    {
        return new MyLocalizedMessage(IMessage.NORMAL_SEVERITY,
                MessageFormat.format(Messages.CLASS_MUST_BE_INSTANCE_OF_ID
                        ,new Object[]{className, extensionType, expectedSuper})
                        ,null, CLASS_MUST_BE_INSTANCE_OF_ID);
    }
    
    
    /**
     * @param className
     * @return error indicating that className must specify a class
     * that is concrete
     */
    public static IMessage create_CLASS_MUST_BE_CONCRETE(final String className)
    {
        return new MyLocalizedMessage(IMessage.NORMAL_SEVERITY,
                MessageFormat.format(Messages.CLASS_MUST_BE_CONCRETE_ID
                        ,new Object[]{className})
                        ,null, CLASS_MUST_BE_CONCRETE_ID);
    }
    
    /**
     * @param apiName -- name of deprecated API
     * @param afterVersion -- version after which deprecation is effective
     * @param useInstead -- name of API to use instead
     * @return an error indicating the use of a deprecated API
     */
    public static IMessage create_API_DEPRECATED_AFTER_VERSION_ID(final String apiName, final String afterVersion, final String useInstead)
    {
        return new MyLocalizedMessage(IMessage.NORMAL_SEVERITY,
                MessageFormat.format(Messages.API_DEPRECATED_AFTER_VERSION_ID
                        ,new Object[]{apiName, afterVersion, useInstead})
                        ,null, API_DEPRECATED_AFTER_VERSION_ID);
    }
    
    /**
     * @param propertyName
     * @param beanClassName
     * @return an error indicating that the propertyName was not
     * found on beanClassName
     */
    public static IMessage create_BEAN_PROPERTY_NOT_FOUND(final String propertyName, final String beanClassName)
    {
        return new MyLocalizedMessage(IMessage.NORMAL_SEVERITY,
                MessageFormat.format(Messages.BEAN_PROPERTY_NOT_FOUND_ID
                        ,new Object[]{propertyName, beanClassName})
                        ,null, BEAN_PROPERTY_NOT_FOUND_ID);
            
    }
    
    
    /**
     * @param nameOfId
     * @return an error indicating that some id must be a valid
     * Java identifier
     */
    public static IMessage create_MUST_BE_A_VALID_JAVA_IDENT(final String nameOfId)
    {
        return new MyLocalizedMessage(IMessage.NORMAL_SEVERITY,
            MessageFormat.format(Messages.MUST_BE_A_VALID_JAVA_IDENT_ID
                ,new Object[]{nameOfId})
                ,null, MUST_BE_A_VALID_JAVA_IDENT_ID);    
    }
    
    /**
     * @return an error indicating an invalid bean scope enum value
     */
    public static IMessage create_BEAN_SCOPE_NOT_VALID()
    {
        return new MyLocalizedMessage(IMessage.NORMAL_SEVERITY,
            Messages.BEAN_SCOPE_NOT_VALID_ID
            , null, BEAN_SCOPE_NOT_VALID_ID);    
    }
    
    /**
     * @param targetName 
     * @return an error indicating that a map entry is being set on a target
     * object that is not a java.util.Map
     */
    public static IMessage create_MAP_ENTRIES_CAN_ONLY_BE_SET_ON_MAP_TYPE(String targetName)
    {
        return new MyLocalizedMessage(IMessage.NORMAL_SEVERITY,
            MessageFormat.format(Messages.MAP_ENTRIES_CAN_ONLY_BE_SET_ON_MAP_TYPE_ID,
                                 new Object[]{targetName}),
            null, MAP_ENTRIES_CAN_ONLY_BE_SET_ON_MAP_TYPE_ID);
    }

    /**
     * @param targetName 
     * @return an error indicating that a list entry is being set on a target
     * object that is not a java.util.List
     */
    public static IMessage create_LIST_ENTRIES_CAN_ONLY_BE_SET_ON_LIST_TYPE(String targetName)
    {
        return new MyLocalizedMessage(IMessage.NORMAL_SEVERITY,
            MessageFormat.format(Messages.LIST_ENTRIES_CAN_ONLY_BE_SET_ON_LIST_TYPE_ID,
                                 new Object[]{targetName}),
            null, LIST_ENTRIES_CAN_ONLY_BE_SET_ON_LIST_TYPE_ID);
    }
    
    /**
     * @param apiName
     * @param beforeVersion
     * @param useInstead
     * @return a diagnostic indicating that an API is being used that is not yet
     * available in the current JSF version.
     */
    public static IMessage create_API_NOT_AVAILABLE_BEFORE_VERSION(final String apiName, final String beforeVersion, final String useInstead)
    {
        return new MyLocalizedMessage(IMessage.NORMAL_SEVERITY,
            MessageFormat.format(Messages.API_NOT_AVAILABLE_BEFORE_VERSION_ID
                ,new Object[]{apiName, beforeVersion, useInstead})
                ,null, API_NOT_AVAILABLE_BEFORE_VERSION_ID);
    }
    
    /**
     * @param file
     * @return a diagnostic indicating that the app config model in use is too
     * new for the selected JSF runtime version.
     */
    public static IMessage create_APP_CONFIG_IS_NEWER_THAN_JSF_VERSION(IFile file)
    {
        final IMessage message = new MyLocalizedMessage(IMessage.HIGH_SEVERITY,
            Messages.APP_CONFIG_IS_NEWER_THAN_JSF_VERSION_ID
                ,null, APP_CONFIG_IS_NEWER_THAN_JSF_VERSION_ID);
        message.setTargetObject(file);
        return message;
    }
    
    /**
     * @param file
     * @param appConfigVersion 
     * @param projectVersion 
     * @return a diagnostic indicating that the app config model in use is older
     * than the selected JSF runtime version.
     */
    public static IMessage create_APP_CONFIG_IS_OLDER_THAN_JSF_VERSION(IFile file, String appConfigVersion, String projectVersion)
    {
        final IMessage message = new MyLocalizedMessage(IMessage.NORMAL_SEVERITY,
            MessageFormat.format(
                Messages.APP_CONFIG_IS_OLDER_THAN_JSF_VERSION_ID,
                    new Object[] {appConfigVersion, projectVersion})
            ,null, APP_CONFIG_IS_OLDER_THAN_JSF_VERSION_ID);
        message.setTargetObject(file);
        return message;
    }
    
    /**
     * @return a diagnostic indicating that the locale format does
     * not match what is expected
     */
    public static IMessage create_LOCALE_FORMAT_NOT_VALID()
    {
        IMessage message = new MyLocalizedMessage(IMessage.NORMAL_SEVERITY,
                    Messages.LOCALE_FORMAT_NOT_VALID_ID
                ,null, LOCALE_FORMAT_NOT_VALID_ID);
        return message;
    }
    
    /**
     * Customized localizable message for app config validation
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
