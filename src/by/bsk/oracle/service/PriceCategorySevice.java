package by.bsk.oracle.service;

import java.util.List;

import by.bsk.oracle.domain.PriceCategory;
import by.bsk.oracle.service.exception.ServiceException;

public interface PriceCategorySevice {

	void addCategory(String name, String idDivision) throws ServiceException;

	void updateCategory(String name, String idCategory) throws ServiceException;

	void deleteCategory(String idPriceCategory) throws ServiceException;

	List<PriceCategory> selectCategoryByIdDivision(int idDivision) throws ServiceException;

}
