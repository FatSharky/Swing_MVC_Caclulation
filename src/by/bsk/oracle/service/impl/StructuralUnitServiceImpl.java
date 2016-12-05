package by.bsk.oracle.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bsk.oracle.dao.StructuralUnitDAO;
import by.bsk.oracle.dao.exception.DAOException;
import by.bsk.oracle.dao.factory.DAOFactory;
import by.bsk.oracle.domain.StructuralUnit;
import by.bsk.oracle.service.StructuralUnitService;
import by.bsk.oracle.service.exception.ServiceException;

public class StructuralUnitServiceImpl implements StructuralUnitService {
	private static final Logger logger = LogManager.getLogger(StructuralUnitServiceImpl.class);

	@Override
	public void addStructuralUnit(String name, String markUp, String tax, String fare, String discount,
			String allownance, String idPrice) throws ServiceException {
		try {
			logger.debug(
					"addStructuralUnit() name={}, markUp={}, tax={}, fare={}, discount={}, allownace={}, idPrice={}",
					name, markUp, tax, fare, discount, allownance, idPrice);
			DAOFactory daoFactory = DAOFactory.getInstance();
			StructuralUnitDAO structuralUnit = daoFactory.getStructuralUnitDAO();
			StructuralUnit unit = new StructuralUnit();
			unit.setName(name);
			unit.setMarkUp(Double.valueOf(markUp));
			unit.setTax(Double.valueOf(tax));
			unit.setFare(Double.valueOf(fare));
			unit.setDiscount(Double.valueOf(discount));
			unit.setAllowance(Double.valueOf(allownance));
			unit.setIdPrice(Integer.valueOf(idPrice));
			structuralUnit.add(unit);
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can't do add structural unit operation", e);
		}
	}

	@Override
	public void updateStructuralUnit(String name, String markUp, String tax, String fare, String discount,
			String allownance, String idUnit) throws ServiceException {
		try {
			logger.debug(
					"updateStructuralUnit() name={}, markUp={}, tax={}, fare={}, discount={}, allownace={}, idUnit={}",
					name, markUp, tax, fare, discount, allownance, idUnit);
			DAOFactory daoFactory = DAOFactory.getInstance();
			StructuralUnitDAO structuralUnit = daoFactory.getStructuralUnitDAO();
			StructuralUnit unit = new StructuralUnit();
			unit.setName(name);
			unit.setMarkUp(Double.valueOf(markUp));
			unit.setTax(Double.valueOf(tax));
			unit.setFare(Double.valueOf(fare));
			unit.setDiscount(Double.valueOf(discount));
			unit.setAllowance(Double.valueOf(allownance));
			unit.setIdUnit(Integer.valueOf(idUnit));
			structuralUnit.update(unit);
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can't do update structural unit operation", e);
		}
	}

	@Override
	public void deleteStructuralUnit(String idUnit) throws ServiceException {
		try {
			logger.debug("deleteStructuralUnit() idUnit={}", idUnit);
			DAOFactory daoFactory = DAOFactory.getInstance();
			StructuralUnitDAO structuralUnit = daoFactory.getStructuralUnitDAO();
			structuralUnit.delete(Integer.valueOf(idUnit));
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can't do delete structural unit operation", e);
		}

	}

	@Override
	public List<StructuralUnit> listStructuralUnit(int idPrice) throws ServiceException {
		List<StructuralUnit> unit = null;
		try {
			logger.debug("listStructuralUnit() idPrice={}", idPrice);
			DAOFactory daoFactory = DAOFactory.getInstance();
			StructuralUnitDAO structuralUnit = daoFactory.getStructuralUnitDAO();
			unit = structuralUnit.selectStrucUnitsByIdProduct(idPrice);
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can't do select list categories operation", e);
		}
		return unit;
	}

}
