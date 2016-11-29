package by.bsk.oracle.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bsk.oracle.dao.PriceCategoryDAO;
import by.bsk.oracle.dao.exception.DAOException;
import by.bsk.oracle.dao.util.DBUtil;
import by.bsk.oracle.domain.PriceCategory;

/**
 * Class {@code DBPriceCategoryDAO} реализует интерфейс
 * {@link by.bsk.oracle.dao.PriceCategoryDAO PriceCategoryDAO} для базы данных
 * Oracle.
 * 
 * @author Vladislav
 *
 */
public class DBPriceCategoryDAO implements PriceCategoryDAO {

	private static final Logger logger = LogManager.getLogger(DBPriceCategoryDAO.class);

	private static final String SQL_ADD_PRICE = "INSERT INTO price_category (name, id_division) VALUES (?, ?);";
	private static final String SQL_UPDATE_PRICE = "UPDATE price_category SET name= ? WHERE id_price= ?;";
	private static final String SQL_DELETE_PRICE = "DELETE FROM price_category WHERE id_price= ?;";
	private static final String SQL_SELECT_PRICE_BY_ID_DIVISION = "SELECT * FROM price_category WHERE id_division=?;";

	@Override
	public void add(PriceCategory entity) throws DAOException {
		logger.debug("add() - priceCategory = {}", entity);
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_ADD_PRICE);
			ps.setString(1, entity.getName());
			ps.setInt(2, entity.getIdDivision());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild create new price category: ", e);
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
	public void update(PriceCategory entity) throws DAOException {
		logger.debug("update() - priceCategory = {}", entity);
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_UPDATE_PRICE);
			ps.setString(1, entity.getName());
			ps.setInt(2, entity.getIdDivision());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild to update price category: ", e);
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
		logger.debug("delete() - idPriceCategory = {}", id);
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_DELETE_PRICE);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild to delete price category: ", e);
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
	public List<PriceCategory> selectPriceCategoryByIdDivision(int idDivision) throws DAOException {
		logger.debug("selectPriceCategoryByIdDivision() idDivision={}", idDivision);
		List<PriceCategory> price = new ArrayList<PriceCategory>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_SELECT_PRICE_BY_ID_DIVISION);
			ps.setInt(1, idDivision);
			rs = ps.executeQuery();
			while (rs.next()) {
				price.add(getPriceFromResultSet(rs));
			}
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild to find price category by id division: ", e);
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
		return price;

	}

	private PriceCategory getPriceFromResultSet(ResultSet set) throws SQLException {
		PriceCategory price = new PriceCategory();
		price.setIdPrice(set.getInt(1));
		price.setName(set.getString(2));
		price.setIdDivision(set.getInt(3));
		return price;
	}
}
