package by.bsk.oracle.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bsk.oracle.dao.ProductDAO;
import by.bsk.oracle.dao.exception.DAOException;
import by.bsk.oracle.dao.factory.DAOFactory;
import by.bsk.oracle.domain.Product;
import by.bsk.oracle.service.ProductService;
import by.bsk.oracle.service.exception.ServiceException;

public class ProductServiceImpl implements ProductService {
	private static final Logger logger = LogManager.getLogger(ProductServiceImpl.class);

	@Override
	public void addProduct(String name, int idUnit) throws ServiceException {
		try {
			logger.debug("addProduct() name={}, idUnit={}", name, idUnit);
			DAOFactory daoFactory = DAOFactory.getInstance();
			ProductDAO productDAO = daoFactory.getProductDAO();
			Product product = new Product();
			product.setIdUnit(idUnit);
			product.setName(name);
			productDAO.add(product);
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can't do add product operation", e);
		}
	}

	@Override
	public void updateProduct(String name, String idProduct) throws ServiceException {
		try {
			logger.debug("updateProduct() name={}, idProduct={}", name, idProduct);
			DAOFactory daoFactory = DAOFactory.getInstance();
			ProductDAO productDAO = daoFactory.getProductDAO();
			Product product = new Product();
			product.setIdProduct(Integer.valueOf(idProduct));
			product.setName(name);
			productDAO.update(product);
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can't do update product operation", e);
		}
	}

	@Override
	public void deleteProduct(String idProduct) throws ServiceException {
		try {
			logger.debug("updateProduct() idProduct={}", idProduct);
			DAOFactory daoFactory = DAOFactory.getInstance();
			ProductDAO productDAO = daoFactory.getProductDAO();
			productDAO.delete(Integer.valueOf(idProduct));
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can't do delete product operation", e);
		}
	}

	@Override
	public List<Product> listProduct(int idUnit) throws ServiceException {
		List<Product> products = null;
		try {
			logger.debug("listProduct() idUnit={}", idUnit);
			DAOFactory daoFactory = DAOFactory.getInstance();
			ProductDAO productDAO = daoFactory.getProductDAO();
			products = productDAO.selectListProductByIdUnit(idUnit);
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can't do select product by id unit operation", e);
		}
		return products;
	}

}
