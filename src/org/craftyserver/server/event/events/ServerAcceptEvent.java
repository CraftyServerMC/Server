package org.craftyserver.server.event.events;

import java.net.Socket;

import org.craftyserver.server.server.Server;

/**
 * Event called when a {@link Server} accepts a socket
 * 
 * <p> Managed by {@link org.craftyserver.general.event.EventManager EventManager} 
 * initialized in {@link org.craftyserver.craftyserver.main.CraftyServer CraftyServer}.
 * 
 * @author PentagonLP
 */
public class ServerAcceptEvent extends ServerEvent {
	
	/**
	 * The stored {@link Socket}
	 */
	private final Socket socket;
	
	/**
	 * Creates a {@code ServerAcceptEvent} by taking in a {@link Server} and a {@link Socket} and storing it.
	 * 
	 * @param	server	the {@link Server} to store in the event
	 * @param	socket	the {@link Socket} to store in the event
	 */
	public ServerAcceptEvent(Server server,  Socket socket) {
		super(server);
		this.socket = socket;
	}
	
	/**
	 * Get the {@link Socket} which is stored as a parameter
	 *
	 * @return	the stored {@link Socket}
	 */
	public Socket getSocket() {
		return socket;
	}
	
}