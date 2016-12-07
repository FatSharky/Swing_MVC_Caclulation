package by.bsk.oracle.dao.factory;

import by.bsk.oracle.dao.DishDAO;
import by.bsk.oracle.dao.DivisionDAO;
import by.bsk.oracle.dao.PriceCategoryDAO;
import by.bsk.oracle.dao.ProductCategoryDAO;
import by.bsk.oracle.dao.ShiftMasterDAO;
import by.bsk.oracle.dao.StructuralUnitDAO;
import by.bsk.oracle.dao.UserDAO;

public abstract class DAOFactory {
	private static DAOFactory sqlFactory;

	public abstract UserDAO getUserDAO();

	public abstract DivisionDAO getDivisionDAO();

	public abstract PriceCategoryDAO getPriceCategoryDAO();

	public abstract ProductCategoryDAO getProductCategoryDAO();

	public abstract StructuralUnitDAO getStructuralUnitDAO();

	public abstract ShiftMasterDAO getShiftMasterDAO();

	public abstract DishDAO getDishDAO();

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
