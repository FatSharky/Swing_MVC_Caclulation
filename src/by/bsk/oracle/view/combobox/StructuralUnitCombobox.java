package by.bsk.oracle.view.combobox;

import java.util.List;

import javax.swing.JComboBox;

import by.bsk.oracle.domain.StructuralUnit;
import by.bsk.oracle.service.StructuralUnitService;
import by.bsk.oracle.service.exception.ServiceException;
import by.bsk.oracle.service.factory.ServiceFactory;

public class StructuralUnitCombobox extends JComboBox<StructuralUnit> {

	private static final long serialVersionUID = 1L;

	public StructuralUnitCombobox() {
	
	}
	
	public StructuralUnitCombobox(int idPrice) {
		createComboBox(idPrice);
	}

	public void createComboBox(int idPrice) {
		List<StructuralUnit> structuralUnits = null;
		try {
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			StructuralUnitService structuralUnit = serviceFactory.getStructuralUnit();
			structuralUnits = structuralUnit.listStructuralUnit(idPrice);
			String[] array = new String[structuralUnits.size()];
			for (int i = 0; i < array.length; i++) {
				StructuralUnit stUnit = new StructuralUnit();
				stUnit.setIdUnit(structuralUnits.get(i).getIdUnit());
				stUnit.setName(structuralUnits.get(i).getName());
				addItem(stUnit);
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
