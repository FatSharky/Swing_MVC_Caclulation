package by.bsk.oracle.dao;

import java.util.List;

import by.bsk.oracle.dao.exception.DAOException;
import by.bsk.oracle.domain.StructuralUnit;

public interface StructuralUnitDAO extends AbstractDAO<StructuralUnit> {
	
	List<StructuralUnit> selectStrucUnitsByIdProduct(int idProduct) throws DAOException;
}
