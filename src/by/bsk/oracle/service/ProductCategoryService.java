package by.bsk.oracle.service;

import java.util.List;

import by.bsk.oracle.domain.ProductCategory;
import by.bsk.oracle.service.exception.ServiceException;

public interface ProductCategoryService {
	
	void addProductCategory(String name, int idDivision) throws ServiceException;

	void updateProductCategory(String name, int idCategory) throws ServiceException;

	void deleteProductCategory(int idCategory) throws ServiceException;

	List<ProductCategory> listCategories(int idDivision) throws ServiceException;
}
