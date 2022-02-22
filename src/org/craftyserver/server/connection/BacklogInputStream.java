package org.craftyserver.server.connection;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Input Stream which stores read bytes in temporary Buffer to be able to reread already read bytes.
 * 
 * <p> With every read outside of the cache, the cache will be shifted to the left by {@code 1 byte} to make 
 * space in the last byte to store the byte that was just read.
 * 
 * <p> If the cache is full, the oldest byte will be dropped.
 * 
 * @author PentagonLP
 */
// TODO make it function more like BufferedReader (mark(), reset()), or maybe even replace with buffered reader?
public class BacklogInputStream {
	
	/**
	 * Input Stream to read from
	 */
	private final InputStream input;
	/** 
	 * Cache to store read bytes in
	 */
	private final byte[] cache;
	/**
	 * Next read position relativ to {@code cache[0]}
	 */
	private int position;
	
	/**
	 * Creates {@code BacklogInputStream} by creating the cache from specified
	 * size and storing a link to the {@link InputStream} to read data from.
	 * 
	 * <p> Readhead position is set to the first {@code byte} to be read from the 
	 * {@code InputStream}, which can be accessed by reading the fist byte outside 
	 * the cache, which has the position {@code cache.length}
	 * 
	 * @param 	cachesize 	the size of the cache, in bytes
	 * @param 	input		the {@link InputStream} to read from
	 */
	public BacklogInputStream(int cachesize, InputStream input) {
		this.input = input;
		
		cache = new byte[cachesize];
		setReadheadPosition(cache.length);
	}
	
	/**
	 * Reads the {@code byte} at the position of the readhead.
	 * 
	 * <p> If the {@code byte} is read from the cache, the readhead to the right by {@code 1 byte}.
	 * If the {@code byte} is read directly from the {@link InputStream}, the cache is shifted to the left by 
	 * {@code 1 byte}. It drops the oldest {@code byte} if full. After that, the {@code byte} that was 
	 * just read is stored in the last position of the cache.
	 * 
	 * @return					the read byte
	 * @throws	IOException		if an error occurs while reading from the {@code InputStream}.
	 * @throws	EOFException	if there is no more data to be read from the {@code InputStream} 
	 * 							and {@code InputStream.read()} returns -1
	 */
	public byte read() throws IOException, EOFException {
		if (position>=cache.length) {
			// Write to cache
			int value = input.read();
			if (value==-1) throw new EOFException("No more data to read!");
			for (int i = 0; i<cache.length; i++)
				if (i+1==cache.length) 
					// Shift
					cache[i] = (byte) value;
				else
					// Write
					cache[i] = cache[i+1];
			return (byte) value;
		} else {
			// Read from cache
			setReadheadPosition(getReadheadPosition()+1);
			return cache[getReadheadPosition()-1];
		}
	}
	
	/**
	 * Reads a number of {@code bytes} at and after the position of the readhead and returns them in a {@code byte array}.
	 * 
	 * <p> If the current {@code byte} is read from the cache, the readhead to the right by {@code 1 byte}.
	 * If the {@code byte} is read directly from the {@link InputStream}, the cache is shifted to the left by 
	 * {@code 1 byte}. It drops the oldest {@code byte} if full. After that, the {@code byte} that was 
	 * just read is stored in the last position of the cache.
	 * 
	 * @param	n				the number of bytes to read
	 * @return					the read byte array
	 * @throws	IOException		if an error occurs while reading from the {@code InputStream}.
	 * @throws	EOFException	if there is no more data to be read from the {@code InputStream} 
	 * 							and {@code InputStream.read()} returns -1
	 */
	public byte[] readNBytes(int n) throws IOException, EOFException {
		byte[] result = new byte[n];
		for (int i = 0; i<n; i++) {
			int value = read();
			result[i] = (byte) value;
		}
		return result;
	}
	
	/**
	 * Set the position of the readhead
	 * 
	 * <p> If the Position is smaller then {@code getCache().length}, the next read {@code byte} will be read from cache. 
	 * If it is higher or equal as {@code getCache().length}, the next read {@code byte} will be read from the input stream
	 * and the {@code byte} will be stored in the cache.
	 * 
	 * @param	pos	the position to set the readhead position to
	 */
	public void setReadheadPosition(int pos) {
		position = pos;
	}
	
	/**
	 * Get the {@link InputStream} the {@code BacklogInputStream} reads new {@code bytes} from
	 * 
	 * @return	the assigned {@link InputStream}
	 */
	public InputStream getInput() {
		return input;
	}
	
	/**
	 * Get the {@code byte array} representing the {@code BacklogInputStream}s cache
	 * 
	 * @return	the {@code BacklogInputStreams} cache as a byte array
	 */
	public byte[] getCache() {
		return cache;
	}
	
	/**
	 * Get the position of the readhead
	 * 
	 * <p> If the Position is smaller then {@code getCache().length}, the next read {@code byte} will be read from cache. 
	 * If it is higher or equal as {@code getCache().length}, the next read {@code byte} will be read from the input stream
	 * and the {@code byte} will be stored in the cache.
	 * 
	 * @return	the readheads' position relativ to {@code cache[0]}
	 */
	public int getReadheadPosition() {
		return position;
	}

}
