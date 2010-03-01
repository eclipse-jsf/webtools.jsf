package org.eclipse.jst.jsf.common.internal.locator;

import java.util.EventListener;
import java.util.EventObject;

/**
 * @author cbateman
 *
 */
public interface ILocatorChangeListener extends EventListener 
{
	/**
	 * @param event
	 */
	public void changed(final LocatorChangeEvent event);

	/**
	 * @author cbateman
	 *
	 */
	public static class LocatorChangeEvent extends EventObject 
	{
		/**
		 * @param source
		 */
		public LocatorChangeEvent(ILocator source) {
			super(source);
		}

		@Override
		public ILocator getSource() {
			return (ILocator) super.getSource();
		}

		/**
		 * 
		 */
		private static final long serialVersionUID = -7930804700395142768L;
		
	}

}
