package by.bsk.oracle.dao.factory;

import by.bsk.oracle.dao.DivisionDAO;
import by.bsk.oracle.dao.PriceCategoryDAO;
import by.bsk.oracle.dao.UserDAO;

public abstract class DAOFactory {
	private static final DAOFactory SQL_DAOFACTORY = new SQLDAOFactory();

	public abstract UserDAO getUserDAO();

	public abstract DivisionDAO getDivisionDAO();
	
	public abstract PriceCategoryDAO getPriceCategoryDAO();

	public static DAOFactory getInstance() {
		return SQL_DAOFACTORY;
	}

}
