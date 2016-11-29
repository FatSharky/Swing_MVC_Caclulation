package by.bsk.oracle.service;

import java.util.List;

import by.bsk.oracle.domain.User;
import by.bsk.oracle.service.exception.ServiceException;

public interface UserService {

	void addUser(String login, String password, String access, int idDivision) throws ServiceException;

	void deleteUser(String idUser) throws ServiceException;

	void updateUser(String login, String password, String idUser) throws ServiceException;

	User userLogIn(String login, String password) throws ServiceException;

	boolean userExist(String login, String password) throws ServiceException;

	User userLogInAsRootAdmin(String login, String password) throws ServiceException;

	List<User> selectAllUsersByIdDivision(int idDivision) throws ServiceException;

	List<User> selectUsersByIdDivision(int idDivision) throws ServiceException;

	User selectUserByLoginAndPass(String login, String password) throws ServiceException;

	int countAdmin(int idDivision) throws ServiceException;

}
