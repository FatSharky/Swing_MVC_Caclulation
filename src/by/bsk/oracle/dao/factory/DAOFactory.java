package by.bsk.oracle.dao.factory;

import by.bsk.oracle.dao.DishDAO;
import by.bsk.oracle.dao.DivisionDAO;
import by.bsk.oracle.dao.PriceCategoryDAO;
import by.bsk.oracle.dao.ProductDAO;
import by.bsk.oracle.dao.RecipeDAO;
import by.bsk.oracle.dao.ShiftMasterDAO;
import by.bsk.oracle.dao.StructuralUnitDAO;
import by.bsk.oracle.dao.UserDAO;

public abstract class DAOFactory {
	private static DAOFactory sqlFactory;

	public abstract UserDAO getUserDAO();

	public abstract DivisionDAO getDivisionDAO();

	public abstract PriceCategoryDAO getPriceCategoryDAO();

	public abstract RecipeDAO getRecipeDAO();

	public abstract StructuralUnitDAO getStructuralUnitDAO();

	public abstract ShiftMasterDAO getShiftMasterDAO();

	public abstract DishDAO getDishDAO();
	
	public abstract ProductDAO getProductDAO();

	public static DAOFactory getInstance() {
		if (sqlFactory == null) {
			synchronized (DAOFactory.class) {
				if (sqlFactory == null) {
					sqlFactory = new SQLDAOFactory();
				}
			}
		}
		return sqlFactory;
	}
}
