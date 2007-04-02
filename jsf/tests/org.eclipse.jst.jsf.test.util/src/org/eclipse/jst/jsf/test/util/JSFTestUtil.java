package org.eclipse.jst.jsf.test.util;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.internal.net.ProxyData;
import org.eclipse.core.internal.net.ProxyManager;
import org.eclipse.core.net.proxy.IProxyData;
import org.eclipse.core.net.proxy.IProxyService;
import org.eclipse.core.runtime.CoreException;
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
        IProxyService proxy = ProxyManager.getProxyManager();
//        InternetPlugin plugin = InternetPlugin.getInstance();

        if (proxied)
        {
            ProxyData proxyData = new ProxyData(IProxyData.HTTP_PROXY_TYPE);
            proxyData.setHost(proxyHostName);
            proxyData.setPassword(proxyPort);
            try
            {
                proxy.setProxyData(new ProxyData[] {proxyData});
                proxy.setProxiesEnabled(true);
            }
            catch (CoreException ce)
            {
                // TODO: is this recoverable? Maybe should throw up.
                Activator.log("Error setting web proxy.  Tests may fail or take a long time to run", ce);
            }

             // setup local proxy
//            System.setProperty(CacheMessages.WTP_NO_USER_INTERACTION_SYSTEM_PROP, "true");
//            IPreferenceStore prefStore = plugin.getPreferenceStore();
//            prefStore.setValue(InternetPlugin.PREFERENCE_PROXYCHECKED, true);
//            prefStore.setValue(InternetPlugin.PREFERENCE_SOCKSCHECKED, false);
//            prefStore.setValue("http.proxySet", true);
//            prefStore.setValue(InternetPlugin.PREFERENCE_HOSTNAME, proxyHostName);
//            prefStore.setValue(InternetPlugin.PREFERENCE_PORT, proxyPort);
//            plugin.updateProxyProperties();
        }
        else
        {
            proxy.setProxiesEnabled(false);
//            System.setProperty(CacheMessages.WTP_NO_USER_INTERACTION_SYSTEM_PROP, "false");
//            plugin.updateProxyProperties();
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
