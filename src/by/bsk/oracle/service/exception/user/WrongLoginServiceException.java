package by.bsk.oracle.service.exception.user;

public class WrongLoginServiceException extends UserServiceException {

	private static final long serialVersionUID = 1L;

	public WrongLoginServiceException(String message, Exception e) {
		super(message, e);
	}

	public WrongLoginServiceException(String message) {
		super(message);
	}

}
