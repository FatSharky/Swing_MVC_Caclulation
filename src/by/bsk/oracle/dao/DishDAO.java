package by.bsk.oracle.dao;

import java.util.List;

import by.bsk.oracle.dao.exception.DAOException;
import by.bsk.oracle.domain.Dish;

public interface DishDAO extends AbstractDAO<Dish> {

	List<Dish> selectDishByIdStructUnit(int idUnit) throws DAOException;
}
