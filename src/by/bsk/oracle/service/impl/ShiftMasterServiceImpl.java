package by.bsk.oracle.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bsk.oracle.dao.ShiftMasterDAO;
import by.bsk.oracle.dao.exception.DAOException;
import by.bsk.oracle.dao.factory.DAOFactory;
import by.bsk.oracle.domain.ShiftMaster;
import by.bsk.oracle.service.ShiftMasterService;
import by.bsk.oracle.service.exception.ServiceException;

public class ShiftMasterServiceImpl implements ShiftMasterService {
	private static final Logger logger = LogManager.getLogger(ShiftMasterServiceImpl.class);

	@Override
	public void addShiftMaster(String fio, int idUnit) throws ServiceException {
		try {
			logger.debug("addShiftMaster() fio={}, idUnit={}", fio, idUnit);
			DAOFactory daoFactory = DAOFactory.getInstance();
			ShiftMasterDAO shiftMasterDAO = daoFactory.getShiftMasterDAO();
			ShiftMaster master = new ShiftMaster();
			master.setFio(fio);
			master.setIdUnit(idUnit);
			shiftMasterDAO.add(master);
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can't do add shift master operation", e);
		}

	}

	@Override
	public void updateShiftMaster(String fio, int idMaster) throws ServiceException {
		try {
			logger.debug("updateShiftMaster() fio={}, idMaster={}", fio, idMaster);
			DAOFactory daoFactory = DAOFactory.getInstance();
			ShiftMasterDAO shiftMasterDAO = daoFactory.getShiftMasterDAO();
			ShiftMaster master = new ShiftMaster();
			master.setFio(fio);
			master.setIdMaster(idMaster);
			shiftMasterDAO.add(master);
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can't do update shift master operation", e);
		}
	}

	@Override
	public void deleteShiftMaster(int idMaster) throws ServiceException {
		try {
			logger.debug("deleteShiftMaster() idMaster={}", idMaster);
			DAOFactory daoFactory = DAOFactory.getInstance();
			ShiftMasterDAO shiftMasterDAO = daoFactory.getShiftMasterDAO();
			shiftMasterDAO.delete(idMaster);
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can't do delete shift master operation", e);
		}
	}

	@Override
	public List<ShiftMaster> selectShiftMasterByIdUnit(int idUnit) throws ServiceException {
		List<ShiftMaster> shiftMasters = null;
		try {
			logger.debug("selectShiftMasterByIdUnit() idUnit={}", idUnit);
			DAOFactory daoFactory = DAOFactory.getInstance();
			ShiftMasterDAO shiftMasterDAO = daoFactory.getShiftMasterDAO();
			shiftMasters = shiftMasterDAO.selectMasterByIdStructuralUnit(idUnit);
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can't do select shift master by id structural unit", e);
		}
		return shiftMasters;
	}

}
