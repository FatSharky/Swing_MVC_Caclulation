package by.bsk.oracle.dao.factory;

import by.bsk.oracle.dao.DishDAO;
import by.bsk.oracle.dao.DivisionDAO;
import by.bsk.oracle.dao.PriceCategoryDAO;
import by.bsk.oracle.dao.ProductDAO;
import by.bsk.oracle.dao.RecipeDAO;
import by.bsk.oracle.dao.ShiftMasterDAO;
import by.bsk.oracle.dao.StructuralUnitDAO;
import by.bsk.oracle.dao.UserDAO;
import by.bsk.oracle.dao.impl.DBDishDAO;
import by.bsk.oracle.dao.impl.DBDivisionDAO;
import by.bsk.oracle.dao.impl.DBPriceCategoryDAO;
import by.bsk.oracle.dao.impl.DBProductDAO;
import by.bsk.oracle.dao.impl.DBRecipeDAO;
import by.bsk.oracle.dao.impl.DBShiftMasterDAO;
import by.bsk.oracle.dao.impl.DBStructuralUnitDAO;
import by.bsk.oracle.dao.impl.DBUserDAO;

public class SQLDAOFactory extends DAOFactory {

	private final UserDAO userDAO = new DBUserDAO();
	private final DivisionDAO divisionDAO = new DBDivisionDAO();
	private final PriceCategoryDAO priceCategoryDAO = new DBPriceCategoryDAO();
	private final RecipeDAO recipeDAO = new DBRecipeDAO();
	private final StructuralUnitDAO structuralUnitDAO = new DBStructuralUnitDAO();
	private final ShiftMasterDAO shiftMasterDAO = new DBShiftMasterDAO();
	private final DishDAO dishDAO = new DBDishDAO();
	private final ProductDAO productDAO = new DBProductDAO();

	@Override
	public UserDAO getUserDAO() {
		return userDAO;
	}

	@Override
	public DivisionDAO getDivisionDAO() {
		return divisionDAO;
	}

	@Override
	public PriceCategoryDAO getPriceCategoryDAO() {
		return priceCategoryDAO;
	}

	@Override
	public RecipeDAO getRecipeDAO() {
		return recipeDAO;
	}

	@Override
	public StructuralUnitDAO getStructuralUnitDAO() {
		return structuralUnitDAO;
	}

	@Override
	public ShiftMasterDAO getShiftMasterDAO() {
		return shiftMasterDAO;
	}

	@Override
	public DishDAO getDishDAO() {
		return dishDAO;
	}

	@Override
	public ProductDAO getProductDAO() {
		return productDAO;
	}

}
