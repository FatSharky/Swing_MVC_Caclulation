package by.bsk.oracle.view.combobox;

import java.util.List;

import javax.swing.JComboBox;

import by.bsk.oracle.domain.PriceCategory;
import by.bsk.oracle.service.PriceCategorySevice;
import by.bsk.oracle.service.exception.ServiceException;
import by.bsk.oracle.service.factory.ServiceFactory;

public class PriceCategoryComboBox extends JComboBox<PriceCategory> {
	private static final long serialVersionUID = 1L;

	public PriceCategoryComboBox(int idDivison) {
		createComboBox(idDivison);
	}

	private void createComboBox(int idDivision) {
		List<PriceCategory> priceCategory = null;
		try {
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			PriceCategorySevice categorySevice = serviceFactory.getPriceCategory();
			priceCategory = categorySevice.selectCategoryByIdDivision(idDivision);
			String[] array = new String[priceCategory.size()];
			for (int i = 0; i < array.length; i++) {
				PriceCategory price = new PriceCategory();
				price.setIdPrice(priceCategory.get(i).getIdPrice());
				price.setName(priceCategory.get(i).getName());
				addItem(price);
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
