package by.bsk.oracle.service;

import java.util.List;

import by.bsk.oracle.domain.Division;
import by.bsk.oracle.service.exception.ServiceException;

public interface DivisionService {

	void addDivision(String name, String unn) throws ServiceException;

	void deleteDivision(String idDivision) throws ServiceException;

	List<Division> selectAllDivision() throws ServiceException;

}
