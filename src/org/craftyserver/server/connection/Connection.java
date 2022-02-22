package org.craftyserver.server.connection;

import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import org.craftyserver.server.event.events.ConnectionClosedEvent;
import org.craftyserver.server.event.events.ConnectionOpenedEvent;
import org.craftyserver.server.event.events.PackageRecievedEvent;
import org.craftyserver.craftyserver.main.CraftyServer;
import org.craftyserver.server.server.Server;
import org.craftyserver.general.log.Level;
import org.craftyserver.general.log.Log;
import org.craftyserver.server.connection.packageparser.DefaultPackageParser;

/**
 * Represents a connection to a Minecraft client. Used for all communication with the client. Reads data, parses packages and calls {@link PackageRecievedEvent}.
 * Should in the future store objects as attributes for the connection, such as protocol version and associated player.
 * 
 * <p> Communication to client is not yet implemented. Parsing of other package formats then default package format is not yet implemented. 
 * 
 * @author PentagonLP
 */
// TODO connection attributes, communication server->client
public class Connection {
	
	/**
	 * List of available {@link PackageParser PackageParsers}
	 */
	private final static ArrayList<PackageParser> PACKAGEPARSERS = new ArrayList<>();
	
	/**
	 * "this" used in {@code clienthread}
	 */
	private final Connection instance = this;
	
	/**
	 * {@link Server} and {@link Socket}, given at initialization
	 */
	private final Server server;
	/**
	 * {@link Server} and {@link Socket}, given at initialization
	 */
	private final Socket socket;
	
	/**
	 * {@link Thread} to read and parse packages coming from the client
	 */
	private final Thread clientthread;
	
	/**
	 * Initialize Connection. Start {@link Thread} to read and parse packages coming from the client, and call {@link PackageRecievedEvent}.
	 * 
	 * @param	server2	the server which has accepted the socket
	 * @param	socket	the socket which was accepted by the server
	 */
	public Connection(Server server2, Socket socket) {
		this.server = server2;
		this.socket = socket;
		
		clientthread = new Thread() {
			public void run() {
				BacklogInputStream input;
				try {
					input = new BacklogInputStream(10, socket.getInputStream());
				} catch (IOException e) {
					Log.log(Level.WARNING, "IOException occured while establishing connection with " + socket.getInetAddress().toString() + ". Closing connection. (" + e + ")");
					Log.printStackTrace(e);
					close(false);
					return;
				}
				CraftyServer.getEventmanagers().fireEvent(new ConnectionOpenedEvent(instance));
				while (!socket.isClosed()) {
					try {
						// Wait until first byte is read
						input.read();
					} catch (EOFException e) {
						Log.log("Client " + getSocket().getInetAddress() + " closed connection. Closing serverside connection.");
						close(true);
						return;
					} catch (IOException e) {
						Log.log(Level.WARNING, "Unexpected IOException while reading recieved data from " + socket.getInetAddress().toString() + ", did the client unexpectedly close the connection? Closing connection with client. (" + e + ")");
						Log.printStackTrace(e);
						close(false);
						return;
					}
					// One byte has already been read. Go back one byte to start from the beginning of the InputStream.
					input.setReadheadPosition(input.getCache().length-1);
					try {
						// Parse package
						// TODO proper package parsing
						Package pack = new DefaultPackageParser().parseNextPackage(input);
						if (pack==null) throw new Exception("Parsed package is null");
						CraftyServer.getEventmanagers().fireEvent(new PackageRecievedEvent(instance, pack));
					} catch (EOFException e) {
						Log.log(Level.WARNING, "Client " + socket.getInetAddress().toString() + " closed connection with server while sending a package! Closing connection with client. (" + e + ")");
						Log.printStackTrace(e);
						close(false);
						return;
					} catch (IOException e) {
						Log.log(Level.WARNING, "Unexpected IOException while parsing package from " + socket.getInetAddress().toString() + ", did the client unexpectedly close the connection? Closing connection with client. (" + e + ")");
						Log.printStackTrace(e);
						close(false);
						return;
					} catch (Exception e) {
						Log.log(Level.WARNING, "Failed to parse package from " + socket.getInetAddress().toString() + "! (" + e + ")");
						Log.printStackTrace(e);
					}
					
				}
			}
		};
		
		clientthread.start();
		getServer().getActiveConnections().add(this);
		
	}
	
	/**
	 * Close connection and specify whether it was closed regularly or closed because of some {@link Exception} occurring.
	 * 
	 * <p> {@code isRegular} is only needed for {@link ConnectionClosedEvent}
	 * 
	 * @param	isRegular	true if the {@code Connection} was closed regularly, false if it was closed due to an exception
	 */
	public void close(boolean isRegular) {
		if (socket!=null&&!socket.isClosed())
			try {
				socket.close();
				Log.log(Level.DEBUG, "Server successfully closed connection with " + socket.getInetAddress().toString());
				CraftyServer.getEventmanagers().fireEvent(new ConnectionClosedEvent(instance, isRegular));
			} catch (IOException e) {
				Log.log(Level.WARNING, "Failed to close connection with " + socket.getInetAddress().toString() + ":");
				Log.printStackTrace(e);
			}
		else
			Log.log(Level.WARNING, "Unable to close already closed or null socket!");
		getServer().getActiveConnections().remove(this);
	}
	
	/**
	 * Get list of all availible {@link PackageParser PackageParsers}. Currently always empty and of no use.
	 * 
	 * @return	ArrayList of all availible PackageParsers
	 */
	public static ArrayList<PackageParser> getPackageparsers() {
		return PACKAGEPARSERS;
	}
	
	/**
	 * Get the server that has accepted the socket associated with the connection
	 * 
	 * @return	the server that has accepted the socket, given at initialization
	 */
	public Server getServer() {
		return server;
	}
	
	/**
	 * Get the socket accepted by the server and that was associated with the connection
	 * 
	 * @return	the socket that was accepted by the server, given at initialization
	 */
	public Socket getSocket() {
		return socket;
	}
	
	/**
	 * Get thread that waits for new data to be send by the client, parses it into {@link Package Packages} and calls {@link PackageRecievedEvent}
	 * 
	 * @return	the thread which is waiting for data and parsing packages from the client
	 */
	public Thread getClientThread() {
		return clientthread;
	}
	
}
