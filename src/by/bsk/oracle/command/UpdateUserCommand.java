package by.bsk.oracle.command;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import by.bsk.oracle.service.UserService;
import by.bsk.oracle.service.exception.ServiceException;
import by.bsk.oracle.service.factory.ServiceFactory;

public class UpdateUserCommand implements ActionListener {

	private JTextField jLogin = new JTextField();
	private JTextField jPassword = new JTextField();
	private JTable jTable = new JTable();
	private JLabel jLabel = new JLabel();
	private DefaultTableModel tModel = new DefaultTableModel();

	public UpdateUserCommand(JTextField jLogin, JTextField jPassword, JTable jTable, JLabel jLabel,
			DefaultTableModel tModel) {
		this.jLogin = jLogin;
		this.jPassword = jPassword;
		this.jTable = jTable;
		this.jLabel = jLabel;
		this.tModel = tModel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String login = jLogin.getText();
		String password = jPassword.getText();
		int i = jTable.getSelectedRow();
		String id = tModel.getValueAt(i, 0).toString();
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserServie();
		try {
			userService.updateUser(login, password, id);
			jLabel.setBackground(Color.GREEN);
			jLabel.setText("Пользователь успешно обновлен");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
