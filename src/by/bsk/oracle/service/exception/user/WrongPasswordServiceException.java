package by.bsk.oracle.service.exception.user;

public class WrongPasswordServiceException extends UserServiceException {

	private static final long serialVersionUID = 1L;

	public WrongPasswordServiceException(String message, Exception e) {
		super(message, e);
	}

	public WrongPasswordServiceException(String message) {
		super(message);
	}

}
