package org.craftyserver.server.module;

import org.craftyserver.general.log.Log;
import org.craftyserver.server.event.events.PackageRecievedEvent;
import org.craftyserver.server.event.listener.PackageRecievedListener;

/**
 * Test class to test {@link Connection}.
 * @author PentagonLP
 * 
 * @deprecated This is a testing-only class and will eventually be deleted. Do not use outside of testing.
 */
// TODO delete once testing is complete
public class Testlistener implements PackageRecievedListener {
	
	/**
	 * Listen for {@link PackageRecievedEvent} called by {@link org.craftyserver.general.event.EventManager EventManager} 
	 * initialized in {@link org.craftyserver.craftyserver.main.CraftyServer CraftyServer}.
	 * 
	 * @param	event	{@link PackageRecievedEvent} called by {@link org.craftyserver.general.event.EventManager EventManager}
	 */
	@Override
	public void onPackageRecieved(PackageRecievedEvent event) {
		Log.log("Package from " + event.getConnection().getSocket().getInetAddress() + " recieved! Length: " + event.getPackage().getLength() + ", ID: " + event.getPackage().getId() + ", Datalength: " + event.getPackage().getData().length);
	}

}
