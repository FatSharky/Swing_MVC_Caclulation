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

import by.bsk.oracle.service.ShiftMasterService;
import by.bsk.oracle.service.exception.ServiceException;
import by.bsk.oracle.service.factory.ServiceFactory;

public class AddMasterCommand implements ActionListener {
	private static final Logger logger = LogManager.getLogger(AddMasterCommand.class);
	private JTextField jTextField;
	private JLabel jLabel;
	private JTable jTable;
	private DefaultTableModel jTableModel;
	private int idUnit;

	public AddMasterCommand(JTextField jTextField, JLabel jLabel, JTable jTable, DefaultTableModel jTableModel,
			int idUnit) {
		this.jTextField = jTextField;
		this.jLabel = jLabel;
		this.jTable = jTable;
		this.jTableModel = jTableModel;
		this.idUnit = idUnit;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String fio = jTextField.getText();
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ShiftMasterService shiftMasterService = serviceFactory.getShiftMasterService();
		try {
			int i = 0;
			shiftMasterService.addShiftMaster(fio, idUnit);
			Object[] data = { i, fio };
			jTableModel.insertRow(jTable.getRowCount(), data);
			jTableModel = ShowJTable.shiftMasterTable(idUnit);
			jLabel.setForeground(Color.GREEN);
			jLabel.setText("Ѕлюдо успешно добавлено");
		} catch (ServiceException e1) {
			JOptionPane.showMessageDialog(null, "„то то пошло совершенно не так");
			logger.error("что то пошло совершенно не так");
		}

	}

}
