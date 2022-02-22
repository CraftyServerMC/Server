package org.craftyserver.server.event.events;

import org.craftyserver.server.server.Server;
import org.craftyserver.general.event.Event;

/**
 * Superclass for all {@link Event Events} that have a {@link Server} as a parameter
 * 
 * @author PentagonLP
 */
public abstract class ServerEvent extends Event {
	
	/**
	 * The stored {@link Server}
	 */
	private final Server server;
	
	/**
	 * Creates a {@code ServerEvent} by taking in a {@link Server} and storing it.
	 * 
	 * @param	server	the {@link Server} to store in the event
	 */
	protected ServerEvent(Server server) {
		this.server = server;
	}
	
	/**
	 * Get the {@link Server} which is stored as a parameter
	 *
	 * @return	the stored {@link Server}
	 */
	public Server getServer() {
		return server;
	}
	
}
