package org.craftyserver.server.event.events;

import org.craftyserver.server.connection.Connection;

/**
 * Event called when a {@link Connection} to a client is opened.
 * 
 * <p> Managed by {@link org.craftyserver.general.event.EventManager EventManager} 
 * initialized in {@link org.craftyserver.craftyserver.main.CraftyServer CraftyServer}.
 * 
 * @author PentagonLP
 */
public class ConnectionOpenedEvent extends ConnectionEvent {
	
	/**
	 * Creates a {@code ConnectionOpenedEvent} by taking in a {@link Connection} and storing it.
	 * 
	 * @param	connection	the {@link Connection} to store in the event
	 */
	public ConnectionOpenedEvent(Connection connection) {
		super(connection);
	}
	
}
