package by.bsk.oracle.command;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bsk.oracle.service.ShiftMasterService;
import by.bsk.oracle.service.exception.ServiceException;
import by.bsk.oracle.service.factory.ServiceFactory;

public class DeleteMasterCommand implements ActionListener {

	private static final Logger logger = LogManager.getLogger(DeleteMasterCommand.class);

	private DefaultTableModel tModel = new DefaultTableModel();
	private JTable jTable = new JTable();

	public DeleteMasterCommand(JTable jTable, DefaultTableModel tModel) {
		this.jTable = jTable;
		this.tModel = tModel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		int i = jTable.getSelectedRow();
		String id = tModel.getValueAt(i, 0).toString();
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ShiftMasterService shiftMasterService = serviceFactory.getShiftMasterService();
		try {
			shiftMasterService.deleteShiftMaster(Integer.valueOf(id));
			tModel.removeRow(i);
			jTable.setModel(tModel);
			jTable.getColumnModel().getColumn(0).setMinWidth(0);
			jTable.getColumnModel().getColumn(0).setMaxWidth(0);
		} catch (ServiceException e1) {
			JOptionPane.showMessageDialog(null, "Что то пошло совершенно не так");
			logger.error("что то пошло совершенно не так");
		}

	}

}
