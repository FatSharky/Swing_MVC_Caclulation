package by.bsk.oracle.dao.factory;

import by.bsk.oracle.dao.DivisionDAO;
import by.bsk.oracle.dao.PriceCategoryDAO;
import by.bsk.oracle.dao.ProductCategoryDAO;
import by.bsk.oracle.dao.StructuralUnitDAO;
import by.bsk.oracle.dao.UserDAO;
import by.bsk.oracle.dao.impl.DBDivisionDAO;
import by.bsk.oracle.dao.impl.DBPriceCategoryDAO;
import by.bsk.oracle.dao.impl.DBProductCategoryDAO;
import by.bsk.oracle.dao.impl.DBStructuralUnitDAO;
import by.bsk.oracle.dao.impl.DBUserDAO;

public class SQLDAOFactory extends DAOFactory {

	private final UserDAO userDAO = new DBUserDAO();
	private final DivisionDAO divisionDAO = new DBDivisionDAO();
	private final PriceCategoryDAO priceCategoryDAO = new DBPriceCategoryDAO();
	private final ProductCategoryDAO productCategoryDAO = new DBProductCategoryDAO();
	private final StructuralUnitDAO structuralUnitDAO = new DBStructuralUnitDAO();

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
	public ProductCategoryDAO getProductCategoryDAO() {
		return productCategoryDAO;
	}

	@Override
	public StructuralUnitDAO getStructuralUnitDAO() {
		return structuralUnitDAO;
	}

}
