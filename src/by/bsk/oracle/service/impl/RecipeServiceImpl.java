package by.bsk.oracle.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bsk.oracle.dao.RecipeDAO;
import by.bsk.oracle.dao.exception.DAOException;
import by.bsk.oracle.dao.factory.DAOFactory;
import by.bsk.oracle.domain.Recipe;
import by.bsk.oracle.service.RecipeService;
import by.bsk.oracle.service.exception.ServiceException;

public class RecipeServiceImpl implements RecipeService {
	private static final Logger logger = LogManager.getLogger(RecipeServiceImpl.class);

	@Override
	public void addProductCategory(String name, int idDivision) throws ServiceException {
		try {
			logger.debug("addProductCategory() name={}, idDivision={}", name, idDivision);
			DAOFactory daoFactory = DAOFactory.getInstance();
			RecipeDAO productCategory = daoFactory.getRecipeDAO();
			Recipe product = new Recipe();
			product.setIdDivision(idDivision);
			product.setName(name);
			productCategory.add(product);
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can't do add product category category operation", e);
		}
	}

	@Override
	public void updateProductCategory(String name, int idCategory) throws ServiceException {
		try {
			logger.debug("updateProductCategory() name={}, idProductCategory={}", name, idCategory);
			DAOFactory daoFactory = DAOFactory.getInstance();
			RecipeDAO productCategory = daoFactory.getRecipeDAO();
			Recipe product = new Recipe();
			product.setIdRecipe(idCategory);
			product.setName(name);
			productCategory.add(product);
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can't do update product category category operation", e);
		}
	}

	@Override
	public void deleteProductCategory(int idCategory) throws ServiceException {
		try {
			logger.debug("deleteProductCategory() idProductCategory={}", idCategory);
			DAOFactory daoFactory = DAOFactory.getInstance();
			RecipeDAO productCategory = daoFactory.getRecipeDAO();
			productCategory.delete(idCategory);
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can't do delete product category category operation", e);
		}
	}

	@Override
	public List<Recipe> listCategories(int idDivision) throws ServiceException {
		List<Recipe> category = null;
		try {
			logger.debug(" listCategories() idDivision={}", idDivision);
			DAOFactory daoFactory = DAOFactory.getInstance();
			RecipeDAO productCategory = daoFactory.getRecipeDAO();
			category = productCategory.selectRecipeByIdDivision(idDivision);
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can't do select list categories operation", e);
		}
		return category;
	}

}
