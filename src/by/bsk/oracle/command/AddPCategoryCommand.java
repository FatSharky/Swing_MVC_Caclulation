package by.bsk.oracle.command;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bsk.oracle.service.PriceCategorySevice;
import by.bsk.oracle.service.exception.ServiceException;
import by.bsk.oracle.service.factory.ServiceFactory;

public class AddPCategoryCommand implements ActionListener {
	private static final Logger logger = LogManager.getLogger(AddPCategoryCommand.class);
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
			jLabel.setText("ѕользователь успешно добавлен");
		} catch (ServiceException e1) {
			JOptionPane.showMessageDialog(null, "„то то пошло совершенно не так");
			logger.error("что то пошло совершенно не так");
		}
	}

}
