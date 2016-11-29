package by.bsk.oracle.dao;

import java.util.List;

import by.bsk.oracle.dao.exception.DAOException;
import by.bsk.oracle.domain.User;

/**
 * ��������� {@code UserDAO} ��������� ��������� {@link AbstractDAO} � ���������
 * ������ ��� ������ � ����� ������, ������� ����� �������������� ������ �
 * ��������� ���� {@link by.bsk.oracle.domain.User User}.
 * 
 * @author Vladislav
 *
 * @see AbstractDAO
 * @see by.bsk.oracle.domain.User User
 */
public interface UserDAO extends AbstractDAO<User> {
	/**
	 * ����� {@code addAdmin} ��������� ������� ����� ������ � ������� users,
	 * ����������� � ���� ������.
	 * 
	 * @param user
	 *            ������ ������� ����� ������� � ��� ������.
	 * @throws DAOException
	 *             ���� ��������� ������ ����������� � ���� ������ ��� ������
	 *             ���������� �� ���������� ��������.
	 */
	void addAdmin(User user) throws DAOException;

	/**
	 * ����� {@code userLogin} ��������� ����� � ���� ������ ������ �
	 * ������������ ������ � ������� � �������� � � ������ ����
	 * {@link by.bsk.oracle.domain.User User}.
	 * 
	 * @param login
	 *            ����� ������������.
	 * @param password
	 *            ������ ������������.
	 * @return ������ ���� {@link by.bsk.oracle.domain.User User}.
	 * @throws DAOException
	 *             ���� ��������� ������ ����������� � ���� ������ ��� ������
	 *             ���������� �� ���������� ��������.
	 */
	User userLogin(String login, String password) throws DAOException;

	/**
	 * ����� {@code userLoginRootAdmin} ��������� ����� � ���� ������ ������ �
	 * ������������ ������ � ������� � �������� � � ������ ����
	 * {@link by.bsk.oracle.domain.User User}.
	 * 
	 * @param login
	 *            ����� ������������.
	 * @param password
	 *            ������ ������������.
	 * @return ������ ���� {@link by.bsk.oracle.domain.User User}.
	 * @throws DAOException
	 *             ���� ��������� ������ ����������� � ���� ������ ��� ������
	 *             ���������� �� ���������� ��������.
	 */
	User userLoginRootAdmin(String login, String password) throws DAOException;

	/**
	 * ����� {@code userExist} ��������� ����� � ���� ������ ������ �
	 * ������������ ������ � ������� � �������� � � ������ ����
	 * {@link by.bsk.oracle.domain.User User}.
	 * 
	 * @param login
	 *            ����� ������������.
	 * @param password
	 *            ������ ������������.
	 * @return true ���� ������������ ���������� � false ���� ���
	 * @throws DAOException
	 *             ���� ��������� ������ ����������� � ���� ������ ��� ������
	 *             ���������� �� ���������� ��������.
	 */
	boolean userExist(String login, String password) throws DAOException;

	/**
	 * ����� {@code selectAllUserByIdDivision} ���������� ���� �� idDivision ��
	 * ������� users, ����������� � ���� ������, � ���������� �� � ������
	 * �������� ���� {@link by.bsk.oracle.domain.User User}.
	 * 
	 * @param idDivision
	 *            id �� �������� ����� �������� ������ � ���� ������
	 * @return ������ �������� ���� {@link by.bsk.oracle.domain.User User}.
	 * @throws DAOException
	 *             ���� ��������� ������ ����������� � ���� ������ ��� ������
	 *             ���������� �� ���������� ��������.
	 */
	List<User> selectAllUserByIdDivision(int idDivision) throws DAOException;

	List<User> selectUsersByIdDivision(int idDivision) throws DAOException;

	User selectUserByLoginAndPass(String login, String password) throws DAOException;

	/**
	 * 
	 * @param idDivision
	 * @return
	 * @throws DAOException
	 */
	int selectCountAdminInDivision(int idDivision) throws DAOException;

}
