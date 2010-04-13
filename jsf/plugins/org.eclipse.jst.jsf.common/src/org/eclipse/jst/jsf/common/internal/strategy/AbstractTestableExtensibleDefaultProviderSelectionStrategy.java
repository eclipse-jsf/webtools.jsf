package org.eclipse.jst.jsf.common.internal.strategy;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.jsf.common.internal.policy.IdentifierOrderedIteratorPolicy;

/**
 * Will select in order the testable, then extension-point, then default strategies.
 * <p>
 * Users should use addTestableStrategy(), addExtensionStrategy(), and addDefaultStrategy() and <b>NOT</b> addStrategy()
 * <p>
 * Users must supply a defaultStrategy at minimum;
 * <p>
 * @param <INPUT>
 * @param <OUTPUT>
 */
public abstract class AbstractTestableExtensibleDefaultProviderSelectionStrategy<INPUT, OUTPUT>
		extends
		IteratorPolicyBasedStrategyComposite<INPUT, OUTPUT, OUTPUT, String, IIdentifiableStrategy<INPUT, OUTPUT, String>> {

	private static final String TEST_STRATEGY_ID 	= "testInjection"; //$NON-NLS-1$
	/**
	 * LocatorProviderStrategy id for extension-pt
	 */
	private static final String EXT_PT_STRATEGY_ID 	= "extensionPointInjection"; //$NON-NLS-1$
	/**
	 * LocatorProviderStrategy id for platform default
	 */
	private static final String DEFAULT_STRATEGY_ID = "platformDefault"; //$NON-NLS-1$
	
	/**
	 * Contructor
	 */
	protected AbstractTestableExtensibleDefaultProviderSelectionStrategy() {
		super(new MyIteratorPolicy());
	}

	@Override
	public OUTPUT getNoResult() {
		return null;
	}

	/**
	 * @param testStrategy
	 */
	public void addTestableStrategy(final ISimpleStrategy<INPUT, OUTPUT> testStrategy) {
		super.addStrategy(new IdententifiableStrategyWrapper(testStrategy, TEST_STRATEGY_ID));
	}
	
	/**
	 * @param extensionStrategy
	 */
	public void addExtensionStrategy(final ISimpleStrategy<INPUT, OUTPUT> extensionStrategy) {
		super.addStrategy(new IdententifiableStrategyWrapper(extensionStrategy, EXT_PT_STRATEGY_ID));
	}
	
	/**
	 * @param defaultStrategy
	 */
	public void addDefaultStrategy(final ISimpleStrategy<INPUT, OUTPUT> defaultStrategy) {
		super.addStrategy(new IdententifiableStrategyWrapper(defaultStrategy, DEFAULT_STRATEGY_ID));
	}
	
	private class IdententifiableStrategyWrapper<INPUT, OUTPUT, String> implements IIdentifiableStrategy<INPUT, OUTPUT, String> {

		private ISimpleStrategy<INPUT, OUTPUT> _innerStrategy;
		private String _id;

		IdententifiableStrategyWrapper(final ISimpleStrategy<INPUT, OUTPUT> innerStrategy, final String id) {
			_innerStrategy = innerStrategy;
			_id = id;
		}
		
		public OUTPUT perform(INPUT input) throws Exception {
			return _innerStrategy.perform(input);
		}

		public OUTPUT getNoResult() {			
			return null;
		}

		public String getId() {
			return _id;
		}

		public java.lang.String getDisplayName() {
			return null;
		}
		
	}
	private static class MyIteratorPolicy extends
			IdentifierOrderedIteratorPolicy<String> {

		private static List<String> _selectionOrder;

		static {
			_selectionOrder = new ArrayList<String>();
			_selectionOrder
					.add(TEST_STRATEGY_ID);
			_selectionOrder
					.add(EXT_PT_STRATEGY_ID);
			_selectionOrder
					.add(DEFAULT_STRATEGY_ID);
		}

		public MyIteratorPolicy() {
			super(_selectionOrder);
			setExcludeNonExplicitValues(true);
		}

	}

}
