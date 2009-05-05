package org.eclipse.jst.jsf.designtime.resolver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;

/*package*/ class SymbolContextResolverReader
{
    private static List<IAdaptable> _handlers = null;

    /**
     * @return all available handers for the ext-pt.  List is not
     * modifiable
     */
    public static synchronized List<IAdaptable> getAllHandlers() {
        if (_handlers == null) {
            _handlers = readAllHandlers();
        }
        return Collections.unmodifiableList(_handlers);
    }

    private static List<IAdaptable> readAllHandlers()
    {
        final List<IAdaptable> result = new ArrayList<IAdaptable>();
        final IExtensionPoint extensionPoint = Platform.getExtensionRegistry()
                .getExtensionPoint(JSFCorePlugin.PLUGIN_ID,
                        "symbolContextResolverFactory"); //$NON-NLS-1$
        final IExtension[] extensions = extensionPoint.getExtensions();

        for (final IExtension ext : extensions)
        {
            final IConfigurationElement[] elementEditElement = ext
                    .getConfigurationElements();

            for (final IConfigurationElement configurationElement : elementEditElement)
            {
                final IConfigurationElement element = configurationElement;
                if (element.getName().equals(
                        "symbolFactoryDelegate")) //$NON-NLS-1$
                {
                    try
                    {
                        final IAdaptable factory = (IAdaptable) configurationElement
                                .createExecutableExtension("class"); //$NON-NLS-1$
                        if (factory != null)
                        {
                            result.add(factory);
                        }
                        
                    } catch (final CoreException e)
                    {
                        JSFCorePlugin.log("Problem loading element edit extension for "+element.toString(), e); //$NON-NLS-1$
                    }
                }
            }
        }
        return result;
    }

}
