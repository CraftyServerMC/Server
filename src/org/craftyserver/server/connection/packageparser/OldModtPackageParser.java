package org.craftyserver.server.connection.packageparser;

import java.io.IOException;

import org.craftyserver.server.connection.BacklogInputStream;
import org.craftyserver.server.connection.Package;
import org.craftyserver.server.connection.PackageParser;

/**
 * Parser for packages of the legacy Server Ping format. At the moment unfinished and not in use. 
 * Exists for testing purposes only and will be renamed moved to a future module dealing with 
 * Serverpings once {@link org.craftyserver.server.connection.Connection Connection} is finished.
 * <p>
 * Used by {@link org.craftyserver.server.connection.Connection Connection}
 * 
 * @author PentagonLP
 * 
 * @deprecated This is a testing-only class and will be moved and/or renamed.
 */
// TODO finish this and probably rename and move to other module which is dealing with serverpings
public class OldModtPackageParser implements PackageParser {
	
	/**
	 * Parse a Package read from the next Bytes read from {@link BacklogInputStream}
	 * 
	 * @param	input		the {@link BacklogInputStream} to read from
	 * @throws	IOException	if reading from {@link BacklogInputStream} failes
	 */
	@Override
	public Package parseNextPackage(BacklogInputStream input) throws IOException {
		byte[] bytes = new byte[3];
		input.readNBytes(3);
		if (bytes.equals(new byte[] {(byte) 0xFE, (byte) 0x01, (byte) 0xFA})) {
			//int length = DataTypeParser.readShort(input.readNBytes(2));
			
		}
		return null;
	}

}
