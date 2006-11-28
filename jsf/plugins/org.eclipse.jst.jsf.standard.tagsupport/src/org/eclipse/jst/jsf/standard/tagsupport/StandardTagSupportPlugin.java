package org.eclipse.jst.jsf.standard.tagsupport;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class StandardTagSupportPlugin extends AbstractUIPlugin {

	/**
	 * the plugin id
	 */
	public static final String PLUGIN_ID = "org.eclipse.jst.jsf.standard.tagsupport";

	// The shared instance
	private static StandardTagSupportPlugin plugin;
	
	/**
	 * The constructor
	 */
	public StandardTagSupportPlugin() {
        // do nothing
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
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
	public static StandardTagSupportPlugin getDefault() {
		return plugin;
	}

}
