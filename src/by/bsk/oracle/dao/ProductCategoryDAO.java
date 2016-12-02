package by.bsk.oracle.dao;

import java.util.List;

import by.bsk.oracle.dao.exception.DAOException;
import by.bsk.oracle.domain.ProductCategory;

public interface ProductCategoryDAO extends AbstractDAO<ProductCategory> {
	List<ProductCategory> selectProdCategoryByIdDivision(int idDivision) throws DAOException;
}
