package by.bsk.oracle.dao.factory;

import by.bsk.oracle.dao.DivisionDAO;
import by.bsk.oracle.dao.PriceCategoryDAO;
import by.bsk.oracle.dao.ProductCategoryDAO;
import by.bsk.oracle.dao.StructuralUnitDAO;
import by.bsk.oracle.dao.UserDAO;

public abstract class DAOFactory {
	private static final DAOFactory SQL_DAOFACTORY = new SQLDAOFactory();

	public abstract UserDAO getUserDAO();

	public abstract DivisionDAO getDivisionDAO();

	public abstract PriceCategoryDAO getPriceCategoryDAO();

	public abstract ProductCategoryDAO getProductCategoryDAO();

	public abstract StructuralUnitDAO getStructuralUnitDAO();

	public static DAOFactory getInstance() {
		return SQL_DAOFACTORY;
	}

}
