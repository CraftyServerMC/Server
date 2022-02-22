package org.craftyserver.server.dataparser;

/**
 * Exception that is thrown when the data in the given {@link java.io.InputStream} does not comply with an expected format of a method from {@link DataTypeParser}
 * 
 * @author PentagonLP
 */
public class InvalidDataException extends RuntimeException {
	
	/**
	 * The {@code serialVersionUID}, required as {@link InvalidDataException} extends {@link RuntimeException}
	 */
	private static final long serialVersionUID = 6204934804865653448L;
	
	/**
	 * Creates a new {@link InvalidDataException} with a given error message.
	 * 
	 * @param	message	the error message
	 */
	public InvalidDataException(String message) {
		super(message);
	}
	
}
