package by.bsk.oracle.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bsk.oracle.dao.UserDAO;
import by.bsk.oracle.dao.exception.DAOException;
import by.bsk.oracle.dao.util.DBUtil;
import by.bsk.oracle.domain.Access;
import by.bsk.oracle.domain.Division;
import by.bsk.oracle.domain.User;

/**
 * Class {@code DBUserDAO} реализует интерфейс {@link by.bsk.oracle.dao.UserDAO
 * UserDAO} для базы данных Oracle.
 * 
 * @author Vladislav
 *
 */
public class DBUserDAO implements UserDAO {

	private static final Logger logger = LogManager.getLogger(DBUserDAO.class);

	private static final String SQL_ADD_USER = "INSERT INTO user (login, password, id_division, access, role) VALUES (?, ?, ?, ?, 'user');";
	private static final String SQL_UPDATE_USER = "UPDATE user SET login=?, password=?, access=? WHERE id_user=?;";
	private static final String SQL_DELETE_USER = "DELETE FROM user WHERE id_user=?;";
	private static final String SQL_ADD_ADMIN = "INSERT INTO user (login, password, id_division, role) VALUES (?, ?, ? 'admin');";
	private static final String SQL_LOGIN_USER = "SELECT u.login,u.role, u.id_division, u.access, d.name,d.unn FROM user as u join division as d on u.id_division=d.id_division WHERE login=? and password=?;";
	private static final String SQL_USER_EXIST = "SELECT id_user, login, password, role, access FROM user WHERE login=? and password=?;";
	private static final String SQL_SELECT_ALL_USER_BY_ID_DIVISION = "SELECT * FROM user where id_division=?;";
	private static final String SQL_SELECT_COUNT_ADMIN_BY_ID_DIVISION = "SELECT count(id_user) FROM user where id_division=? and role='admin';";
	private static final String SQL_SELECT_USERS_BY_ID_DIVISION = "SELECT * FROM user where id_division=? and role='user';";
	private static final String SQL_SELECT_USER_BY_LOGIN_PASS = "SELECT * FROM user  WHERE login=? and password=?;";

	@Override
	public void add(User entity) throws DAOException {
		logger.debug("add() - user = {}", entity);
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_ADD_USER);
			ps.setString(1, entity.getLogin());
			ps.setString(2, entity.getPassword());
			ps.setInt(3, entity.getDivision().getIdDivision());
			ps.setString(4, entity.getAccess().toString().toLowerCase().replace('_', ' '));
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild create new user: ", e);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					logger.debug("CloseConnection()");
					conn.close();
				}
			} catch (SQLException e) {
				logger.error("DAO layer: Faild to close connection or ps", e);
			}
		}

	}

	@Override
	public void update(User entity) throws DAOException {
		logger.debug("update() - user = {}", entity);
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_UPDATE_USER);
			ps.setString(1, entity.getLogin());
			ps.setString(2, entity.getPassword());
			ps.setInt(3, entity.getIdUser());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild to update user: ", e);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					logger.debug("CloseConnection()");
					conn.close();
				}
			} catch (SQLException e) {
				logger.error("DAO layer: Faild to close connection or ps", e);
			}
		}
	}

	@Override
	public void delete(int id) throws DAOException {
		logger.debug("delete() - idUser = {}", id);
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_DELETE_USER);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild to delete user: ", e);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					logger.debug("CloseConnection()");
					conn.close();
				}
			} catch (SQLException e) {
				logger.error("DAO layer: Faild to close connection or ps", e);
			}
		}

	}

	@Override
	public void addAdmin(User user) throws DAOException {
		logger.debug("addAdmin() - user = {}", user);
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_ADD_ADMIN);
			ps.setString(1, user.getLogin());
			ps.setString(2, user.getPassword());
			ps.setInt(3, user.getDivision().getIdDivision());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild create new admin: ", e);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					logger.debug("CloseConnection()");
					conn.close();
				}
			} catch (SQLException e) {
				logger.error("DAO layer: Faild to close connection or ps", e);
			}
		}

	}

	@Override
	public User userLogin(String login, String password) throws DAOException {
		logger.debug("userlogin() - login = {}, password = {}", login, password);
		User user = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_LOGIN_USER);
			ps.setString(1, login);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				User loginUser = new User();
				loginUser.setLogin(rs.getString(1));
				loginUser.setRole(rs.getString(2));
				Division division = new Division();
				division.setIdDivision(rs.getInt(3));
				loginUser.setAccess(Access.valueOf(rs.getString(4).toUpperCase().replace(' ', '_')));
				division.setName(rs.getString(5));
				division.setUNN(rs.getString(6));
				loginUser.setDivision(division);
				user = loginUser;
			}
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild to find user: ", e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					logger.debug("CloseConnection()");
					conn.close();
				}
			} catch (SQLException e) {
				logger.error("DAO layer: Faild to close connection or ps or rs", e);
			}
		}
		return user;
	}

	@Override
	public User userLoginRootAdmin(String login, String password) throws DAOException {
		logger.debug("userloginRootAdmin() - login = {}, password = {}", login, password);
		User user = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_USER_EXIST);
			ps.setString(1, login);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				User loginUser = new User();
				loginUser.setIdUser(rs.getInt(1));
				loginUser.setLogin(rs.getString(2));
				loginUser.setPassword(rs.getString(3));
				loginUser.setRole(rs.getString(4));
				loginUser.setAccess(Access.valueOf(rs.getString(5).toUpperCase().replace(' ', '_')));
				user = loginUser;
			}
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild to find root admin: ", e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					logger.debug("CloseConnection()");
					conn.close();
				}
			} catch (SQLException e) {
				logger.error("DAO layer: Faild to close connection or ps or rs", e);
			}
		}
		return user;
	}

	@Override
	public boolean userExist(String login, String password) throws DAOException {
		logger.debug("userExist() login={}, passwor={}", login, password);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_USER_EXIST);
			ps.setString(1, login);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				logger.debug("true");
				return true;
			} else
				logger.debug("false");
			return false;
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild to find user: ", e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (ps != null) {
					ps.close();
				}
				if (conn != null)
					logger.debug("CloseConnection()");
				{
					conn.close();
				}
			} catch (SQLException e) {
				logger.error("DAO layer: Faild to close connection or ps or rs", e);
			}
		}
	}

	@Override
	public List<User> selectAllUserByIdDivision(int idDivision) throws DAOException {
		logger.debug("selectAllUserByIdDivision() idDivision={}", idDivision);
		List<User> user = new ArrayList<User>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_SELECT_ALL_USER_BY_ID_DIVISION);
			ps.setInt(1, idDivision);
			rs = ps.executeQuery();
			while (rs.next()) {
				user.add(getUserFromResultSet(rs));
			}
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild to find user by id division: ", e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					logger.debug("CloseConnection()");
					conn.close();
				}
			} catch (SQLException e) {
				logger.error("DAO layer: Faild to close connection or ps or rs", e);
			}

		}

		logger.debug("selectAllUserByIdDivision() user={}", user);
		return user;
	}

	@Override
	public List<User> selectUsersByIdDivision(int idDivision) throws DAOException {
		logger.debug("selectUsersByIdDivision() idDivision={}", idDivision);
		List<User> user = new ArrayList<User>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_SELECT_USERS_BY_ID_DIVISION);
			ps.setInt(1, idDivision);
			rs = ps.executeQuery();
			while (rs.next()) {
				user.add(getUserFromResultSet(rs));
			}
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild to find user by id division: ", e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					logger.debug("CloseConnection()");
					conn.close();
				}
			} catch (SQLException e) {
				logger.error("DAO layer: Faild to close connection or ps or rs", e);
			}

		}

		logger.debug("selectUsersByIdDivision() user={}", user);
		return user;
	}

	@Override
	public int selectCountAdminInDivision(int idDivision) throws DAOException {
		logger.debug("selectCountAdminInDivision() idDivision={}", idDivision);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int countAdmin = 0;
		try {
			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_SELECT_COUNT_ADMIN_BY_ID_DIVISION);
			ps.setInt(1, idDivision);
			rs = ps.executeQuery();
			if (rs.next()) {
				countAdmin = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild to find count: ", e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					logger.debug("CloseConnection()");
					conn.close();
				}
			} catch (SQLException e) {
				logger.error("DAO layer: Faild to close connection or ps or rs", e);
			}
		}

		return countAdmin;

	}

	@Override
	public User selectUserByLoginAndPass(String login, String password) throws DAOException {
		logger.debug("selectUserByLoginAndPass() - login = {}, password = {}", login, password);
		User user = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_SELECT_USER_BY_LOGIN_PASS);
			ps.setString(1, login);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = getUserFromResultSet(rs);
			}
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild to find user by login and password: ", e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					logger.debug("CloseConnection()");
					conn.close();
				}
			} catch (SQLException e) {
				logger.error("DAO layer: Faild to close connection or ps or rs", e);
			}
		}
		return user;
	}

	private User getUserFromResultSet(ResultSet set) throws SQLException {
		User user = new User();
		user.setIdUser(set.getInt(1));
		user.setLogin(set.getString(2));
		user.setPassword(set.getString(3));
		user.setRole(set.getString(4));
		user.setAccess(Access.valueOf(set.getString(5).toUpperCase().replace(' ', '_')));
		Division division = new Division();
		division.setIdDivision(set.getInt(6));
		user.setDivision(division);
		return user;

	}

}
