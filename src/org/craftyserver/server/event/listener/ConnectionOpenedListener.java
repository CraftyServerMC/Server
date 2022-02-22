package org.craftyserver.server.event.listener;

import org.craftyserver.server.event.events.ConnectionOpenedEvent;
import org.craftyserver.general.event.Listener;

/**
 * Listener Interface implemented by classes which have subscribed to {@link ConnectionOpenedEvent}
 * 
 * <p> Managed by {@link org.craftyserver.general.event.EventManager EventManager} 
 * initialized in {@link org.craftyserver.craftyserver.main.CraftyServer CraftyServer}.
 * 
 * @author PentagonLP
 */
public interface ConnectionOpenedListener extends Listener {
	
	/**
	 * Method called by {@link org.craftyserver.general.event.EventManager EventManager} when {@link ConnectionOpenedEvent} occurs
	 * 
	 * @param	event	the occurred {@link ConnectionOpenedEvent}
	 */
	public void onConnectionOpened(ConnectionOpenedEvent event);
	
}
