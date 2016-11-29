package by.bsk.oracle.dao.exception;

/**
 * ����� {@code DAOException} ��������� ����� {@link java.lang.Exception
 * Exception Exception} � ��������� ����������, ������� ��������� �� �����
 * ������ � ���� DAO.
 * 
 * @author Vladislav
 *
 */
public class DAOException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * ������� {@code DAOException} � ���������, ������ �� ������� ��������
	 * ���������� {@code e}.
	 * 
	 * @param message
	 *            ��������� ������� ����� �������� �� �������.
	 * @param e
	 *            ����������
	 */
	public DAOException(String message, Exception e) {
		super(message, e);
	}

}
