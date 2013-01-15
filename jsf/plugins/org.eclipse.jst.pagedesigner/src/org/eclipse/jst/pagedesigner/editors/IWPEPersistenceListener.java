package org.eclipse.jst.pagedesigner.editors;

/**
 * Listens for persistence events fired from WPE instances, including
 * save and revert.
 */
public interface IWPEPersistenceListener {
	/**
	 * The type of the persistence event.
	 *
	 */
	public static enum PersistenceEventType {
		/**
		 * WPE was saved
		 */
		SAVED,

		/**
		 * WPE was saved as
		 */
		SAVED_AS,

		/**
		 * WPE was reverted.
		 */
		REVERTED;
	}		

	/**
	 * A persistence event.
	 * 
	 * <p>Not intended to be implemented by clients.</p>
	 */	
	public static interface IPersistenceEvent {		
		/**
		 * @return editor
		 */
		public HTMLEditor getWPEInstance();

		/**
		 * @return EventType
		 */
		public PersistenceEventType getEventType();		
	}

	/**
	 * A persistence event has occurred
	 * @param event 
	 */	
	public void notify(IPersistenceEvent event);

}
