package org.craftyserver.server.dataparser;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.craftyserver.server.connection.BacklogInputStream;

/**
 * Static methodes to read different data types of the Minecraft(TM) protocol from a {@link BacklogInputStream}.
 * See <a>https://wiki.vg/Protocol</a> for the specifications of the Minecraft(TM) protocol
 * 
 * @author PentagonLP
 */
// TODO make brain think of a better, uniform solution for toVarLong, fromVarLong, toVarInt, fromVarInt
public class DataTypeParser {
	
	/**
	 * Read a normal sized (5 bytes) {@code VarInt} from a {@link BacklogInputStream}
	 * 
	 * @param	input	the {@link BacklogInputStream}
	 * @return			the read {@code int}
	 */
	public static int readVarInt(BacklogInputStream input) throws IOException {
		return readVarInt(input, (byte) 5);
	}
	
	/**
	 * Read a normal sized (10 bytes) {@code VarLong} from a {@link BacklogInputStream}
	 * 
	 * @param	input	the {@link BacklogInputStream}
	 * @return			the read {@code long}
	 */
	public static long readVarLong(BacklogInputStream input) throws IOException {
		return readVarLong(input, (byte) 10);
	}
	
	/**
	 * Read a normal sized (5 bytes) {@code VarInt} from a {@code byte} array
	 * 
	 * @param	bytes	the {@code byte} array
	 * @return			the read {@code int}
	 */
	public static int readVarInt(byte[] bytes) throws IOException {
		return readVarInt(new BacklogInputStream(0, new ByteArrayInputStream(bytes)), (byte) 5);
	}
	
	/**
	 * Read a normal sized (10 bytes) {@code VarLong} from a {@code byte} array
	 * 
	 * @param	bytes	the {@code byte} array
	 * @return			the read {@code long}
	 */
	public static long readVarLong(byte[] bytes) throws IOException {
		return readVarInt(new BacklogInputStream(0, new ByteArrayInputStream(bytes)), (byte) 10);
	}
	
	/**
	 * Read a {@code VarInt} with a given size from a {@code byte} array
	 * 
	 * @param	bytes		the {@code byte} array
	 * @param	maxbytes	the maximum amount of bytes to be read
	 * @return				the read {@code int}
	 */
	public static int readVarInt(byte[] bytes, byte maxbytes) throws IOException {
		return readVarInt(new BacklogInputStream(0, new ByteArrayInputStream(bytes)), maxbytes);
	}
	
	/**
	 * Read a {@code VarLong} with a given size from a {@code byte} array
	 * 
	 * @param	bytes		the {@code byte} array
	 * @param	maxbytes	the maximum amount of bytes to be read
	 * @return				the read {@code long}
	 */
	public static long readVarLong(byte[] bytes, byte maxbytes) throws IOException {
		return readVarInt(new BacklogInputStream(0, new ByteArrayInputStream(bytes)), maxbytes);
	}
	
	/**
	 * Read a {@code VarInt} with a given size from a {@link BacklogInputStream}
	 * 
	 * @param	input		the {@link BacklogInputStream}
	 * @param	maxbytes	the maximum amount of bytes to be read
	 * @return				the read {@code int}
	 */
	public static int readVarInt(BacklogInputStream input, byte maxbytes) throws IOException {
		int result = 0;
		for (byte b = 0; b<maxbytes; b++) {
			byte value = input.read();
			result = result << 7; // Free last 7 bit
			result = result | (value & 127); // Copy last 7 Bits of Byte to last 7 Bits of Int
			if ((value & 128) == 0) break; // If 8th Bit is 0, break
			else if (b == maxbytes-1) throw new InvalidDataException("VarLong(" + maxbytes + ") is too long!"); // If 8th Bit is 1, but all Bytes are read, Error
		}
		return result;
	}
	
	/**
	 * Read a {@code VarLong} with a given size from a {@link BacklogInputStream}
	 * 
	 * @param	input		the {@link BacklogInputStream}
	 * @param	maxbytes	the maximum amount of bytes to be read
	 * @return				the read {@code long}
	 */
	public static long readVarLong(BacklogInputStream input, byte maxbytes) throws IOException {
		long result = 0;
		for (byte b = 0; b<maxbytes; b++) {
			byte value = input.read();
			result = result << 7; // Free last 7 bit
			result = result | (value & 127); // Copy last 7 Bits of Byte to last 7 Bits of Int
			if ((value & 128) == 0) break; // If 8th Bit is 0, break
			else if (b == maxbytes-1) throw new InvalidDataException("VarLong(" + maxbytes + ") is too long!"); // If 8th Bit is 1, but all Bytes are read, Error
		}
		return result;
	}
	
	/**
	 * Convert an {@code int} to a {@code VarInt}, stored in a {@code byte} array. The standard byte limit (5 bytes) is used
	 * 
	 * @param	num	the {@code int} to be converted
	 */
	public static byte[] toVarInt(int num) {
		return toVarInt(num, (byte) 5);
	}
	
	/**
	 * Convert a {@code long} to a {@code VarLong}, stored in a {@code byte} array. The standard byte limit (10 bytes) is used
	 * 
	 * @param	num	the {@code long} to be converted
	 */
	public static byte[] toVarLong(long num) {
		return toVarLong(num, (byte) 5);
	}
	
	/**
	 * Convert an {@code int} to a {@code VarInt}, stored in a {@code byte} array. A given byte limit is used.
	 * 
	 * @param	num			the {@code int} to be converted
	 * @param	maxbytes	the byte limit
	 */
	public static byte[] toVarInt(int num, byte maxbytes) {
		byte[] result = new byte[maxbytes];
		byte b = 0; // b = bytelength of varint
		for (b = 0; b<maxbytes; b++) {
			result[b] = (byte) (num&127); // Copy last 7 Bits of Num in Byte
			num = num >>> 7; // Move all remaining Bits of Num all the Way to the right
			result[b] = (byte) (result[b]|(Math.min(b, 1)*128)); // If still Bytes remaining, 8th Bit is 1 and continue
			if (num==0) break; // If no more Bytes left, break
		}
		
		// Result is inverted and too long. Lets fix this.
		byte[] cutresult = new byte[b+1];
		for (byte i = 0; i<=b; i++)
			cutresult[i] = result[b-i];
		
		return cutresult;
	}
	
	/**
	 * Convert a {@code long} to a {@code VarLong}, stored in a {@code byte} array. A given byte limit is used.
	 * 
	 * @param	num			the {@code long} to be converted
	 * @param	maxbytes	the byte limit
	 */
	public static byte[] toVarLong(long num, byte maxbytes) {
		byte[] result = new byte[maxbytes];
		byte b = 0; // b = bytelength of varint
		for (b = 0; b<maxbytes; b++) {
			result[b] = (byte) (num&127); // Copy last 7 Bits of Num in Byte
			num = num >>> 7; // Move all remaining Bits of Num all the Way to the right
			result[b] = (byte) (result[b]|(Math.min(b, 1)*128)); // If still Bytes remaining, 8th Bit is 1 and continue
			if (num==0) break; // If no more Bytes left, break
		}
		
		// Result is inverted and too long. Lets fix this.
		byte[] cutresult = new byte[b+1];
		for (byte i = 0; i<=b; i++)
			cutresult[i] = result[b-i];
		
		return cutresult;
	}

}
