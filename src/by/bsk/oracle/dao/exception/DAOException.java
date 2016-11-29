package by.bsk.oracle.dao.exception;

/**
 * Класс {@code DAOException} наследует класс {@link java.lang.Exception
 * Exception Exception} и описывает исключения, которые возникают во время
 * работы в слое DAO.
 * 
 * @author Vladislav
 *
 */
public class DAOException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Создает {@code DAOException} с собщением, причин по котором возникло
	 * исключение {@code e}.
	 * 
	 * @param message
	 *            Сообщение которое будет выведено на консоль.
	 * @param e
	 *            исключение
	 */
	public DAOException(String message, Exception e) {
		super(message, e);
	}

}
