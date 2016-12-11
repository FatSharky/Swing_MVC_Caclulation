package by.bsk.oracle.command;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bsk.oracle.domain.Dish;
import by.bsk.oracle.domain.PriceCategory;
import by.bsk.oracle.domain.Product;
import by.bsk.oracle.domain.Recipe;
import by.bsk.oracle.domain.ShiftMaster;
import by.bsk.oracle.domain.StructuralUnit;
import by.bsk.oracle.domain.User;
import by.bsk.oracle.service.DishService;
import by.bsk.oracle.service.PriceCategorySevice;
import by.bsk.oracle.service.ProductService;
import by.bsk.oracle.service.RecipeService;
import by.bsk.oracle.service.ShiftMasterService;
import by.bsk.oracle.service.StructuralUnitService;
import by.bsk.oracle.service.UserService;
import by.bsk.oracle.service.exception.ServiceException;
import by.bsk.oracle.service.factory.ServiceFactory;
import by.bsk.oracle.view.util.CheckAccess;
import by.bsk.oracle.view.util.TextFile;

public class ShowJTable {

	private static final Logger logger = LogManager.getLogger(ShowJTable.class);

	private static final String ID = "ID";
	private static final String LOGIN = "Логин";
	private static final String PASSWORD = "Пароль";
	private static final String ROLE = "Роль";
	private static final String ACCESS = "Доступ";
	private static final String NAME = "Наименование";
	private static final String MARK_UP = "Наценка";
	private static final String TAX = "Налог";
	private static final String FARE = "Тр. расходы";
	private static final String DISCOUNT = "Скидка";
	private static final String ALLOWANCE = "Надбавка";
	private static final String FIO = "Фамилия Имя Отечтво";

	private static final String ERROR_MESSAGE = "Не могу создать DefaultTableModel";

	static public DefaultTableModel showTable() {
		ObjectInputStream inputStream = null;
		DefaultTableModel tModel = null;
		try {
			FileInputStream fis = new FileInputStream(TextFile.SESSION_FILE);
			inputStream = new ObjectInputStream(fis);
			User user = (User) inputStream.readObject();
			String[] top = new String[] { ID, LOGIN, PASSWORD, ROLE, ACCESS };
			tModel = new DefaultTableModel();
			tModel.setColumnIdentifiers(top);
			List<User> users = null;
			ServiceFactory serviceFactor = ServiceFactory.getInstance();
			UserService userService = serviceFactor.getUserServie();
			users = userService.selectUsersByIdDivision(user.getDivision().getIdDivision());

			for (int i = 0; i < users.size(); i++) {
				int id = users.get(i).getIdUser();
				String login = users.get(i).getLogin();
				String password = users.get(i).getPassword();
				String role = users.get(i).getRole();
				String access = CheckAccess.checkAccess(users.get(i).getAccess());
				Object[] data = { id, login, password, role, access };
				tModel.addRow(data);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Не могу создать DefaultTableModel");
			logger.error("showTable() error");
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "InputStream не может быть закрыт");
				logger.error("InputStream не может быть закрыт");
			}

		}
		return tModel;
	}

	static public DefaultTableModel showUsersTable(int idUser) {

		DefaultTableModel tModel = null;
		try {
			String[] top = new String[] { ID, LOGIN, PASSWORD, ROLE, ACCESS };
			tModel = new DefaultTableModel();
			tModel.setColumnIdentifiers(top);
			List<User> users = null;
			ServiceFactory serviceFactor = ServiceFactory.getInstance();
			UserService userService = serviceFactor.getUserServie();
			users = userService.selectAllUsersByIdDivision(idUser);
			for (int i = 0; i < users.size(); i++) {
				int id = users.get(i).getIdUser();
				String login = users.get(i).getLogin();
				String password = users.get(i).getPassword();
				String role = users.get(i).getRole();
				String access = CheckAccess.checkAccess(users.get(i).getAccess());
				Object[] data = { id, login, password, role, access };
				tModel.addRow(data);
			}
		} catch (ServiceException e) {
			JOptionPane.showMessageDialog(null, ERROR_MESSAGE);
			logger.error("showUsersTable(int idUser) error");
		}
		return tModel;

	}

	static public DefaultTableModel showOnlyUserTable(int idDivision) {

		DefaultTableModel tModel = null;
		try {
			String[] top = new String[] { ID, LOGIN, PASSWORD, ROLE, ACCESS };
			tModel = new DefaultTableModel();
			tModel.setColumnIdentifiers(top);
			List<User> users = null;
			ServiceFactory serviceFactor = ServiceFactory.getInstance();
			UserService userService = serviceFactor.getUserServie();
			users = userService.selectUsersByIdDivision(idDivision);

			for (int i = 0; i < users.size(); i++) {
				int id = users.get(i).getIdUser();
				String login = users.get(i).getLogin();
				String password = users.get(i).getPassword();
				String role = users.get(i).getRole();
				String access = CheckAccess.checkAccess(users.get(i).getAccess());
				Object[] data = { id, login, password, role, access };
				tModel.addRow(data);
			}
		} catch (ServiceException e) {
			JOptionPane.showMessageDialog(null, ERROR_MESSAGE);
			logger.error(" showOnlyUserTable(int idDivision) error");
		}
		return tModel;

	}

	static public DefaultTableModel insertOnlyUserTable(int idDivision) {

		DefaultTableModel tModel = null;
		try {
			String[] top = new String[] { ID, LOGIN, PASSWORD, ROLE, ACCESS };
			tModel = new DefaultTableModel();
			tModel.setColumnIdentifiers(top);
			List<User> users = null;
			ServiceFactory serviceFactor = ServiceFactory.getInstance();
			UserService userService = serviceFactor.getUserServie();
			users = userService.selectUsersByIdDivision(idDivision);
			for (int i = 0; i < users.size(); i++) {
				int id = users.get(i).getIdUser();
				String login = users.get(i).getLogin();
				String password = users.get(i).getPassword();
				String role = users.get(i).getRole();
				String access = users.get(i).getAccess().toString().toLowerCase().replace('_', ' ');
				Object[] data = { id, login, password, role, access };
				tModel.insertRow(i, data);
			}
		} catch (ServiceException e) {
			JOptionPane.showMessageDialog(null, ERROR_MESSAGE);
			logger.error("insertOnlyUserTable error");
		}
		return tModel;

	}

	static public DefaultTableModel priceCategoryTable(int idDivision) {

		DefaultTableModel tModel = null;
		try {
			String[] top = new String[] { ID, NAME };
			tModel = new DefaultTableModel();
			tModel.setColumnIdentifiers(top);
			List<PriceCategory> priceCategories = null;
			ServiceFactory serviceFactor = ServiceFactory.getInstance();
			PriceCategorySevice priceCategorySevice = serviceFactor.getPriceCategory();
			priceCategories = priceCategorySevice.selectCategoryByIdDivision(idDivision);
			for (int i = 0; i < priceCategories.size(); i++) {
				int id = priceCategories.get(i).getIdPrice();
				String name = priceCategories.get(i).getName();
				Object[] data = { id, name };
				tModel.insertRow(i, data);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, ERROR_MESSAGE);
			logger.error("priceCategoryTable(int idDivision) error");
		}
		return tModel;
	}

	static public DefaultTableModel structuralUnitTable(int idPrice) {
		DefaultTableModel tModel = null;
		try {
			String[] top = new String[] { ID, NAME, MARK_UP, TAX, FARE, DISCOUNT, ALLOWANCE };
			tModel = new DefaultTableModel();
			tModel.setColumnIdentifiers(top);
			List<StructuralUnit> structuralUnit = null;
			ServiceFactory serviceFactor = ServiceFactory.getInstance();
			StructuralUnitService structuralUnitService = serviceFactor.getStructuralUnit();
			structuralUnit = structuralUnitService.listStructuralUnit(idPrice);
			for (int i = 0; i < structuralUnit.size(); i++) {
				int id = structuralUnit.get(i).getIdUnit();
				String name = structuralUnit.get(i).getName();
				double markUp = structuralUnit.get(i).getMarkUp();
				double tax = structuralUnit.get(i).getTax();
				double fare = structuralUnit.get(i).getFare();
				double discount = structuralUnit.get(i).getDiscount();
				double allowance = structuralUnit.get(i).getAllowance();
				Object[] data = { id, name, markUp, tax, fare, discount, allowance };
				tModel.insertRow(i, data);
			}
		} catch (ServiceException e) {
			JOptionPane.showMessageDialog(null, ERROR_MESSAGE);
			logger.error("StructuralUnitTable(int idPrice) error");
		}
		return tModel;
	}

	static public DefaultTableModel productCategoryTable(int idDivision) {
		DefaultTableModel tModel = null;
		try {
			String[] top = new String[] { ID, NAME };
			tModel = new DefaultTableModel();
			tModel.setColumnIdentifiers(top);
			List<Recipe> productCategories = null;
			ServiceFactory serviceFactor = ServiceFactory.getInstance();
			RecipeService productCategoryService = serviceFactor.getRecipeService();
			productCategories = productCategoryService.listCategories(idDivision);
			for (int i = 0; i < productCategories.size(); i++) {
				int id = productCategories.get(i).getIdRecipe();
				String name = productCategories.get(i).getName();
				Object[] data = { id, name };
				tModel.insertRow(i, data);
			}
		} catch (ServiceException e) {
			JOptionPane.showMessageDialog(null, ERROR_MESSAGE);
			logger.error("productCategoryTable(int idDivision) error");
		}
		return tModel;
	}

	static public DefaultTableModel dishTable(int idDivision) {
		DefaultTableModel tModel = null;
		try {
			String[] top = new String[] { ID, NAME };
			tModel = new DefaultTableModel();
			tModel.setColumnIdentifiers(top);
			List<Dish> dishes = null;
			ServiceFactory serviceFactor = ServiceFactory.getInstance();
			DishService dishService = serviceFactor.getDishService();
			dishes = dishService.selectDishByIdDivision(idDivision);
			for (int i = 0; i < dishes.size(); i++) {
				int id = dishes.get(i).getIdDish();
				String name = dishes.get(i).getName();
				Object[] data = { id, name };
				tModel.insertRow(i, data);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, ERROR_MESSAGE);
			logger.error("dishTable(int idDivision) error");
		}
		return tModel;
	}

	static public DefaultTableModel shiftMasterTable(int idUnit) {
		DefaultTableModel tModel = null;
		try {
			String[] top = new String[] { ID, FIO };
			tModel = new DefaultTableModel();
			tModel.setColumnIdentifiers(top);
			List<ShiftMaster> shiftMasters = null;
			ServiceFactory serviceFactor = ServiceFactory.getInstance();
			ShiftMasterService shiftMasterService = serviceFactor.getShiftMasterService();
			shiftMasters = shiftMasterService.selectShiftMasterByIdUnit(idUnit);
			for (int i = 0; i < shiftMasters.size(); i++) {
				int id = shiftMasters.get(i).getIdMaster();
				String name = shiftMasters.get(i).getFio();
				Object[] data = { id, name };
				tModel.insertRow(i, data);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, ERROR_MESSAGE);
			logger.error("shiftMasterTable(int idUnit) error");
		}
		return tModel;
	}

	static public DefaultTableModel productTable(int idUnit) {
		DefaultTableModel tModel = null;
		try {
			String[] top = new String[] { ID, NAME };
			tModel = new DefaultTableModel();
			tModel.setColumnIdentifiers(top);
			List<Product> products = null;
			ServiceFactory serviceFactor = ServiceFactory.getInstance();
			ProductService productService = serviceFactor.getProductService();
			products = productService.listProduct(idUnit);
			for (int i = 0; i < products.size(); i++) {
				int id = products.get(i).getIdProduct();
				String name = products.get(i).getName();
				Object[] data = { id, name };
				tModel.insertRow(i, data);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, ERROR_MESSAGE);
			logger.error("productTable(int idUnit) error");
		}
		return tModel;
	}

}
