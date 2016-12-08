package by.bsk.oracle.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bsk.oracle.dao.ProductDAO;
import by.bsk.oracle.dao.exception.DAOException;
import by.bsk.oracle.dao.util.DBUtil;
import by.bsk.oracle.domain.Product;

public class DBProductDAO implements ProductDAO {
	private static final Logger logger = LogManager.getLogger(DBProductDAO.class);
	private static final String SQL_ADD_PRODUCT = "INSERT INTO product (name, id_units) VALUES (?, ?);";
	private static final String SQL_UPDATE_PRODUCT = "UPDATE product SET name=? WHERE id_product=?;";
	private static final String SQL_DELETE_PRODUCT = "DELETE FROM product WHERE id_product=?;";
	private static final String SQL_SELECT_PRODUCT_BY_ID_UNIT = "SELECT * FROM product WHERE id_units=?;";

	@Override
	public void add(Product entity) throws DAOException {
		logger.debug("add() - product = {}", entity);
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_ADD_PRODUCT);
			ps.setString(1, entity.getName());
			ps.setInt(2, entity.getIdUnit());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild create new product: ", e);
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
	public void update(Product entity) throws DAOException {
		logger.debug("update() - product = {}", entity);
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_UPDATE_PRODUCT);
			ps.setString(1, entity.getName());
			ps.setInt(2, entity.getIdProduct());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild to update product: ", e);
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
		logger.debug("delete() - idProduct = {}", id);
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_DELETE_PRODUCT);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild to delete product: ", e);
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
	public List<Product> selectListProductByIdUnit(int idUnit) throws DAOException {
		logger.debug("selectListProductByIdUnit() idUnit={}", idUnit);
		List<Product> products = new ArrayList<Product>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_SELECT_PRODUCT_BY_ID_UNIT);
			ps.setInt(1, idUnit);
			rs = ps.executeQuery();
			while (rs.next()) {
				products.add(getProductFromResultSet(rs));
			}
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild to find product by id user: ", e);
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
		return products;

	}

	private Product getProductFromResultSet(ResultSet set) throws SQLException {
		Product product = new Product();
		product.setIdProduct(set.getInt(1));
		product.setName(set.getString(2));
		product.setIdUnit(set.getInt(3));
		return product;
	}

}
