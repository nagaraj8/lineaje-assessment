/**
 * 
 */
package com.lineaje.assessment.exception;

/**
 * @author HegdeNagaraj
 *
 */
public class LineajeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2352055059547422503L;

	/**
	 * 
	 */
	public LineajeException() {
	}

	/**
	 * @param message
	 */
	public LineajeException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public LineajeException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public LineajeException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public LineajeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
