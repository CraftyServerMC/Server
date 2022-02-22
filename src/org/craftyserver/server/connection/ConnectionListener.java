package org.craftyserver.server.connection;

import org.craftyserver.server.event.events.ServerAcceptEvent;
import org.craftyserver.server.event.listener.ServerAcceptListener;

/**
 * Listener waiting for {@link ServerAcceptEvent} and initializing {@link Connection}
 * 
 * @author PentagonLP
 */
public class ConnectionListener implements ServerAcceptListener {
	
	/**
	 * Methode called by {@link ServerAcceptListener}. Initializes new {@link Connection}
	 * 
	 * @param	event	the event, given by the {@link org.craftyserver.general.event.EventManager}
	 */
	@Override
	public void onServerAccept(ServerAcceptEvent event) {
		
		new Connection(event.getServer(), event.getSocket());
		
	}

}
