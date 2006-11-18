package org.eclipse.jst.jsf.test.util;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.wst.internet.cache.internal.CacheMessages;
import org.eclipse.wst.internet.internal.proxy.InternetPlugin;
import org.eclipse.wst.validation.internal.ConfigurationManager;
import org.eclipse.wst.validation.internal.GlobalConfiguration;
import org.osgi.framework.Bundle;

/**
 * Test utility methods
 * 
 * @author cbateman
 *
 */
public class JSFTestUtil
{
    /**
     * Used to turn off build validation to speed up testing
     * 
     * @param isEnabled
     * @throws InvocationTargetException 
     * @throws InvocationTargetException
     */
    public static void setValidationEnabled(boolean isEnabled) throws InvocationTargetException
    {
        final GlobalConfiguration config = new GlobalConfiguration(ConfigurationManager.getManager().getGlobalConfiguration());
        config.setDisableAllValidation(!isEnabled);
        config.passivate();
        config.store();
    }
    
    /**
     * @param proxied 
     * @param proxyHostName 
     * @param proxyPort 
     */
    public static void setInternetProxyPreferences(final boolean proxied, final String proxyHostName, final String proxyPort)
    {
        InternetPlugin plugin = InternetPlugin.getInstance();

        if (proxied)
        {
            // setup local proxy
            System.setProperty(CacheMessages.WTP_NO_USER_INTERACTION_SYSTEM_PROP, "true");
            IPreferenceStore prefStore = plugin.getPreferenceStore();
            prefStore.setValue( InternetPlugin.PREFERENCE_PROXYCHECKED, true);
            prefStore.setValue( InternetPlugin.PREFERENCE_SOCKSCHECKED, false );
            prefStore.setValue("http.proxySet", true);
            prefStore.setValue(InternetPlugin.PREFERENCE_HOSTNAME, proxyHostName);
            prefStore.setValue(InternetPlugin.PREFERENCE_PORT, proxyPort);
            plugin.updateProxyProperties();
        }
        else
        {
            System.setProperty(CacheMessages.WTP_NO_USER_INTERACTION_SYSTEM_PROP, "false");
            plugin.updateProxyProperties();
        }
    }
    
    /**
     * Loads the source file in bundle called fileName into the jdtTestEnvironment
     * under srcFolderName/packageName.beanClassName
     * 
     * @param bundle
     * @param fileName
     * @param beanClassName
     * @param srcFolderName
     * @param packageName
     * @param jdtTestEnvironment
     * @throws Exception
     */
    public static void loadSourceClass(final Bundle bundle, 
                                       final String fileName, 
                                       final String beanClassName,
                                       final String srcFolderName,
                                       final String packageName,
                                       final JDTTestEnvironment jdtTestEnvironment) throws Exception
    {
        TestFileResource codeRes = new TestFileResource();
        codeRes.load(bundle, fileName);
        String code = codeRes.toString();
        jdtTestEnvironment.addSourceFile(srcFolderName, packageName, beanClassName, code);
    }
}
