package org.craftyserver.server.event.events;

import org.craftyserver.server.server.Server;

/**
 * Event called when a {@link Server} is started
 * 
 * <p> Managed by {@link org.craftyserver.general.event.EventManager EventManager} 
 * initialized in {@link org.craftyserver.craftyserver.main.CraftyServer CraftyServer}.
 * 
 * @author PentagonLP
 */
public class ServerStartEvent extends ServerEvent {
	
	/**
	 * Creates a {@code ServerStartEvent} by taking in a {@link Server} and storing it.
	 * 
	 * @param	server	the {@link Server} to store in the event
	 */
	public ServerStartEvent(Server server) {
		super(server);
	}
	
}
