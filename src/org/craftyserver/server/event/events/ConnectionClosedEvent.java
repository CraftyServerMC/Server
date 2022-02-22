package org.craftyserver.server.event.events;

import org.craftyserver.server.connection.Connection;

/**
 * Event called when a {@link Connection} to a client is closed.
 * 
 * <p> Managed by {@link org.craftyserver.general.event.EventManager EventManager} 
 * initialized in {@link org.craftyserver.craftyserver.main.CraftyServer CraftyServer}.
 * 
 * @author PentagonLP
 */
// TODO maybe pass on Exception if non regular closure
public class ConnectionClosedEvent extends ConnectionEvent {
	
	/**
	 * If {@code false}, some exception was thrown that led to the closure of the connection
	 */
	private final boolean isregular;
	
	/**
	 * Creates a {@code ConnectionClosedEvent} with a {@link Connection} and a {@code boolean}, indicating whether the closure was normal.
	 * 
	 * <p> If isregular is {@code false}, some exception was thrown that led to the abrupt closure of the connection. 
	 * If {@code true}, the connection was closed normally.
	 * 
	 * @param	connection	the connection that was closed
	 * @param	isregular	Indicator whether the {@link Connection} was closed normally or abruptly due to an exception.
	 */
	public ConnectionClosedEvent(Connection connection, boolean isregular) {
		super(connection);
		this.isregular = isregular;
	}
	
	/**
	 * Get {@code boolean} whether the connection was closed normally or abruptly due to an exception.
	 * 
	 * @return	{@code boolean} whether the {@link Connection} was closed normally or abruptly due to an exception.
	 */
	public boolean isRegular() {
		return isregular;
	}

}
