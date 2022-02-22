package org.craftyserver.server.event.events;

import org.craftyserver.general.event.Event;
import org.craftyserver.server.connection.Connection;

/**
 * Superclass for all {@link Event Events} that have a {@link Connection} as a parameter
 * 
 * @author PentagonLP
 */
public abstract class ConnectionEvent extends Event {
	
	/**
	 * The stored {@link Connection}
	 */
	private final Connection connection;
	
	/**
	 * Creates a {@code ConnectionEvent} by taking in a {@link Connection} and storing it.
	 * 
	 * @param	connection	the {@link Connection} to store in the event
	 */
	protected ConnectionEvent(Connection connection) {
		this.connection = connection;
	}
	
	/**
	 * Get the {@link Connection} which is stored as a parameter
	 *
	 * @return	the stored {@link Connection}
	 */
	public Connection getConnection() {
		return connection;
	}
	
}
