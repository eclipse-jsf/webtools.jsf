package org.eclipse.jst.jsf.ui.internal.component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo.ComponentBeanProperty;
import org.eclipse.jst.jsf.ui.internal.JSFUiPlugin;
import org.eclipse.ui.PlatformUI;

/**
 * A table content provider for component attributes.
 * 
 * @author cbateman
 * 
 */
public class ComponentDetailTableProvider implements IStructuredContentProvider
{
    private final static Object[] NO_ELEMENT = new Object[0];

    public void dispose()
    {
        // TODO Auto-generated method stub

    }

    public void inputChanged(final Viewer viewer, final Object oldInput,
            final Object newInput)
    {
        // TODO Auto-generated method stub
    }

    public Object[] getElements(final Object inputElement)
    {
        if (inputElement instanceof ComponentInfo)
        {
            final List<ComponentProperty> tableProperties = new ArrayList();
            final ComponentInfo comp = (ComponentInfo) inputElement;

            final Runnable r = new Runnable()
            {
                public void run()
                {
                    synchronized (comp)
                    {
                        for (final Iterator it = comp.getAttributeNames()
                                .iterator(); it.hasNext();)
                        {
                            final String name = (String) it.next();
                            final ComponentBeanProperty beanProp = comp.getAttribute(name);

                            tableProperties.add(new ComponentProperty(name, beanProp.getValue()));
                        }
                    }
                }
            };

            // if we are on the UI thread then set a timer that interrupts
            // the bean property updater after 10 seconds to prevent deadlock
            // on the comp synchronization monitor
            if (Thread.currentThread() == PlatformUI.getWorkbench()
                    .getDisplay().getThread())
            {
                try
                {
                    final Thread interruptMe = new Thread(r);
                    interruptMe.start();
                    interruptMe.join(10000);
                }
                catch (InterruptedException e)
                {
                    JSFUiPlugin.log(IStatus.ERROR,
                            "Interrupted during bean eval", e);
                }
            }
            // if not on the UI thread, then just run it.
            else
            {
                r.run();
            }

            return tableProperties.toArray();
        }

        // otherwise, no elements
        return NO_ELEMENT;
    }

    /**
     * A value object for a single component name/value pair
     * 
     * @author cbateman
     * 
     */
    public static class ComponentProperty
    {
        private final String _name;
        private final Object _value;

        /**
         * @param name
         * @param value
         */
        public ComponentProperty(final String name, final Object value)
        {
            _name = name;
            _value = value;
        }

        /**
         * @return the property name
         */
        public final String getName()
        {
            return _name;
        }

        /**
         * @return the property value
         */
        public final Object getValue()
        {
            return _value;
        }
    }
}
