package org.craftyserver.server.event.listener;

import org.craftyserver.server.event.events.ConnectionClosedEvent;
import org.craftyserver.general.event.Listener;

/**
 * Listener Interface implemented by classes which have subscribed to {@link ConnectionClosedEvent}
 * 
 * <p> Managed by {@link org.craftyserver.general.event.EventManager EventManager} 
 * initialized in {@link org.craftyserver.craftyserver.main.CraftyServer CraftyServer}.
 * 
 * @author PentagonLP
 */
public interface ConnectionClosedListener extends Listener {
	
	/**
	 * Method called by {@link org.craftyserver.general.event.EventManager EventManager} when {@link ConnectionClosedEvent} occurs
	 * 
	 * @param	event	the occurred {@link ConnectionClosedEvent}
	 */
	public void onConnectionClosed(ConnectionClosedEvent event);
	
}
