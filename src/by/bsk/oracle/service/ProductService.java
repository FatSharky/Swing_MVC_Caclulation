package by.bsk.oracle.service;

import java.util.List;

import by.bsk.oracle.domain.Product;
import by.bsk.oracle.service.exception.ServiceException;

public interface ProductService {
	void addProduct(String name, int idUnit) throws ServiceException;

	void updateProduct(String name, String idProduct) throws ServiceException;

	void deleteProduct(String idProduct) throws ServiceException;

	List<Product> listProduct(int idUnit) throws ServiceException;

}
