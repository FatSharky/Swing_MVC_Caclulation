package by.bsk.oracle.command;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import by.bsk.oracle.service.DishService;
import by.bsk.oracle.service.factory.ServiceFactory;

public class AddDishCommand implements ActionListener {
	private JTextField jTextField;
	private JLabel jLabel;
	private JTable jTable;
	private DefaultTableModel jTableModel;
	private int idDivision;

	public AddDishCommand(JTextField jTextField, JLabel jLabel, JTable jTable, DefaultTableModel jTableModel,
			int idDivision) {
		this.jTextField = jTextField;
		this.jLabel = jLabel;
		this.jTable = jTable;
		this.jTableModel = jTableModel;
		this.idDivision = idDivision;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String name = jTextField.getText();
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		DishService dishService = serviceFactory.getDishService();
		try {
			int i = 0;
			dishService.addDish(name, String.valueOf(idDivision));
			Object[] data = { i, name };
			jTableModel.insertRow(jTable.getRowCount(), data);
			jTableModel = ShowJTable.dishTable(idDivision);
			jLabel.setForeground(Color.GREEN);
			jLabel.setText("Блюдо успешно добавлено");
		} catch (Exception e1) {
			// TODO: handle exception
		}

	}

}
