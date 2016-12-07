package by.bsk.oracle.service;

import java.util.List;

import by.bsk.oracle.domain.ShiftMaster;
import by.bsk.oracle.service.exception.ServiceException;

public interface ShiftMasterService {

	void addShiftMaster(String fio, int idUnit) throws ServiceException;

	void updateShiftMaster(String fio, int idMaster) throws ServiceException;

	void deleteShiftMaster(int idMaster) throws ServiceException;

	List<ShiftMaster> selectShiftMasterByIdUnit(int idUnit) throws ServiceException;
}
