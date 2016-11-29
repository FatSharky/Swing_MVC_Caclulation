package by.bsk.oracle.dao;

import by.bsk.oracle.dao.exception.DAOException;

/**
 * Интерфейс {@code AbstractDAO} описывает базовые методы взаимодействия с базой
 * данных
 * 
 * @author Vladislav
 *
 * @param <T>
 *            тип сущности
 */
public interface AbstractDAO<T> {
	/**
	 * Метод {@code add} позволяте создать новую строку в базе данных.
	 * 
	 * @param entity
	 *            объект который будет записан в эту строку.
	 * @throws DAOException
	 *             если случается ошибка подключения к базе данных или запрос
	 *             возвращает не правильное значение.
	 * 
	 * 
	 * @see by.bsk.oracle.dao.exception.DAOException DAOException
	 */
	void add(T entity) throws DAOException;

	/**
	 * Метод {@code update} пзволяет изменить строку находящуюся в базе данных.
	 * 
	 * @param entity
	 *            объект который будет изменён.
	 * @throws DAOException
	 *             если случается ошибка подключения к базе данных или запрос
	 *             возвращает не правильное значение.
	 * 
	 * @see by.bsk.oracle.dao.exception.DAOException DAOException
	 */
	void update(T entity) throws DAOException;

	/**
	 * Метод {@code delete} позволяет удалить строку из базы.
	 * 
	 * @param id
	 *            id объекта который будет удалён.
	 * 
	 * @throws DAOException
	 *             если случается ошибка подключения к базе данных или ошибка
	 *             взаимодействия с пулом соединения.
	 * 
	 * @see by.bsk.oracle.dao.exception.DAOException DAOException
	 */
	void delete(int id) throws DAOException;

}
