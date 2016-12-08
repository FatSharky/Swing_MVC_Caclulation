package by.bsk.oracle.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bsk.oracle.dao.RecipeDAO;
import by.bsk.oracle.dao.exception.DAOException;
import by.bsk.oracle.dao.util.DBUtil;
import by.bsk.oracle.domain.Recipe;

public class DBRecipeDAO implements RecipeDAO {
	private static final Logger logger = LogManager.getLogger(DBRecipeDAO.class);
	private static final String SQL_ADD_RECIPE = "INSERT INTO recipe (name, id_division) VALUES (?, ?);";
	private static final String SQL_UPDATE_RECIPE = "UPDATE recipe SET name=? WHERE id_product=?;";
	private static final String SQL_DELETE_RECIPE = "DELETE FROM recipe WHERE id_product=?;";
	private static final String SQL_SELECT_RECIPE_BY_ID_DIVISION = "SELECT * FROM recipe WHERE id_division=?;";

	@Override
	public void add(Recipe entity) throws DAOException {
		logger.debug("add() - recipe= {}", entity);
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_ADD_RECIPE);
			ps.setString(1, entity.getName());
			ps.setInt(2, entity.getIdDivision());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild create new recipe: ", e);
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
	public void update(Recipe entity) throws DAOException {
		logger.debug("update() - recipe = {}", entity);
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_UPDATE_RECIPE);
			ps.setString(1, entity.getName());
			ps.setInt(2, entity.getIdRecipe());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild to update recipe: ", e);
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
		logger.debug("delete() - idRecipe = {}", id);
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_DELETE_RECIPE);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild to delete recipe: ", e);
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
	public List<Recipe> selectRecipeByIdDivision(int idDivision) throws DAOException {
		logger.debug("selectReicpeByIdDivision() idDivision={}", idDivision);
		List<Recipe> recipes = new ArrayList<Recipe>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			conn = DBUtil.getInstance().openConnection();
			ps = conn.prepareStatement(SQL_SELECT_RECIPE_BY_ID_DIVISION);
			ps.setInt(1, idDivision);
			rs = ps.executeQuery();
			while (rs.next()) {
				recipes.add(getRecipeFromResultSet(rs));
			}
		} catch (SQLException e) {
			throw new DAOException("DAO layer: Faild to find recipe by id division: ", e);
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
		return recipes;

	}

	private Recipe getRecipeFromResultSet(ResultSet set) throws SQLException {
		Recipe recipe = new Recipe();
		recipe.setIdRecipe(set.getInt(1));
		recipe.setName(set.getString(2));
		recipe.setIdDivision(set.getInt(3));
		return recipe;
	}
}
