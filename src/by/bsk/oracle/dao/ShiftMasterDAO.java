package by.bsk.oracle.dao;

import java.util.List;

import by.bsk.oracle.dao.exception.DAOException;
import by.bsk.oracle.domain.ShiftMaster;

public interface ShiftMasterDAO extends AbstractDAO<ShiftMaster> {
	List<ShiftMaster> selectMasterByIdProductCategory(int idProduct) throws DAOException;
}
