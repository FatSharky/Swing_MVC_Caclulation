package by.bsk.oracle.dao;

import java.util.List;

import by.bsk.oracle.dao.exception.DAOException;
import by.bsk.oracle.domain.PriceCategory;

/**
 * Интерфейс {@code PriceCategoryDAO} расширяет интерфейс {@link AbstractDAO} и
 * описывает методы для работы с базой данных, которые могут использоваться
 * только с объектами типа {@link by.bsk.oracle.domain.PriceCategory
 * PriceCategory}.
 * 
 * @author Vladislav
 *
 * @see AbstractDAO
 * @see by.bsk.oracle.domain.PriceCategory PriceCategory
 */
public interface PriceCategoryDAO extends AbstractDAO<PriceCategory> {

	/**
	 * Метод {@code selectPriceCategoryByIdDivision} возвращает строку с
	 * заданным id из базы данных и записывает её в объект типа
	 * {@link by.bsk.oracle.domain.Division Division}
	 * 
	 * @param idDivision
	 *            id по которому будет искаться объект
	 * @return объект типа {@link by.bsk.oracle.domain.Division Division}
	 * @throws DAOException
	 *             если случается ошибка подключения к базе данных или запрос
	 *             возвращает не правильное значение.
	 */
	List<PriceCategory> selectPriceCategoryByIdDivision(int idDivision) throws DAOException;
}
