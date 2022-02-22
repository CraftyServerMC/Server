package org.craftyserver.server.event.listener;

import org.craftyserver.server.event.events.PackageRecievedEvent;
import org.craftyserver.general.event.Listener;

/**
 * Listener Interface implemented by classes which have subscribed to {@link PackageRecievedEvent}
 * 
 * <p> Managed by {@link org.craftyserver.general.event.EventManager EventManager} 
 * initialized in {@link org.craftyserver.craftyserver.main.CraftyServer CraftyServer}.
 * 
 * @author PentagonLP
 */
public interface PackageRecievedListener extends Listener {
	
	/**
	 * Method called by {@link org.craftyserver.general.event.EventManager EventManager} when {@link PackageRecievedEvent} occurs
	 * 
	 * @param	event	the occurred {@link PackageRecievedEvent}
	 */
	public void onPackageRecieved(PackageRecievedEvent event);

}
