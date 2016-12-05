package by.bsk.oracle.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bsk.oracle.dao.StructuralUnitDAO;
import by.bsk.oracle.dao.exception.DAOException;
import by.bsk.oracle.dao.util.DBUtil;
import by.bsk.oracle.domain.StructuralUnit;

public class DBStructuralUnitDAO implements StructuralUnitDAO {
	private static final Logger logger = LogManager.getLogger(DBStructuralUnitDAO.class);

	// private static final String SQL_ADD_UNIT = "INSERT INTO stuctural_units
	// (name, markup, tax, fare, discount, allowance, id_price) VALUES (?, ?, ?,
	// ?, ?, ?, ?);";
	private static final String SQL_ADD_UNIT_2 = "INSERT INTO stuctural_units (name, markup, tax, fare, discount, allowance, id_price) VALUES (?, ?, ?, ?, ?, ?, ?);";
	private static final String SQL_UPDATE_UNIT = "UPDATE stuctural_units SET name=?, markup=?, tax= ?, fare=?, discount=?, allowance= ? WHERE id_units=?;";
	private static final String SQL_DELETE_UNIT = "DELETE FROM stuctural_units WHERE id_units=?;";
	private static final String SQL_SELECT_UNIT_BY_ID_PRICE = "SELECT * FROM stuctural_units WHERE id_price=?;";

	@Override
	public void add(StructuralUnit entity) throws DAOException {
		logger.debug("add() - structuralUnit = {}", entity);
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_ADD_UNIT_2);
			ps.setString(1, entity.getName());
			ps.setDouble(2, entity.getMarkUp());
			ps.setDouble(3, entity.getTax());
			ps.setDouble(4, entity.getFare());
			ps.setDouble(5, entity.getDiscount());
			ps.setDouble(6, entity.getAllowance());
			ps.setInt(7, entity.getIdPrice());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild create new structural unit: ", e);
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
	public void update(StructuralUnit entity) throws DAOException {
		logger.debug("update() - structuralUnit = {}", entity);
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_UPDATE_UNIT);
			ps.setString(1, entity.getName());
			ps.setDouble(3, entity.getMarkUp());
			ps.setDouble(4, entity.getTax());
			ps.setDouble(5, entity.getFare());
			ps.setDouble(6, entity.getDiscount());
			ps.setDouble(7, entity.getAllowance());
			ps.setInt(8, entity.getIdUnit());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild to update structural unit: ", e);
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
		logger.debug("delete() - idStructuralUnit = {}", id);
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_DELETE_UNIT);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild to delete structural unit: ", e);
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
	public List<StructuralUnit> selectStrucUnitsByIdProduct(int idProduct) throws DAOException {
		logger.debug("selectStrucUnitsByIdProduct() idProduct={}", idProduct);
		List<StructuralUnit> unit = new ArrayList<StructuralUnit>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_SELECT_UNIT_BY_ID_PRICE);
			ps.setInt(1, idProduct);
			rs = ps.executeQuery();
			while (rs.next()) {
				unit.add(getUnitFromResultSet(rs));
			}
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild to find structural unit by id prouct: ", e);
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
		return unit;

	}

	private StructuralUnit getUnitFromResultSet(ResultSet set) throws SQLException {
		StructuralUnit unit = new StructuralUnit();
		unit.setIdUnit(set.getInt(1));
		unit.setName(set.getString(2));
		unit.setMarkUp(set.getDouble(3));
		unit.setTax(set.getDouble(4));
		unit.setFare(set.getDouble(5));
		unit.setDiscount(set.getDouble(6));
		unit.setAllowance(set.getDouble(7));
		unit.setIdPrice(set.getInt(8));
		return unit;
	}

}
