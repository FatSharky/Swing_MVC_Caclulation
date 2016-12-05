package by.bsk.oracle.command;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import by.bsk.oracle.domain.PriceCategory;
import by.bsk.oracle.domain.ProductCategory;
import by.bsk.oracle.domain.StructuralUnit;
import by.bsk.oracle.domain.User;
import by.bsk.oracle.service.PriceCategorySevice;
import by.bsk.oracle.service.ProductCategoryService;
import by.bsk.oracle.service.StructuralUnitService;
import by.bsk.oracle.service.UserService;
import by.bsk.oracle.service.factory.ServiceFactory;
import by.bsk.oracle.view.util.CheckAccess;

public class ShowJTable {

	static public DefaultTableModel showTable() {
		ObjectInputStream inputStream = null;
		DefaultTableModel tModel = null;
		try {
			FileInputStream fis = new FileInputStream("temp.txt");
			inputStream = new ObjectInputStream(fis);
			User user = (User) inputStream.readObject();
			String[] top = new String[] { "ID", "Логин", "Пароль", "Роль", "Доступ" };
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
				String access = users.get(i).getAccess().toString().toLowerCase().replace('_', ' ');
				Object[] data = { id, login, password, role, access };
				tModel.addRow(data);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return tModel;
	}

	static public DefaultTableModel showUsersTable(int idUser) {

		DefaultTableModel tModel = null;
		try {
			String[] top = new String[] { "ID", "Логин", "Пароль", "Роль", "Доступ" };
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
				String access = users.get(i).getAccess().toString().toLowerCase().replace('_', ' ');
				Object[] data = { id, login, password, role, access };
				tModel.addRow(data);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return tModel;

	}

	static public DefaultTableModel showOnlyUserTable(int idDivision) {

		DefaultTableModel tModel = null;
		try {
			String[] top = new String[] { "ID", "Логин", "Пароль", "Роль", "Доступ" };
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
		} catch (Exception e) {
			// TODO: handle exception
		}
		return tModel;

	}

	static public DefaultTableModel insertOnlyUserTable(int idDivision) {

		DefaultTableModel tModel = null;
		try {
			String[] top = new String[] { "ID", "Логин", "Пароль", "Роль", "Доступ" };
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
		} catch (Exception e) {
			// TODO: handle exception
		}
		return tModel;

	}

	static public DefaultTableModel priceCategoryTable(int idDivision) {

		DefaultTableModel tModel = null;
		try {
			String[] top = new String[] { "ID", "Наименование" };
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

		}
		return tModel;
	}

	static public DefaultTableModel StructuralUnitTable(int idPrice) {
		DefaultTableModel tModel = null;
		try {
			String[] top = new String[] { "ID", "Наименование", "Наценка", "Налог", "Тр. расходы", "Скидка",
					"Надбавка" };
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
		} catch (Exception e) {

		}
		return tModel;
	}

	static public DefaultTableModel productCategoryTable(int idDivision) {
		DefaultTableModel tModel = null;
		try {
			String[] top = new String[] { "ID", "Наименование" };
			tModel = new DefaultTableModel();
			tModel.setColumnIdentifiers(top);
			List<ProductCategory> productCategories = null;
			ServiceFactory serviceFactor = ServiceFactory.getInstance();
			ProductCategoryService productCategoryService = serviceFactor.getProductCategory();
			productCategories = productCategoryService.listCategories(idDivision);
			for (int i = 0; i < productCategories.size(); i++) {
				int id = productCategories.get(i).getIdProcduct();
				String name = productCategories.get(i).getName();
				Object[] data = { id, name };
				tModel.insertRow(i, data);
			}
		} catch (Exception e) {

		}
		return tModel;
	}
}
