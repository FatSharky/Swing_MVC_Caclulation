package by.bsk.oracle.dao;

import java.util.List;

import by.bsk.oracle.dao.exception.DAOException;
import by.bsk.oracle.domain.Division;

/**
 * Интерфейс {@code DivisionDAO} расширяет интерфейс {@link AbstractDAO} и
 * описывает методы для работы с базой данных, которые могут использоваться
 * только с объектами типа {@link by.bsk.oracle.domain.Division Division}.
 * 
 * @author Vladislav
 *
 * @see AbstractDAO
 * @see by.bsk.oracle.domain.Division Division
 */
public interface DivisionDAO extends AbstractDAO<Division> {
	/**
	 * Метод {@code selectAllDivision} возвращает все поля из таблицы division,
	 * находящейся в базе данных и записывает их в список.
	 * 
	 * @return список объектов типа {@link by.bsk.oracle.domain.Division
	 *         Division}
	 * @throws DAOException
	 *             если случается ошибка подключения к базе данных или запрос
	 *             возвращает не правильное значение.
	 */
	List<Division> selectAllDivision() throws DAOException;

	/**
	 * Метод {@code selectDivisionById} возвращает строку с заданным id из базы
	 * данных и записывает её в объект типа {@link by.bsk.oracle.domain.Division
	 * Division}
	 * 
	 * @param idDivision
	 *            id по которому будет искаться объект
	 * @return объект типа {@link by.bsk.oracle.domain.Division Division}
	 * @throws DAOException
	 *             если случается ошибка подключения к базе данных или запрос
	 *             возвращает не правильное значение.
	 */
	Division selectDivisionById(int idDivision) throws DAOException;

}
