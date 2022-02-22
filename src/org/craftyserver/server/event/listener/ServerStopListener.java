package org.craftyserver.server.event.listener;

import org.craftyserver.server.event.events.ServerStopEvent;
import org.craftyserver.general.event.Listener;

/**
 * Listener Interface implemented by classes which have subscribed to {@link ServerStopEvent}
 * 
 * <p> Managed by {@link org.craftyserver.general.event.EventManager EventManager} 
 * initialized in {@link org.craftyserver.craftyserver.main.CraftyServer CraftyServer}.
 * 
 * @author PentagonLP
 */
public interface ServerStopListener extends Listener {
	
	/**
	 * Method called by {@link org.craftyserver.general.event.EventManager EventManager} when {@link ServerStopEvent} occurs
	 * 
	 * @param	event	the occurred {@link ServerStopEvent}
	 */
	public void onServerStop(ServerStopEvent event);

}
