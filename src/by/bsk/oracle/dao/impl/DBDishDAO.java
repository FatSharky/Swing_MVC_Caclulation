package by.bsk.oracle.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bsk.oracle.dao.DishDAO;
import by.bsk.oracle.dao.exception.DAOException;
import by.bsk.oracle.dao.util.DBUtil;
import by.bsk.oracle.domain.Dish;

public class DBDishDAO implements DishDAO {
	private static final Logger logger = LogManager.getLogger(DBDishDAO.class);

	private static final String SQL_ADD_DISH = "INSERT INTO dish (name, id_division) VALUES (?, ?);";
	private static final String SQL_UPDATE_DISH = "UPDATE dish SET name=? WHERE id_dish=?;";
	private static final String SQL_DELETE_DISH = "DELETE FROM dish WHERE id_dish=?;";
	private static final String SQL_SELECT_DISH_BY_ID_DIVISION = "SELECT * FROM dish WHERE id_division=?;";

	@Override
	public void add(Dish entity) throws DAOException {
		logger.debug("add() - dish = {}", entity);
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_ADD_DISH);
			ps.setString(1, entity.getName());
			ps.setInt(2, entity.getIdDivision());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild create new dish: ", e);
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
	public void update(Dish entity) throws DAOException {
		logger.debug("add() - dish = {}", entity);
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_UPDATE_DISH);
			ps.setString(1, entity.getName());
			ps.setInt(2, entity.getIdDish());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild to udpate dish: ", e);
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
		logger.debug("delete() - idDish = {}", id);
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_DELETE_DISH);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild to delete dish: ", e);
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
	public List<Dish> selectDishByIdDivision(int idDivision) throws DAOException {
		logger.debug("selectDishByIdStructUnit() idDivision={}", idDivision);
		List<Dish> dishes = new ArrayList<Dish>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_SELECT_DISH_BY_ID_DIVISION);
			ps.setInt(1, idDivision);
			rs = ps.executeQuery();
			while (rs.next()) {
				dishes.add(getDishFromResultSet(rs));
			}
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild to find dish by id division: ", e);
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
		return dishes;

	}

	private Dish getDishFromResultSet(ResultSet set) throws SQLException {
		Dish dish = new Dish();
		dish.setIdDish(set.getInt(1));
		dish.setName(set.getString(2));
		dish.setIdDivision(set.getInt(3));
		return dish;
	}

}
