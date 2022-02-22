package org.craftyserver.server.connection;

/**
 * {@code Package} send from and to the Client
 * 
 * @author PentagonLP
 */
public class Package {
	
	private final int id, length;
	private final byte[] data;
	
	/**
	 * Creates a new {@code Package} with the given length, ID and data
	 * 
	 * @param	id		the ID of the {@code Package}
	 * @param	length	the length of id and data in the original byte stream
	 * @param	data	the data passed on with the package, as an array of bytes
	 */
	public Package(int id, int length, byte[] data) {
		this.id = id;
		this.length = length;
		this.data = data;
	}
	
	/**
	 * Get the ID of the package
	 * 
	 * @return	the ID
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Get the length of the package
	 * 
	 * @return	the length of ID and data in the original byte stream
	 */
	public int getLength() {
		return length;
	}
	
	/**
	 * Get the data transported by the package
	 * 
	 * @return the data passed on with the {@code Package}, as an array of bytes
	 */
	public byte[] getData() {
		return data;
	}

}
