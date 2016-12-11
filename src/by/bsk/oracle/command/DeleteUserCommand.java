package by.bsk.oracle.command;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bsk.oracle.service.UserService;
import by.bsk.oracle.service.exception.ServiceException;
import by.bsk.oracle.service.factory.ServiceFactory;

public class DeleteUserCommand implements ActionListener {
	
	private static final Logger logger = LogManager.getLogger(DeleteUserCommand.class);
	
	private JTable jTable = new JTable();
	private JLabel jLabel = new JLabel();
	private DefaultTableModel tModel = new DefaultTableModel();

	public DeleteUserCommand(JTable jTable, DefaultTableModel tModel) {
		this.jTable = jTable;
		this.tModel = tModel;

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		int i = jTable.getSelectedRow();
		String id = tModel.getValueAt(i, 0).toString();
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserServie();
		try {
			jLabel.setBackground(Color.GREEN);
			jLabel.setText("Пользователь успешно удалён");
			userService.deleteUser(id);
			tModel.removeRow(i);
			jTable.setModel(tModel);
			jTable.getColumnModel().getColumn(0).setMinWidth(0);
			jTable.getColumnModel().getColumn(0).setMaxWidth(0);
		} catch (ServiceException e) {
			JOptionPane.showMessageDialog(null, "Что то пошло совершенно не так");
			logger.error("что то пошло совершенно не так");
		}

	}

}
