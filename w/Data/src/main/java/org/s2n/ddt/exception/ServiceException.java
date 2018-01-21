package org.s2n.ddt.exception;

public class ServiceException extends Exception {

	/**
	 * Generated serial version id
	 */
	private static final long serialVersionUID = 1L;

	private String msg;

	public ServiceException() {
		super();
	}

	/**
	 * Provide the proper error message
	 * 
	 * @param msg
	 *            the String
	 */
	public ServiceException(String msg) {
		super(msg);
		this.msg = msg;
	}

	/**
	 * provide the proper error message and the exception type which should be
	 * handled by the caller
	 * 
	 * @param msg
	 *            the String
	 * @param e
	 *            the Throwable
	 */
	public ServiceException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}

	/**
	 * String representation of this exception object
	 */
	@Override
	public String toString() {
		return "ServiceException [msg=" + msg + "]";
	}

}
