package org.craftyserver.server.module;

import org.craftyserver.craftyserver.main.CraftyServer;
import org.craftyserver.craftyserver.module.Module;
import org.craftyserver.general.config.ConfigFile;
import org.craftyserver.general.event.EventManager;
import org.craftyserver.general.log.Level;
import org.craftyserver.general.log.Log;
import org.craftyserver.server.connection.Connection;
import org.craftyserver.server.connection.ConnectionListener;
import org.craftyserver.server.event.events.ConnectionClosedEvent;
import org.craftyserver.server.event.events.ConnectionOpenedEvent;
import org.craftyserver.server.event.events.PackageRecievedEvent;
import org.craftyserver.server.event.events.ServerAcceptEvent;
import org.craftyserver.server.event.events.ServerStartEvent;
import org.craftyserver.server.event.events.ServerStopEvent;
import org.craftyserver.server.event.listener.ConnectionClosedListener;
import org.craftyserver.server.event.listener.ConnectionOpenedListener;
import org.craftyserver.server.event.listener.PackageRecievedListener;
import org.craftyserver.server.event.listener.ServerAcceptListener;
import org.craftyserver.server.event.listener.ServerStartListener;
import org.craftyserver.server.event.listener.ServerStopListener;
import org.craftyserver.server.server.Server;

/**
 * Main class of the Server Module, used to accept clients, establish connections and send and receive Packages.
 * 
 * @author PentagonLP
 */
public class ServerModule extends Module {

	/**
	 * The main {@link Server}
	 */
	private final static Server SERVER = new Server();
	
	/**
	 * Default port, if not otherwise specified in the "server.config" {@link ConfigFile}
	 */
	private final static int DEFAULTPORT = 25565;
	
	/**
	 * Enable the {@link ServerModule}. Register Log names, register Events and start main {@link Server}.
	 */
	@SuppressWarnings("deprecation") // Ignore included testing classes. TODO remove this once testing period is over
	public void onEnable() {
		
		Log.registerClassName("Server Module");
		Log.registerClassName(ConnectionListener.class.getName(), "ConnectionListener");
		Log.registerClassName(Connection.class.getName(), "Connection");
		Log.registerClassName(Server.class.getName(), "Server");
		
		// Init EventManagers
		try {
			new EventManager("onServerAccept", ServerAcceptListener.class, ServerAcceptEvent.class);
			new EventManager("onServerStart", ServerStartListener.class, ServerStartEvent.class);
			new EventManager("onServerStop", ServerStopListener.class, ServerStopEvent.class);
			new EventManager("onConnectionOpened", ConnectionOpenedListener.class, ConnectionOpenedEvent.class);
			new EventManager("onConnectionClosed", ConnectionClosedListener.class, ConnectionClosedEvent.class);
			new EventManager("onPackageRecieved", PackageRecievedListener.class, PackageRecievedEvent.class);
		} catch (NoSuchMethodException | SecurityException e) {
			Log.log(Level.FATAL, "Failed to register Event Manager: " + e);
			Log.printStackTrace(e);
			Log.log("Startup aborted.");
			return;
		}
		
		CraftyServer.getEventmanagers().registerAllEvents(new ConnectionListener());
		CraftyServer.getEventmanagers().registerAllEvents(new Testlistener());
		
		CraftyServer.getConfigFile().get("Server").get("serverport").setDefaultValue(DEFAULTPORT);
		
		// Start Server
		Log.log("Starting Server on port " + CraftyServer.getConfigFile().get("Server").get("serverport").getInt() + "...");
		getServer().start(CraftyServer.getConfigFile().get("Server").get("serverport").getInt());
		Log.log("Ready to accept connections!");
	}
	
	/**
	 * Get the default port, if no other port is otherwise specified in the "server.config" {@link ConfigFile}
	 * 
	 * @return	the default port of the main {@link Server}
	 */
	public static int getDefaultServerPort() {
		return DEFAULTPORT;
	}
	
	/**
	 * Get the main {@link Server}
	 * 
	 * @return	the main {@link Server}
	 */
	public static Server getServer() {
		return SERVER;
	}
	
	/**
	 * Disable the {@link ServerModule}. Stop the main {@link Server}.
	 */
	@Override
	public void onDisable() {
		Log.log("Stopping Server...");
		getServer().stop();
		Log.log(Level.DEBUG, "Server stopped.");
	}
	

}
