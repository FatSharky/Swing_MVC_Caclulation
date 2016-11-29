package by.bsk.oracle.dao;

import java.util.List;

import by.bsk.oracle.dao.exception.DAOException;
import by.bsk.oracle.domain.PriceCategory;

/**
 * ��������� {@code PriceCategoryDAO} ��������� ��������� {@link AbstractDAO} �
 * ��������� ������ ��� ������ � ����� ������, ������� ����� ��������������
 * ������ � ��������� ���� {@link by.bsk.oracle.domain.PriceCategory
 * PriceCategory}.
 * 
 * @author Vladislav
 *
 * @see AbstractDAO
 * @see by.bsk.oracle.domain.PriceCategory PriceCategory
 */
public interface PriceCategoryDAO extends AbstractDAO<PriceCategory> {

	/**
	 * ����� {@code selectPriceCategoryByIdDivision} ���������� ������ �
	 * �������� id �� ���� ������ � ���������� � � ������ ����
	 * {@link by.bsk.oracle.domain.Division Division}
	 * 
	 * @param idDivision
	 *            id �� �������� ����� �������� ������
	 * @return ������ ���� {@link by.bsk.oracle.domain.Division Division}
	 * @throws DAOException
	 *             ���� ��������� ������ ����������� � ���� ������ ��� ������
	 *             ���������� �� ���������� ��������.
	 */
	List<PriceCategory> selectPriceCategoryByIdDivision(int idDivision) throws DAOException;
}
