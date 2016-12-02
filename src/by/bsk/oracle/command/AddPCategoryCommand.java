package by.bsk.oracle.command;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import by.bsk.oracle.service.PriceCategorySevice;
import by.bsk.oracle.service.factory.ServiceFactory;

public class AddPCategoryCommand implements ActionListener {
	private JTextField jTextField;
	private JLabel jLabel;
	private JTable jTable;
	private DefaultTableModel jTableModel;
	private int idDivision;

	public AddPCategoryCommand(JTextField jTextField, JLabel jLabel, JTable jTable, DefaultTableModel jTableModel,
			int idDivision) {
		this.jTextField = jTextField;
		this.jLabel = jLabel;
		this.jTable = jTable;
		this.jTableModel = jTableModel;
		this.idDivision = idDivision;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String name = jTextField.getText();
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		PriceCategorySevice priceCategorySevice = serviceFactory.getPriceCategory();
		try {
			int i = 0;
			priceCategorySevice.addCategory(name, String.valueOf(idDivision));
			Object[] data = { i, name };
			jTableModel.insertRow(jTable.getRowCount(), data);
			jLabel.setForeground(Color.GREEN);
			jLabel.setText("Пользователь успешно добавлен");
		} catch (Exception e1) {
			// TODO: handle exception
		}
	}

}
