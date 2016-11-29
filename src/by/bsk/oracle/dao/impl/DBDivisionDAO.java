package by.bsk.oracle.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bsk.oracle.dao.DivisionDAO;
import by.bsk.oracle.dao.exception.DAOException;
import by.bsk.oracle.dao.util.DBUtil;
import by.bsk.oracle.domain.Division;

/**
 * Class {@code DBDivisionDAO} реализует интерфейс
 * {@link by.bsk.oracle.dao.DivisionDAO DivisionDAO} для базы данных Oracle.
 * 
 * @author Vladislav
 *
 */
public class DBDivisionDAO implements DivisionDAO {

	private static final Logger logger = LogManager.getLogger(DBDivisionDAO.class);

	private static final String SQL_ADD_DIVISION = "INSERT INTO division (name, unn) VALUES (?, ?);";
	private static final String SQL_UPDATE_DIVISION = "UPDATE division SET name= ?, unn= ? WHERE id_division=?;";
	private static final String SQL_DELETE_DIVISION = "DELETE FROM division WHERE id_division=?;";
	private static final String SQL_SELECT_ALL_DIVISION = "SELECT * FROM division;";
	private static final String SQL_SELECT_DIVISION_BY_ID = "SELECT * FROM division where id_division=?;";

	@Override
	public void add(Division entity) throws DAOException {
		logger.debug("add() - division = {}", entity);
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_ADD_DIVISION);
			ps.setString(1, entity.getName());
			ps.setString(2, entity.getUNN());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild create new division: ", e);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				logger.error("DAO layer: Faild to close connection or ps", e);
			}
		}

	}

	@Override
	public void update(Division entity) throws DAOException {
		logger.debug("division = {}", entity);
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_UPDATE_DIVISION);
			ps.setString(1, entity.getName());
			ps.setString(2, entity.getUNN());
			ps.setInt(3, entity.getIdDivision());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild to update division: ", e);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				logger.error("DAO layer: Faild to close connection or ps", e);
			}
		}
	}

	@Override
	public void delete(int id) throws DAOException {
		logger.debug("delete() - idDivision = {}", id);
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_DELETE_DIVISION);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild to delete division: ", e);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				logger.error("DAO layer: Faild to close connection or ps", e);
			}
		}
	}

	@Override
	public List<Division> selectAllDivision() throws DAOException {
		logger.debug("selectAllDivision()");
		List<Division> division = new ArrayList<Division>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_SELECT_ALL_DIVISION);
			rs = ps.executeQuery();
			while (rs.next()) {
				division.add(getDivisionFromResultSet(rs));
			}
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild to find all divisions: ", e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				logger.error("DAO layer: Faild to close connection or ps or rs", e);
			}

		}

		return division;

	}

	@Override
	public Division selectDivisionById(int idDivision) throws DAOException {
		logger.debug("selectDivisionById() - idDivision = {}", idDivision);
		Division division = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_SELECT_DIVISION_BY_ID);
			ps.setInt(1, idDivision);
			rs = ps.executeQuery();
			if (rs.next()) {
				division = getDivisionFromResultSet(rs);
			}
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild to find division by id: ", e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				logger.error("DAO layer: Faild to close connection or ps or rs", e);
			}

		}

		return division;

	}

	private Division getDivisionFromResultSet(ResultSet set) throws SQLException {
		Division division = new Division();
		division.setIdDivision(set.getInt(1));
		division.setName(set.getString(2));
		division.setUNN(set.getString(3));
		return division;
	}

}
