package org.eclipse.jst.jsf.test.util.mock;

import java.io.File;

import org.eclipse.core.internal.registry.ExtensionRegistry;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.spi.IRegistryProvider;
import org.eclipse.core.runtime.spi.RegistryStrategy;

public class MockExtensionRegistryProvider implements IRegistryProvider
{

    public IExtensionRegistry getRegistry()
    {
        return new ExtensionRegistry(new RegistryStrategy(new File[0], new boolean[0]), new Object(), new Object());
    }

}
