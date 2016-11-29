package by.bsk.oracle.dao;

import java.util.List;

import by.bsk.oracle.dao.exception.DAOException;
import by.bsk.oracle.domain.User;

/**
 * Интерфейс {@code UserDAO} расширяет интерфейс {@link AbstractDAO} и описывает
 * методы для работы с базой данных, которые могут использоваться только с
 * объектами типа {@link by.bsk.oracle.domain.User User}.
 * 
 * @author Vladislav
 *
 * @see AbstractDAO
 * @see by.bsk.oracle.domain.User User
 */
public interface UserDAO extends AbstractDAO<User> {
	/**
	 * Метод {@code addAdmin} позволяте создать новую строку в таблице users,
	 * находящейся в базе данных.
	 * 
	 * @param user
	 *            объект который будет записан в эту строку.
	 * @throws DAOException
	 *             если случается ошибка подключения к базе данных или запрос
	 *             возвращает не правильное значение.
	 */
	void addAdmin(User user) throws DAOException;

	/**
	 * Метод {@code userLogin} позволяте найти в базе данных строку с
	 * определенным именем и паролем и записать её в объект типа
	 * {@link by.bsk.oracle.domain.User User}.
	 * 
	 * @param login
	 *            логин пользователя.
	 * @param password
	 *            пароль пользователя.
	 * @return объект типа {@link by.bsk.oracle.domain.User User}.
	 * @throws DAOException
	 *             если случается ошибка подключения к базе данных или запрос
	 *             возвращает не правильное значение.
	 */
	User userLogin(String login, String password) throws DAOException;

	/**
	 * Метод {@code userLoginRootAdmin} позволяте найти в базе данных строку с
	 * определенным именем и паролем и записать её в объект типа
	 * {@link by.bsk.oracle.domain.User User}.
	 * 
	 * @param login
	 *            логин пользователя.
	 * @param password
	 *            пароль пользователя.
	 * @return объект типа {@link by.bsk.oracle.domain.User User}.
	 * @throws DAOException
	 *             если случается ошибка подключения к базе данных или запрос
	 *             возвращает не правильное значение.
	 */
	User userLoginRootAdmin(String login, String password) throws DAOException;

	/**
	 * Метод {@code userExist} позволяте найти в базе данных строку с
	 * определенным именем и паролем и записать её в объект типа
	 * {@link by.bsk.oracle.domain.User User}.
	 * 
	 * @param login
	 *            логин пользователя.
	 * @param password
	 *            пароль пользователя.
	 * @return true если пользователь существует и false если нет
	 * @throws DAOException
	 *             если случается ошибка подключения к базе данных или запрос
	 *             возвращает не правильное значение.
	 */
	boolean userExist(String login, String password) throws DAOException;

	/**
	 * Метод {@code selectAllUserByIdDivision} возвращает поля по idDivision из
	 * таблицы users, находящейся в базе данных, и записывает их в список
	 * объектов типа {@link by.bsk.oracle.domain.User User}.
	 * 
	 * @param idDivision
	 *            id по которому будет искаться строки в базе данных
	 * @return список объектов типа {@link by.bsk.oracle.domain.User User}.
	 * @throws DAOException
	 *             если случается ошибка подключения к базе данных или запрос
	 *             возвращает не правильное значение.
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
