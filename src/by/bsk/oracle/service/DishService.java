package by.bsk.oracle.service;

import java.util.List;

import by.bsk.oracle.domain.Dish;
import by.bsk.oracle.service.exception.ServiceException;

public interface DishService {

	void addDish(String name, String idDivision) throws ServiceException;

	void updateDish(String name, String idDish) throws ServiceException;

	void deleteDish(int idDish) throws ServiceException;

	List<Dish> selectDishByIdDivision(int idDivision) throws ServiceException;
}
