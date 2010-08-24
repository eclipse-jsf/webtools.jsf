package org.eclipse.jst.jsf.test.util.junit4;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates a non-static field declared on a test class (may be any visibility)
 * of type IWorkspaceContext that will have an appropriate instance injected into
 * it.
 *  
 * @author cbateman
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface WorkspaceContext
{
}
