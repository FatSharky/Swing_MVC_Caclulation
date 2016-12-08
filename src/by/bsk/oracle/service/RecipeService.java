package by.bsk.oracle.service;

import java.util.List;

import by.bsk.oracle.domain.Recipe;
import by.bsk.oracle.service.exception.ServiceException;

public interface RecipeService {
	
	void addProductCategory(String name, int idDivision) throws ServiceException;

	void updateProductCategory(String name, int idCategory) throws ServiceException;

	void deleteProductCategory(int idCategory) throws ServiceException;

	List<Recipe> listCategories(int idDivision) throws ServiceException;
}
