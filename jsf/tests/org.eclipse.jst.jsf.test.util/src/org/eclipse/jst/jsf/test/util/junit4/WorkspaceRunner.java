package org.eclipse.jst.jsf.test.util.junit4;

import java.io.File;
import java.lang.reflect.Field;

import junit.framework.Assert;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.mock.IWorkspaceContext;
import org.eclipse.jst.jsf.test.util.mock.MockWorkspaceContext;
import org.eclipse.osgi.internal.loader.ModuleClassLoader;
import org.junit.Rule;
import org.junit.rules.MethodRule;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.osgi.framework.Bundle;

/**
 * This class is not operable
 * 
 * Bug https://bugs.eclipse.org/bugs/show_bug.cgi?id=343669
 * disables this.  Need to find way of fixing this tracked by 
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=343672
 *
 */
@SuppressWarnings("deprecation")
public class WorkspaceRunner extends BlockJUnit4ClassRunner
{
    @Rule
    public MethodRule rule = new WorkspaceContextInjector();

    public interface IWorkspaceContextFactory
    {
        IWorkspaceContext createContext();
    }

    public WorkspaceRunner(final Class<?> klass) throws InitializationError
    {
        super(klass);
    }
//
//  Removed: https://bugs.eclipse.org/bugs/show_bug.cgi?id=343669
//	@Override  
//    protected List<MethodRule> rules(final Object test)
//    {
//        final List<MethodRule> rules = super.rules(test);
//        rules.add(new WorkspaceContextInjector());
//        return rules;
//    }

    public static class WorkspaceContextInjector implements MethodRule
    {
        protected IWorkspaceContext before(final FrameworkMethod method,
                final Object target) throws Throwable
        {
            final Class<?> declaringClass = method.getMethod()
                    .getDeclaringClass();
            final ClassLoader classLoader = declaringClass.getClassLoader();
            final Field[] declaredFields = declaringClass.getDeclaredFields();
            final IWorkspaceContext context = Platform.isRunning() ? new RealWorkspaceContext()
                    : new MockWorkspaceContext();
            context.init();
            File baseLoc = null;
            if (Platform.isRunning() && classLoader instanceof ModuleClassLoader)
            {
                final Bundle bundle = ((ModuleClassLoader) classLoader).getBundle();
                final IPath absolutePath = JSFTestUtil.getAbsolutePath(bundle, "/");
                baseLoc = absolutePath.toFile();
            } else
            {
                baseLoc = new File(".").getAbsoluteFile();
            }
            Assert.assertTrue(baseLoc.isDirectory());
            for (final Field field : declaredFields)
            {
                final WorkspaceContext annotation = field
                        .getAnnotation(WorkspaceContext.class);
                if (annotation != null)
                {
                    field.setAccessible(true);
                    field.set(target, context);
                }
                final TestDataBaseLocation locAnnotation = field
                        .getAnnotation(TestDataBaseLocation.class);
                if (locAnnotation != null)
                {
                    field.setAccessible(true);
                    field.set(target, baseLoc);
                }
            }
            return context;
        }

        protected void after(final IWorkspaceContext context) throws Exception
        {
            context.dispose();
        }

        public Statement apply(final Statement base,
                final FrameworkMethod method, final Object target)
        {
            return new Statement()
            {
                @Override
                public void evaluate() throws Throwable
                {
                    final IWorkspaceContext context = before(method, target);
                    try
                    {
                        base.evaluate();
                    } finally
                    {
                        after(context);
                    }
                }
            };
        }
    }
}
