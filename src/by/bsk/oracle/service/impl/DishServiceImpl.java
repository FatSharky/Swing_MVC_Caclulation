package by.bsk.oracle.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bsk.oracle.dao.DishDAO;
import by.bsk.oracle.dao.exception.DAOException;
import by.bsk.oracle.dao.factory.DAOFactory;
import by.bsk.oracle.domain.Dish;
import by.bsk.oracle.service.DishService;
import by.bsk.oracle.service.exception.ServiceException;

public class DishServiceImpl implements DishService {

	private static final Logger logger = LogManager.getLogger(DishServiceImpl.class);

	@Override
	public void addDish(String name, String idDivision) throws ServiceException {
		try {
			logger.debug("addDish() name={}, idDivision={}", name, idDivision);
			DAOFactory daoFactory = DAOFactory.getInstance();
			DishDAO dishDAO = daoFactory.getDishDAO();
			Dish dish = new Dish();
			dish.setName(name);
			dish.setIdDivision(Integer.valueOf(idDivision));
			dishDAO.add(dish);
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can't do add dish operation", e);
		}

	}

	@Override
	public void updateDish(String name, String idDish) throws ServiceException {
		try {
			logger.debug("updateDish() name={}, idDish={}", name, idDish);
			DAOFactory daoFactory = DAOFactory.getInstance();
			DishDAO dishDAO = daoFactory.getDishDAO();
			Dish dish = new Dish();
			dish.setName(name);
			dish.setIdDish(Integer.valueOf(idDish));
			dishDAO.update(dish);
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can't do update dish operation", e);
		}
	}

	@Override
	public void deleteDish(int idDish) throws ServiceException {
		try {
			logger.debug("deleteDish() idDish={}", idDish);
			DAOFactory daoFactory = DAOFactory.getInstance();
			DishDAO dishDAO = daoFactory.getDishDAO();
			dishDAO.delete(idDish);
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can't do delete dish operation", e);
		}
	}

	@Override
	public List<Dish> selectDishByIdDivision(int idDivision) throws ServiceException {
		List<Dish> dishes = null;
		try {
			logger.debug("selectDishByIdDivision() idDivision={}", idDivision);
			DAOFactory daoFactory = DAOFactory.getInstance();
			DishDAO dishDAO = daoFactory.getDishDAO();
			dishes = dishDAO.selectDishByIdDivision(idDivision);
		} catch (DAOException e) {
			throw new ServiceException("Service layer: can't do select dishes by id divison operation", e);
		}
		return dishes;
	}

}
