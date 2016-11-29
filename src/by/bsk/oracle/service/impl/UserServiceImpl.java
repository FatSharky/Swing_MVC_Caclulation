package by.bsk.oracle.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bsk.oracle.dao.UserDAO;
import by.bsk.oracle.dao.exception.DAOException;
import by.bsk.oracle.dao.factory.DAOFactory;
import by.bsk.oracle.domain.Division;
import by.bsk.oracle.domain.User;
import by.bsk.oracle.service.UserService;
import by.bsk.oracle.service.exception.ServiceException;

public class UserServiceImpl implements UserService {
	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

	@Override
	public void addUser(String login, String password, int idDivision) throws ServiceException {
		try {
			logger.debug("addUser() login={}, password={}, idDivision={}", login, password, idDivision);
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoFactory.getUserDAO();
			User user = new User();
			user.setLogin(login);
			user.setPassword(password);
			Division division = new Division();
			division.setIdDivision(idDivision);
			user.setDivision(division);
			userDAO.add(user);
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can't do add user operation", e);
		}

	}

	@Override
	public void updateUser(String login, String password, String idUser) throws ServiceException {
		try {
			logger.debug("updateUser() login={}, password={}, idUser={}", login, password, idUser);
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoFactory.getUserDAO();
			User user = new User();
			user.setLogin(login);
			user.setPassword(password);
			user.setIdUser(Integer.valueOf(idUser));
			userDAO.update(user);
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can't do add update operation", e);
		}

	}

	@Override
	public void deleteUser(String idUser) throws ServiceException {
		try {
			logger.debug("deleteUser() idUser={}", idUser);
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoFactory.getUserDAO();
			userDAO.delete(Integer.valueOf(idUser));
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can't do delete user operation", e);
		}

	}

	@Override
	public User userLogIn(String login, String password) throws ServiceException {
		User user = null;
		try {
			logger.debug("userLogin() login={}, password={}", login, password);
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoFactory.getUserDAO();
			user = userDAO.userLogin(login, password);
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can't do user login operation", e);
		}
		return user;
	}

	@Override
	public boolean userExist(String login, String password) throws ServiceException {
		try {
			logger.debug("userExist() login={}, password={}", login, password);
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoFactory.getUserDAO();
			return userDAO.userExist(login, password);
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can't do user user exist", e);
		}
	}

	@Override
	public User userLogInAsRootAdmin(String login, String password) throws ServiceException {
		User user = null;
		try {
			logger.debug("userLogInAsRootAdmin() login={}, password={}", login, password);
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoFactory.getUserDAO();
			user = userDAO.userLoginRootAdmin(login, password);
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can't do user login as root admin operation", e);
		}
		return user;
	}

	@Override
	public List<User> selectAllUsersByIdDivision(int idDivision) throws ServiceException {
		List<User> users = null;
		try {
			logger.debug("selectAllUsersByIdDivision() idDivision={}", idDivision);
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoFactory.getUserDAO();
			users = userDAO.selectAllUserByIdDivision(idDivision);
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can't do select users by id divison operation", e);
		}
		return users;
	}

	@Override
	public List<User> selectUsersByIdDivision(int idDivision) throws ServiceException {
		List<User> users = null;
		try {
			logger.debug("selectUsersByIdDivision() idDivision={}", idDivision);
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoFactory.getUserDAO();
			users = userDAO.selectUsersByIdDivision(idDivision);
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can't do select users by id divison operation", e);
		}
		return users;
	}

	@Override
	public int countAdmin(int idDivision) throws ServiceException {
		int countAdmin;
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoFactory.getUserDAO();
			countAdmin = userDAO.selectCountAdminInDivision(idDivision);
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can't do count resume operation", e);
		}
		return countAdmin;
	}

	@Override
	public User selectUserByLoginAndPass(String login, String password) throws ServiceException {
		User user = null;
		try {
			logger.debug("selectUserByLoginAndPass() login={}, password={}", login, password);
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoFactory.getUserDAO();
			user = userDAO.selectUserByLoginAndPass(login, password);
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can't do select user by login and pass operation", e);
		}
		return user;
	}

}
