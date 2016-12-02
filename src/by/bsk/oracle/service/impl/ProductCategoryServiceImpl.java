package by.bsk.oracle.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bsk.oracle.dao.ProductCategoryDAO;
import by.bsk.oracle.dao.exception.DAOException;
import by.bsk.oracle.dao.factory.DAOFactory;
import by.bsk.oracle.domain.ProductCategory;
import by.bsk.oracle.service.ProductCategoryService;
import by.bsk.oracle.service.exception.ServiceException;

public class ProductCategoryServiceImpl implements ProductCategoryService {
	private static final Logger logger = LogManager.getLogger(ProductCategoryServiceImpl.class);

	@Override
	public void addProductCategory(String name, int idDivision) throws ServiceException {
		try {
			logger.debug("addProductCategory() name={}, idDivision={}", name, idDivision);
			DAOFactory daoFactory = DAOFactory.getInstance();
			ProductCategoryDAO productCategory = daoFactory.getProductCategoryDAO();
			ProductCategory product = new ProductCategory();
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
			ProductCategoryDAO productCategory = daoFactory.getProductCategoryDAO();
			ProductCategory product = new ProductCategory();
			product.setIdProcduct(idCategory);
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
			ProductCategoryDAO productCategory = daoFactory.getProductCategoryDAO();
			productCategory.delete(idCategory);
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can't do delete product category category operation", e);
		}
	}

	@Override
	public List<ProductCategory> listCategories(int idDivision) throws ServiceException {
		List<ProductCategory> category = null;
		try {
			logger.debug(" listCategories() idDivision={}", idDivision);
			DAOFactory daoFactory = DAOFactory.getInstance();
			ProductCategoryDAO productCategory = daoFactory.getProductCategoryDAO();
			category = productCategory.selectProdCategoryByIdDivision(idDivision);
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can't do select list categories operation", e);
		}
		return category;
	}

}
