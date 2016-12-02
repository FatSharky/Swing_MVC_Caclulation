package by.bsk.oracle.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bsk.oracle.dao.ProductCategoryDAO;
import by.bsk.oracle.dao.exception.DAOException;
import by.bsk.oracle.dao.util.DBUtil;
import by.bsk.oracle.domain.ProductCategory;

public class DBProductCategoryDAO implements ProductCategoryDAO {
	private static final Logger logger = LogManager.getLogger(DBProductCategoryDAO.class);
	private static final String SQL_ADD_PRODUCT_CATEGORY = "INSERT INTO product_category (name, id_division) VALUES (?, ?);";
	private static final String SQL_UPDATE_PRODUCT_CATEGORY = "UPDATE product_category SET name=? WHERE id_product=?;";
	private static final String SQL_DELETE_PRODUCT_CATEGORY = "DELETE FROM product_category WHERE id_product=?;";
	private static final String SQL_SELECT_PRODUCT_BY_ID_DIVISION = "SELECT * FROM product_category WHERE id_division=?;";

	@Override
	public void add(ProductCategory entity) throws DAOException {
		logger.debug("add() - productCategory = {}", entity);
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_ADD_PRODUCT_CATEGORY);
			ps.setString(1, entity.getName());
			ps.setInt(2, entity.getIdDivision());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild create new product category: ", e);
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
	public void update(ProductCategory entity) throws DAOException {
		logger.debug("update() - productategory = {}", entity);
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_UPDATE_PRODUCT_CATEGORY);
			ps.setString(1, entity.getName());
			ps.setInt(2, entity.getIdProcduct());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild to update product category: ", e);
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
		logger.debug("delete() - idProductCategory = {}", id);
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_DELETE_PRODUCT_CATEGORY);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild to delete product category: ", e);
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
	public List<ProductCategory> selectProdCategoryByIdDivision(int idDivision) throws DAOException {
		logger.debug("selectPriceCategoryByIdDivision() idDivision={}", idDivision);
		List<ProductCategory> product = new ArrayList<ProductCategory>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_SELECT_PRODUCT_BY_ID_DIVISION);
			ps.setInt(1, idDivision);
			rs = ps.executeQuery();
			while (rs.next()) {
				product.add(getProductFromResultSet(rs));
			}
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild to find product category by id division: ", e);
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
		return product;

	}

	private ProductCategory getProductFromResultSet(ResultSet set) throws SQLException {
		ProductCategory product = new ProductCategory();
		product.setIdProcduct(set.getInt(1));
		product.setName(set.getString(2));
		product.setIdDivision(set.getInt(3));
		return product;
	}
}
