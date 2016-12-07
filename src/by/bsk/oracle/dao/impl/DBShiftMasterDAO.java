package by.bsk.oracle.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bsk.oracle.dao.ShiftMasterDAO;
import by.bsk.oracle.dao.exception.DAOException;
import by.bsk.oracle.dao.util.DBUtil;
import by.bsk.oracle.domain.ShiftMaster;

public class DBShiftMasterDAO implements ShiftMasterDAO {
	private static final Logger logger = LogManager.getLogger(DBShiftMasterDAO.class);

	private static final String SQL_ADD_MASTER = "INSERT INTO shifts_master (FIO, id_units) VALUES (?, ?);";
	private static final String SQL_UPDATE_MASTER = "UPDATE shifts_master SET FIO=? WHERE id_master=?;";
	private static final String SQL_DELETE_MASTER = "DELETE FROM shifts_master WHERE id_master=?;";
	private static final String SQL_SELECT_MASTER_BY_UNIT = "SELECT * FROM shifts_master WHERE id_units=?";

	@Override
	public void add(ShiftMaster entity) throws DAOException {
		logger.debug("add() - master = {}", entity);
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_ADD_MASTER);
			ps.setString(1, entity.getFio());
			ps.setInt(2, entity.getIdUnit());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild create new shift master: ", e);
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
	public void update(ShiftMaster entity) throws DAOException {
		logger.debug("update() - master = {}", entity);
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_UPDATE_MASTER);
			ps.setString(1, entity.getFio());
			ps.setInt(2, entity.getIdMaster());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild to update shift master: ", e);
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
		logger.debug("delete() - idMaster = {}", id);
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_DELETE_MASTER);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild to delete shift master: ", e);
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
	public List<ShiftMaster> selectMasterByIdStructuralUnit(int idUnit) throws DAOException {
		logger.debug("selectMasterByIdStructuralUnit() idUnit={}", idUnit);
		List<ShiftMaster> shiftMasters = new ArrayList<ShiftMaster>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_SELECT_MASTER_BY_UNIT);
			ps.setInt(1, idUnit);
			rs = ps.executeQuery();
			while (rs.next()) {
				shiftMasters.add(getMasterFromResultSet(rs));
			}
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild to find shift master by id unit: ", e);
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
		return shiftMasters;

	}

	private ShiftMaster getMasterFromResultSet(ResultSet set) throws SQLException {
		ShiftMaster master = new ShiftMaster();
		master.setIdMaster(set.getInt(1));
		master.setFio(set.getString(2));
		master.setIdUnit(set.getInt(3));
		return master;
	}

}
