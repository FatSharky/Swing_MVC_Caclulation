package by.bsk.oracle.dao;

import java.util.List;

import by.bsk.oracle.dao.exception.DAOException;
import by.bsk.oracle.domain.Recipe;

public interface RecipeDAO extends AbstractDAO<Recipe> {
	List<Recipe> selectRecipeByIdDivision(int idDivision) throws DAOException;
}
