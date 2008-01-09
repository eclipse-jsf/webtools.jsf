package org.eclipse.jst.jsf.common.runtime.internal;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class JSFCommonRuntimePlugin extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "CommonComponentPlugin";

	// The shared instance
	private static JSFCommonRuntimePlugin plugin;
	
	/**
	 * The constructor
	 */
	public JSFCommonRuntimePlugin() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(final BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(final BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static JSFCommonRuntimePlugin getDefault() {
		return plugin;
	}

    public static void log(final String message, final Throwable t)
    {
        final ILog log = getDefault().getLog();
        final IStatus status = new Status(IStatus.ERROR, PLUGIN_ID, message, t);
        log.log(status);
    }
}
