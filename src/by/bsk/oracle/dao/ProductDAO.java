package by.bsk.oracle.dao;

import java.util.List;

import by.bsk.oracle.dao.exception.DAOException;
import by.bsk.oracle.domain.Product;

public interface ProductDAO extends AbstractDAO<Product> {

	List<Product> selectListProductByIdUnit(int idUnit) throws DAOException;

}
