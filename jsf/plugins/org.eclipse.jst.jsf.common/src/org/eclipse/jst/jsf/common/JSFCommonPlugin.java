package org.eclipse.jst.jsf.common;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class JSFCommonPlugin extends Plugin {

	/**
	 * Plugin id
	 */
	public static final String PLUGIN_ID = "org.eclipse.jst.jsf.common";

	// The shared instance
	private static JSFCommonPlugin plugin;
	
	/**
	 * The constructor
	 */
	public JSFCommonPlugin() {
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static JSFCommonPlugin getDefault() {
		return plugin;
	}

    /**
     * @param e
     * @param msg
     */
    public static void log(final Exception e, final String msg) {
        final ILog log = getDefault().getLog();

        log.log(new Status(IStatus.ERROR, PLUGIN_ID, IStatus.OK, msg, e));
    }

    /**
     * Logs using the default ILog implementation provided by getLog().
     * 
     * @param severity Severity (IStatus constant) of log entry
     * @param message Human-readable message describing log entry
     * @param ex Throwable instance (can be null)
     */
    public static void log(int severity, String message, Throwable ex) {
        getDefault().getLog().log(new Status(severity, PLUGIN_ID, IStatus.OK, message, ex));
    }

    /**
     * Logs using the default ILog implementation provided by getLog().
     * 
     * @param severity Severity (IStatus constant) of log entry
     * @param message Human-readable message describing log entry
     */
    public static void log(int severity, String message) {
        log(severity, message, null);
    }
}
