package org.craftyserver.server.event.events;

import org.craftyserver.server.server.Server;

/**
 * Event called when a {@link Server} is stopped
 * 
 * <p> Managed by {@link org.craftyserver.general.event.EventManager EventManager} 
 * initialized in {@link org.craftyserver.craftyserver.main.CraftyServer CraftyServer}.
 * 
 * @author PentagonLP
 */
public class ServerStopEvent extends ServerEvent {
	
	/**
	 * Creates a {@code ServerStopEvent} by taking in a {@link Server} and storing it.
	 * 
	 * @param	server	the {@link Server} to store in the event
	 */
	public ServerStopEvent(Server server) {
		super(server);
	}

}
