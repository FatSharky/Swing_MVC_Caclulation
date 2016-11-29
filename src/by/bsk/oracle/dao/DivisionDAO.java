package by.bsk.oracle.dao;

import java.util.List;

import by.bsk.oracle.dao.exception.DAOException;
import by.bsk.oracle.domain.Division;

/**
 * ��������� {@code DivisionDAO} ��������� ��������� {@link AbstractDAO} �
 * ��������� ������ ��� ������ � ����� ������, ������� ����� ��������������
 * ������ � ��������� ���� {@link by.bsk.oracle.domain.Division Division}.
 * 
 * @author Vladislav
 *
 * @see AbstractDAO
 * @see by.bsk.oracle.domain.Division Division
 */
public interface DivisionDAO extends AbstractDAO<Division> {
	/**
	 * ����� {@code selectAllDivision} ���������� ��� ���� �� ������� division,
	 * ����������� � ���� ������ � ���������� �� � ������.
	 * 
	 * @return ������ �������� ���� {@link by.bsk.oracle.domain.Division
	 *         Division}
	 * @throws DAOException
	 *             ���� ��������� ������ ����������� � ���� ������ ��� ������
	 *             ���������� �� ���������� ��������.
	 */
	List<Division> selectAllDivision() throws DAOException;

	/**
	 * ����� {@code selectDivisionById} ���������� ������ � �������� id �� ����
	 * ������ � ���������� � � ������ ���� {@link by.bsk.oracle.domain.Division
	 * Division}
	 * 
	 * @param idDivision
	 *            id �� �������� ����� �������� ������
	 * @return ������ ���� {@link by.bsk.oracle.domain.Division Division}
	 * @throws DAOException
	 *             ���� ��������� ������ ����������� � ���� ������ ��� ������
	 *             ���������� �� ���������� ��������.
	 */
	Division selectDivisionById(int idDivision) throws DAOException;

}
