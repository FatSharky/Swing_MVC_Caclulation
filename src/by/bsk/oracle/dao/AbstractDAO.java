package by.bsk.oracle.dao;

import by.bsk.oracle.dao.exception.DAOException;

/**
 * ��������� {@code AbstractDAO} ��������� ������� ������ �������������� � �����
 * ������
 * 
 * @author Vladislav
 *
 * @param <T>
 *            ��� ��������
 */
public interface AbstractDAO<T> {
	/**
	 * ����� {@code add} ��������� ������� ����� ������ � ���� ������.
	 * 
	 * @param entity
	 *            ������ ������� ����� ������� � ��� ������.
	 * @throws DAOException
	 *             ���� ��������� ������ ����������� � ���� ������ ��� ������
	 *             ���������� �� ���������� ��������.
	 * 
	 * 
	 * @see by.bsk.oracle.dao.exception.DAOException DAOException
	 */
	void add(T entity) throws DAOException;

	/**
	 * ����� {@code update} �������� �������� ������ ����������� � ���� ������.
	 * 
	 * @param entity
	 *            ������ ������� ����� ������.
	 * @throws DAOException
	 *             ���� ��������� ������ ����������� � ���� ������ ��� ������
	 *             ���������� �� ���������� ��������.
	 * 
	 * @see by.bsk.oracle.dao.exception.DAOException DAOException
	 */
	void update(T entity) throws DAOException;

	/**
	 * ����� {@code delete} ��������� ������� ������ �� ����.
	 * 
	 * @param id
	 *            id ������� ������� ����� �����.
	 * 
	 * @throws DAOException
	 *             ���� ��������� ������ ����������� � ���� ������ ��� ������
	 *             �������������� � ����� ����������.
	 * 
	 * @see by.bsk.oracle.dao.exception.DAOException DAOException
	 */
	void delete(int id) throws DAOException;

}
