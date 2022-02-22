package org.craftyserver.server.connection.packageparser;

import java.io.IOException;

import org.craftyserver.server.dataparser.DataTypeParser;
import org.craftyserver.server.connection.BacklogInputStream;
import org.craftyserver.server.connection.Package;
import org.craftyserver.server.connection.PackageParser;

/**
 * Parser for packages of default format. At the moment the only working Parser and the only one in use.
 * <p>
 * Used by {@link org.craftyserver.server.connection.Connection Connection}
 * 
 * @author PentagonLP
 */
public class DefaultPackageParser implements PackageParser {
	
	/**
	 * Parse a package in default format read from the next Bytes read from {@link BacklogInputStream}
	 * 
	 * @param	input		the {@link BacklogInputStream} to read from
	 * @throws	IOException	if reading from {@link BacklogInputStream} fails
	 */
	@Override
	public Package parseNextPackage(BacklogInputStream input) throws IOException {
		// Package format: VarInt(3) length ; VarInt id ; ByteArray data
		int length = DataTypeParser.readVarInt(input, (byte) 3);
		int id = DataTypeParser.readVarInt(input);
		int lengthofid = DataTypeParser.toVarInt(id).length;
		byte[] data = input.readNBytes(length-lengthofid);
		return new Package(id, length, data);
	}

}
