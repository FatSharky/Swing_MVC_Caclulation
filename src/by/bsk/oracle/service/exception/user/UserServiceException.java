package by.bsk.oracle.service.exception.user;

import by.bsk.oracle.service.exception.ServiceException;

public class UserServiceException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserServiceException(String message) {
		super(message);
	}

	public UserServiceException(String message, Exception e) {
		super(message, e);
	}

}
