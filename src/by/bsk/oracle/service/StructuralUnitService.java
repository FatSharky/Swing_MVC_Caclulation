package by.bsk.oracle.service;

import java.util.List;

import by.bsk.oracle.domain.StructuralUnit;
import by.bsk.oracle.service.exception.ServiceException;

public interface StructuralUnitService {
	void addStructuralUnit(String name, String markUp, String tax, String fare, String discount, String allownance,
			String idPrice) throws ServiceException;

	void updateStructuralUnit(String name, String markUp, String tax, String fare, String discount, String allownance,
			String idUnit) throws ServiceException;

	void deleteStructuralUnit(String idUnit) throws ServiceException;

	List<StructuralUnit> listStructuralUnit(int idPrice) throws ServiceException;
}
