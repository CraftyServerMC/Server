package org.craftyserver.server.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import org.craftyserver.server.connection.Connection;
import org.craftyserver.server.event.events.ServerAcceptEvent;
import org.craftyserver.server.event.events.ServerStartEvent;
import org.craftyserver.server.event.events.ServerStopEvent;
import org.craftyserver.craftyserver.main.CraftyServer;
import org.craftyserver.general.log.Level;
import org.craftyserver.general.log.Log;

/**
 * {@code Server} to wait for clients, accept connections and call {@link ServerAcceptEvent}
 * 
 * @author PentagonLP
 */
public class Server {
	
	/**
	 * "this" used in {@code serverthread}
	 */
	private final Server instance = this;
	
	/**
	 * The {@code Servers}' {@link ServerSocket}
	 */
	private ServerSocket serversocket = null;
	/**
	 * {@link Thread} to wait for and accept connections
	 */
	private Thread serverthread; 
	
	/**
	 * List of active {@link Connection Connections} from clients to the {@code Server}
	 */
	private final ArrayList<Connection> activeconnections = new ArrayList<>();
	
	/**
	 * Start the {@code Server} and {@code serverthread} and start to accept clients
	 * 
	 * @param	port	the port for the {@code Server} to run on
	 */
	public boolean start(int port) {
		try {
			serversocket = new ServerSocket(port);
		} catch (IOException e) {
			Log.log(Level.FATAL, "Failed to start internal Server! (" + e + ")");
			Log.printStackTrace(e);
			return false;
		}
		serverthread = new Thread() {
			public void run() {
				while (!serversocket.isClosed()) {
					try {
						Socket socket = serversocket.accept();
						CraftyServer.getEventmanagers().fireEvent(new ServerAcceptEvent(instance, socket));
						Log.log("Accepted connection from " + socket.getInetAddress().toString());
					} catch (SocketException e) {
						if (serversocket.isClosed()) return;
						Log.log(Level.WARNING, "Failed to accept connection from unknown client!  (" + e + ")");
						Log.printStackTrace(e);
					} catch (IOException e) {
						Log.log(Level.WARNING, "Failed to accept connection from unknown client!  (" + e + ")");
						Log.printStackTrace(e);
					}
				}
			}
		};
		serverthread.start();
		
		CraftyServer.getEventmanagers().fireEvent(new ServerStartEvent(this));
		
		return true;
		
	}
	
	/**
	 * Stop the {@code Server}. All of the {@code Servers} {@link Connection Connections} are automaticly closed.
	 */
	public void stop() {
		try {
			serversocket.close();
		} catch (IOException e) {
			Log.log(Level.FATAL, "Failed to stop internal Server! (" + e + ")");
			Log.printStackTrace(e);
			return;
		}
		CraftyServer.getEventmanagers().fireEvent(new ServerStopEvent(this));
	}
	
	/**
	 * Get the {@code Servers}' {@link ServerSocket}
	 * 
	 * @return	the {@code Servers}' {@link ServerSocket}
	 */
	public ServerSocket getServersocket() {
		return serversocket;
	}
	
	/**
	 * Get list of active {@link Connection Connections} from clients to the {@code Server}
	 * 
	 * @return	list of active {@link Connection Connections} from clients to the {@code Server}
	 */
	public ArrayList<Connection> getActiveConnections() {
		return activeconnections;
	}
	
}
