package by.bsk.oracle.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bsk.oracle.dao.PriceCategoryDAO;
import by.bsk.oracle.dao.exception.DAOException;
import by.bsk.oracle.dao.factory.DAOFactory;
import by.bsk.oracle.domain.PriceCategory;
import by.bsk.oracle.service.PriceCategorySevice;
import by.bsk.oracle.service.exception.ServiceException;

public class PriceCategoryServiceImpl implements PriceCategorySevice {
	private static final Logger logger = LogManager.getLogger(PriceCategoryServiceImpl.class);

	@Override
	public void addCategory(String name, String idDivision) throws ServiceException {
		try {
			logger.debug("addCategory() name={}, idDivision={}", name, idDivision);
			DAOFactory daoFactory = DAOFactory.getInstance();
			PriceCategoryDAO priceCategoryDAO = daoFactory.getPriceCategoryDAO();
			PriceCategory category = new PriceCategory();
			category.setIdDivision(Integer.valueOf(idDivision));
			category.setName(name);
			priceCategoryDAO.add(category);
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can't do add category operation", e);
		}

	}

	@Override
	public void updateCategory(String name, String idCategory) throws ServiceException {
		try {
			logger.debug("updateCategory() name={}, idDivision={}", name, idCategory);
			DAOFactory daoFactory = DAOFactory.getInstance();
			PriceCategoryDAO priceCategoryDAO = daoFactory.getPriceCategoryDAO();
			PriceCategory category = new PriceCategory();
			category.setName(name);
			category.setIdDivision(Integer.valueOf(idCategory));
			priceCategoryDAO.update(category);
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can't do update category operation", e);
		}
	}

	@Override
	public void deleteCategory(String idPriceCategory) throws ServiceException {
		try {
			logger.debug("deleteUser() idPriceCategory={}", idPriceCategory);
			DAOFactory daoFactory = DAOFactory.getInstance();
			PriceCategoryDAO priceCategoryDAO = daoFactory.getPriceCategoryDAO();
			priceCategoryDAO.delete(Integer.valueOf(idPriceCategory));
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can't do delete category operation", e);
		}

	}

	@Override
	public List<PriceCategory> selectCategoryByIdDivision(int idDivision) throws ServiceException {
		List<PriceCategory> category = null;
		try {
			logger.debug("selectCategoryByIdDivision() idDivision={}", idDivision);
			DAOFactory daoFactory = DAOFactory.getInstance();
			PriceCategoryDAO priceCategoryDAO = daoFactory.getPriceCategoryDAO();
			category = priceCategoryDAO.selectPriceCategoryByIdDivision(idDivision);
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can't do select categories by id divison operation", e);
		}
		return category;
	}

}
