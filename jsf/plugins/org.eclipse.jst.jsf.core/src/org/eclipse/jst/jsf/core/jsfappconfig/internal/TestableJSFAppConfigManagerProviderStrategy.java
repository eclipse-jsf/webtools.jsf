package org.eclipse.jst.jsf.core.jsfappconfig.internal;

import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.jst.jsf.common.internal.strategy.TestableProjectFactoryStrategy;

/**
 * Injectable test factory provider strategy
 * @param <IJSFAppConfigManagerFactory> 
 *
 */
public class TestableJSFAppConfigManagerProviderStrategy<IJSFAppConfigManagerFactory>
	extends TestableProjectFactoryStrategy<IJSFAppConfigManagerFactory> {

	/**
	 * @param testableFactorySessionKey - project property session key for property value holding testable instance 
	 */
	public TestableJSFAppConfigManagerProviderStrategy(final QualifiedName testableFactorySessionKey) {
		super(testableFactorySessionKey);
	}	

}
