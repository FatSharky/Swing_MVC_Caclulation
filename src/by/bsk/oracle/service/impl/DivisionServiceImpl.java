package by.bsk.oracle.service.impl;

import java.util.List;

import by.bsk.oracle.dao.DivisionDAO;
import by.bsk.oracle.dao.exception.DAOException;
import by.bsk.oracle.dao.factory.DAOFactory;
import by.bsk.oracle.domain.Division;
import by.bsk.oracle.service.DivisionService;
import by.bsk.oracle.service.exception.ServiceException;

public class DivisionServiceImpl implements DivisionService {

	@Override
	public void addDivision(String name, String unn) throws ServiceException {
		Division division = null;
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			DivisionDAO divisionDAO = daoFactory.getDivisionDAO();
			division = new Division();
			division.setName(name);
			division.setUNN(unn);
			divisionDAO.add(division);
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can't do add division operation", e);
		}
	}

	@Override
	public void deleteDivision(String idDivision) throws ServiceException {
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			DivisionDAO divisionDAO = daoFactory.getDivisionDAO();
			divisionDAO.delete(Integer.valueOf(idDivision));
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can't do delete division operation", e);
		}
	}

	@Override
	public List<Division> selectAllDivision() throws ServiceException {
		List<Division> division = null;
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			DivisionDAO divisionDAO = daoFactory.getDivisionDAO();
			division = divisionDAO.selectAllDivision();
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can't do select all user", e);
		}
		return division;
	}

}
